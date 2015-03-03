'use strict';

$(function() {
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

	var loadNews = function(title) {
		$.getJSON('api/news?title=' + title, renderNews);
	};

	$('#search-box').keyup(function() {
		loadNews($(this).val());
	});

	loadNews('');
});
