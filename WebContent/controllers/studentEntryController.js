app.controller("studentEntryController", function($scope, $cookies, $http, $location, $window) {

    var userId = $cookies.get("userId");
    $http({
        method: 'post',
        url: 'services/student',
        contentType: 'application/json',
        data: userId
    }).then(function(data){
        $scope.student = data.data.data.user;
//		console.log(student);
    })

    $scope.getCoursesPage = function(){
        $location.path("/coursesPage");
    }

})