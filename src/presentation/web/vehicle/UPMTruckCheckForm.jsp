<%@page import="communication.administration.dpa.UnconformityDPAData"%>
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
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if(request.getParameter("check")!=null && request.getParameter("reprocessUnconformities")!=null){
		UnconformityDPAData unconformity=sys.getCorporationReportFacade().findUnconformityDPAByCheckId(sys.getUserSessionData(), Integer.valueOf(request.getParameter("check")));
		if(unconformity==null){
	sys.getVehicleABMFacade().procesarCheckList(Integer.valueOf(request.getParameter("check")), sys.getText(), sys.getUserSessionData());
%>
			<%="No Conformidades reprocesadas...se enviaron las notificaciones"%>
			<%
		}else{
			%>
			<%="No se reprocesa, ya tiene no conformidad generada"%>
			<%
		}
	}else{
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String idVehicle = (String)request.getParameter("vehicleId");
		String checkId = (String)request.getParameter("check");
		session.setAttribute("id",idVehicle);

		VehicleReportFacade vehicleReport = sys.getVehicleReportFacade(); 
		vehicleCheck = vehicleReport.findVehicleCheck(new Integer(checkId),userSessionData);
		VehicleData vehicleData = vehicleCheck.getVehicle();

		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(new Integer(vehicleCheck.getName()),sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
		
		DPAVehicleCheckListEditForm form=new DPAVehicleCheckListEditForm();
		if(request.getAttribute("DPAVehicleCheckListEditForm")!=null){
			form=(DPAVehicleCheckListEditForm) request.getAttribute("DPAVehicleCheckListEditForm");
		}else{
			form.load(sys,vehicleCheck,vehicleData,checkList);
		}
		
		session.setAttribute("DPAVehicleCheckListEditForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
	 
	<center>
	<html:form action="/DPAVehicleCheckListEdit" >
		<input type="hidden" value="<%=checkId%>" name="check"/>
		<input type="hidden" value="<%=idVehicle%>" name="vehicleId"/>
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("checklist.dpa.title");
			%>
				<jsp:include  page='<%=page14 %>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
		String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.tranningonthejob.proofdata");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;" property="day" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;" property="month" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;" property="year" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="left" class="texts">        
       						<%= messages.getText("generic.vehicle.vehicledata.plate")%>
						</td>
						<td width="50%" align="right" class="linetwo">		
							<html:text style="width:180;" property="palet" disabled="true" styleClass="input" />&nbsp;    
        				</td>
     				</tr>
     				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
     				<tr> 
        				<td width="50%" align="right" class="linetwo" colspan="2">		
							<a href="layout.jsp?refer=/vehicle/UPMTruckCheckForm.jsp?reprocessUnconformities=true&check=<%=checkId%>&vehicleId=<%=vehicleData.getId()%>"> <%= "Re procesar no conformidades"%> </a>   
        				</td>
     				</tr>
<%		for(int i = 0; i < 3; i++) {
			PointData point = (PointData)points.next();
			Iterator items = point.getSortItems();
%>			
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr>
						<td colspan="2">
							<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText(point.getText())%>' />
							<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%			while(items.hasNext()) {
				ItemData item = (ItemData)items.next();
%>
			     				<tr> 
			        				<th width="50%" align="left" class="lineone">        
			       						<%= messages.getText(item.getText())%>
									</th>
									<td width="50%" align="right" class="lineone">		
										<html:text style="width:180;" property='<%="item["+item.getId().toString()+"]"%>' styleClass="input" />                                 
			        				</td>
			     				</tr>
<%			}
%>							</table>
							<jsp:include  page='../component/utilitybox2bottom.jsp' />
						</td>
					</tr>
				
<%		}
%>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%		boolean header = false;
		int pneusIndex = 0;
%>		<tr>
			<td valign="top" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
<%		String className = "linetwo";
		boolean first = true;
		while(points.hasNext()) {
			PointData point = (PointData)points.next();
			Iterator items = point.getSortItems();
			if(items.hasNext()) {
				ItemData item = (ItemData)items.next();
				int value = -1;
				String pointComment = null;
				if(vehicleCheck != null) {
					String checkValue = vehicleCheck.getValueFromItem(item.getId()).trim();
					value = (checkValue == null) ? -1 : Integer.parseInt(checkValue);
				}
				pointComment = vehicleCheck.getValueFromCommentPoint(point.getId());
				if(item.getType().intValue() == 1) {
					String page12 = "../component/utilityboxtop.jsp?title="+messages.getText(point.getText());
					if(first) {
						first = false;
					}else {
%>
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				  	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
<%					}
%>
					<tr>
						<td valign="top">
							<jsp:include  page='<%=page12 %>' />
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="left" class="guide2" width="40%"><%=messages.getText("syngenta.checklist.condition")%></td>
									<td align="center" class="guide2" width="20%"></td>
									<%if( (Integer.valueOf(vehicleCheck.getName())==CheckListData.UPM_CHECKLIST_PESADOS || Integer.valueOf(vehicleCheck.getName())==CheckListData.UPM_CHECKLIST_INTERNO)){ %>
									<td align="center" class="guide2" width="20%">TIEMPO REPARACIÓN</td>
									<%} %>
									<td align="left" class="guide2" width="20%"><%=messages.getText("syngenta.checklist.observations")%></td>
								</tr>				

<%				}
%>
								<tr>
									<td align="left" class="<%=className%>">
		   								<%=messages.getText(item.getText())%>
									</td>	
									<td align="center" class="<%=className%>">
										<html:radio property='<%="item["+item.getId().toString()+"]"%>' value="1"><%=checkId.equals("80")|| checkId.equals("84")? "S/O":"SI"%></html:radio>
										<html:radio property='<%="item["+item.getId().toString()+"]"%>' value="2"><%=checkId.equals("80") || checkId.equals("84")? "C/O":"NO" %></html:radio>
									</td>	
							
							<%if( (Integer.valueOf(vehicleCheck.getName())==CheckListData.UPM_CHECKLIST_PESADOS || Integer.valueOf(vehicleCheck.getName())==CheckListData.UPM_CHECKLIST_INTERNO)){ %>
									<td>
										<html:select property='<%="itemTag["+item.getId().toString()+"]"%>' style="width:250;" styleClass="input" >
										    
											<html:option value="-1">S/P</html:option>
											<html:option value="0">DETENCIÓN INMEDIATA</html:option>
											<html:option value="1">24hs</html:option>
											<html:option value="2">48hs</html:option>
											<html:option value="3">72hs</html:option>
											<html:option value="30">30días</html:option>
										</html:select>
										
									</td>
								<%} %>
									
									<td align="left" class="<%=className%>">
										<html:text property='<%="comment["+point.getId().toString()+"]"%>' style="width:250;" styleClass="input" />
									</td>
								</tr>
<%				className = (className.equals("linetwo")) ? "lineone" : "linetwo";
			}
		}
%>
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				  	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.observations")%>' />
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="left" class="linetwo" width="100%">
										<html:textarea property="obs"  cols="100" rows="5" />
									</td>
								</tr>
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				  	<tr>
						<jsp:include  page="../component/spaceline.jsp" />
			  		</tr>
					<tr>
						<td>
							<jsp:include  page='../component/utilityboxtop.jsp' />
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								
								<%if( (Integer.valueOf(vehicleCheck.getName())==CheckListData.UPM_CHECKLIST_PESADOS || Integer.valueOf(vehicleCheck.getName())==CheckListData.UPM_CHECKLIST_INTERNO)){ %>
								<tr class="linetwo">
									<th align="left">Lugar</th>
									<td>
										<html:text property='serie' style="width:250;" styleClass="input" />
									</td>
								</tr>
								<%} %>
								
								<tr class="linetwo">
									<th align="left"><%=messages.getText("generic.activity.result") %></th>
									<td>
										<html:radio property="result" value="1"><%="SIN FALTAS/FALLAS"%></html:radio>
										<html:radio property="result" value="3"><%="SIN FALTAS/FALLAS CON OBSERVACIONES"%></html:radio>
										<html:radio property="result" value="2"><%="CON FALTAS/FALLAS"%></html:radio>
									</td>
								</tr>
								<tr class="linetwo">
									<th align="left"><%=messages.getText("generic.checklist.inspector") %></th>
									<td>
										<html:text property='inspectedby' style="width:250;" styleClass="input" />
									</td>
								</tr>
							</table>
							<jsp:include  page='../component/utilityboxbottom.jsp' />
						</td>
					</tr>
				</table>
			</td>
		</tr>	
	  	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
       	<tr>   		
	   		<td align="right">
	   			<input name="button2" type="button" value='<%=messages.getText("generic.messages.cancel")%>' onClick="javascript:document.location.href='layout.jsp?refer=/vehicle/VehicleCheckViewDPA.jsp?check=<%=checkId%>&vehicle=<%=idVehicle%>';" class="input"/>	 		                             
	   			<input name="button2" type="submit" value='<%=messages.getText("generic.messages.save")%>' class="input"/>
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
