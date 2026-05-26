<%@page import="communication.datatype.tables.ActivityStateDataType"%>
<%@page import="persistence.administration.tables.LevelOfServiceDriverCourseDPA"%>
<%@page import="communication.administration.dpa.LevelOfServiceDriverCourseDPAData"%>
<%@page import="communication.administration.dpa.UnconformityTypeDPADataType"%>
<%@page import="communication.administration.dpa.UnconformityDPAData"%>
<%@page import="communication.administration.dpa.LevelOfServiceDriverDPAData"%>
<%@page import="java.net.URISyntaxException"%>
<%@page import="java.net.URI"%>
<%@page import="org.jboss.ws.metadata.config.EndpointProperty"%>
<%@page import="org.jboss.ws.core.StubExt"%>
<%@page import="org.jboss.ws.metadata.config.CommonConfig"%>
<%@page import="org.jboss.ws.metadata.umdm.EndpointMetaData"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="java.util.LinkedList"
	import="business.*"
	import="business.driver.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.util.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.corporation.tables.ItemData"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.UserSessionData"
	import="communication.driver.tables.*"
	import="communication.event.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
 	import="communication.util.DateValue"
 	import="communication.administration.RoleData"	
	import="communication.driver.tables.UnconformityData"
	import="ws.assessment.datacenter.AssessmentWSEJBService" 
	import="ws.assessment.datacenter.AssessmentWSEJB"
	import="javax.xml.ws.BindingProvider"
	import="ws.assessment.datacenter.CourseData"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html:html>

<%! 
	RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;
	SystemAccess sys; 
	DriverData data; 
	UserSessionData userSessionData;
	String id;
%>
<%
	try{

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData(); 
	int corporationId = sys.getCorporationId();	
	DriverReportFacade driverReport = sys.getDriverReportFacade();
	String check = null;
	
	if(userSessionData.isDriver()) {
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
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		if(userSessionData.isDriver()) {
		    data = userSessionData.getDriverData();
		    id = data.getId().toString();
	pathView="./view2.jsp";
		}else {
	id = (String)request.getParameter("id");
	if(id==null){
		id=(String)request.getAttribute("id");
		request.removeAttribute("id");
	}
	if(id==null){
		id=(String)session.getAttribute("id");
		session.removeAttribute("id");
	}
	if(id!=null){	
		data=driverReport.findDriverByPrimaryKey(new Integer(id), sys.getUserSessionData());
	}
	pathView="./view2.jsp?id="+id;
	
	// session.setAttribute("url","driver/DriverView.jsp?id="+id);
	userSessionData.setUrl("driver/DriverView.jsp?id="+id);
		}    
	
		if(data!=null && !(sys.isCorporation(CorporationData.FORESTALES) && data.getCorporation().getId().intValue() != CorporationData.FORESTALES)) {
	if(session.getAttribute("Breadcrumb") != null) {
	    Hashtable parameters = new Hashtable();
	    parameters.put("id",data.getId());
	    parameters.put("name",data.getFirstName()+" "+data.getLastName());
	    ((Breadcrumb)session.getAttribute("Breadcrumb")).addItem(/*Breadcrumb.DRIVER_EDIT*/1,parameters);
	}
	//session.setAttribute("target","DriverView");
	DriverForm driverForm=DriverUtil.createDriverForm(data,DriverUtil.VIEW,messages);
	session.setAttribute("DriverCreateForm",driverForm);
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="./menu/js/html2canvas.min.js"></script>
<script>

	function CanvasToBase64Image(canvas)
	{
		var ctx = canvas.getContext("2d");
		var dataURL = canvas.toDataURL("image/png");
		var r = dataURL;//.replace(/^data:image\/(png|jpg);base64,/, "");
		return r;
	}

	$(document).ready(
		function(){
			$("#linkpdfscreen").click(function(event ) {
  			    event.preventDefault();
  			    var sel=$("table#bdriver");
  			  var count = sel.length;
  			  for(i=0; i<count;i++){
	  			  		html2canvas(sel[i], {
			            onrendered: function(canvas) {
			                //document.body.appendChild(canvas);
			                 fd = new FormData();
							 xhr = new XMLHttpRequest();
							 fd.append('contents', CanvasToBase64Image(canvas));
							 fd.append('id', 0);
							 xhr.open('POST', 'Copy.do',false);
							 xhr.send(fd);

							 document.location.href="ImageToPDF.do";	
				        }
			        });
  			  }
  			  
  			
  		  			  
  			 		
			});
		}
	);
	
	function setAction(form,action){
		form.action.value=action;
	}
	function deleteIFConfirm(form,msg){
		form.submit();
	}
	function openReport(assessment,user,report,format) {
		var form = document.forms['AssessmentReportForm'];
		form.assessment.value = assessment;
		form.user.value = user;
		form.report.value = report;
		form.format.value = format;
		form.submit();
	}
	function openReport(assessment,user,report,format) {
		var form = document.forms['AssessmentReportForm'];
		form.assessment.value = assessment;
		form.user.value = user;
		form.report.value = report;
		form.format.value = format;
		form.submit();
	}
	function confirmDeleleTC() {
		var form = document.forms['DriverListTelematicControlForm'];
		var elements=form.elements;
		var algunaseleccionada = false;
		for(i=0; i<elements.length; i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					algunaseleccionada = true;
					break;
				}	
			} 
		}
		if(algunaseleccionada) {
			if(confirm('<%=messages.getText("driver.deletetelematic.confirm")%>')) {
				form.submit();
			}
		}
	}
	
	function deleteProvisionalBTW(idDriver,expiry,thiss){
		if(confirm('<%=messages.getText("generic.btwprovisional.delete")%>')){
			$(thiss).parent().parent().hide('slow');
			$.ajax(
					{
						url:'AddProvisionalBTW.do?deleteBTW=true&idDriver='+idDriver+'&expiry='+expiry,
						async:true,
						success:function(){}
					}
			);
		}else{
			
		}
	}
	
	function asociateForestal(corporation,msg) {
		if(confirm(msg)) {
			var form = document.forms['DriverForestalAsociateForm'];
			form.corporation.value = corporation;
			form.submit();
		}
	}
	
	function hrd(msg) {
		if(confirm(msg)) {
			document.forms['DriverHRDForm'].submit();
		}
	}
	function openCertificate(activity) {
		var form = document.forms['ActivityCertificateForm'];
		form.activity.value = activity;
		form.submit();
	}
<%if(data.isTemporal()) {%>
	function changeTemporal() {
		if(confirm('<%=messages.getText("driver.changetemporal.confirm")%>')) {
			document.forms['DriverChangeTemporalForm'].submit();
		}
	}
<%}%>
</script>
<head></head>
<body bgcolor="#FFFFFF" >


	<form action="layout.jsp?refer=/driver/DriverExclusion.jsp" name="exclusion" method="post">
		<input type="hidden" name="checkboxParamList" value='<%=String.valueOf(data.getId())%>' />		
		<input type="hidden" name="action" />		
	</form>
<%
	String linkEdit = (corporationId == CorporationData.BAYER && sys.getUserSessionData().isDriver()) ? "layout.jsp?refer=/driver/DriverEditBayer.jsp" : "layout.jsp?refer=/driver/DriverEdit.jsp";
%>
	<form action='<%=linkEdit%>' name='edit' method="post">
		<input type="hidden" name="id" value='<%=String.valueOf(data.getId())%>' />
		<!-- <input type="hidden" name="target" value="DriverView" /> -->
	</form>	
	<form action="layout.jsp?refer=/driver/DriverList.jsp" name='cancel' method="post">
		<!-- <input type="hidden" name="target" value="DriverView" /> -->
	</form>	
	<form action="layout.jsp?refer=/driver/DriverSearch.jsp" name='unificate' method="post">
		<input type="hidden" name="link" value='layout.jsp?refer=/driver/unificateDrivers.jsp?temp=<%=String.valueOf(data.getId())%>' />
	</form>	
	<html:form action="/AssessmentReport">
		<html:hidden property="assessment" />
		<html:hidden property="user" />
		<html:hidden property="report" />
		<html:hidden property="format" />
	</html:form>
	<html:form action="/AsociateForestal">
		<html:hidden property="id" value='<%=String.valueOf(data.getId())%>' />
		<html:hidden property="corporation" />
	</html:form>
	<html:form action="/DriverHRD">
		<html:hidden property="id" value='<%=String.valueOf(data.getId())%>' />
	</html:form>
		<html:form action="/ActivityCertificate"  method="post" >
			<html:hidden property="driver" value='<%=String.valueOf(data.getId())%>' />
			<html:hidden property="activity"/>
		</html:form>	
<%
		if(data.isTemporal()) {
	%>
	<html:form action="/DriverChangeTemporal">
		<html:hidden property="driver" value='<%=String.valueOf(data.getId())%>' />
	</html:form>
<%
	}
%>
	
	<table width="700" align='center' cellpadding="0" cellspacing="0" id="bdriver">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.driver.editing")+"  "+data.getLastName()+","+data.getFirstName()%>' />
		</tr>
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		
  		<!-- Opciones -->
  		<%
  			session.setAttribute("driver",data);
  		%>
  		<jsp:include  page="DriverViewOptions.jsp?id=<%=id%>" />
		<!--  Fin Opciones -->
  		  			
<%
  		  				if(data.getExclusionDate() != null) {
  		  			%>  	<tr>
			<td colspan="5">
				<span class="red"><%=messages.getText("generic.driver.excluded")%></span>
			</td>
  		</tr>
		<tr>
			<td colspan="5">
				<span class="red"><%="generic.driver.excluded:ExclusionDate"%>
				</span>				
			</td>
  		</tr>
		<tr>
 			<td align="left" colspan="5">
 				<span class="red"><%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
 				</span>
    		</td>
		</tr>
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	}
%>  	
	<!--  Es conductor Temporal ? Tablets -->
<%
	if(data.isTemporal()) {
%>  	<tr>
			<td colspan="5" align="left">
				<span class="red"><%=messages.getText("generic.driver.temporalDriver")%></span>
			</td>
  		</tr>				
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%
	}
%>

<!--  Datos Personales -->


		<tr>
			<td width="50%" valign="top">
				<table width="100%" height="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.personaldata")%>' />
							<jsp:include page='<%=pathView%>' />
			   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
					<tr>
						<td height="10"><img src="x" width="1" height="1">
					</tr>
