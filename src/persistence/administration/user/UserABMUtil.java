/*
 * Generated file - Do not edit!
 */
package persistence.administration.user;

/**
 * Utility class for UserABM.
 */
public class UserABMUtil
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
    * @return Home interface for UserABM. Lookup using COMP_NAME
    */
   public static persistence.administration.user.UserABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.user.UserABMHome) lookupHome(null, persistence.administration.user.UserABMHome.COMP_NAME, persistence.administration.user.UserABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for UserABM. Lookup using COMP_NAME
    */
   public static persistence.administration.user.UserABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.user.UserABMHome) lookupHome(environment, persistence.administration.user.UserABMHome.COMP_NAME, persistence.administration.user.UserABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for UserABM. Lookup using COMP_NAME
    */
   public static persistence.administration.user.UserABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.administration.user.UserABMLocalHome) lookupHome(null, persistence.administration.user.UserABMLocalHome.COMP_NAME, persistence.administration.user.UserABMLocalHome.class);
   }

}

