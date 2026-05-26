/**
 * Created on 15-feb-2006
 * CEPA
 * DataCenter 5
 */
package business.administration;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.naming.NamingException;

import persistence.administration.RoleReport;
import persistence.administration.RoleReportHome;
import persistence.administration.RoleReportUtil;

import communication.administration.user.UserSessionData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;

/**
 * @ejb.bean name="RoleReportFacade"
 *           display-name="Name for RoleReportFacade"
 *           description="Description for RoleReportFacade"
 *           jndi-name="ejb/RoleReportFacade"
 *           type="Stateless"
 *           view-type="remote"
 * 
 * 
 * @ejb.ejb-ref 
 * 			ejb-name ="RoleReport"
 * 			ref-name = "ejb/RoleReport"
 * 			view-type ="remote"
 * 
 * @jboss.ejb-ref-jndi
 * 			jndi-name = "ejb/RoleReport" 
 * 			ref-name = "RoleReport"
 * 
 * @ejb.util generate="physical"
 *  
 */
public abstract class RoleReportFacadeBean implements SessionBean {
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
     * @ejb.permission role-name = "administrator,usercreate,resetpassword"
	 */
	public void ejbCreate() throws CreateException {
		// TODO Auto-generated method stub

/*		if (userSessionData == null) {
			throw new CreateBeanException("Class business.datatype.DataTypeReportFacade(ejbCreate), session data is empty","generic.error.deslogued");
		}

		this.userSessionData = userSessionData;
*/
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate,resetpassword"
	 * @return Collection of Roles
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 * @throws InvalidDataException
	 */
	public Collection findAll(UserSessionData userSessionData) throws CommunicationProblemException, DeslogedException, InvalidDataException {

		try {
			RoleReportHome roleHome = RoleReportUtil.getHome();
			RoleReport roleReport = roleHome.create();

			return roleReport.findAll(userSessionData);

		} catch (RemoteException remote) {
			throw new CommunicationProblemException("Has ocurred error in business.datatype.DataTypeReportFacade(findAll), can not access DataTypeReport, RemoteException","business.datatype.datatypereportfacade.notaccess.datatyperreport");
		} catch (CreateException create) {
			throw new CommunicationProblemException("Has ocurred error in business.datatype.DataTypeReportFacade(findAll), can not access DataTypeReport, CreateException","business.datatype.datatypereportfacade.notaccess.datatypereport");
		} catch (NamingException naming) {
			throw new CommunicationProblemException("Has ocurred error in business.datatype.DataTypeReportFacade(findAll), can not access DataTypeReport, NamingException","business.datatype.datatypereportfacade.notaccess.datatypereport");
		}

	}
}