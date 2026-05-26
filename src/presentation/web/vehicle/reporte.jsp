<%@page language="java"
	errorPage="../exception.jsp" 
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
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="java.util.*"
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

	
<script language="JavaScript" src="../util/js/select_component.js" type="text/javascript">
</script>
  <body>
		<html:form action="/vehicle/pruebajfree">


	  <table align="center" width="600" cellpadding="0" cellspacing="0">
		  <tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="guideSearch">
							<td width="19" >
							<img src="../util/imgs/left_top_search.jpg" width="19" height="30" align="bottom"></td>
							<td width="180" align="left" class="guideSearch">
								<%="Datos del vehiculo"%>							</td>
							<td width="19" >
								<img src="../util/imgs/right_top_search.jpg" width="19" height="30" align="bottom"></td>
						</tr>
  						<tr>
    						<td class="leftLineSearch">&nbsp;</td>
    						<td>
											<table class="SearchBack" width="100%">
												<tr class="texts">
													<td align="left">		


														Matricula</td>
						         					<td align="right">
														<html:text property="registrationNumber" size="30" styleClass="input"/>													</td>
												</tr>
												<tr class="texts">
													<td align="left">
														Nro chasis													</td>
						         					<td align="right">
														<html:text property="chasisNumber" size="30" styleClass="input"/>													</td>
												</tr>																		
												<tr class="texts">
						        					<td align="left">
														Nombre del conductor													</td>
						         					<td align="right">
														<html:text property="firstNameDriver" size="30" styleClass="input"/>													</td>
												</tr>
												<tr class="texts">
													<td align="left">
														Apellido del Conductor													</td>
						         					<td align="right">
														<html:text property="lastNameDriver" size="30" styleClass="input"/>													</td>
												</tr>
											</table>						  </td>
						  				<td class="rightLineSearch">&nbsp;</td>
			  </tr>	
						  			<tr>
					                  	<td height="11" valign="top"><img src="../util/imgs/left_base_search.jpg" width="19" height="11"></td>
					  				  	<td class="centerBaseSearch"></td>
					  				  	<td valign="top" height="11"><img src="../util/imgs/right_base_search.jpg" width="19" height="11"></td>
								 	</tr>
			  </table>			</td>
			<td width="16" valign="top">&nbsp;</td>
			<td rowspan="3" valign="top">
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
			      			<td>
								<jsp:include page='<%="./divorg.jsp?form=vehicleForm&format=search" %>'/>							</td>
						</tr>
			      	</table>			</td>
		  </tr>
		  <tr>
					<jsp:include  page="../component/spaceline.jsp" />			</tr>
		  <tr>
			<td valign="top">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
			      			<td>
								<jsp:include page='<%="./divgeo2.jsp?form=vehicleForm&format=search" %>' />							</td>
						</tr>
				</table>			</td>
		    <td width="16" valign="top">&nbsp;</td>
		  </tr>
	


		</table>
		<html:submit value="Generar reporte" styleClass="input" />		  		
		</html:form>		
  </body>

</html:html>