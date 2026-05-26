/*
 * Generated file - Do not edit!
 */
package persistence.cenapra;

/**
 * Utility class for CENAPRAReport.
 */
public class CENAPRAReportUtil
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
    * @return Home interface for CENAPRAReport. Lookup using COMP_NAME
    */
   public static persistence.cenapra.CENAPRAReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.cenapra.CENAPRAReportHome) lookupHome(null, persistence.cenapra.CENAPRAReportHome.COMP_NAME, persistence.cenapra.CENAPRAReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CENAPRAReport. Lookup using COMP_NAME
    */
   public static persistence.cenapra.CENAPRAReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.cenapra.CENAPRAReportHome) lookupHome(environment, persistence.cenapra.CENAPRAReportHome.COMP_NAME, persistence.cenapra.CENAPRAReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for CENAPRAReport. Lookup using COMP_NAME
    */
   public static persistence.cenapra.CENAPRAReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.cenapra.CENAPRAReportLocalHome) lookupHome(null, persistence.cenapra.CENAPRAReportLocalHome.COMP_NAME, persistence.cenapra.CENAPRAReportLocalHome.class);
   }

}

