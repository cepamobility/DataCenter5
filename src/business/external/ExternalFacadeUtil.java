/*
 * Generated file - Do not edit!
 */
package business.external;

/**
 * Utility class for ExternalFacade.
 * @author fcarriquiry
 */
public class ExternalFacadeUtil
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
    * @return Home interface for ExternalFacade. Lookup using JNDI_NAME
    */
   public static business.external.ExternalFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.external.ExternalFacadeHome) lookupHome(null, business.external.ExternalFacadeHome.JNDI_NAME, business.external.ExternalFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for ExternalFacade. Lookup using JNDI_NAME
    */
   public static business.external.ExternalFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.external.ExternalFacadeHome) lookupHome(environment, business.external.ExternalFacadeHome.JNDI_NAME, business.external.ExternalFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for ExternalFacade. Lookup using JNDI_NAME
    */
   public static business.external.ExternalFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.external.ExternalFacadeLocalHome) lookupHome(null, business.external.ExternalFacadeLocalHome.JNDI_NAME, business.external.ExternalFacadeLocalHome.class);
   }

}

