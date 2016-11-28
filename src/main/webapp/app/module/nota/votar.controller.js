(function() {
    'use strict';

    angular
        .module('apuracao')
        .controller('VotarController',VotarController);

    VotarController.$inject = ['$timeout', '$scope', '$state', 'Jurado'];

    function VotarController ($timeout, $scope, $state, Jurado) {
        var vm = this;

        vm.isValid = false;
        vm.validate = validate;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function validate () {
            //Poderia ser uma diretiva tratando os cpf
            var cpf = vm.cpf.replace(/\./g,"").replace(/-/g,"");
            Jurado.isCpfValid({cpf: cpf},
                function (data) {
                    vm.isValid = false;
                    $state.go('votar.jurado', {juradoId:data.id}, { reload: 'votar.jurado' });
                },
                function () {
                    vm.isValid = true;
                });
        }

    }
})();

