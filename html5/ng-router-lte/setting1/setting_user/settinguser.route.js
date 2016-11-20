'use strict';

angular.module('taspenApp').config(function ($stateProvider) {
	$stateProvider.state('backend.settinguser', {
		url: '/admin/setting_user',
		templateUrl: 'backend/admin/setting1/setting_user/setting.user.html'
	});
});
