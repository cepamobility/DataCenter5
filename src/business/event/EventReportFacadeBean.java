/*
 * Created on 28-oct-2005
 */
package business.event;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import persistence.driver.DriverReportUtil;
import persistence.event.EventReport;
import persistence.event.EventReportHome;
import persistence.event.EventReportUtil;
import persistence.hibernate.HibernateUtil;
import business.util.ExceptionHandler;
import business.util.MemoryLog;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.event.report.DetailedEventReportData;
import communication.event.report.EventReportByPeriodData;
import communication.event.report.EventReportData;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.event.tables.FineDataType;
import communication.event.tables.ReparationData;
import communication.event.util.EventByPeriodData;
import communication.event.util.EventCountData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.language.Text;
import communication.queries.report.ReportAccidentCountData;
import communication.queries.report.ReportFineCountData;
import communication.queries.report.ReportFineTypeData;
import communication.travel.tables.TravelDataType;
import communication.util.ListResult;
/**
 * @ejb.bean name = "EventReportFacade" jndi-name = "ejb/EventReportFacade" type =
 *           "Stateless" transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "EventReport" ref-name = "ejb/EventReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/EventReport" ref-name = "EventReport"
 *  
 * @ejb.ejb-ref ejb-name = "DriverReport" ref-name = "ejb/DriverReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/DriverReport" ref-name = "DriverReport"
 *  
 * @ejb.util generate="physical"
 *  
 */
public abstract class EventReportFacadeBean implements SessionBean {
	
