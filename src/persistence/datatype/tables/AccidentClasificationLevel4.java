/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.AccidentClasificationLevel1DataType;
import communication.datatype.tables.AccidentClasificationLevel2DataType;
import communication.datatype.tables.AccidentClasificationLevel3DataType;
import communication.datatype.tables.AccidentClasificationLevel4DataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccidentClasificationLevel4 extends GeneralDataType {
	
	private String parent;
	
	public AccidentClasificationLevel4() {
	}
	
	public AccidentClasificationLevel4(String resourceKey, String parent) {
		super(resourceKey);
		this.parent=parent;
	}
	
	public GeneralDTData getData() {
		return new AccidentClasificationLevel4DataType(resourceBundleId,parent);
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	

}
