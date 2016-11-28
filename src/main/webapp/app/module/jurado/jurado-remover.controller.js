(function() {
    'use strict';

    angular
        .module('apuracao')
        .controller('JuradoDeleteController',JuradoDeleteController);

    JuradoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Jurado'];

    function JuradoDeleteController($uibModalInstance, entity, Jurado) {
        var vm = this;

        vm.jurado = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Jurado.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
