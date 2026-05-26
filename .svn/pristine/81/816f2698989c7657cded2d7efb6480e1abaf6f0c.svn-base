<%@page import="communication.datatype.tables.GeneralDTData"%>
<%@page import="communication.administration.dpa.LevelOfServiceDriverCourseDPAData"%>
<%@page import="communication.administration.dpa.LevelOfServiceDriverDPAData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@page import="presentation.translator.web.dpa.DPALevelOfServiceDriverForm"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
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
	import="java.util.*"	

%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	DriverData driverData;
	String driverId;
	String id;
	
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	

	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_DRIVER);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_DRIVER);	
	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if (check1!=null) { 
		response.sendRedirect(request.getContextPath()+check1);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		
		id=(String)request.getParameter("id");
		driverId=(String)request.getParameter("driverId");
		
		DPALevelOfServiceDriverForm form=new DPALevelOfServiceDriverForm();
		if(request.getAttribute("DPALevelOfServiceDriverForm")!=null){
	form=(DPALevelOfServiceDriverForm)request.getAttribute("DPALevelOfServiceDriverForm");
		}else if(driverId!=null || id!=null){
	form.load(sys, id!=null?Integer.valueOf(id):null, driverId!=null?Integer.valueOf(driverId):null);
		}
		
		id=form.getId()!=null?form.getId().toString():"0";
		driverId=form.getDriverId()!=null?form.getDriverId().toString():"0";
		session.setAttribute("DPALevelOfServiceDriverForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
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
						url:'DPALevelOfServiceDriverAction.do?getItems=true&index=3&level1='+val+'&level2='+val2,
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
						url:'DPALevelOfServiceDriverAction.do?getItems=true&index=2&level1='+val,
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

	function addCourse(){

		var course=$('#selCourse').val();
		var courseTxt=$('#selCourse option:selected').text()
		
		var comment=$('#commentCourse').val();
		var day=$('#dayCourse').val();
		var month=$('#monthCourse').val();
		var year=$('#yearCourse').val();	

		var str="<td> <input type='button' value='Deletar' onclick='$(this).parent().parent().remove();'/></td>";
		$('#tblCoursesH').after("<tr tag='rowCourse'><td tagC='"+course+"'>"+courseTxt+"</td><td>"+day+"-"+month+"-"+year+"</td><td>"+comment+"</td>"+str+"</tr>");	
	}

	function lSubmit(){

		var str="";
		$("tr[tag='rowCourse']").each(
			function(){
				if(str!=""){
						str+="_";
				}
				var child=$(this).children();
				var course=$(child[0]).attr('tagC');
				var date=child[1].innerHTML.replace('-',';').replace('-',';');
				var comment=child[2].innerHTML;
				str+=course+"#"+date+"#"+comment;
			}
		);	

		courses.value=str;
			
		DPALevelOfServiceDriverAction.submit();
	}
	</script>
</head>

<body>
	 
	<center>
	<html:form action="/DPALevelOfServiceDriverAction" styleId="DPALevelOfServiceDriverAction" enctype="multipart/form-data" >
		<html:hidden property="id"/>
		<html:hidden property="driverId"/>
		<html:hidden property="courses" styleId="courses"/>
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.dpa.levelofservice.title");
			%>
				<jsp:include  page='<%=page14%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
	String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.levelofservice.section1");
%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="dateDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="dateMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="dateYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.inspector")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:300;"   property="inspector" styleClass="input" />
							                
							
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.status")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:radio style="width:30;" disabled="true"   property="status" styleClass="input" value="A" ><%=messages.getText("generic.dpa.levelofservicedriver.status.active")%></html:radio>
							<html:radio style="width:30;" disabled="true"  property="status" styleClass="input" value="I" ><%=messages.getText("generic.dpa.levelofservicedriver.status.inactive")%></html:radio>                
							
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.corporationid")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text disabled="true" style="width:300;"   property="corporationId" styleClass="input" />
							                
							
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.firstName")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text disabled="true" style="width:300;"   property="firstName" styleClass="input" />
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.lastName")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text disabled="true" style="width:300;"   property="lastName" styleClass="input" />
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.plant")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text style="width:300;"   property="plant" styleClass="input" />
						</td>
   					</tr>
   					<tr style="display:none;"><td colspan=2>&nbsp</td></tr>
   					<tr style="display:none;" >
   						<td width="50%" align="left" class="texts">
							<label for="divOrg11"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[0])%></label>
						</td>
						<td width="50%" align="right" class="linetwo">	
							<html:select disabled="true" styleId="divOrg11" property="divOrg1" >
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
					<tr style="display:none;"><td colspan="2">&nbsp;</td></tr>	
					<tr style="display:none;">
						<td width="50%" align="left" class="texts">
							<label for="divOrg12"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[1])%></label>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:select disabled="true" styleId="divOrg12" property="divOrg2" ></html:select>
						</td>
					</tr>
					<tr style="display:none;"><td colspan="2">&nbsp;</td></tr>	
					<tr style="display:none;">
					<td width="50%" align="left" class="texts">
					<label for="divOrg13"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[2])%></label>
					</td>
					<td width="50%" align="right" class="linetwo">
						<html:select disabled="true" styleId="divOrg13" property="divOrg3" ></html:select>
					</td>
					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.function")%>
						</td>
						<td width="50%" align="right" class="linetwo">
						<html:radio style="width:30;"  property="function"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="function"  value='<%=LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="function" value='<%=LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_2_LABEL)%></html:radio> 
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.celular")%>
						</td>
						<td width="50%" align="right" class="linetwo">
						<html:text disabled="true" style="width:300;"   property="celular" styleClass="input" /> 
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.birthdate")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text disabled="true" style="width:30;"   property="birthDateDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text disabled="true" style="width:30;"  property="birthDateMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text disabled="true" style="width:60;"  property="birthDateYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.cpf")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text disabled="true" style="width:300;"   property="cpf" styleClass="input"  />                
						    
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.rg")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text disabled="true" style="width:300;"   property="rg" styleClass="input"  />                
						    
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.uf")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text disabled="true" style="width:300;"   property="uf" styleClass="input"  />                
						    
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
	page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.levelofservice.section2");
