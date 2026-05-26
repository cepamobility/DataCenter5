<!doctype html>

<%@page import="communication.datatype.tables.TypeTradeDataType"%>
<%@page import="communication.datatype.tables.TypeVehicleDataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="communication.util.ElementData"%>
<%@page import="java.util.*"%>
 
<%@page import="presentation.translator.web.vehicle.DPACheckListAddVehicleForm"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.language.Text"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	
	String pathMsg="/util/jsp/message.jsp";
	
	request.getRequestDispatcher(pathMsg).include(request,response);
	
	DPACheckListAddVehicleForm form= new DPACheckListAddVehicleForm();
	
	if(request.getAttribute("DPACheckListAddVehicleForm")!=null){
		form=(DPACheckListAddVehicleForm)request.getAttribute("DPACheckListAddVehicleForm");
	}
	
	request.getSession().setAttribute("DPACheckListAddVehicleForm", form);
%>

<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=EDGE" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/apple-touch-icon-57x57-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/apple-touch-icon-72x72-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/apple-touch-icon-114x114-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/apple-touch-icon-144x144-precomposed.png" />
		<meta name="viewport" content="user-scalable=no, width=980" />
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>CEPA Driver Assessment</title>
		<link rel="shortcut icon" type="image/ico" href="../menu/images/favicon.ico">
		<link rel="stylesheet" href="../menu/styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="../menu/styles/base.css">
		<link rel="stylesheet" href="../menu/styles/checklist.css">
		<link rel="stylesheet" href="../menu/styles/jquery-ui-1.10.3.custom.min.css">
		<script type="text/javascript" src="../menu/js/jquery-1.10.2.min.js"></script>
		
		
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
								url:'DPACheckListAddVehicle.do?getItems=true&index=3&level1='+val+'&level2='+val2,
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
								url:'DPACheckListAddVehicle.do?getItems=true&index=2&level1='+val,
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
		</script>
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/images/main_logo_large');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='vehicleSearchDPA.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>


		<section id="content">
			<section class="grid_container">
				<html:form styleId="assessment" styleClass="checklist" action="/vehicle/DPACheckListAddVehicle">
					<fieldset class="title">
						<h4><%=messages.getText("checklist.dpa.addvehicle") %></h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
					</fieldset>

					<fieldset>
						<div class="narrow">
							<label for="q1"><%=messages.getText("generic.vehicle.vehicledata.matricula") %></label>
							<html:text size="160" styleId="q2" property="plate"    />
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow" >
							<label for="q2"><%=messages.getText("generic.vehicle.vehicledata.brand") %></label>
							<html:select  styleId="q1" property="brand"  >
								<html:option value="0"><%=messages.getText("generic.messages.select")%></html:option>		
								<%	
							 		Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE, sys.getUserSessionData()).iterator();
							 		while(iter.hasNext()){
							 			TypeTradeDataType item=(TypeTradeDataType) iter.next();	
								%>
									<html:option value="<%=item.getResourceBundleId()%>"><%=messages.getText(item.getResourceBundleId())%></html:option>
								<%
							 		}
							 %>	
							</html:select>	
						</div>
					</fieldset>
					<fieldset>	
						<div class="narrow"  data-type="2">
							<label for="q3"><%=messages.getText("generic.vehicle.vehicledata.model") %></label>
							<html:text size="160" styleId="q3" property="model"    />
						</div>
					</fieldset>
					<fieldset>	
						<div class="narrow" >
							<label for="q4"><%=messages.getText("generic.vehicle.vehicledata.year") %></label>
							<html:select   styleId="q4" property="year"  >
								<%
									Integer yearNow=Calendar.getInstance().get(Calendar.YEAR);
									for(int i=yearNow;i>yearNow-50;i--){
									%>
										<html:option value="<%=String.valueOf(Integer.valueOf(i))%>"><%=String.valueOf(Integer.valueOf(i))%></html:option>
									<% 
									}
								%>	
							</html:select>
						</div>
					</fieldset>
					<fieldset>	
						<div class="narrow" >
							<label for="q5"><%=messages.getText("generic.vehicle.vehicledata.type")%></label>
							<html:select  styleId="q5" property="type"  >
								<html:option value="0"><%=messages.getText("generic.messages.select")%></html:option>		
								<html:option value="datatype.vehicletype.trator"><%=messages.getText("datatype.vehicletype.trator")%></html:option>
								<html:option value="datatype.vehicletype.semireboque"><%=messages.getText("datatype.vehicletype.semireboque")%></html:option>
								<html:option value="datatype.vehicletype.vansemireboque"><%=messages.getText("datatype.vehicletype.vansemireboque")%></html:option>
							</html:select>	
						</div>
					</fieldset>
					
					<fieldset>	
						<div class="narrow" >
							<label for="divOrg11"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[0])%></label>
							<html:select  styleId="divOrg11" property="divOrg1" >
									<html:option value="0"><%=messages.getText("generic.messages.select")%></html:option>
							 	<%	
							 		Iterator iter=sys.getUserSessionData().getFilter().getLevelItems(1, 0, null, null,communication.util.Filter.CORPORATION);
							 		while(iter.hasNext()){
							 			ItemData item=(ItemData) iter.next();	
								%>
									<html:option value="<%=item.getId().toString()%>"><%=item.getName()%></html:option>
								<%
							 		}
							 %>	
							</html:select>	
						</div>
					<br>
					<div class="narrow" >	
						<label for="divOrg12"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[1])%></label>
						<html:select  styleId="divOrg12" property="divOrg2" ></html:select>
					</div>	
					<br>	
					<div class="narrow" >	
						<label for="divOrg13"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[2])%></label>
						<html:select  styleId="divOrg13" property="divOrg3" ></html:select>
					</div>		
					</fieldset>
						
					
					<input type="submit" class="button" value="<%=messages.getText("generic.messages.save")%>"/>
				</html:form>
			</section>
		</section>
		
	</body>
</html>
