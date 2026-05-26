/*
 * Generated file - Do not edit!
 */
package business.cenapra;

/**
 * Utility class for CENAPRAABMFacade.
 */
public class CENAPRAABMFacadeUtil
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
    * @return Home interface for CENAPRAABMFacade. Lookup using JNDI_NAME
    */
   public static business.cenapra.CENAPRAABMFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.cenapra.CENAPRAABMFacadeHome) lookupHome(null, business.cenapra.CENAPRAABMFacadeHome.JNDI_NAME, business.cenapra.CENAPRAABMFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CENAPRAABMFacade. Lookup using JNDI_NAME
    */
   public static business.cenapra.CENAPRAABMFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.cenapra.CENAPRAABMFacadeHome) lookupHome(environment, business.cenapra.CENAPRAABMFacadeHome.JNDI_NAME, business.cenapra.CENAPRAABMFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for CENAPRAABMFacade. Lookup using JNDI_NAME
    */
   public static business.cenapra.CENAPRAABMFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.cenapra.CENAPRAABMFacadeLocalHome) lookupHome(null, business.cenapra.CENAPRAABMFacadeLocalHome.JNDI_NAME, business.cenapra.CENAPRAABMFacadeLocalHome.class);
   }

}

