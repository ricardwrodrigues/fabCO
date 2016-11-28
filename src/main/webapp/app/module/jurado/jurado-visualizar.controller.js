(function() {
    'use strict';

    angular
        .module('apuracao')
        .controller('JuradoVisualizarController', JuradoDetailController);

    JuradoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Jurado', 'Nota'];

    function JuradoDetailController($scope, $rootScope, $stateParams, previousState, entity, Jurado, Nota) {
        var vm = this;

        vm.jurado = entity;
        vm.previousState = previousState.name;
    }
})();
