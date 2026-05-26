package presentation.translator.web.activity.report;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
 
import javax.imageio.ImageIO;

import presentation.translator.web.util.Util;



import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;


public class GeneratorPDF {
	
	public static final String FONT_REPORT_NAME = BaseFont.HELVETICA;
	 
	private static Font catFont = FontFactory.getFont(FONT_REPORT_NAME, 18, Font.BOLD);
	private static Font whiteFont = FontFactory.getFont(FONT_REPORT_NAME, 16, Font.NORMAL, BaseColor.WHITE);
	private static Font obsFont = FontFactory.getFont(FONT_REPORT_NAME, 12, Font.BOLD);
	private static Font subFont = FontFactory.getFont(FONT_REPORT_NAME, 10, Font.NORMAL);
	private static Font cell = FontFactory.getFont(FONT_REPORT_NAME, 7, Font.NORMAL);
	private static Font cellBold = FontFactory.getFont(FONT_REPORT_NAME, 8, Font.NORMAL);

	public static void generateReport(OutputStream out, CEPAActivityScheduleRegistryData completeResult, Text messages){
		try {
			Date expiry = completeResult.getActivity().getDateExpiry();
	    	DriverData driver = completeResult.getDriver();
	    	String name = driver.getFirstName()+" "+driver.getLastName();
	    	String ci = (driver.getCorporationId() != null) ? driver.getCorporationId() : "";
			
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			String qrStr = name;
			if(ci.length() > 0) {
				qrStr += ";"+ci;
			}
			if(expiry != null) {
				qrStr = ";"+format.format(expiry);
			}
			String qrFile = AlertConstants.TEMP_DIRECTORY_ACTIVITY_QR+completeResult.getActivityRegistryId()+".png";
			String qrFile2 = AlertConstants.TEMP_DIRECTORY_ACTIVITY_QR+completeResult.getActivityRegistryId()+"_modified.png";
			GeneratorQR.generateQR(qrStr, qrFile);
			Document document = new Document();
			 
			document.setPageSize(new Rectangle(325.03937008f,204.09448819f));
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			
			BufferedImage originalImage = ImageIO.read(new File(qrFile));
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			BufferedImage resizeImagePng = resizeImage(originalImage, type);
			ImageIO.write(resizeImagePng, "png", new File(qrFile2)); 
			
			Image logo = Image.getInstance(qrFile2);
			logo.setAbsolutePosition(240,85);
	        document.add(logo);
	        
        	absText(writer,"Certifica a: ",10,150,12,false);
        	absText(writer,driver.getFirstName(),10,130,14,false);
        	absText(writer,driver.getLastName(),10,110,14,false);
			absText(writer,"CI: "+getCedulaFormatted(ci),10,95,12,false);

	        absText(writer,"Emisión: "+format.format(completeResult.getActivity().getDate()),150,170,8,false);
			absText(writer,"Vencimiento: "+format.format(expiry),230,170,8,false);

			String activityType = Util.activityName(messages.getText(completeResult.getActivity().getDefinition().getResourceKeyName()));

	    	if(activityType.length() > 30) {
	    		String part1 = "";
	    		StringTokenizer tokenizer = new StringTokenizer(activityType);
	    		String token = tokenizer.nextToken();
	    		while(part1.length() + token.length() < 35 && tokenizer.hasMoreTokens()) {
	    			part1 = (part1.length() == 0) ? token : part1 + " " + token;
	    			if(tokenizer.hasMoreTokens())
	    				token = tokenizer.nextToken();
	    		}
	    		String part2 = activityType.replaceAll(part1, "").trim();
				absText(writer,"Por haber participado del Curso de ",10,65,10,false);
	    		absText(writer,part1,10,50,12,false);
	    		absText(writer,part2,10,35,12,false);
	    	}else {
				absText(writer,"Por haber participado del Curso de ",10,65,12,false);
	    		absText(writer,activityType,10,45,14,false);
	    	}
//QR 
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}
	
	private static String getCedulaFormatted(String cedula) {
		// TODO Auto-generated method stub
		if(cedula.length()==8){
			return cedula.charAt(0)+"."+cedula.charAt(1)+cedula.charAt(2)+cedula.charAt(3)+"."+cedula.charAt(4)+cedula.charAt(5)+cedula.charAt(6)+"-"+cedula.charAt(7);
		}else if(cedula.length()==7){
			return cedula.charAt(0)+"."+cedula.charAt(1)+cedula.charAt(2)+cedula.charAt(3)+"."+cedula.charAt(4)+cedula.charAt(5)+cedula.charAt(6);
		}
		return cedula;
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(75, 75, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 75, 75, null);
		g.dispose();
			
		return resizedImage;
	    }

	private static void absText(PdfWriter writer, String text, int x, int y, int size, boolean right) {
	    try {
	    	PdfContentByte cb = writer.getDirectContent();
	    	BaseFont bf = BaseFont.createFont(FONT_REPORT_NAME, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	    	cb.saveState();
	    	cb.beginText();
	    	cb.setFontAndSize(bf, size);
	    	if(right) {
	    		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(text, subFont), x, y, 0);
	    	}else {	
	    		cb.moveText(x, y);
	    		cb.showText(text);
	    	}
	    	cb.endText();
	    	cb.restoreState();
	    } catch (DocumentException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
}
