/*
 * Generated file - Do not edit!
 */
package business.vehicle;

/**
 * Utility class for VehicleABMFacade.
 */
public class VehicleABMFacadeUtil
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
    * @return Home interface for VehicleABMFacade. Lookup using JNDI_NAME
    */
   public static business.vehicle.VehicleABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.vehicle.VehicleABMFacadeHome) lookupHome(null, business.vehicle.VehicleABMFacadeHome.JNDI_NAME, business.vehicle.VehicleABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for VehicleABMFacade. Lookup using JNDI_NAME
    */
   public static business.vehicle.VehicleABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.vehicle.VehicleABMFacadeHome) lookupHome(environment, business.vehicle.VehicleABMFacadeHome.JNDI_NAME, business.vehicle.VehicleABMFacadeHome.class);
   }

}

