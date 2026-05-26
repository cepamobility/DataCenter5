<%@page language="java" 
	errorPage="exception.jsp" 
	import="java.util.*"
	import="business.*"
	import="business.vehicle.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.activity.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.vehicle.*" 	
 	import="communication.administration.user.*" 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@page import="communication.administration.corporation.tables.CountryData"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	 	<title> CEPA International</title>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<style type="text/css">
<!--	body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.contain {
			width: 100%; 
			height: 100%;
		}
 -->
		</style>
	</head>
	<script type="text/javascript">
		function doSubmit() {
			var form = document.forms['ReporterJJForm'];
			form.send.disabled = true;
			form.submit();
		}
	</script>

<%!
	Text messages; 
	SystemAccess sys;
	String attribute; 
	String value;
	UserSessionData userSessionData; 
	RequestDispatcher dispatcher; String context; String pathMsg;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	int corporationId = sys.getCorporationId();
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.JJ_REPORTER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		HashMap mapValues = new HashMap();
		if(session.getAttribute("Msg") != null) {
	mapValues = (HashMap)userSessionData.getValue();
		}
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		session.setAttribute("url","reporter.jsp");
		messages=sys.getText();

		CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(CheckListData.COMMENTARY_DRIVE_JJ2),userSessionData);	
		Iterator pointIt = checkList.getSortPoints();
		
		DriverData driver = userSessionData.getDriverData();
		if(driver == null) {
	if(!Util.empty(request.getParameter("id"))) {
		Hashtable parameters = new Hashtable();
		parameters.put("id",request.getParameter("id"));
		Collection drivers = sys.getDriverReportFacade().findDriverReport(parameters,userSessionData);
		driver = (DriverData)drivers.iterator().next();
		userSessionData.setDriver(driver);
	}
		}
		
		if(driver == null) {
	response.sendRedirect(request.getContextPath()+"/reporterjj/forms.jsp");
		}else {
%>	
	<body>
	 	<table width="100%" height="90" border="0" cellpadding="0" cellspacing="0" class="default">
		 	<form name="logout" action="../login.jsp" method="post">
	 		</form>
		 	<form name="history" action="data.jsp" method="post" target="_blank">
		 		<input type="hidden" name="backForm" value='1'/>
	 		</form>
  			<tr>
    			<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
      					<tr>
        					<td><jsp:include  page='<%="../topMenu.jsp?home=true&reporter=true"%>'/></td>
      					</tr>
    				</table>	
    			</td><!--end the top�s table -->
  			</tr>
  		</table>
  		<br>
  		<br>	
  		<html:form action="/reporterjj/ReporterJJRegistry">
  			<html:hidden property="activity" value='<%=CEPAActivityConstants.REPORTERJJ_COMMENTARYDRIVE%>' />
		<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1"> 
		    	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("scheduledactivitytype.reporterjj.commentarydrive")%>' />
			</tr>
		  	<tr> 
	  			<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
			<tr> 
	  			<td colspan="3"> 
		    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("reporterjj.data.driver")%>' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
   						<tr  class="linetwo">
       						<th align="left"> <%=messages.getText("reporterjj.date")%></th>
       						<td align="right">
<%
	Calendar c = Calendar.getInstance();
		String day = String.valueOf(c.get(Calendar.DATE));
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		String year = String.valueOf(c.get(Calendar.YEAR));
		if(sys.getUserSessionData().getFilter().getLoginName().equals(SecurityConstants.LOGIN_REPORTERJJ_MX)) {
%>								<html:hidden property="date" value="<%=day%>" />
								<html:hidden property="month" value="<%=month%>" />
								<html:hidden property="year" value="<%=year%>" />
	    						<%=day+"/"+month+"/"+year%>

<%
	}else {
%>
       							<html:text property="date" size="3" styleClass="input" value="<%=day%>"/>&nbsp;/&nbsp;
       							<html:text property="month" size="3" styleClass="input" value="<%=month%>"/>&nbsp;/&nbsp;
       							<html:text property="year" size="6" styleClass="input" value="<%=year%>"/>
<%
	}
%>       					</td>
    					</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
   						<tr  class="linetwo">
       						<th align="left"> <%=(sys.isCorporation(CorporationData.BAYER_MX)) ? "Nombre del conductor (a)" : messages.getText("reporterjj.driver.name")%></th>
       						<td align="right"><%=driver.getFirstName()+" "+driver.getLastName()%></td>
    					</tr>
						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%
	if(sys.isCorporation(CorporationData.JJ)) {
%>  					<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.wwid")%></th>
       						<td align="right"><%=driver.getCorporationId()%></td>
	      				</tr>
	 					<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%
	}
		if(Util.empty(driver.getEmail())) {
%>						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.email")%><span class="required">*</span></th>
       						<td align="right"><html:text property="email" size="50" styleClass="input"/></td>
	      				</tr>
<%		}else {
			if(!sys.isCorporation(CorporationData.BAYER_MX)) {
%>						<tr class="linetwo" height="25">
	        				<th align="left" colspan="2" class="red"><%=messages.getText("reporterjj.commentarydrive.insertedemail") + " " + driver.getEmail() + " " + messages.getText("reporterjj.commentarydrive.changeemail")%></th>
	      				</tr>
						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.email")%></th>
       						<td align="right"><html:text property="email" size="50" styleClass="input"/></td>
	      				</tr>
<%			}else {
%>						<tr class="linetwo" height="25">
	        				<th align="left" colspan="2" class="red"><%=messages.getText("reporterjj.commentarydrive.insertedemail") + " " + driver.getEmail()%></th>
	      				</tr>
						<html:hidden property="email"/>
<%			}
		}
%>						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
	        				<th align="left" > <%=messages.getText("reporterjj.driver.managerown")%></th>
       						<td align="right">
       							<html:text property="managerName" size="50" styleClass="input"/>
							</td>
	      				</tr>
   						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						<tr class="linetwo">
	        				<th align="left" >
								<%=messages.getText("reporterjj.driver.mangerownemail")%><span class="required"><%=(sys.isCorporation(CorporationData.BAYER_MX)) ? " (Campo obligatorio)" : "*"%></span>
	        				</th>
       						<td align="right">
       							<html:text property="managerEmail" size="50" styleClass="input"/>
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
		    		<jsp:include  page='../component/utilitybox2top.jsp' /> 
					<table width="100%" border="0" align='center' cellpadding="0" cellspacing="0">
<%			if(!sys.isCorporation(CorporationData.JJ)) {
%>						<tr class="lineone">
					      	<td><input type='button' value='<%=messages.getText("generic.messages.history")%>' onclick="javascript:document.forms['history'].submit();" /></td>	
					  	</tr>
					  	<tr class="lineone" height="10">
					      	<td></td>	
					  	</tr>
<%			}
%>   					<tr class="lineone">
					      	<td><%=messages.getText("reporterjj.commentarydrive.text1")%></td>	
					  	</tr>
					  	<tr class="lineone" height="10">
					      	<td></td>	
					  	</tr>
   						<tr class="lineone">
					      	<td><%=messages.getText("reporterjj.commentarydrive.text2")%></td>	
					  	</tr>
					</table>			
	    			<jsp:include page="../component/utilitybox2bottom.jsp"/>
		      	</td>	
		  	</tr>
		  	<tr> 
		    	<jsp:include  page="../component/spaceline.jsp" />
		  	</tr>
		  	<tr> 
				
<%		int index = 1;
		if(pointIt.hasNext()) {
			PointData point = (PointData) pointIt.next();
			if(userSessionData.getFilter().getLoginName().equals(SecurityConstants.LOGIN_REPORTERJJ_MX)) {
				Collection items = point.getItems();
				Iterator itemIt = items.iterator();
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
%>					<input type="hidden" name='<%="item-"+String.valueOf(item.getId())%>' value="1" />
<%				}				
%>					<input type="hidden" name='<%="point-"+String.valueOf(point.getId())%>' value="" />				
<%			}else {
%>				<td colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
						<tr class="guide1">
							<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
							<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	  					</tr>
						<tr>
							<td class="leftLine">&nbsp;</td>
    						<td height="6"><img src="x" width="1" height="1"></td>
    						<td class="rightLine">&nbsp;</td>
						</tr>  					  					
						<tr>
							<td class="leftLine">&nbsp;</td>
							<td>
								<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
									<tr >
<%				Collection items = point.getItems();
				Collections.sort((List)items);
				Iterator itemIt = items.iterator();
				String line = "linetwo";
				while(itemIt.hasNext()) {
					ItemData item = (ItemData)itemIt.next();
					String checkvalue1 = "";
					String checkvalue2 = "";
					if(mapValues.containsKey("i"+item.getId())) { 
						if(Integer.parseInt((String) mapValues.get("i"+item.getId())) == 1) {
							checkvalue1 = "checked";
						}else {
							checkvalue2 = "checked";
						}
					}
%>										<td align="center" class='<%=line%>'>
											<table width="100%">	
												<tr>
													<th colspan="2" align="center" class='<%=line%>'>	
														<%=index+" - "+messages.getText(item.getText()) %>
													</th>
												</tr>
												<tr>
													<td class='<%=line%>'>	
														<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="1" <%=checkvalue1%>/>
														<%=messages.getText("generic.messages.correct") %>
													</td>
													<td class='<%=line%>'>	
														<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="2" <%=checkvalue2%>/>
														<%=messages.getText("generic.messages.incorrect") %>
													</td>
												</tr>
											</table>
										</td>
<%					line = (line.equals("linetwo")) ? "lineone" : "linetwo";
					index++;
				}
				String pointComment = (mapValues.containsKey("p"+point.getId())) ? (String) mapValues.get("p"+point.getId()) : "";

%>									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="left" class="linetwo" width="60%" colspan="4">
											<table width="100%" border="0">
												<tr>
													<th class="linetwo" align="left" valign="top">
														<%=messages.getText("generic.activity.observations") %>
													</th>
													<td class="linetwo">&nbsp;</td>
													<td class="linetwo" align="left" width="100%">
														<textarea name='<%="point-"+String.valueOf(point.getId())%>' style="width:80%" rows="2"><%=pointComment%></textarea>
													</td>
												</tr>
											</table>
										</td>
									</tr>
   								</table>	
							</td>    									
							<td class="rightLine">&nbsp;</td>
						</tr>	
		  				<tr>
		                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
		  				  	<td class="centerBase"></td>
		  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
					 	</tr>
          				</table>
				</td>
<%			}
		}
