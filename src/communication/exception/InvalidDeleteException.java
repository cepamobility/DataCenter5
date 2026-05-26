/**
 * Created on 14-dic-2006
 * CEPA
 * DataCenter 5
 */
package communication.exception;

import java.io.Serializable;


public class InvalidDeleteException extends DatacenterException implements Serializable {

    public InvalidDeleteException(String datacenterMessage, String systemMessage) {
        super(datacenterMessage, systemMessage);
    }

    private static final long serialVersionUID = 1L;

    public String getName() {
        return "InvalidDeleteException";
    }

}
