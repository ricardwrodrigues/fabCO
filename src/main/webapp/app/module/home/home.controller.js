(function() {
    'use strict';

    angular
        .module('apuracao')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state'];

    function HomeController ($scope, $state) {
        var vm = this;
    }
})();
