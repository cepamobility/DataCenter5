/*
 * Generated file - Do not edit!
 */
package business.report;

/**
 * Utility class for BIReportFacade.
 */
public class BIReportFacadeUtil
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
    * @return Home interface for BIReportFacade. Lookup using JNDI_NAME
    */
   public static business.report.BIReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.report.BIReportFacadeHome) lookupHome(null, business.report.BIReportFacadeHome.JNDI_NAME, business.report.BIReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for BIReportFacade. Lookup using JNDI_NAME
    */
   public static business.report.BIReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.report.BIReportFacadeHome) lookupHome(environment, business.report.BIReportFacadeHome.JNDI_NAME, business.report.BIReportFacadeHome.class);
   }

}

