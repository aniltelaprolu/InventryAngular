'use strict';
/**
 * @author ES002
 * @ngdoc function
 * @name inventoryApp.controller:VendorCtrl
 * @description # VendorCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection','ui.grid.rowEdit' ])
		.controller(
				'VendorCtrl',
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
							
							$scope.addInvVendor = function() {
								$scope.showAddVendorForm();
							};
							
							$scope.selectedData=[];
							$scope.sendNotification = function() {
								var url = 'service/vendor/sendMail/';
							// console.log(row.entity.email);
								$http
										.post(url,$scope.selectedData)
										.success(
												function(response) {
													alert("successfully Send");
													
												});								
							};
							
							$scope.sendVendor = function(row) {
								$scope.data = [];
								var url = 'service/vendor/sendMail/';
							// console.log(row.entity.email);
								var InvVendorVO={
										email: row.entity.email,
										name: row.entity.name
								}
								$scope.data.push(InvVendorVO);
								$http
										.post(url,$scope.data)
										.success(
												function(response) {
													alert("successfully Send");
													
												});								
							};
							


							$scope.deleteInvVendor = function(row) {
								var isConfirmed = confirm("Are you sure to delete "+row.entity.name +" ?");
								if(!isConfirmed){
									return false;
								}
								
								var url = 'service/vendor/deleteById/' + row.entity.vendorId;

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
							
							
							$scope.bankDelete = function(bank) {
								var isConfirmed = confirm("Are you sure to delete "+bank.bankName +" Bank Details ?");
								if(!isConfirmed){
									return false;
								}
								
								$http({
								    url: 'service/vendor/deletebankDetailsById', 
								    method: "DELETE",
								    params: {vendorId: bank.vendorId,vendorAccountId:bank.vendorAccountId}
								 })
								.success(
										function(response) {
											console.log(response);
											$scope.getPage();
											alert("successfully deleted");
										})
								.error(function(response) {
									console.log(response);
								});
							};
						
							$scope.bankEdit = function(bank) {
								$scope.showUpdateBankForm(bank);
							};
							
							$scope.showUpdateBankForm = function(bank) {
								$scope.message = "Update A Bank";
								

								var modalInstance = $modal
										.open({
											templateUrl : 'views/vendor/edit_bank.html',
											controller : bankUpdateVendorController,
											scope : $scope,
											size : 'lg',
											resolve : {
												editBankDetails : function() {
													return bank;
												}
											}
										});
								
								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							
							$scope.updateInvVendor = function(entity) {
								$scope.showUpdateVendorForm(entity);
							};
							
							$scope.showUpdateVendorForm = function(entity) {
								$scope.message = "Update A Vendor";
								

								var modalInstance = $modal
										.open({
											templateUrl : 'views/vendor/edit_vendor.html',
											controller : modalUpdateVendorController,
											scope : $scope,
											size : 'lg',
											resolve : {
												updateInvVendorForm : function() {
													return entity;
												}
											}
										});
								
								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							$scope.showInvVendor = function(entity) {
								$scope.message = "Show A Vendor";
								

								var modalInstance = $modal
										.open({
											templateUrl : 'views/vendor/showvendor.html',
											controller : modalShowVendorController,
											size : 'lg',
											scope : $scope,
											resolve : {
												showVendorForm : function() {
													return entity;
												}
											}
										});
								
								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
							//Bank Account Details
							$scope.addBankAccount = function(entity) {
								$scope.showBankAccountForm(entity);
							};
							
							$scope.showBankAccountForm = function(entity) {
								$scope.message = "Bank Account";
								

								var modalInstance = $modal
										.open({
											templateUrl : 'views/vendor/add_vendor_bank_details.html',
											controller : VendorBankController,
											scope : $scope,
											size:'lg',
											resolve : {
												addBankDetails : function() {
													return entity;
												}
											}
										});
								
								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
						
							
							$scope.vendorGridOption = {
								exporterMenuCsv : false,
								enableGridMenu : true,
								enableFiltering: true,
								enableCellEdit: true,
								showGridFooter: true,
								showColumnFooter: true,
								gridMenuTitleFilter : fakeI18n,
								paginationPageSizes : [ 25, 50, 100 ],
								paginationPageSize : 25,	
								useExternalPagination : false,
								columnDefs : [ {
									displayName: "Vendor ID",
									name : 'venId',
									width: "110",
									enableHiding : false
								}, {
									name : 'email',
									width: "200",
								}, {
									name : 'name',
									width: "300",
								},{
									displayName:"Address",
									field : 'venAddress[0].address',
									width: "250",
									
								},{
									displayName:"Phone No",
									name : 'venPhones[0].mobilePhonNo',
									width: "200",
									
								}, {
								    displayName: "Operations",
								    field: "delete",
								    enableSorting: false,
								    width: "240",
								    enableFiltering: false,
								    enableHiding : false,
								    cellTemplate: '<div class="ui-grid-cell-contents"><button class="btn btn-xs btn-danger" ng-click="grid.appScope.deleteInvVendor(row)"><i class="fa fa-trash" title="Delete"></i></button>&nbsp;&nbsp;<button ng-click="grid.appScope.updateInvVendor(row)" class="btn btn-xs btn-primary" title="Edit"><i class="fa fa-pencil"></i></button>&nbsp;&nbsp;<button ng-click="grid.appScope.showInvVendor(row)" class="btn btn-xs btn-info" title="Show Details"><i class="fa fa-eye" aria-hidden="true"></i></button>&nbsp;&nbsp;<button ng-click="grid.appScope.sendVendor(row)" class="btn btn-xs btn-warning" title="Send Notification"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>'+
								    '&nbsp;&nbsp;'+
								    '<button ng-click="grid.appScope.addBankAccount(row)" class="btn btn-xs btn-primary" title="Add BankAccount"><i class="fa fa-university" aria-hidden="true"></i></button>'
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
								}
							};
							var config = {
							    params: {
							        pageNo: $scope.paginationOptions.pageNo,
							        pageSize: $scope.paginationOptions.pageSize
							    }
							}
							$scope.getPage = function() {
								var url = 'service/vendor/getAllVendorData';
						
								
								$http
										.get(url, config)
										.success(
												function(response) {
													$scope.vendorGridOption.totalItems = 100;
													$scope.vendorGridOption.data = response.data;
												});
									
							};

							$scope.showAddVendorForm = function() {
								$scope.message = "Add New Vendor";

								var modalInstance = $modal
										.open({
											templateUrl : 'views/vendor/modalAddVendor.html',
											controller : modalAddVendorController,
											scope : $scope,
											size : 'lg',
											resolve : {
												addInvVendorForm : function() {
													return $scope.addInvVendorForm;
												}
											}
										});

								modalInstance.result.then(
										function(selectedItem) {
											$scope.selected = selectedItem;
										});
							};
							
						   	$scope.deleteInventoryVendor = function() {
								$scope.message = "Delete Vendor";
								
							};
							
							$scope.selectButtonClick1 = function(row) {
								var InvVendorVO={
										email: row.entity.email,
										name: row.entity.name
								}
								$scope.selectedData.push(InvVendorVO);
								
							};

							$scope.getPage();
						} ]);

var modalAddVendorController = function($scope, $modalInstance, $http, addInvVendorForm) {
	
	$scope.copyAddress = function()
	{
		console.log("flag "+$scope.flag);
		if($scope.flag)
		{
		$scope.invVendor.address2 = $scope.invVendor.address1;
		$scope.invVendor.city2 = $scope.invVendor.city1;
		$scope.invVendor.country2 = $scope.invVendor.country1;
		$scope.invVendor.pincode2 = $scope.invVendor.pincode1;
		$scope.invVendor.state2 = $scope.invVendor.state1;
		$scope.invVendor.mobilePhonNos = $scope.invVendor.mobilePhonNob;
		$scope.invVendor.office1phonNos = $scope.invVendor.office1phonNob;
		$scope.invVendor.office2phonNos = $scope.invVendor.office2phonNob;
		}
		else
		{
			$scope.invVendor.address2 = "";
			$scope.invVendor.city2 = "";
			$scope.invVendor.country2 = "";
			$scope.invVendor.pincode2 = "";
			$scope.invVendor.state2 = "";
			$scope.invVendor.mobilePhonNos =  "";
			$scope.invVendor.office1phonNos = "";
			$scope.invVendor.office2phonNos = "";
			
		}
	
	}
	
	$scope.submitInvVendorForm = function() {
		if ($scope.form.addInvVendorForm.$valid) {
			var url = 'service/vendor/save';
			
			var addVendorOpt = {
					email: $scope.invVendor.email,
					name : $scope.invVendor.name,
					// firstName : $scope.invVendor.firstName,
					// lastName : $scope.invVendor.lastName,
					// vendorKey : $scope.invVendor.vendorKey,
					venAddress: [ {
						address :$scope.invVendor.address1,
						city: $scope.invVendor.city1,
						state: $scope.invVendor.state1,
						country: $scope.invVendor.country1,
						pincode: $scope.invVendor.pincode1,
						addressType: "SHIPPING"
						},
						{
							address :$scope.invVendor.address2,
							city: $scope.invVendor.city2,
							state: $scope.invVendor.state2,
							country: $scope.invVendor.country2,
							pincode: $scope.invVendor.pincode2,
							addressType: "BILLING"
						}
					],
					venPhones: [{
						addressType: "BILLING",
						mobilePhonNo: $scope.invVendor.mobilePhonNob,
						office1phonNo: $scope.invVendor.office1phonNob,
						office2phonNo: $scope.invVendor.office2phonNob
						},
						{
							addressType: "SHIPPING",
							mobilePhonNo: $scope.invVendor.mobilePhonNos,
							office1phonNo: $scope.invVendor.office1phonNos,
							office2phonNo: $scope.invVendor.office2phonNos
						}
					]
					/*vendorBankDetails:[{
						accountHolderName : $scope.acHolderName,
						bankName		  : $scope.bankName,
						branch			  : $scope.branch,
						accountNumber	  : $scope.accountNo,
						ifscCode		  : $scope.ifscCode
					}]*/
			
			};
			
			$http
					.post(url, addVendorOpt)
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
								alert("successfully Registered");
							})
					.error(function(response) {
						console.log(response);
						alert("Vendor Already Exist");
					});
		} else {
			console.log('Vender form is not in scope');
			alert("Vendor Already Exist");
		}
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
	}
	
	var modalUpdateVendorController = function($scope, $modalInstance, $http, updateInvVendorForm) {
		console.log("Addesss1"+updateInvVendorForm.entity.venAddress[0].address);
		var mobilePhonNob = "";
		var office1phonNob = "";
		var office2phonNob = "";
		var mobilePhonNos = "";
		var office1phonNos = "";
		var office2phonNos = "";
		if("BILLING"==updateInvVendorForm.entity.venPhones[0].addressType){
		mobilePhonNob=updateInvVendorForm.entity.venPhones[0].mobilePhonNo;
		office1phonNob=updateInvVendorForm.entity.venPhones[0].office1phonNo;
		office2phonNob=updateInvVendorForm.entity.venPhones[0].office2phonNo;
		mobilePhonNos=updateInvVendorForm.entity.venPhones[1].mobilePhonNo;
		office1phonNos=updateInvVendorForm.entity.venPhones[1].office1phonNo;
		office2phonNos=updateInvVendorForm.entity.venPhones[1].office2phonNo;
		}
		else{
			mobilePhonNob=updateInvVendorForm.entity.venPhones[1].mobilePhonNo;
			office1phonNob=updateInvVendorForm.entity.venPhones[1].office1phonNo;
			office2phonNob=updateInvVendorForm.entity.venPhones[1].office2phonNo;
			mobilePhonNos=updateInvVendorForm.entity.venPhones[0].mobilePhonNo;
			office1phonNos=updateInvVendorForm.entity.venPhones[0].office1phonNo;
			office2phonNos=updateInvVendorForm.entity.venPhones[0].office2phonNo;
		}

		$scope.invVendor = {
				venId : updateInvVendorForm.entity.venId,
				email : updateInvVendorForm.entity.email,
				vendorId : updateInvVendorForm.entity.vendorId,
				name : updateInvVendorForm.entity.name,

				// firstName : updateInvVendorForm.entity.firstName,
				// lastName : updateInvVendorForm.entity.lastName,
				// vendorKey : updateInvVendorForm.entity.vendorKey,
				
					address1 :updateInvVendorForm.entity.venAddress[0].address,
					city1: updateInvVendorForm.entity.venAddress[0].city,
					state1: updateInvVendorForm.entity.venAddress[0].state,
					country1: updateInvVendorForm.entity.venAddress[0].country,
					pincode1: parseInt(updateInvVendorForm.entity.venAddress[0].pincode),
					addressType: updateInvVendorForm.entity.venAddress[0].addressType,
					
					address2 :updateInvVendorForm.entity.venAddress[1].address,
					city2: updateInvVendorForm.entity.venAddress[1].city,
					state2: updateInvVendorForm.entity.venAddress[1].state,
					country2: updateInvVendorForm.entity.venAddress[1].country,
					pincode2: parseInt(updateInvVendorForm.entity.venAddress[1].pincode),
					addressType: updateInvVendorForm.entity.venAddress[1].addressType,
				
					mobilePhonNob : mobilePhonNob,
					office1phonNob : office1phonNob,
					office2phonNob : office2phonNob,
					mobilePhonNos : mobilePhonNos,
					office1phonNos : office1phonNos,
					office2phonNos : office2phonNos
		}

		
		$scope.submitUpdatedVendorForm = function() {
			if ($scope.form.updateInvVendorForm.$valid) {
				var url = 'service/vendor/update';
				
				
				var updateVendorOpt = {
						vendorId : updateInvVendorForm.entity.vendorId,
						email: $scope.invVendor.email,
						name : $scope.invVendor.name,
						
						venAddress: [ {
							address :$scope.invVendor.address1,
							city: $scope.invVendor.city1,
							state: $scope.invVendor.state1,
							country: $scope.invVendor.country1,
							pincode: $scope.invVendor.pincode1,
							addressType: "SHIPPING"
							},
							{
								address :$scope.invVendor.address2,
								city: $scope.invVendor.city2,
								state: $scope.invVendor.state2,
								country: $scope.invVendor.country2,
								pincode: $scope.invVendor.pincode2,
								addressType: "BILLING"
							}
						],
						venPhones: [{
							addressType: "BILLING",
							mobilePhonNo: $scope.invVendor.mobilePhonNob,
							office1phonNo: $scope.invVendor.office1phonNob,
							office2phonNo: $scope.invVendor.office2phonNob
							},
							{
								addressType: "SHIPPING",
								mobilePhonNo: $scope.invVendor.mobilePhonNos,
								office1phonNo: $scope.invVendor.office1phonNos,
								office2phonNo: $scope.invVendor.office2phonNos
							}
						]
				};
				
				console.log(updateVendorOpt);
				$http
						.post(url, updateVendorOpt)
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
							alert("Technical Error, Please try after Some time");
						});
			} else {
				console.log('Vender form not updated');
			}
			
		};
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
		
}
	
	var modalShowVendorController = function($scope, $modalInstance, $http, showVendorForm) {
		console.log(showVendorForm.entity.email);
		console.log(showVendorForm.entity.mobilePhonNob);
		
		$scope.invVendor = {
				venId : showVendorForm.entity.venId,
				email : showVendorForm.entity.email,
				vendorId : showVendorForm.entity.vendorId,
				name : showVendorForm.entity.name,
				address1 :showVendorForm.entity.venAddress[0].address,
				city1: showVendorForm.entity.venAddress[0].city,
				state1: showVendorForm.entity.venAddress[0].state,
				country1: showVendorForm.entity.venAddress[0].country,
				pincode1: parseInt(showVendorForm.entity.venAddress[0].pincode),
				addressType: showVendorForm.entity.venAddress[0].addressType,
				
				address2 :showVendorForm.entity.venAddress[1].address,
				city2: showVendorForm.entity.venAddress[1].city,
				state2: showVendorForm.entity.venAddress[1].state,
				country2: showVendorForm.entity.venAddress[1].country,
				pincode2: parseInt(showVendorForm.entity.venAddress[1].pincode),
				addressType2: showVendorForm.entity.venAddress[1].addressType,
				
				mobilePhonNob : showVendorForm.entity.venPhones[0].mobilePhonNo,
				office1phonNob : showVendorForm.entity.venPhones[0].office1phonNo,
				office2phonNob : showVendorForm.entity.venPhones[0].office2phonNo,
				mobilePhonNos : showVendorForm.entity.venPhones[1].mobilePhonNo,
				office1phonNos : showVendorForm.entity.venPhones[1].office1phonNo,
				office2phonNos : showVendorForm.entity.venPhones[1].office2phonNo,
				
				bankDetails : showVendorForm.entity.vendorBankDetails
		}

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
		
}
	
