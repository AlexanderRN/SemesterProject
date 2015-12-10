'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', ['$http', 'InfoFactory', 'InfoService', '$scope', 'reservationFactory', function ($http, InfoFactory, InfoService, $scope, reservationFactory) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();
                
                var passangers = reservationFactory.getInfo()[4];
                $scope.counter = [];
                var type = "";
                
                for(var i = 0; i < passangers; i++)
                {
                    if(i === 0)
                    {
                        type = "Reserver";
                    }
                    else
                    {
                        type = "Passanger" + (i+1);
                    }
                    $scope.counter.push(type);
                   
                }
                
                console.log($scope.counter);
                
            }]);
        