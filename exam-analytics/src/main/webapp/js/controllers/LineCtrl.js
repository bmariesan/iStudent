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

        function parseCriteria(criteria) {
            return criteria.split(" ")[0].toLowerCase();
        }

        vm.statisticOptions = ["Exam Statistics", "Age Statistics",
            "Gender Statistics", "Country Statistics"];

        vm.onClick = function (points, evt) {
            console.log(points, evt);
        };
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
        //todo take criteria from selected option
        vm.setCriteria = function(){
            criteria = parseCriteria(vm.criteria);
            if(criteria === 'age') {
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var averages = [];
                            var ageGroups = [];
                            for(var i = 0; i < response.data.data.length; i++){
                                var average = response.data.data[i].average;
                                isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                                ageGroups.push(response.data.data[i].name);
                            }
                            vm.labels = ageGroups;
                            vm.data = averages;
                        });
            }
            //todo use service here as well to set labels and data
            else if(criteria === 'exam'){
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var averages = [];
                            var exams = [];
                            for(var i = 0; i < response.data.data.length; i++){
                                var average = response.data.data[i].average;
                                isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                                exams.push(response.data.data[i].name);
                            }
                            vm.labels = exams;
                            vm.data = averages;
                        });
            }
            //todo use service here as well to set labels and data
            else if(criteria === 'country'){
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var averages = [];
                            var countries = [];
                            for(var i = 0; i < response.data.data.length; i++){
                                var average = response.data.data[i].average;
                                isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                                countries.push(response.data.data[i].name);
                            }
                            vm.labels = countries;
                            vm.data = averages;
                        });
            }
            //todo use service here as well to set labels and data
            else if(criteria === 'gender'){
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var averages = [];
                            var genders = [];
                            for(var i = 0; i < response.data.data.length; i++){
                                var average = response.data.data[i].average;
                                isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                                genders.push(response.data.data[i].name);
                            }
                            vm.labels = genders;
                            vm.data = averages;
                        });
            }
        };
        StatisticService.get(criteria)
            .then(
                function success(response) {
                    var averages = [];
                    var ageGroups = [];
                    for (var i = 0; i < response.data.data.length; i++) {
                        var average = response.data.data[i].average;
                        isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                        ageGroups.push(response.data.data[i].name);
                    }
                    vm.labels = ageGroups;
                    vm.data = averages;
                },
                function error(response) {
                    vm.serverErrors = response.data;
                    AlertService.alertError(response.data);
                });
    }
})();