%>				
			</tr>	
			<tr>
	        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			<tr>

<%			for(int i = 0; i < 2; i++) {
				PointData point = (PointData) pointIt.next();
%>				<td width="49%" valign="top">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
						<tr class="guide1">
							<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
							<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	  					</tr>
						<tr>
							<td class="leftLine">&nbsp;</td>
    							<td height="6"><img src="x" width="1" height="1"></td>
    							<td class="rightLine">&nbsp;</td>
						</tr>  					  					
							<tr>
								<td class="leftLine">&nbsp;</td>
								<td>
								<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
									<tr>
										<td align="left" class="guide2" width="74%">
											&nbsp;
										</td>
										<td align="left" class="guide2" width="13%">
											<%=(sys.isCorporation(CorporationData.BAYER_MX)) ? messages.getText("activity.commentarydrivejj2.option1") : messages.getText("generic.messages.yes")%>
										</td>
										<td align="left" class="guide2" width="13%">
											<%=(sys.isCorporation(CorporationData.BAYER_MX)) ? messages.getText("activity.commentarydrivejj2.option2") :messages.getText("generic.messages.no")%>
										</td>
									</tr>
<%				Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();
			String line = "linetwo";
			while(itemIt.hasNext()) {
				ItemData item = (ItemData)itemIt.next();
				String checkvalue1 = "";
				String checkvalue2 = "";
				if(mapValues.containsKey("i"+item.getId())) { 
					if(Integer.parseInt((String) mapValues.get("i"+item.getId())) == 1) {
						checkvalue1 = "checked";
					}else {
						checkvalue2 = "checked";
					}
				}
%>										<tr class='<%=line%>'>
										<td align="left" >
											<%=index+" - "+messages.getText(item.getText()) %>
										</td>
										<td align="center">
											<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="1"  <%=checkvalue1%>/>
										</td>
										<td align="center">
											<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="2"  <%=checkvalue2%>/>
										</td>
									</tr>
<%				line = (line.equals("linetwo")) ? "lineone" : "linetwo";
				index++;
			}
			String pointComment = (mapValues.containsKey("p"+point.getId())) ? (String) mapValues.get("p"+point.getId()) : "";
%>									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="left" class="linetwo" width="100%" colspan="4">
											<table width="100%" border="0">
												<tr>
													<th class="linetwo" align="left" valign="top">
														<%=messages.getText("generic.activity.observations") %>
													</th>
													<td class="linetwo">&nbsp;</td>
													<td class="linetwo" align="left" width="100%">
														<textarea name='<%="point-"+String.valueOf(point.getId())%>' style="width:90%" rows="2"><%=pointComment%></textarea>
													</td>
												</tr>
											</table>
										</td>
									</tr>
   								</table>	
							</td>    									
							<td class="rightLine">&nbsp;</td>
						</tr>	
		  				<tr>
		                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
		  				  	<td class="centerBase"></td>
		  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
					 	</tr>
          				</table>
				</td>
<%			if(i == 0) {
%>					<td width="2%"></td>		
<%			}
	}
