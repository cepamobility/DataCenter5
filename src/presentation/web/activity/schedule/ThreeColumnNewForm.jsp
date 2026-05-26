<%@page import="org.apache.struts.action.DynaActionForm"%>
<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.*"
	import="communication.datatype.*"	
	import="business.activity.ActivityReportFacade"
	import="java.util.*"
	import="java.lang.*"
	import="org.apache.struts.action.DynaActionForm"	
%>

<%!
	UserSessionData userSessionData;
	CEPAActivityScheduleRegistryData registryData;
	CEPAActivityScheduleData activityData;
	Text messages;
	SystemAccess sys;
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	messages = sys.getText();

	String idActivity; String idDriver;
	// Obtengo el formulario de esa actividad para dibujarlo en el HTML:
	idActivity =(String)request.getParameter("idActivity");
	if(idActivity==null){
		idActivity=(String)session.getAttribute("id");
	}
	System.out.println("idActivity "+idActivity);
	
	idDriver =(String)request.getParameter("idDriver");
	if(idDriver == null){
		idDriver = (String)session.getAttribute("idDriver");
	}
	System.out.println("idDriver "+idDriver);
	
	activityData =  (idActivity != null) ? sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(
			new Integer(Integer.parseInt(idActivity)),sys.getUserSessionData()) : null;
	//	(CEPAActivityScheduleData) session.getAttribute("activityData");
	
	session.setAttribute("activityData",activityData);
			
	CheckListData checkList = (activityData != null && activityData.getDefinition()  != null) ?
			activityData.getDefinition().getCheckList() : null;
			
	LinkedList points = (checkList != null && checkList.getPoints() != null)? (LinkedList)checkList.getPoints() : null;
	Collections.sort(points);

	int primerPoint, ultimoPoint, totalPoints;
	
	totalPoints = points.size();
	
    int cantCol = 3; // Integer.parseInt(request.getParameter("cantCol"));
	int pointsByCol = (points != null) ? Math.round(points.size() / cantCol) : 1;
	System.out.println("puntos: " + points.size());
	System.out.println("puntos por columna: " + pointsByCol);
	System.out.println("cantCol: " + cantCol);
	primerPoint = 1;
	
	
	// Si hubo problemas con la validación se recargan los datos ya ingresados
	DynaActionForm activityRegistryForm = (DynaActionForm)session.getAttribute("ActivityRegistryForm");
	String generalObsList = null;
	if (activityRegistryForm != null) {
		generalObsList = (String) activityRegistryForm.get("obs"); // listado de ids de observaciones
	}	
	
	/* Para las observaciones generales */
	CEPAActivityScheduleRegistryData registryData = 
		(CEPAActivityScheduleRegistryData) session.getAttribute("registryData");
	Collection obsGeneralesSelected = null;
	Collection idsObsGeneralesSelected = null;
	
			
	boolean existResult = false;
	if (registryData != null) {
		System.out.println("ThreeColumnNewForm: Existen datos para este driver/actividad...");
		existResult = true;
		obsGeneralesSelected = registryData.getObsSelected();
		if (obsGeneralesSelected != null && obsGeneralesSelected.size() > 0){
			
			System.out.println("ThreeColumnNewForm: tiene OBS Generales...");
			
			Iterator itOGS = obsGeneralesSelected.iterator();
			idsObsGeneralesSelected = new LinkedList();
			while (itOGS.hasNext()){
				CEPAScheduleActivityRegistryObservationSelectedData obsD =
					(CEPAScheduleActivityRegistryObservationSelectedData) itOGS.next();
				if (obsD != null && obsD.getObservation() != null &&
						obsD.getObservation().getIdObservation() != null){
					idsObsGeneralesSelected.add(obsD.getObservation().getIdObservation());
				}
			}
		}else{
			System.out.println("ThreeColumnNewForm: NO tiene OBS Generales...");
		}
	}
	Collection obsGenerales = (activityData != null && activityData.getDefinition() != null) ?
			activityData.getDefinition().getGeneralObservations(): null;
	
	boolean encontreObs = false;
	String checkedStr = "";
	/* Fin para las observaciones generales */
	
	String pageTitle = messages.getText("web.activity.view")+" "+
		messages.getText(activityData.getType().getResourceBundleId())+" "+
		messages.getText(activityData.getCountryName())+" " + 
		Util.formatDate(activityData.getDate());
	
