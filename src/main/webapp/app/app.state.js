(function() {
    'use strict';

    angular
        .module('apuracao')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider','$urlRouterProvider'];

    function stateConfig($stateProvider,$urlRouterProvider) {
        $stateProvider.state('app', {
            abstract: true
        });
        //Forca o start
        $urlRouterProvider.otherwise('/');
    }
})();
