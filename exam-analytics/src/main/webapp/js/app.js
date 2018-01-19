(function () {
    'use strict';

    var mainApp = angular.module('mainApp', ['ngRoute','chart.js']);
    mainApp.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/statistics', {
            templateUrl: 'statistics.html', controller: 'mainController', controllerAs: 'vm'
        }).
        otherwise({
            redirectTo: '/'
        });

    }]);
})();