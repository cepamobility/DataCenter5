<%@page language="java"
	errorPage="../exception.jsp"
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
	import="communication.security.*"
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData;
	DataTypeAccess datatypes;
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
	dateFormat = new SimpleDateFormat("dd-MM-yyyy");				    
 	NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
 	CorporationDirectoryData cdd;
 	Integer corporationId;
	

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
 	
	//	Me fijo si tiene permisos para agregar reportes o cepa tips
	addReportPermission = Util.checkPermission(sys,SecurityConstants.ADD_REPORT);
	if (addReportPermission == null) {
		// Si tiene permiso para agregar reportes y viene con la opcion de agregar
		// le dejo agregar reportes
		String add = (String)request.getParameter("add");
		canAddReport = new Boolean(add.trim()).booleanValue();;
	}	

		
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	
	corporationId = sys.getUserSessionData().getFilter().getCorporationId();
	corporation = ""; 
	cdd = sys.getCorporationReportFacade().
	findCorporationDirectoryByPrimaryKey(corporationId,sys.getUserSessionData());
	// Filtro por Corporacion:
	if (cdd == null || Util.empty(cdd.getDirectory())){
		corporation = "null";
	}else{
		corporation = cdd.getDirectory();
	}

	
	// Filtro por idioma
	language = sys.getUserSessionData().getLenguage();
	// if (sys.getUserSessionData().getLenguage().equals("es")){
	//	language = "es";
	// }else if(sys.getUserSessionData().getLenguage().equals("en")){
	//	language = "en";
	// }else if(sys.getUserSessionData().getLenguage().equals("pt")){
	//	language = "pt";
	// }
	
	// String directory = "/home/datacenter5/" + corporation + "/cepa_tips/" + language ;
	String directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY + corporation + "/forms/" + language ;
%>

<html:html>
<head>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<script>
function onSelect1(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById("dayId1");
  		var input_month = document.getElementById("monthId1");
  		var input_year = document.getElementById("yearId1");
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  }
</script>
</head>
<body>

<table width="700"  border="0" cellspacing="0" cellpadding="0" align="center"> 

