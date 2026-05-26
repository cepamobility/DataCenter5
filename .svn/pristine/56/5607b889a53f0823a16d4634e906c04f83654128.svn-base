/**
 * Created on 14-nov-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;

/**
 * @author jrodriguez
 */
public class CorporationAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
	private String name;
	private Integer type;
	
    public CorporationAttribute() {
    }

    public CorporationAttribute(Integer id,String name,Integer type) {
        this.id=id;
        this.name = name;
        this.type = type;
    }

	public CorporationAttribute(Integer id) {
        this.id=id;
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public boolean equals(Object corporation) {
		return id.intValue() == ((CorporationAttribute)corporation).id.intValue();
	}
}
