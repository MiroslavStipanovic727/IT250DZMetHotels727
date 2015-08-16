$(document).ready(function () {
    $("#pretaga").keyup(function () {
        var value = $(this).val();
        if (value != "")
            $.ajax({
                url: 'services/PronadjiKorisnika?prezime=' + value,
                data: {
                    prezime: value
            },
            success: function (data) {
                $('#info').empty();
                $('#info').append(data);
            }
        });
    });
});