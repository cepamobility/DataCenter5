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
		
		if (unconformitydpaid == null || unconformitydpaid.trim().isEmpty()) {
		    throw new IllegalArgumentException(
		        "Falta el parámetro unconformitydpaid"
		    );
		}
		
		data=sys.getCorporationReportFacade().findUnconformityDPAById(sys.getUserSessionData(), Integer.valueOf(unconformitydpaid));
		
		DPACorrectiveActionForm form=new DPACorrectiveActionForm();
		if(request.getAttribute("DPACorrectiveActionForm")!=null){
			form=(DPACorrectiveActionForm)request.getAttribute("DPACorrectiveActionForm");
		} else if(id!=null){
			cData =form.load(Integer.valueOf(id),sys);	
		} else{
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
	<script type="text/javascript">
	var index=0;
	var topBack=0;
	$(document).ready(function(){
		var date = new Date(); //CASO123456

		var year = date.getFullYear();
		var day = ("0" + date.getDate()).slice(-2);
		var month = ("0" + (date.getMonth()+1)).slice(-2);
		
		var fDate = year+"-"+month+"-"+day;

		document.getElementById('q3').value = fDate;
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
	<html:form action="/DPACorrectiveAction" styleId="DPACorrectiveAction" enctype="multipart/form-data" >
		<html:hidden property="unconformityDPAId"/>
		<html:hidden property="id"/>
		<input type="hidden"  name="status"/>
		<input type="hidden" name="cancel" value="no" id="cancel"/>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title"><%=messages.getText("generic.dpa.correctiveaction.title")%></h3>
					  	</div>
					  	<div class="panel-body">
 							<div class="col-xs-12">
								<fieldset>
									<legend><%=messages.getText("generic.dpa.correctiveaction")%></legend>
<%			if(sys.isCorporation(new int[]{CorporationData.FORESTALORIENTAL, CorporationData.MONTES_DE_PLATA}) && sys.getUserSessionData().isCepaUser()) {	
%>									<div class="form-group col-xs-12" align="left">
										<div class="col-xs-4" align="left"><%= messages.getText("generic.messages.type")%></div>
										<div class="col-xs-8" align="right">
											<html:select property="type">
												<html:option value='<%=String.valueOf(CorrectiveActionDPAData.PROVISORIO) %>'><%=messages.getText("generic.dpa.unconformity.provisorio")%></html:option>
												<html:option value='<%=String.valueOf(CorrectiveActionDPAData.DEFINITIVO) %>'><%=messages.getText("generic.dpa.unconformity.definitivo")%></html:option>
											</html:select>
										</div>
									</div>
<%			}else {
%>									<div class="form-group col-xs-12" align="left">
										<div class="col-xs-4" align="left"><%= messages.getText("generic.messages.type")%></div>
										<div class="col-xs-8" align="right">
											<%=(cData == null || cData.getType() == null || cData.getType().equals(CorrectiveActionDPAData.DEFINITIVO)) ? messages.getText("generic.dpa.unconformity.definitivo") : messages.getText("generic.dpa.unconformity.provisorio")%>
										</div>
									</div>
									<html:hidden property="type" value="<%=(cData == null || cData.getType() == null || cData.getType().equals(CorrectiveActionDPAData.DEFINITIVO)) ? String.valueOf(CorrectiveActionDPAData.DEFINITIVO) : String.valueOf(CorrectiveActionDPAData.PROVISORIO)%>"/>
<%			}
%>									<div class="form-group col-xs-12" align="left">
										<div class="col-xs-4" align="left"><%= messages.getText("generic.activity.date")%><%=" ( "+messages.getText("generic.messages.dateformat")+" ) "%></div>
										<div class="col-xs-8" align="right">
											<html:text style="width:40px;"  disabled="true"  property="day" />&nbsp;/&nbsp;                
											<html:text style="width:40px;"  disabled="true" property="month" />&nbsp;/&nbsp; 
											<html:text style="width:80px;"  disabled="true" property="year" />&nbsp;         
											<html:text style="width:40px;"    property="hour" styleId="hour" />&nbsp;:&nbsp;                
											<html:text style="width:40px;"  property="minute" styleId="minute" />&nbsp;&nbsp; 
										</div>
									</div>
									<div class="form-group col-xs-12" align="left">
									  	<div class="col-sm-4 control-label" align="left"><%=messages.getText("generic.dpa.uncnformity.comment")%></div>
									  	<div class="col-sm-8" align="right">
										  	<html:textarea style="width:600px;height:100px" property="comment"/>
									    </div>
									</div>
									<div class="form-group col-xs-12" align="right">
										<input type="button"  onclick="javascript:addPhoto();" value='<%=messages.getText("general.dpa.imagegalery.addphoto")%>' />
										<table width="100%"  id="addphoto">
										</table>
									</div>
								</fieldset>
							</div>
<% 		if(cData!=null){
%> 							<div class="col-xs-12">
								<fieldset>
									<legend><%=messages.getText("generic.dpa.unconformity.files")%></legend>
								  	<div class="table-responsive">
										<table class="table table-bordered table-hover table-condensed table-striped table-custom">
											<tbody>
<% 			Iterator iter = cData.getImages().iterator();
			while(iter.hasNext()){
				CorrectiveActionImageDPAData item=(CorrectiveActionImageDPAData)iter.next();
%>												<tr>
													<td width="5%" align="left">
														<input type="checkbox" value="on" name='<%="fileB["+ item.getFileName()+"]"%>'  checked> </input>        
							   						</td> 
							       					<td width="50%" align="left">        
							   							<%= item.getFileName()%>
													</td>
													<td>
														<img src='ImageService?file=<%=item.getFileName()%>' width="200px" style="cursor: pointer;" onclick="showImage(this);" />
													</td>
							   					</tr>
<%			}
%>											</tbody>
							 			</table>
							 		</div>
							 	</fieldset>
							 </div>
<%		}
%> 							<div class="col-xs-12">
								<fieldset>
									<legend><%=messages.getText("generic.dpa.unconformity.items")%></legend>
								  	<div class="table-responsive">
										<table class="table table-bordered table-hover table-condensed table-striped table-custom">
											<thead></thead>
											<tbody>
<% 		Iterator iter =data.getItems().iterator();
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
%>												<tr>
													<td width="5%" align="left">
<%				if(!(item.getType().startsWith("forestales.check2.v") && !sys.getUserSessionData().isCepaUser())) {
%>														<input type="checkbox" name='<%="item["+ item.getId()+"]"%>'  <%=selected?"checked":"" %> > </input>        
<%				}
%>							   						</td> 
							       					<td width="50%" align="left">        
							   							<%= messages.getText(item.getType())%>
													</td>
							   					</tr>
<%			}
		}
%>											</tbody>
										</table>
									</div>
								</fieldset>
							</div>
							<div class="col-xs-12" align="right">
					   			<input name="button2" type="button" onclick="document.location.href='layout.jsp?refer=/dpa/DPAUnconformityView.jsp?id=<%=unconformitydpaid%>&href=/dpa/DPAUnconformityList.jsp'" value='<%=messages.getText("generic.messages.back")%>' class="btn btn-default"/>
					   			<input name="button2" type="button" onclick="DPACorrectiveAction.submit();" value='<%=messages.getText("generic.messages.save")%>' class="btn btn-primary"/>
					   		</div>
					   	</div>
					</div>
				</div>
			</div>
		</div>
	</html:form>
</body>
	
</html:html>
<%
	}
%>
