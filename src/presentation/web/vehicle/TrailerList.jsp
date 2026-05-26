<%@page language="java" 
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*" 	
 	import="communication.administration.user.*" 	
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
	function setAction(formChecks,formSubmit,action){
		if(makeList(formChecks,formSubmit)) {
			formSubmit.action.value = action;
			formSubmit.submit();
		}
	}
	
	function associate(formChecks,formSubmit,msg,error){
		if(makeList(formChecks,formSubmit)) {
			vector = formSubmit.checkboxParamList.value.split("<");
			if(confirm(msg)){
				formSubmit.submit();
			}
		}
	}
</script>

<html:html>
<%!
	RequestDispatcher dispatcher; 
	String context;
	Text messages; 
	SystemAccess sys;
	Integer pageNum; 
	boolean all; 
	int pageSize; 
	String value;
	UserSessionData userSessionData; 
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","vehicle/TrailerList.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();

		String vehicle = null;
		boolean search = false;
		if(!Util.empty(request.getParameter("vehicle"))) {
		    vehicle = request.getParameter("vehicle");
		    search = true;
		}
		String status = null;
		if(!Util.empty(request.getParameter("status"))) {
		    status = request.getParameter("status");
		}else{
	status = String.valueOf(VehicleData.ACTIVE);
		}
		
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
		VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
		String link = (!search) ? "/datacenter/vehicle/TrailerList.jsp" : "/datacenter/vehicle/TrailerList.jsp?vehicle="+vehicle+"&status="+status;
		String url = (!search) ? "layout.jsp?refer=/vehicle/TrailerList.jsp" : "layout.jsp?refer=/vehicle/TrailerList.jsp?vehicle="+vehicle+"&status="+status;
		String add = (!search) ? "layout.jsp?refer=/vehicle/createTrailer.jsp" : "layout.jsp?refer=/vehicle/createTrailer.jsp?vehicle="+vehicle+"&status="+status;
%>		

	<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
	</script>
	

	<!-- TrailerList.jsp -->
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>

	<body bgcolor="#FFFFFF">
		<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
  			<tr class="guide1"> 
    			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("vehicle.trailer.list")%>' />
  			</tr>
  			<tr> 
    			<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
 			<tr>
    			<td valign="top" height="100%" width="50%"> 
               		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' /> 
      					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        					<tr> 
          						<td width="100%"> 
            						<form id="VehicleList" name="VehicleList" action='<%=url%>' method="post">
              							<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                							<tr> 
                  								<td width="13%" align="left"  class="texts"> 
                  									<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/> 
                    								<input type="hidden" name="all" <%=(all) ? "value='all'" : ""%>/> 
                    								<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>:	
                  								</td >
                  								<td width="87%" align="left"> 
                  									<input type="text" name="value" value='<%=value%>' size="30" class="input"> 
                    								<input type="hidden" name="pageSize" value='<%=pageSize%>'>	
                  								</td>
                  								<td align="right" > 
                  									<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
                      									<tr> 
                        									<td align="left" width="5%" valign="middle">
                        										<input name="button" type="button" onClick="javascript:document.forms[2].submit();" value='<%=messages.getText("generic.messages.search")%>' class="input"/>	
                        									</td>
                      									</tr>
                    								</table>
                    							</td>
                							</tr>
											<tr>
												<td height="20" colspan="3" align="left" valign="top" class="texts">
													<table width="60%" border="0" align="right" cellpadding="0" cellspacing="0" class="specialarea">
                			                        	<tr>
                            			                	<td width="29%" valign="middle"><input type="radio" class="specialarea" name="status" value='<%=VehicleData.ACTIVE%>'<%=(Integer.parseInt(status) == VehicleData.ACTIVE) ? "checked" : ""%>>
				                                            	<%=messages.getText("generic.messages.active")%> 
					                                        </td>
				                                            <td width="37%" valign="middle"><input type="radio" class="specialarea" name="status" value='<%=VehicleData.EXCLUDED%>' <%=(Integer.parseInt(status) == VehicleData.EXCLUDED) ? "checked" : ""%>>
					                                            <%=messages.getText("generic.messages.excluded")%> 
					                                        </td>
		        	                                    	<td width="34%" align="left" valign="middle"><input type="radio" class="specialarea" name="status" value='<%=VehicleData.ALL%>' <%=(Integer.parseInt(status) == VehicleData.ALL) ? "checked" : ""%>>
				    	                                        <%=messages.getText("generic.messages.all.pl")%> 
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
               		<jsp:include  page="../component/utilityboxbottom.jsp" /> 
            	</td>
  				<td width="2%">&nbsp;</td>
  				<td valign="top" height="100%" width="49%">
  					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%" valign="top">
        						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
						
						<%
													if(userSessionData.checkPermission(SecurityConstants.CREATE_VEHICLE)) {
												%>	
								<table width="100%" height="40" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td  valign="middle"  width="5%"  align="left">
											<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										</td>
										<td  valign="middle" width="95%" >	
											<a href='<%=add%>' class="texts">						
												<%=messages.getText("vehicle.trailer.add")%>					
											</a>
										</td> 
									</tr>
								</table>	
						<%
								}
							%>
								<jsp:include page="../component/utilityboxbottom.jsp"/>	
							</td>
						</tr>
  					</table>
  				</td>
            </tr>		
			<tr> 
  				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr> 
  				<td colspan="3"> 
  					<form name="list">
  						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("vehicle.trailer.list")%>' /> 
    					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
      						<tr> 
        						<td width="100%"> 
          							<table width="100%" border="0">
            							<tr align="center"> 
