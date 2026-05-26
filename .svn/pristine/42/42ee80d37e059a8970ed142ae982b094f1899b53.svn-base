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
	import="communication.travel.tables.*"
	import="communication.datatype.tables.*"
	import="communication.driver.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="communication.administration.corporation.tables.*"
%>
<%
	Text messages; 
	SystemAccess sys;
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	Integer eventId = null;
	if(Util.empty(request.getParameter("id"))) {
		eventId = (Integer)session.getAttribute("id");
	}
	messages=sys.getText();
%>

			<!-- EventViewOptions -->
			<td colspan="3">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
					<tr valign="bottom">
						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['edit'].submit()" class="texts">
			         			<%=messages.getText("generic.messages.edit")%>
			         		</a>
						</td>
						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href='<%="javascript:confirmDelete(document.forms[\"TravelDeleteForm\"],\""+messages.getText("generic.travel.confirmdelete")+"\")"%>' class="texts">
								<%=messages.getText("generic.messages.delete")%>
							</a>
						</td>
					</tr>
					<tr valign="bottom">
						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['checkpoints'].submit()" class="texts">
								<%=messages.getText("travel.checkpoints.edit")%>
							</a>
						</td>
						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['back'].submit()" class="texts">
								<%=messages.getText("generic.messages.cancel")%>
							</a>
						</td>
					</tr>
					<tr valign="bottom">
						<td width="50%">
							<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
							<a href="javascript:document.forms['close'].submit()" class="texts">
								<%=messages.getText("travel.close.edit")%>
							</a>
						</td>
						<td width="50%">
							&nbsp;
						</td>
					</tr>
				</table>
				<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
			