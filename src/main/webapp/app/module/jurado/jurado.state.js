(function() {
    'use strict';

    angular
        .module('apuracao')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('jurado', {
            url: '/jurado',
            views: {
                'content@': {
                    templateUrl: 'app/module/jurado/jurado-lista.html',
                    controller: 'JuradoListaController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('jurado-visualizar', {
            parent: 'jurado',
            url: '/jurado/{id}',
            views: {
                'content@': {
                    templateUrl: 'app/module/jurado/jurado-visualizar.html',
                    controller: 'JuradoVisualizarController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Jurado', function($stateParams, Jurado) {
                    return Jurado.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'jurados',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('jurado.novo', {
            url: '/novo',
            views: {
                'content@': {
                    templateUrl: 'app/module/jurado/jurado-formulario.html',
                    controller: 'JuradoFormularioController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Jurado', function($stateParams, Jurado) {
                    return {};
                }]
            }
        })
        .state('jurado.editar', {
            parent: 'jurado',
            url: '/{id}/editar',
            views: {
                'content@': {
                    templateUrl: 'app/module/jurado/jurado-formulario.html',
                    controller: 'JuradoFormularioController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Jurado', function($stateParams, Jurado) {
                    return Jurado.get({id : $stateParams.id}).$promise;
                }]
            }
            
        })
        .state('jurado.remover', {
            parent: 'jurado',
            url: '/{id}/remover',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/module/jurado/jurado-remover.html',
                    controller: 'JuradoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Jurado', function(Jurado) {
                            return Jurado.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('jurado', null, { reload: 'jurado' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
