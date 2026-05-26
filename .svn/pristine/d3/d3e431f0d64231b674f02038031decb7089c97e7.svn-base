<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.fleet.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.fleet.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.util.Filter"
 	import="presentation.translator.web.util.*"
 	import="org.apache.struts.action.*"
 	import="communication.administration.user.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
	String period;String year;String id;String realPeriod;
	int fleetAccidents,fleetIncidents,fleetNearmiss,eventAccidents,eventIncidents;
	UserSessionData userSessionData;
%>
<%
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.FLEET_INSERT_DATA);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		communication.util.Filter filter = userSessionData.getFilter();
		int corporationId = sys.getCorporationId();
		
		id = (Util.empty(request.getParameter("fleet"))) ? (String)session.getAttribute("fleet") : request.getParameter("fleet");
	    period = (Util.empty(request.getParameter("period"))) ? (String)session.getAttribute("period") : request.getParameter("period");
	    year = (Util.empty(request.getParameter("year"))) ? (String)session.getAttribute("year") : request.getParameter("year");
		
	    String link = "fleet/InsertColective.jsp?fleet="+id+"&period="+period+"&year="+year;
	    userSessionData.setUrl(link);
		session.setAttribute("url",link);

		Collection fleetAccidentTotals = new LinkedList();
		Collection fleetIncidentTotals = new LinkedList();
		FleetReportFacade fleetReport = sys.getFleetReportFacade();
		FleetData data = fleetReport.findFleetByPrimaryKey(new Integer(id),userSessionData);
		if(data != null) {
		
			boolean quarter = data.getPeriod().equals(FleetData.BY_QUARTER);
		    fleetAccidents = 0;
		    fleetIncidents = 0;
		    fleetNearmiss = 0;
		    eventAccidents = 0;
		    eventIncidents = 0;
		    
			messages = sys.getText();
			String title = messages.getText("fleet.general.management")+" "+data.getName();
	
			if(data.getPeriod().intValue() == FleetData.BY_QUARTER.intValue()) {
			    realPeriod = String.valueOf((Integer.parseInt(period) + 2) / 3);
			}
		    title += (data.getPeriod().intValue() == FleetData.BY_QUARTER.intValue()) ? " Q"+realPeriod+" - "+year : " "+messages.getText("generic.month."+period)+" - "+year; 

			DataTypeReportFacade dataTypeReport =sys.getDataTypeReportFacade(); 
			OptionItem emptyObject = new OptionItem(messages.getText("generic.messages.select"),"");
	
			Collection collisions = dataTypeReport.findAll(DataTypeConstants.ACCIDENT_CLASIFICATION,userSessionData);
		    Collection collisionsDT = new LinkedList();
		    collisionsDT.add(emptyObject);
		    Iterator it;
		    // Si no es J&J muestro todas las clasificaciones de accidente,
		    // Si es J&J las clasificaciones de accidente dependen de las clasificaciones de colisiones
		    if (corporationId != CorporationData.JJ){ 
			    it = collisions.iterator();
			    while(it.hasNext()) {
			        AccidentClasificationDataType collision = (AccidentClasificationDataType)it.next();
		    	    collisionsDT.add(new OptionItem(messages.getText(collision.getResourceBundleId()),collision.getResourceBundleId()));
			    }
		    }
		    session.setAttribute("accidents",collisionsDT);
		    
			// Clasificacion de Colisiones - pra J&J
			Collection collisionsClass = dataTypeReport.findAll(DataTypeConstants.COLLISION_CLASIFICATION,userSessionData);
			List collisionsClassDT = new LinkedList();
			collisionsClassDT.add(emptyObject);
			it = collisionsClass.iterator();
			while (it.hasNext()){			    
			    CollisionClasificationDataType accident = (CollisionClasificationDataType)it.next();
			    // Si la colision no es incidente ni colision con vehiculo detenido pongo la opcion:
				if (!accident.getResourceBundleId().equals("datatype.collisionclasification.incident") &&
					!accident.getResourceBundleId().equals("datatype.collisionclasification.stopvehicle"))	{
			    	collisionsClassDT.add(new OptionItem(messages.getText(accident.getResourceBundleId()),accident.getResourceBundleId()));
				}
			}
			//Collections.sort(collisionsClassDT);
			session.setAttribute("collisions",collisionsClassDT);
			// Fin Clasificacion de Colisiones - pra J&J

	
			Collection incidents = dataTypeReport.findAll(DataTypeConstants.INCIDENT_CLASIFICATION,userSessionData);
		    Collection incidentsDT = new LinkedList();
		    incidentsDT.add(emptyObject);
		    it = incidents.iterator();
		    while(it.hasNext()) {
		        IncidentClasificationDataType incident = (IncidentClasificationDataType)it.next();
		        incidentsDT.add(new OptionItem(messages.getText(incident.getResourceBundleId()),incident.getResourceBundleId()));
		    }
		    session.setAttribute("incidents",incidentsDT);
	
			Collection consequences = dataTypeReport.findAll(DataTypeConstants.FLEET_CONSEQUENCE,userSessionData);
		    Collection consequencesDT = new LinkedList();
		    consequencesDT.add(emptyObject);
		    it = consequences.iterator();
		    while(it.hasNext()) {
		        FleetConsequenceDataType consequence = (FleetConsequenceDataType)it.next();
		        consequencesDT.add(new OptionItem(messages.getText(consequence.getResourceBundleId()),consequence.getResourceBundleId()));
		    }
		    session.setAttribute("consequences",consequencesDT);
		    
		    session.setAttribute("types",new DataTypeUtil(sys).getVehicleType(true,true,false));
		    
			Collection fuelTypes = dataTypeReport.findAll(DataTypeConstants.TYPEFUEL,userSessionData);
		    Collection fuelTypeDT = new LinkedList();
		    fuelTypeDT.add(emptyObject);
		    it = fuelTypes.iterator();
		    while(it.hasNext()) {
		        TypeFuelDataType fuelType = (TypeFuelDataType)it.next();
		        fuelTypeDT.add(new OptionItem(messages.getText(fuelType.getResourceBundleId()),fuelType.getResourceBundleId()));
		    }
		    session.setAttribute("fuels",fuelTypeDT);

		    String[] labels = filter.getLastLevelLabels();
		    String[] divOrgLabels = filter.getDivOrgLabels();
	
		    Iterator it1 = data.getDivisions().iterator();
	        Hashtable divGeoItems = new Hashtable();
		    while(it1.hasNext()) {
		        FleetDivisionData division = (FleetDivisionData)it1.next();
		        GeoLabelData geolbl = division.getCountry();
		        GeoItemData geoItemData1 = (GeoItemData)geolbl.getItemIterator().next();
		        GeoItemData geoItemData2 = null;
	            GeoItemData geoItemData3 = null;
	            if(geoItemData1.getSubItems() != null && geoItemData1.getSubItems().getItems() != null && geoItemData1.getSubItems().getItems().size() > 0) {
	                geoItemData2 = (GeoItemData)geoItemData1.getSubItems().getItemIterator().next();
	                if(geoItemData2.getSubItems() != null && geoItemData2.getSubItems().getItems() != null && geoItemData2.getSubItems().getItems().size() > 0) {
	                    geoItemData3 = (GeoItemData)geoItemData2.getSubItems().getItemIterator().next();
	                }
	            }
		        switch(filter.getGeoLevelCount()) {
		            case 1:
						if(!divGeoItems.containsKey(String.valueOf(geoItemData1.getId()))) {
							GeoItemData item = filter.getItem(geoItemData1.getId());				                
						    divGeoItems.put(String.valueOf(item.getId()),item.getName());
						}
			            break;
		            case 2:
	        	        Iterator items = filter.getLastLevelItems(2,String.valueOf(geoItemData1.getId()),null);
	        	        while(items.hasNext()) {
	        	            GeoItemData item = (GeoItemData)items.next();
	        	            if(geoItemData2 != null) {
	        	                if(item.getId().equals(geoItemData2.getId())) {
									if(!divGeoItems.containsKey(String.valueOf(item.getId()))) {
							            divGeoItems.put(String.valueOf(item.getId()),item.getName());
									}
	        	                }
	        	        	}else {
								if(!divGeoItems.containsKey(String.valueOf(item.getId()))) {
						            divGeoItems.put(String.valueOf(item.getId()),item.getName());
								}
	        	        	}
	        	        }
			            break;
		            case 3:
	                    String id2 = (geoItemData2 == null) ? null : String.valueOf(geoItemData2.getId());
	        	        items = filter.getLastLevelItems(3,String.valueOf(geoItemData1.getId()),id2);
	        	        while(items.hasNext()) {
	        	            GeoItemData item = (GeoItemData)items.next();
	            	        if(geoItemData3 != null) {
	        	                if(item.getId().equals(geoItemData3.getId())) {
									if(!divGeoItems.containsKey(String.valueOf(item.getId()))) {
							            divGeoItems.put(String.valueOf(item.getId()),item.getName());
									}
								}
	            	        }else {
								if(!divGeoItems.containsKey(String.valueOf(item.getId()))) {
						            divGeoItems.put(String.valueOf(item.getId()),item.getName());
								}
	            	        }
	        	        }
			            break;
		        }
		    }
	        Collection divs = new LinkedList();
	        Enumeration en = divGeoItems.keys();
	        while(en.hasMoreElements()) {
	            String key = (String)en.nextElement();
			    divs.add(new OptionItem(messages.getText((String)divGeoItems.get(key)),key));
	        }
		    session.setAttribute("divGeo",divs);
	
	        Collection uns = new LinkedList();
	
		    DivOrgAttribute[] attrs = filter.getDivOrgsAttrs();
		    for(int i = 0; i < filter.getDivOrgCount(); i++) {
		        Hashtable divOrgItems1 = new Hashtable();
		        Hashtable divOrgItems2 = new Hashtable();
		        Hashtable divOrgItems3 = new Hashtable();
			    it1 = data.getDivisions().iterator();
			    while(it1.hasNext()) {
			        FleetDivisionData division = (FleetDivisionData)it1.next();
			        LabelItemData lbl = division.getDivOrgs()[i];
			        ItemData itemData1 = (ItemData)lbl.getItemIterator().next();
			        ItemData itemData2 = null;
	                ItemData itemData3 = null;
	                if(itemData1.getSubItems() != null && itemData1.getSubItems().getItems() != null && itemData1.getSubItems().getItems().size() > 0) {
	                    itemData2 = (ItemData)itemData1.getSubItems().getItemIterator().next();
	                    if(itemData2.getSubItems() != null && itemData2.getSubItems().getItems() != null && itemData2.getSubItems().getItems().size() > 0) {
	                        itemData3 = (ItemData)itemData2.getSubItems().getItemIterator().next();
	                    }
	                }
					if(!divOrgItems1.containsKey(String.valueOf(itemData1.getId()))) {
						ItemData item = filter.getDivOrgItem(i,1,itemData1.getId());				                
						if(item != null) {
				            divOrgItems1.put(String.valueOf(item.getId()),item.getName());
						}
					}
					if(filter.getDivOrgLevel(i) > 1) {
		                if(itemData2 != null) {
							ItemData item2 = filter.getDivOrgItem(i,2,itemData2.getId());				                
							if(!divOrgItems2.containsKey(String.valueOf(item2.getId()))) {
					            divOrgItems2.put(String.valueOf(item2.getId()),item2.getName());
							}
		                }else {
		        	        Iterator items = filter.getLastLevelItems(2,i,String.valueOf(itemData1.getId()),null);
		        	        while(items.hasNext()) {
		        	            ItemData item2 = (ItemData)items.next();
								if(!divOrgItems2.containsKey(String.valueOf(item2.getId()))) {
						            divOrgItems2.put(String.valueOf(item2.getId()),item2.getName());
								}
		        	        }
		                }
					}
					if(filter.getDivOrgLevel(i) > 2) {
		                if(itemData3 != null) {
							ItemData item3 = filter.getDivOrgItem(i,3,itemData3.getId());				                
							if(!divOrgItems3.containsKey(String.valueOf(item3.getId()))) {
					            divOrgItems3.put(String.valueOf(item3.getId()),item3.getName());
							}
		                }else {
		                    String id2 = (itemData2 == null) ? null : String.valueOf(itemData2.getId());
		        	        Iterator items = filter.getLastLevelItems(2,i,String.valueOf(itemData1.getId()),id2);
		        	        while(items.hasNext()) {
		        	            ItemData item3 = (ItemData)items.next();
								if(!divOrgItems3.containsKey(String.valueOf(item3.getId()))) {
						            divOrgItems3.put(String.valueOf(item3.getId()),item3.getName());
								}
		        	        }
		                }
					}
			    }
	            divs = new LinkedList();
	            en = divOrgItems1.keys();
	            while(en.hasMoreElements()) {
	                String key = (String)en.nextElement();
	    		    divs.add(new OptionItem((String)divOrgItems1.get(key),key));
	            }
			    session.setAttribute("divOrg"+String.valueOf(i+1)+"1",divs);
	            divs = new LinkedList();
	            en = divOrgItems2.keys();
	            while(en.hasMoreElements()) {
	                String key = (String)en.nextElement();
	    		    divs.add(new OptionItem((String)divOrgItems2.get(key),key));
	            }
			    session.setAttribute("divOrg"+String.valueOf(i+1)+"2",divs);
	            divs = new LinkedList();
	            en = divOrgItems3.keys();
	            while(en.hasMoreElements()) {
	                String key = (String)en.nextElement();
	    		    divs.add(new OptionItem((String)divOrgItems3.get(key),key));
	            }
			    session.setAttribute("divOrg"+String.valueOf(i+1)+"3",divs);
		    }
			int[][] divOrgs = filter.getAllDivOrgs();
			String[] lbls = filter.getDivOrgLabels();
			Collection accidentsFleet = new LinkedList();
			Collection incidentsFleet = new LinkedList();
			Collection nearmissFleet = new LinkedList();
			Collection accidentsEvent = new LinkedList();
			Collection incidentsEvent = new LinkedList();
			
%>
	
<script language="JavaScript" type="text/javascript">
function deleteAccIn(form){

	var elements=form.elements;
	var length=elements.length;
	var i;
	var list="";
	var separator="<";
		
	for(i=0;i<length;i++){
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
	form.list.value=list;
	if(list.length>0){
		form.submit();
	}
}

function changeColision(chosenSeleccion1, seleccion2){

// Opciones:
// "datatype.collisionclasification.betweenmotionvehicles"  - Colisión entre vehículos
// "datatype.collisionclasification.fixedobject" - Colisión con objeto fijo
// "datatype.collisionclasification.noncollision" - Accidente sin colisión
// "datatype.collisionclasification.pedestrian" - Lesiones a peatones
// "datatype.collisionclasification.contact" - Contacto con animales / piedras
// "datatype.collisionclasification.companyvehicles" - 
// "datatype.collisionclasification.nonemployee" - Conducidos por Terceros


	// Borro las opciones, menos la primera
	seleccion2.options.length = 1;
		
	// Colision entre vehiculos:
	if (chosenSeleccion1 == "datatype.collisionclasification.betweenmotionvehicles")
	{
		// Nuestro conductor no cedió el paso
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.ourdriverfailedyield")%>',
		'datatype.accidentclasification.ourdriverfailedyield');
		// Nuestro conductor choca de atras
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.ourdriverhitrear")%>',
		'datatype.accidentclasification.ourdriverhitrear');		
		// Tercero no cedio el paso
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.otherpartyfailedyield")%>',
		'datatype.accidentclasification.otherpartyfailedyield');
		// Tercero choca de atras
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.otherpartyhitrear")%>',
		'datatype.accidentclasification.otherpartyhitrear');
		// Otro estacionando
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.otherparking")%>',
		'datatype.accidentclasification.otherparking');		
		// Driver hit & run
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.driverhitrun")%>',
		'datatype.accidentclasification.driverhitrun');		
		// Otro hit & run
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.otherhitrun")%>',
		'datatype.accidentclasification.otherhitrun');		
		// Otro
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.other")%>',
		'datatype.accidentclasification.other');		
	
	}else if (chosenSeleccion1 == "datatype.collisionclasification.fixedobject"){
		// Colisión con objeto fijo
		
		// Nuestro conductor estacionando / en retroceso
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.ourdriverparkingbacking")%>',
		'datatype.accidentclasification.ourdriverparkingbacking');
		// Otro
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.other")%>',
		'datatype.accidentclasification.other');	
		// Maniobra Evasiva
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.evasivemaneuver")%>',
		'datatype.accidentclasification.evasivemaneuver');	
		// Maniobra Evasiva
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.observeclearence")%>',
		'datatype.accidentclasification.observeclearence');	
		// Maniobra Evasiva
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.skiddedslidded")%>',
		'datatype.accidentclasification.skiddedslidded');	
	}else if (chosenSeleccion1 == "datatype.collisionclasification.noncollision"){
		// Accidente sin colisión
		
		// Patinaje, Derrape
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.rollover")%>',
		'datatype.accidentclasification.rollover');
		// Salida de pista
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.offroad")%>',
		'datatype.accidentclasification.offroad');
		// Otro
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.other")%>',
		'datatype.accidentclasification.other');
		
	}else if (chosenSeleccion1 == "datatype.collisionclasification.pedestrian"){
		// Lesiones a peatones
		
		// Lesiones a peatones
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.accidentclasification.atrop")%>',
		'datatype.accidentclasification.atrop');
		
	}else if (chosenSeleccion1 == "datatype.collisionclasification.contact"){
		// Contacto con animales / piedras
		
		// Contacto con animales / piedras
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.collisionclasification.contact")%>',
		'datatype.collisionclasification.contact');
	}else if (chosenSeleccion1 == "datatype.collisionclasification.companyvehicles"){
		// dos vehículos de la compañía que chocan 
		
		// dos vehículos de la compañía que chocan 
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.collisionclasification.companyvehicles")%>',
		'datatype.collisionclasification.companyvehicles');
	}else if (chosenSeleccion1 == "datatype.collisionclasification.nonemployee"){
		// Conducidos por Terceros
		seleccion2.options[seleccion2.options.length] = new
		Option('<%=messages.getText("datatype.collisionclasification.nonemployee")%>',
		'datatype.collisionclasification.nonemployee');
	}
	
}
</script>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<style type="text/css">	</style>
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
	<script language="javascript" type='text/javascript' >
		function confirmDelete() {
			if(confirm('<%=messages.getText("fleet.confirm.deletedivision")%>')) {
				document.forms['DeleteFleetDivisionDetailForm'].submit();
			}
		}
		function updateKms(id) {
			var form = document.forms['FleetUpdateDetailForm'];
			var newValue = form.elements['miles'+id].value * 1.609344;
			form.elements['kms'+id].value = Math.round(newValue);
		}
		function updateMiles(id) {
			var form = document.forms['FleetUpdateDetailForm'];
			var newValue = form.elements['kms'+id].value / 1.609344;
			form.elements['miles'+id].value = Math.round(newValue);
		}
		function fillVehicleCount(){
			var form = document.forms['FleetUpdateDetailForm'];
			$.getJSON("fleet/fillVehicle.jsp?fleet=<%=id%>&p=<%=period%>&y=<%=year%>",
					function(data) {
				 		var items = [];
				 		$.each( data, function( key, val ) {
				 			var elementId = 'vehicles'+key;
							form.elements[elementId].value = val;
				 		})
					}
			);
		}
		function fillEvents(){
			var form = document.forms['FleetUpdateDetailForm'];
			$.getJSON("fleet/fillEvents.jsp?f=<%=id%>&p=<%=period%>&y=<%=year%>&q=<%=quarter%>",
					function(data) {
				 		var items = [];
				 		$.each( data, function( key, val ) {
				 			form.elements['accidents'+key].value = val[0];
				 			form.elements['incidents'+key].value = val[1];
				 			if(val.length == 3)
				 				form.elements['nearmiss'+key].value = val[2];
				 		})
					}
			);
		}
		function fillOnlyVehiclesCount(){
			var form = document.forms['FleetUpdateDetailForm'];
			$.getJSON("fleet/fillVehicles.jsp?f=<%=id%>&p=<%=period%>&y=<%=year%>",
					function(data) {
				 		var items = [];
				 		$.each( data, function( key, val ) {
				 			form.elements['vehicles'+key].value = val[0];
				 		})
					}
			);
		}
		function fillMilesCount(){
			var form = document.forms['FleetUpdateDetailForm'];
			$.getJSON("fleet/fillMiles.jsp?f=<%=id%>&p=<%=period%>&y=<%=year%>",
					function(data) {
				 		var items = [];
				 		$.each( data, function( key, val ) {
				 			form.elements['miles'+key].value = val[2];
				 			form.elements['kms'+key].value = val[1];
				 		})
					}
			);
		}
		function fillMediaValues(){
			var form = document.forms['FleetUpdateDetailForm'];
			var value = document.getElementById('media').value;
			if(isNaN(value)) {
				alert('<%=messages.getText("fleet.insertcolective.wrongaverage")%>');
			} else {
				var units = document.getElementById('unit').selectedIndex;
				for(i=0;i<form.elements.length;i++){
					var name = form.elements[i].name;
					if(name.indexOf('vehicles') == 0 && !isNaN(form.elements[i].value)) {
						var newValue = form.elements[i].value * value;
						if(units == 1) {
					 		form.elements['miles'+name.substring(8)].value = newValue;
					 		form.elements['kms'+name.substring(8)].value = Math.round(newValue * 1.609344);
						}else {
					 		form.elements['kms'+name.substring(8)].value = newValue;
					 		form.elements['miles'+name.substring(8)].value = Math.round(newValue / 1.609344);
						}
					}
				}
			}
		}
		
		
	</script>
	<body bgcolor="#FFFFFF">
		<html:form action="/DeleteFleetDivisionDetail" onsubmit="return confirmDelete();">
			<html:hidden property="fleet" value='<%=id%>' />
			<html:hidden property="fromMonth" value='<%=period%>' />
			<html:hidden property="fromYear" value='<%=year%>' />
			<html:hidden property="toMonth" value='<%=period%>' />
			<html:hidden property="toYear" value='<%=year%>' />
		</html:form>
		<table border="0" align="center" cellpadding="5" cellspacing="5">
			<tr>
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+title%>' />
	  		</tr>
