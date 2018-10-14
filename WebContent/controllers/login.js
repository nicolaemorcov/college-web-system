app.controller("loginController", function($scope, $http, $window, $location) {
    $scope.login = function() {
        userId = $scope.userId;
        password = $scope.password;
        console.log($scope.user);

        $http({
            method: 'post',
            url: 'auth/login',
            contentType: 'application/json',
            data: { "userId": userId, "password": password }
        }).then(function(response) {

            if (response.status == 200) {
                $window.location.href = "/index.html";
            }
        })
    }
})