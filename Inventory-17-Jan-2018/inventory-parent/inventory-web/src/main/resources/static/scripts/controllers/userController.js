'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:UserCtrl
 * @description # UserCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'UserCtrl',
				[
						'$scope',
						'$http',
						'$modal',
						'$interval',
						'$q',
						'$state',
						function($scope, $http, $modal, $interval, $q, $state) {

							var fakeI18n = function(title) {
								var deferred = $q.defer();
								$interval(function() {
									deferred.resolve('col: ' + title);
								}, 1000, 1);
								return deferred.promise;
							};

							$scope.paginationOptions = {
								pageNo : 1,
								pageSize : 25,
								sort : null
							};

							$scope.addInvUser = function() {
								$scope.showAddUserForm();
							};
							
							$scope.editInvUser = function() {
								$scope.showEditUserForm();
							};

							$scope.deleteInvUser = function(row) {
								var url = 'service/user/delete/' + row.entity.userId;

								$http
										.get(url)
										.success(
												function(response) {
													console.log(response);
													$scope.paginationOptions = {
															pageNo : 1,
															pageSize : 25,
															sort : null
														};
													$scope.getPage();
												});
							};
							
							$scope.userGridOption = {
								exporterMenuCsv : false,
								enableGridMenu : true,
								enableFiltering: true,
								showGridFooter: true,
								showColumnFooter: true,
								gridMenuTitleFilter : fakeI18n,
								paginationPageSizes : [ 25, 50, 75 ],
								paginationPageSize : 25,
								useExternalPagination : true,
								columnDefs : [ {
									name : 'userId',
									width: 90,
									enableHiding : false
								}, {
									name : 'firstName',
									width: 200,
								}, {
									name : 'lastName',
									width: 200,
								}, {
									displayName: "Email",
									name : 'userEmail',
									width: 350,
									enableHiding : false
								}, {
									name : 'role',
									width: 110,
								}, {
									name : 'status',
									width: 110,
								}, {
								    displayName: "",
								    field: "delete",
								    enableSorting: false,
								    enableFiltering : false,
								    width: "80",
								    cellTemplate: '<div class="ui-grid-cell-contents">' +
								    					'<span class="grid-cell-icon fa fa-trash" ng-click="grid.appScope.deleteInvUser(row)"></span>' + '&nbsp;&nbsp;&nbsp;&nbsp;' +
								    					'<span class="grid-cell-icon fa fa-edit" ng-click="grid.appScope.showEditUserForm(row)"></span>' +
								    					/*'<a ui-sref=".details" class="btn btn-primary">edit</a>' +*/
								    			  '</div>'
								} ],

								onRegisterApi : function(gridApi) {
									$scope.gridApi = gridApi;
									/*
									 * $scope.gridApi.core.on.sortChanged($scope,
									 * function(grid, sortColumns) { if
									 * (sortColumns.length == 0) {
									 * paginationOptions.sort = null; } else {
									 * paginationOptions.sort =
									 * sortColumns[0].sort.direction; }
									 * getPage(); });
									 */
									// interval of zero just to allow the
									// directive to have initialized
									$interval(function() {
										gridApi.core.addToGridMenu(
												gridApi.grid, [ {
													title : 'Dynamic item',
													order : 100
												} ]);
									}, 0, 1);

									gridApi.core.on
											.columnVisibilityChanged(
													$scope,
													function(changedColumn) {
														$scope.columnChanged = {
															name : changedColumn.colDef.name,
															visible : changedColumn.colDef.visible
														};
													});

									gridApi.pagination.on
											.paginationChanged(
													$scope,
													function(newPage, pageSize) {
														$scope.paginationOptions.pageNo = newPage;
														$scope.paginationOptions.pageSize = pageSize;
														$scope.getPage();
													});
								}
							};

							$scope.getPage = function() {
								var url = 'service/user/getAll';
								/*
								 * switch(paginationOptions.sort) { case
								 * uiGridConstants.ASC: url =
								 * '/data/100_ASC.json'; break; case
								 * uiGridConstants.DESC: url =
								 * '/data/100_DESC.json'; break; default: url =
								 * '/data/100.json'; break; }
								 */

								$http
										.post(url, $scope.paginationOptions)
										.success(
												function(response) {
													$scope.userGridOption.totalItems = 100;
													$scope.userGridOption.data = response.data;
												});
							};

							$scope.showAddUserForm = function() {
								$scope.message = "Add User";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/user/modalAddUser.html',
											controller : modalAddUserController,
											scope : $scope,
											resolve : {
												addInvUserForm : function() {
													return $scope.addInvUserForm;
												}
											}
										});
							};
							
							$scope.showEditUserForm = function(row) {
								/*$state.go('dashboard.user.details');*/
								$scope.message = "Edit User";
								/*window.open('views/user/userDetail?userId='+row.entity.userId,
								        "_blank",
								        'menubar=no,toolbar=no,location=no,directories=no,status=no,resizable=no,dependent,width=800,height=620,left=0,top=0');*/
								var modalInstance = $modal
										.open({
											templateUrl : 'views/user/modalEditUser.html',
											controller : modalEditUserController,
											scope : $scope,
											resolve : {
												editInvUserForm : function() {
													return row.entity;
												}
											}
										});
								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};

							$scope.getPage();
						} ]);

var modalAddUserController = function($scope, $modalInstance, $http, addInvUserForm) {
	$scope.submitInvUserForm = function() {
		if ($scope.form.addInvUserForm.$valid) {
			var url = 'service/user/add';
			var addUserOpt = {
					userEmail : $scope.invUser.userEmail,
					firstName : $scope.invUser.firstName,
					lastName  : $scope.invUser.lastName,
					role 	  : $scope.invUser.role
			};
			$http
					.post(url, addUserOpt)
					.success(
							function(response) {
								console.log(response);
								$scope.paginationOptions = {
										pageNo : 1,
										pageSize : 25,
										sort : null
									};
								$scope.getPage();
								$modalInstance.close('closed');
							})
					.error(function(response) {
						console.log(response);
					});
		} else {
			console.log('userform is not in scope');
		}
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var modalEditUserController = function($scope, $modalInstance, $http, $state, editInvUserForm) {
	$scope.invUser = editInvUserForm;
	$scope.submitUpdatedUserForm = function() {
		if ($scope.form.updateInvUserForm.$valid) {
			var url = 'service/user/update';
			
			console.log($scope.invUser);
			$http
					.post(url, $scope.invUser)
					.success(
							function(response) {
								console.log(response);
								
								$scope.paginationOptions = {
										pageNo : 1,
										pageSize : 25,
										sort : null
									};
								$scope.getPage();
								
								$modalInstance.close('closed');
							})
					.error(function(response) {
						
						console.log(response);
					});
		} else {
		}
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};