/**
 * Created on 27-abr-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.queries;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

public interface APMMReport {

    public String getFrommonth();
    public String getTomonth();
    public String getFromyear();
    public String getToyear();
    
    public String getOrder1();
    public String getOrder2();
    public String getOrder3();
    public String getOrder4();
    
    public String getDecimals();
    public String getOutput();
    
    public String getFrom(Text messages);
    public String getTo(Text messages);
    
    public BookmarkData getBookmark(int action);
    
    public String getFleetTypes();
    public String getDistance();

}
