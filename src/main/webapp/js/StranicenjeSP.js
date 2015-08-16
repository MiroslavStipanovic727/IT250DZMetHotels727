$(document).ready(function () {
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