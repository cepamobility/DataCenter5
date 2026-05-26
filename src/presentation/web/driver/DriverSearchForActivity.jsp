<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="communication.language.Text"
	import="business.driver.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
	import="communication.administration.user.UserSessionData" 	
	import="communication.activity.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
	
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value; String nextLink; String title; String next;
	UserSessionData userSessionData;	
	String idActivity; boolean onlyOne; String onlyOneStr;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData(); 
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_CEPASCHEDULEACTIVITYREGISTRY);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_CD_ACTIVITY);	
	if ((check!=null) && (check1!=null)) {
		System.out.println("No tiene permisos para entrar en DriverSearchForActivity.jsp ");
		response.sendRedirect(request.getContextPath()+check);		
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();

		idActivity = request.getParameter("idActivity");
		if(Util.empty(idActivity)) {
	idActivity = (String)session.getAttribute("idActivity");
		}
		System.out.println("IdActivity: " + idActivity);
		session.setAttribute("idActivity",idActivity);
		
		String target = "DriverSearchForActivity";
		session.setAttribute("target",target);
		System.out.println("target: " + target);
		
		
		/* Si solo debe poder seleccionar un unico driver -> onlyOne = true */		
		onlyOne = false;
		onlyOneStr = request.getParameter("onlyOne");
		if (!Util.empty(onlyOneStr)){
	onlyOne = (onlyOneStr.equalsIgnoreCase("true"))?true:false;
		}	
		
		
		String url = "driver/DriverSearchForActivity.jsp?idActivity="+idActivity;

		if(request.getParameter("next") != null) {
		    next = request.getParameter("next");
		    url += "&next="+next;
		}
		
		// session.setAttribute("target","DriverSearchForActivity");

		boolean advanced = (request.getParameter("advanced") != null);
		if(advanced) {
		    url += "&advanced=true";
		}
		String status = String.valueOf(DriverData.ALL);
		if(!Util.empty(request.getParameter("status"))) {
		    status = request.getParameter("status");
		    url += "&status="+status;
		}    
		attribute = "firstorlastName";
		if(!advanced) {
	if(request.getParameter("pageNum")!=null){
		pageNum = new Integer(request.getParameter("pageNum"));
	    url += "&pageNum="+pageNum;
	}else{
		pageNum = new Integer(1);
	}
		}else{   // Busqueda Avanzada:
	if(session.getAttribute("driverForm") != null) {
	    pageNum = new Integer(((DriverAdvancedSearchForm)session.getAttribute("driverForm")).getPageNum());
		nextLink = ((DriverAdvancedSearchForm)session.getAttribute("driverForm")).getNextLink();
		System.out.println("nextLink "+nextLink);
	}else{
		if(request.getParameter("pageNum")!=null){
	pageNum = new Integer(request.getParameter("pageNum"));
		    url += "&pageNum="+pageNum;
		}else{
	pageNum = new Integer(1);
		}
	}
		}
		if(request.getParameter("attribute")!=null){
	attribute = request.getParameter("attribute");
		    url += "&attribute="+attribute;
		}		
		value = "";
		if(request.getParameter("value")!=null){
	value = request.getParameter("value");
		    url += "&value="+value;
		}		
		pageSize = 10;
		if(request.getParameter("pageSize")!=null){
	pageSize = Integer.parseInt(request.getParameter("pageSize"));
		    url += "&pageSize="+pageSize;
		}		
		int first = 0;
		int count = pageSize;
		all=!Util.empty(request.getParameter("all")) && request.getParameter("all").equals("true");		
		if(all) {
		    count=-1;
		}else{
		    first=(pageNum.intValue()-1)*pageSize;
		}
		DriverReportFacade driverReportFacade = sys.getDriverReportFacade();
		String link = "/driver/DriverSearchForActivity.jsp";
		ListResult driverSearchList  = null;
		if(!advanced) {
	driverSearchList = driverReportFacade.findDriverList(attribute,value,first,count,Integer.parseInt(status),userSessionData);
	session.setAttribute("driverSearchList",driverSearchList);		
		}else {
	DriverAdvancedSearchForm form = null;
	if(session.getAttribute("driverForm") != null) {
	    form = (DriverAdvancedSearchForm)session.getAttribute("driverForm");
	}else {
		// Si no se consulto previamente la busqueda avanzada crea un nuevo formulario en blanco
	    form = new DriverAdvancedSearchForm();
	    form.setPageNum((String)request.getParameter("pageNum"));
	    form.setCount((String)request.getParameter("pageSize"));
	    form.setStatus((String)request.getParameter("status"));
	    form.setNextLink(link);
	}
	session.setAttribute("DriverAdvancedSearchForm",form);
		}
		title = "generic.messages.selectdriver"; 
		if(request.getParameter("title") != null) {
	title = request.getParameter("title");
		    url += "&title="+title;
		}
		
		session.setAttribute("url",url);
		System.out.println("URL: " +url);
		CEPAActivityScheduleData data = sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),userSessionData);
