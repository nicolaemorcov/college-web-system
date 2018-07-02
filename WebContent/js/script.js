function testFunction(){
	console.log("Hello Kolia");
}

$(document).ready(function(){
	console.log("Hello from home.html");
	
	$.get("PopulateData", function(data){
		var trHTML = '';
		console.log("Hello KOLIA !");
		console.log(data);
		
		
		$.each(JSON.parse(data), function(i, item){
			console.log(i);
			console.log(item);
			console.log(item.firstName);
			
			trHTML += '<tr><td>'
				+ item.userId + '</td><td>'
				+ item.firstName + '</td><td>'
				+ item.lastName + '</td><td>'
				+ item.email + '</td></tr>';
			
		})
		$('#table').append(trHTML);
		
		$('#table tr').click(function(){
			alert($(this).attr("td"));
		})
		
	})
	
	
	
})



/*$(document).ready(function(){
	
})*/