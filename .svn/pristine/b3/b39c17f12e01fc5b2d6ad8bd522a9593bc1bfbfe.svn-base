/*
 * Generated file - Do not edit!
 */
package business.vehicle;

/**
 * Utility class for VehicleReportFacade.
 */
public class VehicleReportFacadeUtil
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
    * @return Home interface for VehicleReportFacade. Lookup using JNDI_NAME
    */
   public static business.vehicle.VehicleReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.vehicle.VehicleReportFacadeHome) lookupHome(null, business.vehicle.VehicleReportFacadeHome.JNDI_NAME, business.vehicle.VehicleReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for VehicleReportFacade. Lookup using JNDI_NAME
    */
   public static business.vehicle.VehicleReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.vehicle.VehicleReportFacadeHome) lookupHome(environment, business.vehicle.VehicleReportFacadeHome.JNDI_NAME, business.vehicle.VehicleReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for VehicleReportFacade. Lookup using JNDI_NAME
    */
   public static business.vehicle.VehicleReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.vehicle.VehicleReportFacadeLocalHome) lookupHome(null, business.vehicle.VehicleReportFacadeLocalHome.JNDI_NAME, business.vehicle.VehicleReportFacadeLocalHome.class);
   }

}

