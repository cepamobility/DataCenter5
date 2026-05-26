/*
 * Generated file - Do not edit!
 */
package business.datatype;

/**
 * Utility class for DataTypeReportFacade.
 */
public class DataTypeReportFacadeUtil
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
    * @return Home interface for DataTypeReportFacade. Lookup using JNDI_NAME
    */
   public static business.datatype.DataTypeReportFacadeHome getHome() throws javax.naming.NamingException
   {
         return (business.datatype.DataTypeReportFacadeHome) lookupHome(null, business.datatype.DataTypeReportFacadeHome.JNDI_NAME, business.datatype.DataTypeReportFacadeHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DataTypeReportFacade. Lookup using JNDI_NAME
    */
   public static business.datatype.DataTypeReportFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (business.datatype.DataTypeReportFacadeHome) lookupHome(environment, business.datatype.DataTypeReportFacadeHome.JNDI_NAME, business.datatype.DataTypeReportFacadeHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DataTypeReportFacade. Lookup using JNDI_NAME
    */
   public static business.datatype.DataTypeReportFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (business.datatype.DataTypeReportFacadeLocalHome) lookupHome(null, business.datatype.DataTypeReportFacadeLocalHome.JNDI_NAME, business.datatype.DataTypeReportFacadeLocalHome.class);
   }

}

