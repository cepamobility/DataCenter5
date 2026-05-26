/**
 * Created on 12-jun-2006
 * CEPA
 * DataCenter 5
 */
package communication.exception;

import java.io.Serializable;

import javax.ejb.EJBException;

public abstract class DatacenterException extends EJBException implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String datacenterMessage;
    private String systemMessage;

    public DatacenterException(String datacenterMessage, String systemMessage) {
        this.datacenterMessage = datacenterMessage;
        this.systemMessage = systemMessage;
    }
    
    public abstract String getName();
    
    public String getKey() {
        return datacenterMessage;
    }
    
    public String getSystemMessage() {
        return systemMessage;
    }
}
