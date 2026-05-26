/*
 * Generated file - Do not edit!
 */
package persistence.language;

/**
 * Utility class for LanguageABM.
 */
public class LanguageABMUtil
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
    * @return Home interface for LanguageABM. Lookup using COMP_NAME
    */
   public static persistence.language.LanguageABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.language.LanguageABMHome) lookupHome(null, persistence.language.LanguageABMHome.COMP_NAME, persistence.language.LanguageABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for LanguageABM. Lookup using COMP_NAME
    */
   public static persistence.language.LanguageABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.language.LanguageABMHome) lookupHome(environment, persistence.language.LanguageABMHome.COMP_NAME, persistence.language.LanguageABMHome.class);
   }

}

