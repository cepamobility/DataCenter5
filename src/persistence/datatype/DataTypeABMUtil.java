/*
 * Generated file - Do not edit!
 */
package persistence.datatype;

/**
 * Utility class for DataTypeABM.
 */
public class DataTypeABMUtil
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
    * @return Home interface for DataTypeABM. Lookup using COMP_NAME
    */
   public static persistence.datatype.DataTypeABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.datatype.DataTypeABMHome) lookupHome(null, persistence.datatype.DataTypeABMHome.COMP_NAME, persistence.datatype.DataTypeABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for DataTypeABM. Lookup using COMP_NAME
    */
   public static persistence.datatype.DataTypeABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.datatype.DataTypeABMHome) lookupHome(environment, persistence.datatype.DataTypeABMHome.COMP_NAME, persistence.datatype.DataTypeABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for DataTypeABM. Lookup using COMP_NAME
    */
   public static persistence.datatype.DataTypeABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.datatype.DataTypeABMLocalHome) lookupHome(null, persistence.datatype.DataTypeABMLocalHome.COMP_NAME, persistence.datatype.DataTypeABMLocalHome.class);
   }

}

