'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:ProductCtrl
 * @description # ProductCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'ProductCtrl',
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
							
							$scope.addInvProduct = function() {
								$scope.showAddProductForm();
							};
							
							$scope.editInvProduct = function(entity) {
								$scope.showEditProductForm(entity);
							};
							
							
							/*
							 * Edit Product
							 * 
							 * */
							$scope.showEditProductForm = function(entity) {
								$scope.message = "Update Product";
								
								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/updateProduct.html',
											controller : UpdateProductController,
											scope : $scope,
											resolve : {
												editProductForm : function() {
													return entity;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};

							$scope.deleteInvProduct = function(row) {
								var isConfirmed =confirm ("Are You Sure To Delete "+ row.entity.productName +" ?");
								if(!isConfirmed){
									return false;
								}
								var url = 'service/product/deleteProductById/' + row.entity.productId;
								console.log("Product Id : "+row.entity.productId);

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
							
							$scope.productGridOption = {
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
								columnDefs : [ {
									name : 'productCode',
								},{
									name : 'productName',
								}, {
									name : 'description',
								}, {
									name : 'vendorName',
								},  {
									name : 'productId',
									enableHiding : false,
									visible : false
								},{
								    displayName: "",
								    field: "edit",
								    enableSorting: false, 
								    enableFiltering: false,
								    enableHiding : false,
								    width: "300",
								    cellTemplate:  '<div class="ui-grid-cell-contents">' +
							    					'<button  class="btn btn-xs btn-primary" tooltip="Edit Product Details" tooltip-placement="left" ng-click="grid.appScope.editInvProduct(row)"><i class="glyphicon glyphicon-edit"></i></button>' + '&nbsp;&nbsp;&nbsp;&nbsp;' +
							    					'<button class="btn btn-xs btn-danger" tooltip="Deactive Product" tooltip-placement="left" ng-click="grid.appScope.deleteInvProduct(row)"><i class="glyphicon glyphicon-trash" ></i></button>' +'&nbsp;&nbsp;&nbsp;&nbsp;'+
							    					/*'<button class="btn btn-xs btn-success" ng-click="grid.appScope.addNewVariant(row)"><i class="glyphicon glyphicon-plus" title="Add New Variant">Add Variant</i></button>'+'&nbsp;&nbsp;&nbsp;&nbsp;'+
							    					'<a ui-sref="dashboard.showVariants" class="btn btn-xs btn-success"><i class="fa fa-eye" title="Show All Variant">Show Variant</i></a>'+*/
							    					'</div>'
								
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
								}
							};
							var config = {
								    params: {
								        pageNo: $scope.paginationOptions.pageNo,
								        pageSize: $scope.paginationOptions.pageSize
								    }
							}

							$scope.getPage = function() {
								var url = 'service/product/getAllProduct';
								//
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.productGridOption.totalItems = 100;
													$scope.productGridOption.data = response.data;
												});
							};
						

							$scope.showAddProductForm = function() {
								$scope.message = "Add Product";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/addNewProduct.html',
											controller : AddProductController,
											scope : $scope,
											size  : 'lg',
											resolve : {
												addInvProductForm : function() {
													return $scope.addInvProductForm;
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



var AddProductController = function($scope, $modalInstance, $http, addInvProductForm) {
	//$scope.newProduct = false;
	
	var url = 'service/combodata/getVendorName';
	
	$http
	.get(url)
	.success(
			function(response) {
				console.log(response);
				$scope.vendorNames = response.data;
			})
	.error(function(response) {
		console.log(response);
	});
	
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
	
	
	$scope.GetSelectedSubCategory = function() {
		
		$http({
		    url: 'service/combodata/getSubCategoryName', 
		    method: "GET",
		    params: {categoryId: $scope.CategoryId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.subCategoryNames = response.data;
					
				})
		.error(function(response) {
			console.log(response);
		});
	};
	
	
		$scope.submitInvProductForm = function() {
			$scope.ProductVO={
					vendorId : $scope.VendorId,
					productName : $scope.productName,
					description : $scope.description,
					menuGroupId	: $scope.MenuGroupId,
					categoryId	: $scope.CategoryId,
					subCategoryId	: $scope.SubCategoryId
			}
			
			$http({
			    url: 'service/product/addNewProduct', 
			    method: "POST",
			    data : $scope.ProductVO
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
							alert("successfully Added Product");
						})
				.error(function(response) {
					alert("Product Already Exist");
				});
		};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};




/*
 * Update Product
 * 
 * */
var UpdateProductController = function($scope, $modalInstance, $http, editProductForm) {
	
	var url = 'service/combodata/getVendorName';
		
		$http
		.get(url)
		.success(
				function(response) {
					console.log(response);
					$scope.vendorNames = response.data;
				})
		.error(function(response) {
			console.log(response);
		});
	
		
		
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
		
		
		$scope.GetSelectedSubCategory = function() {
			
			$http({
			    url: 'service/combodata/getSubCategoryName', 
			    method: "GET",
			    params: {categoryId: $scope.CategoryId}
			 })
			.success(
					function(response) {
						console.log(response);
						$scope.subCategoryNames = response.data;
						
					})
			.error(function(response) {
				console.log(response);
			});
		};
		
		
		
	
	$scope.invProduct={
			vendorId 	: editProductForm.entity.vendorId,
			vendorName	: editProductForm.entity.vendorName,
			productId	: editProductForm.entity.productId,
			productName : editProductForm.entity.productName,
			productCode : editProductForm.entity.productCode,
			description : editProductForm.entity.description
			
	}
	console.log("Vendor Id -->"+editProductForm.entity.vendorId);
	
		$scope.submitProductForm = function() {
		if ($scope.form.editProductForm.$valid) {
			var url = 'service/product/updateProduct';
			
			var updateProduct = {
					vendorId	: $scope.invProduct.vendorId,
					newVendorId	: $scope.invNewVendor.vendorId,
					vendorName	: $scope.invProduct.vendorName,
					productId	: $scope.invProduct.productId,
					productName : $scope.invProduct.productName,
					productCode : $scope.invProduct.productCode,
					description : $scope.invProduct.description
			};
			$http
					.post(url, updateProduct)
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