/*	var VendorBankController = function($scope, $modalInstance, $http, addBankDetails) {
		
		 		$scope.bankDetailsArray =
		        [
		            
		        ];
		 		
		 		
		 		$scope.IsVisible = false;
		        $scope.addRow = function () {
		        	
		            if ($scope.accountHolderName != undefined && $scope.bankName != undefined&& $scope.branch != undefined&& $scope.accountNumber != undefined&& $scope.ifscCode != undefined) {
		            	$scope.IsVisible = true;
		            	var bank = {};
		                bank.accountHolderName = $scope.accountHolderName;
		                bank.bankName 		 = $scope.bankName;
		                bank.branch 			 = $scope.branch;
		                bank.accountNumber 	 = $scope.accountNumber;
		                bank.ifscCode 	 	 = $scope.ifscCode;

		                $scope.bankDetailsArray.push(bank);

		                $scope.accountHolderName = null;
		                $scope.bankName = null;
		                $scope.branch = null;
		                $scope.accountNumber = null;
		                $scope.ifscCode = null;
		            }
		        };
		        
		        
		        
		        $scope.removeRow = function () {
		            var arrBank = [];
		            angular.forEach($scope.bankDetailsArray, function (value) {
		                if (!value.Remove) {
		                	arrBank.push(value);
		                }
		            });
		            $scope.bankDetailsArray = arrBank;
		        };
		        
		        
		        $scope.submit = function () {
		            var arrBank = [];
		            angular.forEach($scope.bankDetailsArray, function (value) {
		            	arrBank.push('accountHolderName:'+value.accountHolderName+',bankName: '+value.bankName+',branch:'+value.branch+',accountNumber: '+value.accountNumber+',ifscCode:'+value.ifscCode);
		            });
		            $scope.display = arrBank;
		        };
			      
			      
			   

		
		$scope.submitVendorBankForm = function() {
					var url = 'service/vendor/addBankAccountDetails';
					
					var VendorBankDetailsVO = [];
					
					$scope.VendorBankDetailsVO = $scope.bankDetailsArray
					
						$http
								.post(url,VendorBankDetailsVO)
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
											alert("successfully Added Bank Details");
										})
								.error(function(response) {
									console.log(response);
									alert("Technical Error, Please try after Some time");
								});
					
				};
			
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
	//};
	}*/
	
	
