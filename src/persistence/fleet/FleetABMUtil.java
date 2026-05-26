/*
 * Generated file - Do not edit!
 */
package persistence.fleet;

/**
 * Utility class for FleetABM.
 */
public class FleetABMUtil
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
    * @return Home interface for FleetABM. Lookup using COMP_NAME
    */
   public static persistence.fleet.FleetABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.fleet.FleetABMHome) lookupHome(null, persistence.fleet.FleetABMHome.COMP_NAME, persistence.fleet.FleetABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for FleetABM. Lookup using COMP_NAME
    */
   public static persistence.fleet.FleetABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.fleet.FleetABMHome) lookupHome(environment, persistence.fleet.FleetABMHome.COMP_NAME, persistence.fleet.FleetABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for FleetABM. Lookup using COMP_NAME
    */
   public static persistence.fleet.FleetABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.fleet.FleetABMLocalHome) lookupHome(null, persistence.fleet.FleetABMLocalHome.COMP_NAME, persistence.fleet.FleetABMLocalHome.class);
   }

}

