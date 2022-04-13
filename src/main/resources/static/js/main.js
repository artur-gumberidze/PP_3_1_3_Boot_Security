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
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #id').val('');
            $('.myForm #username').val('');
            $('.myForm #lastname').val('');
            $('.myForm #email').val('');
            $('.myForm #password');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});