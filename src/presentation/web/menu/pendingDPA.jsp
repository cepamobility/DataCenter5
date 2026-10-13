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
<%@page import="business.vehicle.VehicleReportFacadeBean"%>
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
    private static final java.util.logging.Logger LOGGER =
        java.util.logging.Logger.getLogger("pendingDPA");
%>
<%
    // Variables locales: evitar compartir estado entre peticiones del JSP.
    RequestDispatcher dispatcher;
    Text messages;
    SystemAccess sys;
    UserSessionData userSessionData;
    String id;
    Integer vehicleId;
    String unconformitydpaid;
    String preSelected;
    UnconformityDPAData data;
    CorrectiveActionDPAData cData = null;
    DPACorrectiveActionForm form;
    String next;

    try {
        // PARA EDITAR: id=<id de la acción correctiva>
        // PARA NUEVO: se requiere unconformitydpaid para identificar la inconformidad.
        sys = (SystemAccess) session.getAttribute("SystemAccess");
        if (sys == null) {
            throw new IllegalStateException(
                "No existe SystemAccess en la sesión al abrir pendingDPA.jsp");
        }

        userSessionData = sys.getUserSessionData();
        if (userSessionData == null) {
            throw new IllegalStateException(
                "SystemAccess no contiene UserSessionData en pendingDPA.jsp");
        }

        messages = sys.getText();
        if (messages == null) {
            throw new IllegalStateException(
                "SystemAccess.getText() devolvió null en pendingDPA.jsp");
        }

        // Se elimina el include incondicional de message.jsp porque no forma parte
        // del control de permisos y podía interferir con el manejo de la respuesta.
        unconformitydpaid = request.getParameter("unconformitydpaid");
        preSelected = request.getParameter("selected");
        id = request.getParameter("id");
        vehicleId = new Integer(request.getParameter("vehicle"));

        session.setAttribute("vehicle", vehicleId);
        
        if (unconformitydpaid == null || unconformitydpaid.trim().isEmpty()) {
            unconformitydpaid = (String) session.getAttribute("unconformitydpaid");
            session.removeAttribute("unconformitydpaid");
        }

        if (unconformitydpaid == null || unconformitydpaid.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Falta el parámetro requerido unconformitydpaid");
        }

        int unconformityId;
        try {
            unconformityId = Integer.parseInt(unconformitydpaid.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "El parámetro unconformitydpaid no es un entero válido: " + unconformitydpaid, e);
        }

        LOGGER.info("pendingDPA: buscando inconformidad id=" + unconformityId
            + ", acción correctiva id=" + id);

        data = sys.getCorporationReportFacade().findUnconformityDPAById(
            userSessionData, Integer.valueOf(unconformityId));

        if (data == null) {
            throw new IllegalStateException(
                "No se encontró la inconformidad con id=" + unconformityId);
        }
        if (data.getItems() == null) {
            LOGGER.warning("pendingDPA: data.getItems() devolvió null para inconformidad id="
                + unconformityId);
        }

        form = new DPACorrectiveActionForm();
        if (request.getAttribute("DPACorrectiveActionForm") != null) {
            form = (DPACorrectiveActionForm) request.getAttribute("DPACorrectiveActionForm");
        } else if (id != null && !id.trim().isEmpty()) {
            try {
                cData = form.load(Integer.valueOf(id.trim()), sys);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "El parámetro id no es un entero válido: " + id, e);
            }
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, -4); // Hora de Uruguay
            form.setDay(String.valueOf(cal.get(Calendar.DATE)));
            form.setMonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
            form.setYear(String.valueOf(cal.get(Calendar.YEAR)));
            form.setHour(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
            form.setMinute(String.valueOf(cal.get(Calendar.MINUTE)));
        }

        if (form == null) {
            throw new IllegalStateException(
                "No se pudo inicializar DPACorrectiveActionForm");
        }

        form.setUnconformityDPAId(Integer.valueOf(unconformityId));
        session.setAttribute("DPACorrectiveActionForm", form);
        next = "layout.jsp?refer=vehicle/VehicleView.jsp?id=" + id;

        LOGGER.info("pendingDPA: inicialización completada para inconformidad id="
            + unconformityId);
    } catch (RuntimeException e) {
        LOGGER.log(java.util.logging.Level.SEVERE,
            "Error al inicializar pendingDPA.jsp", e);
        throw e;
    }
