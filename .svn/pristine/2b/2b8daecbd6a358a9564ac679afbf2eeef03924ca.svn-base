/**
 * Created on 17-abr-2008
 * CEPA
 * DataCenter 5
 */
package business.util;

import org.apache.log4j.Logger;
public class MemoryLog {

    private static MemoryLog log;
    private Logger logger;
    
    public static final int START = 1;
    public static final int END = 2;
    
    private MemoryLog() {
        logger = Logger.getLogger("business.util.MemoryLog");
    }

    public static MemoryLog getInstance() {
        if(log == null) {
            log = new MemoryLog(); 
        }
        return log;
    }
    
    public void log(int action,String function,Class className) {
        String actionStr = (action == START) ? "START" : "END";
        Runtime runtime = Runtime.getRuntime();
        logger.info(actionStr+" Class:"+className+" - "+function+" Free:"+runtime.freeMemory());
    }
}
