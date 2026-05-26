/*
 * Generated file - Do not edit!
 */
package business.administration.corporation;

/**
 * Utility class for CorporationReportFacade.
 */
public class CorporationReportFacadeUtil
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
    * @return Home interface for CorporationReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.corporation.CorporationReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.administration.corporation.CorporationReportFacadeHome) lookupHome(null, business.administration.corporation.CorporationReportFacadeHome.JNDI_NAME, business.administration.corporation.CorporationReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for CorporationReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.corporation.CorporationReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.administration.corporation.CorporationReportFacadeHome) lookupHome(environment, business.administration.corporation.CorporationReportFacadeHome.JNDI_NAME, business.administration.corporation.CorporationReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for CorporationReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.corporation.CorporationReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.administration.corporation.CorporationReportFacadeLocalHome) lookupHome(null, business.administration.corporation.CorporationReportFacadeLocalHome.JNDI_NAME, business.administration.corporation.CorporationReportFacadeLocalHome.class);
   }

}

