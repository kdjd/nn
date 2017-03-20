/**
 * Created by Huan on 2017/3/17.
 */
define([], function () {

    var basePath = "http://localhost:8080/nns";
    var userPath = "/user";

    return {

        register: function (user, done) {
            $.ajax({
                url: basePath + userPath + "/register",
                type: "POST",
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(user)
            }).done(done);
        },

        getinfo: function (success) {
            $.ajax({
                type: "GET",
                url: basePath + userPath + "/info"
            }).done(success);
        },

        exportJson: function () {
            var url = basePath + userPath + "/export/json";
            if ($('#downloadcsv').length <= 0)
                $('body').append("<iframe id=\"downloadcsv\" style=\"display:none\"></iframe>");
            $('#downloadcsv').attr('src', url);
        },

        uploadFile: function (data) {
            var xhr = new XMLHttpRequest();
            xhr.open("post", basePath + userPath + "/upload?type=ajax", true);
            xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
            xhr.withCredentials = true;
            xhr.send(data);
            xhr.addEventListener('load', function (e) {
            });
        }
    }
});