<%		if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ)) {
%>      		
			<tr>
				<td width="100%" valign="top" colspan="5">
					<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
					<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
						<tr>
	         				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          				<td>
								<a href="javascript:confirmDelete();" class="texts"><%=messages.getText("web.fleet.deletedetail")%></a>            			
							</td>
	         				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          				<td>
								<a href="javascript:fillEvents();" class="texts"><%=messages.getText("fleet.event.fill")%></a>            			
							</td>
						</tr>
<%		if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.ALLERGAN,CorporationData.DOW})) {
%>      		
						<tr>
	         				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          				<td>
								<a href="javascript:fillOnlyVehiclesCount();" class="texts"><%=messages.getText("web.fleet.fillvehicle")%></a>            			
							</td>
	         				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          				<td>
								<a href="javascript:fillMilesCount();" class="texts"><%=messages.getText("web.fleet.fillmiles")%></a>            			
							</td>
						</tr>
<%		}else {
%>      		
						<tr>
	         				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          				<td>
								<a href="javascript:fillVehicleCount();" class="texts"><%=messages.getText("web.fleet.fillvehicle")%></a>            			
							</td>
	         				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          				<td>
								<table>
									<tr>
										<td class="texts"><%=messages.getText("generic.messages.average")%>:</td>
										<td><input type="text" name="media" id="media" class="input"></td>
										<td>
											<select name="unit" id="unit" class="input">
												<option value="0"><%=messages.getText("datatype.distanceunit.kilometers")%></option>
												<option value="1"><%=messages.getText("datatype.distanceunit.miles")%></option>
											</select>
										</td>
										<td><input type="button" value="<%=messages.getText("generic.messages.load")%>" class="input" onclick="fillMediaValues()"></td>
									</tr>
								</table>
							</td>
						</tr>
<%		}
%>      		
					</table>
					<jsp:include page="../component/utilitybox3bottom.jsp"/>
				</td>
			</tr>
