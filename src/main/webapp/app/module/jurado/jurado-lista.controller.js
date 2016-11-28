(function() {
    'use strict';

    angular
        .module('apuracao')
        .controller('JuradoListaController', JuradoController);

    JuradoController.$inject = ['$scope', '$state', 'Jurado'];

    function JuradoController ($scope, $state, Jurado) {
        var vm = this;
        
        vm.jurados = [];

        loadAll();

        function loadAll() {
            Jurado.query(function(result) {
                vm.jurados = result;
            });
        }
    }
})();
