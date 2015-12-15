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


                /************************************
                 * Date picker START
                 ************************************/
                $scope.today = function () {
                    $scope.date = new Date();
                };
                $scope.today();

                $scope.clear = function () {
                    $scope.date = null;
                };

                // Disable weekend selection
                $scope.disabled = function (date, mode) {
                    return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
                };

                $scope.toggleMin = function () {
                    $scope.minDate = $scope.minDate ? null : new Date();
                };
                $scope.toggleMin();
                $scope.maxDate = new Date(2020, 5, 22);

                $scope.open = function ($event) {
                    $scope.status.opened = true;
                };

                $scope.setDate = function (year, month, day) {
                    $scope.date = new Date(year, month, day);
                };

                $scope.dateOptions = {
                    formatYear: 'yyyy',
                    startingDay: 1
                };

                $scope.formats = ['yyyy-MM-dd', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
                $scope.format = $scope.formats[0];

                $scope.status = {
                    opened: false
                };

                var tomorrow = new Date();
                tomorrow.setDate(tomorrow.getDate() + 1);
                var afterTomorrow = new Date();
                afterTomorrow.setDate(tomorrow.getDate() + 2);
                $scope.events =
                        [
                            {
                                date: tomorrow,
                                status: 'full'
                            },
                            {
                                date: afterTomorrow,
                                status: 'partially'
                            }
                        ];

                $scope.getDayClass = function (date, mode) {
                    if (mode === 'day') {
                        var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

                        for (var i = 0; i < $scope.events.length; i++) {
                            var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);

                            if (dayToCheck === currentDay) {
                                return $scope.events[i].status;
                            }
                        }
                    }

                    return '';
                };
                /************************************
                 * Date picker END
                 ************************************/

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
                    
                    var fulldate = $scope.date.toISOString();
                    var dateF = fulldate.slice(0, 10);


//                    if ($scope.from == null || $scope.from == "")
//                    {
//                        alert("Venligst vælg fra lufthavn!")
//                    }
//                    if ($scope.to == null)
//                    {
//                        alert("Venligst vælg til lufthavn!")
//                    }
//                    if (dateF == null || dateF == "")
//                    {
//                        alert("Venligst vælg dato!")
//                    }
//                    if ($scope.persons == null || $scope.persons == "")
//                    {
//                        alert("Venligst vælg antal personer!")
//                    }


                    if (($scope.from !== null || $scope.from !== "") && (dateF !== null || dateF !== "") && ($scope.persons !== null || $scope.persons !== "") && ($scope.to !== null || $scope.to !== "")) {

                        if ($scope.to !== null || $scope.to !== "")
                        {
                            var url = 'api/flightinfo/' + $scope.from + '/' + $scope.to + '/' + dateF + 'T00:00:00.000Z/' + $scope.persons;
                        }
                        else
                        {
                            var url = 'api/flightinfo/' + $scope.from + '/' + dateF + '00:00:00.000Z/' + $scope.persons;
                        }

                        //console.log(url);

                        if ($scope.from == null || dateF == null || $scope.persons == null || $scope.from == "" || dateF == "" || $scope.persons == "")
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

                                    })
                                    .error(function (data, status, headers, config) {

                                    });


                        }
                    }

                };
                $scope.reservation = function () {
//                    var url = "api/flightinfo";
//                    var parameter = JSON.stringify({});
//                    $http.post(url)
//                            .success(function (data, status, headers, config) {
//
//                            })
//                            .error(function (data, status, headers, config) {
//
//                            });

                };
            }]);
        