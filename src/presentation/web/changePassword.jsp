<%@page language="java" 
    errorPage="exception.jsp"
	import="business.*"
	import="business.administration.user.*" 
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="org.apache.struts.action.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	int level;
%>
<%
		
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages=sys.getText();

	level=Integer.parseInt((String)request.getParameter("level"));
	int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
%>

<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
<style type="text/css">
<!--
body {
	background-color: #F7F7F7;
}
-->
</style>

<script>
	function fechar_janela() {
		window.close();
		
	}
</script>





<head/>
	<title><%=messages.getText("web.user.resetpassword.resetpassword")%></title>


	<html:form action="/ResetPasswordUser" >
	
  		<body>
	  		<table width='<%=(corporationId == CorporationData.JJ) ? "470" : "230" %>' height="200" align='center' cellpadding="0" cellspacing="0" bgcolor="#F7F7F7">
<%			if(level == -1){
%>    			<tr>
					<th colspan="2" align="center"><%=messages.getText("generic.user.userdata.changepassword.success")%></th>
				</tr>
				<tr class="lineone">
					<td>
						<div align='center'>
							<input name='<%=messages.getText("generic.messages.accept")%>' type="button"  value='<%=messages.getText("generic.messages.accept")%>' onClick="fechar_janela()" class="input">									    		  		
						</div>	
					</td>										
				</tr>
<%			}else {
    			if(corporationId == CorporationData.JJ) {
%>    			<tr>
					<td colspan="4">
						<table width="100%" border="1" bordercolor="black" >
							<tr>
								<td>
									<table width="100%" align='center' cellpadding="0" cellspacing="0" bgcolor="#F7F7F7">
										<tr>
											<th colspan="2" align="left" class="input"><%=messages.getText("Following recommendations resulting from a J&J Risk Assessment, Data Center password policies have been aligned with J&J's Worldwide Policies on Information Asset Protection (WWIAPs).")%></th>
										</tr>
						    			<tr>
											<th colspan="2" align="left" class="input">&nbsp;</th>
										</tr>
						    			<tr>
											<th colspan="2" align="left" class="input"><%=messages.getText("This policies state that passwords should:")%></th>
										</tr>
						    			<tr>
											<th width="3%" class="lineone">-</th>
											<td align="left" class="input"><%=messages.getText("have 3 levels of complexity (English Upper Case Letters, English Lower Case Letters, Westernized Arabic Numerals, and/or Non-alphanumeric (@#$%^&+=))")%></td>
										</tr>
						    			<tr>
											<th width="3%" class="lineone">-</th>
											<td align="left" class="input"><%=messages.getText("have a minimum of 6 characters")%></td>
										</tr>
						    			<tr>
											<th width="3%" class="lineone">-</th>
											<td align="left" class="input"><%=messages.getText("expire every 90 days at a minimum")%></td>
										</tr>
						    			<tr>
											<th width="3%" class="lineone">-</th>
											<td align="left" class="input"><%=messages.getText("expire upon first logon (Initial passwords)")%></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
<%				}
				if(session.getAttribute("Msg") != null){
%>    			<tr>
		  			<td width="10%" height="10">&nbsp;</td>
					<td colspan="2" align="center" class="red"><%=messages.getText((String)session.getAttribute("Msg"))%></td>
		  			<td width="10%" height="10">&nbsp;</td>
				</tr>
<%					session.removeAttribute("Msg");
				}
%>				  		
		  		<tr>
		  			<td width="10%" height="10">&nbsp;</td>
		  			<td width="40%" height="10">&nbsp;</td>
		  			<td width="40%" height="10">&nbsp;</td>
		  			<td width="10%" height="10">&nbsp;</td>
		  		</tr>
				<tr class="lineone">
		  			<td width="10%" height="10">&nbsp;</td>
					<td height="30" align="left"><b><%=messages.getText("generic.user.userdata.pass")%></b></td>
					<td height="30" align="left"><html:password property="password" styleClass="input" /></td>
		  			<td width="10%" height="10">&nbsp;</td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr class="lineone">
		  			<td width="10%" height="10">&nbsp;</td>
					<td height="30" align="left"><b><%=messages.getText("web.user.create.confirmpassword")%></b></td>
					<td height="30" align="left"><html:password property="rePassword" styleClass="input"/> </td>										
		  			<td width="10%" height="10">&nbsp;</td>
				</tr>
				<tr><td height="10">&nbsp;</td><td height="10">&nbsp;</td></tr>
				<tr class="lineone">
		  			<td width="10%" height="10">&nbsp;</td>
					<td colspan="2">
						<div align='center'>
							<html:submit value='<%=messages.getText("generic.messages.accept")%>'styleClass="input" /> 
							<input name='<%=messages.getText("generic.messages.close")%>' type="button"  value='<%=messages.getText("generic.messages.cancel")%>' onClick="fechar_janela()" class="input">									    		  		
						</div>	
					</td>					 					
		  			<td width="10%" height="10">&nbsp;</td>
				</tr>
<%			}
%>			</table>
  		</body>
	</html:form>	
</html:html>