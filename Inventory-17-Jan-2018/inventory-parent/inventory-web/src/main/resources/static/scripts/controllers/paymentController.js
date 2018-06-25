'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:PaymentCtrl
 * @description # PaymentCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'PaymentCtrl',
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
							
							$scope.createNewPayment = function() {
								$scope.showPaymentForm();
							};
							
							
							$scope.paymentGridOption = {
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
									displayName: "Vendor Name",
									name : 'vendorName',
									minWidth: 55,
								},{
									displayName: "Total Amount",
									name : 'totalAmount',
									enableHiding : false,
								},{
									displayName: "Pay Amount",
									name : 'payAmount',
									enableHiding : false,
								},{
									displayName: "Pay Mode",
									name : 'payBy',
								},{
									displayName: "Payment Status",
									name : 'inventoryPaymentStatus',
								},{
								    displayName: "",
								    field: "edit",
								    enableSorting: false, 
								    enableFiltering: false,
								    enableHiding : false,
								    width: "300",
								    enableColumnMenus: false,
								    cellTemplate:  '<div class="ui-grid-cell-contents"></div>'
								
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
								var url = 'service/payment/getAllPaymentDetails';
								//
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.paymentGridOption.totalItems = 100;
													$scope.paymentGridOption.data = response.data;
												});
							};
						

							$scope.showPaymentForm  = function() {
								$scope.message = "showPaymentForm";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/payment/createNewPayment.html',
											controller : createPaymentController,
											scope : $scope,
											size  : 'lg',
											resolve : {
												createPayment : function() {
													return $scope.createPayment;
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



var createPaymentController = function($scope, $modalInstance, $http, createPayment) {

	

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
	
	
	var url = 'service/companyprofile/getAllCompanyBankDetails';
	
	$http
	.get(url)
	.success(
			function(response) {
				console.log(response);
				$scope.companyBankDetails = response.data;
			})
	.error(function(response) {
		console.log(response);
	});
	
	
	$scope.GetAllBankDetails = function(VendorId) {
		$scope.IsVisible =  false;
		$scope.IsVisibleCheque = false;
		
		var listBank=[{
			vendorAccountId:"",
			accountHolderName:"",
			bankName:"",
			branch:"",
			accountNumber:"",
			ifscCode:""
		}];
		
		$http({
			url: 'service/vendor/getBankDetailsByVendorId',
		    method: "GET",
		    params: {vendorId: $scope.VendorId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.listBank = response.data;
					
				})
		.error(function(response) {
			console.log(response);
		});
		
	};
	
	$scope.GetSelectedPurchaseOrderNumber = function(poStatus) {
			
			$http({
			    url: 'service/payment/getPurchaseOrderNumberByStatus', 
			    method: "GET",
			    params: {poStatus: $scope.poStatus}
			 })
			.success(
					function(response) {
						console.log(response);
						$scope.purchaseOrderNumbers = response.data;
					})
			.error(function(response) {
				console.log(response);
			});
			
		};
		
	
		
		$scope.GetSelectedReceivedDetails = function(PurchaseId) {
			
			$http({
				url: 'service/payment/getReceivedOrderNumberByPurchaseId',
			    method: "GET",
			    params: {purchaseOrderId: $scope.purchaseOrderId}
			 })
			.success(
					function(response) {
						console.log(response);
						$scope.ReceivedOrderNumbers = response.data;
						
					})
			.error(function(response) {
				console.log(response);
			});
		};
		
		
		$scope.GetTotalAmount = function(ReceivedOrderId) {
			
			$http({
				url: 'service/payment/getTotalAmountDetails',
			    method: "GET",
			    params: {receivedOrderId: $scope.ReceivedOrderId}
			 })
			.success(
					function(response) {
						console.log(response);
						$scope.totalAmount = response.data[0].totalAmount;
						
					})
			.error(function(response) {
				console.log(response);
			});
		};
		
		
		$scope.showBankDetails = function() {
			$scope.IsVisible =  true;
			$scope.IsVisibleCheque = false;
		};
		$scope.hideBankDetails = function() {
			$scope.IsVisibleCheque = true;
			$scope.IsVisible =  false;
		};
		
		$scope.submitcreatePaymentForm = function() {
			var paymentVO={
					vendorId : $scope.VendorId,
					poStatus : $scope.poStatus,
					purchaseOrderId :$scope.purchaseOrderId,
					receivedOrderId	: $scope.ReceivedOrderId,
					totalAmount		: $scope.totalAmount,
					payAmount		: $scope.payAmount,
					payBy			: $scope.payBy
					
			};
			console.log($scope.paymentVO);
			
			$http({
			    url: 'service/payment/makePayment', 
			    method: "POST",
			    data : paymentVO
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
