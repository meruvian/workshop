$(function() {
	var sliderFullScreen = function() {
		var img = $('.carousel-inner .item img');
		img.each(function() {
			var i = $(this);

			var div = i.next('.carousel-img')[0] || $('<div></div>').addClass('carousel-img');
			div = $(div);
			div.css('height', $(window).innerHeight() + 'px');
			div.css('background-image', 'url(' + i.attr('src') + ')');
			div.css('background-position', '50%');
			div.css('background-size', 'cover');

			i.after(div);
			i.hide();
		});
	}

	$(window).resize(sliderFullScreen).triggerHandler('resize');

	$(window).bind('scroll', function() {
		var distanceY = $(this).scrollTop(), shrinkOn = $(this).innerHeight;
		// scope.logoTransparent = distanceY <= shrinkOn - 100;
	});
});
