const themeToggle = document.getElementById("themeToggle");
const prismLightTheme = document.getElementById("prismLightTheme");
const prismDarkTheme = document.getElementById("prismDarkTheme");

function applyTheme(theme) {
    const isDark = theme === "dark";
    document.body.classList.toggle("dark", isDark);

    if (themeToggle) {
        themeToggle.textContent = isDark ? "Light" : "Dark";
    }

    if (prismLightTheme) {
        prismLightTheme.disabled = isDark;
    }

    if (prismDarkTheme) {
        prismDarkTheme.disabled = !isDark;
    }
}

if (themeToggle) {
    const savedTheme = localStorage.getItem("theme") || "light";
    applyTheme(savedTheme);

    themeToggle.addEventListener("click", () => {
        const nextTheme = document.body.classList.contains("dark") ? "light" : "dark";
        localStorage.setItem("theme", nextTheme);
        applyTheme(nextTheme);
    });
}

const params = new URLSearchParams(window.location.search);
const folder = params.get("folder");
const folderTitle = document.getElementById("folderTitle");
const cardsContainer = document.getElementById("cards-container");
const fileList = document.getElementById("fileList");
const folderList = document.getElementById("folderList");
const sidebarToggle = document.getElementById("sidebarToggle");
const sidebar = document.getElementById("sidebar");
const instructionsModal = document.getElementById("instructionsModal");
const instructionsTitle = document.getElementById("instructionsTitle");
const instructionsSubtitle = document.getElementById("instructionsSubtitle");
const instructionsContent = document.getElementById("instructionsContent");
const closeModalButton = document.getElementById("closeModal");

const state = {
    currentFolder: folder,
    manifest: null,
    sections: [],
    activeSectionIndex: 0
};

if (sidebarToggle) {
    sidebarToggle.addEventListener("click", () => {
        const isOpen = document.body.classList.toggle("sidebar-open");
        sidebarToggle.setAttribute("aria-expanded", String(isOpen));
    });
}

if (instructionsModal) {
    instructionsModal.addEventListener("click", (event) => {
        if (event.target.dataset.closeModal === "true") {
            closeInstructionsModal();
        }
    });
}

if (closeModalButton) {
    closeModalButton.addEventListener("click", closeInstructionsModal);
}

document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
        closeInstructionsModal();
    }
});

if (folder && folderTitle && cardsContainer && fileList) {
    folderTitle.textContent = folder.toUpperCase();
    loadViewer(folder);
}

async function loadViewer(rootFolder) {
    try {
        const manifest = await loadManifest(rootFolder);
        state.manifest = manifest;
        state.sections = normalizeManifest(rootFolder, manifest);
        state.activeSectionIndex = 0;
        renderFolderNavigation();
        renderActiveSection();
    } catch (error) {
        renderError(`Unable to load ${rootFolder}. ${error.message}`);
    }
}

async function loadManifest(rootFolder) {
    try {
        const response = await fetch(`data/${rootFolder}.json`);
        if (!response.ok) {
            throw new Error("Manifest not found");
        }
        return await response.json();
    } catch (jsonError) {
        return await scanDirectoryManifest(rootFolder);
    }
}

async function scanDirectoryManifest(rootFolder) {
    const rootEntries = await fetchDirectoryEntries(rootFolder);
    const directories = rootEntries.filter((entry) => entry.type === "directory");

    if (directories.length > 0) {
        const sections = [];

        for (const dir of directories) {
            const basePath = `${rootFolder}/${dir.name}`;
            const files = await fetchDirectoryEntries(basePath);
            const visibleFiles = files
                .filter((entry) => entry.type === "file")
                .filter((entry) => isSupportedFile(entry.name))
                .map((entry) => ({
                    filename: entry.name,
                    displayName: entry.name
                }));

            sections.push({
                name: dir.name,
                path: basePath,
                files: visibleFiles
            });
        }

        return { sections };
    }

    return rootEntries
        .filter((entry) => entry.type === "file")
        .filter((entry) => isSupportedFile(entry.name))
        .map((entry) => ({
            filename: entry.name,
            displayName: entry.name
        }));
}

