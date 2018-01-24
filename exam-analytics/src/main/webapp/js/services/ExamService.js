(function () {
    'use strict';

    angular.module('mainApp')
        .factory('ExamService', ExamService);

    ExamService.$inject = ['$http'];
    function ExamService($http) {

        return {
            // get all exams
            get: function () {
                //console.log('getting exams');
                return $http.get('/api/exams');
            }
        }
    }
})();

