/*
 * Generated file - Do not edit!
 */
package persistence.activity;

/**
 * Utility class for ActivityReport.
 */
public class ActivityReportUtil
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
    * @return Home interface for ActivityReport. Lookup using COMP_NAME
    */
   public static persistence.activity.ActivityReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.activity.ActivityReportHome) lookupHome(null, persistence.activity.ActivityReportHome.COMP_NAME, persistence.activity.ActivityReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for ActivityReport. Lookup using COMP_NAME
    */
   public static persistence.activity.ActivityReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.activity.ActivityReportHome) lookupHome(environment, persistence.activity.ActivityReportHome.COMP_NAME, persistence.activity.ActivityReportHome.class);
   }

}

