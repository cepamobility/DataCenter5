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
public class LabelItemAttribute  implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    private Integer id;
	private String name;
    private Integer showLevel;
	
    public LabelItemAttribute() {
    }

    public LabelItemAttribute(Integer id,String name,Integer showLevel) {
        this.id=id;
        this.name = name;
        this.showLevel = showLevel;
    }

	public LabelItemAttribute(String name) {
        this.name = name;
    }

    public LabelItemAttribute(String name,Integer showLevel) {
        this.name = name;
        this.showLevel = showLevel;
    }

    public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

    public int compareTo(Object arg0) {
        LabelItemAttribute arg = (LabelItemAttribute)arg0;
        if(name == null && arg.getName() == null) {
            return 0;            
        }
        if(name != null && arg.getName() != null) {
            return name.compareTo(arg.getName());            
        }
        return (name == null) ? 1 : -1;            
    }

    public Integer getShowLevel() {
        return showLevel;
    }
    
}
