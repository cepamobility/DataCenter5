package presentation.translator.web.activity.report;

import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import business.SystemAccess;
import business.SystemAccessRemote;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class CertificateReport {

	private Text messages;
	private SystemAccessRemote sys;
	private DriverData driver;
	private String activity;
	private String dateStr;
	
	private static Font certFont = FontFactory.getFont(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fonts/Montserrat-Regular.ttf", 14, Font.NORMAL);
	
	private static Font nameFont = FontFactory.getFont(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fonts/Montserrat-Bold.ttf", 25, Font.BOLD);
	private static Font driveridFont = FontFactory.getFont(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fonts/Montserrat-Bold.ttf", 14, Font.BOLD);
	private static Font activityTypeFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 30, Font.NORMAL);
	private static Font dateFont = FontFactory.getFont(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fonts/Montserrat-Regular.ttf", 12, Font.NORMAL);
	private static Font chargeFont = FontFactory.getFont(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"fonts/Montserrat-SemiBold.ttf", 12, Font.BOLD);

	
	public CertificateReport(SystemAccessRemote sys, DriverData driver, String activity, String dateStr) {
		this.sys = sys;
		this.messages = sys.getText();
		this.driver = driver;
		this.activity = activity;
		this.dateStr = dateStr;
	}

	public void executeReport(OutputStream out) {
		try {
			Document document = new Document();
			document.setPageSize(new Rectangle(842,595));
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
  
			
			addPage1(writer, document);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}

	private void addPage1(PdfWriter writer, Document document) throws Exception {
		
		Image back = Image.getInstance(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"hoja1.png");
		back.setAbsolutePosition(0,0);
        document.add(back);

		Paragraph preface = new Paragraph();

		for(int i = 0; i < 6; i++)
			preface.add(new Paragraph(" "));

		PdfPTable outerTable = new PdfPTable(1);
        outerTable.setWidthPercentage(100.0f);

        PdfPCell cert = new PdfPCell(new Paragraph(messages.getText("activity.certificate.certificateto"), certFont));
        cert.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cert.setHorizontalAlignment(Element.ALIGN_CENTER);
        cert.setBorder(Rectangle.NO_BORDER);
        outerTable.addCell(cert);

        PdfPCell name = new PdfPCell(new Paragraph(driver.getFullName(), nameFont));
        name.setVerticalAlignment(Element.ALIGN_BOTTOM);
        name.setHorizontalAlignment(Element.ALIGN_CENTER);
        name.setBorder(Rectangle.NO_BORDER);
        outerTable.addCell(name);


		String txt = ""; 
		PdfPCell  driverId = new PdfPCell(new Paragraph(txt, driveridFont));
		driverId.setVerticalAlignment(Element.ALIGN_BOTTOM);
		driverId.setHorizontalAlignment(Element.ALIGN_CENTER);
		driverId.setBorder(Rectangle.NO_BORDER);
        outerTable.addCell(driverId);

		PdfPCell space = new PdfPCell(new Paragraph(" ", certFont));
		space.setBorder(Rectangle.NO_BORDER);
		space.setFixedHeight(40);
        outerTable.addCell(space);

        String s = messages.getText("activity.certificate.toparticipate");
        PdfPCell participate = new PdfPCell(new Paragraph(s, certFont));
        participate.setVerticalAlignment(Element.ALIGN_BOTTOM);
        participate.setHorizontalAlignment(Element.ALIGN_CENTER);
        participate.setBorder(Rectangle.NO_BORDER);
        outerTable.addCell(participate);

		PdfPCell  activityType = new PdfPCell(new Paragraph(activity, activityTypeFont));
		activityType.setVerticalAlignment(Element.ALIGN_TOP);
		activityType.setHorizontalAlignment(Element.ALIGN_CENTER);
		activityType.setBorder(Rectangle.NO_BORDER);
		activityType.setFixedHeight(40);
        outerTable.addCell(activityType);
		
		PdfPCell hours = new PdfPCell(new Paragraph(" ", certFont));
		hours.setBorder(Rectangle.NO_BORDER);
		hours.setFixedHeight(15);
        outerTable.addCell(hours);

        preface.add(outerTable);

		String line = dateStr;
		line += " - " + messages.getText("activity.certificate.validity")+": "+messages.getText("activity.certificate.expiry3");
		
		PdfPCell date = new PdfPCell(new Paragraph(line, dateFont));
        date.setVerticalAlignment(Element.ALIGN_BOTTOM);
        date.setHorizontalAlignment(Element.ALIGN_CENTER);
        date.setBorder(Rectangle.NO_BORDER);
        outerTable.addCell(date);
        int[][] xSignIzq = {{180,180,180}, {190,130,195}, {180,188,180}, {190,150,180}, {190,150,180}};
        int[][] xSignDer = {{505,505,505}, {495,440,500}, {485,493,505}, {505,465,495}, {505,465,495}};
		
/*		if(variables.getFirmaIzq() > 0) {
			Image firmaIzq = Image.getInstance(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"firma"+variables.getFirmaIzq()+".png");
			firmaIzq.setAbsolutePosition(150,60);
	        document.add(firmaIzq);
	        absText(writer, messages.getText("certificate.firmaname"+variables.getFirmaIzq()), xSignIzq[variables.getFirmaIzq()-1][0], 90, dateFont, 12);
		    absText(writer, messages.getText("certificate.firmacharge"+variables.getFirmaIzq()), xSignIzq[variables.getFirmaIzq()-1][1], 75, chargeFont, 12);
		    absText(writer, messages.getText("certificate.firmadept"+variables.getFirmaIzq()), xSignIzq[variables.getFirmaIzq()-1][2], 60, chargeFont, 12);
		}
		
		
		if(variables.getFirmaDer() > 0) {
			Image firmaIzq = Image.getInstance(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+"firma"+variables.getFirmaDer()+".png");
			firmaIzq.setAbsolutePosition(450,60);
	        document.add(firmaIzq);
	
		      absText(writer, messages.getText("certificate.firmaname"+variables.getFirmaDer()), xSignDer[variables.getFirmaDer()-1][0], 90, dateFont, 12);
		      absText(writer, messages.getText("certificate.firmacharge"+variables.getFirmaDer()), xSignDer[variables.getFirmaDer()-1][1], 75, chargeFont, 12);
		      absText(writer, messages.getText("certificate.firmadept"+variables.getFirmaDer()), xSignDer[variables.getFirmaDer()-1][2], 60, chargeFont, 12);
		}*/

		document.add(preface);
	}

/*
	private void addPage2(PdfWriter writer, Document document) throws Exception {
		
		Image back = Image.getInstance(AlertConstants.TEMP_DIRECTORY_CERTIFICADOS+variables.getPage2name(sys.getUserSessionData().getLenguage()));
		back.setAbsolutePosition(0,0);
        document.add(back);
	}
*/	
	private void absText(PdfWriter writer, String text, int x, int y, Font fontName, int size) {
	    try {
	    	PdfContentByte cb = writer.getDirectContent();
	    	cb.saveState();
	    	cb.beginText();
	    	if(fontName != null) {
	    		cb.setFontAndSize(fontName.getBaseFont(), size);
	    	}else {
		    	BaseFont bf = BaseFont.createFont(AlertConstants.FONT_REPORT_NAME, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	    		cb.setFontAndSize(bf, size);
	    	}
    		cb.moveText(x, y);
    		cb.showText(text);
	    	cb.endText();
	    	cb.restoreState();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	private void absText(PdfWriter writer, String text, int y, int size) {
	    try {
	    	PdfContentByte cb = writer.getDirectContent();
	    	BaseFont bf = BaseFont.createFont(AlertConstants.FONT_REPORT_NAME, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	    	cb.saveState();
	    	cb.beginText();
	    	cb.setFontAndSize(bf, size);
	    	ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(text, certFont), 100, y, 0);
	    	cb.endText();
	    	cb.restoreState();
	    } catch (DocumentException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
}
