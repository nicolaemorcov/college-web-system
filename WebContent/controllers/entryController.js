app.controller("entryController", function($scope, $location){
	console.log("Hi from entry controller")
	
	$scope.getMainPage = function(){
		$location.path("/main");
	}
	
})