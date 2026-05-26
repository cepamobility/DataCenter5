package communication.activity.tables;

import java.io.Serializable;
import java.util.LinkedList;

public class CEPAActivityDefinitionData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Nombre ej: BTW1...
	 */
	private String resourceKeyName;	
	/**
	 * Ej: BTW, Multiplicadores,.....
	 */
	private String category;	
	
	private CheckListData checkList;	
	/**
	 * Utiliza reporte global
	 */
	private boolean haveGlobalReport;
	
	/**
	 * Uriliza reporte individual
	 */
	private boolean haveIndividualReport;
	
	/**
	 * Utiliza sistema de notas
	 */
	private boolean haveNote;
	
	/**
	 * Tiene form
	 */
	private boolean hasForm;
	
	private boolean hasTeoricoAsistence;
	private boolean hasPracticoAsistence;
	
	
	/**
	 * Duración en días
	 */
	private int last;
	
	/**
	 * Vigencia en Meses
	 * valor 0 no expira
	 * valor -1 depende de la evaluación
	 * valor > 0 tiempo en meses para la expiración 
	 */
	private int timeExpiry;
	
	/**
	 * Nuevos Campos para Ajuste de Familias de Actividades
	 */	
	private CEPAActivitySubFamilyData subFamily; // la subfamilia incluye a la familia
	private boolean isBTW; // indica si la actividad se toma dentro del grupo de los BTW

	/**
	 * Conjunto de Observaciones Generales Predefinidas
	 * para el tipo de actividad en concreto.
	 */
	protected LinkedList<CEPAScheduleActivityRegistryObservationData> generalObservations;
	
	public CEPAActivityDefinitionData() {
		
	}
	
	public CEPAActivityDefinitionData(String definition) {
		resourceKeyName = definition;
	}
	
	/**
	 * 
	 * @param resourceKeyName
	 * @param checkList
	 * @param haveGlobalReport
	 * @param haveIndividualReport
	 * @param haveNote
	 * @param last
	 * @param timeExpiry
	 * @param hasForm
	 * @param hasTeoricoAsistence
	 * @param hasPracticoAsistence
	 * @param subFamily
	 * @param isBTW
	 */
    public CEPAActivityDefinitionData(String resourceKeyName,CheckListData checkList,
    		boolean haveGlobalReport,boolean haveIndividualReport, boolean haveNote,
            int last, int timeExpiry, boolean hasForm, boolean hasTeoricoAsistence,
            boolean hasPracticoAsistence, CEPAActivitySubFamilyData subFamily, boolean isBTW,
            LinkedList<CEPAScheduleActivityRegistryObservationData> generalObservations) {
        this.resourceKeyName = resourceKeyName;
        this.haveGlobalReport = haveGlobalReport;
        this.haveIndividualReport = haveIndividualReport;
        this.haveNote = haveNote;
        this.last = last;
        this.timeExpiry = timeExpiry;
        this.checkList = checkList;     
        this.hasForm = hasForm;
        this.hasPracticoAsistence = hasPracticoAsistence;
        this.hasTeoricoAsistence = hasTeoricoAsistence;
        this.subFamily = subFamily;
        this.isBTW = isBTW;
        this.generalObservations = generalObservations;
        
    }
	
	public int getTimeExpiry() {
		return timeExpiry;
	}

	public boolean getHasForm() {
		return hasForm;
	}

	public void setHasForm(boolean hasForm) {
		this.hasForm = hasForm;
	}

	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return Returns the checkList.
	 */
	public CheckListData getCheckList() {
		return checkList;
	}

	/**
	 * @param checkList The checkList to set.
	 */
	public void setCheckList(CheckListData checkList) {
		this.checkList = checkList;
	}

	/**
	 * @param haveGlobalReport The haveGlobalReport to set.
	 */
	public void setHaveGlobalReport(boolean haveGlobalReport) {
		this.haveGlobalReport = haveGlobalReport;
	}

	/**
	 * @param haveIndividualReport The haveIndividualReport to set.
	 */
	public void setHaveIndividualReport(boolean haveIndividualReport) {
		this.haveIndividualReport = haveIndividualReport;
	}

	/**
	 * @param haveNote The haveNote to set.
	 */
	public void setHaveNote(boolean haveNote) {
		this.haveNote = haveNote;
	}

	/**
	 * @param last The last to set.
	 */
	public void setLast(int last) {
		this.last = last;
	}

	/**
	 * @param resourceKeyName The resourceKeyName to set.
	 */
	public void setResourceKeyName(String resourceKeyName) {
		this.resourceKeyName = resourceKeyName;
	}

	/**
	 * @param timeExpiry The timeExpiry to set.
	 */
	public void setTimeExpiry(int timeExpiry) {
		this.timeExpiry = timeExpiry;
	}

	public boolean isHaveGlobalReport() {
		return haveGlobalReport;
	}

	public boolean isHaveIndividualReport() {
		return haveIndividualReport;
	}

	public boolean isHaveNote() {
		return haveNote;
	}

	public int getLast() {
		return last;
	}

	public String getResourceKeyName() {
		return resourceKeyName;
	}

	

	public boolean getHasPracticoAsistence() {
		return hasPracticoAsistence;
	}

	public void setHasPracticoAsistence(boolean hasPracticoAsistence) {
		this.hasPracticoAsistence = hasPracticoAsistence;
	}

	public boolean getHasTeoricoAsistence() {
		return hasTeoricoAsistence;
	}

	public void setHasTeoricoAsistence(boolean hasTeoricoAsistence) {
		this.hasTeoricoAsistence = hasTeoricoAsistence;
	}	
	
	/**
	 * @return the subFamily
	 */
	public CEPAActivitySubFamilyData getSubFamily() {
		return subFamily;
	}

	/**
	 * @param subFamily the subFamily to set
	 */
	public void setSubFamily(CEPAActivitySubFamilyData subFamily) {
		this.subFamily = subFamily;
	}

	/**
	 * @return the isBTW
	 */
	public boolean getIsBTW() {
		return isBTW;
	}

	/**
	 * @param isBTW the isBTW to set
	 */
	public void setIsBTW(boolean isBTW) {
		this.isBTW = isBTW;
	}

	/**
	 * @return the generalObservationsSet
	 */
	public LinkedList<CEPAScheduleActivityRegistryObservationData> getGeneralObservations() {
		return generalObservations;
	}

	/**
	 * @param generalObservationsSet the generalObservationsSet to set
	 */
	public void setGeneralObservations(
			LinkedList<CEPAScheduleActivityRegistryObservationData> generalObservations) {
		this.generalObservations = generalObservations;
	}
	
	
	
}
