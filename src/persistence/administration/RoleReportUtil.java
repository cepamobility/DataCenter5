/*
 * Generated file - Do not edit!
 */
package persistence.administration;

/**
 * Utility class for RoleReport.
 */
public class RoleReportUtil
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
    * @return Home interface for RoleReport. Lookup using COMP_NAME
    */
   public static persistence.administration.RoleReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.RoleReportHome) lookupHome(null, persistence.administration.RoleReportHome.COMP_NAME, persistence.administration.RoleReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for RoleReport. Lookup using COMP_NAME
    */
   public static persistence.administration.RoleReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.RoleReportHome) lookupHome(environment, persistence.administration.RoleReportHome.COMP_NAME, persistence.administration.RoleReportHome.class);
   }

}