<%		}
%>      		
	      	<tr>
    	    	<td width="95%" colspan="3" align="center">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.detail")%>' />
					<html:form action="/Fleetupdatedetail">
						<html:hidden property="fleet" value='<%=String.valueOf(data.getId())%>'/> 									
						<html:hidden property="period" value='<%=period%>'/> 									
						<html:hidden property="year" value='<%=year%>'/> 									
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="guide2" width="100" align="center"><%=messages.getText("generic.fleet.country")%></td>				            				
<%			int columns = 2;
			if(corporationId == CorporationData.NOVARTIS) {
				String[] principalLabels = filter.getDivOrgLabels();
				for(int i = 0; i < principalLabels.length; i++) {
				    if(i != 3 && principalLabels[i] != null) {
				        columns++;
%>								<td class="guide2" width="100" align="center">
									<%=messages.getText(principalLabels[i]) %>
								</td>				    
<%					}
				}
			}else {
				
				if((corporationId == CorporationData.BAYER) && id.equals("9554")){
					String[] principalLabels = filter.getDivOrgLabels();
					for(int i = 0; i < 6; i++) {
				    
				        columns++;
%>								
						<td class="guide2" width="100" align="center">
							<!-- Correccion Forestales: <% //messages.getText(lbls[(index[0]-1)*3+(index[1]-1)])%> -->
							<%=messages.getText(principalLabels[i]) %>
						</td>				    
<%				
					}
				}else{
					String[] principalLabels = filter.getPrincipalLabels();
					for(int i = 0; i < principalLabels.length; i++) {
					    int[] index = divOrgs[i];
					    if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
					        columns++;
	%>								<td class="guide2" width="100" align="center">
										<!-- Correccion Forestales: <% //messages.getText(lbls[(index[0]-1)*3+(index[1]-1)])%> -->
										<%= messages.getText(principalLabels[i]) %>
									</td>				    
	<%					}
					}
				}
				
			}
