 <%@page language="java" 
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.SystemAccess"
	import="business.cenapra.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="communication.administration.user.UserSessionData"  	
	import="communication.activity.tables.*" 
	
%>  

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_PARTICIPANT);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	    String participants = request.getParameter("list");
		session.setAttribute("url","cenapra/deleteParticipant.jsp?list="+participants);

		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		Text messages=sys.getText();

		CENAPRAReportFacade report = sys.getCENAPRAReportFacade();
		Collection list = report.findParticipants(participants,userSessionData);
%>		
	<body>
		<html:form action="/ParticipantDelete">
			<html:hidden property="list" value='<%=participants%>' />
			<html:hidden property="type" value='<%=request.getParameter("type")%>' />
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("cenapra.participant.delete")%>' />
	  			</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td colspan="3"> 
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.todelete")%>' />
						<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
			      			<tr>
    	  						<td width="100%">
	    							<table width="100%" border="0">
           								<tr align="center">
								            <td  width="12%" class="guide2"><%=messages.getText("generic.activity.activitydata.date")%><%=" "+messages.getText("generic.messages.dateformat")%></td>
								            <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.city")%></td>					                					                
					    			    	<td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.state")%></td>					                
										</tr>
<%			Iterator it = list.iterator();
			int counter = 0;
			while(it.hasNext()){
				counter++;
  	 			Object[] participant = (Object[])it.next();
%>     									<tr align=center align="right" class='<%=((counter % 2)==0) ? "lineone" : "linetwo"%>'>                
								            <td  width="40%" align="right"><%=String.valueOf(participant[0])%> </td>				
								            <td  width="40%" align="right"><%=String.valueOf(participant[1])%> </td>				
											<td  width="20%" align="right"><%=Util.formatDate((Date)participant[2])%></td>
            							</tr>
<%			}
%>		   							</table>
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
                    <td width="100%" colspan="3">
                        <jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                            	<td>
                                	<html:textarea property="comment" cols="80" />
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
                  	<td height="18" colspan="3" valign="top" align="right">
						<html:submit styleClass="input" value='<%=messages.getText("generic.messages.delete")%>' />                                                  
                       	<html:cancel styleClass="input"  value='<%=messages.getText("generic.messages.cancel")%>' />                                                                            		
					</td>
				</tr>
			</table>
	  	</html:form>
	</body>
<%	}
%>
</html:html>