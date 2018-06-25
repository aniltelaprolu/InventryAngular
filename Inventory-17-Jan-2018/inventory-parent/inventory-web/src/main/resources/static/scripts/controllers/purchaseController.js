'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:PurchaseCtrl
 * @description # PurchaseCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'PurchaseCtrl',
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
							
							$scope.addPurchaseOrder = function() {
								$scope.showPurchaseForm();
							};
							
							$scope.placeOrder = function() {
								$scope.showReceivedOrderForm();
							}
							
							
							
							$scope.purchaseGridOption = {
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
									displayName: "Purchase No",
									name : 'purchaseNO',
									minWidth: 55,
								},{
									name : 'orderDate',
									type: 'date',
					                cellFilter: 'date:"dd-MM-yyyy"',
									enableHiding : false,
								},{
									name : 'requiredByDate',
									type: 'date',
					                cellFilter: 'date:"dd-MM-yyyy"',
									enableHiding : false,
								},{
									name : 'purchaseId',
									visible : false
								},{
									name : 'shippingAddress',
									enableHiding : false,
								},{
									name : 'billingAddress',
									enableHiding : false,
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
								var url = 'service/purchase/getAllPurchaseDetails';
								//
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.purchaseGridOption.totalItems = 100;
													$scope.purchaseGridOption.data = response.data;
												});
							};
						

							$scope.showPurchaseForm  = function() {
								$scope.message = "Add Purchase";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/purchase/purchase_order.html',
											controller : AddPurchaseOrderController,
											scope : $scope,
											size  : 'lg',
											resolve : {
												addPurchaseForm : function() {
													return $scope.addPurchaseForm;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							
							$scope.showReceivedOrderForm  = function() {
								$scope.message = "Place Order";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/purchase/received_order.html',
											controller : receivedOrderController,
											scope : $scope,
											size  : 'lg',
											resolve : {
												placeOrderForm : function() {
													return $scope.placeOrderForm;
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



var AddPurchaseOrderController = function($scope, $modalInstance, $http, addPurchaseForm) {
	
	/*
	 *Get All VendorName from Vendor Table 
	 * */
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
	 * 
	 *Get All ProductName Based on VendorId From Product Table
	 *
	 * */
	$scope.GetSelectedProduct = function(VendorId) {
		$scope.IsVisible =  false;
		
		$http({
		    url: 'service/combodata/getProductName', 
		    method: "GET",
		    params: {vendorId: VendorId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.productNames = response.data;
					$scope.IsVisible =  true;
				})
		.error(function(response) {
			console.log(response);
		});
		
		$http({
		    url: 'service/combodata/getAddressByVendorId', 
		    method: "GET",
		    params: {vendorId: $scope.VendorId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.billingAddress = response.data[0].billingAddress;
					$scope.shippingAddress = response.data[1].shippingAddress;
					
				})
		.error(function(response) {
			console.log(response);
		});
	};
	
	//add new row
	$scope.rows = [ {} ];
	
	$scope.addRow = function() {
		$scope.rows.push({});

	};
	
	/*
	 * 
	 *Get All VariantName Based on ProductId From Variant Table
	 *
	 * */
	$scope.GetSelectedVariant = function(ProductId) {
		
		$http({
		    url: 'service/combodata/getVariantName', 
		    method: "GET",
		    params: {productId: ProductId}
		 })
		.success(
				function(response) {
					console.log(response);
					//$scope.variantNames = response.data;
					//$scope.rows[ProductId].variantNames= response.data;
					
					for(var i=0; i<$scope.rows.length;i++){
						if($scope.rows[i].ProductId == ProductId){
							$scope.rows[i].variantNames= response.data;
						}
					}
					
					
				})
		.error(function(response) {
			console.log(response);
		});
	};
	
	


	$scope.removeRow = function($index) {
		if ($index > -1 && $scope.rows.length > 1) {
			$scope.rows.splice($index, 1);
		}
	};
	
	$scope.listpurchaseDetails = [];
	
	$scope.addList = function(productId,variantId,quantity){
    	 $scope.listpurchaseDetails.push({"productId":productId,"variantId":variantId,"quantity":quantity});

    }

	
	
	$scope.submitPurchaseForm = function() {
		
		
		
		console.log($scope.rows);
		
		
		for(var i=0; i< $scope.rows.length; i++){
			$scope.rows[i].productId = $scope.rows[i].ProductId 
			$scope.rows[i].variantId = $scope.rows[i].VariantId
			delete($scope.rows[i].ProductId)
			delete($scope.rows[i].VariantId)
		}
		
		console.log($scope.rows)
		
		
		var purchaseOrderVO={
				vendorId 		: $scope.VendorId,
				billingAddress 	: $scope.billingAddress,
				shippingAddress : $scope.shippingAddress,
				orderDate		: $scope.orderDate,
				requiredByDate	: $scope.requiredByDate,
				listpurchaseDetails :$scope.rows
			
		};
		console.log($scope.purchaseOrderVO);
		$http({
		    url: 'service/purchase/purchaseOrder', 
		    method: "POST",
		    data : purchaseOrderVO
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


var receivedOrderController = function($scope, $modalInstance, $http, placeOrderForm) {
	
	
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
	
	
	
	$scope.GetSelectedPONumber = function(vendorId){
		$scope.IsVisible =  false;
		$http({
		    url: 'service/combodata/getAllPurchaseOrderNumberByVendorId',
		    method: "GET",
		    params: {vendorId: vendorId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.purchaseOrderNos = response.data;
					$scope.IsVisible =  true;
					
				})
		.error(function(response) {
			console.log(response);
		});
	};

	
		$scope.GetSelectedPurchaseDetails = function(PurchaseOrderId){
			$scope.IsVisible =  false;
		$http({
		    url: 'service/combodata/getPurchasedetailsByOrderNumber',
		    method: "GET",
		    params: {purchaseOrderId: PurchaseOrderId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.PurchaseDetails = response.data[0].listpurchaseDetails;
					$scope.IsVisible =  true;
					
				})
		.error(function(response) {
			console.log(response);
		});
		
		$http({
			url: 'service/combodata/getPurchasedetailsByOrderNumber',
		    method: "GET",
		    params: {purchaseOrderId: PurchaseOrderId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.billingAddress = response.data[0].billingAddress;
					$scope.shippingAddress = response.data[0].shippingAddress;
					
				})
		.error(function(response) {
			console.log(response);
		});
	};
	

	//add new row
	$scope.rows = [ {} ];
	
	$scope.addRow = function() {
		$scope.rows.push({
			
		});

	};


	$scope.removeRow = function($index) {
		if ($index > -1 && $scope.rows.length > 1) {
			$scope.rows.splice($index, 1);
		}
	};
	
	$scope.orderList = [];
	
	$scope.addList = function(ProductId,VendorId,VariantId,quantity,price){
    	 $scope.orderList.push({"vendorId":VendorId,"productId":ProductId,"variantId":VariantId,"price":price,"quantity":quantity});

    }

	
	
	/*$scope.submitOrderForm = function() {
		
		console.log("**********()()()()()*************")
		console.log($scope.PurchaseDetails)
		
		
		
		$scope.ReceivedOrderVO={
				purchaseOrderId	: $scope.PurchaseOrderId,
				vendorId 		: $scope.VendorId,
				orderDate		: $scope.orderDate,
				shippingDate	: $scope.shippingDate,
				billingAddress	: $scope.billingAddress,
				shippingAddress	: $scope.shippingAddress,
				listReceivedDetails: $scope.addList
				
		};
		console.log($scope.ReceivedOrderVO);
		$http({
		    url: 'service/received/receivedOrder', 
		    method: "POST",
		    data : $scope.ReceivedOrderVO
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
	};*/
	
$scope.submitOrderForm = function() {
		
		console.log("**********()()()()()*************")
		console.log($scope.PurchaseDetails)
		
		
		
		$scope.ReceivedOrderVO={
				purchaseOrderId	: $scope.PurchaseOrderId,
				vendorId 		: $scope.VendorId,
				receivedOrderDate: $scope.orderDate,
				shippingDate	: $scope.shippingDate,
				billingAddress	: $scope.billingAddress,
				shippingAddress	: $scope.shippingAddress,
				listReceivedDetails: $scope.PurchaseDetails		
				/*listReceivedDetails: [{productId: 1, variantId: 1, productName: "HHH", variantName: "AMILSKD", quantity: 4, price: 40},
					{productId: 2, variantId: 2, productName: "HHH", variantName: "AMILSKD", quantity: 4, price: 40}]		*/
		};
		console.log($scope.ReceivedOrderVO);
		$http({
		    url: 'service/received/receivedOrder', 
		    method: "POST",
		    data : $scope.ReceivedOrderVO
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
