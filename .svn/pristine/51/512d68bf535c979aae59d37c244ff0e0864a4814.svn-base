<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.fleet.*"
	import="business.vehicle.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.fleet.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
	import="communication.administration.user.UserSessionData"
 	import="communication.util.Filter"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>



<html:html>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
	Integer period;String year;String id;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Filter filter = userSessionData.getFilter();
	
	String check = Util.checkPermission(sys,SecurityConstants.FLEET_INSERT_DATA);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	    
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		FleetUtil fleetUtil = new FleetUtil();
		
		id = (Util.empty(request.getParameter("fleet"))) ? (String)session.getAttribute("fleet") : request.getParameter("fleet");
	    period = (Util.empty(request.getParameter("period"))) ? new Integer((String)session.getAttribute("period")) : new Integer(request.getParameter("period"));
	    year = (Util.empty(request.getParameter("year"))) ? (String)session.getAttribute("year") : request.getParameter("year");
		session.setAttribute("url","fleet/Odometers.jsp?fleet="+id+"&period="+period+"&year="+year);
		
		FleetReportFacade fleetReport = sys.getFleetReportFacade();
		Object[] data = fleetReport.findFleetData(new Integer(id),userSessionData);
		if(data[0] != null) {
		    Collection excludedIds = new LinkedList();
	messages = sys.getText();
		    
	Hashtable values = fleetReport.findOdometers(new Integer(id),period,new Integer(year),null,null,userSessionData);
	
		    String[] labels = filter.getLevelLabels();
	Collection fuelTypes = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPEFUEL,sys.getUserSessionData());
%>
	
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<style type="text/css">	</style>
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>

	<body bgcolor="#FFFFFF">
		<html:form action="/Fleetodometer">
			<html:hidden property="fleet" value='<%=id%>' />
			<html:hidden property="period" value='<%=String.valueOf(period)%>' />
			<html:hidden property="year" value='<%=year%>' />
			<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.fleet.odometers")+" "+data[0]+" "+messages.getText("generic.month."+String.valueOf(period))+" - "+year%>' />
	  			</tr>
