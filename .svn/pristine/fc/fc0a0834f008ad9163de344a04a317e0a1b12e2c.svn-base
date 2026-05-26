package communication.util;

public class ReportDataUtil {
	
    public static String toHTMLString(String word) {
    	if(word == null) {
    		return null;
    	}
    	try {
	        StringBuffer result = new StringBuffer();
	        for(int i = 0; i < word.length(); i++) {
	            char c = word.charAt(i); 
	            if((int)c > 126 && (int)c < 161) {
	            	result.append(" ");
	            }else {
	            	result.append(c);            	
	            }
	        }
	        return result.toString();
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }

}
