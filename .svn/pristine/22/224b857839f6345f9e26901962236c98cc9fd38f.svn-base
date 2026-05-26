/*
 * Created on 27-oct-2005
 */
package persistence.fleet;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.SessionBean;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.fleet.tables.AbstractFleet;
import persistence.fleet.tables.ColectiveFleet;
import persistence.fleet.tables.ConsumedFuel;
import persistence.fleet.tables.FleetAccident;
import persistence.fleet.tables.FleetDivision;
import persistence.fleet.tables.FleetDivisionDetail;
import persistence.fleet.tables.FleetDivisionDetailPK;
import persistence.fleet.tables.FleetIncident;
import persistence.fleet.tables.IndividualFleet;
import persistence.fleet.tables.Odometer;
import persistence.fleet.tables.RegionBlock;
import persistence.hibernate.HibernateUtil;
import persistence.log.fleet.AbstractFleetLog;
import persistence.log.fleet.ConsumedFuelLog;
import persistence.log.fleet.FleetAccidentLog;
import persistence.log.fleet.FleetDivisionDetailLog;
import persistence.log.fleet.FleetDivisionLog;
import persistence.log.fleet.FleetIncidentLog;
import persistence.log.fleet.OdometerLog;
import persistence.util.ExceptionHandler;
import persistence.vehicle.tables.Vehicle;

import communication.administration.user.UserSessionData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.fleet.tables.ConsumedFuelData;
import communication.fleet.tables.FleetAccidentData;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetDivisionData;
import communication.fleet.tables.FleetDivisionDetailData;
import communication.fleet.tables.FleetIncidentData;
import communication.fleet.tables.OdometerData;
import communication.fleet.util.RegionBlockData;
import communication.util.LogActions;
import communication.vehicle.tables.VehicleData;

/**
 * @ejb.bean name="FleetABM" display-name="Name for FleetABM"
 *      description="Description for FleetABM" jndi-name="ejb/FleetABM"
 *      type="Stateless"
 *  
 */
