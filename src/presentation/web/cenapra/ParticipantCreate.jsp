<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.security.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.cenapra.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>


<%@page import="communication.util.ElementData"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>
<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; 
	SystemAccess sys; 
	DataTypeReportFacade dataType;
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_PARTICIPANT);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

	    dataType = sys.getDataTypeReportFacade();
	    
		session.setAttribute("url","cenapra/ParticipantCreate.jsp");

		String type = request.getParameter("type");
		
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		messages=sys.getText();

		if(session.getAttribute("target")==null){
			session.setAttribute("target",request.getParameter("target"));
		}
		
		Collection capacitation = new LinkedList();
		capacitation.add(new OptionItem(messages.getText("generic.messages.no"),"false"));
		capacitation.add(new OptionItem(messages.getText("generic.messages.yes"),"true"));
		session.setAttribute("capacitation",capacitation);

		Collection groupElements = sys.getUserSessionData().getFilter().getGroupLines();
		Hashtable[] elements = communication.administration.corporation.util.Util.getDivOrgMap(groupElements,sys.getUserSessionData().getFilter().getDivOrgCount());
		Collection items1 = new LinkedList();
	    for(int i = 0; i < sys.getUserSessionData().getDivOrgCount(); i++) {
	        Hashtable items = elements[i*3];
			items1.add(new OptionItem(messages.getText("generic.messages.select"),""));						            
			if(items != null) {			
		        Enumeration keys = items.keys();
			    while(keys.hasMoreElements()) {
	        	    Integer countryId = (Integer)keys.nextElement();
	            	Iterator it1 = ((Collection)items.get(countryId)).iterator();
	        		while(it1.hasNext()) {
    					ElementData element = (ElementData)it1.next();
						items1.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
					}
			    }
			}
		}
		Collections.sort((List)items1);
		session.setAttribute("divOrg11", items1);
		
		
		String action = "";
		String id = "";
		String formName = "";
	    if(type.equals("create")) {
			action = "/ParticipantCreate";
			formName = "ParticipantCreateForm";
			if(session.getAttribute("ParticipantCreateForm") == null) {
			    session.setAttribute("ParticipantCreateForm",new ParticipantForm());
		    }
	    }else {
			action = "/ParticipantUpdate";
			formName = "ParticipantUpdateForm";
			if(!Util.empty(request.getParameter("id"))) {
			    id = request.getParameter("id");
			    session.setAttribute("ParticipantUpdateForm",new ParticipantForm(sys.getCENAPRAReportFacade().findParticipantByPrimaryKey(new Integer(id),sys.getUserSessionData())));
		    }else {
				if(session.getAttribute("ParticipantUpdateForm") != null) {
			        id = ((ParticipantForm)session.getAttribute("ParticipantUpdateForm")).getId();
				}
		    }
		}
		
%>

	<head>
		<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
		<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
		</script>
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
		</script>
	</head>
	<body bgcolor="#FFFFFF">
		<html:form action='<%=action%>'>
