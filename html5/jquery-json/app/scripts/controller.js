$(function() {
	$.get( "scripts/contents/news.json", function( data ) {
		  for(d in data.news){
			  item = data.news[d];
			  var tr = $("<tr></tr>").appendTo("#tbody");
			  tr.append("<td>"+item.title+"</td>");
			  tr.append("<td>"+item.content+"</td>");
			  tr.append("<td>"+item.description+"</td>");
		  }
		}, "json" );
});
