<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.administration.dpa.CorrectiveActionImageDPAData"%>
<%@page import="communication.administration.dpa.CorrectiveActionDPAData"%>
<%@page import="presentation.translator.web.dpa.DPACorrectiveActionForm"%>
<%@page import="communication.administration.dpa.UnconformityItemDPAData"%>
<%@page import="communication.administration.dpa.UnconformityDPAData"%>
<%@page import="communication.administration.dpa.UnconformityItemTypeDPADataType"%>
<%@page import="communication.datatype.DataTypeConstants"%>
<%@page import="communication.driver.tables.TelematicControlData"%>
<%@page import="persistence.driver.tables.TelematicControl"%>
<%@page import="communication.administration.dpa.UnconformityTypeDPADataType"%>
<%@page import="presentation.translator.web.dpa.DPAUnconformityForm"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.vehicle.tables.*"
	import="communication.util.*"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="presentation.translator.web.vehicle.*"
 	import="communication.administration.user.*" 	
 	import="communication.datatype.tables.ActivityTypeDataType"
	import="business.vehicle.VehicleReportFacade"
	import="java.util.*"	
	import="org.apache.struts.action.DynaActionForm"
	import="communication.activity.tables.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	RequestDispatcher dispatcher; 
	Text messages; SystemAccess sys;
	UserSessionData userSessionData; 
	VehicleCheckData vehicleCheck;
	TelematicControlData telematicControl;
	VehicleData vehicleData;
	String id;
	String unconformitydpaid;
	String preSelected;
	UnconformityDPAData data;
	Collection correctiveActions;
	CorrectiveActionDPAData cData;
