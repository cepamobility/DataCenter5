package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.BindingProvider;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.AssessmentResultReportFactory;
import presentation.translator.web.util.AbstractAction;
import ws.assessment.datacenter.AssessmentResult;
import ws.assessment.datacenter.AssessmentResultList;
import ws.assessment.datacenter.AssessmentWSEJB;
import ws.assessment.datacenter.AssessmentWSEJBService;
import business.SystemAccess;

import communication.activity.report.AssessmentResultData;
import communication.activity.report.AssessmentResultDataType;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;

public class AssessmentResultAction extends AbstractAction { 

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	
 		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();
        String message = null;
        
        String assessment = (String) request.getParameter("assessment");
        
        AssessmentResultForm reportForm = (AssessmentResultForm)form;
        assessment = (String) reportForm.getAssessment();
        Integer idAssessment = (assessment != null)?new Integer(assessment):null;
        
        
        
        if (idAssessment == null){
    		// ERROR, vuelve a la pagina anterior.
        	userSessionData.setUrl("/activity/schedule/AssessmentResultReport.jsp");
     		userSessionData.setMsg(messages.getText(message));
     		return mapping.findForward("layout");	
        }
        
        String formatOutput = ((String) reportForm.getFormat() != null) ?
				reportForm.getFormat():"HTML";
				
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        
        ActionForward fwd = new ReportUtil().executeReport(mapping,
        		response,report,formatOutput,session,"AssessmentResult",true);
        return fwd;
	}
        
	
	
	
	/**
	 * 
	 * @param sys
	 * @param reportForm
	 * @param page
	 * @return
	 * @throws Exception
	 */
    public JFreeReport getReport(SystemAccess sys,AssessmentResultForm reportForm,int page) throws Exception {

        Text messages = sys.getText();
        JFreeReport report = null;
        AssessmentResultList assessmentResults = null;
        
        // Parametros
        String assessment = (String) reportForm.getAssessment();
    	String formatOutput = ((String) reportForm.getFormat() != null) ?
    				reportForm.getFormat():"HTML";
    				
    	int first = (formatOutput.equals("HTML")) ? (page-1) * 50 : 0;
        int count = (formatOutput.equals("HTML")) ? 50 : -1;
    
    	Integer idAssessment = (assessment != null)?new Integer(assessment):null;
    	    		
		/**
		 * 	Comunicacion con el WS:
	 	 */  
		try{
			
			AssessmentWSEJBService service = new AssessmentWSEJBService();
			AssessmentWSEJB port = service.getAssessmentWSEJBPort();

			// 	Seteamos la URL del WebService Remoto:
			String endpointURL = SecurityConstants.WS_ASSESSMENT_DC5;
			BindingProvider bp = (BindingProvider)port;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
			//assessmentResults = port.getAssessmentResults(idAssessment);
			
		}catch(Exception e){
			// Error de comunicacion con el WebServices
			e.printStackTrace();
			assessmentResults = null;
		}

		// Genero el Data para enviarle al Factory del Reporte
		AssessmentResultData result = assessmentResults(sys,assessmentResults,
				first,count,sys.getUserSessionData());        
        
	    int total = 0;
	    int pages = 1;
	    if (result != null){
	    	result.setMessages(messages);
	    	total = (int)result.getTotal();
	    	pages = total / 50;
	    	if(pages*50 < total) {
	    		pages++;
	    	}
	    }
	    
	    // Armo el reporte en el factory
		AssessmentResultReportFactory factory = new AssessmentResultReportFactory(
				messages.getText("query.assessmentresult.title2"),result ,
				reportForm,sys,messages,pages,page);
	    report = factory.createReport();
		
    	return report;
   	
	}
	

    /**
     * 
     * @param assessmentResults
     * @param first
     * @param count
     * @param userSessionData
     * @return
     * @throws Exception
     */
    private AssessmentResultData assessmentResults(SystemAccess sys, 
    		AssessmentResultList assessmentResults, Integer first, 
    		Integer count, UserSessionData userSessionData) throws Exception{
    	try{
    		
    		ListResult listResult  = null;
    		String drivers = null;
    		
    		if (assessmentResults != null && assessmentResults.getAssessmentResult() != null &&
    				assessmentResults.getAssessmentResult().size() > 0){
    			
    			Iterator<AssessmentResult> it = assessmentResults.getAssessmentResult().iterator();
    			LinkedList<AssessmentResultDataType> listResultDataType = new LinkedList<AssessmentResultDataType>();
    			
    			// Genero una lista de IDS de los drivers, para traerlos luego todos
    			// juntos de la Base de DAtos    			
    			while (it.hasNext()){
    				AssessmentResult assessmentR = it.next();
    				Integer idDriver = assessmentR.getIdDriver();
    				if (idDriver != null){
    					if (drivers == null){
    						drivers = idDriver.toString();
    					}else{
    						drivers = drivers.concat(", "+idDriver.toString());
    					}
    				}
    			}	
    			
    			System.out.println("Drivers: "+drivers);
    			ListResult driverList = null;
    			int total = 0;
    			
    			// Si tengo un listado de drivers los traigo de la BD:
    			if (drivers != null){
    				int status = DriverData.ALL;
    				driverList = sys.getDriverReportFacade().findDriverList(
    						"id",drivers, first.intValue() ,count.intValue() , status, userSessionData);
    				total = driverList.getTotal();
    				Collection driverElements = driverList.getElements();
    				Iterator<DriverMainAttributes> it2 = driverElements.iterator();
    				
    				// Para cada driver traido de la BD, asigno su resultado del assessment
    				while (it2.hasNext()){
    					DriverMainAttributes driverMain = it2.next();
    					for (AssessmentResult a : assessmentResults.getAssessmentResult()) {
							if (a.getIdDriver() != null && 
								a.getIdDriver().equals(driverMain.getId())){
								AssessmentResultDataType dt = new AssessmentResultDataType(
										driverMain, a.getPsiResult(),a.getPsiNote(),
										a.getTestResult(),a.getTestNote());
								listResultDataType.add(dt);
								break;
							}
						}
    					
    				}        			
    			}
    			if (driverList != null){
    				System.out.println("Total: "+driverList.getTotal());
    			}
				// Collections.sort(listResultDataType);
    			AssessmentResultData resultData = null;
    			
    			if (count > 0 && listResultDataType != null &&
    					listResultDataType.size() > 0){
    				// Si el reporte va a ser Paginado:
    				int min = Math.min(count.intValue(), listResultDataType.size());
    				AssessmentResultDataType[] attributes = new AssessmentResultDataType[min];
    				int j = 0;
    				for (int i = 0; i < min; i++){
    					attributes[j] = listResultDataType.get(i);
    					j++;
    				}
    				resultData  = new AssessmentResultData(attributes, total, userSessionData);    				
    			}else{
    				// Reporte Sin paginar:
    				listResult = new ListResult(listResultDataType,total);
    				resultData = new AssessmentResultData(listResult, userSessionData);
    			}
    			
    			return resultData;
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
        }
        return null;
    	
    }
    
	public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection col=new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
     	userSessionData.setUrl("/activity/schedule/AssessmentResult.jsp");
     	return mapping.findForward("layout");
    }
}
