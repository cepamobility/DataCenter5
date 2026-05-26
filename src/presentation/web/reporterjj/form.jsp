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

		CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(62),userSessionData);	
		Collection points = checkList.getPoints();
		Iterator pointIt = points.iterator();
		
		DriverData driver = userSessionData.getDriverData();
		if(driver == null) {
	if(!Util.empty(request.getParameter("id"))) {
		Hashtable parameters = new Hashtable();
		parameters.put("id",request.getParameter("id"));
		Collection drivers = sys.getDriverReportFacade().findDriverReport(parameters,userSessionData);
		driver = (DriverData)drivers.iterator().next();
		userSessionData.setDriver(driver);
	}
		}
		
		if(driver == null) {
	response.sendRedirect(request.getContextPath()+"/reporterjj/forms.jsp");
		}else {
	PointData point = (PointData)pointIt.next();
%>	
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
		 	<form name="history" action="data.jsp" method="post">
		 		<input type="hidden" name="backForm" value='2'/>
	 		</form>
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true&reporter=true"%>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the topï¿½s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
  		<html:form action="/reporterjj/ReporterJJRegistry">
  			<html:hidden property="activity" value='<%=CEPAActivityConstants.REPORTERJJ_SELFEVALUATION%>' />
		<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1"> 
		    	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(point.getText())%>' />
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
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%
	if(Util.empty(driver.getEmail())) {
%>						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.email")%><span class="required">*</span></th>
       						<td align="right"><html:text property="email" size="50" styleClass="input"/></td>
	      				</tr>
<%		}else {
%>						<tr class="linetwo" height="25">
	        				<th align="left" colspan="2" class="red">O e-mail inserido no sistema é <%=driver.getEmail()%>, você pode alterá-lo entrando em outro.</th>
	      				</tr>
						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.email")%></th>
       						<td align="right"><html:text property="email" size="50" styleClass="input"/></td>
	      				</tr>
<%		}
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.manager")%></th>
       						<td align="right">
       							<html:text property="managerName" size="50" styleClass="input"/>
							</td>
	      				</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.mangeremail")%><span class="required">*</span></th>
       						<td align="right">
       							<html:text property="managerEmail" size="50" styleClass="input"/>
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
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.form.formulario")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr>
				            <td colspan="3" class="lineone">
					    		<jsp:include  page='../component/utilitybox2top.jsp' />
					    			<table width="100%" cellpadding="0" cellspacing="0">
				   						<tr class="lineone">
									      	<td><input type='button' value='<%=messages.getText("generic.messages.history")%>' onclick="javascript:document.forms['history'].submit();" /></td>	
									  	</tr>
									  	<tr class="lineone" height="10">
									      	<td></td>	
									  	</tr>
					    				<tr class="lineone">
					    					<td colspan="4" align="left" class="red"><%=messages.getText("reporterjj.selfevaluation.text5")%></td>
					    				</tr>
					    				<tr class="lineone">
					    					<th width="25%" align="center">1 Quase Sempre</th>
					    					<th width="25%" align="center">2 Na Maioria das Vezes</th>
					    					<th width="25%" align="center">3 As Vezes</th>
					    					<th width="25%" align="center">4 Raramente</th>
					    				</tr>
					    			</table> 
					    		<jsp:include  page='../component/utilitybox2bottom.jsp' /> 
				            </td>
				      	</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   						<tr>
				            <th colspan="3" class="linetwo" align="left">
					    		Ao dirigir, eu ... 
				            </th>
				      	</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				int index = 1;
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
%>
   						<tr  class="linetwo" height="30">
				            <td align="left" width="5%">
			            		<%=index%>
				            </td>
				            <td align="left" width="70%">
			            		<%=messages.getText(item.getText())%>
				            </td>
				            <td align="right" width="25%">
<%					for(int i = 1; i <= 4; i++) {				            	
						String checkvalue = "";
						if(mapValues.containsKey("i"+item.getId()) && Integer.parseInt((String) mapValues.get("i"+item.getId())) == i) {
							checkvalue = "checked";
						}
%>		            			<input type='radio' name='<%="item-"+item.getId()%>' value='<%=i%>' <%=checkvalue%>><%=i%></input>
<%					}
%>				            </td>
						</tr>		
<%					if(itemIt.hasNext()) {
%>   					<tr class="space"><th colspan="3"><hr></th></tr>
<%					}		
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
			<tr>
				<td colspan="3" align="right">
	    			<jsp:include  page='../component/utilitybox2top.jsp' />
	    			<table width="100%" cellpadding="0" cellspacing="0">
	    				<tr class="lineone">
   							<td align="right">
   								<html:submit value='<%=messages.getText("generic.messages.send")%>' styleClass="input" />
   								<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input" />
							</td>
   						</tr>
					</table>			
	    			<jsp:include page="../component/utilitybox2bottom.jsp"/>
		      	</td>	
		  	</tr>
		</table>		
		</html:form>
	</body>
<%		}
	}
%>		
</html>
