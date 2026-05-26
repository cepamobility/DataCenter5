/*
 * Generated file - Do not edit!
 */
package persistence.administration.group;

/**
 * Utility class for GroupABM.
 */
public class GroupABMUtil
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
    * @return Home interface for GroupABM. Lookup using COMP_NAME
    */
   public static persistence.administration.group.GroupABMHome getHome() throws javax.naming.NamingException
   {
         return (persistence.administration.group.GroupABMHome) lookupHome(null, persistence.administration.group.GroupABMHome.COMP_NAME, persistence.administration.group.GroupABMHome.class);
   }

   /**
    * Obtain remote home interface from parameterised initial context
    * @param environment Parameters to use for creating initial context
    * @return Home interface for GroupABM. Lookup using COMP_NAME
    */
   public static persistence.administration.group.GroupABMHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (persistence.administration.group.GroupABMHome) lookupHome(environment, persistence.administration.group.GroupABMHome.COMP_NAME, persistence.administration.group.GroupABMHome.class);
   }

   /**
    * Obtain local home interface from default initial context
    * @return Local home interface for GroupABM. Lookup using COMP_NAME
    */
   public static persistence.administration.group.GroupABMLocalHome getLocalHome() throws javax.naming.NamingException
   {
      return (persistence.administration.group.GroupABMLocalHome) lookupHome(null, persistence.administration.group.GroupABMLocalHome.COMP_NAME, persistence.administration.group.GroupABMLocalHome.class);
   }

}