%>
<%
	//PARA EDITAR id=488552&mode=edit, donde 488552 es el id de la inconformidad
	//PARA NUEVO  TELEMATICCONTROL telec=111&mode=new&type=UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL donde 111 es el id del control telematico
	//PARA NUEVO  CHECK check=111&mode=new&type=UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK donde 111 es el id del vehiclecheck

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	

	
	
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
	String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
	String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	String check0 = Util.checkPermission(sys,SecurityConstants.VIEW_VEHICLE_LIST);
	
	if (check!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else if (check1!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check1);	
	} else if (check2!=null && check0!=null) { 
		response.sendRedirect(request.getContextPath()+check2);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		unconformitydpaid=(String)request.getParameter("unconformitydpaid");
		preSelected=(String)request.getParameter("selected");
		id=(String)request.getParameter("id");
		cData=null;
		if(unconformitydpaid==null){
	unconformitydpaid=(String)session.getAttribute("unconformitydpaid");
	session.removeAttribute("unconformitydpaid");
		}
		data=sys.getCorporationReportFacade().findUnconformityDPAById(sys.getUserSessionData(), Integer.valueOf(unconformitydpaid));
		
		DPACorrectiveActionForm form=new DPACorrectiveActionForm();
		if(request.getAttribute("DPACorrectiveActionForm")!=null){
	form=(DPACorrectiveActionForm)request.getAttribute("DPACorrectiveActionForm");
		}
		else if(id!=null){
	cData =form.load(Integer.valueOf(id),sys);	
		}else{
	Calendar cal=Calendar.getInstance();
	cal.setTime(new Date());
	cal.add(Calendar.HOUR,-4);//Hora de Uruguay
	form.setDay(String.valueOf(cal.get(Calendar.DATE)));
	form.setMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
	form.setYear(String.valueOf(cal.get(Calendar.YEAR)));
	form.setHour(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
	form.setMinute(String.valueOf(cal.get(Calendar.MINUTE)));
		}
		
		form.setUnconformityDPAId(Integer.valueOf(unconformitydpaid));
		session.setAttribute("DPACorrectiveActionForm", form);
		//correctiveActions=sys.getCorporationReportFacade().findCorrectiveActionsByUnconformityId(sys.getUserSessionData(), Integer.valueOf(unconformitydpaid));
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
	var index=0;
	var topBack=0;
	$(document).ready(function(){
		var date=new Date();
		$('#hour').val(date.getHours());
		$('#minute').val(date.getMinutes());

	});

	function addPhoto(){
		$("#DPACorrectiveAction").each(
				function(){
					var row='<tr row="'+index+'" class="lineone"><td>';
					row+='<input class="input"  tag="option" type="file" value="" name="files['+index+']"></input></td><td>';
					row+='</td><td><input type="button" onclick="deleteRow('+index+');" value='+'"'+'<%=messages.getText("generic.messages.delete")%>'+'"'+'/></td></tr>'
					
					$("#addphoto").prepend(row);
					$("tr[row='"+index+"']").hide();
					$("tr[row='"+index+"']").show('slow');
					
					index++;
				
				}
		);
	}
	function deleteRow(i){
		
		$("tr[row='"+i+"']").hide('slow',function(){$("tr[row='"+i+"']").remove();});
	}

	function showImage(tthis){
		topBack=$(window).scrollTop();
		var div="<div id='showImageDiv' onclick='closeImage();' style='cursor:pointer;position:absolute;top:0;left:0;background-color:black;opacity:1;height:"+$(window).height()+"px;width:"+$(window).width()+"px;'>";
		div+="<img src='"+$(tthis).attr('src')+"'  style='max-width:"+($(window).width()-100)+"px;position:absolute;top:50px;left:50px;opacity:1;'/></div>";

		$("html, body").animate({ scrollTop: 0 }, "slow");
			
		$('body').append(div);
		$("#showImageDiv").hide();
		$("#showImageDiv").show('slow'); 
	}

	function closeImage(){
		$('#showImageDiv').hide('slow',function(){$(this).remove();$("html, body").animate({ scrollTop: topBack }, "slow");});
	}
	</script>
</head>

<body>
	 
	<center>
	<html:form action="/DPACorrectiveAction" styleId="DPACorrectiveAction" enctype="multipart/form-data" >
		<html:hidden property="unconformityDPAId"/>
		<html:hidden property="id"/>
		<input type="hidden"  name="status"/>
		<input type="hidden" name="cancel" value="no" id="cancel"/>
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("generic.dpa.correctiveaction.title");
			%>
				<jsp:include  page='<%=page14 %>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
		String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.correctiveaction");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
<%			if(sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA}) && sys.getUserSessionData().isCepaUser()) {	
%>	 				<tr> 
   	   					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.messages.type")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:select property="type">
								<html:option value='<%=String.valueOf(CorrectiveActionDPAData.PROVISORIO) %>'><%=messages.getText("generic.dpa.unconformity.provisorio")%></html:option>
								<html:option value='<%=String.valueOf(CorrectiveActionDPAData.DEFINITIVO) %>'><%=messages.getText("generic.dpa.unconformity.definitivo")%></html:option>
							</html:select>
						</td>
					</tr>
<%			}else {
%>	 				<tr> 
   	   					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.messages.type")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<%=(cData == null || cData.getType().equals(CorrectiveActionDPAData.DEFINITIVO)) ? messages.getText("generic.dpa.unconformity.definitivo") : messages.getText("generic.dpa.unconformity.provisorio")%>
							<html:hidden property="type" value='<%=(cData == null || cData.getType().equals(CorrectiveActionDPAData.DEFINITIVO)) ? String.valueOf(CorrectiveActionDPAData.DEFINITIVO) : String.valueOf(CorrectiveActionDPAData.PROVISORIO)%>' />
						</td>
					</tr>
<%			}
%>   				<tr> 
   	   					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%>
						</td>
						<td width="50%" align="right" class="linetwo">
							
							<html:text style="width:30;"  disabled="true"  property="day" styleClass="input"  />&nbsp;/&nbsp;                
							<html:text style="width:30;"  disabled="true" property="month" styleClass="input"  />&nbsp;/&nbsp; 
							<html:text style="width:60;"  disabled="true" property="year" styleClass="input"  />&nbsp;         
							<html:text style="width:30;"    property="hour" styleId="hour" styleClass="input"  />&nbsp;:&nbsp;                
							<html:text style="width:30;"  property="minute" styleId="minute" styleClass="input"  />&nbsp;&nbsp; 
							
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<tr> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText("generic.dpa.uncnformity.comment")%>
						</td>
						<td width="50%" align="right" class="linetwo">
							<html:textarea style="width:600px;height:100px" property="comment" styleClass="input"  />&nbsp;         
						</td>
   					</tr>
					<tr  class="space"><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
					<td colspan="2">
						<input type="button"  onclick="javascript:addPhoto();" value='<%=messages.getText("general.dpa.imagegalery.addphoto")%>' />
						<table width="100%"  id="addphoto">
						</table>
					</td>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
