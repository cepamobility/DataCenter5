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
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<script language="JavaScript" type="text/javascript">

function preparedCheckboxParameters(form){

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
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.checkboxParamList.value=valueCheckboxParamList;
		if(valueCheckboxParamList.length>0){
			return true;
		}
		else{
			return false;
		}
}
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
</script>
<html:html>
<%!
	RequestDispatcher dispatcher; String context;
	Text messages; SystemAccess sys;
%> 
<%
 	sys=(SystemAccess)session.getAttribute("SystemAccess");
  	String check = Util.checkPermission(sys,SecurityConstants.VIEW_DRIVER_LIST);
  	if(check!=null) {
  		response.sendRedirect(request.getContextPath()+check);
  	}else {
  	    String url = "cepasystem/DriverList.jsp";

  		context=request.getContextPath();
  		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
  		dispatcher.include(request,response);
  	
  		messages=sys.getText();

  		boolean already = (request.getParameter("already") == null) ? false : Boolean.parseBoolean(request.getParameter("already"));
  		DriverUtil util = new DriverUtil();
  		Hashtable parameters = util.getParameters(session,request);	
  		boolean advanced = ((Boolean)parameters.get("advanced")).booleanValue();
  		
  		session.setAttribute("url",url);
  		DriverReportFacade driverReportFacade = sys.getDriverReportFacade();
  		String link = "/cepasystem/DriverList.jsp";
  		if(!advanced && !already) {
  	session.setAttribute("driverSearchList",driverReportFacade.findDriverList(parameters,sys.getUserSessionData()));
  		}else {
  	DriverAdvancedSearchForm form = null;
  	if(session.getAttribute("driverForm") != null) {
  	    form = (DriverAdvancedSearchForm)session.getAttribute("driverForm");
  	}else {
  	    form = new DriverAdvancedSearchForm();
  	    form.setPageNum((String)request.getParameter("pageNum"));
  	    form.setCount((String)request.getParameter("pageSize"));
  	    form.setStatus((String)request.getParameter("status"));
  	    form.setNextLink(link);
  	}
  	session.setAttribute("DriverAdvancedSearchForm",form);
  		}
 %>


	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
    <title>
    </title>
    
<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
  </head>

<body bgcolor="#FFFFFF">
	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.driver.editing")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
      	<tr>
        	<td valign="top" <%=(advanced)?"colspan=3":"width='60%'"%>height="100%">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%" align="right" valign="top">
						<form action="layout.jsp?refer=/cepasystem/DriverList.jsp" method="post">
							<input type="hidden" name="pageNum" value='<%=String.valueOf(parameters.get("pageNum"))%>'>    
							<input type="hidden" name="all" value='<%=String.valueOf(parameters.get("all"))%>'>    
    						<input type="hidden" name="pageSize" value='<%=String.valueOf(parameters.get("pageSize"))%>'/>										  
							<table width="100%" height="20" border="0" cellpadding="0" cellspacing="1">									
								<tr>
    								<td width="41%" align="left" class="texts">
										<%=messages.getText("generic.driver.driverdata.firstorlastname")%>										
									</td>
    								<td width="59%" colspan="2" align="right">
    									<input type="text" name="value" value='<%=String.valueOf(parameters.get("value"))%>' size="40" class="input">
  									</td>
								</tr>
								<tr>
									<td height="20" colspan="3" align="left" valign="top" class="texts">
										<table width="60%" border="0" align="right" cellpadding="0" cellspacing="0" class="specialarea">
                                    		<tr>
                                        		<td width="29%" valign="middle">
                                        			<input type="radio" class="specialarea" name="status" value='<%=String.valueOf(DriverData.ACTIVE)%>' <%=util.isChecked(parameters,DriverData.ACTIVE)%>>
	                                            	<%=messages.getText("generic.messages.active")%> 
    	                                        </td>
        	                                    <td width="37%" valign="middle">
            	                                	<input type="radio" class="specialarea" name="status" value='<%=String.valueOf(DriverData.EXCLUDED)%>' <%=util.isChecked(parameters,DriverData.EXCLUDED)%>>
                	                            	<%=messages.getText("generic.messages.excluded")%> 
                    	                        </td>
                        	                    <td width="34%" align="left" valign="middle">
                            	                	<input type="radio" class="specialarea" name="status" value='<%=String.valueOf(DriverData.ALL)%>' <%=util.isChecked(parameters,DriverData.ALL)%>>
                                    	        	<%=messages.getText("generic.messages.all.pl")%> 
                                	            </td>
                                          	</tr>
                                        </table>
                                     </td>
								</tr>
								<tr>
									<td height="10" colspan="3">
										<img src="x" width="1" height="1">
									</td>
								</tr>
								<tr>
									<td height="10" colspan="3">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
                                        	<tr>
                                          		<td valign="middle">
                                          			<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12"> 
                                          		</td>
                                          		<td colspan="3" align="left" valign="middle" class="texts">
                                          			<a href='<%=util.getAdvancedLink(parameters)%>' class="texts">
                                          				<%=messages.getText("generic.messages.advancedsearch")%>
                                          			</a> 
                                          		</td>
                                          		<td align="right" valign="bottom" class"texts">
                                          			<input name="button" type="button" onClick="javascript:document.forms[2].submit();" value='<%=messages.getText("generic.messages.search")%>' class="input"/>
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
        	<td width="16">&nbsp;</td>
        	<td width="70%" valign="top" height="100%">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
<%
	if(sys.getUserSessionData().checkPermission(SecurityConstants.CREATE_DRIVER)) {
%>				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="10"><img src="x" width="1" height="1"></td>
					</tr>
					<tr>
						<td valign="middle"><img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12"> </td>
						<td valign="middle">
							<a href="layout.jsp?refer=/cepasystem/create.jsp" class="texts">
								<%=messages.getText("generic.messages.adddriver")%>					
							</a>
						</td>
					</tr>
					<tr>
						<td height="47"><img src="x" width="1" height="1"></td>
					</tr>
				</table>
<%
	}
%>				
				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			</td>
     	</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
      	</tr>
      	<tr>
        	<td colspan="3">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.driver.driverlist.systemdrivers")%>' />
				<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      				<tr>
        				<td width="100%">
							<form action="layout.jsp?refer=/cepasystem/DriverExclusion.jsp"  method="post">
								<table width="100%" border="0">
              						<tr align="center">
<%
	if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER) || sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {
%>	              						<td width='3%' class="guide2"></td>
<%
	}
