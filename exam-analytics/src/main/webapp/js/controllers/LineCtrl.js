(function () {
    'use strict';

    angular.module('mainApp')
        .controller('LineCtrl', LineController);
    // inject the services into our controller
    LineController.$inject = ['MainService','AlertService', 'ExamService', 'StatisticService', '$scope', '$timeout','$http'];
    function LineController(MainService, AlertService,ExamService, StatisticService, $scope, $timeout) {
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
                        display: true,
                        position: 'right'
                    }
                ]
            }
        };
        //get all the exams first and bind it to the vm.exams object
        //use the function we created in our service
        //GET ALL EXAMS ==============
        ExamService.get()
            .then(
                function success(response) {
                    vm.exams = response.data;
                },
                function error(response) {
                    vm.serverErrors = response.data;
                    AlertService.alertError(response.data);
                });

        //todo take criteria from selected option
        vm.setCriteria = function(){
            console.log('this works ' + vm.criteria);
            criteria = parseCriteria(vm.criteria);
            console.log('criteria: '+criteria);
            if(criteria === 'age') {
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var averages = [];
                            var ageGroups = [];
                            var jsonResponse = JSON.stringify(response.data.data).split(",");
                            for (var i = 0; i < jsonResponse.length; i++) {
                                var average = jsonResponse[i].split(":")[1];
                                isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                                var ageGroup = jsonResponse[i].split(":")[0];
                                (i == 0) ? ageGroups.push(ageGroup.substring(1, ageGroup.length)) : ageGroups.push(ageGroup);
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
                            var exams = ['a','b','c'];
                            var averages = [6,3.2,8.3];
                            vm.labels = exams;
                            vm.data = averages;
                        });
            }
            //todo use service here as well to set labels and data
            else if(criteria === 'country'){
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var countries = ['aasd','basdasd','casda'];
                            var averages = [7.2,3.2,9.3];
                            vm.labels = countries;
                            vm.data = averages;
                        });
            }
            //todo use service here as well to set labels and data
            else if(criteria === 'gender'){
                StatisticService.get(criteria)
                    .then(
                        function success(response) {
                            var genders = ['h','f','m'];
                            var averages = [2.1,4.2,9.5];
                            vm.labels = genders;
                            vm.data = averages;
                        });
            }
        };
        StatisticService.get(criteria)
            .then(
                function success(response) {
                    if (criteria === 'age') {
                        var averages = [];
                        var ageGroups = [];
                        var jsonResponse = JSON.stringify(response.data.data).split(",");
                        for (var i = 0; i < jsonResponse.length; i++) {
                            var average = jsonResponse[i].split(":")[1];
                            isNaN(average) ? averages.push(0) : averages.push(parseFloat(average));
                            var ageGroup = jsonResponse[i].split(":")[0];
                            (i == 0) ? ageGroups.push(ageGroup.substring(1, ageGroup.length)) : ageGroups.push(ageGroup);
                        }
                        vm.labels = ageGroups;
                        vm.data = averages;
                }
                    else if(criteria === 'gender'){
                        console.log('gender statistics');
                        var jsonResponse = JSON.stringify(response.data.data);
                        console.log(jsonResponse);
                        var genders = [];
                        var averages = [];
                        //console.log(jsonResponse.split)
                    }
                    else if(criteria === 'exam'){
                        console.log('exam statistics');
                    }
                    else if(criteria === 'country'){
                        console.log('country statistic');
                    }
                },
                function error(response) {
                    vm.serverErrors = response.data;
                    AlertService.alertError(response.data);
                });
    }
})();

