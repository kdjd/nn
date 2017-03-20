/**
 * Created by Huan on 2017/3/16.
 */
define([], function () {

    $("#submit").on("click", function (e) {
        var user = {
            name: "4",
            phone: "3",
            email: "2",
            sex: "1"
        };
        $.ajax({
            url: "http://localhost:8080/nns/main/register",
            type: "POST",
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(user)
        }).done(function (result) {
            console.log(result);
        });
    });

});