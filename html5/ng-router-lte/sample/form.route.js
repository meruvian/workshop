(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('form', {
			url: '/sample/form',
			templateUrl: 'sample/form.html'
		});
	}
})();
