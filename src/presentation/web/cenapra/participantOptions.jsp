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
<%!
	
	Text messages2; SystemAccess sys2;
%> 
<%
	sys2 = (SystemAccess)session.getAttribute("SystemAccess");
	messages2 = sys2.getText();
%>

<!-- Opciones -->
		<tr>
			<td width="100%" valign="top" colspan="5">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages2.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
<%		if(sys2.getUserSessionData().checkPermission(SecurityConstants.IMESEVI_UPDATE_PARTICIPANT)) {
%>			        <tr> 
    	      			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
        	  			<td width="46%">
        	  				<a href="javascript:document.forms['edit'].submit()" class="texts"> 
		    	        		<%=messages2.getText("generic.messages.edit")%> 
		    	        	</a> 
		        	    </td>
	         			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
    	      			<td>
    	      				<a href="javascript:document.forms['delete'].submit()" class="texts"> 
        	    				<%=messages2.getText("generic.messages.delete")%> 
        	    			</a> 
            			</td>
    	    		</tr>
<%		}
%>					<tr class="texts1"> 
          				<td width="3%"  class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
	          			<td >
	          				<a href="layout.jsp?refer=cenapra/ParticipantList.jsp" class="texts">
	          					<%=messages2.getText("generic.messages.cancel")%>
	          				</a>
        	    		</td>
          				<td width="3%">&nbsp;</td>
	          			<td width="49%">
	          				&nbsp;
	          			</td>
            		</tr>
	  			</table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>