%>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
		<title></title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>
	<script language="JavaScript" type="text/javascript">
	function submitPagination(page,all) {
		document.forms['Search'].all.value = all;
		document.forms['Search'].pageNum.value = page;
		document.forms['Search'].submit();
	}
	function setAction(form1,form2){
		var elements=form1.elements;
		var length=elements.length;
		var i;
		var list="";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(list==""){
						list=element.value;
					}else{
						list=element.value+"<"+list;
					}	
				}	
			} 
		}
		if(list.length>0){
			form2.checkboxParamList.value = list;
			form2.submit();
		}
	}	
	
	var selectedDriver = null;
	// Si solo permitimos un unico conductor:
	function onlyOneDriver(element){
		if (element.checked && selectedDriver != null){
			
			// Si elijo un conductor y ya habia uno selccionado muestro alerta:
			element.checked = false;
			alert('<%=messages.getText("generic.activity.onlyonedriverplease")%>');
			
		}else if (element.checked && selectedDriver == null){

			// Si elijo un conductor y no habia uno seleccionado lo marco:
			selectedDriver = element.value;
		
		}else if (!element.checked && selectedDriver == element.value){
			// Si desmarco un conductor y era el que estaba seleccionado, indico que no se 
			// selecciona ningun conductor:
			selectedDriver = null;
		}
			
	}
	function volver(form){
		form.submit();
	}
	</script>
	<script language="javascript" src='./menu/ + js/driverFunctions.js' type='text/javascript' >
	</script>
	<html:form action = "AddDriverToActivity" method="post">
		<html:hidden property="checkboxParamList" value="checkboxParamList" />
		<html:hidden property="activity" value='<%=idActivity%>' />
	</html:form>	
	<body bgcolor="#FFFFFF">
		<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
	  		</tr>
    	  	<tr> 
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
      		<tr>
    			<td valign="top">
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.activitydata")%>' />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.date")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%></td>
							<td align="right" class="linetwo"><%=Util.formatDate(data.getDate())%></td>			
						</tr>										
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.type")%></td>
							<td align="right" class="linetwo"><%=messages.getText(data.getType().getResourceBundleId())%></td>
						</tr>
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.city")%></td>
							<td align="right" class="linetwo"><%=data.getCity()%></td>
						</tr>				
						<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr>
							<td align="left" class="texts"><%=messages.getText("generic.activity.activitydata.state")%></td>
							<td align="right" class="linetwo"><%=messages.getText(data.getState().getResourceBundleId())%></td>
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
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.adddriver")%>' />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%">
<%
	if(advanced) {
%>								<jsp:include page='<%="/driver/advancedSearch.jsp?pageNum="+first+"&count="+count+"&status="+status+"&link="+nextLink%>'/>
<%
	}else {
%>								<form name="Search" action="layout.jsp?refer=/driver/DriverSearchForActivity.jsp" method="post">
									<input type="hidden" name="idActivity" value='<%=idActivity%>' />
									<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/>    
									<input type="hidden" name="all" <%=(all) ? "value='all'" : ""%>/>    
  									<input type="hidden" name="link" value='<%=nextLink%>'/>    
	   								<input type="hidden" name="pageSize" value='<%=pageSize%>'/>    
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<th class="linetwo" align="left">
												<%=messages.getText("generic.driver.driverdata.firstorlastname")%>
											</th>
											<td align="left">
												<input type="text" name="value" value='<%=value%>' size="40" class="input">    
											</td>
										</tr>
										<tr>
											<td colspan="2" align="left" class="texts">
												<table>
													<tr>
														<th>
															<input type="radio" class="input2" name="status" value='<%=DriverData.ACTIVE%>' <%=(Integer.parseInt(status) == DriverData.ACTIVE) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.active")%></span>
														</th>
														<th>
															<input type="radio" class="input2" name="status" value='<%=DriverData.EXCLUDED%>' <%=(Integer.parseInt(status) == DriverData.EXCLUDED) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.excluded")%></span>
														</th>
														<th>
															<input type="radio" class="input2" name="status" value='<%=DriverData.ALL%>' <%=(Integer.parseInt(status) == DriverData.ALL) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.all.pl")%></span>
														</th>
													</tr>
												</table>
											</td>
										</tr>
										<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr>
											<td align="left" class="linetwo" valign="bottom">
 												<a href='<%="layout.jsp?refer=/driver/DriverSearchForActivity.jsp?idActivity="+idActivity+"&link="+nextLink+"&advanced=true&pageNum="+String.valueOf(pageNum)+"&status="+status%>'>
 													<%=messages.getText("generic.messages.advancedsearch")%>
	 											</a>
											</td>
											<td align="right">
												<input name="button" type="button" onClick="javascript:document.forms['Search'].submit();" value='<%=messages.getText("generic.messages.search")%>' class="input"/>	
											</td>	
										</tr>  
									</table>	
								</form>			
<%
				}
			%>							</td>
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
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.driver.driverlist.systemdrivers")%>' />
					<form name="list">
						<table width="100%" border="0">
              				<tr align="center">
								<td width='3%' class="guide2"></td>              					
								<td width='15%' class="guide2"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
								<td width='15%'	class="guide2"><%=messages.getText("generic.driver.driverdata.lastname")%></td>
					    		<td width='10%'	class="guide2"><%=messages.getText("generic.driver.driverdata.country")%></td>
					    		
					    		<%
					    							    			// DivOrgs:
					    							    					    							    					    		String[] principalLabels=sys.getUserSessionData().getFilter().getPrincipalLabels();
					    							    					    							    								for (int z = 0 ; z < principalLabels.length ; z++){
					    							    		%> 
					                	<td width='13%'	class="guide2"><%=messages.getText(principalLabels[z])%></td>
								<%
									}
								%> 
			
								<td width='9%'	class="guide2"><%=messages.getText("generic.driver.driverdata.birthdate")%></td>
								<td width='9%'	class="guide2"><%=messages.getText("generic.driver.driverdata.exclusiondate")%></td>
              				</tr>
              				
              				
