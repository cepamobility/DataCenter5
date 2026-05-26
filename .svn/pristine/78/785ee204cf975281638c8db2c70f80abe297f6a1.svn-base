<%@ page language="java"
    errorPage="exception.jsp"
	import="presentation.translator.web.user.*"	
	import="communication.security.*"
	import="java.io.File"
	import="java.net.*"
	import="java.io.FileReader"
	import="java.io.IOException"
	import="java.io.BufferedReader"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html>
<%! RequestDispatcher dispatcher; String context; String pathMsg; String pathGarbagecolector;%>
<%
	context=request.getContextPath();
	pathMsg="/util/jsp/message.jsp";
	pathGarbagecolector="/util/jsp/garbagecolector.jsp";
	
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
	
	dispatcher=request.getRequestDispatcher(pathGarbagecolector);
	dispatcher.include(request,response);
	
	new LogoutAction().logout(request);
    response.sendRedirect(request.getContextPath()+"/home.jsp");
	

%>

<head>

<title> CEPA </title>

<script>
if (parent.frames.length)
		parent.location.href= self.location;
</script>

<link href='<%=context+"/util/css/estilo.css"%>' rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"><style type="text/css">
<!--
body {
	background-color: #DDDDDD;
}
-->
</style></head>

<body>
<form name="loginForm" method="post" action="j_security_check" onsubmit="return enc()">
<table width="499"  border="0" align="center" cellpadding="0" cellspacing="0" class="table">
  <tr>
    <td colspan="4"><img src='<%=context+"/util/imgs/topo_login.jpg"%>' height="90" width="499"></td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td width="4" height="24"><div align="center"></div></td>
    <td width="74" height="24"><img src='<%=context+"/util/imgs/lg.gif"%>' height="20" width="70"></td>
    <td width="4"><div align="center"></div></td>
    <td width="417"><input type="text" name="j_username" size="16" class="input"/></td>
  </tr>
  <tr>
    <td height="28"><div align="center"></div></td>
    <td><img src='<%=context+"/util/imgs/lg2.gif"%>' height="20" width="70"></td>
    <td><div align="center"></div></td>
    <td><input type="password" name="j_password" size="16" class="input"/></td>
  </tr>
  <tr align="right">
    <td height="33" colspan="4" align="center" class="cursor">
    	<html:img src='<%=context+"/util/imgs/lg3.gif"%>' height="27" width="94" 
    		onclick="javascript:document.forms[0].submit()"/>
    </td>
  </tr>
  
  <%
  			// Mensajes de Mantenimiento:
 			try{
 				File file = new File(SecurityConstants.DATACENTER5_WAR_DIRECTORY+"mantenimiento.txt");
 				FileReader input = new FileReader(file); 				
 		        BufferedReader br = new BufferedReader(input);
 				
 				String line;   // String that holds current file line
 				// Read first line
 				line = br.readLine();
 				if (line != null){
 			    	%>
 			    
 			    <tr>
					<td height="35" colspan="3" align="center" class="msglogin">
					<div align="center">
						<table width="75%">
							<tr>
								<td colspan="3" height="5"></td>
							</tr>
							<tr>
								<td colspan="3" align="center" class="msglogin">
									<FONT COLOR="red"><strong>
					<%
					// dis.available() returns 0 if the file does not have more lines.
	 			    while (line != null) {

	 			      // this statement reads the line from the file and print it 			        
	 			    %> 
	 			        <%=line%>
	 			    <%
	 			   		line = br.readLine();
	 			    }
					%>			
									</strong></FONT>
								</td>
							</tr>
						</table>
  					</div>
  					</td>
  				</tr>
 			    
 			    	<%
 			    }
		      	// dispose all the resources after using them.		      	
 				br.close();	      	
 			      		
 			}catch(Exception e){
 				System.out.println("No hay archivo de mantenimiento");
 			}          
 			 
 %>
  
  
  <tr>
    <td colspan="4">
    
    <div align="right">
    
    
    	<%
    		// IP dc5.cepasafedrive.com:
    		// IP cepadc.com: 
			InetAddress inetAddress = InetAddress.getLocalHost();
			String ip = inetAddress.getHostAddress();
			// System.out.println("Server IP: "+ip);
			String host_name = "www.cepadc.com"; // "dc5.cepasafedrive.com";
			if (ip.equalsIgnoreCase(SecurityConstants.CEPADC_PRODUCTION_IP)){
				host_name="www.cepadc.com";
			}
			
		%>		
    
    
    	<!--  Nuevo Certificado -->
	    	<table width="80" align="center" border="0" cellpadding="2" cellspacing="0" title="Click to Verify - This site chose Thawte SSL for secure e-commerce and confidential communications.">
				<tr>
					<td width="80" align="center" valign="top">
						<script type="text/javascript" src="https://seal.thawte.com/getthawteseal?host_name=<%=host_name%>&amp;size=M&amp;lang=en"></script>
					</td>
				</tr>
				<tr>
					<td  align="center" valign="top">
						<a href="http://www.thawte.com/digital-certificates/" target="_blank" style="color:#000000; text-decoration:none; font:bold 10px arial,sans-serif; text-align:center; margin:0px; padding:0px;">ABOUT SSL CERTIFICATES</a></td>
					<td width="155">&nbsp;</td>
				</tr>
			</table>  
    	<!--  Fin Nuevo Certificado -->
    
      </div>
      <hr>
     </td>
  </tr>
</table>
</form>

</body>

</html>