async function fetchDirectoryEntries(path) {
    const response = await fetch(path);
    if (!response.ok) {
        throw new Error(`Cannot inspect ${path}`);
    }

    const html = await response.text();
    const doc = new DOMParser().parseFromString(html, "text/html");

    return [...doc.querySelectorAll("a")]
        .map((anchor) => anchor.getAttribute("href"))
        .filter(Boolean)
        .filter((href) => href !== "../" && href !== "./")
        .map((href) => href.replace(/\/$/, ""))
        .filter((name) => name.length > 0)
        .map((name) => ({
            name,
            type: hasFileExtension(name) ? "file" : "directory"
        }));
}

function normalizeManifest(rootFolder, manifest) {
    if (Array.isArray(manifest)) {
        return [
            {
                name: rootFolder.toUpperCase(),
                path: rootFolder,
                files: manifest
            }
        ];
    }

    if (manifest && Array.isArray(manifest.sections)) {
        return manifest.sections.map((section) => ({
            name: section.name,
            path: section.path || `${rootFolder}/${section.name}`,
            files: section.files || []
        }));
    }

    throw new Error("Unsupported manifest format");
}

function renderFolderNavigation() {
    if (!folderList) {
        return;
    }

    folderList.innerHTML = "";

    const multipleSections = state.sections.length > 1;

    state.sections.forEach((section, index) => {
        const item = document.createElement("li");
        item.textContent = section.name;
        item.classList.toggle("active", state.activeSectionIndex === index);
        item.addEventListener("click", () => {
            state.activeSectionIndex = index;
            renderFolderNavigation();
            renderActiveSection();
            if (window.innerWidth <= 900) {
                document.body.classList.remove("sidebar-open");
                if (sidebarToggle) {
                    sidebarToggle.setAttribute("aria-expanded", "false");
                }
            }
        });
        folderList.appendChild(item);
    });

    const questionsHeader = sidebar ? sidebar.querySelector(".sidebar-section h3") : null;
    if (questionsHeader) {
        questionsHeader.textContent = multipleSections ? "Questions" : "Files";
    }
}

async function renderActiveSection() {
    const section = state.sections[state.activeSectionIndex];

    if (!section) {
        renderError("No files found.");
        return;
    }

    const files = section.files.filter((file) => getBaseName(file.filename) !== "RUN_INSTRUCTIONS.txt");
    renderFileList(files);

    cardsContainer.innerHTML = "";

    if (files.length === 0) {
        cardsContainer.innerHTML = `<div class="empty-state">No supported files found in ${section.name}.</div>`;
        return;
    }

    const instructionsFile = section.files.find((file) => getBaseName(file.filename) === "RUN_INSTRUCTIONS.txt");

    for (const file of files) {
        const sourcePath = resolveFilePath(section, file);
        const code = await fetchText(sourcePath);
        const card = createFileCard(section, file, code, instructionsFile);
        cardsContainer.appendChild(card);
    }

    if (window.Prism) {
        Prism.highlightAllUnder(cardsContainer);
    }
}

function renderFileList(files) {
    if (!fileList) {
        return;
    }

    fileList.innerHTML = "";

    files.forEach((file) => {
        const item = document.createElement("li");
        item.textContent = file.displayName || file.filename;
        item.addEventListener("click", () => scrollToCard(file.filename));
        fileList.appendChild(item);
    });
}

