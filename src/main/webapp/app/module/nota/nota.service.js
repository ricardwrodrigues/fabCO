(function() {
    'use strict';
    angular
        .module('apuracao')
        .factory('Nota', Nota);

    Nota.$inject = ['$resource'];

    function Nota ($resource) {
        var resourceUrl =  'api/nota/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'saveNotas': { method:'POST', url:'api/notas'},
            'getNotasJurado': {
                method: 'GET',
                url: 'api/nota/jurado/:juradoId',
                isArray: true
            }
        });
    }
})();
