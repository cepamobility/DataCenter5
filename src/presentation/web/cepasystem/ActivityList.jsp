 <%@page language="java" 
	errorPage="../exception.jsp" 
	import="java.util.*"
	import="business.SystemAccess"
	import="business.datatype.*"
	import="business.administration.user.*"
	import="communication.datatype.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.ListResult"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="communication.administration.user.UserSessionData"  	
	import="communication.activity.tables.*" 
	
%>  

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.ADMINISTRATOR);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		session.setAttribute("url","cepasystem/ActivityList.jsp");

		RequestDispatcher dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
	
		Text messages=sys.getText();
		
		String startDay = (Util.empty(request.getParameter("startDay"))) ? "" : request.getParameter("startDay");
		String startMonth = (Util.empty(request.getParameter("startMonth"))) ? "" : request.getParameter("startMonth");
		String startYear = (Util.empty(request.getParameter("startYear"))) ? "" : request.getParameter("startYear");
		
		String endDay = (Util.empty(request.getParameter("endDay"))) ? "" : request.getParameter("endDay");
		String endMonth = (Util.empty(request.getParameter("endMonth"))) ? "" : request.getParameter("endMonth");
		String endYear = (Util.empty(request.getParameter("endYear"))) ? "" : request.getParameter("endYear");

		Integer pageNum = (Util.empty(request.getParameter("pageNum"))) ? new Integer(1) : new Integer(request.getParameter("pageNum"));
		
		String type = (Util.empty(request.getParameter("type"))) ? "" : request.getParameter("type");
		String country = (Util.empty(request.getParameter("country"))) ? "" : request.getParameter("country");
		String city = (Util.empty(request.getParameter("city"))) ? "" : request.getParameter("city");
		String status = (Util.empty(request.getParameter("status"))) ? "" : request.getParameter("status");
		
		int pageSize = (Util.empty(request.getParameter("pageSize"))) ? 10 : Integer.parseInt(request.getParameter("pageSize"));

		int first = (Util.empty(request.getParameter("first"))) ? 10 : Integer.parseInt(request.getParameter("first"));
		int count = pageSize;
		boolean all=!Util.empty(request.getParameter("all")) && request.getParameter("all").equals("true");
		if(all) {
		    count=-1;
		}else{
		    first=(pageNum.intValue()-1)*pageSize;
		}		

		ActivityReportFacade activityReportFacade = sys.getActivityReportFacade();
		DataTypeReportFacade datatypeReportFacade = sys.getDataTypeReportFacade();
		
		String link = "/datacenter/cepasystem/ActivityList.jsp"; 		
		ListResult list = activityReportFacade.findCepaSystemActivities(startDay,startMonth,startYear,endDay,endMonth,endYear,type,country,city,status,first,count,userSessionData);
		
		
