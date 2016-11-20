
(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('backup_gaji', {
			url: '/admin/backup_gaji',
			templateUrl: 'setting1/backup_gaji/backup.gaji.html'
		});
	}
})();
