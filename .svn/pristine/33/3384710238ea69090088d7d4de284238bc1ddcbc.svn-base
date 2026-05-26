<%@page language="java"
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.ActivityTypeDataType" 
	import="communication.datatype.*"
	import="communication.datatype.tables.*"	
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="communication.activity.tables.CEPAActivityConstants"
	import="business.activity.ActivityReportFacade"
	import="communication.activity.tables.CEPAActivityScheduleData"	
	import="presentation.translator.web.activity.schedule.*"	 
	import="communication.security.*"	
	import="communication.administration.user.UserSessionData"	
	import="communication.activity.tables.*"	
			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; String pathLanguage;
	SystemAccess sys; 
	UserSessionData userSessionData;
	ActivityForm activityForm;
	DataTypeReportFacade datatypes;
	String idActivity;
%>

<%
	pathLanguage="/util/jsp/language.jsp";
	
	// Carga system access (sys),userSession, id de la corporacion (crpName), lenguaje (lngName) y los textos (messages)
	dispatcher=request.getRequestDispatcher(pathLanguage);
	dispatcher.include(request,response);

	// hace el include de la página que despliega el box que indica el resultado de la operación
	// el texto que se despliega en el box es el guardado en la variable de sesión bajo la key Msg
	pathMsg="/util/jsp/message.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
%>


<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_CEPASCHEDULEACTIVITY);
	
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_CD_ACTIVITY);
	
	if ((check!=null) && (check1!=null))  {
		response.sendRedirect(request.getContextPath()+check);

	}else {

		pathLanguage="/util/jsp/language.jsp";
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		messages=sys.getText();		
		
		datatypes=sys.getDataTypeReportFacade();
		
		/*
		if(session.getAttribute("target")==null){
			session.setAttribute("target",request.getParameter("target"));
		}
		*/
		
		idActivity = (String)request.getParameter("id"); 
		if(idActivity == null){
			idActivity = (String)request.getParameter("idActivity"); // Refactoring
			if(idActivity == null){
				idActivity = (String)session.getAttribute("id");
			}
		}
		// session.setAttribute("id",idActivity);				
		// session.setAttribute("url","activity/schedule/CEPAActivityScheduleEdit.jsp?id="+idActivity);
		userSessionData.setUrl("activity/schedule/CEPAActivityScheduleEdit.jsp?id="+idActivity);	
		
		ActivityReportFacade activityReportFacade = sys.getActivityReportFacade();
		CEPAActivityScheduleData activityData = activityReportFacade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),userSessionData);
	
		if(activityData!=null){ 
			activityForm=ActivityUtil.createActivityForm(activityData,ActivityUtil.EDIT,messages);
			session.setAttribute("ActivityForm",activityForm);

			// session.setAttribute("activityId",activityData.getActivityId());
			activityForm.setId(activityData.getActivityId().toString());
			
			// session.setAttribute("isOld",new Boolean(activityData.getIsOld()));
			activityForm.setIsOld((new Boolean(activityData.getIsOld())).toString());
			
	%>  

<LINK REL="StyleSheet" HREF="../../util/css/estilo.css" TYPE="text/css">

<head></head>

<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' ></script> 

<script>

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

function cambio(parent,child,message){
	var parentCombo = document.forms[2].elements[parent];
	var childCombo = document.forms[2].elements[child];
	if(parentCombo.options[parentCombo.selectedIndex].value != "") {
		var divi = parentCombo.options[parentCombo.selectedIndex].text+parentCombo.options[parentCombo.selectedIndex].value;
		var list = document.forms[2].elements[divi];
		childCombo.options.length=list.options.length;
		for(i=0; i < list.length; i++) {
			childCombo.options[i].value = list.options[i].value;
			childCombo.options[i].text = list.options[i].text;
		}
	}else {
		childCombo.options.length=1;
		childCombo.options[0].value = "";
		childCombo.options[0].text = message;
	}
}
function observationsDefault(type){
	if (type == '<%=CEPAActivityConstants.SECURITY_TALK_KIDS%>'){
		document.getElementById('participantSection').style.display = 'block';
	}else{
		document.getElementById('participantSection').style.display = 'none';
	}
}

<%	if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA})) {
%>		function observationsDefault(type){
<%		if(sys.isCorporation(CorporationData.JJ)) {	
%>			if (type == '<%=CEPAActivityConstants.SECURITY_TALK_KIDS%>'){
				document.getElementById('participantSection').style.display = 'block';
			}else{
				document.getElementById('participantSection').style.display = 'none';
			}
<%		}
		if(sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA})) {
%>			if (type == '<%=CEPAActivityConstants.SECURITY_TALK%>'){
				document.getElementById('themeSection').style.display = 'block';
			}else{
				document.getElementById('themeSection').style.display = 'none';
			}
<%		}
%>
		}
