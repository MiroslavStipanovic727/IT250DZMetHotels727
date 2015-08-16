$(document).ready(function () {
    $("#pretaga").keyup(function () {
        var value = $(this).val();
        if (value != ""){
            $.ajax({
                url: 'services/PronadjiSpecijalnuPonudu?naziv=' + value,
                data: {
                    naziv: value
            },
            success: function (data) {
                $('#table').empty();
                $('#table').append(data);
            }
        });
    } else {
        $.ajax({
        url: 'services/StraniceSPGrid?strana=1',
        success: function (data) {
            $('#table').empty();
            $('#table').append(data);
            $(".callservice").bind("click", handler);
        }
    });
    }
    });
    var handler = function () {
        
        $.ajax({
            url: 'services/StraniceSPGrid?strana=' + $(this).text(),
            success: function (data) {
                $('#table').empty();
                $('#table').append(data);
                $(".callservice").bind("click", handler);
            }
        });
    };
    $.ajax({
        url: 'services/StraniceSPGrid?strana=1',
        success: function (data) {
            $('#table').empty();
            $('#table').append(data);
            $(".callservice").bind("click", handler);
        }
    });
});