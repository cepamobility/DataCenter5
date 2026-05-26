<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.event.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.travel.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String value;String type;String status;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = null;
	if(sys.getUserSessionData().isDriver()) {
	    check = Util.checkPermission(sys,SecurityConstants.DRIVER_VIEW_EVENT_LIST);
	}else {
	    check = Util.checkPermission(sys,SecurityConstants.VIEW_EVENT_LIST);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","travel/TravelList.jsp");

		context=request.getContextPath();
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();
	
		session.setAttribute("target","TravelList");

		session.removeAttribute("FineForm");
		session.removeAttribute("EventForm");
		session.removeAttribute("AccidentForm");
		session.removeAttribute("FineSuperviseForm");
		session.removeAttribute("EventSuperviseForm");

		UserReportFacade userReport=sys.getUserReportFacade();
		if(request.getParameter("pageNum")!=null){
	pageNum=new Integer(request.getParameter("pageNum"));
		}else{
	pageNum=new Integer(1);
		}
		value = "";
		if(request.getParameter("value")!=null){
	value = request.getParameter("value");
		}		
		type = "0";
		if(request.getParameter("type")!=null){
		    type = request.getParameter("type");
		}		
		status = "0";
		if(request.getParameter("status")!=null){
	status = request.getParameter("status");
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

		String link = request.getContextPath()+"/travel/TravelList.jsp";

		ListResult result = sys.getEventReportFacade().findTravelList(value,Integer.parseInt(status),sys.getUserSessionData(),first,count);
%>


	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style><head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
<script language="JavaScript" type="text/javascript">
function doSubmit() {
	document.forms[2].submit();
}
function submitPagination(page,all) {
	document.forms[2].all.value = all;
	document.forms[2].pageNum.value = page;
	document.forms[2].submit();
}
function setAction(form,action){
	form.action.value=action;
}
function deleteIFConfirm(form,msg){

	var elements=form.elements;
	var length=elements.length;
	var i;
	var valueCheckboxParamList="";
	var separator="<";
		
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
	form.travel.value=valueCheckboxParamList;
	if(valueCheckboxParamList.length>0){
		if(confirm(msg)){
			form.submit();
		}
	}
}
</script>
<body bgcolor="#FFFFFF">
	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.data.travel.pl")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
      	</tr>
      	<tr>
        	<td width="50%">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%">
							<form action="layout.jsp?refer=/travel/TravelList.jsp" method="post">
								<table class="texts" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
<%
	if(sys.getUserSessionData().isDriver()) {
%>										<td>
											<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/>    
	 										<input type="hidden" name="all" <%=(all) ? "value='all'" : ""%>/>    
											<input type="hidden" name="value" value='<%=sys.getUserSessionData().getDriverData().getLastName()%>'>    
										</td>
<%
	}else{
%>										<td>
											<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/>    
	 										<input type="hidden" name="all" <%=(all) ? "value='all'" : ""%>/>    	
											<span class="texts"><%=messages.getText("generic.event.eventdata.firstorlastname")%></span>
										</td>
										<td>
											<input type="text" name="value" value='<%=value%>'  width="30" class="input">    
										</td>
<%
	}
%>	
										
										
									</tr>  
	      							<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									
									<tr>
										<td>
											<input type="hidden" name="type" value="0">
											<span class="texts"><%=messages.getText("travel.status")%></span>
										</td>
										<td>
											<select name="status" class="input" >
												<option value="0" class="input">
													<%=messages.getText("generic.messages.all")%>
												</option>
												<option value='<%=String.valueOf(TravelDataType.PENDING)%>' class="input" <%=(Integer.parseInt(status) == TravelDataType.PENDING) ? "selected" : ""%> >
													<%=messages.getText("travel.status.pending")%>
												</option>
												<option value='<%=String.valueOf(TravelDataType.STARTED)%>' class="input" <%=(Integer.parseInt(status) == TravelDataType.STARTED) ? "selected" : ""%> >
													<%=messages.getText("travel.status.started")%>
												</option>
												<option value='<%=String.valueOf(TravelDataType.FINALIZED)%>' class="input" <%=(Integer.parseInt(status) == TravelDataType.FINALIZED) ? "selected" : ""%> >
													<%=messages.getText("travel.status.finalized")%>
												</option>
											</select>
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
        	<td width="16">&nbsp;</td>
        	<td width="50%" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="texts">
					<tr>
						<td>
							<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;
							<span class="texts">
								<a class="texts" href="layout.jsp?refer=/driver/DriverSearch.jsp?link=layout.jsp?refer=/travel/SelectVehicle.jsp&title=generic.travel.selectdriver">
									<%=messages.getText("generic.messages.addtravel")%>					
								</a>
							</span>
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
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.travel.list")%>' />
				<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      				<tr>
        				<td width="100%">
<%
	int total = result.getTotal();
%>		    				<html:form action="/Traveldelete"  >
		    					<table width="100%" border="0" id="drivers">
              						<tr align="center">
	              						<td width='5%' class="guide2"></td>
							            <td width='45%'	class="guide2"><%=messages.getText("generic.event.eventdata.driver")%></td>
							            <td width='15%' class="guide2"><%=messages.getText("travel.leave.date")%></td>
							            <td width='15%' class="guide2"><%=messages.getText("travel.arrive.hour")%></td>
							            <td width='20%' class="guide2"><%=messages.getText("travel.status")%></td>
              						</tr>
<%
	if(total == 0) {
%>
              						<tr align="left">
	              						<td colspan="5">
											<%=messages.getText("generic.messages.notresult")%>
										</td>
									</tr>
<%
	}else {
	Collection elements = result.getElements();
	Collections.sort((List)elements);
	Iterator iter = elements.iterator();
	int counter = 0;
	boolean linetwo = true;
	while(iter.hasNext() && (counter < pageSize || all)){
		counter++;
		TravelDataType event=(TravelDataType)iter.next();
%>									<tr align=center align="right" class='<%=(linetwo) ? "linetwo" : "lineone"%>'>                
	            						<td width='5%' align="center"><input type='checkbox' name='<%=String.valueOf(event.getId())%>' value='<%=String.valueOf(event.getId())%>' ></td>
		          						<td width='45%' align="left">
		          							<a href='<%="layout.jsp?refer=/travel/TravelView.jsp?id="+String.valueOf(event.getId())%>'>
		            						 	<%=event.getDriver().getFirstName()+" "+event.getDriver().getLastName()%>
		            						</a>
		            					</td> 
						            	<td width='15%' align="center"><%=Util.formatDate(event.getDateLeave())+" "+Util.formatHour(event.getHourLeave())%></td>
					                	<td width='15%' align="center"><%=Util.formatHour(event.getHourArrive())%> </td>
					                	<td width='20%' align="center">
					                		<%=messages.getText(event.getState())%> 
					                	</td>
		                			</tr>
<%	
				linetwo = !linetwo;
			}
		}					
%>								</table>
        	      				<html:hidden property="travel" />
            				</html:form>
            			</td>
            		</tr>
   					<tr>
       					<td class="linetwo" align="right">
<%		if(total > 0 && sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_EVENT)) {	%>
   							<input name="button2" type="button" onClick="javascript:setAction(document.forms[3],'delete');deleteIFConfirm(document.forms[3],'<%=messages.getText("generic.travel.confirmdelete")%>');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
<%		}															
%>	            		</td>
              		</tr>
<%		if(total > 0) {	
			Hashtable paginationParameters = new Hashtable();
			paginationParameters.put("value",value);
			paginationParameters.put("type",type);
			paginationParameters.put("status",status);
			paginationParameters.put("advanced","false");
			session.setAttribute("paginationParameters",paginationParameters);
%>     				<tr>
        				<td>
        					<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(total)+"&link="+link%>' />        
        				</td>
      				</tr>
<%		}
%>          	</table>		
   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			</td>
      	</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
      	</tr>
    </table>
</body>
<%	}
%>	
</html>
