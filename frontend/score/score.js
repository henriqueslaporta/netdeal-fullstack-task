(function(angular) {
    'use strict';
  function ScoreController($scope) {
    var ctrl = this;
  
    ctrl.scorePercentage = function() {
      if (ctrl.score > 100) {
        return 100;
      }
      return ctrl.score;
      
    };
    ctrl.getBackgroundColor = function() {
      var hue = Math.round(ctrl?.scorePercentage() * 1.2);
      return 'hsl(' + hue + ',70%,80%)';
    };

    ctrl.getStrongValue = function() {
      if (ctrl.score >= 0 && ctrl.score < 20) { return 'Muito ruim'; }
      else if (ctrl.score >= 20 && ctrl.score < 40) { return 'Ruim'; }
      else if (ctrl.score >= 40 && ctrl.score < 50) { return 'Mediana'; }
      else if (ctrl.score >= 40 && ctrl.score < 70) { return 'Bom'; }
      else if (ctrl.score >= 70 && ctrl.score < 90) { return 'Forte'; }
      return 'Muito forte'; 
    }

  }
  
  angular.module('netdealApp').component('score', {
    templateUrl: 'score/score.html',
    controller: ScoreController,
    bindings: {
      score: '<',
    }
  });
  })(window.angular);
