'use strict';
/**
 * @ngdoc overview
 * @name inventoryApp
 * @description
 * # inventoryApp
 *
 * Main module of the application.
 */
angular
  .module('inventoryApp', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
    'btorfs.multiselect'
  ])
  .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', '$httpProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider, $httpProvider) {
	  
	  //$httpProvider.defaults.headers.common['Accept'] = 'application/json, text/javascript';
	  //$httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
	  $httpProvider.defaults.headers.common['X-CSRF-TOKEN'] = $('meta[name="csrf-token"]').attr('content');
	    
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/dashboard/home');

    $stateProvider
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'inventoryApp',
                    files:[
                    'scripts/directives/header/header.js',
                    'scripts/directives/header/header-notification/header-notification.js',
                    'scripts/directives/sidebar/sidebar.js',
                    'scripts/directives/sidebar/sidebar-search/sidebar-search.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "bower_components/angular-toggle-switch/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['bower_components/angular-cookies/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['bower_components/angular-resource/angular-resource.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['bower_components/angular-sanitize/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['bower_components/angular-touch/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'MainCtrl',
        templateUrl:'views/dashboard/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'inventoryApp',
              files:[
              'scripts/controllers/main.js',
              'scripts/directives/timeline/timeline.js',
              'scripts/directives/notifications/notifications.js',
              'scripts/directives/chat/chat.js',
              'scripts/directives/dashboard/stats/stats.js'
              ]
            })
          }
        }
      })
      .state('dashboard.form',{
        templateUrl:'views/form.html',
        url:'/form'
    })
      .state('dashboard.blank',{
        templateUrl:'views/pages/blank.html',
        url:'/blank'
    })
      .state('login',{
        templateUrl:'views/pages/login.html',
        url:'/login'
    })
      .state('dashboard.chart',{
        templateUrl:'views/chart.html',
        url:'/chart',
        controller:'ChartCtrl',
        resolve: {
          loadMyFile:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'chart.js',
              files:[
                'bower_components/angular-chart.js/dist/angular-chart.min.js',
                'bower_components/angular-chart.js/dist/angular-chart.css'
              ]
            }),
            $ocLazyLoad.load({
                name:'inventoryApp',
                files:['scripts/controllers/chartContoller.js']
            })
          }
        }
    })
      .state('dashboard.vendors',{
        templateUrl:'views/vendors.html',
        controller: 'VendorCtrl',
        url:'/vendors',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	
	                	'scripts/controllers/vendorController.js'
	                	]
	            })
        	}
        }

    })
    .state('dashboard.users',{
        templateUrl:'views/users.html',
        controller: 'UserCtrl',
        url:'/users',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/datatables/jquery.dataTables.js',
	                	'scripts/controllers/userController.js'
	                	]
	            })
        	}
        }
    })
    .state('dashboard.users.details',{
        templateUrl:'views/user/modalEditUser.html',
        //controller: 'UserDetailCtrl',
        url: '/details'/*,
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/controllers/userDetailController.js'
	                	]
	            })
        	}
        }*/
    })
    
    
    .state('dashboard.purchaseorder',{
        templateUrl:'views/purchase.html',
        controller: 'PurchaseCtrl',
        url:'/purchase',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/controllers/purchaseController.js'
	                	]
	            })
        	}
        }
    })
    
    
    .state('dashboard.categories',{
          templateUrl:'views/categories.html',
          controller: 'CategoriesCtrl',
          url:'/categories',
          resolve: {
          	loadMyFile:function($ocLazyLoad) {
          		return $ocLazyLoad.load({
  	                name:'inventoryApp',
  	                files:[
  	                	'scripts/controllers/categoriesController.js'
  	                	]
  	            })
          	}
          }
      })
    
      .state('dashboard.buttons',{
        templateUrl:'views/ui-elements/buttons.html',
        url:'/buttons'
    })
      /*.state('dashboard.notifications',{
        templateUrl:'views/ui-elements/notifications.html',
        url:'/notifications'
    })*/
   
   .state('dashboard.products',{
       templateUrl:'views/products.html',
       controller: 'ProductCtrl',
       url:'/products',
       resolve: {
       	loadMyFile:function($ocLazyLoad) {
       		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	/*'scripts/datatables/jquery.dataTables.js',*/
	                	'scripts/controllers/productController.js'
	                	]
	            })
       	}
       }
   })
   
    .state('dashboard.showVariants',{
        templateUrl:'views/showVariants.html',
        controller: 'VariantCtrl',
        url:'/showVariants',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/controllers/variantController.js'
	                	]
	            })
        	}
        }
    })
    
    .state('dashboard.offers',{
        templateUrl:'views/offers.html',
        controller: 'OffersCtrl',
        url:'/offers',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/controllers/offersController.js'
	                	]
	            })
        	}
        }
    })
    
    
    .state('dashboard.payment',{
        templateUrl:'views/payment.html',
        controller: 'PaymentCtrl',
        url:'/payment',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/controllers/paymentController.js'
	                	]
	            })
        	}
        }
    })
    
     .state('dashboard.setting',{
        templateUrl:'views/setting.html',
        controller: 'SettingCtrl',
        url:'/setting',
        resolve: {
        	loadMyFile:function($ocLazyLoad) {
        		return $ocLazyLoad.load({
	                name:'inventoryApp',
	                files:[
	                	'scripts/controllers/settingController.js',
	                	'scripts/directives/setting/setting.js',
	                	]
	            })
        	}
        }
    })
    
  
      .state('dashboard.icons',{
       templateUrl:'views/ui-elements/icons.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'views/ui-elements/grid.html',
       url:'/grid'
   })
  }]);

    
