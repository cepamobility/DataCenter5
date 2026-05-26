 <%@page language="java"
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.SystemAccess"
	import="business.alert.*"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.alert.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"	
 	import="presentation.translator.web.alert.*"
 	import="communication.administration.user.*" 	
	import="communication.administration.user.UserSessionData" 
 %>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%! RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;String action;
	SystemAccess sys;  AlertForm alertForm;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys == null){
		response.sendRedirect("/exception.jsp");
	}
	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.ALERT_VIEW);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		UserSessionData userSessionData = sys.getUserSessionData();
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		String type="create";
		if(request.getParameter("typealert")!=null){
		    type = request.getParameter("typealert");
		}
		if((session.getAttribute("AlertForm") != null)&&(type.equals("edit"))){
			alertForm = (AlertForm)session.getAttribute("AlertForm");
		}else{
			session.removeAttribute("AlertForm");
			AlertForm alertForm=new AlertForm();
			session.setAttribute("AlertForm",alertForm);
			
		}
		session.setAttribute("target","AlertCorporationCreate");
		pathView="./AlertCorporationCreate.jsp";
		session.setAttribute("url","alert/AlertCorporationCreate.jsp");
	  	AlertABMFacade alertABMFacade = sys.getAlertABMFacade();
		action="/AlertCorporationCreateAction";
		DataTypeUtil dataTypeUtil = new DataTypeUtil(sys);
		session.setAttribute("frecuency",dataTypeUtil.getDataType(DataTypeConstants.ALERT_FRECUENCY,true,false,false));
		session.setAttribute("type",dataTypeUtil.getDataType(DataTypeConstants.ALERT_TYPE,true,false,false));
		
		
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<script>
	function setAction(form,action){
		form.action.value=action;
	}
	function deleteIFConfirm(form,msg){
		form.submit();
	}
	function onSelect1(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId1");
  		var input_month = document.getElementById("monthId1");
  		var input_year = document.getElementById("yearId1");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}
  	
	function onSelect2(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId2");
  		var input_month = document.getElementById("monthId2");
  		var input_year = document.getElementById("yearId2");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}

	function changeemail(){
		var countemail=document.getElementById("countemail");
		email=null;
		for(i=1;i<=5;i++){
			email=document.getElementById("divemail"+i);
			if (i<=countemail.value){
				email.style.display = 'block';
			}else {
				email.style.display = 'none';
			}
		}
	
	}

  	
