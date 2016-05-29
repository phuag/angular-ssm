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


  as.controller('StaffsController',function($scope,$rootScope,$http,base64,$location){
        $scope.p = 1;//page
        $scope.pSize = 10;
        $scope.q = '';//query
        // $scope.statusOpt = {'label': $.i18n.prop('ALL'), 'value': 'ALL'};
        // $scope.statusOpts = [
        //     {'label': $.i18n.prop('ALL'), 'value': 'ALL'},
        //     {'label': $.i18n.prop('DRAFT'), 'value': 'DRAFT'},
        //     {'label': $.i18n.prop('PUBLISHED'), 'value': 'PUBLISHED'}
        // ];



        var actionUrl = 'api/staff/',
                load = function () {
                    $http.get(actionUrl + '?size=' + $scope.pSize
                            + '&page=' + $scope.p)
                            .success(function (data) {
                                $scope.staffs = data.list;
                                $scope.totalItems = data.total;
                            });
                };

        load();

        $scope.search = function () {
            load();
        };

        $scope.toggleStatus = function (r) {
            $scope.statusOpt = r;
        };

        $scope.add = function () {
            $location.path('/users/new');
        };

        $scope.delUser = function (idx) {
            console.log('delete index @' + idx + ', id is@' + $scope.users[idx].id);
            if (confirm($.i18n.prop('confirm.delete'))) {
                $http.delete(actionUrl + $scope.posts[idx].id)
                        .success(function () {
                            $scope.users.splice(idx, 1);
                        });
            }
        };

    });
}());