%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.vnch")%> <%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="vcnhDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="vcnhMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="vcnhYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.libpamcary")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text style="width:300;"   property="libPamcary" styleClass="input" />	
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr>
   					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.dataValDay")%> <%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="dataValDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="dataValMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="dataValYear" styleClass="input"  />&nbsp;         
						</td>
					</tr>	
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr>
   					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.dirdefensiva")%> <%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="dirDefensivaDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="dirDefensivaMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="dirDefensivaYear" styleClass="input"  />&nbsp;         
						</td>
					</tr>	
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr>
   						<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.aso")%> <%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="asoDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="asoMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="asoYear" styleClass="input"  />&nbsp;         
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
	page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.levelofservice.section3");
%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.contract")%> <%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"   property="contractDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  property="contractMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  property="contractYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.cadastrodpa")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="cadastroDPA"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="cadastroDPA" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="cadastroDPA" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>´
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.copyrg")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="copyRG"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="copyRG" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="copyRG" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.copycpf")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="copyCPF"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="copyCPF" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="copyCPF" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.copycnh")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="copyCNH"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="copyCNH" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="copyCNH" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.compresid")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="compResid"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="compResid" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="compResid" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.testpsicologico")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="testPsicologico"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="testPsicologico" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="testPsicologico" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.testtheorist")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="testTheorist"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="testTheorist" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="testTheorist" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.testpractical")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="testPractical"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="testPractical" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="testPractical" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.workoutofgathering")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="workoutOfGathering"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="workoutOfGathering" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="workoutOfGathering" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.termoetilometro")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="termoEtilometro"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="termoEtilometro" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="termoEtilometro" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.prontuariocnh")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="prontuarioCNH"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="prontuarioCNH" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="prontuarioCNH" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.operationaltraining")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="operationalTraining"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="operationalTraining" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="operationalTraining" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.protocolfm300")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="protocolFM300"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="protocolFM300" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="protocolFM300" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.workcontract")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:radio style="width:30;"  property="workContract"  value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL)%></html:radio>
							<html:radio style="width:30;"  property="workContract" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL)%></html:radio> 
							<html:radio style="width:30;"  property="workContract" value='<%=LevelOfServiceDriverDPAData.TYPE_GENERIC_3_VALUE%>' styleClass="input"><%=messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL)%></html:radio>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.dpa.levelofservicedriver.cepa")%>
						</td>
						<td width="100%" align="right" class="linetwo">
							<table width="100%">
							<tr>
								<td>
								<table >
								<tr>
									<td>
										<select id="selCourse">
										
										<%
																					Iterator itDT= sys.getDataTypeReportFacade().findAll(DataTypeConstants.DPA_COURSES, sys.getUserSessionData()).iterator();
																																															while(itDT.hasNext()){
																																																GeneralDTData dType=(GeneralDTData) itDT.next();
																				%>
											<option value="<%=dType.getResourceBundleId()%>" ><%=messages.getText(dType.getResourceBundleId()).toUpperCase()%></option>
										<%
											}
										%>
										</select>
									</td>
									</tr>
									<tr>
									<td>
										<input type="text" size="1" id="dayCourse" />/<input type="text" id="monthCourse" size="1" />/<input id="yearCourse" type="text" size="2" />(dd/mm/aaaa)
									</td>
									</tr>
									<tr>	
									<td>
										<input type="text" size="40" id="commentCourse" >
									</td>
									</tr>
									<tr>
									<td>
										<input type="button" value="adicionar" onclick="addCourse();"/>
									</td>
									</tr>
								</table>	
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" id="tblCourses">
									<tr id="tblCoursesH">
									<td class="guide2">
										Curso
									</td>
									<td class="guide2">
										Data
									</td>
									<td class="guide2">
										Observação
									</td>
									<td class="guide2">
										&nbsp;
									</td>
									
									</tr>
										<%
											Iterator iterC=form.getCoursesData().iterator();
																											while(iterC.hasNext()){
																												LevelOfServiceDriverCourseDPAData dC=(LevelOfServiceDriverCourseDPAData)iterC.next();
										%>
											<tr tag="rowCourse">
												<td class="lineone" tagC="<%=dC.getCourse()%>">
													<%=messages.getText(dC.getCourse())%>
												</td>
												<td class="lineone">
													<%=Util.formatDate(dC.getDate())%>
												</td>
												<td class="lineone">
													<%= dC.getComment() %>
												</td>
												<td>
													<input value="Deletar" type="button" onclick="$(this).parent().parent().remove();"/>
												</td>
											</tr>
											
										
										<%		
												
											}
										%>
									</table>
								</td>
							</tr>
							</table>
						</td>
   					</tr>
   					
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
       	<tr>   		
	   		<td align="right">
	   		
	   			<input name="button2" type="button" onclick="document.location.href='layout.jsp?refer=/driver/DriverView.jsp?id=<%=driverId%>'" value='<%=messages.getText("generic.messages.back")%>' class="input"/>
	   			<input name="button2" type="button" onclick="lSubmit();" value='<%=messages.getText("generic.messages.save")%>' class="input"/>
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
