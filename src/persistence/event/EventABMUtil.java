/*
 * Generated file - Do not edit!
 */
package persistence.event;

/**
 * Utility class for EventABM.
 */
public class EventABMUtil
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
    * @return Home interface for EventABM. Lookup using COMP_NAME
    */
   public static persistence.event.EventABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.event.EventABMHome) lookupHome(null, persistence.event.EventABMHome.COMP_NAME, persistence.event.EventABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for EventABM. Lookup using COMP_NAME
    */
   public static persistence.event.EventABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.event.EventABMHome) lookupHome(environment, persistence.event.EventABMHome.COMP_NAME, persistence.event.EventABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for EventABM. Lookup using COMP_NAME
    */
   public static persistence.event.EventABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.event.EventABMLocalHome) lookupHome(null, persistence.event.EventABMLocalHome.COMP_NAME, persistence.event.EventABMLocalHome.class);
   }

}

