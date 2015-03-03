'use strict';

$(function() {
	$('#save-success').hide();
	$('#save-failed').hide();

	$('#news-form').submit(function(e) {
		e.preventDefault();

		var formData = {
			'title': $(this).find('#title').val(),
			'content': $(this).find('#content').val()
		};

		$.ajax({
			url: 'api/news',
			contentType : 'application/json',
			type : 'POST',
			data: JSON.stringify(formData),
			success: function() {
				$('#save-success').show();
				$('#save-failed').hide();
			},
			error: function() {
				$('#save-success').hide();
				$('#save-failed').show();
			}
		});
	});
});
