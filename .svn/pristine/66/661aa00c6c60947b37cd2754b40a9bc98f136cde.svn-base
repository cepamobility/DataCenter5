/*
 * Generated file - Do not edit!
 */
package persistence.administration.group;

/**
 * Utility class for GroupReport.
 */
public class GroupReportUtil
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
    * @return Home interface for GroupReport. Lookup using COMP_NAME
    */
   public static persistence.administration.group.GroupReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.group.GroupReportHome) lookupHome(null, persistence.administration.group.GroupReportHome.COMP_NAME, persistence.administration.group.GroupReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for GroupReport. Lookup using COMP_NAME
    */
   public static persistence.administration.group.GroupReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.group.GroupReportHome) lookupHome(environment, persistence.administration.group.GroupReportHome.COMP_NAME, persistence.administration.group.GroupReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for GroupReport. Lookup using COMP_NAME
    */
   public static persistence.administration.group.GroupReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.administration.group.GroupReportLocalHome) lookupHome(null, persistence.administration.group.GroupReportLocalHome.COMP_NAME, persistence.administration.group.GroupReportLocalHome.class);
   }

}