<%
	if(corporationId != CorporationData.ALLERGAN) {
%>					<tr>
						<td width="30%" valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.divgeo")%>' />
<%
	if(data.getCountry() != null) {
	    			GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
%>
							<table width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(data.getCountry().getResourceKey())%> </th>
									<td align="right">
										<%=(geoItem1.getSubItems() != null) ? geoItem1.getName() : messages.getText(geoItem1.getName())%>
									</td>
								</tr>
<%
	if(geoItem1.getSubItems() != null) {
		    	GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
%>									
								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(geoItem1.getSubItems().getResourceKey())%> </th>
									<td align="right">
										<%=(geoItem2.getSubItems() != null) ? geoItem2.getName() : messages.getText(geoItem2.getName())%>
									</td>
								</tr>
						
<%
							if(geoItem2.getSubItems() != null) {
													    	GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
						%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(geoItem2.getSubItems().getResourceKey())%> </th>
									<td align="right">
										<%=messages.getText(geoItem3.getName())%>
									</td>
								</tr>
<%
	}
	}
		}
%>							</table>	
			   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			   			</td>
		   			</tr>
<%
	}else {
%>					<tr>
						<td width="30%" valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.hierarchicallevel")%>' />
							<table width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr  class="linetwo">
									<th align="left"> <%=messages.getText("generic.driver.driverdata.iddallas")%></th>
									<td align="right"><%="generic.driver.driverdata.iddallas:iddallas"%></td>
								</tr>
								<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
    							<tr class="linetwo">
        							<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
       								<td align="right">
       									<%="generic.driver.driverdata.bpsregistry:bpsregistry"%>
       								</td>
      							</tr>
								<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
    							<tr class="linetwo">
        							<th align="left"> <%=messages.getText("generic.driver.driverdata.charge")%></th>
       								<td align="right">
       									<%="generic.driver.driverdata.charge:AuthorizateWho"%>
       								</td>
      							</tr>
							</table>	
			   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			   			</td>
		   			</tr>
<%
	}
%>	
				<tr><td height="5"><img src="x" width="1" height="1"></tr>
			   	</table>
			</td>
			<td width="1%" valign="top"></td>
			<td width="50%" valign="top">
				<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<%
	Iterator it = data.getDivOrgsIterator();
	while(it.hasNext()) {		
		DivOrgData divOrgData = (DivOrgData)it.next();
		if(divOrgData.getDivOrgLevels() != null) {
	ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
%>					<tr>
						<td valign="top" width="50%">	
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
							<table width="100%"  cellpadding="0" cellspacing="0">
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(divOrgData.getDivOrgLevels().getName())%> </th>
									<td align="right"><%=item1.getName()%></td>
								</tr>
<%
	if(item1.getSubItems() != null) {
		ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
%>								<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item1.getSubItems().getName())%> </th>
									<td align="right"><%=item2.getName()%></td>
								</tr>
<%
	if(item2.getSubItems() != null) {
			ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
%>								<tr  class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
									<th align="left"> <%=messages.getText(item2.getSubItems().getName())%> </th>
									<td align="right"><%=item3.getName()%></td>
								</tr>
<%
	}
	}
%>
							</table>
   							<jsp:include page="../component/utilityboxbottom.jsp"/>	
	   					</td>
	   				</tr>
	   				<tr><td height="5"><img src="x" width="1" height="1"></tr>
<%
	}
	}
	if(corporationId == CorporationData.ALLERGAN) {
%>					<tr>
						<td width="30%" valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.complement")%>' />
							<table width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr  class="linetwo">
									<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
									<td align="left">
										<div align="right"><%="generic.driver.driverdata.officeaddress:OfficeAddress"%></div>
									</td>
								</tr>
								<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
					        		<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
				        			<td align="right">
					        			<%="generic.driver.driverdata.superviser:Superviser"%>
					        		</td>
			   					</tr>
				      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			   					<tr class="linetwo">
			       					<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
				        			<td align="right">
					        			<%="generic.driver.driverdata.manager:Manager"%>
					        		</td>
		      					</tr>
							</table>	
			   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			   			</td>
		   			</tr>
<%
	} else if(sys.isCorporation(CorporationData.DOW)) {
%>					<tr>
						<td width="30%" valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.complement")%>' />
							<table width="100%" height="100%" cellpadding="0" cellspacing="0">
								<tr  class="linetwo">
									<th align="left" > <%=messages.getText("generic.driver.driverdata.superviserid")%></th>
									<td align="right"><%="generic.driver.driverdata.superviserid:IdDallas"%></td>
								</tr>
								<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr  class="linetwo">
									<th align="left" > <%=messages.getText("generic.driver.driverdata.supervisername")%></th>
									<td align="right"><%="generic.driver.driverdata.supervisername:Bpsregistry"%></td>
								</tr>
								<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class="linetwo">
					        		<th align="left" > <%=messages.getText("generic.driver.driverdata.focalpointid")%></th>
				        			<td align="right">
					        			<%="generic.driver.driverdata.focalpointid:Superviser"%>
					        		</td>
			   					</tr>
				      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			   					<tr class="linetwo">
			       					<th align="left" > <%=messages.getText("generic.driver.driverdata.focalpointname")%></th>
				        			<td align="right">
					        			<%="generic.driver.driverdata.focalpointname:Manager"%>
					        		</td>
		      					</tr>
							</table>	
			   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			   			</td>
		   			</tr>
<%
	}
%>
				</table>	
			</td>
<%
	if(!sys.getUserSessionData().isDriver()) {
%>			<td width="1%" valign="top"></td>
			
		</tr>	
<%
		}
	%>		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		
		<tr>
			<td width="48%" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.workinfo")%>' />
				<table width="100%" align='center' cellpadding="0" cellspacing="0">
