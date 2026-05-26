/*
 * Generated file - Do not edit!
 */
package persistence.language;

/**
 * Utility class for LangugeReport.
 */
public class LangugeReportUtil
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
    * @return Home interface for LangugeReport. Lookup using COMP_NAME
    */
   public static persistence.language.LangugeReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.language.LangugeReportHome) lookupHome(null, persistence.language.LangugeReportHome.COMP_NAME, persistence.language.LangugeReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for LangugeReport. Lookup using COMP_NAME
    */
   public static persistence.language.LangugeReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.language.LangugeReportHome) lookupHome(environment, persistence.language.LangugeReportHome.COMP_NAME, persistence.language.LangugeReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for LangugeReport. Lookup using COMP_NAME
    */
   public static persistence.language.LangugeReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.language.LangugeReportLocalHome) lookupHome(null, persistence.language.LangugeReportLocalHome.COMP_NAME, persistence.language.LangugeReportLocalHome.class);
   }

}

