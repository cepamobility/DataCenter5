 <%@page language="java" 
 	errorPage="../../exception.jsp"
 		import="business.*"
		import="java.io.*"
		import="java.lang.*"
		import="java.text.*"
		import="java.util.*"
		import="communication.language.*"
		import="communication.security.*"
	 	import="presentation.translator.web.util.*"
 	 	import="communication.administration.corporation.tables.*"	
 %>
 <%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
		System.out.println("ENTRE AL INCLUDE");
		SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");	
		String language="en";
		
		String corporation;
		CorporationDirectoryData cdd;
		// Filtro por Corporacion:
		// Para cada corporacion voy a buscar unicamente los nombres de actividades
		// que esa corporacion tiene.
		// Por ejemplo: Si la corporacion X tiene solo el curso btw1, voy a leer
		// del disco solo el primer archivo de nombre btw1.
		Integer corporationId = sys.getUserSessionData().getFilter().getCorporationId();

		corporation = ""; // Nombre de la carpeta donde estan los Reportes:
		cdd = sys.getCorporationReportFacade().
		findCorporationDirectoryByPrimaryKey(corporationId,sys.getUserSessionData());
		
		if (cdd == null || Util.empty(cdd.getDirectory())){
			corporation = "null";
		}else{
			corporation = cdd.getDirectory();
		}	
		
		
		// Filtro por idioma
		if (sys.getUserSessionData().getLenguage().equals("es")){
			language = "es";
		}else if(sys.getUserSessionData().getLenguage().equals("en")){
			language = "en";
		}else if(sys.getUserSessionData().getLenguage().equals("pt")){
			language = "pt";
		}
		//Format dateFormat;	
		//dateFormat = new SimpleDateFormat("dd-MM-yyyy");				    
	 	//NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);

	    //  String directory = "/home/datacenter5/" + corporation +"/courses_guides/" + language ;	
	     String directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY + corporation +"/courses_guides/" + language ;
	    File dir = new File (directory);
	    int fileCounter = 0;
	    String link = "";
	    File file;
	    //String name = "";
	    //long fileSize = 0;
	    //Date date;
		Text messages=sys.getText();
		
		String activity = (String)request.getParameter("activity");
		//System.out.println("CurseGuideInclude.jsp: "+activity);
		String value = (activity.contains(".")? 
				activity.substring(activity.lastIndexOf(".")+1).trim():
				activity);
		//System.out.println("CurseGuideInclude.jsp: Value="+value);
	    String [] fileNames;
	    //System.out.println("Dir: "+dir.getName());
	    try{
		if ( dir.isDirectory() )
		{
			fileNames = dir.list();	
			//System.out.println("FileNames: "+fileNames );
			if (fileNames != null && fileNames.length > fileCounter)
			{					
				fileCounter = 0;
			    while (fileNames.length - 1 >= fileCounter)
				{
					String course = fileNames[fileCounter].substring(0,fileNames[fileCounter].length()-4);
					//System.out.println("Course: "+course);
					//System.out.println("Value: "+value);
					if (value.trim().equalsIgnoreCase(course.trim()))
					{
						link = ".."+directory.substring(5)+"/"+fileNames[fileCounter];
						//System.out.println("CurseGuideInclude.jsp: link="+link);
						file = new File(directory+"/"+fileNames[fileCounter]);
						//name = file.getName();						
						//date = new Date(file.lastModified());
						//fileSize = file.length();			
						// Muestro solo si no es un directorio
						if (!file.isDirectory()){		%>

				                	<table width="100%" align="left">
				                		<tr>
				                			<td width="100%" align="left"><font size="2" face="Arial, Helvetica, sans-serif">
							                	<strong> 
							                	<a href='<%=link%>' title='<%=messages.getText("generic.supportmaterial.coursesguides.guide")%>'>
							                			<%=messages.getText("generic.supportmaterial.coursesguides.guide")%>
							                  	</a>
							                	</strong>
							                    </font>
							                </td>
							             </tr>
						             </table>
					             
		<%								}
						break;
					}
					fileCounter++;
				}
			}
		}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    %>
	    
	    
	    
	    
	    
	    
	    