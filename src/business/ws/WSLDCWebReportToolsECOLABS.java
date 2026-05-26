package business.ws;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;

@Stateless(mappedName = "WSLDCWebReportToolsECOLABS")
@WebService(name="WSLDCWebReportToolsECOLABS")
public class WSLDCWebReportToolsECOLABS extends WSLDCWebReportToolsMDP {
	
    @Resource
    private WebServiceContext wsContext;
    
	 

	@WebMethod(operationName="login")
	public User login(@WebParam(name = "u") String u, @WebParam(name = "p")String p){
		User user=new User(u,p);
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			user.setFullName(sys.getUserSessionData().getFullName());
			user.setFlags(new String[]{});
			
			Object[][] o= sys.getText().getContents();
			String[][] m=new String[o.length][];
			int i=0;
			for(Object[] a:o){
				m[i]=new String[] {a[0].toString(),a[1].toString()};
				i++;
			}
			user.setMessages(m);
			//MessageContext mc = wsContext.getMessageContext();
	        /*HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
	        session.setAttribute("user", user);
	        session.setAttribute("sys", sys);
	        MessageContext mc = wsContext.getMessageContext();
	        HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
	        SystemAccessRemote sys= (SystemAccessRemote) session.getAttribute("sys");
	        */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user=null;
		}
         
         return user;
	}
 
	
}
