'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:CategoriesCtrl
 * @description # CategoriesCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'CategoriesCtrl',
				[
						'$scope',
						'$http',
						'$modal',
						'$interval',
						'$q',
						function($scope, $http, $modal, $interval, $q) {
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
							
							$scope.addMenus = function() {
								$scope.showMenuForm();
							};
							
							$scope.editCategory = function(entity) {
								$scope.showEditCategoryForm(entity);
							};
							
							$scope.addCategory = function(entity) {
								$scope.showNewCategoryForm(entity);
							};
							
							$scope.addSubCategory = function(entity) {
								$scope.showNewSubCategoryForm(entity);
							};
							
							/*
							 * Edit 
							 * 
							 * */
							$scope.showEditCategoryForm = function(entity) {
								$scope.message = "Update Category";
								
								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/update_categories_details.html',
											controller : UpdateMenuController,
											scope : $scope,
											resolve : {
												editMenuForm : function() {
													return entity;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};

							$scope.deleteMenu= function(row) {
								var isConfirmed =confirm ("Are You Sure To Delete "+ row.entity.menuGroupName +" ?");
								if(!isConfirmed){
									return false;
								}
								var url = 'service/category/deleteMenuById/' + row.entity.menuGroupId;

								$http
										.delete(url)
										.success(
												function(response) {
													alert("successfully deleted");
													$scope.paginationOptions = {
															pageNo : 1,
															pageSize : 25,
															sort : null
														};
													$scope.getPage();
												});
							};
							
							$scope.categoriesGridOption = {
									exporterMenuCsv : false,
									enableGridMenu : true,
									enableFiltering: true,
									showGridFooter: true,
									showColumnFooter: true,
									responsive:true,
									gridMenuTitleFilter : fakeI18n,
									paginationPageSizes : [ 25, 50, 100 ],
									paginationPageSize : 25,
									useExternalPagination : false,
									//rowHeight:60,
									columnDefs : [ {
										name : 'menuGroupName',
										width: '250',
										rowHeight:60
									},
									{	displayName: "categoryName",
										name : 'category[0].categoryName',
										width: '250'
									}
								/*	{ field: 'category', name: 'categoryName',width: '250', cellTemplate:'<div ng-repeat="item in row.entity[col.field]">{{item.categoryName}}</div>'}*/
									,
										/*displayName: "SubCategory",
										minWidth: 55,
										name : 'category[0].subCategory[0].subCategoryName',*/
										{ field: 'category', name: 'subCategoryName',width: '250', cellTemplate:'<div ng-repeat="item in row.entity[col.field]">{{item.subCategory[0].subCategoryName}}</div>'}
									,   {
										name : 'productId',
										enableHiding : false,
										visible : false
									},{
									    displayName: "",
									    field: "edit",
									    enableSorting: false, 
									    enableFiltering: false,
									    enableHiding : false,
									    width: "350",
									    enableColumnMenus: false,
									    cellTemplate:  '<div class="ui-grid-cell-contents">'
									    	+'<button  class="btn btn-xs btn-primary" tooltip="Edit" tooltip-placement="left" ng-click="grid.appScope.editCategory(row)"><i class="glyphicon glyphicon-edit" title="Edit"></i></button>'
									    	+'&nbsp;&nbsp;&nbsp;&nbsp;'+
									    	'<button  class="btn btn-xs btn-primary" tooltip="add Category" tooltip-placement="left" ng-click="grid.appScope.addCategory(row)"><i class="fa fa-plus" title="add Category">&nbsp;&nbsp;&nbsp; add Category</i></button>'
									    	+'&nbsp;&nbsp;&nbsp;&nbsp;'+
									    	'<button  class="btn btn-xs btn-primary" tooltip="add SubCategory" tooltip-placement="left" ng-click="grid.appScope.addSubCategory(row)"><i class="fa fa-plus" title="add SubCategory">&nbsp;&nbsp;&nbsp; add SubCategory</i></button>'
									    	+'&nbsp;&nbsp;&nbsp;&nbsp;'+
									    	'<button class="btn btn-xs btn-danger" tooltip="Delete" tooltip-placement="left" ng-click="grid.appScope.deleteMenu(row)"><i class="glyphicon glyphicon-trash" ></i></button>'
									    	+'</div>'
									
									} ],

									onRegisterApi : function(gridApi) {
										$scope.gridApi = gridApi;
										//
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
									},
								enableColumnResizing: true
								};
							var config = {
								    params: {
								        pageNo: $scope.paginationOptions.pageNo,
								        pageSize: $scope.paginationOptions.pageSize
								    }
							}

							$scope.getPage = function() {
								var url = 'service/category/getAllMenuGroups';
								//
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.categoriesGridOption.totalItems = 100;
													$scope.categoriesGridOption.data = response.data;
												});
							};
						

							$scope.showMenuForm  = function() {
								$scope.message = "Add Category";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/add_menu_details.html',
											controller : AddMenuController,
											scope : $scope,
											resolve : {
												addMenuForm : function() {
													return $scope.addMenuForm;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							
							$scope.showNewCategoryForm = function(entity) {
								$scope.message = "Add Category";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/add_new_category.html',
											controller : AddNewCategoryController,
											scope : $scope,
											resolve : {
												addNewCategoryForm : function() {
													return entity;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							$scope.showNewSubCategoryForm = function(entity) {
								$scope.message = "Add SubCategory";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/add_new_subcategory.html',
											controller : AddNewSubCategoryController,
											scope : $scope,
											resolve : {
												addNewSubCategoryForm : function() {
													return entity;
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



var AddMenuController = function($scope, $modalInstance, $http, addMenuForm) {
	//$scope.newProduct = false;
	
		$scope.submitMenuForm = function() {
			$scope.menuGroupVO={
					menuGroupName	: $scope.menuGroupName/*,
					category	:[{
						categoryName	: $scope.categoryName,
						subCategory :[{
							subCategoryName	: $scope.subCategoryName
						}]
					}]*/
			}
			
			$http({
			    url: 'service/category/addNewMenuGroup', 
			    method: "POST",
			    data : $scope.menuGroupVO
			 })
			 .success(
						function(response) {
							$scope.paginationOptions = {
									pageNo : 1,
									pageSize : 25,
									sort : null
								};
							$scope.getPage();
							$modalInstance.close('closed');
							alert("successfully Added Menus");
						})
				.error(function(response) {
					alert("Technical Error");
				});
		};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};


var AddNewCategoryController = function($scope, $modalInstance, $http, addNewCategoryForm) {
	
		/*
		 * Get Menus
		 * */
		var url = 'service/combodata/getMenus';
		
		$http
		.get(url)
		.success(
				function(response) {
					console.log(response);
					$scope.menuNames = response.data;
				})
		.error(function(response) {
			console.log(response);
		});
	
		$scope.submitNewCategoryForm = function() {
			$scope.CategoryVO={
					menuGroupId	: $scope.MenuGroupId,
					categoryName:$scope.categoryName
					//categoryCode:$scope.categoryCode
			}
			
			$http({
			    url: 'service/category/addNewCategory', 
			    method: "POST",
			    data : $scope.CategoryVO
			 })
			 .success(
						function(response) {
							$scope.paginationOptions = {
									pageNo : 1,
									pageSize : 25,
									sort : null
								};
							$scope.getPage();
							$modalInstance.close('closed');
							alert("successfully Added Category");
						})
				.error(function(response) {
					alert("Technical Error");
				});
		};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};


var AddNewSubCategoryController = function($scope, $modalInstance, $http, addNewSubCategoryForm) {
	
	/*
	 * Get Menus
	 * */
	var url = 'service/combodata/getMenus';
	
	$http
	.get(url)
	.success(
			function(response) {
				console.log(response);
				$scope.menuNames = response.data;
			})
	.error(function(response) {
		console.log(response);
	});
	
	/*
	 * Get Category Name
	 * */
	
	$scope.GetSelectedCategory = function() {
		
		$http({
		    url: 'service/combodata/getCategoryName', 
		    method: "GET",
		    params: {menuGroupId: $scope.MenuGroupId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.categoryNames = response.data;
					
				})
		.error(function(response) {
			console.log(response);
		});
	};
	
	
	$scope.submitNewSubCategoryForm = function() {
		$scope.SubCategoryVO={
				menuGroupId : $scope.MenuGroupId,
				categoryId	: $scope.CategoryId,
				subCategoryName:$scope.subCategoryName
				//subCategoryCode:$scope.subCategoryCode
		}
		
		$http({
		    url: 'service/category/addNewSubCategory', 
		    method: "POST",
		    data : $scope.SubCategoryVO
		 })
		 .success(
					function(response) {
						$scope.paginationOptions = {
								pageNo : 1,
								pageSize : 25,
								sort : null
							};
						$scope.getPage();
						$modalInstance.close('closed');
						alert("successfully Added SubCategory");
					})
			.error(function(response) {
				alert("Technical Error");
			});
	};

$scope.cancel = function() {
	$modalInstance.dismiss('cancel');
};
};


var UpdateMenuController = function($scope, $modalInstance, $http, editMenuForm) {
	
	
	$scope.menuGroup={
			menuGroupId	:editMenuForm.entity.menuGroupId,
			menuGroupName 	: editMenuForm.entity.menuGroupName,
			categoryName	: editMenuForm.entity.category[0].categoryName
			
	}
	console.log("MenuGroup Id -->"+editMenuForm.entity.menuGroupId);
	console.log("MenuGroup Name -->"+editMenuForm.entity.menuGroupName);
	
		$scope.submitEditMenuForm = function() {
		if ($scope.form.editMenuForm.$valid) {
			var url = 'service/category/editMenuGroupById';
			
			var updateMenu = {
					menuGroupId	: $scope.menuGroup.menuGroupId,
					menuGroupName: $scope.menuGroup.menuGroupName,
					category:[{
						categoryName : $scope.menuGroup.categoryName
					}]
					
			};
			$http
					.post(url, updateMenu)
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
								alert("successfully Updated");
							})
					.error(function(response) {
						console.log(response);
					});
		} else {
			console.log('Product form is not in scope');
		}
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