var VendorBankController = function($scope, $modalInstance, $http, addBankDetails) {
	
	var listBank=[];
	
	$scope.listBank = addBankDetails.entity.vendorBankDetails;
	
		
		console.log("hellooo this is the controller");
		console.log(addBankDetails.entity.vendorId)
		
		$scope.arr = [{"vendorId":addBankDetails.entity.vendorId}]
		
		$scope.addRow = function(){
			var obj = {}
			obj.vendorId = addBankDetails.entity.vendorId
			$scope.arr.push(obj);
		}
		
		$scope.deleteRow = function(index){
			$scope.arr.pop(index);
		}
		
		$scope.submitVendorBankForm = function() {
					var url = 'service/vendor/addBankAccountDetails';
					
					var VendorBankDetailsVO = $scope.arr;
					
						$http
								.post(url,VendorBankDetailsVO)
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
											alert("successfully Added Bank Details");
										})
								.error(function(response) {
									console.log(response);
									alert("Technical Error, Please try after Some time");
								});
					
				};
			
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		};
	}




var bankUpdateVendorController = function($scope, $modalInstance, $http, editBankDetails) {
	

	$scope.invVendor = {
			vendorId 			:editBankDetails.vendorId,
			vendorAccountId		: editBankDetails.vendorAccountId,
			accountHolderName	:editBankDetails.accountHolderName,
			bankName			:editBankDetails.bankName,
			branch				:editBankDetails.branch,
			accountNumber		:editBankDetails.accountNumber,
			ifscCode			:editBankDetails.ifscCode
	}

	
	$scope.submitUpdateVendorBankForm = function() {
		if ($scope.form.editBankDetails.$valid) {
			var url = 'service/vendor/updateBankAccountDetails';
			
			
			var updateVendorOpt = {
					vendorId 			: editBankDetails.vendorId,
					vendorAccountId		:$scope.invVendor.vendorAccountId,
					accountHolderName	:$scope.invVendor.accountHolderName,
					bankName			:$scope.invVendor.bankName,
					branch				:$scope.invVendor.branch,
					accountNumber		:$scope.invVendor.accountNumber,
					ifscCode			:$scope.invVendor.ifscCode
			};
			
			console.log(updateVendorOpt);
			$http
					.post(url, updateVendorOpt)
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
						alert("Technical Error, Please try after Some time");
					});
		} else {
			console.log('Vender form not updated');
		}
		
	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
}