package communication.activity.tables;

import java.io.Serializable;

import persistence.activity.tables.PointObservation;

import communication.datatype.tables.GeneralDTData;

public class PointObservationData implements Serializable,Comparable {
    
    private static final long serialVersionUID = 1L;
    
    protected Integer idObservation;
    protected String text;	
    protected String textReport;	
    protected Integer obsOrder;
 	
    
	/**
     * @param id
     * @param text
     * @param textReport
     * @param itemOrder
     */
    public PointObservationData(Integer id, String text, String textReport, Integer obsOrder) {
        super();
        this.idObservation = id;
        this.text = text;
        this.textReport = textReport;
        this.obsOrder = obsOrder;
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

	public PointObservationData() {
		super();	
	}
	
	public PointObservationData(GeneralDTData data){
		text=data.getResourceBundleId();
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
		PointObservationData other = (PointObservationData) obj;
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
	
	public PointObservationData(Integer id) {
	    this.idObservation=id;
	}
	
	public PointObservationData(Integer id,String text) {
	    this.idObservation=id;
	    this.text = text;
	}	

	/**
	 * @return Returns the itemOrder.
	 */
	public Integer getObsOrder() {
		return obsOrder;
	}

	/**
	 * @param itemOrder The itemOrder to set.
	 */
	public void setObsOrder(Integer obsOrder) {
		this.obsOrder = obsOrder;
	}

    public int compareTo(Object o) {
        return obsOrder.compareTo(((PointObservationData)o).obsOrder);
    }

    /**
     * 
     * @return
     */
    public PointObservation getPointObservation(){
    	PointObservation p = new persistence.activity.tables.PointObservation(
    			this.idObservation,this.text,this.textReport,this.obsOrder);
    	return p;
    }
   	
}