    private ExceptionHandler handler = new ExceptionHandler(EventReportFacadeBean.class);
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
     * @ejb.permission role-name = "eventview,eventedit,administrator,driveredit,driverviewowndata,drivereventview,drivereventedit,query,viewrecomendation,fleetinsertdata"
	 */
	public void ejbCreate() throws CreateException {
	}

	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess,eventview,drivereventview"
	 *  
	 * @param attrName
	 * @param attrValue
	 * @return Collection of UserData
	 * @throws Exception
	 */
	public ListResult findEventList(String name,int type,int first,int count,
			int status,int country,int folioNumber, UserSessionData userSessionData) throws Exception {
    	try{
    		EventReportHome eventHome = EventReportUtil.getHome();
    		EventReport eventReport = eventHome.create();
            return (userSessionData.getCorporationId() == CorporationData.DPA) ? eventReport.findListDPA(userSessionData, name, type, status, country, folioNumber, first, count) : eventReport.findList(userSessionData, name, type, status, country, folioNumber, first, count);
    	} catch (Exception e) {
    		Object[] parameters = new Object[7];
    		parameters[0] = name;
    		parameters[1] = type;
    		parameters[2] = first;
    		parameters[3] = count;
    		parameters[4] = status;
    		parameters[5] = country;
    		parameters[6] = folioNumber;
            handler.handleException("findEventList",e,userSessionData,parameters);
    	}
        return null;
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess,eventview,drivereventview"
	 */
	public Collection getEventsByVehicle(Integer vehicleId,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getEventsByVehicle",getClass());
        Collection result = null;
    	try{
    		EventReportHome eventHome = EventReportUtil.getHome();
    		EventReport eventReport = eventHome.create();
            result = eventReport.getEventsByVehicle(vehicleId,userSessionData);
    	} catch (Exception e) {
    		Object[] parameters = new Object[1];
    		parameters[0] = vehicleId;
            handler.handleException("getEventsByVehicle",e,userSessionData,parameters);
    	}
        MemoryLog.getInstance().log(MemoryLog.END,"getEventsByVehicle",getClass());
        return result;
	}


    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
     */
    public EventDataType findEventByPrimaryKey(Integer id,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findEventByPrimaryKey",getClass());
        EventDataType data=null;
        try{
            if (id == null) {
                throw new InvalidDataException("findEventByPrimaryKey","id = null");
            }
            EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            data = eventReport.findEventByPrimaryKey(id,userSessionData);            
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = id;
            handler.handleException("findEventByPrimaryKey",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findEventByPrimaryKey",getClass());
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
     */
    public FineDataType findFineByPrimaryKey(Integer id,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findFineByPrimaryKey",getClass());
        FineDataType data=null;
        try{
            if (id == null) {
                throw new InvalidDataException("findFineByPrimaryKey","id = null");
            }
            EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            data=eventReport.findFineByPrimaryKey(id,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = id;
            handler.handleException("findFineByPrimaryKey",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findFineByPrimaryKey",getClass());
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "eventview,administrator,driveredit,driverviewowndata"
     */
    public Collection findEventByDriver(Integer driverId,UserSessionData userSessionData) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"findEventByDriver",getClass());
        Collection result = null;
        try{
            if (driverId == null) {
                throw new InvalidDataException("findEventByDriver","id = null");
            }
            EventReport eventReport = EventReportUtil.getHome().create();
            if(userSessionData.isDriver()) {
                result = eventReport.findEventByDriver(userSessionData,userSessionData.getDriverData().getId());
            }else {
                result = eventReport.findEventByDriver(userSessionData,driverId);
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
    		parameters[0] = driverId;
            handler.handleException("findEventByDriver",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"findEventByDriver",getClass());
        return result;
    } 
    
    /**
     * Devuelve el evento dado el id del conductor y la fecha
     * Si no existe devuelve null.
     * @ejb.interface-method
     * @ejb.permission role-name = "eventview,administrator,driveredit,driverviewowndata,eventupload"
     * @param userSessionData
     * @param driverId
     * @param date
     * @return
     * @throws Exception
     */
    public EventMainAttributes existEvent(UserSessionData userSessionData, Integer driverId, Date date) throws Exception {
    	 MemoryLog.getInstance().log(MemoryLog.START,"existEvent",getClass());
    	 
    	 EventMainAttributes result = null;
         try{
             if (driverId == null) {
                 throw new InvalidDataException("findEventByDriver","id = null");
             }
             EventReport eventReport = EventReportUtil.getHome().create();
             result = eventReport.existEvent(userSessionData,driverId, date); 

         } catch (Exception e) {
         	Object[] parameters = new Object[2];
     		parameters[0] = driverId;
     		parameters[1] = date;
             handler.handleException("existEvent",e,userSessionData,parameters);
         }
         MemoryLog.getInstance().log(MemoryLog.END,"existEvent",getClass());
         return result;
    }
    
    /** 
     * Devuelve la multa dado el id del conductor y la fecha
    * Si no existe devuelve null.
    * 
	 * @ejb.interface-method
    * @ejb.permission role-name = "eventview,administrator,driveredit,driverviewowndata,eventupload"
    * @throws InvalidDataException
    * @throws DeslogedException
    * @throws CommunicationProblemException
    */
   public boolean existFine(FineDataType fine, UserSessionData userSessionData) throws Exception {
	   	MemoryLog.getInstance().log(MemoryLog.START,"existFine",getClass());
  	 	try{
           EventReport eventReport = EventReportUtil.getHome().create();
           return eventReport.existFine(fine, userSessionData); 
  	 	} catch (Exception e) {
  	 		Object[] parameters = {fine};
           handler.handleException("existFine",e,userSessionData,parameters);
  	 	}
  	 	MemoryLog.getInstance().log(MemoryLog.END,"existFine",getClass());
  	 	return false;
   }
  
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "driverview,driverviewowndata,administrator"
     */
    public EventReportData getReportEvents(boolean accidents,boolean incidents, 
    		boolean fines, boolean nearmiss, Integer fromMonth, Integer fromYear, Integer toMonth, 
    		Integer toYear, Integer[][] divOrgs, Integer[][] divgeos, String working, int first,
    		int count,UserSessionData userSessionData, String record,String driverStatus) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getReportEvents",getClass());
        EventReportData data = null;
    	
        try {
            
        	EventReport eventReport = EventReportUtil.getHome().create();
            ListResult total= eventReport.getNewReportEvents(accidents,incidents,fines,nearmiss,fromMonth,fromYear,toMonth,toYear,divOrgs,divgeos,working,first,count,userSessionData,false,record,Integer.parseInt(driverStatus));
            data = new EventReportData(total);
        
        } catch (Exception e) {
        	Object[] parameters = new Object[14];
    		parameters[0] = accidents;
    		parameters[1] = incidents;
    		parameters[2] = fines;
    		parameters[3] = fromMonth;
    		parameters[4] = fromYear;
    		parameters[5] = toMonth;
    		parameters[6] = toYear;
    		parameters[7] = divOrgs;
    		parameters[8] = divgeos;
    		parameters[9] = first;
    		parameters[10] = count;
    		parameters[11] = record;
    		parameters[12] = driverStatus;
    		parameters[13] = working;
            handler.handleException("getReportEvents",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getReportEvents",getClass());
        return data;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "driverview,driverviewowndata,administrator"
     */
    public DetailedEventReportData getDetailedReportEvents(boolean accidents,boolean incidents, 
    		boolean fines, boolean nearmiss, Integer fromMonth, Integer fromYear, Integer toMonth, 
    		Integer toYear, Integer[][] divOrgs, Integer[][] divgeos, String working, int first,
    		int count,UserSessionData userSessionData,String record,String driverStatus) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getDetailedReportEvents",getClass());
        DetailedEventReportData data = null;
    	
        try {
            
        	EventReport eventReport = EventReportUtil.getHome().create();
            ListResult total= eventReport.getNewReportEvents(accidents,incidents,fines,nearmiss,fromMonth,fromYear,toMonth,toYear,divOrgs,divgeos,working,first,count,userSessionData,true,record,Integer.parseInt(driverStatus));
            boolean onlyFines = !accidents && !incidents && !nearmiss && fines;
            data = new DetailedEventReportData(total,onlyFines, userSessionData.getFilter().getCorporationId());
        
        } catch (Exception e) {
        	Object[] parameters = new Object[13];
    		parameters[0] = accidents;
    		parameters[1] = incidents;
    		parameters[2] = fines;
    		parameters[3] = fromMonth;
    		parameters[4] = fromYear;
    		parameters[5] = toMonth;
    		parameters[6] = toYear;
    		parameters[7] = divOrgs;
    		parameters[8] = divgeos;
    		parameters[9] = first;
    		parameters[10] = count;
    		parameters[11] = record;
    		parameters[12] = driverStatus;
            handler.handleException("getDetailedReportEvents",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getDetailedReportEvents",getClass());
        return data;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "driverview,administrator,driverviewowndata"
     */
    public Collection getDriverViewData(String event,Integer driverId, UserSessionData userSessionData) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getDriverViewData",getClass());
        Collection data = null;
    	
        try {
        	
        	EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create(); 
            data = eventReport.getDriverViewData(event,driverId,userSessionData);
        
            if(userSessionData.getCorporationId() == CorporationData.FORESTALES) {
            	Integer[] links = DriverReportUtil.getHome().create().getDriverLink(driverId, userSessionData);
            	for(int i = 0; i < links.length; i++) {
            		if(links[i] != null) {
            			data.addAll(eventReport.getDriverViewDataForestales(event, links[i], i, userSessionData));
            		}
            	}
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
    		parameters[0] = event;
    		parameters[1] = driverId;
            handler.handleException("getDriverViewData",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getDriverViewData",getClass());
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "driverview,administrator"
     */
    public Collection getObservations(Integer eventId,UserSessionData userSessionData) throws Exception{
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getObservations",getClass());
        Collection result = null;
        
        try{
        
        	EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            result = eventReport.getObservations(eventId,userSessionData);
        
        } catch(Exception e){
        	Object[] parameters = new Object[1];
    		parameters[0] = eventId;
            handler.handleException("getObservations",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getObservations",getClass());
        return result;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ReportAccidentCountData getReportEventCount(String frommonth, Integer fromyear, 
    		String tomonth, Integer toyear, Integer[][] divOrgs, Integer[][] divGeos, 
    		String vehicleType, int i, int j, UserSessionData userSessionData,
    		int order1,int order2,int order3,int order4,Text messages) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getReportEventCount",getClass());
        Collection dataResult = new LinkedList();
        Hashtable firstLevelChart = new Hashtable();
        Hashtable secondLevelChart = new Hashtable();
        Hashtable thirddLevelChart = new Hashtable();
        
        try{
            
        	EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            
            Iterator it = eventReport.getEventCount(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,vehicleType,userSessionData,EventDataType.ACCIDENT,order1,order2,order3,order4).iterator();
            while(it.hasNext()) {
                EventCountData data = new EventCountData((Object[])it.next(),EventDataType.ACCIDENT,order1,order2,order3,order4,messages);
                dataResult.add(data);
            }

            it = eventReport.getEventCount(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,vehicleType,userSessionData,EventDataType.INCIDENT,order1,order2,order3,order4).iterator();
            while(it.hasNext()) {
                EventCountData data = new EventCountData((Object[])it.next(),EventDataType.INCIDENT,order1,order2,order3,order4,messages);
                Iterator iter = dataResult.iterator();
                boolean find = false;
                while(iter.hasNext() && !find) {
                    EventCountData element = (EventCountData)iter.next();
                    if(element.equalsData(data)) {
                        element.setIncidentCount(data.getIncidentCount());
                        element.setIncidentCost(data.getIncidentCost());
                        find = true;
                    }
                }
                if(!find) {
                    dataResult.add(data);
                }
            }
    
            it = eventReport.getEventCount(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,vehicleType,userSessionData,EventDataType.NEARMISS,order1,order2,order3,order4).iterator();
            while(it.hasNext()) {
                EventCountData data = new EventCountData((Object[])it.next(),EventDataType.NEARMISS,order1,order2,order3,order4,messages);
                Iterator iter = dataResult.iterator();
                boolean find = false;
                while(iter.hasNext() && !find) {
                    EventCountData element = (EventCountData)iter.next();
                    if(element.equalsData(data)) {
                        element.setNearmissCount(data.getNearmissCount());
                        element.setNearmissCost(data.getNearmissCost());
                        find = true;
                    }
                }
                if(!find) {
                    dataResult.add(data);
                }
            }
            /* Multas */
            it = eventReport.getEventCount(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,vehicleType,userSessionData,EventDataType.FINE,order1,order2,order3,order4).iterator();
            while(it.hasNext()) {
                EventCountData data = new EventCountData((Object[])it.next(),EventDataType.FINE,order1,order2,order3,order4,messages);
                Iterator iter = dataResult.iterator();
                boolean find = false;
                while(iter.hasNext() && !find) {
                    EventCountData element = (EventCountData)iter.next();
                    if(element.equalsData(data)) {
                        element.setFineCount(data.getFineCount());
                        element.setFineCost(data.getFineCost());
                        find = true;
                    }
                }
                if(!find) {
                    dataResult.add(data);
                }
            }

        } catch(Exception e){
    		
        	Object[] parameters = new Object[14];
    		parameters[0] = frommonth;
    		parameters[1] = fromyear;
    		parameters[2] = tomonth;
    		parameters[3] = toyear;
    		parameters[4] = divOrgs;
    		parameters[5] = divGeos;
    		parameters[6] = vehicleType;
    		parameters[7] = i;
    		parameters[8] = j;
    		parameters[9] = order1;
    		parameters[10] = order2;
    		parameters[11] = order3;
    		parameters[12] = order4;
    		parameters[13] = messages;
    		
            handler.handleException("getObservations",e,userSessionData,parameters);
        }
 
        MemoryLog.getInstance().log(MemoryLog.END,"getReportEventCount",getClass());
		return new ReportAccidentCountData(dataResult,firstLevelChart,secondLevelChart,thirddLevelChart);
     }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ReportFineCountData getReportFineCount(String frommonth, Integer fromyear, 
    		String tomonth, Integer toyear, Integer[][] divOrgs, Integer[][] divGeos, 
    		UserSessionData userSessionData) throws Exception{
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getReportFineCount",getClass());
        Object[][] result = null;
        Hashtable units = new Hashtable();
        Hashtable unitChart = new Hashtable();
        Hashtable divisionChart = new Hashtable();
        Hashtable subdivisionChart = new Hashtable();
        try{
            EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            Collection col = eventReport.getReportFine(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,userSessionData,2);
            Iterator it = col.iterator();
            int count = 0;
            while(it.hasNext()) {
                Object[] values = (Object[])it.next();
                Integer key1 = (values[3] instanceof String) ? new Integer((String)values[3]) : (Integer)values[3];
                Integer key2 = (Integer)values[7];
                Integer key3 = (Integer)values[5];
                if(units.containsKey(key1)) {
                    Hashtable divisions = (Hashtable)units.get(key1);
                    if(divisions.containsKey(key2)) {
                        Hashtable subdivisions = (Hashtable)divisions.get(key2);
                        if(subdivisions.containsKey(key3)) {
                            Object[] data = (Object[])subdivisions.get(key3);
                            updateData(data,values);
                        }else {
                            Object[] data = {values[4],values[8],values[6],new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0)};
                            count++;
                            updateData(data,values);
                            subdivisions.put(key3,data);
                        }
                    }else {
                        Hashtable subdivisions = new Hashtable();
                        Object[] data = {values[4],values[8],values[6],new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0)};
                        count++;
                        updateData(data,values);
                        subdivisions.put(key3,data);
                        divisions.put(key2,subdivisions);
                    }        
                }else {
                    Hashtable divisions = new Hashtable();
                    Hashtable subdivisions = new Hashtable();
                    Object[] data = {values[4],values[8],values[6],new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0),new Integer(0)};
                    count++;
                    updateData(data,values);
                    subdivisions.put(key3,data);
                    divisions.put(key2,subdivisions);
                    units.put(key1,divisions);
                }
            }
            result = new Object[count][11];
            Enumeration en = units.keys();
            int index = 0;
            while(en.hasMoreElements()) {
                Integer unitName = (Integer)en.nextElement();
                Hashtable divisions = (Hashtable)units.get(unitName);
                Enumeration en2 = divisions.keys();
                while(en2.hasMoreElements()) {
                    Integer divisionKey = (Integer)en2.nextElement();
                    Hashtable subdivisions = (Hashtable)divisions.get(divisionKey);
                    Enumeration en3 = subdivisions.keys();
                    while(en3.hasMoreElements()) {
                        Integer subdivisionKey = (Integer)en3.nextElement();
                        Object[] line = (Object[])subdivisions.get(subdivisionKey);
                        result[index] = line;
                        if(!unitChart.containsKey(unitName)) {
                            Object[] data = new Object[3];
                            data[0] = result[index][0];
                            data[1] = result[index][10];
                            unitChart.put(unitName,data);
                        }else {
                            Object[] data = (Object[])unitChart.get(unitName);
                            data[1] = new Integer(((Integer)data[1]).intValue() + ((Integer)result[index][10]).intValue()); 
                        }
                        if(!divisionChart.containsKey(divisionKey)) {
                            Object[] data = new Object[3];
                            data[0] = result[index][1];
                            data[1] = result[index][10];
                            divisionChart.put(divisionKey,data);
                        }else {
                            Object[] data = (Object[])divisionChart.get(divisionKey);
                            data[1] = new Integer(((Integer)data[1]).intValue() + ((Integer)result[index][10]).intValue()); 
                        }
                        if(!subdivisionChart.containsKey(subdivisionKey)) {
                            Object[] data = new Object[3];
                            data[0] = result[index][2];
                            data[1] = result[index][10];
                            subdivisionChart.put(subdivisionKey,data);
                        }else {
                            Object[] data = (Object[])subdivisionChart.get(subdivisionKey);
                            data[1] = new Integer(((Integer)data[1]).intValue() + ((Integer)result[index][10]).intValue()); 
                        }
                        index++;
                    }
                }
            }
        } catch(Exception e){
        	
        	
        	Object[] parameters = new Object[6];
        	parameters[0] = frommonth;
        	parameters[1] = fromyear;
        	parameters[2] = tomonth;
        	parameters[3] = toyear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	
            handler.handleException("getObservations",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getReportFineCount",getClass());
        return new ReportFineCountData(result,unitChart,divisionChart,subdivisionChart);
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public ReportFineTypeData getReportFineType(String frommonth, Integer fromyear, 
    		String tomonth, Integer toyear, Integer[][] divOrgs, Integer[][] divGeos, 
    		UserSessionData userSessionData) throws Exception{
        MemoryLog.getInstance().log(MemoryLog.START,"getReportFineType",getClass());
        Object[][] result = null;
        Hashtable unitChart = new Hashtable();
        Hashtable divisionChart = new Hashtable();
        Hashtable subdivisionChart = new Hashtable();
        try {            
            EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            Collection col = eventReport.getReportFine(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,userSessionData,1);
            Iterator it = col.iterator();
            result = new Object[col.size()][6];
            int i = 0;
            while(it.hasNext()) {
                Object[] values = (Object[])it.next();
                Object[] row = {values[4],values[8],values[6],values[2],values[0],values[1]};
                result[i] = row;
                if(!unitChart.containsKey(values[3]+"-"+values[2])) {
                    Object[] data = new Object[3];
                    data[0] = values[4];
                    data[1] = values[1];
                    data[2] = values[2];
                    unitChart.put(values[3]+"-"+values[2],data);
                }else {
                    Object[] data = (Object[])unitChart.get(values[3]+"-"+values[2]);
                    data[1] = new Integer(((Integer)data[1]).intValue() + ((Integer)values[1]).intValue()); 
                }
                if(!divisionChart.containsKey(values[7]+"-"+values[2])) {
                    Object[] data = new Object[3];
                    data[0] = values[8];
                    data[1] = values[1];
                    data[2] = values[2];
                    divisionChart.put(values[7]+"-"+values[2],data);
                }else {
                    Object[] data = (Object[])divisionChart.get(values[7]+"-"+values[2]);
                    data[1] = new Integer(((Integer)data[1]).intValue() + ((Integer)values[1]).intValue()); 
                }
                if(!subdivisionChart.containsKey(values[5]+"-"+values[2])) {
                    Object[] data = new Object[3];
                    data[0] = values[7];
                    data[1] = values[1];
                    data[2] = values[2];
                    subdivisionChart.put(values[5]+"-"+values[2],data);
                }else {
                    Object[] data = (Object[])subdivisionChart.get(values[5]+"-"+values[2]);
                    data[1] = new Integer(((Integer)data[1]).intValue() + ((Integer)values[1]).intValue()); 
                }
                i++;
            }
        } catch(Exception e){
        	Object[] parameters = new Object[6];
        	parameters[0] = frommonth;
        	parameters[1] = fromyear;
        	parameters[2] = tomonth;
        	parameters[3] = toyear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos; 
            handler.handleException("getReportFineType",e,userSessionData,parameters);
        }

        MemoryLog.getInstance().log(MemoryLog.END,"getReportFineType",getClass());
        return new ReportFineTypeData(result,unitChart,divisionChart,subdivisionChart);
    }

    /**
     * 
     * @param data
     * @param values
     */
    private void updateData(Object[] data, Object[] values) {
        if(values[2].equals("datatype.finetype.extserious")) {
            data[4] = values[0];
        }else if(values[2].equals("datatype.finetype.serious")) {
            data[3] = values[0];
        }else if(values[2].equals("datatype.finetype.half")) {
            data[6] = values[0];
        }else if(values[2].equals("datatype.finetype.nodata")) {
            data[8] = values[0];
        }else if(values[2].equals("datatype.finetype.others")) {
            data[7] = values[0];
        }else {
            data[5] = values[0];
        }
        data[9] = new Integer(((Integer)data[9]).intValue() + ((Integer)values[1]).intValue());
        data[10] = new Integer(((Integer)data[10]).intValue() + ((Integer)values[0]).intValue());
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public Collection getReportEventType(String frommonth, Integer fromyear, String tomonth, 
    		Integer toyear, Integer[][] divOrgs, Integer[] divGeos, String events, 
    		UserSessionData userSessionData,int type,String record,
    		int order1,int order2,int order3,int order4,Text messages) throws Exception {
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getReportEventType",getClass());
        Collection data = new LinkedList();
        
        try {
        	
            EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            if (!events.equals("")){
            	data = eventReport.getEventCountByType(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,events,userSessionData,type,record,order1,order2,order3,order4,messages);
            }
            
        } catch(Exception e){
        	Object[] parameters = new Object[14];
        	parameters[0] = frommonth;
        	parameters[1] = fromyear;
        	parameters[2] = tomonth;
        	parameters[3] = toyear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	parameters[6] = events;
        	parameters[7] = type;
        	parameters[8] = record;
        	parameters[9] = order1;
        	parameters[10] = order2;
        	parameters[11] = order3;
        	parameters[12] = order4;
        	parameters[13] = messages;
            handler.handleException("getReportEventType",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getReportEventType",getClass());
        return data;
    }

    /**
     * 
     * @param row
     * @param result
     */
    @SuppressWarnings("unused")
	private void updateDataAccident(Object[] row, Object[] result) {
        if(((Boolean)result[3]).booleanValue()) {
            row[1] = new Integer(((Integer)row[1]).intValue() + ((Integer)result[0]).intValue());
        }
        Object driverstate = result[4];
        if(driverstate != null) {
            if(driverstate.equals("datatype.driverstate.injured")) {
                row[2] = new Integer(((Integer)row[2]).intValue() + ((Integer)result[0]).intValue());
            }else if(driverstate.equals("datatype.driverstate.dead")) {
                row[5] = new Integer(((Integer)row[5]).intValue() + ((Integer)result[0]).intValue());
            }
        }
        
        row[3] = new Integer(((Integer)row[3]).intValue() + ((Integer)result[8]).intValue());
        row[4] = new Integer(((Integer)row[4]).intValue() + ((Integer)result[6]).intValue());
        row[6] = new Integer(((Integer)row[6]).intValue() + ((Integer)result[7]).intValue());
        row[7] = new Integer(((Integer)row[7]).intValue() + ((Integer)result[5]).intValue());
        row[8] = new Integer(((Integer)row[8]).intValue() + ((Integer)result[0]).intValue());
        if (result[1]==null)
        	 result[1]=new Integer(0);
        row[9] = new Integer(((Integer)row[9]).intValue() + ((Integer)result[1]).intValue());
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public Collection getReportFineByType(String frommonth, Integer fromyear, 
    		String tomonth, Integer toyear, Integer[][] divOrgs, 
    		Integer[] divGeos, String fines, UserSessionData userSessionData,
    		int order1, int order2, int order3, int order4, Text messages) throws Exception{
        
    	MemoryLog.getInstance().log(MemoryLog.START,"getReportFineByType",getClass());
        Collection result = null;
        
        try {
        
        	EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            result =  eventReport.getReportFineType(frommonth,fromyear,tomonth,toyear,divOrgs,divGeos,fines,userSessionData,order1,order2,order3,order4,messages);
        
        } catch(Exception e){
        	Object[] parameters = new Object[12];
        	parameters[0] = frommonth;
        	parameters[1] = fromyear;
        	parameters[2] = tomonth;
        	parameters[3] = toyear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	parameters[6] = fines;
        	parameters[7] = order1;
        	parameters[8] = order2;
        	parameters[9] = order3;
        	parameters[10] = order4;
        	parameters[11] = messages;
        	
            handler.handleException("getReportFineByType",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getReportFineByType",getClass());
        return result;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public Collection getReportEventsSeverity(String fromMonth, Integer fromYear, String toMonth, Integer toYear, Integer[][] divOrgs, Integer[][] divGeos, UserSessionData userSessionData, int order1, int order2, int order3,int order4,Text messages) throws Exception {
    	Collection result = new LinkedList();
    	try {
    		
            EventReportHome home = EventReportUtil.getHome();
            EventReport eventReport = home.create();
            
            result = eventReport.getReportEventsSeverity(fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,userSessionData,EventDataType.ACCIDENT,order1,order2,order3,order4,messages);
	
    	}catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = fromMonth;
        	parameters[1] = fromYear;
        	parameters[2] = toMonth;
        	parameters[3] = toYear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	parameters[6] = order1;
        	parameters[7] = order2;
        	parameters[8] = order3;
        	parameters[9] = order4;
        	parameters[10] = messages;
            handler.handleException("getReportEventsSeverity",e,userSessionData,parameters);
		}
    	return result;
    }
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     */
    public EventReportByPeriodData getReportEventsByPeriod(String fromMonth, Integer fromYear, 
    		String toMonth, Integer toYear, Integer[][] divOrgs, Integer[][] divGeos, 
    		UserSessionData userSessionData,int order1,int order2,int order3
    		,int order4,Text messages) throws Exception {
        MemoryLog.getInstance().log(MemoryLog.START,"getReportEventsByPeriod",getClass());
        int count = 0;
        Collection dataresult = null;
        try {
            if(fromYear.intValue() == toYear.intValue()) {
                if(Integer.parseInt(fromMonth) <= Integer.parseInt(toMonth)) {
                    count = 1 + Integer.parseInt(toMonth) - Integer.parseInt(fromMonth);
                }
            }else {
                if(fromYear.intValue() < toYear.intValue()) {
                    count = (13 - Integer.parseInt(fromMonth)) + Integer.parseInt(toMonth) + (12 * (toYear.intValue() - fromYear.intValue() - 1));  
                }
            }
            if(count > 0) {
                EventReportHome home = EventReportUtil.getHome();
                EventReport eventReport = home.create();
                
                dataresult = eventReport.getReportEventsByPeriod(fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,userSessionData,EventDataType.ACCIDENT,order1,order2,order3,order4,messages);
    
                Iterator itIncidents = eventReport.getReportEventsByPeriod(fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,userSessionData,EventDataType.INCIDENT,order1,order2,order3,order4,messages).iterator();
                while(itIncidents.hasNext()) {
                    EventByPeriodData data = (EventByPeriodData)itIncidents.next();
                    Iterator itElements = dataresult.iterator();
                    boolean found = false;
                    while(itElements.hasNext() && !found) {
                        EventByPeriodData element = (EventByPeriodData)itElements.next();
                        if(element.equalData(data)) {
                            element.addMonth(data);
                            found = true;
                        }
                    }
                    if(!found) {
                        dataresult.add(data);
                    }
                }
    
                Iterator itNearmiss = eventReport.getReportEventsByPeriod(fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,userSessionData,EventDataType.NEARMISS,order1,order2,order3,order4,messages).iterator();
                while(itNearmiss.hasNext()) {
                    EventByPeriodData data = (EventByPeriodData)itNearmiss.next();
                    Iterator itElements = dataresult.iterator();
                    boolean found = false;
                    while(itElements.hasNext() && !found) {
                        EventByPeriodData element = (EventByPeriodData)itElements.next();
                        if(element.equalData(data)) {
                            element.addMonth(data);
                            found = true;
                        }
                    }
                    if(!found) {
                        dataresult.add(data);
                    }
                }

                Iterator itFines = eventReport.getReportEventsByPeriod(fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,userSessionData,EventDataType.FINE,order1,order2,order3,order4,messages).iterator();
                while(itFines.hasNext()) {
                    EventByPeriodData data = (EventByPeriodData)itFines.next();
                    Iterator itElements = dataresult.iterator();
                    boolean found = false;
                    while(itElements.hasNext() && !found) {
                        EventByPeriodData element = (EventByPeriodData)itElements.next();
                        if(element.equalData(data)) {
                            element.addMonth(data);
                            found = true;
                        }
                    }
                    if(!found) {
                        dataresult.add(data);
                    }
                }
            }
        } catch(Exception e){
        	
        	
        	Object[] parameters = new Object[1];
        	parameters[0] = fromMonth;
        	parameters[1] = fromYear;
        	parameters[2] = toMonth;
        	parameters[3] = toYear;
        	parameters[4] = divOrgs;
        	parameters[5] = divGeos;
        	parameters[6] = order1;
        	parameters[7] = order2;
        	parameters[8] = order3;
        	parameters[9] = order4;
        	parameters[10] = messages;
            handler.handleException("getReportFineByType",e,userSessionData,parameters);
        }
        MemoryLog.getInstance().log(MemoryLog.END,"getReportEventsByPeriod",getClass());            
        return new EventReportByPeriodData(dataresult,count+6);
    }

    private void getElements4Levels(Hashtable firstLevel, Iterator it, String type, int count, String fromMonth, Integer fromYear) {
        while (it.hasNext()) {
            Object[] data = (Object[]) it.next();
            String key0 = String.valueOf(data[3]);
            String key1 = String.valueOf(data[5]);
            String key2 = String.valueOf(data[7]);
            String key3 = String.valueOf(data[9]);
        
            if (firstLevel.containsKey(key0)||(key3==null)) {
            	Hashtable secondLevel = (Hashtable) firstLevel.get(key0);
                	
                if (secondLevel.containsKey(key1)) {
                	Hashtable thirdLevel = (Hashtable) secondLevel.get(key1);
                	if (thirdLevel.containsKey(key2)) {
                		Hashtable fourLevel = (Hashtable) thirdLevel.get(key2);				
                		if (fourLevel.containsKey(key3)) {
                			Object[] row = (Object[]) fourLevel.get(key3);
                			row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),4)] = data[0];
                			row[count +5] = new Integer(((Integer) row[count +5]).intValue() + ((Integer) data[0]).intValue());
                		} else {
                			Object[] row = new Object[count +6];
                			row[0] = type;
                			row[1] = data[4];
                			row[2] = data[6];
                			row[3] = data[8];
                			row[4] = data[10];
                			for (int i = 0; i <= count; i++) {
                				row[i +5] = new Integer(0);
                			}
                			row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),4)] = data[0];
                			row[count +5] = new Integer(((Integer) row[count +5]).intValue() + ((Integer) data[0]).intValue());
                			fourLevel.put(key3, row);
                		}
                    }else{
                    	Hashtable fourLevel = new Hashtable();
                        Object[] row = new Object[count +6];
                        row[0] = type;
                        row[1] = data[4];
                        row[2] = data[6];
                        row[3] = data[8];
                        row[4] = data[10];
                        for (int i = 0; i <= count; i++) {
                            row[i +5] = new Integer(0);
                        }
                        row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),4)] = data[0];
                        row[count +5] = new Integer(((Integer) row[count +5]).intValue() + ((Integer) data[0]).intValue());
                        fourLevel.put(key3, row);
                        thirdLevel.put(key2, fourLevel);
                    }
                } else {
                	Hashtable thirdLevel = new Hashtable();
                    Hashtable fourLevel = new Hashtable();
                    Object[] row = new Object[count +6];
                    row[0] = type;
                    row[1] = data[4];
                    row[2] = data[6];
                    row[3] = data[8];
                    row[4] = data[10];
                    for (int i = 0; i <= count; i++) {
                        row[i +5] = new Integer(0);
                    }
                    row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),4)] = data[0];
                    row[count +5] = new Integer(((Integer) row[count +5]).intValue() + ((Integer) data[0]).intValue());
                    fourLevel.put(key3, row);
                    thirdLevel.put(key2, fourLevel);
                    secondLevel.put(key1, thirdLevel);
    
                }
            } else {
                Hashtable secondLevel = new Hashtable();
                Hashtable thirdLevel = new Hashtable();
                Hashtable fourLevel = new Hashtable();
                Object[] row = new Object[count +6];
                row[0] = type;
                row[1] = data[4];
                row[2] = data[6];
                row[3] = data[8];
                row[4] = data[10];
                for (int i = 0; i <= count; i++) {
                    row[i +5] = new Integer(0);
                }
                row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),4)] = data[0];
                row[count +5] = new Integer(((Integer) row[count +5]).intValue() + ((Integer) data[0]).intValue());
                fourLevel.put(key3, row);
                thirdLevel.put(key2, fourLevel);
                secondLevel.put(key1, thirdLevel);
                firstLevel.put(key0, secondLevel);
            }
        }	
    }
    
    private void getElements3Levels(Hashtable firstLevel, Iterator it, String type, int count, String fromMonth, Integer fromYear) {
        while (it.hasNext()) {
           Object[] data = (Object[]) it.next();
           String key0 = String.valueOf(data[3]);
           String key1 = String.valueOf(data[5]);
           String key2 = String.valueOf(data[7]);
           if (firstLevel.containsKey(key0)) {
               	Hashtable secondLevel = (Hashtable) firstLevel.get(key0);
               	if (secondLevel.containsKey(key1)) {
               		Hashtable thirdLevel = (Hashtable) secondLevel.get(key1);				
               		if (thirdLevel.containsKey(key2)) {
            			Object[] row = (Object[]) thirdLevel.get(key2);
            			row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),3)] = data[0];
            			row[count +4] = new Integer(((Integer) row[count +4]).intValue() + ((Integer) data[0]).intValue());
            		} else {
            			Object[] row = new Object[count +5];
            			row[0] = type;
            			row[1] = data[4];
            			row[2] = data[6];
            			row[3] = data[8];
            			for (int i = 0; i <= count; i++) {
            				row[i +4] = new Integer(0);
            			}
            			row[getIndex(((Integer) data[1]).intValue(),
            					((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),3)] = data[0];
            			row[count +4] = new Integer(((Integer) row[count +4]).intValue() + ((Integer) data[0]).intValue());
            			thirdLevel.put(key2, row);
            		}
                }else{
                	Hashtable thirdLevel = new Hashtable();
                    Object[] row = new Object[count +5];
                    row[0] = type;
                    row[1] = data[4];
                    row[2] = data[6];
                    row[3] = data[8];
                    for (int i = 0; i <= count; i++) {
                        row[i +4] = new Integer(0);
                    }
                    row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),3)] = data[0];
                    row[count +4] = new Integer(((Integer) row[count +4]).intValue() + ((Integer) data[0]).intValue());
                    thirdLevel.put(key2, row);
                    secondLevel.put(key1, thirdLevel);
                }
            } else {
            	Hashtable secondLevel = new Hashtable();
                Hashtable thirdLevel = new Hashtable();
                Object[] row = new Object[count +5];
                row[0] = type;
                row[1] = data[4];
                row[2] = data[6];
                row[3] = data[8];
                for (int i = 0; i <= count; i++) {
                    row[i +4] = new Integer(0);
                }
                row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),3)] = data[0];
                row[count +4] = new Integer(((Integer) row[count +4]).intValue() + ((Integer) data[0]).intValue());
                thirdLevel.put(key2, row);
                secondLevel.put(key1, thirdLevel);
                firstLevel.put(key0, secondLevel);

            }
        }	
    }
    