%>					               	 	<td width='15%' class="guide2"><%=messages.getText("generic.driver.driverdata.firstname")%></td>
					                	<td width='15%'	class="guide2"><%=messages.getText("generic.driver.driverdata.lastname")%></td>
					                	<td width='10%'	class="guide2"><%=messages.getText("generic.driver.driverdata.country")%></td>
					                	<td width='9%'	class="guide2">
											<%=messages.getText("generic.driver.driverdata.birthdateabb")%>
						                	<div class="texts">
						                		<%=messages.getText("generic.messages.dateformat")+" "%>
						                	</div>
						                </td>
						                <td width='9%'	class="guide2">
						                	<%=messages.getText("generic.driver.driverdata.exclusiondate")%>
 							               	<div class="texts">
 						    	           		<%=messages.getText("generic.messages.dateformat")%>
 						        	       	</div>
 						            	</td>
	              					</tr>
<%
	ListResult col=(ListResult)session.getAttribute("driverSearchList");
		if(col.getTotal() == 0){
%>			    					<tr>
        	      						<td class="linetwo">
	        	      						<%=messages.getText("generic.messages.notresult")%>
              							</td>
              						</tr>
<%
	}else {
	Iterator iter=col.getElements().iterator();
%>
<%
	int max = (Boolean.parseBoolean(request.getParameter("all"))) ? col.getTotal() : Integer.parseInt((String)parameters.get("pageSize"));   
   			int count = 0;
	while(iter.hasNext() && (count < max)){
	    DriverMainAttributes driver = (DriverMainAttributes)iter.next();
%>		        					<tr align=center align="right" class='<%=(count % 2 == 0) ? "lineone" : "linetwo"%>'>
                
<%
                	if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER) || sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {
                %>	            						<td width='3%' align="center">
											<input type='checkbox' name='<%=driver.getId()%>' value='<%=driver.getId()%>' >
										</td>
<%
	}
%>	          							<td width='15%' align="right">
<%
	if(sys.getUserSessionData().checkPermission(SecurityConstants.EDIT_DRIVER)) {
%>	              							<a href='layout.jsp?refer=/cepasystem/DriverView.jsp?id=<%=driver.getId()%>' > <%=driver.getFirstName()%> </a>
<%
	}else {
%>                  						<%=driver.getFirstName()%>
<%
	}
%>              						</td>
              							<td width='15%' align="right">
<%
	if(sys.getUserSessionData().checkPermission(SecurityConstants.EDIT_DRIVER)) {
%>	              							<a href='layout.jsp?refer=/cepasystem/DriverView.jsp?id=<%=driver.getId()%>' > <%=driver.getLastName()%> </a>
<%
	}else {
%>                  						<%=driver.getLastName()%>
<%
	}
%>              						</td>
						               	<td width='10%' align="right">
						               		<%=messages.getText(driver.getCountryName())%> 
						               	</td>
						               	<td width='9%' align="right">
											<%=Util.formatDate(driver.getBirthDate())%> 
										</td>
						               	<td width='9%' align="right">
						               		<%=(driver.getExclusionDate()==null) ? "-" : Util.formatDate(driver.getExclusionDate())%> 
						               	</td>
                					</tr>
<%			}
		}
%>		      					</table>
              					<input type="hidden" name="checkboxParamList" />
              					<input type="hidden" name="action" />
            				</form>
            			</td>
            		</tr>
<%		if(col.getTotal() > 0) {
%>            		
             		<tr>
                		<td class="linetwo" align="right">
<%	
			if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {	%>
                    		<input name="button2" type="button" onClick="javascript:setAction(document.forms[3],'exclude');" value='<%=messages.getText("generic.driver.exclude")%>' class="input"/>
<%			}															
			if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_DRIVER)) {	%>
                    		<input name="button2" type="button" onClick="javascript:setAction(document.forms[3],'include');" value='<%=messages.getText("generic.messages.include")%>' class="input"/>
<%			}															
			if(sys.getUserSessionData().checkPermission(SecurityConstants.DELETE_DRIVER)) {	%>
                    		<input name="button2" type="button" onClick="javascript:setAction(document.forms[3],'delete');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
<%			}															
%>	            		</td>
      				</tr>
      				<tr>
        				<td>
<%			session.setAttribute("paginationParameters",parameters);
%>        					<jsp:include page='<%="../util/pagination2.jsp?size="+parameters.get("pageSize")+"&num="+parameters.get("pageNum")+"&total="+String.valueOf(col.getTotal())+"&link="+link%>' />        
        				</td>
      				</tr>
<%		}	
%>				</table>
   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
			</td>
      	</tr>
      	<tr><td>&nbsp;</td></tr>
  	</table>
</body>
<%
	}	
%>	
</html:html>
