<%@page language="java"
    import="java.util.*"
    import="business.*"
    import="business.datatype.*"
    import="business.activity.*"
    import="business.administration.user.*"
    import="communication.language.*"
    import="communication.datatype.*"
    import="communication.datatype.tables.*"
    import="communication.security.*"
    import="communication.activity.tables.CEPAActivityConstants"
    import="communication.administration.user.tables.*"
    import="communication.administration.user.*"    
    import="communication.driver.tables.*"
    import="communication.administration.corporation.tables.*"
    import="communication.activity.tables.*"
    import="presentation.translator.web.util.*"
    import="presentation.translator.web.activity.*"
    import="presentation.translator.web.activity.schedule.*"
    import="communication.administration.RoleData"
    errorPage="../../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
    Text messages; SystemAccess sys; 
    UserSessionData userSessionData;
    RequestDispatcher dispatcher;
    String context;
    ActivityForm activityForm = null;
    String cityJavascript = "";
    String participantsJavascript = "";
    String themeJavascript = "";
    String typeJavascript="";
    String diaJavascript="";
    String mesJavascript="";
    String anioJavascript="";    
    String hourJavascript="";
    String minJavascript="";    
    String viewAsterisco;    
    String url;
   	int corporationId;
%>
<%
   
    sys=(SystemAccess)session.getAttribute("SystemAccess");
    userSessionData=sys.getUserSessionData();
    String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITY);
    String check1 = Util.checkPermission(sys,SecurityConstants.CREATE_CD_ACTIVITY);
   
    if ((check!=null) && (check1!=null))  {
        response.sendRedirect(request.getContextPath()+check);
    }else {
   		corporationId = userSessionData.getFilter().getCorporationId().intValue();
       
        // session.setAttribute("url","activity/schedule/CEPAActivityScheduleCreate.jsp");           
       	userSessionData.setUrl("activity/schedule/CEPAActivityScheduleCreate.jsp");
       	
        context=request.getContextPath();

        // hace el include de la pagina que despliega el box que indica el resultado de la operaciï¿½n
        // el texto que se despliega en el box es el guardado en la variable de sesiï¿½n bajo la key Msg       
        dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
        dispatcher.include(request,response);

        messages=sys.getText();       

        /*
        if(session.getAttribute("target")==null){
            session.setAttribute("target",request.getParameter("target"));
        } 
        */
       
        if(session.getAttribute("ActivityForm")==null) {
            activityForm = new ActivityForm();
            session.setAttribute("ActivityForm",activityForm);
        } else {
            activityForm = (ActivityForm) session.getAttribute("ActivityForm");
        }   

        if(request.getParameter("city") !=null) {
            cityJavascript = request.getParameter("city");           
           //  session.setAttribute("cityJavascript",request.getParameter("city"));
        } else {
        	cityJavascript = "";
        }	
        if(request.getParameter("participants") !=null) {
        	participantsJavascript = request.getParameter("participants");           
        } else {
        	participantsJavascript = "";
        }	
        if(request.getParameter("theme") !=null) {
        	themeJavascript = request.getParameter("theme");           
        } else {
        	themeJavascript = "";
        }	
       
        if(request.getParameter("type") !=null) {
            typeJavascript = request.getParameter("type");
            // session.setAttribute("typeJavascript",request.getParameter("type"));
        } else {
        	typeJavascript = "";
        }	
       
        if(request.getParameter("dia") !=null) {
            diaJavascript = request.getParameter("dia");
            // session.setAttribute("diaJavascript",request.getParameter("dia"));
        } else {
        	diaJavascript = "";
        }	        
       
        if(request.getParameter("mes") !=null) {
            mesJavascript = request.getParameter("mes");
            // session.setAttribute("mesJavascript",request.getParameter("mes"));
        } else {
        	mesJavascript = "";
        }	
       
        anioJavascript = (request.getParameter("anio") !=null)  ? request.getParameter("anio") : "";
        hourJavascript = (request.getParameter("durHour") !=null)  ? request.getParameter("durHour") : "";
        minJavascript = (request.getParameter("durMinute") !=null)  ? request.getParameter("durMinute") : "";
        
		System.out.println(cityJavascript);
       
		if(!userSessionData.getRole().getName().equals(RoleData.CDS)) {
			List col= new LinkedList();
			if(userSessionData.checkPermission(SecurityConstants.CREATE_CEPASCHEDULEACTIVITY) || userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY)) {
				Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData).iterator();
				while(iter.hasNext()){
	            	GeneralDTData itemType=(GeneralDTData)iter.next();
					if(!(sys.isForestalUnified() && CEPAActivityConstants.isForestalCommonActivity(itemType.getResourceBundleId()))) {
						if((itemType.getResourceBundleId().equals(CEPAActivityConstants.PREVENTIVO_MEDIANOS_PRACTICO) || itemType.getResourceBundleId().equals(CEPAActivityConstants.PREVENTIVO_MEDIANOS_TEORIA)) && 
								corporationId == CorporationData.ORICA){ 
							continue;//No se permite crear cursos de medianos para ORICA
						}
						// Commentary Drive segun Coproracion
		                if (itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.COMMENTARYDRIVE)) {
							if (corporationId == CorporationData.MONSANTO) {
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
		             			|| itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.INITIAL_FOREIGN_EVALUATION)
		             			|| itemType.getResourceBundleId().trim().equals(CEPAActivityConstants.SAFE_FLEET_MULTIPLIER_SPEECH)) {
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
							if (corporationId != CorporationData.MONSANTO) {
								// Commentary Drive Others               
								if(corporationId == CorporationData.JJ) {
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
							if (corporationId == CorporationData.MONSANTO) {
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
			Collections.sort(col);
	        // session.setAttribute("typecepaactivityscedule",col);   
			request.setAttribute("typecepaactivityscedule",col);
		}
		
		Collection colState = new LinkedList();
        colState.add(new OptionItem(messages.getText(CEPAActivityConstants.PREVISTA),CEPAActivityConstants.PREVISTA));                                         
        // session.setAttribute("statecepaactivityscedule",colState);   
        request.setAttribute("statecepaactivityscedule",colState);

%>



<script language="JavaScript" type="text/javascript">
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

function setCommentary(type,form,form1) {

    if (type == '<%= CEPAActivityConstants.COMMENTARYDRIVE %>' || 
    	    type == '<%= CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK %>' ||
    	    type == '<%= CEPAActivityConstants.COMMENTARYDRIVEARGENTINA %>') {
        form.commentary.value = true;
    }
   
    form.city.value = form1.city.value;
    form.type.value = form1.type.value;   
   
    form.dia.value = form1.day.value;   
    form.mes.value = form1.month.value;   
    form.anio.value = form1.year.value;           
   
    form.submit();   

}

<%	if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA})) {
%>		function observationsDefault(type){
<%		if(sys.isCorporation(CorporationData.JJ)) {	
%>			if (type == '<%=CEPAActivityConstants.INTERNAL_SECURITY_TALK%>'){
				document.forms['ActivityForm'].observations.value = '<%= messages.getText("general.activity.internalsecuritytalk.defaultobservations")%>';
			}else{
				document.forms['ActivityForm'].observations.value = '';
			}
			if (type == '<%=CEPAActivityConstants.SECURITY_TALK_KIDS%>'){
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


function viewAsterisco(type,asteriscoGeo,asteriscoDiv) {

			var asterGeo1 = asteriscoGeo + "1";
			var asterGeo2 = asteriscoGeo + "2";
			
			var asterDiv11 = asteriscoDiv + "11";
			
			var asterDiv21 = asteriscoDiv + "21";			
			var asterDiv22 = asteriscoDiv + "22";						
			var asterDiv23 = asteriscoDiv + "23";									
				
			var asterDiv31 = asteriscoDiv + "31";										
			var asterDiv32 = asteriscoDiv + "32";													
			
			if(type == '<%= CEPAActivityConstants.COMMENTARYDRIVE %>' ||
					type == '<%= CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK %>' ||
					type == '<%= CEPAActivityConstants.COMMENTARYDRIVEARGENTINA %>' ) {
				document.getElementById(asterGeo1).style.display = 'none';
				document.getElementById(asterGeo2).style.display = 'none';
				
				document.getElementById(asterDiv11).style.display = 'none';
				document.getElementById(asterDiv21).style.display = 'none';
				document.getElementById(asterDiv22).style.display = 'none';
				document.getElementById(asterDiv23).style.display = 'none';
				document.getElementById(asterDiv31).style.display = 'none';
				document.getElementById(asterDiv32).style.display = 'none';																				

			}else {
				document.getElementById(asterGeo1).style.display = 'block';
				document.getElementById(asterGeo2).style.display = 'block';				
				
				document.getElementById(asterDiv11).style.display = 'block';
				document.getElementById(asterDiv21).style.display = 'block';
				document.getElementById(asterDiv22).style.display = 'block';
				document.getElementById(asterDiv23).style.display = 'block';
				document.getElementById(asterDiv31).style.display = 'block';
				document.getElementById(asterDiv32).style.display = 'block';
			}

}

</script>


	<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<form action="layout.jsp?refer=/activity/schedule/CEPAActivityScheduleCreate.jsp" name='FormRedibujar' method="post">
	    <input type="hidden" name="commentary" value ="false" />    
	    <input type="hidden" name="type" value =""/>    
	    <input type="hidden" name="city" value =""/>    
	    <input type="hidden" name="dia" value =""/> 
	    <input type="hidden" name="mes" value =""/>    
	    <input type="hidden" name="anio" value =""/>                
	</form>
	<html:form action="Activitycreate" method="post">
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr class="guide1">
            	<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("generic.messages.addcepaactivityschedule")%>' />
	    	</tr>
    	    <tr>
        	    <jsp:include  page="../../component/spaceline.jsp" />
	        </tr>
    	    <tr>
        	    <td valign="top">           
            	 	<table width="100%" cellpadding="0" cellspacing="0" border="0">             
                		<tr>
                 			<td valign="top">
                    			<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
                        		<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        			<tr>
                            			<td width="100%" align="right" valign="top">
                                    		<table width="100%" height="20" border="0" cellpadding="0" cellspacing="1">
                                       			<tr>
                                            		<td align="left" class="texts">
                                                		<%=messages.getText("generic.activity.activitydata.date")%><span class="required">*</span>
                                            		</td>
                                            		<td align="left">
                                                		<html:text property="day" value = '<%= diaJavascript %>' style="width:20;" styleClass="input" maxlength="2" styleId="dayId1"/>&nbsp;/&nbsp;
                                                		<html:text property="month" value = '<%= mesJavascript %>' style="width:20;" styleClass="input" maxlength="2" styleId="monthId1"/>&nbsp;/&nbsp;
                                                		<html:text property="year" value = '<%= anioJavascript %>' style="width:40;" styleClass="input"  maxlength="4" styleId="yearId1"/>
                                                		<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;" onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
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
                                        			<td align="left" class="texts" colspan="2">
			                                            <%=" ("+messages.getText("generic.messages.dateformat")+")"%>
            			                            </td>
                        			      		</tr>
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
                                        		<tr>
                                            		<td  align="left" class="texts">
                                              			<%=messages.getText("generic.activity.activitydata.type")%><span class="required">*</span>      
                                            		</td>
                                            		<td align="left">
<% 													if(userSessionData.getRole().getName().equals(RoleData.CDS)) {
															
															if(sys.getCorporationId() == CorporationData.CEPA) {
%>				            								<input type="hidden" name="type" value='<%=CEPAActivityConstants.CEPA_SYSTEM_COURSE_2_ENTRENADORES%>' />
                            								<%=messages.getText(CEPAActivityConstants.CEPA_SYSTEM_COURSE_2_ENTRENADORES)%>
<%														}else {
%>														<select name="type" class="input" style="width:180;" >
					                            			<option value='<%=CEPAActivityConstants.CEPA_SYSTEM_COURSE_2_ENTRENADORES%>' ><%=messages.getText(CEPAActivityConstants.CEPA_SYSTEM_COURSE_2_ENTRENADORES)%></option>
					                            			<option value='<%=CEPAActivityConstants.COMMENTARYDRIVECAYACO%>' ><%=messages.getText(CEPAActivityConstants.COMMENTARYDRIVECAYACO)%></option>
														</select> 
<%														}
													}else {                                            		
														viewAsterisco = "";
														if(corporationId == CorporationData.MONSANTO) {
															viewAsterisco = "viewAsterisco(document.forms['ActivityForm'].type.value,'asteriscoGeo','asteriscoDiv');";
														}else if (sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA})){
															// Cargo observaciones por defecto en Charlas Internas de Seguridad - J&J
															viewAsterisco = "observationsDefault(document.forms['ActivityForm'].type.value);";
														}
														// Por defecto se muestra esto:
%>
                                            				<html:select property="type" styleClass="input" style="width:155;"  value = '<%=typeJavascript %>' onchange='<%=viewAsterisco %>'>
                                                    				<html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>                                               
                                                			</html:select>
<% 												}
%>	                                      			</td>
                                        		</tr> 
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
		          								<tr class="texts">
        		    								<th align="left">
               											 <%=messages.getText("generic.activity.activitydata.city")%><span class="required">*</span>
               										</th>
           											 <td width="59%" align="right">
        												<html:text property="city" style="width:155;" value = '<%=cityJavascript%>' styleClass="input" />
		            								</td>
        		 								 </tr>                                       
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
		          								<tr class="texts">
        		    								<th align="left">
               											 <%=messages.getText("generic.activity.activitydata.duration")%>
               										</th>
           											 <td width="59%" align="right">
        												<html:text property="durHour" style="width:40;" value = '<%=hourJavascript%>' styleClass="input" />&nbsp;:&nbsp;
        												<html:text property="durMinute" style="width:40;" value = '<%=minJavascript%>' styleClass="input" />
		            								</td>
        		 								 </tr>                                       
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
                		                        <tr>
                        		                    <td width="41%" align="left" class="texts">
                                             			<%=messages.getText("generic.activity.activitydata.state")%><span class="required">*</span>
                                            		</td>
                                            		<td width="59%"  align="left">
                                                		<html:select property="state" styleClass="input" style="width:155;" >
                                                    		<html:options collection="statecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>                                             
                                                		</html:select>                                              
                                          			</td>
                                        		</tr> 
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
                		                        <tr>
                        		                    <td width="100%" align="left" colspan="2">
                        		                    	<div id="participantSection" style="display: none;">
                        		                    		<table style="width: 100%">
                        		                    			<tr>
				                        		                    <td width="41%" class="texts">
				                                             			<%=messages.getText("generic.activity.activitydata.participantcount")%>
				                                            		</td>
				                                            		<td width="59%"  align="left">
				        												<html:text property="participants" style="width:35;" value = '<%=participantsJavascript%>' styleClass="input" />
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
%>                		                        <tr>
                        		                    <td width="100%" align="left" colspan="2">
                        		                    	<div id="themeSection" style="display: none;">
                        		                    		<table style="width: 100%">
                        		                    			<tr>
				                        		                    <td width="41%" class="texts">
				                                             			<%=messages.getText("generic.activity.activitydata.theme")%>
				                                            		</td>
				                                            		<td width="59%"  align="left">
				        												<html:text property="theme" style="width:135;" value = '<%=themeJavascript%>' styleClass="input" />
                                          							</td>
                                          						</tr>
                        		                    		</table>
                        		                    	</div>
                        		                    </td>                        		                    
                                        		</tr> 
<%					}
%>                                       		</table>   
                             			</td>
                          			</tr>
                        		</table>           
                        		<jsp:include page="../../component/utilityboxbottom.jsp"/>   
                			</td>               
    						<td width="16">&nbsp;</td>
    						<td rowspan="3" valign="top">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
            	  					<jsp:include page='<%="divorg.jsp?form=ActivityForm&consult=false" %>'/>           
            	  				</table>
    						</td>
  						</tr>   
   						<tr>
            				<jsp:include  page="../../component/spaceline.jsp" />
    					</tr>
  						<tr>
    						<td valign="top">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
									<jsp:include page='<%="divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ActivityForm&consult=false"%>' />        
								</table>
    						</td>
  						</tr>
  						
  						<!--  Observaciones de la actividad -->
  						<% if (sys.getCorporationId() == CorporationData.JJ){ 
				  					// Por ahora para J&J, pero se puede habilitar para todas las empresas
				  					// si se requiere.
				  		%>
  						<tr>
				    		<jsp:include  page="../../component/spaceline.jsp" />
  						</tr>   
                		<tr>
                    		<td width="100%" colspan="3">
                        		<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.activity.activityobservations")%>' />
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
                		
                		<!-- Comentarios -->
  						<tr>
				    		<jsp:include  page="../../component/spaceline.jsp" />
  						</tr>   
                		<tr>
                    		<td width="100%" colspan="3">
                        		<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />
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
                        	<td height="18" colspan="3" valign="top" align="right">
                       			<html:submit styleClass="input"> <%=messages.getText("generic.messages.save")%> </html:submit>                                                  
                       			<html:cancel styleClass="input"> <%=messages.getText("generic.messages.cancel")%> </html:cancel>                                                  
            		        </td>
                		</tr>                                         
   					</table>
   				</td>
   			</tr>
   		</table>	
	</html:form>  
<%	}   
%>   
