<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.driver.*"
	import="communication.driver.tables.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.*"
	import="presentation.translator.web.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<%! RequestDispatcher dispatcher; 
	Text messages; 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();

		String driverId = request.getParameter("id");
		if(Util.empty(driverId)) {
		    driverId = (String)session.getAttribute("driver");
		}
		session.setAttribute("url","user/driverUser.jsp?id="+driverId);

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		
		DriverReportFacade report = sys.getDriverReportFacade();
		DriverData driver = report.findDriverByPrimaryKey(new Integer(driverId),sys.getUserSessionData());
		
		Collection options = new LinkedList();
		options.add(new OptionItem(messages.getText("generic.messages.no"),"false"));
		options.add(new OptionItem(messages.getText("generic.messages.yes"),"true"));
		session.setAttribute("options",options);
%>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<script>
		function showPassword(insert){			
			if (insert.selectedIndex != 0){				
				document.getElementById('type').style.display = "";
			}else{
				document.getElementById('type').style.display = "none";
			}
		}
	</script>
	<jsp:include page='<%="/language/findalllanguage.jsp" %>'/>
	<body bgcolor="#FFFFFF">
		<html:form action="/DriverUserCreate">
			<html:hidden property="driver" value='<%=driverId%>' />
			<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.user.newdriveruser")%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.userview.systemuserdata")%>' />
						<table>
					      	<tr  class="linetwo">
					        	<th width="224" align="left"> <%=messages.getText("generic.user.userdata.nickname")%></th>
					         	<td align="right">
									<html:text property="loginname"/>
					          	</td>
					      	</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
					      	<tr  class="linetwo">
	        					<th align="left"><%=messages.getText("generic.user.userdata.pass")%><span class="required">*</span></th>
	        					<td align="right">
	           						<html:password property="password" size="25" styleClass="input"/>
	          					</td>
	      					</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
					      	<tr  class="linetwo">
	        					<th align="left"> <%=messages.getText("web.user.create.confirmpassword")%><span class="required">*</span></th>
	        					<td align="right">
	           						<html:password property="confirm" size="25" styleClass="input"/>
	          					</td>
	      					</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" > <%=messages.getText("generic.user.userdata.firstname")%></th>
        						<td align="right">
					            	<%=driver.getFirstName()%>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.user.userdata.lastname")%></th>
        						<td align="right">
					            	<%=driver.getLastName()%>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.user.userdata.mail")%></th>
        						<td align="right">
				            		<%=driver.getEmail()%>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" > <%=messages.getText("generic.user.userdata.language")%></th>
        						<td align="left" >
									<html:select property="language" styleClass="input" style="width:160; ">
										<html:options collection="languageOptionList" property="value" labelProperty="label"/>
									</html:select>
      							</td>
							</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr class="linetwo">
        						<td align="right"> </td>
        						<td align="right">
          							<div align="right">
							            <html:submit styleClass="input">
											<%=messages.getText("generic.messages.save")%>
										</html:submit>
							            <html:cancel styleClass="input">
											<%=messages.getText("generic.messages.cancel")%>
										</html:cancel>
          							</div>
          						</td>
      						</tr>
      					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
			</table>
		</html:form>
	</body>
<%	}	
%>
</html:html>
