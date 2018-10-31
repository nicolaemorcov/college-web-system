app.controller("enrollController", function($scope, $http, myFactory, myCourses, $cookies, $location){
	var userId = $cookies.get("userId");
	if(userId == null){
    	$location.path("/login")
    }
	
	$scope.User = myFactory.get();
	$scope.Courses = myCourses.get();
})