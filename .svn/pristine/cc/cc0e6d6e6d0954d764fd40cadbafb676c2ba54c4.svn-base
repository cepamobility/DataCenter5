ip="www.cepadc.com";
Config={ 
   url:{	 
   		sync:'http://'+ip+'/datacenter/webapi/instructor/sync',
		login:'http://'+ip+'/datacenter/webapi/instructor/login',
		checklists:'http://'+ip+'/datacenter/webapi/instructor/checklists',
		syncUp:'http://'+ip+'/datacenter/webapi/instructor/syncUp'
		
	} 
 };


angular.module('starter.services', [])


.factory('DataAccess', function($http,$localstorage) {
  // Might use a resource here that returns a JSON array
	
	var auxFunc={
			findActivities:function(corpID,status, callback){
				var activities=[];
				try{
					var tx = db.transaction("activity", "readwrite");
					var storeActivity = tx.objectStore("activity");
					var indexActivity = storeActivity.index("by_corporationId");

					var request = indexActivity.openCursor(IDBKeyRange.only(corpID));
					request.onsuccess = function() {
					  var cursor = request.result;
						  if (cursor) {
						    // Called for each matching record
							  if(cursor.value.status==status){
								activities.push(cursor.value);
							  }  
						   	 cursor.continue();
						  }else{
							  callback(activities);
						  }
					};
					
				}catch(e){
					alert('Error findActivities:'+e.toString());
					callback([{name:'No Data',id:0}]);
				}	
			},
			idsOpenActivities:function(callback){
				 var tx = db.transaction("activityopen", "readwrite");
				 var store = tx.objectStore("activityopen");
				 var myIndex = store.index('by_id'); 
				 var request = myIndex.openCursor();
				 var ids="";		 
				 request.onsuccess = function() {
					 var cursor = request.result;
					  if (cursor) {
					  	if(ids!="") ids+=",";
					  	ids+=cursor.value.id;
					  	cursor.continue();
					  }else	 {
					 	callback(ids);
					  }	 	
				 }
				  
			},
			idstSyncActivities:function(callback){
				 var tx = db.transaction("activitysync", "readwrite");
				 var store = tx.objectStore("activitysync");
				 var myIndex = store.index('by_id'); 
				 var request = myIndex.openCursor();
				 var ids="";		 
				 request.onsuccess = function() {
					 var cursor = request.result;
					  if (cursor) {
					  	if(ids!="") ids+=",";
					  	ids+=cursor.value.id;
					  	cursor.continue();
					  }else	 {
					 	callback(ids);
					  }	 	
				 }
				  
			},
			idsHistoryActivities:function(callback){
				 var tx = db.transaction("activityhistory", "readwrite");
				 var store = tx.objectStore("activityhistory");
				 var myIndex = store.index('by_id'); 
				 var request = myIndex.openCursor();
				 var ids="";		 
				 request.onsuccess = function() {
					 var cursor = request.result;
					  if (cursor) {
					  	if(ids!="") ids+=",";
					  	ids+=cursor.value.id;
					  	cursor.continue();
					  }else	 {
					 	callback(ids);
					  }	 	
				 }
				  
			},
			findActivity:function(acID, callback){
				try{
					var tx = db.transaction("activity", "readwrite");
					var storeCorp = tx.objectStore("activity");
					var indexCorp = storeCorp.index("by_id");
					var request=indexCorp.openCursor(IDBKeyRange.only(acID));
					request.onsuccess = function() {
						 var cursor = request.result;
						  if (cursor) {
							  	callback(cursor.value);
						  }	  	
					};
				}catch(e){
					alert('Error findActivities:'+e.toString());
					callback([{name:'No Data',id:0}]);
				}	
			},
			findNoteById:function(id,callback){
				 var tx = db.transaction("activitycheck", "readwrite");
				 var store = tx.objectStore("activitycheck");
				 var myIndex = store.index('by_id');
				 var request = myIndex.openCursor(IDBKeyRange.only(id));
				 var note={};
				 request.onsuccess = function() {
					 var cursor = request.result;
					  if (cursor) {
						 	note=cursor.value;
					  }
					  callback(note);
				 }
				 
			}
	};
	
	var object={
    sync2: function( callbackFinish, callM) {
			/*
				interface IDBKeyRange {
				    static IDBKeyRange only (any value);
				    static IDBKeyRange lowerBound (any lower, optional boolean open = false);
				    static IDBKeyRange upperBound (any upper, optional boolean open = false);
				    static IDBKeyRange bound (any lower, any upper, optional boolean lowerOpen = false, optional boolean upperOpen = false);
				};
			*/

			callM('Starting SYNC...');;
			 
			auxFunc.idsOpenActivities(
					
				function(idsOpen){
				
					auxFunc.idstSyncActivities(
						function(idsSync){
							auxFunc.idsHistoryActivities(
								function(idsHistory){
									var actIds=idsOpen;
									if(actIds!="" && idsSync!="")actIds+=",";
									actIds+idsSync;
									if(actIds!="" && idsHistory!="")actIds+=",";
									actIds+=idsHistory;
									
									var lastUpdate=$localstorage.get('lastUpdate','01-01-2001');
						 			var user=$localstorage.getObject('user');
									if(user==null){
										alert('User not login');
									}
									$.ajax({
									  type: "GET",
									  url: Config.url.sync+'?lastId=0&activitiesId='+actIds+'&lastUpdate='+lastUpdate+'&user='+user.username+'&password='+user.password+'',
									  async:true,
									  contentType: "application/json",
									  dataType:'json',
									  success: function(data){
										 $localstorage.set('lastUpdate',new Date().getDate()+"-"+new Date().getMonth()+"-"+new Date().getFullYear());
										  var corporations=data;
										  callM('Load '+corporations.length+" corporations");
										  try{
											 for(var c=0;c<corporations.length;c++){
												callM('Saving data '+corporations[c].name+'...');	
												 
												var tx = db.transaction("corporations", "readwrite");
												var storeCorp = tx.objectStore("corporations");
												storeCorp.put(corporations[c]);
												
												for(var uc=0;corporations[c].updateDrivers!=null && uc<corporations[c].updateDrivers.length;uc++){
													var drivers=corporations[c].updateDrivers[uc];
													var tx = db.transaction("drivers", "readwrite");
													var storeDriver = tx.objectStore("drivers");
													storeDriver.put(drivers);
												}
												var checklists="";
												for(var ac=0;corporations[c].activities!=null && ac<corporations[c].activities.length;ac++){
													var activity=corporations[c].activities[ac];
													var tx = db.transaction("activity", "readwrite");
													var storeActivity = tx.objectStore("activity");
													storeActivity.put($.extend(activity,{status:'NEW'}));
													if(checklists!=""){
														checklists+=",";
													}
													checklists+=activity.checklist;
												}
												$.ajax({
													  type: "GET",
													  url: Config.url.checklists+'?ids='+checklists.toString()+'&user='+user.username+'&password='+user.password+'',
													  async:false,
													  contentType: "application/json",
													  dataType:'json',
													  success: function(checklists){
														  for(var ac=0;checklists!=null && ac<checklists.length;ac++){
																var ch=checklists[ac];
																var tx = db.transaction("checklist", "readwrite");
																var storeCh = tx.objectStore("checklist");
																storeCh.put(ch);
															}
													  }
												});
												
												
												
												callM('End Corporation '+ corporations[c].name);	
											 }

											 callM('Data saved!!!');
											 callbackFinish();

										  }catch(e){
											   alert('General error sync '+e.toString());
										  }
								  		},
										error:function (xhr, ajaxOptions, thrownError) {
									  		alert('Server not respond:'+thrownError);
									  	}

									 
								     });
								
								}			
							);
						}			
					);
					
				}
			);

			
			
    },
    syncUp2:function(callback){
    	try{
        	var idsChecklistNote=[];
    		var activities=new Object(); 
    		var activitiesByI=[];
    		
        	var tx = db.transaction("activitysync", "readwrite");
    		var storeCorp = tx.objectStore("activitysync");
    		var indexCorp = storeCorp.index("by_corporationId");
    		var request=indexCorp.openCursor();
    		var requestAux=request;
    		request.onsuccess = function(event) {
    			 var cursor = event.target.result;
    			  if (cursor) {
    				  var activity=cursor.value;
    				  for(var i=0; i<activity.participants.length;i++){
    					  var driver=activity.participants[i];
    					  idsChecklistNote.push(activity.id+"_"+driver.id);
    					  activities["A_"+activity.id+"_"+driver.id]=activity;
    					  activitiesByI.push(activity);
    				  }
    				  
    				  cursor.continue();
    			  }else{
    				  for(var i=0;i<activitiesByI.length;i++){
    					  	activity=activitiesByI[i];
    						var tx = db.transaction("activitysync", "readwrite");
    						var store = tx.objectStore("activitysync");
    						activity.status='HISTORY';
    						store.delete(activity.id);
    						var tx = db.transaction("activityhistory", "readwrite");
    						var store = tx.objectStore("activityhistory");
    						store.put(activity);
    				 }
    				  
    				 callback(true);
    		    }
    		};
    	}catch(e){
    		alert('Error syncUp2 '+e.toString());
    	}
    },
    
    syncUp:function(callback){
    	try{
    		
    		var idsChecklistNote=[];
    		var activities=new Object(); 
    		var activitiesByI=[];
    		
    		var tx = db.transaction("activitysync", "readwrite");
			var storeAc = tx.objectStore("activitysync");
			var indexAc = storeAc.index("by_corporationId");
			var request=indexAc.openCursor();
			var requestAux=request;
			request.onsuccess = function(event) {
				 var cursor = event.target.result;
    			  if (cursor) {
    				  var activity=cursor.value;
    				  for(var i=0; i<activity.participants.length;i++){
    					  var driver=activity.participants[i];
    					  idsChecklistNote.push(activity.id+"_"+driver.id);
    					  activities["A_"+activity.id+"_"+driver.id]=activity;
    					  activitiesByI.push(activity);
    				  }
    				  
    				  cursor.continue();
    			  }else{
    				  
    				  if(idsChecklistNote.length>0){
    					  
     					 var tx = db.transaction("activitycheck", "readwrite");
						 var store = tx.objectStore("activitycheck");
						 var myIndex = store.index('by_id');
						 var syncUpObject=[];
						 Util.equalsAnyOf(myIndex, idsChecklistNote,function(note){
							    var activity=activities["A_"+note.id];
							 	var driver=null;
							 	for(var i=0;driver==null && i<activity.participants.length;i++){
							 		var driverAux=activity.participants[i];
							 		if(driverAux.id==note.driverId){
							 			driver=driverAux;
							 		}
							 	}
							 	for(var i=0;i<note.points.length;i++){
							 		var point=note.points[i];
							 		delete point['$$hashKey'];
							 		for(var j=0;  j<point.items.length;j++){
							 			var item=point.items[j];
							 			delete item['$$hashKey'];
							 		}
							 		for(var j=0;  j<point.observations.length;j++){
							 			var item=point.observations[j];
							 			delete item['$$hashKey'];
							 		}
							 	}
							 	for(var i=0;i<note.observations.length;i++){
							 		var obs=note.observations[i];
							 		delete obs['$$hashKey'];
							 	}
							 	var result=note.result;
							 	if(note.result=='A'){
							 		result='approved';
							 	}
							 	var objUp={
								 		activityId:activity.id,
								 		driverId:driver.id,
								 		firstName:driver.firstName,
								 		lastName:driver.lastName,
								 		email:driver.email,
								 		sexo:driver.sex,
								 		corporationId:driver.corporationid,
								 		corpId:activity.corporationId,
								 		temp:driver.temp?true:false,
								 		result:note.result,
								 		points:note.points,
								 		observations:note.observations
								 	};
							 	
							 	syncUpObject.push(objUp);
						 },
						  function(){
							var user=$localstorage.getObject('user');
							if(user==null){
								alert('User not login');
							}
							 $.ajax({
								  type: "POST",
								  url: Config.url.syncUp,
								  async:false,
								  contentType: "application/json",
								  dataType:'json',
								  data:JSON.stringify({user:user.username,password:user.password,data:syncUpObject}),
								  success: function(result){
									  if(result){
										  
										  for(var i=0;i<activitiesByI.length;i++){
											  	activity=activitiesByI[i];
   											var tx = db.transaction("activitysync", "readwrite");
												var store = tx.objectStore("activitysync");
												activity.status='HISTORY';
												store.delete(activity.id);
												var tx = db.transaction("activityhistory", "readwrite");
												var store = tx.objectStore("activityhistory");
												store.put(activity);
										  }
									  }
									  callback(result);
								  },error:function(){
									  alert('ERROR: Server internal error or server not respond');
									  callback(false);
								  }
						 	});
						 });
					 
    				  }else{
    					  callback(true);
    				  }
    			  }    				   
    			  
    		};

    	}catch(e){
    		alert('ERROR: Sync Aup '+e.toString());
    	}
		
    },
    clearCorps: function(callback) {
		var corps=[];
		try{
			var tx = db.transaction("corporations", "readwrite");
			var storeCorp = tx.objectStore("corporations");
			var indexCorp = storeCorp.index("by_name");

			var request = indexCorp.openCursor();
			request.onsuccess = function() {
			  var cursor = request.result;
				  if (cursor) {
				    // Called for each matching record.
					  cursor.delete(cursor.value.id);
					  cursor.continue();
				  }else{
					  callback();
				  }
			};
			
		}catch(e){
			alert('Error clearCorps:'+e.toString());
			callback();
		}	
	},
    
	findCorps2: function(callback) {
		var corps=[];
		try{
			var tx = db.transaction("corporations", "readwrite");
			var storeCorp = tx.objectStore("corporations");
			var indexCorp = storeCorp.index("by_name");

			var request = indexCorp.openCursor();
			request.onsuccess = function() {
			  var cursor = request.result;
				  if (cursor) {
				    // Called for each matching record.
					  if(cursor.value.activities.length>0) 
						  corps.push(cursor.value);
					  cursor.continue();
				  }else{
					  callback(corps);
				  }
			};
			
		}catch(e){
			alert('Error findCorps:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	
	findActivities:function(corpID, callback){
		var activities=[];
		try{
			var tx = db.transaction("corporations", "readwrite");
			var storeCorp = tx.objectStore("corporations");
			var indexCorp = storeCorp.index("by_id");
			var request=indexCorp.openCursor(IDBKeyRange.only(parseInt(corpID,10)));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  	callback(cursor.value.activities);
				  }	  	
			};
		}catch(e){
			alert('Error findActivities:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	}
	,
	
	findOpenActivities:function(corpID, callback){
		var activities=[];
		try{
			var tx = db.transaction("activityopen", "readwrite");
			var storeCorp = tx.objectStore("activityopen");
			var indexCorp = storeCorp.index("by_corporationId");
			var request=indexCorp.openCursor(IDBKeyRange.only(parseInt(corpID,10)));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  activities.push(cursor.value);
					  cursor.continue();
				  }else{
					  callback(activities);
				  }
			};
		}catch(e){
			alert('Error findOpenActivities:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	
	findSyncActivities:function(corpID, callback){
		var activities=[];
		try{
			var tx = db.transaction("activitysync", "readwrite");
			var storeCorp = tx.objectStore("activitysync");
			var indexCorp = storeCorp.index("by_corporationId");
			var request=indexCorp.openCursor(IDBKeyRange.only(parseInt(corpID,10)));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  activities.push(cursor.value);
					  cursor.continue();
				  }else{
					  callback(activities);
				  }	
			};
		}catch(e){
			alert('Error findSyncActivities:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	
	findHistoryActivities:function(corpID, callback){
		var activities=[];
		try{
			var tx = db.transaction("activityhistory", "readwrite");
			var storeCorp = tx.objectStore("activityhistory");
			var indexCorp = storeCorp.index("by_corporationId");
			var request=indexCorp.openCursor(IDBKeyRange.only(parseInt(corpID,10)));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  activities.push(cursor.value);
					  cursor.continue();
				  }else{
					  callback(activities);
				  }	
			};
		}catch(e){
			alert('Error findHistoryActivities:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	}
	
	,
	
	findActivity:function(acID, callback){
		 auxFunc.findActivity(acID, callback);
	},
	
	findChecklist:function(chID, callback){
		try{
			var tx = db.transaction("checklist", "readwrite");
			var storeCorp = tx.objectStore("checklist");
			var indexCorp = storeCorp.index("by_id");
			var request=indexCorp.openCursor(IDBKeyRange.only(chID));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  	callback(cursor.value);
				  }	  	
			};
		}catch(e){
			alert('Error findChecklist:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	openActivity:function(activity, callback){
		try{
			var tx = db.transaction("activity", "readwrite");
			var store = tx.objectStore("activity");
			store.delete(activity.id);
			activity.status='OPEN';
			var tx = db.transaction("activityopen", "readwrite");
			var store = tx.objectStore("activityopen");
			store.put(activity);
			callback();
		}catch(e){
			alert('Error openActivitiy:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	toSyncActivity:function(activity, callback){
		try{
			var tx = db.transaction("activityopen", "readwrite");
			var store = tx.objectStore("activityopen");
			activity.status='SYNC';
			store.delete(activity.id);
			var tx = db.transaction("activitysync", "readwrite");
			var store = tx.objectStore("activitysync");
			store.put(activity);
			callback();
		}catch(e){
			alert('Error openActivitiy:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	toHistoryActivity:function(activity, callback){
		try{
			var tx = db.transaction("activitysync", "readwrite");
			var store = tx.objectStore("activitysync");
			activity.status='HISTORY';
			store.delete(activity.id);
			var tx = db.transaction("activityhistory", "readwrite");
			var store = tx.objectStore("activityhistory");
			store.put(activity);
			callback();
		}catch(e){
			alert('Error openActivitiy:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	fromSyncToOpenActivity:function(activity, callback){
		try{
			var tx = db.transaction("activitysync", "readwrite");
			var store = tx.objectStore("activitysync");
			activity.status='OPEN';
			store.delete(activity.id);
			var tx = db.transaction("activityopen", "readwrite");
			var store = tx.objectStore("activityopen");
			store.put(activity);
			callback();
		}catch(e){
			alert('Error fromSyncToOpenActivity:'+e.toString());
			callback();
		}	
	},
	findDrivers:function(corpId, value,callback){
		var drivers=[];
		try{
			var tx = db.transaction("corporations", "readwrite");
			var storeCorp = tx.objectStore("corporations");
			var indexCorp = storeCorp.index("by_id");
			
			var request = indexCorp.openCursor(IDBKeyRange.only(parseInt(corpId,10)));
			request.onsuccess = function() {
			  var cursor = request.result;
				  if (cursor) {
					  var words=value.split(' ');
					  for(var i=0; i<cursor.value.updateDrivers.length;i++) {
						  var driver=cursor.value.updateDrivers[i];
						  var ok=true;
						  for(var j=0; j< words.length;j++){
							  ok=(driver.firstName+" "+driver.lastName+" "+driver.corporationId).like("%"+words[j]+"%") && ok;
						  }
						  if(ok){
							  drivers.push(driver);
						  }
					  }  
					  
					  cursor.continue();
				  }else{
					  callback(drivers);
				  }
			};
		}catch(e){
			alert('Error findDrivers:'+e.toString());
			callback(drivers);
		}	
		
	},
	findOpenActivity:function(acID, callback){
		try{
			var tx = db.transaction("activityopen", "readwrite");
			var storeCorp = tx.objectStore("activityopen");
			var indexCorp = storeCorp.index("by_id");
			var request=indexCorp.openCursor(IDBKeyRange.only(acID));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  	callback(cursor.value);
				  }	  	
			};
		}catch(e){
			alert('Error findActivities:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	findSyncActivity:function(acID, callback){
		try{
			var tx = db.transaction("activitysync", "readwrite");
			var storeCorp = tx.objectStore("activitysync");
			var indexCorp = storeCorp.index("by_id");
			var request=indexCorp.openCursor(IDBKeyRange.only(acID));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  	callback(cursor.value);
				  }	  	
			};
		}catch(e){
			alert('Error findSyncActivity:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	findHistoryActivity:function(acID, callback){
		try{
			var tx = db.transaction("activityhistrory", "readwrite");
			var storeCorp = tx.objectStore("activityhistrory");
			var indexCorp = storeCorp.index("by_id");
			var request=indexCorp.openCursor(IDBKeyRange.only(acID));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
					  	callback(cursor.value);
				  }	  	
			};
		}catch(e){
			alert('Error findHistoryActivity:'+e.toString());
			callback([{name:'No Data',id:0}]);
		}	
	},
	countNewActivities:function(corpId,callback){
		var tx = db.transaction("activity", "readwrite");
		 var store = tx.objectStore("activity");
		 var myIndex = store.index('by_corporationId'); 
		 var countRequest = myIndex.openCursor(IDBKeyRange.only(corpId));
		 var count=0;
		 countRequest.onsuccess = function() {
			 var cursor = countRequest.result;
			  if (cursor) {
			  	count++;
			  	cursor.continue();
			  }else	 {
			 	callback(count);
			  }	 	
		 }
		  
		  
	},
	countOpenActivities:function(corpId,callback){
		 var tx = db.transaction("activityopen", "readwrite");
		 var store = tx.objectStore("activityopen");
		 var myIndex = store.index('by_corporationId'); 
		 var countRequest = myIndex.openCursor(IDBKeyRange.only(corpId));
		 var count=0;
		 countRequest.onsuccess = function() {
			 var cursor = countRequest.result;
			  if (cursor) {
			  	count++;
			  	cursor.continue();
			  }else	 {
			 	callback(count);
			  }	 	
		 }
		  
	},
	countSyncActivities:function(corpId,callback){
		 var tx = db.transaction("activitysync", "readwrite");
		 var store = tx.objectStore("activitysync");
		 var myIndex = store.index('by_corporationId'); 
		 var countRequest = myIndex.openCursor(IDBKeyRange.only(corpId));
		 var count=0;
		 countRequest.onsuccess = function() {
			 var cursor = countRequest.result;
			  if (cursor) {
			  	count++;
			  	cursor.continue();
			  }else	 {
			 	callback(count);
			  }	 	
		 }
		  
	},
	countHistoryActivities:function(corpId,callback){
		 var tx = db.transaction("activityhistory", "readwrite");
		 var store = tx.objectStore("activityhistory");
		 var myIndex = store.index('by_corporationId'); 
		 var countRequest = myIndex.openCursor(IDBKeyRange.only(corpId));
		 var count=0;
		 countRequest.onsuccess = function() {
			 var cursor = countRequest.result;
			  if (cursor) {
			  	count++;
			  	cursor.continue();
			  }else	 {
			 	callback(count);
			  }	 	
		 }
		  
	},
	addDriver:function(acid,driver,callback){
	 
		    var tx = db.transaction("activityopen", "readwrite");
			var storeCorp = tx.objectStore("activityopen");
			var indexCorp = storeCorp.index("by_id");
			var request=indexCorp.openCursor(IDBKeyRange.only(acid));
			request.onsuccess = function() {
				 var cursor = request.result;
				  if (cursor) {
						var already=false;
						var activity=cursor.value;  
					 	if(activity!=undefined && activity!=null){
						  	for(var i=0;!already && i<activity.participants.length;i++){
						  		var participant=activity.participants[i];
						  		already=(participant.id==driver.id);
						  	}
						  	if(!already){
						  		activity.participants.push(driver);
						  		activity.participants=activity.participants.sort(
						  			function(a,b){
						  				(a.firstName+a.lastName).localeCompare(b.firstName+b.lastName);
						  			}
						  		);
						  		cursor.update(activity);
						  	}
						  	callback(already);
					 	}
				  }else{
					  alert('ERROR: Activity not exist '+acid);  
					 	callback(false);
				  }	  	
			}; 
		  
		  
	},
	addNote:function(note){
		try{
				
			 var tx = db.transaction("activitycheck", "readwrite");
			 var store = tx.objectStore("activitycheck");
			 store.put(note);
		}catch(e){
			alert("ERROR Service.addNote:"+e.toString());
		}
	},
	findNote:function(acid,driverId,callback){
		 var tx = db.transaction("activitycheck", "readwrite");
		 var store = tx.objectStore("activitycheck");
		 var myIndex = store.index('by_id');
		 var request = myIndex.openCursor(IDBKeyRange.only(acid+"_"+driverId));
		 var note={};
		 request.onsuccess = function() {
			 var cursor = request.result;
			  if (cursor) {
				 	note=cursor.value;
			  }
			  callback(note);
		 }
		 
	}
	



  };

  return object;
});
