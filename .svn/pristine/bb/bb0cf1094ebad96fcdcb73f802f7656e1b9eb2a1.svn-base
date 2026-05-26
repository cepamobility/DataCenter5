 <%@page language="java" 
	errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="communication.administration.user.UserSessionData"  	
	import="communication.activity.tables.*" 
	
%>  

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<script language="JavaScript" type="text/javascript">
function submitPagination(page,all) {
	document.forms[2].all.value = all;
	document.forms[2].pageNum.value = page;
	document.forms[2].submit();
}

function setAction(form,action){
	if(preparedCheckboxParameters(form)) {
		form.action.value=action;
		form.submit();
	}
}

function resetPageNum(){
	document.forms[2].resetPN.value = "true";
	document.forms[2].submit();
	return true;
	}

</script>

<html:html>
<%!
	RequestDispatcher dispatcher; 
	String context;
	String pathView; 
	String pathMsg; 
	String pathSearch;
	String pathLanguage; 
	Text messages; 
	SystemAccess sys;
	Integer pageNum; 
	boolean all; 
	int pageSize; 
	String attribute; 
	String value;
	UserSessionData userSessionData; 
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		
		// session.setAttribute("url","activity/schedule/CEPAActivityScheduleList.jsp");
		userSessionData.setUrl("activity/schedule/CEPAActivityScheduleList.jsp");
		
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);

		messages=sys.getText();
			
		// session.setAttribute("target","ActivityList");
		
		boolean search = (request.getParameter("search") != null);		
		System.out.println("Search="+Boolean.toString(search));
		attribute = "date";
		
		if(!search) {
			if(request.getParameter("pageNum")!=null){
				pageNum=new Integer(request.getParameter("pageNum"));
			}else{
				pageNum=new Integer(1);
			}
		}else{
			if(session.getAttribute("activityForm") != null) {
			    pageNum=new Integer(((ActivitySearchForm)session.getAttribute("activityForm")).getPageNum()); 
			}else{
				if(request.getParameter("pageNum")!=null){
					pageNum=new Integer(request.getParameter("pageNum"));
				}else{
					pageNum=new Integer(1);
				}
			}
		}		
		
		if(request.getParameter("attribute")!=null){
			attribute = request.getParameter("attribute");
		}		
		value = "";		
		if(request.getParameter("value")!=null){
			value = request.getParameter("value");
		}		
		
		// Los gerentes solo ven actividades dictadas
//		if (userSessionData.isGerente()) {
//			value = CEPAActivityConstants.DICTADA;	
//			attribute = "state";
//		}		
		
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
		ActivityReportFacade activityReportFacade = sys.getActivityReportFacade();
		
		
		String link = "/datacenter/activity/schedule/CEPAActivityScheduleList.jsp"; 
		
		if (!search) {
			session.setAttribute("activitySearchList",activityReportFacade.findCEPAScheduleActivityList(attribute,value,first,count,userSessionData));
		}	
		
		
		int[][] principalDivOrgs = userSessionData.getFilter().getPrincipalDivOrgs();
		String[] principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
		
		String division = "";
		String subdivision = "";
		

%>		

<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
</script>
<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
</script>
    <!-- CEPAActivityScheduleList.jsp -->
	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
</head>


<body bgcolor="#FFFFFF">

							
	<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../../component/spaceline.jsp" /> 
      	</tr>
      	<tr>
        	<td valign="top" width="70%" >
				<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%"> 
                            <jsp:include page='<%="simpleSearch.jsp?pageNum="+first+"&count="+count%>'/>												
						</td>
					</tr>
				</table>			
   				<jsp:include page="../../component/utilityboxbottom.jsp"/>	
		    </td> 
<%
	// Por ahora, no ofrecemos options para un gerente
	//if(!userSessionData.isGerente()) {
%>
        	<td width="16" >&nbsp;</td>
        	<td width="60%" valign="top" >
        		<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
<%//			if(userSessionData.checkPermission(SecurityConstants.CREATE_CEPASCHEDULEACTIVITY) && 
  //			!userSessionData.isGerente()) {
				if(userSessionData.checkPermission(SecurityConstants.CREATE_CEPASCHEDULEACTIVITY) || 
						userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY))	{
%>					<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;
						<a href="layout.jsp?refer=/activity/schedule/CEPAActivityScheduleCreate.jsp?target=ActivityList" class="texts" >						
							<%=messages.getText("generic.messages.addcepaactivityschedule")%>					
						</a> 
<%			}	
%>				<jsp:include page="../../component/utilityboxbottom.jsp"/>	
			</td>

<%
//	}
%>
		</tr>		

      	<tr>
			<jsp:include  page="../../component/spaceline.jsp" />
      	</tr>
      	<tr>
        	<td colspan="3"> 
				<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
				<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      				<tr>
        				<td width="100%">
<%!		ListResult col; Iterator iter; CEPAActivityScheduleData activity; int i;
		String uid; int pageMax; int contIter; int total;
%>

<%		col=(ListResult)session.getAttribute("activitySearchList");
		total = col.getTotal();
		
//		if (total==0 && (pageNum.intValue() > 1)) {
//			pageNum = new Integer(pageNum.intValue() - 1);
//			first=(pageNum.intValue()-1)*pageSize;
//			session.setAttribute("activitySearchList",	activityReportFacade.findCEPAScheduleActivityList(attribute,value,first,count,userSessionData));			
//		}			
		
		if (all) pageSize = total; 
		
		if(col!=null && col.getElements().isEmpty()){
			
		
%>			
            				<table width="100%" border="0">
              					<tr align="center">
									<td width='4%' class="guide2"></td>
					                <td  width="12%" class="guide2"><%=messages.getText("generic.activity.activitydata.date")%><%=" "+messages.getText("generic.messages.dateformat")%></td>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.type")%></td>
					                <% for (int z = 0; z < principalLabels.length; z++){ 
						                	if (z == 0 || z == 1){ %>
					                <td width="12%"	class="guide2"><%=messages.getText(principalLabels[z])%></td>
									<% 		}
						                } %>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.country")%></td>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.city")%></td>					                					                
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.state")%></td>					                
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.drivercount")%></td>					                
              					</tr>
            				</table>
          					<%=messages.getText("generic.messages.notresult")%>
