package presentation.translator.web.routes;

import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


public class RoutesUploadForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormFile file;
	private String year = Integer.toString(
				Calendar.getInstance(TimeZone.getDefault()).get(1));
	private String radioDate="actualdate";
	private String otherDateDay="";
	private String otherDateMonth="";
	private String otherDateYear="";
	private String directory="";
	
	private String country = "";
	private String depto = "";
	
	/**
	* Reset all properties to their default values.
	*
	* @param mapping The mapping used to select this instance
	* @param request The servlet request we are processing
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		year = Integer.toString(
				Calendar.getInstance(TimeZone.getDefault()).get(1));
		radioDate="actualdate";
		otherDateDay="";
		otherDateMonth="";
		otherDateYear="";
		directory="";
		country="";
		depto="";
	}
	
	
	/**
	 * @param file
	 * @param year
	 * @param radiodate
	 * @param otherdateday
	 * @param otherdatemonth
	 * @param otherdateyear
	 * @param directory
	 */
	public RoutesUploadForm(FormFile file, String year, String radiodate, String otherdateday, 
			String otherdatemonth, String otherdateyear, String directory, String country,
			String depto) {
		super();
		this.file = file;
		this.year = year;
		this.radioDate = radiodate;
		this.otherDateDay = otherdateday;
		this.otherDateMonth = otherdatemonth;
		this.otherDateYear = otherdateyear;
		this.directory = directory;
		this.country = country;
		this.depto = depto;
	}
	
	/**
	 * 
	 */
	public RoutesUploadForm() {
		super();		
		year = Integer.toString(
			Calendar.getInstance(TimeZone.getDefault()).get(1));
		radioDate="actualdate";		
		otherDateDay="";
		otherDateMonth="";
		otherDateYear="";
		directory="";
		country = "";
		depto = "";
	}



	/**
	 * @return Returns the directory.
	 */
	public String getDirectory() {
		return directory;
	}
	/**
	 * @param directory The directory to set.
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	/**
	 * @return Returns the file.
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param file The file to set.
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	/**
	 * @return Returns the otherdateday.
	 */
	public String getOtherDateDay() {
		return otherDateDay;
	}
	/**
	 * @param otherdateday The otherdateday to set.
	 */
	public void setOtherDateDay(String otherdateday) {
		this.otherDateDay = otherdateday;
	}
	/**
	 * @return Returns the otherdatemonth.
	 */
	public String getOtherDateMonth() {
		return otherDateMonth;
	}
	/**
	 * @param otherdatemonth The otherdatemonth to set.
	 */
	public void setOtherDateMonth(String otherdatemonth) {
		this.otherDateMonth = otherdatemonth;
	}
	/**
	 * @return Returns the otherdateyear.
	 */
	public String getOtherDateYear() {
		return otherDateYear;
	}
	/**
	 * @param otherdateyear The otherdateyear to set.
	 */
	public void setOtherDateYear(String otherdateyear) {
		this.otherDateYear = otherdateyear;
	}
	/**
	 * @return Returns the radiodate.
	 */
	public String getRadioDate() {
		return radioDate;
	}
	/**
	 * @param radiodate The radiodate to set.
	 */
	public void setRadioDate(String radiodate) {
		this.radioDate = radiodate;
	}
	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}


	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return Returns the depto.
	 */
	public String getDepto() {
		return depto;
	}


	/**
	 * @param depto The depto to set.
	 */
	public void setDepto(String depto) {
		this.depto = depto;
	}

	
	
}