%>								<td class="guide2" width="100"  align="center"><%=messages.getText("fleet.division.vehicletype")%></td>
								<td class="guide2" width="100"  align="center"><%=messages.getText("fleet.division.vehiclecount")%></td>
<%		if(!sys.isForestalUnified() && !sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>								<td class="guide2" width="100" align="center"><%=messages.getText("fleet.division.miles")%></td>
<%		}
%>								<td class="guide2" width="100" align="center"><%=messages.getText("fleet.division.kms")%></td>
								<td class="guide2" width="100" align="center"><%=messages.getText("fleet.division.accidentcount")%></td>
								<td class="guide2" width="100" align="center"><%=messages.getText("fleet.division.incidentcount")%></td>
<%		if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DPA})) {
%>								<td class="guide2" width="100" align="center"><%=messages.getText("fleet.division.nearmisscount")%></td>
<%		}
		if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>								<td class="guide2" width="80" align="center"><%=messages.getText("fleet.division.apmk")%></td>
								<td class="guide2" width="80" align="center"><%=messages.getText("fleet.division.ipmk")%></td>
<%		}else {
%>								<td class="guide2" width="80" align="center"><%=messages.getText("fleet.division.apmm")%></td>
<%			if(sys.isBrasilCorporation()) {
%>								<td class="guide2" width="80" align="center"><%=messages.getText("fleet.division.apmk")%></td>								
<%			}
		}
%>							</tr>
<%		it = data.getDivisionsIterator();
		Hashtable details = fleetReport.findFleetDetail(new Integer(id),new Integer(period),new Integer(year),userSessionData);
		System.out.println("-----------------"+data.getDivisions().size());
		int totalCount = 0;
		int totalMiles = 0;
		boolean lineone = true;
		while(it.hasNext()) {
		   	FleetDivisionData division = (FleetDivisionData)it.next(); 
		   	String[] divisionLabels = division.getDivOrgLabels();
		   	Integer[] divisionIds = division.getDivOrgIds();
		   	FleetDivisionDetailData detail = (details.containsKey(division.getId())) ? (FleetDivisionDetailData) details.get(division.getId()) : null;
		   	if(!(detail == null && division.getExclusionDate() != null)) {
			   	if(detail != null) {
			   	    Object[] accidentData = {null,null,null,null,null,null,detail.getAccidents()};
			   	    Object[] incidentData = {null,null,null,null,null,null,detail.getIncidents()};
			   	    Object[] nearmissData = {null,null,null,null,null,null,detail.getNearmiss()};
			   	    int cantDiv = 3;
			   	    //int cantDiv =  sys.getUserSessionData().getDivOrgCount();
			   	    for(int i = 0; i < cantDiv; i++) {
			   	        int indexValue = -1;
			   	        if(divisionLabels[i* cantDiv +2] != null) {
				   	         indexValue = i* cantDiv +2;
			   	        }else if(divisionLabels[i* cantDiv +1] != null) {
				   	         indexValue = i* cantDiv +1;
			   	        }else if(divisionLabels[i* cantDiv] != null) {
				   	         indexValue = i* cantDiv;
			   	        }
			   	        if(indexValue != -1) {
					        accidentData[i] = divisionLabels[indexValue];
					        accidentData[i+ cantDiv] = divisionIds[indexValue];
					        incidentData[i] = divisionLabels[indexValue];
					        incidentData[i+ cantDiv] = divisionIds[indexValue];
					        if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DPA})) {
					        	nearmissData[i] = divisionLabels[indexValue];
					        	nearmissData[i+ cantDiv] = divisionIds[indexValue];
					        }
			   	        }
			   	    }
					accidentsFleet.add(accidentData);
					incidentsFleet.add(incidentData);
					totalCount += detail.getVehicles().intValue();
					totalMiles += detail.getMiles().intValue();
					fleetAccidents += detail.getAccidents().intValue();
					fleetIncidents += detail.getIncidents().intValue();
					if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DPA})) {
						fleetNearmiss += detail.getNearmiss().intValue();
			        }
			   	}    
%>							<tr>
									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(division.getCountryName())%></td>
<%				if(corporationId == CorporationData.NOVARTIS) {
					for(int i = 0; i < divisionLabels.length; i++) {
						if(i != 3 && divisionLabels[i] != null) {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center">
										<%=divisionLabels[i]%>
									</td>				    
<%						}
					}
				}else {
					if(corporationId == CorporationData.BAYER && id.equals("9554")){
						String[] principalLabels = filter.getDivOrgLabels();
						for(int i = 0; i < 6; i++) {
						    int[] index = divOrgs[i];
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center">
										<!-- Correccion Forestales: <% //divisionLabels[(index[0]-1)*3+(index[1]-1)]%> -->
										<%=divisionLabels[(index[0]-1)*3+(index[1]-1)]%>
									</td>				    
<%							
						}
					}else{
					
						String[] principalLabels = filter.getPrincipalLabels();
						for(int i = 0; i < principalLabels.length; i++) {
						    int[] index = divOrgs[i];
						    if(!(corporationId == 1 && i > 3)) {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center">
										<!-- Correccion Forestales: <% //divisionLabels[(index[0]-1)*3+(index[1]-1)]%> -->
										<%=divisionLabels[(index[0]-1)*3+(index[1]-1)]%>
									</td>				    
<%							}
						}
					}	
				}
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(division.getVehicleType().getResourceBundleId())%></td>
									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type="text" name='<%="vehicles"+String.valueOf(division.getId())%>' value='<%=Util.getFleetValue(detail,request,1,division.getId())%>' class="input" size="10"/></td>
<%		if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type="text" name='<%="miles"+String.valueOf(division.getId())%>' value='<%=Util.getFleetValue(detail,request,2,division.getId())%>' class="input" size="10" onchange="updateKms('<%=String.valueOf(division.getId())%>');"/></td>
<%		}else {
%>									<input type="hidden" name='<%="miles"+String.valueOf(division.getId())%>' value='<%=Util.getFleetValue(detail,request,2,division.getId())%>' />
<%		}
		if(!sys.isForestalUnified()) {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type="text" name='<%="kms"+String.valueOf(division.getId())%>' value='<%=Util.getFleetValue(detail,request,6,division.getId())%>' class="input" size="10" onchange="updateMiles('<%=String.valueOf(division.getId())%>');"/></td>
<%		}
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type="text" name='<%="accidents"+String.valueOf(division.getId())%>' value='<%=Util.getFleetValue(detail,request,3,division.getId())%>' class="input" size="10"/></td>
									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type="text" name='<%="incidents"+String.valueOf(division.getId())%>' value='<%=Util.getFleetValue(detail,request,4,division.getId())%>' class="input" size="10"/></td>
