'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:OffersCtrl
 * @description # OffersCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'OffersCtrl',
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
							
							$scope.addNewOffers = function() {
								$scope.showNewOffersForm();
							};
							
							$scope.editOffers = function(entity) {
								$scope.showEditOfferForm(entity);
							};
							
							$scope.showEditOfferForm = function(entity) {
								$scope.message = "Update Offres";
								
								var modalInstance = $modal
										.open({
											templateUrl : 'views/offers/update_offers.html',
											controller : UpdateOffersController,
											scope : $scope,
											size  : 'lg',
											resolve : {
												editOfferForm : function() {
													return entity;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							
							$scope.deleteOffer = function(row) {
								var isConfirmed = confirm("Are you sure to delete "+row.entity.offerName +" ?");
								if(!isConfirmed){
									return false;
								}
								
								var url = 'service/offer/deleteOffersById/' + row.entity.offerDetailId;

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
							
							
							
							$scope.offersGridOption = {
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
									displayName: "Offer Name",
									name : 'offerName',
									minWidth: 55,
								},{
									name : 'startDate',
									type: 'date',
					                cellFilter: 'date:"dd-MM-yyyy"',
									enableHiding : false,
								},{
									name : 'endDate',
									type: 'date',
					                cellFilter: 'date:"dd-MM-yyyy"',
									enableHiding : false,
								},{
									name : 'offerDetailId',
									visible : false
								},{
									name : 'description',
									enableHiding : false,
								},{
									displayName: "Discount",
									name : 'discountPercentage',
									enableHiding : false,
								},{
									displayName: "Status",
									name : 'offerStatus',
									enableHiding : false,
								},{
								    displayName: "",
								    field: "edit",
								    enableSorting: false, 
								    enableFiltering: false,
								    enableHiding : false,
								    width: "300",
								    enableColumnMenus: false,
								    cellTemplate:  '<div class="ui-grid-cell-contents">'
								    	+'<button  class="btn btn-xs btn-primary" tooltip="Edit" tooltip-placement="left" ng-click="grid.appScope.editOffers(row)"><i class="glyphicon glyphicon-edit" title="Edit"></i></button>'
								    	+'&nbsp;&nbsp;&nbsp;'+'<button class="btn btn-xs btn-danger" ng-click="grid.appScope.deleteOffer(row)"><i class="fa fa-trash" title="Delete"></i></button>'
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
								var url = 'service/offer/getAllOfferDetails';
								//
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.offersGridOption.totalItems = 100;
													$scope.offersGridOption.data = response.data;
												});
							};
						

							$scope.showNewOffersForm  = function() {
								$scope.message = "showNewOffersForm";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/offers/createNewOffers.html',
											controller : createNewOffersController,
											scope : $scope,
											size  : 'lg',
											resolve : {
												addOffersForm : function() {
													return $scope.addOffersForm;
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



var createNewOffersController = function($scope, $modalInstance, $http, addOffersForm) {

	
		var url = 'service/combodata/getProductNames';
		
		$http
		.get(url)
		.success(
				function(response) {
					console.log(response);
					$scope.productNames = response.data;
				})
		.error(function(response) {
			console.log(response);
		});
		
		
		
		$scope.GetSelectedVariant = function() {
			
			$http({
			    url: 'service/combodata/getVariantName', 
			    method: "GET",
			    params: {productId: $scope.ProductId}
			 })
			.success(
					function(response) {
						console.log(response);
						$scope.variantNames = response.data;
						
					})
			.error(function(response) {
				console.log(response);
			});
			
		};
		
		$scope.GetSelectedQuantity = function(VariantId,ProductId) {
			
			$http({
				url: 'service/offer/getInventoryItemQuantity',
			    method: "GET",
			    params: {productId: ProductId,variantId:VariantId}
			 })
			.success(
					function(response) {
						console.log(response);
						$scope.quantity = response.data[0].quantity;
						
					})
			.error(function(response) {
				console.log(response);
			});
		};
	
		
		$scope.submitOffersForm = function() {
			var ItemLevelOfferVO={
					productId 	: $scope.ProductId,
					variantId 	: $scope.VariantId,
					startDate	: $scope.startDate,
					endDate		: $scope.endDate,
					offerName	: $scope.offerName,
					discountPercentage : $scope.discount,
					flatDiscount: $scope.flatDiscount,
					description	: $scope.description,
					quantity	: $scope.quantity,
					givenQuantity: $scope.givenQuantity
			};
			console.log($scope.ItemLevelOfferVO);
			
			$http({
			    url: 'service/offer/createNewOffer', 
			    method: "POST",
			    data : ItemLevelOfferVO
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
							alert("successfully Done");
						})
				.error(function(response) {
					alert("Technical Error");
				});
		};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};



var UpdateOffersController = function($scope, $modalInstance, $http, editOfferForm) {
	

	$scope.itemLeevelOffers={
			offerDetailId	: editOfferForm.entity.offerDetailId,
			offerName 		: editOfferForm.entity.offerName,
			offerCode		: editOfferForm.entity.offerCode,
			productName		: editOfferForm.entity.productName,
			description		: editOfferForm.entity.description,
			quantity		: editOfferForm.entity.quantity,
			givenQuantity	: editOfferForm.entity.givenQuantity,
			discount		: editOfferForm.entity.discountPercentage,
			flatDiscount	: editOfferForm.entity.flatDiscount,
			startDate		: editOfferForm.entity.startDate,
			endDate			: editOfferForm.entity.endDate,
			productId		: editOfferForm.entity.productId,
			variantId		: editOfferForm.entity.variantId
			
			
	}
	
	$scope.submitUpdateOffersForm = function() {
		if ($scope.form.editOfferForm.$valid) {
			var url = 'service/offer/updateOfferDetails';
			
			var ItemLevelOfferVO = {
					offerDetailId	: $scope.itemLeevelOffers.offerDetailId,
					offerName		: $scope.itemLeevelOffers.offerName,
					offerCode		: $scope.itemLeevelOffers.offerCode,
					productName		: $scope.itemLeevelOffers.productName,
					description		: $scope.itemLeevelOffers.description,
					quantity		: $scope.itemLeevelOffers.quantity,
					givenQuantity	: $scope.itemLeevelOffers.givenQuantity,
					discountPercentage: $scope.itemLeevelOffers.discount,
					flatDiscount	: $scope.itemLeevelOffers.flatDiscount,
					startDate		: $scope.itemLeevelOffers.startDate,
					endDate			: $scope.itemLeevelOffers.endDate,
					productId		: $scope.itemLeevelOffers.productId,
					variantId		: $scope.itemLeevelOffers.variantId
					
			};
			$http
					.post(url, ItemLevelOfferVO)
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
