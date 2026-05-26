<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; String context; Text messages; SystemAccess sys;
%> 
<%
 	sys=(SystemAccess)session.getAttribute("SystemAccess");
  	String check = Util.checkPermission(sys,SecurityConstants.VIEW_DRIVER_LIST);
  	if(check!=null) {
  		response.sendRedirect(request.getContextPath()+check);
  	}else {
  	    String url = "driver/DriverView.jsp";

  		context=request.getContextPath();
  		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
  		dispatcher.include(request,response);
  	
  		messages=sys.getText();
  		session.setAttribute("target","DriverView");
  		boolean already = (request.getParameter("already") == null) ? false : 
  	Boolean.parseBoolean(request.getParameter("already"));
  		DriverUtil util = new DriverUtil();
  		Hashtable parameters = util.getParameters(session,request);	
  		boolean advanced = ((Boolean)parameters.get("advanced")).booleanValue();
  		
  		session.setAttribute("url",url);
  		DriverReportFacade driverReportFacade = sys.getDriverReportFacade();
  		String link = "/driver/DriverView.jsp";
  		ListResult telematicControlSearchList = null;
  		/*if(!advanced && !already) {
  	driverSearchList = driverReportFacade.findDriverList(parameters,sys.getUserSessionData());
  	session.setAttribute("driverSearchList",driverSearchList);			
  		}else {*/
  		
  		//DriverTelematicControlForm form = null;
  	if(session.getAttribute("driverForm") != null) {
  		/* form = (DriverTelematicControlForm)session.getAttribute("driverForm");*/
  	}else {
  	   /* form = new DriverTelematicControlForm();
  	    form.setPageNum((String)request.getParameter("pageNum"));
  	    form.setCount((String)request.getParameter("pageSize"));
  	    form.setStatus((String)request.getParameter("status"));
  	    form.setNextLink(link);*/
  	}
  	//session.setAttribute("DriverTelematicControlForm",form);
  	
  	
  		//}
 %>


	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
    <title>
    </title>
    
<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
  </head>
  
  <script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<script language="JavaScript" type="text/javascript">

function preparedCheckboxParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.checkboxParamList.value=valueCheckboxParamList;
		if(valueCheckboxParamList.length>0){
			return true;
		}
		else{
			return false;
		}
}
function submitPagination(page,all) {
	document.forms[2].all.value = all;
	document.forms[2].pageNum.value = page;
	document.forms[2].submit();
}
function setAction(form,action){
	if(preparedCheckboxParameters(form)) {
		form.action.value=action;
		form.submit();
	}
}
</script>

<body bgcolor="#FFFFFF">
	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.driver.editing")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
      
      	<tr>
        	<td colspan="3">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.telematiccontrol.list")%>' />
				<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      				<tr>
        				<td width="100%">
							<form action="layout.jsp?refer=/driver/DriverExclusion.jsp"  method="post">
								<table width="100%" border="0">
              						<tr align="center">
<%		if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER) || sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {	
%>	              						<td width='3%' class="guide2"></td>
<%		}																
%>					               	 	<td width="15%" class="guide2" align="center"><%=messages.getText("driver.telematic.occurrences") %></td>
									 	<td width="15%" class="guide2" align="center"><%=messages.getText("driver.telematic.speed") %></td>
									 	<td width="15%" class="guide2" align="center"><%=messages.getText("driver.telematic.date") %></td>
									 	<td width="10%" class="guide2" align="center"><%=messages.getText("driver.telematic.time") %></td>
									 	<td width="15%" class="guide2" align="center"><%=messages.getText("driver.telematic.duration") %></td>
									 	<td width="15%" class="guide2" align="center"><%=messages.getText("driver.telematic.totalDuration") %></td>
									 	<td width="15%" class="guide2" align="center"><%=messages.getText("driver.telematic.distance") %></td>
									 	
	              					</tr>
<% 		String[] principalLabels=sys.getUserSessionData().getFilter().getPrincipalLabels();	
%>	              					
<%		ListResult col = (ListResult)session.getAttribute("telematicControlSearchList");
			int total = (col == null)?0:col.getTotal();
			if(total == 0){
%>			    					<tr>
        	      						<td class="linetwo">
	        	      						<%=messages.getText("generic.messages.notresult")%>
              							</td>
              						</tr>
<%		}else {
			
			Iterator iter = col.getElements().iterator();
%>
<%			int max = (Boolean.parseBoolean(request.getParameter("all"))) ? col.getTotal() : Integer.parseInt((String)parameters.get("pageSize"));   
   			int count = 0;
			
   			while(iter.hasNext() && (count < max)){
				
   				TelematicControlData control = (TelematicControlData)iter.next();
%>		        					<tr align=center align="right" class='<%=(count % 2 == 0) ? "lineone" : "linetwo"%>'>
                
<%				if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER) || sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {
%>	            						<td width='3%' align="center">
											<input type='checkbox' name='<%=control.getId()%>' value='<%=control.getId()%>' >
										</td>
<%				}	if(sys.getUserSessionData().checkPermission(SecurityConstants.EDIT_DRIVER)) {
%>	          							<td width='15%' align="right">
<%				
%>	              							<a href='layout.jsp?refer=/driver/DriverView.jsp?id=<%=control.getId()%>' > <%=(control.getOccurrences() != null) ? control.getOccurrences():"---"%> </a>
<%								
%>              						 <td width="15%" align="center"><%= (control.getOccurrences() != null) ? control.getOccurrences():"---" %></td>
							 	<td width="15%" align="center"><%=  (control.getSpeed() != null) ? control.getSpeed():"---" %></td>
							 	<td width="15%" align="center"><!-- <a href='<%="layout.jsp?refer=driver/TelematicControlList.jsp?id="+String.valueOf(control.getId())%>'>-->
								 <%= (control.getDate() != null) ? control.getDateString():"---" %>
					                <!-- </a> -->
							 	</td>
							 	<td width="10%" align="center"><%= (control.getTime() != null) ? control.getTime() : "---" %></td>
							 	<td width="15%" align="center"><%= (control.getDuration() != null) ? control.getDuration():" ---" %></td>
							 	<td width="15%" align="center"><%= (control.getTotalDuration() != null) ? control.getTotalDuration(): "---" %></td>
							 	<td width="15%" align="center"><%= (control.getDistance() != null) ? control.getDistance().toString(): "---" %></td>	
							 						        
<% 				}
%>         					</tr>
<%			}
		}
%>		      					</table>
              					<input type="hidden" name="checkboxParamList" />
              					<input type="hidden" name="action" />
            				</form>
            			</td>
            		</tr>
<%		if(col.getTotal() > 0) {
%>            		
             		<tr>
                		<td class="linetwo" align="right">
<%			if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER)) {	%>
                    		<input name="button2" type="button" onClick="javascript:setAction(document.forms[3],'delete');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
<%			}															
%>	            		</td>
      				</tr>
      				<tr>
        				<td>
<%			session.setAttribute("paginationParameters",parameters);
%>        					<jsp:include page='<%="../util/pagination2.jsp?size="+parameters.get("pageSize")+"&num="+parameters.get("pageNum")+"&total="+String.valueOf(col.getTotal())+"&link="+link%>' />        
        				</td>
      				</tr>
<%			}	
			
%>				</table>
   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			</td>
      	</tr>
      	<tr><td>&nbsp;</td></tr>
  	</table>
</body>
<%
	}	
%>	
</html:html>