<%				if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DPA})) {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center">
												<input type="text" name='<%="nearmiss"+String.valueOf(division.getId())%>' 
												value='<%=Util.getFleetValue(detail,request,5,division.getId())%>' 
												class="input" size="10"/>
										</td>
<%				}
				if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
					double mvi = (detail != null && detail.getAccidents() != null && detail.getMiles() != null && detail.getMiles().intValue() != 0) ? detail.getAccidents() * 1000000 / Util.toKms(detail.getMiles().intValue()).doubleValue() : 0.0;
					double apmk = (detail != null && detail.getIncidents() != null && detail.getMiles() != null && detail.getMiles().intValue() != 0) ? detail.getIncidents() * 1000000 / Util.toKms(detail.getMiles().intValue()).doubleValue() : 0.0;
%>									<td class="<%=(lineone)?"lineone":"linetwo"%>" align="center"><%=Util.formatDouble(new Double(mvi),2)%></td>
									<td class="<%=(lineone)?"lineone":"linetwo"%>" align="center"><%=Util.formatDouble(new Double(apmk),2)%></td>								
<%				}else {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(detail != null && detail.getAccidents() != null && detail.getMiles() != null && detail.getMiles().intValue() != 0) ? Util.formatDouble(new Double(detail.getAccidents().doubleValue() * 1000000 / detail.getMiles().doubleValue()),2) : "0.00"%></td>
<%					if(sys.isBrasilCorporation()) {
%>									<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(detail != null && detail.getAccidents() != null && detail.getMiles() != null && detail.getMiles().intValue() != 0) ? Util.formatDouble(new Double(detail.getAccidents().doubleValue() * 1000000 / Util.toKms(detail.getMiles()).doubleValue()),2) : "0.00"%></td>								
<%					}
				}
%>							</tr>
	<%			lineone = !lineone;
		   	}
		}
%>							<tr height="25">
								<td class="guide3" colspan='<%=columns%>'><%=messages.getText("fleet.cost.total") %></td>
								<td class="guide3" align="center"><%=String.valueOf(totalCount)%></td>
<%		if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>								<td class="guide3" align="center"><%=String.valueOf(totalMiles)%></td>
<%		}
		if(!sys.isForestalUnified()) {
%>							<td class="guide3" align="center"><%=String.valueOf(Util.toKms(totalMiles))%></td>
<%		}
%>								<td class="guide3" align="center"><%=String.valueOf(fleetAccidents)%></td>
								<td class="guide3" align="center"><%=String.valueOf(fleetIncidents)%></td>
<%		if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.DPA})) {
%>								<td class="guide3" align="center"><%=String.valueOf(fleetNearmiss)%></td>
<%		}
		if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
			double mvi = (totalMiles == 0) ? 0.0 : fleetAccidents * 1000000 / Util.toKms(totalMiles).doubleValue();
			double apmk = (totalMiles == 0) ? 0.0 : fleetIncidents * 1000000 / Util.toKms(totalMiles).doubleValue();
%>								<td class="guide3" align="center"><%=Util.formatDouble(new Double(mvi),2)%></td>
								<td class="guide3" align="center"><%=Util.formatDouble(new Double(apmk),2)%></td>								
<%		}else {
			if(totalMiles > 0) {
%>								<td class="guide3" align="center"><%=Util.formatDouble(new Double((double)fleetAccidents * 1000000 / (double)totalMiles),2)%></td>
<%				if(sys.isBrasilCorporation()) {
%>								<td class="guide3" align="center"><%=Util.formatDouble(new Double((double)fleetAccidents * 1000000 / Util.toKms(totalMiles).doubleValue()),2)%></td>								
<%				}
			}else {
%>								<td class="guide3" align="center">0.00</td>
<%				if(sys.isBrasilCorporation()) {
%>								<td class="guide3" align="center">0.00</td>								
<%				}
			}
		}
%>							</tr>
					      	<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				      		</tr>
							<tr>
								<td class="linetwo" colspan="14" align="right">
									<html:submit value='<%=messages.getText("generic.fleet.addline")%>' styleClass="input"/>
								</td>
							</tr>
						</table>
					</html:form>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	      	<tr>
    	    	<td width="100%" colspan="3" >
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.colective.accidents")%>' />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
		   				<tr>
							<td width="100%" class="lineone" >
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.addaccidents")%>' />
									<html:form action="/Fleetaddaccident" >
										<html:hidden property="fleet" value='<%=id%>'/>
										<html:hidden property="period" value='<%=period%>'/>
										<html:hidden property="year" value='<%=year%>'/>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
<%			    for(int i = 1; i <= userSessionData.getDivOrgCount(); i++) {
    				for(int j = 1; j <= filter.getDivOrgLevel(i-1); j++) {
    				    if(!(corporationId == CorporationData.MONSANTO && i >= 2 && i == 1)) {
							String s = "divOrg"+String.valueOf(i)+String.valueOf(j);
    				        Collection divOrg = (Collection)session.getAttribute(s);
    		    			if(divOrg.size() == 1) {
%>											<html:hidden property='<%=s%>' value='<%=((OptionItem)divOrg.iterator().next()).getValue()%>'/>
<%    						}else {
%>											<tr>
												<th class="lineone" align="left">
												<!-- Correccion Forestales <% //messages.getText(divOrgLabels[(i-1)*3+(j-1)])%> -->
													<%=messages.getText(divOrgLabels[(i-1)*userSessionData.getDivOrgCount()+(j-1)])%>
													<span class="required">*</span>
												</th>				    
												<td class="lineone" align="left" >
		      										<html:select property='<%=s%>' styleClass="input" style="width:250" >
										          		<html:options collection='<%=s%>' property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>
<%    		    			}
    				    }
    				}
				}	
 			    Collection divGeoCollection = (Collection)session.getAttribute("divGeo");
				if(divGeoCollection.size() == 1) {
%>											<html:hidden property="country" value='<%=((OptionItem)divGeoCollection.iterator().next()).getValue()%>'/>
<%    			}else {
%>											<tr>
												<th class="lineone" align="left"><%=messages.getText("corporation.divgeo.countries")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="country" styleClass="input" style="width:250" >
										          		<html:options collection="divGeo" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
<%	 			}	 
		 		// Si la corporacion en J&J(cambian los accidentes) o Abbott muestro Clasificacion por Colision: 
					if(corporationId == CorporationData.JJ){
				    	session.setAttribute("accidents",Util.getCollisions(session.getAttribute("FleetAddAccidentForm"),messages));	

%>
											<!-- Clasificacion de Colision: -->
											<tr>
												<th class="lineone" align="left"><%=messages.getText("generic.event.eventdata.colisionclasification")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="colisionclasification" style="width:250"
		      										styleClass="input"  
		      										onchange="javascript:changeColision(this.options[this.selectedIndex].value, this.form.clasification)" >
		      										   		<html:options collection="collisions" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
			<%	} else if(corporationId == CorporationData.ABBOTT){ %>
			
											<!-- Clasificacion de Colision abbott: -->
											<tr>
												<th class="lineone" align="left"><%=messages.getText("generic.event.eventdata.colisionclasification")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="colisionclasification" style="width:250" styleClass="input" >
		      										   		<html:options collection="collisions" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
<% 				}%>
											<!-- Clasificacion de Accidentes: -->
											<tr>
												<th class="lineone" align="left"><%=messages.getText("generic.event.eventdata.clasification")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="clasification" styleClass="input" style="width:250" >
										          		<html:options collection="accidents" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("generic.event.consequences")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="consequence" styleClass="input"   style="width:250">
										          		<html:options collection="consequences" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.colective.accident.number")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text property="number" styleClass="input" style="width:250"/>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.colective.accident.cost")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text property="cost" styleClass="input" style="width:250"/>
												</td>
											</tr>	
											<tr>
												<td class="lineone" align="right" colspan="2">
		      										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.add")%>'/>
												</td>
											</tr>	
										</table>
									</html:form>
								<jsp:include  page="../component/utilitybox2bottom.jsp" />
							</td>
						</tr>
