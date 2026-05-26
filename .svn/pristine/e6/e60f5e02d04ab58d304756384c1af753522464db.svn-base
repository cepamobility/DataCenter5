/**
 * Created on 12-jun-2006
 * CEPA
 * DataCenter 5
 */
package business.util;

import org.jboss.logging.Logger;

import communication.administration.user.UserSessionData;

public class ExceptionHandler {

    private String className;
    private Logger logger;
   
    
    public ExceptionHandler(Class bussinessClass) {
        logger=Logger.getLogger(bussinessClass);
        className = bussinessClass.getName();
    }

    public void handleException(String function, Exception e,UserSessionData userSessionData, Object[] parameters) throws Exception {
        logger.error("--------------------------------------------------------");
        logger.error("User: "+ ((userSessionData != null)?userSessionData.getFilter().getLoginName():"") );
        logger.error("Corp: "+ ((userSessionData != null)?String.valueOf(userSessionData.getFilter().getCorporationId()):"") );
        logger.error("Class: "+className);
        logger.error("Func: "+function);
        logger.error("Parameters: ");
        if (parameters != null){	        	
	        for(int i = 0; i < parameters.length; i++) {
	        	if (parameters[i] != null){
	        		logger.error("|----"+String.valueOf(parameters[i]));
	        	}
	        }
        }else{
        	logger.error("|----Without parameters.");
        }
        Throwable thw = (Throwable)e;
        while(thw.getCause() != null) { thw = thw.getCause(); };
        logger.error("Exc: "+thw.getClass());
        logger.error("Exc msg: "+thw.getMessage());
        logger.error("--------------------------------------------------------");        
    }

}
