let emailList = [];
let traitList = [];
const emailResultBox = document.getElementById("emailResultBox");
const emailInputBox = document.getElementById("emailInput");
const traitResultBox = document.getElementById("traitResultBox");
const traitInputBox = document.getElementById("traitInput");
const valueInputBox = document.getElementById("valueInput");
const traitsForm = document.getElementById("traitsForm");
const tableBody = document.getElementById("tableBody");
let personId = null;
let wantedTraits = null;
let traitsMap = null;
let inverseTraitsMap = null;
let result = null;

class TraitAndValue {
    constructor(name, value) {
        this.name = name;
        this.value = value;
    }
}

function selectEmailInput(email) {
    return function() {
        emailInputBox.value = email;
        while(emailResultBox.hasChildNodes()) {
            emailResultBox.removeChild(emailResultBox.childNodes[0]);
        }
    }
}

function displayEmails(result) {
    var list = document.createElement("ul");
    if(emailResultBox.hasChildNodes()) {
        emailResultBox.removeChild(emailResultBox.childNodes[0]);
    }
    result.forEach(e => {
        var li = document.createElement("li");
        li.addEventListener("click", selectEmailInput(e));
        li.innerHTML = e;
        list.appendChild(li);
    });


    emailResultBox.appendChild(list);
    console.log(emailResultBox.innerHtml);
}

emailInputBox.addEventListener("input", function(event) {
    emailOnKeyPress();
})

function emailOnKeyPress() {
    console.log("Key pressed");
    let result = [];
    let input = emailInputBox.value;
    if(input.length) {
        result = emailList.filter( (email) => {
            return email.toLowerCase().includes(input.toLowerCase());
        });
    }
    displayEmails(result);
}




function selectTraitInput(trait) {
    return function() {
        traitInputBox.value = trait;
        while(traitResultBox.hasChildNodes()) {
            traitResultBox.removeChild(traitResultBox.childNodes[0]);
        }
    }
}

function displayTraits(result) {
    var list = document.createElement("ul");
    if(traitResultBox.hasChildNodes()) {
        traitResultBox.removeChild(traitResultBox.childNodes[0]);
    }
    result.forEach(e => {
        var li = document.createElement("li");
        li.addEventListener("click", selectTraitInput(e));
        li.innerHTML = e;
        list.appendChild(li);
    });


    traitResultBox.appendChild(list);
    console.log(traitResultBox.innerHtml);
}

traitInputBox.addEventListener("input", function(event) {
    traitOnKeyPress();
})

function traitOnKeyPress() {
    console.log("Key pressed");
    let result = [];
    let input = traitInputBox.value;
    if(input.length) {
        result = traitList.filter( (trait) => {
            return trait.toLowerCase().includes(input.toLowerCase());
        });
    }
    displayTraits(result);
}


function traitAddButton() {
    
    if(!inverseTraitsMap.has(traitInputBox.value)  || valueInputBox.value == "") {
        return undefined;
    }

    $.ajax({
        url: "../api/wantedTrait",
        method: "post",
        data: {
            personId: personId,
            traitId: inverseTraitsMap.get(traitInputBox.value),
            value: valueInputBox.value
        },
        success: function(response) {
            loadPersonTraits();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Wystąpił nieznany błąd.");
        }
    });
}

function traitRemoveButton() {
    //TODO:
    if(!inverseTraitsMap.has(traitInputBox.value)) {
        return undefined;
    }
    
    $.ajax({
        url: "../api/wantedTrait",
        method: "delete",
        data: {
            personId: personId,
            traitId: inverseTraitsMap.get(traitInputBox.value)
        },
        success: function(response) {
            loadPersonTraits();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Wystąpił nieznany błąd.");
        }
    });
}


function loadPersonTraits() {
    while(tableBody.hasChildNodes()) {
        tableBody.removeChild(tableBody.childNodes[0]);
    }

    $.ajax({
        url: "../api/wantedTrait",
        method: "get",
        data: {
            id: personId
        },
        success: function(response) {
            wantedTraits = response.map(e => {
                const traitName = traitsMap.get(e.traitId);
                return new TraitAndValue(traitName, e.value);
            });
            
            wantedTraits.forEach(e => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${e.name}</td>
                    <td>${e.value}</td>
                `;
                tableBody.appendChild(row);
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Wystąpił nieznany błąd.");
        }
    });
}


function emailButtonFunction() {
    $.ajax({
        url: "../api/person/email",
        method: "get",
        data: {
            email: emailInputBox.value
        },
        success: function(response) {
            personId = response.id;
            traitsForm.style.display = "block";
            loadPersonTraits();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log($(form).serialize());
            var err = eval("(" + XMLHttpRequest.responseText + ")");
            alert(err.message);
        }
    });
    
}



$(document).ready(function () {
    $.ajax({
    url: "../api/people",
    method: "get",
    data: null,
    success: function(response) {
        emailList = response.map(element => {
            return element.email;
        });
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert("Wystąpił nieoczekiwany błąd");
    }
    });

    $.ajax({
        url: "../api/trait",
        method: "get",
        data: null,
        success: function(response) {
            traitsMap = new Map();
            inverseTraitsMap = new Map();
            response.forEach(e => {
                traitsMap.set(e.id, e.name);
                inverseTraitsMap.set(e.name, e.id);
            });
            traitList = response.map(e => {
                return e.name;
            })
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("Wystąpił nieoczekiwany błąd");
        }
        });

});

console.log("loaded wantedTraits.js");
