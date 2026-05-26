
var ui=new UserInterfaceLibrary();

function UserInterfaceLibrary(){
  state=4;
  SPEED_LOADING_MESSAGE=1000; //MILISECONDS
  SPEED_POINT_IN_LOADING_MESSAGE=400;
}


UserInterfaceLibrary.prototype.switchPoint=function(){
		var point2=null;
		if(state!=5){
			switch(state){
				case 1:
					state=2;
					point2=document.getElementById("td_uno");
					point2.style.visibility="visible";
					break;
				case 2:
					state=3;
					point2=document.getElementById("td_dos");
					point2.style.visibility="visible";
					break;
				case 3:
					state=4;
					point2=document.getElementById("td_tres");
					point2.style.visibility="visible";
					break;
				case 4:
					state=1;
					point2=document.getElementById("td_uno");
					point2.style.visibility="hidden";
					point2=document.getElementById("td_dos");
					point2.style.visibility="hidden";
					point2=document.getElementById("td_tres");
					point2.style.visibility="hidden";
					break;
			}
			window.setTimeout('this.switchPoint()',this.SPEED_POINT_IN_LOADING_MESSAGE)
	}
}


UserInterfaceLibrary.prototype.loading=function(){
   var loading=document.getElementById('loading');
		loading.style.display='none';
	var displayloading=document.getElementById('source');
		displayloading.style.display='block';
	state=5;	
}

 
UserInterfaceLibrary.prototype.reziseIFrame=function(){

	try{
		parent.refactors();
	}
	catch(e){
		refactors();		
	}
}  
		