app.controller("entryController", function($scope, $location){
	console.log("Hi from entry controller")
	
	$scope.login = function(){
		$location.path("/login");
	}
	$scope.getMainPage = function(){
		$location.path("/main");
	}
	
})