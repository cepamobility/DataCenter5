package communication.datatype.tables;

	import java.io.Serializable;

	
	public class PuestoPepsicoDataType extends GeneralDTData implements Serializable {

	    private static final long serialVersionUID = 1L;

	    public PuestoPepsicoDataType(String resourceKey) {
			super(resourceKey);
		}
	    
	    public PuestoPepsicoDataType(){
	    	super("");
	    }
		
	}

