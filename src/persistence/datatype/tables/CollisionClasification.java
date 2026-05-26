/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.CollisionClasificationDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CollisionClasification extends GeneralDataType {
	
	public CollisionClasification() {
	}
	
	public CollisionClasification(String resourceKey) {
		super(resourceKey);
	}
	
	public GeneralDTData getData() {
		return new CollisionClasificationDataType(resourceBundleId);
	}
}
