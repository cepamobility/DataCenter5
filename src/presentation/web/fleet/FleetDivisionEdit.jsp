<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.fleet.*" 
	import="business.datatype.*" 
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.security.*"
	import="communication.fleet.tables.*"
	import="communication.datatype.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! Text messages; String pathLanguage;
	FleetReportFacade fleetReport; 
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_FLEET);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String id=(String)request.getParameter("id");
		if(id==null){
			id=(String)session.getAttribute("id");
			session.removeAttribute("id");
		}
		session.setAttribute("url","fleet/FleetDivisionEdit.jsp?id="+id);
		if(id!=null){	
			messages=sys.getText();
			fleetReport = sys.getFleetReportFacade();
			FleetData data = fleetReport.findFleetByPrimaryKey(new Integer(id),sys.getUserSessionData());
			if(data != null) {
				String width="100%";
				boolean linetwo = false;
				switch(sys.getUserSessionData().getFilter().getDivOrgCount()) {
				    case 1:
				        width="47%";
				        break;
				    case 2:
				        width="31%";
				        break;
				    case 3:
				        width="24%";
				        break;
				}
				DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
	
				session.setAttribute("vehicleTypes",new DataTypeUtil(sys).getVehicleType(true,false,false));
	
				Collection distanceTypes = new LinkedList();
				distanceTypes.add(new OptionItem(messages.getText("generic.messages.select"),""));                			
				Iterator it = dataTypeReportFacade.findAll(DataTypeConstants.TYPEDISTANCEUNIT,sys.getUserSessionData()).iterator();
				while(it.hasNext()) {
					String value = ((TypeDistanceUnitDataType)it.next()).getResourceBundleId();
					distanceTypes.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
				}
				session.setAttribute("distanceunits",distanceTypes);
%>
<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
</script>
<script language='javascript' type='text/javascript'>
function setAction(form,action){	
	if(preparedCheckboxParameters(form)) {
		form.action.value=action;		
		form.submit();
	}
}
</script>

<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">

<body bgcolor="#FFFFFF">
	
		<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.fleet.divisions")+" "+data.getName()%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
			<form name="FleetDivisionExclusion" action="layout.jsp?refer=/fleet/FleetDivisionExclusion.jsp"  method="post"  >
				<input type="hidden" name="fleet" value='<%=id%>' /> 
				<input type="hidden" name="checkboxParamList" />
				<input type="hidden" name="action" />
				
		  		<tr>
    	    		<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.divisions")%>' />
		        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
<!--  Dibujo Columnas -->
        				<tr>
	        				<td class="guide2" width="5%">&nbsp;</td>				    
							<td class="guide2"><%=messages.getText("corporation.divgeo.countries")%></td>				            				
<%			int[][] divOrgs = sys.getUserSessionData().getFilter().getAllDivOrgs();			
			String[] attrs = sys.getUserSessionData().getFilter().getDivOrgLabels();
			for(int i = 0; i < divOrgs.length; i++) {
			    int[] index = divOrgs[i];
			    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i > 3)) {			        
%>								<td class="guide2" width="13%" align="center"><%=messages.getText(attrs[(index[0]-1)*3+(index[1]-1)])%></td>				    
<%				}
			}
	

// Columna de VehicleType y Odometros
			if(data.getType().intValue() == FleetData.COLECTIVE) {
%>							<td class="guide2"><%=messages.getText("fleet.division.vehicletype")%></td>				            				
<%			}else{
%>							<td class="guide2"><%=messages.getText("fleet.division.odometertype")%></td>
<%			}
%>		
							<td class="guide2" align="center"><%=messages.getText("generic.driver.driverdata.exclusiondate")%><%="  "+messages.getText("generic.messages.dateformat")%></td>
				</tr>
				
<!--  Dibujo registros de datos: -->	
<%			it = data.getDivisionsIterator();
			linetwo = false;
			
			if(it.hasNext()) {
				while(it.hasNext()) {
					FleetDivisionData divisionData = (FleetDivisionData)it.next();
					String[] divisionLabels = divisionData.getDivOrgLabels();
%>						<tr>
							<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(divisionData.getId())%>' value='<%=String.valueOf(divisionData.getId())%>' ></td>
							<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='<%=width%>'>
								<%=divisionData.getGeoLabel(messages)%>
							</td>	
	
<%					for(int i = 0; i < divOrgs.length; i++) {
					    int[] index = divOrgs[i];
					    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i > 3)) {
%>								<td class='<%=(linetwo)?"linetwo":"lineone"%>' width='<%=width%>' align="center"><%=divisionLabels[(index[0]-1)*3+(index[1]-1)]%></td>				    
<%						}
					}
