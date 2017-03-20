/**
 * Created by Huan on 2017/3/15.
 */
define(['service/a.service'], function (service) {

    $("#button").on('click', function () {
        $.ajax({
            url: "http://localhost:8080/nns/main/",
            type: "GET"
        }).done(function (result) {
            console.log(result);
        });
    });

});