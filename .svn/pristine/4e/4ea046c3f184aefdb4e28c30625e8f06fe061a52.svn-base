/*
 * Generated file - Do not edit!
 */
package business.activity;

/**
 * Utility class for ActivityABMFacade.
 */
public class ActivityABMFacadeUtil
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
    * @return Home interface for ActivityABMFacade. Lookup using JNDI_NAME
    */
   public static business.activity.ActivityABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.activity.ActivityABMFacadeHome) lookupHome(null, business.activity.ActivityABMFacadeHome.JNDI_NAME, business.activity.ActivityABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for ActivityABMFacade. Lookup using JNDI_NAME
    */
   public static business.activity.ActivityABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.activity.ActivityABMFacadeHome) lookupHome(environment, business.activity.ActivityABMFacadeHome.JNDI_NAME, business.activity.ActivityABMFacadeHome.class);
   }

}