%>
			</tr>	
			<tr>
	        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			<tr>
<%			if(pointIt.hasNext()) {
			PointData point = (PointData) pointIt.next();
%>				<td colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
						<tr class="guide1">
							<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
							<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	  					</tr>
						<tr>
							<td class="leftLine">&nbsp;</td>
    						<td height="6"><img src="x" width="1" height="1"></td>
    						<td class="rightLine">&nbsp;</td>
						</tr>  					  					
							<tr>
								<td class="leftLine">&nbsp;</td>
								<td valign="top">
									<table width="100%" height="100%" border="0" align="center" cellpadding="2" cellspacing="2">
										<tr>
<%		Collection items = point.getItems();
		Collections.sort((List)items);
		Iterator itemIt = items.iterator();
		for(int i = 0; i < 2; i++) {	
%>
											<td align="left" width="50%" valign="top">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td align="left" class="guide2" width="74%">
															&nbsp;
														</td>
														<td align="center" class="guide2" width="13%">
															<%=(sys.isCorporation(CorporationData.BAYER_MX)) ? messages.getText("activity.commentarydrivejj2.option1") : messages.getText("generic.messages.yes")%>
														</td>
														<td align="center" class="guide2" width="13%">
															<%=(sys.isCorporation(CorporationData.BAYER_MX)) ? messages.getText("activity.commentarydrivejj2.option2") : messages.getText("generic.messages.no")%>
														</td>
													</tr>
<%			if(!sys.isCorporation(CorporationData.BAYER_MX)) {
%>													<tr>
														<td align="left" class="guide2small" width="74%">
															&nbsp;
														</td>
														<td align="center" class="guide2small" width="13%">
															<%="("+messages.getText("generic.messages.withouthelp")+")"%>
														</td>
														<td align="center" class="guide2small" width="13%">
															<%="("+messages.getText("generic.messages.withhelp")+")"%>
														</td>
													</tr>
<%			}
			String line = "linetwo";
			int count = 1;
			for(int j = 0; j < 9; j++) {
				ItemData item = (ItemData)itemIt.next();
				String checkvalue1 = "";
				String checkvalue2 = "";
				if(mapValues.containsKey("i"+item.getId())) { 
					if(Integer.parseInt((String) mapValues.get("i"+item.getId())) == 1) {
						checkvalue1 = "checked";
					}else {
						checkvalue2 = "checked";
					}
				}
%>													<tr class='<%=line%>'>
														<td align="left" >
															<%=index+" - "+messages.getText(item.getText()) %>
														</td>
														<td align="center">
															<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="1"  <%=checkvalue1%>/>
														</td>
														<td align="center">
															<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="2" <%=checkvalue2%> />
														</td>
													</tr>
<%				line = (line.equals("linetwo")) ? "lineone" : "linetwo";
				index++;
			}
%>												</table>
											</td>
<%		}
		String pointComment = (mapValues.containsKey("p"+point.getId())) ? (String) mapValues.get("p"+point.getId()) : "";
%>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td align="left" class="linetwo" width="60%" colspan="4">
												<table width="100%" border="0">
													<tr>
														<th class="linetwo" align="left" valign="top">
															<%=messages.getText("generic.activity.observations") %>
														</th>
														<td class="linetwo">&nbsp;</td>
														<td class="linetwo" align="left" width="100%">
															<textarea name='<%="point-"+String.valueOf(point.getId())%>' style="width:80%" rows="2"><%=pointComment%></textarea>
														</td>
													</tr>
												</table>
											</td>
										</tr>
	   								</table>	
								</td>    									
								<td class="rightLine">&nbsp;</td>
							</tr>	
			  				<tr>
			                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
			  				  	<td class="centerBase"></td>
			  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
						 	</tr>
          				</table>
					</td>
<%			}
%>				
				</tr>	
				<tr>
	        		<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
			<tr>
				<td align="center" colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
						<tr>