<%
	Collection vehicles = fleetReport.getVehiclesByFleet(new Integer(id),userSessionData);			
	Hashtable hash = fleetReport.getOdometerType(new Integer(id),userSessionData);			
	Iterator it = vehicles.iterator();
	Object[] last = {"0","0","0","0",new Integer(0),new Integer(0),new Integer(0),new Integer(0)};
	boolean first = true;
	boolean lineone = true;
	String odometerType = "";
	int count = 0;
	int totalMiles = 0;
    	    int totalKms = 0;
    	    int totalAccidents = 0;
    	    int totalIncidents = 0;
    	    int totalNearmiss = 0;
    	    
    	    /* Variables de totales sin incluir odometros de vehiculos sin odometro previo */
    	   	int countWithOut0 = 0;
	int totalMilesWithOut0  = 0;
    	    int totalKmsWithOut0  = 0;
    	    int totalAccidentsWithOut0  = 0;
    	    int totalIncidentsWithOut0  = 0;
    	    int totalNearmissWithOut0  = 0;
    	    
	while(it.hasNext()) {
    			Object[] dataV = (Object[])it.next();
    			System.out.println(last[4]+".equals("+dataV[4]+") || !"+last[5]+".equals("+dataV[5]+") || !"+last[6]+
    					".equals("+dataV[6]+") || !"+last[7]+".equals("+dataV[7]+")");
    			boolean distinct = !last[4].equals(dataV[4]) || (last[5] != null && !last[5].equals(dataV[5])) || 
    					(last[6] != null && !last[6].equals(dataV[6])) || (last[7] != null && !last[7].equals(dataV[7]));
    			if(distinct) {
                    String key = String.valueOf(dataV[4])+"-"+String.valueOf(dataV[5])+"-"+String.valueOf(dataV[6])+"-"+String.valueOf(dataV[7]);
                    odometerType = (hash.containsKey(key)) ? (String)hash.get(key) : (String)hash.elements().nextElement();
	if(first) {
	   first = false; 
	}else {
%>						
							<!--  SubTotal -->	
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals")%></td>
								<td class="guide3"><%=String.valueOf(totalMiles)%></td>
								<td class="guide3"><%=String.valueOf(totalKms)%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide3"><%=String.valueOf(totalAccidents)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidents)%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide3"><%=String.valueOf(totalNearmiss)%></td>
<%
	}
		}else {
%>								<td class="guide3">---</td>
								<td class="guide3">--</td>
<%
	}
%>						</tr>

<%
	if (sys.getCorporationId() == CorporationData.LILLY){
%>
							<!-- SubTotal sin tener en cuenta los odometros de los vehiculos con odomtro anterior 0 o vacio -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals-without0")%></td>
								<td class="guide3"><%=String.valueOf(totalMilesWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalKmsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalAccidentsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidentsWithOut0)%></td>
							</tr>
<%
	}
%>

							


							<tr>
								<td class='lineone' colspan="9">
								<%=messages.getText("fleet.odometers.subtotalvehicles")+": "+count%>
								</td>
							</tr>
							<tr>
				    	 		<td>&nbsp;</td>
				    		</tr>
				    	 	<tr>				    	 		 	
								<td class='lineone' colspan="9">
				    	 		 	<strong><%=messages.getText("fleet.odometers.vehiclesoutfleet")%></strong>
				    	 		</td>				    	 		 	
				    	 	</tr>
<%
	Collection excluded = fleetReport.getExcludedOdometer(new Integer(id),period,new Integer(year),(Integer)last[4],(Integer)last[5],(Integer)last[6],(Integer)last[7],userSessionData);			
		if(excluded == null || excluded.size() == 0) {
%>							<tr>
								<td class="linetwo" colspan="8"><%=messages.getText("generic.messages.nodata")%></td>
							</tr>	
<%
		}else {
					Iterator excludedIt = excluded.iterator();
					int totalMilesExcluded = 0;
					int totalKmsExcluded = 0;
					int totalAccidentsExcluded = 0;
					int totalIncidentsExcluded = 0;
					int totalNearmissExcluded = 0;
					
					while(excludedIt.hasNext()) {
			    		Object[] excludedData = (Object[])excludedIt.next();
			    		excludedIds.add(excludedData);
						String[] odometerData = fleetUtil.findFleetOdometerValues(values,excludedData,odometerType);
					    totalMiles += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
			    		totalKms += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
					    totalAccidents += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
					    totalIncidents += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
					    totalNearmiss += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
					    totalMilesExcluded += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
			    		totalKmsExcluded += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
					    totalAccidentsExcluded += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
					    totalIncidentsExcluded += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
					    totalNearmissExcluded += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
					    
					    /* totales sin vehiclos con odometro anterior 0 */
			    	    if (!Util.empty(odometerData[3]) && !odometerData[3].trim().equalsIgnoreCase("0")){
				    	    totalMilesWithOut0 += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
				    	    totalKmsWithOut0 += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
				    	    totalAccidentsWithOut0 += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
				    	    totalIncidentsWithOut0 += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
				    	    totalNearmissWithOut0 += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
			    	    }
	%>							<tr class=<%=(lineone) ? "lineone" : "linetwo"%>>
									<td><%=odometerData[1]%></td>
									<td><%=odometerData[2]%></td>
									<td><%=odometerData[3]%></td>
									<td><%=odometerData[4]%></td>
									<td><%=odometerData[5]%></td>
									<td><%=odometerData[6]%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>									<td><%=odometerData[7]%></td>
									<td><%=odometerData[8]%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td><%=odometerData[11]%></td>
<%
	}
			}else {
%>									<td><%=messages.getText(odometerData[10])%></td>
									<td><%=odometerData[9]%></td>
<%
	}
%>							</tr>			    
<%
			    	lineone = !lineone;
			    	    						count++;
			    	    					}
			    %>							
							<!--  SubTotal Veh. Excluidos: -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals")%></td>
								<td class="guide3"><%=String.valueOf(totalMilesExcluded)%></td>
								<td class="guide3"><%=String.valueOf(totalKmsExcluded)%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide3"><%=String.valueOf(totalAccidentsExcluded)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidentsExcluded)%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide3"><%=String.valueOf(totalNearmissExcluded)%></td>
