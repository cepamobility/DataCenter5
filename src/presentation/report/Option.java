/**
 * Created on 06-may-2008
 * CEPA
 * DataCenter 5
 */
package presentation.report;

public class Option implements Comparable {
    
    private String key;
    private Integer order;
    private int type;
    
    public static final int CHECK = 0; 
    public static final int LABEL = 1; 
    public static final int RADIO = 2; 
    
    public Option(String key, Integer order, int type) {
        this.key = key;
        this.order = order;
        this.type = type;
    }


    public int compareTo(Object o) {
        return order.compareTo(((Option)o).order);
    }


    public String getKey() {
        return key;
    }


    public Integer getOrder() {
        return order;
    }


    public int getType() {
        return type;
    }

}
