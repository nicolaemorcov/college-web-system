var app = angular.module("myViewer", []);

var mainController = function($scope, $http, $log) {
    //	$scope.user = "Nicolae Morcov";

    $scope.showEdit = false;

    $scope.test = function() {
        console.log("Button clicked, table should appear.")
        if ($scope.showEdit == true) {
            $scope.showEdit = false;
        } else {
            $scope.showEdit = true;
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

//    get all data from db
    $http.get("services/users").then(function(response) {
        $scope.users = response.data.data.users;
        $scope.courses = response.data.data.courses;
    })

    
//    edit User
    $scope.edit = function(){
    	var userToEdit = $scope.users.find(x => x.id === $scope.selectedUser.id);
    	console.log(userToEdit);
    	$http({
    		method: 'post',
    		url: 'services/edit',
    		contentType: 'application/json',
    		data: userToEdit
    	}).success(function(data){
    		
    	})
    }
    
    
//    search for users
    $scope.search = function(userId) {
        $scope.userList = [{}];
        $log.info("Searching for " + $scope.users.userId + " blia");
        angular.forEach($scope.users, function(value, key) {
            console.log(key + value.userId);
            if (userId == value.userId.toLowerCase() || userId == value.email.toLowerCase() || userId == value.lastName.toLowerCase()) {
                $scope.user = value;
                $scope.userList.push($scope.user);
            }

        })
    }

    //	$scope.id = angular.element(document.querySelector("#id")).val();

    //	$scope.edit = function()

}