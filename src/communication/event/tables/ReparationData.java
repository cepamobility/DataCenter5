
package communication.event.tables;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;

public class ReparationData implements Comparable<ReparationData> {
	
    public static final int PENDING = 0;
	public static final int ON_PROCCESS = 1;
	public static final int FINALIZED = 2;
	public static final int NEW_REPARATION_ID = 130000;

	public static final int MANTENIMIENTO = 0;
	public static final int TALLER = 1;
	public static final int GOMERIA = 2;
	public static final int METALURGIA = 3;
	public static final int SEGURIDAD = 4;
	public static final int SEGURIDAD_2 = 5;
	public static final int SOCTMA = 6;
	public static final int SOCTMA2 = 7;
	public static final int MARTINMUNOZ = 8;
	public static final int MANTENIMIENTO_CANELONES = 9;
	public static final int ALVARO_RAMIREZ = 10;
	public static final int LUCIO_MARTINEZ = 11;
	
	public static final int FRAYLOG_FB = 12;
	public static final int FRAYLOG_CN = 13;
	public static final int SIG = 14;
	public static final int RESPAGNOLO = 15;
	public static final int GOMERIA_2 = 16;
	public static final int FRAYLOG_DZ = 17;
	public static final int JUAN_TERRA = 18;
	public static final int GOMERIA_LOSTOROS = 19;
	public static final int C_PEREZ = 20;

	public static final String[] LOGIN_REPARATION_USERS = {"5cbef95b59c67878c7f81c33ca9ee8e2","d0cad81a433d459c681f1602c4d29f87","f1489d47e8901d4e5e40e2327a58db8c","cf4867ec8d7caac4cd54fda0df30af3c","a307623bd29439b04316beacdd80ed60","a2b70dceb1230a5d461a5ec3682752f3","85a22350cd16b31ff5f7ba8de995f719","85a22350cd16b31ff5f7ba8de995f719","9681c8b0bb51c08eb36c13723e43ea1e","5cbef95b59c67878c7f81c33ca9ee8e2","577b174426f588b2af8b8ab878daa48e", "39a3ce7f22da0f55aadf93b089ccc537", "cb95655f9d633e9d0c584e07c50041b6", "298f4743f9435167200ef1b149274984", "010e01f4331022008179f5745b4b1c5c", "67ceaab1ba9e934becb9b1600c46c21f", "63833136014693ae58979247823f6a3e", "e6440516b2ea649e70b4a0cbdd573e7f", "9a29881a083f795b531a410d4205b967", "395dd93cd2c600afef1e5ab06be9f0da", "8a0e0e13ec6d4f2aa0cfc9cffce24327"};
	public static final String[] LOGIN_REPARATION_EMAILS = {"mantenimiento@fraylog.com.uy","taller@fraylog.com.uy","gomeria@fraylog.com.uy","metalurgica@fraylog.com.uy","mberrondo@fraylog.com.uy","sgonzalez@fraylog.com.uy","mantenimiento@mendy.com.uy","mirigoyen@mendy.com.uy","mmunoz@fraylog.com.uy","mantenimientocanelones@mail.fraylog.com.uy","alvaro.ramirez@ciruy.com","lucio.martinez@ciruy.com", "fraylog.fb@ciruy.com", "fraylog.cn@ciruy.com", "sig@fraylog.com.uy", "respagnolo@fraylog.com.uy", "gomeria@mail.fraylog.com.uy", "fraylog.dz@ciruy.com", "jterra@fraylog.com.uy", "gomerialostoros@gmail.com", "cperez@fraylog.com.uy"};
	public static final String[] LOGIN_REPARATION_NAMES = {"Usuario Mantenimiento","Usuario Taller","Usuario Gomería","Usuario Metalúrgica","Magdalena Berrondo","Sebastian Gonzalez","Usuario SOCTMA","Martin Irigoyen","Martin Muñoz","Mantenimiento Canelones","Alvaro Ramirez","Lucio Martinez", "Usuario Fraylog FB", "Usuario Fraylog CN", "Usuario SIG", "Usuario Respagnolo", "Usuario Gomeria 2", "Usuario Fraylog DZ", "Juan Terra", "Gomeria Los Toros", "Carlos Perez"};

	protected Integer id;

    protected Date date;

    protected DriverData driver;
    protected VehicleData vehicle;

    protected String observation; 
    
    protected Integer status;
    protected Integer kms;

    protected Collection<ReparationItemData> items;
    protected HashMap<Integer, String> comments;
    
    private Collection<String> strItems;
	private Date inserted; 

	public ReparationData() {
		items = new LinkedList<ReparationItemData>();
		comments = new HashMap<Integer, String>();
		strItems = new LinkedList<String>();
	}