<%	    if(type.equals("create")) {
%>			<html:hidden property="id" value='<%=id%>'/>
<%		}
%>			<table width="700" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("cenapra.messages.addparticipant")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.personaldata")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("cenapra.data.firstname")%><span class="required">*</span></th>
			         						<td align="right"><html:text property="firstName" size="40" styleClass="input"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr class="linetwo">
			        						<th align="left"><%=messages.getText("cenapra.data.lastname")%><span class="required">*</span></th>
						        			<td align="right"><html:text property="lastName" size="40" styleClass="input"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText("cenapra.data.birthdate")%><span class="required">*</span></th>
			        						<td align="right" valign="middle">
		            							<html:text property="birthDate" style="width:20;" styleClass="input" styleId="dayId1"/>&nbsp;/&nbsp;
		            							<html:text property="birthMonth" style="width:20;" styleClass="input" styleId="monthId1"/>&nbsp;/&nbsp;
		            							<html:text property="birthYear" style="width:40;" styleClass="input" styleId="yearId1"/>
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
			      						<tr  class="linetwo">
			        						<td align="right" colspan="2">
			        							<%="( "+messages.getText("generic.supportmaterial.reports.format")+": dd/mm/aaaa )"%>
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
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.populationgroup")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%		if(sys.getCorporationId() == CorporationData.CENAPRA) {
			Iterator it = dataType.findAll(DataTypeConstants.POPULATION_GROUP,sys.getUserSessionData()).iterator();
			while(it.hasNext()) {
			    PopulationGroupDataType group = (PopulationGroupDataType)it.next();
%>			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText(group.getResourceBundleId())%></th>
			         						<td align="right"><html:radio property="group" value='<%=group.getResourceBundleId()%>' styleClass="input"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}
		}else {
			String[] data = {"datatype.populationgroup.education","datatype.populationgroup.general","datatype.populationgroup.health",
					"datatype.populationgroup.ongs","datatype.populationgroup.security","datatype.populationgroup.youth"};
			for(int i = 0; i < data.length; i++) {
%>			      						<tr  class="linetwo">
											<th align="left"> <%=messages.getText(data[i])%></th>
											<td align="right"><html:radio property="group" value='<%=data[i]%>' styleClass="input"/></td>
										</tr>
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}
%>										<tr class="linetwo">
											<th align="left"> <%=messages.getText("cenapra.data.otherprofesion")%></th>
										</tr>
										<tr class="linetwo">
											<td align="left" colspan="5"><html:textarea property="othergroup" cols="60" rows="3"/></td>
										</tr>
<%		}
%>
				   					</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
			      				</td>
			      			</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
							<tr>
								<td width="100%" valign="top">
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.contactdata")%>' />
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%			if(sys.getCorporationId() == CorporationData.CENAPRA) {	
%>											<tr class="linetwo">
				        						<th align="left"> <%=messages.getText("cenapra.data.state")%></th>
         										<td align="right"><html:text property="state" size="40" styleClass="input"/></td>
         									</tr>
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.delegation")%></th>
         										<td align="right"><html:text property="delegation" size="40" styleClass="input"/></td>
         									</tr>
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}	
%>											<tr class="linetwo">
												<th align="left"> <%=messages.getText("cenapra.data.city")%></th>
         										<td align="right"><html:text property="city" size="40" styleClass="input"/></td>
											</tr>
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			if(sys.getCorporationId() == CorporationData.CENAPRA) {	
%>											<tr class="linetwo">
				        						<th align="left"> <%=messages.getText("cenapra.data.postalcode")%></th>
				         						<td align="right"><html:text property="postalcode" size="40" styleClass="input"/></td>
											</tr>
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}	
%>											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.address")%></th>
         										<td align="right" colspan="4"><html:text property="address" size="40" styleClass="input"/></td>
				         					</tr>	
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.phone")%></th>
				         						<td align="right"><html:text property="phone" size="40" styleClass="input"/></td>
				         					</tr>
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
				        						<th align="left"> <%=messages.getText("cenapra.data.movile")%></th>
         										<td align="right"><html:text property="movile" size="40" styleClass="input"/></td>
											</tr>
				      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
											<tr class="linetwo">
        										<th align="left"> <%=messages.getText("cenapra.data.email")%></th>
				         						<td align="right" colspan="4"><html:text property="email" size="40" styleClass="input"/></td>
         									</tr>
										</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="middle">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
                    		<tr>
                     			<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<tr>
                         					<td valign="top"> 
                         						<jsp:include page='<%="divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form="+formName+"&consult=false"%>' />
                         					</td>
                       					</tr>
	                     			</table>					  
								</td>
							</tr>		
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
							<tr>
                     			<td>
