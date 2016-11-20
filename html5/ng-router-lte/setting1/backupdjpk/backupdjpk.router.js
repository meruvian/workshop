
(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('backupdjpk', {
			url: '/admin/backupdjpk',
			templateUrl: 'setting1/backupdjpk/backup.dpjk.html'
		});
	}
})();

