<%@page language="java"
	errorPage="../exception.jsp" 
    import="java.util.*"
    import="business.*"
    import="business.activity.*"
    import="communication.language.*"
    import="communication.security.*"
    import="communication.activity.tables.CEPAActivityConstants"
    import="communication.administration.user.*"    
    import="communication.administration.corporation.tables.*"
    import="presentation.translator.web.util.*"
%>
<%@page import="communication.cepasystem.tables.ActivityData"%>
<%@page import="business.activity.ActivityReportFacade"%>

<%!
    Text messages2; SystemAccess sys2; 
    
%>
<%
    sys2 = (SystemAccess)session.getAttribute("SystemAccess");
	messages2 = sys2.getText();
	ActivityData data2 = null;
	ActivityReportFacade report2 = sys2.getActivityReportFacade();
	 if(!Util.empty(request.getParameter("id"))) {
         data2 = report2.getCepaSystemActivity(new Integer(request.getParameter("id")),sys2.getUserSessionData());
     }else {
			if(session.getAttribute("activity") != null) {
             data2 = report2.getCepaSystemActivity(new Integer((String)session.getAttribute("activity")),sys2.getUserSessionData());
         }
     }
%>

<!-- Opciones -->
		
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="communication.cepasystem.tables.ActivityData"
%>
<tr>
			<td width="100%" valign="top" colspan="5">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages2.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
<%		if(data2.getState().intValue() == ActivityData.PENDING_SUPERVISION) {
%>			        <tr> 
    	      			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
        	  			<td width="46%">
        	  				<a href="javascript:document.forms['edit'].submit()" class="texts"> 
		    	        		<%=messages2.getText("generic.messages.edit")%> 
		    	        	</a> 
		        	    </td>
	         			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
    	      			<td>
	          				<a href="javascript:document.forms['supervise'].submit()" class="texts">
	          					<%=messages2.getText("generic.messages.supervise")%>
	          				</a>
            			</td>
    	    		</tr>
<%		}
%>					<tr class="texts1"> 
		  				<td width="3%"  class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          			<td >
    	      				<a href="javascript:document.forms['delete'].submit()" class="texts"> 
        	    				<%=messages2.getText("generic.messages.delete")%> 
        	    			</a> 
        	    		</td>
         				<td width="3%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
	          			<td width="49%">
<%		if(data2.getState().intValue() == ActivityData.PENDING_SUPERVISION) {
%>	          				<a href="javascript:document.forms['adddriver'].submit()" class="texts">
	          					<%=messages2.getText("cepasystem.activity.adddriver")%>
	          				</a>
<%		}
%>	          			</td>
            		</tr>
					<tr class="texts1"> 
		  				<td width="3%"  class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          			<td >
	          				<a href="layout.jsp?refer=cepasystem/ActivityList.jsp" class="texts">
	          					<%=messages2.getText("generic.messages.cancel")%>
	          				</a>
        	    		</td>
         				<td width="3%"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12"></td>
	          			<td width="49%">
	          			</td>
            		</tr>
	  			</table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>