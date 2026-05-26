package presentation.translator.web.activity.report;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import communication.alert.AlertConstants;

public class TableHeader extends PdfPageEventHelper {

	private String line1;
	private String line2;

	public TableHeader(String type, String date, String name) {
		line1 = type + ", " + date;
		line2 = name;
	}

	public void onStartPage(PdfWriter writer, Document document) {
		Image img;
		try {
			img = Image.getInstance(AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/header.jpg");
			img.setAbsolutePosition(0, PageSize.A4.getHeight()-img.getHeight());
	        document.add(img);

			Image logo = Image.getInstance(AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/logo.jpg");
			logo.setAbsolutePosition(PageSize.A4.getWidth()-(logo.getWidth()+20),PageSize.A4.getHeight()-(logo.getHeight()+30));
	        document.add(logo);
	        
	        int h = (int) PageSize.A4.getHeight();
	        absText(writer, line1, 20, h-40, 10);
	        absText(writer, line2, 20, h-55, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void onEndPage(PdfWriter writer, Document document) {
		Image img;
		try {
			img = Image.getInstance(AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/footer.jpg");
			img.setAbsolutePosition(0, 0);
	        document.add(img);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  
	private void absText(PdfWriter writer, String text, int x, int y, int size) {
	    try {
	    	PdfContentByte cb = writer.getDirectContent();
	    	BaseFont bf = BaseFont.createFont(AlertConstants.FONT_REPORT_NAME, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	    	cb.saveState();
	    	cb.beginText();
	    	cb.moveText(x, y);
	    	cb.setFontAndSize(bf, size);
	    	cb.showText(text);
	    	cb.endText();
	    	cb.restoreState();
	    } catch (DocumentException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
}

