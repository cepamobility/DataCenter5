/*
 * Generated file - Do not edit!
 */
package persistence.administration.user;

/**
 * Utility class for UserReport.
 * @generated 
 */
public class UserReportUtil
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
    * @return Home interface for UserReport. Lookup using COMP_NAME
    */
   public static persistence.administration.user.UserReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.user.UserReportHome) lookupHome(null, persistence.administration.user.UserReportHome.COMP_NAME, persistence.administration.user.UserReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for UserReport. Lookup using COMP_NAME
    */
   public static persistence.administration.user.UserReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.user.UserReportHome) lookupHome(environment, persistence.administration.user.UserReportHome.COMP_NAME, persistence.administration.user.UserReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for UserReport. Lookup using COMP_NAME
    */
   public static persistence.administration.user.UserReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.administration.user.UserReportLocalHome) lookupHome(null, persistence.administration.user.UserReportLocalHome.COMP_NAME, persistence.administration.user.UserReportLocalHome.class);
   }

}

