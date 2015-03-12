'use strict';

$(function() {
	var newsId = '';

	var renderNews = function(news) {
		var dataTable = $('#data-table');
		dataTable.empty();

		$.each(news, function(i, n) {
			var tr = $('<tr></tr>');
			$('<td>').text(n.id).appendTo(tr);
			$('<td>').text(n.title).appendTo(tr);
			$('<td>').text(n.content).appendTo(tr);
			var deleteBtn = $('<a href="#" class="btn btn-danger">Delete</a>');
			deleteBtn.click(function(e) {
				e.preventDefault();

				deleteNews(n.id);
			});

			var editBtn = $('<a href="#" class="btn btn-default">Edit</a>');
			editBtn.click(function(e) {
				e.preventDefault();

				editNews(n);
			});

			$('<td>').append(deleteBtn).append('&nbsp;').append(editBtn).appendTo(tr);

			dataTable.append(tr);
		});
	};

	var addNews = function(news) {
		var url = 'api/news';
		var method = 'POST';

		if (newsId !== '') {
			url += '/' + newsId;
			method = 'PUT';
		}

		$.ajax({
			url: url,
			contentType : 'application/json',
			type : method,
			data: JSON.stringify(news),
			success: function() {
				loadNews('');
			}
		});
	};

	var editNews = function(news) {
		$('#news-form #title').val(news.title);
		$('#news-form #content').val(news.content);
		newsId = news.id;
	};

	var deleteNews = function(id) {
		$.ajax({
			type: 'DELETE',
			url: 'api/news/' + id,
			success: function() {
				loadNews('');
			}
		});
	};

	var loadNews = function(title) {
		title = title || '';
		newsId = '';
		$('#news-form').find('input[type=text], textarea').val('');

		$.getJSON('api/news?title=' + title, renderNews);
	};

	$('#search-box').keyup(function() {
		loadNews($(this).val());
	});

	$('#news-form').submit(function(e) {
		e.preventDefault();

		var news = {
			title: $(this).find('#title').val(),
			content: $(this).find('#content').val()
		};

		addNews(news);
	});

	loadNews('');
});
