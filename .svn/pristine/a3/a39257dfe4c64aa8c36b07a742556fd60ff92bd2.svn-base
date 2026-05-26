package communication.exception;

import java.io.Serializable;

import communication.administration.user.UserSessionData;

public class DCException extends DatacenterException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String user;
	private String corp;
	private String className;
	private String func;
	private Object[] parameters;

	public DCException(Exception e){
        super("",e.getMessage());
    }
	
	public DCException(String datacenterMessage,String systemMessage){
		super(datacenterMessage,systemMessage);
	}

    public DCException(Throwable thw, String function,UserSessionData userSessionData, Object[] parameters, String className) {
        super(thw.getClass().getName(),thw.getLocalizedMessage());
        if(userSessionData != null) {
        	user = userSessionData.getFilter().getLoginName();
        	corp = String.valueOf(userSessionData.getFilter().getCorporationId());
        }
        this.className = className;
        func = function;
        this.parameters = parameters;
	}

	public String getName() {
        return "DCException";
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

}
