<%@page language="java"
errorPage="../../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import="communication.driver.tables.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.CorporationData"
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
	DataTypeAccess datatypes;	
%> 
<%

try{ 
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	
	userSessionData=sys.getUserSessionData();	
	messages = sys.getText();
	Collection count = new LinkedList();
	for(int i = 1; i < 6; i++) {
		count.add(new OptionItem(String.valueOf(i),String.valueOf(i)));	    
	}
	session.setAttribute("count",count);
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	if(session.getAttribute("DriversWithoutTOJForm") == null) {
		DriversWithoutBTWForm form = new DriversWithoutBTWForm();
	    session.setAttribute("DriversWithoutTOJForm",form);
	}
	
	session.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(false,false,false,sys.isCorporation(CorporationData.MONSANTO)));

	Collection col1=new LinkedList();
	col1.add(new OptionItem(messages.getText("generic.messages.select"),""));
   	col1.add(new OptionItem(messages.getText("generic.messages.active"),"generic.messages.active"));
   	col1.add(new OptionItem(messages.getText("generic.messages.excluded"),"generic.messages.excluded"));   	
   	col1.add(new OptionItem(messages.getText("generic.messages.all.pl"),"generic.messages.all.pl"));   	
                   
	session.setAttribute("colstatus",col1);			

%>

<script>
	function changeCombo(radio,combo) {
		var el = document.forms[2].elements[radio];
		document.forms[2].elements[combo].disabled = !el[2].checked;
	}
</script>
<script>
function changeType(value){
	document.forms[2].type.value = value;

}
</script>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/DriversWithoutTOJ">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("query.driverswithouttoj.title")%>' />
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
	      								<tr class="linetwo">
        									<th align="left">
			        							<%=messages.getText("query.activityexpiry.driverstate")%><span class="required">*</span>
        									</th>			
        									<td align="right" colspan="3">
												<html:select property="status" styleClass="input" style="width:180;" >
													<html:options collection="colstatus" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
         									</td>								
      									</tr>
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>     									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
			      							<th align="left" valign="top"> 
			      								<%=messages.getText("generic.driver.driverdata.vehicleallowed")%>
			      							</th>
			      							<td align="right" colspan="4">
			 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:180;"  multiple="true">
			      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
				  								</html:select>   
				  							</td>
										</tr>
<%					}else {
%>										<html:hidden property="vehicleAllowed" value=""/>		
<%					}
%>			      					</table>
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
			      			</tr>
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=DriversWithoutTOJForm"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=DriversWithoutTOJForm&consult=true" %>'/>
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
							<tr class="lineone">
								<td align="left" class="lineone">
									<html:select property="output" styleClass="input" >
										<html:options collection="format" property="value" labelProperty="label"/>
									</html:select>							          				
								</td>
								<td align="right" class="lineone">
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
		
	</body>
</html:html>

<%
}catch(Exception e){
	e.printStackTrace();
}
%>
