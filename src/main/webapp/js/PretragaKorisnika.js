$(document).ready(function () {
    $("#pretaga").keyup(function () {
        var value = $(this).val();
        if (value != "")
            $.ajax({
                url: 'services/PronadjiSpecijalnuPonudu?naziv=' + value,
                data: {
                    naziv: value
            },
            success: function (data) {
                $('#info').empty();
                $('#info').append(data);
            }
        });
    });
});