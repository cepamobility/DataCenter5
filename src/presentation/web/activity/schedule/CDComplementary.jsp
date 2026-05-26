<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.activity.tables.*"
	import="communication.util.*"
	import="communication.driver.tables.*"
	import="communication.datatype.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.activity.ActivityReportFacade"
	import="business.datatype.*"
	import="java.util.*"	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%!
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
%>
<%
		sys = (SystemAccess)session.getAttribute("SystemAccess");
		messages = sys.getText();
		userSessionData = sys.getUserSessionData();
		activityReport = sys.getActivityReportFacade();
		CheckListData checkList = activityReport.findCheckList(new Integer(18),userSessionData);	
		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
%>
				<tr>
		        	<td height="10"><img src="x" width="1" height="1"></td>
				</tr>
				<tr>
					<td align="center">
						<table width="600" border="0" cellpadding="0" cellspacing="0">
<%		while(pointIt.hasNext()) {	
			PointData point = (PointData) pointIt.next();
%>							<tr>
								<td width="49%">
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
<%			Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();
			String line = "linetwo";
			while(itemIt.hasNext()) {
				ItemData item = (ItemData)itemIt.next();
%>										<tr>
    										<td class="leftLine">&nbsp;</td>
											<td align="center">
												<table width="100%" cellpadding="0" cellspacing="0" border="0">
													<tr class='<%=(line)%>'>
														<td>
															<input type="checkbox" name='<%="item"+String.valueOf(item.getId())%>' />
														</td>
														<td align="left" >
															<%=messages.getText(item.getText()) %>
														</td>
													</tr>
												</table>
    										<td class="rightLine">&nbsp;</td>
    									</tr>
<%				line = (line.equals("linetwo")) ? "lineone" : "linetwo";
			}
%>						  				<tr>
						                  	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
						  				  	<td class="centerBase"></td>
						  				  	<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
									 	</tr>
                      				</table>
    							</td>
							</tr>
<%		}
%>						</table>
					</td>
				</tr>
