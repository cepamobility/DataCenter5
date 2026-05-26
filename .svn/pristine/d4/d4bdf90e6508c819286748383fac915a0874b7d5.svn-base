/*
 * Generated file - Do not edit!
 */
package persistence.driver;

/**
 * Utility class for DriverReport.
 */
public class DriverReportUtil
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
    * @return Home interface for DriverReport. Lookup using COMP_NAME
    */
   public static persistence.driver.DriverReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.driver.DriverReportHome) lookupHome(null, persistence.driver.DriverReportHome.COMP_NAME, persistence.driver.DriverReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DriverReport. Lookup using COMP_NAME
    */
   public static persistence.driver.DriverReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.driver.DriverReportHome) lookupHome(environment, persistence.driver.DriverReportHome.COMP_NAME, persistence.driver.DriverReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DriverReport. Lookup using COMP_NAME
    */
   public static persistence.driver.DriverReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.driver.DriverReportLocalHome) lookupHome(null, persistence.driver.DriverReportLocalHome.COMP_NAME, persistence.driver.DriverReportLocalHome.class);
   }

}