<%
	if(sys.isCorporation(CorporationData.DPA)){
%>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.cpf")%></th>
						<td align="right"><%="generic.driver.driverdata.cpf:IdDallas"%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.rg")%></th>
						<td align="right"><%="generic.driver.driverdata.rg:Bpsregistry"%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%
	}	
	if(corporationId == CorporationData.CIMSA) {
%>	      			<tr class="linetwo">
	        			<th align="left" > <%=messages.getText("generic.driver.driverdata.points")%></th>
	        			<td align="right">
	        				<%=(data.getPoints() == null) ? "--" : String.valueOf(data.getPoints().intValue() - sys.getEventReportFacade().getEventsPoints(data.getId(),sys.getUserSessionData()).intValue())%>
	        				<%="generic.driver.driverdata.points:String.valueOf(data.getPoints().intValue() - sys.getEventReportFacade().getEventsPoints(data.getId(),sys.getUserSessionData()).intValue()"%>
	        			</td>
	      			</tr>
	      			
<%
	      				}
	      				      				if(sys.isCorporation(CorporationData.MONSANTO)) {
	      			%>	      			
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr class="linetwo">
		        		<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
	        			<td align="right">
		        			<%=(data.getSuperviser() == null) ? "--" : data.getSuperviser()%>
		        			<%="generic.driver.driverdata.superviser:Superviser"%>
		        		</td>
   					</tr>
	      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   					<tr class="linetwo">
       					<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
	        			<td align="right">
		        			<%="generic.driver.driverdata.manager:Manager"%>
		        		</td>
		      		</tr>
				    
<%
				    	}
				    %>				
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>	
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.corporationid")%></th>
						<td align="right">
						<%="generic.driver.driverdata.corporationid:corporationid"%>
						</td>
					</tr>
					
<%
						if(sys.isCorporation(CorporationData.JJ)) {
					%>	      			
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr class="linetwo">
		        		<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
	        			<td align="right">
		        			<%="generic.driver.driverdata.superviser:Superviser"%>
		        		</td>
   					</tr>
	      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   					<tr class="linetwo">
       					<th align="left" > <%=messages.getText("generic.driver.driverdata.manager")%></th>
	        			<td align="right">
		        			<%="generic.driver.driverdata.manager:manager"%>
		        		</td>
		      		</tr>
	      			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   					<tr class="linetwo">
       					<th align="left" > <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
	        			<td align="right">
		        			<%="generic.driver.driverdata.bpsregistry:bpsregistry"%>
		        		</td>
		      		</tr>
<%
	}
	if (corporationId == CorporationData.PEPSICO){   
	// TODO:
%>
					<!--  Pespisco: Puesto del condctor dentro de la empresa-->
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.puestopepsico")%></th>
						<td align="right">
						<%="generic.driver.driverdata.puestopepsico:puesto"%>
						</td>
					</tr>

<%
	}
%>
					
					
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.startdate")%></th>
						<td align="right">
						<%="generic.driver.driverdata.startdate:InclusionDate"%>
						</td>
					</tr>
			      	<tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
<%
	if(sys.isCorporation(CorporationData.DOW)) {
%>					
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
						<td align="right"><%="generic.driver.driverdata.email:email"%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
						<td align="right"><%="generic.driver.driverdata.phonenumber:phonenumber"%></td>
					</tr>
<%
	} else {
	if(corporationId != CorporationData.CIMSA) {
%>					
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.email")%></th>
						<td align="right"><%="generic.driver.driverdata.email:email"%></td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.phonenumber")%></th>
						<td align="right"><%="generic.driver.driverdata.phonenumber:PhoneNumber"%></td>
					</tr>
<%
	if(sys.isCorporation(CorporationData.DPA)){
%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.mobile")%></th>
						<td align="right"><%="generic.driver.driverdata.mobile:Superviser"%></td>
					</tr>
<%
	}	
	if(sys.isCorporation(new int[]{CorporationData.BAYER,CorporationData.SYNGENTA})){
		// Bayer releva los datos: coordinador y su email (officeaddress)
%>
				   	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>	
					<tr class="linetwo">
		        		<th align="left" > <%=messages.getText("generic.driver.driverdata.iddallas")%></th>
		        		<td align="right"><%="generic.driver.driverdata.iddallas:IdDallas"%></td>
		     		</tr>
				   	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>	
					<tr class="linetwo">
		        		<th align="left" > <%=messages.getText("generic.driver.driverdata.superviser")%></th>
		        		<td align="right"><%="generic.driver.driverdata.superviser:Superviser"%></td>
		     		</tr>
				 
<%
				 	} 
				 		 	if (sys.getCorporationId() != CorporationData.PEPSICO && corporationId != CorporationData.ALLERGAN){
				 %>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
						<td align="left">
							<div align="right"><%="generic.driver.driverdata.officeaddress:OfficeAddress"%></div>
						</td>
					</tr>
<%
	}
%>
<%
	}

	if(!sys.isCorporation(CorporationData.SYNGENTA)) {
		if (sys.getCorporationId() != CorporationData.PEPSICO && corporationId != CorporationData.ALLERGAN){
%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.drives")%></th>
						<td align="left">
							<div align="right"><%="generic.driver.driverdata.drives:Drives"%></div>
						</td>
					</tr>
<%
	}
%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.employee")%></th>
						<td align="left"><%="generic.driver.driverdata.employee:employee"%>
%>						</td>
					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.contact")%></th>
						<td align="left">
							<div align="right"><%="generic.driver.driverdata.contact:contact"%></div>
						</td>
					</tr>
<%
	}else {
%>					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"><%=messages.getText("generic.driver.driverdata.manager")%></th>
					    <td align="right">
		        			<%="generic.driver.driverdata.manager:manager"%>
					    </td>
					</tr>
<%
	}
	if(corporationId == CorporationData.ASTRAZENECA) {
%>		        <!--  Si es AZ uso authorizatewho como Cargo" -->             
		              <tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		              <tr  class="linetwo">
		                <th align="left"> <%=messages.getText("generic.driver.driverdata.authorizatewho")%></th>
		                <td align="right">
		                	<%="generic.driver.driverdata.authorizatewho:AuthorizateWho"%>
		               </td>
		              </tr>
<%
	}	
              if(corporationId == CorporationData.PEPSICO) {
            	  // Si es Pepsico uso OfficeAddess como Nombre de la persona que genera el vinculo
            	  // del conductor a la empresa. "Nombre del Vínculo"
%>              
              <tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.officeaddress")%></th>
                <td align="right"><%="generic.driver.driverdata.officeaddress:OfficeAddress"%>
                </td>
              </tr>   
              
              <!-- Quien autoriza la Alta y fecha en que fue solicitada -->
				<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.authorizatewho")%></th>
                <td align="right"><%="generic.driver.driverdata.authorizatewho:AuthorizateWho"%>
                </td>
              </tr>
              <tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
              <tr  class="linetwo">
                <th align="left"> <%=messages.getText("generic.driver.driverdata.authorizatedate")%></th>
                <td align="right">
                	<%="generic.driver.driverdata.authorizatedate:AuthorizateDate"%>
                </td>
                		
              </tr>      
                   
              
<%
                                       	}
                                       %>     
					
					
<%
     											if(corporationId == CorporationData.MONSANTO || corporationId == CorporationData.BAYER) {
     										%>					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("corporation.monsanto.assignedvehicles")%></th>
						<td align="left">
							<%="corporation.monsanto.assignedvehicles:AssignedVehicle"%>
						</td>
					</tr>
<%
	}
%>


	<%
		if (corporationId == CorporationData.MONSANTO ||
			corporationId == CorporationData.ANGLO){
	%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.iddallas")%></th>
						<td align="right"> <%="generic.driver.driverdata.iddallas:IdDallas"%></td>
					</tr>
	<%
		}
	%>


<%
	if(sys.getUserSessionData().isForest() || corporationId == CorporationData.ASTRAZENECA) {
%>					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">
        				<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
        				<td align="right">
							<div align="right"><%="generic.driver.driverdata.bpsregistry:Bpsregistry"%></div>
        				</td>
      				</tr>
<%
	}
		if(corporationId == CorporationData.BAYER) {
%>					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">
        				<th align="left"> <%=messages.getText("generic.driver.driverdata.bpsregistry")%></th>
        				<td align="right">
							<div align="right"><%="generic.driver.driverdata.bpsregistry:Bpsregistry"%></div>
        				</td>
      				</tr>
<%
	}
		if(corporationId == CorporationData.LOSPIQUES || corporationId == CorporationData.PACIFICRUBIALES) {
%>					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      				<tr class="linetwo">
        				<th align="left"> <%=messages.getText("generic.driver.driverdata.healthExpiry")%></th>
						<td align="right"><%="generic.driver.driverdata.healthExpiry:HealthExpiry"%></td>
      				</tr>
<%
	}
		}
%>        		</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
			<td width="1%" valign="top"></td>
			<td width="48%" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.licenseinfo")%>' />
				<table width="100%" align='center' cellpadding="0" cellspacing="0">
