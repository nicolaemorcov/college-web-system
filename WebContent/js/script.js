var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "main.htm"
    }).when("/enroll", {
        templateUrl: "enroll.htm"
    }).when("/paris", {
        templateUrl: "courseReg.htm"
    }).when("/userDetail",{
    	templateUrl: "userDetail.htm",
    	controller: "userController"
    }).when("/enroll",{
    	templateUrl: "enroll.htm",
    	controller: "enrollController"
    });
    
});

app.controller("enrollController", function($scope, $http, myFactory, myCourses){
	$scope.User = myFactory.get();
	$scope.Courses = myCourses.get();
})

app.factory("myFactory", function(){
	var savedUser = {};
	
	function set(data){
		savedUser = data;
	}
	function get(){
		return savedUser;
	}
	
	return{
		set: set,
		get: get
	}
})

app.factory("myCourses", function(){
	var courseList = {};
	
	function set(data){
		courseList = data;
	}
	function get(){
		return courseList;
	}
	return{
		set: set,
		get: get
	}
	
})

app.controller("userController", function($scope, $http, myFactory){
	var student = myFactory.get();
	var studentId = student.id;
//	$scope.Student = student;
//	console.log($scope.Student.id);
//	console.log(student.id + " fdesf");
	
	
	
	$http({
		method: 'post',
		url: 'services/userDetails',
		contentType: 'application/json',
		data: studentId
	}).then(function(data){
		var student = data.data.data.user;
		console.log(student);
		$scope.Student = student;
	})
	
})

app.controller("mainController", function($scope, $http, $log, myFactory, myCourses) {
    $scope.message = "I did it";

    $scope.showEdit = true;

    $scope.test = function() {
        console.log("Button clicked, table should appear.")
        if ($scope.showEdit == false) {
            $scope.showEdit = true;
        } else {
            $scope.showEdit = false;
        }
    }

//    $scope.showUser = false;
//    $scope.editUser = function(x) {
//        console.log("Button clicked, user should appear.")
//        console.log(x);
//        if ($scope.showUser == true) {
//            $scope.showUser = false;
//        } else {
//            $scope.showUser = true;
//        }
//    }
    
    
//    SetSelectedUser
    $scope.setSelectedUser = function(x){
    	console.log("Button clicked, user should appear.");
    	console.log(x);
    	$scope.selectedUser = x;
    	myFactory.set(x);
    }
    
//  SetSelectedCourse
    $scope.setSelectedCourse = function(x){
    	console.log("Button clicked, user should appear.");
    	console.log(x);
    	$scope.selectedCourse = x;
    }

//    get all data from db
    $http.get("services/users").then(function(response) {
    	var courses = response.data.data.courses;
    	var users = response.data.data.users;
        $scope.users = users;
        $scope.courses = courses;
        myCourses.set(courses);
    })

    
//    edit User
    $scope.edit = function(){
    	var itemToEdit;
    	if($scope.selectedUser != null){
    		itemToEdit = $scope.users.find(x => x.id === $scope.selectedUser.id);
    	}
    	else{
    		itemToEdit = $scope.courses.find(x => x.id === $scope.selectedCourse.id);
    	}
//    	var userToEdit = $scope.users.find(x => x.id === $scope.selectedUser.id);
//    	var courseToEdit = $scope.courses.find(x => x.id === $scope.selectedCourse.id);
    	$http({
    		method: 'post',
    		url: 'services/edit',
    		contentType: 'application/json',
    		data: itemToEdit
    	}).success(function(data){
    		
    	})
    }

})