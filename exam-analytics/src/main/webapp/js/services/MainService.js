(function () {
    'use strict';

    angular.module('mainApp')
        .factory('MainService', MainService);

    MainService.$inject = ['$http'];
    function MainService($http) {

        return {
            // get all statistics
            get: function () {
                console.log('here service');
                return $http.get('/api/statistics');
            }
        }
    }
})();

