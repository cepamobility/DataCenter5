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
	import="org.apache.struts.action.*"
	import="org.apache.struts.config.*"
	import="org.apache.struts.util.*"
	import="presentation.translator.web.activity.schedule.AssessmentResultAction"
	
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
	
	AssessmentResultForm reportForm = new AssessmentResultForm();
	
	String assessment = (request.getParameter("assessment") == null)?"1":request.getParameter("assessment");
	String format = (request.getParameter("format") == null)?"HTML":request.getParameter("format");
	
	System.out.println("Assessment Id: "+assessment);
	
	reportForm.setFormat(format);
	reportForm.setPage( (new Integer(pageNumber)).toString()	);
	reportForm.setAssessment(assessment);
	
	JFreeReportBoot.getInstance().start();
	JFreeReport report = (new AssessmentResultAction()).getReport(sys,reportForm,pageNumber);
		
	byte[] b = new ReportUtil().executeReport(response,report,"HTML",session,"AssessmentResult",null);

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
	System.out.println(e.getMessage());
}
%>

