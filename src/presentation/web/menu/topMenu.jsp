<%@ page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="java.util.*"
	import="communication.language.Text"
	import="communication.administration.bookmark.tables.BookmarkData"
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	Text messages=sys.getText();
%>
	<!--  topMenu.jsp -->
	<table width="100%" height="68" border="0" cellpadding="0" cellspacing="0" id="topo">
        <tr>
        	<td  width="1%" align="left">
        		&nbsp;
        	</td>
        	<td  width="14%" align="left">
				<div style="width:200px" align="left">
					<img src='../imgs/cepa_logo.png' style="height:69px;" align="absbottom">
				</div>
        	</td>
        	<td  width="70%" colspan="7" align="center">
				<div style="width:744px" align="center">
					<span style=" font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 75%; color: black; font-weight: bold; vertical-align: middle;">
						<%=messages.getText("generic.messages.welcome")+" "+sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName()%>
					</span>
				</div>
        	</td>
        	<td width="15%" align="right" >
				<img src='<%="../imgs/logo"+String.valueOf(sys.getUserSessionData().getFilter().getCorporationId())+".jpg"%>' 
					align="absbottom" 
					alt="<%=sys.getUserSessionData().getFilter().getCorporationName()%>"
					title="<%=sys.getUserSessionData().getFilter().getCorporationName()%>"
					>
			</td>
        </tr>
    </table>
	<table width="100%" height="21" border="0" cellpadding="0" cellspacing="0">
        <tr>
        	<td width="17%" height="21" rowspan="2">
        	</td>
			<td width="82%" height="21" align="right">
				<a href="#"  onClick="javascript:document.forms[0].submit()">
					<span class="style2">
						<%=messages.getText("generic.messages.logout")%>					
					</span>				
				</a>			
			</td>
            <td width="1%" height="21" align="right" >
            	<span class="style2" >&nbsp;</span>            
            </td>
		</tr>
	</table>