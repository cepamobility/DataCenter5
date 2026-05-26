package persistence.activity.tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.activity.tables.PointObservationData;
import communication.administration.corporation.tables.CorporationData;

public class Point implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String text;	
	
	// Orden en el que se despliegan los puntos en el formulario	
	private int pointOrder = -1;
	
	// Orden en el que se despliegan los puntos en el reporte
	private int pointOrderReport = -1;
	
	// Texto descriptivo del punto
	private String textReport;
	
	// Item por defecto, si no se marca ningún item en el punto
	private Item defaultItem;
	
	// Items que integran este punto del formulario
	//Collection items;
	Set<Item> items;
	
	protected Set<PointObservation> observations;
	
	private boolean disableForNovato = false;
	
	public boolean getDisableForNovato() {
		return disableForNovato;
	}

	public void setDisableForNovato(boolean disableForNovato) {
		this.disableForNovato = disableForNovato;
	}

	/**
	 * @return Returns the items.
	 */
	public Set<Item> getItems() {
		return items;
	}

	/**
	 * @param items The items to set.
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the resourceKeyText.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param resourceKeyText The resourceKeyText to set.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return Returns the textReport.
	 */
	public String getTextReport() {
		return textReport;
	}

	/**
	 * @param textReport The textReport to set.
	 */
	public void setTextReport(String textReport) {
		this.textReport = textReport;
	}

	/**
	 * @return Returns the defaultItem.
	 */
	public Item getDefaultItem() {
		return defaultItem;
	}

	/**
	 * @param defaultItem The defaultItem to set.
	 */
	public void setDefaultItem(Item defaultItem) {
		this.defaultItem = defaultItem;
	}

	/**
	 * @return Returns the pointOrder.
	 */
	public int getPointOrder() {
		return pointOrder;
	}

	/**
	 * @param pointOrder The pointOrder to set.
	 */
	public void setPointOrder(int pointOrder) {
		this.pointOrder = pointOrder;
	}

	public int getPointOrderReport() {
		return pointOrderReport;
	}

	public void setPointOrderReport(int pointOrderReport) {
		this.pointOrderReport = pointOrderReport;
	}

	/**
	 * @return Returns the defaultTextReport.
	 */
	/*public String getDefaultTextReport() {
		return defaultTextReport;
	}
*/
	/**
	 * @param defaultTextReport The defaultTextReport to set.
	 */
	/*public void setDefaultTextReport(String defaultTextReport) {
		this.defaultTextReport = defaultTextReport;
	}
*/
	/* Obtiene la lista de Items ordenados por el orden del item
	 * dentro del punto.
	 */
	public ArrayList<Item> getOrderedItems(){
		// Creo una lista a partir del Set:
		ArrayList<Item> orderedItems	= new ArrayList<Item>();
		Iterator<Item> it = this.items.iterator();
		while (it.hasNext())
		{
			Item pc = it.next();
			orderedItems.add(pc);
		}
				
		// ordeno la lista por orden de los puntos
		java.util.Collections.sort(orderedItems,
				new Comparator(){
					public int compare(Object pc1, Object pc2) 
					{
						return ((Item)pc1).getItemOrder() - 
							((Item)pc2).getItemOrder();
					}
	    		}
		);		
		return orderedItems;
	}
	
	/* Obtiene la lista de observaciones del punto  ordenados por el orden de la obs
	 * dentro del punto.
	 */
	public ArrayList<PointObservation> getOrderedPointObservations(){
		// Creo una lista a partir del Set:
		ArrayList<PointObservation> orderedObs	= new ArrayList<PointObservation>();
		Iterator<PointObservation> it = this.observations.iterator();
		while (it.hasNext())
		{
			PointObservation pc = it.next();
			orderedObs.add(pc);
		}
				
		// ordeno la lista por orden de los puntos
		java.util.Collections.sort(orderedObs,
				new Comparator(){
					public int compare(Object pc1, Object pc2) 
					{
						return ((PointObservation)pc1).getObsOrder() - 
							((PointObservation)pc2).getObsOrder();
					}
	    		}
		);		
		return orderedObs;
	}
	
	/*
	 * Devuelve true si el objeto pasado por parametro
	 * es igual al punto (segun su id). 
	 */
	public boolean equals(Object obj)
	{
		if (obj.getClass().equals(Point.class))
		{
			Point point = (Point) obj;
			if (point.getId().equals(this.id))
				return true;
			else
				return false;
		}else
			return false;
				
	}

    public PointData getPointData() {
        Collection<ItemData> itemCollection = new LinkedList<ItemData>();
        Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            Item item = it.next();
            itemCollection.add(item.getItemData());
        }
        ItemData defaultItemData = (defaultItem == null) ? null : defaultItem.getItemData();
        
        Collection<PointObservationData> obsCollection = new LinkedList<PointObservationData>();
        Iterator<PointObservation> itObs = observations.iterator();
        while(itObs.hasNext()) {
            PointObservation obs = itObs.next();
            obsCollection.add(obs.getObsData());
        }
        return new PointData(id,this.text,new Integer(pointOrder),
        		new Integer(pointOrderReport),textReport,itemCollection,
        		disableForNovato,defaultItemData,obsCollection);

    }

    public int compareTo(Object o) {
        return new Integer(pointOrder).compareTo(new Integer(((Point)o).pointOrder));
    }

	/**
	 * @return the observations
	 */
	public Set<PointObservation> getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(Set<PointObservation> observations) {
		this.observations = observations;
	}
    
	public boolean isValid(Integer checklist, int corporation) {
		if(checklist.intValue() == 79 && id.intValue() >= 3391)
			return corporation == CorporationData.MONTES_DE_PLATA;
		if(checklist.intValue() == 80 && id.intValue() == 8814)
			return corporation == CorporationData.MONTES_DE_PLATA;
		if(checklist.intValue() == 80 && id.intValue() >= 4001 && id.intValue() <= 4022) {
			return corporation == CorporationData.MONTES_DE_PLATA || corporation == CorporationData.FRAYLOG || (corporation == CorporationData.FORESTALORIENTAL && id < 4018);
		}
		if(checklist.intValue() == 80 && id.intValue() >= 8804 && id.intValue() <= 8813) {
			return corporation == CorporationData.FORESTALORIENTAL;
		}
		return true;
	}
}
