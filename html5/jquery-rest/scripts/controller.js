$(function() {
	
	//get all
	$.get( "http://localhost:8080/api/news", {'title':'io'}, function( data ) {
		  for(d in data){
			  var item = data[d];
			  var itemId = "add.html?id="+item.id;
			  var tr = $("<tr></tr>").appendTo("#tbody");
			  
			  tr.append("<td><a href='"+itemId+"'>"+item.title+"</a></td>");
			  tr.append("<td>"+item.content+"</td>");
			  tr.append("<td><a href='a' title='Delete' data='"+item.id+"' class='btn btn-danger btn-xs del'><i class='fa fa-trash'></i></a></td>");
			
		  }
	}, "json");
	

	
	//post
	$('#searchForm').submit(function(e){
		var data = $('input[name=stitle]').val();
		$('#tbody').empty();
		$.get( "http://localhost:8080/api/news?title="+data, function( data ) {
			  for(d in data){
				  var item = data[d];
				  var itemId = "add.html?id="+item.id;
				  var tr = $("<tr></tr>").appendTo("#tbody");
				  
				  tr.append("<td><a href='"+itemId+"'>"+item.title+"</a></td>");
				  tr.append("<td>"+item.content+"</td>");
				  tr.append("<td><a href='a' title='Delete' data='"+item.id+"' class='btn btn-danger btn-xs del'><i class='fa fa-trash'></i></a></td>");
				
			  }
		}, "json");
		e.preventDefault();
	});
	
	//delete
	$('body').on('click', 'a.del', function(e){
		var id = $(this).attr('data');
		$.ajax({
			  type: "DELETE",
			  url: "http://localhost:8080/api/news/"+id,
			  success: function(data){
				  window.location = "/";
			  }
			});
		e.preventDefault();
	});
	
	//post
	$('#newsForm').submit(function(e){
		var jsonData = ConvertFormToJSON($(this));
		$.ajax({
			  type: "POST",
			  url: "http://localhost:8080/api/news",
			  data: JSON.stringify(jsonData),
			  dataType: "json",
			  contentType: "application/json",
			  success: function(data){ window.location = "/";}
			});
		
		e.preventDefault();
	});
	
	
	
	function ConvertFormToJSON(form){
	    var array = $(form).serializeArray();
	    var json = {};
	    
	    $.each(array, function() {
	        json[this.name] = this.value || '';
	    });
	    
	    return json;
	}
});
