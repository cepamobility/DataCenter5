<%@page import="communication.administration.corporation.tables.CorporationData"%>
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

	
	
	String check0 = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	
	if (check!=null && check0!=null ) { 
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
		
		DPAUnconformityListForm form=new DPAUnconformityListForm();
				 
		if(request.getAttribute("DPAUnconformityListForm")!=null){
	form=(DPAUnconformityListForm) request.getAttribute("DPAUnconformityListForm");
		}else if(session.getAttribute("DPAUnconformityListForm")!=null){
	form=(DPAUnconformityListForm) session.getAttribute("DPAUnconformityListForm");
		}
		
		session.setAttribute("DPAUnconformityListForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<script type="text/javascript">
function submitPagination(page,all) {
	DPAUnconformityList.all.value = all;
	DPAUnconformityList.pageNum.value = page;
	DPAUnconformityList.submit();
}

function deleteUnconformityDPA(){
	var ids="";
	$('Input[type="checkbox"][tag="del"]').each(
		function(){
			if($(this).is(':checked')){
				if(ids!=""){
					ids+=",";
				}
				ids+=$(this).attr('idU');
			}

		}
	);
					
	if(ids!="" && confirm('<%=messages.getText("generic.dpa.unconformitydpa.delete")%>')){
			$.ajax(
					{
						url:'DPAUnconformityList.do?delete=yes&ids='+ids,
						async:true,
						success:function(){
							$('Input[type="checkbox"][tag="del"]').each(
								function(){
									if($(this).is(':checked')){
										$(this).parent().parent().remove();
									}
								}
							);

							document.location.reload();
						}
					}
			);
	}

}
</script>
 

<body>
	 
	<center>
	<html:form action="/DPAUnconformityList" styleId="DPAUnconformityList" >
		<html:hidden property="pageNum" styleId="pageNum"/>
		<html:hidden property="all" styleId="all"/>
		<html:hidden property="maxResult" styleId="maxResult"/>
		
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.dpa.unconformity.title");
			%>
				<jsp:include  page='<%=page14%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
	String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.unconformity");
%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.messages.datestart")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="startDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="startMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="startYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.messages.dateend")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;" property="endDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"   property="endMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"   property="endYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.unconformity.type")%>
						</td>
						<td width="50%" align="right" class="linetwo">
						<html:select   property="unconformityType">
							<html:option value='<%=UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK%>'><%=messages.getText( UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)%></html:option>
							<html:option value='<%=UnconformityTypeDPADataType.TYPE_DRIVER%>'><%=messages.getText( UnconformityTypeDPADataType.TYPE_DRIVER)%></html:option>
						</html:select>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.unconformity.filter")%>
						</td>
						<td width="50%" align="right" class="linetwo">
						 <html:text style=""   property="text" styleClass="input"  />
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr><td colspan=2 align="right"><input name="button2" type="button" onclick="submitPagination(1,false);" value='<%=messages.getText("generic.messages.search")%>' class="input"/></td></tr>
				</table>
				
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr> 
			<td height="40" colspan="3" valign="middle">

				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td  class="guide2"></td>
					<td  class="guide2"></td>
					<%
						if(form.getUnconformityType().equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)) {
					%>
						<td  class="guide2"><%=messages.getText("generic.messages.vehicle")%></td>
					<%
						}
					%>
					<%
						if(form.getUnconformityType().equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)) {
					%>
					<td  class="guide2"><%=messages.getText("driver.telematic.driver")%></td>
					<%
						}
					%>
					<%
						if(form.getUnconformityType().equals(UnconformityTypeDPADataType.TYPE_DRIVER)) {
					%>
					<td  class="guide2"><%=messages.getText("driver.telematic.driver")%></td>
					<%
						}
					%>
					<td  class="guide2"><%=messages.getText("generic.activity.date")%></td>
					<td  class="guide2"><%=messages.getText("generic.dpa.uncnformity.comment")%></td>
				</tr>	
					<%
							Iterator iter=form.getUnconformities().iterator();
														
														int i=0;
														while(iter.hasNext()){
															UnconformityDPAData item=(UnconformityDPAData)iter.next();
						%>
					<tr <%=(i++%2==0?"class='lineone'":"class='linetwo'")%> >
					
						<td width="5%" ><%
												if(!sys.getUserSessionData().isContractor()){
											%><input type="checkbox" tag="del" idU="<%=item.getId()%>"><%
												}
											%></td>
					
						<%
												if(item.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)){
											%>
						<td width="15%" align="left" class="texts">
							<%
								if(sys.isCorporation(CorporationData.DPA)){
							%>
							<a href='layout.jsp?refer=/vehicle/VehicleCheckViewDPA.jsp?check=<%=item.getCheck()%>'><%=messages.getText(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)%></a>
							<%
								}else{
							%>
								  <a href='layout.jsp?refer=/vehicle/VehicleCheckViewUPM.jsp?vehicle=<%=item.getRelatedInformation("id")%>&check=<%=item.getCheck()%>'><%=messages.getText(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)%></a>
							<%
								}
							%>
							
						</td>
						<td width="15%" align="left" class="texts">
							<a href='layout.jsp?refer=/vehicle/VehicleView.jsp?id=<%=item.getRelatedInformation("id")%>'><%=item.getRelatedInformation("matricula")%></a>        
   						</td> 
       					<%
        						}
        					%>	
						<%
								if(item.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)){
							%>
						<td width="25%" align="left" class="texts">
							<a href='layout.jsp?refer=driver/telematicControlDPA.jsp?id=<%=item.getTelematicControl().getId()%>'><%=messages.getText(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)%></a>
						</td>
						<td width="30%" align="left" class="texts">
							<a href='layout.jsp?refer=/driver/DriverView.jsp?id=<%=item.getTelematicControl().getDriver().getId()%>'><%=item.getTelematicControl().getDriver().getFirstName()+" "+item.getTelematicControl().getDriver().getLastName()%></a>        
   						</td> 
       					<%
        						}
        					%>	
       					<%
	       						if(item.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
	       					%>
       					<td width="15%" align="left" class="texts"></td>
						<td width="25%" align="left" class="texts">
							<a href='layout.jsp?refer=driver/DriverView.jsp?id=<%=item.getDriverId()%>'><%=item.getRelatedInformation("fullName")%></a>
						</td>
       					<%
       						}
       					%>	
						<td width="10%" align="left" class="texts">
							<a href="layout.jsp?refer=/dpa/DPAUnconformityView.jsp?id=<%=item.getId()%>&href=/dpa/DPAUnconformityList.jsp" ><%=Util.formatDate(item.getDate())%></a>        
   						</td>  
       					
       					<td width="30%" align="left" class="texts">        
   							<%= item.getComment()%>
						</td>
   					</tr>
   				
   					<%}%>
   					<%if(sys.getUserSessionData().isCepaUser()){ %>
   					<tr>
   						<td colspan="5" align="right">
   							<input name="button2" type="button" onclick="deleteUnconformityDPA();" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
   						</td>
   					</tr>
   					<%}%>
   	 			</table>
				
			</td>
		</tr>  
       	<tr>   		
	   		<td>
	   			<jsp:include page='<%="../util/pagination2.jsp?size="+form.getMaxResult()+"&num="+form.getPageNum()+"&total="+String.valueOf(form.getResult().getTotal())+"&all="+form.getAll()%>' />
	   	   	</td>             
   	  	</tr>              
	</table>
	</html:form>
	</center>
	</body>
	
</html:html>
<%
	form.resetUnconformities();
	}
%>
