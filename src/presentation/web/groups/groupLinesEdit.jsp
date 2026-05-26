<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.group.*" 
	import="business.datatype.*" 
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.security.*"
	import="communication.administration.group.tables.*"
	import="communication.datatype.tables.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! Text messages; String pathLanguage;
	GroupReportFacade groupReport; 
	SystemAccess sys;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_GROUP);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String id=(String)request.getParameter("id");
		if(id==null){
			id=(String)session.getAttribute("id");
			session.removeAttribute("id");
		}
		if(id!=null){	
			session.setAttribute("url","groups/groupLinesEdit.jsp?id="+id);
			messages=sys.getText();
			groupReport = sys.getGroupReportFacade();
			GroupData data = groupReport.findGroup(new Integer(id),sys.getUserSessionData());
			if(data != null) {
				String width="100%";
				switch(sys.getUserSessionData().getFilter().getDivOrgCount()) {
				    case 1:
				        width="47%";
				        break;
				    case 2:
				        width="31%";
				        break;
				    case 3:
				        width="24%";
				        break;
				}
%>

<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<head/>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<script>
function deleteConfirm(form,msg){

	var elements=form.elements;
	var length=elements.length;
	var i;
	var valueCheckboxParamList="";
		
	for(i=0;i<length;i++){
		var element=elements[i];
		if(element.type.toLowerCase()=="checkbox"){
			if(element.checked){
				if(valueCheckboxParamList==""){
					valueCheckboxParamList=element.value;
				}else{
					valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
				}	
			}	
		} 
	}
	form.list.value=valueCheckboxParamList;
	if(valueCheckboxParamList.length>0){
		if(confirm(msg)){
			form.submit();
		}
	}
}
function cambio(parent,child,message){
	var parentCombo = document.forms[3].elements[parent];
	var childCombo = document.forms[3].elements[child];
	if(parentCombo.options[parentCombo.selectedIndex].value != "") {
		var divi = parentCombo.options[parentCombo.selectedIndex].text+parentCombo.options[parentCombo.selectedIndex].value;
		var list = document.forms[3].elements[divi];
		childCombo.options.length=list.options.length;
		for(i=0; i < list.length; i++) {
			childCombo.options[i].value = list.options[i].value;
			childCombo.options[i].text = list.options[i].text;
		}
	}else {
		childCombo.options.length=1;
		childCombo.options[0].value = "";
		childCombo.options[0].text = message;
	}
}
</script>

	<body bgcolor="#FFFFFF">
	
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.group.lines")+" "+data.getName()%>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
			<html:form action="/Groupdeleteline"  >
				<html:hidden property="group" value='<%=id%>' /> 
				<html:hidden property="list" /> 
		  		<tr>
    	    		<td colspan="3">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("group.data.lines")%>' />
		        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
        				<tr>
	        				<td class="guide2" width="5%">&nbsp;</td>				    
							<td class="guide2"><%=messages.getText("corporation.divgeo.countries")%></td>				            				
<%			DivOrgAttribute[] attrs = sys.getUserSessionData().getFilter().getDivOrgsAttrs();
			int count = 0;
			for(int i = 0; i < attrs.length; i++) {
			    if(attrs[i] != null) {
				    count++;
%>							<td class="guide2"><%=messages.getText(attrs[i].getName())%></td>				    
<%				}
			}
%>						</tr>	
<%			Iterator it = data.getLinesIterator();
			boolean linetwo = false;
			if(it.hasNext()) {
				while(it.hasNext()) {
					GroupLineData line = (GroupLineData)it.next();
%>								<tr>
									<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(line.getId())%>' value='<%=String.valueOf(line.getId())%>' ></td>
									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='<%=width%>'>
										<%=line.getGeoLabel(messages)%>
									</td>
<%					for(int i = 0; i < count; i++) { 
    					String label = line.getLabel(i);
%>									<td class='<%=(linetwo) ? "linetwo" : "lineone" %>' width='<%=width%>'>
										<%=(!Util.empty(label)) ? label : ""%>
									</td>
<%					}	
%>								</tr>
<%					linetwo = !linetwo;
				}
			}else {
%>								<tr>
									<td class="linetwo" colspan="5">
										<%=messages.getText("generic.messages.notresult")%>
									</td>
								</tr>
<%			}
%>      						<tr>
									<jsp:include  page="../component/spaceline.jsp" />
  								</tr>
								<tr>
									<td class="linetwo" colspan="5" align="right">
									<%
										String onclick1 = "javascript:deleteConfirm(document.forms[2],'"+messages.getText("fleet.division.delete.confirm")+"')";
									%>
										<input type="button" value='<%=messages.getText("generic.messages.delete")%>' onclick="<%=onclick1%>" class="input" />
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
      			</tr>
      		</html:form>	
   			<html:form action="/Groupaddline">
   				<html:hidden property="id" value='<%=id%>'/>
      			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
		  		<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    	<%
                    		String page1 = "../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+"Corporation.jsp?form=GroupAddLineForm&group=true";
                    	%>
                	   		<jsp:include page='<%=page1%>' />
                    	 </table>
	   	             </td>
    	            <td width="16">&nbsp;</td>
        	       	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                	   		<jsp:include page='<%="/util/groupDivOrg.jsp?form=GroupAddLineForm&consult=false" %>'/>
                    	 </table>
	   	             </td>
    	    		<td>
      			</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr  class="linetwo">
	        		<td colspan="3" align="right">
						<html:submit value='<%=messages.getText("generic.messages.add")%>' styleClass="input" />
						<html:cancel value='<%=messages.getText("generic.messages.exit")%>' styleClass="input" />
	          		</td>
	      		</tr>
      		</html:form>
        </table>
	</body>
<%			}else {
%>	<jsp:include  page='<%="/util/allowedData.jsp?message=group.error.access.group" %>'/>
<%			}
		}
	}
%>
</html:html>