<%		}else {
			if(col!=null){
				contIter=0;
				iter=col.getElements().iterator();
				contIter=0;
%>
            				<form  action="layout.jsp?refer=/activity/schedule/CEPAActivityScheduleExclusion.jsp"  method="post">

              					<table width="100%" border="0">
                					<tr align="center">
<%			//	if(userSessionData.checkPermission(SecurityConstants.DELETE_CEPASCHEDULEACTIVITY)) {	
%>	              						<td width='4%' class="guide2"></td>
<%		//		}																
%>					                <td  width="12%" class="guide2"><%=messages.getText("generic.activity.activitydata.date")%><%=" "+messages.getText("generic.messages.dateformat")%></td>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.type")%></td>
					                <% for (int z = 0; z < principalLabels.length; z++){ 
					                		if (z == 0 || z == 1){ %>
					                <td width="12%"	class="guide2"><%=messages.getText(principalLabels[z])%></td>
					                <% 		}
					                	} %>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.country")%></td>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.city")%></td>
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.state")%></td>					                					                
					                <td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.drivercount")%></td>					                					                
                					</tr>
              					</table> 
              					<table  width="100%" >
<%				while(iter.hasNext() && (contIter<pageSize || all)){
					contIter++;
    				activity=(CEPAActivityScheduleData)iter.next();
%>
                					<tr align=center align="right" class='<%=((contIter % 2)==0) ? "lineone" : "linetwo"%>'>
                
<%					if(userSessionData.checkPermission(SecurityConstants.DELETE_CEPASCHEDULEACTIVITY) 
				//		&& !activity.getState().equals("datatype.scheduledactivitystate.dictada")
//						&& activityReportFacade.getParticipants(activity.getActivityId(),userSessionData).isEmpty() 
) {
	%>	            		<td width='4%' align="center"><input type='checkbox' name='<%=activity.getActivityId()%>' value='<%=activity.getActivityId()%>' ></td>
<%					}	
					else {
						%>	<td width='4%' align="center"></td>						
<%					}
						
%>	          							<td  width="11%" align="right">
<%					if(userSessionData.checkPermission(SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY)) {
%>	              							<a href='layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id=<%=activity.getActivityId()%>' > <%=Util.formatDate(activity.getDate())%> </a>
<%					}else {		

%>                  						
						<%=Util.formatDate(activity.getDate())%>
<%					}

					division = activity.getData(CEPAActivityScheduleData.DIV_ORG,principalDivOrgs[0][0],principalDivOrgs[0][1]);
					if (principalLabels.length > 1){
						subdivision = activity.getData(CEPAActivityScheduleData.DIV_ORG,principalDivOrgs[1][0],principalDivOrgs[1][1]);	
					}

%>              					</td>
						                <td width="12%" align="right"><%=messages.getText(activity.getType().getResourceBundleId())%> </td>
						                <td width="12%" align="right"><%=messages.getText(division)%> </td>
						                <% if (principalLabels.length > 1){ %>
							                <td width="12%" align="right"><%=messages.getText(subdivision)%> </td>						                
							            <% } %>
						                <td  width="12%" align="right"><%=messages.getText(activity.getCountryName())%> </td>				
						                <td  width="12%" align="right"><%=messages.getText(activity.getCity())%> </td>				
						                <td  width="12%" align="right"><%=messages.getText(activity.getState().getResourceBundleId())%> </td>										                						                
						                
						                <%
						                 int driverCount = activityReportFacade.getParticipants(activity.getActivityId(),userSessionData).size();                
						                %>
						                <td  width="12%" align="right"><%=driverCount%> </td>				                						                
                					</tr>
<%				}
%>
              					</table>
              					<input type="hidden" name="checkboxParamList" />         					
            				</form>
            				<table width='100%'  border="0" cellpadding="0" cellspacing="0" class="default">
              					<tr>
                					<td class="lineone" align="right">
<%				if(userSessionData.checkPermission(SecurityConstants.DELETE_CEPASCHEDULEACTIVITY)) {	%>
               					<input name="button2" type="button" onClick="javascript:setAction(document.forms[3],'delete');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>                    					
<%				}															
%>
	            					</td>
              					</tr>
          					</table>
          				</td>
      				</tr>
      				<tr>
        				<td>
<%						Hashtable paginationParameters = new Hashtable();
						paginationParameters.put("attribute",attribute);
						paginationParameters.put("value",value);
//						paginationParameters.put("advanced",String.valueOf(advanced));
						// Seteo el status solo para que funcione la paginación hecha por Juan
						paginationParameters.put("status",String.valueOf(2));
						session.setAttribute("paginationParameters",paginationParameters);
						/* Debug */
						// System.out.println("PageSize="+pageSize+" , Numero de Pagina="+pageNum+" , Total de Registros="+total);
						
%>        				
        					<jsp:include page='<%="../../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(total)+"&link="+link%>' />        
        				</td>
      				</tr>
<%			}			
		}	
%>				</table>
   				<jsp:include page="../../component/utilityboxbottom.jsp"/>	
			</td>
      	</tr>
  </table>
</body>
<%	}
//	session.removeAttribute("activitySearchList");
%>
</html:html>
