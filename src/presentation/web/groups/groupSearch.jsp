<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.fleet.*"
	import="business.administration.group.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.group.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<script language="JavaScript" type="text/javascript">
function submitPagination(page,all) {
	document.forms['search'].all.value = all;
	document.forms['search'].pageNum.value = page;
	document.forms['search'].submit();
}
function doSubmit() {
	document.forms['search'].submit();
}
function addIFConfirm(form,msg){

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
</script>
<html:html>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
	//grouplist
	Integer pageNum; boolean all; int pageSize; 
	String value;String type;String status;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_GROUP_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	    String groupId = request.getParameter("id");
	    String action = request.getParameter("action");
	    String groups = request.getParameter("groups");
	    String link = "groups/groupSearch.jsp?action="+action;
	    if(!Util.empty(groupId)) {
	        link += "&id="+groupId;
	    }
	    if(!Util.empty(groups)) {
	        link += "&groups="+groups;
	    }
		session.setAttribute("url",link);
		
		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();
	
		if(request.getParameter("pageNum")!=null){
	pageNum=new Integer(request.getParameter("pageNum"));
		}else{
	pageNum=new Integer(1);
		}

		value = "";
		if(request.getParameter("value")!=null){
	value = request.getParameter("value");
		}		
		pageSize = 10;
		if(request.getParameter("pageSize")!=null){
	pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}		
		int first = 0;
		int count = pageSize;
		all=!Util.empty(request.getParameter("all")) && request.getParameter("all").equals("true");
		if(all) {
		    count=-1;
		}else{
		    first=(pageNum.intValue()-1)*pageSize;
		}
		GroupReportFacade groupReportFacade = sys.getGroupReportFacade();
		String link2 = request.getContextPath()+link;
		ListResult groupList = groupReportFacade.findSearchList(value,first,count,sys.getUserSessionData(),groups);
%>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<style type="text/css">	</style>
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>

	<body bgcolor="#FFFFFF">
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.data.organizationalgroups")%>' />
	  		</tr>
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	      	<tr>
    	    	<td>
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%">
								<form action='<%="layout.jsp?refer="+link%>' method="post" name="search">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/>    
	 											<input type="hidden" name="all" <%=(all) ? "value='all'" : "" %>/>    
												<span class="input"><%=messages.getText("generic.fleet.fleetdata.name")%></span>
											</td>
											<td>
												<input type="text" name="value" value='<%=value%>'  width="100%"class="input">    
											</td>
										</tr>  
		      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>  
											<td colspan="2" align="right">
	   											<input type="hidden" name="pageSize" value='<%=pageSize%>'/>    
												<input name="button" type="button" onClick="javascript:doSubmit();" value='<%=messages.getText("generic.messages.search")%>' class="input"/>	
											</td>	
										</tr>  
									</table>	
								</form>			
							</td>
						</tr>
					</table>			
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
    	  	</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
    	  	<tr>
        		<td colspan="3">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.group.grouplist")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
<%		int total = groupList.getTotal();
		if(total == 0) {
%>  	      					<%=messages.getText("generic.messages.notresult")%>
<%		}else {
%>			    				<html:form action='<%="/"+action%>'  >
									<html:hidden property="group" value='<%=groupId%>' />
									<html:hidden property="list" />
				    				<table width="100%" border="0">
            		  					<tr align="center">
        	    	  						<td width='5%' class="guide2"></td>
							                <td width='24%' class="guide2"><%=messages.getText("generic.fleet.fleetdata.name")%></td>
							                <td width='48%'	class="guide2"><%=messages.getText("generic.fleet.fleetdata.description")%></td>
					    		            <td width='24%'	class="guide2"><%=messages.getText("generic.fleet.fleetdata.type")%></td>
              							</tr>
<%			Iterator it = groupList.getElements().iterator();
			count = 0;
			boolean linetwo = true;
			while(it.hasNext() && (count < pageSize || all)) {
				GroupMainAttributes group = (GroupMainAttributes)it.next();
%>		            					<tr align=center align="right" class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
	    	        						<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(group.getId())%>' value='<%=String.valueOf(group.getId())%>' ></td>
							                <td width='24%' align="right">
												<a href='layout.jsp?refer=/groups/groupView.jsp?id=<%=group.getId()%>'>
													<%=group.getName()%> 
												</a>
											</td>
	          								<td width='48%' align="right">
	              								 <%=group.getDescription()%> 
	              							</td>
							                <td width='24%' align="right"><%=(group.getType().equals(GroupMainAttributes.COMPOSED)) ? messages.getText("generic.fleet.colective") : messages.getText("generic.fleet.individual")%> </td>
          	      						</tr>
<%				count++;
				linetwo = !linetwo;
			}
%>		      						</table>
            					</html:form>
		    				</td>
            			</tr>
		    			<tr>
            				<td>
            					<table width='100%'  border="0" cellpadding="0" cellspacing="0" class="default">
              						<tr>
                						<td class="linetwo" align="right">
                    						<input name="button2" type="button" onClick="javascript:addIFConfirm(document.forms['<%=action+"Form"%>'],'<%=messages.getText("web.group.confirmadd")%>');" value='<%=messages.getText("generic.messages.add")%>' class="input"/>
		            					</td>
        	      					</tr>
          						</table>
          					</td>
      					</tr>
	      				<tr>
    	    				<td>
<%			Hashtable paginationParameters = new Hashtable();
			paginationParameters.put("value",value);
			session.setAttribute("paginationParameters",paginationParameters);
%>								<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(total)+"&link="+link%>' />        
<%		}
%>							</td>
      					</tr>
					</table>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
    	  	</tr>
	    </table>
	</body>
<%	}	
%>	
</html:html>
