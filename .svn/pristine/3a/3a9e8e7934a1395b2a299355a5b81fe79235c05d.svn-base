
package persistence.administration.group;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.administration.group.tables.AbstractGroup;
import persistence.administration.tables.UserGroup;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;
import persistence.util.Util;

import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupMainAttributes;
import communication.administration.user.UserSessionData;
import communication.util.ListResult;

/**
 * @ejb.bean name="GroupReport" display-name="Name for GroupReport"
 *          description="Description for GroupReport"
 *          jndi-name="ejb/GroupReport" type="Stateless"
 *  
 */
public abstract class GroupReportBean implements SessionBean {
	
    ExceptionHandler handler = new ExceptionHandler(GroupReportBean.class);
    
    /**
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,viewgrouplist"
     * @throws CreateException
     */
    public void ejbCreate() throws CreateException{
    }
    
    /**
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,viewgrouplist"
     */
    public ListResult findList(String name, int first, int count, UserSessionData userSessionData) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            
            /*
             * Obtengo la cantidad de registros total. 
             */
            Query query = session.createQuery("SELECT COUNT(*) FROM AbstractGroup WHERE" +
                    " corporation ="+userSessionData.getFilter().getCorporationId()+
                    " AND lower(name) LIKE lower(:name)");
            query.setParameter("name","%"+name+"%");
            Integer total =  new Integer(((Long) query.uniqueResult()).intValue());
    
