app.controller("registerController", function($scope, $http, $location){
	console.log("I'm in register");
	
	$scope.register = function(){
		var firstName = $scope.firstName;
		var lastName = $scope.lastName;
		var email = $scope.email;
		var userId = $scope.userId;
		var password = $scope.password;
		var role = $scope.role;
		
		$http({
			method: 'post',
			url: '/reg/registration',
			contentType: 'application/json',
			data: {firstName, lastName, email, userId, password, role}
		}) 
		.then(function(response){
			console.log(response);
			if(response.status == 200){
				$location.path("/success")
			}
		})
	}
	$scope.getLogin = function(){
		$location.path("/login")
	}
	
})