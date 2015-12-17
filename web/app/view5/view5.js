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

                $scope.showlogin = false;
                $scope.showsignup = true;

                $scope.showLogin = function () {

                    $scope.showlogin = true;
                    $scope.showsignup = false;
                };

                $scope.showSignup = function () {

                    $scope.showlogin = false;
                    $scope.showsignup = true;
                };


                $scope.pasList = [];

                $scope.reservation = reservationFactory.getInfo();
                var passangers = reservationFactory.getInfo()[4];
                $scope.counter = [];
                var type = "";

                for (var i = 0; i < passangers; i++)
                {
                    if (i === 0)
                    {
                        type = "Reserver";
                    }
                    else
                    {
                        type = "Passenger " + (i + 1);
                    }
                    $scope.counter.push(type);

                }

                $scope.test = function () {

                    if ($scope.isAuthenticated === true)
                    {
                        $scope.postReservation();
                    }
                    else
                    {
//                        $scope.username = $scope.regusername;
//                        $scope.password = $scope.regpassword;

                        if ($scope.register())
                        {
                            $scope.login();
                        }
                        //  $scope.postReservation();

                    }

                };

                $scope.register = function () {

                    var user = $scope.user.username;
                    var pass = $scope.user.password;
                    
                    var check = false;
                    
                    console.log(user + ", " + pass);
                    $http.post("api/demouser/" + user + "/" + pass) //Add rest url
                            .success(function (data, status, headers, config)
                            {
                                $scope.msg = "User has been created";
                                if(data == true){}
                            })
                            .error(function (data, status, headers, config)
                            {
                                $scope.msg = "Error occured while creating user, please try again later";
                                return data;
                            });
                            
                            return data;
                };

                $scope.postReservation = function () {

                    // FLIGTH INFO
//                    var reservationInfoAsArray = reservationFactory.getInfo();
//                    var airline = reservationInfoAsArray[0];
//                    var from = reservationInfoAsArray[1];
//                    var to = reservationInfoAsArray[2];
//                    var date = reservationInfoAsArray[3];
//                    var passengerCount = reservationInfoAsArray[4];
//                    var traveltime = reservationInfoAsArray[5];
//                    var totalprice = reservationInfoAsArray[6];

                    // PASSENGER INFO
//                    var passengersInfo = "";
//
//                    for (var i = 0; i < passengerCount; i++)
//                    {
//                        passengersInfo += "{ firstname:" + $scope.pasList[i].firstname + ", lastname:" + $scope.pasList[i].lastname + "}";
//                    }

//                    var parameter = JSON.stringify(passengersInfo);

                    // PREPARE POST URL
//                    var url = "api/reservation/" + from + "/" + to + "/" + date + "/" + traveltime + "/" + totalprice + "/" + parameter;

                    // POST TO API
//                    $http.post(url)
//                        .success(function (data, status, headers, config) 
//                        {
//                            $scope.msg = data;
//                        })
//                        .error(function (data, status, headers, config)
//                        {
//
//                        });

                    var reservationInfoAsArray = reservationFactory.getInfo();
                    var airline = reservationInfoAsArray[0].toString();
                    var from = reservationInfoAsArray[1].toString();
                    var to = reservationInfoAsArray[2].toString();
                    var date = reservationInfoAsArray[3].toString();
                    var passengerCount = reservationInfoAsArray[4].toString();
                    var traveltime = reservationInfoAsArray[5].toString();
                    var totalprice = reservationInfoAsArray[6].toString();

                    var url = 'api/reservation/';
                    var reservationInfo = reservationFactory.getInfo();
                    var passengersInfo = [];

                    for (var i = 0; i < passengerCount; i++)
                    {
                        passengersInfo.push({"firstname": $scope.pasList[i].firstname, "lastname": $scope.pasList[i].lastname});
                    }

                    //var parameter = JSON.stringify(passengersInfo + reservationInfo);

                    //console.log(reservationInfo);
                    //console.log(parameter);

                    var resJson =
                            {
                                "reservation": {
                                    "airline": airline,
                                    "origin": from,
                                    "destination": to,
                                    "date": date,
                                    "numberOfSeats": passengerCount,
                                    "traveltime": traveltime,
                                    "totalprice": totalprice,
                                    "username": $scope.username
                                }
                            };
                    var pasJson =
                            {
                                "passengers": passengersInfo
                            };

                    $http({
                        url: 'api/reservation/insert/' + JSON.stringify(resJson) + "/" + JSON.stringify(pasJson),
                        method: 'PUT',
                        headers: {'Content-Type': 'application/json'},
                        data: JSON.stringify(resJson)
                    })
                            .success(function (data, status, headers, config)
                            {
                                console.log(pasJson);
                                console.log(data);
                            });

                }; // RESERVATION FUNCTION END

            }]); //Controller END
        