	public ReparationData(Integer id) {
		this.id = id;
		items = new LinkedList<ReparationItemData>();
		comments = new HashMap<Integer, String>(); 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public DriverData getDriver() {
		return driver;
	}

	public void setDriver(DriverData driver) {
		this.driver = driver;
	}

	public VehicleData getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleData vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int compareTo(ReparationData o) {
		if(date.equals(o.date))
			return driver.compareTo(o.driver);
		else
			return date.compareTo(o.date);
	}

    public static int getSubMaintenance(int i) {
    	switch(i) {
	    	case 1:
	    		return 11;
	    	case 2: case 3:
	    		return 3;
	    	case 4:
	    		return 8;
	    	case 5:
	    		return 2;
	    	case 6:
	    		return 5;
	    	case 7: case 9:
	    		return 9;
	    	case 8:
	    		return 4;
    	}
    	return 0;
    }

    public static int getSubMaintenance(int i, int j) {
    	switch(i) {
	    	case 1:
	    		switch(j) {
		    		case 1:
		    			return 9;
		    		case 2:
		    			return 7;
		    		case 3: case 7:
		    			return 1;
		    		case 4:
		    			return 3;
		    		case 5:
		    			return 6;
		    		case 6:
		    			return 3;
	    		}
	    		return 0;
	    	case 2: 
	    		switch(j) {
		    		case 1: case 3:
		    			return 3;
		    		case 2:
		    			return 4;
		    		case 4:
		    			return 7;
	    		}
	    		return 0;
	    	case 3: 
	    		return 9;
	    	case 4:
	    		return 3;
    	}
    	return 0;
    }

    public static int getSubGroup(int i) {
    	switch(i) {
	    	case 1:
	    		return 7;
	    	case 2: 
	    		return 4;
	    	case 3: case 4:
	    		return 1;
    	}
    	return 0;
    }

	public Integer getKms() {
		return kms;
	}

	public void setKms(Integer kms) {
		this.kms = kms;
	}

	public void setItems(Collection<ReparationItemData> items) {
		this.items = items;
	}

	public Collection<ReparationItemData> getItems() {
		return items;
	}

	public Collection<ReparationItemData> getItems(int item) {
		Collection<ReparationItemData> values = new LinkedList<ReparationItemData>();
		Iterator<ReparationItemData> it = items.iterator();
		while(it.hasNext()) {
			ReparationItemData itemData = it.next();
			if(itemData.getMaintenance1().intValue() == item)
				values.add(itemData);
		}
		return values;
	}

	public Collection<ReparationItemData> getPendingItems(int item) {
		Collection<ReparationItemData> values = new LinkedList<ReparationItemData>();
		Iterator<ReparationItemData> it = items.iterator();
		while(it.hasNext()) {
			ReparationItemData itemData = it.next();
			if(itemData.getMaintenance1().intValue() == item && itemData.getStatus().intValue() == ReparationItemData.PENDING)
				values.add(itemData);
		}
		return values;
	}

	public void addNewItem(int i, int j) {
		items.add(new ReparationItemData(i,j));
	}

	public void addStrNewItem(int i, int j) {
		if(!strItems.contains(i+"-"+j)) {
			strItems.add(i+"-"+j);
			items.add(new ReparationItemData(i,j));
		}
	}

	public Integer containsItem(int i, int j) {
		Iterator<ReparationItemData> it = items.iterator();
		while(it.hasNext()) {
			ReparationItemData itemData = it.next();
			if(itemData.getMaintenance1().intValue() == i && itemData.getMaintenance2().intValue() == j)
				return itemData.getId();
		}
		return null;
	}

	public boolean hasPendingItems() {
		Iterator<ReparationItemData> it = items.iterator();
		while(it.hasNext()) {
			if(it.next().getStatus().intValue() == PENDING)
				return true;
		}
		return false;
	}

	public void addComment(int maintenance, String comment) {
		if(comments.containsKey(maintenance)) {
			comments.remove(maintenance);
		}
		comments.put(maintenance, comment);
	}

	public String getComment(int maintenance) {
		if(comments.containsKey(maintenance)) {
			return comments.get(maintenance);
		}
		return null;
	}

	public Iterator<Integer> getCommentKeys() {
		return comments.keySet().iterator();
	}

	public boolean checkGroup(int i) {
		Iterator<ReparationItemData> it = items.iterator();
		int min = i * 10;
		int max = min + 10;
		while(it.hasNext()) {
			ReparationItemData itemData = it.next();
			if(itemData.getMaintenance1() > min && itemData.getMaintenance1().intValue() < max)
				return true;
		}
		return false;
	}

	public Date getInserted() {
		return inserted;
	}

	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}

}
