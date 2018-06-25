'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('inventoryApp')
	.directive('headerNotification',function(){
		return {
        templateUrl:'scripts/directives/header/header-notification/header-notification.html',
        restrict: 'E',
        replace: true,
    	}
	})
    .controller('notificationCtrl', 
    		[
    			'$scope',
				'$http',
				function($scope, $http){
    				$scope.logOut = function(){
    					$http
						.post('logout');
    				}
	
    }]);


