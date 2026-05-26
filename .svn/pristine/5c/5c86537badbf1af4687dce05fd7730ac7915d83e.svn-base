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

import communication.datatype.tables.DamageSchemaDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DamageSchema extends GeneralDataType {
	private Collection damageSchemaValues; 
	
	/**
	 * 
	 */
	public DamageSchema() {

		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 */
	public DamageSchema(String resourceKey, Collection damageSchemaValues) {
		super(resourceKey);
		this.damageSchemaValues=new LinkedList(damageSchemaValues);
		
	}
	
	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		// TODO Auto-generated method stub
		Collection values=new LinkedList();
		Iterator iter =damageSchemaValues.iterator();
		while (iter.hasNext()) {
			DamageSchemaValue dsv = (DamageSchemaValue) iter.next();
			values.add(dsv.getData());
		}
		return new DamageSchemaDataType(resourceBundleId,values);
	}

	public Collection getDamageSchemaValues() {
		return damageSchemaValues;
	}
	public void setDamageSchemaValues(Collection damageSchemaValues) {
		this.damageSchemaValues = damageSchemaValues;
	}
}
