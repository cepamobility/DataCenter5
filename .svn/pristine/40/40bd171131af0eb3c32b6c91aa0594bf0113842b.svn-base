<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.driver.*"	
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"	
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
    import="presentation.translator.web.vehicle.*"	
	import="communication.security.*"    
	import="communication.administration.user.UserSessionData"	
	import="communication.administration.corporation.tables.*"	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>
<%! RequestDispatcher dispatcher; 
	Text messages; String pathLanguage;
	SystemAccess sys; 
	UserSessionData userSessionData;	
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_VEHICLE);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else { 		
		
		pathLanguage="/util/jsp/language.jsp";
		
		String url = "vehicle/createTrailer.jsp";			

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		messages=sys.getText();		
		Integer trailerId;
		boolean edit = (!Util.empty(request.getParameter("type")) && request.getParameter("type").equals("edit"));
		if(!Util.empty(request.getParameter("trailer"))) {
			trailerId = new Integer(request.getParameter("trailer"));
			
		    TrailerData data = sys.getVehicleReportFacade().findTrailer(trailerId,userSessionData);
		    TrailerForm trailer = new TrailerForm(data);
		    if(request.getParameter("vehicle")!=null){
		    	trailer.setVehicleId(request.getParameter("vehicle"));
		    }
		    session.setAttribute("TrailerForm",trailer);
		}else {
			if(session.getAttribute("TrailerForm") == null) {
				if(!Util.empty(request.getParameter("trailer"))) {
					trailerId = new Integer(request.getParameter("trailer"));
					TrailerData data = sys.getVehicleReportFacade().
						findTrailer(trailerId,userSessionData);					   
				    TrailerForm trailer = new TrailerForm(data);
				    if(request.getParameter("vehicle")!=null){
				    	trailer.setVehicleId(request.getParameter("vehicle"));
				    }

				    session.setAttribute("TrailerForm",trailer);
				}else{									   
				    TrailerForm trailer = new TrailerForm();										
					session.setAttribute("TrailerForm",trailer);
				}
			}			
		}
		
		
		
		Collection trade = new LinkedList();
		trade.add(new OptionItem(messages.getText("generic.messages.select"),""));                
		Iterator iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE,
				userSessionData).iterator();
		while(iter.hasNext()) {
			String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
			trade.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		session.setAttribute("trade",trade);
		
		Collection cover = new LinkedList();
        cover.add(new OptionItem(messages.getText("generic.messages.select"),""));        
		iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECOVER,
				userSessionData).iterator();
		while(iter.hasNext()) {
			String value = ((TypeCoverDataType)iter.next()).getResourceBundleId();
			cover.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		session.setAttribute("cub",cover);
		
		Collection typePlatform = new LinkedList();
		typePlatform.add(new OptionItem(messages.getText("generic.messages.select"),""));        
		iter = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPEPLATFORM,
				userSessionData).iterator();
		while(iter.hasNext()) {
			String value = ((PlatformDataType)iter.next()).getResourceBundleId();
			typePlatform.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
		}
		session.setAttribute("typePlatform",typePlatform);
		
		
		String title = (edit) ? "generic.messages.updatetrailer" : "generic.messages.addtrailer";
%>

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
  
