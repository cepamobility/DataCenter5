<%@page language="java"
	errorPage="../exception.jsp"
    import="java.util.*"
    import="business.*"
    import="communication.language.*"
    import="communication.security.*"
    import="communication.datatype.*"
    import="communication.datatype.tables.*"
    import="communication.administration.user.*"    
    import="communication.cenapra.tables.*"
    import="presentation.translator.web.util.*"
    import="presentation.translator.web.cenapra.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
    Text messages; SystemAccess sys; 
    UserSessionData userSessionData;
    RequestDispatcher dispatcher;
    ActivityData data;
%>
<%
   
    sys=(SystemAccess)session.getAttribute("SystemAccess");
    userSessionData=sys.getUserSessionData();
    String check = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_ACTIVITY);
    if(check!=null)  {
        response.sendRedirect(request.getContextPath()+check);
    }else {
        session.setAttribute("url","cenapra/ActivityCreate.jsp");           
       
        dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
        dispatcher.include(request,response);

        messages=sys.getText();       

        String action = "/CENAPRAActivityCreate";
        String formName = "CENAPRAActivityCreateForm";
        String title = "generic.messages.addcepaactivityschedule";

		Collection typesItems = new LinkedList();
		typesItems.add(new OptionItem(messages.getText("generic.messages.select"),""));
		Iterator iter=sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData).iterator();
		while(iter.hasNext()){
        	GeneralDTData itemType=(GeneralDTData)iter.next();
        	typesItems.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
		}
       	session.setAttribute("types",typesItems);
        
		Collection colState = new LinkedList();
        colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.prevista"),"datatype.scheduledactivitystate.prevista"));                                         
        
        if(request.getParameter("type").equals("edit")) {
            action = "/CENAPRAActivityEdit";
            formName = "CENAPRAActivityEditForm";
            title = "generic.messages.editcepaactivityschedule";
           	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.ensupervision"),"datatype.scheduledactivitystate.ensupervision"));            		                   	
           	colState.add(new OptionItem(messages.getText("datatype.scheduledactivitystate.dictada"),"datatype.scheduledactivitystate.dictada"));            		
            String id = request.getParameter("id");
            if(Util.empty(id)) {
                id = (String)session.getAttribute("id");
            }
            if(id != null) {
                data = sys.getCENAPRAReportFacade().getActivity(new Integer(id),sys.getUserSessionData());
                CENAPRAActivityForm form = new CENAPRAActivityForm(data); 
                session.setAttribute("CENAPRAActivityEditForm",form);
            }
        }
        
        session.setAttribute("statecepaactivityscedule",colState);   
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
	</script>
	<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<html:form action='<%=action%>'>
<%		if(request.getParameter("type").equals("edit")) {
%>		<html:hidden property="id" value='<%=String.valueOf(data.getId())%>' />	
<%		}
%>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        	<tr class="guide1">
            	<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText(title)%>' />
	    	</tr>
    	    <tr>
        	    <jsp:include  page="../component/spaceline.jsp" />
	        </tr>
    	    <tr>
        	    <td valign="top">           
            	 	<table width="100%" cellpadding="0" cellspacing="0" border="0">             
                		<tr>
                 			<td valign="top" colspan="3">
                    			<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.administrativedata")%>' />
                        		<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        			<tr>
                            			<td width="100%" align="right" valign="top">
                                    		<table width="100%" height="20" border="0" cellpadding="0" cellspacing="1">
                                       			<tr>
                                            		<td align="left" class="texts">
                                                		<%=messages.getText("generic.activity.activitydata.date")+" ("+messages.getText("generic.messages.dateformat")+")"%><span class="required">*</span>
                                            		</td>
                                            		<td align="right">
                                                		<html:text property="date"  style="width:20;" styleClass="input" maxlength="2" styleId="dayId1"/>&nbsp;/&nbsp;
                                                		<html:text property="month" style="width:20;" styleClass="input" maxlength="2" styleId="monthId1"/>&nbsp;/&nbsp;
                                                		<html:text property="year" style="width:40;" styleClass="input"  maxlength="4" styleId="yearId1"/>
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
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
                		                        <tr>
                        		                    <td align="left" class="texts">
                                             			<%=messages.getText("generic.activity.activitydata.type")%><span class="required">*</span>
                                            		</td>
                                            		<td align="right">
		                                        		<html:select property="type" styleClass="input" style="width:200;" >
                                                    		<html:options collection="types" property="value" labelProperty="label" styleClass="input"/>                                             
                                                		</html:select>                                              
                                         			</td>
                                        		</tr> 
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
		          								<tr>
        		    								<th align="left" class="texts">
               											 <%=messages.getText("cenapra.activity.instructor")%><span class="required">*</span>
               										</th>
           											 <td align="right">
        												<html:text property="instructor" style="width:255;" styleClass="input" />
		            								</td>
        		 								 </tr>                                       
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
		          								<tr>
        		    								<th align="left" class="texts">
               											 <%=messages.getText("generic.activity.activitydata.city")%><span class="required">*</span>
               										</th>
           											 <td align="right">
        												<html:text property="city" style="width:200;" styleClass="input" />
		            								</td>
        		 								 </tr>                                       
                                           		<tr><td height="10" colspan="2"><img src="x" width="1" height="1"></td></tr>
                		                        <tr>
                        		                    <td align="left" class="texts">
                                             			<%=messages.getText("generic.activity.activitydata.state")%><span class="required">*</span>
                                            		</td>
                                            		<td align="right">
<%		if(request.getParameter("type").equals("edit")) {
%>		                                        		<html:select property="state" styleClass="input" style="width:200;" >
                                                    		<html:options collection="statecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>                                             
                                                		</html:select>                                              
<%		}else {
%>             											<html:hidden property="state" value="datatype.scheduledactivitystate.prevista" />
														<span class="linetwo"><%=messages.getText("datatype.scheduledactivitystate.prevista") %></span>                                   		
<%		}
%>                                         			</td>
                                        		</tr> 
                                       		</table>   
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
    						<td valign="top">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
									<jsp:include page='<%="divgeo"+sys.getUserSessionData().getFilter().getGeoLevelCount()+".jsp?form="+formName+"&consult=false"%>' />        
								</table>
    						</td>
    						<td width="16">&nbsp;</td>
    						<td valign="top">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">
            	  					<jsp:include page='<%="divorg.jsp?form="+formName+"&consult=false"%>' />           
            	  				</table>
    						</td>
  						</tr>
   						<tr>
            				<jsp:include  page="../component/spaceline.jsp" />
    					</tr>
                   		<tr>
                    		<td width="100%" colspan="3">
								<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("cenapra.data.observations")%>' />
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" valign="top">
		      						<tr  class="linetwo">
                                		<td colspan="2">
                                    		<html:textarea property="observations" cols="80" />
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
                    		<td width="100%" colspan="3">
                        		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.driver.comment")%>' />
                        		<table width="100%" cellpadding="0" cellspacing="0">
                            		<tr>
                                		<td>
                                    		<html:textarea property="comment" cols="80" />
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
                        	<td height="18" colspan="3" valign="top" align="right">
                       			<html:submit styleClass="input" value='<%=messages.getText("generic.messages.save")%>' />                                                  
                       			<html:cancel styleClass="input"  value='<%=messages.getText("generic.messages.cancel")%>' />                                                  
                           		<br>
            		        </td>
                		</tr>                                         
   					</table>
   				</td>
   			</tr>
   		</table>	
	</html:form>  
<%	}   
%>   
