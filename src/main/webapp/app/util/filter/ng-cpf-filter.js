/**
 * Created by ricardo on 22/10/16.
 */
'use strict';

angular.module('util.filters',[]).filter('cpf', function() {
    return function(input) {
        var str = input+ '';
        str = str.replace(/\D/g,'');
        str = str.replace(/(\d{3})(\d)/,"$1.$2");
        str = str.replace(/(\d{3})(\d)/,"$1.$2");
        str = str.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
        return str;
    };
});