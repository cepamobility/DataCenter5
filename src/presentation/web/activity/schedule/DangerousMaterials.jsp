<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="business.activity.*"
	import="business.administration.user.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.tables.*"
	import="communication.activity.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.util.Filter"
 	import="presentation.translator.web.util.*"
 	import="presentation.translator.web.activity.schedule.*" 	
 	import="communication.administration.user.*" 	
 	import="business.activity.ActivityReportFacade"
	import="java.util.*"	
	import="business.datatype.*"	
	import="communication.datatype.tables.*"
	import="communication.datatype.*"	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>
<%!
	RequestDispatcher dispatcher; String context;String pathView; String pathMsg; 
	String pathSearch;
	String pathLanguage; Text messages; SystemAccess sys;
	Integer pageNum; boolean all; int pageSize; 
	String attribute; String value;
	UserSessionData userSessionData; 
	ActivityReportFacade activityReport;
	String str1;
	String str2;	
	String str3, url, idActivity, idDriver;		
	CEPAActivityScheduleData activityData;
		
	
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");

	userSessionData=sys.getUserSessionData();
	
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITYREGISTRY);
	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		idActivity=(String)request.getParameter("idActivity");
		if(idActivity==null){
			idActivity=(String)session.getAttribute("id");			
		}
		// session.setAttribute("id",idActivity);
		
		idDriver=(String)request.getParameter("idDriver");
		// session.setAttribute("idDriver",idDriver);			
		
		activityReport = sys.getActivityReportFacade();
		
		url = "activity/schedule/DangerousMaterials.jsp?idActivity="+idActivity+"&idDriver="+idDriver;
		userSessionData.setUrl(url);
		
		activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");
		
		String noteStr = "";
		String obsStr = "";
		String instructorStr = "";	
		String result = "0"; // 0 a 100
				
		CEPAActivityScheduleRegistryData registryData;
		if (activityReport.hasResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData())) {
			registryData = activityReport.getCompleteResult(
				new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
			
			session.setAttribute("registryData",registryData);
					
			noteStr = registryData.getNote();
			obsStr = registryData.getObs();		
			instructorStr = registryData.getInstructor();
			result = String.valueOf(registryData.getClassNumber());		
				
		}	
		
%>	


<script language="JavaScript" type="text/javascript">

function validateInteger(value){
	value = parseInt(value);
    if (isNaN(value) || value < 0 || value > 100 ) {
       return "_";
    }else{
       return value;
    }
} 

function setAction(form1,form2){
			
	result = validateInteger(form1.result.value);	
	if(result == "_") {
		alert('<%=messages.getText("result.mustbebetween.0and100")%>');
		return false;
	}
	form2.classNumber = form1.result;

	form2.note.value = form1.note.value; 
	form2.obs.value = form1.obs.value;	
	form2.instructor.value = "";
	
	form2.noteoricoasistence.value = false;				
	form2.nopracticoasistence.value = false;		
	
	form2.idDriver.value = '<%=idDriver%>';
	form2.idActivity.value = '<%=idActivity%>';
	form2.url.value = '<%=url%>';
	
	form2.submit();
	
}
</script>

<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">

<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
</head>

<body>
<div align="center"> 

<form name="form2" method="post" action="">
    <table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td height="40" colspan="5" valign="middle">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr > 
              <td width="9" height="40"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40"></td>
              <td height="40" background="/datacenter/util/imgs/bg2.jpg"> 
              <div align="center">
              <font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
              <strong>
              <%=messages.getText(activityData.getDefinition().getResourceKeyName())%>
              </strong></font></div></td>
              <td width="9" height="40"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="40"></td>
            </tr>
        </table>
        </td>
      </tr>
      <tr> 
        <td width="200">&nbsp;</td>
        <td>&nbsp;</td>
        <td width="200">&nbsp;</td>
        <td>&nbsp;</td>
        <td width="200">&nbsp;</td>
      </tr>
      
	<!--  Titulo del Punto: -->

		

      <tr class="lineone"> 
        <td colspan="5"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr class="lineone"> 
              <td valign="top"><img src="/datacenter/util/imgs/left_top_gray.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_top_gray.gif"> <div align="left"><strong><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></strong></div></td>
              <td valign="top"><strong><img src="/datacenter/util/imgs/right_top_gray.gif" width="9" height="11"></strong></td>
            </tr>
            
           
            <tr class="lineone"> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td align='left'>
              	
              		<strong><%= messages.getText("generic.activity.note") %></strong>
              	
              </td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr class="lineone"> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td>
              	<%
              		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
      				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
              		if (notes != null){ 
              	%>
              		<select name="note" class="input">
              		
              		<%
              			Iterator it = notes.iterator();
              			while (it.hasNext()){
              				String activityNote = ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
              				String selected = noteStr.equals(activityNote)?"selected":"";
              		%>
               			  <option value='<%= activityNote %>' class="input" <%= selected %> ><%= messages.getText(activityNote) %></option>
               		<%
               			}%>
                	</select> 
                <%
                	}%>              
                  </td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            
            
            <tr class="lineone"> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td>&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f7f7f7">&nbsp;</td>
            </tr>
            <tr class="lineone"> 
              <td  background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td  bgcolor="#f7f7f7">
              	
              		<strong><%= messages.getText("generic.activity.observations") %></strong>
              	
              </td>
              <td  background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f7f7f7">&nbsp;</td>
            </tr>
            <tr class="lineone"> 
              <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td>
              	<strong>
              		 
                		<textarea name="obs" cols="75" rows="15"><%= obsStr%></textarea>
                	
                </strong>
              </td>
              <td  background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr class="lineone"> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td>&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f7f7f7">&nbsp;</td>
            </tr>
            <tr class="lineone"> 
              <td  background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td>
              	
                	<strong><%=messages.getText("generic.activity.result")%></strong> 
                	<input type=text name="result" value='<%=result%>' class="input" /> 
                
              </td>
              <td  background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f7f7f7">&nbsp;</td>
            </tr>
            <tr> 
              <td  height="11" valign="top"><img src="/datacenter/util/imgs/left_base_gray.gif" width="9" height="11"></td>
              <td  valign="top" background="/datacenter/util/imgs/center_base_gray.gif"><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></td>
              <td  valign="top"><img src="/datacenter/util/imgs/right_base_gray.gif" width="9" height="11"></td>
            </tr>
          </table></td>
      </tr>
      <tr> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><div align="right"> 
            <input name="button2" type="button"  value="Aceptar" onClick="javascript:setAction(document.forms['form2'],document.forms['ActivityRegistryForm']);" class="input" align="right"/>
          </div></td>
      </tr>
    </table>
    
        	<input type="hidden" name="checkboxParamList"/>
        	<input type="hidden" name="skidcar" value="" />
        	<input type="hidden" name="plate" value="" />
	        <input type="hidden" name="frenaje" value ="convencional"/>       
	        <input type="hidden" name="novato" value="false"/>	                 	           

  </form>
  
</div>

<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idDriver" value='<%=idDriver%>' />
	<html:hidden property="idActivity" value='<%=idActivity%>' />
	<html:hidden property="url" value='<%=url%>' />
	<html:hidden property="classNumber" value="0" />

	<html:hidden property="checkboxParamList" value="" />
	<html:hidden property="obs" value="" />	
	<html:hidden property="note" value="" />	
	<html:hidden property="instructor" value="" />		
	<html:hidden property="partner" value="" />	
	<html:hidden property="cepaCourse" value="" />	
	<html:hidden property="conductionType" value="" />	
	<html:hidden property="plate" value="" />			
	<html:hidden property="skidcar" value="" />			
	<html:hidden property="trade" value="" />	
	<html:hidden property="initialKm" value="-1" />
	<html:hidden property="finalKm" value="-1" />
	<html:hidden property="initialHr" />
	<html:hidden property="finalHr" />	
	<html:hidden property="initialMinute" />
	<html:hidden property="finalMinute" />							
	<html:hidden property="vehicleType" value="" />									
	<html:hidden property="circulationAreaHighWay" value="false" />
	<html:hidden property="circulationAreaRoad" value="false" />	
	<html:hidden property="circulationAreaSubUrban" value="false" />	
	<html:hidden property="circulationAreaUrban" value="false" />											
	<html:hidden property="velocityBox" value="" />											
	<html:hidden property="light" value="" />	
	<html:hidden property="pointCommentList" value="" />		
	<html:hidden property="frenaje" value="" />						
	<html:hidden property="novato" value="false" />							
	<html:hidden property="noteoricoasistence" value="false" />								
	<html:hidden property="nopracticoasistence" value="false" />									
</html:form>

</body>
<%
	}	
%>	
</html:html>