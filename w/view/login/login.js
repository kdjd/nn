/**
 * Created by Huan on 2017/3/17.
 */
define(['service/user'], function (service) {
    $("#form").validate({
        submitHandler: function () {
            $(".login-info").hide();

            var login = $("#name").val();
            var password = $("#password").val();

            service.login(login, password, function (result) {
                if (result.header.success) {
                    var r = result.body;
                    console.log(r);
                    if (r.result == 'noUser') {
                        $("#noUser").show();
                    } else if (r.result == 'wrongPassword') {
                        $("#wrongPassword").show();
                    } else if (r.result == 'success') {
                        window.location.href = "../main/main.html";
                    }
                }
            });
        },
        rules: {
            name: {
                required: true,
                nameValidator: true
            },
            password: {
                required: true
            }
        },
        messages: {
            name: {
                required: "请输入用户名"
            },
            password: {
                required: "请输入密码"
            }
        }
    });

});