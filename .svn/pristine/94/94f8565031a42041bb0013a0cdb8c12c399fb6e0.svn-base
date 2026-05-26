/*
 * Generated file - Do not edit!
 */
package business.administration.corporation;

/**
 * Utility class for CorporationABMFacade.
 */
public class CorporationABMFacadeUtil
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
    * @return Home interface for CorporationABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.corporation.CorporationABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.administration.corporation.CorporationABMFacadeHome) lookupHome(null, business.administration.corporation.CorporationABMFacadeHome.JNDI_NAME, business.administration.corporation.CorporationABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CorporationABMFacade. Lookup using JNDI_NAME
    */
   public static business.administration.corporation.CorporationABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.administration.corporation.CorporationABMFacadeHome) lookupHome(environment, business.administration.corporation.CorporationABMFacadeHome.JNDI_NAME, business.administration.corporation.CorporationABMFacadeHome.class);
   }

}

