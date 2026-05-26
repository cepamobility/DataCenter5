package presentation.translator.web.driver;

public class CsvUpdateDriversForm extends DriverAdvancedSearchForm{

	
	public CsvUpdateDriversForm() {
		super();		
	}

	/**
	 * 
	 * @param form
	 * @param driverId
	 * @param name
	 * @param pageNum
	 * @param count
	 * @param status
	 * @param link
	 */
	public CsvUpdateDriversForm(String form, String driverId, String name,
			int pageNum, int count, String status, String link) {
		super(form, driverId, name, pageNum, count, status, link);		
	}
	
	

}
