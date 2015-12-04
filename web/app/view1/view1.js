'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', ['$http', function ($http) {
//  this.msgFromFactory = InfoFactory.getInfo();
//  this.msgFromService = InfoService.getInfo();

                $http.get('api/flightinfo/CPH/2016-01-04T23:00:00.000Z/3')
                        .success(function (data, status, headers, config) {
                            //data = data;
                            
                            console.log(data);
                            
                            //$scope.msg = "Du er nu logget ind som USER";
                            //$scope.ifUser = true;

                        })
                        .error(function (data, status, headers, config) {

                        });

            }]);