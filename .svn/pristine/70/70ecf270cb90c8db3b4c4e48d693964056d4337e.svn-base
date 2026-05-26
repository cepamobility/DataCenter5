package persistence.activity;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.activity.tables.ActivityLink;
import persistence.activity.tables.ActivityLinkPK;
import persistence.activity.tables.CEPAScheduleActivity;
import persistence.activity.tables.CEPAScheduleActivityRegistry;
import persistence.activity.tables.CEPAScheduleActivityRegistryObservationSelected;
import persistence.activity.tables.Item;
import persistence.activity.tables.ItemValue;
import persistence.activity.tables.PointComment;
import persistence.activity.tables.PointObservationSelected;
import persistence.cepasystem.tables.CepaSystemActivity;
import persistence.cepasystem.tables.CepaSystemActivityRegistry;
import persistence.datatype.tables.CEPAActivityScheduleState;
import persistence.driver.tables.Driver;
import persistence.driver.tables.DriverLink;
import persistence.hibernate.HibernateUtil;
import persistence.log.activity.ActivityLog;
import persistence.log.activity.ActivityRegistryLog;
import persistence.util.ExceptionHandler;
import persistence.util.LogAction;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.util.LogActions;

/**
 * @ejb.bean name="ActivityABM"
 *           display-name="Name for ActivityABM"
 *           description="Description for ActivityABM" 
 *           jndi-name="ejb/ActivityABM"
 *           type="Stateless"
 *           view-type="both"
 */

public abstract class ActivityABMBean implements SessionBean { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExceptionHandler handler = new ExceptionHandler(ActivityABMBean.class);
    
	/**
     * @ejb.create-method 
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cepascheduleactivityupdate,cepascheduleactivitydelete,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,cdactivitymodify,cdactivitydelete,jjreporter"
	 * 
	 */	
	public void ejbCreate(){
		
	};
		
