package communication.vehicle.tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.EnsuranceDataType;
import communication.datatype.tables.TypeCenterCostDataType;
import communication.datatype.tables.TypeCoverDataType;
import communication.datatype.tables.TypeDistanceUnitDataType;
import communication.datatype.tables.TypeEngineDataType;
import communication.datatype.tables.TypeFuelDataType;
import communication.datatype.tables.TypeTradeDataType;
import communication.datatype.tables.TypeUseDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.datatype.tables.TypeVolumeUnitDataType;
import communication.driver.tables.DriverData;
import communication.util.LogActions;
import communication.util.Validate;

public class VehicleActiveGroupData implements Serializable  {

	private String divOrgItem1;
	private Integer count;
	
	public String getDivOrgItem1() {
		return divOrgItem1;
	}
	public void setDivOrgItem1(String divOrgItem1) {
		this.divOrgItem1 = divOrgItem1;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
