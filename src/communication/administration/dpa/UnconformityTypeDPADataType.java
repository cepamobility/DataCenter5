/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.administration.dpa;

import java.io.Serializable;

import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 * 
 */
public class UnconformityTypeDPADataType extends GeneralDTData implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	public static String TYPE_VEHICLE_CHECK="generic.unconformity.type.vehiclecheck"; 
	public static String TYPE_TELEMATIC_CONTROL="generic.unconformity.type.telematiccontrol";
	public static String TYPE_DRIVER="generic.unconformity.type.driver";
	
	
	/**
	 * @param resourceBundleId
	 */
	public UnconformityTypeDPADataType(String resourceBundleId) {
		super(resourceBundleId);
	}

}