<%	}
	%>

</script> 


	<body bgcolor="#FFFFFF">
		<html:form action="Activityupdate" method="post" enctype="multipart/form-data">
			<html:hidden property="id"/>
			<table width="750" border="0" cellspacing="0" cellpadding="0" align="center">
 				<tr class="guide1">
					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("web.activity.editing")%>' />
  				</tr>
				<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
  				</tr>
  		  	  <tr>
	    <td valign="top" width="49%">	
	    <table width="100%" cellpadding="0" cellspacing="0">
	    	<tr><td>
    
	<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
       	 <table width="100%" cellpadding="0" cellspacing="0">
          <%			
			String day = "";
			String month = "";
			String year = "";
			if (activityData.getDate() != null) { 
				Calendar date = Calendar.getInstance();
				date.setTime(activityData.getDate());
				day = String.valueOf(date.get(Calendar.DATE));
				month = String.valueOf(date.get(Calendar.MONTH)+1);
				year = String.valueOf(date.get(Calendar.YEAR));   			
			}	
		%>                
          
          <tr class="texts" >
            <th align="left"> <%=messages.getText("generic.activity.activitydata.date")%><span class="required">*</span></th>
            <td align="right">
            	<html:text property="day" style="width:20;" styleClass="input" value="<%=day %>" styleId="dayId1"/>
              	&nbsp;/&nbsp;
              	<html:text property="month" style="width:20;" styleClass="input"  value="<%=month %>" styleId="monthId1"/>
              	&nbsp;/&nbsp;
              	<html:text property="year" style="width:40;" styleClass="input" value="<%=year %>" styleId="yearId1"/>
              	<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     				onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			</td>
			<script type="text/javascript"  >
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

<%
		  // Se puede modificar el tipo de la activiad si es nueva y no tiene resultados ingresados
          if (!activityData.getIsOld() && !activityReportFacade.existsAnyResultForActivity(new Integer(idActivity),userSessionData)) {
%>        	  
		  <tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
          <tr class="texts">
            <th align="left" > <%=messages.getText("generic.activity.activitydata.type")%><span class="required">*</span></th>
            <td align="right">
                <%			
			
			Collection col=new LinkedList();
            if (userSessionData.checkPermission(SecurityConstants.MODIFY_CD_ACTIVITY))
            {
            	
				if (sys.isCorporation(CorporationData.MONSANTO)) {
					col.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydrive"),"datatype.scheduledactivitytype.commentarydrive"));
				}
				else {
					col.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydriveother"),"datatype.scheduledactivitytype.commentarydriveother"));
				}
            }
//          else
//          {
    		if (userSessionData.checkPermission(SecurityConstants.EDIT_CEPASCHEDULEACTIVITY) ) {
				Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData).iterator();
				while(iter.hasNext()){
	            	GeneralDTData itemType=(GeneralDTData)iter.next();
					if(!(sys.isForestalUnified() && CEPAActivityConstants.isForestalCommonActivity(itemType.getResourceBundleId()))) {
						if((itemType.getResourceBundleId().equals(CEPAActivityConstants.PREVENTIVO_MEDIANOS_PRACTICO) || itemType.getResourceBundleId().equals(CEPAActivityConstants.PREVENTIVO_MEDIANOS_TEORIA)) && 
								sys.isCorporation(CorporationData.ORICA)){ 
							continue;//No se permite crear cursos de medianos para ORICA
						}
						// Commentary Drive segun Coproracion
		                if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVE)) {
							if (sys.isCorporation(CorporationData.MONSANTO)) {
								// Si es Monsanto muestro Commentary Drive                                                       
								if (userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
									col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
								}
		                  	}
		             	}else if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK)) {
							// Commentary High Risk
							if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
								col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
		                   	}
		             	}else if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVE_JJ2)) {
							if (!sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.JJ})) {
								// Commentary Drive Others                                                   
								if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
									col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
								}
		                   	}
		             	} else if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVE_RENOVATION)
		             			|| itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION)) {
							// Si no es monsanto muestro el Commentary Drive de Others
							if (!sys.isCorporation(new int[]{CorporationData.MONSANTO})) {
								// Commentary Drive Others                                                   
								if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
									col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
								}
		                   	}
		             	}else if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVEOTHERSPECIAL)
		             			|| itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER)
		             			|| itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVE_NOVARTIS)) {
							// Si no es monsanto muestro el Commentary Drive de Others
							if (!sys.isCorporation(CorporationData.MONSANTO)) {
								// Commentary Drive Others               
								if(sys.isCorporation(CorporationData.JJ)) {
									if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY) && !userSessionData.isGerente()) {
										col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
									}
								}else {
									if(userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
										col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
									}
								}
		                   	}
		             	} else if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.BTWTHAILAND)) {  // BTW Thailand solo para Monsanto:
							if (sys.isCorporation(CorporationData.MONSANTO)) {
								if(userSessionData.checkPermission(SecurityConstants.CREATE_CEPASCHEDULEACTIVITY)) {
									col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
								}
		                   	}
		               	} else if (sys.getUserSessionData().isForest() &&  // Para forestales las siguientes actividades no se pueden crear, son solo para mostrar actividades migradas:
		               	        (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.SEGUIMIENTOENRUTA) ||
								itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.GABINETEPSICOTECNICO) || 
								itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.PESADOS_1))){
						} else {
		                  	if (userSessionData.checkPermission(SecurityConstants.CREATE_CEPASCHEDULEACTIVITY)) { // Las demï¿½s Actividades las muestro para todas las empresas
								col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
		                    }      
		             	}
					}
	      		}
            }            
			// session.setAttribute("typecepaactivityscedule",col);	
			request.setAttribute("typecepaactivityscedule",col);