<%
	} 
			}else {
%>								<td class="guide3">---</td>
								<td class="guide3">---</td>
<%
	}
%>	
							</tr>						
							
							<tr>
								<td class='lineone' colspan="9">
								<%=messages.getText("fleet.odometers.subtotalvehicles")+": "+excluded.size()%>
								</td>
							</tr>
<%
	}
%>							<tr>
				    	 		<td>&nbsp;</td>
				    		</tr>
				    		
				    		<!--  Subtotales -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals")%></td>
								<td class="guide3"><%=String.valueOf(totalMiles)%></td>
								<td class="guide3"><%=String.valueOf(totalKms)%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide3"><%=String.valueOf(totalAccidents)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidents)%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide3"><%=String.valueOf(totalNearmiss)%></td>
<%
	}
		}else {
%>								<td class="guide3">---</td>
								<td class="guide3">---</td>
<%
	}
%>						</tr>


<%
	if (sys.getCorporationId() == CorporationData.LILLY){
%>
							<!-- SubTotal sin tener en cuenta los odometros de los vehiculos con odomtro anterior 0 o vacio -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals-without0")%></td>
								<td class="guide3"><%=String.valueOf(totalMilesWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalKmsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalAccidentsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidentsWithOut0)%></td>
							</tr>
<%
	}
%>							<tr>
								<td class='lineone' colspan="9">
								<%=messages.getText("fleet.odometers.subtotalvehicles")+": "+(excluded.size()+count)%>
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
<%
	count = 0;
		totalMiles = 0;
		totalKms = 0;
		totalAccidents = 0;
		totalIncidents = 0;
		totalNearmiss = 0;
		
		
		countWithOut0 = 0;
		totalMilesWithOut0 = 0;
		totalKmsWithOut0 = 0;
		totalAccidentsWithOut0 = 0;
		totalIncidentsWithOut0 = 0;
		totalNearmissWithOut0 = 0;
		
		 
	}
%>	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="100%" colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.detail")%>' />
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<td colspan="9">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("fleet.division.data")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr class="lineone">
												<td width="38%">
													<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
														<tr class="lineone">
															<th align="left"><%=messages.getText("generic.fleet.country")%></th>
															<td>&nbsp;</td>
															<td align="left"><%=filter.getCountry((Integer)dataV[4],messages)%></td>
														</tr>
														<tr class="lineone">
															<th align="left"><%=messages.getText("generic.fleet.odometertype")%></th>
															<td>&nbsp;</td>
															<td align="left"><%=messages.getText(odometerType)%></td>
														</tr>
													</table>
												</td>
												<td width="2%" class="lineone">&nbsp;</td>
												<td width="60%">
													<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<%
	for(int i = 0; i < labels.length; i++) {
    					if(!Util.empty(labels[i])) {
%>														<tr class="lineone">
															<th align="left"><%=messages.getText(labels[i])%></th>
															<td>&nbsp;</td>
															<td align="left"><%=filter.getRoot(i,(Integer)dataV[i+5])%></td>
														</tr>
<%
	}
	}
%>													</table>
												</td>
											</tr>
										</table>
									<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
							</tr>
					      	<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				      		</tr>
							<tr>
								<td class="guide2" ><%=messages.getText("generic.vehicle.vehicledata.matricula")%></td>				            				
								<td class="guide2" ><%=messages.getText("generic.vehicle.vehicledata.driver")%></td>
								<td class="guide2" ><%=messages.getText("fleet.odometer.lastvalue")%></td>
								<td class="guide2" ><%=messages.getText("fleet.odometer.value")%></td>
								<td class="guide2" ><%=messages.getText("fleet.odometer.road.miles")%></td>
								<td class="guide2" ><%=messages.getText("fleet.odometer.road.km")%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide2" ><%=messages.getText("fleet.odometer.accidents")%></td>
								<td class="guide2" ><%=messages.getText("fleet.odometer.incidents")%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide2" ><%=messages.getText("fleet.odometer.nearmiss")%></td>
<%
	}
		}else {
%>								<td class="guide2" ><%=messages.getText("fleet.odometer.fuel")%></td>
								<td class="guide2" ><%=messages.getText("datatype.volumeunit.liters")%></td>
<%
	}
