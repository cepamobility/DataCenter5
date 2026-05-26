<%@page language="java"
    errorPage="../../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.security.*"
 	import="presentation.translator.web.util.*"
 	
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
	DataTypeReportFacade datatypes;
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_DRIVER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {

		datatypes=sys.getDataTypeReportFacade();
		
		session.setAttribute("url","activity/schedule/ReportGeneralTraining.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();

		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("format",format);
		OptionItem allObject = new OptionItem(messages.getText("query.activity.training.all"),"query.activity.training.all");

%>

	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<body bgcolor="#FFFFFF">
		<html:form action="/ReportGeneralTraining">
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("generic.query.activity.training_g")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td width="50%" valign="top">
                    	<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
                       					<jsp:include page='<%="/driver/divgeo.jsp?form=ReportGeneralTrainingForm&consult=true" %>'/>
	                     			</table>					  
								</td>
							</tr>		
							<tr>
								<jsp:include  page="../../component/spaceline.jsp" />
							</tr>
                    		<tr>
                     			<td>
									<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("query.activity.training.period")%>' />
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.activity.training.from")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
			         							<html:text property="frommonth" size="5" styleClass="input"/>
			         						</td>
			        						<th align="left"> 	
			        							<%=messages.getText("query.activity.training.month")%>
			        						</th>
			         						<td align="right">
			         							<html:text property="fromyear" size="10" styleClass="input"/>
			         						</td>
			        						<th align="left"> 	
			        							<%=messages.getText("query.activity.training.year")%>
			        						</th>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr  class="linetwo">
			        						<th align="left"> 	
			        							<%=messages.getText("query.activity.training.to")%><span class="required">*</span>
			        						</th>
			         						<td align="right">
			         							<html:text property="tomonth" size="5" styleClass="input"/>
			         						</td>
			        						<th align="left"> 	
			        							<%=messages.getText("query.activity.training.month")%>
			        						</th>
			         						<td align="right">
			         							<html:text property="toyear" size="10" styleClass="input"/>
			         						</td>
			        						<th align="left"> 	
			        							<%=messages.getText("query.activity.training.year")%>
			        						</th>
			      						</tr>
			      						<tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr>
	    									<td width="41%" align="left" class="texts">
												<%											
													Collection col=new LinkedList();
													col.add(allObject);
													Iterator iter=datatypes.findAll(DataTypeConstants.TYPECEPAACTIVITY,sys.getUserSessionData()).iterator();
                                          	  		while(iter.hasNext()){
                                         	   			GeneralDTData itemType=(GeneralDTData)iter.next();
                                            			col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
                                        	    	}
                                            
													session.setAttribute("typecepaactivityscedule",col);	
											
												%>
                                              	<%=messages.getText("generic.activity.activitydata.type")%><span class="required">*</span>       
											</td>
	    									<td width="59%"  align="right">
												<html:select property="type" styleClass="input" style="width:155;" >
													<html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>												
												</html:select>   											
    									  </td>
									    </tr>
									    <tr class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
			      						<tr>
	    									<td width="41%" align="left" class="texts">
												<%											
													Collection colstate=new LinkedList();
													colstate.add(allObject);
                                         		   	Iterator iterstate=datatypes.findAll(DataTypeConstants.TYPECEPAACTIVITYSCHEDULESTATE,sys.getUserSessionData()).iterator();
                                          		  	while(iterstate.hasNext()){
                                            			GeneralDTData itemState=(GeneralDTData)iterstate.next();
                                            			colstate.add(new OptionItem(messages.getText(itemState.getResourceBundleId()),itemState.getResourceBundleId()));
                                           			}
                                            
													session.setAttribute("statecepaactivityscedule",colstate);	
											
												%>
                                              	<%=messages.getText("generic.activity.activitydata.state")%><span class="required">*</span>    
											</td>
	    									<td width="59%"  align="right">
												<html:select property="state" styleClass="input" style="width:155;" >
													<html:options collection="statecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>												
												</html:select>   											
    									  </td>
									    </tr>
									</table>
									<jsp:include  page="../../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
	                <td width="16">&nbsp;</td>
                	<td width="50%" valign="top">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
                    		<jsp:include page='<%="/driver/divorg.jsp??form=ReportGeneralTrainingForm&consult=true" %>'/>
	                     </table>
    	             </td>
				</tr>
	  			<tr>
					<jsp:include  page="../../component/spaceline.jsp" />
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
	</body>
<%	}	%>
</html:html>
