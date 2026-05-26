package presentation.translator.web.dpa;



import java.lang.reflect.InvocationTargetException;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import persistence.util.Util;

import communication.administration.corporation.tables.InstructorRegistryData;



public class InstructorRegistryForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String comment;
    private int count;
    private String dateDay;
    private String dateMonth;
    private String dateYear;
    private String loginname;
    private String type;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	 
	public String getDateDay() {
		return dateDay;
	}
	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}
	public String getDateMonth() {
		return dateMonth;
	}
	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}
	public String getDateYear() {
		return dateYear;
	}
	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public InstructorRegistryData makeData( ){
		InstructorRegistryData data=new InstructorRegistryData();
		try {
			BeanUtils.copyProperties(data, this);
			data.setDate(Util.getDate(dateYear, dateMonth, dateDay));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
    
    
}
