/*
 * Generated file - Do not edit!
 */
package business.administration.group;

/**
 * Utility class for GroupReportFacade.
 */
public class GroupReportFacadeUtil
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
    * @return Home interface for GroupReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.group.GroupReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.administration.group.GroupReportFacadeHome) lookupHome(null, business.administration.group.GroupReportFacadeHome.JNDI_NAME, business.administration.group.GroupReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for GroupReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.group.GroupReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.administration.group.GroupReportFacadeHome) lookupHome(environment, business.administration.group.GroupReportFacadeHome.JNDI_NAME, business.administration.group.GroupReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for GroupReportFacade. Lookup using JNDI_NAME
    */
   public static business.administration.group.GroupReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.administration.group.GroupReportFacadeLocalHome) lookupHome(null, business.administration.group.GroupReportFacadeLocalHome.JNDI_NAME, business.administration.group.GroupReportFacadeLocalHome.class);
   }

}