<%
	if(corporationId == CorporationData.CIMSA) {
%>					<tr class="linetwo">
	        		<th align="left" > <%=messages.getText("generic.driver.driverdata.externlicense")%></th>
	        			<td align="right">
	        				<%="generic.driver.driverdata.externlicense:OfficeAddress"%>
	        			</td>
	      			</tr>
      				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
	      			<tr class="linetwo">
	      				<td colspan="2">
							<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.driver.internlicense")%>' />
							<table width="100%">
<%
	String checks = driverForm.getLicenseCategory();
	for(int j = 1; j <= 7; j++) {
		if(checks.charAt(j-1) == '1') {
%>								<tr>
									<td class="input"><%=messages.getText("driver.internlicense.option"+j)%></td>		
								</tr>
<%
	}
	}
%>							</table>
							<jsp:include  page="../component/utilitybox2bottom.jsp" />
	      				</td>
	      			</tr>
      				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
 					<tr class="linetwo">
    					<th align="left"> <%=messages.getText("generic.driver.driverdata.healthExpiry")%></th>
						<td align="right"><%="generic.driver.driverdata.healthExpiry:healthExpiry"%></td>
    	  			</tr>
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%
	}
%>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licensenumber")%></th>
						<td align="right"><%="generic.driver.driverdata.licensenumber:LicenseNumber"%></td>
					</tr>
<%
	if(!sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.ALLERGAN})) {
%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseget")%></th>
						<td align="right"><%="generic.driver.driverdata.licenseget:LicenseGet"%></td>
					</tr>
			      	<tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
	           			</td>
	      			</tr>
<%
	}
%>					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					
<%
						if (driverForm.getLicenseNotExpiry() != null && driverForm.getLicenseNotExpiry().equalsIgnoreCase("on")){
					%>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%></th>
						<td align="right">
							<%="generic.driver.driverdata.licensenotexpiry:LicenseNotExpiry"%>
						</td>
					</tr>
<%
	}else if (driverForm.getLicenseExpiry() != null){
%>
					<tr  class="linetwo">
						<th align="left" > <%=messages.getText("generic.driver.driverdata.licenseexpiry")%></th>
						<td align="right">
							<%="generic.driver.driverdata.licenseexpiry:LicenseExpiry"%>
						</td>
					</tr>
			      	<tr  class="linetwo">
	        			<td align="right" colspan="2">
	        				<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
            			</td>
	      			</tr>
<%
	}
		if(!sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.CIMSA})) {
%>				
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.Licensecategory")%></th>
						<td align="right"><%="generic.driver.driverdata.licensecategory:Licensecategory"%></td>
					</tr>
<%
	if (corporationId == CorporationData.PEPSICO){
%>
					<!-- Entidad Federativa, PESPSICO -->
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.federativeentity")%></th>
						<td align="right"><%="generic.driver.driverdata.federativeentity:FederativeEntity"%></td>
					</tr>
					<!-- Cobertura de la Licencia, PEPSICO -->
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.licensecoverage")%></th>
						<td align="right"><%="generic.driver.driverdata.licensecoverage:LicenseCoverage"%></td>
					</tr>
<%
	} 
	if(corporationId != CorporationData.ALLERGAN) {
%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.vehicleallowed")%></th>
						<td align="right"><%="generic.driver.driverdata.vehicleallowed:VehicleAllowed"%></td>
					</tr>
<%
	}	
		}
		if(corporationId == CorporationData.MONSANTO) {
%>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr  class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.licenseobs")%></th>
					</tr>
					<tr  class="linetwo">
						<td align="left"><%="generic.driver.driverdata.licenseobs:LicenseObs"%></td>
					</tr>
<%
	}
%>				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
<%
	if(corporationId == CorporationData.BAYER) {
%>				<br>	
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.hrd")%>' />
				<table width="100%" height="100%" cellpadding="0" cellspacing="0">
					<tr  class="linetwo"> 
						<th align="left"> <%=messages.getText("generic.driver.driverdata.nohrd")%></th>
						<td align="right"><%="generic.driver.driverdata.nohrd:NoHrd"%></td>
					</tr>
					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<tr class="linetwo">
						<th align="left"> <%=messages.getText("generic.driver.driverdata.nohrdcomment")%></th>
   						<td align="right"><%="generic.driver.driverdata.nohrdcomment:NoHrdComment"%></td>
					</tr>
				</table>	
   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
<%
		}
	%>

			</td>
		</tr>
		
		
		<!--  Historial -->
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.history")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
					                <td width='12%' class="guide2" align="center"><%=messages.getText("generic.event.eventdata.date")%></td>
					                <td width='18%'	class="guide2" align="center"><%=messages.getText("generic.event.eventdata.type")%></td>
					                <td width='24%'	class="guide2" align="center"><%=messages.getText("generic.event.eventdata.clasification")%></td>
									<td width='35%'	class="guide2" align="center"><%=messages.getText("driver.history.result")%></td>
<%
	if(sys.isCorporation(CorporationData.FORESTALES)) {
%>  								<td width='20%'	class="guide2" align="center"><%=messages.getText("generic.corporation")%></td>
<%
	}
