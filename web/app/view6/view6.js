angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'app/view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])

        .controller('View6Ctrl', ['$http', 'InfoFactory', 'InfoService', '$scope', 'reservationFactory', function ($http, InfoFactory, InfoService, $scope, reservationFactory) {

                $scope.register = function (user, pass) {
                    
                    console.log(user + ", " + pass);
                    $http.post("api/demouser/" + user + "/" + pass) //Add rest url
                            .success(function (data, status, headers, config)
                            {
                                $scope.msg = "User has been created";
                            })
                            .error(function (data, status, headers, config)
                            {
                                $scope.msg = "Error occured while creating user, please try again later"
                            });
                };



            }]); //Controller END