/**
 * Created on 29-dic-2006
 * CEPA
 * DataCenter 5
 */
package communication.util;

import java.io.Serializable;

public class ElementData implements Comparable,Serializable  {

    private Integer id;
    private String name;
    
    public ElementData() {
        super();
    }

    /**
     * @param parent
     * @param id
     * @param name
     */
    public ElementData(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Object o) {
        return name.compareTo(((ElementData)o).getName());
    }
   
}
