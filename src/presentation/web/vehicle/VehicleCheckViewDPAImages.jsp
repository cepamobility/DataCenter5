<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.vehicle.report.CheckListImageData"%>
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
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();	
	
	String check = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
	
	if (check!=null) { 
		response.sendRedirect(request.getContextPath()+check);	
	}else {
		messages=sys.getText();
	
		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);

		String checkId = (String)request.getParameter("check");
		if(checkId==null){
	checkId= (String) request.getSession().getAttribute("check");
	request.getSession().removeAttribute("check");
		}
		
		String vehicleId = (String)request.getParameter("vehicleId");
		if(vehicleId==null){
	vehicleId= (String) request.getSession().getAttribute("vehicleId");
	request.getSession().removeAttribute("vehicleId");
		}

		VehicleReportFacade vehicleReport = sys.getVehicleReportFacade(); 
		vehicleCheck = vehicleReport.findVehicleCheck(new Integer(checkId),userSessionData);
		VehicleData vehicleData = vehicleCheck.getVehicle();
		
		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(new Integer(vehicleCheck.getName()),sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
		Iterator images=vehicleCheck.getPointImageSet().iterator();
		
		DPAImageUploadForm form=new DPAImageUploadForm();
		request.getSession().setAttribute("DPAImageUploadForm", form);
%>	

<%@page import="business.vehicle.VehicleReportFacadeBean"%>

<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
	<script>
var index=0;

$(document).ready(
	function(){
		$('#point').change(
				function(){
					//if(index>0){
					//	if(confirm("<%=messages.getText("general.dpa.lostselectedphotos")%>")){
				//			$('input[tag="option"]').remove();
				//			index=0;
				//		}
				//	}
				}	
		);
	}	
);


function addPhoto(){
	var val= $('#point')[0].value;
	var label=$('#point option:selected').html();
	var text=$('#point option:selected').attr('class');
	$("#formUpload").each(
			function(){
				if($(this).attr('action')=='/datacenter/DPAImageUpload.do'){
					var row='<tr row="'+index+'" class="lineone"><td><input type="hidden" tag="option" name="filesOptions['+index+']" value="'+val+'" />';
					row+='<input type="hidden" tag="option" name="filesTexts['+index+']" value="'+text+'" />';
					row+='<input class="input"  tag="option" type="file" value="" name="files['+index+']"></input></td><td>';
					row+=label+'</td><td><input type="button" onclick="deleteRow('+index+');" value='+'"'+'<%=messages.getText("generic.messages.delete")%>'+'"'+'/></td></tr>'
					
					$("#addphoto").prepend(row);
					$("tr[row='"+index+"']").hide();
					$("tr[row='"+index+"']").show('slow');
					
					index++;
				}
			}
	);
}

function deleteRow(i){
	
	$("tr[row='"+i+"']").hide('slow',function(){$("tr[row='"+i+"']").remove();});
}

function deleteImagesSubmit(){
	var list="";
	$('input[type="checkbox"]').each(
		function(){
			if($(this).is(':checked')){
				var file=$(this).attr('file');
				if(list!=""){
					list+=";";
				}
				list+=file;
			}
		}
	);
	
	if(list!=""){
		$("#dImages").attr('value',list);
		$('#formDelete').submit();
	}
	
}

</script>
</head>

<body>
	<center>
		<table width="768" border="0" cellspacing="0" cellpadding="0">  
			<tr class="guide1">
			<%
				String page14 = "../component/titlecomponent.jsp?title="+messages.getText("checklist.dpa.title");
			%>
				<jsp:include  page='<%=page14 %>' />
  			</tr>
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		<tr> 
			<td height="40" colspan="3" valign="middle">
<%
		String page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.imagegalery");
				%>
				<jsp:include  page='<%=page13 %>' />
				<html:form action="/DPAImageUpload" method="post" styleId="formUpload"  enctype="multipart/form-data">
				<input type='hidden' name='check' value='<%=checkId%>'/>
				<input type='hidden' name='vehicleId' value='<%=vehicleId%>'/>
				
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   					<tr >
   					<td width="20%" align="left" ><%= messages.getText("generic.dpa.galeryimage.option")%>
   					</td>
   					<td>
   					<html:select property="point" styleId="point" styleClass="input">
<% 						while(points.hasNext()) {
							PointData point = (PointData)points.next();
							if(point.getPointOrder().intValue() == 1 && sys.isCorporation(CorporationData.DPA)) {
%>								<html:option value="<%= String.valueOf(point.getId())%>" styleClass="checklistdap.images.panoramic" ><%=messages.getText("checklistdap.images.panoramic")%></html:option>
<%							}else {
								Iterator items = point.getSortItems();
								if(items.hasNext()) {
									ItemData item = (ItemData)items.next();
									int value = -1;
									String pointComment = null;
									if(vehicleCheck != null) {
										String checkValue = vehicleCheck.getValueFromItem(item.getId()).trim();
										value = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
									}//
									if(value==2){
										%>	
										<html:option value="<%= String.valueOf(point.getId())%>" styleClass="<%=item.getText()%>" ><%=messages.getText(item.getText()) %></html:option>
										<% 			
									}
								}
							}
						}	
%>	
					</html:select>
					</td>
					</tr>
					<tr>
					<td colspan="2">
						<input type="button"  onclick="javascript:addPhoto();" value='<%=messages.getText("general.dpa.imagegalery.addphoto")%>' />
						<table width="100%"  id="addphoto">
						</table>
					</td>
					</tr>
					<tr>
					<td colspan="2" align="right">
						<input type="submit" value='<%=messages.getText("generic.messages.send")%>'/>						
					</td>
					</tr>		
				</table>
				</html:form>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>  
		<tr>
		<td>
		<%
			 page13 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.dpa.galeryimage.list");
				%>
				<jsp:include  page='<%=page13 %>' />
				<html:form action="/DPAImageUpload" styleId="formDelete" method="post">
				<input type='hidden' name='check' value='<%=checkId%>'/>
				<input type='hidden' name='vehicleId' value='<%=vehicleId%>'/>
				<input type='hidden' name='deleteImages' value='true'/>
				<input type='hidden' name='dImages' id="dImages" value=''/>
				<table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
   					
   					<%
   					int count=0;
   					while(images.hasNext()) {
							CheckListImageData image=(CheckListImageData)images.next();
							%>
								<tr class='lineone'>
									<td><input type="checkbox" file='<%=image.getFileName() %>'/></td>
									<td><%=image.getFileName() %></td>
									<td><%=messages.getText(image.getText())%></td>
								</tr>
								<tr style="background-color: black;" >
									<td colspan="3" align="center">
										<image src='ImageService?file=<%=image.getFileName()%>&corp=<%=sys.getCorporationId()%>' />
									</td>
								</tr>
								<tr style="background-color: white;" >
									<td colspan="3" align="center" height="25px">
										&nbsp;
									</td>
								</td>
							<% 
						}	
					%>	
					
					
					<tr>
					</tr>
					<tr>
					<td colspan="3" align="right">
						<input type="button" onclick="deleteImagesSubmit();" value='<%=messages.getText("generic.messages.delete")%>' />						
					</td>
					</tr>		
				</table>
				</html:form>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
		</td>
		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
       	<tr>   		
	   		<td align="right">
	   			<input name="button2" type="button" value='<%=messages.getText("generic.messages.back")%>' onClick="javascript:document.location.href='layout.jsp?refer=/vehicle/VehicleCheckViewDPA.jsp?vehicle=<%=vehicleId%>&check=<%=checkId%>'" class="input"/>	 		                             
           	</td>             
   	  	</tr>              
	</table>
	</center>
	</body>
</html:html>
<%
	}
%>