function onSelect3(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId3");
  		var input_month = document.getElementById("monthId3");
  		var input_year = document.getElementById("yearId3");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
  
function onSelect4(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId4");
  		var input_month = document.getElementById("monthId4");
  		var input_year = document.getElementById("yearId4");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
</script>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>

<body bgcolor="#FFFFFF">
	<html:form action='<%=(edit) ? "/UpdateTrailer" : "/CreateTrailer" %>' >
		<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
 			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
  			</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
			<tr>
				<td width="292" valign="top">
                   	<table width="100%">
			  			<tr>
			    			<td colspan="3" valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
			        			<table width="100%" cellpadding="0" cellspacing="0">
			          				<tr class="texts" >
			            				<th align="left"> 
			            					<%=messages.getText("generic.vehicle.vehicledata.registrationNumber")%>
			            					<span class="required">*</span>
			            				</th>
			            				<td align="right">
			                				<html:text property="plate" style="width:155" styleClass="input" maxlength="30"/>
			            				</td>
			          				</tr>
									<tr  class="space">
										<th align="right">&nbsp;</th>
										<td align="right">&nbsp;</td>
									</tr>
			          				<tr class="texts">
			            				<th align="left" > 
			            					<%=messages.getText("generic.vehicle.vehicledata.trade")%>
		    	        					<span class="required">*</span>
		        	    				</th>
		            					<td align="right">
			            	    			<html:select property="trade" styleClass="input" style="width:155;">
		                  						<html:options collection="trade" property="value" labelProperty="label" styleClass="input"/>
		                					</html:select>
		            					</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
		    	        				<th width="224" align="left"> <%=messages.getText("generic.vehicle.vehicledata.model")%><span class="required">*</span></th>
		        	    				<td align="right">
		            	    				<html:text property="model" style="width:155" styleClass="input"/>
		            					</td>
		          					</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
			            				<th align="left"> 
			            					<%=messages.getText("generic.vehicle.vehicledata.year")%>
		    	        					<span class="required">*</span>
		        	    				</th>
		            					<td align="right">
		                					<html:text property="year" style="width:155" styleClass="input" maxlength="4"/>
		            					</td>
		          					</tr>
			        			</table>
			      				<jsp:include page="../component/utilityboxbottom.jsp"/>  
							</td>
		  				</tr>
			  			<tr>
							<jsp:include  page="../component/spaceline.jsp" />
			  			</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      					<tr>
                        					<td valign="top"> <jsp:include page='<%="../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=TrailerForm&consult=false"%>' /></td>
                      					</tr>
                     			</table>					  
							</td>
						</tr>		
				  	</table>
			  	</td>
                <td width="16">&nbsp;</td>
               	<td width="292" valign="top">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                   		<jsp:include page='<%="../util/divorg.jsp?form=TrailerForm&consult=false" %>'/>
                     </table>
   	             </td>
	  		</tr>
  			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
			</tr>
			<tr>
				<td width="100%" valign="top" colspan="3">
                   	<table width="100%">
			  			<tr>
			    			<td width="100%" valign="top">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.technicaldata")%>' />
			        			<table width="100%" cellpadding="0" cellspacing="0">
		          					<tr class="texts" >
		            					<th align="left">
		            						<%=messages.getText("generic.vehicle.vehicledata.costCenter")%>
		        	    				</th>
		    	        				<td align="right">
			                				<html:text property="costCenter" style="width:155" styleClass="input" maxlength="15"/>
			            				</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					
		          					<% 
		          					// Para KLABIN: Tipo de Plataforma de Carga
		          					if (sys.getUserSessionData().getFilter().getCorporationId().
		          							equals(new Integer(CorporationData.KLABIN))){
		          						
		          						%>
		          					<tr class="texts" >
		            					<th align="left">
		            						<%=messages.getText("generic.vehicle.vehicledata.typeplatform")%>
		        	    				</th>
		                				<td align="right">
			            					<html:select property="platformType" styleClass="input" style="width:155;">
		                  						<html:options collection="typePlatform" property="value" labelProperty="label" styleClass="input"/>
		                					</html:select>
			            				</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<%} %>
		          					
		          					
		          					<tr class="texts">
		            					<th align="left">
		            						<%=messages.getText("generic.vehicle.vehicledata.permissionNumber")%> 
		        	    				</th>
		    	        				<td align="right">
			            					<html:text property="permissionNumber" style="width:155" styleClass="input" maxlength="30"/>
			            				</td>
			          				</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
            							<th align="left"> 
            								<%=messages.getText("generic.vehicle.vehicledata.permissionExpiryDate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
			            				</th>
            							<td align="right">
			            					<html:text property="permissionExpiryDay" style="width:20;" styleClass="input" maxlength="2"  styleId="dayId1"/>
			              					&nbsp;/&nbsp;
			              					<html:text property="permissionExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId1"/>
			              					&nbsp;/&nbsp;
			              					<html:text property="permissionExpiryYear" style="width:40;" styleClass="input" maxlength="4" styleId="yearId1"/>
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
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
            							<th align="left"> 
            								<%=messages.getText("generic.vehicle.vehicledata.purchaseDate")%><%=" ( "+messages.getText("generic.messages.dateformat")+" )"%>
			            				</th>
            							<td align="right">
					    			        <html:text property="purchaseDay" style="width:20;" styleClass="input" maxlength="2"  styleId="dayId2" />
								            &nbsp;/&nbsp;
								            <html:text property="purchaseMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId2"/>
					    			        &nbsp;/&nbsp;
								            <html:text property="purchaseYear" style="width:40;" styleClass="input" maxlength="4"  styleId="yearId2"/>
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
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
									<tr class="texts">
			            				<th align="left"> 
			            					<%=messages.getText("vehicle.data.sucta")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
			            				</th>
			            				<td align="right">
			            					<html:text property="suctaExpiryDate" style="width:20;" styleClass="input" maxlength="2" styleId="dayId3"/>
				                			&nbsp;/&nbsp;
			      	        				<html:text property="suctaExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId3"/>
						            		&nbsp;/&nbsp;
				            				<html:text property="suctaExpiryYear" style="width:40;" styleClass="input" maxlength="4"  styleId="yearId3"/>
				            			   <html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button3" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>			          	
            							
			            				</td>
			            				<script type="text/javascript">
											Calendar.setup({
		        							inputArea     :    "yearId3",
		        							ifFormat       :    "%d/%m/%Y",
		        							showsTime      :    true,
		        							timeFormat     :    "24",
		        							button         :    "calendar_button3",
		        							step           :    1,
		        							onSelect       :    onSelect3,
		        							singleClick    :    true
		    							});
										</script>
			          				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					  				<tr class="texts">
			            				<th align="left"> 
			            					<%=messages.getText("vehicle.data.mtop")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
			            				</th>
			            				<td align="right">
			            					<html:text property="mtopExpiryDate" style="width:20;" styleClass="input" maxlength="2" styleId="dayId4"/>
				                			&nbsp;/&nbsp;
			      	        				<html:text property="mtopExpiryMonth" style="width:20;" styleClass="input" maxlength="2" styleId="monthId4"/>
						            		&nbsp;/&nbsp;
				            				<html:text property="mtopExpiryYear" style="width:40;" styleClass="input" maxlength="4"  styleId="yearId4"/>
				            				  <html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button4" style="cursor: pointer; border: 1px solid red;"
     										onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>			          	
            							
			            				</td>
			            				<script type="text/javascript">
											Calendar.setup({
		        							inputArea     :    "yearId4",
		        							ifFormat       :    "%d/%m/%Y",
		        							showsTime      :    true,
		        							timeFormat     :    "24",
		        							button         :    "calendar_button4",
		        							step           :    1,
		        							onSelect       :    onSelect4,
		        							singleClick    :    true
		    							});
										</script>
			          				</tr>
          							<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
            							<th align="left" > 
            								<%=messages.getText("generic.vehicle.vehicledata.ownership")%>
			            				</th>
            							<td align="right">
<%			Collection ownership = new LinkedList();
			ownership.add(new OptionItem(messages.getText("generic.messages.select"),""));
			Iterator iter1 = sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECENTERCOST,userSessionData).iterator();
			while(iter1.hasNext()) {
				String value = ((TypeCenterCostDataType)iter1.next()).getResourceBundleId();
				ownership.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("cc",ownership);
%>		        							<html:select property="ownership" styleClass="input" style="width:155;">
                  								<html:options collection="cc" property="value" labelProperty="label" styleClass="input"/>
			                				</html:select>
            							</td>
          							</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
						       		<tr class="texts">
			            				<th align="left" > 
		    	        					<%=messages.getText("generic.vehicle.vehicledata.colour")%> 
		        	    				</th>
		            					<td align="right">
		                					<html:text property="colour" style="width:155" styleClass="input" maxlength="30"/>
		            					</td>
		          					</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
			            				<th width="224" align="left"> 
		    	        					<%=messages.getText("generic.vehicle.vehicledata.chasisNumber")%> 
		        	    				</th>
		            					<td align="right">
		                					<html:text property="chasisNumber" style="width:155" styleClass="input" maxlength="30"/>
		            					</td>
		          					</tr>
									<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			          				<tr class="texts">
			            				<th align="left" > 
		    	        					<%=messages.getText("generic.vehicle.vehicledata.cover")%>
		        	    				</th>
		            					<td align="right">
			            					<html:select property="cover" styleClass="input" style="width:155;">
		                  						<html:options collection="cub" property="value" labelProperty="label" styleClass="input"/>
		                					</html:select>
		            					</td>
			          				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          							<tr class="texts">
            							<th align="left"> 
			            					<%=messages.getText("vehicle.data.ensurancetype")%> 
            							</th>          
            							<td align="right">
<%			Collection ensurance = new LinkedList();
			ensurance.add(new OptionItem(messages.getText("generic.messages.select"),""));
			Iterator iter2 = sys.getDataTypeReportFacade().findAll(DataTypeConstants.ENSURANCE_TYPE,userSessionData).iterator();
			while(iter2.hasNext()) {
				String value = ((EnsuranceDataType)iter2.next()).getResourceBundleId();
				ensurance.add(new OptionItem(messages.getText(value),String.valueOf(value)));				
			}
			session.setAttribute("ensurance",ensurance);
%>					        				<html:select property="ensuranceType" styleClass="input" style="width:155;">
                  								<html:options collection="ensurance" property="value" labelProperty="label" styleClass="input"/>
                							</html:select>
    	    	    					</td>
	    	    	  				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("vehicle.data.ensurance")%> 
		        	    				</th>          
		    	        				<td align="right">
			            					<html:text property="ensurance" style="width:155" styleClass="input" maxlength="30"/>
			            				</td>
			          				</tr>
									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          							<tr class="texts">
            							<th width="224" align="left"> 
			            					<%=messages.getText("vehicle.data.axiscount")%> 
            							</th>
            							<td align="right">
				                			<html:select property="axisCount" styleClass="input" >
            			      					<html:option value="1">1</html:option>
                  								<html:option value="2">2</html:option>
                  								<html:option value="3">3</html:option>
            			    				</html:select>
            							</td>
			          				</tr>
			          
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
          							<tr class="texts">
            							<th width="224" align="left"> 
			            					<%=messages.getText("generic.vehicle.vehicledata.type")%>
            							</th>
            							<td align="right">
				                			<html:select property="type" styleClass="input" >
            			      					<html:option value="">Seleccionar</html:option>
                  								<html:option value="Zorra">Zorra</html:option>
                  								<html:option value="Semi">Semirremolque</html:option>
            			    				</html:select>
            							</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("generic.vehicle.vehicledata.abs")%>
		        	    				</th>          
		    	        				<td align="right">
			            					<html:checkbox property="abs" style="width:155" styleClass="input"  />
			            				</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("generic.vehicle.vehicledata.suspension")%>
		        	    				</th>          
		    	        				<td align="right">
			            					<html:checkbox property="abs" style="width:155" styleClass="input"  />
			            				</td>
			          				</tr>
			          				<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
		          					<tr class="texts">
		            					<th align="left"> 
		            						<%=messages.getText("generic.vehicle.vehicledata.weightcontrol")%>
		        	    				</th>          
		    	        				<td align="right">
			            					<html:checkbox property="weightControl" style="width:155" styleClass="input"  />
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
			  	<td height="18" colspan="3" valign="top" align="right">
					 <html:submit styleClass="input"> <%=messages.getText("generic.messages.save")%> </html:submit>
				     <html:cancel styleClass="input"> <%=messages.getText("generic.messages.cancel")%> </html:cancel >
				</td>
			</tr>
		</table>
	</html:form>   
</body>
<%	}
%>
</html:html>
