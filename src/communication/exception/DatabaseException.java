package communication.exception;

import java.io.Serializable;


public class DatabaseException extends DatacenterException implements Serializable  {

    private static final long serialVersionUID = 1L;
    
    
	public DatabaseException(String datacenterMessage, String systemMessage) {
        super(datacenterMessage,systemMessage);
    }

    public String getName() {
        return "DatabaseException";
    }
    
    
}
