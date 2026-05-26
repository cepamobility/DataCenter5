/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.administration.corporation.tables.Corporation;

import communication.datatype.tables.CrponPrimaryClasificationDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CrponPrimaryClasification extends GeneralDataType {
	
	private Corporation corporation;
	private Collection crponASecondaryClasificationValues;
	
	/**
	 * 
	 */
	public CrponPrimaryClasification() {

		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public CrponPrimaryClasification(String resourceKey) {
		super(resourceKey);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		// TODO Auto-generated method stub
		Collection valuesDataType=new LinkedList();
		Iterator iter=crponASecondaryClasificationValues.iterator();
		while(iter.hasNext()){
			CrponSecondaryClasification sc=(CrponSecondaryClasification)iter.next();
			valuesDataType.add(sc.getData());
		}
		return new CrponPrimaryClasificationDataType(resourceBundleId,valuesDataType);
	}

	public Collection getCrponASecondaryClasificationValues() {
		return crponASecondaryClasificationValues;
	}
	
	public void setCrponASecondaryClasificationValues(
			Collection crponASecondaryClasificationValues) {
		this.crponASecondaryClasificationValues = crponASecondaryClasificationValues;
	}
	public Corporation getCorporation() {
		return corporation;
	}
	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}
}
