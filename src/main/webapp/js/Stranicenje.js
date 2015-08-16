$(document).ready(function () {
    var handler = function () {
        
        $.ajax({
            url: 'services/StraniceGrid?strana=' + $(this).text(),
            success: function (data) {
                $('#table').empty();
                $('#table').append(data);
                $(".callservice").bind("click", handler);
            }
        });
    };
    $.ajax({
        url: 'services/StraniceGrid?strana=1',
        success: function (data) {
            $('#table').empty();
            $('#table').append(data);
            $(".callservice").bind("click", handler);
        }
    });
});