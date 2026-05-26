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
	import="presentation.translator.web.documents.*"
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
	DataTypeAccess datatypes;
	GeoItemData item;
	String check;
	String addDocumentPermission;
	boolean canAddDocument = false;
	String country;
	String directory = "";	
	String linkDirectory;
	File dir;
	File subDir;
	File file;
	Collection files;
	String link = "";
	String name="";
	String dirName="";
	String subDirectory="", linkSubDirectory = "";
	String[] fileNames, dirNames;
	Date date;
	long fileSize;
	int fileCounter = 0;
	int dirCounter = 0;
	List subdirs = new LinkedList();
	Format dateFormat;	
	LinkedList dateSort = new LinkedList();
	dateFormat = new SimpleDateFormat("dd-MM-yyyy");			
	CorporationDirectoryData cdd;
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
	
	check = Util.checkPermission(sys,SecurityConstants.VIEW_DOCUMENT);
		
	
	// Me fijo si tiene permisos para agregar reportes
	addDocumentPermission = Util.checkPermission(sys,SecurityConstants.ADD_DOCUMENT);
	
	if(check != null ) {
		response.sendRedirect(request.getContextPath()+check);
	}else{
		
		if (addDocumentPermission == null) {
	// Si tiene permiso para agregar documentos y viene con la opcion de agregar
	// le dejo agregar documentos
	String add = (String)request.getParameter("add");
	canAddDocument = new Boolean(add.trim()).booleanValue();;
		}
		
		//System.out.println("Country= "+country);
		Integer corporationId = sys.getUserSessionData().getFilter().getCorporationId();

		String corporation = ""; // Nombre de la carpeta donde estan los Documentos:
	
		// Obtengo el directorio de archivos de la corporacion:
		cdd = sys.getCorporationReportFacade().
	findCorporationDirectoryByPrimaryKey(corporationId,sys.getUserSessionData());
	
		if (cdd == null || Util.empty(cdd.getDirectory())){
	corporation = "null";
		}else{
	corporation = cdd.getDirectory();
		}	
		
		// directory = "/home/datacenter5/" + corporation + "/reports" ;
		directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY + corporation + "/" + request.getParameter("fileDocument");
		linkDirectory = SecurityConstants.DATACENTER5_LINK_DIRECTORY + corporation + "/" + request.getParameter("fileDocument");
		
		DocumentUploadForm ruf = new DocumentUploadForm();
%>
<html:html>
<head>
<style>
a:link{
	color: black;
}
a:visited{
	color: black;
}
a:hover{
	color: black;
}
a:active{
	color: black;
}
</style>
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
<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
<%if (canAddDocument){ %>		
	<tr><td>
	<html:form action="DocumentUpload" method="post" enctype="multipart/form-data" >	
		<table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
	          <td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
	          	<%=messages.getText("generic.supportmaterial.reports.add"+request.getParameter("fileDocument")) %>
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
			      		<td align="left"><%=messages.getText("generic.supportmaterial.reports.year")%>:</td>
			      		<td align="left">
			      		
			      		<%
			      			LinkedList col= new LinkedList();
				      		Calendar cal = Calendar.getInstance();
				      		for (int i=2000; i<= cal.get(1)+10; i++)	{
				      			col.add(new OptionItem(Integer.toString(i),Integer.toString(i)));
				      		}
				      		session.setAttribute("years",col);					      		
			      		%>
			                <html:select property="year" styleClass="input" style="width:155;"  >
        			          <html:options collection="years" property="value" labelProperty="label" styleClass="input"/>
			                </html:select>
						</td>
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
			      			<html:text property="otherDateDay" maxlength="2" size="2" styleId="dayId1" />
			      			<html:text property="otherDateMonth" maxlength="2" size="2" styleId="monthId1"/>
			      			<html:text property="otherDateYear" maxlength="4" size="4" styleId="yearId1"/>
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
			      		(<%=messages.getText("generic.supportmaterial.reports.format")%>: DD/MM/YYYY)</td>
			      	</tr>
			      	<tr>
			      		<td>&nbsp;</td>
			      	</tr>
			      	<tr>
			      		<td>
			      			<html:hidden property="directory" value='<%=directory%>' />
			      			<html:submit value='<%=messages.getText("generic.supportmaterial.document.upload")%>' />
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



<%		System.out.println("dir "+directory);
		dir = new File(directory);
		try
		{
			if ( dir.isDirectory() )
			{
				dirNames = dir.list();	
				if (dirNames != null && dirNames.length > dirCounter)
				{
					Arrays.sort(dirNames, Collections.reverseOrder());
					dirCounter = 0;
					while (dirNames.length > dirCounter)
					{										
						//link = ".."+directory.substring(5)+"/"+dirNames[dirCounter];
						subDir = new File(directory+"/"+dirNames[dirCounter]);
						subDirectory = directory + "/" +dirNames[dirCounter];
						linkSubDirectory = linkDirectory + "/" +dirNames[dirCounter]; 
						dirName = subDir.getName();
						dirCounter++;
%>

  <tr>
    <td> 
      <table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
          <td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
          <%=messages.getText("generic.supportmaterial."+request.getParameter("fileDocument"))%>&nbsp;-&nbsp;<%=dirName%>&nbsp;
         </td>
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
				//file = new File (directory);
				if ( subDir.isDirectory() )
				{
					fileNames = subDir.list();
					fileCounter = 0;
					if (fileNames != null && fileNames.length > fileCounter)
					{

						System.out.println("Cantidad de Archivos: "+fileNames.length);
						%>
	          <!--  Titulos:  -->
              <tr><td colspan="3">               
              <table width="672" cellpadding="0" cellspacing="0" class="texts">
              <tr>
                <td width="80%" align="left">
                	<%=messages.getText("generic.supportmaterial.reports.files") %>
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

<%						// Ordeno los archivos por fecha
						dateSort = new LinkedList();
						while (fileNames.length > fileCounter)
						{
							file = new File(subDirectory+"/"+fileNames[fileCounter]);
							dateSort.add(new LongItem(new Long(file.lastModified()),
									file.getName()));
							fileCounter++;
						}
						Collections.sort(dateSort,Collections.reverseOrder());
						Iterator it = dateSort.iterator();
						while (it.hasNext())
						{					
							LongItem oi = (LongItem)it.next();
							if (oi == null) System.out.println("NULL");
							System.out.println("File: "+oi.getLabel()+", "+oi.getValue());
							link = linkSubDirectory +"/"+oi.getValue();
							System.out.println("File: "+subDirectory+"/"+oi.getValue());
							file = new File(subDirectory+"/"+oi.getValue());
							name = oi.getValue().replace("_"," ");
							date = new Date(oi.getLabel().longValue());
					 		fileSize = file.length();										 		
%>
              <tr> 
                <td width="80%" align="left">
                	<img src="../datacenter/util/imgs/icon1.jpg" width="12" height="12">
                	&nbsp;&nbsp;<strong><a href='<%=link%>' ><%=name%></a></strong> 
                </td>
                <td width="10%" align="right"> <div align="center"><strong><%=dateFormat.format(date)%></strong> </div></td>
                <td width="10%" align="right"> <div align="center"><strong><%=(fileSize >= 1000)? nf.format(fileSize/1000)+"KB":nf.format(fileSize)+"Bytes"%></strong> </div></td>
              </tr>
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr>
<%
						}
					}else{
%>
			  <tr> 
                <td width="100%" colspan="3">
                	<%=messages.getText("generic.supportmaterial.documents.nodocuments")%>
                </td>
	          </tr>
              <tr> 
                <td width="100%" colspan="3">&nbsp;</td>
              </tr>
<%					}
				}else{
%>

			  <tr> 
                <td width="100%" colspan="3">
                	<%=messages.getText("generic.supportmaterial.documents.nodocuments")%>
                </td>
	          </tr>
              <tr> 
                <td width="100%" colspan="3">&nbsp;</td>
              </tr>
<%					
				}				
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
				}// Fin while años
			}// Fin if
	  	}else{ 
%>
 <tr>
    <td> 
      <table width="700"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="19"><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
          <td width="672" background="/datacenter/util/imgs/center.jpg" class='guide1'>
          <%=messages.getText("generic.supportmaterial."+request.getParameter("fileDocument"))%>&nbsp;-&nbsp;
         </td>
        <td width="19"><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
      </tr>
      <tr>
        <td width="19" background="/datacenter/util/imgs/left_line.gif">&nbsp;</td>
        <td width="672">
        	<table width="672" cellpadding="0" cellspacing="0" class="texts">
              <tr> 
                <td colspan="3">&nbsp;</td>
              </tr>
  		<tr> 
            <td width="100%" colspan="3">
            	<%=messages.getText("generic.supportmaterial.documents.nodocuments")%>
            </td>
        </tr>
        <tr> 
            <td width="100%" colspan="3">&nbsp;</td>
        </tr>
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
<%	  	}
	}catch(Exception e){
		System.out.println("DocumentYear.jsp exception: \n"+e.getMessage()+"\n");
		e.printStackTrace();
	}
%>
</table>
</body>
</html:html>
<%	}
%>
