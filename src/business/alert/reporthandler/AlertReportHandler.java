package business.alert.reporthandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.jfree.report.DefaultResourceBundleFactory;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;
import org.jfree.report.modules.output.table.xls.ExcelProcessor;

import persistence.util.Util;

import business.alert.access.AlertSystemAccess;
import business.alert.reporthandler.utilReport.ModulesReports;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertDriverData;
import communication.driver.report.FOSADriver;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class AlertReportHandler implements Serializable{

	
    private int ALL = 0;
    public static final String XSL_NAME = "situacion_de_conductores_";
    
    
    public AlertReportHandler(){ 
    	System.out.println("New AlertReportHandler");
    }
    
    /**
     * Reporte de Conductores para FOSA
     * @param divorg1
     * @param directory
     * @param fileName
     * @param sys
     * @return
     * @throws Exception
     */
    public  String reportDriversForestales(int divorg1, String directory, 
    		String fileName, AlertSystemAccess sys, int idCorporation) {
		
    	try{
	        /* Creo un systemAccess "generico" de Forestales: 
	         * 
	         * FOSA o WEYERHAEUSER
	         * */	        
	
	        AlertDivisionForm reportForm = new AlertDivisionForm();
	        
	        String reportName = XSL_NAME+fileName;
	        String output = "XLSAlertFile";
	        	
	    	Integer [][] divOrgs = null;
	    	Integer [][] divGeos = null;
	
	    	// Especifico un contratista, lo voy a recibir por parámetro.
	    	
	    	
	    	if (idCorporation == CorporationData.WEYERHAEUSER){
	    		divOrgs = new Integer[3][];
	    		divOrgs[2] = new Integer[1];
		    	divOrgs[2][0] = divorg1; // divorg1
	    	}else{
	    		// FOSA:
	    		divOrgs = new Integer[1][];
		    	divOrgs[0] = new Integer[1];
	    		divOrgs[0][0] = divorg1; // divorg1
	    	}
	    	
	    	JFreeReport report = null;
	        try{
	        	JFreeReportBoot boot = JFreeReportBoot.getInstance();
	        	boot.start();
	        }catch(Throwable e){
	        	e.printStackTrace();
		    	System.out.println("GUS: Error al Instanciar el JFreeReport... \nintento de nuevo...");
		    	JFreeReportBoot boot = JFreeReportBoot.getInstance();
	        	boot.start();
	        }
	        
	        report = this.getReport(sys, reportForm, 1, divOrgs, divGeos, idCorporation);	 
	    	
	        if (report != null){
	    		this.executeReport(null,null,report,output,null,reportName,true,directory);
	    	}
	    	
	        return reportName;
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println("GUS: Error al Instanciar 2 veces el JFreeReport...");
	    }
		return null;
	}
	   
    /**
     * 
     * @param sys
     * @param reportForm
     * @param page
     * @param divOrgs
     * @param divGeos
     * @return
     * @throws Exception
     */
    private JFreeReport getReport(AlertSystemAccess sys, AlertDivisionForm reportForm,int page, 
	    		Integer [][] divOrgs, Integer [][] divGeos, int idCorporation) throws Exception {

    	
        Hashtable<String,String> parameters = new Hashtable<String,String>();	        
        parameters.put("status",String.valueOf(DriverData.ACTIVE));
        parameters.put("accidents",String.valueOf(ALL));  // ALL = 0 
        parameters.put("incidents",String.valueOf(ALL));  // ALL = 0 
        parameters.put("fines",String.valueOf(ALL));      // ALL = 0   
        
        if(sys.getUserSessionData().isForest()) {
            parameters.put("bps",String.valueOf(DriverData.ALL)); 			// ALL = 2
            parameters.put("aptitudStatus",String.valueOf(DriverData.ALL)); // ALL = 2
            parameters.put("aptitudResult",String.valueOf(DriverData.ALL)); // ALL = 2
            parameters.put("alcoholStatus",String.valueOf(DriverData.ALL)); // ALL = 2
            parameters.put("alcoholResult",String.valueOf(DriverData.ALL)); // ALL = 2
            parameters.put("postiveAlcohol",String.valueOf(DriverData.ALL)); // ALL = 2     
        }
        
        parameters.put("type","detailed");
        
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = 0;
        int count = -1;

        if (idCorporation == CorporationData.WEYERHAEUSER){
        	// WEYERHAEUSER
        	
        	ListResult listResult = ModulesReports.getFOSAReportDrivers(parameters,divOrgs,divGeos,first,
					count,sys.getUserSessionData());
        	
        	if (listResult != null && listResult.getTotal() >= 0){
        		AlertWeyerhaeuserDriverReportData result = new AlertWeyerhaeuserDriverReportData(listResult);
        	
        		result.setMessages(messages);
        		int pages = (int)(result.getTotal() / 50);
        		if(pages*50 < result.getTotal()) {
                  pages++;
        		}
              
        		boolean[] expired = result.getExpired();
        		AlertWeyerhaeuserDriverReportFactory factory = 
        				new AlertWeyerhaeuserDriverReportFactory("Drivers",result,reportForm,sys,
              			messages,pages,page, count, expired);
        		report = factory.createReport();
        	}
              
        	
        }else{
        	// FOSA
        	
        	AlertFOSADetailedDriverReportData result = new AlertFOSADetailedDriverReportData(
        			ModulesReports.getFOSAReportDrivers(parameters,divOrgs,divGeos,first,
        					count,sys.getUserSessionData()));
      
        	  result.setMessages(messages);
              int pages = (int)(result.getTotal() / 50);
              if(pages*50 < result.getTotal()) {
                  pages++;
              }
              
              boolean[] expired = result.getExpired();
              AlertFOSADetailedDriverReportFactory factory = 
              	new AlertFOSADetailedDriverReportFactory("Drivers",result,reportForm,sys,
              			messages,pages,page, count, expired);
              report = factory.createReport();        
              
        }
        
      
        return report;
    }
	
    /**
     * 
     * @param mapping
     * @param response
     * @param report
     * @param outputFormat
     * @param session
     * @param name
     * @param save
     * @param directory
     * @throws Exception
     */
    private void executeReport(ActionMapping mapping, HttpServletResponse response, 
    		JFreeReport report, String outputFormat,HttpSession session, String name, 
    		boolean save,String directory) throws Exception {
    	
    	if (report != null){
    		report.setResourceBundleFactory(new DefaultResourceBundleFactory(new Locale("en","US")));
    	
    		if (outputFormat.equalsIgnoreCase("XLSAlertFile")){
	        	// Reportes Excel para Alertas, quedan guardados en el disco duro, pero no se muestran.
	           
	        	/*
	        	final ServletOutputStream out = response.getOutputStream();
	            response.setHeader("Content-Type", "application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "inline; filename=\""+name+".xls" + "\"");                
	            */
	        	
	        	File f = new File(directory+name+".xls");
	            FileOutputStream fos = new FileOutputStream(f);
	            
	            final ExcelProcessor processor = new ExcelProcessor(report);
	            processor.setOutputStream(fos);
	            processor.processReport();
	            fos.flush();
	            fos.close();        	
	        }
    	}
    }

	public String reportDriversForestalesGabinetesInline(int divorg1,
			AlertSystemAccess sys, Integer corporationId) {
		try{
			
			 Hashtable<String,String> parameters = new Hashtable<String,String>();	        
		       
		        
	          
	         parameters.put("type","detailed");        
	        
	        
	        
	        Integer [][] divOrgs = null;
	    	Integer [][] divGeos = null;
	    	int first = 0;
	        int count = -1;
	         
	    	// Especifico un contratista, lo voy a recibir por parámetro.
	    	
    	 
    		// FOSA:
    		divOrgs = new Integer[1][];
	    	divOrgs[0] = new Integer[1];
    		divOrgs[0][0] = divorg1; // divorg1
     
			ListResult listResult = ModulesReports.getFOSAReportDriversGabinete(parameters,divOrgs,divGeos,sys.getUserSessionData());
			
			String result="<table border=1>";
			Iterator iter= listResult.getElements().iterator();
			
			if(listResult.getElements().size()>0){
				result+="<tr style='background-color:Black;color:white;font-weight:bold;'><td>"+sys.getText().getText("generic.alert.reportdriver.ci")+"</td><td>"+sys.getText().getText("generic.alert.reportdriver.name")+"</td><td>"+sys.getText().getText("generic.alert.reportdriver.lastname")+"</td><td>"+sys.getText().getText("generic.alert.reportdriver.expiry")+"</td></tr>";
				int i=0;
				while(iter.hasNext()){
					i++;
					String style="background-color:#F7F7F7;color:black;font-family:Arial, Helvetica, sans-serif;font-size:12;";
					if(i%2==0){
						style="background-color:#FFFFFF;color:black;font-family:Arial, Helvetica, sans-serif;font-size:12;";
					}
					
					AlertDriverData driver= (AlertDriverData)iter.next();
					result+="<tr style='"+style+"'><td>"+driver.getDriverReportDataType().getDriver().getCorporationId()+"</td><td>"+driver.getDriverReportDataType().getFirstname()+"</td><td>"+driver.getDriverReportDataType().getLastname()+"</td><td>"+(driver.getExpiry()!=null?Util.formatDate(driver.getExpiry()):"----")+"</td></tr>";
				}
			}else{
				result+="<tr style='background-color:Black;color:white;font-weight:bold;'><td>"+sys.getText().getText("driver.unconformities.nodata")+"</td></tr>";
				
			}
			result+="</table>";
					
			return result;
			
	    }catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println("AlertReportHandler(reportDriversForestalesGabinetesInline): Error ");
	    }
		return "";
	}

	public String reportDriversForestalesBTWsInline(int divorg1,
			AlertSystemAccess sys, Integer idcorporationId) {
		try{
			
			Hashtable<String,String> parameters = new Hashtable<String,String>();	        
	        parameters.put("status",String.valueOf(DriverData.ACTIVE));
	        parameters.put("type","detailed");
	        
	        Integer [][] divOrgs = null;
	    	Integer [][] divGeos = null;
	    	int first = 0;
	        int count = -1;
	         
	    	// Especifico un contratista, lo voy a recibir por parámetro.
	    	
    	 
    		// FOSA:
    		divOrgs = new Integer[1][];
	    	divOrgs[0] = new Integer[1];
    		divOrgs[0][0] = divorg1; // divorg1
    		
	        ListResult listResult = ModulesReports.getFOSAReportDriversTraining(parameters,divOrgs,divGeos,sys.getUserSessionData());
			
	    	String result="<br><table border=1>";
			Iterator iter= listResult.getElements().iterator();
			if(listResult.getElements().size()>0){
				result+="<tr style='background-color:Black;color:white;font-weight:bold;'><td>"+sys.getText().getText("generic.alert.reportdriver.ci")+"</td><td>"+sys.getText().getText("generic.alert.reportdriver.name")+"</td><td>"+sys.getText().getText("generic.alert.reportdriver.lastname")+"</td><td>"+sys.getText().getText("generic.alert.reportdriver.expiry")+"</td></tr>";
				int i=0;
				while(iter.hasNext()){
					i++;
					String style="background-color:#F7F7F7;color:black;font-family:Arial, Helvetica, sans-serif;font-size:12;";
					if(i%2==0){
						style="background-color:#FFFFFF;color:black;font-family:Arial, Helvetica, sans-serif;font-size:12;";
					}
					
					AlertDriverData driver= (AlertDriverData)iter.next();
					result+="<tr style='"+style+"'><td>"+driver.getDriverReportDataType().getDriver().getCorporationId()+"</td><td>"+driver.getDriverReportDataType().getFirstname()+"</td><td>"+driver.getDriverReportDataType().getLastname()+"</td><td>"+(driver.getExpiry()!=null?Util.formatDate(driver.getExpiry()):"----")+"</td></tr>";
				}
			}else{
				result+="<tr style='background-color:Black;color:white;font-weight:bold;'><td>"+sys.getText().getText("driver.unconformities.nodata")+"</td></tr>";
			}
			result+="</table>";
					
			return result;
			
	      
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println("AlertReportHandler(reportDriversForestalesBTWsInline): Error ");
	    }
		return "BTBs";
	}
	
}
