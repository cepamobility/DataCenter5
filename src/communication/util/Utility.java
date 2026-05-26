package communication.util;

import java.util.Calendar;
import java.util.Date;

public class Utility {
	 
	public static String activityName(String activity) {
		 String a = activity.replace("(versión Tablets)","");
		 a = a.replace("(versão Tablets)","");
		 a = a.replace("(version Tablets)","");
		 return a;
	 }
	 
    public static String formatDate(Date date) {
    	try{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE)+"/"+String.valueOf(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
    	}catch(Throwable th){
    	}
    	
    	return "";
    }
}
