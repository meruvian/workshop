(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('pembagiantugaskerja', {
			url: '/admin/pembagiantugaskerja',
			templateUrl: 'setting1/pembagian_tugas_kerja/pembagian.tugas.kerja.html'
		});
	}
})();