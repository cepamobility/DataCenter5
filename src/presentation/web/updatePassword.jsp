<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="communication.administration.user.tables.*"
	import="communication.language.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String passwd = request.getParameter("password");
	String confirmation = request.getParameter("rePassword");
	String valid = null;
	if(!passwd.equals(confirmation)) {
        session.setAttribute("Msg",Text.getMessage("generic user.userdata.passconfirm"));
    	response.sendRedirect("./firstaccess.jsp");
	}else {
		if(sys.getUserReportFacade().getPasswordType(sys.getUserSessionData().getFilter().getLoginName(),sys.getUserSessionData()) == UserAttributes.DIFFICULT) {
		    valid = UserData.validatePassword(passwd);
		}
	    if(valid != null) {
	        session.setAttribute("Msg", Text.getMessage(valid));
	    	response.sendRedirect("./firstaccess.jsp");
	    }else {
			UserABMFacade userABM = null;
			
			userABM = sys.getUserABMFacade();
			userABM.userResetOwnPassword(passwd,sys.getUserSessionData());
			session.setAttribute("SystemAccess",new SystemAccess(sys.getUserSessionData().getFilter().getLoginName()));
			
			response.sendRedirect("./home.jsp");
	    }
	}
%>

</html>