%>
                <html:select property="type" styleClass="input" style="width:155;" onchange="observationsDefault(document.forms['ActivityForm'].type.value);" >
                  <html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>
                </html:select>
            </td>
          </tr>          
 <%
          }
 %>         
      
		  <tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
          <tr class="texts">
            <th align="left"> <%=messages.getText("generic.activity.activitydata.city")%><span class="required">*</span></th>
            <td align="right"><html:text property="city" style="width:155;" styleClass="input"/>
            </td> 
          </tr>		
			<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
        	<tr class="texts">
    			<th align="left">
           		 	<%=messages.getText("generic.activity.activitydata.duration")%>
           		</th>
       			<td align="right">
    				<html:text property="durHour" style="width:40;" styleClass="input" />&nbsp;:&nbsp;
    				<html:text property="durMinute" style="width:40;" styleClass="input" />
          		</td>
    		</tr>                                       
		  <tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
          <tr class="texts">
            <th align="left" > <%=messages.getText("generic.activity.activitydata.state")%><span class="required">*</span></th>
            <td align="right">
                <%			
			
               // System.out.println("STATE:" + activityForm.getState());
			Collection colState = new LinkedList();
            if (userSessionData.isInstructor())
            {
               	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.prevista"),"datatype.scheduledactivitystate.prevista"));
               	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.ensupervision"),"datatype.scheduledactivitystate.ensupervision"));
            }
            else  
            {	
        		if (activityData.getDefinition().getResourceKeyName().equals(CEPAActivityConstants.CEPA_SYSTEM_COURSE_2_ENTRENADORES)) {
               		colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.prevista"),"datatype.scheduledactivitystate.prevista"));            		
                   	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.dictada"),"datatype.scheduledactivitystate.dictada"));
        		}else {
            		if (activityData.getState().getResourceBundleId().equals(CEPAActivityConstants.PREVISTA)) {
                   		colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.prevista"),"datatype.scheduledactivitystate.prevista"));            		
                   		colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.ensupervision"),"datatype.scheduledactivitystate.ensupervision"));
	            	}
	            	if (activityData.getState().getResourceBundleId().equals(CEPAActivityConstants.ENSUPERVISION)) {
	                   	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.prevista"),"datatype.scheduledactivitystate.prevista"));					
	                   	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.ensupervision"),"datatype.scheduledactivitystate.ensupervision"));            		                   	
	                   	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.dictada"),"datatype.scheduledactivitystate.dictada"));            		
	            	}	
	            	if (activityData.getState().getResourceBundleId().equals(CEPAActivityConstants.DICTADA)) {
	                   	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.ensupervision"),"datatype.scheduledactivitystate.ensupervision"));            		
	                   	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.dictada"),"datatype.scheduledactivitystate.dictada"));            		                   	
	            	}
        		}	
            }    
			// session.setAttribute("states",colState);	  
			request.setAttribute("states",colState);
%>
                <html:select property="state" styleClass="input" style="width:155;"  >
                  <html:options collection="states" property="value" labelProperty="label" styleClass="input"/>
                </html:select>
            </td>
          </tr>          
          <tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
<%	
		String participantDisplay = (activityData.getType().getResourceBundleId().equals(CEPAActivityConstants.SECURITY_TALK_KIDS)) ? "block" : "none"; 
