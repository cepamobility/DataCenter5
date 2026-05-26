package presentation.web.template;

public class linksDefinitions{
	String Reference;
	public void setReference(String ref){
		Reference = ref;	
	}
	public String getReference(){
	
	if(Reference.equals("driver") )
		return "/driver/DriverList.jsp";
	else if(Reference.equals("event"))
		return "/event/EventList.jsp";
	else if(Reference.equals("vehicle"))
		return "/vehicle/VehicleList.jsp";	
	else if(Reference.equals("corporation"))
		return "/corporation/edit.jsp";
	else if(Reference.equals("user"))
		return "/user/UserNEDList.jsp";	
	else
		return null;
	
	}//end linkDefinitions	 
	
}//end class