<%			for(int i = 0; i < 2; i++) {
			PointData point = (PointData) pointIt.next();
			String width = (i == 0) ? "60%" : "40%";
%>					<td width="<%=width%>" valign="top">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
						<tr class="guide1">
							<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(point.getText())%></div></td>
							<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	  					</tr>
						<tr>
							<td class="leftLine">&nbsp;</td>
    						<td height="6"><img src="x" width="1" height="1"></td>
    						<td class="rightLine">&nbsp;</td>
						</tr>  					  					
						<tr>
							<td class="leftLine">&nbsp;</td>
							<td>
								<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  	
									<tr>
										<td align="left" class="guide2" width="74%">
											&nbsp;
										</td>
										<td align="center" class="guide2" width="13%">
											<%=(sys.isCorporation(CorporationData.BAYER_MX)) ? messages.getText("activity.commentarydrivejj2.option1") : messages.getText("generic.messages.yes")%>
										</td>
										<td align="center" class="guide2" width="13%">
											<%=(sys.isCorporation(CorporationData.BAYER_MX)) ? messages.getText("activity.commentarydrivejj2.option2") : messages.getText("generic.messages.no")%>
										</td>
									</tr>			
<%			if(!sys.isCorporation(CorporationData.BAYER_MX)) {
%>									<tr>
										<td align="left" class="guide2small" width="74%">
											&nbsp;
										</td>
										<td align="center" class="guide2small" width="13%">
											<%="("+messages.getText("generic.messages.withouthelp")+")"%>
										</td>
										<td align="center" class="guide2small" width="13%">
											<%="("+messages.getText("generic.messages.withhelp")+")"%>
										</td>
									</tr>
<%			}
			Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();
			String line = "linetwo";
			while(itemIt.hasNext()) {
				ItemData item = (ItemData)itemIt.next();
				String checkvalue1 = "";
				String checkvalue2 = "";
				if(mapValues.containsKey("i"+item.getId())) { 
					if(Integer.parseInt((String) mapValues.get("i"+item.getId())) == 1) {
						checkvalue1 = "checked";
					}else {
						checkvalue2 = "checked";
					}
				}
%>									<tr class='<%=line%>'>
										<td align="left" >
											<%=index+" - "+messages.getText(item.getText()) %>
										</td>
										<td align="center">
											<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="1" <%=checkvalue1%> />
										</td>
										<td align="center">
											<input type="radio" name='<%="item-"+String.valueOf(item.getId())%>' value="2" <%=checkvalue2%> />
										</td>
									</tr>
<%				line = (line.equals("linetwo")) ? "lineone" : "linetwo";
				index++;
			}
			String pointComment = (mapValues.containsKey("p"+point.getId())) ? (String) mapValues.get("p"+point.getId()) : "";
%>									<tr>
										<td align="left" class="linetwo" width="60%" colspan="4">
											<table width="100%" border="0">
												<tr>
													<th class="linetwo" align="left" valign="top">
														<%=messages.getText("generic.activity.observations") %>
													</th>
													<td class="linetwo">&nbsp;</td>
													<td class="linetwo" align="left" width="100%">
														<textarea name='<%="point-"+String.valueOf(point.getId())%>' style="width:80%" rows="2"><%=pointComment%></textarea>
													</td>
												</tr>
											</table>
										</td>
									</tr>
   								</table>	
							</td>    									
							<td class="rightLine">&nbsp;</td>
						</tr>	
		  				<tr>
		                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
		  				  	<td class="centerBase"></td>
		  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
					 	</tr>
          				</table>
				</td>
<%			if(i == 0) {
%>					<td width="2%"></td>		
<%			}
	}
