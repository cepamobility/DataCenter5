/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.cenapra;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import presentation.translator.web.util.Util;

import communication.cenapra.tables.ParticipantData;
import communication.driver.tables.DriverData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParticipantUtil {

    public Hashtable getParameters(HttpSession session,HttpServletRequest request) {
        Hashtable<String,Object> parameters = new Hashtable<String,Object>();

            
        parameters.put("status",getValue(request,"status",String.valueOf(DriverData.ACTIVE)));
        parameters.put("pageNum",getValue(request,"pageNum","1"));
        parameters.put("firstName",getValue(request,"firstName",""));
        parameters.put("lastName",getValue(request,"lastName",""));
        parameters.put("pageSize",getValue(request,"pageSize","10"));
        parameters.put("activity",getValue(request,"activity","-1"));
        parameters.put("activityType",getValue(request,"activityType",ParticipantData.WITHOUT_ACTIVITY));
        
        if(Util.empty(request.getParameter("all"))) {
            parameters.put("all",new Boolean(false));
        }else {
            parameters.put("all",new Boolean(request.getParameter("all")));
        }
        return parameters;
    }
    
    private String getValue(HttpServletRequest request,String parameter,String defaultValue) {
        if(!Util.empty(request.getParameter(parameter))){
            return request.getParameter(parameter);
        }else {
            return defaultValue;
        }
    }

    public String isChecked(Hashtable parameters,int value) {
        if(parameters.containsKey("status") && Integer.parseInt((String)parameters.get("status")) == value) {
            return "checked";
        }
        return "";
    }
    
}