</script>
<head/>
<body bgcolor="#FFFFFF">

	<form action="layout.jsp?refer=/alert/alertList.jsp" name='cancel' method="post">
		<input type="hidden" name="target" value="alertView" />
	</form>	
	<html:form action='<%=action%>' >
	<table width="700" align='center' cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?colspan=5&title="+messages.getText("web.alert.view")%>' />
		</tr>
   		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		
  		<tr>
			<td width="100%" valign="top" colspan="5">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
					<tr> 
          				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          				<td width="46%"><a href="javascript:document.forms['cancel'].submit()" class="texts"> 
		           	 	<%=messages.getText("generic.messages.cancel")%> </a></td>
        			</tr>
        		</table>
        		<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>  		  			
		<tr>
			<td valign="top" width="34%" >
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.alert.data")%>' />	
						<table width="100%"  border="0" cellpadding="0" cellspacing="0">
							<tr class="texts">
								<td width="41%" align="left">
									<%=messages.getText("generic.messages.initialdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
								</td>
								<td width="59%"  align="right">
									<html:text property="startDate" style="width:20;" styleClass="input" styleId="dayId1"/>/
		            				<html:text property="startMonth" style="width:20;" styleClass="input" styleId="monthId1"/>/
		            				<html:text property="startYear" style="width:40;" styleClass="input" styleId="yearId1"/>
		            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
          						<script type="text/javascript">
									Calendar.setup({
										inputArea     :    "yearId1",
      									ifFormat       :    "%d/%m/%Y",
      									showsTime      :    true,
      									timeFormat     :    "24",
      									button         :    "calendar_button1",
      									step           :    1,
      									onSelect       :    onSelect1,
      									singleClick    :    true
    								});
								</script>
	    					</tr>
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
	    					<tr class="texts">	  
	    						<td width="41%" align="left">
									<%=messages.getText("generic.messages.lastdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
								</td>
	    						<td width="59%"  align="right">
	    							<html:text property="lastDate" style="width:20;" styleClass="input" styleId="dayId2"/>/
		            				<html:text property="lastMonth" style="width:20;" styleClass="input" styleId="monthId2"/>/
		            				<html:text property="lastYear" style="width:40;" styleClass="input" styleId="yearId2"/>
		            				<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          			</td>
			          			<script type="text/javascript">
									Calendar.setup({
        								inputArea     :    "yearId2",
        								ifFormat       :    "%d/%m/%Y",
        								showsTime      :    true,
        								timeFormat     :    "24",
        								button         :    "calendar_button2",
        								step           :    1,
        								onSelect       :    onSelect2,
        								singleClick    :    true
    								});
								</script>	     									  
							</tr>
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
					      	<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.alert.alerttype")%>
								</td>
								<td align="right">
									<html:select property="alertType" styleClass="input" style="width:155;">
                  						<html:options collection="type" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>
																			
    					  	    </td>																									
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.alert.alertperiod")%>
								</td>
								<td align="right">
								<html:select property="frecuency" styleClass="input" style="width:155;">
                  						<html:options collection="frecuency" property="value" labelProperty="label" styleClass="input"/>
                					</html:select>										
    					  	    </td>																									
							</tr>		
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.alert.alertfrecuency")%>
								</td>
								<td align="right">
								<html:text property="dailyFrecuency" style="width:20;" styleClass="input" styleId="dailyFrecuency"/>							
    					  	    </td>																									
							</tr>
						</table>
						<jsp:include page="../component/utilityboxbottom.jsp"/>
			</td>
			<td width="1%" valign="top"></td>
			<td width="62%" valign="top">
				<table width="100%" height="100%" valign="top" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top" width="48%">	
							<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.alert.data.mail")%>' />
							<table width="100%" valign="top" cellpadding="0" cellspacing="0" >
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.alert.search.subject")%>' />
											<table width="100%" cellpadding="0" cellspacing="0">
												<tr>
											  	 <td width="85%" align="center">
												 	<html:textarea property="subject" cols="40" rows="1" />
											   	</td>
												</tr>
												<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											</table>
											<jsp:include  page="../component/utilitybox2bottom.jsp" />
				      				</td>
				      			</tr>
								<tr class="space"><td>&nbsp;</td></tr>
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.alert.search.body")%>' />
											<div id="scrollalert2">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
											  	 		<td width="100%" align="center">
												 			<html:textarea property="body" cols="50" rows="10" />
											   			</td>
													</tr>
													
												</table>
				      						</div>
											<jsp:include  page="../component/utilitybox2bottom.jsp" />
				      				</td>
				      			</tr>
								<tr class="space"><td>&nbsp;</td></tr>
								<tr class="linetwo">
									<td class="linetwo">
										<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.message.sendemail")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
											<tr class="linetwo">
												<td style="width:130;" align="left" class="lineone"><%=messages.getText("alert.count.email")%></td>
													<td align="left" class="linetwo">
		  												<select name="countemail" id="countemail" class="input" style="width:40;" onChange="javascript:changeemail();">
		  													<option value="1" >1</option>
		  													<option value="2" >2</option>
		  													<option value="3" >3</option>
		  													<option value="4" >4</option>
		  													<option value="5" >5</option>
		  												</select>
		  											</td>
		  									</tr>
		  									<tr>
												<jsp:include  page="../component/spaceline.jsp" />
		  									</tr>
		  									<tr>
		  										<td class="linetwo" colspan="5">
		  											<div id="divemail1">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 1"%></th>
				       	 									<td align="right" class="linetwo"><html:text property="email1" style="width:200;" styleClass="input" styleId="email1"/></td>
														</tr>
		      											<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail2" style="display:none">
		  											<table class="linetwo">
		  	       										<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 2"%></th>
				     	   									<td align="right" class="linetwo"><html:text property="email2" style="width:200;" styleClass="input" styleId="email2"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail3" style="display:none">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 3"%></th>
				     										<td align="right" class="linetwo"><html:text property="email3" style="width:200;" styleClass="input" styleId="email3"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail4" style="display:none">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 4"%></th>
				        									<td align="right" class="linetwo"><html:text property="email4" style="width:200;" styleClass="input" styleId="email4"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  											<div id="divemail5" style="display:none">
		  											<table class="linetwo">
		  												<tr>
															<th align="left" class="linetwo"> <%=messages.getText("alert.message.email")+" 5"%></th>
				       	 									<td align="right" class="linetwo"><html:text property="email5" style="width:200;" styleClass="input" styleId="email5"/></td>
														</tr>
														<tr>
															<jsp:include  page="../component/spaceline.jsp" />
		  												</tr>
		  											</table>
		  											</div>
		  										</td>
		  									</tr>	   					
				      					</table>
											<jsp:include  page="../component/utilitybox2bottom.jsp" />
				      				</td>
				      				
				      			</tr>
							</table>
							<jsp:include page="../component/utilityboxbottom.jsp"/>
						</td>
					</tr>
				</table>
				
			</td>
								
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		
  		<tr>
			<td width="100%" valign="top" colspan="5">
				<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
				<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
					<tr> 
          				<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          				<td width="46%"><a href="javascript:document.forms['cancel'].submit()" class="texts"> 
		           	 	<%=messages.getText("generic.messages.cancel")%> </a></td>
		           	 	<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          				<td width="46%"><a href="javascript:document.forms['AlertForm'].submit()" class="texts"> 
		           	 	<%=messages.getText("generic.messages.accept")%> </a></td>
		           	 	
        			</tr>
        		</table>
        		<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
		</tr>
					
	</table>
	
	<br />
	<br />
	</html:form>	
<%		
	}
%>
</body>
</html:html>