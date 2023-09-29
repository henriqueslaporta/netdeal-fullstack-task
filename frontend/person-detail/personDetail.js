(function(angular) {
    'use strict';
  function PersonDetailController(personService) {
    var ctrl = this;
  
    ctrl.onSelect = function(selectedPerson) {
      if (ctrl.isSelected()) {
        personService.onSelect({});
      } else {
        personService.onSelect({...selectedPerson});
      }
    };

    ctrl.isSelected = function() {
      return personService.equals(ctrl?.person?.id);
    };

  }
  
  angular.module('netdealApp').component('personDetail', {
    templateUrl: 'person-detail/personDetail.html',
    controller: PersonDetailController,
    bindings: {
      person: '<',
    },
  });
  })(window.angular);
