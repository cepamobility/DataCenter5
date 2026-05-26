<%@page language="java" 
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.fleet.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*" 	
 	import="communication.administration.user.*" 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.administration.corporation.tables.CountryData"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	 	<title> CEPA International</title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<style type="text/css">
<!--	body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.contain {
			width: 100%; 
			height: 100%;
		}
 -->
		</style>
	</head>

<%!
	Text messages; 
	SystemAccess sys;
	String attribute; 
	String value;
	UserSessionData userSessionData; 
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	int corporationId = sys.getCorporationId();
	userSessionData=sys.getUserSessionData();
	DriverData driver = userSessionData.getDriverData();
	String check = Util.checkPermission(sys,SecurityConstants.DRIVER_INSERT_ODOMETER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","vehicle/AssociatedVehicles.jsp");

		messages=sys.getText();
	
		session.setAttribute("target","VehicleList");
		
		String status = String.valueOf(VehicleData.ACTIVE);
		if(!Util.empty(request.getParameter("status"))) {
		    status = request.getParameter("status");
		} 
		
		attribute = "matricula";
		
		if(request.getParameter("attribute")!=null){
	attribute = request.getParameter("attribute");
		}		
		value = "";		
		if(request.getParameter("value")!=null){
	value = request.getParameter("value");
		}		
		boolean monsantoLAdriver = corporationId == CorporationData.MONSANTO && 
		(driver.getCountryName().equals(CountryData.ARGENTINA) || 
		driver.getCountryName().equals(CountryData.PARAGUAY) || 
		driver.getCountryName().equals(CountryData.BOLIVIA) || 
		driver.getCountryName().equals(CountryData.URUGUAY));
%>		
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
			<form name="data" action="../driver/data.jsp" method="post">
			</form>		
			<form name="message" action="../driver/sendMessage.jsp" method="post">
			</form>		
			<form name="event" action="http://teamsites.monsanto.com/public/segvehicular/Shared%20Documents/Reporte%20de%20eventos%20versión%203.1.xlsx" method="post" target="_blank">
			</form>		
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the topï¿½s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1"> 
			    	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.vehicle.vehiclelist.systemvehicles")%>' />
				</tr>
			  	<tr> 
    				<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
  				<tr> 
    				<td colspan="3"> 
			    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.vehicle.vehiclelist.systemvehicles")%>' /> 
						<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
			        		<tr> 
						       	<td width="100%"> 
            						<table width="100%" border="0">
			              				<tr align="center"> 
            			    				<td width='14%' class="guide2"><%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%></td>
            			    				<td width='19%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.trade")%></td>
                							<td width='19%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.model")%></td>
			                				<td width='10%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.year")%></td>
            			    				<td width='34%'	class="guide2"><%=messages.getText("generic.fleet.fleetsearch.lastperiod")%></td>
              							</tr>
<%
		VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
		ListResult result = vehicleReportFacade.findVehicleList(attribute,value,0,-1,Integer.parseInt(status),userSessionData);
		if(result.getTotal() == 0) {
%>		    							<tr align="center"> 
											<td colspan="5" class="linetwo">
												<%=messages.getText("generic.messages.notresult")%> 
											</td>
										</tr>	
<%		}else {
    		Iterator it = result.getElements().iterator();
    		while(it.hasNext()) {
    		    VehicleData vehicle = (VehicleData)it.next();
        		int periodType = sys.getFleetReportFacade().findFleetPeriodType(vehicle.getId(),sys.getUserSessionData());
        		if(periodType != -1) {
	    		    Calendar instance = Calendar.getInstance();
	    		    Calendar lastInstance = sys.getFleetReportFacade().findLastOdometer(vehicle.getId(),sys.getUserSessionData());
	    		    String action = null;
	    		    int month = instance.get(Calendar.MONTH);
	    		    int year = instance.get(Calendar.YEAR);
	    		    
	    		    int start = (monsantoLAdriver) ? OdometerData.START_ODOMETER_MONSANTO_CONOSUR : OdometerData.START_ODOMETER;
	    		    if(instance.get(Calendar.DATE) >= start) {
	    		    	if(instance.get(Calendar.DATE) <= 31) {
	    		    		instance.add(Calendar.MONTH,1);
	    		    	}
		    		    month = instance.get(Calendar.MONTH);
		    		    year = instance.get(Calendar.YEAR);
		    		    if(lastInstance == null) {
		    		        action = "../fleet/OdometerValue.jsp";
		    		    }else { 
			    		    
		    		    	action = (instance.get(Calendar.MONTH) == lastInstance.get(Calendar.MONTH)) ? 
			    		    		"../fleet/OdometersLoaded.jsp" : "../fleet/OdometerValue.jsp";
			    		    
			    		    // Ajustes
			    		    if ( month == 0){
			    		    	// Si el ultimo odometro no es diciembre, conservo el aï¿½o.
			    		    	month = 12;
			    		    	year --;
			    		    }
		    		    }
	    		    }else {
	    		    	if(lastInstance == null) {
	    		        	action = "../fleet/OdometerValue.jsp";
	    		        	if (month == 0){
	    		        		// Si estoy en enero (en un dia anterior al 28) y no tengo odometro anterior, 
	    		        		// ingreso diciembre del aï¿½o anterior.	    		        		
	    		        		year = year -1;
	    		        	}
	    		    	}else { 
	    		    		
	    		    		int previousMonth;
	    		    		
	    		    		if (month == 0) previousMonth = 11;
	    		    		else previousMonth = month - 1;
	    		    			
	    					boolean insert = false;
	    					Calendar c = Calendar.getInstance();
	    					switch(sys.getCorporationId()) {
	    						case CorporationData.JJ:
	    							insert = c.get(Calendar.DATE) <= OdometerData.END_ODOMETER_JJ;
	    							break;
	    						case CorporationData.ALLERGAN:
	    							insert = c.get(Calendar.DATE) < OdometerData.END_ODOMETER_ALLERGAN;
	    							break;
	    						case CorporationData.MONSANTO:
	    							if(sys.getUserSessionData().getDriverData().getCountryName().equals(CountryData.BRASIL)) {
	    								insert = c.get(Calendar.DATE) < OdometerData.END_ODOMETER_MONSANTO_BR;
	    							}else {
	    								insert = c.get(Calendar.DATE) < OdometerData.END_ODOMETER_MONSANTO_ARG;
	    							}
	    					}
	    		    		if (previousMonth == lastInstance.get(Calendar.MONTH) || !insert){	    		    			
	    		    			year = lastInstance.get(Calendar.YEAR);
	    		    			action = "../fleet/OdometersLoaded.jsp"; // el load se hace sobre mes ingresado.
	    		    		}else{	    		    			    		    			
				    		    year = instance.get(Calendar.YEAR);
				    		    if(month == 0) {
				    		    	year--;
				    		    }
	    		    			action = "../fleet/OdometerValue.jsp";
	    		    		}	    		    		
	    		    	}
	    		    }
	    		    if(month == 0) {
	    		    	 month = 12;
	    		    }
%>										<form name='<%="vehicle"+String.valueOf(vehicle.getId())%>' action='<%=action%>' method="post">
								    		<input type="hidden" name="vehicle" value='<%=String.valueOf(vehicle.getId())%>' />
								    		<input type="hidden" name="period" value='<%=String.valueOf(month)%>' />
					    					<input type="hidden" name="year" value='<%=String.valueOf(year)%>' />
											<tr align=center align="right" class="linetwo">
            			    					<td width='14%'>
<%					String link = "javascript:document.forms['vehicle"+String.valueOf(vehicle.getId())+"'].submit();"; 
%>		        									<a href="<%=link%>" >
	                									<%=vehicle.getMatricula()%>
				                					</a>
	        			        				</td>
    	            							<td width='19%'><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
			        	        				<td width='19%'><%=vehicle.getModel()%></td>
            				    				<td width='10%'><%=String.valueOf(vehicle.getYear())%></td>
										    	<td width='34%' align="right">
<% 					if(lastInstance == null) {
%>    												-------
<%					}else {
						if(periodType == FleetData.BY_QUARTER.intValue()) {
%>													<%="Q"+String.valueOf(lastInstance.get(Calendar.MONTH))+" "+lastInstance.get(Calendar.YEAR)%>
<% 		 				}else {
%>													<%=messages.getText("generic.month."+String.valueOf(lastInstance.get(Calendar.MONTH)+1))+" "+lastInstance.get(Calendar.YEAR)%>
<% 						}
					}
%>												</td>
                							</tr>	
			                			</form>
<%    			}
    		}	
		}
%>									</table>
								</td>
							</tr>
						</table>			
      					<jsp:include page="../component/utilityboxbottom.jsp"/>
			      	</td>	
			  	</tr>
			  	<tr> 
			    	<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
<%		if(corporationId == CorporationData.LILLY || monsantoLAdriver) {
%>				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../component/utilitybox4top.jsp" />
						<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
							<tr class="lineone">
								<td align="left" class="lineone">
									<input type="button" value='<%=messages.getText("generic.messages.senderror")%>' onclick="javascript:document.forms['message'].submit();">
								</td>
<%			if(corporationId == CorporationData.MONSANTO && monsantoLAdriver) {
%>								<td align="center" class="lineone">
									<input type="button" value='<%=messages.getText("generic.messages.reportevent")%>'  onclick="javascript:document.forms['event'].submit();">
								</td>
<%			}
%>								<td align="right" class="lineone">
									<input type="button" value='<%=messages.getText("generic.messages.myinfo")%>'  onclick="javascript:document.forms['data'].submit();">
								</td>
							</tr>
						</table>			
      					<jsp:include page="../component/utilitybox4bottom.jsp"/>
			      	</td>	
			  	</tr>
			  	<tr> 
			    	<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
<%		}
%>			</table>		
		</body>
<%	}
%>		
</html>
