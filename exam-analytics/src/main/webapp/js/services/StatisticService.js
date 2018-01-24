(function () {
    'use strict';

    angular.module('mainApp')
        .factory('StatisticService', StatisticService);

    StatisticService.$inject = ['$http'];
    function StatisticService($http) {

        return {
            // get exam statistics by a criteria given
            get: function (criteria) {
                console.log('getting statistics');
                return $http.get('/api/statistics/'+criteria);
            }
        }
    }
})();

