async function loadFolder(folderName) {

    const container = document.getElementById("cards-container");
    container.innerHTML = "";

    const response = await fetch(`data/${folderName}.json`);
    const files = await response.json();

    for (let file of files) {

        const codeResponse = await fetch(`${folderName}/${file.filename}`);
        const codeText = await codeResponse.text();

        const card = document.createElement("div");
        card.className = "card";

        card.innerHTML = `
            <h3>${file.displayName}</h3>
            <pre>${codeText}</pre>
        `;

        container.appendChild(card);
    }
}