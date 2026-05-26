/*
 * Created on 27-oct-2005
 */
package persistence.cenapra;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.cenapra.log.CENAPRAActivityLog;
import persistence.cenapra.log.CENAPRAActivityRegistryLog;
import persistence.cenapra.log.CENAPRAParticipantLog;
import persistence.cenapra.tables.CENAPRAActivity;
import persistence.cenapra.tables.CENAPRAActivityRegistry;
import persistence.cenapra.tables.CENAPRAActivityRegistryValue;
import persistence.cenapra.tables.CENAPRAParticipant;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;

import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ActivityData;
import communication.cenapra.tables.ParticipantData;
import communication.util.LogActions;

/**
 * @ejb.bean name="CENAPRAABM" display-name="Name for CENAPRAABM"
 *      description="Description for CENAPRAABM" jndi-name="ejb/CENAPRAABM"
 *      type="Stateless"
 *  
 */
public abstract class CENAPRAABMBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(CENAPRAABMBean.class);
    
    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void ejbCreate() throws javax.ejb.CreateException { }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
	 */
	public void createParticipant(ParticipantData data, String comment, UserSessionData userSessionData) throws Exception {
		try{
            Session session=HibernateUtil.currentSession();			
			CENAPRAParticipant participant = new CENAPRAParticipant(data,session,userSessionData.getFilter().getCorporationId());
			participant.setActivity(false);

            Integer id = (Integer)session.save(participant);
            participant.setId(id);
            
            CENAPRAParticipantLog log = new CENAPRAParticipantLog(participant,LogActions.CREATE,comment,userSessionData.getFilter().getLoginName());
            session.save(log);
		} catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"createParticipant",userSessionData,parameters);
        }
	}
	
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
	 */
	public void updateParticipant(ParticipantData data, String comment, UserSessionData userSessionData) throws Exception {
	    try{
	        Session session=HibernateUtil.currentSession();

            CENAPRAParticipant participant = (CENAPRAParticipant)session.load(CENAPRAParticipant.class,data.getId());
			participant.setData(data,session,userSessionData.getFilter().getCorporationId());
			session.update(participant);
		
            CENAPRAParticipantLog log = new CENAPRAParticipantLog(participant,LogActions.UPDATE,comment,userSessionData.getFilter().getLoginName());
            session.save(log);

        } catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"update",userSessionData,parameters);
	    }
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteParticipant(Integer participantId, String comment,UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CENAPRAParticipant participant = (CENAPRAParticipant)session.load(CENAPRAParticipant.class,participantId);
            session.delete(participant);

            CENAPRAParticipantLog log = new CENAPRAParticipantLog(participant,LogActions.DELETE,comment,userSessionData.getFilter().getLoginName());
            session.save(log);
        } catch (Exception e) {
			Object[] parameters = {participantId,comment};
            handler.getException(e,"deleteParticipant",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void createParticipantRegistry(Integer activityId, Integer participantId, String observations, UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();
            
            CENAPRAActivityRegistry registry = new CENAPRAActivityRegistry(activityId,participantId,observations,session);
            Integer id = (Integer)session.save(registry);
            
            CENAPRAParticipant participant = (CENAPRAParticipant)session.load(CENAPRAParticipant.class,participantId);
            participant.setActivity(true);
            session.update(participant);
            
            registry.setId(id);
            CENAPRAActivityRegistryLog log = new CENAPRAActivityRegistryLog(registry,LogActions.CREATE,userSessionData.getFilter().getLoginName());
            session.save(log);
            
        } catch (Exception e) {
			Object[] parameters = {activityId,participantId,observations};
            handler.getException(e,"createParticipantRegistry",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void createActivity(ActivityData data, String comment, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();         
            CENAPRAActivity activity = new CENAPRAActivity(data,session,userSessionData.getFilter().getCorporationId());

            Integer id = (Integer)session.save(activity);
            activity.setId(id);
            
            CENAPRAActivityLog log = new CENAPRAActivityLog(activity,LogActions.CREATE,comment,userSessionData.getFilter().getLoginName());
            session.save(log);
            
        } catch (Exception e) {
			Object[] parameters = {data,comment};
			e.printStackTrace();
            handler.getException(e,"createActivity",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void updateActivity(ActivityData data, String comment, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CENAPRAActivity activity = (CENAPRAActivity)session.load(CENAPRAActivity.class,data.getId());
            activity.setData(data,session,userSessionData.getFilter().getCorporationId());
            session.update(activity);

            CENAPRAActivityLog log = new CENAPRAActivityLog(activity,LogActions.UPDATE,comment,userSessionData.getFilter().getLoginName());
            session.save(log);
        } catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"updateActivity",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivityParticipants(String list,UserSessionData userSessionData) throws Exception {
        try {
        	Session session=HibernateUtil.currentSession();

        	Query q = session.createSQLQuery("SELECT participant FROM cenapraactivityregistry WHERE id IN ("+list+")").addScalar("participant", Hibernate.INTEGER);
        	Collection idList = q.list();
        	if(idList != null) {
        		Iterator it = idList.iterator();
        		while(it.hasNext()){
        			Integer id = (Integer)it.next();
	            	q = session.createSQLQuery("SELECT COUNT(*) AS c FROM cenapraactivityregistry WHERE participant = "+id).addScalar("c", Hibernate.INTEGER);
	            	if(((Integer)q.list().iterator().next()).intValue() == 1) {
	                    CENAPRAParticipant participant = (CENAPRAParticipant)session.load(CENAPRAParticipant.class,new Integer(id));
	                    participant.setActivity(false);
	                    session.update(participant);
	            	}
        		}
            }

            q = session.createQuery("DELETE FROM CENAPRAActivityRegistry WHERE id IN ("+list+")");
            q.executeUpdate();
            
        } catch (Exception e) {
			Object[] parameters = {list};
            handler.getException(e,"deleteActivityParticipant",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivityParticipants(Integer activityId,UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();

        	Query q = session.createSQLQuery("SELECT participant FROM cenapraactivityregistry WHERE activity = "+activityId).addScalar("participant", Hibernate.INTEGER);
        	Collection list = q.list();
        	if(list != null) {
        		Iterator it = list.iterator();
        		while(it.hasNext()){
        			Integer id = (Integer)it.next();
                	q = session.createSQLQuery("SELECT COUNT(*) AS c FROM cenapraactivityregistry WHERE participant = "+id).addScalar("c", Hibernate.INTEGER);
                	if(((Integer)q.list().iterator().next()).intValue() == 1) {
                        CENAPRAParticipant participant = (CENAPRAParticipant)session.load(CENAPRAParticipant.class,id);
                        participant.setActivity(false);
                        session.update(participant);
                	}
        		}
        	}
        	
        	q = session.createQuery("DELETE FROM CENAPRAActivityRegistry WHERE activity = "+String.valueOf(activityId));
            q.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {activityId};
            handler.getException(e,"deleteActivityParticipants",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivity(Integer activityId, String comment,UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CENAPRAActivity activity = (CENAPRAActivity)session.load(CENAPRAActivity.class,activityId);
            session.delete(activity);

            CENAPRAActivityLog log = new CENAPRAActivityLog(activity,LogActions.DELETE,comment,userSessionData.getFilter().getLoginName());
            session.save(log);
        } catch (Exception e) {
			Object[] parameters = {activityId,comment};
            handler.getException(e,"deleteActivity",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivityRegistryValues(Integer activity, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query query = session.createQuery("DELETE FROM CENAPRAActivityRegistryValue v WHERE v.pk.registry IN (SELECT id FROM CENAPRAActivityRegistry WHERE activity = "+String.valueOf(activity)+")");
            query.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {activity};
            handler.getException(e,"deleteActivityRegistryValues",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteRegistryValues(Integer registryId, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query query = session.createQuery("DELETE FROM CENAPRAActivityRegistryValue v WHERE v.pk.registry = "+String.valueOf(registryId));
            query.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {registryId};
            handler.getException(e,"deleteActivityRegistryValues",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void deleteActivityRegistryValues(String list, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query query = session.createQuery("DELETE FROM CENAPRAActivityRegistryValue v WHERE v.pk.registry IN ("+list+")");
            query.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {list};
            handler.getException(e,"deleteActivityRegistryValues",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void createActivityRegistryValue(Integer item, Integer registry, Integer check, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CENAPRAActivityRegistryValue value = new CENAPRAActivityRegistryValue(registry,item,check,session);
            session.save(value);
        } catch (Exception e) {
			Object[] parameters = {item};
            handler.getException(e,"createActivityRegistryValue",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void updateRegistryCheck(Integer registryId, String observations, Integer note, String number, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CENAPRAActivityRegistry registry = (CENAPRAActivityRegistry)session.load(CENAPRAActivityRegistry.class,registryId);
            registry.setResult(true);
            registry.setObservations(observations);
            registry.setNote(note);
            registry.setNumber(number);
            session.update(registry);
        } catch (Exception e) {
			Object[] parameters = {registryId,observations,note,number};
            handler.getException(e,"updateRegistryCheck",userSessionData,parameters);
        }
    }
}
