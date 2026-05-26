/**
 * Created on 01-mar-2006
 * CEPA
 * DataCenter 5
 */
package communication.util;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author jrodriguez
 */
public class ListResult implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Collection elements;
    private int total;
    private int subtotal;
    
    public ListResult(){}
    /**
     * @param elements
     * @param total
     */
    public ListResult(Collection elements, int total) {
        this.elements = elements;
        this.total = total;
    }
    
    public ListResult(Collection elements, int total, int subtotal) {
        this.elements = elements;
        this.total = total;
        this.subtotal = subtotal;
	}
	/**
     * @return Returns the elements.
     */
    public Collection getElements() {
        return elements;
    }
    
    /**
     * @return Returns the total.
     */
    public int getTotal() {
        return total;
    }
    
    /**
     * @param elements The elements to set.
     */
    public void setElements(Collection elements) {
        this.elements = elements;
    }
    
    /**
     * @param total The total to set.
     */
    public void setTotal(int total) {
        this.total = total;
    }
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
}
