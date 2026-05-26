/**
 * Created on 12-jun-2006
 * CEPA
 * DataCenter 5
 */
package persistence.util;

import org.jboss.logging.Logger;

import persistence.log.LoggerFormatting;

import communication.administration.user.UserSessionData;
import communication.exception.DCException;

public class ExceptionHandler {

    private Logger logger;
    private LoggerFormatting logFormatting=new LoggerFormatting();
    
    private String className = ""; 

    public ExceptionHandler(Class logClass) {
        logger=Logger.getLogger(logClass);
        className = logClass.getName();
    }

    public void getException(Exception e,String function,UserSessionData userSessionData,Object[] parameters) throws Exception {
    	e.printStackTrace();
        logger.error("--------------------------------------------------------");
        if(userSessionData != null) {
        	logger.error("User: "+userSessionData.getFilter().getLoginName());
        	logger.error("Corp: "+String.valueOf(userSessionData.getFilter().getCorporationId()));
        }
        logger.error("Class: "+className);
        logger.error("Func: "+function);
        logger.error("Parameters");
        for(int i = 0; i < parameters.length; i++) {
            logger.error("|----"+String.valueOf(parameters[i]));
        }
        Throwable thw = (Throwable)e;
        while(thw.getCause() != null) { thw = thw.getCause(); };
        logger.error("Exc: "+thw.getClass());
        logger.error("Exc msg: "+thw.getMessage());
        logger.error("--------------------------------------------------------");
        throw new DCException(thw,function,userSessionData,parameters,className);
    }
}