%>								</tr>
<%
	Collection total = new LinkedList();
	if(!sys.getUserSessionData().getRole().getName().equals(RoleData.CDS)) {
		total.addAll(sys.getEventReportFacade().getDriverViewData("ACCIDENT",data.getId(),sys.getUserSessionData()));
		total.addAll(sys.getEventReportFacade().getDriverViewData("INCIDENT",data.getId(),sys.getUserSessionData()));
		total.addAll(sys.getEventReportFacade().getDriverViewData("FINE",data.getId(),sys.getUserSessionData()));
		total.addAll(sys.getEventReportFacade().getDriverViewData("NEARMISS",data.getId(),sys.getUserSessionData()));
	}			
	total.addAll(sys.getActivityReportFacade().findActivityByDriver(data.getId(),sys.getUserSessionData(),true));
	// eLearnings
	// total.addAll(sys.getElearningReportFacade().findElearningsByDriver(data.getId(),sys.getUserSessionData(),true));
	
	
	
	if(total.size() == 0){
%>								<tr>
									<td colspan="4">
										<span class="linetwo"><%=messages.getText("generic.event.nohistory")%></span>
									</td>								
								</tr>
<%
	}else {
	
		Collections.sort((List)total);	
		Iterator itTotal = total.iterator();
    			boolean lineone = false;
    			while(itTotal.hasNext()) {
    				Object next =itTotal.next();
%>								<tr   class='<%=(lineone) ? "lineone" : "linetwo"%>'>						        	
<%
						        		if (next instanceof EventDataType){
						        					        	    					EventDataType event = (EventDataType)next;
						        					        	 						if(event != null && event.getAccident()!=null && event.getIncident()!=null){
						        	%>								    <td width='12%' align="center">
										<a href='layout.jsp?refer=/event/FineView.jsp?fine=<%=event.getId()%>' > <%=Util.formatDate(event.getDate())%></a>
									</td>
          							<td width='18%' align="center"><%=messages.getText("generic.event.fine")%> </td>
          							<td width='25%' align="center">
              							 <%=messages.getText(event.getIncident().getResourceBundleId())%> 
					              	</td>
					              	<td width='40%' align="center">
					              		<%=messages.getText(event.getAccident().getResourceBundleId())%>
					              	</td>	
<%
		} else if (event != null && event.getIncident()!= null){
	%>									<td width='12%' align="center">
										<a href='layout.jsp?refer=/event/EventView.jsp?eventId=<%=event.getId()%>' > <%=Util.formatDate(event.getDate())%></a>
									</td>
									<td width='18%' align="center"><%=messages.getText("generic.event.incident")%> </td>
          							<td width='25%' align="center">
              							 <%=(Util.empty(event.getIncident().getResourceBundleId()))?"---":messages.getText(event.getIncident().getResourceBundleId())%> 
					              	</td>
					              	<td width='40%' align="center">
<%
	if(event.isDamage()){
%>										<%=messages.getText("query.accidenttype.damage")%>
<%
	}else{
%>										<%=messages.getText("driver.history.withoutconsequences")%>
<%
	}
%>					              	</td>
<%
	} else if (event != null && event.getAccident()!= null){
%>									<td width='12%' align="center">
										<a href='layout.jsp?refer=/event/EventView.jsp?eventId=<%=event.getId()%>' > <%=Util.formatDate(event.getDate())%></a>
									</td>
									<td width='18%' align="center"><%=messages.getText("generic.event.accident")%> </td>
          							<td width='25%' align="center">
              							 <%=(Util.empty(event.getAccident().getResourceBundleId()))?"---":messages.getText(event.getAccident().getResourceBundleId())%> 
					              	</td>
					              	<td width='40%' align="center">
<%
	if(event.isDamage()){
%>										<%=messages.getText("query.accidenttype.damage")%>
<%
	}else{
%>										<%=messages.getText("driver.history.withoutconsequences")%>
<%
	}
%>					              	</td>
<%
	} else if (event != null && event.getNearmiss()!= null){
%>									<td width='12%' align="center">
										<a href='layout.jsp?refer=/event/EventView.jsp?eventId=<%=event.getId()%>' > <%=Util.formatDate(event.getDate())%></a>
									</td>
									<td width='18%' align="center"><%=messages.getText("generic.event.nearmiss")%> </td>
          							<td width='25%' align="center">
              							 <%=(Util.empty(event.getNearmiss().getResourceBundleId()))?"---":messages.getText(event.getNearmiss().getResourceBundleId())%> 
					              	</td>
									<td width='40%' align="center">
<%
	if((event.getDriverstate()!=null && event.getDriverstate().equals("datatype.driverstate.dead"))||(event.getFatalitiesfunccount()!=null && event.getFatalitiesfunccount().intValue()!=0)||(event.getFatalitiesothercount()!= null && event.getFatalitiesothercount().intValue()!=0)){
%>					              		<%=messages.getText("event.eventdata.functionaries.state.dead")%>
<%
	}else if((event.getDriverstate()!=null && event.getDriverstate().equals("datatype.driverstate.injured"))||(event.getInjuredfunccount()!=null && event.getInjuredfunccount().intValue()!=0)||(event.getInjuredothercount()!= null && event.getInjuredothercount().intValue()!=0)){
%>										<%=messages.getText("event.eventdata.functionaries.state.injured")%>
<%
	}else if(event.isDamage()){
%>										<%=messages.getText("query.accidenttype.damage")%>
<%
	}else{
%>										<%=messages.getText("driver.history.withoutconsequences")%>
<%
	}
%>					              	</td>
<%
	}
		if(sys.isCorporation(CorporationData.FORESTALES)) {
%>  								<td align="center"><%=event.getCorporationName()%></td>
<%
	}
    				}else{
    					CEPAActivityScheduleRegistryData activity = (CEPAActivityScheduleRegistryData)next;
    					String note = "";
    					if(activity.getActivity().getState() != null && !activity.getActivity().getState().getResourceBundleId().equals(ActivityStateDataType.DICTADA)) {
    						note = "<span class='required'>"+messages.getText(((CEPAActivityScheduleData)activity.getActivity()).getState().getResourceBundleId())+"</span>";
    					} else if (activity.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE)) {
    						note = messages.getText("generic.messages.notapply");
    					} else {
    						note = messages.getText(activity.getNote());
    					}
    					String href = CEPAActivityConstants.getViewLink(activity, String.valueOf(data.getId()), sys);
%>									<td width='12%' align="center">
<%
	if(activity.getActivity().getState() != null && activity.getActivity().getState().getResourceBundleId() != null && activity.getActivity().getState().getResourceBundleId().equals(ActivityStateDataType.DICTADA)) {
%>										<a href='javascript:openCertificate(<%=String.valueOf(activity.getActivity().getActivityId())%>);'>
											<img alt="Certificado" src="menu/images/certificate.jpg" height="15" >
										</a>
<%
	}
%>										<a href='<%=href%>'>
											<%=Util.formatDate(activity.getActivity().getDate())%>
										</a>
									</td>
<%
	if (!activity.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE)){
%>									<td width='18%' align="center"><%=messages.getText("web.activity.view")%> </td>
          							<td width='25%' align="center">
              							 <%=messages.getText(activity.getActivity().getType().getResourceBundleId())%> 
					              	</td>
					              	<td width='40%' align="center">
					              		<%=note%>
					              	</td>	
<%
		if(sys.isCorporation(CorporationData.FORESTALES)) {
	%>  								<td align="center"><%=activity.getCorporationName()%></td>
<%
	}
			if(activity.getActivity().getType().getResourceBundleId().equalsIgnoreCase(CEPAActivityConstants.BTW_PROVISIONAL)){
%>									<td>
										<input type="button" onclick='deleteProvisionalBTW("<%=data.getId()%>","<%=Util.formatDate(activity.getActivity().getDate())%>",this);' value='<%=messages.getText("generic.messages.delete")%>' />
									</td>
<%
	}
 						}else{
%>									<td width='18%' align="center"><%=messages.getText("driver.history.commentarydrive")%> </td>
          							<td width='25%' align="center">
              							 <%=messages.getText(activity.getConductionType())%> 
					              	</td>
					              	<td width='40%' align="center">
						              	<%=note%>
					              	</td>
<%
	if(sys.isCorporation(CorporationData.FORESTALES)) {
%>  								<td align="center"><%=activity.getCorporationName()%></td>
<%
	}
		}
    				}
	lineone = !lineone;
%>								</tr>
<%
	}
	}
%>
    						</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
<%
				if(false) {
					int evitables = 0;
					int inevitables = 0;
					it = sys.getEventReportFacade().getEventEvitability(new Integer(id),userSessionData).iterator();
					while(it.hasNext()) {
						Object[] data = (Object[])it.next();
						if(data[0].equals(Text.YES)) {
							evitables = ((Integer)data[1]).intValue();
						}
						if(data[0].equals(Text.NO)) {
							inevitables = ((Integer)data[1]).intValue();
						}
					}
			%>		
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.evitability")%>' />
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width='50%' class="linetwo" align="left">
							<%=messages.getText("driver.evitability.yes")+": "+evitables%>
						</th>
					    <th width='50%' class="linetwo" align="left">
					    	<%=messages.getText("driver.evitability.no")+": "+inevitables%>
					    </th>
					</tr>
				</table>
				<jsp:include page="../component/utilityboxbottom.jsp"/>
			</td>
		</tr>	
<%
		}
		if(sys.isCorporation(CorporationData.FORESTALES)) {
			Integer[] links = driverReport.getDriverLink(new Integer(id), sys.getUserSessionData());
			String[] corporations = {"9","19","50"};
			for(int i = 0; i < 3; i++) {
	%>		
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
		</tr>
		<tr>
			<td width="100%" colspan="5">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.forestales.corporation"+String.valueOf(i+1))%>' /> 
<%
 	if(links[i] == null) {
  			String msg = messages.getText("forestal.asociate.confirm");
 %>				<table cellpadding="2" cellspacing="2" width="100%">
					<tr class="linetwo">
						<td align="left"><%=messages.getText("driver.forestales.nolink")%></td>
						<td align="right"><input type="button" class="input" value='<%=messages.getText("driver.forestales.asociatecorporation")%>' onclick="asociateForestal(<%=corporations[i]%>,'<%=msg%>')"/></td>
					</tr>
				</table>	
<%
		}else {
			DriverMainAttributes driver = (i == 1) ? driverReport.findDriverForestalData(links[i], 3, userSessionData) : driverReport.findDriverForestalData(links[i], 1, userSessionData);
			if(driver != null) {
				String name = driver.getFirstName()+" "+driver.getLastName();
				if(driver.getExclusionDate() != null) {
					name += " <span class='required'>("+messages.getText("driver.data.inactive")+")</span>"; 
				}
	%>				<table cellpadding="2" cellspacing="2" width="100%">
					<tr class="linetwo">
						<td align="left"><%=name%></td>
						<td align="right"><%=driver.getDivOrgItem2()%></td>
					</tr>
					<tr class="linetwo">
						<td align="left"><%=driver.getDivOrgItem1()%></td>
					</tr>
				</table>	
<%
		}
		}
	%>				
				<jsp:include page="../component/utilityboxbottom.jsp"/>
			</td>
		</tr>	
<%
		}
		}
		if(!sys.isCorporation(new int[]{CorporationData.FORESTALES,CorporationData.ALLERGAN,CorporationData.SAEXPLORATION,CorporationData.FEMSA})) {
	%>		
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
<%
	String title = "../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.assessments");
