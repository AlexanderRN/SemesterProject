'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl'
                });
            }])

        .controller('View1Ctrl', ['$http', 'InfoFactory', 'InfoService', '$scope', 'reservationFactory', function ($http, InfoFactory, InfoService, $scope, reservationFactory) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();

                $scope.showlist = true;
                $scope.alert = false;

                $scope.paste = function (insertdate) {

                    $scope.date = insertdate;
                };

                $scope.info = [];
                $scope.reserve = function (airline, origin, destination, date, numberOfSeats, traveltime, totalPrice) {
                    var text = airline + "," + origin + "," + destination + "," + date + "," + numberOfSeats + "," + traveltime + "," + totalPrice;

                    $scope.info.push(airline, origin, destination, date, numberOfSeats, traveltime, totalPrice);
                    //console.log($scope.info);
                    //return $scope.info;
                    reservationFactory.setInfo($scope.info);
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
                        $scope.showlist = true;
                        $scope.alert = false;
                    }

                    {
                        $http.get(url)
                                .success(function (data, status, headers, config) {
                                    $scope.airlines = data.airlines;
                                    console.log($scope.airlines);

                                    //$scope.msg = "Du er nu logget ind som USER";
                                    //$scope.ifUser = true;

//                                    if ( $scope.airlines.airline.lenght <= 0 )
//                                    {
//                                        $scope.showlist = false;
//                                        $scope.alert = true;
//                                    }
//                                    else
//                                    {
//                                        $scope.showlist = true;
//                                        $scope.alert = false;
//                                    }
                                    $scope.fdate.slice(1, 6);
                                })
                                .error(function (data, status, headers, config) {

                                });


                    }

                };
                $scope.reservation = function () {
                    var URL = "api/flightinfo";
                    var parameter = JSON.stringify({});
                    $http.post(url)
                            .success(function (data, status, headers, config) {

                            })
                            .error(function (data, status, headers, config) {

                            });



                };
            }]);
        