app.controller("courseController", function($scope, $http, myCourses){
	var courseId;
	var course = myCourses.get();
	courseName = course.name;
	console.log("CourseController used");
//	$scope.Student = student;
//	console.log($scope.Student.id);
//	console.log(student.id + " fdesf");
	
	$http({
		method: 'post',
		url: 'services/courseDetails',
		contentType: 'application/json',
		data: courseName
	}).then(function(data){
		$scope.courses = data.data.data.courses;
		
	})
	
//	$http({
//		method: 'post',
//		url: 'services/courseDetails',
//		contentType: 'application/json',
//		data: courseName
//	}).then(function(data){
//		$scope.course = data.data.data.course;
//		console.log(course.courseFee);
//	})
	
//	$scope.Student = student;
//	console.log(student.id + " ************* " + $scope.Student.email);
//	console.log(student.firstName + " *-------------* " + $scope.Student.password);
	
	
})