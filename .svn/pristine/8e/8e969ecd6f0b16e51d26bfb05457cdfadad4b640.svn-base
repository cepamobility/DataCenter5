/*
 * Created on 14-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.language;


import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.hibernate.HibernateUtil;
import persistence.language.tables.Language;
import persistence.util.ExceptionHandler;
import business.language.LanguageReportFacadeBean;

import communication.administration.user.UserSessionData;
import communication.exception.CommunicationProblemException;
import communication.exception.InvalidDataException;

/**
 * @ejb.bean name="LangugeReport"
 *           display-name="Name for LangugeReport"
 *           description="Description for LangugeReport"
 *           jndi-name="ejb/LangugeReport"
 *           type="Stateless"
 *           view-type="both"
 */
public abstract class LangugeReportBean implements SessionBean,Serializable {

    ExceptionHandler handler = new ExceptionHandler(LanguageReportFacadeBean.class);

    /**
     * @ejb.create-method
     * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
     * @throws CreateException
     */
    public void ejbCreate()throws CreateException{
         
    }
    
	/**
	 * @ejb.interface-method  
	 * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
	 * @param locale
	 * @return Object[][]
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 */
	public Object[][] findMessages(Locale locale) throws Exception{
		try{
            if(locale==null){
                throw new InvalidDataException("findMessages","locale is empty");
            }

            Session session=HibernateUtil.currentSession();

            Query query=session.createSQLQuery("SELECT labelkey,text FROM generalmessages WHERE language = '"+locale.getLanguage()+"'").addScalar("labelkey",Hibernate.STRING).addScalar("text",Hibernate.STRING);
            Collection list1 = query.list();
            
            Collection list2 = new LinkedList();
            if(locale.getVariant()!=null && locale.getVariant().length() > 0){
                query=session.createSQLQuery("SELECT labelkey,text FROM corporationmessages WHERE language = '"+locale.getLanguage()+"' AND corporationname = "+locale.getVariant()).addScalar("labelkey",Hibernate.STRING).addScalar("text",Hibernate.STRING);
                list2 = query.list();
            }
            
            Object[][] messages=new Object[list1.size() + list2.size()][2];
            int i = 0;
            
            Iterator it = list1.iterator();
			while(it.hasNext()){
                messages[i] = (Object[])it.next();
				i++;
			}
			
            it = list2.iterator();
            while(it.hasNext()){
                messages[i] = (Object[])it.next();
                i++;
            }

            return messages;
		}catch(Exception e){
			Object[] parameters = {locale};
			handler.getException(e,"findMessages",null,parameters);
		}		
		return new Object[0][0];
	}
	
	/**
	 * @ejb.interface-method  
	 * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
	 * @return Collection of LanguageData
	 * @throws CommunicationProblemException
	 */
	public Collection findAllLanguage(UserSessionData userSession) throws Exception{
		Collection languages=new LinkedList();
		try{
            Session session=HibernateUtil.currentSession();			
			Query query=session.createQuery("SELECT l FROM persistence.language.tables.Language AS l  ");
			List result=query.list();
			Iterator iter=result.iterator();
			while(iter.hasNext()){
				Language lng=(Language)iter.next();
				languages.add(lng.getData());
			}
		}catch(Exception e){
			Object[] parameters = {};
			handler.getException(e,"findAllLanguage",userSession,parameters);
		}
		return languages;
	}
}
