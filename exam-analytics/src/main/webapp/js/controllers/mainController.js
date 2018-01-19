(function () {
    'use strict';

    angular.module('mainApp')
        .controller('mainController', MainController);
    // inject the services into our controller
    console.log('here cont1');
    MainController.$inject = ['MainService','AlertService', '$http'];
    function MainController(MainService, AlertService) {
        console.log('here cont1.5');
        var vm = this;
        console.log('here cont2');
        vm.exam = 'whatever';
        console.log(vm);
        // get all the exams first and bind it to the vm.exams object
        // use the function we created in our service
        // GET ALL INTERNSHIPS ==============
        MainService.get()
            .then(
                function success(response) {
                    vm.statistics = response.data;
                },
                function error(response) {
                    vm.serverErrors = response.data;
                    AlertService.alertError(response.data);
                });
    }
})();