%>							<jsp:include  page="<%=title%>" />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
					                <td width='40%' class="guide2" align="left"><%=messages.getText("driver.assessment.name")%></td>
					                <td width='20%' class="guide2" align="center"><%=messages.getText("driver.assessment.status")%></td>
					                <td width='20%' class="guide2" align="center"><%=messages.getText("driver.assessment.result")%></td>
					                <td width='20%' class="guide2" align="center"><%=messages.getText("driver.assessment.errors")%></td>
								</tr>
<%
	try // Capturamos errores de comunicacion con el webservice
	{
		
		
		AssessmentWSEJBService service = new AssessmentWSEJBService();
		AssessmentWSEJB port = service.getAssessmentWSEJBPort();
	
		// Seteamos la URL del WebService Remoto:
		String endpointURL = SecurityConstants.WS_ASSESSMENT_DC5;
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		EndpointMetaData endpointMetaData = ((StubExt) port).getEndpointMetaData();
	    CommonConfig commonConfig = endpointMetaData.getConfig();
	    boolean hacked = false;
	    try {
	        if (commonConfig.getProperties() != null){
	            Iterator<EndpointProperty> iter = commonConfig.getProperties().iterator();
	            while (iter.hasNext()){
	                EndpointProperty p = iter.next();
	                if (p.name.equals(new URI(EndpointProperty.CHUNKED_ENCODING_SIZE))){
	                    p.value = "0";
	                    hacked = true;
	                    
	                }

	            }
	        }
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }

	    if (!hacked)commonConfig.addProperty(EndpointProperty.CHUNKED_ENCODING_SIZE, "0");
		    
		Collection assessments = port.getUserAssessments(new Integer(id));
	
		if(assessments != null) {
	it = assessments.iterator();
	while(it.hasNext()) {
		CourseData attribute = (CourseData)it.next();
%>								<tr class="linetwo">
									<td align="left">		
										<%=attribute.getName()%>
									</td>
									<td align="center">		
										<%=(attribute.isTestComplete()) ? messages.getText("driver.assessment.complete") : messages.getText("driver.assessment.incomplete")%>
									</td>
									<td align="center">
										<a href="<%="javascript:openReport("+String.valueOf(attribute.getId())+",'"+attribute.getUser()+"',1,2);"%>">HTML</a>
										-
										<a href="<%="javascript:openReport("+String.valueOf(attribute.getId())+",'"+attribute.getUser()+"',1,1);"%>">PDF</a>
									</td>
									<td align="center">
										<a href="<%="javascript:openReport("+String.valueOf(attribute.getId())+",'"+attribute.getUser()+"',2,2);"%>">HTML</a>
										-
										<a href="<%="javascript:openReport("+String.valueOf(attribute.getId())+",'"+attribute.getUser()+"',2,1);"%>">PDF</a>
									</td>
								</tr>	
<%
		}
		}
			}catch(Exception e){
		e.printStackTrace();
			}
	%>	    					</table>	    					
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
<%
				}	
						if(sys.isCorporation(new int[] {CorporationData.MONSANTO,CorporationData.ANGLO,CorporationData.DPA})) {
					Collection telematics = sys.getDriverReportFacade().findTelematicControls(new Integer(id),sys.getUserSessionData());
			%>		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<html:form action="/TelematicControlAction" method="post">
				<table width="100%" cellpadding="0" cellspacing="0" id="telematicControl">
					<tr>
						<td valign="top">
							<html:hidden property="driver" value="<%=id%>"/>
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.telematiccontrol.list")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								
<%
									if(telematics.size() == 0) {
								%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("driver.aptitudtest.nodata")%></span>
									</td>								
								</tr>
<%
	}else {
%>
								 <tr>
<%
	if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER) || sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {
%>	            				
									<td width='4%' class="guide2" align="center">
									</td>
<%
	}	
		if(!sys.isCorporation(CorporationData.DPA)) {
%>
								 	<td width="20%" class="guide2" align="center">
								 		<%=messages.getText("driver.telematic.color")%>
								 	</td>
<%
	}
%>
								 	<td width="20%" class="guide2" align="center">
								 		<%=messages.getText("driver.telematic.type")%>
								 	</td>
								 	<td width="20%" class="guide2" align="center">
								 		<%=messages.getText("driver.telematic.vehicle")%>
								 	</td>
								 	<td width="18%" class="guide2" align="center">
								 		<%=messages.getText("driver.telematic.date")%>
								 	</td>
<%
	if(!sys.isCorporation(CorporationData.DPA)) {
%>
								 	<td width="18%" class="guide2" align="center">
								 		<%=messages.getText("driver.telematic.time")%>
								 	</td>
<%
	}
%>
								 </tr>							 	
								 	
<%
							 									 		Iterator itTelematic = telematics.iterator();
							 									 						 									 	    			boolean lineone = false;
							 									 						 									 	    			while(itTelematic.hasNext()) {
							 									 						 									 	    				TelematicControlData control = (TelematicControlData)itTelematic.next();
							 									 						 									 	    				if (control != null) {
							 									 						 									 	    					String color = "---";
							 									 						 									 	    					String type = "---";
							 									 						 									 	    					switch(control.getType().intValue()) {
							 									 						 									 						case 0:
							 									 						 									 							color = TelematicControlData.COLOR_GREEN;
							 									 						 									 							type = "---";
							 									 						 									 							break;
							 									 						 									 						case 6:
							 									 						 									 							color = TelematicControlData.COLOR_RED;
							 									 						 									 							type = "driver.telematiccontrol.hrd";
							 									 						 									 							break;
							 									 						 									 						case TelematicControlData.TYPE_EXCESSIVE_SPEED:
							 									 						 									 							color = control.getColor();
							 									 						 									 							type = "driver.telematiccontrol.excessivespeed";
							 									 						 									 							break;
							 									 						 									 						default:
							 									 						 									 							type = control.getStringType();
							 									 						 									 							break;
							 									 						 									 	    					}
							 									 	%>							<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
<%
	if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {
%>	            				<td align="center">
									<html:checkbox property="deleteIds" value="<%=control.getId().toString()%>"></html:checkbox>
								</td>
<%
	}	
		if(!sys.isCorporation(CorporationData.DPA)) {
%>				                <td align="center">
							 		<%=messages.getText(color)%>
							 	</td>
<%
	}
%>			                <td align="center">
							 		<%=messages.getText(type)%>
							 	</td>
				                <td align="center">
							 		<%=(control.getVehicle() != null) ? control.getVehicle().getMatricula() : "---"%>
							 	</td>
							 	<td align="center">
								 	<a href='<%=(sys.isCorporation(CorporationData.DPA)) ? "layout.jsp?refer=driver/telematicControlDPA.jsp?id="+String.valueOf(control.getId()) : "layout.jsp?refer=driver/telematicControl.jsp?id="+String.valueOf(control.getId())%>'>
								 		<%=(control.getDate() != null) ? control.getDateString():"---"%>
					                </a>
							 	</td>
<%
	if(!sys.isCorporation(CorporationData.DPA)) {
%>							 	<td align="center">
							 		<%=(control.getTime() != null) ? control.getTime() : "---"%>
							 	</td>
<%
	}
%>							</tr>
<%
	}
	lineone = !lineone;
	   			}
	}
	if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {
%>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
					  		</tr>
			   				<tr>
			      				<td class="linetwo" align="right" colspan="8">
 			     					<input type="button" value='<%=messages.getText("generic.messages.delete")%>' onclick="confirmDeleleTC();" class="input" />
	            				</td>
			  				</tr>
<%
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			   	</html:form>
			</td>
		</tr>			
<%
				}

						if(sys.getUserSessionData().isForest()) {
			%>		
		<!-- Test de aptitud, Historico -->
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.modify.aptitudtest")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='25%' class="guide2" align="center">
									
										<%=messages.getText("driver.aptitudtest.date")%>
									</td>
   									<td width='25%'	class="guide2" align="center">
   										<%=messages.getText("driver.aptitudtest.apt")%>
   									</td>
				    				<td width='25%'	class="guide2" align="center">
				    					<%=messages.getText("driver.aptitudtest.expiry")%>
				    				</td>
<%
	if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
%>				    				<td width='25%'	class="guide2" align="center">
				    					<%=messages.getText("driver.aptitudtest.type")%>
				    				</td>
<%
	}else if(sys.isCorporation(new int[]{CorporationData.MONTES_DE_PLATA, CorporationData.FORESTALORIENTAL, CorporationData.FORESTALES})) {
%>				    				<td width='5%'	class="guide2" align="center">
				    				</td>
<%
	}
