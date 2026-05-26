<%@page language="java" 
    errorPage="../exception.jsp"
	import="business.*"	
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
%>
<%!
	SystemAccess sys;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	if (sys.getCorporationId() == CorporationData.MONSANTO){
%>


<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center"> 
  <table width="559" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="73" colspan="4"> 
        <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td><div align="center"><font size="5" face="Arial, Helvetica, sans-serif"><strong></strong></font></div>
              <div align="center"></div>
              <table width="91%" height="63" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="3B6692">
                <tr> 
                  <td height="63" align="center" valign="middle"> <font color="#FFFFFF" size="5" face="Arial, Helvetica, sans-serif"><strong><em>Manual 
                    de Usuario</em></strong></font><br> <font color="#FFFFFF" size="2" face="Arial, Helvetica, sans-serif"><strong>INSTRUCCIONES 
                    B&Aacute;SICAS DE ACCESO Y USO</strong></font></td>
                </tr>
              </table></td>
          </tr>
        </table>
        <div align="right"> </div></td>
    </tr>
       
    
    <tr valign="top"> 
      <td height="21" colspan="5"> <hr color="#3B6692"noshade></td>
    </tr>
    
    <tr> 
      	<td height="19" colspan="5">
      		<%
      			String link1 = SecurityConstants.DATACENTER5_LINK_DIRECTORY+"monsanto/help/es/Ingreso_de_Odometros.pdf";
      			String link2 = SecurityConstants.DATACENTER5_LINK_DIRECTORY+"monsanto/help/es/Ingreso_de_Eventos.pdf"; 
      		 %>
      		<a href='<%=link1%>' >Ingreso de Od&oacute;metros</a><br/>
			<a href='<%=link2%>' >Ingreso de Eventos</a>			
		</td>
    </tr>
  </table>
</div>
</body>
<%
	}
%>