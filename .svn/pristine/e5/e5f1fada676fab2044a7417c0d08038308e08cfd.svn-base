<%@page import="communication.administration.corporation.tables.CorporationData"%>
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
	Collection order4 = new LinkedList();
	boolean all = (request.getParameter("all") == null) ? false : Boolean.parseBoolean(request.getParameter("all"));
	int principalCount = sys.getUserSessionData().getFilter().getPrincipalCount()+1;
	
	if(sys.isCorporation(CorporationData.BAYER)){
		principal=sys.getUserSessionData().getFilter().getDivOrgLabels();
		principalCount=principal.length;
%>	<select name="allorder" style="display: none">	
<%	for(int i = 0; i < 6; i++) {
		int indexValue = (i < 3) ? i : i+2;
		if (i==0){
			if(all) {
				order1.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
			}
			order1.add(new OptionItem(messages.getText("corporation.country"),String.valueOf(3)));
			order2.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
			order3.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
			order4.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
		}
		order1.add(new OptionItem(messages.getText(principal[i]),String.valueOf(indexValue)));
		order2.add(new OptionItem(messages.getText(principal[i]),String.valueOf(indexValue)));
		
		if (i==0){
%>		<option value='<%=String.valueOf(4)%>'><%=messages.getText("generic.messages.select")%></option>
		<option value='<%=String.valueOf(3)%>'><%=messages.getText("corporation.country")%></option>
		
<% 		}
		
%>		<option value='<%=String.valueOf(indexValue)%>'><%=messages.getText(principal[i])%></option>
<% 
		
	}
%>	</select>
<%		}else {
	
%>	<select name="allorder" style="display: none">	
<%			for(int i = 0; i < principal.length; i++) {		
				if (i==0){
					if(all) {
						order1.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
					}
					order1.add(new OptionItem(messages.getText("corporation.country"),String.valueOf(3)));
					order2.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
					order3.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
					order4.add(new OptionItem(messages.getText("generic.messages.select"),String.valueOf(4)));
				}
				order1.add(new OptionItem(messages.getText(principal[i]),String.valueOf(i)));
				order2.add(new OptionItem(messages.getText(principal[i]),String.valueOf(i)));
				
				if (i==0){
%>		<option value='<%=String.valueOf(4)%>'><%=messages.getText("generic.messages.select")%></option>
		<option value='<%=String.valueOf(3)%>'><%=messages.getText("corporation.country")%></option>
		
<% 				}
%>		<option value='<%=String.valueOf(i)%>'><%=messages.getText(principal[i])%></option>
<% 
		
		}
%>	</select>
<%	}

	session.setAttribute("order1",order1);
	session.setAttribute("order2",order2);
	session.setAttribute("order3",order3);
	session.setAttribute("order4",order4);
%>

