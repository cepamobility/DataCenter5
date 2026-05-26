<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="java.util.*"
 	import="communication.administration.user.*" 
 	import="communication.administration.corporation.tables.*"		
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

<%!
	Text messages;
	SystemAccess sys;
	UserSessionData userSessionData;	 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess"); 
	userSessionData=sys.getUserSessionData();
	String check = null;
	if(sys.getUserSessionData().isDriver()) {
		check = Util.checkPermission(sys,SecurityConstants.DRIVER_VIEW_VEHICLE);
	}else {
		check = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText() ;
		VehicleAdvancedSearchForm form = null;
		int size = Integer.parseInt(request.getParameter("count"));		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		if (pageNum < 1) pageNum = 1; 
		if(session.getAttribute("vehicleForm") != null) {
		    form = (VehicleAdvancedSearchForm)session.getAttribute("vehicleForm");
		}else {
		    form = new VehicleAdvancedSearchForm(
		    		request.getParameter("form"),
		    		request.getParameter("vehicle"),
		            request.getParameter("name"),pageNum,size,
		            request.getParameter("link"),
		            request.getParameter("status"),
		            request.getParameter("prefix"),
		            request.getParameter("permissionNumber"));
	session.setAttribute("VehicleAdvancedSearchForm",form);
		}
		Collection list = new LinkedList();
		Iterator iter = list.iterator();
		int count = 10;
		int corporationId = sys.getCorporationId();
		String all = "false";
		Collection statusList = new LinkedList();
		statusList.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(VehicleData.ACTIVE)));
		statusList.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(VehicleData.EXCLUDED)));
		statusList.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(VehicleData.ALL)));
		request.setAttribute("statusList",statusList);
		
		Collection paginationList = new LinkedList();
		paginationList.add(new OptionItem("10","10"));
		paginationList.add(new OptionItem("20","20"));
		paginationList.add(new OptionItem("30","30"));
		paginationList.add(new OptionItem("40","40"));
		paginationList.add(new OptionItem("50","50"));
		
		request.setAttribute("paginationList",paginationList);
		
		
//		Datos Opcionales de Vehiculos (Camiones Brasil), en particular para KLABIN
		boolean hasOptionalData = false;
		if (corporationId == CorporationData.KLABIN || 
		corporationId == CorporationData.YOLI ||
		        corporationId == CorporationData.CIMSA ||
		        corporationId == CorporationData.PEPSICO){
	// Cimsa y Pepsico usan la matricula como numero economico
	// y el campo prefijo de optionaInfo como matricula
	
	hasOptionalData = true;
		}
%>
	
<script language="JavaScript" src="../util/js/select_component.js" type="text/javascript">
</script>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script>
function doSubmit(all,page) {
	document.forms[0].all.value = all;
	document.forms[0].first.value = page;
	document.forms[0].submit();
}

</script>
<html:form action="advancedsearch">
	<html:hidden property="all"/>
	<html:hidden property="pageNum"/>
	<!-- <html:hidden property="count"/> -->
	<body>
  <!-- vehicle: advancedSearch.jsp -->
	  <table align="center" width="820" cellpadding="0" cellspacing="0">
		  <tr  valign="top">
			<td>
				<table width="100%" border="0" class="SearchBack" align="center" cellpadding="0" cellspacing="0">
      				<tr class="guideSearch">
						<td width="19" ><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom"></td>
						<td class="guideSearch" align="left"><%=messages.getText("generic.vehicle.vehicledata")%></td>
						<td width="19" ><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom"></td>
					</tr>
  					<tr>
						<td class="leftLineSearch">&nbsp;</td>
        				<td height="6" class="lineone"><img src="x" width="1" height="1"></td>
        				<td class="rightLineSearch">&nbsp;</td>
  					</tr>  					  					
    				<tr>
          				<td class="leftLineSearch">&nbsp;</td>
          				<td> 	
          					<table class="SearchBack" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                            	<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="registrationNumber" size="30" styleClass="input"/>	
                					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
