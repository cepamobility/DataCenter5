/**
 * Created on 18-ene-2006
 * CEPA
 * DataCenter 5
 */
package communication.language;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Locale;

import persistence.language.LangugeReport;
import business.SystemAccess;
import business.SystemAccessRemote;

import communication.administration.user.UserSessionData;

public class TextContainer implements Serializable {

    private static TextContainer textContainer;
    private Hashtable texts = new Hashtable();
    
    public static TextContainer getInstance() {
        if(textContainer == null) {
            textContainer = new TextContainer();
        }
        return textContainer;
    }
    
    public static TextContainer getNewInstance() {
        textContainer = new TextContainer();
        return textContainer;
    }    

    public Object[][] getTexts(String language,Integer corporation,SystemAccess bean,UserSessionData userSessionData) throws Exception {
        try {
            String key = language+"-"+String.valueOf(corporation);
            if(texts.containsKey(key)) {
                return (Object[][])texts.get(key);
            }else {
                Locale locale=new Locale(language,"",String.valueOf(corporation));
                Object[][] contents = bean.getLanguageReportFacade().findAllMessages(locale,userSessionData);
                synchronized (texts) {
                    texts.put(key,contents);
                }	
                return contents;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Object[][] getTexts(String language,Integer corporation,SystemAccessRemote bean,UserSessionData userSessionData) throws Exception {
        try {
            String key = language+"-"+String.valueOf(corporation);
            if(texts.containsKey(key)) {
                return (Object[][])texts.get(key);
            }else {
                Locale locale=new Locale(language,"",String.valueOf(corporation));
                Object[][] contents = bean.getLanguageReportFacade().findAllMessages(locale,userSessionData);
                synchronized (texts) {
                    texts.put(key,contents);
                }	
                return contents;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Reload Texts
     * @param language
     * @param corporation
     * @param bean
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Object[][] reloadTexts(String language,Integer corporation,SystemAccess bean,UserSessionData userSessionData) throws Exception {
        LangugeReport languageReport = null;
        try {
            String key = language+"-"+String.valueOf(corporation);
            Locale locale=new Locale(language,"",String.valueOf(corporation));
            Object[][] contents = bean.getLanguageReportFacade().findAllMessages(locale,userSessionData);
            synchronized (texts) {
                texts.put(key,contents);
            }	
            return contents;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Reload Texts
     * @param language
     * @param corporation
     * @param bean
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Object[][] reloadTexts(String language,Integer corporation,SystemAccessRemote bean,UserSessionData userSessionData) throws Exception {
        LangugeReport languageReport = null;
        try {
            String key = language+"-"+String.valueOf(corporation);
            Locale locale=new Locale(language,"",String.valueOf(corporation));
            Object[][] contents = bean.getLanguageReportFacade().findAllMessages(locale,userSessionData);
            synchronized (texts) {
                texts.put(key,contents);
            }	
            return contents;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
