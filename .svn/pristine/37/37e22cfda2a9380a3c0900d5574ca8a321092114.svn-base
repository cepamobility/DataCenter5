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
public class DivOrgAttribute  implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
	protected String name;
	
	public static final String SYNGENTA_DIVISION = "443951";
	public static final String SYNGENTA_SUBDIVISION = "443958";
	public static final String SYNGENTA_UNIT = "443959";
	public static final String CEPA_LOCATION_DEFAULT="364322";
	
    public DivOrgAttribute() {
    }

    public DivOrgAttribute(Integer id,String name) {
        this.id=id;
        this.name = name;
    }

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

    public int compareTo(Object arg0) {
        DivOrgAttribute arg = (DivOrgAttribute)arg0;
        if(name == null && arg.getName() == null) {
            return 0;            
        }
        if(name != null && arg.getName() != null) {
            return name.compareTo(arg.getName());            
        }
        return (name == null) ? 1 : -1;            
    }

}
