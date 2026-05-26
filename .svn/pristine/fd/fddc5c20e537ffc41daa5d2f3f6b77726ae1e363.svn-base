 

angular.module('starter.controllers', [])//'starter.services'

.controller('AppCtrl', function($scope,$localstorage, $http,$rootScope, $ionicModal, $timeout) {//Security
	 $scope.txt=txt[lng];
    //Funciones auxiliares
   $scope.loginIn= function(user,pwd, callback){
	   $.ajax({
			  type: "GET",
			  url: Config.url.login+'?user='+user+'&password='+pwd,
			  async:true,
			  contentType: "application/json",
			  dataType:'json',
			  success: function(data){
				  callback(data.lng);
			  },
			  error:function(data){
				  alert('Server not respond '+data);
			  }
	   });
	   
	  
   };

   
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});
  
  // Form data for the login modal
  $scope.loginData =$localstorage.getObject('user') || {};

  if($scope.loginData.username==null || $scope.loginData.username==undefined){
    // Create the login modal that we will use later
    $ionicModal.fromTemplateUrl('templates/login.html', {
      scope: $scope
    }).then(function(modal) {
      $scope.modal = modal;
      $scope.modal.show();
    });
  }else{
	  $ionicModal.fromTemplateUrl('templates/login.html', {
	      scope: $scope
	    }).then(function(modal) {
	      $scope.modal = modal;
	    });
  }
  
  // Triggered in the login modal to close it
 /* $scope.closeLogin = function() {
    $scope.modal.hide();
  };*/

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
       $scope.loginIn($scope.loginData.username,$scope.loginData.password,
              
              function(data){
                $("#msg").html('Login request.....');
                if(data!=null){
                  userlogin=true;
                  lng=data;
                  $scope.modal.hide();
                  $scope.loginData.lng=lng;
                  $localstorage.setObject('user',$scope.loginData);
                }else{
                  $("#msg").html('Invalid user or password.');
                  
                }

                $rootScope.$apply();
              }
          
          );
    
  };

  
  
})

