app.controller("logoutController", function($scope, $cookies, $location) {
	console.log("Hello from outside");

	$scope.login = function(){
		$location.path("/login")
	}
	
	$scope.logout = function() {

		$http({
			method: 'get',
			url: '/auth/logout',
			contentType: 'application/json',
			
		})
		.then(function(response){
			$cookies.get("userId");
			$.cookies.del('userId');
		})
		
	}
})