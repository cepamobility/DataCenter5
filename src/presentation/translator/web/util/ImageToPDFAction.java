/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
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

import persistence.util.UtilBase64;
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
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.EmptyDataSource;
import communication.util.ImageBase64DataSource;
import communication.util.MailSender;
import communication.vehicle.report.CheckListDPADataSource;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.report.PictureDPADataSource;
import communication.vehicle.report.UnconformityDPADataSource;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class ImageToPDFAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		    HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
	        
	        HashMap<String,Object> parameters = new HashMap<String,Object>();
	        
	        String fileImage="tmp.png";
	    	
	    	File fileDelete=new File(AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+fileImage);
	        if(fileDelete.exists()){
	        	fileDelete.delete();
	        }
	        /*HashMap<String,ArrayList<String>> hashImage=(HashMap)request.getSession().getAttribute("imagesCheckList");
	        request.getSession().removeAttribute("imagesCheckList");
	        
	        
			ImageIO.write(UtilBase64.decodeToImage(hashImage.get("0").get(0).replaceAll("data:image/png;base64,","")), "png", new File(AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+fileImage));
			*/
	        
	        HashMap<String,ArrayList<String>> hashImage=(HashMap)request.getSession().getAttribute("imagesCheckList");
	        request.getSession().setAttribute("imagesCheckList",null);
  	        request.getSession().removeAttribute("imagesCheckList");
  	        ImageBase64DataSource sourceDetail=new ImageBase64DataSource(hashImage.get("0"));
  	        
	    	parameters.put("image", AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+fileImage);
	        parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
	        parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
	        parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
	        
 
	      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/ImageToPDF.jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/ImageToPDF.jasper");
	        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/ImageToPDF.jasper"));

	        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
	        response.setHeader("Content-Type", "application/pdf");
	   	 	response.setHeader("Content-Disposition", "inline; filename=\"Screen.pdf");
	        OutputStream out = response.getOutputStream();
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,sourceDetail);
	        
	        JRPdfExporter exporter = new JRPdfExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
	        exporter.exportReport();       
	        out.flush();
	        out.close();
		 
			return null;
		
	}
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
