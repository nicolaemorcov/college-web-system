var app = angular.module("myApp", ["ngRoute", "ngCookies"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: "main.htm"
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
    });
    
});


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
