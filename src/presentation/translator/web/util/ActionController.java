package presentation.translator.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;
import org.jboss.logging.Logger;

import persistence.log.LoggerFormatting;
import presentation.translator.web.user.LogoutAction;

import communication.administration.user.UserSessionData;

public class ActionController extends ActionServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	// private LoggerFormatting logFormatting=new LoggerFormatting();
	
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException, ServletException {
		try{
			super.doGet(arg0, arg1);
		}catch(Exception e){
			exception(" do Get",e,arg0,arg1, Logger.getLogger((String)arg0.getAttribute("className")));
		}
	}
	
	
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException, ServletException {
		try {
			super.doPost(arg0, arg1);
		}catch(Exception e) {
			exception(" do Post",e,arg0,arg1, Logger.getLogger((String)arg0.getAttribute("className")));
		}
	}
	
	private void exception(String method, Exception e,HttpServletRequest arg0, HttpServletResponse arg1,Logger logger)throws IOException {
	    try {
            if(arg0.getSession().getAttribute("SystemAccess") == null) {
            	new LogoutAction().logout(arg0);
            	arg1.sendRedirect("../datacenter/home.jsp?session=1");
            }else {
                Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
                while (thw.getCause() != null) {
                    thw = thw.getCause();
                }
                arg0.setAttribute("exception", thw);
                arg0.setAttribute("class", getClass().getName());
                RequestDispatcher dispatcher = arg0.getRequestDispatcher("/exception.jsp");
                dispatcher.forward(arg0, arg1);
            }
        } catch (Exception ex) {
            try {
                arg0.setAttribute("exception", ex);
                RequestDispatcher dispatcher = arg0.getRequestDispatcher("/exception.jsp");
                dispatcher.forward(arg0, arg1);
            } catch (ServletException se) {
                se.printStackTrace();
            }
        }
	}
}
