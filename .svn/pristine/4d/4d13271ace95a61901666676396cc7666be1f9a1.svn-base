<%@page language="java"
	errorPage="../../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import="communication.driver.tables.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.activity.tables.*"
	import="communication.administration.user.*"
	import="business.activity.*"
    
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

	if(session.getAttribute("DriversBTWAssistForm") == null) {
		DriversBTWAssistForm form = new DriversBTWAssistForm();
	    session.setAttribute("DriversBTWAssistForm",form);
	}
	
	Collection col1=new LinkedList();
	col1.add(new OptionItem(messages.getText("generic.messages.select"),""));
   	col1.add(new OptionItem(messages.getText("generic.messages.active"),"generic.messages.active"));
   	col1.add(new OptionItem(messages.getText("generic.messages.excluded"),"generic.messages.excluded"));   	
   	col1.add(new OptionItem(messages.getText("generic.messages.all.pl"),"generic.messages.all.pl"));   	
	session.setAttribute("colstatus",col1);			
	
   	Collection col2 = new LinkedList();   	
	col2.add(new OptionItem(messages.getText("generic.messages.select"),""));
	col2.add(new OptionItem(messages.getText("generic.messages.notteoricoasistence"),"generic.messages.notteoricoasistence"));
	col2.add(new OptionItem(messages.getText("generic.messages.notpracticoasistence"),"generic.messages.notpracticoasistence"));
	session.setAttribute("colassistence",col2);

   		
	//Solo listo los cursos que tienen asistencia teorico y practico (AMBOS)
	ActivityReportFacade activityReport = sys.getActivityReportFacade();
	ListResult listResult = activityReport.getActivitiesWithTeoricoAndPracticoAsistence(sys.getUserSessionData());	
	Iterator it = listResult.getElements().iterator();
	Collection col = new LinkedList();
	col.add(new OptionItem(messages.getText("generic.messages.select"),""));
	while (it.hasNext()){
		CEPAActivityDefinitionData cepaDef = (CEPAActivityDefinitionData)it.next();
		String msg = cepaDef.getResourceKeyName();
		if (cepaDef.getResourceKeyName().equalsIgnoreCase("datatype.scheduledactivitytype.btw1") ||
	       		cepaDef.getResourceKeyName().equalsIgnoreCase("datatype.scheduledactivitytype.btw2") ||
	       		cepaDef.getResourceKeyName().equalsIgnoreCase("datatype.scheduledactivitytype.btw3") ||
	       		cepaDef.getResourceKeyName().equalsIgnoreCase("datatype.scheduledactivitytype.btw2spv") ||
	       		cepaDef.getResourceKeyName().equalsIgnoreCase("datatype.scheduledactivitytype.btw2-4x4") ||
	       		cepaDef.getResourceKeyName().equalsIgnoreCase("datatype.scheduledactivitytype.btw3update")){      		
	       		msg = "datatype.scheduledactivitytype.btw";
       	}  
		if (!col.contains(new OptionItem(messages.getText(msg),msg))){
			col.add(new OptionItem(messages.getText(msg),msg));
		}
	}	  	

   	// Lista completa de cursos de la Compania:
    Iterator iter1=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,sys.getUserSessionData()).iterator();
	Collection colCorp = new LinkedList();
	colCorp.add(new OptionItem(messages.getText("generic.messages.select"),""));
    while(iter1.hasNext()){
       	GeneralDTData itemType=(GeneralDTData)iter1.next();
       	String msg = itemType.getResourceBundleId();
       	
       	// Si la compañia tiene varios tipos de BTW los pongo como un único BTW
       	if (itemType.getResourceBundleId().equalsIgnoreCase("datatype.scheduledactivitytype.btw1") ||
       		itemType.getResourceBundleId().equalsIgnoreCase("datatype.scheduledactivitytype.btw2") ||
       		itemType.getResourceBundleId().equalsIgnoreCase("datatype.scheduledactivitytype.btw3") ||
       		itemType.getResourceBundleId().equalsIgnoreCase("datatype.scheduledactivitytype.btw2spv") ||
       		itemType.getResourceBundleId().equalsIgnoreCase("datatype.scheduledactivitytype.btw2-4x4") ||
       		itemType.getResourceBundleId().equalsIgnoreCase("datatype.scheduledactivitytype.btw3update")){      		
       		msg = "datatype.scheduledactivitytype.btw";
       	}       	
   		if (!colCorp.contains(new OptionItem(messages.getText(msg),msg))){
   	       	colCorp.add(new OptionItem(messages.getText(msg),msg));
   		}
    }
    // Solo listo los cursos con asistencia teorico y practico (ambas) de la compañia:
    col.retainAll(colCorp);
	session.setAttribute("typecepaactivityscedule",col);	
	
	session.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(true,false,false,false));
	
	// TODO: Si no hay cursos con asistencias mostrar un mensaje !!!

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


<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>

	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/DriversBTWAssist">			
			<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("activity.query.driversbtwassist")%>' />
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
	      								<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.activityexpiry.activitytype")%><span class="required">*</span>
			        						</th>
			         						<td align="right" colspan="3">
												<html:select property="type" styleClass="input" style="width:155;" >
													<html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
			         						</td>
			      						</tr>		
	   									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
			      						
			        						<th align="left">
			        							<%=messages.getText("query.activityexpiry.driverstate")%><span class="required">*</span>
			        						</th>			
        						
			        						<td align="right" colspan="3">
												<html:select property="status" styleClass="input" style="width:155;" >
													<html:options collection="colstatus" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
        			 						</td>								
      									</tr>
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>     									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
			      							<th align="left"> 
			      								<%=messages.getText("generic.driver.driverdata.vehicleallowed")%><span class="required">*</span>
			      							</th>
			      							<td align="right" colspan="4">
			 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:155;" >
			      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
				  								</html:select>   
				  							</td>
										</tr>
<%					}else {
%>										<html:hidden property="vehicleAllowed" value=""/>		
<%					}
%>										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
      									<tr class="linetwo">
			        						<th align="left">
			        							<%=messages.getText("query.driversbtwassist.asistence")%><span class="required">*</span>
			        						</th>			
			        						<td align="right" colspan="3">
												<html:select property="assistence" styleClass="input" style="width:155;" >
													<html:options collection="colassistence" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
        			 						</td>								
      									</tr>
									</table>
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
			      			</tr>
			      			<tr>
				    			<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=DriversWithoutBTWForm"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=DriversBTWAssistForm&consult=true" %>'/>
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
									<td align="left" class="texts">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td colspan="2" align="right" class="texts">
										<html:submit styleClass="input" ><%=messages.getText("generic.messages.open")%></html:submit>
									</td>
								</tr>
							</table>
						<jsp:include  page="../../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