<%		String divorg = "divorg.jsp?form="+formName+"&consult=false";
%>		            				<jsp:include page='<%=divorg%>' />
								</td>
							</tr>
							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
							</tr>
                    		<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.participant.actionorganization")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%		if(sys.getCorporationId() == CorporationData.CENAPRA) {
			Iterator it = dataType.findAll(DataTypeConstants.ACTION_ORGANIZATION,sys.getUserSessionData()).iterator();
			while(it.hasNext()) {
			    ActionOrganizationDataType organization = (ActionOrganizationDataType)it.next();
			    if(!Util.empty(messages.getText(organization.getResourceBundleId()))) {
%>			      						<tr  class="linetwo">
			        						<th align="left"> <%=messages.getText(organization.getResourceBundleId())%></th>
			         						<td align="right"><html:radio property="organization" value='<%=organization.getResourceBundleId()%>' styleClass="input"/></td>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%				}
			}
		}else {
			String[] data = {"datatype.actionorganization.firsteducation","datatype.actionorganization.general","datatype.actionorganization.othereducation",
					"datatype.actionorganization.otherong","datatype.actionorganization.privatehealth","datatype.actionorganization.publichealth","datatype.actionorganization.secondeducation"};
			for(int i = 0; i < data.length; i++) {
%>			      						<tr  class="linetwo">
											<th align="left"> <%=messages.getText(data[i])%></th>
											<td align="right"><html:radio property="organization" value='<%=data[i]%>' styleClass="input"/></td>
										</tr>
										<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}
%>										<tr class="linetwo">
											<th align="left"> <%=messages.getText("cenapra.data.otherprofesion")%></th>
										</tr>
										<tr class="linetwo">
											<td align="left" colspan="5"><html:textarea property="otherorganization" cols="60" rows="3"/></td>
										</tr>
<%		}
%>			      					</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
			      				</td>
			      			</tr>
						</table>
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
				</tr>
				<tr>
					<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.profesion")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.medical")%></th>
         						<td align="right">
         							<html:checkbox property="medical" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.paramedical")%></th>
         						<td align="right">
         							<html:checkbox property="paramedical" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.nurse")%></th>
         						<td align="right">
         							<html:checkbox property="nurse" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.police")%></th>
         						<td align="right">
         							<html:checkbox property="police" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.vialinspector")%></th>
         						<td align="right">
         							<html:checkbox property="vialinspector" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.teacher")%></th>
         						<td align="right">
         							<html:checkbox property="teacher" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.profesion.prevent")%></th>
         						<td align="right">
         							<html:checkbox property="prevent" styleClass="input"/>
         						</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.otherprofesion")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5"><html:textarea property="otherprofesion" cols="60" rows="3"/></td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.education")%>' />
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
<%			Iterator it = dataType.findAll(DataTypeConstants.EDUCATION,sys.getUserSessionData()).iterator();
			while(it.hasNext()) {
			    EducationDataType education = (EducationDataType)it.next();
%>		     					<tr  class="linetwo">
			        				<th align="left"> <%=messages.getText(education.getResourceBundleId())%></th>
	         						<td align="right"><html:radio property="education" value='<%=education.getResourceBundleId()%>' styleClass="input"/></td>
	      						</tr>
	      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
<%			}
%>							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.othereducation")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5"><html:textarea property="othereducation" cols="60" rows="3"/></td>
         					</tr>
	     					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
				</tr>
				<tr>
					<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.vialexperience")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.vialsecurity")%></th>
         						<td align="right">
						      		<html:select property="capacitation" styleClass="input" style="width:50;" >
										<html:options collection="capacitation" property="value" labelProperty="label"/>
							  		</html:select>   
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.capacitation")%></th>
         						<td align="right">
						      		<html:select property="capacitationtrainning" styleClass="input" style="width:50;" >
										<html:options collection="capacitation" property="value" labelProperty="label"/>
							  		</html:select>   
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.capacitationtype")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5"><html:textarea property="capacitationtype" cols="60" rows="3"/></td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.teachexperience")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.hasteachexperience")%></th>
         						<td align="right">
						      		<html:select property="teachexperience" styleClass="input" style="width:50;" >
										<html:options collection="capacitation" property="value" labelProperty="label"/>
							  		</html:select>   
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.teachfirst")%></th>
         						<td align="right">
						      		<html:select property="first" styleClass="input" style="width:50;" >
										<html:options collection="capacitation" property="value" labelProperty="label"/>
							  		</html:select>   
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.teachsecond")%></th>
         						<td align="right">
						      		<html:select property="second" styleClass="input" style="width:50;" >
										<html:options collection="capacitation" property="value" labelProperty="label"/>
							  		</html:select>   
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.teachthird")%></th>
         						<td align="right">
						      		<html:select property="third" styleClass="input" style="width:50;" >
										<html:options collection="capacitation" property="value" labelProperty="label"/>
							  		</html:select>   
								</td>
         					</tr>
							<tr class="linetwo">
        						<th align="left"> <%=messages.getText("cenapra.data.otherteach")%></th>
         					</tr>
							<tr class="linetwo">
         						<td align="left" colspan="5"><html:textarea property="otherteach" cols="60" rows="3"/></td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
				<tr><jsp:include  page="../component/spaceline.jsp" /></tr>
				<tr>
					<td colspan="3" width="100%" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.observations")%>' />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
							<tr>
         						<td>
         							<html:textarea property="observations" cols="120" rows="3"/>
         						</td>
         					</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
				<tr><jsp:include  page="../component/spaceline.jsp" /></tr>
              	<tr>
                	<td width="100%" colspan="3">
                    	<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />
                      	<table width="100%" cellpadding="0" cellspacing="0">
                        	<tr>
                           		<td>
                               		<html:textarea property="comment" cols="120" rows="3" />
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
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
							<html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
					 		<html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel >
				  		</div>          
				  	</td>
			  	</tr>
		  	</table>
		</html:form>
	</body>
<%	}	%>
</html:html>
