package presentation.translator.web.reports;

import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


public class ReportUploadForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FormFile file;
	String year = Integer.toString(
				Calendar.getInstance(TimeZone.getDefault()).get(1));
	String radioDate="actualdate";
	String otherDateDay="";
	String otherDateMonth="";
	String otherDateYear="";
	String directory="";
	
	Integer divOrg1;
	
	
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
	public ReportUploadForm(FormFile file, String year, String radiodate, String otherdateday, String otherdatemonth, String otherdateyear, String directory) {
		super();
		this.file = file;
		this.year = year;
		this.radioDate = radiodate;
		this.otherDateDay = otherdateday;
		this.otherDateMonth = otherdatemonth;
		this.otherDateYear = otherdateyear;
		this.directory = directory;
	}
	
	/**
	 * @param file
	 * @param year
	 * @param radiodate
	 * @param otherdateday
	 * @param otherdatemonth
	 * @param otherdateyear
	 * @param directory
	 * 
	 */
	public ReportUploadForm(FormFile file, String year, String radiodate, String otherdateday, String otherdatemonth, String otherdateyear, String directory, Integer divOrg1) {
		super();
		this.divOrg1=divOrg1;
		this.file = file;
		this.year = year;
		this.radioDate = radiodate;
		this.otherDateDay = otherdateday;
		this.otherDateMonth = otherdatemonth;
		this.otherDateYear = otherdateyear;
		this.directory = directory;
	}
	
	/**
	 * 
	 */
	public ReportUploadForm() {
		super();		
		year = Integer.toString(
			Calendar.getInstance(TimeZone.getDefault()).get(1));
		radioDate="actualdate";		
		otherDateDay="";
		otherDateMonth="";
		otherDateYear="";
		directory="";
		divOrg1=0;
	}

	
	


	public Integer getDivOrg1() {
		return divOrg1;
	}




	public void setDivOrg1(Integer divOrg1) {
		this.divOrg1 = divOrg1;
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

	
	
}