%>							</tr>
<%
	last = dataV;
		}
		String[] odometerData = fleetUtil.findFleetOdometerValues(values,dataV,odometerType);
	    	    totalMiles += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
	    	    totalKms += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
	    	    totalAccidents += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
	    	    totalIncidents += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
	    	    totalNearmiss += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
	    	    
	    	    /* totales sin vehiclos on odometro anterior 0 */
	    	    if (!Util.empty(odometerData[3]) && !odometerData[3].trim().equalsIgnoreCase("0")){
		    	    totalMilesWithOut0 += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
		    	    totalKmsWithOut0 += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
		    	    totalAccidentsWithOut0 += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
		    	    totalIncidentsWithOut0 += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
		    	    totalNearmissWithOut0 += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
	    	    }
%>							<tr class=<%=(lineone) ? "lineone" : "linetwo"%>>
									
								<td>
									<%
										if ( !Util.empty(odometerData[0])){
									%>
										<a href="layout.jsp?refer=/vehicle/VehicleView.jsp?id=<%=odometerData[0]%>" ><%=odometerData[1]%></a>
									<%
										}else{
									%>
										<%=odometerData[1]%>
									<%
										}
									%>									
								</td>
								<td>
									<%
										if (odometerData.length >= 13 && !Util.empty(odometerData[12])){
									%>
										<a href="layout.jsp?refer=/driver/DriverView.jsp?id=<%=odometerData[12]%>"><%=odometerData[2]%></a>
									<%
										}else{
									%>
										<%=odometerData[2]%>
									<%
										}
									%>
									
								</td>
								<td><%=odometerData[3]%></td>
								<td>
									<input type="hidden" name='<%="lastodometer"+odometerData[0]%>' value='<%=odometerData[4]%>'/>
									<input name='<%="odometer"+odometerData[0]%>' value='<%=odometerData[4]%>' class="input" size="10"/>
								</td>
								<td><%=odometerData[5]%></td>
								<td><%=odometerData[6]%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td>
									<input name='<%="lastaccidents"+odometerData[0]%>' value='<%=odometerData[7]%>' type="hidden"/>
									<input name='<%="accidents"+odometerData[0]%>' value='<%=odometerData[7]%>' class="input" size="10"/>
								</td>
								<td>
									<input name='<%="lastincidents"+odometerData[0]%>' value='<%=odometerData[8]%>' type="hidden"/>
									<input name='<%="incidents"+odometerData[0]%>' value='<%=odometerData[8]%>' class="input" size="10"/>
								</td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td>
									<input name='<%="lastnearmiss"+odometerData[0]%>' value='<%=odometerData[11]%>' type="hidden"/>
									<input name='<%="nearmiss"+odometerData[0]%>' value='<%=odometerData[11]%>' class="input" size="10"/>
								</td>
<%
	}
		}else {
%>								<td>
									<input name='<%="lastfueltype"+odometerData[0]%>' value='<%=odometerData[10]%>' type="hidden"/>
									<select name='<%="fueltype"+odometerData[0]%>' class="input">
										<option value="">...</option>
<%
	Iterator fuelIt = fuelTypes.iterator();
	    			while(fuelIt.hasNext()) {
	        			String name = ((TypeFuelDataType)fuelIt.next()).getResourceBundleId();
%>										<option value='<%=name%>' <%=(odometerData[10].equals(name)) ? "selected" : ""%>><%=messages.getText(name)%></option>
<%
	}
%>									</select>
								</td>
								<td>
									<input name='<%="lastfuel"+odometerData[0]%>' value='<%=odometerData[9]%>' type="hidden"/>
									<input name='<%="fuel"+odometerData[0]%>' value='<%=odometerData[9]%>' class="input" size="10"/>
								</td>
<%
	}
%>						</tr>			    
<%
			    	lineone = !lineone;
			    	    		count++;
			    	    	}
			    	    	if(!first) {
			    %>					
							<!--  Subtotal -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals")%></td>
								<td class="guide3"><%=String.valueOf(totalMiles)%></td>
								<td class="guide3"><%=String.valueOf(totalKms)%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide3"><%=String.valueOf(totalAccidents)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidents)%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide3"><%=String.valueOf(totalNearmiss)%></td>
<%
	}
		}else {
%>								<td class="guide3">---</td>
								<td class="guide3">---</td>
<%
	}
