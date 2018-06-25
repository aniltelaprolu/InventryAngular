'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:UserDetailCtrl
 * @description # UserDetailCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'UserDetailCtrl',
				[
						'$scope',
						'$http',
						'$modal',
						'$interval',
						'$q',
						function($scope, $http, $modal, $interval, $q) {
							
						} ]);
