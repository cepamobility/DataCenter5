

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers','starter.services','ionic.utils' ])//
.run(function($ionicPlatform,$localstorage,$ionicLoading) {
  $ionicLoading.show({
      template: '<ion-spinner></ion-spinner>'
    });
  $ionicPlatform.ready(function() {
	
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  
    try{

    	var request = indexedDB.open("CEPADB18",1);
        request.onupgradeneeded = function() {
       
          try{
          	var db = request.result;
            var store = db.createObjectStore("drivers", {keyPath: "id"});
            store.createIndex("by_id", "id", {unique: true});
            store.createIndex("by_firstname", "firstName");
            store.createIndex("by_lastname", "lastName");
            store.createIndex("by_corporationId", "corporationId");
            var store = db.createObjectStore("corporations", {keyPath: "id"});
            store.createIndex("by_id", "id", {unique: true});
            store.createIndex("by_name", "name");
            var store = db.createObjectStore("activity", {keyPath: "id"});
            store.createIndex("by_id", "id",{unique: true});
            store.createIndex("by_checklist", "checklist");
            store.createIndex("by_corporationId", "corporationId");
            store.createIndex("by_version", "version");
            var store = db.createObjectStore("activityopen", {keyPath: "id"});
            store.createIndex("by_id", "id",{unique: true});
            store.createIndex("by_checklist", "checklist");
            store.createIndex("by_corporationId", "corporationId");
            store.createIndex("by_version", "version");
            var store = db.createObjectStore("activitysync", {keyPath: "id"});
            store.createIndex("by_id", "id",{unique: true});
            store.createIndex("by_checklist", "checklist");
            store.createIndex("by_corporationId", "corporationId");
            store.createIndex("by_version", "version");
            var store = db.createObjectStore("activityhistory", {keyPath: "id"});
            store.createIndex("by_id", "id",{unique: true});
            store.createIndex("by_checklist", "checklist");
            store.createIndex("by_corporationId", "corporationId");
            store.createIndex("by_version", "version");
            var store = db.createObjectStore("checklist", {keyPath: "id"});
            store.createIndex("by_id", "id",{unique: true});
            
            var store = db.createObjectStore("activitycheck", {keyPath: "id"});
            store.createIndex("by_id", "id",{unique: true});
            store.createIndex("by_activityId", "activityId");
            store.createIndex("by_corporationId", "corporationId");
            store.createIndex("by_checklist", "checklist");
            store.createIndex("by_driverId", "driverId");
            
          }catch(e){
        	  alert('Error creating db '+e.toString());
          }
        };

        request.onsuccess = function() {
          db = request.result;
          var user=$localstorage.getObject('user');
		  if(user!=null && user.lng!=null){
			  lng=user.lng;	
		  }
		  $ionicLoading.hide();
        };
        
        
    }catch(e){
      alert('Error on access local database '+e.toString());
    }
  

   });
})
.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

  .state('app', {
    url: "/app",
    cache: false,
    abstract: true,
    templateUrl: "templates/menu.html",
    controller: 'AppCtrl'
  })

  .state('app.search', {
    url: "/search",
    cache: false,
    views: {
      'menuContent': {
        templateUrl: "templates/search.html"
      }
    }
  })

  .state('app.browse', {
    url: "/browse",
    cache: false,
    views: {
      'menuContent': {
        templateUrl: "templates/browse.html",
        controller: 'CorpCtrl'
      }
    }
  })
    .state('app.main', {
      url: "/main",
      cache: false,
      views: {
        'menuContent': {
          templateUrl: "templates/playlists.html",
          controller: 'PlaylistsCtrl'
        }
      }
    })

  .state('app.single', {
    url: "/playlists/:playlistId",
    cache: false,
    views: {
      'menuContent': {
        templateUrl: "templates/playlist.html",
        
        controller: 'PlaylistCtrl'
      }
    }
  })
  .state('app.sync', {
	    url: "/sync",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/sync.html",
	        controller: 'SyncCtrl'
	      }
	    }
  }) .state('app.syncUp', {
	    url: "/syncUp",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/sync.html",
	        controller: 'SyncUpCtrl'
	      }
	    }
  })
  .state('app.corp', {
	    url: "/corp",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/corp.html",
	        controller: 'CorpCtrl'
	      }
	    }	    
  })
   .state('app.submenu', {
	    url: "/submenu/:corpId",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/submenu.html",
	        controller: 'SubMenuCtrl'
	      }
	    }	    
  })
  .state('app.ac', {
	    url: "/ac/:id",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/activity.html",
	        controller: 'ACCtrl'
	      }
	    }
  }).state('app.acopen', {
	    url: "/acopen/:id",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/openactivity.html",
	        controller: 'ACOpenCtrl'
	      }
	    }	    
  })
  	.state('app.acsync', {
	    url: "/acsync/:id",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/syncactivity.html",
	        controller: 'ACSyncCtrl'
	      }
	    }	    
  }).state('app.achistory', {
	    url: "/achistory/:id",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/historyactivity.html",
	        controller: 'ACSyncCtrl'
	      }
	    }	    
  })
  .state('app.activities', {
	    url: "/activities/:corpId",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/activities.html",
	        controller: 'ActivitiesCtrl'
	      }
	    }
  
  }).state('app.activitiesopen', {
	    url: "/activitiesopen/:corpId",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/openactivities.html",
	        controller: 'ActivitiesOpenCtrl'
	      }
	    }
  
  }).state('app.activitiessync', {
	    url: "/activitiessync/:corpId",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/syncactivities.html",
	        controller: 'ActivitiesSyncCtrl'
	      }
	    }	    
  }).state('app.activitieshistory', {
	    url: "/activitieshistory/:corpId",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/historyactivities.html",
	        controller: 'ActivitiesHistoryCtrl'
	      }
	    }	    
  }).state('app.checklist', {
	    url: "/checklist/:checklistId/:activityId/:driverId",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/checklist.html",
	        controller: 'ChecklistCtrl'
	      }
	    }	    
  }).state('app.addDriver', {
	    url: "/activity/adddriver/:corpId/:acid",
	    cache: false,
	    views: {
	      'menuContent': {
	        templateUrl: "templates/searchDriver.html",
	        controller: 'SearchDriverCtrl'
	      }
	    }	    
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/main');
});
