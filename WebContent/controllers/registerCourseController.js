app.controller("registerCourseController", function($scope, $http){
	
	$scope.registerCourse = function(){
		
		var name = $scope.courseName;
		var length = $scope.courseLength;
		var fee = $scope.tuitionFees
		
		$http({
			method: 'post',
			url: '/reg/courseRegistration',
			contentType: 'application/json',
			data: { "name": name, "length": length, "fee": fee}
		})
		.then(function(response){
			if(response.status == 200){
				
			}
		})
		
	}
	
})