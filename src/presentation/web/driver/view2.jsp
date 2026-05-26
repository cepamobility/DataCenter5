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
		id = null;
		if(sys.getUserSessionData().isDriver()) {
		    data = sys.getUserSessionData().getDriverData();
		    id = data.getId();
		}else {
			id=new Integer((String)request.getParameter("id"));
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
							<td align="right"><%="generic.driver.driverdata.firstname:FirstName" %></td>
						</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%></th>
							<td align="right"><%="generic.driver.driverdata.lastname:LastName" %></td>
						</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%></th>
							<td align="right"><%="generic.driver.driverdata.birthdate:BirthDate" %></td>
						</tr>
						<tr >
				 			<td align="right" colspan="2">
				 				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
				    		</td>
						</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.sex")%> </th>
							<td align="right"><%="generic.driver.driverdata.sex:Sex" %></td>
						</tr>
						
<%					if (sys.getCorporationId() == CorporationData.ALLERGAN ||
							sys.getCorporationId() == CorporationData.ASTRAZENECA){ %>
					<!-- RG -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.federativeentity")%></th>
							<td align="right"><%="generic.driver.driverdata.federativeentity:FederativeEntity" %></td>
						</tr>
					<!-- CPF -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecoverage")%></th>
							<td align="right"><%="generic.driver.driverdata.licensecoverage:LicenseCoverage" %></td>
						</tr>
<%					} 
					if(sys.getCorporationId() != CorporationData.PEPSICO) {  %>		
						<!-- Nacionalidad -->			
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.nationality")%> </th>
							<td align="right"><%="generic.driver.driverdata.nationality:Nationality" %></td>
						</tr>
<%					} 
%>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.city")%></th>
							<td align="right"><%="generic.driver.driverdata.city:City" %></td>
						</tr>
							
							
<%			 	if(sys.isCorporation(CorporationData.DPA)){
%>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.state")%></th>
							<td align="right"><%="generic.driver.driverdata.state:StateOrRegion" %></td>
						</tr>
<% 				}	
				if(sys.getCorporationId() == CorporationData.BAYER) {
%>					
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.manager")%></th>
							<td align="right"><%="generic.driver.driverdata.manager:Manager" %></td>
						</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left"><%=messages.getText("generic.driver.driverdata.stateOrRegion")%></th>
							<td align="right"><%="generic.driver.driverdata.stateOrRegion:stateOrRegion" %></td>
						</tr>
						
<% 			} 
%>
						
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
			        		<th align="left"> <%=messages.getText("generic.driver.driverdata.personaladdress")%></th>
							<td align="right"><%="generic.driver.driverdata.personaladdress:PersonalAddress" %></td>
			      		</tr>
<%			if(sys.getCorporationId() == CorporationData.CIMSA) {
%>					
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
							<td align="right"><%="generic.driver.driverdata.phonenumber:PhoneNumber" %></td>
						</tr>
						
						<!--  Marital Status  -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.maritalstatus")%></th>
							<td align="right"><%="generic.driver.driverdata.maritalstatus:MaritalStatus" %></td>
						</tr>
						
						<!--  Education Level -->
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr  class="linetwo">
							<th align="left" > <%=messages.getText("generic.driver.driverdata.educationlevel")%></th>
							<td align="right"><%="generic.driver.driverdata.educationlevel:EducationLevel" %></td>
						</tr>
						
						
<%			}
%>	
<% 			if(sys.isCorporation(CorporationData.JJ)){%>
      							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      							<tr>
      								<th align="left"> <%=messages.getText("generic.driver.driverdata.jj.hrd")%></th>
       								<td align="right">
       									<%="generic.driver.driverdata.jj.hrd:IdDallas" %>
       								</td>
      							</tr>
<%			}%>

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