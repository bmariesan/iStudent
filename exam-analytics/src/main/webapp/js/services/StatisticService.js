(function () {
    'use strict';

    angular.module('mainApp')
        .factory('StatisticService', StatisticService);

    StatisticService.$inject = ['$http'];
    function StatisticService($http) {

        return {
            // get exam statistics by a criteria given
            get: function (criteria) {
                return $http.get('/api/statistics/'+criteria);
            },
            getExams: function () {
                return $http.get('/api/exams');
            },
            getBayesStatistic: function (criteria,examId) {
                var criteriaWithoutBayes = criteria.substring(6,criteria.length);
                return $http.get('/api/statistics/'+criteriaWithoutBayes+"/"+examId);
            }
        }
    }
})();

