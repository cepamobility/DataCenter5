<%@page language="java"
	errorPage="../../exception.jsp"
	import="business.*"
	import="java.io.*"
	import="java.text.*"
	import="java.util.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.tables.geographicdivisions.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="presentation.translator.web.activity.schedule.*"	
	import="java.util.*"
 	import="communication.administration.user.*" 
 	import="communication.administration.corporation.tables.*"	
	import="communication.util.Filter"
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData;
	DataTypeReportFacade datatypes;
	GeoItemData item;
	String check;
	Iterator iter;
	LinkedList sortCountries = new LinkedList();
	String name="";
	File dir;
	File file, fileMx;
	String fileName = "";
	String countryCode = "";
	String addReportPermission ="";
	int fileCounterMx = 0;
	boolean canAddReport = false;
	String corporation = "";
	String language = "en";
	String[] fileNames;
	String[] fileNamesMx;
	String link = "";
	long fileSize;
	Date date;
	Format dateFormat;	
	CorporationDirectoryData cdd;
	dateFormat = new SimpleDateFormat("dd-MM-yyyy");				    
 	NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
	

	// Manejo de Mensajes
 	RequestDispatcher dispatcher; 
 	String context; 
 	String pathMsg;
 	String pathLanguage;
 	pathLanguage="/util/jsp/language.jsp";	
	// Carga system access (sys),userSession, id de la corporacion (crpName), lenguaje (lngName) y los textos (messages)
	dispatcher=request.getRequestDispatcher(pathLanguage);
	dispatcher.include(request,response);
	// hace el include de la página que despliega el box que indica el resultado de la operación
	// el texto que se despliega en el box es el guardado en la variable de sesión bajo la key Msg
	pathMsg="/util/jsp/message.jsp";
	dispatcher=request.getRequestDispatcher(pathMsg);
	dispatcher.include(request,response);
	// Fin manejo de mensajes
 	
	
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	
	
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
	
	// String directory = "/home/datacenter5/" + corporation +"/courses_guides/" + language ;
	String directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY + corporation +"/courses_guides/" + language ;
	

	Collection courses = new LinkedList();
	datatypes = sys.getDataTypeReportFacade();
    iter = datatypes.findAll(DataTypeConstants.TYPECEPAACTIVITY,userSessionData).iterator();
    while(iter.hasNext()){
    	GeneralDTData itemType = (GeneralDTData)iter.next();
	   	courses.add(new OptionItem(messages.getText(itemType.getResourceBundleId()),itemType.getResourceBundleId()));
   	}               
	// session.setAttribute("courses",courses);	
	request.setAttribute("courses",courses);
%>

<html:html>
<head>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
</head>
<body>

<table width="700"  border="0" cellspacing="0" cellpadding="0" align="center"> 

  <tr>
    <td> 
      <table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
          <td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
          <%=messages.getText("generic.supportmaterial.coursesguides.title")%></td>
        <td width="19"><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
      </tr>
      <tr>
        <td width="19" background="/datacenter/util/imgs/left_line.gif">&nbsp;</td>
        <td width="672">
        	<table width="672" cellpadding="0" cellspacing="0" class="texts">
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr>
	          <!--  Titulos:  -->
              <tr><td colspan="3">               
              <table width="672" cellpadding="0" cellspacing="0" class="texts">
              <tr>
                <td width="80%" align="left">
                	<%=messages.getText("generic.supportmaterial.coursesguides.files")%>:
                <td>
                <td width="10%" align="center">	
                  	<%=messages.getText("generic.supportmaterial.reports.date")%>:
                </td>  
                <td width="10%" align="center">	
                  	<%=messages.getText("generic.supportmaterial.reports.filesize")%>:
                </td>
              </tr> 
              </table>
              </td></tr>
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr> 

<% 
		int fileCounter = 0;
		try{
			
			dir = new File (directory);
			if ( dir.isDirectory() )
			{
				fileNames = dir.list();	
				if (fileNames != null && fileNames.length > fileCounter)
				{	
				
					iter = courses.iterator();					
					while (iter.hasNext()){
						OptionItem oi = (OptionItem)iter.next();
						fileCounter = 0;
						while (fileNames.length > fileCounter)
						{
							// label = descripcion del nombre de la actividad
							// value = generic messages del nombre de la actividad
							// course nombre del archivo.
							String course = fileNames[fileCounter].substring(0,fileNames[fileCounter].length()-4);
							String value = oi.getValue().substring(oi.getValue().lastIndexOf(".")+1).trim();
							
							link = ".."+directory.substring(5)+"/"+fileNames[fileCounter];
							file = new File(directory+"/"+fileNames[fileCounter]);
							name = file.getName();						
							date = new Date(file.lastModified());
							fileSize = file.length();			
							// Muestro solo si no es un directorio
							if (!file.isDirectory()){		%>
								 <tr> 
					                <td width="80%" align="left">
					                	<img src="../datacenter/util/imgs/icon1.jpg" width="12" height="12">
					                	&nbsp;&nbsp;<strong><a href='<%=link%>' ><%=oi.getLabel()%></a></strong> 
					                </td>
					                <td width="10%" align="right"> <div align="center"><strong><%=dateFormat.format(date)%></strong> </div></td>
					                <td width="10%" align="right"> <div align="center"><strong><%=((fileSize >= 1000)? (nf.format(fileSize/1000)+"KB"):(nf.format(fileSize)+"Bytes"))%></strong> </div></td>
					              </tr>
					              <tr> 
					                <td colspan="3">&nbsp;</td>
					              </tr>
<%								}
							break;
						}
					}
				}else{
%>
			  <tr> 
                <td width="100%" colspan="3">
                	<%=messages.getText("generic.supportmaterial.coursesguides.noguides")%>
                </td>
	          </tr>
              <tr> 
                <td width="100%" colspan="3">&nbsp;</td>
              </tr>
<%				}
			}else{
%>
			  <tr> 
                <td width="100%" colspan="3">
                	<%=messages.getText("generic.supportmaterial.coursesguides.noguides")%>
                </td>
	          </tr>
              <tr> 
                <td width="100%" colspan="3">&nbsp;</td>
              </tr>
<%			}
		
			%>    

            </table></td>
        <td width="19" background="/datacenter/util/imgs/right_line.gif">&nbsp;</td>
      </tr>
      <tr>
        <td width="19"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
        <td width="672" background="/datacenter/util/imgs/center_base.gif"><img src="x" height="1" width="1"></td>
        <td width="19"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
      </tr>
    </table>
    <br>
    </td>
  </tr>
 
<%
	}catch(Exception e){
		e.getMessage();
		e.printStackTrace();
	}
 %>
  
    
</table>
</body>
</html:html>

