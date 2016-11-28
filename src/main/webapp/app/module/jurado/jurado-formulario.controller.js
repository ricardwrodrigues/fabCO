(function() {
    'use strict';

    angular
        .module('apuracao')
        .controller('JuradoFormularioController', JuradoDialogController);

    JuradoDialogController.$inject = ['$timeout', '$scope', '$stateParams', 'Jurado', 'Nota', '$state', 'entity'];

    function JuradoDialogController ($timeout, $scope, $stateParams, Jurado, Nota, $state, entity) {
        var vm = this;
        
        vm.jurado = entity;
        vm.clear = clear;
        vm.save = save;
        vm.notas = Nota.query();
        vm.regexCpf = '([0-9]{3}\.?[0-9]{3}\.?[0-9]{3}\-?[0-9]{2})';

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
           $state.go('jurado', null, { reload: 'jurado' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.jurado.id !== null) {
                Jurado.update(vm.jurado, onSaveSuccess, onSaveError);
            } else {
                Jurado.save(vm.jurado, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $state.go('jurado', null, { reload: 'jurado' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

    }
})();
