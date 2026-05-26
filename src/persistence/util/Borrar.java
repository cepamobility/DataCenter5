package persistence.util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class Borrar {

	 public static void main(String[] args) throws Exception{
         /* Create Workbook and Worksheet */
		 HSSFWorkbook wb = new HSSFWorkbook();
		 HSSFSheet sheet = wb.createSheet("Report");

		 // some more code

		 HSSFRow row = sheet.createRow(0);

		 HSSFCell cell = row.createCell((short) 0);
		 HSSFCellStyle cellStyle = wb.createCellStyle();

		 cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		 cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		 HSSFFont font = wb.createFont();
		 font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		 font.setColor(HSSFColor.WHITE.index);
		 
		 HSSFRichTextString texto = new HSSFRichTextString("valor");
	     cell.setCellValue(texto);

		 cellStyle.setFont(font);
		 cell.setCellStyle(cellStyle);
		 
         FileOutputStream out = new FileOutputStream(new File("C:/Users/Juan Rodriguez/Desktop/cell_fill_color.xls"));
         wb.write(out);
         out.close();
         
         
 }
}
