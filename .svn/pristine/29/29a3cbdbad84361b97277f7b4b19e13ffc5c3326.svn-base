<%@page language="java" 
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.event.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.event.tables.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="communication.administration.corporation.tables.*"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	boolean excluded = Boolean.parseBoolean(request.getParameter("excluded"));
%>
			<td colspan="3">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
					<tr valign="bottom">
<%	if(Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE) == null) {
%>						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['edit'].submit()" class="texts">
			         			<%=messages.getText("generic.messages.edit")%>
			         		</a>
						</td>
<%	}
%>						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['back'].submit()" class="texts">
								<%=messages.getText("generic.messages.cancel")%>
							</a>
						</td>
					</tr>
<%	if(Util.checkPermission(sys,SecurityConstants.DELETE_VEHICLE) == null || Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE) == null) {
%>					<tr valign="bottom">
<%		if(Util.checkPermission(sys,SecurityConstants.DELETE_VEHICLE) == null) {
%>						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['delete'].submit()" class="texts">
								<%=messages.getText("generic.messages.delete")%>
							</a>
						</td>
<%		}
		if(Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE) == null) {
%>						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['exclusion'].submit()" class="texts">
		    	     			<%=(excluded) ? messages.getText("generic.driver.include") : messages.getText("generic.driver.exclude")%>
							</a>
						</td>
<%		}
%>					</tr>
<%	}
%>
				</table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
