/*
 * Generated file - Do not edit!
 */
package persistence.vehicle;

/**
 * Utility class for VehicleABM.
 */
public class VehicleABMUtil
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
    * @return Home interface for VehicleABM. Lookup using COMP_NAME
    */
   public static persistence.vehicle.VehicleABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.vehicle.VehicleABMHome) lookupHome(null, persistence.vehicle.VehicleABMHome.COMP_NAME, persistence.vehicle.VehicleABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for VehicleABM. Lookup using COMP_NAME
    */
   public static persistence.vehicle.VehicleABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.vehicle.VehicleABMHome) lookupHome(environment, persistence.vehicle.VehicleABMHome.COMP_NAME, persistence.vehicle.VehicleABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for VehicleABM. Lookup using COMP_NAME
    */
   public static persistence.vehicle.VehicleABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.vehicle.VehicleABMLocalHome) lookupHome(null, persistence.vehicle.VehicleABMLocalHome.COMP_NAME, persistence.vehicle.VehicleABMLocalHome.class);
   }

}

