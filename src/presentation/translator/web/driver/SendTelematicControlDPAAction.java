/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import persistence.administration.user.tables.User;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.tables.UserData;
import communication.alert.AlertConstants;
import communication.driver.report.TelematicControlDPADataSource;
import communication.driver.tables.TelematicControlData;
import communication.driver.tables.TelematicControlSendMailData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.MailSender;

/**
 * @author jrodriguez
 */
public class SendTelematicControlDPAAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm controlData = (DynaActionForm)form;
        
 		String fileName = controlData.getString("fileName");
 		String file = AlertConstants.TEMP_DIRECTORY_DPA+"out/"+fileName;

 	
		StringTokenizer tokenizer = new StringTokenizer(controlData.getString("address"),",");
		Collection addressTo = new LinkedList();
		while(tokenizer.hasMoreTokens()) {
			String address = tokenizer.nextToken().trim();
			if(!Util.checkEmail(address)) {
		    	session.setAttribute("Msg", messages.getText("generic.sendemail.wrongaddress")+": "+address);
		    	sys.getUserSessionData().setUrl("/driver/SendTelematicControl.jsp?control="+controlData.getString("control"));
				return mapping.findForward("success");
			}else {
				addressTo.add(address);
			}
		}

		TelematicControlData data = sys.getDriverReportFacade().findTelematicControl(new Integer(controlData.getString("control")), sys.getUserSessionData());
        
	
 		
 		
		HashMap<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
        parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
        parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
        parameters.put("title", "Relatório de Notificação de Não Conformidade");
        if(data.getDriver()!=null){
        	parameters.put("transporter", "Transportadora: "+data.getDriver().getDivOrgName(1, 2));
        }else{
        	DivOrgData divOrgData=((DivOrgData)data.getVehicle().getDivOrgsIterator().next());
        	ItemData itm=(ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
        	itm=(ItemData)itm.getSubItems().getItemIterator().next();
        	itm=(ItemData)itm.getSubItems().getItemIterator().next();
        	parameters.put("transporter", "Transportadora: "+  itm.getName());
        }
        
        parameters.put("notification", "Notificação: "+messages.getText(data.getStringType()));
        parameters.put("subtitle", messages.getText(data.getStringType()));
        parameters.put("footmessage", "Prezadas Transportadoras, se necessário confereir as infrações acima, acesso o sistema de monitoramento Mix Telematics com seus devidos usuários.");
        parameters.put("date", Util.formatDate(Calendar.getInstance().getTime()));
        parameters.put("back1", AlertConstants.TEMP_DIRECTORY_DPA+"images/back1.jpg");
        parameters.put("back2", AlertConstants.TEMP_DIRECTORY_DPA+"images/back2.jpg");
        parameters.put("subtitleimage","Imagen");
        if(data.getFileName()!=null){
        	parameters.put("image",AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+data.getFileName());
        }
        TelematicControlDPADataSource dataSource = new TelematicControlDPADataSource(data, sys.getText());
        
      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/TelematicControlDPA.jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/TelematicControlDPA.jasper");
	       
        FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/TelematicControlDPA.jasper"));
        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input);
        FileOutputStream out = new FileOutputStream(new File(file));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();       
        out.flush();
        out.close();

    	MailSender sender = new MailSender();
		Collection bcc = new LinkedList();
		if(SecurityConstants.isProductionServer()){
			bcc.add("controle.dpa@cepadc.com");
			UserData user=sys.getUserReportFacade().findUserByPrimaryKey(sys.getUserSessionData().getFilter().getLoginName(), sys.getUserSessionData());
			bcc.add(user.getEmail());;
		}
		String body = Util.toHTMLString(controlData.getString("body"));

		Collection<String> files = new LinkedList();
		Collection<String> names = new LinkedList();
		files.add(file);
		names.add(fileName);
		
		boolean sended = false;
		int count = 0;
		while(!sended && count < 10) {
			try {
				sended = sender.sendImageMandrill(addressTo,
						"CEPA DataCenter", 
						AlertConstants.EMAIL_SUPPORT, 
						controlData.getString("subject"), body, files,
						names, 
						"", "", bcc,null);
			}catch (Exception e) {
				e.printStackTrace();
			}
			count++;
		}
		
		if(sended){
			sys.getDriverABMFacade().sendRegistryTelematicControl(null, data, controlData.getString("body"), controlData.getString("address"), controlData.getString("subject"), sys.getUserSessionData());
		}

		session.setAttribute("Msg", messages.getText("generic.sendemail.reportsended"));
		if(data.getDriver()!=null)
			sys.getUserSessionData().setUrl("/driver/telematicControlDPA.jsp?id="+data.getId());
		else
			sys.getUserSessionData().setUrl("/vehicle/telematicControlVehicleDPA.jsp?id="+data.getId());
		return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
