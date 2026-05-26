/*
 * Generated file - Do not edit!
 */
package persistence.activity;

/**
 * Utility class for ActivityABM.
 */
public class ActivityABMUtil
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
    * @return Home interface for ActivityABM. Lookup using COMP_NAME
    */
   public static persistence.activity.ActivityABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.activity.ActivityABMHome) lookupHome(null, persistence.activity.ActivityABMHome.COMP_NAME, persistence.activity.ActivityABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for ActivityABM. Lookup using COMP_NAME
    */
   public static persistence.activity.ActivityABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.activity.ActivityABMHome) lookupHome(environment, persistence.activity.ActivityABMHome.COMP_NAME, persistence.activity.ActivityABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for ActivityABM. Lookup using COMP_NAME
    */
   public static persistence.activity.ActivityABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.activity.ActivityABMLocalHome) lookupHome(null, persistence.activity.ActivityABMLocalHome.COMP_NAME, persistence.activity.ActivityABMLocalHome.class);
   }

}

