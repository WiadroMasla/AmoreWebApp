
const root = document.getElementById("root");
var debug = null;


function buttonAction() {
    while(root.hasChildNodes()) {
        root.removeChild(root.childNodes[0]);
    }

    $.ajax({
        url: "../api/admin/matcher",
        method: "post",
        data: null,
        success: function(response) {
            debug = response;

            response.forEach((personPair) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${personPair.person1.email}</td>
                    <td>${personPair.person1.name}</td>
                    <td>${personPair.person1.surname}</td>
                    <td>${personPair.person2.email}</td>
                    <td>${personPair.person2.name}</td>
                    <td>${personPair.person2.surname}</td>
                `;
                root.appendChild(row);
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Wystąpił nieoczekiwany błąd");
        }
    });
}

console.log("loaded");