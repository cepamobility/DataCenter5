package persistence.activity.tables;

import communication.activity.tables.CEPAScheduleActivityRegistryObservationData;

public class CEPAScheduleActivityRegistryObservation {
	
	 private static final long serialVersionUID = 1L;
	    
	    protected Integer idObservation;
	    protected String text;	
	    protected String textReport;	
	    protected int obsOrder = -1;
	    
	    public CEPAScheduleActivityRegistryObservationData getData(){
	    	CEPAScheduleActivityRegistryObservationData d = new CEPAScheduleActivityRegistryObservationData();
	    	d.setIdObservation(idObservation);
	    	d.setText(text);
	    	d.setTextReport(textReport);
	    	d.setObsOrder(obsOrder);
	    	return d;
	    }
	    
	    public CEPAScheduleActivityRegistryObservation(CEPAScheduleActivityRegistryObservationData d){
	    	this.idObservation = d.getIdObservation();
	    	this.text = d.getText();
	    	this.textReport = d.getTextReport();
	    	this.obsOrder = d.getObsOrder();
	    }
	    	    
		public CEPAScheduleActivityRegistryObservation(Integer idObservation,
				String text, String textReport, int obsOrder) {
			super();
			this.idObservation = idObservation;
			this.text = text;
			this.textReport = textReport;
			this.obsOrder = obsOrder;
		}
		public CEPAScheduleActivityRegistryObservation() {
			super();
			// TODO Auto-generated constructor stub
		}
		/**
		 * @return the idObservation
		 */
		public Integer getIdObservation() {
			return idObservation;
		}
		/**
		 * @param idObservation the idObservation to set
		 */
		public void setIdObservation(Integer idObservation) {
			this.idObservation = idObservation;
		}
		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}
		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		/**
		 * @return the textReport
		 */
		public String getTextReport() {
			return textReport;
		}
		/**
		 * @param textReport the textReport to set
		 */
		public void setTextReport(String textReport) {
			this.textReport = textReport;
		}
		/**
		 * @return the obsOrder
		 */
		public int getObsOrder() {
			return obsOrder;
		}
		/**
		 * @param obsOrder the obsOrder to set
		 */
		public void setObsOrder(int obsOrder) {
			this.obsOrder = obsOrder;
		}
		/**
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
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
			CEPAScheduleActivityRegistryObservation other = (CEPAScheduleActivityRegistryObservation) obj;
			if (idObservation == null) {
				if (other.idObservation != null)
					return false;
			} else if (!idObservation.equals(other.idObservation))
				return false;
			return true;
		}
	    
		
	    

}
