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
	import="communication.datatype.tables.AlertTypeDataType"
	import="communication.datatype.tables.AlertPriorityDataType"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"	
 	import="communication.administration.user.*" 	
	import="communication.administration.user.UserSessionData"  
	
%>  

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<!--  alertList.jsp -->
<%!
	RequestDispatcher dispatcher; 
	SystemAccess sys;	
	UserSessionData userSessionData; 
	Text messages; 
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.ALERT_VIEW);
  
	session.removeAttribute("AlertForm");
	
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","alert/alertList.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		messages=sys.getText();
		
		String startDay = (Util.empty(request.getParameter("startDay"))) ? "" : request.getParameter("startDay");
		String startMonth = (Util.empty(request.getParameter("startMonth"))) ? "" : request.getParameter("startMonth");
		String startYear = (Util.empty(request.getParameter("startYear"))) ? "" : request.getParameter("startYear");
		
		String endDay = (Util.empty(request.getParameter("endDay"))) ? "" : request.getParameter("endDay");
		String endMonth = (Util.empty(request.getParameter("endMonth"))) ? "" : request.getParameter("endMonth");
		String endYear = (Util.empty(request.getParameter("endYear"))) ? "" : request.getParameter("endYear");

		Integer pageNum = (Util.empty(request.getParameter("pageNum"))) ? new Integer(1) : new Integer(request.getParameter("pageNum"));
		
		String type = (Util.empty(request.getParameter("type"))) ? "" : request.getParameter("type");
		String priority = (Util.empty(request.getParameter("priority"))) ? "" : request.getParameter("priority");
		String finalized = (Util.empty(request.getParameter("finalized"))) ? "" : request.getParameter("finalized");
		String email = (Util.empty(request.getParameter("email"))) ? "" : request.getParameter("email");
		int pageSize = (Util.empty(request.getParameter("pageSize"))) ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		
		int first = (Util.empty(request.getParameter("first"))) ? 10 : Integer.parseInt(request.getParameter("first"));
		int count = pageSize;
		boolean all=!Util.empty(request.getParameter("all")) && request.getParameter("all").equals("true");
		if(all) {
		    count=-1;
		}else{
		    first=(pageNum.intValue()-1)*pageSize;
		}		

		DataTypeReportFacade datatypeReportFacade = sys.getDataTypeReportFacade();
		AlertReportFacade alertReportFacade = sys.getAlertReportFacade();
		String link = "/datacenter/alert/alertList.jsp"; 
		Integer finalize;
		/*if ((finalized!=null)&&(!finalized.equals("")))
			finalize=new Integer(finalized);
		else
			finalize=new Integer(AlertConstants.ACTIVE);
		*/
		finalize=null;
		
		ListResult list = alertReportFacade.findAlertList(
				startDay,startMonth,startYear,endDay,endMonth,endYear,
				type,priority,email,finalize,first,count,userSessionData);
		
		System.out.println("Total de Alertas: "+list.getTotal());
		
%>		

	<script language="JavaScript" type="text/javascript">
	
	
	function preparedCheckboxParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.checkboxParamList.value=valueCheckboxParamList;
		if(valueCheckboxParamList.length>0){
			return true;
		}
		else{
			return false;
		}
	}
	
	function submitPagination(page,all){
		document.forms['page'].all.value = all;
		document.forms['page'].pageNum.value = page;
		document.forms['page'].submit();
	}
	
	function setAction(form){
		if(preparedCheckboxParameters(form)) {
			form.submit();
		}
	}
	
	function resetPageNum(){
		document.forms['page'].resetPN.value = "true";
		document.forms['page'].submit();
		return true;
	}
	
 	
	</script>
	

		<table width="768px" border="0" align="center" cellpadding="0" cellspacing="0">
			
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.alert.list")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" /> 
	      	</tr>
	      	
	    
	    

