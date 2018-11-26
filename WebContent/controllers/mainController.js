app.controller("mainController", function($scope, $http, $location,$cookieStore, $log, myFactory, myCourses, $cookies) {
	var userId = $cookies.get("userId");
	// check if logged in
    if(userId == null){
    	$location.path("/login")
    }
    else{
    	$scope.loggedOutMessage = "Welcome please Log In";

        $scope.showEdit = true;
        var userId = $cookies.get("userId");
       
        // logout
        $scope.logout = function() {

        	$location.path("/logout")
    		}
        
        // ajax get all data from db
        $http({
        	method: 'post',
        	url: 'services/users',
        	contentType: 'application/json',
        	data: userId
        })
        .then(function(response){
        	var data = response.data.data;
        	if(data.role == "STUDENT"){
        		$location.path("/studentEntry")
        	}
        	else{
	        	var courses = data.courses;
	        	var users = data.users;
	        	console.log(response.data.status);
	            $scope.users = users;
	            $scope.courses = courses;
	            myCourses.set(courses);
        	}
        })
        
        
    // // get all data from db
// $http.get("services/users").then(function(response) {
// var courses = response.data.data.courses;
// var users = response.data.data.users;
// $scope.users = users;
// $scope.courses = courses;
// myCourses.set(courses);
// })
        
        
        
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

        // save edited user
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
        
        // delete user
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
        
        // goes to user Page
        $scope.getUserPage = function(x){
        	$scope.selectedUser = myFactory.get();
        	$location.path('userDetail').search({email: x.email});
        	myFactory.set(x);
        }
        
        $scope.editUser = function(){
        	
        }
        
        // goes to course page
        $scope.getCoursePage = function(x){
        	$location.path('courseDetail').search({course: x.id});
        	$scope.selectedCourse = x;
        	myCourses.set(x);
        }
        
// // SetSelectedUser
// $scope.setSelectedUser = function(x){
// console.log("Button clicked, user should appear.");
// console.log(x);
// $scope.selectedUser = x;
// myFactory.set(x);
// }
        
    // SetSelectedCourse
        $scope.setSelectedCourse = function(x){
        	console.log("Button clicked, user should appear.");
        	console.log(x);
        	$scope.selectedCourse = x;
        }

        
        $scope.upload = function(){
        	console.log("I'm uploading");
        	$http({
        		method: 'post',
        		url: 'services/upload'
        		
        	}).success(function(response){
        		
        	})
        }


       
        
// edit User
        $scope.edit = function(){
        	var itemToEdit;
        	if($scope.selectedUser != null){
        		itemToEdit = $scope.users.find(x => x.id === $scope.selectedUser.id);
        	}
        	else{
        		itemToEdit = $scope.courses.find(x => x.id === $scope.selectedCourse.id);
        	}
// var userToEdit = $scope.users.find(x => x.id === $scope.selectedUser.id);
// var courseToEdit = $scope.courses.find(x => x.id ===
// $scope.selectedCourse.id);
        	$http({
        		method: 'post',
        		url: 'services/edit',
        		contentType: 'application/json',
        		data: itemToEdit
        	}).success(function(data){
        		
        	})
        }
    }
	

})