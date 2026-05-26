<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import="communication.driver.tables.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="communication.activity.tables.*"
	import="business.activity.*"
    errorPage="../../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; SystemAccess sys;
	RequestDispatcher dispatcher; String context;
	UserSessionData userSessionData;	
%> 
<%
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	
	userSessionData=sys.getUserSessionData();	
	messages = sys.getText();
	session.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(true,false,false,false));
    
	Collection col1=new LinkedList();
   	col1.add(new OptionItem(messages.getText("generic.messages.active"),String.valueOf(DriverData.ACTIVE)));
   	col1.add(new OptionItem(messages.getText("generic.messages.excluded"),String.valueOf(DriverData.EXCLUDED)));   	
   	col1.add(new OptionItem(messages.getText("generic.messages.all.pl"),String.valueOf(DriverData.ALL)));   	
                   
	session.setAttribute("colstatus",col1);			

%>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>

		<html:form action="/ActivityMaxExpiry">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("activity.query.activitymaxexpiry")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("query.activityexpiry.basicdata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("generic.driver.driverdata.firstname")%> </th>
				         					<td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo">
											<th align="left"> <%=messages.getText("generic.driver.driverdata.lastname")%> </th>
				         					<td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
			      						<tr class="linetwo">
        									<th align="left">
        										<%=messages.getText("query.activityexpiry.driverstate")%><span class="required">*</span>
        									</th>			
        									<td align="right" colspan="4">
												<html:select property="status" styleClass="input" style="width:155;" >
													<html:options collection="colstatus" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
         									</td>								
      									</tr>
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.MONSANTO) {
%>      									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
			      							<th align="left"> 
			      								<%=messages.getText("generic.driver.driverdata.vehicleallowed")%><span class="required">*</span>
			      							</th>
			      							<td align="right" colspan="4">
			 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:130;" >
			      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
				  								</html:select>   
				  							</td>
										</tr>
<%					}else {
%>										<html:hidden property="vehicleAllowed" value=""/>		
<%					}
%>     									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									</table>
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
			      			</tr>
							<!-- tr>
								<td>
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.activitydata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
					            		<tr class="linetwo">
				              				<th align="left" valign="middle"><%=messages.getText("query.messages.from") %>
				       						<td align="right" class="linetwo">
				       							<jsp:include  page='<%="../../component/dateComponent.jsp?index=1&day=fromDay&month=fromMonth&year=fromYear&form=ActivityMaxExpiryForm" %>' />
						          			</td>
										<tr>
							      		<tr>
											<jsp:include  page="../../component/spaceline.jsp" />
						      			</tr>
					            		<tr class="linetwo">
				              				<th align="left" valign="middle"><%=messages.getText("query.messages.to") %>
				       						<td align="right" class="linetwo">
				       							<jsp:include  page='<%="../../component/dateComponent.jsp?index=2&day=toDay&month=toMonth&year=toYear&form=ActivityMaxExpiryForm" %>' />
						          			</td>
										<tr>
									</table>
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
			      			</tr -->
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ActivityMaxExpiryForm"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="../../util/divorg.jsp?form=ActivityMaxExpiryForm&consult=true"%>' />
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../../component/utilitybox4top.jsp" />
							<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="2" align="right" class="lineone">
										<html:submit styleClass="input" ><%=messages.getText("generic.messages.open")%></html:submit>
									</td>
								</tr>
							</table>
						<jsp:include  page="../../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>

<%@ include file = '../../util/jsp/googleanalytics.jsp' %>
		
