/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import java.util.Date;

/**
 * @author jrodriguez
 */
public class EmailAddress {

	private String address;
	private String password;
	private Date date;
	private Integer sended;
	
	public EmailAddress() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getSended() {
		return sended;
	}

	public void setSended(Integer sended) {
		this.sended = sended;
	}

}
