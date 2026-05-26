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
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccidentClasificationLevel3 extends GeneralDataType {
	
	private String parent;
	
	public AccidentClasificationLevel3() {
	}
	
	public AccidentClasificationLevel3(String resourceKey, String parent) {
		super(resourceKey);
		this.parent=parent;
	}
	
	public GeneralDTData getData() {
		return new AccidentClasificationLevel3DataType(resourceBundleId,parent);
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	

}
