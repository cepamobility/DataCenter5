<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages;	SystemAccess sys;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();

	String form = request.getParameter("form");
	String[] principal = sys.getUserSessionData().getFilter().getPrincipalLabels();
	Collection order1 = new LinkedList();	    
	Collection order2 = new LinkedList();	    
	Collection order3 = new LinkedList();	    
%>	<select name="allorder" style="display: none">	
<%	for(int i = 0; i < 3; i++) {
		order1.add(new OptionItem(messages.getText(principal[i]),String.valueOf(i)));	    
		if(i > 0) {
			order2.add(new OptionItem(messages.getText(principal[i]),String.valueOf(i)));	    
		}
		if(i > 1) {
			order3.add(new OptionItem(messages.getText(principal[i]),String.valueOf(i)));	    
		}
%>		<option value='<%=String.valueOf(i)%>'><%=messages.getText(principal[i])%></option>
<%	}
%>	</select>
<%	session.setAttribute("order1",order1);
	session.setAttribute("order2",order2);
	session.setAttribute("order3",order3);
%>

<script>
	function change1(formName){
		var all = document.forms[formName].elements['allorder'];
		var combo1 = document.forms[formName].elements['order1'];
		var combo2 = document.forms[formName].elements['order2'];
		var combo3 = document.forms[formName].elements['order3'];
		var value = combo1.options[combo1.selectedIndex].value;
		combo2.options.length = 2;
		combo3.options.length = 1;
		j = 0
		for(i = 0; i < 3; i++) {
			if(all.options[i].value != value) {
				combo2.options[j].value = all.options[i].value;
				combo2.options[j].text = all.options[i].text;
				if(j == 1) {
					combo3.options[0].value = all.options[i].value;
					combo3.options[0].text = all.options[i].text;
				}
				j = j + 1;
			}
		}
	}
	
	function change2(formName){
		var all = document.forms[formName].elements['allorder'];
		var combo1 = document.forms[formName].elements['order1'];
		var combo2 = document.forms[formName].elements['order2'];
		var combo3 = document.forms[formName].elements['order3'];
		var value1 = combo1.options[combo1.selectedIndex].value;
		var value2 = combo2.options[combo2.selectedIndex].value;
		combo3.options.length = 1;
		for(i = 0; i < 3; i++) {
			if(all.options[i].value != value1) {
				if(all.options[i].value != value2) {
					combo3.options[0].value = all.options[i].value;
					combo3.options[0].text = all.options[i].text;
				}
			}
		}
	}
</script>

							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.driver.orderby")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
          								<tr class="linetwo">
            								<th align="left" >1. </th>
            								<td align="right" colspan="2">
                								<html:select property="order1" styleClass="input" style="width:155;" onchange='<%="javascript:change1('"+form+"')"%>'>
                  									<html:options collection="order1" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
            								</td>
            							</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
            								<th align="left" >2. </th>
            								<td align="right" colspan="2">
                								<html:select property="order2" styleClass="input" style="width:155;"  onchange='<%="javascript:change2('"+form+"')"%>'>
                  									<html:options collection="order2" property="value" labelProperty="label" styleClass="input" />
                								</html:select>
            								</td>
            							</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
            								<th align="left" >3. </th>
            								<td align="right" colspan="2">
                								<html:select property="order3" styleClass="input" style="width:155;" >
                  									<html:options collection="order3" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
            								</td>
            							</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>	
            							