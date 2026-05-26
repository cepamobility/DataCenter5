// Javascript for events create

	function onSelectEvent(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdEvent");
  		var input_month = document.getElementById("monthIdEvent");
  		var input_year = document.getElementById("yearIdEvent");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}

	function onSelectLote(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
		// var input_day = document.getElementById("dayIdFine");
  		var input_month = document.getElementById("monthIdLote");
  		var input_year = document.getElementById("yearIdLote");
  		var date4 = new Date(time);
  		// input_day.value = date4.print("%d");
  		input_month.value = date4.print("%m");
  		input_year.value= parseInt(date4.print("%Y"),10);
  	}
	
	function onSelectEnsurance(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
		var input_day = document.getElementById("dayIdEnsurance");
  		var input_month = document.getElementById("monthIdEnsurance");
  		var input_year = document.getElementById("yearIdEnsurance");
  		var date4 = new Date(time);
  		input_day.value = date4.print("%d");
  		input_month.value = date4.print("%m");
  		input_year.value= parseInt(date4.print("%Y"),10);
  	}

	function localOnLoad(){
		form = document.forms[2];
		var corpId = form.elements['corpId'];
		if (corpId.value=="4"){
			changeEvents(form,2,'collision');
		}else{
			changeEvents(form,1,'collision');
		}
	}
	function changeVehicleCount(formName){
		value = document.forms[formName].vehiclecount.value;
		for(i = 1; i <= value; i++) {
			document.getElementById('vehicle'+new String(i)).style.display = 'block';
		}
		for(i = new Number(value)+1; i < 6; i++) {
			document.getElementById('vehicle'+new String(i)).style.display = 'none';
		}		
		refactor.refactorResto(300);		
	}
	function changePedestrianCount(formName){
		value = document.forms[formName].pedestriancount.value;
		for(i = 1; i <= value; i++) {
			document.getElementById('pedestrian'+new String(i)).style.display = 'block';
		}
		for(i = new Number(value)+1; i < 6; i++) {
			document.getElementById('pedestrian'+new String(i)).style.display = 'none';
		}
		refactor.refactorResto(300);		
	}
	function nodata(form) {
		form.elements['hour'].disabled = form.elements['nodatahour'].checked;
		form.elements['minute'].disabled = form.elements['nodatahour'].checked;
		if(form.elements['nodatahour'].checked) {
			form.elements['hour'].value = '';
			form.elements['minute'].value = '';
		}
	}
	function setnodata(form) {
		form.elements['way'].options.selectedIndex = 1;
		form.elements['direction'].options.selectedIndex = 1;
		form.elements['control'].options.selectedIndex = 1;
		form.elements['climate'].options.selectedIndex = 1;
		form.elements['pavement'].options.selectedIndex = 1;
		form.elements['condition'].options.selectedIndex = 1;
		form.elements['lighting'].options.selectedIndex = 1;
	}

	
	function changeClasification(form,value,element) {
		var options = form.elements[element];
		for(i = 0; i < options.length; i++) {
			if(options[i].value == 'datatype.collisionclasification.incident') {
				options[i].disabled = (value == 1);
				options[i].checked = (value == 2);
			}else{
				options[i].disabled = (value == 2);
			}
			if (value==2){
				options[i].value==-1;
			}
		}
	}

	// GUS, cambia el estado de los elementos dentro de un div
	// de habilitados a inhabilitados, para no tenerlos en cuenta o si en 
	// EventForm
	function doubleCabinCimsa() {
		
    	if ($('input[name=doubleCabin]').is(':checked')) {

    		$('#onlyTruckImage :input').attr('disabled', true);
	   		$("#onlyTruckImage").css("display", "none");         	

        	$('#doubleCabinImage :input').removeAttr('disabled');
       		$("#doubleCabinImage").css("display", "block");        	
        	
    	} else {

    		$('#onlyTruckImage :input').removeAttr('disabled');
    		$("#onlyTruckImage").css("display", "block");

    		$('#doubleCabinImage :input').attr('disabled', true);          	        	
        	$("#doubleCabinImage").css("display", "none");
        	      	
    	}   
	}
	// function doubleCabinCimsa(){
	//	var form = document.forms['EventForm'];
	//	var checklist = form.elements['doubleCabin'];

	//	if (checklist.checked){
			// http://www.quirksmode.org/css/display.html
	//		document.getElementById('onlyTruckImage').style.display = "none";
	//		document.getElementById('doubleCabinImage').style.display = "block";			
	//	}else{
	//		document.getElementById('onlyTruckImage').style.display = "block";
	//		document.getElementById('doubleCabinImage').style.display = "none";
	//	}
		
		// for (i = 1; i<= 41; i++){
		//	form.elements['damage'+i].checked = false;
		//	}
		
	// }
	
	
	function changeEventsClasification(count,form,change) {
		var options = form.elements[change];
		
		for(i = 0; i < options.length; i++) {
				options[i].disabled = true;
				
				if(count==1){
					if((options[i].value == 'datatype.accidentclasification.ourdriverfailedyield')||(options[i].value == 'datatype.accidentclasification.ourdriverhitrear')||
				  		(options[i].value == 'datatype.accidentclasification.otherpartyfailedyield')||(options[i].value == 'datatype.accidentclasification.otherpartyhitrear')||
						(options[i].value == 'datatype.accidentclasification.otherparking')||
						(options[i].value == 'datatype.accidentclasification.driverhitrun')||
						(options[i].value == 'datatype.accidentclasification.otherhitrun')||
				  		(options[i].value == 'datatype.accidentclasification.other')) {
						options[i].disabled = false;
					}else{
						options[i].checked = false;
					}
				}else if (count===2){
					if((options[i].value == 'datatype.accidentclasification.evasivemaneuver')||(options[i].value == 'datatype.accidentclasification.ourdriverparkingbacking')||
					   	(options[i].value == 'datatype.accidentclasification.other')||
						(options[i].value == 'datatype.accidentclasification.observeclearence')||
						(options[i].value == 'datatype.accidentclasification.skiddedslidded')) {
						options[i].disabled = false;
					}else{
						options[i].checked = false;
					}
				}else if (count==3){
					if((options[i].value == 'datatype.accidentclasification.offroad') || (options[i].value == 'datatype.accidentclasification.rollover') ||
				  		(options[i].value == 'datatype.accidentclasification.other')) {
						options[i].disabled = false;
					}else{
						options[i].checked = false;
					}
				}else if (count==4){
					if(options[i].value == 'datatype.accidentclasification.atrop'){
						options[i].checked = true;
						options[i].disabled = false;
					}else
					{
						options[i].checked = false;
						options[i].disabled = true;					
					}
				}else if (count==5){
					if(options[i].value == 'datatype.collisionclasification.contact'){
						options[i].checked = true;
						options[i].disabled = false;
					}else{
						options[i].checked = false;
						options[i].disabled = true;					
					}
				}else if (count==6){
					if(options[i].value == 'datatype.collisionclasification.companyvehicles'){
						options[i].checked = true;
						options[i].disabled = false;
					}else{
						options[i].checked = false;
						options[i].disabled = true;					
					}
				}else if (count==7){
					if(options[i].value == 'datatype.collisionclasification.nonemployee'){
						options[i].checked = true;
						options[i].disabled = false;
					}else{
						options[i].checked = false;
						options[i].disabled = true;					
					}
				}else if (count==8){
					if(options[i].value == 'datatype.incidentclasification.rightparked'){
						for(j=i; j < options.length; j++){
							options[j].disabled = false;
						}
						break;
					}else{
						options[i].checked = false;
					}
					
				}else{
					options[i].checked = false;
				}
		}
	}
	
	function changeEventClasificationType(form,value) {
		form.clasificationType.value = value;
	}
	
	function changeEvents(form,flag,element,change) {
		if (flag!=1){
			var options = form.elements[element];
			var checked = 0;
			for(i = 0; i < options.length; i++) {
					if(options[i].value == 'datatype.collisionclasification.betweenmotionvehicles' && options[i].checked) {
						changeEventsClasification(1,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.fixedobject' && options[i].checked){
						changeEventsClasification(2,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.noncollision' && options[i].checked){
						changeEventsClasification(3,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.pedestrian' && options[i].checked){
						changeEventsClasification(4,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.contact' && options[i].checked){
						changeEventsClasification(5,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.companyvehicles' && options[i].checked){
						changeEventsClasification(6,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.nonemployee' && options[i].checked){
						changeEventsClasification(7,form,change);
						checked=1;
					}else if(options[i].value == 'datatype.collisionclasification.incident' && options[i].checked){
						changeEventsClasification(8,form,change);
						checked=1;
					}
		   }
		   if (checked==0){
						changeEventsClasification(45,form,change);
			}
	   }
	}
	
	
	function driverConsequences(form,value) {
		if((form.elements['injured'].checked)&& (form.elements['fatalities'].checked)&&(value == 2)) {
				form.elements['fatalities'].click();
				
				
		}else if((form.elements['fatalities'].checked)&&(form.elements['injured'].checked)&&(value == 1)){
				form.elements['injured'].click();
				
		}
		
	}
	
