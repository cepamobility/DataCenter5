/**
 * Created on 15-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.administration.tables.Role;
import persistence.hibernate.HibernateUtil;

import communication.administration.RoleData;
import communication.administration.user.PermissionData;
import communication.administration.user.UserSessionData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;

/**
 * @ejb.bean name="RoleReport"
 *           display-name="Name for RoleReport"
 *           description="Description for RoleReport"
 *           jndi-name="ejb/RoleReport"
 *           type="Stateless"
 *           view-type="remote"
 */
public abstract class RoleReportBean implements SessionBean {

    /**
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,usercreate,userpermission,resetpassword"
     * @throws CreateException
     */
     public void ejbCreate() throws CreateException {}
        
     /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate,userpermission"
     * @param userRequest
     * @return
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public Collection findAll(UserSessionData userRequest) throws DeslogedException, CommunicationProblemException {

		if (userRequest == null) {
			throw new DeslogedException("Class persistence.administration.tables.DataTypeReport(findAll), session data is empty","generic.error.deslogued");
		}
		List result = new LinkedList();
		Session session = null;
		RoleData roleData;
		Iterator it;
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery("select role " +
					"from persistence.administration.tables.Role role  ");					
			List dtColl = query.list();
			Iterator iter = dtColl.iterator();
			while (iter.hasNext()) {
				Role role = (Role) iter.next();
				roleData = role.getData();

				// Elimino los permisos de ese rol que sean de otras corporaciones diferentes
				// a las que pertenece el usuario:
				it = roleData.getPermissions().iterator();
				while (it.hasNext()){
					PermissionData permission = (PermissionData)it.next();
					if (permission.getCorporationId() != null &&
						!permission.getCorporationId().equals(new Integer(0)) &&
						!permission.getCorporationId().equals(userRequest.getFilter().getCorporationId())){
						it.remove();
//						roleData.getPermissions().remove(permission);
					}
				}
				result.add(roleData);
			}
			session.close();
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new CommunicationProblemException("Has ocurred error in persistence.administration.RoleReport(findAll), HibernateException","generic.error.hibernate");
		} 
		return result;
    }
    
    /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate,userpermission,resetpassword"
     * @param role
     * @param userRequest
     * @return
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public RoleData findRoleByPrimaryKey(String roleName, UserSessionData userRequest) throws DeslogedException, CommunicationProblemException {

		if (userRequest == null) {
			throw new DeslogedException("Class persistence.administration.tables.DataTypeReport(findAll), session data is empty","generic.error.deslogued");
		}
		
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
			Role role = (Role)session.load(Role.class,roleName);
			RoleData roleData = role.getData(); 

			// Elimino los permisos de ese rol que sean de otras corporaciones diferentes
			// a las que pertenece el usuario:
			Iterator it = roleData.getPermissions().iterator();
			while (it.hasNext()){
				PermissionData permission = (PermissionData)it.next();
				// Si es null es que no se actualizo la corporacion y por lo tanto
				// es para todas las corporaciones, igual que el 0.
				if (permission.getCorporationId() != null &&
					!permission.getCorporationId().equals(new Integer(0)) &&
					!permission.getCorporationId().equals(userRequest.getFilter().getCorporationId())){
					// roleData.getPermissions().remove(permission);
					it.remove();
				}
			}
			return roleData;
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new CommunicationProblemException("Has ocurred error in persistence.administration.RoleReport(findAll), HibernateException","generic.error.hibernate");
		} 
    }
}