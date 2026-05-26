/*
 * Generated file - Do not edit!
 */
package business.language;

/**
 * Utility class for LanguageReportFacade.
 */
public class LanguageReportFacadeUtil
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
    * @return Home interface for LanguageReportFacade. Lookup using JNDI_NAME
    */
   public static business.language.LanguageReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.language.LanguageReportFacadeHome) lookupHome(null, business.language.LanguageReportFacadeHome.JNDI_NAME, business.language.LanguageReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for LanguageReportFacade. Lookup using JNDI_NAME
    */
   public static business.language.LanguageReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.language.LanguageReportFacadeHome) lookupHome(environment, business.language.LanguageReportFacadeHome.JNDI_NAME, business.language.LanguageReportFacadeHome.class);
   }

}

