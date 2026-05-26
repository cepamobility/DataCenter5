/*
 * Generated file - Do not edit!
 */
package persistence.external;

/**
 * Utility class for External.
 */
public class ExternalUtil
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
    * @return Home interface for External. Lookup using COMP_NAME
    */
   public static persistence.external.ExternalHome getHome() throws javax.naming.NamingException
   {
         return (persistence.external.ExternalHome) lookupHome(null, persistence.external.ExternalHome.COMP_NAME, persistence.external.ExternalHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for External. Lookup using COMP_NAME
    */
   public static persistence.external.ExternalHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.external.ExternalHome) lookupHome(environment, persistence.external.ExternalHome.COMP_NAME, persistence.external.ExternalHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for External. Lookup using COMP_NAME
    */
   public static persistence.external.ExternalLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.external.ExternalLocalHome) lookupHome(null, persistence.external.ExternalLocalHome.COMP_NAME, persistence.external.ExternalLocalHome.class);
   }

}

