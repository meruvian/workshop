
(function() {
	'use strict';

	angular.module('myApp').config(allRoute);

	function allRoute($stateProvider) {
		$stateProvider.state('resetpassword', {
			url: '/admin/resetpassword',
			templateUrl: 'setting1/reset_password/resetpassword.html'
		});
	}
})();
