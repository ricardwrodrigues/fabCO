(function() {
    'use strict';

    angular
        .module('apuracao')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('votar', {
            url: '/votar',
            views: {
                'content@': {
                    templateUrl: 'app/module/nota/votar.html',
                    controller: 'VotarController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('votar.jurado', {
            parent: 'votar',
            url: '/nota/jurado/:juradoId',
            views: {
                'content@': {
                    templateUrl: 'app/module/nota/nota-jurado.html',
                    controller: 'NotaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Nota', function($stateParams, Nota) {
                    return Nota.getNotasJurado({juradoId : $stateParams.juradoId}).$promise;
                }]
            }
        });
    }

})();
