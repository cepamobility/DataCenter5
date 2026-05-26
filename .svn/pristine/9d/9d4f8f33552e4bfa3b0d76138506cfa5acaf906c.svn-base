/************************************************************************
	this function prepared a list of checkboxs names separated by "<" 
	returned in window attribute name "checkboxParamList".
************************************************************************/

function preparedCheckboxParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.checkboxParamList.value=valueCheckboxParamList;
		if(valueCheckboxParamList.length>0){
			return true;
		}
		else{
			return false;
		}
}


	function makeList(formChecks,formSubmit){
		var list="";		
		
		var elements=formChecks.elements;
		for(i = 0; i < elements.length; i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(list==""){
						list=element.value;
					}else{
						list=element.value+"<"+list;
					}	
				}	
			} 		
		}
		formSubmit.checkboxParamList.value=list;
		if(list.length > 0){
			return true;
		}else{
			return false;
		}
}

/***********************************************************************
	
	

***********************************************************************/