    private void getElements2Levels(Hashtable firstLevel, Iterator it, String type, int count, String fromMonth, Integer fromYear) {
        while (it.hasNext()) {
            Object[] data = (Object[]) it.next();
            String key0 = String.valueOf(data[3]);
            String key1 = String.valueOf(data[5]);
    
        	if (firstLevel.containsKey(key0)) {
        		Hashtable secondLevel = (Hashtable) firstLevel.get(key0);				
        		if (secondLevel.containsKey(key1)) {
        			Object[] row = (Object[]) secondLevel.get(key1);
        			row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),2)] = data[0];
        			row[count +3] = new Integer(((Integer) row[count +3]).intValue() + ((Integer) data[0]).intValue());
        		} else {
        			Object[] row = new Object[count +4];
        			row[0] = type;
        			row[1] = data[4];
        			row[2] = data[6];
        			for (int i = 0; i <= count; i++) {
        				row[i +3] = new Integer(0);
        			}
        			row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),2)] = data[0];
        			row[count +3] = new Integer(((Integer) row[count +3]).intValue() + ((Integer) data[0]).intValue());
        			secondLevel.put(key1, row);
        		}
            }else{
            	Hashtable secondLevel = new Hashtable();
                Object[] row = new Object[count +4];
                row[0] = type;
                row[1] = data[4];
                row[2] = data[6];
                for (int i = 0; i <= count; i++) {
                    row[i +3] = new Integer(0);
                }
                row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),2)] = data[0];
                row[count +3] = new Integer(((Integer) row[count +3]).intValue() + ((Integer) data[0]).intValue());
                secondLevel.put(key1, row);
                firstLevel.put(key0, secondLevel);
            }
            
        }	
    }

    private void getElements1Levels(Hashtable firstLevel, Iterator it, String type, int count, String fromMonth, Integer fromYear) {
    	while (it.hasNext()) {
            Object[] data = (Object[]) it.next();
            String key0 = String.valueOf(data[3]);    				
             if (firstLevel.containsKey(key0)) {
             	Object[] row = (Object[]) firstLevel.get(key0);
             	row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),1)] = data[0];
             	row[count +2] = new Integer(((Integer) row[count +2]).intValue() + ((Integer) data[0]).intValue());
       		} else {
            	Object[] row = new Object[count +3];
            	row[0] = type;
            	row[1] = data[4];
            	for (int i = 0; i <= count; i++) {
            		row[i +2] = new Integer(0);
                }
                row[getIndex(((Integer) data[1]).intValue(),((Integer) data[2]).intValue(), Integer.parseInt(fromMonth), fromYear.intValue(),1)] = data[0];
                row[count +2] = new Integer(((Integer) row[count +2]).intValue() + ((Integer) data[0]).intValue());
                firstLevel.put(key0, row);
           }     
        }	
    }

    private int getIndex(int month,int year,int fromMonth,int fromYear,int levels) {
        if(year == fromYear) {
            return month - fromMonth + levels+1;
        }else {
            return (12 - fromMonth) + month + 12 * (year - fromYear - 1) + levels+1;
        }
    }

   
   /**
    * @ejb.interface-method
    * @ejb.permission role-name = "eventview,administrator,driveredit,driverviewowndata"
    * @throws Exception
    */
  public Integer getEventsPoints(Integer driver, UserSessionData userSessionData) throws Exception {
	   try {
		   return EventReportUtil.getHome().create().getEventsPoints(driver, userSessionData);
	   }catch (Exception e) {
		   Object[] p = {driver};
		   handler.handleException("getEventsPoints", e, userSessionData, p);
	   }
	   return new Integer(0);
  }
   
   
  /**
   * @throws InvalidDataException
   * @throws DeslogedException
   * @throws CommunicationProblemException
   * @ejb.interface-method
   * @ejb.permission role-name = "administrator,eventcreate,vehicleview,drivervehicleview,driverodometer"
   */ 
  public ListResult findTravelList(String value, int status, UserSessionData userRequest,int first,int count) throws Exception {
	   try {
		   return EventReportUtil.getHome().create().findTravelList(value, status, userRequest, first, count);
	   }catch (Exception e) {
		   Object[] p = {value};
		   handler.handleException("findTravelList", e, userRequest, p);
	   }
	   return null;
  }

  /**
   * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
   * @ejb.interface-method
   * @param id
   * @param userSessionData
   * @return
   * @throws InvalidDataException
   * @throws CommunicationProblemException
   */
  public TravelDataType findTravelByPrimaryKey(Integer id,UserSessionData userSessionData) throws Exception {
	   try {
		   return EventReportUtil.getHome().create().findTravelByPrimaryKey(id, userSessionData);
	   }catch (Exception e) {
		   Object[] p = {id};
		   handler.handleException("findTravelByPrimaryKey", e, userSessionData, p);
	   }
	   return null;
  }

	  /**
	   * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
	   * @ejb.interface-method
	   * @param id
	   * @param userSessionData
	   * @return
	   * @throws InvalidDataException
	   * @throws CommunicationProblemException
	   */
  	public Collection getTravelCheckPoints(Integer travel, UserSessionData userSessionData) throws Exception {
		try{	
			return EventReportUtil.getHome().create().getTravelCheckPoints(travel,userSessionData);
		}catch (Exception e) {
			Object[] parameters = {travel};
			   handler.handleException("getTravelCheckPoints", e, userSessionData, parameters);
		}
	    return null;    	
  	}

    /**
     * @ejb.permission role-name = "administrator,viewrecomendation"
     * @ejb.interface-method
     */
    public EventDataType getEventRecomendation(Integer event, UserSessionData userSessionData) throws Exception {
		try{	
			return EventReportUtil.getHome().create().getEventRecomendation(event,userSessionData);
		}catch (Exception e) {
			Object[] parameters = {event};
			   handler.handleException("getEventRecomendation", e, userSessionData, parameters);
		}
	    return null;    	
    }

    /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "eventview,administrator"
	 */
    public Collection getEventEvitability(Integer driver, UserSessionData userSessionData) throws Exception {
		try{	
			return EventReportUtil.getHome().create().getEventEvitability(driver,userSessionData);
		}catch (Exception e) {
			Object[] parameters = {driver};
			   handler.handleException("getEventEvitability", e, userSessionData, parameters);
		}
	    return null;    	
    }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "fleetinsertdata,administrator"
	 */
    public HashMap<String, int[]> getInsertedEvents(Integer fleet, Integer period, Integer year, boolean quarter, UserSessionData userSessionData) throws Exception {
		try{	
			return EventReportUtil.getHome().create().getInsertedEvents(fleet,period,year,quarter,userSessionData);
		}catch (Exception e) {
			Object[] parameters = {fleet,period,year,quarter};
			   handler.handleException("getInsertedEvents", e, userSessionData, parameters);
		}
	    return null;    	
    }

	/**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventcreate,drivercreateevent,eventsupervise,eventupdate"
	 *@return Asigned Primery Key
	 */
    public boolean existEvent(Integer event, Integer driver, Integer vehicle, String plate, Date date, String type, int eventType, UserSessionData userSessionData) throws Exception {
		try{	
			return EventReportUtil.getHome().create().existEvent(event,driver,vehicle,plate,date,type,eventType,userSessionData);
		}catch (Exception e) {
			Object[] p = {event,driver,vehicle,plate,date,type,eventType};
			handler.handleException("existEvent", e, userSessionData, p);
		}
	    return false;    	
    }

	/**
	 * @throws Exception 
	 * @ejb.permission role-name ="administrator,query"
	 * @ejb.interface-method 
	 */
	public Collection getFinePoints(Date from, Date to, Integer[][] divOrgs, Integer[][] divGeos, String types, int status, UserSessionData userSessionData) throws Exception {
		try{
			return EventReportUtil.getHome().create().getFinePoints(from,to,divOrgs,divGeos,types,status,userSessionData);
		}catch (Exception e) {
			Object[] p = {from,to,divOrgs,divGeos,types,status};
			handler.handleException("getFinePoints", e, userSessionData, p);
		}
	    return new LinkedList();    	
    }

  	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventedit"
     * @throws Exception
   	 */
	public Collection<Object[]> getFiles(Integer event, UserSessionData userSessionData) throws Exception {
		Collection<Object[]> files = new LinkedList<Object[]>();
		try {
			return EventReportUtil.getHome().create().getFiles(event,userSessionData);
		}catch (Exception e) {
			Object[] p = {event};
			handler.handleException("getFiles", e, userSessionData, p);
        }
		return files;
	}

	/**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,reparations"
	 * @throws Exception
	 */
	public ReparationData findReparationByPrimaryKey(Integer reparationId, UserSessionData userSessionData) throws Exception {
		try {
			return EventReportUtil.getHome().create().findReparationByPrimaryKey(reparationId,userSessionData);
		} catch(Exception e){
			Object[] p = {reparationId};
			handler.handleException("findReparationByPrimaryKey", e, userSessionData, p);
        }
		return null;
	}
}
