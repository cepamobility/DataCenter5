/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package business.language;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Locale;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.naming.NamingException;

import business.fleet.FleetABMFacadeBean;
import business.util.ExceptionHandler;

import persistence.language.LangugeReport;
import persistence.language.LangugeReportHome;
import persistence.language.LangugeReportUtil;

import communication.administration.user.UserSessionData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;

/**
 * @ejb.bean name="LanguageReportFacade"
 *           display-name="Name for LanguageReportFacade"
 *           description="Description for LanguageReportFacade"
 *           jndi-name="ejb/LanguageReportFacade"
 *           type="Stateless"
 *           view-type="remote"
 * 
 * @ejb.ejb-ref
 * 			    ejb-name = "LangugeReport"
 *  			ref-name = "ejb/LangugeReport" 
 * 				view-type = "remote"
 * 
 * @jboss.ejb-ref-jndi 
 * 
 * 				jndi-name = "ejb/LangugeReport"
 * 				ref-name ="LangugeReport"
 * 
 * @ejb.util generate="physical"
 */
public abstract class LanguageReportFacadeBean implements SessionBean {
	
    private ExceptionHandler handler = new ExceptionHandler(LanguageReportFacadeBean.class);

    /**
	 * Create method
	 * 
	 * @ejb.create-method
	 * @ejb.permission role-name="systemaccess,administrator,resetpassword" 
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * 
	 * @return Collection of LanguageData
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
     * @ejb.permission role-name="systemaccess,resetpassword" 
	 */
	public Collection findAllLanguage(UserSessionData userSessionData) throws Exception{
		Collection languages=null;
		try{
            if(userSessionData==null){
                throw new DeslogedException("findAllLanguage","session == null");
            }
			LangugeReportHome lngReportHome=LangugeReportUtil.getHome();
			LangugeReport languageReport=lngReportHome.create();
			languages=languageReport.findAllLanguage(userSessionData);
		}catch(Exception e){
			Object[] p = null;			
			handler.handleException("findAllLanguage",e, userSessionData, p);
		}
		return languages;
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * 
	 * @return Collection of LanguageData
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @throws InvalidDataException
	 * @throws RemoteException
     * @ejb.permission role-name="systemaccess,resetpassword" 
	 */
	public Object[][] findAllMessages(Locale locale,UserSessionData userSessionData) throws Exception{
		LangugeReport languageReport=null;
		try{
            if(userSessionData==null){
                throw new DeslogedException("findAllMessages","session is null");
            }

            LangugeReportHome lngReportHome=LangugeReportUtil.getHome();
			languageReport=lngReportHome.create();
			return languageReport.findMessages(locale);
			
			
		}catch(Exception e){
			Object[]p = new Object[1];
			p[0] = locale;
			handler.handleException("findAllMessages",e, userSessionData, p);
		}
        return null;
	}
}