<script>
	function change1(formName){
		var all = document.forms[formName].elements['allorder'];
		var combo1 = document.forms[formName].elements['order1'];
		var combo2 = document.forms[formName].elements['order2'];
		var combo3 = document.forms[formName].elements['order3'];
		var combo4 = document.forms[formName].elements['order4'];
		var value = combo1.options[combo1.selectedIndex].value;
		combo3.selectedIndex='0';
		combo4.selectedIndex='0';
		combo3.disabled=true;
		combo4.disabled=true;
		combo2.options.length = <%=principalCount%>;
		j=1;
		for(i = 1; i < 5; i++) {
			if(all.options[i].value != value) {
				combo2.options[j].value = all.options[i].value;
				combo2.options[j].text = all.options[i].text;
				j+=1;
			}
		}
	}
	
	function change2(formName){
		var all = document.forms[formName].elements['allorder'];
		var combo1 = document.forms[formName].elements['order1'];
		var combo2 = document.forms[formName].elements['order2'];
		var combo3 = document.forms[formName].elements['order3'];
		var combo4 = document.forms[formName].elements['order4'];
		var value = combo1.options[combo1.selectedIndex].value;
		var value1 = combo2.options[combo2.selectedIndex].value;
		combo4.selectedIndex='0';
		combo4.disabled=true;
		if (value1!='4'){
			combo3.disabled=false;
			combo3.options.length = <%=String.valueOf(principalCount-1)%>;
			j=1;
			for(i = 1; i < all.options.length; i++) {
				if(all.options[i].value != value && all.options[i].value != value1) {
					combo3.options[j].value = all.options[i].value;
					combo3.options[j].text = all.options[i].text;
					j+=1;
				}
			}
		}else{
			combo3.selectedIndex='0';
			combo3.disabled=true;
		}
	}
	
	
	function change3(formName){
		var all = document.forms[formName].elements['allorder'];
		var combo1 = document.forms[formName].elements['order1'];
		var combo2 = document.forms[formName].elements['order2'];
		var combo3 = document.forms[formName].elements['order3'];
		var combo4 = document.forms[formName].elements['order4'];
		var value = combo1.options[combo1.selectedIndex].value;
		var value1 = combo2.options[combo2.selectedIndex].value;
		var value2 = combo3.options[combo3.selectedIndex].value;
		
		if (value2!='4'){
			combo4.disabled=false;
			combo4.options.length = <%=String.valueOf(principalCount-2)%>;
			j=1;
			for(i = 1; i < all.options.length; i++) {
				if(all.options[i].value != value && all.options[i].value != value1 && all.options[i].value != value2) {
					combo4.options[j].value = all.options[i].value;
					combo4.options[j].text = all.options[i].text;
					j+=1;
				}
			}
		}else{
			combo4.disabled=true;
			combo4.selectedIndex='0';
		}
	}
	
	
	
</script>

<tr>
	<td>
		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.driver.orderby")%>' />
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
        	<tr class="linetwo">
        		<th align="left" >1. </th>
        		<td align="right" colspan="2">
        			<%
        				String onchange1 = "javascript:change1('" + form + "')";
        			%>
        			
        			<html:select property="order1" styleClass="input" style="width:155;" onchange='<%=onchange1%>' > 
        				<html:options collection="order1" property="value" labelProperty="label" styleClass="input"/>
        			</html:select>
        		</td>
            </tr>
<%			if(principalCount > 1) { 
%>			<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			<tr class="linetwo">
            	<th align="left" >2. </th>
            	<td align="right" colspan="2">
            		<%
            			String onchange2 = "javascript:change2('"+form+"')";
            		%>
                	<html:select property="order2" styleClass="input" style="width:155;" onchange='<%=onchange2%>'>
                  		<html:options collection="order2" property="value" labelProperty="label" styleClass="input" />
                	</html:select>
            	</td>
           	</tr>
<%			}else {
%>    		<html:hidden property="order2" value="4"/>
<%			}
			if(principalCount > 2) { 
%>         	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			<tr class="linetwo">
            	<th align="left" >3. </th>
            	<td align="right" colspan="2">
            		<%
            			String onchange3 = "javascript:change3('"+form+"')";
            		%>
            		<html:select property="order3" styleClass="input" style="width:155;" onchange='<%=onchange3%>'>
            			<html:options collection="order3" property="value" labelProperty="label" styleClass="input"/>
            		</html:select>
            	</td>
            </tr>
<%			}else {
%>    		<html:hidden property="order3" value="4"/>
<%			}
			if(principalCount > 3) { 
%>        	<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			<tr class="linetwo">
        		<th align="left" >4. </th>
        		<td align="right" colspan="2">
            		<html:select property="order4" styleClass="input" style="width:155;" >
            			<html:options collection="order4" property="value" labelProperty="label" styleClass="input"/>
            		</html:select>
            	</td>
            </tr>
<%			}else {
%>    		<html:hidden property="order4" value="4"/>
<%			}
%>		</table>
		<jsp:include  page="../component/utilityboxbottom.jsp" />
	</td>
</tr>								
     