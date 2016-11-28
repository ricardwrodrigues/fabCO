(function () {
    'use strict';

    angular
        .module('apuracao')
        .controller('NotaController', NotaController);

    NotaController.$inject = ['$scope', '$state', '$timeout', 'Nota', 'entity'];

    function NotaController($scope, $state, $timeout, Nota, entity) {
        var vm = this;

        vm.notas = entity;
        vm.saveNotas = saveNotas;
        vm.isSaving = false;
        vm.regexCpf = '([0-9]{3}\.?[0-9]{3}\.?[0-9]{3}\-?[0-9]{2})';

        //loadAll();

        function loadAll() {

            EscolaSamba.escolaNota({juradoId:vm.juradoId},function (result) {
                vm.escolas = result;
            });
        }

        function saveNotas() {
            //Melhorias a ser aplicada um DTO para load/save backend "tempo!?"
            vm.notas = [];
            for (var i in vm.escolas) {
                var escola = vm.escolas[i];
                for (var x in escola.notas) {
                    var nota = {};
                    nota.nota = escola.notas[x].nota;
                    nota.quesito = {id: escola.notas[x].quesito.id};
                    nota.jurado = {id: vm.juradoId};
                    nota.escolaSamba = {id: escola.id};
                    vm.notas.push(nota);
                }
            }
            Nota.saveNotas(vm.notas, onSaveSuccess, onSaveError);

        }

        function onSaveSuccess() {
            vm.isSaving = true;
            $timeout(function (){
                vm.isSaving = false;
                $state.go('home');
            },3000);
        }

        function onSaveError() {
            vm.isSaving = false;
        }
    }
})();
