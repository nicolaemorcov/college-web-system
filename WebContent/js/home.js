var app = angular.module("myViewer", []);

var mainController = function($scope, $http, $log){
//	$scope.user = "Nicolae Morcov";
	
	$http.get("services/users").then(function(response){
		$scope.users = response.data.data.users ;
		$scope.courses = response.data.data.courses;
	})
	
	$scope.search = function(userId) {
		$scope.userList = [{}];
		$log.info("Searching for " + $scope.users.userId + " blia");
		angular.forEach($scope.users, function(value, key){
			console.log(key + value.userId);
			if(userId==value.userId || userId==value.email || userId==value.lastName){
				$scope.user = value;
				$scope.userList.push($scope.user);
			}
			
		})
	}
	
}