	/**
	 * ABM members
	 */
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitycreate,cdactivitycreate,jjreporter" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public Integer createCEPAScheduleActivity(CEPAActivityScheduleData data, String comment, UserSessionData userRequest) throws Exception {
	    new LogAction("createCEPAScheduleActivity");
		try{
			Session session=HibernateUtil.currentSession();
			
			CEPAScheduleActivity activity = new CEPAScheduleActivity(data);
			
			Integer id = (Integer)session.save(activity);
			activity.setActivityId(id);
			
			ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),
					new Date(),LogActions.CREATE,comment);
			session.save(log);
			
			return id;
			
		} catch (Exception e) {
			Object[] parameters = {data,comment};
			e.printStackTrace();
            handler.getException(e,"createCEPAScheduleActivity",userRequest,parameters);
        }	
		return null;
	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify, jjreporter" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void updateCEPAScheduleActivity(CEPAActivityScheduleData data,String comment,UserSessionData userRequest) throws Exception {
        new LogAction("updateCEPAScheduleActivity");
		
		//updateCEPAScheduleActivityValidation(data,userRequest);
		
		try{
			CEPAScheduleActivity activity=new CEPAScheduleActivity(data);
			Session session=HibernateUtil.currentSession();
			session.update(activity);
					
			ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.UPDATE,comment);
			session.save(log);			
			
		}catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"updateCEPAScheduleActivity",userRequest,parameters);
        }	
	}

	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void updateForestalActivity(Integer activityId, CEPAActivityScheduleData data,String comment,UserSessionData userRequest) throws Exception {
        new LogAction("updateCEPAScheduleActivity");
		
		//updateCEPAScheduleActivityValidation(data,userRequest);
		
		try{
			Session session=HibernateUtil.currentSession();
			CEPAScheduleActivity activity = (CEPAScheduleActivity) session.load(CEPAScheduleActivity.class, activityId);
			activity.setState((CEPAActivityScheduleState) session.load(CEPAActivityScheduleState.class,data.getState().getResourceBundleId()));
			activity.setDate(data.getDate());
			activity.setCity(data.getCity());
			session.update(activity);
					
			ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.UPDATE,comment);
			session.save(log);			
			
		}catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"updateCEPAScheduleActivity",userRequest,parameters);
        }	
	}

	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitydelete,cdactivitydelete" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void deleteCEPAScheduleActivity(CEPAActivityScheduleData data, String comment,UserSessionData userRequest) throws Exception {
        new LogAction("deleteCEPAScheduleActivity");
		try{
			CEPAScheduleActivity activity = null; 
			Session session = HibernateUtil.currentSession();
			activity = (CEPAScheduleActivity) session.load(CEPAScheduleActivity.class, data.getActivityId());
			ActivityLog log = new ActivityLog(activity,userRequest.getFilter().getLoginName(),new Date(),LogActions.DELETE,comment);
			session.save(log);						
			session.delete(activity); 
		}	catch(Exception e){
			Object[] parameters = {data,comment};
            handler.getException(e,"deleteCEPAScheduleActivity",userRequest,parameters);
		}
		
	}
	

	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivityregistrycreate,cepascheduleactivityregistryupdate,cdactivitycreate,jjreporter" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void createCEPAScheduleActivityRegistry(CEPAActivityScheduleRegistryData data,
		UserSessionData userRequest,boolean pasarADictada) throws Exception {
        new LogAction("createCEPAScheduleActivityRegistry");

		try{
			Session session=HibernateUtil.currentSession();
			
			CEPAScheduleActivityRegistry registry=new CEPAScheduleActivityRegistry(data);
			
			// recupero registry si existe
            String queryStr = "select registry from CEPAScheduleActivityRegistry as registry "+
            				   " where registry.activity.activityId = (:idActivity) " +
            				   "and registry.driver.id = (:idDriver) ";
   
            Query query = session.createQuery(queryStr);
            query.setParameter("idActivity",registry.getActivity().getActivityId());
            query.setParameter("idDriver",registry.getDriver().getId());
                 
            List activityColl = query.list();
            Iterator iter = activityColl.iterator();
            CEPAScheduleActivityRegistry result = null;
            while (iter.hasNext()) {
            	result = (CEPAScheduleActivityRegistry) iter.next();
            }
                              

			// actualizo el registry recuperado
            if(result != null) {
            	result.setItemSet(null);
            	result.setItemValueSet(null);
            	result.setPointCommentSet(null);
            	result.setNote(registry.getNote());
            	result.setClasification(registry.getClasification());
            	result.setObs(registry.getObs());
            	result.setInstructor(registry.getInstructor());
            	result.setDrivingExperience(registry.getDrivingExperience());
            	result.setClassNumber(registry.getClassNumber());
            	
            	result.setHasResult(registry.getHasResult());
            	
            	result.setTeoricoAsistence(registry.getTeoricoAsistence());
            	result.setPracticoAsistence(registry.getPracticoAsistence());
            	
            	// Agregado para el Commentary Drive:
            	result.setConductionType(registry.getConductionType());
            	result.setCepaCourse(registry.isCepaCourse());
            	result.setPartner(registry.getPartner());
            	result.setPlate(registry.getPlate());
            	// Fin del Agregado para el commentary Drive
            	
            	// Agregado de Tranning on the Job
            	result.setInitialHr(registry.getInitialHr());            	
            	result.setFinalHr(registry.getFinalHr());
            	result.setInitialKm(registry.getInitialKm());            	
            	result.setFinalKm(registry.getFinalKm());
            	result.setCirculationAreaHighWay(registry.isCirculationAreaHighWay());
            	result.setCirculationAreaRoad(registry.isCirculationAreaRoad());
            	result.setCirculationAreaSubUrban(registry.isCirculationAreaSubUrban());
            	result.setCirculationAreaUrban(registry.isCirculationAreaUrban());
            	
            	result.setLight(registry.getLight());
            	result.setTrade(registry.getTrade());
            	result.setVehicleType(registry.getVehicleType());
            	result.setVelocityBox(registry.getVelocityBox());
            	// Fin del Agregado
            	
            	// Agregado para BTW1
            	result.setNovato(registry.getNovato());
            	result.setPhrd(registry.getPhrd());
            	// Fin dle Agregado            	
            	
            	// ItemSet
            	result.setItemSet(new HashSet<Item>());
            	// Se puede dar null para el caso de Commentary Drive de J&J,
            	// por eso hago esta restriccion:
            	if (registry.getItemSet() != null) {
            		result.setItemSet(new HashSet<Item>());
            		result.getItemSet().addAll(registry.getItemSet());
            	}
            	
            	// ItemValueSet 
            	if (registry.getItemValueSet() != null){
            		result.setItemValueSet(new HashSet<ItemValue>());
            		result.getItemValueSet().addAll(registry.getItemValueSet());            	
            	}
            	
            	// PointComment
           		// result.setPointCommentSet(null);
            	if (registry.getPointCommentSet() != null){
                	result.setPointCommentSet(new HashSet<PointComment>());
            		result.getPointCommentSet().addAll(registry.getPointCommentSet());
            	}
            	
            	//PointObservationSelected
            	if (registry.getPointObservationSelectedSet() != null){
            		result.setPointObservationSelectedSet(new HashSet<PointObservationSelected>());
            		result.getPointObservationSelectedSet().addAll(registry.getPointObservationSelectedSet());
            	}
            	
            	// Observations
            	if (registry.getObservationsSelected() != null){
            		result.setObservationsSelected(new HashSet<CEPAScheduleActivityRegistryObservationSelected>());
            		result.getObservationsSelected().addAll(registry.getObservationsSelected());            		
            	}
            	
            	session.update(result);  
            	
            	// Si la actividad es una direccion comentada, al ingresar el ultimo de los resultados pasa a estado de dictada
            	CEPAScheduleActivity activity = result.getActivity(); 
            	/*if ((result.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVE))
            			|| (result.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER))) {
            	*/
            	if (pasarADictada) {
            		CEPAActivityScheduleState state = new CEPAActivityScheduleState();
            		session.load(state,CEPAActivityConstants.DICTADA);
            		activity.setState(state);
                	session.update(activity);
                	// faltaria actualizar el log            		
            	}
            	//session.update(activity);
            	//faltaria actualizar el log

            	// LOG:
            	if (result.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER) ||
            		result.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
            		result.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA) ||
            		result.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK)
            		){
            		
            		ActivityRegistryLog log = new ActivityRegistryLog(result,
    					userRequest.getFilter().getLoginName(),	new Date(),
    					LogActions.UPDATECOMMENTARYDRIVE,"Update Commentary Drive Registry");
            		session.save(log);			       			
            	}else{
            		ActivityRegistryLog log = new ActivityRegistryLog(result,
        				userRequest.getFilter().getLoginName(),new Date(),
        				LogActions.UPDATE,"update");
               		session.save(log);			       			
            	}
            }
            else {
	
    			Integer id = (Integer)session.save(registry);
    			registry.setActivityRegistryId(id);


    			//  LOG: 
            	
    			if (registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK)
            		){
            		
            		ActivityRegistryLog log = new ActivityRegistryLog(registry,
    					userRequest.getFilter().getLoginName(),	new Date(),
    					LogActions.ADDCOMMENTARYDRIVE,"Add Commentary Drive Registry");
            		session.save(log);			       			
            	}else{
            	
            		ActivityRegistryLog log = new ActivityRegistryLog(registry,
            			userRequest.getFilter().getLoginName(),new Date(),
            			LogActions.CREATE,"creacion");
            		session.save(log);   
            	}
            }	
			
		} catch (Exception e) {
			Object[] parameters = {data,String.valueOf(pasarADictada)};
            handler.getException(e,"createCEPAScheduleActivityRegistry",userRequest,parameters);
        }	
	}
	
	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivitydelete,cepascheduleactivityregistrydelete,cdactivitydelete" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
	public void deleteCEPAScheduleActivityRegistry(CEPAActivityScheduleRegistryData data, String comment,UserSessionData userRequest) throws Exception {
        new LogAction("deleteCEPAScheduleActivityRegistry");
		try{
			
			CEPAScheduleActivityRegistry registry = null; 

			Session session = HibernateUtil.currentSession();

			registry = (CEPAScheduleActivityRegistry) session.load(CEPAScheduleActivityRegistry.class, data.getActivityRegistryId());
			
			registry.getItemSet().removeAll(registry.getItemSet());

			// LOG:
			
        	if (registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK)
            		){
        		
        		ActivityRegistryLog log = new ActivityRegistryLog(registry,
					userRequest.getFilter().getLoginName(),	new Date(),
					LogActions.DELETECOMMENTARYDRIVE,"Delete Commentary Drive Registry");
        			session.save(log);			       			
        	}else{
        	
        		ActivityRegistryLog log = new ActivityRegistryLog(registry,
        			userRequest.getFilter().getLoginName(),new Date(),
        			LogActions.DELETE,comment);
        		session.save(log);						
        	}
			
			session.delete(registry); 
			
		}	catch(Exception e){
			Object[] parameters = {data,comment};
            handler.getException(e,"deleteCEPAScheduleActivityRegistry",userRequest,parameters);
		}
		
	}
	
    /**
     * @ejb.permission role-name = "administrator,cepascheduleactivitydelete,cepascheduleactivityregistrydelete,cdactivitydelete" 
     * @ejb.interface-method view-type = "both"
     */
    public void deleteActivityRegistry(Integer activity,Integer driver,String comment,UserSessionData userSessionData) throws Exception {
        new LogAction("deleteActivityRegistry");
        try{
            Session session = HibernateUtil.currentSession();

            Query q = session.createQuery("SELECT registry FROM CEPAScheduleActivityRegistry registry WHERE registry.activity.activityId = "+String.valueOf(activity)+" and registry.driver.id = "+String.valueOf(driver));
            CEPAScheduleActivityRegistry registry = (CEPAScheduleActivityRegistry)q.uniqueResult();
            
            registry.getItemSet().removeAll(registry.getItemSet());

            // LOG:
            
            if (registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEOTHER) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA) ||
            		registry.getActivity().getDefinition().getResourceKeyName().
            		equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK)
            		){
                ActivityRegistryLog log = new ActivityRegistryLog(registry,userSessionData.getFilter().getLoginName(), new Date(),LogActions.DELETECOMMENTARYDRIVE,"Delete Commentary Drive Registry");
                session.save(log);                          
            }else{
                ActivityRegistryLog log = new ActivityRegistryLog(registry,userSessionData.getFilter().getLoginName(),new Date(),LogActions.DELETE,comment);
                session.save(log);                      
            }
            
            session.delete(registry); 
            
        } catch(Exception e){
			Object[] parameters = {activity,driver,comment};
            handler.getException(e,"deleteActivityRegistry",userSessionData,parameters);
        }
        
    }


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public Integer createCepaSystemActivity(communication.cepasystem.tables.ActivityData data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();         
            CepaSystemActivity activity = new CepaSystemActivity(data,session,
            		userSessionData.getFilter().getCorporationId());

            Integer id = (Integer)session.save(activity);
            return id;
        } catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"createCepaSystemActivity",userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public void updateCepaSystemActivity(communication.cepasystem.tables.ActivityData data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CepaSystemActivity activity = (CepaSystemActivity)session.load(CepaSystemActivity.class,data.getId());
            activity.setData(data,session,userSessionData.getFilter().getCorporationId());
            session.update(activity);

        } catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"updateCepaSystemActivity",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,imeseviparticipantupdate,imeseviactivityupdate"
     */
    public void createCepaSystemActivityRegistry(Integer activityId, Integer driverId, String observations, UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();
            
            CepaSystemActivityRegistry registry = new CepaSystemActivityRegistry(activityId,driverId,observations,session);
            session.save(registry);
            
        } catch (Exception e) {
			Object[] parameters = {activityId,driverId,observations};
            handler.getException(e,"createCepaSystemActivityRegistry",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public void deleteCepaSystemActivityDrivers(Integer activityId,UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();
            Query q = session.createQuery("DELETE FROM CepaSystemActivityRegistry WHERE activity = "+String.valueOf(activityId));
            q.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {activityId};
            handler.getException(e,"deleteCepaSystemActivityParticipants",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public void deleteCepaSystemActivity(Integer activityId, String comment,UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CepaSystemActivity activity = (CepaSystemActivity)session.load(CepaSystemActivity.class,activityId);
            session.delete(activity);

//            CENAPRAActivityLog log = new CENAPRAActivityLog(activity,LogActions.DELETE,comment,userSessionData.getFilter().getLoginName());
//            session.save(log);
        } catch (Exception e) {
			Object[] parameters = {activityId,comment};
            handler.getException(e,"deleteCepaSystemActivity",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public void deleteCepaSystemActivityDrivers(String list,UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();
            Query q = session.createQuery("DELETE FROM CepaSystemActivityRegistry WHERE id IN ("+list+")");
            q.executeUpdate();
            
        } catch (Exception e) {
			Object[] parameters = {list};
            handler.getException(e,"deleteCepaSystemActivityDrivers",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator"
     */
    public void superviseCepaSystemActivity(Integer activityId, String comment, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            CepaSystemActivity activity = (CepaSystemActivity)session.load(CepaSystemActivity.class,activityId);
            activity.setState(communication.cepasystem.tables.ActivityData.SUPERVISED);
            session.update(activity);

        } catch (Exception e) {
			Object[] parameters = {activityId,comment};
            handler.getException(e,"superviseCepaSystemActivity",userSessionData,parameters);
        }
    }

	/**
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate,cdactivitymodify" 
	 * @ejb.interface-method view-type = "both"
	 * @param data
	 * @param userRequest
	 */
    public void updateActivityRegistry(Integer originalActivity, Integer driver, Integer newActivity,UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query q = session.createQuery("SELECT registry FROM CEPAScheduleActivityRegistry registry WHERE registry.activity.activityId = "+String.valueOf(originalActivity)+" and registry.driver.id = "+String.valueOf(driver));
            Iterator it = q.list().iterator();
            while(it.hasNext()) {
	            CEPAScheduleActivityRegistry registry = (CEPAScheduleActivityRegistry)it.next();
	            CEPAScheduleActivity activity = (CEPAScheduleActivity) session.load(CEPAScheduleActivity.class, newActivity);
	            registry.setActivity(activity);
	            session.update(registry);
            }
        } catch (Exception e) {
			Object[] parameters = {originalActivity,driver,newActivity};
            handler.getException(e,"updateActivityRegistry",userSessionData,parameters);
        }
    }
    
	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate"
	 * @param data Driver that will be created 
	 * @param userRequest User logged
	 */
	public void saveActivityLinks(Integer[] data, UserSessionData userSessionData) throws Exception {
		try{
            Session session=HibernateUtil.currentSession();			
            ActivityLink link = new ActivityLink(data);
            session.save(link);
        }catch(Exception e) {
			Object[] parameters = data;
			handler.getException(e,"saveDriverLinks",userSessionData,parameters);
        }
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate"
	 * @param data Driver that will be created 
	 * @param userRequest User logged
	 */
	public void updateActivityLinks(Integer[] data, UserSessionData userSessionData) throws Exception {
		try{
            Session session=HibernateUtil.currentSession();			
    		Query query = session.createQuery("SELECT link FROM ActivityLink link WHERE link.pk.activity = "+data[0]+" and link.pk.driver = "+data[1]);
    		List list = query.list();
    		if(list != null && list.size() > 0) {
    			ActivityLink link = (ActivityLink) list.iterator().next();
	            link.setFosa(data[2]);
	            link.setWeyerhauser(data[3]);
	            link.setMdp(data[4]);
	            session.update(link);
    		}
        }catch(Exception e) {
			Object[] parameters = data;
			handler.getException(e,"updateDriverLinks",userSessionData,parameters);
        }
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate"
	 * @param data Driver that will be created 
	 * @param userRequest User logged
	 */
	public void updateActivityLink(Integer activityOld, Integer driver, Integer newActivity, UserSessionData userSessionData) throws Exception {
		try{
            Session session=HibernateUtil.currentSession();			
    		Query query = session.createQuery("SELECT link FROM ActivityLink link WHERE link.pk.activity = "+activityOld+" and link.pk.driver = "+driver);
    		List list = query.list();
    		if(list != null && list.size() > 0) {
    			ActivityLink link = (ActivityLink) list.iterator().next();
    			ActivityLink newLink = new ActivityLink(); 
    			newLink.setPk(new ActivityLinkPK(newActivity, driver));
    			newLink.setFosa(link.getFosa());
    			newLink.setWeyerhauser(link.getWeyerhauser());
    			newLink.setMdp(link.getMdp());
	            session.delete(link);
	            session.save(newLink);
    		}
        }catch(Exception e) {
			Object[] parameters = {activityOld, driver, newActivity};
			handler.getException(e,"updateActivityLink",userSessionData,parameters);
        }
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,cepascheduleactivityupdate"
	 * @param data Driver that will be created 
	 * @param userRequest User logged
	 */
	public void replicateActivityResult(Integer originalActivity,Integer originalDriver,Integer newActivity,Integer newDriver,UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();
            Query q = session.createQuery("SELECT registry FROM CEPAScheduleActivityRegistry registry WHERE registry.activity.activityId = "+String.valueOf(originalActivity)+" and registry.driver.id = "+String.valueOf(originalDriver));
            Iterator it = q.list().iterator();
            if(it.hasNext()) {
	            CEPAScheduleActivityRegistry newRegistry = new CEPAScheduleActivityRegistry((CEPAScheduleActivityRegistry)it.next(),newDriver,newActivity);
	            session.save(newRegistry);
            }
        } catch (Exception e) {
			Object[] parameters = {originalActivity,originalDriver,newActivity,newDriver};
            handler.getException(e,"replicateActivityResult",userSessionData,parameters);
        }
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,cepascheduleactivityupdate"
	 * @param data
	 * @throws Exception 
	 */
	public void deleteActivityLink(Integer activityId,Integer driver,UserSessionData userSession) throws Exception {
		try{
            Session session = HibernateUtil.currentSession();
    		Query query = session.createQuery("SELECT link FROM ActivityLink link WHERE link.pk.activity = "+activityId+" and link.pk.driver = "+driver);
    		List list = query.list();
    		if(list != null && list.size() > 0) {
    			ActivityLink link = (ActivityLink)list.iterator().next();
    			session.delete(link);
    			try {
    				if(link.getFosa() != null) {
    					if(getParticipantCount(link.getFosa(), userSession) == 0) {
	    					CEPAScheduleActivity activity = (CEPAScheduleActivity) session.load(CEPAScheduleActivity.class, link.getFosa());
	    					session.delete(activity);
    					}
    				}
    				if(link.getWeyerhauser() != null) {
    					if(getParticipantCount(link.getWeyerhauser(), userSession) == 0) {
			    			CEPAScheduleActivity activity = (CEPAScheduleActivity) session.load(CEPAScheduleActivity.class, link.getWeyerhauser());
			    			session.delete(activity);
    					}
    				}
    				if(link.getMdp() != null) {
    					if(getParticipantCount(link.getMdp(), userSession) == 0) {
	    					CEPAScheduleActivity activity = (CEPAScheduleActivity) session.load(CEPAScheduleActivity.class, link.getMdp());
	    					session.delete(activity);
    					}
    				}
    			}catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}catch (Exception e) {
			Object[] parameters = {activityId};
    		handler.getException(e,"deleteActivityLink",userSession,parameters);
    	}
	}

    private Integer getParticipantCount(Integer activity, UserSessionData userRequest) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
	        String queryStr = "SELECT COUNT(*) AS count FROM activityregistry WHERE activity = "+activity.toString();
	        Query query = session.createSQLQuery(queryStr).addScalar("count",Hibernate.INTEGER);
            return (Integer)query.uniqueResult();
        }catch (Exception e) {
        	Object[] parameters = {activity};
            handler.getException(e,"getParticipantCount",userRequest,parameters);
        }
        return null;
    }            
}
