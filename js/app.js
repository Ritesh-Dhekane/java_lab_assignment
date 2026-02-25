// Dark Mode Toggle
const toggle = document.getElementById("themeToggle");

if (toggle) {

    function updateIcon() {
        toggle.textContent =
            document.body.classList.contains("dark") ? "🌙" : "☀️";
    }

    toggle.addEventListener("click", () => {
        document.body.classList.toggle("dark");
        localStorage.setItem(
            "theme",
            document.body.classList.contains("dark") ? "dark" : "light"
        );
        updateIcon();
    });

    if (localStorage.getItem("theme") === "dark") {
        document.body.classList.add("dark");
    }

    updateIcon();
}

// Viewer Page Logic
const params = new URLSearchParams(window.location.search);
const folder = params.get("folder");

if (folder) {
    document.getElementById("folderTitle").innerText = folder.toUpperCase();
    loadFiles(folder);
}

async function loadFiles(folder) {

    const container = document.getElementById("cards-container");
    const fileList = document.getElementById("fileList");

    container.innerHTML = "";
    fileList.innerHTML = "";

    let files = [];

    try {
        const res = await fetch(`data/${folder}.json`);
        files = await res.json();
    } catch {
        // If JSON not present → auto-detect directory
        const res = await fetch(folder);
        const text = await res.text();

        const parser = new DOMParser();
        const doc = parser.parseFromString(text, "text/html");

        files = [...doc.querySelectorAll("a")]
            .map(a => a.getAttribute("href"))
            .filter(f => f.endsWith(".java"))
            .map(f => ({ filename: f, displayName: f }));
    }

    for (let file of files) {

        const li = document.createElement("li");
        li.innerText = file.displayName;
        li.onclick = () => scrollToCard(file.filename);
        fileList.appendChild(li);

        const codeRes = await fetch(`${folder}/${file.filename}`);
        const code = await codeRes.text();

        const card = document.createElement("div");
        card.className = "card";
        card.id = file.filename;

        card.innerHTML = `
            <h3>${file.displayName}</h3>
            <pre><code class="language-java">${code}</code></pre>
        `;

        container.appendChild(card);
    }

    Prism.highlightAll();
}

function scrollToCard(id) {
    document.getElementById(id).scrollIntoView({behavior:"smooth"});
}