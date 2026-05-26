/**
 * Created on 14-nov-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.user.tables;

import java.io.Serializable;

import communication.administration.corporation.tables.CorporationAttribute;

/**
 * @author jrodriguez
 */
public class UserCorporationData implements Serializable, Comparable<UserCorporationData> {

    private static final long serialVersionUID = 1L;

    private Integer id;
	private String corporationName;
	private String login;
	private String role;
	
    public UserCorporationData() {
    }

	public UserCorporationData(Integer id, String corporationName, String login, String role) {
		this.id = id;
		this.corporationName = corporationName;
		this.login = login;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CorporationAttribute) {
			return id.intValue() == ((CorporationAttribute)obj).getId().intValue();
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(UserCorporationData o) {
		return corporationName.compareTo(o.corporationName);
	}

	
}
