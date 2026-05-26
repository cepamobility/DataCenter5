/*
 * Generated file - Do not edit!
 */
package persistence.datatype;

/**
 * Utility class for DataTypeReport.
 */
public class DataTypeReportUtil
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
    * @return Home interface for DataTypeReport. Lookup using COMP_NAME
    */
   public static persistence.datatype.DataTypeReportHome getHome() throws javax.naming.NamingException
   {
         return (persistence.datatype.DataTypeReportHome) lookupHome(null, persistence.datatype.DataTypeReportHome.COMP_NAME, persistence.datatype.DataTypeReportHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DataTypeReport. Lookup using COMP_NAME
    */
   public static persistence.datatype.DataTypeReportHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.datatype.DataTypeReportHome) lookupHome(environment, persistence.datatype.DataTypeReportHome.COMP_NAME, persistence.datatype.DataTypeReportHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DataTypeReport. Lookup using COMP_NAME
    */
   public static persistence.datatype.DataTypeReportLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.datatype.DataTypeReportLocalHome) lookupHome(null, persistence.datatype.DataTypeReportLocalHome.COMP_NAME, persistence.datatype.DataTypeReportLocalHome.class);
   }

}

