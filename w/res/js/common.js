/**
 * Created by Huan on 2017/3/20.
 */
define(['jquery'], function ($) {
    $.validator.addMethod('nameValidator', function (value) {
        var reg = /^[A-Za-z0-9]+$/;
        return reg.test(value);
    }, '用户名只能包含数字,字母');
});