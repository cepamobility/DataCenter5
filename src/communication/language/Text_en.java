/*
 * Created on 14-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.language;

import java.io.Serializable;
import java.util.ListResourceBundle;
import java.util.Locale;

import persistence.language.LangugeReport;
import persistence.language.LangugeReportHome;
import persistence.language.LangugeReportUtil;

import communication.exception.InInitializerException;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Text_en extends ListResourceBundle implements Serializable {

	private static final Object[][]contents;
	
	static{
		try{
			LangugeReportHome languageHome=LangugeReportUtil.getHome();
			LangugeReport language=languageHome.create();
			Locale locale=new Locale("en","","");
			
			contents=language.findMessages(locale);
		}catch(Exception e){
			throw new InInitializerException("Can't access language report, remote exception in class communication.language.Test_en","communication.language.communicationerror");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return contents;
	}

}
