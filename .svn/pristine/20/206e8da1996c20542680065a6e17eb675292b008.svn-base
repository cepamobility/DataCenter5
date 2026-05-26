<%@page language="java"
    errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.security.*"
 	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*" 
	import="communication.driver.tables.DriverData"
	import="communication.administration.corporation.tables.CorporationData"
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
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.QUERY);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		session.setAttribute("url","activity/schedule/ReportCommentaryDrive.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		// session.setAttribute("format",format);
		request.setAttribute("format",format);
		
		request.setAttribute("vehicleType",new DataTypeUtil(sys).getExtendedVehicleType(true,false,false,sys.isCorporation(CorporationData.MONSANTO)));
	    
		Collection coltype=new LinkedList();										                                     		   		
		coltype.add(new OptionItem(messages.getText("generic.messages.all"),"0"));
		coltype.add(new OptionItem(messages.getText("generic.activity.form.commentarydrive.peerdrivewithpeer"),"generic.activity.form.commentarydrive.peerdrivewithpeer"));
		coltype.add(new OptionItem(messages.getText("generic.activity.form.commentarydrive.commentarydrivewithsuperior"),"generic.activity.form.commentarydrive.commentarydrivewithsuperior"));	
		// session.setAttribute("cdtype",coltype);											
		request.setAttribute("cdtype",coltype);

		Collection cdkind = new LinkedList();										                                     		   		
		cdkind.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydrive"),"datatype.scheduledactivitytype.commentarydrive"));
		cdkind.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydriverisk"),"datatype.scheduledactivitytype.commentarydriverisk"));
		cdkind.add(new OptionItem(messages.getText("datatype.scheduledactivitytype.commentarydriveargentina"),"datatype.scheduledactivitytype.commentarydriveargentina"));
		// session.setAttribute("cdkind",cdkind);											
		request.setAttribute("cdkind",cdkind);

		ReportCommentaryDriveForm reportForm = (session.getAttribute("ReportCommentaryDriveForm") != null) ? (ReportCommentaryDriveForm)session.getAttribute("ReportCommentaryDriveForm") : new ReportCommentaryDriveForm();
		session.setAttribute("ReportCommentaryDriveForm",reportForm);

		
%>
	
	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script language="javascript" type="text/javascript">
		function conComentarios(form, withcommentarydrive)
		{
		    form.withCommentaryDrive.value = withcommentarydrive;
			form.oneCommentaryDrive.value= "off";
			form.moreThanTwoCommentaryDrive.value = "off";
			form.twoCommentaryDrive.value = "off";
			// Internet Explorer
			form.oneCommentaryDrive.checked = false;
			form.moreThanTwoCommentaryDrive.checked = false;
			form.twoCommentaryDrive.checked = false;			
			// Fin Internet Explorer
			
			
			if (withcommentarydrive == "wcd") {
				form.oneCommentaryDrive.disabled = false;
				form.twoCommentaryDrive.disabled = false;
				form.moreThanTwoCommentaryDrive.disabled = false;
				// Internet Explorer		
				form.oneCommentaryDrive.checked = true;
				form.moreThanTwoCommentaryDrive.checked = true;
				form.twoCommentaryDrive.checked = true;
				// Firefox
				form.oneCommentaryDrive.value= "on";
				form.moreThanTwoCommentaryDrive.value = "on";
				form.twoCommentaryDrive.value = "on";
							
			} else {		
				form.oneCommentaryDrive.disabled = true;
				form.twoCommentaryDrive.disabled = true;
				form.moreThanTwoCommentaryDrive.disabled = true;		
			}
		}
		function Validar(form)
		{	
			// Para Internet Explorer
			if (form.oneCommentaryDrive.checked)
				form.oneCommentaryDrive.value = "on";
			else
				form.oneCommentaryDrive.value = "off";

			if (form.twoCommentaryDrive.checked)
				form.twoCommentaryDrive.value = "on";
			else
				form.twoCommentaryDrive.value = "off";				
			
			if (form.moreThanTwoCommentaryDrive.checked)
				form.moreThanTwoCommentaryDrive.value = "on";
			else
				form.moreThanTwoCommentaryDrive.value = "off";
			
			// Fin Para Internet Explorer
			

			
			if (form.withCommentaryDrive.value == "wcd" &&
			    form.oneCommentaryDrive.value != "on" &&
				form.twoCommentaryDrive.value != "on" &&
				form.moreThanTwoCommentaryDrive.value != "on")
			{	
				alert('<%=messages.getText("query.activity.commentarydrive.selectamount")%>');			
			}
			else
			{
				// else OKEY !!					
				form.submit();
			}			
		}
		
		function changeCheckBox(checkbox)
		{
			if (checkbox.value == "on")
				checkbox.value = "off";
			else
				checkbox.value = "on";
		}

		function showType(combo) {
			if(combo.selectedIndex == 0) {
				document.forms['ReportCommentaryDriveForm'].commentaryDriveType.style.display = "inline";
			}else {
				document.forms['ReportCommentaryDriveForm'].commentaryDriveType.style.display = "none";
			}
		}
</script>
	

	<body bgcolor="#FFFFFF" >
		<!-- ReportCommentaryDrive -->
		<html:form action="/ReportCommentaryDrive" >
			<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
				<!--  Titulo -->
  				<tr class="guide1">
  					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("generic.query.activity.commentarydrive")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
			  	</tr>
				<tr>
				    <!--  Datos Generales -->
					<td width="49%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
                     			<td>
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("query.activity.commentarydrive.generaldata")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">

										<!--  Desde Fecha: -->
			      						<tr  class="linetwo">
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>			      							
						      							<td align="left" class="texts"> 	
			        										<%=messages.getText("query.activity.training.from")%> (dd mm yyyy)<span class="required">*</span>
			        									</td>
			        									<td align="right">
			       											<jsp:include  page='<%="../../component/dateComponent.jsp?index=1&day=fromday&month=frommonth&year=fromyear&form=ReportCommentaryDriveForm" %>' />
			         									</td>		
			         								</tr>
			         							</table>
			         						</td>	        						
			      						</tr>
			      						<tr class="space"><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
										<!--  Hasta Fecha: -->
			      						<tr  class="linetwo">
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>			      							
						      							<td align="left" class="texts"> 	
			        										<%=messages.getText("query.activity.training.from")%> (dd mm yyyy)<span class="required">*</span>
			        									</td>
			        									<td align="right">
			       											<jsp:include  page='<%="../../component/dateComponent.jsp?index=2&day=today&month=tomonth&year=toyear&form=ReportCommentaryDriveForm" %>' />
			         									</td>		
			         								</tr>
			         							</table>
			         						</td>	        						
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<!--  Commentary Drive Type -->
									    <tr>
									    	<td width="100%" >
									    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr class="guideSearch">
					      								<td width="10" align="left"><img src="/datacenter/util/imgs/left_top_search.jpg"></td>
														<td class="guideSearch" align="left"><%=messages.getText("generic.activity.form.commentarydrive.commentarydrivetype")%></td>
														<td width="10" align="right"><img src="/datacenter/util/imgs/right_top_search.jpg"></td>
													</tr>	
													<tr>
														<td width="10" class="leftLineSearch"></td>
						      							<td>
															<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">																
																<tr>
																	<td class="lineone" style="width:100%" align="right">
																		<html:select property="commentaryDriveKind"  styleClass="input" style="width:200;" onchange="showType(this);">
																			<html:options collection="cdkind" property="value" labelProperty="label" styleClass="input" />
																		</html:select>									
																	</td>
																</tr>
																<tr><td class="lineone">&nbsp;</td></tr>
																<tr>
																	<td class="lineone" style="width:100%" align="right">
																		<html:select property="commentaryDriveType" styleClass="input" style="width:200;">
																			<html:options collection="cdtype" property="value" labelProperty="label" styleClass="input" />
																		</html:select>									
																	</td>
																</tr>
                                           					</table>
                                           				</td>
														<td width="10" class="rightLineSearch">&nbsp;</td>
						      						</tr>
						      						<tr>
				                 						<td width="10" align="left" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" ></td>
				 				  						<td class="centerBaseSearch">&nbsp;</td>
				 				  						<td width="10" align="right" valign="top" ><img src="/datacenter/util/imgs/right_base_search.jpg" ></td>
						 							</tr>
												</table>
											</td>	
										</tr>
									    <tr class="space"><th align="right">&nbsp;</th>
									    	<td align="right">&nbsp;</td>
									    </tr>
									    <!--  Estado del Conductor: -->
			      						<tr>
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>
	    												<td width="41%" align="left" class="texts">
															<%=messages.getText("query.activityexpiry.driverstate") %>
															<span class="required">*</span>       
														</td>
														<td align="right">
<%													Collection colDriverState = new LinkedList();										                                     		   															
										   	        colDriverState.add(new OptionItem(messages.getText("generic.messages.all.pl"),(new Integer(DriverData.ALL)).toString()));   	
											   	    colDriverState.add(new OptionItem(messages.getText("generic.messages.active"),(new Integer(DriverData.ACTIVE)).toString()));
											   	    colDriverState.add(new OptionItem(messages.getText("generic.messages.excluded"),(new Integer(DriverData.EXCLUDED)).toString()));   	
													// session.setAttribute("colDriverState",colDriverState);											
													request.setAttribute("colDriverState",colDriverState);
%>															<html:select property="driverState" styleClass="input" style="width:155;">
																<html:options collection="colDriverState" property="value" labelProperty="label" styleClass="input" />
															</html:select>																								
														</td>	   
													</tr>
												</table>
											</td> 									
									    </tr>
<% 					if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == communication.administration.corporation.tables.CorporationData.MONSANTO) {
%>     									<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
										<tr  class="linetwo">
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr>
						      							<th align="left"> 
						      								<%=messages.getText("generic.driver.driverdata.vehicleallowed")%><span class="required">*</span>
						      							</th>
						      							<td align="right">
						 	     							<html:select property="vehicleAllowed" styleClass="input" style="width:130;" >
						      									<html:options collection="vehicleType" property="value" labelProperty="label" styleClass="input"/>
							  								</html:select>   
														</td>	   
													</tr>
												</table>
											</td> 									
									    </tr>
<%					}else {
%>										<html:hidden property="vehicleAllowed" value=""/>		
<%					}
%>									    <tr class="space"><th align="right">&nbsp;</th>
									    	<td align="right">&nbsp;</td>
									    </tr>
									    <tr class="space"><th align="right">&nbsp;</th>
									    	<td align="right">&nbsp;</td>
									    </tr>
										<!--  Cantidad de Commentary Drives -->
									    <tr>
									    	<td width="100%" >
									    		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
					      							<tr class="guideSearch">
					      								<td width="10" align="left"><img src="/datacenter/util/imgs/left_top_search.jpg"></td>
														<td class="guideSearch" align="left"><%=messages.getText("query.activity.commentarydrive.amount")%></td>
														<td width="10" align="right"><img src="/datacenter/util/imgs/right_top_search.jpg"></td>
													</tr>	
													<tr>
														<td width="10" class="leftLineSearch"></td>
						      							<td>
															<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">																
																	<tr>
																		<td class="lineone" style="width:100%">
																			<html:radio property="withCommentaryDrive" value="wocd" 
																				onchange="conComentarios(document.forms['ReportCommentaryDriveForm'],'wocd');" />									                                    	
                                            									<%=messages.getText("query.activity.commentarydrive.withoutcommentarydrive")%>
                                            							</td>
                                            						</tr>
                                            						<tr>
                                            							<td class="lineone" style="width:100%">
                                            								<html:radio property="withCommentaryDrive"  value="wcd"
                                            								onchange="conComentarios(document.forms['ReportCommentaryDriveForm'],'wcd');"/>
                                            									<%=messages.getText("query.activity.commentarydrive.withcommentarydrive")%>
                                            							</td>
                                            						</tr>
