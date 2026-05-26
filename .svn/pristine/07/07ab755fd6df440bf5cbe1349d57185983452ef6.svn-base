
package persistence.datatype;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Query;
import org.hibernate.Session;

import persistence.datatype.tables.AccidentClasificationByCorporation;
import persistence.datatype.tables.FineQualifyByCorporation;
import persistence.datatype.tables.IncidentClasificationByCorporation;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;

import communication.administration.user.UserSessionData;
import communication.datatype.DataTypeConstants;

/**
 * @ejb.bean name="DataTypeABM" display-name="Name for DataTypeABM"
 *           description="Description for DataTypeABM" jndi-name="ejb/DataTypeABM"
 *           type="Stateless"
 *  
 */
public abstract class DataTypeABMBean implements SessionBean {
    
    private ExceptionHandler handler = new ExceptionHandler(DataTypeABMBean.class);

    /**
     * @ejb.create-method 
     * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
     * @throws CreateException
     */
    public void ejbCreate() throws CreateException { }

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
	 */ 	 
    public void add(String datatype,int type,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            switch(type) {
                case DataTypeConstants.OLD_ACCIDENT_CLASIFICATION:
                    AccidentClasificationByCorporation accident = new AccidentClasificationByCorporation(null,datatype,userSessionData.getFilter().getCorporationId(),true);
                    session.save(accident);
                    break;
                case DataTypeConstants.OLD_INCIDENT_CLASIFICATION:
                    IncidentClasificationByCorporation incident = new IncidentClasificationByCorporation(null,datatype,userSessionData.getFilter().getCorporationId());
                    session.save(incident);
                    break;
/*                case DataTypeConstants.OLD_FINE_QUALIFY:
                    FineQualifyByCorporation fine = new FineQualifyByCorporation(null,datatype,userSessionData.getFilter().getCorporationId());
                    session.save(fine);
                    break;*/
            }
        }catch(Exception e) {
        	Object[] parameters = {datatype,type};
            handler.getException(e,"add",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */      
    public void delete(String datatype,int type,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            switch(type) {
                case DataTypeConstants.OLD_ACCIDENT_CLASIFICATION:
                    Query q = session.createQuery("SELECT accident FROM AccidentClasificationByCorporation accident WHERE resourceBundleId = (:datatype) AND corporationId = (:corporationId)");
                    q.setParameter("datatype",datatype);
                    q.setParameter("corporationId",userSessionData.getFilter().getCorporationId());
                    AccidentClasificationByCorporation accident = (AccidentClasificationByCorporation)q.uniqueResult();
                    session.delete(accident);
                    break;
                case DataTypeConstants.OLD_INCIDENT_CLASIFICATION:
                    q = session.createQuery("SELECT incident FROM IncidentClasificationByCorporation incident WHERE resourceBundleId = (:datatype) AND corporationId = (:corporationId)");
                    q.setParameter("datatype",datatype);
                    q.setParameter("corporationId",userSessionData.getFilter().getCorporationId());
                    IncidentClasificationByCorporation incident = (IncidentClasificationByCorporation)q.uniqueResult();
                    session.delete(incident);
                    break;
                case DataTypeConstants.OLD_FINE_QUALIFY:
                    q = session.createQuery("SELECT fine FROM FineQualifyByCorporation fine WHERE resourceBundleId = (:datatype) AND corporationId = (:corporationId)");
                    q.setParameter("datatype",datatype);
                    q.setParameter("corporationId",userSessionData.getFilter().getCorporationId());
                    FineQualifyByCorporation fine = (FineQualifyByCorporation)q.uniqueResult();
                    session.delete(fine);
                    break;
            }
        }catch(Exception e) {
        	Object[] parameters = {datatype,type};
            handler.getException(e,"delete",userSessionData,parameters);
        }
    }
}
