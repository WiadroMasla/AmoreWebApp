var id;
var matchInfo;
const loginView = document.getElementById("login");
const homeView = document.getElementById("home");
const matchInfoView = document.getElementById("matchInfo");
const deleteMatchButton = document.getElementById("deleteMatchBtn");

class Id {
    constructor(id) {
        this.id = id;
    }
}

function noMatch() {
    matchInfoView.innerHTML = "Brak dopasowania. Sprawdź ponownie później.";
    deleteMatchButton.style.display = 'none';
}

function isMatch() {
    matchInfoView.innerHTML = "Email:" + matchInfo.email + "<br>"
        + "Imię:" + matchInfo.name + "<br>"
        + "Nazwisko:" + matchInfo.surname + "<br>"
        + "Telefon:" + matchInfo.phone + "<br>"
        + "Adres:" + matchInfo.address + "<br><br>";
    deleteMatchButton.style.display = 'block';
}

function getMatchStatus() {
    $.ajax({
        url: "api/match",
        type: "get",
        data: new Id(id),
        success: function(response) {
            matchInfo = response;

            if(matchInfo == '') {
                noMatch();
            } else {
                isMatch();
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Wystąpił nieznany błąd.");
        }
    });
}

function removeMatch() {
    $.ajax({
            url: "api/match",
            type: "delete",
            data: new Id(id),
            success: function(response) {
                alert("Usunięto dopasowanie.");
                getMatchStatus();
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Wystąpił nieznany błąd.");
            }
        });
}



function switchView() {
    loginView.style.display = 'none';
    getMatchStatus();
    homeView.style.display = 'block';
}

$(document).ready(function() {
    $('#loginForm').validate({

        rules: {
                email: 'required',
                password: 'required'
            },
        messages: {
                email: 'Pole jest wymagane',
                password: 'Pole jest wymagane'
            },
        submitHandler: function(form) {
            $.ajax({
                url: form.action,
                type: form.method,
                data: $(form).serialize(),
                success: function(response) {
                    id = response.id;
                    switchView();
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log($(form).serialize());
                    var err = eval("(" + XMLHttpRequest.responseText + ")");
                    alert(err.message);
                }
            });
        }
    });
});

console.log("loaded");