public abstract class FleetABMBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(FleetABMBean.class);
    
    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetdelete,fleetcreate,driverfleetinsertdata,fleetinsertdata,driverodometer"
     */
    public void ejbCreate() throws javax.ejb.CreateException { }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,fleetcreate"
	 * @param data Fleet that will be created 
	 * @param userRequest User logged
	 */
	public void fleetCreate(FleetData data, UserSessionData userRequest, String comment) throws Exception {
		try{
            Session session=HibernateUtil.currentSession();			
            AbstractFleet fleet = null;
            switch(data.getType().intValue()) {
                case FleetData.COLECTIVE:
                    fleet = new ColectiveFleet(data,session,userRequest);
                    break;
                case FleetData.INDIVIDUAL:
                    fleet = new IndividualFleet(data,session,userRequest);
                    break;
            }
            Integer id = (Integer)session.save(fleet);
			fleet.setId(id);
			
			//FleetLog log = new FleetLog(fleet,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,comment);
			//session.save(log);
			
		} catch (Exception e) {
			Object[] parameters = {data,comment};
            handler.getException(e,"fleetCreate",userRequest,parameters);
        }
	}
	

	
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate"
     * @param data Fleet that will be created 
     * @param userRequest User logged
     */
	public void  fleetsUpdateBlockPeriod(Collection fleetsData,UserSessionData userSessionData, RegionBlockData regionBlockData) throws Exception {
		
		// Local Variables
    	AbstractFleet fleet;
    	AbstractFleetLog log;	
    	Session session;
		Iterator it;
		FleetData fleetData;
		RegionBlock regionBlock;
		try
		{   
			session = HibernateUtil.currentSession();
			it = fleetsData.iterator();
			while (it.hasNext()){	
				fleetData = (FleetData)it.next();
				fleet = (AbstractFleet)session.load(AbstractFleet.class,fleetData.getId());
				
				if ( !fleetData.getBlockMonth().equals(fleet.getBlockMonth()) ||
						!fleetData.getBlockYear().equals(fleet.getBlockYear()) )
				{
					/* Update ONLY if the blockMonty or blockYear was changed */
					
					/* Antes de Actualizar los datos guardo el log. */ 
		            log = new AbstractFleetLog(fleet,
		  	        		userSessionData.getFilter().getLoginName(),
		  	        		Calendar.getInstance().getTime(),
		  	        		LogActions.BLOCKDATAENTRY, regionBlockData.getComment(), null,null);
			    
		            /* Actualizo la flota */
		            fleet.updateData(fleetData,session,userSessionData);
		    		
			        /* Save Fleet */
					session.saveOrUpdate(fleet);
	
					/* Save Log */
					session.save(log);		
				}
			}
			// TODO: Bloquear la Region Aca...
			regionBlock = new RegionBlock(regionBlockData);
			session.save(regionBlock);			
			
		}
		catch(Exception e) {
			Object[] parameters = {fleetsData,regionBlockData};
			handler.getException(e,"fleetUpdateBlockPeriod",userSessionData,parameters);
		}
	}
	

	
	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate"
     * @param data Fleet that will be created 
     * @param userRequest User logged
     */
    public void fleetUpdate(FleetData data, UserSessionData userRequest,String comment, int action) throws Exception {
    	try
    	{	
        	/* Actualizo la Flota segun sea colectiva o individual: */
            switch(data.getType().intValue()) {
            	/* Flota Colectiva: */
                case FleetData.COLECTIVE:
           			this.fleetColectiveUpdate(data,userRequest,comment,action);            		
           			break;
                    
                /* Flota Individual: */
                case FleetData.INDIVIDUAL:
                	this.fleetIndividualUpdate(data,userRequest,comment,action);
            		break;
            }  
            
        } catch (Exception e) {
        	Object[] parameters = {data,comment,action};
        	handler.getException(e,"fleetUpdate",userRequest,parameters);
        }
    }
    
    /*
     * Update Individual Fleet
     */
    private void fleetIndividualUpdate(FleetData data, UserSessionData userRequest,String comment, int action) throws Exception
    {
    	// Local Variables
    	IndividualFleet individualFleet;    	
    	Iterator it;
    	VehicleData vehicleData;
    	Vehicle vehicle;
    	AbstractFleetLog log;
    	HashSet vehiclesIds = new HashSet();
    	
    	try
		{
			Session session=HibernateUtil.currentSession();         
	    	individualFleet = (IndividualFleet)session.load(IndividualFleet.class,data.getId());
	
			/* Actualizo Vechiculos de la Flota:  */
				switch (action)
				{
					case  LogActions.ADDVEHICLE:
						it = data.getVehicles().iterator();
						while(it.hasNext()) 
	               	{
							vehicleData = (VehicleData)it.next();
	               		if(individualFleet.getVehicleSet().isEmpty() || 
	               			!individualFleet.getVehiclesIds().contains(vehicleData.getId()) ) {                           	
	
	               			// Agrego el vehiculo a la flota:
	               			individualFleet.addVehicle((Vehicle)session.load(Vehicle.class,vehicleData.getId()));
	               			// Guardo el Id del Vehiculo agregado en el Log.
	               			vehiclesIds.add(vehicleData.getId());
	                    }
	                }
						break;
					case  LogActions.DELETEVEHICLE:
						it = individualFleet.getVehicleSet().iterator();
	               	while(it.hasNext()) 
	               	{
	               		vehicle = (Vehicle)it.next();
	               		if(!data.getVehiclesIds().contains(vehicle.getId())) 
	               		{   
	               			// Guardo el Id del Vehiculo borrado en el log:
	               			vehiclesIds.add(vehicle.getId());
	               			// Borro el vechiculo de la flota:
	               			it.remove();                                    
	                    }
	                }
						break;  
				}
				
			/* Antes de Actualizar los datos guardo el log. */ 
	        log = new AbstractFleetLog(individualFleet,
	        		userRequest.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), action, comment,vehiclesIds, null);
	     			
			/* Actualizo los datos */
			individualFleet.updateData(data,session,userRequest);				
				
			/* Save Updates */
			session.update(individualFleet);          		
			/* Save Log */
	        session.save(log);
		}
		catch (Exception e)	{
			Object[] p = {data,comment,action};
			handler.getException(e,"fleetIndividualUpdate",userRequest,p);	
		}
		
    }
    
    
    
    /*
     * Actualiza las Flotas Colectivas
     */
    private void fleetColectiveUpdate (FleetData data, UserSessionData userRequest, String comment, int action) throws Exception {
    	// Local Variables
    	ColectiveFleet fleet;
    	AbstractFleetLog log;	

		try
		{		
	    	Session session=HibernateUtil.currentSession();         
	    	fleet = (ColectiveFleet)session.load(ColectiveFleet.class,data.getId());
	    	
			/* Antes de Actualizar los datos guardo el log. */ 
	        log = new AbstractFleetLog(fleet,
	        		userRequest.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), action, comment, null,null);
	        /* Actualizo la flota */
	        fleet.updateData(data,session,userRequest);
	        /* Save Fleet */
			session.saveOrUpdate(fleet);
			/* Save Log */
			session.save(log);
	    	
		}
		catch(Exception e) {
			Object[] parameters = {data,comment,action};
			handler.getException(e,"fleetColectiveUpdate",userRequest,parameters);
		}
		
    }
    
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetdelete"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     * @param comment
     */
    public void fleetDelete(Integer id, UserSessionData userRequest, String comment) throws Exception {
        try{
        	AbstractFleetLog log;
            Session session=HibernateUtil.currentSession();         
            AbstractFleet fleet = (AbstractFleet)session.load(AbstractFleet.class,id);
            
        	/* Genero el Log del Delete de la Flota */
	        log = new AbstractFleetLog(fleet,
	        		userRequest.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), LogActions.DELETE, 
	        		comment, null, null);
			/* Save Log */
			session.save(log);
			
			/* Borro la Flota */
            session.delete(fleet);            
        } catch (Exception e) {
			Object[] parameters = {id,comment};
            handler.getException(e,"fleetDelete",userRequest,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate"
     * @param data Fleet that will be created 
     * @param userRequest User logged
     * @param fleetDivisionData  
     * @param comment
     */
    public FleetDivisionData fleetDivisionAdd(FleetData data, UserSessionData userRequest, 
    		FleetDivisionData fdd, String comment) throws Exception
    {
    	try
    	{
    	Session session=HibernateUtil.currentSession();         
    	AbstractFleet fleet = (AbstractFleet)session.load(AbstractFleet.class,data.getId());
    	Iterator it = data.getDivisionsIterator();
    	boolean nuevo = true;
    	AbstractFleetLog log;
    	FleetDivisionData fleetDivisionData;
    	HashSet divisionsLog = new HashSet();
    	
		while(it.hasNext()) 
		{
			fleetDivisionData = (FleetDivisionData)it.next();
			if (!fdd.equals(fleetDivisionData))
				nuevo = true;
			else
			{
				nuevo = false;
				break;
			}
		}
		if (nuevo)
		{
			/* Como las Divisiones tienen id es necesario hacer un Save 
			 * de la FleetDivision para que se gener el id a partir de la
			 * secuencia de la base de datos.
			 */
			FleetDivision fleetDivision = new FleetDivision(fleet,fdd,session,userRequest);							
						
			/* Antes de Actualizar los datos guardo el log. */ 
			divisionsLog.add(fleetDivision);
	        log = new AbstractFleetLog(fleet,
	        		userRequest.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), LogActions.ADDDIVISION, 
	        		comment, null, divisionsLog);
			/* Save Log */
			session.save(log);
			
			/* Save FleetDivision */
			session.save(fleetDivision);

			/* Retorno la FleetDivisionData */
			return fleetDivision.getData();
		}
		else
			return fdd;
    	}
    	catch (Exception e)	{
    		Object[] parameters = {data,fdd,comment};
    		handler.getException(e,"fleetDivisionAdd",userRequest,parameters);
    	}
    	return fdd;
	}					
    
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetdelete"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     * @param comment Comments
     */
    public void deleteDivision(Integer id, UserSessionData userSessionData, String comment)
    throws Exception
    {
        try{
        	AbstractFleetLog log;
        	FleetDivisionLog logDivision;
        	HashSet divisionsLog = new HashSet();
            Session session=HibernateUtil.currentSession();         
            FleetDivision fleetDivision = (FleetDivision)session.load(FleetDivision.class,id);

            /* Antes de Actualizar los datos guardo el log. */ 
            divisionsLog.add(fleetDivision);
	        log = new AbstractFleetLog(fleetDivision.getFleet(),
	        		userSessionData.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), LogActions.DELETEDIVISION, 
	        		comment, null, divisionsLog);
	        /* Save Log */
			session.save(log);
			
	        logDivision = new FleetDivisionLog(log,fleetDivision.getData(),session,userSessionData);
	        session.save(logDivision);
			            
            /* Delete FleetDivision */
            session.delete(fleetDivision);
            
        } catch (Exception e) {
        	Object[] parameters = {id,comment};
            handler.getException(e,"deleteDivision",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetdelete"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     * @param comment Comments
     */
    public void excludeDivision(Integer id, UserSessionData userSessionData, String comment, Date exclusionDate)
    throws Exception
    {
        try{
        	FleetDivisionLog logDivision;
        	AbstractFleetLog log;
        	HashSet divisionsLog = new HashSet();
            Session session=HibernateUtil.currentSession();         
            FleetDivision fleetDivision = (FleetDivision)session.load(FleetDivision.class,id);
              
            /* Antes de Actualizar los datos guardo el log. */ 
            divisionsLog.add(fleetDivision);
	        log = new AbstractFleetLog(fleetDivision.getFleet(),
	        		userSessionData.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), LogActions.EXCLUDEDIVISION, 
	        		comment, null, divisionsLog);
	        /* Save Log */
			session.save(log);
			
			logDivision = new FleetDivisionLog(log,fleetDivision.getData(),session,userSessionData);
	        session.save(logDivision);
			            
            /* Exclude FleetDivision */
            fleetDivision.setExclusionDate(exclusionDate);
            session.update(fleetDivision);
            
        } catch (Exception e) {
        	Object[] parameters = {id,comment,exclusionDate};
            handler.getException(e,"excludeDivision",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,fleetdelete"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     * @param comment Comments
     */
    public void includeDivision(Integer id, UserSessionData userSessionData, String comment)
    throws Exception
    {
        try{
        	AbstractFleetLog log;
        	FleetDivisionLog logDivision;
        	HashSet divisionsLog = new HashSet();
            Session session=HibernateUtil.currentSession();         
            FleetDivision fleetDivision = (FleetDivision)session.load(FleetDivision.class,id);

            /* Antes de Actualizar los datos guardo el log. */ 
            divisionsLog.add(fleetDivision);
	        log = new AbstractFleetLog(fleetDivision.getFleet(),
	        		userSessionData.getFilter().getLoginName(),
	        		Calendar.getInstance().getTime(), LogActions.INCLUDEDIVISION,
	        		comment, null, divisionsLog);
	        /* Save Log */
			session.save(log);
			
			logDivision = new FleetDivisionLog(log,fleetDivision.getData(),session,userSessionData);
	        session.save(logDivision);
	        
            /* Reactivate FleetDivision */
            fleetDivision.setExclusionDate(null);
            session.update(fleetDivision);
            
        } catch (Exception e) {
        	Object[] parameters = {id,comment};
            handler.getException(e,"includeDivision",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     */
    public void addAccident(FleetAccidentData accident, UserSessionData userSessionData) throws Exception{
        try{
            if(userSessionData == null) {
                throw new DeslogedException("addAccident","userSessionData == null");
            }
            if(accident == null) {
                throw new InvalidDataException("addAccident","accident == null");
            }
            Session session=HibernateUtil.currentSession();         
            FleetAccident fleetAccident = new FleetAccident(accident,session,userSessionData);
            session.save(fleetAccident);
            
            FleetAccidentLog log = new FleetAccidentLog(fleetAccident,userSessionData.getFilter().getLoginName(),new Date(),LogActions.CREATE);
            session.save(log);
            
        } catch (Exception e) {
        	Object[] parameters = {accident};
            handler.getException(e,"addAccident",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     */
    public void deleteAccident(Integer accident, UserSessionData userSessionData) throws Exception{
        try{
            if(userSessionData == null) {
                throw new DeslogedException("deleteAccident","userSessionData == null");
            }
            if(accident == null) {
                throw new InvalidDataException("deleteAccident","accident == null");
            }
            Session session=HibernateUtil.currentSession();         
            FleetAccident fleetAccident = (FleetAccident)session.load(FleetAccident.class,accident);
            session.delete(fleetAccident);
            
            FleetAccidentLog log = new FleetAccidentLog(fleetAccident,userSessionData.getFilter().getLoginName(),new Date(),LogActions.DELETE);
            session.save(log);
            
        } catch (Exception e) {
        	Object[] parameters = {accident};
            handler.getException(e,"deleteAccident",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     */
    public void addIncident(FleetIncidentData incident, UserSessionData userSessionData) throws Exception{
        try{
            if(userSessionData == null) {
                throw new DeslogedException("addIncident","userSessionData == null");
            }
            if(incident == null) {
                throw new InvalidDataException("addIncident","incident == null");
            }
            Session session=HibernateUtil.currentSession();         
            FleetIncident fleetIncident = new FleetIncident(incident,session,userSessionData);
            session.save(fleetIncident);
            
            FleetIncidentLog log = new FleetIncidentLog(fleetIncident,userSessionData.getFilter().getLoginName(),new Date(),LogActions.CREATE);
            session.save(log);
        } catch (Exception e) {
        	Object[] parameters = {incident};
            handler.getException(e,"addIncident",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     */
    public void deleteIncident(Integer incident, UserSessionData userSessionData) throws Exception{
        try{
            if(userSessionData == null) {
                throw new DeslogedException("deleteIncident","userSessionData == null");
            }
            if(incident == null) {
                throw new InvalidDataException("deleteIncident","incident == null");
            }
            Session session=HibernateUtil.currentSession();         
            FleetIncident fleetIncident = (FleetIncident)session.load(FleetIncident.class,incident);
            session.delete(fleetIncident);
            
            FleetIncidentLog log = new FleetIncidentLog(fleetIncident,userSessionData.getFilter().getLoginName(),new Date(),LogActions.DELETE);
            session.save(log);
        } catch (Exception e) {
        	Object[] parameters = {incident};
            handler.getException(e,"deleteIncident",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     */
    public boolean updateDetail(FleetDivisionDetailData detail, UserSessionData userSessionData) throws Exception {
        try{
            if(userSessionData == null) {
                throw new DeslogedException("updateDetail","userSessionData == null");
            }
            if(detail == null) {
                throw new InvalidDataException("updateDetail","detail == null");
            }
            boolean flaglog=false; 
            Session session=HibernateUtil.currentSession();
            
            
            Query q = session.createQuery("SELECT vehicles,miles,accidents,incidents " +
            		"FROM FleetDivisionDetail " +
                    " WHERE pk.fleet = "+detail.getFleetDivision().getId().toString()+
                    " AND pk.period = "+detail.getPeriod().toString()+
                    " AND pk.year = "+detail.getYear().toString());

            int vehicles = 0;
            int miles = 0;
            int accidents = 0;
            int incidents = 0;
            List list = q.list();
            Iterator it = list.iterator();
            boolean exists = it.hasNext(); 
            if(exists) {
                Object[] data = (Object[])it.next();
                if (data != null && data[0] != null && data[1] != null && data[2] != null && data[3] != null){
                	vehicles = ((Integer)data[0]).intValue();
                	miles = ((Integer)data[1]).intValue();
                	accidents = ((Integer)data[2]).intValue();
                	incidents = ((Integer)data[3]).intValue();
                }
            }
            
            FleetDivisionDetail divisionDetail = new FleetDivisionDetail(detail,session);
            session.saveOrUpdate(divisionDetail);

            if(!exists) {

                FleetDivisionDetailLog log = new FleetDivisionDetailLog(detail,userSessionData.getFilter().getLoginName(),new Date());
                session.save(log);
                flaglog=true;
            }else {
                
                if( detail != null && 
                   (detail.getVehicles() != null && detail.getVehicles().intValue() != vehicles) ||
                   (detail.getMiles() != null && detail.getMiles().intValue() != miles) ||
                   (detail.getAccidents() != null && detail.getAccidents().intValue() != accidents) ||
                   ( detail.getIncidents() != null && detail.getIncidents().intValue() != incidents) 
                   ){ 

                    FleetDivisionDetailLog log = new FleetDivisionDetailLog(detail,userSessionData.getFilter().getLoginName(),new Date());
                    session.save(log);
                    
                }
            }
            
            return flaglog;
            
        } catch (Exception e) {
        	Object[] parameters = {detail};
            handler.getException(e,"updateDetail",userSessionData,parameters);
        }
        return false;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     */
    public void updateDivisionDetail(FleetDivisionDetailData detail, UserSessionData userSessionData) throws Exception {    
        try{
	        Session session=HibernateUtil.currentSession();
	        FleetDivisionDetail fleetDetail = (FleetDivisionDetail)session.load(FleetDivisionDetail.class, new FleetDivisionDetailPK(detail.getFleetDivision().getId(), detail.getPeriod(), detail.getYear()));
	        fleetDetail.setAccidents(detail.getAccidents());
	        fleetDetail.setIncidents(detail.getIncidents());
	        session.update(fleetDetail);
        } catch (Exception e) {
        	Object[] parameters = {detail};
            handler.getException(e,"updateDivisionDetail",userSessionData,parameters);
        }
    }
   
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate,driverfleetinsertdata,fleetinsertdata,driverodometer"
     */
    public void updateOdometer(OdometerData odometerData, UserSessionData userSessionData) throws Exception {
        try{
            if(userSessionData == null) {
                throw new DeslogedException("updateOdometer","userSessionData == null");
            }
            if(odometerData == null) {
                throw new InvalidDataException("updateOdometer","odometer == null");
            }
            Session session=HibernateUtil.currentSession();         
            Odometer odometer = new Odometer(odometerData,session);
            session.saveOrUpdate(odometer);
            
            OdometerLog log = new OdometerLog(odometer,userSessionData.getFilter().getLoginName(),Calendar.getInstance().getTime(),LogActions.UPDATE);
            session.save(log);
            
        } catch (Exception e) {
        	Object[] parameters = {odometerData};
            handler.getException(e,"updateOdometer",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     */
    public void addConsumedFuel(ConsumedFuelData data, Integer fleetId, UserSessionData userSessionData) throws Exception{
        try{
            if(userSessionData == null) {
                throw new DeslogedException("addConsumedFuel","userSessionData == null");
            }
            if(data == null) {
                throw new InvalidDataException("addConsumedFuel","fuel == null");
            }
            Session session=HibernateUtil.currentSession();         
            ConsumedFuel consumedFuel = new ConsumedFuel(data,fleetId,session,userSessionData);
            session.save(consumedFuel);
            
            ConsumedFuelLog log = new ConsumedFuelLog(consumedFuel,userSessionData.getFilter().getLoginName(),new Date(),LogActions.CREATE);
            session.save(log);
        } catch (Exception e) {
        	Object[] parameters = {data,fleetId};
            handler.getException(e,"addConsumedFuel",userSessionData,parameters);
        }
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     * @param id Fleet that will be delete
     * @param userRequest User logged
     */
    public void deleteConsumedFuel(Integer consumedId, UserSessionData userSessionData) throws Exception{
        try{
            if(userSessionData == null) {
                throw new DeslogedException("deleteConsumedFuel","userSessionData == null");
            }
            if(consumedId == null) {
                throw new InvalidDataException("deleteConsumedFuel","consumedId == null");
            }
            Session session=HibernateUtil.currentSession();         
            ConsumedFuel consumedFuel = (ConsumedFuel)session.load(ConsumedFuel.class,consumedId);
            session.delete(consumedFuel);
            
            ConsumedFuelLog log = new ConsumedFuelLog(consumedFuel,userSessionData.getFilter().getLoginName(),new Date(),LogActions.DELETE);
            session.save(log);
        } catch (Exception e) {
        	Object[] parameters = {consumedId};
            handler.getException(e,"deleteConsumedFuel",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetupdate"
     * @param data Fleet that will be created 
     * @param userRequest User logged
     */
    public void addVehicle(Integer fleetId, Collection vehicles, String comment, UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();         
            IndividualFleet individualFleet = (IndividualFleet)session.load(IndividualFleet.class,fleetId);
            HashSet vehiclesIds = new HashSet();
            Iterator it = vehicles.iterator();
            while(it.hasNext()) {
                Integer vehicleId = new Integer((String)it.next());
                if(individualFleet.getVehicleSet().isEmpty() || !individualFleet.getVehiclesIds().contains(vehicleId) ) {                             
                    // Agrego el vehiculo a la flota:
                    individualFleet.addVehicle((Vehicle)session.load(Vehicle.class,vehicleId));
                    vehiclesIds.add(vehicleId);
                }
            }
            /* Antes de Actualizar los datos guardo el log. */ 
            AbstractFleetLog log = new AbstractFleetLog(individualFleet,userSessionData.getFilter().getLoginName(),Calendar.getInstance().getTime(), LogActions.ADDVEHICLE, comment,vehiclesIds, null);
                
            /* Save Updates */
            session.update(individualFleet);                
            /* Save Log */
            session.save(log);
        }catch (Exception e) {
        	Object[] parameters = {fleetId,vehicles,comment};
            handler.getException(e,"addVehicle",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,fleetinsertdata"
     */
    public void deleteCollectiveData(Integer fleetId, Integer periodFrom, Integer yearFrom, Integer periodTo, Integer yearTo, UserSessionData userSessionData) throws Exception {
        try {
            Session session=HibernateUtil.currentSession();       
            String sql = "SELECT detail FROM FleetDivisionDetail  as detail WHERE detail.pk.fleet.fleet.id = " + fleetId + " AND ";
            if(yearFrom.intValue() == yearTo.intValue()) {
            	sql += " detail.pk.period >= " + periodFrom + " AND detail.pk.period <= " + periodTo + " AND detail.pk.year = " + yearFrom;
            }else {
            	sql += "((detail.pk.period >= " + periodFrom + " AND detail.pk.year = " + yearFrom+")";
            	for(int i =  yearFrom+1; i < yearTo; i++)
            		sql += " OR detail.pk.year = " + i;
            	sql += " OR (detail.pk.period <= " + periodTo + " AND detail.pk.year = " + yearTo+"))";
            }
            
            Query q = session.createQuery(sql);
            Iterator it = q.list().iterator();
            while(it.hasNext()) {
            	session.delete(it.next());
            }

        }catch (Exception e) {
        	Object[] parameters = {fleetId,fleetId,periodFrom,yearFrom,periodTo,yearTo};
            handler.getException(e,"deleteCollectiveData",userSessionData,parameters);
        }
    }
}
