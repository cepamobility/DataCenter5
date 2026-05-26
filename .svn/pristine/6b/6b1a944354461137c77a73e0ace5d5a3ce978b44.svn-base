/*
 * Generated file - Do not edit!
 */
package persistence.fleet;

/**
 * Utility class for FleetReport.
 */
public class FleetReportUtil
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
    * @return Home interface for FleetReport. Lookup using COMP_NAME
    */
   public static persistence.fleet.FleetReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.fleet.FleetReportHome) lookupHome(null, persistence.fleet.FleetReportHome.COMP_NAME, persistence.fleet.FleetReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for FleetReport. Lookup using COMP_NAME
    */
   public static persistence.fleet.FleetReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.fleet.FleetReportHome) lookupHome(environment, persistence.fleet.FleetReportHome.COMP_NAME, persistence.fleet.FleetReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for FleetReport. Lookup using COMP_NAME
    */
   public static persistence.fleet.FleetReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.fleet.FleetReportLocalHome) lookupHome(null, persistence.fleet.FleetReportLocalHome.COMP_NAME, persistence.fleet.FleetReportLocalHome.class);
   }

}