%>		

	
<%@page import="business.activity.ActivityReportFacade"%><script language="JavaScript" type="text/javascript">
	function submitPagination(page,all) {
		document.forms['ActivitySearch'].all.value = all;
		document.forms['ActivitySearch'].pageNum.value = page;
		document.forms['ActivitySearch'].submit();
	}
	function setAction(form){
		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}else{
						valueCheckboxParamList=element.value+","+valueCheckboxParamList;
					}	
				}	
			} 
			
		}
		if(valueCheckboxParamList.length>0){
			form.list.value=valueCheckboxParamList;
			form.submit();
		}
	}
	function resetPageNum(){
		document.forms[2].resetPN.value = "true";
		document.forms[2].submit();
		return true;
	}
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
	</script>
	<script language="javascript" src='/datacenter/menu/js/driverFunctions.js' type='text/javascript' >
	</script>
	<script language="javascript" src='/datacenter/util/js/Prepared_Parameters.js' type='text/javascript' >
	</script>

		<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr class="guide1">
				<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
	  		</tr>
    	  	<tr>
				<jsp:include  page="../component/spaceline.jsp" /> 
	      	</tr>
    	  	<tr>
        		<td valign="top" width="60%" >
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.search")%>' />
					<form name="ActivitySearch" action="layout.jsp?refer=/cepasystem/ActivityList.jsp" method="post">
						<input type="hidden" name="pageNum" value='<%=String.valueOf(pageNum)%>'/>
						<input type="hidden" name="all" value='<%=(all) ? "all" : "" %>' >    
						<input type="hidden" name="pageSize" value='<%=pageSize%>'/>					
						<table width="100%"  border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="41%" align="left" class="texts">
									<%=messages.getText("generic.messages.initialdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
								</td>
								<td width="59%"  align="right">
    								<input type="text" name="startDay" id="dayId1" style="width:27;" class="input" styleId="dayId1" value='<%=startDay%>'/>&nbsp;/&nbsp;
						        	<input type="text" name="startMonth" id="monthId1" style="width:27;" class="input" styleId="monthId1" value='<%=startMonth%>'/>&nbsp;/&nbsp;
								    <input type="text" name="startYear" id="yearId1" style="width:40;" class="input" styleId="yearId1" value='<%=startYear%>'/>
						        </td>
	          					<td width="40"  align="right">
	          						<img src="./component/jscalendar-1.0/img.gif" id="calendar_button1" styleId="calendar_button1" style="cursor: pointer;"
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
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
	    					<tr>	  
	    						<td width="41%" align="left" class="texts">
									<%=messages.getText("generic.messages.lastdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
								</td>
	    						<td width="59%"  align="right">
	    							<input type="text" name="endDay" id="dayId2" style="width:27;" class="input" styleId="dayId2" value='<%=endDay%>'/>&nbsp;/&nbsp;
	    							<input type="text" name="endMonth" id="monthId2" style="width:27;" class="input" styleId="monthId2" value='<%=endMonth%>'/>&nbsp;/&nbsp;
	    							<input type="text" name="endYear" id="yearId2" style="width:40;" class="input" styleId="yearId2" value='<%=endYear%>'/>
	          					</td>
	          					<td width="40"  align="right">
									<img src="./component/jscalendar-1.0/img.gif" id="calendar_button2"  styleId="calendar_button2" style="cursor: pointer;"
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
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.activity.activitydata.type")%>
								</td>
								<td align="right" colspan="2">
									<select name="type" class="input" style="width:180;" >
										<option value=""><%=messages.getText("generic.messages.select")%></option>
<%			Collection types = datatypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData);
			Collections.sort((List)types);
			Iterator it = types.iterator();
           	while(it.hasNext()){
              	GeneralDTData itemType=(GeneralDTData)it.next();
%>                            			<option value='<%=itemType.getResourceBundleId()%>' <%=(type.equals(itemType.getResourceBundleId())) ? "selected" : "" %>><%=messages.getText(itemType.getResourceBundleId())%></option>
<%		   	}
%>									</select>   											
    					  	    </td>																									
							</tr>		

				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="left">												
									<%=messages.getText("generic.activity.activitydata.country")%>													
								</td>
								<td align="right" colspan="2">
									<select name="country" class="input" style="width:180;" >
										<option value=""><%=messages.getText("generic.messages.select")%></option>
<%				it = userSessionData.getFilter().getLastLevelItems(userSessionData.getFilter().getGeoLevelCount(),null,null);
                while(it.hasNext()){
                 	GeoItemData item=(GeoItemData)it.next();
%>					  					<option value='<%=String.valueOf(item.getId())%>' <%=(country.equals(String.valueOf(item.getId()))) ? "selected" : "" %>><%=messages.getText(item.getName())%></option>
<%              }
%>									</select>   											
	    						</td>				
	    					</tr>	
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
	    					<tr class="texts">
								<th align="left"> 
									<%=messages.getText("generic.activity.activitydata.city")%> 
								</th>
								<td align="right" colspan="2">
									<input type="text" name="city" style="width:180" class="input" value='<%=city%>'/>
								</td>
							</tr>
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="left">
									<%=messages.getText("generic.activity.activitydata.state")%>
								</td>
								<td align="right" colspan="2">
									<select name="status" class="input" style="width:180;" >
										<option value=""><%=messages.getText("generic.messages.select")%></option>
<%		         	Collection states = datatypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYSCHEDULESTATE,userSessionData);
					Collections.sort((List)states);
					it = states.iterator();
					while(it.hasNext()){
					  	GeneralDTData item = (GeneralDTData)it.next();
%>					  					<option value='<%=item.getResourceBundleId()%>' <%=(status.equals(item.getResourceBundleId())) ? "selected" : "" %>><%=messages.getText(item.getResourceBundleId())%></option>
<%         	        }
%>									</select>   											
    							</td>																									
							</tr>		
				    	  	<tr>
								<jsp:include  page="../component/spaceline.jsp" /> 
					      	</tr>
							<tr class="texts">
								<td align="right" colspan="3">
									<input name="button" type="submit" value='<%=messages.getText("generic.messages.search")%>' class="input"/>
								</td>
							</tr>	
						</table>
					</form>
   					<jsp:include page="../component/utilityboxbottom.jsp"/>	
			    </td> 
	        	<td width="2%" >&nbsp;</td>
    	    	<td width="38%" valign="top" >
    	    		<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.options")%>' />
