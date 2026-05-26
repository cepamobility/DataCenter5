<%@page import="communication.driver.tables.DriverLogisticData"%>
<%@page import="communication.datatype.tables.GeneralDTData"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="presentation.translator.web.driver.DriverLogisticForm"%>
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
 	import="presentation.translator.web.driver.*"
 	import="communication.administration.user.*" 	
 	import="java.util.*"	
	
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	

	
	
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_DRIVER);
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String driverId = (String)request.getParameter("driverId");
		String activity = (request.getParameter("activity") == null) ? "" : request.getParameter("activity");
	 
		DriverLogisticForm form=new DriverLogisticForm();
		form.load(sys, Integer.valueOf(driverId),activity);
		form.setCancel("false");
		session.setAttribute("DriverLogisticForm", form);
%>	


<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script type="text/javascript">
		function deleteLogistic() {
			if(confirm('<%=messages.getText("driverlogistic.delete.confirm")%>')) {
				document.forms['DriverLogisticDeleteForm'].submit();
			}
		}
	</script>
</head>

<body>
	 
	<center>
	<html:form action="/DriverLogisticDelete" >
		<input type="hidden" value="<%=driverId%>" name="driverId"/>
		<input type="hidden" value="<%=activity%>" name="activity"/>
	</html:form>
	<html:form action="/DriverLogistic" >
		<input type="hidden" value="<%=driverId%>" name="driverId"/>
		<input type="hidden" value="<%=activity%>" name="oldActivity"/>
		<input type="hidden" value="false" name="cancel" id="cancel"/>
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.dridverlogistic.title");
			%>
				<jsp:include  page='<%=page14%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
	String page13 = "../component/utilityboxtop.jsp?title=";
%>
				<jsp:include  page='<%=page13%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%
	Collection col = new LinkedList();
		if(!Util.empty(activity))
	col.add(new OptionItem(messages.getText(activity),activity));
		Collection available = sys.getDriverReportFacade().getAvailableDriverLogistic(new Integer(driverId), sys.getUserSessionData());
		Collections.sort((List)available);
		Iterator iter = available.iterator();
		while(iter.hasNext()){
	            	String itemType=(String)iter.next();
	col.add(new OptionItem(messages.getText(itemType),itemType));
		}
		request.setAttribute("typecepaactivityscedule",col);
%>
          			<tr class="texts">
       					<td width="50%" align="left" class="texts">        
            				<%=messages.getText("generic.activity.activitydata.type")%><span class="required">*
            			</td>
						<td width="50%" align="right" class="linetwo">
                			<html:select property="activity" styleClass="input" style="width:155;" >
                  				<html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>
                			</html:select>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   	 				<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;" property="dateDay" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;" property="dateMonth" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;" property="dateYear" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.driverlogistic.status")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:select property="status" styleClass="input">
								<html:option value="<%=String.valueOf(DriverLogisticData.EMPTY)%>"><%=messages.getText("driverlogistic.status."+DriverLogisticData.EMPTY)%></html:option>
								<html:option value="<%=String.valueOf(DriverLogisticData.ASSIGNED)%>"><%=messages.getText("driverlogistic.status."+DriverLogisticData.ASSIGNED)%></html:option>
								<html:option value="<%=String.valueOf(DriverLogisticData.MISSING)%>"><%=messages.getText("driverlogistic.status."+DriverLogisticData.MISSING)%></html:option>
								<html:option value="<%=String.valueOf(DriverLogisticData.WAITING)%>"><%=messages.getText("driverlogistic.status."+DriverLogisticData.WAITING)%></html:option>
							</html:select>         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.driverlogistic.city")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:text  property="city" styleClass="input"  />         
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%=messages.getText("generic.driverlogistic.observations")%>
						</td>
   					</tr>
   					<tr> 
						<td width="100%" align="right" class="linetwo" colspan="2">
							<html:textarea  property="observations" styleClass="input" cols="100" rows="5"></html:textarea>         
						</td>
   					</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr>   		
	   		<td align="right">
	   			<input name="button2" type="submit" id="s" value='<%=messages.getText("generic.messages.save")%>' class="input"/>
	   			<input name="button2" type="button" onclick="cancel.value='true';s.click();" value='<%=messages.getText("generic.messages.back")%>' class="input"/>
<%
	if(!Util.empty(activity)) {
%>
		   			<input name="button2" type="button" onclick="deleteLogistic();" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
<%				}
%>		   	</td>             
   	  	</tr>              
	</table>
	</html:form>
	</center>
	</body>
	
</html:html>
<%
	}
%>
