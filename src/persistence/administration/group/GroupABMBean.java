/*
 * Created on 27-oct-2005
 */
package persistence.administration.group;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.SessionBean;

import org.hibernate.classic.Session;

import persistence.administration.group.tables.AbstractGroup;
import persistence.administration.group.tables.ComposedGroup;
import persistence.administration.group.tables.GroupElement;
import persistence.administration.group.tables.GroupLine;
import persistence.administration.group.tables.IndividualGroup;
import persistence.administration.tables.UserGroup;
import persistence.administration.tables.UserGroupPK;
import persistence.administration.user.tables.User;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;

import communication.administration.group.tables.GroupLineData;
import communication.administration.group.tables.GroupMainAttributes;
import communication.administration.user.UserSessionData;

/**
 * @ejb.bean name="GroupABM" display-name="Name for GroupABM"
 *      description="Description for GroupABM" jndi-name="ejb/GroupABM"
 *      type="Stateless"
 *  
 */
public abstract class GroupABMBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(GroupABMBean.class);
    
    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,groupcreate,resetpassword"
     */
    public void ejbCreate() throws javax.ejb.CreateException { }

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,groupcreate,resetpassword"
	 */
	public Integer createGroup(GroupMainAttributes data, UserSessionData userSessionData) throws Exception {
		try{
            Session session=HibernateUtil.currentSession();			
			
            if(data.getType().equals(GroupMainAttributes.INDIVIDUAL)) {
                IndividualGroup group = new IndividualGroup(data,session,userSessionData.getFilter().getCorporationId());
                return (Integer)session.save(group);
            }else {
                ComposedGroup group = new ComposedGroup(data,session,userSessionData.getFilter().getCorporationId());
                return (Integer)session.save(group);
            }
            
		} catch (Exception e) {
        	Object[] parameters = {data};
            handler.getException(e,"createGroup",userSessionData,parameters);
        }
        return null;
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void updateGroupData(GroupMainAttributes data, UserSessionData userSessionData) throws Exception {
        try{
            Session session=HibernateUtil.currentSession();         
            AbstractGroup group = (AbstractGroup)session.load(AbstractGroup.class,data.getId()); 
            group.setName(data.getName());    
            group.setDescription(data.getDescription());    
            group.setPeriod(data.getPeriod());    
            group.setType(data.getType());    
            session.update(group);
        } catch (Exception e) {
        	Object[] parameters = {data};
            handler.getException(e,"updateGroupData",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupdelete"
     */
    public void deleteGroup(Integer groupId,UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            AbstractGroup group = (AbstractGroup)session.load(AbstractGroup.class,groupId);
            session.delete(group);
        } catch (Exception e) {
        	Object[] parameters = {groupId};
            handler.getException(e,"deleteGroup",userSessionData,parameters);
        } 
    }
    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate,resetpassword"
     */
    public void addLine(Integer id, GroupLineData groupLine, UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            IndividualGroup group = (IndividualGroup)session.load(IndividualGroup.class,id); 
            group.getLines().add(new GroupLine(groupLine,group,userSessionData,session));
            session.update(group);
        } catch (Exception e) {
        	Object[] parameters = {id,groupLine};
            handler.getException(e,"addLine",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void deleteLine(Integer id, Collection lines, UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            IndividualGroup group = (IndividualGroup)session.load(IndividualGroup.class,id); 
            Iterator it = group.getLines().iterator();
            while(it.hasNext()) {
                if(lines.contains(String.valueOf(((GroupLine)it.next()).getId()))) {
                    it.remove();
                }
            }
            session.update(group);
        } catch (Exception e) {
        	Object[] parameters = {id,lines};
            handler.getException(e,"deleteLine",userSessionData,parameters);
        } 
    }
    
    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void addGroup(Integer groupId, Collection groups,UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            ComposedGroup group = (ComposedGroup)session.load(ComposedGroup.class,groupId); 
            Iterator it = groups.iterator();
            while(it.hasNext()) {
                AbstractGroup groupElement = (AbstractGroup)session.load(AbstractGroup.class,new Integer((String)it.next()));
                group.getGroups().add(new GroupElement(group,groupElement));
            }
            session.update(group);
        } catch (Exception e) {
        	Object[] parameters = {groupId,groups};
            handler.getException(e,"addGroup",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void deleteGroup(Integer groupId, Collection groups,UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            ComposedGroup group = (ComposedGroup)session.load(ComposedGroup.class,groupId); 
            Iterator it = group.getGroups().iterator();
            while(it.hasNext()) {
                AbstractGroup groupElement = ((GroupElement)it.next()).getElement();
                if(groups.contains(String.valueOf(groupElement.getId()))) {
                    it.remove();
                }
            }
            session.update(group);
        } catch (Exception e) {
        	Object[] parameters = {groupId,groups};
            handler.getException(e,"deleteGroup",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate,resetpassword"
     */
    public void addUserGroup(Integer groupId, String loginName,UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            AbstractGroup group = (AbstractGroup)session.load(AbstractGroup.class,groupId); 
            User user = (User)session.load(User.class,(String)loginName);
            session.save(new UserGroup(user,group));
        } catch (Exception e) {
        	Object[] parameters = {groupId,loginName};
            handler.getException(e,"addUserGroup",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,groupupdate"
     */
    public void deleteUserGroup(Integer groupId, String loginName,UserSessionData userSessionData) throws Exception {
        try{    
            Session session=HibernateUtil.currentSession();         
            AbstractGroup group = (AbstractGroup)session.load(AbstractGroup.class,groupId); 
            User user = (User)session.load(User.class,(String)loginName);
            UserGroup userGroup = (UserGroup) session.load(UserGroup.class,new UserGroupPK(user,group));
            session.delete(userGroup);
        } catch (Exception e) {
        	Object[] parameters = {groupId,loginName};
            handler.getException(e,"deleteUserGroup",userSessionData,parameters);
        } 
    }
}