<%		if (sys.getCorporationId() == CorporationData.FORESTALORIENTAL ){ 
			// Opciones para FORESTAL ORIENTAL
%>
			<!--  Opciones -->
			<tr>
      			<jsp:include page="../component/spaceline.jsp" />
      		</tr>
      		<tr>
    	    	<td colspan="3"> 
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
        							<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;
									<a href='layout.jsp?refer=/alert/AlertFOSADriversEdit.jsp?target=alertList' class="texts" >						
											<%=messages.getText("generic.messages.addfosadriveralert")%>					
									</a> 
        					</td>
        				</tr>
        			</table>
        			<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
      		</tr>
        	<tr>
      			<jsp:include page="../component/spaceline.jsp" />
      		</tr>
      			
<% 		}  // fin opciones bayer
%>
	      	
<%		if (sys.getCorporationId() == CorporationData.BAYER ){ 
			// Opciones para BAYER
%>
			<!--  Opciones -->
			<tr>
      			<jsp:include page="../component/spaceline.jsp" />
      		</tr>
      		<tr>
    	    	<td colspan="3"> 
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
        							<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;
									<a href='layout.jsp?refer=/alert/AlertCepaTipsEdit.jsp?target=alertList' class="texts" >						
											<%=messages.getText("generic.messages.addcepatipsalert")%>					
									</a> 
        					</td>
        				</tr>
        			</table>
        			<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
      		</tr>
        	<tr>
      			<jsp:include page="../component/spaceline.jsp" />
      		</tr>
      			
<% 		}  // fin opciones bayer
%>
		
      		
      		<!-- Listado de Alertas para la Corporacion: -->
      		<tr>
      			<jsp:include page="../component/spaceline.jsp" />
      		</tr>
	      	<tr>
    	    	<td colspan="3"> 
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.alert.alertList.systemalert")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
<%		if (list != null && all) {
    			pageSize = list.getTotal(); 
			}
%>								
									<form name="exclusion"  action="Alertdelete.do"  method="post">
		    						<table width="100%" border="0">
              							<tr align="center">
											<td width='3%' class="guide2"></td>
					                		<td  width="10%" class="guide2"><%=messages.getText("generic.alert.search.startdate")%><%=" "+messages.getText("generic.messages.dateformat")%></td>
					                		<td  width="10%" class="guide2"><%=messages.getText("generic.alert.search.lastdate")%><%=" "+messages.getText("generic.messages.dateformat")%></td>
						                	<td width="10%"	class="guide2"><%=messages.getText("generic.alert.search.type")%></td>
					                		<td width="47%"	class="guide2"><%=messages.getText("generic.alert.search.subject")%></td>
					                		<td width="10%"	class="guide2"><%=messages.getText("generic.alert.search.finalized")%><%=" "+messages.getText("generic.messages.dateformat")%></td>				                
            	  						</tr>
