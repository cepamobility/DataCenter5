/*
 * Generated file - Do not edit!
 */
package business.fleet;

/**
 * Utility class for FleetReportFacade.
 */
public class FleetReportFacadeUtil
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
    * @return Home interface for FleetReportFacade. Lookup using JNDI_NAME
    */
   public static business.fleet.FleetReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.fleet.FleetReportFacadeHome) lookupHome(null, business.fleet.FleetReportFacadeHome.JNDI_NAME, business.fleet.FleetReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for FleetReportFacade. Lookup using JNDI_NAME
    */
   public static business.fleet.FleetReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.fleet.FleetReportFacadeHome) lookupHome(environment, business.fleet.FleetReportFacadeHome.JNDI_NAME, business.fleet.FleetReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for FleetReportFacade. Lookup using JNDI_NAME
    */
   public static business.fleet.FleetReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.fleet.FleetReportFacadeLocalHome) lookupHome(null, business.fleet.FleetReportFacadeLocalHome.JNDI_NAME, business.fleet.FleetReportFacadeLocalHome.class);
   }

}

