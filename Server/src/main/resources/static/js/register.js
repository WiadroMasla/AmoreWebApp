$(document).ready(function() {
    $('#form').validate({

        rules: {
                email: {
                    required: true,
                    email: true
                },
                password: 'required',
                name: 'required',
                surname: 'required',
                gender: 'required'
            },
        messages: {


                email: {
                    required: 'Pole jest wymagane',
                    email: 'Niepoprawny adres email.'
                },
                password: 'Pole jest wymagane',
                name: 'Pole jest wymagane',
                surname: 'Pole jest wymagane',
                gender: 'Proszę wybrać jedną z opcji'
            },
        submitHandler: function(form) {
            $.ajax({
                url: form.action,
                type: form.method,
                data: $(form).serialize(),
                success: function(response) {
                    alert("Dodano użytkownika.");
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
console.log("loaded js");