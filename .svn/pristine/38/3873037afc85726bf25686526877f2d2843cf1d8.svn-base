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
public class ItemAttribute  implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
	private String name;
	protected boolean active;
	
    public ItemAttribute() {
    }

    public ItemAttribute(Integer id,String name,boolean active) {
        this.id=id;
        this.name = name;
        this.active = active;
    }
    
    public ItemAttribute(Integer id) {
        this.id=id;
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
    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }
    
    public int compareTo(Object arg0) {
        ItemAttribute arg = (ItemAttribute)arg0;
        if(name == null && arg.getName() == null) {
            return 0;            
        }
        if(name != null && arg.getName() != null) {
            return name.compareTo(arg.getName());            
        }
        return (name == null) ? 1 : -1;            
    }

    /**
     * @param object
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
