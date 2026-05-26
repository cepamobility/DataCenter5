<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="presentation.translator.web.dpa.DPAExportImportLevelOfServiceDriverForm"%>
<%@page import="communication.datatype.DataTypeConstants"%>
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
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"	
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleData vehicleData;
	String idReferido;
	String mode;
	String id;
%>
<%

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	

	
	
	String check = null;
	String check1 = null;	
	String check2 = null;	
	
	
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

		String href = (String)request.getParameter("href");
		id=(String)request.getParameter("id");
		
		DPAExportImportLevelOfServiceDriverForm form=new DPAExportImportLevelOfServiceDriverForm();
								 
		if(request.getAttribute("DPAExportImportLevelOfServiceDriverForm")!=null){
			form=(DPAExportImportLevelOfServiceDriverForm) request.getAttribute("DPAExportImportLevelOfServiceDriverForm");
		}else if(session.getAttribute("DPAExportImportLevelOfServiceDriverForm")!=null){
			form=(DPAExportImportLevelOfServiceDriverForm) session.getAttribute("DPAExportImportLevelOfServiceDriverForm");
		}
		
		session.setAttribute("DPAExportImportLevelOfServiceDriverForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script type="text/javascript">
			$(document).ready(
					function(){
							changeExportImport('<%=form.getExportImport()%>');
					}	
			);

			function changeExportImport(ei){
				if(ei=='e'){
					$('tr[tag="import"]').hide('slow');
					$('tr[tag="exportDriver"]').hide('slow');
					$('tr[tag="export"]').show('slow');
				}else{
					$('tr[tag="export"]').hide('slow');
					$('tr[tag="import"]').show('slow');
				}
			}

			function selV(t){
				if($(t).is(':checked')){
					$('tr[tag="exportDriver"]').hide('slow');
				}
			}
			
			function selD(t){
				if($(t).is(':checked')){
						$('tr[tag="exportDriver"]').show('slow');
				}
			}
			
	
			var itemDefault='<option value="0"><%=messages.getText("generic.messages.select")%></option>';	
			
			$(document).ready(
				function(){

					var val= $('#divOrg11')[0].value;
					if(val!='0'){
						loadDivOrg1();
					}
					val= '<%=form.getDivOrg2()%>'; 
					if(val!='0'){
						$('#divOrg12')[0].value=val;
						loadDivOrg2();
					}
					val= '<%=form.getDivOrg3()%>';
					if(val!='0'){
						$('#divOrg13')[0].value=val;
					}
					
					$('#divOrg11').change(loadDivOrg1);
				
					$('#divOrg12').change(loadDivOrg2);	


					function loadDivOrg2(){
						var val= $('#divOrg11')[0].value;
						var val2= $('#divOrg12')[0].value;
						$.ajax(
							{
								url:'DPAExportImportLevelOfServiceDriverAction.do?getItems=true&index=3&level1='+val+'&level2='+val2,
								async:false,
								success:function(data){
									$('#divOrg13').empty();
									$('#divOrg13').append(itemDefault);
									for(var i=0; i<data.length; i++){
										$('#divOrg13').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');	
									}
								}
							}
						);
					}
					
					function loadDivOrg1(){
						var val= $('#divOrg11')[0].value;
						$.ajax(
							{
								url:'DPAExportImportLevelOfServiceDriverAction.do?getItems=true&index=2&level1='+val,
								async:false,
								success:function(data){
									$('#divOrg12').empty();
									$('#divOrg12').append(itemDefault);
									$('#divOrg13').empty();
									$('#divOrg13').append(itemDefault);
									for(var i=0; i<data.length; i++){
										$('#divOrg12').append('<option value="'+data[i].id+'">'+data[i].name+'</option>');	
									}
								}
							}
						);
							
					}
				
				}
			);

			function exportImportSubmit(){
				$('#DPAExportImportLevelOfServiceDriverAction').submit();
			}

						
		</script>
</head>

<body>
	 
	<center>
	<html:form action="/DPAExportImportLevelOfServiceDriverAction" styleId="DPAExportImportLevelOfServiceDriverAction" method="post" enctype="multipart/form-data" >
		<html:hidden property="exportImport" value="e"/>
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.dpa.levelofservicedriver.title");
			%>
				<jsp:include  page='<%=page14 %>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
		String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.levelofservicedriver.exportimport");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				
					
   					<tr> 
       					<td width="50%" align="left" class="texts" colspan="2">        
   							<html:radio property="exportVehicleOrDriver" onchange="selV(this);" value="V"  ><%=messages.getText("generic.dpa.levelofservicedriver.exportvehicle") %></html:radio>
   							<html:radio property="exportVehicleOrDriver" onchange="selD(this);" value="D"  ><%=messages.getText("generic.dpa.levelofservicedriver.exportdriver") %></html:radio>
   						</td>
						
   					</tr>
   					
   					<tr><td colspan="2">&nbsp;</td></tr>
   					<tr tag="import"> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.dpa.levelofservicedriver.fileimport")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:file     property="file" styleClass="input"  />         
						</td>
   					</tr>
   					<tr  tag="import"><td colspan="2">&nbsp;</td></tr>
   	 				<tr tag="export"> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.messages.datestart")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="day" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="month" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="year" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr tag="exportDriver"> 
       					<td width="100%" align="left" class="texts" colspan="2">        
   							<html:radio property="telematicActive" onchange="" value="Ativo"  ><%="Controle Telematico Activo" %></html:radio>
   							<html:radio property="telematicActive" onchange="" value="Inativo"  ><%="Controle Telematico Inativo" %></html:radio>
   							<html:radio property="telematicActive" onchange="" value="T"  ><%="Controle Telematico Todos" %></html:radio>
   						</td>
					</tr>
   					
   					<tr  tag="export"><td colspan="2">&nbsp;</td></tr>
   					<tr  tag="export">
   						<td>
							<label for="divOrg11"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[0])%></label>
						</td>
						<td>	
							<html:select  styleId="divOrg11" property="divOrg1" >
									<html:option value="0"><%=messages.getText("generic.messages.select")%></html:option>
							 	<%	
							 		Iterator iter=sys.getUserSessionData().getFilter().getLevelItems(1, 0, null, null,communication.util.Filter.USER);
							 		while(iter.hasNext()){
							 			ItemData item=(ItemData) iter.next();	
								%>
									<html:option value="<%=item.getId().toString()%>"><%=item.getName()%></html:option>
								<%
							 		}
							 %>	
							</html:select>	
						</td>
					</tr>
					<tr  tag="export"><td colspan="2">&nbsp;</td></tr>	
					<tr  tag="export">
						<td>
							<label for="divOrg12"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[1])%></label>
						</td>
						<td>
							<html:select  styleId="divOrg12" property="divOrg2" ></html:select>
						</td>
					</tr>
					<tr  tag="export"><td colspan="2">&nbsp;</td></tr>	
					<tr  tag="export">
					<td>
					<label for="divOrg13"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[2])%></label>
					</td>
					<td>
						<html:select  styleId="divOrg13" property="divOrg3" ></html:select>
					</td>
					</tr>	
					<tr><td colspan=2 align="right"><input name="button2" type="button" onclick="exportImportSubmit();" value='<%=messages.getText("generic.messages.export")%>' class="input"/></td></tr>
				</table>
				
				<jsp:include  page="../component/utilityboxbottom.jsp" />
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
