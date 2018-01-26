(function () {
    'use strict';

    angular.module('mainApp')
        .controller('LineCtrl', LineController);
    // inject the services into our controller
    LineController.$inject = ['AlertService', 'StatisticService', '$timeout','$http'];
    function LineController(AlertService, StatisticService, $timeout) {
        var vm = this;
        vm.criteria = 'Age Statistics';
        var criteria = 'age';

        getStatistic(criteria);

        StatisticService.getExams().then(
            function success(response) {
                vm.exams= angular.copy(response.data);
                vm.exam = {};
            });

        function parseCriteria(criteria) {
            return criteria.split(" ")[0].toLowerCase();
        }

        vm.statisticOptions = ["Exam Statistics", "Age Statistics",
            "Gender Statistics", "Country Statistics",
            "Bayes-age statistics", "Bayes-country statistics", "Bayes-gender statistics"];

        vm.onClick = function (points, evt) {
            //do nothing yet
            // console.log(points);
        };
        vm.series = ['Series A'];
        vm.datasetOverride = [{yAxisID: 'y-axis-1'}, {yAxisID: 'y-axis-2'}];
        vm.options = {
            scales: {
                yAxes: [
                    {
                        id: 'y-axis-1',
                        type: 'linear',
                        display: true,
                        position: 'left'
                    },
                    {
                        id: 'y-axis-2',
                        type: 'linear',
                        display: false,
                        position: 'right'
                    }
                ]
            }
        };
        vm.setCriteria = function() {
            criteria = parseCriteria(vm.criteria);
            getStatistic(criteria);
        };

        function getStatistic(criteria) {
            if (criteria.indexOf('bayes') !== -1) {
                StatisticService.getBayesStatistic(criteria,vm.exam.id)
                    .then(
                        function success(response) {
                            setLabelsAndData(response);
                        },
                        function error(response) {
                            vm.serverErrors = response.data;
                            AlertService.alertError(response.data);
                        });
            } else {
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            setLabelsAndData(response);
                        },
                        function error(response) {
                            vm.serverErrors = response.data;
                            AlertService.alertError(response.data);
                        });
            }

        }
        function setLabelsAndData(response) {
            var averages = [];
            var criteriaList = [];
            for(var i = 0; i < response.data.data.length; i++){
                var average = response.data.data[i].average;
                isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                criteriaList.push(response.data.data[i].name);
            }
            vm.labels = criteriaList;
            vm.data = averages;
        }
    }
})();

