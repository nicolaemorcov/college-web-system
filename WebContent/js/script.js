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
    }).when("/courseDetail",{
    	templateUrl: "courseDetail.htm",
    	controller: "courseController"
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
		$scope.course = data.data.data.course;
		console.log(course.courseFee);
	})
	
//	$scope.Student = student;
//	console.log(student.id + " ************* " + $scope.Student.email);
//	console.log(student.firstName + " *-------------* " + $scope.Student.password);
	
	
})

app.controller("mainController", function($scope, $http, $location, $log, myFactory, myCourses) {
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
    
    $scope.selected = {};
    
    // gets the template to ng-include for a table row / item
    $scope.getTemplate = function (x) {
        if (x.id === $scope.selected.id) return 'edit';
        else return 'display';
    };
    
    $scope.editContact = function (x) {
        $scope.selected = angular.copy(x);
    };

    $scope.saveContact = function (index) {
        console.log("Saving contact");
        var itemToEdit = $scope.users[index] = angular.copy($scope.selected);

        $http({
    		method: 'post',
    		url: 'services/edit',
    		contentType: 'application/json',
    		data: itemToEdit
    	}).then(function(data){
    		
    	})
    	$scope.reset();
    };
    
    $scope.deleteContact = function (index) {
        console.log("Saving contact");
        var userId = index.userId;

        $http({
    		method: 'post',
    		url: 'services/delete',
    		contentType: 'application/json',
    		data: userId
    	}).then(function(data){
    		
    	})
    	$scope.reset();
    };
    
    $scope.reset = function () {
        $scope.selected = {};
    };
    
    
    $scope.getUserPage = function(x){
//    	$scope.selectedUser = myFactory.get();
    	$location.path('userDetail').search({email: x.email});
    	myFactory.set(x);
    }
    $scope.editUser = function(){
    	
    }
    $scope.getCoursePage = function(x){
    	$location.path('courseDetail').search({course: x.id});
    	$scope.selectedCourse = x;
    	myCourses.set(x);
    }
    
////    SetSelectedUser
//    $scope.setSelectedUser = function(x){
//    	console.log("Button clicked, user should appear.");
//    	console.log(x);
//    	$scope.selectedUser = x;
//    	myFactory.set(x);
//    }
    
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
    
    $scope.upload = function(){
    	console.log("I'm uploading");
    	$http({
    		method: 'post',
    		url: 'services/upload'
    		
    	}).success(function(response){
    		
    	})
    }


   
    
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