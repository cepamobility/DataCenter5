<%@page language="java"
    errorPage="../../exception.jsp"
	import="java.io.File"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.*"
	import="presentation.report.driver.*"
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot"
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.activity.schedule.report.*"
	import="presentation.report.activity.schedule.*"
	import="presentation.translator.web.activity.schedule.*"
	import="presentation.report.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	

try{
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	int pageNumber = Integer.parseInt(request.getParameter("page")); 
	
	DriversWithoutBTWForm reportForm = new DriversWithoutBTWForm(request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
	        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
	        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
	        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));

	reportForm.setFromday(request.getParameter("fromday"));
	reportForm.setFrommonth(request.getParameter("frommonth"));
	reportForm.setFromyear(request.getParameter("fromyear"));
	reportForm.setToday(request.getParameter("today"));
	reportForm.setTomonth(request.getParameter("tomonth"));
	reportForm.setToyear(request.getParameter("toyear"));
	
	reportForm.setStatus(request.getParameter("status"));
	if(request.getParameter("vehicleAllowed") != null)
		reportForm.setVehicles(request.getParameter("vehicleAllowed"));
	
	reportForm.setOutput(request.getParameter("output"));
	
	JFreeReportBoot.getInstance().start();
	JFreeReport report = (new DriversWithoutBTWAction()).getReport(sys,reportForm,pageNumber);
		
	byte[] b = new ReportUtil().executeReport(response,report,"HTML",session,"driver",null);

	if(b != null) {
		for(int i = 0; i < b.length; i++) {
		    response.getWriter().write(b[i]);
		} 
		
		String image = null;
		ArrayList arrayGraphics = (ArrayList)session.getAttribute("arrayGraficas");
		if (arrayGraphics != null && arrayGraphics.size() > 0)
		{
			for (int i = 0; i < arrayGraphics.size(); i++)
			{
				image = "/images/" + (String)arrayGraphics.get(i);			
%>			<div align="center">
				<br>
				<img src='<%=image%>' />
				<br>
			</div>
<%			}
		}
	}
}catch(Exception e){
	e.printStackTrace();
}
%>