%>								</tr>
<%
	Collection tests = data.getAptitudTest();
	Collections.sort((List)tests);
	if(tests == null || tests.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("driver.aptitudtest.nodata")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itTests = tests.iterator();
    			boolean lineone = false;
    			while(itTests.hasNext()) {
    			    AptitudTestData test = (AptitudTestData)itTests.next();
    			    String isApt = (test.isApt()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
    			    String isAptLabelKey = (test.isApt()) ? "driver.aptitudtest.apt" : "driver.aptitudtest.aptdriver.aptitudtest.noapt";
%>									<tr align="center" align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
						                <td width='25%' align="center">
						                
						                <a href='AptitudTestCertificate.do?driver=<%=id%>&date=<%=Util.formatDate(test.getDate())%>&result=<%=isAptLabelKey%>&expirity=<%=Util.formatDate(test.getExpiry())%>&ci=<%=driverForm.getCorporationId()%>'>
						                	<%=Util.formatDate(test.getDate())%> 
						                </a>
						                
						                </td>
<%
	if(test.isPending()) {
		isApt = messages.getText("generic.messages.pending");
	}
%>					  		            <td width='25%' align="center"><%=isApt%> </td>
						                <td width='25%' align="center"><%=Util.formatDate(test.getExpiry())%> </td>						                
<%
						                	if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
						                %>				    					<td width='25%'	align="center">
				    						<%=(test.isExternal()) ? messages.getText("driver.aptitudtest.external") : messages.getText("driver.aptitudtest.cepa")%>
				    					</td>
<%
	}else if(sys.isCorporation(new int[]{CorporationData.MONTES_DE_PLATA, CorporationData.FORESTALORIENTAL, CorporationData.FORESTALES})) {
%>				    					<td align="center">
					    					<a href='layout.jsp?refer=/driver/SendAptitudTest.jsp?idDriver=<%=id%>&date=<%=Util.formatDate(test.getDate())%>&result=<%=isAptLabelKey%>&expirity=<%=Util.formatDate(test.getExpiry())%>'>
												<img alt="Enviar reporte" src="menu/images/send.jpg" height="15" >
						                	</a>
						                </td>
<%
	}
%>									</tr>
<%
	lineone = !lineone;
	   			}
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>	
		
		<!-- Control de Alcholemia, Historico -->		
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.modify.alcoholcontrol")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='49%' class="guide2" align="center">
										<%=messages.getText("driver.alcoholcontrol.date")%>
									</td>
   									<td width='49%'	class="guide2" align="center">
   										<%=messages.getText("driver.alcoholcontrol.result")%>
   									</td>
								</tr>
<%
	Collection controls = data.getAlcoholControl();
	if(controls.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("driver.alcoholcontrol.nodata")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itControls = controls.iterator();
    			boolean lineone = false;
    			while(itControls.hasNext()) {
    			    AlcoholControlData control = (AlcoholControlData)itControls.next();
%>									<tr align="center" align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
						                <td width='50%' align="center"><%=Util.formatDate(control.getDate())%> </td>
						                <td width='50%' align="center"><%=(control.getResult().intValue() == DriverData.POSITIVE) ? messages.getText("generic.messages.positive") : messages.getText("generic.messages.negative")%> </td>
									</tr>
<%
	lineone = !lineone;
	   			}
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>
  				
<%
  					if(corporationId == CorporationData.TRANSPORTES_AMERICAN) {
  				%>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
			<tr>
				<td width="100%" colspan="5">
					<form name="checks">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<%
									String page1 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.unconformities");
								%>
								<jsp:include  page='<%=page1%>' />
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td width='20%' class="guide2" align="center">
											<%=messages.getText("driver.prizes.date")%>
										</td>
   										<td width='30%'	class="guide2" align="left">
   											<%=messages.getText("driver.unconformities.type")%>
   										</td>
   										<td width='50%'	class="guide2" align="left">
   											<%=messages.getText("driver.unconformities.observations")%>
   										</td>
									</tr>
<%
	Collection unconformities = sys.getDriverReportFacade().getUnconformities(data.getId(),userSessionData);
		if(unconformities.size() == 0) {
%>									<tr>
										<td colspan="5">
											<span class="linetwo"><%=messages.getText("driver.unconformities.nodata")%></span>
										</td>								
									</tr>
<%
	}else {
    		Iterator itUnconformities = unconformities.iterator();
    		boolean lineone = false;
    		while(itUnconformities.hasNext()) {
    			UnconformityData unconformity = (UnconformityData)itUnconformities.next();
%>									<tr class="<%=(lineone) ? "lineone" : "linetwo"%>">
					            		<td align="center"><%=Util.formatDate(unconformity.getDate())%> </td>
					            		<td align="center"><%=messages.getText(unconformity.getType().getResourceBundleId())%> </td>
					            		<td align="left"><%=unconformity.getObservations()%> </td>
									</tr>
<%
	lineone = !lineone;
	   		}
		}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
	   				</tr>
	   			</table>
	   			</form>
			</td>
		</tr>			
<%
				}
			%>		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.modify.prizes")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='32%' class="guide2" align="center">
										<%=messages.getText("driver.prizes.date")%>
									</td>
   									<td width='32%'	class="guide2" align="center">
   										<%=messages.getText("driver.prizes.type")%>
   									</td>
				    				<td width='32%'	class="guide2" align="center">
				    					<%=messages.getText("driver.prizes.mount")%>
				    				</td>
								</tr>
<%
	Collection prizes = data.getPrizes();
	if(prizes.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("driver.prizes.nodata")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itPrizes = prizes.iterator();
    			boolean lineone = false;
    			while(itPrizes.hasNext()) {
    			    DriverPrizeData prize = (DriverPrizeData)itPrizes.next();
%>									<tr align="center" align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
						                <td width='32%' align="center"><%=Util.formatDate(prize.getDate())%> </td>
						                <td width='32%' align="center"><%=(prize.getType().intValue() == DriverPrizeData.PRIZE) ? messages.getText("driver.prize.prize") : messages.getText("driver.prize.fine")%> </td>
						                <td width='32%' align="center"><%=String.valueOf(prize.getMount())%> </td>						                
									</tr>
<%
	lineone = !lineone;
	   			}
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
<%
				}
						if(!sys.isCorporation(new int[]{CorporationData.SAEXPLORATION,CorporationData.FEMSA})) {
			%>		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.vehicles")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='32%' class="guide2" align="center">
										<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
									</td>
   									<td width='32%'	class="guide2" align="center">
   										<%=messages.getText("generic.vehicle.vehicledata.year")%>
   									</td>
				    				<td width='32%'	class="guide2" align="center">
				    					<%=messages.getText("generic.vehicle.vehicledata.country")%>
				    				</td>
								</tr>
<%
	Collection vehicles = sys.getVehicleReportFacade().findDriversVehicles(data.getId(), sys.getUserSessionData());
	if(vehicles.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("generic.event.novehicles")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itVehicles = vehicles.iterator();
    			boolean lineone = false;
    			while(itVehicles.hasNext()) {
    			    VehicleMainAttributes vehicle = (VehicleMainAttributes)itVehicles.next();
    			    if(!sys.isCorporation(CorporationData.BAYER) || vehicle.getExclusionDate() == null) {
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='32%' align="center">
	              							<a href='layout.jsp?refer=/vehicle/VehicleView.jsp?id=<%=vehicle.getId()%>' > <%=vehicle.getMatricula()%></a>
	              							
              							</td>
						                <td width='32%' align="center"><%=vehicle.getYear()%> </td>
						                <td width='32%' align="center"><%=messages.getText(vehicle.getCountryName())%> </td>						                
									</tr>
<%
	lineone = !lineone;
    			    }
	   			}
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.vehiclehistory")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='32%' class="guide2" align="center">
										<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
									</td>
   									<td width='32%'	class="guide2" align="center">
   										<%=messages.getText("query.messages.from")%>
   									</td>
				    				<td width='32%'	class="guide2" align="center">
				    					<%=messages.getText("query.messages.to")%>
				    				</td>
								</tr>
<%
	vehicles = sys.getVehicleReportFacade().findDriversVehicleHistory(sys.getUserSessionData(), data.getId());
	if(vehicles.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("generic.event.novehicles")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itVehicles = vehicles.iterator();
    			boolean lineone = false;
    			while(itVehicles.hasNext()) {
    			    String[] vehicle = (String[])itVehicles.next();
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='32%' align="center">
	              							<a href='layout.jsp?refer=/vehicle/VehicleView.jsp?id=<%=vehicle[0]%>' > <%=vehicle[1]%></a>
	              							
              							</td>
						                <td width='32%' align="center"><%=vehicle[2]%> </td>
						                <td width='32%' align="center"><%=vehicle[3]%> </td>						                
									</tr>
<%
	lineone = !lineone;
	   			}
	}
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
<%
				}
			%>
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<%
  			if(corporationId==CorporationData.DPA && Util.checkPermission(sys, SecurityConstants.VIEW_DRIVER_LIST)==null){
  		%>
  		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.unconformity.title")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='20%' class="guide2" align="center">
										<%=messages.getText("generic.driver.observation.date")%>
									</td>
   									<td width='60%' class="guide2" align="center">
										<%=messages.getText("generic.dpa.uncnformity.comment")%>
									</td>
   									<td width='20%' class="guide2" align="center">
										&nbsp;
									</td>
   									
								</tr>
<%
	Collection unconformities=sys.getCorporationReportFacade().findUnconformityDPAByDriverId(sys.getUserSessionData(), Integer.valueOf(id));
	if(unconformities.size() == 0) {
%>								<tr>
									<td >
										<span class="linetwo"><%="----"%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itLOS = unconformities.iterator();
    			boolean lineone = false;
    			while(itLOS.hasNext()) {
    				UnconformityDPAData un = (UnconformityDPAData)itLOS.next();
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='20%' align="center">
	              							 <a href='layout.jsp?refer=/dpa/DPAUnconformityForm.jsp?driverId=<%=id%>&mode=edit&id=<%=un.getId()%>&type=generic.unconformity.type.driver&href=/driver/DriverView.jsp?id=<%=id%>' >  <%=Util.formatDate(un.getDate())%></a>
	              						</td>
	              						<td width='60%' align="center">
	              							   <%=un.getComment()%> 
	              						</td>
	              						<td width="20%">
	              						<a href="javascript:document.location.href='layout.jsp?refer=/dpa/DPASendUnconformityDriver.jsp?unid=<%=un.getId()%>';" class="texts">
											<%=messages.getText("generic.messages.sendpdf")%>
										</a>     
								</td>
						           </tr>
<%
	lineone = !lineone;
	   			}
	}
%>			
										
							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>
		 
  		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.levelofservicedriver")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='32%' class="guide2" align="center">
										<%=messages.getText("generic.activity.date")%>
									</td>
									<td width='32%' class="guide2" align="center">
										<%=messages.getText("generic.dpa.levelofservicedriver.inspector")%>
									</td>
   									
								</tr>
<%
	Collection collLOS=sys.getCorporationReportFacade().findLevelOfServiceDriversDPAByDriverId(sys.getUserSessionData(), Integer.valueOf(id));
	if(collLOS.size() == 0) {
%>								<tr>
									<td >
										<span class="linetwo"><%=messages.getText("generic.dpa.levelofservicesdriver.nodata")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itLOS = collLOS.iterator();
    			boolean lineone = false;
    			while(itLOS.hasNext()) {
    				LevelOfServiceDriverDPAData level = (LevelOfServiceDriverDPAData)itLOS.next();
%>									<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          							<td width='32%' align="center">
	              							<a href='layout.jsp?refer=/dpa/DPALevelOfServiceDriverForm.jsp?id=<%=level.getId()%>' > <%=Util.formatDate(level.getDate())%></a>
	              						</td>
	              						<td width='32%' align="center">
	              							 <%=level.getInspector()%></a>
	              						</td>
						           </tr>
<%
	lineone = !lineone;
	   			}
	}
%>			
									<tr>
	          							<td align="right" colspan="2">
	              							<input class="input" type="button" onclick="document.location.href='layout.jsp?refer=/dpa/DPALevelOfServiceDriverForm.jsp?driverId=<%=id%>'" value='<%=messages.getText("generic.messages.new")%>'/>
	              						</td>
						           </tr>				
							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			</td>
		</tr>			
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  <%
  	if(corporationId == CorporationData.DPA ) {
  %>			
		<tr>
			<td width="100%" colspan="5" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.levelofservicedriver.cepa")%>' />
				<table width="100%" height="100%" cellpadding="0" cellspacing="0">
					<tr  class="guide2"> 
						<th align="left">Curso</th>
						<td align="left">Data</td>
						<td align="left">Observação</td>
					</tr>
					<%
						LevelOfServiceDriverDPAData levelData=sys.getCorporationReportFacade().findLevelOfServiceDriverDPAByDriverId(sys.getUserSessionData(), Integer.valueOf(id));
												if(levelData!=null){
													
													Iterator<LevelOfServiceDriverCourseDPAData> courses=levelData.getCourse().iterator();
													while(courses.hasNext()){
														LevelOfServiceDriverCourseDPAData course=courses.next();
					%>
							<tr class="linetwo">
     							<th align="left"> <%=messages.getText(course.getCourse())%></th>
      							<td align="left"><%=Util.formatDate(course.getDate())%></td>
      							<td align="left"><%=course.getComment()%></td>
   							</tr>
   					<%
   						}    }
   					%>		
				</table>	
   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
   			</td>
  			</tr>
  		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>	
<%
		}
	%>		
  		
  		<%
		  		  			}
		  		  		%>
  		
<%
  			if(!sys.getUserSessionData().isDriver()) {
  		%>  		
		<tr>
			<td width="100%" colspan="5">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.observations")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='24%' class="guide2" align="center">
										<%=messages.getText("generic.driver.observation.date")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
									</td>
									<td width='24%' class="guide2" align="center">
										<%=messages.getText("generic.driver.observation.action")%>
									</td>
   									<td width='24%'	class="guide2" align="center">
   										<%=messages.getText("generic.driver.observation.user")%>
   									</td>
				    				<td width='24%'	class="guide2" align="center">
				    					<%=messages.getText("generic.driver.observation.comment")%>
				    				</td>
								</tr>
<%
	Collection observations = sys.getDriverReportFacade().getObservations(data.getId(), sys.getUserSessionData());
		if(observations.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("generic.event.noobservations")%></span>
									</td>								
								</tr>
<%
	}else {
    				Iterator itObservations = observations.iterator();
    				boolean lineone = false;
    				while(itObservations.hasNext()) {
    			    	ObservationData observation = (ObservationData)itObservations.next();
%>								<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
	          						<td width='24%' align="center">
              							<%=Util.formatDate(observation.getDate())%>
              						</td>
<%						switch(observation.getAction().intValue()) {
							case LogActions.CREATE:
%>						            <td width='24%' align="center"><%=messages.getText("generic.log.create")%> </td>
<%								break;
							case LogActions.UPDATE:
%>						            <td width='24%' align="center"><%=messages.getText("generic.log.update")%> </td>
<%								break;
							case LogActions.DELETE:
%>						            <td width='24%' align="center"><%=messages.getText("generic.log.delete")%> </td>
<%								break;
							case LogActions.INCLUDE:
%>						            <td width='24%' align="center"><%=messages.getText("generic.log.include")%> </td>
<%								break;
							case LogActions.EXCLUDE:
%>						            <td width='24%' align="center"><%=messages.getText("generic.log.exclude")%> </td>
<%								break;
						}
%>						            <td width='24%' align="center"><%=observation.getUser()%> </td>						                
						            <td width='24%' align="left"><%=(observation.getComment()==null)?"----":observation.getComment()%> </td>						                
								</tr>
<%    					lineone = !lineone;
	   				}
				}
					
%>							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
					<tr>
						<jsp:include  page="../component/spaceline.jsp" />
					</tr>
<%			}
%>


		<!-- Opciones -->
  		<% session.setAttribute("driver",data); %>
  		<jsp:include  page='<%="DriverViewOptions.jsp?id=" + id %>' />
		<!--  Fin Opciones -->
					
		</table>
		<br>
		<br>
		
<%		}else {
%>		
			<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%		}
	}
%>
	</body>
<%
	}catch(Exception e){
		e.printStackTrace();
	}
%>
</html:html>
