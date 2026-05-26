<%@page import="presentation.translator.web.util.UploadValidator.ColumnMetadata"%>
<%@page import="communication.util.MetadataColumn"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="./util/css/bootstrap.min.css" TYPE="text/css">
	</head>
	
 
	<body>
		<center>
			 
			<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
		  		<tr>
		    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
		    		<td width="562" height="30">&nbsp;</td>
		    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
		  		</tr>
		  		<tr>
		    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
	    		  	<td width="562" align="center" class="lineone">
		    			<span class="success">		    			
							<%=messages.getText("vehicle.upload.update.title")%>								
			    		</span>
						<br>
		    			<br>
						<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
					  		<tr>
					    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
				    		  	<td width="562" align="center" class="lineone">
				    		  	<div style="height: 400px;width: 100%;overflow: scroll;">
				    		  		<table class="table table-bordered table-striped table-condensed">
				    		  		<%
				    		  			if(request.getAttribute("updatesuccess")!=null){
				    		  				%>
				    		  				<tr>
				    		  					<td>	
				    		  						<%="Se atualizó con éxito"%>
				    		  					</td>
				    		  				</tr>
				    		  				<tr>
						    		  			<td>
						    		  				<a href="UploadUpdateVehicleMDP.do?step=dowloadVehiclesInDCNotInExcel">Descargar Vehiculos que estan DataCenter pero no en el excel. </a>
						    		  			</td>	
				    		  			
						    		  		</tr>
						    		  		<tr>
						    		  			<td>
						    		  				<a href="UploadUpdateVehicleMDP.do?step=dowloadPlatformInDCNotInExcel">Descargar Plataformas que estan DataCenter pero no en el excel </a>
						    		  			</td>	
				    		  			
						    		  		</tr>
				    		  				<% 
				    		  			}else{
				    		  				%>
				    		  				<tr>
						    		  			<td>
						    		  				<a href="UploadUpdateVehicleMDP.do?step=dowloadNotExistVehicles">Descargar Vehiculos que no existen </a>
						    		  			</td>
						    		  		</tr>
						    		  		<tr>
						    		  			<td>
						    		  				<a href="UploadUpdateVehicleMDP.do?step=dowloadNotExistPlatforms">Descargar Plataformas que no existen </a>
						    		  			</td>	
				    		  			
						    		  		</tr>
						    		  		<tr>
						    		  			<td>
						    		  				<a href="UploadUpdateVehicleMDP.do?step=dowloadVehiclesInDCNotInExcel">Descargar Vehiculos que estan DataCenter pero no en el excel. </a>
						    		  			</td>	
				    		  			
						    		  		</tr>
						    		  		<tr>
						    		  			<td>
						    		  				<a href="UploadUpdateVehicleMDP.do?step=dowloadPlatformInDCNotInExcel">Descargar Plataformas que estan DataCenter pero no en el excel </a>
						    		  			</td>	
				    		  			
						    		  		</tr>
						    		  		<tr>
				    		  					<td>	
				    		  						<%="No se actualizó, vea los siguentes mensajes, hasta que no exista ningun error no se podrá actualizar."%>
				    		  					</td>
				    		  				</tr>
				    		  				 
    		
				    		  				<%
				    		  			}
				    		  		
				    		  			ArrayList<String> m=(ArrayList<String>)request.getAttribute("messages");
				    		  			Iterator<String> iter= m.iterator();
				    		  			while(iter.hasNext()){
				    		  				String row=iter.next();
				    		  				%>
				    		  				<tr>
				    		  					<td>	
				    		  						<%=row%>
				    		  					</td>
				    		  				</tr>
				    		  				<%
				    		  			}
				    		  		%>
										
											
										
									</table>
								</div>	
				    		  	</td>
					    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
					  		</tr>
					  		<tr>
					    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
					    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
					    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
						  	</tr>
						</table>
						 
					</td>
		    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
		    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
			</table>
		 
		</center>
		<br/><br/>
	</body>
</html>
