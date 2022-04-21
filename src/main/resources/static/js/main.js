
$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') {
            $.get(href, function (user, status) {
                $('.myForm #id').val(user.id);
                $('.myForm #username').val(user.username);
                $('.myForm #lastname').val(user.lastname);
                $('.myForm #email').val(user.email);
                $('.myForm #password').val(user.password);
                $('.myForm #age').val(user.age);
            });
            $('.myForm #exampleModal').modal();
        } else if (text==='New') {
            $("#exampleModals").css("display", "block")
            $("#firsDiv").css("display", "none");
        }

    });

    $('#tBtn').click(function () {
        $("#exampleModals").css("display", "none")
        $("#firsDiv").css("display", "block");
    })

    $('#btn').click(function() {
        $(this).css("background-color", "aqua")
        $("#admbtn").css("background-color", "white")
        $("#firsDiv").css("display", "none");
        $("#offDiv").css("display", "block");
    });

    $('#admbtn').click(function() {
        $(this).css("background-color", "aqua")
        $("#btn").css("background-color", "white")

    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});