<% if(cData!=null){%>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
			 page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.unconformity.files");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				
					<% Iterator iter;
						iter=cData.getImages().iterator();
						while(iter.hasNext()){
							CorrectiveActionImageDPAData item=(CorrectiveActionImageDPAData)iter.next();
						
					%>
					<tr>
						<td width="5%" align="left" class="texts">
							<input type="checkbox" value="on" name='<%="fileB["+ item.getFileName()+"]"%>'  checked> </input>        
						</td> 
       					<td width="50%" align="left" class="texts">        
   							<%= item.getFileName()%>
						</td>
						<td>
							<img src='ImageService?file=<%=item.getFileName()%>' width="200px" style="cursor: pointer;" onclick="showImage(this);" />
							
						</td>
   					</tr>
   					<tr><td colspan=3>&nbsp</td></tr>
   					<%}%>
   	 			</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
<%}%>		
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
			 page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.unconformity.items");
				%>
				<jsp:include  page='<%=page13 %>' />
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
				
					<% Iterator iter;
						iter=data.getItems().iterator();
						while(iter.hasNext()){
							UnconformityItemDPAData item=(UnconformityItemDPAData)iter.next();
							boolean selected=false;
							if(cData!=null){
								Iterator iter2=cData.getItems().iterator();
								while(!selected && iter2.hasNext()){
									UnconformityItemDPAData itemS=(UnconformityItemDPAData)iter2.next();
									selected=itemS.getId().equals(item.getId());
								}
							}
							selected=selected ||(preSelected!=null && preSelected.equals(item.getType()));
							
							if(item.getCorrectiveActionId()==null || item.getCorrectiveActionId()==0 || selected) {
					%>
					<tr>
						<td width="5%" align="left" class="texts">
<%								if(!(item.getType().startsWith("forestales.check2.v") && !sys.getUserSessionData().isCepaUser())) {
%>							<input type="checkbox" name='<%="item["+ item.getId()+"]"%>'  <%=selected?"checked":"" %> > </input>        
<%								}
%>   						</td> 
       					<td width="50%" align="left" class="texts">        
   							<%= messages.getText(item.getType())%>
						</td>
   					</tr>
   					<tr><td colspan=2>&nbsp</td></tr>
   					<%}}%>
   	 			</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
       	<tr>   		
	   		<td align="right">
	   			<input name="button2" type="button" onclick="document.location.href='layout.jsp?refer=/dpa/DPAUnconformityView.jsp?id=<%=unconformitydpaid%>&href=/dpa/DPAUnconformityList.jsp'" value='<%=messages.getText("generic.messages.back")%>' class="input"/>
	   			<input name="button2" type="button" onclick="DPACorrectiveAction.submit();" value='<%=messages.getText("generic.messages.save")%>' class="input"/>
	   	   	</td>             
   	  	</tr>              
	</table>

	</html:form>
	</center>
	</body>
	
</html:html>
<%
	}
%>
