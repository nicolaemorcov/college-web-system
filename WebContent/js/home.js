var app = angular.module("myViewer", []);

var mainController = function($scope, $http, $log) {
    //	$scope.user = "Nicolae Morcov";

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
    }
    
//  SetSelectedCourse
    $scope.setSelectedCourse = function(x){
    	console.log("Button clicked, user should appear.");
    	console.log(x);
    	$scope.selectedCourse = x;
    }

//    get all data from db
    $http.get("services/users").then(function(response) {
        $scope.users = response.data.data.users;
        $scope.courses = response.data.data.courses;
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
    
    
////    search for users
//    $scope.search = function(userId) {
//        $scope.userList = [{}];
//        $log.info("Searching for " + userId + " blia");
//        angular.forEach($scope.users, function(value, key) {
//            console.log(key + value.userId);
//            if (userId == value.userId.toLowerCase() || userId == value.email.toLowerCase() || userId == value.lastName.toLowerCase()) {
//                $scope.user = value;
//                $scope.userList.push($scope.user);
//            }
//
//        })
//    }

    //	$scope.id = angular.element(document.querySelector("#id")).val();

    //	$scope.edit = function()

}