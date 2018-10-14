app.controller("enrollController", function($scope, $http, myFactory, myCourses){
	$scope.User = myFactory.get();
	$scope.Courses = myCourses.get();
})