/**
 * Created by Huan on 2017/3/17.
 */
/**
 * Created by Huan on 2017/3/16.
 */
define(['service/user'], function (service) {

    // $("#button").on('click', function () {
    //     var user = {
    //         name: 'name',
    //         password: 'password',
    //         email: '12@qq.com'
    //     };
    //     service.register(user, null);
    // })

    $("#form").validate({
        submitHandler: function () {
            var user = {};
            user.name = $("#name").val();
            user.email = $("#email").val();
            user.password = $("#password").val();

            service.register(user, function (result) {
                if (result.header.success) {
                    window.location = "../main/main.html";
                }
            });

            console.log(user);
        },
        rules: {
            name: {
                required: true,
                nameValidator: true
            },
            password: {
                required: true,
                minlength: 5
            },
            confirm_password: {
                required: true,
                minlength: 5,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            name: {
                required: "请输入用户名"
            },
            password: {
                required: "请输入密码",
                minlength: "密码长度不能小于5位"
            },
            confirm_password: {
                required: "请输入密码",
                minlength: "密码长度不能小于5位",
                equalTo: "请保持密码输入一致"
            },
            email: {
                required: "请输入邮箱地址",
                email: "请输入正确的邮箱地址"
            }
        }
    });

});