<%			Collection accidents = fleetReport.findFleetAccidents(new Integer(id),new Integer(period),new Integer(year),userSessionData);
			int columnCount = 4;
			if(accidents.size() > 0) {
			    FleetAccidentData accident = (FleetAccidentData)accidents.iterator().next();
%>	      				<tr>
							<jsp:include  page="../component/spaceline.jsp" />
			      		</tr>
						<tr>
							<td width="100%">
								<html:form action="/Fleetdeleteaccident">
								<html:hidden property="fleet" value='<%=String.valueOf(data.getId())%>'/> 									
								<html:hidden property="period" value='<%=period%>'/> 									
								<html:hidden property="year" value='<%=year%>'/> 									
								<html:hidden property="type" value="accident"/>
								<html:hidden property="list" />
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="guide2" width="5%">&nbsp;</td>				    
										<td class="guide2" width="13%"><%=messages.getText("generic.fleet.country")%></td>				            				
<%			for(int i = 0; i < divOrgs.length; i++) {
			    int[] index = divOrgs[i];
			    if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
			        columnCount++;
%>								<td class="guide2" width="13%" align="center"><%=messages.getText(lbls[(index[0]-1)*3+(index[1]-1)])%></td>				    
<%				}
			}
%>										<td class="guide2" ><%=messages.getText("generic.event.eventdata.clasification")%></td>
										<td class="guide2" ><%=messages.getText("generic.event.consequences")%></td>
										<td class="guide2" ><%=messages.getText("fleet.colective.accident.number")%></td>
										<td class="guide2" ><%=messages.getText("fleet.colective.accident.cost")%></td>
									</tr>
<%				it = accidents.iterator();
				int totalCost = 0;
				boolean lineone=true;
				while(it.hasNext()) {
			    	accident = (FleetAccidentData)it.next();
			   	    Object[] accidentData = {null,null,null,null,null,null,accident.getNumber()};
			   	    if(accident.getDivOrg1() != null) {
			   	        accidentData[0] = accident.getDivOrg1().getName();
			   	        accidentData[3] = accident.getDivOrg1().getId();
			   	    }
			   	    if(accident.getDivOrg2() != null) {
			   	        accidentData[1] = accident.getDivOrg2().getName();
			   	        accidentData[4] = accident.getDivOrg2().getId();
			   	    }
			   	    if(accident.getDivOrg3() != null) {
			   	        accidentData[2] = accident.getDivOrg3().getName();
			   	        accidentData[5] = accident.getDivOrg3().getId();
			   	    }
			    	eventAccidents += accident.getNumber().intValue();
				    totalCost += accident.getCost().intValue();
%>									<tr>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type='checkbox' name='<%=String.valueOf(accident.getId())%>' value='<%=String.valueOf(accident.getId())%>' ></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>'  align="center"><%=messages.getText(accident.getCountry().getName())%></td>
<%					String[][] values = accident.getData();	
					for(int i = 0; i < divOrgs.length; i++) {
			    		int[] index = divOrgs[i];
				    	if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
%>										<td class='<%=(lineone)?"lineone":"linetwo"%>'  align="center"><%=values[index[0]-1][index[1]-1]%></td>				    
<%						}
					}
					accidentsEvent.add(accidentData);
%>										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(accident.getClasification().getResourceBundleId())%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(accident.getConsequence().getResourceBundleId())%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=String.valueOf(accident.getNumber())%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=String.valueOf(accident.getCost())%></td>
									</tr>
<%					lineone = !lineone;
				}
%>									<tr>
										<td class="guide3" colspan='<%=columnCount%>'><%=messages.getText("fleet.cost.total") %></td>
										<td class="guide3" align="center"><%=String.valueOf(eventAccidents)%></td>
										<td class="guide3" align="center"><%=String.valueOf(totalCost)%></td>
									</tr>
<%				if(eventAccidents > 0) {
%>							      	<tr>
										<jsp:include  page="../component/spaceline.jsp" />
						      		</tr>
									<tr>
										<td colspan='<%=String.valueOf(columnCount+2)%>' align="right">
                    						<input name="button2" type="button" onClick="javascript:deleteAccIn(document.forms['FleetDeleteAccidentForm']);" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
										</td>							
									</tr>	
<%				}
%>								</table>
								</html:form>
							</td>
						</tr>
<%			}
%>					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	      	<tr>
    	    	<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.colective.incidents")%>' />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
		   				<tr>
							<td width="100%" class="lineone" >
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.addincidents")%>' />
									<html:form action="/Fleetaddincident">
										<html:hidden property="fleet" value='<%=id%>'/>
										<html:hidden property="period" value='<%=period%>'/>
										<html:hidden property="year" value='<%=year%>'/>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
<%			    for(int i = 1; i <= userSessionData.getDivOrgCount(); i++) {
    				for(int j = 1; j <= filter.getDivOrgLevel(i-1); j++) {
    				    if(!(corporationId == CorporationData.MONSANTO && i >= 2 && i == 1)) {
							String s = "divOrg"+String.valueOf(i)+String.valueOf(j);
    				        Collection divOrg = (Collection)session.getAttribute(s);
    		    			if(divOrg.size() == 1) {
%>											<html:hidden property='<%=s%>' value='<%=((OptionItem)divOrg.iterator().next()).getValue()%>'/>
<%    						}else {
%>											<tr>
												<th class="lineone" align="left"><%=messages.getText(divOrgLabels[(i-1)*3+(j-1)])%><span class="required">*</span></th>				    
												<td class="lineone" align="left" >
		      										<html:select property='<%=s%>' styleClass="input"  style="width:250">
										          		<html:options collection='<%=s%>' property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>
<%    		    			}
    				    }
    				}
				}	
				Collection divGeoCollection = (Collection)session.getAttribute("divGeo");
				if(divGeoCollection.size() == 1) {
%>					<html:hidden property="country" value='<%=((OptionItem)divGeoCollection.iterator().next()).getValue()%>'/>
<%    			}else {
%>											<tr>
												<th class="lineone" align="left"><%=messages.getText("corporation.divgeo.countries")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="country" styleClass="input" style="width:250" >
										          		<html:options collection="divGeo" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
<%	 			}	 
%>											<tr>
												<th class="lineone" align="left"><%=messages.getText("generic.event.eventdata.clasification")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="clasification" styleClass="input" style="width:250" >
										          		<html:options collection="incidents" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.colective.incident.number")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text property="number" styleClass="input" style="width:250"/>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.colective.accident.cost")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text  property="cost" styleClass="input" style="width:250" />
												</td>
											</tr>	
											<tr>
												<td class="lineone" align="right" colspan="2">
		      										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.add")%>'/>
												</td>
											</tr>	
										</table>
									</html:form>
								<jsp:include  page="../component/utilitybox2bottom.jsp" />
							</td>
						</tr>
<%			incidents = fleetReport.findFleetIncidents(new Integer(id),new Integer(period),new Integer(year),userSessionData);
			columnCount = 3;
			if(incidents.size() > 0) {
				FleetIncidentData incident = (FleetIncidentData)incidents.iterator().next();
%>						<tr>
							<jsp:include  page="../component/spaceline.jsp" />
						</tr>
						<tr>
							<td width="100%">
								<html:form action="/Fleetdeleteincident">
								<html:hidden property="fleet" value='<%=String.valueOf(data.getId())%>'/> 									
								<html:hidden property="period" value='<%=period%>'/> 									
								<html:hidden property="year" value='<%=year%>'/> 									
								<html:hidden property="type" value="incident"/>
								<html:hidden property="list" />
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="guide2" width="5%">&nbsp;</td>				    
										<td class="guide2" width="13%"><%=messages.getText("generic.fleet.country")%></td>				            				
<%			for(int i = 0; i < divOrgs.length; i++) {
			    int[] index = divOrgs[i];
			    if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
			        columnCount++;
%>										<td class="guide2" width="13%" align="center"><%=messages.getText(lbls[(index[0]-1)*3+(index[1]-1)])%></td>				    
<%				}
			}
%>										<td class="guide2" ><%=messages.getText("generic.event.eventdata.clasification")%></td>
										<td class="guide2" ><%=messages.getText("fleet.colective.incident.number")%></td>
										<td class="guide2" ><%=messages.getText("fleet.colective.incident.cost")%></td>
									</tr>
