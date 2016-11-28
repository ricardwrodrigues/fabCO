(function() {
    'use strict';
    angular
        .module('apuracao')
        .factory('Jurado', Jurado);

    Jurado.$inject = ['$resource'];

    function Jurado ($resource) {
        var resourceUrl =  'api/jurado/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', url:'api/jurados', isArray: true},
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
            'isCpfValid': { method:'GET', url:'api/jurado/isCpfValid/:cpf'}
        });
    }
})();
