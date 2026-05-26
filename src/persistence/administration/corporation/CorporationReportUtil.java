/*
 * Generated file - Do not edit!
 */
package persistence.administration.corporation;

/**
 * Utility class for CorporationReport.
 */
public class CorporationReportUtil
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
    * @return Home interface for CorporationReport. Lookup using COMP_NAME
    */
   public static persistence.administration.corporation.CorporationReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.corporation.CorporationReportHome) lookupHome(null, persistence.administration.corporation.CorporationReportHome.COMP_NAME, persistence.administration.corporation.CorporationReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CorporationReport. Lookup using COMP_NAME
    */
   public static persistence.administration.corporation.CorporationReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.corporation.CorporationReportHome) lookupHome(environment, persistence.administration.corporation.CorporationReportHome.COMP_NAME, persistence.administration.corporation.CorporationReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for CorporationReport. Lookup using COMP_NAME
    */
   public static persistence.administration.corporation.CorporationReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.administration.corporation.CorporationReportLocalHome) lookupHome(null, persistence.administration.corporation.CorporationReportLocalHome.COMP_NAME, persistence.administration.corporation.CorporationReportLocalHome.class);
   }

}

