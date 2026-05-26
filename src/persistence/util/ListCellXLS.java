package persistence.util;

import java.util.ArrayList;

import org.apache.poi.hssf.util.HSSFColor;

public class ListCellXLS extends ArrayList<CellXLS>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean addCell(String m) {
			// TODO Auto-generated method stub
			return super.add(CellXLS.getCell(m));
	}

	public boolean addCell(HSSFColor color, String m) {
		// TODO Auto-generated method stub
		return super.add(CellXLS.getCell(color,m));
	}

}
