$(document).ready(function(){
	$.get("PopulateData", function(data){
		var studentsList = JSON.parse(data);
		var trHTML = '';
		console.log("Hello KOLIA !");
//		console.log(data);
		var welcome = "<h2>Welcome ";
		
		for (var i = 0; i < studentsList.users.length; i++) {
			var student = studentsList.users[i];
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