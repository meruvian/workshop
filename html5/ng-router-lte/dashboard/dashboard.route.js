(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('dashboard', {
			url: '/dashboard',
			templateUrl: 'dashboard/dashboard.html'
		});
	}
})();
