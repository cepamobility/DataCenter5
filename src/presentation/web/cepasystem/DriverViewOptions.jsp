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
	import="communication.driver.tables.*"
	import="communication.event.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
 	import="communication.util.DateValue"
 %>
 <%! 
	
	SystemAccess sys2; 
	Text messages2;
	DriverData data2; 
%>
<%
	sys2 = (SystemAccess)session.getAttribute("SystemAccess");
	messages2 = sys2.getText();
	String id2 = (String)request.getParameter("id");
	if(id2 !=null){	
		data2=sys2.getDriverReportFacade().findDriverByPrimaryKey(new Integer(id2), sys2.getUserSessionData());
	}
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<!-- Opciones -->
		<tr>
			<td width="100%" valign="top" colspan="5">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages2.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
<%			if(sys2.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER) || sys2.getUserSessionData().isDriver() && data2.getCountryName().equals(CountryData.ARGENTINA)) {	
%>
		        <tr> 
          			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          			<td width="46%"><a href="javascript:document.forms['edit'].submit()" class="texts"> 
		            	<%=messages2.getText("generic.messages.edit")%> </a> 
		            </td>
          			<td width="2%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
          			<td width="49%"><a href="javascript:document.forms['cancel'].submit()" class="texts"><%=messages2.getText("generic.messages.cancel")%></a></td>
        		</tr>
<%			}else { %>
				<tr>
					<td width="3%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
					<td width="46%"><a href="javascript:document.forms['cancel'].submit()" class="texts"><%=messages2.getText("generic.messages.cancel")%></a></td>
				</tr>
<%			}
			if(data2.getExclusionDate() != null) {
			    if(sys2.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {	
%>
        		<tr class="texts1"> 
          			<td width="3%"  class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          			<td ><a href="javascript:setAction(document.forms['exclusion'],'include');deleteIFConfirm(document.forms['exclusion'],'<%=messages2.getText("generic.driver.driverdata.confirminclude")%>');" class="texts")> 
            			<%=messages2.getText("generic.driver.include")%> </a> 
            		</td>
<%            		if(sys2.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER)) {	
%>         			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          			<td><a href="javascript:setAction(document.forms['exclusion'],'delete');deleteIFConfirm(document.forms['exclusion'],'<%=messages2.getText("generic.driver.driverdata.confirmdelete")%>');" class="texts"> 
            			<%=messages2.getText("generic.messages.delete")%> </a> 
            		</td>
<%					} %>
            	</tr>
<%				}
			}else {
			    if(sys2.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {	
%>				<tr class="texts1"> 
          			<td width="3%"  class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          			<td><a href="javascript:setAction(document.forms['exclusion'],'exclude');deleteIFConfirm(document.forms['exclusion'],'<%=messages2.getText("generic.driver.driverdata.confirmexclude")%>');" class="texts"> 
			            <%=messages2.getText("generic.driver.exclude")%> </a> 
			       </td>			       			     
<%				}else{ %>
				<tr class="texts1"> 
<%				}
			    
			    // Si es Forestal y tiene permiso de: Modificacion o Tests
			    if(sys2.getUserSessionData().isForest() &&
			    		(sys2.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)
			    	|| sys2.getUserSessionData().checkPermission(SecurityConstants.TESTS_DRIVER))) 
			    {
%>		  			<td width="2%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
          			<td width="49%"><a href="./layout.jsp?refer=driver/aptitudTest.jsp?id=<%=String.valueOf(data2.getId())%>' class="texts"><%=messages2.getText("driver.modify.aptitudtest")%></a></td>
<%				}
			    
%>				</tr>
<%				
%>				<tr class="texts1"> 

<%				if(sys2.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER)) {	
%>         			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          			<td><a href="javascript:setAction(document.forms['exclusion'],'delete');deleteIFConfirm(document.forms['exclusion'],'<%=messages2.getText("generic.driver.driverdata.confirmdelete")%>');" class="texts"> 
            			<%=messages2.getText("generic.messages.delete")%> </a> 
            		</td>
<%				}

				// Si es Forestal y tiene permiso de: Modificacion o Tests
				if(sys2.getUserSessionData().isForest() &&
				    		(sys2.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)
				    	|| sys2.getUserSessionData().checkPermission(SecurityConstants.TESTS_DRIVER)))
				{
%>		  			<td width="2%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
          			<td width="49%"><a href='./layout.jsp?refer=driver/alcoholControl.jsp?id=<%=String.valueOf(data2.getId())%>' class="texts"><%=messages2.getText("driver.modify.alcoholcontrol")%></a></td>
<%				}

%>        		</tr>
<%	
				// Si es Forestal y tiene permiso de: Modificacion o Tests
				if(sys2.getUserSessionData().isForest() &&
			    	(sys2.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)
					|| sys2.getUserSessionData().checkPermission(SecurityConstants.TESTS_DRIVER)))
				{
%>				<tr class="texts1"> 				    
					<td colspan="2">&nbsp;</td>
		  			<td width="2%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
          			<td width="49%"><a href='./layout.jsp?refer=driver/driverPrizes.jsp?id=<%=String.valueOf(data2.getId())%>' class="texts"><%=messages2.getText("driver.modify.prizes")%></a></td>
        		</tr>
<%				}
			}
%>	  </table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>