app.controller("userController", function($scope, $http, myFactory){
	var studentId;
	var student = myFactory.get();
	studentId = student.id;
	console.log("UserController used");
//	$scope.Student = student;
//	console.log($scope.Student.id);
//	console.log(student.id + " fdesf");
	
	
	
	$http({
		method: 'post',
		url: 'services/userDetails',
		contentType: 'application/json',
		data: studentId
	}).then(function(data){
		$scope.student = data.data.data.user;
//		console.log(student);
	})
	
	$scope.Student = student;
	console.log(student.id + " ************* " + $scope.Student.email);
	console.log(student.firstName + " *-------------* " + $scope.Student.password);
	
	
})