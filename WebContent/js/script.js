$(document).ready(function(){
	$.get("services/users", function(data){
		var studentsList = data.data.users;
		var courseList = data.data.courses;
		var trHTML = '';
		var trHTML1 = '';
		console.log("Hello KOLIA !");
		console.log(studentsList);
		console.log(courseList);
		var welcome = "<h2>Welcome ";
		
		for (var i = 0; i < studentsList.length; i++) {
			var student = studentsList[i];
			var courseId = student.course;
			if(courseId == null){
				courseId = "N/A"
			}
			else{
				courseId = student.course.id;
			}
//			console.log(student);
			trHTML += '<tr class="users" id="' + student.id  + '"><td>'
			+ student.userId + '</td><td>'
			+ student.firstName + '</td><td>'
			+ student.lastName + '</td><td>'
			+ student.email + '</td><td>'
			+ courseId + '</td><td class="edit" id="edit">'
			+ '<a href="EditServlet?id=' + student.id + '">edit</td><td id="delete">'
			+ '<a href="DeleteServlet?id=' + student.id + '">delete</td></tr>';
		}
		$('#table').append(trHTML);
		
		for (var i = 0; i < courseList.length; i++) {
			var course = courseList[i];

			
			
			trHTML1 += '<tr class="courses" id="' + course.id  + '"><td>'
			+ course.id + '</td><td>'
			+ course.name + '</td><td>'
			+ course.length + '</td>'
			+ '<td class="edit" id="edit">'
			+ '<a href="EditServlet?id=' + course.id + '">edit</td><td id="delete">'
			+ '<a href="DeleteServlet?id=' + course.id + '">delete</td></tr>';
		}
		$('#table1').append(trHTML1);
		
	})
	
})

$(document).ready(function(){
	
	
	
	
	
	
	$("table").on("click", ".users", function() {
//		var uId = $(this).attr("id");
		console.log(this.id);
		$(this).addClass('main').siblings().removeClass('main');
	})
	
	$( "#edit" ).click(function() {
       $('#edit').replaceWith($('<input>' + $('#edit').innerHTML + '</input>'));
    });

	$( "#myButton" ).click(function() {
		$.get("Controller", function(data){
			var user = JSON.parse(data);
			console.log("I m in here")
			console.log(data)
		})
	    });
	
})