<% 		if(list == null || list.getTotal() == 0) {
%>             							<tr align="left">
											<td colspan="10">	
          										<%=messages.getText("generic.messages.notresult")%>
          									</td>
          								</tr>		
<%		}else {
				if (list != null && list.getElements() != null){
					Iterator it3 = list.getElements().iterator();
					int counter = 0;
					while(it3.hasNext() && (counter < pageSize || all)){
						counter++;
    					AlertData alert = (AlertData)it3.next();
%>		        						<tr align=center align="right" class='<%=((counter % 2)==0) ? "lineone" : "linetwo"%>'>                
<%					if(alert.getEnddate()==null) {
%>	            							<td width='3%' align="center"><input type='checkbox' name='<%=alert.getAlertId()%>' value='<%=alert.getAlertId()%>' ></td>
<%					}else if(alert.getEnddate()!=null){ 			
%>											<td width='3%' align="center" ><input type='checkbox' name='<%=alert.getAlertId()%>' value='<%=alert.getAlertId()%>' disabled></td>
<%					}
%>											<td  width="10%" align="right">
													<% if (alert.getAlertType().getResourceBundleId().equalsIgnoreCase(AlertConstants.ALERTTYPE_CEPA_TIPS)){ %>
														<a href='layout.jsp?refer=/alert/AlertCepaTipsEdit.jsp?id=<%=alert.getAlertId()%>' >
														<%=Util.formatDate(alert.getStartdate())%>
														</a>
													<% }else{ %>
															<%if (sys.getCorporationId() == CorporationData.FORESTALORIENTAL ){ 
																			// Opciones para FORESTAL ORIENTAL
															%>
																<a href='layout.jsp?refer=/alert/AlertFOSADriversEdit.jsp?alertId=<%=alert.getAlertId()%>' >
				              									<%=Util.formatDate(alert.getStartdate())%>
				              									</a>
															<% }else{ %>
				              									<a href='layout.jsp?refer=/alert/AlertView.jsp?id=<%=alert.getAlertId()%>' >
				              									<%=Util.formatDate(alert.getStartdate())%>
			              										</a>
		              									<% } %>
		              								<% } %>
		        							</td>
											<td  width="10%" align="right">
												<% if (alert.getAlertType().getResourceBundleId().equalsIgnoreCase(AlertConstants.ALERTTYPE_CEPA_TIPS)){ %>
														<a href='layout.jsp?refer=/alert/AlertCepaTipsEdit.jsp?id=<%=alert.getAlertId()%>' >
															<%=(alert.getLastdate()!=null)? Util.formatDate(alert.getLastdate() ) : messages.getText("generic.alert.na")%>
														</a>
												<% }else{ %>
												
														<%if (sys.getCorporationId() == CorporationData.FORESTALORIENTAL ){ 
																			// Opciones para FORESTAL ORIENTAL
															%>
																<a href='layout.jsp?refer=/alert/AlertFOSADriversEdit.jsp?alertId=<%=alert.getAlertId()%>' >
				              										<%=(alert.getLastdate()!=null)? Util.formatDate(alert.getLastdate() ) : messages.getText("generic.alert.na")%>
				              									</a>
														<% }else{ %>
				              									<a href='layout.jsp?refer=/alert/AlertView.jsp?id=<%=alert.getAlertId()%>' >
			              											<%=(alert.getLastdate()!=null)? Util.formatDate(alert.getLastdate() ) : messages.getText("generic.alert.na")%>
			              										</a>
		              									<% } %>
		              									
	              										
	              								<% } %>
              								</td>
						                	<td width="10%" align="right">
						                		<%=(alert != null && alert.getAlertType() != null) ?messages.getText(alert.getAlertType().getResourceBundleId()):"-"%> 
						                	</td>
						                	<td width="47%" align="right">
						                			<%=(alert != null && alert.getSubject() != null) ?alert.getSubject():"-"%> 
						                	</td>
						                	<td width="10%" align="right">
						                		<%=(alert != null && alert.getEnddate()!=null)? Util.formatDate(alert.getEnddate()):"-"%> 
						                	</td>
				                		</tr>
<%				}
				}
		}
%>
              						</table>
              						<input type="hidden" name="checkboxParamList" />         					
	            				</form>
	            			</td>
	            		</tr>
			      		<tr>
	            			<td>		
	            				
	            				<table width='100%'  border="0" cellpadding="0" cellspacing="0" class="default">
    	          					<tr>
        	        					<td class="lineone" align="right">
            			   					<button type="button"  onClick="javascript:setAction(document.forms['exclusion']);"  class="input"><%=messages.getText("generic.messages.finalize")%></button>                    					
			        					</td>
            	  					</tr>
          						</table>
          					
          					</td>
	      				</tr>
    	  				<tr>
        					<td>
<%			Hashtable paginationParameters = new Hashtable();
			paginationParameters.put("startDay",startDay);
			paginationParameters.put("startMonth",startMonth);
			paginationParameters.put("startYear",startYear);
			paginationParameters.put("endDay",endDay);
			paginationParameters.put("endMonth",endMonth);
			paginationParameters.put("endYear",endYear);
			paginationParameters.put("type",type);
			paginationParameters.put("priority",priority);
			paginationParameters.put("email",email);
			paginationParameters.put("finalized",finalized);
			session.setAttribute("paginationParameters",paginationParameters);
%>								<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(list.getTotal())+"&link="+link%>' />        
        					</td>
      					</tr>
					</table>
	   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
      		</tr>
	  </table>
	  
	  <form name='page' method="post" action='layout.jsp?refer=/alert/alertList.jsp'>
		<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'>    
		<input type="hidden" name="all" value='<%=String.valueOf(all)%>'>    
    	<input type="hidden" name="pageSize" value='<%=String.valueOf(pageSize)%>'/>
    </form>
    
<%	}
	
%>
		
    						


