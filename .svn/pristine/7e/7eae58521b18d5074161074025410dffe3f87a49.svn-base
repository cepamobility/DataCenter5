<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="java.util.LinkedList"
	import="business.*"
	import="business.driver.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.util.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.driver.tables.*"
	import="communication.event.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
 	import="communication.util.DateValue"
 %>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;
	SystemAccess sys; DriverData data; 
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_DRIVER);
	
	// Permiso para Tests de Alcholemia
	String check2 = Util.checkPermission(sys,SecurityConstants.TESTS_DRIVER);
	
	if(check!=null && check2!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		String id=(String)request.getParameter("id");
		if(id==null){
	id=(String)request.getAttribute("id");
	request.removeAttribute("id");
		}
		if(id==null){
	id=(String)session.getAttribute("id");
	session.removeAttribute("id");
		}
		if(id!=null){	
	data=sys.getDriverReportFacade().findDriverByPrimaryKey(new Integer(id), sys.getUserSessionData());
		}
		pathView="./view.jsp?id="+id;
		session.setAttribute("url","driver/DriverView.jsp?id="+id);
	
		if(data!=null){
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<script>
	function deleteTest(checksForm,form){
		var elements=checksForm.elements;
		for(i=0;i<elements.length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(form.list.value == ""){
						form.list.value=element.value;
					}else{
						form.list.value=form.list.value+"<"+element.value;
					}	
				}	
			} 
		}
		if(element.value.length>0){
			form.submit();
		}
	}
</script>
<head/>
<html:form action="/DeleteAlcoholControl">
	<html:hidden property="driver" value='<%=String.valueOf(data.getId())%>'/>
	<html:hidden property="list"/>
</html:form>
<body bgcolor="#FFFFFF">
	<table width="500" align='center' cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.driver.editing")+"  "+data.getLastName()+","+data.getFirstName()%>' />
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<form name="checks">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.alcoholcontrol")%>' />
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td width='8%' class="guide2" align="center">&nbsp;</td>
									<td width='45%' class="guide2" align="center">
										<%=messages.getText("driver.alcoholcontrol.date")%>
									</td>
   									<td width='45%'	class="guide2" align="center">
   										<%=messages.getText("driver.alcoholcontrol.result")%>
   									</td>
								</tr>
<%
	Collection controls = data.getAlcoholControl();
	if(controls.size() == 0) {
%>								<tr>
									<td colspan="5">
										<span class="linetwo"><%=messages.getText("driver.alcoholcontrol.nodata")%></span>
									</td>								
								</tr>
<%
	}else {
    			Iterator itControls = controls.iterator();
    			boolean lineone = false;
    			while(itControls.hasNext()) {
    			    AlcoholControlData control = (AlcoholControlData)itControls.next();
%>								<tr align=center align="right" class='<%=(lineone) ? "lineone" : "linetwo"%>'>
									<td width='7%' class='<%=(lineone) ? "lineone" : "linetwo"%>' align="center">
										<input type="checkbox" value='<%=String.valueOf(control.getDate().getTime())%>'>
									</td>
						            <td width='31%' class='<%=(lineone) ? "lineone" : "linetwo"%>' align="right"><%=Util.formatDate(control.getDate())%> </td>
						            <td width='31%' class='<%=(lineone) ? "lineone" : "linetwo"%>' align="right"><%=(control.getResult().intValue() == DriverData.POSITIVE) ? messages.getText("generic.messages.positive") : messages.getText("generic.messages.negative")%> </td>
								</tr>
<%    				lineone = !lineone;
	   			}
			}
%>								<tr>
									<td colspan="4" class="guide2" align="right">
										<input type="button" value='<%=messages.getText("generic.messages.delete")%>' class="input" onclick="javascript:deleteTest(document.forms['checks'],document.forms['DeleteAlcoholControlForm']);"/>
									</td>
								</tr>	
							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>	
						</td>
			   		</tr>
			   	</table>
			   	</form>
			</td>
		</tr>			
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
		<tr>
			<td width="100%" colspan="5">
				<html:form action="/AddAlcoholControl">
					<html:hidden property="driver" value='<%=String.valueOf(data.getId())%>'/>
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("driver.alcoholcontrol.new")%>' />
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<th class="linetwo" align="left"><%=messages.getText("driver.alcoholcontrol.date")+" ( "+messages.getText("generic.supportmaterial.reports.format")+": dd/mm/aaaa )"%></th>
										<td class="linetwo">&nbsp;</td>
										<td class="linetwo" align="right">
	            							<html:text property="controlDate" style="width:20;" styleClass="input"/>&nbsp;/&nbsp;
	            							<html:text property="controlMonth" style="width:20;" styleClass="input"/>&nbsp;/&nbsp;
	            							<html:text property="controlYear" style="width:40;" styleClass="input"/>
		          						</td>
									</tr>
									<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							  		</tr>
									<tr>
										<th class="linetwo" align="left"><%=messages.getText("driver.alcoholcontrol.result")%></th>
										<td class="linetwo">&nbsp;</td>
										<td class="linetwo" align="right">
											<html:select property="result" styleClass="input">
												<html:option value='<%=String.valueOf(DriverData.POSITIVE)%>'><%=messages.getText("generic.messages.positive")%></html:option>
												<html:option value='<%=String.valueOf(DriverData.NEGATIVE)%>'><%=messages.getText("generic.messages.negative")%></html:option>
											</html:select>
		          						</td>
									</tr>
									<tr>
										<jsp:include  page="../component/spaceline.jsp" />
							  		</tr>
									<tr>
										<td colspan="3" class="linetwo" align="right">
											<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
										</td>
									</tr>	
								</table>
								<jsp:include page="../component/utilityboxbottom.jsp"/>	
							</td>
				   		</tr>
				   	</table>
			   	</html:form>
			</td>
		</tr>			
<%		}else {
%>		<jsp:include  page='<%="/util/allowedData.jsp?message=driver.error.access.driver" %>'/>
<%		}
	}
%>
</body>
</html:html>