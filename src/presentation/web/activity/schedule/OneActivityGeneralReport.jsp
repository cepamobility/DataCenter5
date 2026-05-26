<%@page language="java"
    errorPage="../../exception.jsp"
	import="java.io.File"
	import="java.util.ArrayList"
	import="business.*"
	import="java.util.*"
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
	import="communication.activity.tables.*"	
	import="communication.activity.report.ActivityGeneralReportData"	
	import="presentation.report.activity.ActivityGeneralReportFactory"
	import="org.jfree.chart.ChartFactory"
	import="org.jfree.chart.ChartUtilities"
	import="communication.administration.user.UserSessionData" 
	import="org.jfree.chart.JFreeChart"
	import="org.jfree.chart.plot.PlotOrientation"
	import="org.jfree.data.category.DefaultCategoryDataset"
	import="java.awt.Color"
	import="communication.driver.tables.DriverData" 
	import="communication.security.*"


%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%	
	boolean isOld = false;
	Collection drivers = new LinkedList();
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
//	int pageNumber = Integer.parseInt(request.getParameter("page")); 
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	String idDrivers = "";
	String idActivity = (String)request.getParameter("activityId");
	
    CEPAActivityScheduleData activity = sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),sys.getUserSessionData());
    Object[][] participants = sys.getActivityReportFacade().getParticipantsRegistry(activity.getActivityId(),sys.getUserSessionData());
	
    int[][] clasifications;
    isOld = activity.getIsOld();  
    
    String activityType = activity.getType().getResourceBundleId();
    String checklist = activity.getDefinition().getCheckList().getId().toString();
    
    if (!activity.getIsOld()) {
    	
    	clasifications = new int[6][2];
    	
        for(int i = 0; i < participants.length; i++) {
            if(participants[i][3].equals(CEPAActivityConstants.APRUVED)) {
                clasifications[0][0]++; 
            }else if(participants[i][3].equals(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS)) {
                clasifications[1][0]++; 
            }else if(participants[i][3].equals(CEPAActivityConstants.NOTAPRUVED)) {
                clasifications[2][0]++; 
            }else if(participants[i][3].equals(CEPAActivityConstants.THEORETICALPENDING)){
            	clasifications[3][0]++;
            }else if(participants[i][3].equals(CEPAActivityConstants.PRACTICEPENDING)){
            	clasifications[4][0]++;
            }else{
                clasifications[5][0]++; 
            }
        }
        for(int i = 0; i < 6; i++) {
            clasifications[i][1] = (int)(clasifications[i][0] * 100 / participants.length); 
        }
        
        
    }
    else {
    	clasifications = new int[12][2];
        for(int i = 0; i < participants.length; i++) {
            if(participants[i][3].equals("dc4.notasdescripcion.nddesccorta1")) {
                clasifications[0][0]++; 
            }else if(participants[i][3].equals("dc4.notasdescripcion.nddesccorta2")) {
                clasifications[1][0]++; 
            }else if(participants[i][3].equals("dc4.notasdescripcion.nddesccorta3")) {
                clasifications[2][0]++;
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta4")) {
                clasifications[3][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta5")) {
                clasifications[4][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta6")) {
                clasifications[5][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta7")) {
                clasifications[6][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta8")) {
                clasifications[7][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta9")) {
                clasifications[8][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta10")) {
                clasifications[9][0]++;	                	
            }else if (participants[i][3].equals("dc4.notasdescripcion.nddesccorta11")) {	                	
                clasifications[10][0]++;	                
            }else {
                clasifications[11][0]++; 
            }
        }
        for(int i = 0; i < 12; i++) {
            clasifications[i][1] = (int)(clasifications[i][0] * 100 / participants.length); 
        }
    }
    
%>
<script type="text/javascript" language="javascript" >
function reportAllPDF(form){
		<% 
			idDrivers ="";
			drivers = sys.getActivityReportFacade().getParticipants(new Integer(idActivity),userSessionData);
			if (drivers.size() > 0){
				Iterator it = drivers.iterator();
				while (it.hasNext()){
					DriverData driver = (DriverData) it.next();
					idDrivers += driver.getId().toString()+";";
				}
				idDrivers = idDrivers.substring(0,idDrivers.length()-1);
			}
			//System.out.println("idDrivers:" + idDrivers);
		%>
		form.idDrivers.value = '<%=idDrivers%>';
		form.idActivity.value = '<%=idActivity %>';
		form.activityType.value = '<%=activityType%>';
		form.checklist.value = '<%=checklist%>';
		form.submit();	
	}	
</script>

<%
    ActivityGeneralReportData reportData = new ActivityGeneralReportData(participants,sys.getText());

    JFreeReportBoot.getInstance().start();
//    ActivityGeneralReportFactory factory = new ActivityGeneralReportFactory("Activity",new StaticTableModelProvider(reportData).getModel(),sys,activity,clasifications,data.getOutput());
//    ActivityGeneralReportFactory factory = new ActivityGeneralReportFactory("Activity",new StaticTableModelProvider(reportData).getModel(),sys,activity,clasifications,"HTML");

	String output = (String)request.getParameter("output");
	if (output == null)
		output = "HTML";
	
    ActivityGeneralReportFactory factory = new ActivityGeneralReportFactory("Activity",reportData,sys,activity,clasifications,output);
    JFreeReport report = factory.createReport();        
	
    final DefaultCategoryDataset result = new DefaultCategoryDataset();
    if (!activity.getIsOld()) {
    	
    	result.setValue(clasifications[0][0], messages.getText(CEPAActivityConstants.APRUVED),"");
    	result.setValue(clasifications[1][0], messages.getText(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS),"");
    	result.setValue(clasifications[2][0], messages.getText(CEPAActivityConstants.NOTAPRUVED),"");
    	result.setValue(clasifications[3][0], messages.getText(CEPAActivityConstants.THEORETICALPENDING),"");
    	result.setValue(clasifications[4][0], messages.getText(CEPAActivityConstants.PRACTICEPENDING),"");

    }
    else {
    	result.setValue(clasifications[0][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta1"),"");
    	result.setValue(clasifications[1][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta2"),"");
    	result.setValue(clasifications[2][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta3"),"");
    	result.setValue(clasifications[3][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta4"),"");
    	result.setValue(clasifications[4][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta5"),"");
    	result.setValue(clasifications[5][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta6"),"");
    	result.setValue(clasifications[6][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta7"),"");
    	result.setValue(clasifications[7][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta8"),"");
    	result.setValue(clasifications[8][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta9"),"");
    	result.setValue(clasifications[9][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta10"),"");
    	result.setValue(clasifications[10][0], sys.getText().getText("dc4.notasdescripcion.nddesccorta11"),"");            	
    }

    final JFreeChart chart = ChartFactory.createBarChart("Resultados","Resultados","",result, PlotOrientation.VERTICAL,true,true,false);
    chart.setBackgroundPaint(Color.WHITE);
    report.setProperty("Resultados", chart);
    //report.setPropertyMarked("Resultados", true);      
    File imagen = new File(SecurityConstants.IMAGES_WAR_DIRECTORY + sys.getUserSessionData().getFilter().getLoginName()+"-activity-"+String.valueOf(System.currentTimeMillis())+".png");           
    ChartUtilities.saveChartAsPNG(imagen,chart,600,400);            

    ArrayList arrayGraficas = new ArrayList();
    arrayGraficas.add(imagen.getName());
    
//    session.setAttribute("arrayGraficas", arrayGraficas);
    
//	byte[] b = ReportUtil.executeReport(response,report,"HTML",session,"driver",arrayGraficas);
	ReportUtil reportUtil = new ReportUtil();
	byte[] b = reportUtil.executeReport(response,report,output,session,"driver",arrayGraficas);

	if(b != null) {
		for(int i = 0; i < b.length; i++) {
		    response.getWriter().write(b[i]);
		} 

		String image = null;
		String button="";
		if(	(drivers.size() > 0) && !(
			(activity.getDefinition().getResourceKeyName().
     			equals("datatype.scheduledactivitytype.commentarydriveother") 
				&& !activity.getIsOld()) 						
		) && activity.getDefinition().isHaveIndividualReport() ){
		 	button = "<br><div  align='left'>" +
							"<input name='btnAllDrivers' type='button' " + 
							" onClick='javascript:reportAllPDF(document.forms[\"" + 
							    ((!isOld)?"ActivityReportFormAllDrivers":"ActivityReportFormAllDriversOld") +
				    		"\"]);' " +
							" value=' " + messages.getText("generic.messages.allparticipantsresult") + "' " +
							" class='input'/>" +                    															
						"</div><br>";
			response.getWriter().write(button);		
		}
		ArrayList arrayGraphics = (ArrayList)session.getAttribute("arrayGraficas");
		if (arrayGraphics != null && arrayGraphics.size() > 0)
		{
			for (int i = 0; i < arrayGraphics.size(); i++)
			{
				image = "/images/" + (String)arrayGraphics.get(i);			
%>			<div align="left">
				<br>
				<img src='<%=image%>' />
				<br>
			</div>
<%			}
		}
	}
	
	String activityId = String.valueOf(activity.getActivityId());
//	String action = "./layout.jsp?refer=/activity/schedule/OneActivityGeneralReport.jsp?activityId=" + activityId;
	String action = "./layout.jsp?refer=/activity/schedule/OneActivityGeneralReportPDF.jsp";
	
%>

		<html:form action="/Activitygeneralreport">			
			<html:hidden property="all"/>
			<html:hidden property="pageNum"/>
			<html:hidden property="id" value ='<%= activityId %>'/>
			<html:hidden property="reportAction" value=""/>	
			<html:hidden property="output" value="PDF"/>			
			<html:hidden property="month1"/>
			<html:hidden property="year1"/>
			<html:hidden property="month2"/>
			<html:hidden property="year2"/>			
			<html:hidden property="type"/>
			<html:submit styleClass="input">PDF></html:submit>
		</html:form>

		<!-- Formulario para Reporte Total -->
		<html:form action="/ActivityReportAllDrivers"  method="post" >
			<html:hidden property="idDrivers" value="" />
			<html:hidden property="idActivity" value="" />
			<html:hidden property="activityType" value="" />
			<html:hidden property="checklist" value="" />
			
		</html:form>		
		<!-- Formulario para Reporte Total de Actividades Old-->
		<html:form action="/ActivityReportAllDriversOld"  method="post" >
			<html:hidden property="idDrivers" value="" />
			<html:hidden property="idActivity" value="" />
			<html:hidden property="activityType" value="" />
			<html:hidden property="checklist" value="" />
		</html:form>	

