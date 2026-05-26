<%@page language="java" 
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.driver.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.driver.*"
	import="communication.administration.user.UserSessionData" 	
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
</script>
<html:html>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value;String vehicle;String title;String next;
	UserSessionData userSessionData;	
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData(); 
	String check = Util.checkPermission(sys,SecurityConstants.DRIVER_SEARCH);
	if(check!=null) {
	    check = Util.checkPermission(sys,SecurityConstants.CREATE_VEHICLE);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();

		vehicle = request.getParameter("vehicle");		
		String url = "driver/fineDriverSearch.jsp?vehicle="+vehicle;

		if(request.getParameter("next") != null) {
		    next = request.getParameter("next");
		    url += "&next="+next;
		}
		
		session.setAttribute("target","DriverSearch");

		String status = String.valueOf(DriverData.ALL);
		if(!Util.empty(request.getParameter("status"))) {
		    status = request.getParameter("status");
		    url += "&status="+status;
		}    
		attribute = "firstorlastName";
		if(request.getParameter("pageNum")!=null){
	pageNum=new Integer(request.getParameter("pageNum"));
		    url += "&pageNum="+pageNum;
		}else{
	pageNum=new Integer(1);
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
		String link = "/driver/fineDriverSearch.jsp";
		boolean type = (Util.empty(request.getParameter("type"))) ? false : request.getParameter("type").equals("cds");  
		if(type) {
	session.setAttribute("driverSearchList",driverReportFacade.findCDSDriverList(attribute,value,first,count,Integer.parseInt(status),userSessionData));
		}else {
	session.setAttribute("driverSearchList",driverReportFacade.findDriverList(attribute,value,first,count,Integer.parseInt(status),userSessionData));
		}
		title = "generic.messages.selectdriver"; 
		if(request.getParameter("title") != null) {
	title = request.getParameter("title");
		    url += "&title="+title;
		}
		
		session.setAttribute("url",url);
%>

<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<title></title>
<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>

<body bgcolor="#FFFFFF">
	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
      	</tr>
      	<tr>
        	<td colspan="3">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%">
							<form action='layout.jsp?refer=/driver/fineDriverSearch.jsp' method="post">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="21%">
											<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/>    
	 										<input type="hidden" name="all" <%=(all) ? "value='all'" : ""%>/>    
											<span class="texts"><%=messages.getText("generic.driver.driverdata.firstorlastname")%></span>
										</td>
										
                  <td width="27%"> <div align="left">
                      <input type="text" name="value" value='<%=value%>' size="30" class="input">
                    </div></td>
										
                  <td width="51%"> <div align="left">
                      <input type="hidden" name="vehicle" value='<%=vehicle%>'/>
                      <input type="hidden" name="pageSize" value='<%=pageSize%>'/>
                      <input name="button" type="button" onClick="javascript:document.forms[2].submit();" value='<%=messages.getText("generic.messages.search")%>' class="input"/>
                    </div></td>	
									</tr>  
									<tr>
										<td colspan="3" align="left" class="texts">
											<table>
												<tr>
													<td width="40%">
														
													</td>
													
                        <td valign="middle"> 
                          <input type="radio" class="input2" name="status" value='<%=DriverData.ACTIVE%>' <%=(Integer.parseInt(status) == DriverData.ACTIVE) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.active")%></span>
													</td>
													
                        <td valign="middle"> 
                          <input type="radio" class="input2" name="status" value='<%=DriverData.EXCLUDED%>' <%=(Integer.parseInt(status) == DriverData.EXCLUDED) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.excluded")%></span>
													</td>
													
                        <td valign="middle"> 
                          <input type="radio" class="input2" name="status" value='<%=DriverData.ALL%>' <%=(Integer.parseInt(status) == DriverData.ALL) ? "checked" : ""%>><span class="input2"><%=messages.getText("generic.messages.all.pl")%></span>
													</td>
												</tr>
											</table>
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
        		<%
        			String page1 = "../component/utilityboxtop.jsp?title="+messages.getText("web.driver.driverlist.systemdrivers");
        		%>
				<jsp:include  page='<%=page1%>' />
				<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      				<tr>
        				<td width="100%">
<%!		ListResult col; Iterator iter; DriverMainAttributes driver; int i;
		String uid; int pageMax; int contIter; int total;
%>
<%
	col=(ListResult)session.getAttribute("driverSearchList");
		total = col.getTotal();
%>		    				<table width="100%" border="0">
              					<tr align="center">
					                <td width='19%' class="guide2"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
					                <td width='19%'	class="guide2"><%=messages.getText("generic.driver.driverdata.lastname")%></td>
					                <td width='19%'	class="guide2"><%=messages.getText("generic.driver.driverdata.country")%></td>
					                <td width='19%'	class="guide2"><%=messages.getText("generic.driver.driverdata.birthdate")+" ("+messages.getText("generic.messages.dateformat")+")"%></td>
					                <td width='19%'	class="guide2"><%=messages.getText("generic.driver.driverdata.exclusiondate")+" ("+messages.getText("generic.messages.dateformat")+")"%></td>
              					</tr>
<%
	if(col!=null && col.getElements().isEmpty()){
%>            				
              					<tr align="center">
              						<td colspan="5">
          								<%=messages.getText("generic.messages.notresult")%>
          							</td>
          						</tr>
<%
	}else {
	if(col!=null){
		iter=col.getElements().iterator();
		contIter=0;
%>
<%
	while(iter.hasNext() && (contIter<pageSize || all)){
	contIter++;
    				driver=(DriverMainAttributes)iter.next();
    				String continueLink = "layout.jsp?refer=event/FineCreate.jsp?vehicle="+vehicle+"&id="+String.valueOf(driver.getId());
%>
                				<tr align=center align="right" class='<%=((contIter % 2)==0) ? "lineone" : "linetwo"%>'>
	          						<td width='19%' align="right">
	              						<a href='<%=continueLink%>' > <%=driver.getFirstName()%> </a>
              						</td>
              						<td width='19%' align="right">
	              						<a href='<%=continueLink%>' > <%=driver.getLastName()%> </a>
              						</td>
						        	<td width='19%' align="right"><%=messages.getText(driver.getCountryName())%> </td>
						            <td width='19%' align="right"><%=Util.formatDate(driver.getBirthDate())%> </td>
						            <td width='19%' align="right"><%="-"%> </td>
                				</tr>
<%				}
%>        					</table>
          				</td>
      				</tr>
      				<tr>
        				<td>
<%			Hashtable paginationParameters = new Hashtable();
			paginationParameters.put("attribute",attribute);
			paginationParameters.put("value",value);
			paginationParameters.put("status",status);
			session.setAttribute("paginationParameters",paginationParameters);
%>        				
        					<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(total)+"&link="+link%>' />        
        				</td>
      				</tr>
<%			}			
		}	
%>				</table>
   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			</td>
      	</tr>
    </table>
</body>
<%
	}	
%>	
</html:html>
