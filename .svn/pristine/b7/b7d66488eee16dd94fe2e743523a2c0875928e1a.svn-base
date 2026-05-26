/*
 * Generated file - Do not edit!
 */
package persistence.alert;

/**
 * Utility class for AlertReport.
 */
public class AlertReportUtil
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
    * @return Home interface for AlertReport. Lookup using COMP_NAME
    */
   public static persistence.alert.AlertReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.alert.AlertReportHome) lookupHome(null, persistence.alert.AlertReportHome.COMP_NAME, persistence.alert.AlertReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for AlertReport. Lookup using COMP_NAME
    */
   public static persistence.alert.AlertReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.alert.AlertReportHome) lookupHome(environment, persistence.alert.AlertReportHome.COMP_NAME, persistence.alert.AlertReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for AlertReport. Lookup using COMP_NAME
    */
   public static persistence.alert.AlertReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.alert.AlertReportLocalHome) lookupHome(null, persistence.alert.AlertReportLocalHome.COMP_NAME, persistence.alert.AlertReportLocalHome.class);
   }

}

