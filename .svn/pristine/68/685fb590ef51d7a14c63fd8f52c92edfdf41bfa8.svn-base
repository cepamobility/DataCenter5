/**
 * Created on 06-may-2008
 * CEPA
 * DataCenter 5
 */
package presentation.report;

import java.util.Collection;
import java.util.LinkedList;

public class Column implements Comparable {
    
    private Integer order;
    private int width;
    private Collection headers;
    
    public Column(int order, int width) {
        this.order = new Integer(order);
        this.width = width;
        headers = new LinkedList();
    }
    
    public int compareTo(Object o) {
        return order.compareTo(((Column)o).order);
    }

    public Collection getHeaders() {
        return headers;
    }

    public Integer getOrder() {
        return order;
    }

    public int getWidth() {
        return width;
    }
    
    public void addHeader(Header header) {
        headers.add(header);
    }
}

