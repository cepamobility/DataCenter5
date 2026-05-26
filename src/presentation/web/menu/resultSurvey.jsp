<%@page import="communication.administration.user.tables.UserData"%>
<%@page import="communication.security.SecurityConstants"%>
<%@page import="communication.util.MailSender"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="communication.vehicle.report.CheckListSurveyDataSource"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="presentation.translator.web.util.Util"%>
<%@page import="communication.alert.AlertConstants"%>
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
		vehicleCheck.setVehicle(new VehicleData(new Integer(CheckListData.SURVEY_DEFAULT_VEHICLE)));

		Calendar c = Calendar.getInstance();
		vehicleCheck.setDate(c.getTime());
		
		vehicleCheck.setName(module);
		vehicleCheck.setInspectedby(sys.getUserSessionData().getFullName());
			
		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(new Integer(module),sys.getUserSessionData());
        Collection itemValueSet = new LinkedList();
        Iterator points = checkList.getSortPoints();
        float sum = 0;
        int yes = 0;
        int no = 0;
        int partial = 0;
        while(points.hasNext()) {
        	PointData point = (PointData)points.next();
        	Iterator itemsIt = point.getSortItems();
            while(itemsIt.hasNext()) {
                ItemData item = (ItemData)itemsIt.next();
                String value = (String)answerMap.get(item.getId());
	            itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),value));
	            if(item.getType().intValue() <= 2) {
	            	switch(Integer.parseInt(value)) {
		            	case 1:
		            		sum += 1;
		            		yes++;
		            		break;
		            	case 2:
		            		sum += 0.25;
		            		partial++;
		            		break;
		            	default:
		            		no++;
		            		break;
	            	}
	            }
            }
        }
        sum = sum * 100 / 63;
        
	  	vehicleCheck.setResult(new Integer(Math.round(sum)));
	  	vehicleCheck.setInspectedby(sys.getUserSessionData().getFilter().getLoginName());
	  	String obs = (String)answerMap.get(new Integer(2));
	    vehicleCheck.setObs(obs);
		vehicleCheck.setItemValueSet(itemValueSet);
		    
		sys.getVehicleABMFacade().createOrUpdateVehicleCheck(vehicleCheck, "", sys.getUserSessionData());
	
		HashMap<String,Object> parameters = new HashMap<String,Object>();
     	parameters.put("header", AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/header.jpg");
     	parameters.put("footer", AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/footer.jpg");
     	parameters.put("logo_cepa", AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/logo_cepa.png");
     	parameters.put("title", "Survey");
     	parameters.put("date",Util.formatDate(c.getTime()));
     	parameters.put("header1", AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"images/back1.jpg");
     	parameters.put("obsText", messages.getText("generic.messages.observations"));
     	parameters.put("obsValue", obs);
     	parameters.put("resultText", messages.getText("generic.messages.points"));
     	parameters.put("resultValue", messages.getText("generic.messages.yes")+": "+yes+", "+messages.getText("generic.messages.partially")+": "+partial+", "+messages.getText("generic.messages.no")+": "+no+" ("+sum+"%)");

	    CheckListSurveyDataSource dataSource = new CheckListSurveyDataSource(vehicleCheck, checkList, messages);

	    //JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"survey.jrxml", AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"survey.jasper");
	  	FileInputStream input = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"survey.jasper"));

	   	JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input);
	    String file = AlertConstants.TEMP_DIRECTORY_JASPERREPORT+"out/Survey_"+sys.getUserSessionData().getFilter().getLoginName()+".pdf";
	     
	    OutputStream outStream = new FileOutputStream(new File(file));
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	     

	     JRPdfExporter exporter = new JRPdfExporter();
	     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
	     exporter.exportReport();       
	     outStream.flush();
	     outStream.close();

	     Collection files = new LinkedList();
	     files.add(file);
	
	     Collection names = new LinkedList();
	     names.add("Survey_"+sys.getUserSessionData().getFilter().getLoginName()+".pdf");

	     Collection address = new LinkedList();
	     address.add("f.millan@cepasafedrive.com");
	     
	     MailSender sender = new MailSender();
		Collection bcc = new LinkedList();
		if(SecurityConstants.isProductionServer()){
				bcc.add("cepasafedrive@gmail.com");
				UserData user=sys.getUserReportFacade().findUserByPrimaryKey(sys.getUserSessionData().getFilter().getLoginName(), sys.getUserSessionData());
				if(user.getEmail() != null)
					bcc.add(user.getEmail());
			}
			String body = Util.toHTMLString("Survey Results - Thanks");

			boolean sended = false;
			int count = 0;
			while(!sended && count < 10) {
				try {
			    	String[] mailAddress = sender.getAvailableAddress(CorporationData.REPORTER_JJ); 
					String mailServer = AlertConstants.MAILSERVER; 
					if (mailAddress[0] != null && mailAddress[0].contains("cepainternational.com")){
						mailServer = AlertConstants.MAILSERVER2;
					}else if (mailAddress[0] != null && mailAddress[0].contains("cepalogistica.com")){
						mailServer = AlertConstants.MAILSERVER_INMOTION;
					}
					//sender.sendImage(address,
					//		mailServer,
					//		"CEPA DataCenter", 
					//		mailAddress[0],
					//		mailAddress[1], 
					//		"Survey", body, files,
					//		names, 
					//		"", "", bcc,null);
					sended = true;
				}catch (Exception e) {
					e.printStackTrace();
				}
				count++; 
			}

		String resp = "{\"valids\": ["+valids+"],\"errors\": [],\"final_text\": \"Module finished\"}";
		response.getWriter().print(resp);
	    sys.getUserSessionData().setUrl("/surveyThanks.jsp");
	}
%>
