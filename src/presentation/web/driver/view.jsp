<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.driver.*" 
	import="business.datatype.*" 
	import="communication.language.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.datatype.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.driver.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	DriverData data; Integer id;
	SystemAccess sys;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = null;
	if(sys.getUserSessionData().isDriver()) {
	    check = Util.checkPermission(sys,SecurityConstants.DRIVER_VIEW_OWN_DATA);
	}else {
	    check = Util.checkPermission(sys,SecurityConstants.EDIT_DRIVER);
	}

	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();
		pathLanguage="/util/jsp/language.jsp";
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		if(session.getAttribute("SystemAccess")==null){
			response.sendRedirect("/exception.jsp");
		}
		String idStr = request.getParameter("id");
		id = null;
		boolean isSyngentaDriver = sys.isCorporation(CorporationData.BAYER_MX) && sys.getUserSessionData().getDriverData() != null && (idStr == null || Integer.parseInt(idStr) == sys.getUserSessionData().getDriverData().getId().intValue());
		if(sys.getUserSessionData().isDriver() || isSyngentaDriver) {
		    data = sys.getUserSessionData().getDriverData();
		    id = data.getId();
		}else {
			id=new Integer(idStr);
			if(id!=null){	
				data=sys.getDriverReportFacade().findDriverByPrimaryKey(id, sys.getUserSessionData());
			}
		}
		if(data!=null){
			DriverForm driverForm=DriverUtil.createDriverForm(data,DriverUtil.VIEW,messages);
			session.setAttribute("DriverCreateForm",driverForm);
%>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">


<head/>
	   
	<html:form  action="/Driverupdate" >
		<table width="100%" border="0" align='left' cellpadding="0" cellspacing="0">
			<tr>
		    	<td class="default">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
							<td align="right"><%=driverForm.getFirstName() %></td>
						</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%></th>
							<td align="right"><%=driverForm.getLastName() %></td>
						</tr>
<%			if(Boolean.parseBoolean(driverForm.getDirector())) {
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	  					<tr>
							<td colspan="2" align="left">
								<span class="red"><%=messages.getText("generic.driver.driverdata.director") %></span>
							</td>
  						</tr>				
<%			}
%>						
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%></th>
							<td align="right"><%=driverForm.getBirthDate()+"-"+driverForm.getBirthMonth()+"-"+driverForm.getBirthYear() %></td>
						</tr>
						<tr >
				 			<td align="right" colspan="2">
				 				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
				    		</td>
						</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.sex")%> </th>
							<td align="right"><%=messages.getText(driverForm.getSex())%></td>
						</tr>
						
<%					if (sys.getCorporationId() == CorporationData.ALLERGAN ||
							sys.getCorporationId() == CorporationData.ASTRAZENECA){ %>
					<!-- RG -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.federativeentity")%></th>
							<td align="right"><%=(driverForm.getFederativeEntity() == null) ? "" : 	driverForm.getFederativeEntity()%></td>
						</tr>
					<!-- CPF -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecoverage")%></th>
							<td align="right"><%=(driverForm.getLicenseCoverage() == null) ? "" : driverForm.getLicenseCoverage()%></td>
						</tr>
<%					} 
					if(sys.getCorporationId() != CorporationData.PEPSICO) {  %>		
						<!-- Nacionalidad -->			
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.nationality")%> </th>
							<td align="right"><%=messages.getText(driverForm.getNationality())%></td>
						</tr>
<%					} 
%>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.city")%></th>
							<td align="right"><%=driverForm.getCity()%></td>
						</tr>
							
							
<%			 	if(sys.isCorporation(CorporationData.DPA)){
%>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.state")%></th>
							<td align="right"><%=(driverForm.getStateOrRegion()!=null) ? driverForm.getStateOrRegion():"----"%></td>
						</tr>
<% 				}	
				if(sys.getCorporationId() == CorporationData.BAYER) {
%>					
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.manager")%></th>
							<td align="right"><%=(driverForm.getManager() != null) ? driverForm.getManager() : "---"%></td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.stateOrRegion")%></th>
							<td align="right"><%=(driverForm.getStateOrRegion() != null)?messages.getText(driverForm.getStateOrRegion()):"---"%></td>
						</tr>
						
<% 			} 
%>
						
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
			        		<th align="left"> <%=messages.getText("generic.driver.driverdata.personaladdress")%></th>
							<td align="right"><%=driverForm.getPersonalAddress()%></td>
			      		</tr>
<%			if(sys.getCorporationId() == CorporationData.CIMSA) {
%>					
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
							<td align="right"><%=(driverForm.getPhoneNumber()!=null) ? driverForm.getPhoneNumber():"----"%></td>
						</tr>
						
						<!--  Marital Status  -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.maritalstatus")%></th>
							<td align="right"><%=(driverForm.getMaritalStatus()!=null) ? messages.getText(driverForm.getMaritalStatus()):"----"%></td>
						</tr>
						
						<!--  Education Level -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.educationlevel")%></th>
							<td align="right"><%=(driverForm.getEducationLevel()!=null) ? messages.getText(driverForm.getEducationLevel()):"----"%></td>
						</tr>
						
						
<%			}
%>	

				</table>

		      	</td>
			</tr>		      	
		</table>
	</html:form>
<%
		}		
	}
%>

</html:html>