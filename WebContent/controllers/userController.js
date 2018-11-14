app.controller("userController", function($scope, $http, myFactory, $cookies){
	var studentId;
	var userId = $cookies.get("userId");
	var student = myFactory.get();
	var student1 = student.userId
	studentId = student.id;
	console.log("UserController used");
//	$scope.Student = student;
//	console.log($scope.Student.id);
//	console.log(student.id + " fdesf");
	
	
	if(!student1){
		$http({
			method: 'post',
			url: 'services/userDetails',
			contentType: 'application/json',
			data: userId
		}).then(function(data){
			$scope.student = data.data.data.user;
	//		console.log(student);
		})
	}
	else{
		$http({
			method: 'post',
			url: 'services/userDetails',
			contentType: 'application/json',
			data: student1
		}).then(function(data){
			$scope.student = data.data.data.user;
	//		console.log(student);
		})
	}
	$scope.Student = student;
	console.log(student.id + " ************* " + $scope.Student.email);
	console.log(student.firstName + " *-------------* " + $scope.Student.password);
	

	
})