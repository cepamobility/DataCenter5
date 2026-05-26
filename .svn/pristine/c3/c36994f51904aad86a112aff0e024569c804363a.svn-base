var Util={
		
		findItem:function(checklist,itemId){
		 
		  for(var i=0; i<checklist.points.length;i++){
            	var point=checklist.points[i];
            	for(var j=0; j<point.items.length;j++){
            		var item=point.items[j];
            		if(item.id==itemId){
            			return item;
            		}
            	} 
            }
			 
		},
		findObservation:function(checklist,itemId){
			 
			  for(var i=0; i<checklist.points.length;i++){
	            	var point=checklist.points[i];
	            	for(var j=0; j<point.observations.length;j++){
	            		var item=point.observations[j];
	            		if(item.id==itemId){
	            			return item;
	            		}
	            	} 
	            }
				 
		},
		findACObservation:function(activity,itemId){
		 
			for(var i=0; i<activity.observations.length;i++){
            	var obs=activity.observations[i];
        		if(obs.id==itemId){
        			return obs;
        		}
            }
			 
		},
		findItemByRadio:function(checklist, idRadio){
			  var items=[];
			  for(var i=0; i<checklist.points.length;i++){
	            	var point=checklist.points[i];
	            	for(var j=0; j<point.items.length;j++){
	            		var item=point.items[j];
	            		if(item.idRadio==idRadio){
	            			items.push(item);
	            		}
	            	} 
	           }
			  return items;
		},
		equalsAnyOf:function (index,keysToFind, onfound, onfinish) {
		    var set = keysToFind.sort();
		    var i = 0;
		    var cursorReq = index.openCursor(); // Assume 'index' exists in a parent closure scope
		    cursorReq.onsuccess = function (event) {
		        var cursor = event.target.result;
		        if (!cursor) { onfinish(); return; }
		        var key = cursor.key;
		        while (key > set[i]) {
		            // The cursor has passed beyond this key. Check next.
		            ++i;
		            if (i === set.length) {
		                // There is no next. Stop searching.
		                onfinish();
		                return;
		            }
		        }
		        if (key === set[i]) {
		            // The current cursor value should be included and we should continue
		            // a single step in case next item has the same key or possibly our
		            // next key in set.
		            onfound(cursor.value);
		            cursor.continue();
		        } else {
		            // cursor.key not yet at set[i]. Forward cursor to the next key to hunt for.
		            cursor.continue(set[i]);
		        }
		    };
		    return c;
		},
		logical_or:function (index1, keyRange1, index2, keyRange2, onfound, onfinish) {
		    var openCursorRequest1 = index1.openCursor(keyRange1);
		    var openCursorRequest2 = index2.openCursor(keyRange2);
		 
		    assert(index1.objectStore === index2.objectStore); // OR can only be done on same store
		    var primKey = index1.objectStore.keyPath;
		    
		    var set = {};
		    var resolved = 0;
		 
		    function complete() {
		        if (++resolved === 2) onfinish();
		    }
		 
		    function union(item) {
		        var key = JSON.stringify(item[primKey]);
		        if (!set.hasOwnProperty(key)) {
		            set[key] = true;
		            onfound(item);
		        }
		    }
		 
		    openCursorRequest1.onsuccess = function (event) {
		        var cursor = event.target.result;
		        if (cursor) {
		            union(cursor.value);
		        } else {
		            complete();
		        }
		    }
		 
		    openCursorRequest2.onsuccess = function (event) {
		        var cursor = event.target.result;
		        if (cursor) {
		            union(cursor.value);
		        } else {
		            complete();
		        }
		    }
		}
} 

function removeByAttr(arr, attr, value){
    var i = arr.length;
    while(i--){
       if( arr[i] 
           && arr[i].hasOwnProperty(attr) 
           && (arguments.length > 2 && arr[i][attr] === value ) ){ 

           arr.splice(i,1);

       }
    }
    return arr;
}

//Traducir textos
function translate(texts){
	var res=texts;
	$.ajax({
		  type: "POST",
		  url: 'http://www.cepadc.com/DCWebReportToolsJJ/webapi/language/translate',
		  async:false,
		  data:JSON.stringify(texts),
		  contentType: "application/json",
		  dataType:"json",
		  success: function(data){
			  res=data;
			  		  },
		  dataType: 'json'
		});
	
	return res;
}

//funcion para salir del sistema
function logout(){
	
	$.ajax({
		  type: "GET",
		  url: 'http://www.cepadc.com/DCWebReportToolsJJ/webapi/security/logout',
		  async:false,
		  contentType: "application/json",
		  dataType:"json",
		  success: function(data){
			  if(data.logout){
		  			document.location.href='index.html';
			  }
		  },
		  dataType: 'json'
		});
	
}


//Funciones Auxiliares
function getPartial(partialname){
	var res="";
	$.ajax({
		  type: "GET",
		  url: 'partials/'+partialname,
		  async:false,
		  dataType:"text",
		  success: function(data){
			res=data;  
		  }
		});
	
	return res;
}


function stikyFirstColumn(root,tableclass){
	 $("#"+root).find('.'+tableclass).each(function() {
		   var clone=$(this).clone();
		   
		   $(clone).attr('id',$(clone).attr('id')+"bis");
		   $(clone).css({'overflow-x':'hidden',position:'absolute',left:$(this).position().left,top:$(this).position().top});
		   
		   var trs=$(this).find('tr');
		   var trsc=$(clone).find('tr');
		   var size=0;
		   for(var i=0; i<trs.length;i++){
			   var t=trs[i];
			   var tc=trsc[i];
			   var tds=$(t).children();
			   var tdsc=$(tc).children();
			   if($(tds[0]).innerWidth()>size){
				   size=$(tds[0]).innerWidth();
			   }
			   					 
			   var height=$(tds[0]).innerHeight();
			   for(var j=1; j<tds.length;j++){
				   if($(tds[j]).innerHeight()>height){
					   height=$(tds[j]).innerHeight();
				   }
				   $(tdsc[j]).remove();
			   }
			   $(tc).height(height);
		   }
		   
		   $(clone).width(size);
		   
		   $('#reportview1').append(clone);
		   
		   
		});
}

//Descarga un archivo, el parametro p es la barra de avance ngProgressLite
function download(p,url){
	p.set(Math.random());
	p.start();
	setTimeout(function(){
				var iframe = document.createElement("iframe");
			    iframe.setAttribute("src", url);
			    iframe.setAttribute("style", "display: none");
			    setTimeout(function(){p.done();},3000);
			    document.body.appendChild(iframe);		
			},100);

}

function openPopupTab(url){
	setTimeout(function(){
				var w=window.open(url,"_blanck");
				w.focus(); 
			},100);

} 

String.prototype.like = function(search) {
    if (typeof search !== 'string' || this === null) {return false; }
    // Remove special chars
    search = search.replace(new RegExp("([\\.\\\\\\+\\*\\?\\[\\^\\]\\$\\(\\)\\{\\}\\=\\!\\<\\>\\|\\:\\-])", "g"), "\\$1");
    // Replace % and _ with equivalent regex
    search = search.replace(/%/g, '.*').replace(/_/g, '.');
    // Check matches
    return RegExp('^' + search + '$', 'gi').test(this);
}
