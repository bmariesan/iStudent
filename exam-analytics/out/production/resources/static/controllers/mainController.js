(function () {
    'use strict';

    angular.module('mainApp')
        .controller('mainController', MainController);
    // inject the Internships service into our controller
    MainController.$inject = ['MainService','AlertService', '$http'];
    function MainController(MainService, AlertService) {
        var vm = this;
        console.log('here');
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

