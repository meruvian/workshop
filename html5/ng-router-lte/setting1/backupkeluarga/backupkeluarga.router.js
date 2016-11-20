
(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('backupkeluarga', {
			url: '/admin/backupkeluarga',
			templateUrl: 'setting1/backupkeluarga/backupkeluarga.html'
		});
	}
})();