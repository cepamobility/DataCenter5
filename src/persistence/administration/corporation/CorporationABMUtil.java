/*
 * Generated file - Do not edit!
 */
package persistence.administration.corporation;

/**
 * Utility class for CorporationABM.
 */
public class CorporationABMUtil
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
    * @return Home interface for CorporationABM. Lookup using COMP_NAME
    */
   public static persistence.administration.corporation.CorporationABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.corporation.CorporationABMHome) lookupHome(null, persistence.administration.corporation.CorporationABMHome.COMP_NAME, persistence.administration.corporation.CorporationABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CorporationABM. Lookup using COMP_NAME
    */
   public static persistence.administration.corporation.CorporationABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.corporation.CorporationABMHome) lookupHome(environment, persistence.administration.corporation.CorporationABMHome.COMP_NAME, persistence.administration.corporation.CorporationABMHome.class);
   }

}

