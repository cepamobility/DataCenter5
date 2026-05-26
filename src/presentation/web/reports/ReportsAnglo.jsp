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
	import="communication.administration.user.tables.geographicdivisions.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="presentation.translator.web.activity.schedule.*"	
	import="java.util.*"
 	import="communication.administration.user.*" 
 	import = "communication.administration.corporation.tables.*"	
	import = "communication.util.Filter"
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData;
	DataTypeAccess datatypes;
	GeoItemData item;
	String check;
	Iterator iter;
	LinkedList sortCountries = new LinkedList();
	String name="";
	String countryCode = "";
	String addReportPermission;
	boolean canAddReport = false;
	
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	
	//	Me fijo si tiene permisos para agregar reportes
	addReportPermission = Util.checkPermission(sys,SecurityConstants.ADD_REPORT);
	if (addReportPermission == null) {
		// Si tiene permiso para agregar reportes y viene con la opcion de agregar
		// le dejo agregar reportes
		String add = (String)request.getParameter("add");
		//System.out.println("Reports.jsp: add="+add);
		canAddReport = new Boolean(add.trim()).booleanValue();
		//System.out.println("Reports.jsp: canAddReport="+canAddReport);
	}		
	
	check = Util.checkPermission(sys,SecurityConstants.VIEW_REPORT);		
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else{
%>
<html:html>
  	<body>
	  	<table width="600"  border="0" cellspacing="0" cellpadding="0" align="center">
	  		<tr>
	    		<td>
	    			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	        			<tr>
	          				<td width="19"><img src="../datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          				<td width="572" class="guide1">CEPA DATA CENTER</td>
	          				<td width="19"><img src="../datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	        			</tr>
	        			<tr>
	          				<td width="19" background="../datacenter/util/imgs/left_line.gif">&nbsp;</td>
	          				<td width="572">
								<jsp:include  page='<%="ReportCountryAnglo.jsp?fileReport=reports&add="+Boolean.toString(canAddReport)%>' />        	
	            			</td>
	        				<td width="19" background="../datacenter/util/imgs/right_line.gif">&nbsp;</td>
	      				</tr>
	      				<tr>
	        				<td width="19"><img src="../datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
	        				<td width="572" background="../datacenter/util/imgs/center_base.gif"><img src="x" height="1" width="1"></td>
	        				<td width="19"><img src="../datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
	      				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>&nbsp;</td>
	    	</tr>
	  		<tr>
	    		<td>
	    			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	        			<tr>
	          				<td width="19"><img src="../datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          				<td width="572" class="guide1">CENTRAL DE CONTROLE TELEMÁTICO</td>
	          				<td width="19"><img src="../datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	        			</tr>
	        			<tr>
	          				<td width="19" background="../datacenter/util/imgs/left_line.gif">&nbsp;</td>
	          				<td width="572">
								<jsp:include  page='<%="ReportCountryAnglo.jsp?fileReport=telematic&add="+Boolean.toString(canAddReport)%>' />        	
	            			</td>
	        				<td width="19" background="../datacenter/util/imgs/right_line.gif">&nbsp;</td>
	      				</tr>
	      				<tr>
	        				<td width="19"><img src="../datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
	        				<td width="572" background="../datacenter/util/imgs/center_base.gif"><img src="x" height="1" width="1"></td>
	        				<td width="19"><img src="../datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
	      				</tr>
	    			</table>
	    		</td>
	    	</tr>
	    </table>
	</body>    
</html:html>
<%	}
%>
