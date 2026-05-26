<%@page language="java"
   errorPage="../../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="presentation.translator.web.activity.schedule.*"	
	import="java.util.*"
 	import="communication.administration.user.*" 		
	import="communication.datatype.*"
	import="communication.datatype.tables.*" 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

<%!
	Text messages;
	SystemAccess sys;
	UserSessionData userSessionData;	 
	DataTypeReportFacade datatypes;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess"); 

	userSessionData=sys.getUserSessionData();
	String check = Util.checkPermission(sys,SecurityConstants.VIEW_CEPASCHEDULE_ACTIVITY_LIST);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();		
		datatypes=sys.getDataTypeReportFacade();		
		ActivitySearchForm form = null;
		int size = Integer.parseInt(request.getParameter("count"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		if(session.getAttribute("activityForm") != null) {
		    form = (ActivitySearchForm)session.getAttribute("activityForm");
		}else {
		    form = new ActivitySearchForm(request.getParameter("form"),request.getParameter("activity"),request.getParameter("name"),pageNum,size,request.getParameter("link"));
			session.setAttribute("ActivitySearchForm",form);
		}
		Collection list = new LinkedList();
		Iterator iter = list.iterator();
		int count = 10;
		String all = "false"; 
%>
	
<script language="JavaScript" src="../../util/js/select_component.js" type="text/javascript">
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
	
function cambio(parent,child){
	var parentCombo = document.forms[2].elements[parent];
	var childCombo = document.forms[2].elements[child];
	if(parentCombo.options[parentCombo.selectedIndex].value != "") {
		var divi = parentCombo.options[parentCombo.selectedIndex].text+parentCombo.options[parentCombo.selectedIndex].value;
		var list = document.forms[2].elements[divi];
		childCombo.options.length=list.options.length;
		for(i=0; i < list.length; i++) {
			childCombo.options[i].value = list.options[i].value;
			childCombo.options[i].text = list.options[i].text;
		}
	}else {
		childCombo.options.length=1;
		childCombo.options[2].value = "";
		childCombo.options[2].text = '<%=messages.getText("generic.messages.select")%>';
	}
}

function doSubmit(all,page) {
	document.forms[0].all.value = all;
	document.forms[0].first.value = page;
	document.forms[0].submit();
}

function resetPageNum(){
	document.forms[2].resetPN.value = "true";
	document.forms[2].submit();
	return true;
	}

</script>
<html:form action="Activitysearch">
	<html:hidden property="all"/>
	<html:hidden property="pageNum"/>
	<html:hidden property="count"/>
	<html:hidden property="resetPN"/>
  <body>
  		<!-- simpleSearch.jsp -->
	  <table align="center" width="100%" cellpadding="0" cellspacing="0">
		  <tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left" class="texts">
						<%=messages.getText("generic.activity.activitydata")%>							
					</td>
				</tr>
  				<tr>
    				<td>
						<table width="100%">												
<%											
						List col=new LinkedList();
						col.add(new OptionItem(messages.getText("generic.messages.select"),""));
						iter = datatypes.findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData).iterator();
                        while(iter.hasNext()){
                        	GeneralDTData itemType=(GeneralDTData)iter.next();

	                    	// Commentary Drive segun Coproracion
                    		if (itemType.getResourceBundleId().trim().
                  				equals("datatype.scheduledactivitytype.commentarydrive"))
                    		{
                            	if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(1))) // 1 = Monsanto
                            	{
                            		// Si es Monsanto muestro Commentary Drive	                                            		
									// System.out.println(messages.getText(itemType.getResourceBundleId())+" " +itemType.getResourceBundleId());
                            		//System.out.println("CEPAACtivityScheduleCreate - Monsanto");
                            		if (userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY) )
									{
                            			col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
									}
                            	}
                    		}
                    		else if (itemType.getResourceBundleId().trim().
                  				equals("datatype.scheduledactivitytype.commentarydriveother"))
                    		{
                        		// Si no es monsanto muestro el Commentary Drive de Others
                        		if (!sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(1))) // 1 = Monsanto
                            	{
                            		// Commentary Drive Others                                            		
                            		//System.out.println("CEPAACtivityScheduleCreate - J&J");
									//System.out.println(messages.getText(itemType.getResourceBundleId())+" " +itemType.getResourceBundleId());
									if (userSessionData.checkPermission(SecurityConstants.CREATE_CD_ACTIVITY) )
									{
                            			col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
									}
                            	}
                        		
                    		}                    		
                    		else
                    		{
                    			// Las demás Actividades las muestro para todas las empresas
								// System.out.println(messages.getText(itemType.getResourceBundleId())+" " +itemType.getResourceBundleId());
                    	       	col.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
                    		}
                    	}
					   	Collections.sort(col);                   
						session.setAttribute("typecepaactivityscedule",col);	
						
						Collection colstate=new LinkedList();
						colstate.add(new OptionItem(messages.getText("generic.messages.select"),""));							
                        Iterator iterstate=datatypes.findAll(DataTypeConstants.TYPECEPAACTIVITYSCHEDULESTATE,userSessionData).iterator();
   	                    while(iterstate.hasNext()){
       	                   	GeneralDTData itemState=(GeneralDTData)iterstate.next();
       	                   	colstate.add(new OptionItem(messages.getText(itemState.getResourceBundleId()),itemState.getResourceBundleId()));
               	        }
                                       
						session.setAttribute("statecepaactivityscedule",colstate);												
						
						Collection colCountries=new LinkedList();
						colCountries.add(new OptionItem(messages.getText("generic.messages.select"),""));
						iter=sys.getUserSessionData().getFilter().getLastLevelItems(sys.getUserSessionData().getFilter().getGeoLevelCount(),null,null);
                        while(iter.hasNext()){
                             GeoItemData item=(GeoItemData)iter.next();
                             colCountries.add(new OptionItem(messages.getText(item.getName()),String.valueOf(item.getId())));
                         }
						Collections.sort((List)colCountries);
						session.setAttribute("countries",colCountries);																							
