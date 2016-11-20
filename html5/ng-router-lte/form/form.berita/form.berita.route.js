
(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('berita', {
			url: '/form/form.berita',
			templateUrl: 'form/form.berita/form.berita.html'
		});
	}
})();
