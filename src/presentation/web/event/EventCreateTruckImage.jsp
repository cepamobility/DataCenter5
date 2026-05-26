<%@page language="java" 
    errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	boolean onlyTruck = new Boolean(request.getParameter("onlyTruck")).booleanValue();
%>
								<!-- Solo camion sin zorra:  -->
								<div id="onlyTruckImage"  <%=(onlyTruck)?" style='display:block' ":" style='display:none' " %> >
											<script language="javascript">
														if (navigator.appVersion.indexOf("MSIE")!= -1){
														// Internet Explorer
													
														document.write (
															'<table width="340px" border="0" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/camion_sin_zorra.jpg">' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage1"  />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage2"   />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage3"   />' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage4"    /> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage5"    />'+
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage6"    />' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage7"    />' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage8"    />' +
																		'&nbsp;' +
																		'<html:checkbox property="damage9"    />' +
																		'<html:hidden property="damage10"    />' +
																		'<html:hidden property="damage11"    />' +
																		'<html:hidden property="damage12"    />' +
																		'<html:hidden property="damage13"    />' +
																		'<html:hidden property="damage14"    />'+
																		'<html:hidden property="damage15"    />' +
																		'<html:hidden property="damage16"    />' +
																	'</td>' +
																'</tr>' +
																'<tr height="36">' +													
																	'<td id="1" align="left" valign="middle" height="32">' +
																		'<html:checkbox property="damage17"    />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage18"    />' +
																		'&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage19"    />' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage20"    />' +
																		'&nbsp;' +
																		'<html:checkbox property="damage21"    />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
	 																	'<html:checkbox property="damage22"    />' +
	 																	'&nbsp;' +
	 																	'<html:checkbox property="damage23"    />' +
	 																	'<html:hidden property="damage24"    />'+
														  				'<html:hidden property="damage25"    />'+
	 																	'<html:hidden property="damage26"    />' +
	 																'</td>' +
																'</tr>' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage27"    />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage28"    />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage29"    />' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage30"    /> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage31"    />'+
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage32"    />' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage33"    />' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage34"    />' +
																		'<html:hidden property="damage35"    />' +
																		'<html:hidden property="damage36"    />' +
																		'<html:hidden property="damage37"    />' +
																		'<html:hidden property="damage38"    />' +
																		'<html:hidden property="damage39"    />' +
																		'<html:hidden property="damage40"    />'+
																		'<html:hidden property="damage41"    />' +
																		'<html:hidden property="damage42"    />' +
																	'</td>' +
																'</tr>' +
																
															'</table>'
															);
														}else{
														// Not IExplorer
														document.write (
															'<table width="340px" border="0" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/camion_sin_zorra.jpg">' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage1"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage2"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage3"     />' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage4"     /> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage5"     />'+
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage6"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage7"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage8"     />' +
																		'<html:hidden property="damage9"     />' +
																		'<html:hidden property="damage10"     />' +
																		'<html:hidden property="damage11"     />' +
																		'<html:hidden property="damage12"     />' +
																		'<html:hidden property="damage13"     />' +
																		'<html:hidden property="damage14"     />'+
																		'<html:hidden property="damage15"     />' +
																		'<html:hidden property="damage16"     />' +
																	'</td>' +
																'</tr>' +
																'<tr height="36">' +													
																	'<td id="1" align="left" valign="middle" height="32">' +
																		'<html:checkbox property="damage17"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage18"     />' +
																		'&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage19"     />' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage20"     />' +
																		'&nbsp;' +
																		'<html:checkbox property="damage21"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
	 																	'<html:checkbox property="damage22"     />' +
	 																	'&nbsp;' +
	 																	'<html:checkbox property="damage23"     />' +
	 																	'<html:hidden property="damage24"     />'+
														  				'<html:hidden property="damage25"     />'+
	 																	'<html:hidden property="damage26"     />' +
	 																'</td>' +
																'</tr>' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage27"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage28"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage29"     />' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage30"     /> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage31"     />'+
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage32"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage33"     />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage34"     />' +
																		'<html:hidden property="damage35"     />' +
																		'<html:hidden property="damage36"     />' +
																		'<html:hidden property="damage37"     />' +
																		'<html:hidden property="damage38"     />' +
																		'<html:hidden property="damage39"     />' +
																		'<html:hidden property="damage40"     />'+
																		'<html:hidden property="damage41"     />' +
																		'<html:hidden property="damage42"     />' +
																	'</td>' +
																'</tr>' +
																
															'</table>'
															);
														}
									</script>
					</div><!--  onlyTruckImage -->
					
					<!--  Camion con zorra  -->
					<div id="doubleCabinImage"  <%=(onlyTruck)?" style='display:none' ":" style='display:block' " %> >
					<script language="javascript">
											    //  Doble Cabina:
												if (navigator.appVersion.indexOf("MSIE")!= -1){
														// Internet Explorer
														document.write (
															'<table width="570px" border="0" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/camion_con_zorra.jpg">' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage1" />' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage2"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage3"/>' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage4"/> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage5"/>'+
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage6"/>' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage7"/>' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage8"/>' +
																		'&nbsp;' +
																		'<html:checkbox property="damage9"/>&nbsp;&nbsp;' +
																		'<html:checkbox property="damage10"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage11"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage12"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage13"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage14"/>&nbsp;&nbsp;'+
																		'<html:checkbox property="damage15"/>&nbsp;' +
																		'<html:checkbox property="damage16"/>' +
																	'</td>' +
																'</tr>' +
																'<tr height="36">' +													
																	'<td id="1" align="left" valign="middle" height="32">' +
																		'<html:checkbox property="damage17"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage18"/>' +
																		'&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage19"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage20"/>' +
																		'&nbsp;' +
																		'<html:checkbox property="damage21"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
	 																	'<html:checkbox property="damage22"/>' +
	 																	'&nbsp;' +
	 																	'<html:checkbox property="damage23"/>' +
																		'&nbsp;' +
	 																	'<html:checkbox property="damage24"/>'+
			 															'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
	 																	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
	 																	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
	 																	'&nbsp;&nbsp;&nbsp;&nbsp;' +
														  				'<html:checkbox property="damage25"/>'+
	 																	'<html:checkbox property="damage26"/>' +
	 																'</td>' +
																'</tr>' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage27"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage28"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage29"/>' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage30"/> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage31"/>'+
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage32"/>' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage33"/>' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage34"/>' +
																		'&nbsp;' +
																		'<html:checkbox property="damage35"/>&nbsp;&nbsp;' +
																		'<html:checkbox property="damage36"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage37"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage38"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage39"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage40"/>&nbsp;&nbsp;'+
																		'<html:checkbox property="damage41"/>&nbsp;' +
																		'<html:checkbox property="damage42"/>' +
																	'</td>' +
																'</tr>' +
																
															'</table>');
														}else{
														// Not IExplorer
														document.write (
															'<table width="570px" border="0" cellpadding="0" cellspacing="0" background="/datacenter/util/imgs/camion_con_zorra.jpg">' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage1"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage2"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage3"/>' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage4"/> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage5"/>'+
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage6"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage7"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage8"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage9"/>&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage10"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage11"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage12"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage13"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage14"/>&nbsp;&nbsp;'+
																		'<html:checkbox property="damage15"/>&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage16"/>' +
																	'</td>' +
																'</tr>' +
																'<tr height="36">' +													
																	'<td id="1" align="left" valign="middle" height="32">' +
																		'<html:checkbox property="damage17"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage18"/>' +
																		'&nbsp;&nbsp;&nbsp;'+
																		'<html:checkbox property="damage19"/>' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage20"/>' +
																		'&nbsp;' +
																		'<html:checkbox property="damage21"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
	 																	'<html:checkbox property="damage22"/>' +
	 																	'&nbsp;' +
	 																	'<html:checkbox property="damage23"/>' +
																		'&nbsp;' +
	 																	'<html:checkbox property="damage24"/>'+
			 															'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
	 																	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
	 																	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
	 																	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
														  				'<html:checkbox property="damage25"/>'+
	 																	'<html:checkbox property="damage26"/>' +
	 																'</td>' +
																'</tr>' +
																'<tr>'+
																	'<td id="1" align="left" valign="middle" height="20" width="100%">' +
																		'<html:checkbox property="damage27"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage28"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage29"/>' +
																		'&nbsp;&nbsp;&nbsp; ' +
																		'<html:checkbox property="damage30"/> ' +
																		'&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage31"/>'+
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage32"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage33"/>' +
																		'&nbsp;&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage34"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage35"/>&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage36"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage37"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage38"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage39"/>' +
																		'&nbsp;&nbsp;' +
																		'<html:checkbox property="damage40"/>&nbsp;&nbsp;'+
																		'<html:checkbox property="damage41"/>&nbsp;&nbsp;&nbsp;' +
																		'<html:checkbox property="damage42"/>' +
																	'</td>' +
																'</tr>' +
																
															'</table>'
															);
														}
													</script>
											</div>												
										