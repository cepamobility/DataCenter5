<%@page language="java"
errorPage="../../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="business.activity.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.CorporationData"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import ="communication.activity.tables.CEPAActivityConstants"
    
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages; 	RequestDispatcher dispatcher; 
	SystemAccess sys;	String context;
	Integer pageNum; ActivityGeneralReportForm form;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");

	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	messages = sys.getText();
	int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();

	boolean first = Boolean.parseBoolean(request.getParameter("first"));
	if(first) {
		form = new ActivityGeneralReportForm();
		session.setAttribute("ActivityGeneralReportForm",form);
	}else {
		if(session.getAttribute("ActivityGeneralReportForm") == null) {
			form = new ActivityGeneralReportForm();
			session.setAttribute("ActivityGeneralReportForm",form);
		}else {
			form = (ActivityGeneralReportForm)session.getAttribute("ActivityGeneralReportForm");
		}
	}
	
	Collection years = new LinkedList();
	Calendar instance = Calendar.getInstance();
	for(int i = 1998; i <= instance.get(Calendar.YEAR)+1; i++) {
		years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
	}
	session.setAttribute("years",years);
	
	Collection months = new LinkedList();
	for(int i = 1; i <= 12; i++) { 
	    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
	}
	session.setAttribute("months",months);
	
	Collection activities = new LinkedList();
//	activities.add(new OptionItem(messages.getText("generic.messages.select"),""));                			
	activities.add(new OptionItem(messages.getText("generic.messages.all"),"generic.messages.all"));                			
	Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,sys.getUserSessionData()).iterator();
    while(iter.hasNext()){
    	GeneralDTData itemType=(GeneralDTData)iter.next();
    	if (!itemType.getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE)
    	       && (sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.FORESTALORIENTAL || !itemType.getResourceBundleId().equals(CEPAActivityConstants.PESADOS_1))) {
	    	activities.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
    	}
	}
    
	session.setAttribute("activities",activities);	
	
	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);
%>

<script language="JavaScript" type="text/javascript">

function onSelectFrom(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_month = document.getElementById("monthIdFrom");
  		var input_year = document.getElementById("yearIdFrom");
  		var date3 = new Date(time);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}
	function onSelectTo(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_month = document.getElementById("monthIdTo");
  		var input_year = document.getElementById("yearIdTo");
  		var date3 = new Date(time);
  		input_month.value = parseInt(date3.print("%m"),10);
  		input_year.value= parseInt(date3.print("%Y"),10);
	}	