<%				it = incidents.iterator();
				int totalCost = 0;
				boolean lineone = true;
				while(it.hasNext()) {
					incident = (FleetIncidentData)it.next();
			   	    Object[] incidentData = {null,null,null,null,null,null,incident.getNumber()};
			   	    if(incident.getDivOrg1() != null) {
			   	     	incidentData[0] = incident.getDivOrg1().getName();
			   	     	incidentData[3] = incident.getDivOrg1().getId();
			   	    }
			   	    if(incident.getDivOrg2() != null) {
			   	        incidentData[1] = incident.getDivOrg2().getName();
			   	        incidentData[4] = incident.getDivOrg2().getId();
			   	    }
			   	    if(incident.getDivOrg3() != null) {
			   	        incidentData[2] = incident.getDivOrg3().getName();
			   	        incidentData[5] = incident.getDivOrg3().getId();
			   	    }
					incidentsEvent.add(incidentData);
			    	eventIncidents += incident.getNumber().intValue();
				    totalCost += incident.getCost().intValue();
%>									<tr>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type='checkbox' name='<%=String.valueOf(incident.getId())%>' value='<%=String.valueOf(incident.getId())%>' ></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(incident.getCountry().getName())%></td>
<%				String[][] values = incident.getData();	
				for(int i = 0; i < divOrgs.length; i++) {
			    	int[] index = divOrgs[i];
				    if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
%>										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=values[index[0]-1][index[1]-1]%></td>				    
<%					}
				}
%>										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(incident.getClasification().getResourceBundleId())%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=String.valueOf(incident.getNumber())%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=String.valueOf(incident.getCost())%></td>
									</tr>
<%					lineone = !lineone;
				}
%>									<tr>
										<td class="guide3" colspan='<%=columnCount%>'><%=messages.getText("fleet.cost.total") %></td>
										<td class="guide3" align="center"><%=String.valueOf(eventIncidents)%></td>
										<td class="guide3" align="center"><%=String.valueOf(totalCost)%></td>
									</tr>
<%				if(eventIncidents > 0) {
%>							      	<tr>
										<jsp:include  page="../component/spaceline.jsp" />
						      		</tr>
									<tr>
										<td colspan='<%=String.valueOf(columnCount+1)%>' align="right">
                    						<input name="button2" type="button" onClick="javascript:deleteAccIn(document.forms['FleetDeleteIncidentForm']);" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
										</td>							
									</tr>	
<%				}
%>								</table>
								</html:form>
							</td>
						</tr>
<%			}
%>					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
<%		if(corporationId == -1) {
%>	      	<tr>
    	    	<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.colective.fuel")%>' />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
		   				<tr>
							<td width="100%" class="lineone" >
								<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.addfuel")%>' />
									<html:form action="/Fleetaddfuel">
										<html:hidden property="fleet" value='<%=id%>'/>
										<html:hidden property="period" value='<%=period%>'/>
										<html:hidden property="year" value='<%=year%>'/>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
<%			    for(int i = 1; i <= userSessionData.getDivOrgCount(); i++) {
    				for(int j = 1; j <= filter.getDivOrgLevel(i-1); j++) {
    				    if(!(corporationId == CorporationData.MONSANTO && i >= 2 && i == 1)) {
							String s = "divOrg"+String.valueOf(i)+String.valueOf(j);
    				        Collection divOrg = (Collection)session.getAttribute(s);
    		    			if(divOrg.size() == 1) {
%>											<html:hidden property='<%=s%>' value='<%=((OptionItem)divOrg.iterator().next()).getValue()%>'/>
<%    						}else {
%>											<tr>
												<th class="lineone" align="left"><%=messages.getText(divOrgLabels[(i-1)*3+(j-1)])%><span class="required">*</span></th>				    
												<td class="lineone" align="left" >
		      										<html:select property='<%=s%>' styleClass="input"  style="width:250">
										          		<html:options collection='<%=s%>' property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>
<%    		    			}
    				    }
    				}
				}	
				Collection divGeoCollection = (Collection)session.getAttribute("divGeo");
				if(divGeoCollection.size() == 1) {
%>											<html:hidden property="country" value='<%=((OptionItem)divGeoCollection.iterator().next()).getValue()%>'/>
<%    			}else {
%>											<tr>
												<th class="lineone" align="left"><%=messages.getText("corporation.divgeo.countries")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="country" styleClass="input" style="width:250" >
										          		<html:options collection="divGeo" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
<%	 			}	 
%>
											<tr>
												<th class="lineone" align="left"><%=messages.getText("generic.vehicle.vehicledata.vehicletype")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="vehicletype" styleClass="input" style="width:250" >
										          		<html:options collection="types" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.division.vehiclecount")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text property="vehicles" styleClass="input" style="width:250"/>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.division.miles")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text property="miles" styleClass="input" style="width:250"/>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.colective.fuel")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:select property="fuel" styleClass="input" style="width:250" >
										          		<html:options collection="fuels" property="value" labelProperty="label" styleClass="input" />
													</html:select>
												</td>
											</tr>	
											<tr>
												<th class="lineone" align="left"><%=messages.getText("fleet.colective.liters")%><span class="required">*</span></th>				            				
												<td class="lineone" align="left">
		      										<html:text property="liters" styleClass="input" style="width:250"/>
												</td>
											</tr>	
											<tr>
												<td class="lineone" align="right" colspan="2">
		      										<html:submit styleClass="input" value='<%=messages.getText("generic.messages.add")%>'/>
												</td>
											</tr>	
										</table>
									</html:form>
								<jsp:include  page="../component/utilitybox2bottom.jsp" />
							</td>
						</tr>
<%				Collection consumedFuel = fleetReport.findConsumedFuel(new Integer(id),new Integer(period),new Integer(year),userSessionData);
				if(consumedFuel.size() > 0) {
%>						<tr>
							<jsp:include  page="../component/spaceline.jsp" />
						</tr>
						<tr>
							<td width="100%">
								<html:form action="/Fleetdeletefuel">
								<html:hidden property="fleet" value='<%=String.valueOf(data.getId())%>'/> 									
								<html:hidden property="period" value='<%=period%>'/> 									
								<html:hidden property="year" value='<%=year%>'/> 									
								<html:hidden property="type" value="fuel"/>
								<html:hidden property="list" />
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td class="guide2" width="5%">&nbsp;</td>				    
										<td class="guide2" width="13%"><%=messages.getText("generic.fleet.country")%></td>				            				
<%					for(int i = 0; i < divOrgs.length; i++) {
					    int[] index = divOrgs[i];
					    if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
			    		    columnCount++;
%>										<td class="guide2" width="13%" align="center"><%=messages.getText(lbls[(index[0]-1)*3+(index[1]-1)])%></td>				    
<%						}
					}
%>										<td class="guide2" ><%=messages.getText("generic.vehicle.vehicledata.vehicletype")%></td>
										<td class="guide2" ><%=messages.getText("fleet.division.vehiclecount")%></td>
										<td class="guide2" ><%=messages.getText("fleet.division.miles")%></td>
										<td class="guide2" ><%=messages.getText("fleet.colective.fuel")%></td>
										<td class="guide2" ><%=messages.getText("fleet.colective.liters")%></td>
									</tr>
<%					it = consumedFuel.iterator();
					boolean lineone = true;
					while(it.hasNext()) {
					    ConsumedFuelData fuel = (ConsumedFuelData)it.next();
%>									<tr>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><input type='checkbox' name='<%=String.valueOf(fuel.getId())%>' value='<%=String.valueOf(fuel.getId())%>' ></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=messages.getText(fuel.getCountry().getName())%></td>
<%						String[][] values = fuel.getData();	
						for(int i = 0; i < divOrgs.length; i++) {
					    	int[] index = divOrgs[i];
				    		if(!(corporationId == CorporationData.MONSANTO && i > 3)) {
%>										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=values[index[0]-1][index[1]-1]%></td>				    
<%							}
						}
%>										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(fuel.getTypeVehicle() != null) ? messages.getText(fuel.getTypeVehicle().getResourceBundleId()) : "----"%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(fuel.getVehicles() != null) ? String.valueOf(fuel.getVehicles()) : "----"%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(fuel.getMiles() != null) ? String.valueOf(fuel.getMiles()) : "----"%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(fuel.getTypeVehicle() != null) ? messages.getText(fuel.getFuel().getResourceBundleId()) : ""%></td>
										<td class='<%=(lineone)?"lineone":"linetwo"%>' align="center"><%=(fuel.getLiters() != null) ? String.valueOf(fuel.getLiters()) : "----"%></td>
									</tr>
<%						lineone = !lineone;
					}
					if(consumedFuel.size() > 0) {
%>							      	<tr>
										<jsp:include  page="../component/spaceline.jsp" />
						      		</tr>
									<tr>
										<td colspan='<%=String.valueOf(columnCount+1)%>' align="right">
                    						<input name="button2" type="button" onClick="javascript:deleteAccIn(document.forms['FleetDeleteFuelForm']);" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
										</td>							
									</tr>	
<%					}
%>								</table>
								</html:form>
							</td>
						</tr>
