<%@page import="business.activity.ActivityReportFacade"%>
<%@page language="java" 
	errorPage="exception.jsp" 
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*" 	
 	import="communication.administration.user.*" 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.administration.corporation.tables.CountryData"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	 	<title> CEPA International</title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<style type="text/css">
<!--	body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.contain {
			width: 100%; 
			height: 100%;
		}
 -->
		</style>
	</head>

<%!
	Text messages; 
	SystemAccess sys;
	String attribute; 
	String value;
	UserSessionData userSessionData; 
	RequestDispatcher dispatcher; String context; String pathMsg;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	int corporationId = sys.getCorporationId();
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.JJ_REPORTER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		HashMap mapValues = new HashMap();
		if(session.getAttribute("Msg") != null) {
	mapValues = (HashMap)userSessionData.getValue();
		}
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		session.setAttribute("url","reporter.jsp");
		messages=sys.getText();
		ActivityReportFacade activityReport = sys.getActivityReportFacade();

		DriverData driver = userSessionData.getDriverData();
		
		CEPAActivityScheduleRegistryData registryData = activityReport.getCompleteResult(new Integer(request.getParameter("id")),driver.getId(),sys.getUserSessionData());		
		CheckListData checkList = activityReport.findCheckList(new Integer(CheckListData.REPORTERJJ_COMMENTARYDRIVE),userSessionData);	
		Iterator pointIt = checkList.getSortPoints();
		
		if(driver == null) {
	response.sendRedirect(request.getContextPath()+"/reporterjj/forms.jsp");
		}else {
%>	
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
		 	<form name="history" action="data.jsp" method="post">
		 		<input type="hidden" name="backForm" value='<%=request.getParameter("history")%>'/>
	 		</form>
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true&reporter=true" %>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
		<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1"> 
		    	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("scheduledactivitytype.reporterjj.commentarydrive")%>' />
			</tr>
		  	<tr> 
	  			<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("reporterjj.data.driver")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr  class="linetwo">
       						<th align="left"> <%=messages.getText("reporterjj.driver.name")%></th>
       						<td align="right"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
    					</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.wwid")%></th>
       						<td align="right"><%=driver.getCorporationId()%></td>
	      				</tr>
					</table>			
	    			<jsp:include page="../component/utilityboxbottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
	  			<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='../component/utilitybox2top.jsp' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr class="lineone">
					      	<td><%=messages.getText("reporterjj.commentarydrive.text1")%></td>	
					  	</tr>
					  	<tr class="lineone" height="10">
					      	<td></td>	
					  	</tr>
   						<tr class="lineone">
					      	<td><%=messages.getText("reporterjj.commentarydrive.text2")%></td>	
					  	</tr>
					</table>			
	    			<jsp:include page="../component/utilitybox2bottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
<%		PointData point = null;
		while(pointIt.hasNext()) {			
			point = (PointData)pointIt.next();
			Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();
			int index = 1;
%>
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(point.getText())%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
						<tr  class="guide2" height="30">
				            <td align="left" width="80%" class="red"><%=messages.getText("reporterjj.commentarydrive.text3")%></td>
				            <td align="center" width="20%"></td>
						</tr>	
<%			while(itemIt.hasNext()) {
				ItemData item = (ItemData)itemIt.next();
				Integer valueItem = registryData.getValueFromItem(item);
				String result = (valueItem == 1) ? messages.getText("reporterjj.commentarydrive.positivebehaviour") : messages.getText("reporterjj.commentarydrive.precisamelhorar");
%>
   						<tr  class="linetwo" height="30">
				            <td align="left">
			            		<%=messages.getText(item.getText())%>
				            </td>
							<td align="center">		            			
								<%=result%>
							</td>
				        </tr>		
<%				if(itemIt.hasNext()) {
%>   					<tr class="space"><th colspan="3"><hr></th></tr>
<%				}		
				index++;
			}
%>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   					</table>
	    			<jsp:include page="../component/utilityboxbottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
<%		}
%>			
		  	<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("reporterjj.commentarydrive.reconheciento")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
						<tr class="linetwo">
							<td>
								<%=messages.getText("reporterjj.commentarydrive.reconhecientotext")%>
							</td>
					  	</tr>
						<tr  class="linetwo" height="30">
							<td>
								<%=registryData.getPointComment(point) %>
							</td>
   						</tr>
					</table>			
	    			<jsp:include page="../component/utilityboxbottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
		  	<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("reporterjj.commentarydrive.precisamelhorar")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
						<tr class="linetwo">
							<td>
								<%=messages.getText("reporterjj.commentarydrive.precisamelhorartext")%>
							</td>
					  	</tr>
						<tr  class="linetwo" height="30">
							<td>
								<%=registryData.getObs() %>
							</td>
   						</tr>
					</table>			
	    			<jsp:include page="../component/utilityboxbottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr>
				<td colspan="3" align="right">
	    			<jsp:include  page='../component/utilitybox2top.jsp' />
	    			<table width="100%" cellpadding="0" cellspacing="0">
	    				<tr class="lineone">
   							<td align="right">
   								<input type="button" value='<%=messages.getText("generic.messages.back")%>' onclick="document.forms['history'].submit();" styleClass="input" />
							</td>
   						</tr>
					</table>			
	    			<jsp:include page="../component/utilitybox2bottom.jsp"/>
		      	</td>	
		  	</tr>
		</table>		
	</body>
<%		}
	}
%>		
</html>
