package persistence.language;

import javax.ejb.SessionBean;

import org.hibernate.classic.Session;

import persistence.hibernate.HibernateUtil;
import persistence.language.tables.GeneralMessage;
import persistence.language.tables.GeneralMessagePK;
import persistence.util.ExceptionHandler;

import communication.administration.user.UserSessionData;
import communication.exception.DeslogedException;

/**
 * @ejb.bean name="LanguageABM" display-name="Name for LanguageABM"
 *           description="Description for LanguageABM"
 *           jndi-name="ejb/LanguageABM" type="Stateless" view-type="remote"
 */
public abstract class LanguageABMBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(LanguageABMBean.class);

    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void ejbCreate() throws javax.ejb.CreateException {  }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void saveText(String key,String language, String text, UserSessionData userSessionData) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("saveText","session = null");
        }
        try {
            Session session = HibernateUtil.currentSession();
            GeneralMessage message = new GeneralMessage(key,language,text,session);
            session.saveOrUpdate(message);
        } catch (Exception e) {
        	Object[] parameters = {key,language,text};
            handler.getException(e,"saveText",userSessionData,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void updateText(String key,String language, String text, UserSessionData userSessionData) throws Exception {
        if (userSessionData == null) {
            throw new DeslogedException("updateText","session = null");
        }
        try {
            Session session = HibernateUtil.currentSession();
            GeneralMessage message = (GeneralMessage)session.load(GeneralMessage.class, new GeneralMessagePK(key,language,session));
            message.setText(text);
            session.update(message);
        } catch (Exception e) {
        	Object[] parameters = {key,language,text};
            handler.getException(e,"updateText",userSessionData,parameters);
        }
    }
}