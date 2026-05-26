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
<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
</script>
<script language="JavaScript" type="text/javascript">
function submitPagination(page,all) {
	document.forms[2].all.value = all;
	document.forms[2].pageNum.value = page;
	document.forms[2].submit();
}

function setAction(form,action){
	if(preparedCheckboxParameters(form)) {
		form.action.value=action;
		form.submit();
	}
}
</script>

<%!
	Text messages; 
	SystemAccess sys;
	String attribute; 
	String value;
	UserSessionData userSessionData; 
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = null;
	if(sys.getUserSessionData().isDriver()) {
		check = Util.checkPermission(sys,SecurityConstants.DRIVER_VIEW_VEHICLE);
	}else {
		check = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","vehicle/VehicleOdometerList.jsp");

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
%>		

<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr class="guide1"> 
    	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.vehicle.vehiclelist.systemvehicles")%>' />
	</tr>
  	<tr> 
    	<jsp:include  page="../component/spaceline.jsp" />
  	</tr>
 	<tr>
  		<td>
			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' /> 
      		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        		<tr> 
          			<td width="100%"> 
            			<form id="VehicleList" name="VehicleList" action="layout.jsp?refer=/vehicle/VehicleOdometerList.jsp" method="post">
              				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                				<tr> 
                  					<td width="13%" align="left"  class="texts"> 
                    					<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>:	
                  					</td>
                  					<td width="87%" colspan="2" align="left"> 
                    					<input type="text" name="value" value='<%=value%>' size="40" class="input"> 
                  					</td>
                				</tr>
                				<tr> 
                  					<td height="20" colspan="3" align="left" valign="top" class="texts"> 
                    					<table width="87%" border="0" align="right" cellpadding="0" cellspacing="0" class="specialarea">
                      						<tr> 
                        						<td width="12%" valign="middle">
                        							<input type="radio" class="specialarea" name="status" value='<%=VehicleData.ACTIVE%>'<%=(Integer.parseInt(status) == VehicleData.ACTIVE)? "checked" : ""%>> 
                          							<%=messages.getText("generic.messages.active")%> 
                          						</td>
                        						<td width="15%" valign="middle">
                        							<input type="radio" class="specialarea" name="status" value='<%=VehicleData.EXCLUDED%>' <%=(Integer.parseInt(status) == VehicleData.EXCLUDED) ? "checked" : ""%>> 
                          							<%=messages.getText("generic.messages.excluded")%> 
                          						</td>
                        						<td width="73%" align="left" valign="middle"> 
                          							<input type="radio" class="specialarea" name="status" value='<%=VehicleData.ALL%>' <%=(Integer.parseInt(status) == VehicleData.ALL) ? "checked" : ""%> />
                          							<%=messages.getText("generic.messages.all.pl")%> 
                          						</td>
                      						</tr>
                    					</table>
                    				</td>
                				</tr>
                				<tr> 
                  					<td height="10" colspan="3"> <img src="x" width="1" height="1"></td>
                				</tr>
                				<tr> 
                  					<td align="right" colspan="3"> 
                  						<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
                      						<tr> 
                        						<td height="14" width="35%"align="right" valign="bottom"> 
                        							<input name="button" type="button" onClick="javascript:document.forms[2].submit();" value='<%=messages.getText("generic.messages.search")%>' class="input"/>	
                        						</td>
                      						</tr>
                    					</table>
                    				</td>
                				</tr>
              				</table>
            			</form>
          			</td>
        		</tr>
      		</table>
      		<jsp:include page="../component/utilityboxbottom.jsp"/>
      	</td>	
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
                				<td width='14%' class="guide2">
	               					<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
	               				</td>
                				<td width='19%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.trade")%></td>
                				<td width='19%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.model")%></td>
                				<td width='10%'	class="guide2"><%=messages.getText("generic.vehicle.vehicledata.year")%></td>
                				<td width='34%'	class="guide2"><%=messages.getText("generic.fleet.fleetsearch.lastperiod")%></td>
              				</tr>
<%
		VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
		ListResult result = vehicleReportFacade.findVehicleList(attribute,value,0,-1,Integer.parseInt(status),userSessionData);
		if(result.getTotal() == 0) {
%>		    				<tr align="center"> 
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
	    		    if(instance.get(Calendar.DATE) >= 28) {
	    		    	if(instance.get(Calendar.DATE) <= 31) {
	    		    		instance.add(Calendar.MONTH,1);
	    		    	}
	    		    }
	    		    int month = instance.get(Calendar.MONTH);
	    		    int year = instance.get(Calendar.YEAR);
	    		    if(lastInstance == null) {
	    		        action = "layout.jsp?refer=fleet/OwnOdometers.jsp";
	    		        if (month == 0){
    		        		// Si estoy en enero y no tengo odometro anterior, 
    		        		// ingreso diciembre del año anterior.    		        		
    		        		year = year -1;
    		        	}
	    		    }else { 
	    		    	
	    		    	int previousMonth;
    		    		
    		    		if (month == 0) previousMonth = 11;
    		    		else previousMonth = month - 1;
    		    			
    		    		if (previousMonth == lastInstance.get(Calendar.MONTH)){    		    			
    		    			year = lastInstance.get(Calendar.YEAR);
	    		    		action = "layout.jsp?refer=fleet/InsertedOdometers.jsp" ; 
    		    		}else{    		    			
    		    			action = "layout.jsp?refer=fleet/OwnOdometers.jsp";	
    		    		}
    		    		
    		    		if ( lastInstance.get(Calendar.MONTH) < 11){
		    		    	// Si el ultimo odometro no es diciembre, conservo el año.
		    		    	year = lastInstance.get(Calendar.YEAR);
		    		    }
		    		    
	    		    }
	    		    if(month == 0) {
	    		    	 month = 12;
	    		    }
%>							<form name='<%="vehicle"+String.valueOf(vehicle.getId())%>' action='<%=action%>' method="post">
					    		<input type="hidden" name="vehicle" value='<%=String.valueOf(vehicle.getId())%>' />
					    		<input type="hidden" name="period" value='<%=String.valueOf(month)%>' />
					    		<input type="hidden" name="year" value='<%=String.valueOf(year)%>' />
								<tr align=center align="right" class="linetwo">
                					<td width='14%'>
<%					String link = "javascript:document.forms['vehicle"+String.valueOf(vehicle.getId())+"'].submit();";
%>                					
                						<a href="<%=link%>" >
	                						<%=vehicle.getMatricula()%>
	                					</a>
	                				</td>
    	            				<td width='19%'><%=messages.getText(vehicle.getTrade().getResourceBundleId())%></td>
        	        				<td width='19%'><%=vehicle.getModel()%></td>
            	    				<td width='10%'><%=String.valueOf(vehicle.getYear())%></td>
							    	<td width='34%' align="right">
<% 					if(lastInstance == null) {
%>										----						
<%					}else {
						if(periodType == FleetData.BY_QUARTER.intValue()) {
%>										<%="Q"+String.valueOf(lastInstance.get(Calendar.MONTH))+" "+lastInstance.get(Calendar.YEAR)%>
<% 	 					}else {
%>										<%=messages.getText("generic.month."+String.valueOf(lastInstance.get(Calendar.MONTH)+1))+" "+lastInstance.get(Calendar.YEAR)%>
<% 						}
					}
%>									</td>
                				</tr>	
                			</form>
<%    			}
    		}	
		}
%>						</table>
					</td>
				</tr>
			</table>			
      		<jsp:include page="../component/utilityboxbottom.jsp"/>
      	</td>	
  	</tr>
  	<tr> 
    	<jsp:include  page="../component/spaceline.jsp" />
  	</tr>
</table>
<%	}
%>
