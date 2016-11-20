(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('settinghakaksesaplikasi', {
			url: '/admin/setting_hak_akses_aplikasi',
			templateUrl: 'setting1/setting_hak_akses_aplikasi/settinghakaksesaplikasi.html'
		});
	}
})();
