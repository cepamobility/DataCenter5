<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="business.datatype.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>
<%! RequestDispatcher dispatcher;
	Text messages; 
	SystemAccess sys;
	String type;
	String formEnding;
	String onclickAll = "";
	String onclickNone = "";
	String onclickSelect = "";
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.QUERY);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

	    int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
	    String ending = (corporationId == CorporationData.JJ) ? "JJ" : "";
	        
		session.setAttribute("url","queries/reportAccidentType.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);
		type = request.getParameter("type");
		if(session.getAttribute("ReportAccidentTypeForm"+ending) == null) {
		    if(corporationId == CorporationData.JJ) {
		        ReportAccidentTypeFormJJ form = new ReportAccidentTypeFormJJ();
			    session.setAttribute("ReportAccidentTypeForm"+ending,form);
		    }else {
		        ReportAccidentTypeForm form = new ReportAccidentTypeForm();
			    session.setAttribute("ReportAccidentTypeForm"+ending,form);
		    }
		}
		
		Collection years = new LinkedList();
		years.add(new OptionItem("",""));
		Calendar instance = Calendar.getInstance();
		for(int i = 1998; i <= instance.get(Calendar.YEAR)+1; i++) {
			years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
		}
		session.setAttribute("years",years);
		
		Collection months = new LinkedList();
	    months.add(new OptionItem("",""));
		for(int i = 1; i <= 12; i++) { 
		    months.add(new OptionItem(messages.getText("generic.month."+String.valueOf(i)),String.valueOf(i)));
		}
		for(int i = 1; i <= 4; i++) { 
		    months.add(new OptionItem("Q"+String.valueOf(i),"Q"+String.valueOf(i)));
		}
		session.setAttribute("months",months);
		
		Collection record = new LinkedList();
		record.add(new OptionItem(messages.getText("generic.messages.all"),"all"));
		record.add(new OptionItem(messages.getText("generic.event.nonrecordable"),"nrecordable"));	    
		record.add(new OptionItem(messages.getText("generic.event.recordable"),"recordable"));	    	    
		session.setAttribute("record",record);

		DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();
	    
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>


	
	<body bgcolor="#FFFFFF">
		<html:form action='<%="/Reportaccidenttype"+ending%>' >
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.query.accidentType")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> <jsp:include page='<%="/util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+"Corporation"+ending+".jsp?form=ReportAccidentTypeForm"+ending+"&consult=true&default=generic.messages.all.pl"%>' /></td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidettype.period")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.accidettype.from")%><span class="required">*</span>
			        						</th>
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
			         						
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.accidettype.to")%><span class="required">*</span>
			        						</th>
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
			         					
			      						</tr>
				  						<!--  <tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr class="linetwo">
											<th align="left"> 	
						        				=messages.getText("generic.event.record")%><span> </span>
						        			</th>
						         			<td align="right" colspan="2">
			                					<html:select property="record" styleClass="input" >
			                  						<html:options collection="record" property="value" labelProperty="label" styleClass="input"/>
			                					</html:select>
						         			</td>
										</tr> -->
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.accidentincidenttype.accidents")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
												<html:checkbox property="colective" ><%=messages.getText("query.accidettype.colective")%></html:checkbox>
			         						</td>
			         						<td align="right">
												<html:checkbox property="individual"><%=messages.getText("query.accidettype.individual")%></html:checkbox>
			         						</td>
			      						</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
							<tr>
								<td valign="top"> <jsp:include page='<%="/util/reportOrder.jsp?form=ReportAccidentTypeForm"+ending+"&all=true"%>' /></td>
							</tr>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="/util/divorgCorporation"+ending+".jsp?form=ReportAccidentTypeForm"+ending+"&consult=true&default=generic.messages.all.pl"%>' />
	                     </table>
    	             </td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidenttype.accidents")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection accidents = dataTypeReport.findAll(communication.datatype.DataTypeConstants.OLD_ACCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(accidents != null && accidents.size() > 0) {
					
				    Iterator it = accidents.iterator();
				    while(it.hasNext()) {
				        AccidentClasificationDataType accident = (AccidentClasificationDataType)it.next();
%>							<tr>
								<td width="50%" class="linetwo">
									<input type="checkbox" name='a<%=accident.getResourceBundleId()%>' checked/><%=messages.getText(accident.getResourceBundleId())%>
								</td>
<%					    if(it.hasNext()) {
    						accident = (AccidentClasificationDataType)it.next();
%>								<td width="50%" class="linetwo">
									<input type="checkbox" name='a<%=accident.getResourceBundleId()%>'  checked/><%=messages.getText(accident.getResourceBundleId())%>
								</td>
<%    					}
%>							</tr>
<%					}
				}
				// Accident: a + datatype...
				formEnding = "ReportAccidentTypeForm"+ending;
				onclickAll = "javascript:changeSelection('"+formEnding+"','adatatype','datatype.collisionclasification',1)";
				onclickNone = "javascript:changeSelection('"+formEnding+"','adatatype','datatype.collisionclasification',2)";
				onclickSelect = "javascript:changeSelection('"+formEnding+"','adatatype','datatype.collisionclasification',3)";
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick="<%=onclickAll%>" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick="<%=onclickNone%>" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick="<%=onclickSelect%>" class="input" />
								</td>
							</tr>							
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidenttype.incidents")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection incidents = dataTypeReport.findAll(communication.datatype.DataTypeConstants.OLD_INCIDENT_CLASIFICATION,sys.getUserSessionData());
				if(incidents != null && incidents.size() > 0) {
				
				    Iterator it = incidents.iterator();
				    while(it.hasNext()) {
				        IncidentClasificationDataType incident = (IncidentClasificationDataType)it.next();
%>							<tr>
								<td width="50%" class="linetwo">
									<input type="checkbox" name='i<%=incident.getResourceBundleId()%>'  checked /><%=messages.getText(incident.getResourceBundleId())%>
								</td>
<%					    if(it.hasNext()) {
    						incident = (IncidentClasificationDataType)it.next();
%>								<td width="50%" class="linetwo">
									<input type="checkbox" name='i<%=incident.getResourceBundleId()%>'  checked /><%=messages.getText(incident.getResourceBundleId())%>
								</td>
<%    					}
%>							</tr>
<%					}
				}
				// Incidentes: i + datatype ....
				formEnding = "ReportAccidentTypeForm"+ending;
				onclickAll = "javascript:changeSelection2('"+formEnding+"','idatatype',1)";
				onclickNone = "javascript:changeSelection2('"+formEnding+"','idatatype',2)";
				onclickSelect = "javascript:changeSelection2('"+formEnding+"','idatatype',3)";
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick="<%=onclickAll%>" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick="<%=onclickNone%>" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick="<%=onclickSelect%>" class="input" />
								</td>
							</tr>							
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
				<tr>
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("query.accidenttype.fines")%>' />
						<table width="100%" cellpadding="0" cellspacing="0">
