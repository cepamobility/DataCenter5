<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	String entity = sys.getUserSessionData().getMsg();
	DriverData driver = (DriverData) userSessionData.getValue();
	
	Integer contractorId = new Integer(driver.getDivOrgItem1());
	Integer[] links = sys.getCorporationReportFacade().getTransporterLink(contractorId,userSessionData);
	ItemData item = userSessionData.getFilter().getDivOrgItem(0, 1, contractorId);
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	</head>
	<script type="text/javascript">
		function warning() {
			var elements=document.forms['DriverForestalesCreateForm'].elements;
			var length=elements.length;
				
			for(i=0;i<length;i++){
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(element.checked){
						return true;
					}	
				} 
			}
			alert('Debe seleccionar al menos una corporación');
			return false;
		}
	</script>
 
	<body>
		<center>
			<html:form action="/DriverForestalesCreate" onsubmit="return warning()">
			<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
		  		<tr>
		    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
		    		<td width="562" height="30">&nbsp;</td>
		    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
		  		</tr>
		  		<tr>
		    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
	    		  	<td width="562" align="center" class="lineone">
		    			<span class="success">		    			
							<%=messages.getText("driver.forestales.selectcorporation")%>								
			    		</span>
						<br>
		    			<br>
						<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
					  		<tr>
					    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
					    		<td width="562" height="30">Datos del conductor</td>
					    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
					  		</tr>
					  		<tr>
					    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
				    		  	<td width="562" align="center" class="lineone">
				    		  		<table  width="100%">
										<tr  class="lineone">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
											<td align="right"><%=driver.getFirstName() %></td>
										</tr>
				   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="lineone">
											<th align="left"><%=messages.getText("generic.driver.driverdata.lastname")%></th>
											<td align="right"><%=driver.getLastName() %></td>
										</tr>
				   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="lineone">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.birthdate")%></th>
											<td align="right"><%=Util.formatDate(driver.getBirthDate())%></td>
										</tr>
				   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="lineone">
											<th align="left"> <%=messages.getText("role.contractors.name")%></th>
											<td align="right"><%=item.getName()%></td>
										</tr>
				    		  		</table>
				    		  	</td>
					    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
					  		</tr>
					  		<tr>
					    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
					    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
					    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
						  	</tr>
						</table>
		    			<br>
		    			<br>
						<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
					  		<tr>
					    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
					    		<td width="562" height="30"><%=messages.getText("driver.forestales.corporations") %></td>
					    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
					  		</tr>
					  		<tr>
					    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
				    		  	<td width="562" align="center" class="lineone">
				    		  		<table width="562">
										<tr  class="lineone">
<%						for(int i = 1; i < 4; i++) {
							if(links[i - 1] != null) {
%>											<td align="center" width="50%"><html:checkbox property='<%="corporation"+i %>' /><%=messages.getText("driver.forestales.corporation"+i) %></td>
<%							}
						}	
%>										</tr>
				    		  		</table>
				    		  	</td>
					    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
					  		</tr>
					  		<tr>
					    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
					    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
					    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
						  	</tr>
						</table>
						<br>
						<html:submit value='<%=messages.getText("generic.messages.accept")%>' />
						<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' />
					</td>
		    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
		    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
			</table>
			</html:form>
		</center>
		<br/><br/>
	</body>
</html>