<% 		boolean disabled = reportForm.getWithCommentaryDrive().equalsIgnoreCase("wocd");
		
%>                                            						
                                            						<tr align="left">
                                            							<td class="lineone" style="width:100%">
                                            								&nbsp;&nbsp;&nbsp;
<%		if(disabled) {
%>                                          								<html:checkbox disabled="true" property="oneCommentaryDrive"></html:checkbox>
<%		}else {
%>                                          								<html:checkbox property="oneCommentaryDrive" ></html:checkbox>
<%		}
%>	                                        									<%=messages.getText("query.activity.commentarydrive.withcommentarydrive.one") %>
                                            							</td>
                                            						</tr>   
                                            						<tr align="left">
                                            							<td class="lineone" style="width:100%">
                                            								&nbsp;&nbsp;&nbsp;
<%		if(disabled) {
%>                                          								<html:checkbox disabled="true" property="twoCommentaryDrive" ></html:checkbox>
<%		}else {
%>                                          								<html:checkbox property="twoCommentaryDrive" ></html:checkbox>
<%		}
%>                                          									<%=messages.getText("query.activity.commentarydrive.withcommentarydrive.two") %>
                                            							</td>
                                            						</tr>
                                            						<tr align="left">
                                            							<td class="lineone" style="width:100%">
                                            								&nbsp;&nbsp;&nbsp;
