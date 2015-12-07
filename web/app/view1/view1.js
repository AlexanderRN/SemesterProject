'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl'
                });
            }])

        .controller('View1Ctrl', ['$http', 'InfoFactory', 'InfoService', '$scope', function ($http, InfoFactory, InfoService, $scope) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();

                $scope.showlist = false;
                $scope.alert = false;
                
                $scope.paste = function(insertdate){
                    
                    $scope.date = insertdate;
                };

                $scope.searchFlights = function () {

                    if ($scope.to !== null)
                    {
                        var url = 'api/flightinfo/' + $scope.from + '/' + $scope.to + '/' + $scope.date + 'T00:00:00.000Z/' + $scope.persons;
                    }
                    else
                    {
                        var url = 'api/flightinfo/' + $scope.from + '/' + $scope.date + '00:00:00.000Z/' + $scope.persons;
                    }

                    //console.log(url);

                    if ($scope.from == null || $scope.date == null || $scope.persons == null || $scope.from == "" || $scope.date == "" || $scope.persons == "")
                    {
                        $scope.showlist = false;
                        $scope.alert = true;
                    }
                    
                    {
                        $http.get(url)
                                .success(function (data, status, headers, config) {
                                    $scope.inmydata = data;
                                    $scope.inairline = data.airline;
                                    //console.log($scope.inmydata);

                                    //$scope.msg = "Du er nu logget ind som USER";
                                    //$scope.ifUser = true;
                                    
                                    if ( $scope.inmydata.flights.lenght <= 0 )
                                    {
                                        $scope.showlist = false;
                                        $scope.alert = true;
                                    }
                                    else
                                    {
                                        $scope.showlist = true;
                                        $scope.alert = false;
                                    }

                                })
                                .error(function (data, status, headers, config) {
                                    
                                });

                    }

                };

            }]);