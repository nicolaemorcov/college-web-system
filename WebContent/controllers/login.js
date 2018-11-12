app.controller("loginController", function($scope, $http, $window, $location, $cookies, $cookieStore) {
    $scope.login = function() {
        userId = $scope.userId;
        password = $scope.password;
        console.log($scope.user);

        
        $http({
            method: 'post',
            url: '/auth/login',
            contentType: 'application/json',
            data: { "userId": userId, "password": password }
        }).then(function(response) {

            if (response.status == 200) {
            	$cookieStore.put("userId", userId)
            	$window.location.href = '/index.html';
            }
        })
    }
    
    $scope.getRegister = function(){
    	$location.path("/register");
    }
    
})