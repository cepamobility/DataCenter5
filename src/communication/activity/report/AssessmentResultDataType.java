package communication.activity.report;

import java.io.Serializable;

import communication.driver.tables.DriverMainAttributes;

public class AssessmentResultDataType implements Comparable, Serializable {

	
	// private DriverReportDataType driver;
	private DriverMainAttributes driver;
	
	protected Integer psiResult;
	protected String psiNote;
	protected Integer testResult;
	protected String testNote;
	
	
	public AssessmentResultDataType() {
		super();
	}

	public AssessmentResultDataType(DriverMainAttributes driver,
			 Integer psiResult, String psiNote,
			 Integer testResult, String testNote) {
		super();
		this.driver = driver;
		this.psiResult = psiResult;
		this.psiNote = psiNote;
		this.testResult = testResult;
		this.testNote = testNote;
	}

	/**
	 * @return the driver
	 */
	public DriverMainAttributes getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(DriverMainAttributes driver) {
		this.driver = driver;
	}

	

	/**
	 * @return the psiResult
	 */
	public Integer getPsiResult() {
		return psiResult;
	}

	/**
	 * @param psiResult the psiResult to set
	 */
	public void setPsiResult(Integer psiResult) {
		this.psiResult = psiResult;
	}

	/**
	 * @return the psiNote
	 */
	public String getPsiNote() {
		return psiNote;
	}

	/**
	 * @param psiNote the psiNote to set
	 */
	public void setPsiNote(String psiNote) {
		this.psiNote = psiNote;
	}

	/**
	 * @return the testResult
	 */
	public Integer getTestResult() {
		return testResult;
	}

	/**
	 * @param testResult the testResult to set
	 */
	public void setTestResult(Integer testResult) {
		this.testResult = testResult;
	}

	/**
	 * @return the testNote
	 */
	public String getTestNote() {
		return testNote;
	}

	/**
	 * @param testNote the testNote to set
	 */
	public void setTestNote(String testNote) {
		this.testNote = testNote;
	}

	public int compareTo(Object arg0) {
		return this.driver.compareTo(arg0);
		
	}
	
	
	
	
}