%>          
          <tr>
          	<td width="100%" align="left" colspan="2">
            	<div id="participantSection" style='display: <%=participantDisplay%>;'>
            		<table style="width: 100%" border="0">
                		<tr>
  		                   	<td width="41%" class="texts">
                       			<%=messages.getText("generic.activity.activitydata.participantcount")%>
                      		</td>
                      		<td width="59%"  align="right">
								<html:text property="participants" style="width:35;" value = '<%=(activityData.getParticipants() == null) ? "" : activityData.getParticipants().toString()%>' styleClass="input" />
                			</td>
                		</tr>
                 	</table>
                   </div>
          		</td>                        		                    
         	</tr> 
<%					if(sys.isCorporation(CorporationData.DOW)) {
%>                		                        <tr>
													    <td width="100%" align="left" colspan="2">
												    		<table style="width: 100%">
												    			<tr>
												                    <td width="41%" class="texts" align="left">
												             			<%=messages.getText("user.data.vehicle")%>
												            		</td>
												            		<td width="59%"  align="right">
																		<html:select property="theme" styleClass="input">
																			<html:option value="datatype.vehicletype.automobile" styleClass="input">Automóvel</html:option>
																			<html:option value="datatype.vehicletype.4x4" styleClass="input">4x4</html:option>
																			<html:option value="datatype.vehicletype.other" styleClass="input">Outros</html:option>
																		</html:select>
																	</td>
																</tr>
												    		</table>
													    </td>                        		                    
													</tr> 
<%					}else {
						String themeDisplay = (sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA}) && activityData.getType().getResourceBundleId().equals(CEPAActivityConstants.SECURITY_TALK)) ? "block" : "none"; 
%>          
											          <tr>
											          	<td width="100%" align="left" colspan="2">
											            	<div id="participantSection" style='display: <%=themeDisplay%>;'>
											            		<table style="width: 100%" border="0">
											                		<tr>
											  		                   	<td width="41%" class="texts">
											                       			<%=messages.getText("generic.activity.activitydata.theme")%>
											                      		</td>
											                      		<td width="59%"  align="right">
																			<html:text property="theme" style="width:135;" value = '<%=(activityData.getTheme() == null) ? "" : activityData.getTheme()%>' styleClass="input" />
											                			</td>
											                		</tr>
											                 	</table>
											                   </div>
											          		</td>                        		                    
											         	</tr> 
<%					}
%>         					</table>
 							<jsp:include  page="../../component/utilityboxbottom.jsp" />
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<jsp:include  page="../../component/spaceline.jsp" />
								</tr>
								<jsp:include page='<%="../../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ActivityForm&consult=false"%>' />
							</table>
						</td>
					</tr>
				</table>
			</td>
			<td width="2%" valign="top">&nbsp;</td>
    		<td valign="top" width="49%">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<jsp:include page='<%="../../util/divorg.jsp?form=ActivityForm&consult=false" %>'/>
				</table>
			</td>
  		</tr>
  
  		<!-- Observaciones de la actividad -->
  		<% if (sys.getCorporationId() == CorporationData.JJ){ 
  					// Por ahora para J&J, pero se puede habilitar para todas las empresas
  					// si se requiere.
  		%>
  		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.activityobservations")+"*"%>' />
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<html:textarea property="observations" cols="70" />
						</td>
					</tr>
				</table>
				<jsp:include  page="../../component/utilityboxbottom.jsp" />
			</td>
		</tr>
		<%
  			}
		%>
		
		<tr>
			<jsp:include  page="../../component/spaceline.jsp" />
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.file")%>' />
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<html:file property="fileUpload"/></td>
						</td>
						<td>
<%				if(activityData.getFile() != null && activityData.getFile().trim().length() > 0) {
%>							<a href="ServletActivityFileDownload?idActivity=<%=idActivity%>" class="texts">
								<%=messages.getText("generic.supportmaterial.reports.file") %>
							</a>
<%				}
%>						</td>
					</tr>
				</table>
				<jsp:include  page="../../component/utilityboxbottom.jsp" />
			</td>
		</tr>
  		<!-- Comentario de la actividad -->
		<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")+"*"%>' />
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<html:textarea property="comment" cols="70" />
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
		  	<td height="18" colspan="3" valign="top">
			<div align="right">
			 <html:submit styleClass="input"> <%=messages.getText("generic.messages.save")%> </html:submit>
		     <html:reset styleClass="input"> <%=messages.getText("generic.messages.reset")%> </html:reset>
			</div>    
			</td>
		</tr>
	</table>
	</html:form>   
</body>

<%		}else { %>	
		<jsp:include  page='<%="/util/allowedData.jsp?message=activity.error.access.activity" %>'/>
<%		}
		}
%>
</html:html>
