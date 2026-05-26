/*
 * Generated file - Do not edit!
 */
package business.alert;

/**
 * Utility class for AlertReportFacade.
 */
public class AlertReportFacadeUtil
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
    * @return Home interface for AlertReportFacade. Lookup using JNDI_NAME
    */
   public static business.alert.AlertReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.alert.AlertReportFacadeHome) lookupHome(null, business.alert.AlertReportFacadeHome.JNDI_NAME, business.alert.AlertReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for AlertReportFacade. Lookup using JNDI_NAME
    */
   public static business.alert.AlertReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.alert.AlertReportFacadeHome) lookupHome(environment, business.alert.AlertReportFacadeHome.JNDI_NAME, business.alert.AlertReportFacadeHome.class);
   }

}

