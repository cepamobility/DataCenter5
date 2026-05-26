/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

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
import communication.alert.AlertConstants;
import communication.language.Text;
import communication.vehicle.report.CheckListDPADataSource;
import communication.vehicle.report.CheckListImageData;
import communication.vehicle.report.PictureDPADataSource;
import communication.vehicle.tables.VehicleCheckData;

/**
 * @author fcarriquiry
 */
public class DPADownloadCheckListAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
      if(sys.isCorporation(CorporationData.SYNGENTA)){
    	  return downloadSyngenta(mapping, form, request, response);
      }else if(sys.isCorporation(CorporationData.DPA)){
    	  return downloadDPA(mapping, form, request, response);
      }
      
      return null;
	}
	
	public ActionForward downloadSyngenta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        DynaActionForm controlData = (DynaActionForm)form;
        
 		VehicleCheckData data = sys.getVehicleReportFacade().findVehicleCheck(new Integer(controlData.getString("check")), sys.getUserSessionData());
		
		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(new Integer(data.getName()),sys.getUserSessionData());
		Boolean resume=controlData.getString("checkListResume")!=null && controlData.getString("checkListResume").equals("1");
		
        HashMap<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("header", AlertConstants.getTEMP(sys.getCorporationId())+"images/header.jpg");
        parameters.put("footer", AlertConstants.getTEMP(sys.getCorporationId())+"images/footer.jpg");
        parameters.put("logo_cepa", AlertConstants.getTEMP(sys.getCorporationId())+"images/logo_cepa.png");
        parameters.put("title",  messages.getText("generic.activity.form.truckchecklist").toUpperCase());
        parameters.put("date", Util.formatDate(Calendar.getInstance().getTime()));
        parameters.put("header1", AlertConstants.getTEMP(sys.getCorporationId())+"images/check_header1.jpg");
        parameters.put("header2", AlertConstants.getTEMP(sys.getCorporationId())+"images/check_header2.jpg");
        parameters.put("header3", AlertConstants.getTEMP(sys.getCorporationId())+"images/check_header3.jpg");
        parameters.put("header4", AlertConstants.getTEMP(sys.getCorporationId())+"images/check_header4.jpg");
        parameters.put("checkdata", "Fecha: "+Util.formatDate(data.getDate()));
                
        parameters.put("data",  AlertConstants.getTEMP(sys.getCorporationId())+"images/datosCheckListT1.jpg");
        parameters.put("aprouved", "A");
        parameters.put("reprouved", "R");
        parameters.put("plates", "PLACAS");
        parameters.put("obs", messages.getText("generic.messages.observations"));
        
        String result = messages.getText("generic.activity.result") +": ";
        result += (data.getResult().intValue() == 1) ? messages.getText("generic.activity.note.approved") : messages.getText("generic.activity.note.notapproved"); 
        parameters.put("pictures", messages.getText("generic.checklist.pictures"));
        parameters.put("finaldata", messages.getText("generic.checklist.finaldata"));
        parameters.put("result", result);
        parameters.put("inspected", messages.getText("generic.checklist.inspector")+": "+data.getInspectedby());
        parameters.put("obsText", messages.getText("generic.messages.observations"));

        parameters.put("obsValue", (data.getObs() == null) ? "--------" : data.getObs());
        String cargo= (data.getSerie() == null) ? "--------" : data.getSerie();
        parameters.put("city", messages.getText("generic.driver.charge")+": "+cargo);
        parameters.put("plate", messages.getText("generic.event.eventdata.plate")+":"+data.getVehicle().getMatricula() );
        
        
        Iterator auxPointsIter=checkList.getSortPoints();
    	ArrayList<PointData> auxPoints=new ArrayList<PointData>();
    	PointData pointHeader=null;
    	HashMap<Integer,PointData> pointHedaersOK=new HashMap<Integer, PointData>();
    	int aux=1;
    	int indexCal=1;
    	int indexDes=1;
    	while(auxPointsIter.hasNext()) {
			PointData point = (PointData)auxPointsIter.next();
			Iterator items = point.getSortItems();
			while(items.hasNext()) {
				ItemData item = (ItemData)items.next();
				if(item.getType()==2){
					String val=data.getValueFromItem(item.getId());
					if(val==null || val.trim().length()==0){
						val="-----";
					}
					
					if(aux%2==0){
						parameters.put("des"+(indexDes++),  messages.getText(item.getText())+":"+val);
					}else{
						parameters.put("cal"+(indexCal++),  messages.getText(item.getText())+":"+val);
					}
					aux++;
				}
			}
		}
    	parameters.put("imgauto",AlertConstants.getTEMP(sys.getCorporationId())+"images/autotecho.jpg");
    	parameters.put("cover",messages.getText("syngenta.checklist.cover"));
    	
        Collection<CheckListImageData> images = data.getPointImageSet();
        String[] imgSources = new String[images.size()];
        String[] imgTexts = new String[images.size()];
        String[] imgPointTexts = new String[images.size()];
	    if(images.size() > 0) {
	        Iterator<CheckListImageData> imgIt = images.iterator();
	        int index = 0;
	        while(imgIt.hasNext()) {
	        	CheckListImageData imageData = imgIt.next();
	        	imgSources[index] = imageData.getFileName();
	        	PointData point = checkList.getPoint(imageData.getPointId());
        		imgTexts[index] = point.getSortItems().next().getText();
        		imgPointTexts[index]=data.getValueFromCommentPoint(point.getId());
	        	index++;
	        }
	        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
	        sub.setCorporationId(sys.getCorporationId());
	      //JasperCompileManager.compileReportToFile(AlertConstants.getTEMP(sys.getCorporationId())+"jasper/PictureSubreport.jrxml", AlertConstants.getTEMP(sys.getCorporationId())+"jasper/PictureSubreport.jasper");
	        FileInputStream input = new FileInputStream(new File(AlertConstants.getTEMP(sys.getCorporationId())+"jasper/PictureSubreport.jasper"));
	        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
	        parameters.put("sub", sub);
	        parameters.put("subreport", subReport);
        }else {
	        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
	        sub.setCorporationId(sys.getCorporationId());
	      //JasperCompileManager.compileReportToFile(AlertConstants.getTEMP(sys.getCorporationId())+"jasper/PictureSubreport2.jrxml", AlertConstants.getTEMP(sys.getCorporationId())+"jasper/PictureSubreport2.jasper");
	        FileInputStream input = new FileInputStream(new File(AlertConstants.getTEMP(sys.getCorporationId())+"jasper/PictureSubreport2.jasper"));
	        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
	        parameters.put("sub", sub);
	        parameters.put("subreport", subReport);
        }
	    
		Collection<String> files = new LinkedList();
		Collection<String> names = new LinkedList();

	    CheckListDPADataSource dataSource = new CheckListDPADataSource(resume, data, checkList, sys.getText());
	    dataSource.setCorporationId(sys.getCorporationId());
	    
	  //JasperCompileManager.compileReportToFile(AlertConstants.getTEMP(sys.getCorporationId())+"jasper/CheckListSyngenta.jrxml", AlertConstants.getTEMP(sys.getCorporationId())+"jasper/CheckListSyngenta.jasper");
        FileInputStream input2 = new FileInputStream(new File(AlertConstants.getTEMP(sys.getCorporationId())+"jasper/CheckListSyngenta.jasper"));

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        response.setHeader("Content-Type", "application/pdf");
   	 	response.setHeader("Content-Disposition", "inline; filename=\"CheckList_"+data.getVehicle().getMatricula().replace(" ", "_")+".pdf");
        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();       
        out.flush();
        out.close();
	 
		return null;
	}
	
	public ActionForward downloadDPA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
      
        
        DynaActionForm controlData = (DynaActionForm)form;
        
 		VehicleCheckData data = sys.getVehicleReportFacade().findVehicleCheck(new Integer(controlData.getString("check")), sys.getUserSessionData());
		boolean t1 = data.getName().equals("75");
		
		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(new Integer(data.getName()),sys.getUserSessionData());
		Boolean resume=controlData.getString("checkListResume")!=null && controlData.getString("checkListResume").equals("1");
		
        HashMap<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("header", AlertConstants.TEMP_DIRECTORY_DPA+"images/header.jpg");
        parameters.put("footer", AlertConstants.TEMP_DIRECTORY_DPA+"images/footer.jpg");
        parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_DPA+"images/logo_cepa.png");
        parameters.put("title", (resume?messages.getText("checklist.sendreport.resume").toUpperCase()+" ":"")+messages.getText("checklist.dpa.title"+data.getName()));
        parameters.put("date", Util.formatDate(Calendar.getInstance().getTime()));
        parameters.put("header1", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header1.jpg");
        parameters.put("header2", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header2.jpg");
        parameters.put("header3", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header3.jpg");
        parameters.put("header4", AlertConstants.TEMP_DIRECTORY_DPA+"images/check_header4.jpg");
        parameters.put("checkdata", "DATA: "+Util.formatDate(data.getDate()));
        Iterator<PointData> it = checkList.getSortPoints();
        String[][] values = null;
        if(t1) {
            parameters.put("data", AlertConstants.TEMP_DIRECTORY_DPA+"images/datosCheckListT1.jpg");
            parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT1.jpg");
	        parameters.put("romeo", "ROMEU");
	        parameters.put("juliet", "JULIETA");
        	values = new String[][]{{"driver","link","transporter"},{"valueRomeo1","valueJuliet1"},{"valueRomeo2","valueJuliet2"}};
        }else {
            parameters.put("data", AlertConstants.TEMP_DIRECTORY_DPA+"images/datosCheckListT2.jpg");
            parameters.put("table", AlertConstants.TEMP_DIRECTORY_DPA+"images/tablaCheckListT2.jpg");
	        parameters.put("romeo", "ROMEU");
	        parameters.put("horse", "CAVALO");
	        parameters.put("first", messages.getText("generic.dpa.checklist.1carreta"));
	        parameters.put("second", messages.getText("generic.dpa.checklist.2carreta"));
        	values = new String[][]{{"driver","link","transporter"},{"valueHorse","valueFirst1","valueSecond1"},{"valueRomeo","valueFirst2","valueSecond2"}};
        }
        for(int j = 0; j < values.length; j++) {
	        Iterator<ItemData> items = it.next().getSortItems();
	        for(int i = 0; i < values[j].length; i++) {
		        ItemData item = items.next();
		        String text = (j == 0) ? messages.getText(item.getText()) + ": " + data.getValueFromItem(item) : data.getValueFromItem(item);
		        parameters.put(values[j][i], text);
	        }
        }
        parameters.put("aprouved", "A");
        parameters.put("reprouved", "R");
        parameters.put("tanks", messages.getText("generic.dpa.checklist.nrotank"));
        parameters.put("plates", "PLACAS");
        parameters.put("obs", messages.getText("generic.messages.observations"));
        
        String result = messages.getText("generic.activity.result") +": ";
        result += (data.getResult().intValue() == 1) ? messages.getText("generic.activity.note.approved") : messages.getText("generic.activity.note.notapproved"); 
        parameters.put("pictures", messages.getText("generic.checklist.pictures"));
        parameters.put("finaldata", messages.getText("generic.checklist.finaldata"));
        parameters.put("result", result);
        parameters.put("inspected", messages.getText("generic.checklist.inspector")+": "+data.getInspectedby());
        parameters.put("obsText", messages.getText("generic.messages.observations"));

        parameters.put("obsValue", (data.getObs() == null) ? "--------" : data.getObs());
        String city= (data.getSerie() == null) ? "--------" : data.getSerie();
        parameters.put("city", messages.getText("checklist.dpa.city")+": "+city);

        Collection<CheckListImageData> images = data.getPointImageSet();
        String[] imgSources = new String[images.size()];
        String[] imgTexts = new String[images.size()];
        String[] imgPointTexts = new String[images.size()];
	    if(images.size() > 0) {
	        Iterator<CheckListImageData> imgIt = images.iterator();
	        int index = 0;
	        while(imgIt.hasNext()) {
	        	CheckListImageData imageData = imgIt.next();
	        	imgSources[index] = imageData.getFileName();
	        	PointData point = checkList.getPoint(imageData.getPointId());
	        	if(point.getPointOrder().intValue() == 1) {
	        		imgTexts[index] = "checklistdap.images.panoramic";
	        		imgPointTexts[index]="";
	        	}else {
	        		imgTexts[index] = point.getSortItems().next().getText();
	        		imgPointTexts[index]=data.getValueFromCommentPoint(point.getId());
	        	}
	        	index++;
	        }
	        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
	      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/PictureSubreport.jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/PictureSubreport.jasper");
	        FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/PictureSubreport.jasper"));
	        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
	        parameters.put("sub", sub);
	        parameters.put("subreport", subReport);
        }else {
	        PictureDPADataSource sub = new PictureDPADataSource(imgSources,imgTexts,imgPointTexts,messages);
	      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/PictureSubreport2.jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/PictureSubreport2.jasper");
	        FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/PictureSubreport2.jasper"));
	        JasperReport subReport = (JasperReport)JRLoader.loadObject(input);
	        parameters.put("sub", sub);
	        parameters.put("subreport", subReport);
        }
	    
		Collection<String> files = new LinkedList();
		Collection<String> names = new LinkedList();

	    CheckListDPADataSource dataSource = new CheckListDPADataSource(resume, data, checkList, sys.getText());
        
        String type = (t1) ? "T1" : "T2"; 
      //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckListDPA"+type+".jrxml", AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckListDPA"+type+".jasper");
        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_DPA+"jasper/CheckListDPA"+type+".jasper"));

        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
        response.setHeader("Content-Type", "application/pdf");
   	 	response.setHeader("Content-Disposition", "inline; filename=\"CheckList_"+data.getVehicle().getMatricula().replace(" ", "_")+".pdf");
        OutputStream out = response.getOutputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
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
