/*
 * Generated file - Do not edit!
 */
package business.event;

/**
 * Utility class for EventReportFacade.
 */
public class EventReportFacadeUtil
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
    * @return Home interface for EventReportFacade. Lookup using JNDI_NAME
    */
   public static business.event.EventReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.event.EventReportFacadeHome) lookupHome(null, business.event.EventReportFacadeHome.JNDI_NAME, business.event.EventReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for EventReportFacade. Lookup using JNDI_NAME
    */
   public static business.event.EventReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.event.EventReportFacadeHome) lookupHome(environment, business.event.EventReportFacadeHome.JNDI_NAME, business.event.EventReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for EventReportFacade. Lookup using JNDI_NAME
    */
   public static business.event.EventReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.event.EventReportFacadeLocalHome) lookupHome(null, business.event.EventReportFacadeLocalHome.JNDI_NAME, business.event.EventReportFacadeLocalHome.class);
   }

}

