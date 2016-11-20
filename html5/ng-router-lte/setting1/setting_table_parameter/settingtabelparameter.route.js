'use strict';

angular.module('taspenApp').config(function ($stateProvider) {
	$stateProvider.state('backend.settingtabelparameter', {
		url: '/admin/setting_table_parameter',
		templateUrl: 'backend/admin/setting1/setting_table_parameter/setting.table.parameter.html'
	});
});
