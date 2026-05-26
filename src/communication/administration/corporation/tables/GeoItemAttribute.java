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
public class GeoItemAttribute  implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
	private String name;
	protected boolean active;
	
    public GeoItemAttribute() {
    }

    public GeoItemAttribute(Integer id,String name,boolean active) {
        this.id=id;
        this.name = name;
        this.active = active;
    }

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
    /**
     * @param newName
     */
    public void setName(String newName) {
        name = newName;
    }

    public boolean isActive() {
        return active;
    }
    
    public int compareTo(Object arg0) {
        GeoItemAttribute arg = (GeoItemAttribute)arg0;
        if(name == null && arg.getName() == null) {
            return 0;            
        }
        if(name != null && arg.getName() != null) {
            return name.compareTo(arg.getName());            
        }
        return (name == null) ? 1 : -1;            
    }

    public boolean equals(Object o) {
        return id.equals(((GeoItemAttribute)o).id);
    }
}
