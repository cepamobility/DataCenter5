/*
 * Generated file - Do not edit!
 */
package business.administration.user;

/**
 * Utility class for UserABMFacade.
 */
public class UserABMFacadeUtil
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
    * @return Home interface for UserABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.user.UserABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.administration.user.UserABMFacadeHome) lookupHome(null, business.administration.user.UserABMFacadeHome.JNDI_NAME, business.administration.user.UserABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for UserABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.user.UserABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.administration.user.UserABMFacadeHome) lookupHome(environment, business.administration.user.UserABMFacadeHome.JNDI_NAME, business.administration.user.UserABMFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for UserABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.user.UserABMFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.administration.user.UserABMFacadeLocalHome) lookupHome(null, business.administration.user.UserABMFacadeLocalHome.JNDI_NAME, business.administration.user.UserABMFacadeLocalHome.class);
   }

}

