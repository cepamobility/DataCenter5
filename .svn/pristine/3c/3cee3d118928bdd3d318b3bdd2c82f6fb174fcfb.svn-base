<%@page import="communication.fleet.tables.OdometerFileData"%>
<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="java.io.*"
	import="java.text.*"
	import="java.util.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.util.*"
 	import="communication.administration.user.*" 
	import="communication.util.Filter"
	import="presentation.translator.web.activity.schedule.*"
 	import="presentation.translator.web.util.*"
	import="communication.fleet.tables.FleetData" 			
	import="communication.fleet.tables.FleetMainAttributes"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
		
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData = sys.getUserSessionData();

	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.FLEET_INSERT_DATA);	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		String fleet = (String)session.getAttribute("fleet");
		String period = (String)session.getAttribute("period");
		String year = (String)session.getAttribute("year");

		Collection review = (Collection)session.getAttribute("odometerData");
%>

<html:html>
	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	</head>
	<body>
		<table width="700"  border="0" cellspacing="0" cellpadding="0" align="center"> 
			<tr>
				<td>
					<html:form action="/UploadOdometerConfirm">
						<html:hidden property='fleet' value="<%=fleet%>"/>	
						<html:hidden property="period" value="<%=period%>"/>	
						<html:hidden property="year" value="<%=year%>"/>	
						<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	      					<tr>
	          					<td class="accessTitle">
	          						<%=messages.getText("fleet.uploadodometers.review") %>
	          					</td>
	      					</tr>
	      					<tr><td height="10"></td></tr>
	      					<tr>
	          					<td>
	          						<table width="100%" cellpadding="2" cellspacing="2">
	          							<tr>
	          								<td width="5%" align="right">
	          									<table width="15" height="15" bgcolor="00D700" border="1">
	          										<tr><td></td></tr>
	          									</table>
	          								</td>
	          								<th width="28%" align="left" class="linetwo">
	          									<%=messages.getText("generic.messages.validdata")%>
	          								</th>
	          								<td width="5%" align="right">
	          									<table width="15" height="15" bgcolor="FAEE14" border="1">
	          										<tr><td></td></tr>
	          									</table>
	          								</td>
	          								<th width="28%" align="left" class="linetwo">
	          									<%=messages.getText("fleet.uploadodometers.confictdata")%>
	          								</th>
	          								<td width="5%" align="right">
	          									<table width="15" height="15" bgcolor="FF0000" border="1">
	          										<tr><td></td></tr>
	          									</table>
	          								</td>
	          								<th width="28%" align="left" class="linetwo">
	          									<%=messages.getText("generic.error.invaliddata")%>
	          								</th>
	          							</tr>
									</table>	          								
	          					</td>
	      					</tr>
	      					<tr><td height="10"></td></tr>
<%		Iterator values = review.iterator();
		while(values.hasNext()) {
			OdometerFileData odometerReview = (OdometerFileData)values.next(); 
		
			String lastOdometerMsg = (odometerReview.getLastOdometer() == 0) ? messages.getText("fleet.uploadodometers.havenot")
					: messages.getText("fleet.uploadodometers.last")+": "+odometerReview.getLastOdometer()+" ("+messages.getText("generic.month."+odometerReview.getLastPeriod())+"/"+odometerReview.getLastYear()+")";
			String message = "";
			String vehicleMsg = messages.getText("generic.data.vehicle")+": " + odometerReview.getFilePlate(); 
			String odometerMsg = messages.getText("fleet.uploadodometers.new")+": " + odometerReview.getFileOdometer();

			//Color de la línea			
			String bgcolor = "#00D700";
			boolean action = false;
			
			int id = -1;
			String odometerAction = "1";
			int incidents = 0;
			int accidents = 0;
			String vehicle = "";	
			switch(odometerReview.getError()) {
				case OdometerFileData.ERROR_POST:
					message = messages.getText("fleet.uploadodometers.error1"); 
					bgcolor = "#FF0000";
					odometerAction = "-1";
					message = messages.getText("fleet.uploadodometers.message1"); 
					odometerAction = "-1";
					message = messages.getText("fleet.uploadodometers.message2")+" "; 
					bgcolor = "#FAEE14";
					action = true;
					message = messages.getText("fleet.uploadodometers.message3");
					message = messages.getText("fleet.uploadodometers.message4")+": "+String.valueOf("newOdometer-lastOdometer");
			}
%>		  					<!-- tr>
		      					<td width="800">
		      						<table width="800" height="45" cellspacing="0" class="texts" bgcolor="<%=bgcolor%>">
										<input type="hidden" name='<%="id"+vehicle%>' value='<%=String.valueOf(id)%>' />		
										<input type="hidden" name='<%="incidents"+vehicle%>' value='<%=String.valueOf(incidents)%>' />		
										<input type="hidden" name='<%="accidents"+vehicle%>' value='<%=String.valueOf(accidents)%>' />		
							  			<tr>
											<td class="input2"><%=vehicleMsg%></td>
											<th align="right" rowspan="2"  class="input2">
												<%=message%>
											</th>
							  			</tr>
							  			<tr>
											<td class="input2"><%=odometerMsg%></td>
							  			</tr>
							  			<tr>
											<td class="input2"><%=lastOdometerMsg%></td>
<%			if(action) {			
%>											<td align="right" class="input2">
												<%=messages.getText("fleet.uploadodometers.action")%>:
												<select name='<%="action"+vehicle%>' class="input">
													<option value="2"><%=messages.getText("fleet.uploadodometers.overwrite")%></option>
													<option value="-1"><%=messages.getText("fleet.uploadodometers.discard")%></option>
												</select>
											</td>
<%			}else {
%>											<input type="hidden" name='<%="action"+vehicle%>' value='<%=odometerAction%>' />		
<%			}
%>							  			</tr>
		      						</table>              
		      					</td>
	      					</tr>
		  					<tr>
		      					<td width="800" height="10"></td>
	      					</tr -->
<%		}
%>							<tr>
								<td align="right">
									<html:submit value='<%=messages.getText("generic.messages.save")%>' styleClass="input"/>
									<html:cancel value='<%=messages.getText("generic.messages.cancel")%>' styleClass="input"/>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
			</tr>
		</table>
	</body>
</html:html>
<%	}
%>

