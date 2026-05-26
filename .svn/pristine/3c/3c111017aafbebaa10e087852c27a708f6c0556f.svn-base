package persistence.activity.tables;

import java.io.Serializable;

import communication.activity.tables.PointObservationData;
import communication.datatype.tables.GeneralDTData;

public class PointObservation implements Serializable, Comparable {
    
    private static final long serialVersionUID = 1L;
    
    protected Integer idObservation;
    protected String text;	
    protected String textReport;	
    protected int obsOrder = -1;
    
    

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

	public PointObservation() {
		super();	
	}
	
	public PointObservation(Integer id, String text, String textReport, Integer order){
		this.idObservation = id;
		this.text= text;
		this.textReport = textReport;
		this.obsOrder = order;		
	}
	
	public PointObservation(Object[] data) {
		this.idObservation =(Integer) data[0];
		this.text=(String)data[1];
		this.textReport =(String)data[2];
		this.obsOrder =(Integer) data[3];		
	}
	
	public PointObservation(GeneralDTData data){
		text = data.getResourceBundleId();
	}

	public String getText() {
		return text;
	}

	public void setText(String resourceKeyText) {
		this.text = resourceKeyText;
	}
	
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idObservation == null) ? 0 : idObservation.hashCode());
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
		PointObservation other = (PointObservation) obj;
		if (idObservation == null) {
			if (other.idObservation != null)
				return false;
		} else if (!idObservation.equals(other.idObservation))
			return false;
		return true;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getIdObservation() {
		return idObservation;
	}

	/**
	 * @param id The id to set.
	 */
	public void setIdObservation(Integer id) {
		this.idObservation = id;
	}	
	
	public PointObservation(Integer id) {
	    this.idObservation =id;
	}
	
	public PointObservation(Integer id,String text) {
	    this.idObservation =id;
	    this.text = text;
	}	

	/**
	 * @return Returns the itemOrder.
	 */
	public int getObsOrder() {
		return obsOrder;
	}

	/**
	 * @param itemOrder The itemOrder to set.
	 */
	public void setObsOrder(int obsOrder) {
		this.obsOrder = obsOrder;
	}

    public PointObservationData getObsData() {
        return new PointObservationData(idObservation,text,textReport,new Integer(obsOrder));
    }

   

    public int compareTo(Object o) {
        return new Integer(obsOrder).compareTo(new Integer(((PointObservation)o).obsOrder));
    }
   

}
