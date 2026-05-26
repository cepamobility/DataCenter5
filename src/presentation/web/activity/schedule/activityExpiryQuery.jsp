<%@page language="java"
	import="business.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"		    
	import="communication.administration.corporation.tables.*"
	import="communication.driver.tables.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.administration.user.*"
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
	ActivityExpiryForm form;
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	
	userSessionData=sys.getUserSessionData();	
	int corporationId = userSessionData.getFilter().getCorporationId().intValue();
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
	session.removeAttribute("isBTW");	
	if(session.getAttribute("ActivityExpiryForm") == null) {
	    form = new ActivityExpiryForm();
	    session.setAttribute("ActivityExpiryForm",form);
	}
	
	Collection days = new LinkedList();
    days.add(new OptionItem("",""));
	for(int i = 1; i <= 31; i++) {
		days.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("days",days);
	
	Collection years = new LinkedList();
    years.add(new OptionItem("",""));
	for(int i = 1998; i <= 2026; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("years",years);
	
	Collection months = new LinkedList();
    months.add(new OptionItem("",""));
	for(int i = 1; i <= 12; i++) { 
	    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
	}
	session.setAttribute("months",months);

	Collection col = new LinkedList();
	
	// Solo listo los cursos que tienen vencimiento:
	ActivityReportFacade activityReport = sys.getActivityReportFacade();
	ListResult listResult = activityReport.getActivitiesWithExpiryTime(sys.getUserSessionData());	
	Iterator it = listResult.getElements().iterator();
	col.add(new OptionItem(messages.getText("generic.messages.select"),""));
	while (it.hasNext()){
		CEPAActivityDefinitionData cepaDef = (CEPAActivityDefinitionData)it.next();
    	if (sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.FORESTALORIENTAL || !cepaDef.getResourceKeyName().equals(CEPAActivityConstants.PESADOS_1)) {
			col.add(new OptionItem(messages.getText(cepaDef.getResourceKeyName()),cepaDef.getResourceKeyName()));
    	}
	}	
   	

   	// Lista completa de cursos de la Compania:
    Iterator iter1=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,sys.getUserSessionData()).iterator();
	Collection colCorp = new LinkedList();
	colCorp.add(new OptionItem(messages.getText("generic.messages.select"),""));
    while(iter1.hasNext()){
       	GeneralDTData itemType=(GeneralDTData)iter1.next();
       	colCorp.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),
       			itemType.getResourceBundleId()));
    }
      
    // Solo listo los cursos con vencimiento de la compa�ia:
    col.retainAll(colCorp);
	session.setAttribute("typecepaactivityscedule",col);		
	
	session.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(false,false,false,sys.isCorporation(CorporationData.MONSANTO)));
    
	Collection col1=new LinkedList();
	col1.add(new OptionItem(messages.getText("generic.messages.select"),""));
   	col1.add(new OptionItem(messages.getText("generic.messages.active"),"generic.messages.active"));
   	col1.add(new OptionItem(messages.getText("generic.messages.excluded"),"generic.messages.excluded"));   	
   	col1.add(new OptionItem(messages.getText("generic.messages.all.pl"),"generic.messages.all.pl"));   	
                   
	session.setAttribute("colstatus",col1);			

%>

<script>

    function onSelectFrom(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdFrom");
  		var input_month = document.getElementById("monthIdFrom");
  		var input_year = document.getElementById("yearIdFrom");
  		var date3 = new Date(time);
  		input_day.value = parseInt(date3.print("%d"),10);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}
	function onSelectTo(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayIdTo");
  		var input_month = document.getElementById("monthIdTo");
  		var input_year = document.getElementById("yearIdTo");
  		var date3 = new Date(time);
  		input_day.value = parseInt(date3.print("%d"),10);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}	
    
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

		<html:form action="/Activityexpiry">			
			<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("query.activityexpiry.title")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width='<%=(corporationId == CorporationData.FORESTALORIENTAL) ? "100%" : "49%" %>' <%=(corporationId == CorporationData.FORESTALORIENTAL) ? "colspan=3" : "" %> valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td <%=(corporationId == CorporationData.FORESTALORIENTAL) ? "colspan=3" : "" %>>
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("query.activityexpiry.basicdata")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
			      						<tr  class="linetwo">
			        						<th align="left" valign="top"> 	
			        							<%=messages.getText("query.activityexpiry.activitytype")%><span class="required">*</span>
			        						</th>
			         						<td align="right" colspan="4">
												<html:select property="type" styleClass="input" style='<%=(corporationId == CorporationData.FORESTALORIENTAL) ? "width:350" : "width:250;" %>' multiple="true" >
													<html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
			         						</td>
			      						</tr>		
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>      								<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
			      							<th align="left"> 
			      								<%=messages.getText("activity.query.includethird")%>
			      							</th>
			      							<td align="right" colspan="4">
			 	     							<html:select property="third" styleClass="input" >
			      									<html:option value="1" styleClass="input"><%=messages.getText("generic.messages.no")%></html:option>
			      									<html:option value="2" styleClass="input"><%=messages.getText("generic.messages.yes")%></html:option>
				  								</html:select>   
				  							</td>
										</tr>
