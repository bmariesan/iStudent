(function () {
    'use strict';

    angular.module('mainApp')
        .controller('LineCtrl', LineController);
    // inject the services into our controller
    console.log('here cont1');
    LineController.$inject = ['MainService','AlertService', 'ExamService', '$scope', '$timeout','$http'];
    function LineController(MainService, AlertService,ExamService, $scope, $timeout) {
        console.log('here cont1.5');
        var vm = this;
        console.log('here cont2');
        vm.sName = 'Exam statistics';//replace with function
        console.log(vm);
        vm.labels = ["January", "February", "March", "April", "May", "June", "July"];
        vm.series = ['Series A', 'Series B'];
        vm.data = [
            [65, 59, 80, 81, 56, 55, 40],
            [28, 48, 40, 19, 86, 27, 90]
        ];

        vm.onClick = function (points, evt) {
            console.log(points, evt);
        };
        vm.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
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
        //append these to service functions
        $scope.labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
        $scope.series = ['Series A', 'Series B'];
        $scope.data = [
            [65, 59, 80, 81, 56, 55, 40],
            [28, 48, 40, 19, 86, 27, 90]
        ];
        $timeout(function () {
            $scope.labels = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
            $scope.data = [
                [28, 48, 40, 19, 86, 27, 90],
                [65, 59, 80, 81, 56, 55, 40]
            ];
            $scope.series = ['Series C', 'Series D'];
        }, 0);
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
    }
})();

