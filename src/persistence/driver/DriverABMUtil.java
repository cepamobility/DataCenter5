/*
 * Generated file - Do not edit!
 */
package persistence.driver;

/**
 * Utility class for DriverABM.
 */
public class DriverABMUtil
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
    * @return Home interface for DriverABM. Lookup using COMP_NAME
    */
   public static persistence.driver.DriverABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.driver.DriverABMHome) lookupHome(null, persistence.driver.DriverABMHome.COMP_NAME, persistence.driver.DriverABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DriverABM. Lookup using COMP_NAME
    */
   public static persistence.driver.DriverABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.driver.DriverABMHome) lookupHome(environment, persistence.driver.DriverABMHome.COMP_NAME, persistence.driver.DriverABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DriverABM. Lookup using COMP_NAME
    */
   public static persistence.driver.DriverABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.driver.DriverABMLocalHome) lookupHome(null, persistence.driver.DriverABMLocalHome.COMP_NAME, persistence.driver.DriverABMLocalHome.class);
   }

}

