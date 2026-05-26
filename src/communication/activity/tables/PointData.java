package communication.activity.tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import communication.activity.report.ActivityReportTabletDataType;
import communication.administration.corporation.tables.CorporationData;
import communication.security.SecurityConstants;
import communication.vehicle.tables.VehicleData;

public class PointData implements Serializable,Comparable<PointData> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String text;	
	private Integer pointOrder = new Integer(-1);
	private Integer pointOrderReport = new Integer (-1);
	private String textReport;
	private Collection<ItemData> items;
    private ItemData defaultItem;
	private boolean disableForNovato = false;
	
	private Collection<PointObservationData> observations;

    public PointData() {
        super();
    }

    
    /**
     * @param id
     * @param text
     * @param pointOrder
     * @param pointOrderReport
     * @param textReport
     * @param items
     * @param disableForNovato
     */
    public PointData(Integer id, String text, Integer pointOrder, Integer pointOrderReport, 
    		String textReport, Collection<ItemData> items, boolean disableForNovato, 
    		ItemData defaultItem, Collection<PointObservationData> pointObservations) {
        super();
        this.id = id;
        this.text = text;
        this.pointOrder = pointOrder;
        this.pointOrderReport = pointOrderReport;
        this.textReport = textReport;
        this.items = items;
        this.disableForNovato = disableForNovato;
        this.defaultItem = defaultItem;
        this.observations = pointObservations;
    }


    public PointData(Integer id) {
		this.id = id;
	}


	public boolean isDisableForNovato() {
        return disableForNovato;
    }

    public Integer getId() {
        return id;
    }

    public Collection<ItemData> getItems() {
        return items;
    }

    public Integer getPointOrder() {
        return pointOrder;
    }

    public Integer getPointOrderReport() {
        return pointOrderReport;
    }

    public String getText() {
        return text;
    }

    public String getTextReport() {
        return textReport;
    }

    public void setDisableForNovato(boolean disableForNovato) {
        this.disableForNovato = disableForNovato;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setItems(Collection<ItemData> items) {
        this.items = items;
    }

    public void setPointOrder(Integer pointOrder) {
        this.pointOrder = pointOrder;
    }

    public void setPointOrderReport(Integer pointOrderReport) {
        this.pointOrderReport = pointOrderReport;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextReport(String textReport) {
        this.textReport = textReport;
    }

    public int compareTo(PointData o) {
        return pointOrder.compareTo(o.getPointOrder());
    }

    public Iterator<ItemData> getSortItems() {
    	if (items == null || items.size() == 0) 
    		return null;
    	
        Collections.sort((List<ItemData>)items);
        return items.iterator();
    }


    public boolean getDisableForNovato() {
        return disableForNovato;
    }


    public ItemData getDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(ItemData defaultItem) {
        this.defaultItem = defaultItem;
    }
    
    public ArrayList<ItemData> getOrderedItems(){
        ArrayList<ItemData> orderedItems  = new ArrayList<ItemData>();
        orderedItems.addAll(items);
        Collections.sort(orderedItems);      
        return orderedItems;
    }
    
    public ArrayList<PointObservationData> getOrderedPointObservationsData(){
        ArrayList<PointObservationData> orderedObs  = new ArrayList<PointObservationData>();
        orderedObs.addAll(observations);
        Collections.sort(orderedObs);      
        return orderedObs;
    }


	/**
	 * @return the observations
	 */
	public Collection<PointObservationData> getObservationsData() {
		return observations;
	}


	/**
	 * @param observations the observations to set
	 */
	public void setObservations(Collection<PointObservationData> observations) {
		this.observations = observations;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointData other = (PointData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Collection<ItemData> getRelevantItems(ActivityReportTabletDataType dataType) {
		Collection<ItemData> values = new LinkedList<ItemData>();
		HashMap<Integer,ItemData> elements = new HashMap<Integer,ItemData>();
		Iterator<ItemData> it = getSortItems();
		Collection<Integer> exclude = new LinkedList<Integer>();
		while(it.hasNext()) {
			ItemData item = it.next();
			if(item.getType().intValue() == 2 && dataType.isItem(id, item.getId()) && item.getDisabledItems() != null) {
				 StringTokenizer tokenizer = new StringTokenizer(item.getDisabledItems(),",");
				 while(tokenizer.hasMoreTokens()) {
					 exclude.add(new Integer(tokenizer.nextToken()));
				 }
			} else if(item.getType().intValue() == 1)
				elements.put(item.getId(), item);
		}
		if(exclude.size() > 0) {
			Iterator<Integer> itExc = exclude.iterator();
			while(itExc.hasNext()) 
				elements.remove(itExc.next());
		}
		Iterator<ItemData> itItems = elements.values().iterator();
		while(itItems.hasNext()) {
			values.add(itItems.next());
		}
		return values;
	}
    
	public String getPointKey(ActivityReportTabletDataType dataType) {
		String key = String.valueOf(id);
		Iterator<ItemData> it = getSortItems();
		while(it.hasNext()) {
			ItemData item = it.next();
			if(item.getType().intValue() == 2 && dataType.isItem(id, item.getId())) {
				return key+"-"+item.getId();
			}
		}
		return key;
	}

	public boolean isProcesable(VehicleData vehicleData, int corporation) {
		if(id < 4000 || id >= 4022)
			return true;
		if(corporation == CorporationData.FORESTALORIENTAL) {
			switch(id) {
				case 4001: case 4017:
					return true;
				case 4010: case 4011:
					int vehicleId = vehicleData.getId().intValue();
					if((vehicleData.getContractType() != null && vehicleData.getContractType().equals("vehicle.contracttype.suplente")) || vehicleId == 493076 || vehicleId == 617777 || vehicleId == 618122)
						return false;
					Calendar c = Calendar.getInstance();
					c.set(Calendar.DATE, 1);
					c.set(Calendar.MONTH, 10);
					c.set(Calendar.YEAR, 2020);
					return Calendar.getInstance().after(c);
				case 4002: case 4003: case 4006: case 4007: case 4013: case 4016: 
					return vehicleData.getContractType() != null && vehicleData.getContractType().equals("vehicle.contracttype.titular");
				default:
					return false;
			}
		}
        return false;
    }
}
