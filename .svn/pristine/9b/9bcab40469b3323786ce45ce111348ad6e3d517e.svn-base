<%@page language="java"
	import="business.*"
	import="java.util.*"
	import="communication.language.*"
	import="communication.security.*"
	import="presentation.translator.web.util.*"
	errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<%! 
	RequestDispatcher dispatcher; String context; String pathMsg; String pathFindAllWwRegion;
	Text messages; String pathLanguage;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathLanguage="/util/jsp/language.jsp";

		session.setAttribute("url","corporation/create.jsp");

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		messages=sys.getText();
%>
	<head/>
	<script>
		function view(form,combo,level2,level3) {
			var count = form.elements[combo].options[form.elements[combo].selectedIndex].value;
			if(count >= 2) {
				document.getElementById(level2).style.display = 'block';
			}else {
				document.getElementById(level2).style.display = 'none';
			}
			if(count >= 3) {
				document.getElementById(level3).style.display = 'block';
			}else {
				document.getElementById(level3).style.display = 'none';
			}
		}
	</script>
	<body>
		<html:form action="/CorporationCreate" >
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.driver.driverdata.corporation")%>' />
				</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
		      	<tr>
		      		<td>
		    			<table width='100%' align='center' cellpadding="0" cellspacing="0">
					  		<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.data")%>' />
									<table align='center' width='100%' cellpadding="0" cellspacing="0">
								  		<tr>
											<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.name")%></td>
											<td class="linetwo">
						   						<html:text property="corporationName" size="25" styleClass="input" />              
											</td>
										</tr>
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr>
											<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.type")%></td>
											<td class="linetwo">
						   						<html:select property="type" styleClass="input">
						   							<html:option value='<%=String.valueOf(CorporationData.DATACENTER)%>'>DATACENTER</html:option>
						   							<html:option value='<%=String.valueOf(CorporationData.FORESTAL)%>'>FORESTAL</html:option>
						   							<html:option value='<%=String.valueOf(CorporationData.CEPA_SYSTEM)%>'>CEPA SYSTEM</html:option>
						   						</html:select>              
											</td>
										</tr>
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr>
											<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.organizationaldivisions")%></td>
											<td class="linetwo">
						   						<html:select property="divOrgCount" styleClass="input" onchange="javascript:view(document.forms['CorporationCreateForm'],'divOrgCount','divorg2','divorg3')" >              
						   							<html:option value="1">1</html:option>
						   							<html:option value="2">2</html:option>
						   							<html:option value="3">3</html:option>
						   						</html:select>
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
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.corporationdata.divorg")+" 1"%>' />
									<table align='center' width='100%' cellpadding="0" cellspacing="0">
								  		<tr class="linethree">
											<td align="left" valign="middle" colspan="2">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.divorgname")%>' />
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											  		<tr class="linethree">
														<td align="left"><%=messages.getText("es")%></td>
														<td>&nbsp;</td>
														<td align="left"><%=messages.getText("en")%></td>
														<td>&nbsp;</td>
														<td align="left"><%=messages.getText("pt")%></td>
											  		</tr>
											  		<tr class="linethree">
														<td align="center">
									   						<html:text property="spanish1" size="25" styleClass="input" />              
														</td>
														<td>&nbsp;</td>
														<td align="center">
									   						<html:text property="english1" size="25" styleClass="input" />              
														</td>
														<td>&nbsp;</td>
														<td align="center">
									   						<html:text property="portuguese1" size="25" styleClass="input" />              
														</td>
											  		</tr>
											  	</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr>
											<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.levelcount")%></td>
											<td align="left" class="linetwo" width="80%">
						   						<html:select property="levelCount1" styleClass="input" onchange="javascript:view(document.forms['CorporationCreateForm'],'levelCount1','level12','level13')">              
						   							<html:option value="1">1</html:option>
						   							<html:option value="2">2</html:option>
						   							<html:option value="3">3</html:option>
						   						</html:select>
											</td>
										</tr>
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr class="linethree">
											<td align="left" valign="middle" colspan="2">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 1"%>' />
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											  		<tr class="linethree">
														<td align="left"><%=messages.getText("es")%></td>
														<td>&nbsp;</td>
														<td align="left"><%=messages.getText("en")%></td>
														<td>&nbsp;</td>
														<td align="left"><%=messages.getText("pt")%></td>
											  		</tr>
											  		<tr class="linethree">
														<td align="left">
									   						<html:text property="spanish11" size="25" styleClass="input" />              
														</td>
														<td>&nbsp;</td>
														<td align="left" class="linetwo">
									   						<html:text property="english11" size="25" styleClass="input" />              
														</td>
														<td>&nbsp;</td>
														<td align="left" class="linetwo">
									   						<html:text property="portuguese11" size="25" styleClass="input" />              
														</td>
											  		</tr>
											  		<tr class="linethree">
														<td align="left">
									   						<html:checkbox property="showLevel11" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
														</td>
													</tr>	
											  	</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
										<tr>
											<td width='100%' colspan="2">											
												<div id=level12 style="display:none" >
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr class="linethree">
														<td align="left" valign="middle" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 2"%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr class="linethree">
																	<td align="left"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:text property="spanish12" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="english12" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="portuguese12" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:checkbox property="showLevel12" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																	</td>
																</tr>	
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
												</table>
												</div>
											</td>
										</tr>	
										<tr>
											<td width='100%' colspan="2">											
												<div id=level13 style="display:none" >
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr class="linethree">
														<td align="left" valign="middle"  class="linethree" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 3"%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr>
																	<td align="left" class="linethree"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:text property="spanish13" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="english13" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="portuguese13" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:checkbox property="showLevel13" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																	</td>
																</tr>	
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
												</table>
												</div>
											</td>
										</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
							<tr>
								<td width='100%' colspan="2">											
									<div id=divorg2 style="display:none" >
									<table align='center' width='100%' cellpadding="0" cellspacing="0">
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr>
											<td>
												<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.corporationdata.divorg")+" 2"%>' />
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											  		<tr>
														<td align="left" valign="middle" class="linetwo" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.divorgname")%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr class="linethree">
																	<td align="left"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="center">
												   						<html:text property="spanish2" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="center">
												   						<html:text property="english2" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="center">
												   						<html:text property="portuguese2" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr>
														<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.levelcount")%></td>
														<td align="left" class="linetwo" width="80%">
									   						<html:select property="levelCount2" styleClass="input"  onchange="javascript:view(document.forms['CorporationCreateForm'],'levelCount2','level22','level23')">              
									   							<html:option value="1">1</html:option>
									   							<html:option value="2">2</html:option>
									   							<html:option value="3">3</html:option>
									   						</html:select>
														</td>
													</tr>
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr>
														<td align="left" valign="middle" class="linetwo" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 1"%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr>
																	<td align="left" class="linethree"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:text property="spanish21" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="english21" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="portuguese21" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:checkbox property="showLevel21" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																	</td>
																</tr>	
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
													<tr>
														<td width='100%' colspan="2">											
															<div id=level22 style="display:none" >
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														      	<tr>
																	<jsp:include  page="../component/spaceline.jsp" />
														  		</tr>
														  		<tr>
																	<td align="left" valign="middle" class="linetwo" colspan="2">
																		<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 2"%>' />
																		<table align='center' width='100%' cellpadding="0" cellspacing="0">
																	  		<tr>
																				<td align="left" class="linethree"><%=messages.getText("es")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("en")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("pt")%></td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:text property="spanish22" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="english22" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="portuguese22" size="25" styleClass="input" />              
																				</td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:checkbox property="showLevel22" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																				</td>
																			</tr>	
																	  	</table>
																		<jsp:include  page="../component/utilitybox2bottom.jsp" />
																	</td>
																</tr>
															</table>
															</div>
														</td>
													</tr>	
													<tr>
														<td width='100%' colspan="2">											
															<div id=level23 style="display:none" >
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														      	<tr>
																	<jsp:include  page="../component/spaceline.jsp" />
														  		</tr>
														  		<tr>
																	<td align="left" valign="middle" class="linetwo" colspan="2">
																		<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 3"%>' />
																		<table align='center' width='100%' cellpadding="0" cellspacing="0">
																	  		<tr>
																				<td align="left" class="linethree"><%=messages.getText("es")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("en")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("pt")%></td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:text property="spanish23" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="english23" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="portuguese23" size="25" styleClass="input" />              
																				</td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:checkbox property="showLevel23" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																				</td>
																			</tr>	
																	  	</table>
																		<jsp:include  page="../component/utilitybox2bottom.jsp" />
																	</td>
																</tr>
															</table>
															</div>
														</td>
													</tr>
												</table>
												<jsp:include  page="../component/utilityboxbottom.jsp" />
											</td>
										</tr>
									</table>
									</div>
								</td>
							</tr>
							<tr>
								<td width='100%' colspan="2">											
									<div id=divorg3 style="display:none" >
									<table align='center' width='100%' cellpadding="0" cellspacing="0">
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr>
											<td>
												<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.corporationdata.divorg")+" 3"%>' />
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											  		<tr>
														<td align="left" valign="middle" class="linetwo" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.divorgname")%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr class="linethree">
																	<td align="left"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="center">
												   						<html:text property="spanish3" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="center">
												   						<html:text property="english3" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="center">
												   						<html:text property="portuguese3" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr>
														<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.levelcount")%></td>
														<td align="left" class="linetwo" width="80%">
									   						<html:select property="levelCount3" styleClass="input"  onchange="javascript:view(document.forms['CorporationCreateForm'],'levelCount3','level32','level33')">              
									   							<html:option value="1">1</html:option>
									   							<html:option value="2">2</html:option>
									   							<html:option value="3">3</html:option>
									   						</html:select>
														</td>
													</tr>
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr>
														<td align="left" valign="middle" class="linetwo" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 1"%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr>
																	<td align="left" class="linethree"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:text property="spanish31" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="english31" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left" class="linetwo">
												   						<html:text property="portuguese31" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:checkbox property="showLevel31" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																	</td>
																</tr>	
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
													<tr>
														<td width='100%' colspan="2">											
															<div id=level32 style="display:none" >
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														      	<tr>
																	<jsp:include  page="../component/spaceline.jsp" />
														  		</tr>
														  		<tr>
																	<td align="left" valign="middle" class="linetwo" colspan="2">
																		<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 2"%>' />
																		<table align='center' width='100%' cellpadding="0" cellspacing="0">
																	  		<tr>
																				<td align="left" class="linethree"><%=messages.getText("es")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("en")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("pt")%></td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:text property="spanish32" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="english32" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="portuguese32" size="25" styleClass="input" />              
																				</td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:checkbox property="showLevel32" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																				</td>
																			</tr>	
																	  	</table>
																		<jsp:include  page="../component/utilitybox2bottom.jsp" />
																	</td>
																</tr>
															</table>
															</div>
														</td>
													</tr>
													<tr>
														<td width='100%' colspan="2">											
															<div id=level33 style="display:none" >
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														      	<tr>
																	<jsp:include  page="../component/spaceline.jsp" />
														  		</tr>
														  		<tr>
																	<td align="left" valign="middle" class="linetwo" colspan="2">
																		<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 3"%>' />
																		<table align='center' width='100%' cellpadding="0" cellspacing="0">
																	  		<tr>
																				<td align="left" class="linethree"><%=messages.getText("es")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("en")%></td>
																				<td>&nbsp;</td>
																				<td align="left" class="linethree"><%=messages.getText("pt")%></td>
																	  		</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:text property="spanish33" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
															   						<html:text property="english33" size="25" styleClass="input" />              
																				</td>
																				<td>&nbsp;</td>
																				<td align="left" class="linetwo">
														   							<html:text property="portuguese33" size="25" styleClass="input" />              
																				</td>
																  			</tr>
																	  		<tr class="linethree">
																				<td align="left">
															   						<html:checkbox property="showLevel33" styleClass="input" ><%=messages.getText("corporation.create.show")%></html:checkbox>              
																				</td>
																			</tr>	
																  		</table>
																		<jsp:include  page="../component/utilitybox2bottom.jsp" />
																	</td>
																</tr>
															</table>
															</div>
														</td>
													</tr>	
												</table>
												<jsp:include  page="../component/utilityboxbottom.jsp" />
											</td>
										</tr>
									</table>
									</div>
								</td>
							</tr>
					  		<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.corporationdata.divgeo")%>' />
									<table align='center' width='100%' cellpadding="0" cellspacing="0">
								  		<tr>
											<td class="linetwo"><%=messages.getText("generic.corporation.corporationdata.levelcount")%></td>
											<td align="left" class="linetwo" width="80%">
						   						<html:select property="levelCount4" styleClass="input" onchange="javascript:view(document.forms['CorporationCreateForm'],'levelCount4','level42','level43')">              
						   							<html:option value="1">1</html:option>
						   							<html:option value="2">2</html:option>
						   							<html:option value="3">3</html:option>
						   						</html:select>
											</td>
										</tr>
								      	<tr>
											<jsp:include  page="../component/spaceline.jsp" />
								  		</tr>
								  		<tr class="linethree">
											<td align="left" valign="middle" colspan="2">
												<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 1"%>' />
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											  		<tr class="linethree">
														<td align="left"><%=messages.getText("es")%></td>
														<td>&nbsp;</td>
														<td align="left"><%=messages.getText("en")%></td>
														<td>&nbsp;</td>
														<td align="left"><%=messages.getText("pt")%></td>
											  		</tr>
											  		<tr class="linethree">
														<td align="left">
									   						<html:text property="spanish41" size="25" styleClass="input" />              
														</td>
														<td>&nbsp;</td>
														<td align="left" class="linetwo">
									   						<html:text property="english41" size="25" styleClass="input" />              
														</td>
														<td>&nbsp;</td>
														<td align="left" class="linetwo">
									   						<html:text property="portuguese41" size="25" styleClass="input" />              
														</td>
											  		</tr>
											  	</table>
												<jsp:include  page="../component/utilitybox2bottom.jsp" />
											</td>
										</tr>
										<tr>
											<td width='100%' colspan="2">											
												<div id=level42 style="display:none" >
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr class="linethree">
														<td align="left" valign="middle" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 2"%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr class="linethree">
																	<td align="left"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:text property="spanish42" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left">
												   						<html:text property="english42" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left">
												   						<html:text property="portuguese42" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
												</table>
												</div>
											</td>
										</tr>	
										<tr>
											<td width='100%' colspan="2">											
												<div id=level43 style="display:none" >
												<table align='center' width='100%' cellpadding="0" cellspacing="0">
											      	<tr>
														<jsp:include  page="../component/spaceline.jsp" />
											  		</tr>
											  		<tr class="linethree">
														<td align="left" valign="middle"  class="linethree" colspan="2">
															<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText("generic.corporation.corporationdata.level")+" 3"%>' />
															<table align='center' width='100%' cellpadding="0" cellspacing="0">
														  		<tr>
																	<td align="left" class="linethree"><%=messages.getText("es")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("en")%></td>
																	<td>&nbsp;</td>
																	<td align="left" class="linethree"><%=messages.getText("pt")%></td>
														  		</tr>
														  		<tr class="linethree">
																	<td align="left">
												   						<html:text property="spanish43" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left">
												   						<html:text property="english43" size="25" styleClass="input" />              
																	</td>
																	<td>&nbsp;</td>
																	<td align="left">
												   						<html:text property="portuguese43" size="25" styleClass="input" />              
																	</td>
														  		</tr>
														  	</table>
															<jsp:include  page="../component/utilitybox2bottom.jsp" />
														</td>
													</tr>
												</table>
												</div>
											</td>
										</tr>
									</table>
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
		      		<td>
			            <html:submit styleClass="input"><%=messages.getText("generic.messages.save")%></html:submit>
			            <html:cancel styleClass="input"><%=messages.getText("generic.messages.cancel")%></html:cancel>
		      		</td>
		      	</tr>
			</table>
		</html:form>
	</body>
<%		}	%>
</html:html>
