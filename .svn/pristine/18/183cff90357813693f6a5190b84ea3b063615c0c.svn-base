/*
 * Generated file - Do not edit!
 */
package business.administration.group;

/**
 * Utility class for GroupABMFacade.
 */
public class GroupABMFacadeUtil
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
    * @return Home interface for GroupABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.group.GroupABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.administration.group.GroupABMFacadeHome) lookupHome(null, business.administration.group.GroupABMFacadeHome.JNDI_NAME, business.administration.group.GroupABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for GroupABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.group.GroupABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.administration.group.GroupABMFacadeHome) lookupHome(environment, business.administration.group.GroupABMFacadeHome.JNDI_NAME, business.administration.group.GroupABMFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for GroupABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.group.GroupABMFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.administration.group.GroupABMFacadeLocalHome) lookupHome(null, business.administration.group.GroupABMFacadeLocalHome.JNDI_NAME, business.administration.group.GroupABMFacadeLocalHome.class);
   }

}

