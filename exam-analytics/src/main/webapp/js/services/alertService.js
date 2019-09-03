(function () {
    'use strict';

    angular.module('mainApp')
    .factory('AlertService', AlertService);

    AlertService.$inject = ['$http'];
    function AlertService($http) {
        var service = {};

        service.alertError = alertError;
        service.alertSuccess = alertSuccess;

        return service;

        // arrayMessage MUST be a json!
        function alertError(arrayMessage) {
            var msgBox = angular.element(document.querySelector('#message-box'));
            var msgBoxContent = angular.element(document.querySelector('#message-box-content'));

            var errorMsg = "";
            for (var key in arrayMessage)
                if (arrayMessage[key].constructor === Array)
                    for (var msg in arrayMessage[key])
                        errorMsg += arrayMessage[key][msg];
                else
                    errorMsg += arrayMessage[key];

            msgBoxContent.text(errorMsg);
            msgBox.removeClass("fade");
            msgBox.addClass("show");
            setTimeout(function () {
                msgBox.removeClass("show");
                msgBox.addClass("fade");
            }, 10000);
        }

        // arrayMessage MUST be a string!
        function alertSuccess(message) {
            var msgBox = angular.element(document.querySelector('#message-box'));
            var msgBoxContent = angular.element(document.querySelector('#message-box-content'));
            msgBoxContent.text(message);
            msgBox.removeClass("alert-danger");
            msgBox.addClass("alert-success");
            msgBox.removeClass("fade");
            msgBox.addClass("show");
            setTimeout(function () {
                msgBox.removeClass("show");
                msgBox.addClass("fade");
            }, 10000);
        }
    }
})();