<%				Collection fines = dataTypeReport.findAll(communication.datatype.DataTypeConstants.OLD_FINE_QUALIFY,sys.getUserSessionData());
				if(fines != null && fines.size() > 0) {
				    Iterator it = fines.iterator();
				    while(it.hasNext()) {
				        FineQualifyDataType fine = (FineQualifyDataType)it.next();
%>							<tr>
								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=fine.getResourceBundleId()%>'  checked /><%=messages.getText(fine.getResourceBundleId())%>
								</td>
<%					    if(it.hasNext()) {
    						fine = (FineQualifyDataType)it.next();
%>								<td width="50%" class="linetwo">
									<input type="checkbox" name='<%=fine.getResourceBundleId()%>'  checked /><%=messages.getText(fine.getResourceBundleId())%>
								</td>
<%    					}
%>							</tr>
<%					}
				}
				formEnding = "ReportAccidentTypeForm"+ending; // Reportaccidenttype"+ending
				onclickAll = "javascript:changeSelection2('"+formEnding+"','datatype.finequalify',1)";
				onclickNone = "javascript:changeSelection2('"+formEnding+"','datatype.finequalify',2)";
				onclickSelect = "javascript:changeSelection2('" + formEnding + "','datatype.finequalify',3)";
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick="<%=onclickAll%>" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick="<%=onclickNone%>" class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.invertselection")%>' onclick="<%=onclickSelect%>" class="input" />
								</td>
							</tr>							
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
	  			<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			<tr>
					<td align="left" colspan="3" class="texts">
						<html:select property="output" styleClass="input" >
							<html:options collection="format" property="value" labelProperty="label"/>
						</html:select>							          				
					</td>
	  			</tr>
  				<tr>
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
							<html:submit styleClass="input"><%=messages.getText("generic.messages.open")%></html:submit>
				  		</div>          
				  	</td>
			  	</tr>
		  	</table>
		</html:form>


	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<script>

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
	function changeSelection2(formName,value,selection) {
		var elements=document.forms[formName].elements;
		for(i = 0; i < elements.length; i++) {
			var element=elements[i];
			if(element.name.indexOf(value) > -1) {
				// alert(selection);
				if(selection == 1) {
					element.checked = true;
				}else if(selection == 2) {
					element.checked = false;
				}else {
					element.checked = !element.checked;
				}
			}
		}
	}
	function changeSelection(formName,value1,value2,selection) {
		var elements=document.forms[formName].elements;
		for(i = 0; i < elements.length; i++) {
			var element=elements[i];
			if((element.name.indexOf(value1) > -1) || (element.name.indexOf(value2) > -1)){
				if(selection == 1) {
					element.checked = true;
				}else if(selection == 2) {
					element.checked = false;
				}else {
					element.checked = !element.checked;
				}
			}
		}
	}
	</script>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
		
	</body>
<%	}	%>
</html:html>