.controller('PlaylistsCtrl', function($scope,$location,$rootScope) {
 try{
	$scope.txt=txt[lng]; 
    $scope.playlists = [
        { title: txt[lng].titCorp, id: 2,url:'/app/corp' },
        { title: txt[lng].titSync, id: 1,url:'/app/sync' },
        { title: txt[lng].titSyncUp, id: 3,url:'/app/syncUp' }
       
        
      ];
    
    $scope.open=function(url){
    	 $location.path(url);
         $rootScope.$apply();
    }
    
  }catch(e){
    alert('Generic Error PlaylistsCtrl:'+e.toString());
  }
  
})
.controller('SyncCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {
	try{
		$scope.txt=txt[lng]; 
	    $scope.f= txt[lng].tit2Sync;
       	$("#scroll").css("width",window.innerWidth);     
      	$("#scroll").css("height",window.innerHeight-30);
      	$scope.messages=[];
	    $scope.loading=true;
	    $scope.messages.push(txt[lng].init);
	    
	    setTimeout(
		    function(){
		    	try{
		    	
		    		DataAccess.clearCorps(
		    				function(){
					    		DataAccess.sync2(
							          function(){
							            try{
							              $scope.messages.push(txt[lng].finish);
							              alert(txt[lng].finish);
							              $scope.loading=false;
							              $location.path('/app/main');
							              $rootScope.$apply();
							
							            }catch(e){
							              
							            }
							          },
							          function(m){
							              try{
							               
							                $scope.messages.push(m);
							                $rootScope.$apply();
							                }catch(e){
							                  
							                }
							                 
							            }
					    			);
		    		});
		    	}catch(e){
		    	    alert('Generic error SyncCtrl setTimeout:'+e.toString());
		    	}
		    },1000);
	  }catch(e){
	    alert('Generic error SyncCtrl:'+e.toString());
	  }
	});

})
.controller('SyncUpCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {
	try{
		$scope.txt=txt[lng]; 
	    $scope.f= txt[lng].tit2Sync;
       	$("#scroll").css("width",window.innerWidth);     
      	$("#scroll").css("height",window.innerHeight-30);
      	$scope.messages=[];
	    $scope.loading=true;
	    $scope.messages.push(txt[lng].init);
	    
	    setTimeout(
		    function(){
		    	try{
		    		
		    		DataAccess.syncUp(
					          function(result){
					            try{
					            	if(result){
							              alert(txt[lng].finish);
					            	}else{
					            		alert('Error '+txt[lng].finish);
					            	}
					              $scope.loading=false;
					              $location.path('/app/main');
					              $rootScope.$apply();
					            }catch(e){
					              
					            }
					          });
				 
		    	}catch(e){
		    	    alert('Generic error SyncUpCtrl setTimeout:'+e.toString());
		    	}
		    },1000);
	  }catch(e){
	    alert('Generic error SyncCtrl:'+e.toString());
	  }
	});

})
.controller('CorpCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
$scope.$on('$ionicView.enter', function() {
 try{
	 $scope.txt=txt[lng]; 
     $scope.f=txt[lng].corp;
     $("#scroll").css("width",window.innerWidth);     
     $("#scroll").css("height",window.innerHeight-60);
     $scope.loading=true;
     DataAccess.findCorps2(
        function(corps){
          try{
            $scope.corps=corps;
            
            $scope.loading=false;
            $rootScope.$apply();
          }catch(e){
            alert('Error finding corporations '+e.toString());
          }
        }
     );
     $scope.open=function(id){
    	  $location.path('/app/submenu/'+id);
          $rootScope.$apply();
     }   
  }catch(e){
    alert('Generic Error CorpCtrl:'+e.toString());
  }
 });
})
.controller('SubMenuCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
 $scope.$on('$ionicView.enter', function() {	 
	try{
		 $scope.txt=txt[lng];
	     $scope.f=txt[lng].corpSubMenu;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     DataAccess.countNewActivities(parseInt($stateParams.corpId,10),
    		function(count){
	    	 	$scope.total=count;
	    	 	DataAccess.countOpenActivities(parseInt($stateParams.corpId,10),
	    	 			function(count){
	    	 				$scope.totalOpen=count;
	    	 				DataAccess.countSyncActivities(parseInt($stateParams.corpId,10),
	    	 						function(count){
	    	 							$scope.totalSync=count;
	    	 							DataAccess.countHistoryActivities(parseInt($stateParams.corpId,10),
	    		    	 						function(count){
	    		    	 							$scope.totalHistory=count;
	    		    	 							$rootScope.$apply();
	    		    	 						}
	    		    	 				);
	    	 						}
	    	 				);
    	 			});			
     		});	  
 	
	     $scope.openActivitiesNew=function(){ 
	    	  $location.path('/app/activities/'+parseInt($stateParams.corpId,10));
	          $rootScope.$apply();
	     }   
	     $scope.openOpen=function(id){ 
	    	  $location.path('/app/activitiesopen/'+parseInt($stateParams.corpId,10));
	          $rootScope.$apply();
	     }   
	     $scope.openSync=function(id){ 
	    	  $location.path('/app/activitiessync/'+parseInt($stateParams.corpId,10));
	          $rootScope.$apply();
	     }   
	     $scope.openHistory=function(id){ 
	    	  $location.path('/app/activitieshistory/'+parseInt($stateParams.corpId,10));
	          $rootScope.$apply();
	     }
	     
	  }catch(e){
	    alert('Generic Error SubMenuCtrl:'+e.toString());
	  }
 });	  
})
.controller('ActivitiesCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {
	try{
		 $scope.txt=txt[lng];
		 $scope.f=txt[lng].corpActivity;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findActivities($stateParams.corpId,
	        function(activities){
	          try{
	            $scope.activities=activities;
	            
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error finding activities '+e.toString());
	          }
	        }
	     );

	     $scope.open=function(id){ 
	    	  $location.path('/app/ac/'+id);
	          $rootScope.$apply();
	     }   
	     
	  }catch(e){
	    alert('Generic Error CorpCtrl:'+e.toString());
	  }
	  
	 });	  
})
.controller('ActivitiesOpenCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {	
	try{
		 $scope.txt=txt[lng];
	     $scope.f=txt[lng].activityStarted;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findOpenActivities($stateParams.corpId,
	        function(activities){
	          try{
	            $scope.activities=activities;
	            
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error finding activities '+e.toString());
	          }
	        }
	     );

	     $scope.open=function(id){ 
	    	  $location.path('/app/acopen/'+id);
	          $rootScope.$apply();
	     }   
	     
	  }catch(e){
	    alert('Generic Error CorpCtrl:'+e.toString());
	  }
	  
	 });	  
})
.controller('ActivitiesSyncCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {
	 try{
		 $scope.txt=txt[lng];
	     $scope.f=txt[lng].activitySync;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findSyncActivities($stateParams.corpId,
	        function(activities){
	          try{
	            $scope.activities=activities;
	            
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error finding activities '+e.toString());
	          }
	        }
	     );

	     $scope.open=function(id){ 
	    	  $location.path('/app/acsync/'+id);
	          $rootScope.$apply();
	     }   
	     
	  }catch(e){
	    alert('Generic Error CorpCtrl:'+e.toString());
	  }
	  
	 });	  
})
.controller('ActivitiesHistoryCtrl', function($scope,$rootScope,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {
	try{
			 $scope.txt=txt[lng];
		     $scope.f=txt[lng].activityHistory;
		     $("#scroll").css("width",window.innerWidth);     
		     $("#scroll").css("height",window.innerHeight-60);
		     $scope.loading=true;
		     DataAccess.findHistoryActivities($stateParams.corpId,
		        function(activities){
		          try{
		            $scope.activities=activities;
		            
		            $scope.loading=false;
		            $rootScope.$apply();
		          }catch(e){
		            alert('Error finding activities '+e.toString());
		          }
		        }
		     );

		     $scope.open=function(id){ 
		    	  $location.path('/app/achistory/'+id);
		          $rootScope.$apply();
		     }   
		     
		  }catch(e){
		    alert('Generic Error CorpCtrl:'+e.toString());
		  }
		  
	 });	  
})
.controller('ACCtrl', function($scope,$rootScope,$ionicPopup,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {
	try{
		 $scope.txt=txt[lng];
		 $scope.f=txt[lng].activity;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findActivity(parseInt($stateParams.id,10),
	        function(activity){
	          try{
	            $scope.ac=activity;
	           
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error findActivity '+e.toString());
	          }
	        }
	     );
	     
	     $scope.openActivityPopup = function(ac) {
	    	  
	    	  $scope.openActivity=function(ac){
	    		var tx = db.transaction("activityopen", "readwrite");
	  			var storeCorp = tx.objectStore("activityopen");
	  			var indexCorp = storeCorp.index("by_id");

	  			  if(ac.status=='NEW') {
		    		  DataAccess.openActivity(ac,
		    				function(){
			    			  $location.path('/app/acopen/'+ac.id);
					          $rootScope.$apply();  
		    		  		}		  
		    		  );
	    		  }else{
		    		  var myPopup = $ionicPopup.show({
		  	    		title: txt[lng].activityAlreadyStarted,
		  	    	    subTitle: ac.name,
		  	    	    scope: $scope,
		  	    	    buttons: [
		  	    	               {
		  	    	                   text: 'Close',
		  	    	                   type: 'button-dark',
		  	    	                   onTap: function(e) {
		  	    	                	   
		  	    	                	   return true;
		  	    	                   }
		  	    	               }
		  	    	           ]
		  	    	  });
		  	    	  myPopup.then(function(res) {
		  	    	    
		  	    	  });
	    		  }
	    	  }
	    	  
	    	  
	    	  // An elaborate, custom popup
	    	  var myPopup = $ionicPopup.show({
	    		title: txt[lng].activityStart,
	    	    subTitle: ac.name +"-"+"("+ac.participants.length+")",
	    	    scope: $scope,
	    	    buttons: [
	    	               {
	    	                   text: txt[lng].activityStart ,
	    	                   type: 'button-balanced',
	    	                   onTap: function(e) {
	    	                	   $scope.openActivity($scope.ac);
	    	                	   return true;
	    	                   }
	    	               },
	    	               {
	    	                   text: txt[lng].close,
	    	                   type: 'button-dark',
	    	                   onTap: function(e) {
	    	                	   
	    	                	   return true;
	    	                   }
	    	               }
	    	           ]
	    	  });
	    	  myPopup.then(function(res) {
	    	    
	    	  });
	     }

	  }catch(e){
	    alert('Generic Error ACCtrl:'+e.toString());
	  }
	  
	 });	  
})
.controller('ACOpenCtrl', function($scope,$rootScope,$ionicPopup,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {		
	 try{
		 $scope.txt=txt[lng];
		 $scope.f= txt[lng].activityStarted;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findOpenActivity(parseInt($stateParams.id,10),
	        function(activity){
	          try{
	            $scope.ac=activity;
	           
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error findActivity '+e.toString());
	          }
	        }
	     );
	     
	     $scope.openDriver = function(driver) {
	    	  $scope.driver=driver

	    	  $scope.calificarDriver=function(driver){
	    		  $location.path('/app/checklist/'+$scope.ac.checklist+"/"+$scope.ac.id+"/"+driver.id);
		          $rootScope.$apply();
		    	  
	    	  }
	    	  
	    	  $scope.removeDriver=function(driver){
	    		 removeByAttr($scope.ac.participants,'id',driver.id);
	    		  
	    		 var tx = db.transaction("activity", "readwrite");
				 var storeActivity = tx.objectStore("activity");
				 storeActivity.put($scope.ac);
				 $rootScope.$apply();
	    	  }
	    	  // An elaborate, custom popup
	    	  var myPopup = $ionicPopup.show({
	    		title: txt[lng].driverView,
	    	    subTitle: $scope.driver.firstName+" - "+$scope.driver.lastName,
	    	    scope: $scope,
	    	    buttons: [
	    	               {
	    	                   text:  txt[lng].remove,
	    	                   type: 'button-assertive',
	    	                   onTap: function(e) {
	    	                	   $scope.removeDriver($scope.driver);
	    	                	   return true;
	    	                   }
	    	               },
	    	               {
	    	                   text:  txt[lng].note,
	    	                   type: 'button-balanced',
	    	                   onTap: function(e) {
	    	                	   $scope.calificarDriver($scope.driver);
	    	                	   return true;
	    	                   }
	    	               },
	    	               {
	    	                   text:  txt[lng].close,
	    	                   type: 'button-dark',
	    	                   onTap: function(e) {
	    	                	   
	    	                	   return true;
	    	                   }
	    	               }
	    	           ]
	    	  });
	    	  myPopup.then(function(res) {
	    	    
	    	  });
	     }
	     
	     $scope.readyForSync=function(){
	    	 	var myPopup = $ionicPopup.show({
	    		title: $scope.txt.readyForSync,
	    	    subTitle: $scope.ac.name,
	    	    scope: $scope,
	    	    buttons: [
	    	               {
	    	                   text: txt[lng].confirm,
	    	                   type: 'button-balanced',
	    	                   onTap: function(e) {
	    	                	   DataAccess.toSyncActivity($scope.ac,
	    	                			function(){
	    	                		  		$location.path('/app/corp/'+$scope.ac.corporationId);
	    	                		  		$rootScope.$apply();
	    	                	   		}
	    	                		);
    	                	  		return true;
    	                	    }
	    	               },
	    	               {
	    	                   text: txt[lng].close,
	    	                   type: 'button-dark',
	    	                   onTap: function(e) {
	    	                	   
	    	                	   return true;
	    	                   }
	    	               }
	    	           ]
	    	  });
	    	  myPopup.then(function(res) {
	    	    
	    	  });
	     }
	     
	     $scope.newDriver=function(){ 
	    	 $location.path('/app/activity/adddriver/'+$scope.ac.corporationId+'/'+$scope.ac.id);
	    	 $rootScope.$apply();
	    	 
	     }   

	  }catch(e){
	    alert('Generic Error ACCtrl:'+e.toString());
	  }
	
  });	  
})
.controller('ACSyncCtrl', function($scope,$rootScope,$ionicPopup,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {		
	try{
		 $scope.txt=txt[lng];
		 $scope.f=$scope.txt.activity;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findSyncActivity(parseInt($stateParams.id,10),
	        function(activity){
	          try{
	            $scope.ac=activity;
	           
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error findSyncActivity '+e.toString());
	          }
	        }
	     );
	     
	     $scope.openActivityPopup = function(ac) {
	    	  
	    	 	  DataAccess.fromSyncToOpenActivity(ac,
	    	  		   	function(){
		    			  $location.path('/app/acopen/'+ac.id);
				          $rootScope.$apply();  
	    		  		}		  
	    		  );
	    		
	     };
	    	  
	    	  
	    	 
	  }catch(e){
	    alert('Generic Error ACCtrl:'+e.toString());
	  }
	});	  
})
.controller('ACHistoryCtrl', function($scope,$rootScope,$ionicPopup,$location, $stateParams,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {		
	try{
		 $scope.txt=txt[lng];
		
		 $scope.f=txt[lng].activity;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findHistoryActivity(parseInt($stateParams.id,10),
	        function(activity){
	          try{
	            $scope.ac=activity;
	           
	            $scope.loading=false;
	            $rootScope.$apply();
	          }catch(e){
	            alert('Error findActivity '+e.toString());
	          }
	        }
	     );
	     
	     $scope.openDriver = function(driver) {
	    	  $scope.driver=driver

	    	  $scope.calificarDriver=function(driver){
	    		  $location.path('/app/checklist/'+$scope.ac.checklist+"/"+$scope.ac.id+"/"+driver.id);
		          $rootScope.$apply();
		    	  
	    	  }
	    	  
	    	  $scope.removeDriver=function(driver){
	    		 removeByAttr($scope.ac.participants,'id',driver.id);
	    		  
	    		 var tx = db.transaction("activity", "readwrite");
				 var storeActivity = tx.objectStore("activity");
				 storeActivity.put($scope.ac);
				 $rootScope.$apply();
	    	  }
	    	  // An elaborate, custom popup
	    	  var myPopup = $ionicPopup.show({
	    		title: txt[lng].driverView,
	    	    subTitle: $scope.driver.firstName+" - "+$scope.driver.lastName,
	    	    scope: $scope,
	    	    buttons: [
	    	               {
	    	                   text: txt[lng].remove,
	    	                   type: 'button-assertive',
	    	                   onTap: function(e) {
	    	                	   $scope.removeDriver($scope.driver);
	    	                	   return true;
	    	                   }
	    	               },
	    	               {
	    	                   text: txt[lng].note,
	    	                   type: 'button-balanced',
	    	                   onTap: function(e) {
	    	                	   $scope.calificarDriver($scope.driver);
	    	                	   return true;
	    	                   }
	    	               },
	    	               {
	    	                   text: txt[lng].close,
	    	                   type: 'button-dark',
	    	                   onTap: function(e) {
	    	                	   
	    	                	   return true;
	    	                   }
	    	               }
	    	           ]
	    	  });
	    	  myPopup.then(function(res) {
	    	    
	    	  });
	     }

	  }catch(e){
	    alert('Generic Error ACCtrl:'+e.toString());
	  }
	});	  
})
.controller('ChecklistCtrl', function($scope,$rootScope,$ionicPopup,$location, $stateParams,$ionicHistory,$compile, DataAccess) {
	$scope.$on('$ionicView.enter', function() {		
	 try{
		 $scope.txt=txt[lng];
		 $scope.f=txt[lng].checklist;
	     $("#scroll").css("width",window.innerWidth);     
	     $("#scroll").css("height",window.innerHeight-60);
	     $scope.loading=true;
	     DataAccess.findChecklist(parseInt($stateParams.checklistId,10),
	        function(checklist){
	          try{
	        	  
	        	$scope.ch=checklist;
	            $scope.ch.note='approved';
	            
	            $scope.ch.points=$scope.ch.points.sort(
	            		function(a,b){
	            			return a.order-b.order;
	            		}	
	            );
	            for(var i=0; i<$scope.ch.points.length;i++){
	            	var point=$scope.ch.points[i];
	            	$scope.ch.points[i].items=point.items.sort(
		            		function(a,b){
		            			return a.order-b.order;
		            		}	
		            );
	            	$scope.ch.points[i].observations=point.observations.sort(
		            		function(a,b){
		            			return a.order-b.order;
		            		}	
		            );
	            }
	            
	            DataAccess.findOpenActivity(parseInt($stateParams.activityId,10),
	            	function(activity){
	            		$scope.ac=activity;
		                $rootScope.$apply(function(){$scope.loading=false;});
		  	            setTimeout(
		              			function(){
		              				$("ion-item[tagI='H']").each(
		          			            	function(){ 
		          			            		$(this).hide();
		          			            	});
		              				$scope.loading=true;
		              				DataAccess.findNote($stateParams.activityId,$stateParams.driverId,
		        		            		function(note){
		        			            		if(note!=null && note.id!=null ){
		        			            			for(var k=0; k<note.points.length;k++){
		            			            			for(var i=0; i<note.points[k].items.length;i++){
		            			            				var itemId=note.points[k].items[i].id;
		    			            						var item=Util.findItem($scope.ch,itemId);
		    		            							if(item.type==1){
		    		            								item.checked=true;
		    			            						}else if(item.type==2){
		    			            							var itemsRadio=Util.findItemByRadio($scope.ch,item.idRadio);
		    		            								for(var j=0; j<itemsRadio.length;j++){
		    		            									var itemRadio=itemsRadio[j];
		    		            									itemRadio.idRadio=item.id;
		    		            								}
		    			            						}
		    			            				    } 
		            			            			
		            			            			for(var j=0; j< note.points[k].observations.length;j++){
		            			                    		var item=note.points[k].observations[j];
		            			                    		var itemObs=Util.findObservation($scope.ch,item.id);
		            			                    		itemObs.checked=true; 
		            			                    	}
		        			            			}
		        			            			
		        			            			for(var k=0; k<note.observations.lengthL;k++){
		        			            				var obs=note.observations[k];
		        			            				var itemObs=Util.findACObservation($scope.ac,obs.id);
		        			            				itemObs.checked=true;
		        			            			}
		        			            			
		        			            			setTimeout(function(){$scope.ch.note=note.result;$rootScope.$apply();},1000);
		        			            			
		        			            			
		        			            			$rootScope.$apply(function(){$scope.loading=false;});
		        			            		}
		        		            		});
		              				
		              			},
		              			100
		  	              );
		          
	            	}	
	            );
	            
        		
  	        
	          }catch(e){
	            alert('Error findChecklist '+e.toString());
	          }
	        }
	     );
	     $scope.actRadios=function(idRadio){
	    	var itemsRadio=Util.findItemByRadio($scope.ch,idRadio);
			for(var j=0; j<itemsRadio.length;j++){
				var itemRadio=itemsRadio[j];
				itemRadio.idRadio=item.id;
			}
		 }
	     
	     $scope.openClose=function(id){
	    	 $("ion-item[tag='"+id+"']").each(function(){
	    		 if($(this).attr('tagI')=='H'){
	    			 $(this).show();
	    		 	 $(this).attr('tagI','S');
	    		 }else{
	    			 $(this).hide();
	    		 	 $(this).attr('tagI','H');
	    		 }
	    	 });		 	 
	     }
    
	    $scope.offItems=function(itemsStr){
	    	if(itemsStr!=null && itemsStr!=''){
			    var items=itemsStr.split(',');
			    for( var i=0;i<items.length;i++){
			    	$("#r"+items[i]).each(function(){$(this).attr('disabled','true')});
			    	$("#c"+items[i]).each(function(){$(this).attr('disabled','disabled')});
			    }    
	    	}
	    }
	     
	    $scope.acept=function(){
		    var note={result:'approved',id:parseInt($stateParams.activityId,10)+"_"+parseInt($stateParams.driverId,10),checklist:parseInt($stateParams.checklistId,10),activityId:parseInt($stateParams.activityId,10),driverId:parseInt($stateParams.driverId,10),points:[], items:[], observations:[]};
		    note.result=$scope.ch.note;
		    for(var i=0; i<$scope.ch.points.length;i++){
            	var point=$scope.ch.points[i];
            	var pointAux={id:point.id,items:[],observations:[]};
            	for(var j=0; j< point.items.length;j++){
            		var item=point.items[j];
            		if(item.type==1){
            			if(item.checked){
            				note.items.push(item.id);
            				pointAux.items.push(item);
            			}
            		}else if(item.type==2){
            			if(item.idRadio==item.id){
            				note.items.push(item.id);
            				pointAux.items.push(item);
            			}
            		}
            	}
            	for(var j=0; j< point.observations.length;j++){
            		var item=point.observations[j];
            		if(item.checked){
            			 pointAux.observations.push(item);
            		}
            		
            	}
            	note.points.push(pointAux);
            }
		    
		    for(var i=0; i<$scope.ac.observations.length;i++){
            	var obs=$scope.ac.observations[i];
        		if(obs.checked){
        			note.observations.push(obs);
        		}
            }
		    
		    DataAccess.addNote(note);
		    alert($scope.txt.noteAdded);
		    $ionicHistory.goBack(-1);
	    }
	    
	    $scope.cancel=function(){
	    	 $ionicHistory.goBack(-1);
	    }

	  }catch(e){
	    alert('Generic Error ChecklistCtrl:'+e.toString());
	  }
	})  
})
.controller('SearchDriverCtrl', function($scope,$rootScope,$ionicPopup,$location, $ionicPopover, $stateParams,$compile,$ionicHistory, DataAccess) {
	$scope.$on('$ionicView.enter', function() {		
	 try{
		 $scope.txt=txt[lng];
		 $scope.f=txt[lng].searchDriverTit;
		 $scope.drivers=[];
		 
		 $scope.searchDriver=function(value){
			 $scope.loading=true;
			 DataAccess.findDrivers(parseInt($stateParams.corpId,10),value,
				function(drivers){
				 	$scope.loading=false;	
				 	$scope.drivers=drivers;
				 	if($scope.drivers.length==0){
				 		alert(txt[lng].searchNoData);
				 	}
				 	$rootScope.$apply();
		 		}		 
			 );
			 
		 }
		 
		 $scope.addDriver=function(driver){
			 $scope.loading=true;
			 $scope.$apply();
			 DataAccess.addDriver(parseInt($stateParams.acid,10),driver, function(already){
				 $scope.loading=false;
				 if(already){
					 alert( $scope.txt.alreadyAdded);	 
				 }else{
					 alert( $scope.txt.added);
				 }
				 
				 $scope.$apply();
			 }
			);
		 }
		 
		 
		 // .fromTemplateUrl() method
		  $ionicPopover.fromTemplateUrl('my-popover.html', {
		    scope: $scope
		  }).then(function(popover) {
		    $scope.popover = popover;
		  });


		  $scope.newDriver = function($event) {
		    $scope.popover.show($event);
		  };
		  $scope.cancelNewDriver = function() {
		    $scope.popover.hide();
		  };
		  $scope.aceptNewDriver = function(firstName,lastName,CPID,email,sex) {
			    $scope.popover.hide();
			    var driver={id:parseInt(CPID,10),firstName:firstName,lastName:lastName,corporationid:CPID,email:email,sex:sex,temp:true};
			    $scope.addDriver(driver);
		  };
		  
		  
		 $scope.back=function(){
			 $ionicHistory.goBack(-1);
		 }
		 
	  }catch(e){
	    alert('Generic Error ChecklistCtrl:'+e.toString());
	  }
	})  
});
