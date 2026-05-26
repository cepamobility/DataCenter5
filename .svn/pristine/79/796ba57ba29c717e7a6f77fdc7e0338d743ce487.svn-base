<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
%>

<%!		String msg; 
		SystemAccess sys;%>
<%
			msg = (String)session.getAttribute("Msg");
			sys = (SystemAccess)session.getAttribute("SystemAccess");
			
			if (msg == null && sys != null && sys.getUserSessionData() != null){
				// Refactoring: Obtiene el mensaje de userSessionData
				msg = sys.getUserSessionData().getMsg();
			}
			
			if(msg!=null){
				session.removeAttribute("Msg");
				if (sys != null && sys.getUserSessionData() != null){
					sys.getUserSessionData().setMsg(null);
				}
	
%>
	
				<script language="javascript">	
					alert('<%=msg%>');
				</script>
<%
			}
%>
