/*
 * Generated file - Do not edit!
 */
package business.administration.user;

/**
 * Utility class for UserReportFacade.
 */
public class UserReportFacadeUtil
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
    * @return Home interface for UserReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.user.UserReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.administration.user.UserReportFacadeHome) lookupHome(null, business.administration.user.UserReportFacadeHome.JNDI_NAME, business.administration.user.UserReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for UserReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.user.UserReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.administration.user.UserReportFacadeHome) lookupHome(environment, business.administration.user.UserReportFacadeHome.JNDI_NAME, business.administration.user.UserReportFacadeHome.class);
   }

}

