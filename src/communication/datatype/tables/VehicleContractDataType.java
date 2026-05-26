package communication.datatype.tables;

import java.io.Serializable;

public class  VehicleContractDataType extends GeneralDTData implements Serializable {

	    private static final long serialVersionUID = 1L;
	    
	    public static final String WITHOUT_CONTRACT = "vehicle.contract.withoutcontract";
	    public static final String COMODATO = "vehicle.contract.comodato";
	    public static final String DISTRATO = "vehicle.contract.distrato";
	    public static final String RESPONSABILITY = "vehicle.contract.responsability";
	    public static final String DEVOLUTION = "vehicle.contract.devolution";
	    public static final String COMODATO_OWNFLEET = "vehicle.contract.comodatoonwfleet";

	    /**
		 * @param resourceBundleId
		 */
		public  VehicleContractDataType(String resourceBundleId) {
			super(resourceBundleId);
		}
		

	}

