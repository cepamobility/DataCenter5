/*
 * Generated file - Do not edit!
 */
package business.cenapra;

/**
 * Utility class for CENAPRAReportFacade.
 */
public class CENAPRAReportFacadeUtil
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
    * @return Home interface for CENAPRAReportFacade. Lookup using JNDI_NAME
    */
   public static business.cenapra.CENAPRAReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.cenapra.CENAPRAReportFacadeHome) lookupHome(null, business.cenapra.CENAPRAReportFacadeHome.JNDI_NAME, business.cenapra.CENAPRAReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CENAPRAReportFacade. Lookup using JNDI_NAME
    */
   public static business.cenapra.CENAPRAReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.cenapra.CENAPRAReportFacadeHome) lookupHome(environment, business.cenapra.CENAPRAReportFacadeHome.JNDI_NAME, business.cenapra.CENAPRAReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for CENAPRAReportFacade. Lookup using JNDI_NAME
    */
   public static business.cenapra.CENAPRAReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.cenapra.CENAPRAReportFacadeLocalHome) lookupHome(null, business.cenapra.CENAPRAReportFacadeLocalHome.JNDI_NAME, business.cenapra.CENAPRAReportFacadeLocalHome.class);
   }

}

