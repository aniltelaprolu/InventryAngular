'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:VariantCtrl
 * @description # VariantCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'VariantCtrl',
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
							
							$scope.addNewVariant = function() {
								$scope.showAddVariantForm();
							}
							
							$scope.updateVariant = function(entity){
								$scope.showEditVariantForm(entity);
							}
							
							
							$scope.showEditVariantForm = function(entity) {
								$scope.message = "Update Variant";
								
								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/update_variant.html',
											controller : variantEditController,
											scope : $scope,
											resolve : {
												editVariantForm : function() {
													return entity;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							
							$scope.deleteVariant = function(row) {
								var isConfirmed =confirm ("Are You Sure To Delete "+ row.entity.variantName +" ?");
								if(!isConfirmed){
									return false;
								}
								var url = 'service/product/deleteVariantById/' + row.entity.variantId;
								console.log("Variant Id : "+row.entity.variantId);

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

							$scope.variantGridOption = {
								exporterMenuCsv : false,
								enableGridMenu : true,
								enableFiltering : true,
								showGridFooter : true,
								showColumnFooter : true,
								responsive : true,
								gridMenuTitleFilter : fakeI18n,
								paginationPageSizes : [ 25, 50, 100 ],
								paginationPageSize : 25,
								useExternalPagination : false,
								columnDefs : [
										{
											name : 'productId',
											enableHiding : false,
											visible : false
										},
										{
											name : 'productName',
											width: '250'
										},
										{
											name : 'variantName',
											width: '250'
										},
										{
											name : 'price',
											width: '150'
										},
										{
											name : 'quantity',
											width: '150'
										},
										{
											displayName : "",
											field : "edit",
											enableSorting : false,
											enableFiltering : false,
											enableHiding : false,
											width : "300",
											cellTemplate : '<div class="ui-grid-cell-contents">'
													+ '<button  class="btn btn-xs btn-primary" tooltip="Edit Variant" tooltip-placement="left" ng-click="grid.appScope.updateVariant(row)"><i class="glyphicon glyphicon-edit" ></i></button>'
													+ '&nbsp;&nbsp;&nbsp;&nbsp;'
													+ '<button class="btn btn-xs btn-danger" tooltip="Delete Variant" tooltip-placement="left" ng-click="grid.appScope.deleteVariant(row)"><i class="glyphicon glyphicon-trash" ></i></button>'
													+ '&nbsp;&nbsp;&nbsp;&nbsp;'
													+ '</div>'

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
								params : {
									pageNo : $scope.paginationOptions.pageNo,
									pageSize : $scope.paginationOptions.pageSize
								}
							}

							$scope.getPage = function() {
								var url = 'service/product/getAllVariants';
								//
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.variantGridOption.totalItems = 100;
													$scope.variantGridOption.data = response.data;
												});
							};
							
							
							
							$scope.showAddVariantForm = function(entity) {
								$scope.message = "Add Variant";
								
								var modalInstance = $modal
										.open({
											templateUrl : 'views/product/addNewVariant.html',
											controller : AddNewVariantController,
											scope : $scope,
											size : 'lg',
											resolve : {
												addInvVariantForm : function() {
													return $scope.addInvVariantForm;
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



var AddNewVariantController = function($scope, $modalInstance, $http, addInvVariantForm,fileReader) {
	

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
	
	
	
	$scope.GetSelectedProduct = function() {
		
		$http({
		    url: 'service/combodata/getProductName', 
		    method: "GET",
		    params: {vendorId: $scope.VendorId}
		 })
		.success(
				function(response) {
					console.log(response);
					$scope.productNames = response.data;
					
				})
		.error(function(response) {
			console.log(response);
		});
	};
	
	
	var url = 'service/combodata/getParameterName';
	
	$http
	.get(url)
	.success(
			function(response) {
				///console.log(response);
				$scope.ParameterNames = response.data;
				
				for(var i=0; i<response.data.length;i++){
					console.log("*************************************************************************")
					console.log(response.data[i].parameterOptions)
					var optsArr = response.data[i].parameterOptions.split(',')
					response.data[i].optsArr = optsArr;
				}
				
				//console.log(response);
				
				$scope.dropdowns = [];
				$scope.dropdowns.push(response.data);
				
				console.log($scope.dropdowns);
			
			})
	.error(function(response) {
		console.log(response);
	});
	
	
	
	$scope.GetSelectedParameterOptions = function(ParameterId) {
		
		console.log("paramerterID", ParameterId)
		
		$http({
		    url: 'service/combodata/getParameterOptionsById', 
		    method: "GET",
		    params: {parameterId: ParameterId}
		 })
		.success(
				function(response) {
					console.log(response);
					console.log(response.data)
				})
		.error(function(response) {
			console.log(response);
		});
	};

	
	

	  $scope.colorsList = [];
	    $scope.change = function(x, arr){
	      $scope.selectedValue = x;
	      $scope.arr = arr;
	      	    }
	    
	   
	    $scope.add = function(){
	    
	       var data =   $scope.arr;
	   
	       data = data.filter(function(e){
	        return e != $scope.selectedValue 
	       });
	       
	       
	      $scope.dropdowns.push(data);
	     

	    }
	    
	    var result = [];
	   
	    $scope.addList = function(name,value){
	    	
	    	console.log("****************");
	    	console.log(name)
	    	console.log(value)
	    	console.log(result)
	    	
	    	if(result.length!=0){
	    		for(var i =0; i< result.length; i++){
	    			console.log(result[i])
	    			if(result[i].parameterId == name.parameterId){
	    				result[i].parameterName = value;
	    			}
	    		}
	    	}else{
	    		var obj = {}
		    	obj.parameterId = name.parameterId;
		    	obj.parameterName = value
		    	
		    	result.push(obj);
	    	}
	    	
	    	
	    	
	    	$scope.colorsList.push({"name":name,"value":value});

	    }
	  
	  	 $scope.rows = [{}];
		
		 $scope.addRow = function() {
			 
			 $scope.rows.push({
			    	
			    });
			 
		  };
			    $scope.removeRow = function($index) {
			        if ($index > -1 && $scope.rows.length > 1) {
			          $scope.rows.splice($index, 1);
			        }
			      };
			      $scope.rows1 = [{}];
					
					 $scope.addRow1 = function() {
						 
						 $scope.rows1.push({
						    	
						    });
						 
					  };
						    $scope.removeRow1 = function($index) {
						        if ($index > -1 && $scope.rows1.length > 1) {
						          $scope.rows1.splice($index, 1);
						        }
						      };
				
						      //Image
						      $scope.images = [];
						      $scope.count =0;
						      $scope.getFile = function () {
						          $scope.progress = 0;
						          fileReader.readAsDataUrl($scope.file, $scope)
						          .then(function(result) {

						              $scope.images.push(result);
						              $scope.count =$scope.count +1;
						          });
						      };
						  	$scope.deleteImages = function($index) {
						  		$scope.images.splice($index);
								$scope.count =$scope.count -1;
						  	};

			
	$scope.submitInvVariantForm = function() {
	if ($scope.form.addInvVariantForm.$valid) {
		var url = 'service/product/addNewVariant';
		var i;
		
		for(i=0;i<$scope.colorsList.length;i++)
		{
			if($scope.colorsList[i].name.name == 'Color')
			{
				$scope.color = $scope.colorsList[i].value;
			}
			if($scope.colorsList[i].name.name == 'Length')
			{
					$scope.length = $scope.colorsList[i].value;
			}
			if($scope.colorsList[i].name.name == 'Size')
			{
				$scope.size = $scope.colorsList[i].value;
			}
		}
		
		var VariantVO = {
					vendorId : $scope.VendorId,
					productId : $scope.productId,
					variantName : $scope.invVariant.variantName,
					price		: $scope.invVariant.price,
					quantity	: $scope.invVariant.quantity,
					variantParams :result
		};
		
		console.log("********************************")
		console.log(VariantVO)
		console.log($scope.dropdowns)
		console.log($scope.dropdowns)
		
		$http
				.post(url, VariantVO)
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
							alert("successfully Added Variant");
						})
				.error(function(response) {
					console.log(response);
				});
	} else {
		console.log('Product is not in scope');
	}
};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};




var variantEditController =function($scope,$modalInstance,$http,editVariantForm){
	
	
// get the data from DB
$scope.invVariant={
		vendorId 	: editVariantForm.entity.vendorId,
		vendorName 	: editVariantForm.entity.vendorName,
		variantName : editVariantForm.entity.variantName,
		productName : editVariantForm.entity.productName,
		productId	: editVariantForm.entity.productId,
		variantId	: editVariantForm.entity.variantId,
		variantName : editVariantForm.entity.variantName,
		price		: editVariantForm.entity.price,
		quantity	: editVariantForm.entity.quantity
		/*parameters:[{
			color   : editVariantForm.entity.parameters[0].color,
			size	: editVariantForm.entity.parameters[0].size,
			length 	: editVariantForm.entity.parameters[0].length
		}]*/
}

$scope.submitUpadateVariantForm = function() {
	if ($scope.form.editVariantForm.$valid) {
		var url = 'service/product/updateVariant';
		
		var VariantVO = {
				vendorId 	: $scope.invVariant.vendorId,
				productId	: $scope.invVariant.productId,
				variantId	: $scope.invVariant.variantId,
				variantName : $scope.invVariant.variantName,
				price 		: $scope.invVariant.price,
				quantity 	: $scope.invVariant.quantity/*,
				parameters:[{
					color   : $scope.selectedColorOption,
					size	: $scope.selectedSizeOption,
					length	: $scope.selectedLength
				}]*/
		};
		$http
				.post(url, VariantVO)
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
		console.log('Variant form is not in scope');
	}
};

$scope.cancel = function() {
	$modalInstance.dismiss('cancel');
};
	
};