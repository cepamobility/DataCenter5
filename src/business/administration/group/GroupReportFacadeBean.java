/*
 * Created on 28-oct-2005
 */
package business.administration.group;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import persistence.administration.group.GroupReport;
import persistence.administration.group.GroupReportHome;
import persistence.administration.group.GroupReportUtil;
import business.SystemAccess;
import business.util.ExceptionHandler;

import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupMainAttributes;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.util.ListResult;
 
/**
 * @ejb.bean name = "GroupReportFacade" jndi-name = "ejb/GroupReportFacade" type =
 *           "Stateless" transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "GroupReport" ref-name = "ejb/GroupReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/GroupReport" ref-name = "GroupReport"
 *  
 * @ejb.util generate="physical"
 *  
 */
public abstract class GroupReportFacadeBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(GroupReportFacadeBean.class);
	
	/**
	 * Default create method
	 * @throws CreateException
	 * @ejb.create-method
     * @ejb.permission role-name = "administrator,viewgrouplist"
	 */
	public void ejbCreate() throws CreateException {	}

	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,viewgrouplist"
	 * @return Collection of UserData
	 * @throws Exception
	 */
	public ListResult findList(String name,int first,int count,UserSessionData userSessionData) throws Exception {
    	try{
    		GroupReportHome home = GroupReportUtil.getHome();
            GroupReport groupReport = home.create();
            return groupReport.findList(name,first,count,userSessionData);
    	}catch (Exception e) {
    		Object[] parameters = new Object[3];
    		parameters[0] = name;
    		parameters[1] = first;
    		parameters[2] = count;
            handler.handleException("findList",e,userSessionData,parameters);
    	}
    	return null;
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,viewgrouplist"
     * @return Collection of UserData
     * @throws Exception
     */
    public ListResult findSearchList(String name,int first,int count,UserSessionData userSessionData,String groups) throws Exception {
        try{
            GroupReportHome home = GroupReportUtil.getHome();
            GroupReport groupReport = home.create();
            return groupReport.findSearchList(name,first,count,userSessionData,groups);
        }catch (Exception e) {
        	Object[] parameters = new Object[4];
        	parameters[0] = name;
        	parameters[1] = first;
        	parameters[2] = count;
        	parameters[3] = groups;
            handler.handleException("findSearchList",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupedit"
     * @return AbstractGroup Data
     * @throws Exception
     */
    public GroupData findGroup(Integer groupId,UserSessionData userSessionData) throws Exception {
        try{
            GroupReportHome home = GroupReportUtil.getHome();
            GroupReport groupReport = home.create();
            return groupReport.findGroup(groupId,userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = groupId;       	
        	                                 
            handler.handleException("findGroup",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupupdate"
     * @return AbstractGroup Data
     * @throws Exception
     */
    public GroupMainAttributes findGroupAttributes(Integer groupId,UserSessionData userSessionData) throws Exception {
        try{
            GroupReportHome home = GroupReportUtil.getHome();
            GroupReport groupReport = home.create();
            return groupReport.findGroupAttributes(groupId,userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = groupId;
            handler.handleException("findGroupAttributes",e,userSessionData,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupedit"
     * @return AbstractGroup Data
     * @throws Exception
     */
    public Collection findUsersGroup(Integer groupId,UserSessionData userSessionData) throws Exception {
        Collection users = new LinkedList();
        try {
            GroupReportHome home = GroupReportUtil.getHome();
            GroupReport groupReport = home.create();
            return groupReport.findUsersGroup(groupId,userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = groupId;
            handler.handleException("findUsersGroup",e,userSessionData,parameters);
        }
        return users;
    }
       
    
}
