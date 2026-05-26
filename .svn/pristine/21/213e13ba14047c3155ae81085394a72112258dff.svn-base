<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.fleet.*" 
	import="business.vehicle.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.fleet.tables.*"
	import="communication.vehicle.tables.*"
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
	VehicleReportFacade vehicleReport; 
	
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_FLEET);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		String id=(String)request.getParameter("id");
		if(id==null){
			id=(String)session.getAttribute("id");
			session.removeAttribute("id");
		}
		session.setAttribute("url","fleet/FleetVehicles.jsp?id="+id);
		if(id!=null){	
			messages=sys.getText();
			vehicleReport = sys.getVehicleReportFacade();
			Collection data = vehicleReport.findVehiclesByFleetNew(new Integer(id),sys.getUserSessionData());

			FleetReportFacade fleetReport = sys.getFleetReportFacade();
			Collection fleetVehicles = fleetReport.findFleetVehicles(new Integer(id),sys.getUserSessionData());
			if(fleetVehicles != null) {
%>

<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<head/>

<script>
function deleteConfirm(form,msg){

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
				}else{
					valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
				}	
			}	
		} 
	}
	form.list.value=valueCheckboxParamList;
	if(valueCheckboxParamList.length>0){
		if(confirm(msg)){
			form.submit();
		}
	}
}
function add(form,checks){

	var elements=form.elements;
	var length=elements.length;
	var i;
	var valueCheckboxParamList="";
	var separator="<";
		
	for(i=0;i<length;i++){
		var element=elements[i];
		if(element.type.toLowerCase()=="checkbox"){
			if(checks) {
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			}else {
					element.check = true;
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
			}
		} 
	}
	form.list.value=valueCheckboxParamList;
	if(valueCheckboxParamList.length>0){
		form.submit();
	}
}
</script>

	<body bgcolor="#FFFFFF">
	
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.fleet.vehicles")%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
	  		<tr>
	  			<html:form action="/Fleetdeletecountry">
	  				<html:hidden property="fleet" value='<%=id%>'/>
	  				<html:hidden property="list" />
   		    		<td width="292" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.vehicles")%>' />
		        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
	    	    				<tr>
									<td class="guide2" width="5%">&nbsp;</td>				    
									<td class="guide2" width="90%"><%=messages.getText("Matricula")%></td>				    
								</tr>	
<%			boolean linetwo = false;
			if(fleetVehicles.size() > 0) {
				Iterator it = fleetVehicles.iterator();
				while(it.hasNext()) {
					Object[] vehicle = (Object[])it.next();
%>								<tr>
									<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(vehicle[0])%>' value='<%=String.valueOf(vehicle[0])%>' ></td>
									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='95%'>
										<%=vehicle[1]%>
									</td>
								</tr>
<%					linetwo = !linetwo;
				}
%>
								<tr>
									<td class='<%=(!linetwo)?"lineone":"linetwo"%>' colspan="8">
										<%=messages.getText("fleet.odometers.totalvehicles")+": "+fleetVehicles.size() %>
									</td>
								</tr>

<%			}else {
%>								<tr>
									<td class="linetwo" colspan="2">
										<%=messages.getText("generic.messages.notresult")%>
									</td>
								</tr>
<%			}
%>      						<tr>
									<jsp:include  page="../component/spaceline.jsp" />
  								</tr>
								<tr>
									<td class="linetwo" colspan="5" align="right">
									<%
										String button5 = "javascript:deleteConfirm(document.forms[2],'"+messages.getText("fleet.delete.vehicle.confirm")+"')";
									%>
										<input type="button" value='<%=messages.getText("generic.messages.delete")%>' onclick="<%=button5%>" class="input" />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</html:form>
    	    		<td width="16">&nbsp;</td>
	  			<html:form action="/Fleetaddcountry">
	  				<html:hidden property="fleet" value='<%=id%>'/>
	  				<html:hidden property="list" />
    	    		<td width="292" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.divisonsvehicles")%>' />
		        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
	    	    				<tr>
									<td class="guide2" width="5%">&nbsp;</td>				    
									<td class="guide2" width="90%"><%=messages.getText("Matricula")%></td>				    
								</tr>	
<%			
			Iterator it = data.iterator();
			boolean linetwo = false;
			if(it.hasNext()) {
				int count = 0;
				while(it.hasNext()) {
					Object[] vehicle = (Object[])it.next();
					count++;
%>								<tr>
									<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(vehicle[0])%>' value='<%=String.valueOf(vehicle[0])%>' ></td>
									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='95%'>
										<%=vehicle[1]%>
									</td>
								</tr>
<%					linetwo = !linetwo;
				} %>
								<tr>
									<td class='<%=(!linetwo)?"lineone":"linetwo"%>' colspan="8">
										<%=messages.getText("fleet.odometers.totalvehicles")+": "+count %>
									</td>
								</tr>			
<%			}else {
%>								<tr>
									<td class="linetwo" colspan="2">
										<%=messages.getText("generic.messages.notresult")%>
									</td>
								</tr>
<%			}
%>      						<tr>
									<jsp:include  page="../component/spaceline.jsp" />
  								</tr>
								<tr>
									<td class="linetwo" colspan="5" align="right">
										<input type="button" value='<%=messages.getText("generic.messages.add")%>' onclick="javascript:add(document.forms[3],true)" class="input" />
										<input type="button" value='<%=messages.getText("generic.messages.addall")%>' onclick="javascript:add(document.forms[3],false)" class="input" />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</html:form>
   			</tr>
   			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
   			</tr>
   			
			<tr>
				<td colspan="3" align="right">
					<form action="layout.jsp?refer=/fleet/FleetView.jsp?id=<%=id%>"  method="post">	
						<input type="button" value='<%=messages.getText("generic.messages.exit")%>' onclick="javascript:document.forms[4].submit()" class="input" />
					</form>
				</td>
			</tr>
				
				
        </table>
	</body>
<%			}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.access.fleet" %>'/>
<%			}
		}
	}
%>
</html:html>

