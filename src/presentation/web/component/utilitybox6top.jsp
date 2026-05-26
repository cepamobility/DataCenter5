
<%@page language="java"
	errorPage="../exception.jsp"
%>

    		
				<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">  				
					<tr class="guide1">
						<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
   		  		  		<td height="30" class="guide0"><div align="left"><%=(request.getParameter("title") == null) ? "" : request.getParameter("title")%></div></td>
						<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
  					</tr>
  					<tr>
						<td class="leftLine">&nbsp;</td>
        				<td height="6"><img src="x" width="1" height="1"></td>
        				<td class="rightLine">&nbsp;</td>
  					</tr>  					  					
    				<tr>
    					<td class="leftLine">&nbsp;</td>
    					<td>