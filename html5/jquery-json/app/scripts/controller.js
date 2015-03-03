$(function() {
	// $.get( "scripts/contents/news.json", function( data ) {
	// 	  for(d in data.news){
	// 		  item = data.news[d];
	// 		  var tr = $("<tr></tr>").appendTo("#tbody");
	// 		  tr.append("<td>"+item.title+"</td>");
	// 		  tr.append("<td>"+item.content+"</td>");
	// 		  tr.append("<td>"+item.description+"</td>");
	// 	  }
	// 	}, "json" );

	var renderNews = function(news) {
		var dataTable = $('#data-table');
		dataTable.empty();

		$.each(news, function(i, n) {
			var tr = $('<tr></tr>');
			$('<td>').text(n.id).appendTo(tr);
			$('<td>').text(n.title).appendTo(tr);
			$('<td>').text(n.content).appendTo(tr);

			dataTable.append(tr);
		});
	};

	var loadNews = function(data) {
		$.getJSON('data/' + data, renderNews);
	};

	loadNews('news1.json');

	$('#news1').click(function() {
		loadNews('news1.json');
	});

	$('#news2').click(function() {
		loadNews('news2.json');
	});
});