%>

	<input type="hidden" value="<%=totalPoints%>" name="pointCount" id="pointCount" />
	
    <table width="630px" border="0" cellspacing="0" cellpadding="0">	
    	
    	<tr>
   				<jsp:include  page='<%="../../component/titlecomponent.jsp?colspan=5&title=" + pageTitle %>' />			
    	</tr>
  		<tr>
    			<jsp:include  page="../../component/spaceline.jsp" />
		</tr>
    	
   		<tr> 
<%
	for (int col=1; col <= cantCol;col++) { 
		if (col == cantCol) {
			ultimoPoint = totalPoints;
		}
		else {
			ultimoPoint = primerPoint+pointsByCol-1;
		}
		System.out.println("Col:" + col);
		System.out.println("Primer Point:" + primerPoint);
		System.out.println("Ultimo Point:" + ultimoPoint);
		
%>
	        <td width="200" valign="top"> 		
				<jsp:include  page='<%="ImprimoColNewForm.jsp?primerPoint="+ primerPoint +
						"&ultimoPoint=" + ultimoPoint + "&idActivity="+idActivity+"&idDriver="+idDriver%>' />        				
			</td>
<%	
		if (col < cantCol) {
%>		
        	<td valign="top">&nbsp;</td>

 <%		
		}
		primerPoint = col*pointsByCol + 1;
		
	}	
%>		

		</tr>
		
		<!-- 	Observaciones Generales -->
		<% if (obsGenerales != null && obsGenerales.size() > 0){ %>
		<tr>
		 	<td width="100%"  colspan="5" valign="top"> 		
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr> 
    					<td width="9" valign="top">
    						<img src="/datacenter/util/imgs/left_form.gif" >
    					</td>    	
        				<td background="/datacenter/util/imgs/center_form.gif" valign="top"> 
        					<div align="center">
        						<!--  Cambiar esto por un estilo !! -->
        						<font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"><strong>
        						<%=messages.getText("generic.messages.observation")%>
        						</strong></font>
        					</div>
        				</td>
        				<td width="9" valign="top">
        					<img src="/datacenter/util/imgs/right_form.gif" >        	
        				</td>
     				</tr> 
     				
     				<% 
     					for (int j = 1; j <= obsGenerales.size(); j++){
     						Iterator itObs = obsGenerales.iterator();
     						encontreObs = false;
     						CEPAScheduleActivityRegistryObservationData obs = null;
     						// Tengo que fijarme si el orden de la observacion es el correcto:
     						while (!encontreObs && itObs.hasNext())
     						{
     							obs = (CEPAScheduleActivityRegistryObservationData)itObs.next();
     							if (obs != null && obs.getObsOrder() == j) encontreObs = true;
     						}
     						if (!encontreObs) break;
     				%>
     				<!--  Dibujo Observacion General: -->
     				<tr> 
      					<td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;
        				</td>
        				<td>  		
					<%
							// Si esta observacion ya fue seleccionado lo marco.
							checkedStr = "";			
							// Si hubo problemas con la validación se recargan los datos ya ingresados
							if (activityRegistryForm != null ) {
								if (generalObsList != null && 
										generalObsList.contains(obs.getIdObservation().toString())){
									checkedStr = "checked"; 			
								}
							}

							if (existResult) {		
								if (idsObsGeneralesSelected != null &&
										idsObsGeneralesSelected.contains(obs.getIdObservation())){
									checkedStr = "checked";
									// System.out.println("Obs General marcado: "+obs.getIdObservation() + " - "  + obs.getText());
								}else{	
									checkedStr = "";
								}
							}
							String namecheck="obsGeneral" + j;
							
							// El valor de las observaciones generales empieza con og
							// para diferenciar el check de los items y las 
							// observaciones de los puntos
					%>        
        
        	
        				<input type="checkbox" name='<%=namecheck %>'  
        						id="<%=obs.getIdObservation().toString()%>";
              					value='<%="og".concat(obs.getIdObservation().toString()) %>' 
              					class="check" <%=checkedStr%> > 
        			    <!--  Cambiar esto por un estilo !! -->
            			<font size="1" face="Arial, Helvetica, sans-serif"> 
            				<%= messages.getText(obs.getText())%>
            			</font> 
        			</td>
        			<td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;
        			</td>
     			</tr>
				<%
					} // fin for obs						
				%>		
				<!--  Dibujo linea inferior de los cuadrantes -->
			 	 <tr> 
			        <td width="9" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
			        <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
			        <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
			     </tr>
			     <!--  dejo una linea en blanco entre cuadrante y cuadrante -->
			     <tr>
			     	<td>&nbsp;</td>
			     </tr>      
							
				</table> 				
			</td>
    	</tr>
    	<% } // fin si hay observaciones generales para la actividad %>
		
	</table>