<%			if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY))	{
%>						<img align="middle" border="0" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;
						<a href="layout.jsp?refer=/cepasystem/ActivityCreate.jsp?type=create" class="texts"align="center">						
							<%=messages.getText("generic.messages.addcepaactivityschedule")%>					
						</a> 
<%			}	
%>					<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
			</tr>		
	      	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
      		</tr>
	      	<tr>
    	    	<td colspan="3"> 
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.activity.activitylist.systemactivities")%>' />
					<table width='100%' border="0" align='center' cellpadding="0" cellspacing="0">
      					<tr>
        					<td width="100%">
<%		if (all) {
    		pageSize = list.getTotal(); 
		}
%>								<form name="exclusion"  action="layout.jsp?refer=/cepasystem/deleteActivity.jsp"  method="post">
		    						<table width="100%" border="0">
              							<tr align="center">
<%		if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	
%>											<td width='4%' class="guide2"></td>
<%		}
%>						                	<td  width="12%" class="guide2"><%=messages.getText("generic.activity.activitydata.date")%><%=" "+messages.getText("generic.messages.dateformat")%></td>
<% 		String[] principalLabels = userSessionData.getFilter().getPrincipalLabels();
		for (int z = 0; z < principalLabels.length; z++){ 
%>					                		<td width="12%"	class="guide2"><%=messages.getText(principalLabels[z])%></td>
<% 		} 
%>					                		<td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.type")%></td>
						                	<td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.city")%></td>					                					                
						                	<td width="12%"	class="guide2"><%=messages.getText("generic.activity.activitydata.state")%></td>					                
            	  						</tr>
<% 		if(list.getTotal() == 0) {
%>             							<tr align="left">
											<td colspan="10">	
          										<%=messages.getText("generic.messages.notresult")%>
          									</td>
          								</tr>		
<%		}else {
			it = list.getElements().iterator();
			int counter = 0;
			while(it.hasNext() && (counter < pageSize || all)){
				counter++;
    			ActivityListData activity=(ActivityListData)it.next();
%>		        						<tr align=center align="right" class='<%=((counter % 2)==0) ? "lineone" : "linetwo"%>'>                
<%				if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {
%>	            							<td width='4%' align="center"><input type='checkbox' name='<%=activity.getActivityId()%>' value='<%=activity.getActivityId()%>' ></td>
<%				}					
%>											<td  width="12%" align="right">
	              								<a href='layout.jsp?refer=/cepasystem/ActivityView.jsp?type=activity&id=<%=activity.getActivityId()%>' > 
													<%=Util.formatDate(activity.getDate())%> 
												</a>
											</td>
						                	<td  width="12%" align="right"><%=messages.getText(activity.getType())%> </td>				
						                	<td  width="12%" align="right"><%=activity.getCity()%> </td>				
						                	<td  width="12%" align="right"><%=messages.getText(activity.getStatus())%> </td>										                						                
                						</tr>
<%			}
		}
%>
              						</table>
              						<input type="hidden" name="list" />         					
              						<input type="hidden" name="type" value="list" />         					
	            				</form>
	            			</td>
	            		</tr>
			      		<tr>
	            			<td>		
	            				<table width='100%'  border="0" cellpadding="0" cellspacing="0" class="default">
    	          					<tr>
        	        					<td class="linetwo" align="right">
<%			if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {	%>
            			   					<input name="button2" type="button" onClick="javascript:setAction(document.forms['exclusion']);" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>                    					
<%			}															
%>			        					</td>
            	  					</tr>
          						</table>
          					</td>
	      				</tr>
<%			if(userSessionData.checkPermission(SecurityConstants.IMESEVI_UPDATE_ACTIVITY)) {
%>	      				<tr>
        					<td>
<%				Hashtable paginationParameters = new Hashtable();
				paginationParameters.put("startDay",startDay);
				paginationParameters.put("startMonth",startMonth);
				paginationParameters.put("startYear",startYear);
			
				paginationParameters.put("endDay",endDay);
				paginationParameters.put("endMonth",endMonth);
				paginationParameters.put("endYear",endYear);
			
				paginationParameters.put("type",type);
				paginationParameters.put("country",country);
				paginationParameters.put("city",city);
				paginationParameters.put("status",status);
				session.setAttribute("paginationParameters",paginationParameters);
%>								<jsp:include page='<%="../util/pagination2.jsp?size="+pageSize+"&num="+String.valueOf(pageNum)+"&total="+String.valueOf(list.getTotal())+"&link="+link%>' />        
        					</td>
      					</tr>
<%			}	
%>					</table>
	   				<jsp:include page="../component/utilityboxbottom.jsp"/>	
				</td>
      		</tr>
	  </table>
<%	}
%>
