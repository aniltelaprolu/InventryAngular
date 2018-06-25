'use strict';
/**
 * @ngdoc function
 * @name inventoryApp.controller:SettingCtrl
 * @description # SettingCtrl Controller of the inventoryApp
 */
angular
		.module(
				'inventoryApp',
				[ 'ui.grid', 'ui.grid.pagination', 'ngTouch',
						'ui.grid.exporter', 'ui.grid.selection' ])
		.controller(
				'SettingCtrl',
				[
						'$scope',
						'$http',
						'$modal',
						'$interval',
						'$q',
						function($scope, $http, $modal, $interval, $q,
								fileReader) {

							$scope.item = 1;
							$scope.showdiv = function(num) {
								console.log(num)
								for (var i = 1; i <= 11; i++) {
									if (i == num) {
										$scope.item = num;
									}
								}

							};

							// Image
							$scope.images = [];
							$scope.count = 0;
							$scope.getFile = function() {
								$scope.progress = 0;
								fileReader.readAsDataUrl($scope.file, $scope)
										.then(function(result) {

											$scope.images.push(result);
											$scope.count = $scope.count + 1;
										});
							};
							$scope.deleteImages = function($index) {
								$scope.images.splice($index);
								$scope.count = $scope.count - 1;
							};

							
							
							var url = 'service/companyprofile/getCompanyDetails';

							$http.get(url).success(function(response) {
								console.log(response);
								$scope.companyDetails = response.data[0];
							}).error(function(response) {
								console.log(response);
							});
							
								var url = 'service/companyprofile/getAllCompanyBankDetails';
	
								$http.get(url).success(function(response) {
									console.log(response);
									$scope.companyBankDetails = response.data;
								}).error(function(response) {
									console.log(response);
								});

							
							$scope.submitCompanyForm = function() {
								var companyVO = {
									companyId : $scope.companyDetails.companyId,
									companyName : $scope.companyDetails.companyName,
									gstNumber : $scope.companyDetails.gstNumber,
									email : $scope.companyDetails.email,
									website : $scope.companyDetails.website,
									phone : $scope.companyDetails.phone,
									description : $scope.companyDetails.description,
									
									companyBank:[{
										accountHolderName	:$scope.companyDetails.accountHolderName,
										bankName			:$scope.companyDetails.bankName,
										branch				:$scope.companyDetails.branch,
										accountNumber		:$scope.companyDetails.accountNumber,
										ifscCode			:$scope.companyDetails.ifscCode
									}],
									
									companyAddress: [ {
										address :$scope.companyDetails.invVendor.address1,
										city: $scope.companyDetails.invVendor.city1,
										state: $scope.companyDetails.invVendor.state1,
										country: $scope.companyDetails.invVendor.country1,
										pincode: $scope.companyDetails.invVendor.pincode1,
										addressType: "SHIPPING"
										},
										{
											address :$scope.companyDetails.invVendor.address2,
											city: $scope.companyDetails.invVendor.city2,
											state: $scope.companyDetails.invVendor.state2,
											country: $scope.companyDetails.invVendor.country2,
											pincode: $scope.companyDetails.invVendor.pincode2,
											addressType: "BILLING"
										}
									]
								};
								console.log(companyVO);
								
								$http(
										{
											url : 'service/companyprofile/updateCompanyProfileDetails',
											method : "POST",
											data : companyVO
										}).success(function(response) {
									alert("successfully Done");
								}).error(function(response) {
									alert("Technical Error");
								});
								console.log("HI Setting");
							};
							
							
							
							/**
							 * 
							 */
							$scope.submitCompanyProfileForm = function() {
								var companyVO = {
									companyId : $scope.companyDetails.companyId,
									companyName : $scope.companyDetails.companyName,
									gstNumber : $scope.companyDetails.gstNumber,
									email : $scope.companyDetails.email,
									website : $scope.companyDetails.website,
									phone : $scope.companyDetails.phone,
									description : $scope.companyDetails.description
									
								};
								console.log(companyVO);
								
								$http(
										{
											url : 'service/companyprofile/updateCompanyProfileDetails',
											method : "POST",
											data : companyVO
										}).success(function(response) {
									alert("successfully Done");
								}).error(function(response) {
									alert("Technical Error");
								});
								console.log("HI Setting");
							};
							
							
							
							
							/**
							 * 
							 */
							$scope.submitCompanyAddressForm = function() {
								var companyVO = {
									companyId : $scope.companyDetails.companyId,
									
									companyAddress: [ {
										address :$scope.companyDetails.invVendor.address1,
										city: $scope.companyDetails.invVendor.city1,
										state: $scope.companyDetails.invVendor.state1,
										country: $scope.companyDetails.invVendor.country1,
										pincode: $scope.companyDetails.invVendor.pincode1,
										addressType: "SHIPPING"
										},
										{
											address :$scope.companyDetails.invVendor.address2,
											city: $scope.companyDetails.invVendor.city2,
											state: $scope.companyDetails.invVendor.state2,
											country: $scope.companyDetails.invVendor.country2,
											pincode: $scope.companyDetails.invVendor.pincode2,
											addressType: "BILLING"
										}
									]
								};
								console.log(companyVO);
								
								$http(
										{
											url : 'service/companyprofile/updateCompanyAddressDetails',
											method : "POST",
											data : companyVO
										}).success(function(response) {
									alert("successfully Done");
								}).error(function(response) {
									alert("Technical Error");
								});
								console.log("HI Setting");
							};

						} ]);
