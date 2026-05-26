/*
 * Created on 13-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.language.tables;

import org.hibernate.Session;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneralMessage {
	
    public static final String SPANISH = "es";
    public static final String ENGLISH = "en";
    public static final String PORTUGUESE = "pt";

    private String text;
	
	private GeneralMessagePK primaryKey;
	
	public GeneralMessage(){
		
	}
	
	
	public GeneralMessage(String key, String language, String text,Session session) {
        this.text = text;
        primaryKey = new GeneralMessagePK(key,language,session);
    }


    public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public GeneralMessagePK getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(GeneralMessagePK primaryKey) {
		this.primaryKey = primaryKey;
	}
}
