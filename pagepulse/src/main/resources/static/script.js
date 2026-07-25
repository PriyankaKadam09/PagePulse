function checkURL() {

    let url = document.getElementById("url").value;
    let result = document.getElementById("result");

    result.innerHTML = "Checking website... ⏳";

    fetch("/api/audit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            url: url
        })
    })
        .then(response => response.json())
        .then(data => {
            let history = document.getElementById("history");

            let li = document.createElement("li");

            let now = new Date();

            li.innerHTML = `
<b>${data.url || "Invalid URL"}</b><br>
Status: ${data.status}<br>
Checked: ${now.toLocaleString()}
`;

            history.prepend(li);


            if(data.reachable) {

                result.innerHTML = `
            <div class="success">
                <h3>✅ Website Active</h3>
                <p><b>URL:</b> ${data.url}</p>
                <p><b>Status:</b> ${data.status}</p>
                <p><b>Response Time:</b> ${data.responseTime} ms</p>
                <p>${data.message}</p>
            </div>
            `;

            } else {

                result.innerHTML = `
            <div class="error">
                <h3>❌ Website Down</h3>
                <p>${data.message}</p>
            </div>
            `;

            }

        })
        .catch(error => {
            result.innerHTML = "Error: " + error;
        });


}
function clearHistory() {
    document.getElementById("history").innerHTML = "";
}
document.getElementById("url").addEventListener("keypress", function(event) {

    if (event.key === "Enter") {
        checkURL();
    }

});

