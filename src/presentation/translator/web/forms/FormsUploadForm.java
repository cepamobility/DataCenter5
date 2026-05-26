package presentation.translator.web.forms;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class FormsUploadForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormFile file;
	private String radioDate="actualdate";
	private String otherDateDay;
	private String otherDateMonth;
	private String otherDateYear;
	private String directory;
	
	/**
	* Reset all properties to their default values.
	*
	* @param mapping The mapping used to select this instance
	* @param request The servlet request we are processing
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		radioDate=null;
		radioDate="actualdate";
		file = null;
		otherDateDay=null;
		otherDateMonth=null;
		otherDateYear=null;
		directory=null;
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
	public FormsUploadForm(FormFile file, String radiodate, String otherdateday,
			String otherdatemonth, String otherdateyear, String directory,
			String cepaTipsMX) {
		super();
		this.file = file;
		this.radioDate = radiodate;
		this.otherDateDay = otherdateday;
		this.otherDateMonth = otherdatemonth;
		this.otherDateYear = otherdateyear;
		this.directory = directory;
	}
	
	/**
	 * 
	 */
	public FormsUploadForm() {
		super();
		file = null;
		radioDate="actualdate";		
		otherDateDay=null;
		otherDateMonth=null;
		otherDateYear=null;
		directory=null;
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

}
