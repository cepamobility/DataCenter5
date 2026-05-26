package business.security;

import java.lang.reflect.Method;
import javax.ejb.EJBContext;
                
import org.jboss.security.SecurityProxy;

public class SPUserReport implements SecurityProxy
{
    Method isFirstAccess;
    
    public void init(Class beanHome, Class beanRemote,
                     Object securityMgr)
        throws InstantiationException
    {
        // Get the echo method for equality testing in invoke
        try {
            Class[] params = {String.class};
            System.out.println("Proxy init 3 !!");
           // isFirstAccess = beanRemote.getDeclaredMethod("isFirstAccess", params);
        } catch(Exception e) {
            String msg = "Failed to finde an echo(String) method";
            throw new InstantiationException(msg);
        }
    }
    
    public void setEJBContext(EJBContext ctx)
    {
        System.out.println("setEJBContext, ctx="+ctx);
    }
    
    public void invokeHome(Method m, Object[] args)
        throws SecurityException
    {
        // We don't validate access to home methods
    	System.out.println("Proxy invokeHome !!");
    }

    public void invoke(Method m, Object[] args, Object bean)
        throws SecurityException
    {
    	System.out.println("Proxy invoke !!");
        // Check for the echo method
        /*if (!m.equals(isFirstAccess)) {
            // Validate that the msg arg is not 4 letter word
             throw new SecurityException("!!isFirstAccess");
        }*/
    	
        // We are not responsible for doing the invoke
    }

	@Override
	public void init(Class beanHome, Class beanRemote, Class arg2, Class arg3, Object securityMgr)
			throws InstantiationException {
		// TODO Auto-generated method stub
		 // Get the echo method for equality testing in invoke
        try {
            Class[] params = {String.class};
           // isFirstAccess = beanRemote.getDeclaredMethod("isFirstAccess", params);
            System.out.println("Proxy init +3 !!");
        } catch(Exception e) {
            String msg = "Failed to finde an echo(String) method";
            throw new InstantiationException(msg);
        }
	}
}
