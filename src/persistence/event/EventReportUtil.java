/*
 * Generated file - Do not edit!
 */
package persistence.event;

/**
 * Utility class for EventReport.
 */
public class EventReportUtil
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
    * @return Home interface for EventReport. Lookup using COMP_NAME
    */
   public static persistence.event.EventReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.event.EventReportHome) lookupHome(null, persistence.event.EventReportHome.COMP_NAME, persistence.event.EventReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for EventReport. Lookup using COMP_NAME
    */
   public static persistence.event.EventReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.event.EventReportHome) lookupHome(environment, persistence.event.EventReportHome.COMP_NAME, persistence.event.EventReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for EventReport. Lookup using COMP_NAME
    */
   public static persistence.event.EventReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.event.EventReportLocalHome) lookupHome(null, persistence.event.EventReportLocalHome.COMP_NAME, persistence.event.EventReportLocalHome.class);
   }

}