<%
 	if(Util.checkPermission(sys,SecurityConstants.DELETE_VEHICLE) == null) {
 %>								        	<td width='5%' class="guide2"></td>
<%
	}
%>								            <td width='15%' class="guide2"><%=messages.getText("vehicle.trailer.registrationNumber")%></td>
								            <td width='27%' class="guide2"><%=messages.getText("vehicle.trailer.trade")%></td>
								            <td width='27%' class="guide2"><%=messages.getText("vehicle.trailer.model")%></td>
								            <td width='10%'	class="guide2"><%=messages.getText("vehicle.trailer.year")%></td>
								            <td width='15%'	class="guide2"><%=messages.getText("generic.driver.excludeddate")%></td>
            							</tr>
<%
	ListResult listResult = sys.getVehicleReportFacade().getTrailers(value,first,count,Integer.parseInt(status),userSessionData);
		int total = listResult.getTotal();
		if(all) 
		    pageSize = total;
		if(listResult != null && listResult.getElements().isEmpty()){
%>
              							<tr align="left"> 
											<td colspan="6" class="linetwo"><%=messages.getText("generic.messages.notresult")%></td>
              							</tr>
              						</table>
            					</td>
        					</tr>
<%
	}else {
	if(listResult != null){
	    int i = 0;
		Iterator it = listResult.getElements().iterator();
		while(it.hasNext() && (i < pageSize || all)){
	i++;
    				TrailerData trailer = (TrailerData)it.next();
%>             							<tr align=center align="right" class='<%=((i % 2)==0) ? "lineone" : "linetwo"%>'>
<%
	if(Util.checkPermission(sys,SecurityConstants.DELETE_VEHICLE) == null) {
%>		                					<td width='5%' align="center">
		                						<input type='checkbox' name='<%=trailer.getId()%>' value='<%=trailer.getId()%>' >
		                					</td>
<%
	}
%>		            						<td width='15%' align="center"> 
<%
 	if(search){
 %>            									<%=trailer.getPlate()%> 
<%
 	}else {
  					if(userSessionData.checkPermission(SecurityConstants.EDIT_VEHICLE)){
 %>			          							<a href='layout.jsp?refer=/vehicle/TrailerView.jsp?trailer=<%=trailer.getId()%>' > 						
													<%=trailer.getPlate()%> 
												</a>
					<%
						}else{
					%>
													<%=trailer.getPlate()%>
					<%
						}
					%>
												
<%
													}
												%>												
											</td>
           									<td width='27%' align="center"> 
           										<%=messages.getText(trailer.getTrade().getResourceBundleId())%> 
           									</td>
           									<td width='27%' align="center"> 
           										<%=trailer.getModel()%> 
           									</td>
           									<td width='10%' align="center"> 
           										<%=trailer.getYear()%> 
           									</td>
           									<td width='15%' align="center"> 
           										<%=(trailer.getExclusionDate() == null) ? "---" : Util.formatDate(trailer.getExclusionDate())%> 
           									</td>
           								</tr>
<%
	}
%>		      							<tr> 
											<td colspan="6" align="right">
<%
	if(!search){
	if(userSessionData.checkPermission(SecurityConstants.MODIFY_VEHICLE)) {
%>
  												<input name="button2" type="button" onClick="javascript:setAction(document.forms['list'],document.forms['exclusion'],'exclude');" value='<%=messages.getText("generic.driver.exclude")%>' class="input"/> 
  												<input name="button2" type="button" onClick="javascript:setAction(document.forms['list'],document.forms['exclusion'],'include');" value='<%=messages.getText("generic.messages.include")%>' class="input"/> 
	<%
 		}				
 	 				if(Util.checkPermission(sys,SecurityConstants.DELETE_VEHICLE) == null) {
 	%> 	              								<input name="button2" type="button" onClick="javascript:setAction(document.forms['list'],document.forms['exclusion'],'delete');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/> 
<%					}
					
					
					
				}else {
%> 	              								<input name="button2" type="button" onClick="javascript:associate(document.forms['list'],document.forms['AssociateTrailerForm'],'<%=messages.getText("trailer.associate.confirm")%>','<%=messages.getText("trailer.error.select1")%>');" value='<%=messages.getText("generic.messages.associate")%>' class="input"/> 
												<input name="cancel" type="button" value='<%=messages.getText("generic.messages.cancel")%>' class="input"/>
<%				}
%>										    </td>
              							</tr>
              						</table>
            					</td>
        					</tr>
        					<tr> 
          						<td> 
<%				Hashtable paginationParameters = new Hashtable();
				paginationParameters.put("value",value);
				paginationParameters.put("status",status);
				if(vehicle != null) {
					paginationParameters.put("vehicle",vehicle);
				}
				session.setAttribute("paginationParameters",paginationParameters);
%>            						<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(total)+"&link="+link%>' /> 
          						</td>
        					</tr>
<%			}			
		}	
%>      				</table>
      					<jsp:include page="../component/utilityboxbottom.jsp"/> 
					</form>
      			</td>
  			</tr>
  			<tr> 
    			<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  		</table>
	</body>
	<html:form action="/AssociateTrailer"  method="post">				
		<html:hidden property="vehicle" value='<%=vehicle%>' />
		<html:hidden property="checkboxParamList" />
	</html:form>	
	<form action="layout.jsp?refer=/vehicle/TrailerExclusion.jsp" name="exclusion" method="post">
		<input type="hidden" name="action" value="" />								
		<input type="hidden" name="checkboxParamList" />
	</form>						
<%	}
%>
</html:html>
