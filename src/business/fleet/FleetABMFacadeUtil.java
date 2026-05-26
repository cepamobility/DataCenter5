/*
 * Generated file - Do not edit!
 */
package business.fleet;

/**
 * Utility class for FleetABMFacade.
 */
public class FleetABMFacadeUtil
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
    * @return Home interface for FleetABMFacade. Lookup using JNDI_NAME
    */
   public static business.fleet.FleetABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.fleet.FleetABMFacadeHome) lookupHome(null, business.fleet.FleetABMFacadeHome.JNDI_NAME, business.fleet.FleetABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for FleetABMFacade. Lookup using JNDI_NAME
    */
   public static business.fleet.FleetABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.fleet.FleetABMFacadeHome) lookupHome(environment, business.fleet.FleetABMFacadeHome.JNDI_NAME, business.fleet.FleetABMFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for FleetABMFacade. Lookup using JNDI_NAME
    */
   public static business.fleet.FleetABMFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.fleet.FleetABMFacadeLocalHome) lookupHome(null, business.fleet.FleetABMFacadeLocalHome.JNDI_NAME, business.fleet.FleetABMFacadeLocalHome.class);
   }

}

