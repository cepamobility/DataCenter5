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
	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if (check1!=null) { 
		response.sendRedirect(request.getContextPath()+check1);	
	} else if (check2!=null) { 
		response.sendRedirect(request.getContextPath()+check2);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String teleControl = (String)request.getParameter("telec");
		String checkId = (String)request.getParameter("check");
		String driverId = (String)request.getParameter("driverId");
		String unconformityType = (String)request.getParameter("type");
		mode = (String)request.getParameter("mode");
		String href = (String)request.getParameter("href");
		id=(String)request.getParameter("id");
		
		if(mode.equals("new")){
	if(unconformityType.equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL) ){
		//Unconformity, Telematic Control
		//telematicControl= sys.getDriverReportFacade().findTelematicControl(Integer.valueOf(teleControl), sys.getUserSessionData());
		idReferido=teleControl;
		data=sys.getCorporationReportFacade().findUnconformityDPAByTelematicControlId(sys.getUserSessionData(), Integer.valueOf(idReferido));
	}else if(unconformityType.equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)){
		//Unconformity, Vehicle Check
		/*VehicleReportFacade vehicleReport = sys.getVehicleReportFacade(); 
		vehicleCheck = vehicleReport.findVehicleCheck(new Integer(checkId),userSessionData);
		vehicleData = vehicleCheck.getVehicle();
		*/
		idReferido=checkId;
		data=sys.getCorporationReportFacade().findUnconformityDPAByCheckId(sys.getUserSessionData(), Integer.valueOf(idReferido));
	}else if(unconformityType.equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
		idReferido=driverId;
		//data=sys.getCorporationReportFacade().findUnconformityDPAByDriverId(sys.getUserSessionData(), Integer.valueOf(idReferido));
	}
		}
		
		DPAUnconformityForm form=new DPAUnconformityForm();
		form.setIdReferido(Integer.valueOf(idReferido!=null?idReferido:"0"));
		form.setUnconformityType(unconformityType);
		if(request.getAttribute("DPAUnconformityForm")!=null){
	form=(DPAUnconformityForm) request.getAttribute("DPAUnconformityForm");
		}else{
	if(mode.equals("edit")){
		form.load(sys, Integer.valueOf(id),data);
	}else if(mode.equals("new") && data!=null){
		form.load(sys, null,data);
	}else{
		Calendar cal=Calendar.getInstance();
		form.setDateDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		form.setDateMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
		form.setDateYear(String.valueOf(cal.get(Calendar.YEAR)));
	}
		}
		form.setHref(href.replaceAll("_", "&"));
		session.setAttribute("DPAUnconformityForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
	 
	<center>
	<html:form action="/DPAUnconformity" >
		<input type="hidden" value="<%=teleControl%>" name="telecControl"/>
		<input type="hidden" value="<%=checkId%>" name="checkId"/>
		<html:hidden property="unconformityType"/>
		<input type="hidden" value="<%=mode%>" name="mode"/>
		<html:hidden property="idReferido"/>
		<html:hidden property="href"/>
		<html:hidden property="id"/>
		<input type="hidden"  name="status"/>
		<input type="hidden" name="cancel" value="no" id="cancel"/>
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
   							<%= messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;" property="dateDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;" property="dateMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;" property="dateYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.dpa.uncnformity.comment")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:textarea style="width:600px;height:100px" property="comment" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
			 page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.unconformity.items");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr class="guide2">
					<td></td>
					<td><%=messages.getText("generic.dpa.unconformity.items")%></td>
					<td style="display: none;"><%=messages.getText("generic.dpa.unconformity.termdate")%></td>
					<td align="center" style="display: none;"><%= messages.getText("generic.dpa.uncnformity.notify")%></td>
					<td align="center"><%= messages.getText("generic.dpa.uncnformity.comment")%></td>
					</tr>
					<% Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.DPA_UNCONFORMITY_ITEM_TYPE, sys.getUserSessionData()).iterator();
						while(iter.hasNext()){
							UnconformityItemTypeDPADataType item=(UnconformityItemTypeDPADataType)iter.next();
					%>
					<tr>
						<td width="5%" align="left" class="texts">
							<html:checkbox property='<%="item["+ item.getResourceBundleId().substring(item.getResourceBundleId().lastIndexOf(".")+1)+"]"%>'></html:checkbox>        
   						</td> 
       					<td width="45%" align="left" class="texts">        
   							<%= messages.getText(item.getResourceBundleId())%>
						</td>
						<td width="5%"  align="left" class="texts" style="display: none;">
						<html:select property='<%="expirity["+ item.getResourceBundleId().substring(item.getResourceBundleId().lastIndexOf(".")+1)+"]"%>'>
						<% for(int i=1;i<121;i++){%>
							<html:option value='<%=String.valueOf(i) %>'><%=i%></html:option>
						<%}%>	   
						</html:select>
						</td>
						<td width="5%" align="center" class="linetwo" style="display: none;">
							<html:checkbox property='<%="notify["+item.getResourceBundleId().substring(item.getResourceBundleId().lastIndexOf(".")+1)+"]" %>'></html:checkbox>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:textarea style="width:300px;height:50px" property='<%="text["+ item.getResourceBundleId().substring(item.getResourceBundleId().lastIndexOf(".")+1)+"]"%>' styleClass="input"  />                
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<%}%>
   	 			</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
       	<tr>   		
	   		<td align="right">
	   			<input name="button2" type="button" onclick="cancel.value='yes';s.click();" value='<%=messages.getText("generic.messages.back")%>' class="input"/>
	   			<input name="button2" type="submit" id="s" value='<%=messages.getText("generic.messages.save")%>' class="input"/>
	       	</td>             
   	  	</tr>              
	</table>
	</html:form>
	</center>
	</body>
	
</html:html>
<%
	}
%>