// Registros para Columnas de VehicleType y Odomerto

					if(data.getType().intValue() == FleetData.COLECTIVE) {
%>									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='<%=width%>'>
										<%=messages.getText(divisionData.getVehicleType().getResourceBundleId())%>
									</td>
<%					}else{
%>									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='<%=width%>'>
										<%=messages.getText(divisionData.getOdometer().getResourceBundleId())%>
									</td>
<%					}
%>							
<!--  Registros para Columna de ExclusionDate -->	
									<td align="center"  class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='<%=width%>'>
										<%=(divisionData.getExclusionDate()== null) ? "-" : Util.formatDate(divisionData.getExclusionDate())%>
									</td>
							</tr>
<%					linetwo = !linetwo;
				}
			}else {
%>								<tr>
									<td class="linetwo" colspan="5">
										<%=messages.getText("generic.messages.notresult")%>
									</td>
								</tr>
<%			}
%>      						<tr>
									<jsp:include  page="../component/spaceline.jsp" />
  								</tr>
								<tr>
									<td class="linetwo" colspan="8" align="right">
			<%if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_FLEET)) {	%>
							                <input name="button" type="button" onClick="javascript:setAction(document.forms['FleetDivisionExclusion'],'exclude');" value='<%=messages.getText("generic.driver.exclude")%>' class="input"/> 
							                <input name="button" type="button" onClick="javascript:setAction(document.forms['FleetDivisionExclusion'],'include');" value='<%=messages.getText("generic.messages.include")%>' class="input"/>
                    						<input name="button" type="button" onClick="javascript:setAction(document.forms['FleetDivisionExclusion'],'delete');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
<%			}															
%>									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
      			</tr>
      		</form>	
      		
   			<html:form action="/Fleetadddivision">
   				<html:hidden property="id" value='<%=id%>'/>
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
		  		<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.divisiondata")%>' />
				                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                				    		<tr class="linetwo">
<%			if(data.getType().intValue() == FleetData.COLECTIVE) {
%>												<th align="left" ><%=messages.getText("generic.fleet.fleetdata.vehicletype")%><span class="required">*</span></th>
                     							<td>
													<html:select property="vehicleType" styleClass="input" style="width:160; ">
														<html:options collection="vehicleTypes" property="value" labelProperty="label"/>
													</html:select>
												</td>	
<%			}else{
%>												<th align="left" ><%=messages.getText("generic.vehicle.vehicledata.distanceunit")%><span class="required">*</span></th>
                     							<td>
                     								<html:select property="odometer" styleClass="input" style="width:160; ">
														<html:options collection="distanceunits" property="value" labelProperty="label"/>
													</html:select>
												</td>	
<%			}
%>											</tr>
										</table>
									<jsp:include page="../component/utilityboxbottom.jsp"/>
								</td>
							</tr>
			      			<tr>
								<jsp:include  page="../component/spaceline.jsp" />
				  			</tr>
                	   		<jsp:include page='<%="../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=FleetAddDivisionForm&consult=false"%>' />
                    	 </table>
	   	             </td>
    	            <td width="16">&nbsp;</td>
        	       	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                	   		<jsp:include page='<%="../util/divorg.jsp?form=FleetAddDivisionForm&consult=false" %>'/>   
                    	 </table>
	   	             </td>
    	    		<td>
      			</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr  class="linetwo">
	        		<td colspan="3" align="right">
						<html:submit value='<%=messages.getText("generic.messages.add")%>' styleClass="input" />
						<html:cancel value='<%=messages.getText("generic.messages.exit")%>' styleClass="input" />
	          		</td>
	      		</tr>
      		</html:form>
        </table>
	</body>
<%			}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.access.fleet" %>'/>
<%			}
		}
	}
%>
</html:html>

