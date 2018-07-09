$(document).ready(function getCourses(){
	$.get("PopulateCourses", function(data1){
		var trHTML = '';
		var courseList = JSON.parse(data1);
//		console.log("Hello KOLIA !");
		console.log(data1);
		var welcome = "<h2>Welcome ";
		
		for (var i = 0; i < courseList.courses.length; i++) {
			var course = courseList.courses[i];

			
			
			trHTML += '<tr class="courses" id="' + course.id  + '"><td>'
			+ course.id + '</td><td>'
			+ course.name + '</td><td>'
			+ course.length + '</td>'
			+ '<td class="edit" id="edit">'
			+ '<a href="EditServlet?id=' + course.id + '">edit</td><td id="delete">'
			+ '<a href="DeleteServlet?id=' + course.id + '">delete</td></tr>';
		}
		$('#table1').append(trHTML);
				
	})
	var optionHTML = '';
	function chooseCourse(){
		
		$.each(JSON.parse(data1), function(i, item){
			optionHTML += '<td><select name="course" style="width: 150px">'
				+'<option>' + item.name + '</option>' 
				+ '</select></td>'
		})
	}
	$('#course').append(optionHTML);
	
	
})