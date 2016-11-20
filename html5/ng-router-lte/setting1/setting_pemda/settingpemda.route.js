(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('settingpemda', {
			url: '/admin/settingpemda',
			templateUrl: 'setting1/setting_pemda/setting.pemda.html'
		});
	}
})();