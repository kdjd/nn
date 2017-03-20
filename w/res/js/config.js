/**
 * Created by Huan on 2017/3/15.
 */
require.config({
    shim: {
        "bootstrap": {"deps": ['jquery']}
    },
    paths: {
        jquery: "plugin/jquery-3.2.0.min",
        // jqueryForm: "plugin/jquery.form.min",
        jqueryValidate: "plugin/jquery.validate.min",
        bootstrap: "plugin/bootstrap.min",
        md5: "plugin/md5.min",
        common: "common",
        service: "service"
    }

});
define(['jquery', 'bootstrap', 'jqueryValidate', 'common'], function () {

    loadController();

    function loadController() {
        var html = window.location.href.toString();
        var paramsIndex = html.indexOf('?');
        if (paramsIndex > 0) {
            html = html.substr(0, paramsIndex);
        }
        var ctrl = html.replace('.html', '.js');
        require([ctrl]);
    }
});