<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.cenapra.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.cenapra.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.cenapra.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
%> 
<%
 	sys=(SystemAccess)session.getAttribute("SystemAccess");
  	String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_VIEW_PARTICIPANT);
  	if(check!=null) {
  		response.sendRedirect(request.getContextPath()+check);
  	}else {
  	    String url = "cenapra/ParticipantList.jsp";

  		context=request.getContextPath();
  		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
  		dispatcher.include(request,response);
  	
  		messages = sys.getText();

  		boolean already = (request.getParameter("already") == null) ? false : Boolean.parseBoolean(request.getParameter("already"));
  		ParticipantUtil util = new ParticipantUtil();
  		Hashtable parameters = util.getParameters(session,request);	
  		
  		session.setAttribute("url",url);
  		CENAPRAReportFacade report = sys.getCENAPRAReportFacade();
  		String link = "/cenapra/ParticipantList.jsp";

  		ListResult result = report.findList(parameters,sys.getUserSessionData());
  		String activityType = (String)parameters.get("activityType");
 %>
	<head>
		<LINK REL="StyleSheet" HREF="../util/css/estilo.css" TYPE="text/css">
    	<title></title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
		<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
		</script>
		<script language="JavaScript" type="text/javascript">
		function setAction(form){
			var elements=form.elements;
			var length=elements.length;
			var i;
			var valueCheckboxParamList="";
			
			for(i=0;i<length;i++){
				var element=elements[i];
				if(element.type.toLowerCase()=="checkbox"){
					if(element.checked){
						if(valueCheckboxParamList==""){
							valueCheckboxParamList=element.value;
						}else{
							valueCheckboxParamList=element.value+","+valueCheckboxParamList;
						}	
					}	
				} 
				
			}
			if(valueCheckboxParamList.length>0){
				form.list.value=valueCheckboxParamList;
				form.submit();
			}
		}
		function submitPagination(page,all) {
			document.forms['ParticipantSearch'].all.value = all;
			document.forms['ParticipantSearch'].pageNum.value = page;
			document.forms['ParticipantSearch'].submit();
		}
		</script>
  	</head>
	<body bgcolor="#FFFFFF">
		<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("cenapra.participants")%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
      		<tr>
        		<td valign="top" width='60%' height="100%">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
					<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="right" valign="top">
								<form name="ParticipantSearch" action="layout.jsp?refer=/cenapra/ParticipantList.jsp" method="post">
									<input type="hidden" name="pageNum" value='<%=String.valueOf(parameters.get("pageNum"))%>'>    
									<input type="hidden" name="all" value='<%=String.valueOf(parameters.get("all"))%>'>    
    								<input type="hidden" name="pageSize" value='<%=String.valueOf(parameters.get("pageSize"))%>'/>										  
									<table width="100%" height="20" border="0" cellpadding="0" cellspacing="1">									
										<tr>
    										<td width="41%" align="left" class="texts">
												<%=messages.getText("participant.data.firstname")%>										
											</td>
    										<td width="59%" colspan="2" align="right">
    											<input type="text" name="firstName" value='<%=String.valueOf(parameters.get("firstName"))%>' size="40" class="input">
  											</td>
										</tr>
										<tr>
											<td height="10" colspan="3">
												<img src="x" width="1" height="1">
											</td>
										</tr>
										<tr>
    										<td width="41%" align="left" class="texts">
												<%=messages.getText("participant.data.lastname")%>										
											</td>
    										<td width="59%" colspan="2" align="right">
    											<input type="text" name="lastName" value='<%=String.valueOf(parameters.get("lastName"))%>' size="40" class="input">
  											</td>
										</tr>
										<tr>
											<td height="10" colspan="3">
												<img src="x" width="1" height="1">
											</td>
										</tr>
										<tr>
    										<td width="41%" align="left" class="texts">
												<%=messages.getText("participant.data.state")%>										
											</td>
    										<td width="59%" colspan="2" align="right">
    											<select name="activityType" class="input">
    												<option value='<%=ParticipantData.ALL%>' <%=(activityType.equals(ParticipantData.ALL)) ? "selected" : ""%>><%=messages.getText("generic.messages.all.pl")%></option>
    												<option value='<%=ParticipantData.WITH_ACTIVITY%>' <%=(activityType.equals(ParticipantData.WITH_ACTIVITY)) ? "selected" : ""%>><%=messages.getText("generic.cenapra.withactivity")%></option>
    												<option value='<%=ParticipantData.WITHOUT_ACTIVITY%>' <%=(activityType.equals(ParticipantData.WITHOUT_ACTIVITY)) ? "selected" : ""%>><%=messages.getText("generic.cenapra.withoutactivity")%></option>
    											</select>
  											</td>
										</tr>
										<tr>
											<td height="10" colspan="3">
												<img src="x" width="1" height="1">
											</td>
										</tr>
										<tr>
											<td height="10" colspan="2" align="right">
                                        		<input name="button" type="submit" value='<%=messages.getText("generic.messages.search")%>' class="input"/>
                                          	</td>
                                        </tr>
                                  	</table>
								</form>
							</td>
						</tr>
					</table>			
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
		  		</td>
        		<td width="16">&nbsp;</td>
        		<td width="70%" valign="top" height="100%">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
					<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="10"><img src="x" width="1" height="1"></td>
						</tr>
<%
	if(Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_PARTICIPANT) == null) {
%>						<tr>
							<td valign="middle">
								<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12"> 
							</td>
							<td valign="middle">							
								<a href="layout.jsp?refer=/cenapra/ParticipantCreate.jsp?type=create" class="texts">
									<%=messages.getText("cenapra.messages.addparticipant")%>					
								</a>
							</td>
						</tr>
<%
	}