<%		if(disabled) {
%>                                          								<html:checkbox disabled="true" property="moreThanTwoCommentaryDrive" ></html:checkbox>
<%		}else {
%>                                          								<html:checkbox property="moreThanTwoCommentaryDrive" ></html:checkbox>
<%		}
%>                                          									<%=messages.getText("query.activity.commentarydrive.withcommentarydrive.morethantwo") %>
                                            								
                                            							</td>
                                            						</tr>                                            						
                                            					
                                           					</table>
                                           				</td>
														<td width="10" class="rightLineSearch">&nbsp;</td>
						      						</tr>
						      						<tr>
				                 						<td width="10" align="left" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" ></td>
				 				  						<td class="centerBaseSearch">&nbsp;</td>
				 				  						<td width="10" align="right" valign="top" ><img src="/datacenter/util/imgs/right_base_search.jpg" ></td>
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
							<tr>
								<td style="width:100%">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
										<!-- Divisiones Geograficas -->
										<jsp:include page='<%="../../util/divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form=ReportCommentaryDriveFrom&consult=true"%>' />
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td width="2%" valgin="top">&nbsp;</td>
					<td width="49%" valign="top">						
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<!--  Divisiones Organizacionales: -->
							<jsp:include page='<%="../../util/divorg.jsp?form=ReportCommentaryDriveForm&consult=true" %>'/>
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
									<td align="left" colspan="3" class="texts">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
				  				  	<td height="18" colspan="3" valign="top">
				          				<div align="right">
											<html:submit value='<%=messages.getText("generic.messages.open")%>' styleClass="input" />
								  		</div>          
								  	</td>
							  	</tr>
							</table>
						<jsp:include  page="../../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
<%	}	%>
</html:html>