<%		      	if (hasOptionalData){ 
%>              				<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.prefix")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="prefix" size="30" styleClass="input"/>	
                					</td>
              					</tr>            
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
<% 				}
				// Centro de Costo
              	// Klabin no tiene Centro de Costo
              	if (corporationId != CorporationData.KLABIN && corporationId != CorporationData.PEPSICO){
%>              				<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.costCenter")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="costCenter" size="30" styleClass="input"/>	
                					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
<% 				} 
				// Monsanto permission number
				if (corporationId == CorporationData.MONSANTO){
%>              				<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.permissionNumber")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="permissionNumber" size="30" styleClass="input"/>	
                					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
<% 				} 
%>              <!-- Chasis Number -->
              					<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.chasisNumber")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="chasisNumber" size="30" styleClass="input"/>	
                					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
              					<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.firstnamedriver")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="firstNameDriver" size="30" styleClass="input"/>	
                					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
              					<tr  valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.vehicle.vehicledata.lastnamedriver")%>	
                  					</th>
                					<td align="right"> 
                						<html:text property="lastNameDriver" size="30" styleClass="input"/>	
                					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
              					<tr valign="top"> 
                					<th align="left">
										<%=messages.getText("generic.driver.driverdata.status")%> 
                  					</th>
                					<td align="right"> 
                						<html:select property="status" styleClass="input" > 
                  							<html:options collection="statusList" property="value" labelProperty="label"/> 
                  						</html:select> 
                  					</td>
              					</tr>
    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
 			  					<tr>
     								<th align="left"> 
     									<%=messages.getText("generic.driver.driverdata.pagination")%> 
     								</th>
      								<td align="right">
										<html:select property="count" styleClass="input" >
											<html:options collection="paginationList" property="value" labelProperty="label"/>
										</html:select>							          				
			    					</td>
 			  					</tr>
            				</table>
            			</td>
          				<td class="rightLineSearch">&nbsp;</td>
        			</tr>
        			<tr valign="top"> 
          				<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
          				<td class="centerBaseSearch"></td>
          				<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
        			</tr>
      			</table>			
			</td>
			<td width="16" valign="top"><img src="x" width="1" height="1"></td>
			<td rowspan="3" valign="top">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr valign="top" class="lineone">
			      		<td>
							<jsp:include page='<%="/util/divorg.jsp?form=VehicleAdvancedSearchForm&format=search" %>'/>						
						</td>
					</tr>
				</table>			
			</td>
		</tr>
		<tr valign="top">
			<jsp:include  page="../component/spaceline.jsp" />		
	    </tr>
		<tr valign="top">
			<td valign="top">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr valign="top" class="lineone">
			      		<td>
							<jsp:include page='<%="/util/divgeo2.jsp?form=VehicleAdvancedSearchForm&format=search" %>'/>							
						</td>
					</tr>
				</table>			
			</td>
		    <td width="16" valign="top"><img src="x" width="1" height="1"></td>
		</tr>
		<tr> 
    		<jsp:include  page="../component/spaceline.jsp" />
		</tr>
		<tr valign="top">
		    <td valign="top" align="left">
		    	<span class="texts"><img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
            		<a href='layout.jsp?refer=/vehicle/VehicleList.jsp?pageNum=<%=String.valueOf(pageNum)%>' class="texts"><%=messages.getText("generic.messages.simplesearch")%></a> 
              	</span>
		    </td>
		    <td valign="top">&nbsp;</td>
		    <td valign="bottom" align="right">
		    	<html:submit value='<%=messages.getText("generic.messages.search")%>' styleClass="input" />
		    </td>
	    </tr>
	  </table>
	</body>
	<!--  fin advancedSearch -->
	<html:hidden property="form" />
	<html:hidden property="vehicleId" />
	<html:hidden property="name" />
</html:form>
<%		
	} 
%>
</html:html>