%>						</tr>

							
							<%
															if (sys.getCorporationId() == CorporationData.LILLY){
														%>
							<!-- SubTotal sin tener en cuenta los odometros de los vehiculos con odomtro anterior 0 o vacio -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals-without0")%></td>
								<td class="guide3"><%=String.valueOf(totalMilesWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalKmsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalAccidentsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidentsWithOut0)%></td>
							</tr>
							<%
								}
							%>



							<tr>
								<td class='lineone' colspan="9">
								<%=messages.getText("fleet.odometers.subtotalvehicles")+": "+count%>
								</td>
							</tr>
							<tr>
				    	 		<td>&nbsp;</td>
				    		</tr>
				    	 	<tr>				    	 		 	
				    	 		<td class='lineone' colspan="9">
				    	 		 	<strong><%=messages.getText("fleet.odometers.vehiclesoutfleet")%></strong>
				    	 		</td>				    	 		 	
				    	 	</tr>
<%
	Collection excluded = fleetReport.getExcludedOdometer(new Integer(id),period,new Integer(year),(Integer)last[4],(Integer)last[5],(Integer)last[6],(Integer)last[7],userSessionData);			
		if(excluded == null || excluded.size() == 0) {
%>							<tr>
								<td class="linetwo" colspan="8"><%=messages.getText("generic.messages.nodata")%></td>
							</tr>	
<%
		}else {
			
			
			Iterator excludedIt = excluded.iterator();
			int totalMilesExcluded = 0;
			int totalKmsExcluded = 0;
			int totalAccidentsExcluded = 0;
			int totalIncidentsExcluded = 0;
			int totalNearmissExcluded = 0;
			while(excludedIt.hasNext()) {
			    Object[] excludedData = (Object[])excludedIt.next();
			    		excludedIds.add(excludedData);
				String[] odometerData = fleetUtil.findFleetOdometerValues(values,excludedData,odometerType);
			    totalMiles += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
			    totalKms += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
			    totalAccidents += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
			    totalIncidents += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
			    totalNearmiss += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
			    totalMilesExcluded += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
			    totalKmsExcluded += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
			    totalAccidentsExcluded += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
			    totalIncidentsExcluded += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
			    totalNearmissExcluded += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
			    
			    /* totales sin vehiclos on odometro anterior 0 */
			    	    if (!Util.empty(odometerData[3]) && !odometerData[3].trim().equalsIgnoreCase("0")){
		    	    totalMilesWithOut0 += (Util.empty(odometerData[5])) ? 0 : Integer.parseInt(odometerData[5]);
		    	    totalKmsWithOut0 += (Util.empty(odometerData[6])) ? 0 : Integer.parseInt(odometerData[6]);
		    	    totalAccidentsWithOut0 += (Util.empty(odometerData[7])) ? 0 : Integer.parseInt(odometerData[7]);
		    	    totalIncidentsWithOut0 += (Util.empty(odometerData[8])) ? 0 : Integer.parseInt(odometerData[8]);
		    	    totalNearmissWithOut0 += (Util.empty(odometerData[11])) ? 0 : Integer.parseInt(odometerData[11]);
			    	    }
	%>							<tr class=<%=(lineone) ? "lineone" : "linetwo"%>>
								<td><%=odometerData[1]%></td>
								<td><%=odometerData[2]%></td>
								<td><%=odometerData[3]%></td>
								<td><%=odometerData[4]%></td>
								<td><%=odometerData[5]%></td>
								<td><%=odometerData[6]%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td><%=odometerData[7]%></td>
								<td><%=odometerData[8]%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td><%=odometerData[11]%></td>
<%
	}
		}else {
%>								<td><%=odometerData[10]%></td>
								<td><%=odometerData[9]%></td>
<%
	}
%>							</tr>			    
<%
			    	lineone = !lineone;
			    	    				count++;
			    	    			}
			    %>							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals")%></td>
								<td class="guide3"><%=String.valueOf(totalMilesExcluded)%></td>
								<td class="guide3"><%=String.valueOf(totalKmsExcluded)%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide3"><%=String.valueOf(totalAccidentsExcluded)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidentsExcluded)%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide3"><%=String.valueOf(totalNearmissExcluded)%></td>
