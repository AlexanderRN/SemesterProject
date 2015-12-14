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
                
                $scope.test = function(){
                    
                    //var firstnames = $scope.resform;
                    
                    //console.log($scope.pasList);
                    
                    //$scope.postReservation();
                    
                    var name = $scope.pasList[0].firstname + " " + $scope.pasList[0].lastname;
                    
                    alert("Hej " + name + " din reservation er hermed gennemført!");
                    
                };

                $scope.postReservation = function () {
                    
                    // FLIGTH INFO
                    var reservationInfoAsArray = reservationFactory.getInfo();
                    var airline = reservationInfoAsArray[0];
                    var from = reservationInfoAsArray[1];
                    var to = reservationInfoAsArray[2];
                    var date = reservationInfoAsArray[3];
                    var passengerCount = reservationInfoAsArray[4];
                    var traveltime = reservationInfoAsArray[5];
                    var totalprice = reservationInfoAsArray[6];
                    
                    // PASSENGER INFO
                    var passengersInfo = "";
                    
                    for (var i = 0; i < passengerCount; i++)
                    {
                        passengersInfo += "{ firstname:" + $scope.pasList[i].firstname + ", lastname:" + $scope.pasList[i].lastname + "}";
                    }
                    
                    var parameter = JSON.stringify(passengersInfo);
                    
                    // PREPARE POST URL
                    var url = "api/reservation/" + from + "/" + to + "/" + date + "/" + traveltime + "/" + totalprice + "/" + parameter;
                    
                    // POST TO API
                    $http.post(url)
                        .success(function (data, status, headers, config) 
                        {
                            $scope.msg = data;
                        })
                        .error(function (data, status, headers, config)
                        {

                        });
                    }; // RESERVATION FUNCTION END
            
            }]); //Controller END
        