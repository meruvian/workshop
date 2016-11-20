var app = angular.module('myApp', ['ui.router']);
app.controller('MainCtrl', function ($state) {
	if ('' === $state.current.name) {
		$state.transitionTo('dashboard');
	}
})