%>												
									<tr>
    									<td width="41%" align="left" class="texts">
											<%=messages.getText("generic.messages.initialdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
										</td>
    									<td width="59%"  align="right">
    									    <html:text property="initialDay" style="width:27;" styleClass="input" styleId="dayId1"/>&nbsp;/&nbsp;
						        			<html:text property="initialMonth" style="width:27;" styleClass="input" styleId="monthId1"/>&nbsp;/&nbsp;
								            <html:text property="initialYear" style="width:40;" styleClass="input" styleId="yearId1"/>
								        </td>
			          					<td width="40"  align="right">
			          						<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer;"
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
	    								<td width="41%" align="left" class="texts">
											<%=messages.getText("generic.messages.lastdate")%><%=" ("+messages.getText("generic.messages.dateformat")+")"%>
										</td>
	    								<td width="59%"  align="right">
	    								    <html:text property="lastDay" style="width:27;" styleClass="input" styleId="dayId2"/>&nbsp;/&nbsp;
							        		<html:text property="lastMonth" style="width:27;" styleClass="input" styleId="monthId2"/>&nbsp;/&nbsp;
									        <html:text property="lastYear" style="width:40;" styleClass="input" styleId="yearId2"/>
			          					</td>
			          					<td width="40"  align="right">
			          						<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button2" style="cursor: pointer;"
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
										<tr class="texts">
											<td align="left">
												<%=messages.getText("generic.activity.activitydata.type")%>
											</td>
											<td align="right">
												<html:select property="type" styleClass="input" style="width:155;" >
													<html:options collection="typecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>												
												</html:select>   											
    								  	    </td>																									
										</tr>		
										<tr class="texts">
											<td align="left">												
												<%=messages.getText("generic.activity.activitydata.country")%>													</td>
											<td align="right">
												<html:select property="country" styleClass="input" style="width:155;" >
													<html:options collection="countries" property="value" labelProperty="label" styleClass="input"/>												
												</html:select>   											
	    									</td>				
	    								<tr class="texts">
								            <th align="left"> 
								            	<%=messages.getText("generic.activity.activitydata.city")%> 
								            </th>
								            <td align="right">
								            	<html:text property="city" style="width:155" styleClass="input"/>
								            </td>
								        </tr>
								        <% 
								        	// El usr gerente no elige el estado de la actividad ya que 
								        	// siempre busca por actividades dictadas
									        if (!userSessionData.isGerente()) {
								        %>			    									  																					
											<tr class="texts">
												<td align="left">
													<%=messages.getText("generic.activity.activitydata.state")%>
												</td>
												<td align="right">
													<html:select property="state" styleClass="input" style="width:155;" >
														<html:options collection="statecepaactivityscedule" property="value" labelProperty="label" styleClass="input"/>												
													</html:select>   											
    									  		</td>																									
											</tr>		
										<%
									        }
										%>	
									</table>
									</td>						  			
							  </tr>					  			
			  	</table>			
				</td>
				</tr>
		  		<tr>
					<jsp:include  page='<%="../component/spaceline.jsp" %>'/>
				</tr>

		  <tr>
		    <td valign="top">&nbsp;</td>
		    <td valign="top">&nbsp;</td>
		    <td valign="bottom" align="right">
			    <html:submit onclick="resetPageNum();" value='<%=messages.getText("generic.messages.search")%>' styleClass="input" />
		    </td>
	    </tr>
		</table>
		<html:hidden property="form" />
		<html:hidden property="activityId" />
		<html:hidden property="name" />
  </body>
</html:form>
<%		} %>
</html:html>