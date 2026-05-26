/*
 * Generated file - Do not edit!
 */
package business.driver;

/**
 * Utility class for DriverReportFacade.
 */
public class DriverReportFacadeUtil
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
    * @return Home interface for DriverReportFacade. Lookup using JNDI_NAME
    */
   public static business.driver.DriverReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.driver.DriverReportFacadeHome) lookupHome(null, business.driver.DriverReportFacadeHome.JNDI_NAME, business.driver.DriverReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DriverReportFacade. Lookup using JNDI_NAME
    */
   public static business.driver.DriverReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.driver.DriverReportFacadeHome) lookupHome(environment, business.driver.DriverReportFacadeHome.JNDI_NAME, business.driver.DriverReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DriverReportFacade. Lookup using JNDI_NAME
    */
   public static business.driver.DriverReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.driver.DriverReportFacadeLocalHome) lookupHome(null, business.driver.DriverReportFacadeLocalHome.JNDI_NAME, business.driver.DriverReportFacadeLocalHome.class);
   }

}