<%
              				              					ListResult col = (ListResult)session.getAttribute("driverSearchList");
              				              				              				              						int total = (col == null)?0:col.getTotal();
              				              				              				              						if(total == 0){
              				              				%>            				<tr align="center">
			              		<td colspan="6">
	          						<%=messages.getText("generic.messages.notresult")%>
    			      			</td>
          					</tr>
<%
	}else {
	if(col!=null){
		Iterator iter=col.getElements().iterator();
		int contIter = 0;
		while(iter.hasNext() && (contIter<pageSize || all)){
	contIter++;
	DriverMainAttributes driver=(DriverMainAttributes)iter.next();
%>	            			<tr align=center align="right" class='<%=((contIter % 2)==0) ? "lineone" : "linetwo"%>'>
				          		<td width='3%' align="right">
									<input type='checkbox' value='<%=String.valueOf(driver.getId())%>' 
										<%=(onlyOne == true)?" onclick='javascript:onlyOneDriver(this);' ":""%> />
              					</td>
			              		<td width='15%' align="right"><%=driver.getFirstName()%></td>
              					<td width='15%' align="right"><%=driver.getLastName()%></td>
								<td width='10%' align="right"><%=messages.getText(driver.getCountryName())%> </td>
								
								<%
																	// DivOrgs:
																																				for(int z = 1; z <= principalLabels.length; z ++){
																%>
				               	<td width='13%' align="right">
											<%=driver.getDivOrgItem(z)%> 
										</td>
<%
	}
%> 

								
						        <td width='9%' align="right"><%=Util.formatDate(driver.getBirthDate())%> </td>
								<td width='9%' align="right"><%="-"%> </td>
            			    </tr>
<%				}
%>      					<tr>
								<td align="right" colspan="<%= (new Integer(6 + principalLabels.length)).toString() %>">
									<input name="button2" type="button"  value="Agregar Conductor" onClick="javascript:setAction(document.forms['list'],document.forms['AddDriverToActivityForm']);" class="input"/>
							   	</td>                 							
							</tr>
							<tr>
								<td colspan="<%= (new Integer(6 + principalLabels.length)).toString() %>">
<%				Hashtable paginationParameters = new Hashtable();
				paginationParameters.put("attribute",attribute);
				paginationParameters.put("value",value);
				paginationParameters.put("advanced",String.valueOf(advanced));
				paginationParameters.put("status",status);
				paginationParameters.put("id",idActivity);
				// session.setAttribute("paginationParameters",paginationParameters);
				request.setAttribute("paginationParameters",paginationParameters);
%>        							<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(total)+"&link="+link%>' />        
						    	</td>
      						</tr>
<%			}			
		}	
%>						</table>
					</form>
					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
      		</tr>
    	  	<tr> 
				<jsp:include  page="../component/spaceline.jsp" />
	      	</tr>
    	</table>
	</body>
<% 
	}	
%>	
</html:html>

