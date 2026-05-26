<%@page import="communication.administration.corporation.util.DPAUnconformityItemFilter"%>
<%@page import="presentation.translator.web.dpa.DPAUnconformityItemListForm"%>
<%@page import="presentation.translator.web.dpa.DPAUnconformityListForm"%>
<%@page import="presentation.translator.web.dpa.DPAUnconformityListAction"%>
<%@page import="communication.administration.dpa.UnconformityDPAData"%>
<%@page import="communication.administration.dpa.UnconformityItemTypeDPADataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="communication.driver.tables.TelematicControlData"%>
<%@page import="persistence.driver.tables.TelematicControl"%>
<%@page import="communication.administration.dpa.UnconformityTypeDPADataType"%>
<%@page import="presentation.translator.web.dpa.DPAUnconformityForm"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="communication.activity.tables.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleCheckData vehicleCheck;
	TelematicControlData telematicControl;
	VehicleData vehicleData;
	String idReferido;
	String mode;
	String id;
	UnconformityDPAData data;
%>
<%
	//PARA EDITAR id=488552&mode=edit, donde 488552 es el id de la inconformidad
	//PARA NUEVO  TELEMATICCONTROL telec=111&mode=new&type=UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL donde 111 es el id del control telematico
	//PARA NUEVO  CHECK check=111&mode=new&type=UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK donde 111 es el id del vehiclecheck

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	

	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	String check0 = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);
	
	if (check!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if (check1!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check1);	
	} else if (check2!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check2);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String href = (String)request.getParameter("href");
		id=(String)request.getParameter("id");
		
		DPAUnconformityItemListForm form=new DPAUnconformityItemListForm();
				 
		if(request.getAttribute("DPAUnconformityItemListForm")!=null){
	form=(DPAUnconformityItemListForm) request.getAttribute("DPAUnconformityItemListForm");
		}else if(session.getAttribute("DPAUnconformityItemListForm")!=null){
	form=(DPAUnconformityItemListForm) session.getAttribute("DPAUnconformityItemListForm");
		}
		
		session.setAttribute("DPAUnconformityItemListForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<script type="text/javascript">
 
 
</script>
 

<body>
	 
	<center>
	<html:form action="/DPAUnconformityItemList" styleId="DPAUnconformityItemList" >
		
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.dpa.unconformity.title");
			%>
				<jsp:include  page='<%=page14 %>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
		String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.unconformity");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.messages.datestart")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<jsp:include  page='<%="../component/dateComponent.jsp?index=1&day=startDay&month=startMonth&year=startYear&form=DPAUnconformityItemListForm" %>' />
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.messages.dateend")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<jsp:include  page='<%="../component/dateComponent.jsp?index=2&day=endDay&month=endMonth&year=endYear&form=DPAUnconformityItemListForm" %>' />
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.dpa.unconformity.datetype")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:select   property="dateType" styleClass="input">
								<html:option value='<%=String.valueOf(DPAUnconformityItemFilter.UNCONFORMITY)%>'><%=messages.getText("generic.activity.date") %></html:option>
								<html:option value='<%=String.valueOf(DPAUnconformityItemFilter.EXPIRY)%>'><%=messages.getText("generic.dpa.unconformity.termdate") %></html:option>
							</html:select>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.dpa.unconformity.type")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:select   property="unconformityType" styleClass="input">
								<html:option value='<%=UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK %>'><%=messages.getText( UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK) %></html:option>
								<html:option value='<%=UnconformityTypeDPADataType.TYPE_DRIVER %>'><%=messages.getText( UnconformityTypeDPADataType.TYPE_DRIVER) %></html:option>
							</html:select>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr style="visibility:hidden;"> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.dpa.unconformity.filter")%>
						</td>
						<td width="50%" align="right" class="linetwo">
						 <html:text style=""   property="text" styleClass="input"  />
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr><td colspan=2 align="right"><input name="button2" type="button" onclick="DPAUnconformityItemList.submit();;" value='<%=messages.getText("generic.messages.search")%>' class="input"/></td></tr>
				</table>
				
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		              
	</table>
	</html:form>
	</center>
	</body>
	
</html:html>
<%
	}
%>
