/*
 * Created on 27-oct-2005
 */
package business.administration.group;

import java.util.Collection;
import java.util.Iterator;

import persistence.administration.group.GroupABM;
import persistence.administration.group.GroupABMHome;
import persistence.administration.group.GroupABMUtil;
import persistence.administration.group.GroupReport;
import persistence.administration.group.GroupReportHome;
import persistence.administration.group.GroupReportUtil;
import business.util.ExceptionHandler;

import communication.administration.group.tables.GroupLineData;
import communication.administration.group.tables.GroupMainAttributes;
import communication.administration.user.UserSessionData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.exception.InvalidDeleteException;

/**
 * 
 * @ejb.bean name = "GroupABMFacade" jndi-name = "ejb/GroupABMFacade" type =
 *           "Stateless" transaction-type = "Container"  
 * 
 * @ejb.ejb-ref ejb-name = "GroupABM" ref-name = "ejb/GroupABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/GroupABM" ref-name = "GroupABM"
 * 
 * @ejb.ejb-ref ejb-name = "GroupReport" ref-name = "ejb/GroupReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/GroupReport" ref-name = "GroupReport"
 * 
 * @ejb.util generate="physical"
 * 
 */
public abstract class GroupABMFacadeBean  implements javax.ejb.SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(GroupABMFacadeBean.class);

	/**
	 * Create method
	 * @ejb.permission role-name = "administrator,groupcreate"
	 * @ejb.create-method
	 */
	public void ejbCreate() throws javax.ejb.CreateException {

	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupcreate"
	 * @param data
	 * @throws Exception 
	 */
	public Integer createGroup(GroupMainAttributes group,UserSessionData userSessionData) throws Exception {
        try{	
            if(userSessionData == null) {
                throw new DeslogedException("createGroup","userSessionData = null");
            }
            if(group == null) {
                throw new InvalidDataException("createGroup","group = null");
            }
		
            String valid = group.validate();
            if (valid != null) {
                throw new InvalidDataException("createGroup",valid);
            }

            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();		
            return groupABM.createGroup(group, userSessionData);
	
        } catch (Exception exception) {
        	Object [] parameters = new Object[1];
        	parameters[0] = group;
            handler.handleException("createGroup",exception, userSessionData, parameters);
        } 
        return null;
	}

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     * @param data
     * @throws Exception 
     */
    public void updateGroupData(GroupMainAttributes group,UserSessionData userSessionData) throws Exception {
        try{    
            if(userSessionData == null) {
                throw new DeslogedException("updateGroup","userSessionData = null");
            }
            if(group == null) {
                throw new InvalidDataException("updateGroup","group = null");
            }
        
            String valid = group.validate();
            if (valid != null) {
                throw new InvalidDataException("updateGroup",valid);
            }

            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();      
            groupABM.updateGroupData(group, userSessionData);
    
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = group;
            handler.handleException("updateGroupData",exception,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupdelete"
     */
    public void deleteGroups(Collection groups,UserSessionData userSessionData) throws Exception {
        try{    

            GroupReportHome reportHome = GroupReportUtil.getHome();
            GroupReport groupReport= reportHome.create();

            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            Iterator it = groups.iterator();
            while(it.hasNext()) {
                Integer groupId = new Integer((String)it.next());
                String delete = groupReport.validateDelete(groupId,userSessionData);
                if(delete != null) {
                    throw new InvalidDeleteException(delete,"");
                }
                groupABM.deleteGroup(groupId,userSessionData);
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = groups;
            handler.handleException("deleteGroups",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void addLine(Integer id, GroupLineData groupLine, UserSessionData userSessionData) throws Exception {
        try{    
            if(userSessionData == null) {
                throw new DeslogedException("addLine","userSessionData = null");
            }
            if(id == null) {
                throw new InvalidDataException("addLine","group = null");
            }
        
            String valid = groupLine.validate();
            if (valid != null) {
                throw new InvalidDataException("addLine",valid);
            }

            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();      
            groupABM.addLine(id,groupLine,userSessionData);
    
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = id;
        	parameters[1] = groupLine;
        	
            handler.handleException("addLine",exception,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void addLines(Integer id, Collection groupLines, UserSessionData userSessionData) throws Exception {
        try{    
            if(userSessionData == null) {
                throw new DeslogedException("addLine","userSessionData = null");
            }
            if(id == null) {
                throw new InvalidDataException("addLine","group = null");
            }
        

            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();      
            Iterator it = groupLines.iterator();
            while(it.hasNext()) {
                GroupLineData groupLine = (GroupLineData)it.next();
                String valid = groupLine.validate();
                if (valid != null) {
                    throw new InvalidDataException("addLine",valid);
                }
                groupABM.addLine(id,groupLine,userSessionData);
            }
    
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = id;
        	parameters[1] = groupLines;
            handler.handleException("addLines",exception,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void deleteLine(Integer id, Collection lines, UserSessionData userSessionData) throws Exception {
        try{    
            if(userSessionData == null) {
                throw new DeslogedException("deleteLine","userSessionData = null");
            }
            if(id == null) {
                throw new InvalidDataException("deleteLine","group = null");
            }
        
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();      
            groupABM.deleteLine(id,lines,userSessionData);
    
        } catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = id;
        	parameters[1] = lines;
            handler.handleException("deleteLine",exception,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void addUserGroup(Integer groupId, Collection users,UserSessionData userSessionData) throws Exception {
        try{    
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            Iterator it = users.iterator();
            while(it.hasNext()) {
                groupABM.addUserGroup(groupId,(String)it.next(),userSessionData);
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = groupId;
        	parameters[1] = users;
            handler.handleException("addUserGroup",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void addGroup(Integer groupId, Collection groups,UserSessionData userSessionData) throws Exception {
        try{    
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            groupABM.addGroup(groupId,groups,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = groupId;
        	parameters[1] = groups;
            handler.handleException("addGroup",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void deleteGroup(Integer groupId, Collection groups,UserSessionData userSessionData) throws Exception {
        try{    
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            groupABM.deleteGroup(groupId,groups,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = groupId;
        	parameters[1] = groups;
            handler.handleException("deleteGroup",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void deleteUserGroup(Integer groupId, Collection users,UserSessionData userSessionData) throws Exception {
        try{    
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            Iterator it = users.iterator();
            while(it.hasNext()) {
                groupABM.deleteUserGroup(groupId,(String)it.next(),userSessionData);
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = groupId;
        	parameters[1] = users;
            handler.handleException("deleteUserGroup",e,userSessionData,parameters);
        } 
    }
}
