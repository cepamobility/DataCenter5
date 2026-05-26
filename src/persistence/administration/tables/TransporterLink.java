package persistence.administration.tables;

import persistence.administration.corporation.tables.DivOrgItemLevel1;

public class TransporterLink {

	public static final Integer FOSA_TRANSPORTER = 724645;
	public static final Integer FOSA_REGION = 330244;
	public static final Integer FOSA_COUNTRY = 31;
	
	public static final Integer WEYERHAEUSER_TRANSPORTER = 724646;
	public static final Integer WEYERHAEUSER_PRODUCT_ID = 204;
	public static final String WEYERHAEUSER_PRODUCT_NAME = "corporation.weyerhaeuser.product";

	public static final Integer WEYERHAEUSER_TRANSPORTER_ID = 205;
	public static final String WEYERHAEUSER_TRANSPORTER_NAME = "corporation.weyerhaeuser.transportist";

	public static final Integer WEYERHAEUSER_REGION = 150030;
	public static final Integer WEYERHAEUSER_COUNTRY = 182;
	public static final Integer WEYERHAEUSER_DESTINY = 220306;
	public static final Integer WEYERHAEUSER_PRODUCT = 220311;
	
	public static final Integer MDP_TRANSPORTER = 724647;
	public static final Integer MDP_REGION = 456463;
	public static final Integer MDP_COUNTRY = 31;
	public static final Integer SYNGENTA_BR_FUNCTION_NA = 497792;
	public static final Integer SYNGENTA_BR_AREA_NA = 542767;
	public static final Integer SYNGENTA_BR_REGIONAL_NA = 539736;
	public static final Integer SYNGENTA_BR_LOCATION_NA = 539735;
	
	public static final Integer FRAYLOG_INACTIVE = 565838;
	public static final Integer FRAYLOG_REGION = 563289;
	public static final Integer FRAYLOG_COUNTRY = 31;
	public static final Integer FRAYLOG_DEFAULT_DIVISION = 563306;
	
	public static final Integer SAN_NICOLAS_DEFAULT_DIVISION = 649827;
	public static final Integer SAN_NICOLAS_REGION = 649830;
	public static final Integer SAN_NICOLAS_COUNTRY = 31;
	public static final Integer SAN_NICOLAS_UPM = 660880;
	public static final Integer SAN_NICOLAS_LUMIN = 660881;
	public static final Integer SAN_NICOLAS_MDP = 660882;
	
	public static final Integer SAN_NICOLAS_COSTCENTER_ID = 649822;
	public static final String SAN_NICOLAS_COSTCENTER_NAME = "corporation.san_nicolas.companies";
	public static final Integer SAN_NICOLAS_DEAFAULT_COSTCENTER = 649828;
	
	public static final Integer SOCTMA_REGION = 666970;
	public static final Integer SOCTMA_COUNTRY = 31;
	public static final Integer SOCTMA_DEFAULT_DIVISION = 666975;

	private Integer id;
	private DivOrgItemLevel1 fosa;
	private DivOrgItemLevel1 weyerhauser;
	private DivOrgItemLevel1 mdp;
	
	public TransporterLink() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DivOrgItemLevel1 getFosa() {
		return fosa;
	}

	public void setFosa(DivOrgItemLevel1 fosa) {
		this.fosa = fosa;
	}

	public DivOrgItemLevel1 getWeyerhauser() {
		return weyerhauser;
	}

	public void setWeyerhauser(DivOrgItemLevel1 weyerhauser) {
		this.weyerhauser = weyerhauser;
	}

	public DivOrgItemLevel1 getMdp() {
		return mdp;
	}

	public void setMdp(DivOrgItemLevel1 mdp) {
		this.mdp = mdp;
	}

	public Integer[] getIds() {
		Integer[] ids = new Integer[3];
		if(fosa != null) {
			ids[0] = fosa.getId();
		}
		if(weyerhauser != null) {
			ids[1] = weyerhauser.getId();
		}
		if(mdp != null) {
			ids[2] = mdp.getId();
		}
		return ids;
	}

}
