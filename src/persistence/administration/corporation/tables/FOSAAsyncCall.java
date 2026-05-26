/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FOSAAsyncCall implements Serializable {

    private Long id;
    private Integer reference;
        
	private String plate;
    private  Integer hours;
    private  String reasonDescription;
    private Integer reasonCode;
    private String dateHourStartPenalty;
    private Integer typeOfPenalty;
    private Double totalLessPoints;
    private Double accountTotal;
    		
    private String result1;
    private String result2;
    private String result3;
    private String result4;
    private String result5;
    private String result6;
    private String result7;
    private String result8;
    private String result9;
    private String result10;
    
    private Date callDate;
    private int count=0;
    private int status=0;
        
    public Integer getReference() {
		return reference;
	}
    
	public void setReference(Integer reference) {
		this.reference = reference;
	}
    
	public Double getTotalLessPoints() {
		return totalLessPoints;
	}
	public void setTotalLessPoints(Double totalLessPoints) {
		this.totalLessPoints = totalLessPoints;
	}
	public Double getAccountTotal() {
		return accountTotal;
	}
	public void setAccountTotal(Double accountTotal) {
		this.accountTotal = accountTotal;
	}
	public String getResult4() {
		return result4;
	}
	public void setResult4(String result4) {
		this.result4 = result4;
	}
	public String getResult5() {
		return result5;
	}
	public void setResult5(String result5) {
		this.result5 = result5;
	}
	public String getResult6() {
		return result6;
	}
	public void setResult6(String result6) {
		this.result6 = result6;
	}
	public String getResult7() {
		return result7;
	}
	public void setResult7(String result7) {
		this.result7 = result7;
	}
	public String getResult8() {
		return result8;
	}
	public void setResult8(String result8) {
		this.result8 = result8;
	}
	public String getResult9() {
		return result9;
	}
	public void setResult9(String result9) {
		this.result9 = result9;
	}
	public String getResult10() {
		return result10;
	}
	public void setResult10(String result10) {
		this.result10 = result10;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public String getReasonDescription() {
		return reasonDescription;
	}
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}
	public Integer getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getDateHourStartPenalty() {
		return dateHourStartPenalty;
	}
	public void setDateHourStartPenalty(String dateHourStartPenalty) {
		this.dateHourStartPenalty = dateHourStartPenalty;
	}
	public Integer getTypeOfPenalty() {
		return typeOfPenalty;
	}
	public void setTypeOfPenalty(Integer typeOfPenalty) {
		this.typeOfPenalty = typeOfPenalty;
	}
	public String getResult1() {
		return result1;
	}
	public void setResult1(String result1) {
		this.result1 = result1;
	}
	public String getResult2() {
		return result2;
	}
	public void setResult2(String result2) {
		this.result2 = result2;
	}
	public String getResult3() {
		return result3;
	}
	public void setResult3(String result3) {
		this.result3 = result3;
	}
	public Date getCallDate() {
		return callDate;
	}
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}



	

}