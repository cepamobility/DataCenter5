<!doctype html>

<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="presentation.translator.web.util.DataTypeUtil"%>
<%@page import="communication.datatype.tables.TypeTradeDataType"%>
<%@page import="communication.datatype.tables.TypeVehicleDataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="communication.util.ElementData"%>
<%@page import="java.util.*"%>
 
<%@page import="presentation.translator.web.vehicle.DPACheckListAddVehicleForm"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.language.Text"%>
<%@page import="presentation.translator.web.util.*"%>
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
		<meta name="MobileOptimized" content="width" />
		<meta name="HandheldFriendly" content="true" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="content-language" content="es" />
		<title>Checklist</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/mbase.css">
		<link rel="stylesheet" href="../styles/mchecklist.css">
		<link rel="stylesheet" href="../styles/jquery-ui-1.10.3.custom.min.css">
		<script type="text/javascript">
			var itemDefault='<option value="0"><%=messages.getText("generic.messages.select")%></option>';	
		
			$(document).ready(
				function(){

					var val= $('#divOrg11')[0].value;
					if(val!='0'){
						loadDivOrg1();
					}
					val= '<%=form.getDivOrg2()%>';
					if(val!='0' && val!='null'){
						$('#divOrg12')[0].value=val;
						loadDivOrg2();
					}
					val= '<%=form.getDivOrg3()%>';
					if(val!='0' && val!='null'){
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
		<section class="grid_container">
			<h1 class="customer_logo"><img src="../menu/images/main_logo.png"/></h1>
			<br>
			<div class="toolbar">
				<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
				<span class="exit"><a href='../login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
			</div>
		</section>
		<section id="content">
			<section class="grid_container">
				<html:form styleId="assessment" styleClass="checklist" action="/mobile/DPACheckListAddVehicle">
					<fieldset class="title">
						<h4><%=messages.getText("checklist.dpa.addvehicle") %></h4>
						<!-- h5>Data Efetivação: 07/02/2011 • Revisão: 00</h5 -->
					</fieldset>

					<fieldset>
						<div class="narrow">
							<label for="q1"><%=messages.getText("generic.vehicle.vehicledata.matricula") %></label>
							<html:text styleId="q2" property="plate" />
						</div>
					</fieldset>
					<fieldset>
						<div class="narrow" >
							<label for="q2"><%=messages.getText("generic.vehicle.vehicledata.brand") %></label>
							<html:select  styleId="q1" property="brand"  >
								<html:option value="0"><%=messages.getText("generic.messages.select")%></html:option>		
<%							 		Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE, sys.getUserSessionData()).iterator();
							 		while(iter.hasNext()){
							 			TypeTradeDataType item=(TypeTradeDataType) iter.next();	
%>										<html:option value="<%=item.getResourceBundleId()%>"><%=messages.getText(item.getResourceBundleId())%></html:option>
<%							 		}
%>							</html:select>	
						</div>
					</fieldset>
					<fieldset>	
						<div class="narrow"  data-type="2">
							<label for="q3"><%=messages.getText("generic.vehicle.vehicledata.model") %></label>
							<html:text styleId="q3" property="model" />
						</div>
					</fieldset>
					<fieldset>	
						<div class="narrow" >
							<label for="q4"><%=messages.getText("generic.vehicle.vehicledata.year") %></label>
							<html:select   styleId="q4" property="year"  >
<%									Integer yearNow=Calendar.getInstance().get(Calendar.YEAR);
									for(int i=yearNow;i>yearNow-50;i--){
%>										<html:option value="<%=String.valueOf(Integer.valueOf(i))%>"><%=String.valueOf(Integer.valueOf(i))%></html:option>
<%									}
%>							</html:select>
						</div>
					</fieldset>
					<fieldset>	
						<div class="narrow" >
							<label for="q5"><%=messages.getText("generic.vehicle.vehicledata.type")%></label>
<% 							session.setAttribute("vt",new DataTypeUtil(sys).getVehicleType(false,true,false));
%>		        				<html:select  styleId="q5" property="type" styleClass="input" style="width:155;">
                  					<html:options collection="vt" property="value" labelProperty="label" styleClass="input"/>
                				</html:select>
						</div>
					</fieldset>
<%		if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
%>					<fieldset>	
						<div class="narrow" >
							<label for="divOrg11"><%=messages.getText(sys.getUserSessionData().getFilter().getLevelNames(messages)[0])%></label>
							<html:select  styleId="divOrg11" property="divOrg1" >
								<html:option value="0"><%=messages.getText("generic.messages.select")%></html:option>
<%							 		Iterator iter=sys.getUserSessionData().getFilter().getLevelItems(1, 0, null, null,communication.util.Filter.USER);
							 		while(iter.hasNext()){
							 			ItemData item=(ItemData) iter.next();	
%>								<html:option value="<%=item.getId().toString()%>"><%=item.getName()%></html:option>
<%							 		}
%>							</html:select>	
						</div>
						<br>
					</fieldset>
<%		}else {
%>					<html:hidden  value="1" property="divOrg1" />
<%		}
%>					
					<input type="submit" class="button" value="<%=messages.getText("generic.messages.save")%>"/>
					<input type="button" class="button" value="<%=messages.getText("generic.messages.cancel")%>"/>
				</html:form>
			</section>
		</section>
	</body>
</html>
