/*
 * Generated file - Do not edit!
 */
package persistence.alert;

/**
 * Utility class for AlertABM.
 */
public class AlertABMUtil
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
    * @return Home interface for AlertABM. Lookup using COMP_NAME
    */
   public static persistence.alert.AlertABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.alert.AlertABMHome) lookupHome(null, persistence.alert.AlertABMHome.COMP_NAME, persistence.alert.AlertABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for AlertABM. Lookup using COMP_NAME
    */
   public static persistence.alert.AlertABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.alert.AlertABMHome) lookupHome(environment, persistence.alert.AlertABMHome.COMP_NAME, persistence.alert.AlertABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for AlertABM. Lookup using COMP_NAME
    */
   public static persistence.alert.AlertABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.alert.AlertABMLocalHome) lookupHome(null, persistence.alert.AlertABMLocalHome.COMP_NAME, persistence.alert.AlertABMLocalHome.class);
   }

}