<%
	}
		}else {
%>								<td class="guide3">---</td>
								<td class="guide3">---</td>
<%
	}
%>						</tr>

							<tr>
								<td class='lineone' colspan="9">
								<%=messages.getText("fleet.odometers.subtotalvehicles")+": "+excluded.size()%>
								</td>
							</tr>
<%
	}
%>							<tr>
				    	 		<td>&nbsp;</td>
				    		</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals")%></td>
								<td class="guide3"><%=String.valueOf(totalMiles)%></td>
								<td class="guide3"><%=String.valueOf(totalKms)%></td>
<%
	if(!sys.isCorporation(CorporationData.ALLERGAN)) {
%>								<td class="guide3"><%=String.valueOf(totalAccidents)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidents)%></td>
<%
	if(sys.getCorporationId() == CorporationData.MONSANTO) {
%>								<td class="guide3"><%=String.valueOf(totalNearmiss)%></td>
<%
	}
		}else {
%>								<td class="guide3">---</td>
								<td class="guide3">---</td>
<%
	}
%>							</tr>

							<%
								if (sys.getCorporationId() == CorporationData.LILLY){
							%>
							<!-- SubTotal sin tener en cuenta los odometros de los vehiculos con odomtro anterior 0 o vacio -->
							<tr>
								<td colspan="2">&nbsp;</td>
								<td class="guide3" colspan="2"><%=messages.getText("fleet.odometers.subtotals-without0")%></td>
								<td class="guide3"><%=String.valueOf(totalMilesWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalKmsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalAccidentsWithOut0)%></td>
								<td class="guide3"><%=String.valueOf(totalIncidentsWithOut0)%></td>
							</tr>
							<%
								}
							%>



							<tr>
								<td class='lineone' colspan="9">
									<%=messages.getText("fleet.odometers.subtotalvehicles")+": "+(excluded.size()+count)%>
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
<%
	}
%>		  		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	      		</tr>
<%
	// Box de Observaciones
%>
				<tr>
					<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.observations")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr class="lineone">
								<td colspan="3" class="guide3">
									<%=messages.getText("fleet.odometers.vehiclesoutfleet")%>
								</td>
							</tr>
							<tr class="linetwo">
								<td class="guide2" align="center" >
									<%=messages.getText("generic.driver.observation.date")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
								</td>				            				
								<td class="guide2" align="center">
									<%=messages.getText("generic.vehicle.vehicledata.matricula")%>								
								</td>				            				
								<td class="guide2"  align="center" >
									<%=messages.getText("generic.driver.observation.user")%>
								</td>				  
							</tr>          				
<%
          					if(excludedIds.size() == 0){
          				%>							<tr>
								<td colspan="3">
									<span class="linetwo"><%=messages.getText("generic.event.noobservations")%></span>
								</td>								
							</tr>							
<%
								}else{
												    			it = excludedIds.iterator();
														while(it.hasNext()){
															Object[] vehicleData = (Object[])it.next();
															Collection observations = sys.getVehicleReportFacade().getVehicleFleetObservations(new Integer(id),(Integer)vehicleData[0],userSessionData);								
															if(observations.size() > 0) {
																Iterator itObservations = observations.iterator();
															    VehicleFleetObservationData observation = (VehicleFleetObservationData)itObservations.next();
							%>							<tr align=center align="center" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
						    	<td align="right">
						        	<%=Util.formatDate(observation.getDate())%>
				    	       	</td>
				        	    <td align="center">
				            		<%=vehicleData[1]%>
					          	</td>
					            <td align="center">
					            	<%=observation.getUser()%>
					           	</td>
			    	      	</tr>
<%				  	}
				}
			}
%>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
		  		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	      		</tr>						
		      	<tr>
					<td colspan="3" align="right">
						<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
						<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
					</td>
    	  		</tr>
	    	  	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	      		</tr>
	    	</table>
		</html:form>
	</body>
<%		}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.access.fleet" %>'/>
<%		}
	}	
%>	
</html:html>
