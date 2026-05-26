<%@page language="java" 
	import="business.*"
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.administration.corporation.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="org.apache.struts.action.*"
	errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! Text messages; String pathLanguage;
	CorporationData corporation;
	String level1,level2,label1,label2,level3;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		session.setAttribute("url","corporation/NewCountry.jsp");
		session.removeAttribute("target");

		corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
		String level = (String)request.getParameter("level");
		if(Integer.parseInt(level) > 1) {
			if(!Util.empty((String)session.getAttribute("level1"))) {
				level1 = (String)session.getAttribute("level1");
				label1 = null;
				session.removeAttribute("level1");
			}else {
				level1=(String)request.getParameter("level1");
				label1=(String)request.getParameter("label1");
			}
		}else{
			level1=null;
			label1=null;
		}
		if(Integer.parseInt(level) > 2) {
			if(!Util.empty((String)session.getAttribute("level2"))) {
				level2 = (String)session.getAttribute("level2");
				label2 = null;
				session.removeAttribute("level2");
			}else {
				level2=(String)request.getParameter("level2");
				label2=(String)request.getParameter("label2");
			}
		}else{
			level2=null;
			label2=null;
		}
		level3=(String)request.getParameter("level3"); 
		if(corporation != null) {
			CorporationCountryForm actionForm = new CorporationCountryForm(level, level1,label1,level2,label2,level3);
			session.setAttribute("CorporationAddCountryForm",actionForm);
			String label = corporation.getGeoLabels()[Integer.parseInt(level)-1].getResourceKey();
%>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head/>
<script>

function addItem(form,level){
	var name = 'label'+level+'';
	form.elements[name].value = form.elements['newItem'].value;
	form.submit();
}

</script>

<html:form action="Corporationaddcountry">
	<html:hidden property="level" />   
	<html:hidden property="level1" />   
	<html:hidden property="label1" />   
	<html:hidden property="level2" />   
	<html:hidden property="label2" />   
	<html:hidden property="label3" />   

	<table width='40%' align='center' cellpadding="0" cellspacing="0">
    	<tr class="title2">
      		<td width="9"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40" /></td>
      		<td background="/datacenter/util/imgs/bg2.jpg" class="title2"><div align="center"><%=messages.getText("generic.messages.add")%> <%=messages.getText(label)%></div></td>
      		<td width="9"><div align="right"><img src="/datacenter/util/imgs/titulo_right.jpg" height="40" /></div></td>
		</tr>
	</table>
	<br>
	<table width='40%' align='center' cellpadding="0" cellspacing="0">
		<tr align='center' width='100%' class="linetwo">
			<td valign='top' align='left'>
				<div align="left"><%=messages.getText("generic.corporation.divgeo.insertname")%>  <%=messages.getText(label)%></div>
			</td>
		</tr>
		<tr>
			<td valign='top' align='left' class="linetwo">
				<input type="text" name="newItem" size="50" class="input"/>
	    	</td>
	    </tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="linetwo">
	   			<div align="right">
   					<input type="button" name="corporationName" onClick="javascript:addItem(document.forms[2],'<%=level%>');" value='<%=messages.getText("generic.messages.next")%>' size="50" class="input"/>
		            <html:cancel styleClass="input">
						<%=messages.getText("generic.messages.cancel")%>
					</html:cancel>
  				</div>
			</td>
		</tr>
	</table>		
</html:form>
<%		}
	}
%>
</html:html>
