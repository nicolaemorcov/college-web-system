var app = angular.module("myApp", ["ngRoute", "ngCookies"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: "entry.html",
        controller: "entryController"
    })
    .when("/main", {
        templateUrl: "main.htm"
	})
	.when("/coursesPage", {
    	templateUrl: "coursesPage.html",
    })
    .when("/register", {
    	templateUrl: "register.html",
    	controller: "registerController"
    })
    .when("/enroll", {
        templateUrl: "enroll.htm"
    })
    .when("/login", {
        templateUrl: "login.html",
        controller: "loginController"
    })
    .when("/logout", {
    	templateUrl: "logout.html",
    	controller: "logoutController"
    })
    .when("/paris", {
        templateUrl: "courseReg.htm"
    })
    .when("/studentEntry",{
    	templateUrl: "studentEntry.html",
    	controller: "studentEntryController"
    })
    .when("/userDetail",{
    	templateUrl: "userDetail.htm",
    	controller: "userController"
    })
    .when("/courseDetail",{
    	templateUrl: "courseDetail.htm",
    	controller: "courseController"
    })
    .when("/enroll",{
    	templateUrl: "enroll.htm",
    	controller: "enrollController"
    })
    .when("/success", {
    	templateUrl: "success.html"
    });
    
});
app.controller("indexController", function($scope, $cookies, $location){
	var userId = $cookies.get("userId");
	if(userId){
		$scope.showRegister = false;
		$scope.showLogout = true;
		$location.path("/main");
	}else{
		$scope.showRegister = true;
		$scope.showLogout = false;
	}
//	$scope.showTutorAdmin = false;
	
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


