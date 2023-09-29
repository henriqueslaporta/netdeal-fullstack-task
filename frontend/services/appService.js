(function(angular) {
    'use strict';
    angular.module('netdealApp').service('personService', function($http) {
        this.person = null;
        
        this.onSelect = function (person) {
            this.person = person;
        }

        this.equals = function (id) {
            return this?.person?.id === id
        }

      });
  })(window.angular);


