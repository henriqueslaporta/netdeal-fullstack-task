(function (angular) {
  "use strict";
  function PersonListController($http, personService) {
    var ctrl = this;

    ctrl.list = [];

    ctrl.updateList = async function () {
      await $http.get("http://localhost:8080/persons/").then(function (response) {
        ctrl.list = response.data;
      });
    }

    ctrl.updateList();

    ctrl.getPersonSelected = function () {
      return personService.person;
    }
  }

  angular.module("netdealApp").component("personList", {
    templateUrl: "person-list/personList.html",
    controller: PersonListController,
  });
})(window.angular);
