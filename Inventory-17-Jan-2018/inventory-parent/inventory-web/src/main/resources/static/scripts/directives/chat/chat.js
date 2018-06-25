'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('inventoryApp')
    .directive("ngFileSelect",function(){
  return {
    link: function($scope,el){
      el.bind("change", function(e,id){
        console.log(e.target.target);
        $scope.file = (e.srcElement || e.target).files[0];
        $scope.getFile(id);
      });
    }
  }})
	.directive('chat',function(){
		return {
        templateUrl:'scripts/directives/chat/chat.html',
        restrict: 'E',
        replace: true,
    	}
	});