            /*
             * 
             */
            query = session.createQuery("SELECT group FROM AbstractGroup as group WHERE" +
                    " corporation ="+userSessionData.getFilter().getCorporationId()+
                    " AND lower(name) LIKE lower(:name)");
            query.setParameter("name","%"+name+"%");
            if(count != -1) {
                if(total.intValue() < first) {
                    if(total.intValue() % count == 0) {
                        first = total.intValue() - count;
                    }else {
                        first = total.intValue() - (total.intValue() % count);
                    }
                }
                query.setFirstResult(first);
                query.setMaxResults(count);
            }
            Collection result = new LinkedList();
            List list = query.list();
            if (list != null){
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                AbstractGroup group = (AbstractGroup)it.next();
	                result.add(group.getMainAttributes());
	            }
            }
            return new ListResult(result, total.intValue());
        }catch (Exception e) {
        	Object[] parameters = {name,first,count};
            handler.getException(e,"findList",userSessionData,parameters);
        }
        return null;
    }

    /**
     * Parametros:<br />
     *   <b>name:</b> nombre del grupo<br />
     *   
     *   <b>first:</b> define el salto de los primeros registros.<br />
     *             ej: first = 30, el primer valor de la lista sera 31.<br />
     *             
     *   <b>count:</b> define la cantidad de objetos del resultado incluidos en la lista<br />
     *             ej: si el valor de count es de 10, me dara los primeros 10 o menos valores que obtenga de la consulta.<br />
     *             
     *   <b>groups:</b> lista de id de los grupos para excluir, separados por coma, ej: "1,5,8,10"? supongo!!!<br />
     *    
     * 
     * @throws Exception
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,viewgrouplist"
     */
    public ListResult findSearchList(String name, int first, int count, UserSessionData userSessionData,String groups) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            
            /*
             * Cuenta la cantidad de grupos segun el valor del nombre del grupo pasado por parametro.
             */
            String queryStr = "SELECT COUNT(*) FROM AbstractGroup WHERE" +
                    " corporation ="+userSessionData.getFilter().getCorporationId()+
                    " AND lower(name) LIKE lower(:name) ";
            if(!Util.empty(groups)) {
                queryStr += "AND id NOT IN ("+groups+")";
            }
            Query query = session.createQuery(queryStr);
            query.setParameter("name","%"+name+"%");
            Integer total = new Integer(((Long) query.uniqueResult()).intValue());
            
            /*
             * Similar a la anterior pero en este caso obtengo la lista de grupos de la consulta hsql.
             */
            queryStr = "SELECT gr FROM AbstractGroup gr WHERE" +
                    " gr.corporation ="+userSessionData.getFilter().getCorporationId()+
                    " AND lower(gr.name) LIKE lower(:name) ";
            if(!Util.empty(groups)) {
                queryStr += "AND gr.id NOT IN ("+groups+")";
            }
            query = session.createQuery(queryStr);
            query.setParameter("name","%"+name+"%");
            
            if(count != -1) {
                if(total.intValue() < first) {
                    if(total.intValue() % count == 0) {
                        first = total.intValue() - count;
                    }else {
                        first = total.intValue() - (total.intValue() % count);
                    }
                }
                query.setFirstResult(first);
                query.setMaxResults(count);
            }
            Collection result = new LinkedList();
            Iterator it = query.list().iterator();
            while (it.hasNext()) {
                AbstractGroup group = (AbstractGroup)it.next();
                result.add(group.getMainAttributes());
            }
            return new ListResult(result, total.intValue());
        }catch (Exception e) {
        	Object[] parameters = {name,first,count};
            handler.getException(e,"findSearchList",userSessionData,parameters);
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
            Session session = HibernateUtil.currentSession();
            AbstractGroup group = (AbstractGroup)session.load(AbstractGroup.class,groupId);
            return group.getData();
        }catch (Exception e) {
        	Object[] parameters = {groupId};
            handler.getException(e,"findGroup",userSessionData,parameters);
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
            Session session = HibernateUtil.currentSession();
            AbstractGroup group = (AbstractGroup)session.load(AbstractGroup.class,groupId);
            return group.getMainAttributes();
        }catch (Exception e) {
        	Object[] parameters = {groupId};
            handler.getException(e,"findGroupAttributes",userSessionData,parameters);
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
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT usergroup FROM UserGroup usergroup WHERE usergroup.pk.group = "+String.valueOf(groupId));
            Iterator iter = query.list().iterator();
            while (iter.hasNext()) {
                users.add(((UserGroup) iter.next()).getPk().getUser().getPrincipalAttributes());
            }
        }catch (Exception e) {
        	Object[] parameters = {groupId};
            handler.getException(e,"findUsersGroup",userSessionData,parameters);
        }
        return users;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupdelete"
     * @throws Exception
     */
    public String validateDelete(Integer group,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();

            Query query = session.createQuery("SELECT count(*) FROM UserGroup WHERE pk.group = "+String.valueOf(group));
            if(((Long)query.uniqueResult()).intValue() != 0) {
                return "tiene usuarios asociados";
            }

            query = session.createQuery("SELECT count(*) FROM GroupElement WHERE element = "+String.valueOf(group));
            if(((Long)query.uniqueResult()).intValue() != 0) {
                return "es parte de otro grupo";
            }
        }catch (Exception e) {
        	Object[] parameters = {group};
            handler.getException(e,"validateDelete",userSessionData,parameters);
        }
        return null;
    }

    /**
     * Obtiene un grupo para ese contratista, tal que ese grupo
     * tenga solamente ese contratista como divorg11.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupedit, groupupdate"
     * @return AbstractGroup Data
     * @throws Exception
     */
    public Integer getContractorGroup(String contractorId, UserSessionData userSessionData) throws Exception{
    	Integer groupId = null;
    	 try {
             Session session = HibernateUtil.currentSession();
             
             Query query = session.createSQLQuery(" SELECT DISTINCT groupelement FROM grouplines " + 
            		 " WHERE divorg11 = " + contractorId + 
            		 " AND groupelement IN ( " +
            		 		" SELECT DISTINCT groupelement " +
            		 		" FROM grouplines " +
            		 		" GROUP BY groupelement " +
            		 		" HAVING COUNT(*) = 1 ) ").addScalar("groupelement",Hibernate.INTEGER);
             
             List listGroups = query.list();
             if (listGroups != null){
            	 Iterator iter = listGroups.iterator();
            	 
            	 // Tomo solo el primer grupo que tenga únicamente a ese contratista:
            	 if (iter.hasNext()) {
            		 groupId = (Integer)iter.next();
            	 }
             }
         }catch (Exception e) {
         	Object[] parameters = {contractorId};
             handler.getException(e,"getContractorGroup",userSessionData,parameters);
         }
         return groupId;
    }

}
