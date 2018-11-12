app.controller("logoutController", function($scope, $cookies, $location, $http, $window) {
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
			$window.location.href = '/index.html';
		})
		
})