function doSubmit() {
	document.forms['ActivityGeneralReportForm'].submit();
}
function submitPagination(page,all) {
	document.forms['ActivityGeneralReportForm'].all.value = all;
	document.forms['ActivityGeneralReportForm'].pageNum.value = page;
	document.forms['ActivityGeneralReportForm'].submit();
}
function doReport(id) {
	document.forms['ActivityGeneralReportForm'].id.value = id;
	document.forms['ActivityGeneralReportForm'].reportAction.value = 'doReport';
	document.forms['ActivityGeneralReportForm'].submit();
}
</script>
<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
</script>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<body bgcolor="#FFFFFF">
		<html:form action="/Activitygeneralreport">			
			<html:hidden property="all"/>
			<html:hidden property="pageNum"/>
			<html:hidden property="id"/>
			<html:hidden property="reportAction" value='doSearch'/>
			<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("query.activity.generalreport.title")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width='<%=(corporationId == CorporationData.FORESTALORIENTAL) ? "100%" : "49%" %>' <%=(corporationId == CorporationData.FORESTALORIENTAL) ? "colspan=3" : "" %> valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr valign="top">
								<td <%=(corporationId == CorporationData.FORESTALORIENTAL) ? "colspan=3" : "" %>>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td <%=(corporationId == CorporationData.FORESTALORIENTAL) ? "colspan=3" : "" %> valign="top">
												<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("query.activity.generalreport.activitydata")%>' />
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
								    	  						<tr  class="linetwo">
								        							<th align="left"> 	
								        								<%=messages.getText("query.activityexpiry.activitytype")%>
								        							</th>
								         							<td align="right" colspan="2">
					                									<html:select property="type" styleClass="input" style='width:250' multiple="true">
					                  										<html:options collection="activities" property="value" labelProperty="label" styleClass="input"/>
					                									</html:select>
									         						</td>
									      						</tr>
									      						<tr>
																	<jsp:include  page="../../component/spaceline.jsp" />
			      												</tr>
									      						<tr  class="linetwo">
									        						<th align="left"> 	
									        							<%=messages.getText("query.messages.from")%><span class="required">*</span>
									        						</th>
									         						<td align="right" width="60%">
					        	        								<html:select property="month1" styleClass="input" style="width:75;" styleId="monthIdFrom">
					            	      									<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
				                										</html:select>
							         								</td>
								         							<td align="right">
					                									<html:select property="year1" styleClass="input" styleId="yearIdFrom">
					                  										<html:options collection="years" property="value" labelProperty="label" styleClass="input" />
					                									</html:select>
			         													<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
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
								    	    							<%=messages.getText("query.messages.to")%><span class="required">*</span>
								        							</th>
								         							<td align="right">
				            	    									<html:select property="month2" styleClass="input" style="width:75;" styleId="monthIdTo">
				                	  										<html:options collection="months" property="value" labelProperty="label" styleClass="input"/>
				                										</html:select>
							         								</td>
							         								<td align="right">
				                										<html:select property="year2" styleClass="input" styleId="yearIdTo">
				                  											<html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
					                									</html:select>
			         													<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer; border: 1px solid red;"
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
																<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									      						<tr  class="linetwo">
									        						<th align="center" colspan="5">
									        							<html:checkbox property="prevista"><%=messages.getText(CEPAActivityConstants.PREVISTA)%></html:checkbox>&nbsp;
									        							<html:checkbox property="enSupervision"><%=messages.getText(CEPAActivityConstants.ENSUPERVISION)%></html:checkbox>&nbsp;
									        							<html:checkbox property="dictada"><%=messages.getText(CEPAActivityConstants.DICTADA)%></html:checkbox>
									        						</th>
									        					</tr> 	
															</table>
														</td>
													</tr>	
												</table>
												<jsp:include  page="../../component/utilityboxbottom.jsp" />
											</td>
										</tr>
							      		<tr>
											<jsp:include  page="../../component/spaceline.jsp" />
						      			</tr>
						      			<tr height="100%">
							    			<td valign="top" width="49%">
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ActivityGeneralReportForm"%>' />
												</table>
											</td>
<%				if(corporationId == CorporationData.FORESTALORIENTAL) {
%>											<td width="2%">&nbsp;</td>
    										<td valign="top" width="49%">
												<table width="100%" border="0" cellpadding="0" cellspacing="0">
													<jsp:include page='<%="/util/divorg.jsp?form=ActivityGeneralReportForm" %>'/>
												</table>
											</td>
<%				}
%>										</tr>
					      			</table>
					      		</td>	
<%				if(corporationId != CorporationData.FORESTALORIENTAL) {
%>				    			<td width="2%">&nbsp;</td>
			    				<td width="49%" valign="top" >
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<jsp:include page='<%="/util/divorg.jsp?form=ActivityGeneralReportForm" %>' />
									</table>
								</td>
<%				}
%>							</tr>
				      		<tr>
								<jsp:include  page='<%="../../component/spaceline.jsp" %>' />
			      			</tr>
							<tr>
								<td align="left" class="texts" colspan="3">
									<jsp:include  page="../../component/utilitybox4top.jsp" />
										<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
											<tr class="lineone">
												<td align="left">
													<html:select property="output" styleClass="input" >
														<html:options collection="format" property="value" labelProperty="label"/>
													</html:select>							          				
												</td>							
												<td colspan="2" align="right" class="texts">
													<html:submit styleClass="input"><%=messages.getText("generic.messages.consult")%></html:submit>
												</td>
											</tr>
										</table>
									<jsp:include  page="../../component/utilitybox4bottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
  				</tr>
	      		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
	    	  	</tr>
			</table>
		</html:form>
<%@ include file = '../../util/jsp/googleanalytics.jsp' %>
		
	</body>
</html:html>
