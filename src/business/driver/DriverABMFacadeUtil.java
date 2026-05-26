/*
 * Generated file - Do not edit!
 */
package business.driver;

/**
 * Utility class for DriverABMFacade.
 */
public class DriverABMFacadeUtil
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
    * @return Home interface for DriverABMFacade. Lookup using JNDI_NAME
    */
   public static business.driver.DriverABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.driver.DriverABMFacadeHome) lookupHome(null, business.driver.DriverABMFacadeHome.JNDI_NAME, business.driver.DriverABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DriverABMFacade. Lookup using JNDI_NAME
    */
   public static business.driver.DriverABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.driver.DriverABMFacadeHome) lookupHome(environment, business.driver.DriverABMFacadeHome.JNDI_NAME, business.driver.DriverABMFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DriverABMFacade. Lookup using JNDI_NAME
    */
   public static business.driver.DriverABMFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.driver.DriverABMFacadeLocalHome) lookupHome(null, business.driver.DriverABMFacadeLocalHome.JNDI_NAME, business.driver.DriverABMFacadeLocalHome.class);
   }

}

