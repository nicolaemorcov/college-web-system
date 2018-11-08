app.controller("logoutController", function($scope, $cookies, $location, $http) {
	console.log("Hello from outside");

	$scope.login = function(){
		$location.path("/login")
	}
		$http({
			method: 'get',
			url: '/auth/logout',
			contentType: 'application/json',
			
		})
		.then(function(response){
			var cook = $cookies.get("userId");
			$cookies.remove("userId");
			console.log(cook);
			$location.path("/")
		})
		
})