%>						<tr>
							<td height="47"><img src="x" width="1" height="1"></td>
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
        			<%
        				String page1 = "../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.list");
        			%>
					<jsp:include  page='<%=page1%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
								<form name="delete" action="layout.jsp?refer=/cenapra/deleteParticipant.jsp"  method="post">
									<table width="100%" border="0">
    	          						<tr align="center">
<%
	boolean delete = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_PARTICIPANT) == null;
		if(delete) {
%>    	          							<td width='3%' class="guide2"></td>
<%
	}
%>					               	 		<td width='15%' class="guide2"><%=messages.getText("participant.data.firstname")%></td>
						                	<td width='15%'	class="guide2"><%=messages.getText("participant.data.lastname")%></td>
<%
	String[] principalLabels=sys.getUserSessionData().getFilter().getPrincipalLabels();
		for (int z = 0 ; z < principalLabels.length ; z++){
%>					                		<td width='13%'	class="guide2"><%=messages.getText(principalLabels[z])%></td>
<%
	}
%>					                		<td width='9%'	class="guide2">
												<%=messages.getText("generic.driver.driverdata.birthdateabb")%>
							                	<div class="texts">
							                		<%=messages.getText("generic.messages.dateformat")+" "%>
							                	</div>
							                </td>
	              						</tr>
<%
	if(result.getTotal() == 0){
%>			    						<tr>
        	      							<td class="linetwo" colspan="7">
	        	      							<%=messages.getText("generic.messages.notresult")%>
	              							</td>
    	          						</tr>
<%
	}else {
	Iterator iter = result.getElements().iterator();
	int max = (Boolean.parseBoolean(request.getParameter("all"))) ? 
	result.getTotal() : Integer.parseInt((String)parameters.get("pageSize"));   
   			int count = 0;
	while(iter.hasNext() && (count < max)){
	    ParticipantMainAttributes participant = (ParticipantMainAttributes)iter.next();
%>		        						<tr class='<%=(count % 2 == 0) ? "lineone" : "linetwo"%>' >
<%
	if(delete) {
%>	            							<td width='3%' align="center">
												<input type='checkbox' name='<%=String.valueOf(participant.getId())%>' value='<%=String.valueOf(participant.getId())%>' >
											</td>
<%
	}
%>				  							<td width='15%' align="center">
	    	          							<a href='layout.jsp?refer=/cenapra/ParticipantView.jsp?id=<%=String.valueOf(participant.getId())%>' > <%=participant.getFirstName()%> </a>
											</td>
              								<td width='15%' align="center">
	              								<a href='layout.jsp?refer=/cenapra/ParticipantView.jsp?id=<%=String.valueOf(participant.getId())%>' > <%=participant.getLastName()%> </a>
											</td>
											<!--  TODO: Municipio y Estado ?? -->											
											<td align="center">
												<%=participant.getCity()%>
											</td>
											<td align="center">
												<%=participant.getStateName()%>
											</td>
<%
	if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
%>											<td align="center">
												<%=participant.getLocality()%>
											</td>
<%
	}
%>						               		<td width='9%' align="center">
												<%=Util.formatDate(participant.getBirthDate())%> 
											</td>
    	            					</tr>
<%			}
		}
%>		      						</table>
              						<input type="hidden" name="list" />
              						<input type="hidden" name="type" value="list" />
	            				</form>
    	        			</td>
        	    		</tr>
<%		if(result.getTotal() > 0) {
    			if(delete) {
%>            		<tr>
                			<td class="linetwo" align="right">
                    			<input name="button2" type="button" onClick="javascript:setAction(document.forms['delete']);" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
        					</td>
      					</tr>
<%			}	
%>     				<tr>
        					<td>
<%				session.setAttribute("paginationParameters",parameters);
%>        						<jsp:include page='<%="../util/pagination2.jsp?size="+parameters.get("pageSize")+"&num="+parameters.get("pageNum")+"&total="+String.valueOf(result.getTotal())+"&link="+link%>' />        
        					</td>
        				</tr>
<%		}	
%>     					
					</table>
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
