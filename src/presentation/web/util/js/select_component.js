function esta(formu,valor,select) {
	enc=false;
	if(formu.elements[select].options.length==0){
		return false;
	}
	else{	
		for(this.i=0;this.i<formu.elements[select].options.length;this.i++)
			if(formu.elements[select].options[this.i].value==valor) enc=true;
		return enc;
	}			
}			  

function pongo(formu,orig,dest) {
  op = formu.elements[orig];
    
  if(op.selectedIndex!=-1){
  		nombre=op.options[op.selectedIndex].text;
		valor=op.options[op.selectedIndex].value;
		cant2=formu.elements[dest].options.length;
		if(!esta(formu,op.options[op.selectedIndex].value,dest))
		 formu.elements[dest].options[cant2]=new Option(nombre,valor);
	}
}

function pongoAll(formu,orig,dest) {
    var op = formu.elements[orig];
  	var tam=formu.elements[orig].options.length;
  	var i=0;
  	for(i=0;i<tam;i++){
  		text=op.options[i].text;
  		valor=op.options[i].value;
		if(!esta(formu,valor,dest)){
		     formu.elements[dest].options[formu.elements[dest].options.length]=new Option(text,valor);
		}     
	}
}

function sacoAll2(formu,orig,dest) {
	var op = formu.elements[orig];
  	var tam=op.options.length;
  	var i=0;
  	while( i < tam)
  	{
  		valor=op.options[i].value;
	  	if(esta(formu,valor,dest)){
	 	      sacoD(formu,dest,valor);
		 }   
	 	 i=i+1;
	}
}

function sacoD(formu,select,valor){
	
	var j=0;
	var n=0;
	var ok=false;
	while((n < formu.elements[select].options.length+1)&&!ok){
		if(formu.elements[select].options[n].value==valor){
			j=n;
			ok=true;
		}
		n=n+1;
	}
	var i=0;
	for(i=j;i<formu.elements[select].options.length-1;i++){
		formu.elements[select].options[i].text=formu.elements[select].options[i+1].text;
		formu.elements[select].options[i].value=formu.elements[select].options[i+1].value;
	}
	formu.elements[select].options.length=formu.elements[select].options.length-1;
	
}


function saco(formu,val) {
	if(formu.elements[val].selectedIndex!=-1){
		var i=0;
		for(i=formu.elements[val].selectedIndex;i<formu.elements[val].options.length-1;i++){
			formu.elements[val].options[i].text=formu.elements[val].options[i+1].text;
			formu.elements[val].options[i].value=formu.elements[val].options[i+1].value;
		}
		formu.elements[val].options.length=formu.elements[val].options.length-1;
	}
}

function sacoAll(formu,val) {		  
	formu.elements[val].options.length=0;
}

function createListPrs(){
	var lst=frm.sltPrs.options;
	for(i=0;i<lst.length;i++){
		frm.selectPrs.value+=lst[i].value+"|";
	}
	
}

function addItem(formu,value,selectDest){

	if(value!=null&&value!=""&&!esta(formu,value,selectDest)){
		var selDest=formu.elements[selectDest];
		selDest.options[selDest.options.length]=new Option(value,value);	
	}
}

function cambio(){
	var divi = document.forms[2].division.options[document.forms[2].division.selectedIndex].text;
	var list = document.forms[2].elements[divi];
	document.forms[2].elements['subDivision'].options.length=list.options.length;
	for(i=0; i < list.length; i++) {
		document.forms[2].elements['subDivision'].options[i].value = list.options[i].value;
		document.forms[2].elements['subDivision'].options[i].text = list.options[i].text;
	}
}
