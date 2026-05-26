/*
 * Generated file - Do not edit!
 */
package persistence.vehicle;

/**
 * Utility class for VehicleReport.
 */
public class VehicleReportUtil
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
    * @return Home interface for VehicleReport. Lookup using COMP_NAME
    */
   public static persistence.vehicle.VehicleReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.vehicle.VehicleReportHome) lookupHome(null, persistence.vehicle.VehicleReportHome.COMP_NAME, persistence.vehicle.VehicleReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for VehicleReport. Lookup using COMP_NAME
    */
   public static persistence.vehicle.VehicleReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.vehicle.VehicleReportHome) lookupHome(environment, persistence.vehicle.VehicleReportHome.COMP_NAME, persistence.vehicle.VehicleReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for VehicleReport. Lookup using COMP_NAME
    */
   public static persistence.vehicle.VehicleReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.vehicle.VehicleReportLocalHome) lookupHome(null, persistence.vehicle.VehicleReportLocalHome.COMP_NAME, persistence.vehicle.VehicleReportLocalHome.class);
   }

}

