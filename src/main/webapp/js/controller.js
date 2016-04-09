(function(){
  var as = angular.module('ssmApp.controllers', []);
  as.controller('MainController', function ($q, $scope, $rootScope, $http, i18n, $location) {
    var load = function () {
    };

    load();

    $scope.language = function () {
        return i18n.language;
    };
    $scope.setLanguage = function (lang) {
        i18n.setLanguage(lang);
    };

    $scope.activeWhen = function (value) {
        return value ? 'active' : '';
    };

    $scope.path = function () {
        return $location.url();
    };

    $scope.logout = function () {
        $rootScope.user = null;
        $scope.username = $scope.password = null;
        $scope.$emit('event:logoutRequest');
        $location.url('/login');
    };
  })


  as.controller('LoginController', function ($scope, $rootScope, $http, base64, $location) {

    $scope.login = function () {
        console.log('username:password @' + $scope.username + ',' + $scope.password);
        $scope.$emit('event:loginRequest', $scope.username, $scope.password);
        // $('#login').modal('hide');
    };
  });
}());
