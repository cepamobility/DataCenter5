<%@page language="java"
    errorPage="../exception.jsp"
	import="java.util.ArrayList"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="presentation.report.driver.*"
	import="communication.language.*"
	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot"
	import="org.jfree.report.ext.servletdemo.*"
	import="communication.driver.report.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.report.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	

	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	int pageNumber = Integer.parseInt(request.getParameter("page"));
	JFreeReport report;
	
	if (!Util.empty((String)request.getParameter("combined")) && 
			((String)request.getParameter("combined")).equals("true") )	{
		CombinedReportForm reportForm = new CombinedReportForm(
				request.getParameter("divOrg11"),
				request.getParameter("divOrg12"),
				request.getParameter("divOrg13"),
		        request.getParameter("divOrg21"),
		        request.getParameter("divOrg22"),
		        request.getParameter("divOrg23"),
		        request.getParameter("divOrg31"),
		        request.getParameter("divOrg32"),
		        request.getParameter("divOrg33"),
		        request.getParameter("country1"),
		        request.getParameter("country2"),
		        request.getParameter("country3"),
		        request.getParameter("firstName"),
		        request.getParameter("lastName"),
		        request.getParameter("status"),
		        request.getParameter("activities"),
		        request.getParameter("fromMonthActivity"),
		        request.getParameter("fromYearActivity"),
		        request.getParameter("toMonthActivity"),
		        request.getParameter("toYearActivity"),	        
		        request.getParameter("events"),
		        request.getParameter("fromMonthEvent"),
		        request.getParameter("fromYearEvent"),
		        request.getParameter("toMonthEvent"),
		        request.getParameter("toYearEvent"),
		        request.getParameter("activityType"),
		        request.getParameter("accidentType"),
		        request.getParameter("accidentCount"),
		        request.getParameter("evitability"),
		        request.getParameter("incidentType"),
		        request.getParameter("incidentCount"),
		        request.getParameter("fineType"),
		        request.getParameter("fineCount"),
		        request.getParameter("relationEA"),
		        request.getParameter("relationAE"),
		        request.getParameter("relationAccident"),
		        request.getParameter("relationIncident"),
		        request.getParameter("relationFine"),
		        request.getParameter("output"));
				JFreeReportBoot.getInstance().start();
				report = new CombinedReportAction().getReport(sys,reportForm,pageNumber);
	}else if (!Util.empty((String)request.getParameter("hrdQuery")) && 
			((String)request.getParameter("hrdQuery")).equals("true") )	{
		// HRD:
		DriverHRDQueryForm reportForm = new DriverHRDQueryForm(
				request.getParameter("divOrg11"),
				request.getParameter("divOrg12"),
				request.getParameter("divOrg13"),
		        request.getParameter("divOrg21"),
		        request.getParameter("divOrg22"),
		        request.getParameter("divOrg23"),
		        request.getParameter("divOrg31"),
		        request.getParameter("divOrg32"),
		        request.getParameter("divOrg33"),
		        request.getParameter("country1"),
		        request.getParameter("country2"),
		        request.getParameter("country3"),
		        request.getParameter("fn"),
		        request.getParameter("ln"),
		        request.getParameter("fy"),
		        request.getParameter("fm"),
		        request.getParameter("tm"),
		        request.getParameter("ty"),
		        new Integer(request.getParameter("status")).intValue(),
		        request.getParameter("output"),
		        request.getParameter("rt"));
		reportForm.setLastUpdate(request.getParameter("update"));
		JFreeReportBoot.getInstance().start();
		report = new DriverHRDQueryAction().getReport(sys,reportForm,pageNumber);

    }else if (!Util.empty((String)request.getParameter("phrdQuery")) &&
		((String)request.getParameter("phrdQuery")).equals("true") ){
    	
    	// PHRD:
    	DriverPHRDQueryForm reportForm = new DriverPHRDQueryForm(
				request.getParameter("divOrg11"),
				request.getParameter("divOrg12"),
				request.getParameter("divOrg13"),
		        request.getParameter("divOrg21"),
		        request.getParameter("divOrg22"),
		        request.getParameter("divOrg23"),
		        request.getParameter("divOrg31"),
		        request.getParameter("divOrg32"),
		        request.getParameter("divOrg33"),
		        request.getParameter("country1"),
		        request.getParameter("country2"),
		        request.getParameter("country3"),
		        request.getParameter("fn"),
		        request.getParameter("ln"),
		        request.getParameter("fy"),
		        request.getParameter("fm"),
		        request.getParameter("tm"),
		        request.getParameter("ty"),
		        new Integer(request.getParameter("status")).intValue(),
		        request.getParameter("output"));
		JFreeReportBoot.getInstance().start();
		report = new DriverPHRDQueryAction().getReport(sys,reportForm,pageNumber);	
    	
    }else if (!Util.empty((String)request.getParameter("hrdMonQuery")) &&
			((String)request.getParameter("hrdMonQuery")).equals("true") )
	{
		// HRD Brasil Monsnato:
		DriverHRDMonQueryForm reportForm = new DriverHRDMonQueryForm(
				request.getParameter("divOrg11"),
				request.getParameter("divOrg12"),
				request.getParameter("divOrg13"),
		        request.getParameter("divOrg21"),
		        request.getParameter("divOrg22"),
		        request.getParameter("divOrg23"),
		        request.getParameter("divOrg31"),
		        request.getParameter("divOrg32"),
		        request.getParameter("divOrg33"),
		        request.getParameter("country1"),
		        request.getParameter("country2"),
		        request.getParameter("country3"),
		        request.getParameter("fn"),
		        request.getParameter("ln"),
		        request.getParameter("fy"),
		        request.getParameter("fm"),
		        request.getParameter("tm"),
		        request.getParameter("ty"),
		        new Integer(request.getParameter("status")).intValue(),
		        request.getParameter("output"),
		        request.getParameter("rt"),request.getParameter("avoidable"));
		reportForm.setLastUpdate(request.getParameter("update"));
		JFreeReportBoot.getInstance().start();
		report = new DriverHRDMonQueryAction().getReport(sys,reportForm,pageNumber);

    }else if (!Util.empty((String)request.getParameter("historyQuery")) &&
			Boolean.parseBoolean((String)request.getParameter("historyQuery"))) {
        DriverHistoryQueryForm form = new DriverHistoryQueryForm(
				request.getParameter("divOrg11"),
				request.getParameter("divOrg12"),
				request.getParameter("divOrg13"),
		        request.getParameter("divOrg21"),
		        request.getParameter("divOrg22"),
		        request.getParameter("divOrg23"),
		        request.getParameter("divOrg31"),
		        request.getParameter("divOrg32"),
		        request.getParameter("divOrg33"),
		        request.getParameter("country1"),
		        request.getParameter("country2"),
		        request.getParameter("country3"),
		        request.getParameter("firstname"),
		        request.getParameter("lastname"),
		        request.getParameter("status"),
		        request.getParameter("output"),
		        request.getParameter("reportType"));
			form.setLastUpdate(request.getParameter("update"));
			JFreeReportBoot.getInstance().start();
			report = new DriverHistoryQueryAction().getReport(sys,form,pageNumber);
	}else    	
    {
		if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL) {
			FOSADriverReportForm reportForm = new FOSADriverReportForm(
					request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
			        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
			        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
			        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));
					reportForm.setId(request.getParameter("id"));
					reportForm.setName(request.getParameter("name"));
					reportForm.setLastname(request.getParameter("lastname"));
					reportForm.setLastname(request.getParameter("birthDate"));
			        reportForm.setLastname(request.getParameter("birthMonth"));
			        reportForm.setLastname(request.getParameter("birthYear"));
					reportForm.setStatus(request.getParameter("status"));
					reportForm.setAccident(request.getParameter("accident"));
					reportForm.setIncident(request.getParameter("incident"));
					reportForm.setFine(request.getParameter("fine"));
					reportForm.setAccidentCount(request.getParameter("accidentCount"));
					reportForm.setIncidentCount(request.getParameter("incidentCount"));
					reportForm.setFineCount(request.getParameter("fineCount"));
					reportForm.setType(request.getParameter("type"));
					reportForm.setOutput(request.getParameter("output"));				
					reportForm.setBps(request.getParameter("bps"));
					reportForm.setAlcoholResult(request.getParameter("alcoholResult"));
					reportForm.setAlcoholStatus(request.getParameter("alcoholStatus"));
					reportForm.setAptitudStatus(request.getParameter("aptitudStatus"));
					reportForm.setAptitudResult(request.getParameter("aptitudResult"));
					reportForm.setPostiveAlcohol(request.getParameter("postiveAlcohol"));
	
	        JFreeReportBoot.getInstance().start();
			report = new FOSADriverReportAction().getReport(sys,reportForm,pageNumber);
		}else if(sys.getCorporationId() == CorporationData.LOSPIQUES) {
			DriverMultiReportForm reportForm = new DriverMultiReportForm(
					request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
			        request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
			        request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
			        request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));
					reportForm.setName(request.getParameter("name"));
					reportForm.setStatus(request.getParameter("status"));
					reportForm.setAccident(request.getParameter("accident"));
					reportForm.setIncident(request.getParameter("incident"));
					reportForm.setFine(request.getParameter("fine"));
					reportForm.setAccidentCount(request.getParameter("accidentCount"));
					reportForm.setIncidentCount(request.getParameter("incidentCount"));
					reportForm.setFineCount(request.getParameter("fineCount"));
					reportForm.setType(request.getParameter("type"));
					reportForm.setOutput(request.getParameter("output"));			
					reportForm.setBps(request.getParameter("bps"));
					reportForm.setAlcoholResult(request.getParameter("alcoholResult"));
					reportForm.setAlcoholStatus(request.getParameter("alcoholStatus"));
					reportForm.setAptitudStatus(request.getParameter("aptitudStatus"));
					reportForm.setAptitudResult(request.getParameter("aptitudResult"));
					reportForm.setPostiveAlcohol(request.getParameter("postiveAlcohol"));
		
		        JFreeReportBoot.getInstance().start();
				report = new DriverReportAction().getReport(sys,reportForm,pageNumber);
		}else {
			DriverReportForm reportForm = new DriverReportForm(
					request.getParameter("divOrg11"),request.getParameter("divOrg12"),request.getParameter("divOrg13"),
		    		request.getParameter("divOrg21"),request.getParameter("divOrg22"),request.getParameter("divOrg23"),
		        	request.getParameter("divOrg31"),request.getParameter("divOrg32"),request.getParameter("divOrg33"),
		        	request.getParameter("country1"),request.getParameter("country2"),request.getParameter("country3"));
					reportForm.setName(request.getParameter("name"));
					reportForm.setStatus(request.getParameter("status"));
					reportForm.setAccident(request.getParameter("accident"));
					reportForm.setIncident(request.getParameter("incident"));
					reportForm.setFine(request.getParameter("fine"));
					reportForm.setAccidentCount(request.getParameter("accidentCount"));
					reportForm.setIncidentCount(request.getParameter("incidentCount"));
					reportForm.setFineCount(request.getParameter("fineCount"));
					reportForm.setType(request.getParameter("type"));
					reportForm.setOutput(request.getParameter("output"));
		
					reportForm.setBps(request.getParameter("bps"));
					reportForm.setAlcoholResult(request.getParameter("alcoholResult"));
					reportForm.setAlcoholStatus(request.getParameter("alcoholStatus"));
					reportForm.setAptitudStatus(request.getParameter("aptitudStatus"));
					reportForm.setAptitudResult(request.getParameter("aptitudResult"));
					reportForm.setPostiveAlcohol(request.getParameter("postiveAlcohol"));
					reportForm.setWithVehicles(request.getParameter("withVehicles"));
			
	        JFreeReportBoot.getInstance().start();
			report = new DriverReportAction().getReport(sys,reportForm,pageNumber);
		}
    }
	ReportUtil reportUtil= new ReportUtil();
	byte[] b = reportUtil.executeReport(response,report,"HTML",session,"driver",null);

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
%>