<%				}
%>					</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
<%		}
%> 	      	<tr>
    	    	<td width="100%" colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.colective.integritycheck")%>' />
						<form action='<%="./layout.jsp?refer=/"+link+"&integrity=1"%>' method="post">
							<table width="100%" class="linetwo" cellpadding="0" cellspacing="0">
<%			if(eventAccidents == 0 && eventIncidents == 0) {
%>								<tr>
					                <td class="blue" align="left"><%=messages.getText("generic.fleet.colective.integritycheckwarning")%></td>
								</tr>
<%			}else {
	    		boolean verification = (Util.empty(request.getParameter("integrity"))) ? false : request.getParameter("integrity").equals("1");
				if(verification) {
				    String[][] accidentCheck = Util.integrityCheck(accidentsFleet,accidentsEvent);
				    if(accidentCheck.length == 0) {
%>		   	  					<tr align="center">
									<td width="100%" class="lineone" >
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.accidents")%>' />
											<table width="50%" class="linetwo" cellpadding="0" cellspacing="1">
												<tr align="center">
								    	            <td class="guide2">&nbsp;</td>
								    	            <td class="guide2"><%=messages.getText("generic.messages.accident")%></td>
				           						</tr>
				           	  					<tr align="center">
								    	            <td class="guide2"><%=messages.getText("generic.data.fleet")%></td>
								    	            <td class="linetwo"><%=fleetAccidents%></td>
				           						</tr>
				           	  					<tr align="center">
								    	            <td class="guide2"><%=messages.getText("generic.data.event.pl")%></td>
								    	            <td class="lineone"><%=eventAccidents%></td>
				           						</tr>           						
				           	  					<tr align="center">
								    	            <td class="linefour"><%=messages.getText("generic.messages.differences")%></td>
								    	            <td class="linefour"><%=Math.abs(fleetAccidents-eventAccidents)%></td>
				           						</tr>           						
											</table>
											<table width="50%" class="linetwo" cellpadding="0" cellspacing="1">
												<tr align="center">
									                <td class="green2"><%=messages.getText("fleet.accidents.integritycheck.ok")%></td>
				           						</tr>           						
											</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</td>
								</tr>
<%					}else { 
%>		   	  					<tr align="center">
									<td width="100%" class="lineone" >
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.accidents")%>' />
											<table width="100%" class="linetwo" cellpadding="0" cellspacing="1">
							  					<tr align="center">
								    	            <td class="guide2"><%=messages.getText("generic.message.n")%></td>
								    	            <td class="guide2"><%=messages.getText("generic.data.fleet.pl")%></td>
								    	            <td class="guide2"><%=messages.getText("generic.data.event.pl")%></td>
												</tr>				    	            
<%          			boolean lineone = false;
						for(int i = 0; i < accidentCheck.length; i++ ) {
%>		   	  									<tr align="center">
									                <td class="guide2" align="left">
										                <%=accidentCheck[i][0]+"/"+accidentCheck[i][1]%>
										            </td>
									                <td class='<%=(lineone) ? "lineone" : "linetwo" %>' align="center">
										                <%=accidentCheck[i][3]%>
										            </td>
									                <td class='<%=(lineone) ? "lineone" : "linetwo" %>' align="center">
										                <%=String.valueOf(Integer.parseInt(accidentCheck[i][3]) - Integer.parseInt(accidentCheck[i][4]))%>
										            </td>
						       					</tr>     
<%							lineone = !lineone;
						}
%>												<tr>
													<jsp:include  page="../component/spaceline.jsp" />
					      						</tr>
												<tr>
					                				<td class="red2" colspan="3"><%=messages.getText("fleet.accidents.integritycheck.wrong")%></td>           							
												</tr>
											</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</td>
								</tr>
<%					}
%>						      	<tr>
									<jsp:include  page="../component/spaceline.jsp" />
	      						</tr>
<%				    String[][] incidentCheck = Util.integrityCheck(incidentsFleet,incidentsEvent);
				    if(incidentCheck.length == 0) {
%>		   	  					<tr align="center">
									<td width="100%" class="lineone" >
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.incidents")%>' />
											<table width="50%" class="linetwo" cellpadding="0" cellspacing="1">
												<tr align="center">
								    	            <td class="guide2">&nbsp;</td>
								    	            <td class="guide2"><%=messages.getText("generic.messages.incident")%></td>
				           						</tr>
				           	  					<tr align="center">
								    	            <td class="guide2"><%=messages.getText("generic.data.fleet")%></td>
								    	            <td class="linetwo"><%=fleetIncidents%></td>
				           						</tr>
				           	  					<tr align="center">
								    	            <td class="guide2"><%=messages.getText("generic.data.event.pl")%></td>
								    	            <td class="lineone"><%=eventIncidents%></td>
				           						</tr>           						
				           	  					<tr align="center">
								    	            <td class="linefour"><%=messages.getText("generic.messages.differences")%></td>
								    	            <td class="linefour"><%=Math.abs(fleetIncidents-eventIncidents)%></td>
				           						</tr>           						
											</table>
											<table width="50%" class="linetwo" cellpadding="0" cellspacing="1">
												<tr align="center">
									                <td class="green2"><%=messages.getText("fleet.accidents.integritycheck.ok")%></td>
				           						</tr>           						
											</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</td>
								</tr>
<%					}else { 
%>		   	  					<tr align="center">
									<td width="100%" class="lineone" >
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.fleet.colective.incidents")%>' />
											<table width="100%" class="linetwo" cellpadding="0" cellspacing="1">
							  					<tr align="center">								    	            <td class="guide2">&nbsp;</td>

								    	            <td class="guide2"><%=messages.getText("generic.data.fleet.pl")%></td>
								    	            <td class="guide2"><%=messages.getText("generic.data.event.pl")%></td>
												</tr>				    	            
<%          			boolean lineone = false;
						for(int i = 0; i < incidentCheck.length; i++ ) {
%>		   	  									<tr>
									                <td class="guide2" align="left">
										                <%=incidentCheck[i][0]+"/"+incidentCheck[i][1]%>
										            </td>
									                <td class='<%=(lineone) ? "lineone" : "linetwo" %>' align="center">
										                <%=incidentCheck[i][3]%>
										            </td>
									                <td class='<%=(lineone) ? "lineone" : "linetwo" %>' align="center">
										                <%=String.valueOf(Integer.parseInt(incidentCheck[i][3]) - Integer.parseInt(incidentCheck[i][4]))%>
										            </td>
						       					</tr>     
<%							lineone = !lineone;
						}
%>												<tr>
													<jsp:include  page="../component/spaceline.jsp" />
					      						</tr>
												<tr>
					                				<td class="red2" colspan="3"><%=messages.getText("fleet.incidents.integritycheck.wrong")%></td>           							
												</tr>
											</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</td>
								</tr>
<%					}
				}
%>						      	<tr>
									<jsp:include  page="../component/spaceline.jsp" />
					      		</tr>
								<tr>
									<td>
										<input type="submit" value='<%=messages.getText("fleet.insert.colective.verify")%>' class="input" />
									</td>
								</tr>
						      	<tr>
									<jsp:include  page="../component/spaceline.jsp" />
					      		</tr>
<%			} 
%>							</table>
						</form>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</td>
			</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	    </table>
	</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.access.fleet" %>'/>
<%		}
	}	
%>	
</html:html>