<%					}else {
%>										<html:hidden property="third" value=""/>		
<%					}
%>    									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
        									<th align="left">
        										<%=messages.getText("query.activityexpiry.driverstate")%><span class="required">*</span>
        									</th>			
        									<td align="right" colspan="4">
												<html:select property="status" styleClass="input" style='<%=(corporationId == CorporationData.FORESTALORIENTAL) ? "width:350" : "width:170;" %>'  >
													<html:options collection="colstatus" property="value" labelProperty="label" styleClass="input" />												
												</html:select>   											
         									</td>								
      									</tr>
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>      								<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
			      							<th align="left" valign="top"> 
			      								<%=messages.getText("generic.driver.driverdata.vehicleallowed")%>
			      							</th>
			      							<td align="right" colspan="4">
			 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:250;" multiple="true">
			      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
				  								</html:select>   
				  							</td>
										</tr>
<%					}else {
%>										<html:hidden property="vehicleAllowed" value=""/>		
<%					}
%>     									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.from")%>
			        						</th>
			         						<td align="right">
                								<html:select property="fromday" styleClass="input" styleId="dayIdFrom" >
                  									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>			        						
			         						<td align="right">
                								<html:select property="frommonth" styleClass="input" styleId="monthIdFrom" style="width:60;">
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="fromyear" styleClass="input" styleId="yearIdFrom">
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td width="40"  align="right">
			         							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;"
     											 onmouseover="this.style.background='red';" style="width:20;" onmouseout="this.style.background=''"/>
			         						</td>
			         						<script type="text/javascript">
												Calendar.setup({
        											inputArea     :    "yearIdFrom",
        											ifFormat       :    "%d/%m/%Y",
        											showsTime      :    true,
        											timeFormat     :    "24",
        											button         :    "calendar_button1",
        											step           :    1,
        											onSelect       :    onSelectFrom,
        											singleClick    :    true
    											});
											</script>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("driver.query.to")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
                								<html:select property="today" styleClass="input" styleId="dayIdTo">
                  									<html:options collection="days" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>			        						
			         						<td align="right">
                								<html:select property="tomonth" styleClass="input" styleId="monthIdTo" style="width:60;">
                  									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td align="right">
                								<html:select property="toyear" styleClass="input" styleId="yearIdTo">
                  									<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
                								</html:select>
			         						</td>
			         						<td width="40"  align="right">
			         							<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer;"
     											 onmouseover="this.style.background='red';" style="width:20;"  onmouseout="this.style.background=''"/>
			         						</td>
			         						<script type="text/javascript">
												Calendar.setup({
        											inputArea     :    "yearIdTo",
        											ifFormat       :    "%d/%m/%Y",
        											showsTime      :    true,
        											timeFormat     :    "24",
        											button         :    "calendar_button2",
        											step           :    1,
        											onSelect       :    onSelectTo,
        											singleClick    :    true
    											});
											</script>
			      						</tr>
									</table>
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
				      		<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
			      			</tr>
			      			<tr>
				    			<td width="49%" valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ActivityExpiryForm"%>' />
									</table>
								</td>
<%				if(corporationId == CorporationData.FORESTALORIENTAL) {
%>								<td width="2%">&nbsp;</td>
    							<td width="49%" valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divorg.jsp?form=ActivityExpiryForm&consult=true"%>' />
									</table>
								</td>
<%				}
%>							</tr>
						</table>
					</td>
<%				if(corporationId != CorporationData.FORESTALORIENTAL) {
%>    				<td width="2%">&nbsp;</td>
    				<td width="49%" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<jsp:include page='<%="/util/divorg.jsp?form=ActivityExpiryForm&consult=true"%>' />
						</table>
					</td>
<%				}
%> 				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../../component/utilitybox4top.jsp" />
							<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="left" class="lineone">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
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
		
