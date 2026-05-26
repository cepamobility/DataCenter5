<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.datatype.*"
	import="business.fleet.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.*"
	import="communication.administration.*"
	import="communication.datatype.tables.*"
	import="communication.fleet.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.fleet.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_FLEET);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();

		String id=(String)request.getParameter("id");
		if(id==null){
			id=(String)session.getAttribute("id");
			session.removeAttribute("id");
		}
		session.setAttribute("url","fleet/FleetEdit.jsp?id="+id);

		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
		session.setAttribute("vehicleTypes",new DataTypeUtil(sys).getVehicleType(true,false,false));
		
		Collection periodList = new LinkedList();
		periodList.add(new OptionItem(messages.getText("generic.messages.select"),""));                			
		periodList.add(new OptionItem(messages.getText("generic.fleet.fleetdata.monthly"),String.valueOf(FleetMainAttributes.MONTHLY)));                			
		periodList.add(new OptionItem(messages.getText("generic.fleet.fleetdata.byquarter"),String.valueOf(FleetMainAttributes.BY_QUARTER)));                			
		session.setAttribute("periodList",periodList);
		

		if(id!=null){	
			FleetReportFacade fleetReport = sys.getFleetReportFacade();
			FleetData data = fleetReport.findFleetByPrimaryKey(new Integer(id),sys.getUserSessionData());
			if(data != null) {
				FleetDataForm form = new FleetDataForm(data);
				session.setAttribute("FleetEditForm",form);
				String title = (data.getType().intValue() == FleetData.COLECTIVE) ? "generic.messages.editcolectivefleet" : "generic.messages.editindividualfleet";
%>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
	<META http-equiv="Cache-Control" content="no-cache">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Expires" content="-1">		
	</head>		

	<body bgcolor="#FFFFFF">
		<html:form action="/Fleetedit" focus="name">
			<html:hidden property="type" value='<%=String.valueOf(data.getType())%>' />
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.fleet.fleetdata")%>' />
						<table width="100%">
					      	<tr  class="linetwo">
					        	<th align="left"> <%=messages.getText("generic.fleet.fleetdata.name")%><span class="required">*</span></th>
					         	<td align="right">
					            	<html:text property="name" styleClass="input" style="width:250;" maxlength="150"/>
					          	</td>
					      	</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left" valign="top"><%=messages.getText("generic.fleet.fleetdata.description")%><span class="required">*</span></th>
        						<td align="right">
        							<html:textarea property="description" styleClass="input" rows="3" cols="50" style="width:250;"/>
          						</td>
      						</tr>
					      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.fleet.fleetdata.period")%><span class="required">*</span></th>
        						<td align="right">
									<html:select property="period" styleClass="input" style="width:250;">
										<html:options collection="periodList" property="value" labelProperty="label"/>
									</html:select>
          						</td>
      						</tr>
	      			      	<tr  class="space"><th align="right"></th><td align="right"></td></tr>
      						<tr  class="linetwo">
        						<td colspan="2" align="right">
									<html:submit value='<%=messages.getText("generic.messages.accept")%>' styleClass="input" />
									<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input" />
          						</td>
      						</tr>
      					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
			</table>
		</html:form>
	</body>
<%			}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=fleet.error.access.fleet" %>'/>
<%			}
		}
	}	%>
</html:html>
