/*
 * Generated file - Do not edit!
 */
package business.event;

/**
 * Utility class for EventABMFacade.
 * @author fcarriquiry
 */
public class EventABMFacadeUtil
{

   private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
      // Obtain initial context
      javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
      try {
         Object objRef = initialContext.lookup(jndiName);
         // only narrow if necessary
         if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
         else
            return objRef;
      } finally {
         initialContext.close();
      }
   }

   // Home interface lookup methods

   /**
    * Obtain remote home interface from default initial context
    * @return Home interface for EventABMFacade. Lookup using JNDI_NAME
    */
   public static business.event.EventABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.event.EventABMFacadeHome) lookupHome(null, business.event.EventABMFacadeHome.JNDI_NAME, business.event.EventABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for EventABMFacade. Lookup using JNDI_NAME
    */
   public static business.event.EventABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.event.EventABMFacadeHome) lookupHome(environment, business.event.EventABMFacadeHome.JNDI_NAME, business.event.EventABMFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for EventABMFacade. Lookup using JNDI_NAME
    */
   public static business.event.EventABMFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.event.EventABMFacadeLocalHome) lookupHome(null, business.event.EventABMFacadeLocalHome.JNDI_NAME, business.event.EventABMFacadeLocalHome.class);
   }

}