function createFileCard(section, file, code, instructionsFile) {
    const card = document.createElement("article");
    const baseName = getBaseName(file.filename);
    const extension = getExtension(baseName);
    const label = file.displayName || baseName;
    const badge = getBadge(extension);
    const languageClass = getLanguageClass(extension);

    card.className = "card";
    card.id = makeCardId(baseName);

    const actionsHtml = instructionsFile
        ? `<button class="action-btn" type="button" data-instructions-path="${resolveFilePath(section, instructionsFile)}" data-section-name="${escapeHtml(section.name)}">View Run Instructions</button>`
        : "";

    let extraContent = "";

    if (extension === "html") {
        extraContent = `
            <div class="html-preview">
                <p class="preview-label">Live Preview</p>
                <iframe class="preview-frame" title="${escapeHtml(label)} preview" srcdoc="${escapeAttribute(code)}" sandbox="allow-forms"></iframe>
            </div>
        `;
    } else if (extension === "jsp") {
        extraContent = `
            <div class="note-box">
                <p class="note-label">Preview Status</p>
                <span class="jsp-badge">JSP preview requires Apache Tomcat runtime</span>
            </div>
        `;
    }

    card.innerHTML = `
        <div class="card-header">
            <div class="card-title-block">
                <h3 class="card-title">${escapeHtml(label)}</h3>
                <p class="card-subtitle">${escapeHtml(section.name)}</p>
            </div>
            <span class="badge ${badge.className}">${badge.text}</span>
        </div>
        <div class="card-actions">${actionsHtml}</div>
        <div class="card-body">
            <pre><code class="${languageClass}">${escapeHtml(code)}</code></pre>
            ${extraContent}
        </div>
    `;

    const instructionsButton = card.querySelector("[data-instructions-path]");
    if (instructionsButton) {
        instructionsButton.addEventListener("click", async () => {
            const instructionsPath = instructionsButton.dataset.instructionsPath;
            const sectionName = instructionsButton.dataset.sectionName;
            const instructions = await fetchText(instructionsPath);
            openInstructionsModal(`Run Instructions`, `${sectionName}`, instructions);
        });
    }

    return card;
}

async function fetchText(path) {
    const response = await fetch(path);
    if (!response.ok) {
        throw new Error(`Cannot load ${path}`);
    }
    return await response.text();
}

function openInstructionsModal(title, subtitle, content) {
    if (!instructionsModal || !instructionsContent || !instructionsTitle || !instructionsSubtitle) {
        return;
    }

    instructionsTitle.textContent = title;
    instructionsSubtitle.textContent = subtitle;
    instructionsContent.innerHTML = `<pre>${escapeHtml(content)}</pre>`;
    instructionsModal.classList.remove("hidden");
    instructionsModal.setAttribute("aria-hidden", "false");
}

function closeInstructionsModal() {
    if (!instructionsModal) {
        return;
    }

    instructionsModal.classList.add("hidden");
    instructionsModal.setAttribute("aria-hidden", "true");
}

function renderError(message) {
    if (cardsContainer) {
        cardsContainer.innerHTML = `<div class="empty-state">${escapeHtml(message)}</div>`;
    }
}

function getExtension(filename) {
    return filename.includes(".") ? filename.split(".").pop().toLowerCase() : "";
}

function getBaseName(path) {
    return path.split("/").pop();
}

function getLanguageClass(extension) {
    if (extension === "java") {
        return "language-java";
    }

    if (extension === "html" || extension === "jsp" || extension === "xml") {
        return "language-markup";
    }

    return "language-none";
}

function getBadge(extension) {
    const badgeMap = {
        java: { text: "JAVA", className: "badge-java" },
        jsp: { text: "JSP", className: "badge-jsp" },
        html: { text: "HTML", className: "badge-html" },
        txt: { text: "DOCS", className: "badge-docs" },
        xml: { text: "XML", className: "badge-xml" }
    };

    return badgeMap[extension] || { text: extension.toUpperCase() || "FILE", className: "badge-docs" };
}

function isSupportedFile(filename) {
    const extension = getExtension(filename);
    return ["java", "jsp", "html", "txt", "xml"].includes(extension);
}

function hasFileExtension(name) {
    return /\.[a-z0-9]+$/i.test(name);
}

function makeCardId(filename) {
    return `card-${filename.replace(/[^a-z0-9_-]/gi, "-")}`;
}

function scrollToCard(filename) {
    const card = document.getElementById(makeCardId(getBaseName(filename)));
    if (card) {
        card.scrollIntoView({ behavior: "smooth", block: "start" });
    }
}

function resolveFilePath(section, file) {
    if (file.path) {
        return file.path;
    }

    return `${section.path}/${file.filename}`;
}

function escapeHtml(text) {
    return text
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;");
}

function escapeAttribute(text) {
    return text
        .replace(/&/g, "&amp;")
        .replace(/"/g, "&quot;");
}
