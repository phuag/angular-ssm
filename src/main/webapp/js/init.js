(function () {
  var
      //the HTTP headers to be used by all requests
      httpHeaders,
      //the message to be shown to the user
      message,
      //define the main moudle
      as = angular.module('ssmApp', ['ngRoute', 'ngResource', 'ngCookies', 'ui.bootstrap', 'ngMessages', 'ssmApp.i18n', 'ssmApp.services', 'ssmApp.controllers', 'ssmApp.filters']);

  as.config(function($routeProvider, $httpProvider){
    $routeProvider
      .when('/',
                {templateUrl: 'partials/home.html',
                publicAccess: true})
      .when('/home',
                {templateUrl: 'partials/home.html',
                publicAccess: true})
      .when('/login',
                {templateUrl: 'partials/login.html',
                publicAccess: true})
                /*
      .when('/posts',
                {controller: 'PostsController',
                templateUrl: 'partials/posts/home.html'})
      .when('/posts/new',
                {controller: 'NewPostController',
                templateUrl: 'partials/posts/new.html'})
      .when('/posts/:id',
                {controller: 'DetailsController',
                templateUrl: 'partials/posts/details.html'})*/;



    //configure $http to catch message responses and show them
    $httpProvider.interceptors.push(function ($q) {
        var setMessage = function (response) {
            //if the response has a text and a type property, it is a message to be shown
            if (response.data.text && response.data.type) {
                message = {
                    text: response.data.text,
                    type: response.data.type,
                    show: true
                };
            }
        };

        return {
            //this is called after each successful server request
            'response': function (response) {
                // console.log('request:' + response);
                setMessage(response);
                return response || $q.when(response);
            },
            //this is called after each unsuccessful server request
            'responseError': function (response) {
                //console.log('requestError:' + response);
                setMessage(response);
                return $q.reject(response);
            }

        };
    });

    $httpProvider.interceptors.push(function ($rootScope, $q) {

        return {
            'request': function (config) {
                // console.log('request:' + config);
                return config || $q.when(config);
            },
            'requestError': function (rejection) {
                // console.log('requestError:' + rejection);
                return rejection;
            },
            //success -> don't intercept
            'response': function (response) {
                // console.log('response:' + response);
                return  response || $q.when(response);
            },
            //error -> if 401 save the request and broadcast an event
            'responseError': function (response) {
                console.log('responseError:' + response);
                if (response.status === 401) {
                    var deferred = $q.defer(),
                            req = {
                                config: response.config,
                                deferred: deferred
                            };
                    $rootScope.requests401.push(req);
                    $rootScope.$broadcast('event:loginRequired');
                    return deferred.promise;
                }
                return $q.reject(response);
            }

        };
    });

    httpHeaders = $httpProvider.defaults.headers;
  })

  $http.get('api/ping')
          .success(function (data) {
              console.log("ping result@"+data);
          })
          .error(function (data) {
               $rootScope.message={text:'Network connection eror!', type:'danger', show:true};
          });
})