%>
<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=EDGE" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/apple-touch-icon-57x57-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/apple-touch-icon-72x72-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/apple-touch-icon-114x114-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/apple-touch-icon-144x144-precomposed.png" />
		<meta name="viewport" content="user-scalable=no, width=980" />
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>No Conformidad</title>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico">
		<link rel="stylesheet" href="styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="styles/base.css">
		<link rel="stylesheet" href="styles/checklist.css">
		<link rel="stylesheet" href="styles/jquery-ui-1.10.3.custom.min.css">
		<style>
			.pendingContainer { padding-top: 50px; position: relative; overflow: hidden; border-radius: 15px; width: 100%; max-width: 1024px; background: white; margin: 100px auto; padding: 70px 50px 25px 50px; box-sizing: border-box; }
			.pendingHeader { 
					display: flex; 
					padding: 8px 16px; 
					align-items: center; 
					background: grey; 
					min-height: fit-content; 
					margin-bottom: 10px; 
					top: 0;
				    left: 0;
				    right: 0;
				    position: absolute;
				}
			.pendingHeader h1 { margin: 0; color: white; font-size: 22px; font-weight: 500; }
			.narrow { display: flex; flex-direction: column; gap: 15px; margin-inline: 15px; width: 100%; box-sizing: border-box; min-width: 0; }
			.content { padding-inline: 20px }
			.form-group input {
				font-size: 1rem;
			}
			textarea {
				resize: none;
			}
			@media (max-width: 1024px) { 
				form label {font-size: 2rem;}
				.pendingContainer { flex-direction: column; max-width: 750px; }
				.pendingHeader h1 {
					font-size: 2.2rem;
				}
			}
		</style>
		<script type="text/javascript">
			var index=0;
			var topBack=0;
			$(document).ready(function(){
				var date = new Date(); //CASO123456
	
				var year = date.getFullYear();
				var day = ("0" + date.getDate()).slice(-2);
				var month = ("0" + (date.getMonth()+1)).slice(-2);
				
				var fDate = year+"-"+month+"-"+day;
	
				var q3 = document.getElementById('q3');
				if (q3) { q3.value = fDate; }
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
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='<%=next%>'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
		
		<div class="pendingContainer">
			<div class="pendingHeader"><h1>No Conformidad</h1></div>
			
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
											<div class="form-group col-xs-12" align="left">
												<div class="col-xs-4" align="left"><%= messages.getText("generic.messages.type")%></div>
												<div class="col-xs-8" align="right">
													<html:select property="type">
														<html:option value='<%=String.valueOf(CorrectiveActionDPAData.PROVISORIO) %>'><%=messages.getText("generic.dpa.unconformity.provisorio")%></html:option>
														<html:option value='<%=String.valueOf(CorrectiveActionDPAData.DEFINITIVO) %>'><%=messages.getText("generic.dpa.unconformity.definitivo")%></html:option>
													</html:select>
												</div>
											</div>
											<div class="form-group col-xs-12" align="left">
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
%>									<div class="col-xs-12">
										<fieldset>
											<legend><%=messages.getText("generic.dpa.unconformity.files")%></legend>
										  	<div class="table-responsive">
												<table class="table table-bordered table-hover table-condensed table-striped table-custom">
													<tbody>
<%                  Collection images = cData.getImages();
                    if (images != null) {
                        Iterator iter1 = images.iterator();
                        while (iter1.hasNext()) {
                            CorrectiveActionImageDPAData item =
                                (CorrectiveActionImageDPAData) iter1.next();
%>														<tr>
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
<%                      }
                    } else {
                        LOGGER.warning("pendingDPA: cData.getImages() devolvió null para acción id=" + id);
                    }
%>													</tbody>
									 			</table>
									 		</div>
									 	</fieldset>
									 </div>
<%		}
%> 	
									 <div class="col-xs-12">
										<fieldset>
											<legend><%=messages.getText("generic.dpa.unconformity.items")%></legend>
										  	<div class="table-responsive">
												<table class="table table-bordered table-hover table-condensed table-striped table-custom">
													<thead></thead>
													<tbody>
		<%      Collection unconformityItems = data.getItems();
                if (unconformityItems != null) {
                    Iterator iter2 = unconformityItems.iterator();
                    while (iter2.hasNext()) {
                        UnconformityItemDPAData item =
                            (UnconformityItemDPAData) iter2.next();
					boolean selected=false;
                        if (cData != null && cData.getItems() != null) {
                            Iterator iter3 = cData.getItems().iterator();
                            while (!selected && iter3.hasNext()) {
                                UnconformityItemDPAData itemS =
                                    (UnconformityItemDPAData) iter3.next();
                                selected = itemS.getId() != null
                                    && itemS.getId().equals(item.getId());
                            }
                        }
                        selected = selected || (preSelected != null && preSelected.equals(item.getType()));
                        if (item.getCorrectiveActionId() == null
                                || item.getCorrectiveActionId() == 0 || selected) {
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
                } else {
                    LOGGER.warning("pendingDPA: data.getItems() devolvió null para inconformidad id=" + unconformitydpaid);
                }
		%>											</tbody>
												</table>
											</div>
										</fieldset>
									</div>
									
									<div class="col-xs-12" align="right">
							   			<input name="button2" type="button" onclick="document.location.href='layout.jsp?refer=/pendingChecklist.jsp?checklist=80&id=<%=vehicleId%>'" value='<%=messages.getText("generic.messages.back")%>' class="btn btn-default"/>
							   			<input name="button2" type="button" onclick="DPACorrectiveAction.submit();" value='<%=messages.getText("generic.messages.save")%>' class="btn btn-primary"/>
							   		</div>
						   		</div>
							</div>

						</div>
					</div>
				</div>
			</html:form>
		</div>
	</body>
</html>