<%if (canAddReport){ %>		
	<tr><td>
	<html:form action="FormsUpload" method="post" enctype="multipart/form-data" >	
		<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          <td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
	          	<%=messages.getText("generic.supportmaterial.forms.addforms") %>
	          </td>
	        <td width="19"><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
	      </tr>
	      <tr>
	          <td width="19" background="/datacenter/util/imgs/left_line.gif">&nbsp;</td>
		      <td width="672">
		      	<table width="672" cellpadding="0" cellspacing="0" class="texts">
			      	<tr>
			      		<td>&nbsp;
			      		</td>
			      	</tr>
			      	<tr>
			      		<td align="left"><%=messages.getText("generic.supportmaterial.reports.file")%>:</td>
			      		<td align="left"><html:file property="file"/></td>			      					      					      		
			      	</tr>
			      	<tr>
   				      	<td colspan="2" align="left"><%=messages.getText("generic.supportmaterial.reports.date")%>:</td>
   				    </tr>
			      	<tr>
			      		<td colspan="2" align="left">&nbsp;&nbsp;
			      			<html:radio property="radioDate" value="actualdate"  />&nbsp;
			      			<%=messages.getText("generic.supportmaterial.reports.actualdate")%>
			      		</td>
			      	</tr>
			      	<tr>
			      		<td colspan="1" align="left">&nbsp;&nbsp;
			      			<html:radio property="radioDate" value="otherdate" />&nbsp;
			      			<%=messages.getText("generic.supportmaterial.reports.otherdate")%>
			      		</td>
			      		<td colspan="1" align="left">
			      			<html:text property="otherDateDay" maxlength="2" size="2" value="" styleId="dayId1"/>
			      			<html:text property="otherDateMonth" maxlength="2" size="2" value="" styleId="monthId1"/>
			      			<html:text property="otherDateYear" maxlength="4" size="4" value="" styleId="yearId1"/>
			      			<html:img page="/component/jscalendar-1.0/img.gif" styleId="calendar_button1" style="cursor: pointer; border: 1px solid red;"
     						onmouseover="this.style.background='red';" style="width:20;" align="middle" onmouseout="this.style.background=''"/>
			          		<script type="text/javascript">
								Calendar.setup({
        							inputArea     :    "yearId1",
        							ifFormat       :    "%d/%m/%Y",
        							showsTime      :    true,
        							timeFormat     :    "24",
        							button         :    "calendar_button1",
        							step           :    1,
        							onSelect       :    onSelect1,
        							singleClick    :    true
    							});
							</script>
			      			<%="( "+messages.getText("generic.supportmaterial.reports.format")+": "+messages.getText("generic.messages.dateformat")+" )"%>
			      		</td>
			      	</tr>
			      	<tr>
			      		<td>&nbsp;</td>
			      	</tr>
			      	<tr>
			      		<td>
			      			<html:hidden property="directory" value='<%=directory%>' />
			      			<html:submit value='<%=messages.getText("generic.supportmaterial.forms.uploadforms")%>' />
			      		</td>
			      	</tr>
			      	
		      	</table>              
		      </td>
		      <td width="19" background="/datacenter/util/imgs/right_line.gif">&nbsp;</td>  	
	      </tr>
	      <tr>
	        <td width="19"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
        	<td width="672" background="/datacenter/util/imgs/center_base.gif"><img src="x" height="1" width="1"></td>
    	    <td width="19"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
	      </tr>
		</table>
		</html:form>
		</td></tr>
<%	} %>

  <tr>
    <td> 
      <table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
          <td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
          <%=messages.getText("generic.supportmaterial.forms")%></td>
        <td width="19"><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
      </tr>
      <tr>
        <td width="19" background="/datacenter/util/imgs/left_line.gif">&nbsp;</td>
        <td width="672">
        	<table width="672" cellpadding="0" cellspacing="0" class="texts">
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
				{ %>
					
					<!--  Titulos:  -->
              <tr><td colspan="3">               
              <table width="672" cellpadding="0" cellspacing="0" class="texts">
              <tr>
                <td width="80%" align="left">
                	<%=messages.getText("generic.supportmaterial.forms.files") %>
                <td>
                <td width="10%" align="center">	
                  	<%=messages.getText("generic.supportmaterial.reports.date") %>
                </td>  
                <td width="10%" align="center">	
                  	<%=messages.getText("generic.supportmaterial.reports.filesize") %>
                </td>
              </tr> 
              </table>
              </td></tr>
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr> 
					
					
					<%
					Arrays.sort(fileNames);
					fileCounter = 0;
					while (fileNames.length > fileCounter)
					{										
						// link = ".."+directory.substring(5)+"/"+fileNames[fileCounter];
						link = SecurityConstants.DATACENTER5_LINK_DIRECTORY + corporation + "/forms/" + language +"/"+fileNames[fileCounter] ;
						file = new File(directory+"/"+fileNames[fileCounter]);
						name = file.getName().replace("_"," ");						
						date = new Date(file.lastModified());
						fileSize = file.length();
						fileCounter++;
						// Muestro solo si no es un directorio
						if (!file.isDirectory())
						{					 												 		
%>
              <tr> 
                <td width="80%" align="left">
                	<img src="../datacenter/util/imgs/icon1.jpg" width="12" height="12">
                	&nbsp;&nbsp;<strong><a href='<%=link%>' ><%=name%></a></strong> 
                </td>
                <td width="10%" align="right"> <div align="center"><strong><%=dateFormat.format(date)%></strong> </div></td>
                <td width="10%" align="right"> <div align="center"><strong><%=((fileSize >= 1000)? (nf.format(fileSize/1000)+"KB"):(nf.format(fileSize)+"Bytes"))%></strong> </div></td>
              </tr>
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr>
<%
						}
					}
				}else{
%>
			  <tr> 
                <td width="100%" colspan="3">
                	<%=messages.getText("generic.supportmaterial.forms.noforms")%>
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
                	<%=messages.getText("generic.supportmaterial.forms.noforms")%>
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
  
<%	}catch(Exception e){
		e.getMessage();
		e.printStackTrace();
	}
 %>
  
    
</table>
</body>
</html:html>

