<%@page import="communication.fleet.tables.FleetData"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="communication.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
%>
<%! Text messages;
	SystemAccess sys;
	UserSessionData userSessionData;
	String id;
	boolean excluded;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	messages = sys.getText();
	id = request.getParameter("id");
	excluded = Boolean.parseBoolean(request.getParameter("excluded"));
	int type = Integer.parseInt(request.getParameter("type"));
%>
  			<!--  Opciones -->
  				<td colspan="5">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="texts1">					
					<tr>
						<td width="49%">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">					
<%				if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_FLEET)) {	
%>								<tr valign="bottom">
									<td style="width:50%" align="left">
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href="javascript:document.forms['edit'].submit();" class="texts">
											<%=messages.getText("generic.messages.edit")%>
										</a>
									</td>
								</tr>
								<tr valign="bottom">
							    	<td style="width:50%" align="left">
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href='layout.jsp?refer=/fleet/FleetVehicles.jsp?id=<%=id%>' class="texts">
											<%=messages.getText("web.fleet.changevehicles")%>										
										</a>
									</td>
								</tr>
<%				}
				if(userSessionData.checkPermission(SecurityConstants.DELETE_FLEET)) {
%>								<tr valign="bottom">
					      			<td style="width:50%" align="left">
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href="javascript:document.forms['edit'].submit();" class="texts">
											<%=messages.getText("generic.messages.edit")%>
										</a>
									</td>
								</tr>
<%				}
				if(type == FleetData.COLECTIVE) {
%>								<tr valign="bottom">
					      			<td style="width:50%" align="left">
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href="javascript:document.forms['deletedetail'].submit();" class="texts">
											<%=messages.getText("web.fleet.deletedetail")%>
										</a>
									</td>
								</tr>
<%				}
%>							</table>
						</td>
						<td width="2%">&nbsp;</td>
						<td width="49%">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">					
<%				if(sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_FLEET)) {	
%>								<tr valign="bottom">
									<td style="width:50%" align="left">
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href='layout.jsp?refer=/fleet/FleetDivisionEdit.jsp?id=<%=id%>' class="texts">
											<%=messages.getText("web.fleet.changedivisions")%>										
										</a>
									</td>
								</tr>
<%				    if(excluded) {	
%>								<tr valign="bottom">
									<td style="width:50%">  
										<!--  Opcion de Inclsuion: -->
										<img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;
										<a href="javascript:setAction(document.forms['exclusion'],'include');document.forms['exclusion'].submit();" class="texts")>
						         			<%=messages.getText("web.fleet.include")%>
						         		</a>
			         				</td>
			         			</tr>	
<%					}else {
%>								<tr valign="bottom">
  									<td style="width:50%"> 	
										<!-- Opcion de Exclusion: -->
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href="javascript:setAction(document.forms['exclusion'],'exclude');document.forms['exclusion'].submit();" class="texts">
											<%=messages.getText("web.fleet.exclude")%>
										</a>
									</td>
								</tr>
<%					}
				}	
%>								<tr valign="bottom">
  									<td style="width:50%"> 	
										<!-- Opcion de Exclusion: -->
										<img align="middle" border="0" src="/datacenter/util/imgs/icon2.gif" width="12">&nbsp;
										<a href='layout.jsp?refer=/fleet/FleetList.jsp' class="texts">
											<%=messages.getText("generic.messages.cancel")%>											
										</a>
									</td>
								</tr>
							</table>
						</td>						
					</tr>
				</table>	
				<jsp:include  page="../component/utilitybox3bottom.jsp" />
				</td>
			</tr>
  			<!-- Fin Opciones -->
