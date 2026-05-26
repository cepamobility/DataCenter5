/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.datatype.tables;

import java.io.Serializable;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityStateDataType extends GeneralDTData implements Serializable  {

    private static final long serialVersionUID = 1L;
    
    public static final String DICTADA = "datatype.scheduledactivitystate.dictada";
    public static final String PREVISTA = "datatype.scheduledactivitystate.prevista";
    public static final String ENSUPERVISION = "datatype.scheduledactivitystate.ensupervision";


    public ActivityStateDataType(String resourceKey) {
		super(resourceKey);
	}
	
}
