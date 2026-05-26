<%@page import="bsh.TokenMgrError"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="communication.activity.tables.PointCommentData"%>
<%@page import="communication.activity.tables.ItemValueStrData"%>
<%@page import="communication.activity.tables.ItemData"%>
<%@page import="communication.activity.tables.PointData"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Collection"%>
<%@page import="communication.activity.tables.CheckListData"%>
<%@page import="communication.vehicle.tables.VehicleData"%>
<%@page import="communication.vehicle.tables.VehicleCheckData"%>
<%@ page contentType="application/json" %>

<%@page import="java.util.HashMap"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONTokener"%>
<%@page import="org.json.JSONStringer"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Enumeration"%>
<%@page import="communication.language.Text"%>
<%@page import="business.SystemAccess"%>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData sessionData = sys.getUserSessionData();
	Text messages = sys.getText();
	Enumeration enumeration = request.getParameterNames();
 	if(enumeration.hasMoreElements())  {
		String name = (String)enumeration.nextElement();
		JSONObject object = new JSONObject(name);
		String module = object.getString("module");
		JSONArray answers = object.getJSONArray("answers");
		HashMap answerMap = new HashMap();
		String valids = "";
		for(int i = 0; i < answers.length(); i++) {
			JSONObject answerObj = (JSONObject)answers.get(i);
			Integer id = new Integer(answerObj.getString("id"));
			String answer = answerObj.getString("answer");
			if(i > 0) {
				valids += ",";
			}
			valids += "\""+id+"\"";
			answerMap.put(id,answer);
		}

		VehicleCheckData vehicleCheck = new VehicleCheckData();
		
		vehicleCheck.setVehicle(new VehicleData(new Integer((String)answerMap.get(new Integer(1)))));
		
		StringTokenizer tokenDate = new StringTokenizer((String)answerMap.get(new Integer(3)),"-");
		Calendar current = Calendar.getInstance();

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(tokenDate.nextToken()));
		c.set(Calendar.MONTH, Integer.parseInt(tokenDate.nextToken())-1);
		c.set(Calendar.DATE, Integer.parseInt(tokenDate.nextToken()));
		if(answerMap.containsKey(new Integer(33))){
			StringTokenizer tokenHour = new StringTokenizer((String)answerMap.get(new Integer(33)),"-");
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tokenHour.nextToken()));
			c.set(Calendar.MINUTE, Integer.parseInt(tokenHour.nextToken()));
		}
		vehicleCheck.setDate(c.getTime());
		
		if(sys.isCorporation(CorporationData.DPA) && !(sessionData.isAdministrator() || sessionData.isAccountManager()) && current.get(Calendar.MONTH) != c.get(Calendar.MONTH)) {
			String resp = "{\"valids\": ["+valids+"],\"errors\": [{\"id\" : \"3\",\"error\" : \"Só poderá cadastrar checklist do mês vigênte\"}],\"final_text\": \"Module finished\"}";
			response.getWriter().print(resp);
		}else {

			vehicleCheck.setName(module);
			vehicleCheck.setInspectedby(sys.getUserSessionData().getFullName());
			
			CheckListData checkList = sys.getVehicleReportFacade().getCheckList(new Integer(module),sys.getUserSessionData());
	        Collection itemValueSet = new LinkedList();
	        Collection pointCommentValueSet = new LinkedList();
	        Iterator points = checkList.getSortPoints();
	        while(points.hasNext()) {
	        	PointData point = (PointData)points.next();
	        	Iterator itemsIt = point.getSortItems();
	            while(itemsIt.hasNext()) {
	                ItemData item = (ItemData)itemsIt.next();
	                String value = (String)answerMap.get(item.getId());
	                
	                if(item.getType() > 2) {
		                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),value));
	                }else {
	                	System.out.println("VALOR:"+value);
		                String insertedValue = null;
		                String insertedTag = "0";
		                String comment = "";
		                int valueIndex = value.indexOf("-");
		                int valueIndexP = value.indexOf("#");
		                int valueIndex16 = value.indexOf("##");
		                if(valueIndex16 > 0) {
		                	insertedTag = "-1";
		                	String[] aux =value.split("##");
		                	
		                	if(aux.length>2){
			                	insertedValue = aux[0];
			                	insertedTag=aux[1];
			                	comment = aux[2];
		                	}else{
		                		if(aux.length>1){
				                	insertedValue = aux[0];
				                	comment = aux[1];
		                		}else{
		                			insertedValue = aux[0];
		                		}
		                	}
		                	
		                	System.out.println("Iv:"+insertedValue+"It:"+insertedTag+"C:"+comment);
		                }else
		                if(valueIndexP > 0) {
		                	String[] aux =value.split("#");
		                	
		                	if(aux.length>2){
			                	insertedValue = aux[0];
			                	insertedTag=aux[1]!=null && aux[1].equalsIgnoreCase("on")?"1":"0";
			                	comment = aux[2];
		                	}else{
		                		if(aux.length>1){
				                	insertedValue = aux[0];
				                	comment = aux[1];
		                		}else{
		                			insertedValue = aux[0];
		                		}
		                	}
		                	
		                	System.out.println("Iv:"+insertedValue+"It:"+insertedTag+"C:"+comment);
		                }else
		                if(valueIndex > 0) {
		                	insertedValue = value.substring(0, valueIndex);
		                	comment = value.substring(valueIndex+1);
		                }else {
		                	insertedValue = value;
		                }
		                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),insertedValue.toString(),insertedTag));
		                if( comment != null && comment.length() > 0 && !comment.equals("-1") ) {
		                	pointCommentValueSet.add(new PointCommentData(point,comment));
		                }
	                }
	            }
	        }
	        
	        vehicleCheck.setResult(new Integer((String)answerMap.get(new Integer(0))));
	        vehicleCheck.setSerie((String)answerMap.get(new Integer(4)));
	        vehicleCheck.setObs((String)answerMap.get(new Integer(2)));
			vehicleCheck.setItemValueSet(itemValueSet);
			vehicleCheck.setPointCommentSet(pointCommentValueSet);
		    
			if(module.equals("80") || module.equals("79") || module.equals("84")){//UPM MDP e internos MDP y WEYER
				sys.getVehicleABMFacade().createOrUpdateVehicleCheckUPMMDP(new LinkedList(), vehicleCheck, "", sys.getUserSessionData(),sys.getText());	
			}else{
				sys.getVehicleABMFacade().createOrUpdateVehicleCheck(vehicleCheck, "", sys.getUserSessionData());
			}
		    
	
			String resp = "{\"valids\": ["+valids+"],\"errors\": [],\"final_text\": \"Module finished\"}";
			System.out.println(resp);
			response.getWriter().print(resp);
	        sys.getUserSessionData().setUrl("/vehicle/VehicleView.jsp?id="+answerMap.get(new Integer(1)));
	        sys.getUserSessionData().setMsg("Check list ingresado con éxito");
		}
	}
%>
