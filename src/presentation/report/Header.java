/**
 * Created on 06-may-2008
 * CEPA
 * DataCenter 5
 */
package presentation.report;

import java.util.Collection;
import java.util.LinkedList;

public class Header implements Comparable {
    
    private String key;
    private Integer order;
    private Collection options;
    
    public Header(String key,int order) {
        this.key = key;
        this.order = new Integer(order);
        options = new LinkedList();
    }

    public int compareTo(Object o) {
        return order.compareTo(((Header)o).order);
    }

    public String getKey() {
        return key;
    }

    public Collection getOptions() {
        return options;
    }

    public Integer getOrder() {
        return order;
    }
        
    public void addOptions(String[] textOptions) {
        for(int i = 0; i < textOptions.length; i++) {
            options.add(new Option(textOptions[i],i,Option.CHECK));
        }
    }
    
    public void addOptions(String[] textOptions,int index) {
        for(int i = 0; i < textOptions.length; i++) {
            options.add(new Option(textOptions[i],index,Option.CHECK));
            index++;
        }
    }
}

