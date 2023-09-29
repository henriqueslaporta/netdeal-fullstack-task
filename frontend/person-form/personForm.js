(function(angular) {
    'use strict';
  function PersonFormController($http, personService) {
    var ctrl = this;
    
    ctrl.user = {
        name: null,
        password: null,
        parentPerson: null,
    }

    ctrl.save = async function(user) {
      ctrl.user.parentPerson = personService?.person?.id;
      await $http.post("http://localhost:8080/persons/", ctrl.user).then(function (response) {
        ctrl.updateList();
        ctrl.user = {
          name: null,
          password: null,
          parentPerson: null,
        }
        personService.onSelect(null)
      });
    }

  }
  
  angular.module('netdealApp').component('personForm', {
    templateUrl: 'person-form/personForm.html',
    controller: PersonFormController,
    bindings: {
      updateList: '&',
    },
  });
  })(window.angular);