%>
						</tr> 				
          				</table>
		    	 </td>
			</tr>
<%	if(sys.isCorporation(CorporationData.BAYER_MX)) {
%>
			<tr>
	        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
						<tr class="guide1">
							<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.activity.result")%></div></td>
							<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	  					</tr>
						<tr>
							<td class="leftLine">&nbsp;</td>
    						<td height="6"><img src="x" width="1" height="1"></td>
    						<td class="rightLine">&nbsp;</td>
						</tr>  					  					
						<tr>
							<td class="leftLine">&nbsp;</td>
							<td>
								<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  	
									<tr>
										<td align="left" class="linetwo" width="100%" colspan="3">
											<%=messages.getText("commentarydrive.bayermx.note")%>
										</td>
									</tr>
									<tr>
										<td align="center" class="linetwo" width="33%">
											<html:radio property="note" value="1"><%=messages.getText(CEPAActivityScheduleRegistryData.APRUVED)%></html:radio>
										</td>
										<td align="center" class="linetwo" width="33%">
											<html:radio property="note" value="2"><%=messages.getText(CEPAActivityScheduleRegistryData.APRUVEDWITHOBSERVATIONS)%></html:radio>
										</td>
										<td align="center" class="linetwo" width="33%">
											<html:radio property="note" value="3"><%=messages.getText(CEPAActivityScheduleRegistryData.NOTAPRUVED)%></html:radio>
										</td>
									</tr>
								</table>
							</td>
						</tr>
		  				<tr>
		                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
		  				  	<td class="centerBase"></td>
		  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
					 	</tr>
          			</table>
		    	 </td>
		   	</tr>
<%	}
%>
			<tr>
	        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
						<tr class="guide1">
							<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.messages.observations")%></div></td>
							<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	  					</tr>
						<tr>
							<td class="leftLine">&nbsp;</td>
    							<td height="6"><img src="x" width="1" height="1"></td>
    							<td class="rightLine">&nbsp;</td>
						</tr>  					  					
							<tr>
								<td class="leftLine">&nbsp;</td>
								<td>
		    					<table width="100%" border="0" cellpadding="0" cellspacing="0">
		    						<tr>
				       					<td valign="top" align="left" style="width:85%" class="linetwo">
				       						<font size="1" face="Arial, Helvetica, sans-serif"> 
												<html:textarea property="obs" style="width:80%" rows="3" ></html:textarea>
											</font>	        	
				        				</td> 		             
				    				</tr>
				    			</table>
		        			</td>        
							<td class="rightLine">&nbsp;</td>
						</tr>	
		  				<tr>
		                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
		  				  	<td class="centerBase"></td>
		  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
					 	</tr>
          				</table>
		    	 </td>
		   	</tr>
			<tr>
	        	<td height="10"><img src="x" width="1" height="1"></td>
			</tr>
			<tr>
				<td colspan="3" align="right">
	    			<jsp:include  page='../component/utilitybox2top.jsp' />
	    			<table width="100%" cellpadding="0" cellspacing="0">
	    				<tr class="lineone">
   							<td align="right">
   								<input type="button" name="send" value='<%=messages.getText("generic.messages.send")%>' class="input" onclick="doSubmit();"/>
   								<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input" />
							</td>
   						</tr>
					</table>			
	    			<jsp:include page="../component/utilitybox2bottom.jsp"/>
		      	</td>	
		  	</tr>
		</table>		
		</html:form>
	</body>
<%		}
	}
%>		
</html>
