/*
 * Generated file - Do not edit!
 */
package persistence.report;

/**
 * Utility class for BIReport.
 */
public class BIReportUtil
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
    * @return Home interface for BIReport. Lookup using COMP_NAME
    */
   public static persistence.report.BIReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.report.BIReportHome) lookupHome(null, persistence.report.BIReportHome.COMP_NAME, persistence.report.BIReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for BIReport. Lookup using COMP_NAME
    */
   public static persistence.report.BIReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.report.BIReportHome) lookupHome(environment, persistence.report.BIReportHome.COMP_NAME, persistence.report.BIReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for BIReport. Lookup using COMP_NAME
    */
   public static persistence.report.BIReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.report.BIReportLocalHome) lookupHome(null, persistence.report.BIReportLocalHome.COMP_NAME, persistence.report.BIReportLocalHome.class);
   }

}

