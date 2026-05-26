/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

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

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.MailSender;
import communication.vehicle.report.CheckListDPADataSource;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.report.PictureDPADataSource;
import communication.vehicle.report.UnconformityDPADataSource;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class DPASendUnconformityDriverAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
         DynaActionForm controlData = (DynaActionForm)form;
        
 		String fileName = controlData.getString("unconformityFileName");
 		String unid = controlData.getString("unid");
 		String file = AlertConstants.TEMP_DIRECTORY_DPA+"out/"+fileName;
 		
 		UnconformityDPAData unconformity =(UnconformityDPAData) sys.getCorporationReportFacade().findUnconformityDPAById(sys.getUserSessionData(), Integer.valueOf(unid));
 		
 		DriverData driverData=sys.getDriverReportFacade().findDriverByPrimaryKey(unconformity.getDriverId(), sys.getUserSessionData());	
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

		
		Collection<String> files = new LinkedList();
		Collection<String> names = new LinkedList();
		String fileName2 = controlData.getString("unconformityFileName");
 		String file2 = AlertConstants.TEMP_DIRECTORY_DPA+"out/"+fileName2;

 		

        HashMap<String,Object> parameters2 = new HashMap<String,Object>();
        parameters2.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
        parameters2.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
        parameters2.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
        parameters2.put("title", messages.getText("generic.dpa.unconformity.title"));
        parameters2.put("date", Util.formatDate(Calendar.getInstance().getTime()));
        parameters2.put("back1", AlertConstants.TEMP_DIRECTORY_DPA+"images/back1.jpg");
        parameters2.put("tabla1",AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla1.jpg");
        parameters2.put("tabla2",AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla3.jpg");
        parameters2.put("tabla3",AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla2.jpg");
        parameters2.put("tabla4",AlertConstants.TEMP_DIRECTORY_DPA+"images/tabla4.jpg");
        parameters2.put("checktitle",messages.getText("web.driver.editing"));
        parameters2.put("drivertxt", "Nome");
        parameters2.put("driver", driverData.getFirstName()+"  "+driverData.getLastName());
        parameters2.put("unconformitydata", "DATA: "+Util.formatDate(unconformity.getDate()));
        parameters2.put("unconformitytype", messages.getText("generic.dpa.unconformity.type")+": "+messages.getText(unconformity.getType().getResourceBundleId()));
        parameters2.put("items", messages.getText("generic.dpa.unconformity.items"));
        parameters2.put("obstext", messages.getText("generic.dpa.uncnformity.comment"));
        parameters2.put("obsvalue", unconformity.getComment());
        parameters2.put("SUBREPORT_DIR", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/");
		UnconformityDPADataSource dataSourceUn = new UnconformityDPADataSource(unconformity.getItems(),messages);
		//JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/Unconformity2Driver.jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/Unconformity2Driver.jasper");
        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/Unconformity2Driver.jasper"));

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        FileOutputStream out2 = new FileOutputStream(new File(file2));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters2, dataSourceUn);
       
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out2);
        exporter.exportReport();       
        out2.flush();
        out2.close();
        
        files.add(file2);
		names.add(fileName2);
        
    	MailSender sender = new MailSender();
		Collection bcc = new LinkedList();
		if(SecurityConstants.isProductionServer()){
			bcc.add("controle.dpa@cepadc.com");
			UserData user=sys.getUserReportFacade().findUserByPrimaryKey(sys.getUserSessionData().getFilter().getLoginName(), sys.getUserSessionData());
			bcc.add(user.getEmail());
		}
		String body = Util.toHTMLString(controlData.getString("body"));

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

		session.setAttribute("Msg", messages.getText("generic.sendemail.reportsended"));
    	sys.getUserSessionData().setUrl("/driver/DriverView.jsp?id="+unconformity.getDriverId());
		return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
