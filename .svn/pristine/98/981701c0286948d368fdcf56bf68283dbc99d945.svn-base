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
 	import="communication.datatype.tables.ActivityTypeDataType"
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
	String checkedNoTeoricoAsistenceStr="";			
	String checkedNoPracticoAsistenceStr="";				
	boolean noTeoricoAsistence;
	boolean noPracticoAsistence;
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
		
		// session.setAttribute("url","activity/btw1.jsp?idActivity="+idActivity+"&idDriver="+idDriver);
		url = "activity/btw1.jsp?idActivity="+idActivity+"&idDriver="+idDriver;
		userSessionData.setUrl(url);
		
		activityData = (CEPAActivityScheduleData) session.getAttribute("activityData");
		
		String noteStr = "";
		String obsStr = "";
		String instructorStr = "";	
		String selNota1 ="";
		String selNota2="";
		String selNota3="";
		Integer corporation = sys.getUserSessionData().getFilter().getCorporationId();
		CEPAActivityScheduleRegistryData registryData;
		if (activityReport.hasResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData())) {
			registryData = activityReport.getCompleteResult(
				new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
			
			session.setAttribute("registryData",registryData);
					
			noteStr = registryData.getNote();
			obsStr = registryData.getObs();		
			instructorStr = registryData.getInstructor();	
			
			if (registryData.getNote().equals("generic.activity.note.notapruved")) {
				selNota1 = "selected";
			}
			if (registryData.getNote().equals("generic.activity.note.apruved")) {
				selNota2 = "selected";
			}
			if (registryData.getNote().equals("generic.activity.note.apruvedwithobservations")) {
				selNota3 = "selected";
			}		
			
			noTeoricoAsistence = !registryData.getTeoricoAsistence();
			checkedNoTeoricoAsistenceStr ="";
			if (noTeoricoAsistence) {
				checkedNoTeoricoAsistenceStr ="checked";				
			}
			
			noPracticoAsistence = !registryData.getPracticoAsistence();
			checkedNoPracticoAsistenceStr ="";							
			if (noPracticoAsistence) {
				checkedNoPracticoAsistenceStr ="checked";							
			}		
		}	
		
%>	

<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">

<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
</head>

<script language="JavaScript" type="text/javascript">
function preparedCheckboxRadioParameters(form){

		var elements=form.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
		
			if((element.type.toLowerCase()=="checkbox") || (element.type.toLowerCase()=="radio")) {
				if ((element.checked) 
				&& ((element.name !="checkboxasistence1")) && ((element.name !="checkboxasistence2"))
				&& ((element.name !="checkboxnovato"))
				&& ((element.name !="velocityBox"))
				&& (element.name !="optradiofrenaje") && (!(element.disabled))){
							
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}
					else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
			}
		}
		form.checkboxParamList.value=valueCheckboxParamList;
		return true;
}

function setAction(form1,form2){
	if(preparedCheckboxRadioParameters(form1)) {
		form2.checkboxParamList.value = form1.checkboxParamList.value;
		form2.note.value = form1.note.value;
		form2.obs.value = form1.obs.value;		
		form2.instructor.value = form1.instructor.value;	
		
    	form2.noteoricoasistence.value = form1.checkboxasistence1.checked;				
		form2.nopracticoasistence.value = form1.checkboxasistence2.checked;	

		form2.idDriver.value = '<%=idDriver%>';
		form2.idActivity.value = '<%=idActivity%>';
		form2.url.value = '<%=url%>';				
		form2.submit();
	}
}
</script>
<body>
<div align="center"> 

<form name="form2" method="post" action="">
    <table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td height="40" colspan="1" valign="middle">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr> 
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
        <br/>
        </td>
      </tr>
      
	<!--  Titulo del Punto: -->

      <tr> 
        <td colspan="1" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  <strong><%= messages.getText("generic.activity.asistence")%></strong></font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence1" class="check" <%=checkedNoTeoricoAsistenceStr %>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.asistence.noteorico")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> <input type="checkbox" name="checkboxasistence2" class="check" <%=checkedNoPracticoAsistenceStr%>> 
                <font size="1" face="Arial, Helvetica, sans-serif"> <%= messages.getText("generic.activity.asistence.nopractico")%></font> 
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>            
            
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table> 
          <br/>           
		</td>
	</tr>
	
	<!--  Texto Fijo -->
	<tr>
	 <td colspan="1" valign="top"> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td width="9"><img src="/datacenter/util/imgs/left_form.gif" width="9" height="15"></td>
              <td background="/datacenter/util/imgs/center_form.gif"> <div align="center"><font color="#FFFFFF" size="1" face="Arial, Helvetica, sans-serif"> 
                  </font></div></td>
              <td width="9"><strong><font color="#FFFFFF"><img src="/datacenter/util/imgs/right_form.gif" width="9" height="15"></font></strong></td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                <font size="3" face="Arial, Helvetica, sans-serif"><strong>
					<%=(corporation.equals(new Integer(4)))?
						messages.getText("generic.activity.monitores1.title1J&J"):
						messages.getText("generic.activity.monitores1.title1")%></strong>
				</font>                
				<br/>
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                <font size="3" face="Arial, Helvetica, sans-serif">&nbsp;</font>                				
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>            
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                <font size="2" face="Arial, Helvetica, sans-serif"><strong>
					<%=(corporation.equals(new Integer(4)))?
						messages.getText("generic.activity.monitores1.text1J&J"):
						messages.getText("generic.activity.monitores1.text1")%></strong>
				</font>				               
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                <font size="3" face="Arial, Helvetica, sans-serif">&nbsp;</font>                				
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>           
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                <font size="3" face="Arial, Helvetica, sans-serif">
					<%=(corporation.equals(new Integer(4)))?
						messages.getText("generic.activity.monitores1.objectiv1J&J"):
						messages.getText("generic.activity.monitores1.objectiv1")%>
				</font>                
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" background="/datacenter/util/imgs/line_left.gif">&nbsp;</td>
              <td> 
                <font size="1" face="Arial, Helvetica, sans-serif">
					<%if (corporation.equals(new Integer(4))){ %>
					<ul>
					<li><%=messages.getText("generic.activity.monitores1.point1J&J")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point2J&J")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point3J&J")%></li>						
					<li><%=messages.getText("generic.activity.monitores1.point4J&J")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point5J&J")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point6J&J")%></li>
					</ul>
					<% }else{ %>
					<ul>
					<li><%=messages.getText("generic.activity.monitores1.point1")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point2")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point3")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point4")%></li>						
					<li><%=messages.getText("generic.activity.monitores1.point5")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point6")%></li>
					<li><%=messages.getText("generic.activity.monitores1.point7")%></li>
					</ul>
					<%} %>
				</font>                
              </td>
              <td width="9" background="/datacenter/util/imgs/line_right.gif">&nbsp;</td>
            </tr>             
            <tr> 
              <td width="9" height="19" valign="top"><img src="/datacenter/util/imgs/left_base_form.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_base.gif">&nbsp;</td>
              <td width="9" valign="top"><img src="/datacenter/util/imgs/right_base_form.gif" width="9" height="11"></td>
            </tr>
          </table>
          <br/>            
		</td>
	</tr>
	<!--  Fin Texto fijo -->
	
    <tr> 
        <td >
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr> 
              <td valign="top"><img src="/datacenter/util/imgs/left_top_gray.gif" width="9" height="11"></td>
              <td background="/datacenter/util/imgs/center_top_gray.gif"> <div align="left"><strong><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></strong></div></td>
              <td valign="top"><strong><img src="/datacenter/util/imgs/right_top_gray.gif" width="9" height="11"></strong></td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td align='center' bgcolor="#f9f9f9"><div align="left"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.note") %><strong></font></div></td>
              <td background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"> 
              <%
	              		DataTypeReportFacade dataTypeReportFacade = sys.getDataTypeReportFacade();		
	      				Collection notes = dataTypeReportFacade.findAll(DataTypeConstants.TYPECEPAACTIVITYNOTE,userSessionData);
	              		if (notes != null){ 
	              	%>
	              		<select name="note" class="input">
	              		
	              		<%
	              			Iterator it = notes.iterator();
	              			while (it.hasNext()){
	              				String activityNote =  ((CEPAActivityNoteDataType) it.next()).getResourceBundleId();
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
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td  background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td  bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"><strong><%= messages.getText("generic.activity.observations") %></strong></font></td>
              <td  background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td width="9" height="88" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9"><strong><font color="#FF0000" size="1" face="Arial, Helvetica, sans-serif"> 
                <textarea name="obs" cols="70" rows="15"><%= obsStr%></textarea>
                </font></strong></td>
              <td  background="/datacenter/util/imgs/right_gray.gif">&nbsp;</td>
            </tr>
            <tr> 
              <td valign="top" background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td bgcolor="#f9f9f9">&nbsp;</td>
              <td valign="top" background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td  background="/datacenter/util/imgs/left_gray.gif">&nbsp;</td>
              <td  bgcolor="#f9f9f9"><font size="1" face="Arial, Helvetica, sans-serif"> 
                <strong><%= messages.getText("generic.activity.instructor")%></strong> 
                <input type=text name="instructor" value = '<%= instructorStr%>' class="input"> </font></td>
              <td  background="/datacenter/util/imgs/right_gray.gif" bgcolor="#f9f9f9">&nbsp;</td>
            </tr>
            <tr> 
              <td  height="11" valign="top"><img src="/datacenter/util/imgs/left_base_gray.gif" width="9" height="11"></td>
              <td  valign="top" background="/datacenter/util/imgs/center_base_gray.gif"><img src="/datacenter/util/imgs/center_base_gray.gif" width="1" height="11"></td>
              <td  valign="top"><img src="/datacenter/util/imgs/right_base_gray.gif" width="9" height="11"></td>
            </tr>
          </table>
          <br/>
          </td>
      </tr>
      <tr> 
       	<td align="right"> 
            <input name="button2" type="button" value="Aceptar" 
            	onClick="javascript:setAction(document.forms['form2'],document.forms['ActivityRegistryForm']);" class="input" align="right"/>
            <input type="hidden" name="checkboxParamList"/>
        	<input type="hidden" name="skidcar" value="" />
        	<input type="hidden" name="plate" value="" />
	        <input type="hidden" name="frenaje" value ="convencional"/>       
	        <input type="hidden" name="novato" value="false"/>	                 	                       	
        </td>
      </tr>
    </table>
    
        	
  </form>
  
</div>

<html:form action = "ActivityRegistrycreate" method="post">
	<html:hidden property="idDriver" value='<%=idDriver%>' />
	<html:hidden property="idActivity" value='<%=idActivity%>' />
	<html:hidden property="url" value='<%=url%>' />
	<html:hidden property="checkboxParamList" value="checkboxParamList" />
	<html:hidden property="obs" value="obs" />	
	<html:hidden property="note" value="note" />	
	<html:hidden property="instructor" value="instructor" />		
	<html:hidden property="partner" value="partner" />	
	<html:hidden property="cepaCourse" value="cepaCourse" />	
	<html:hidden property="conductionType" value="conductionType" />	
	<html:hidden property="plate" value="" />			
	<html:hidden property="skidcar" value="skidcar" />			
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
	<html:hidden property="frenaje" value="frenaje" />						
	<html:hidden property="novato" value="false" />							
	<html:hidden property="noteoricoasistence" value="false" />								
	<html:hidden property="nopracticoasistence" value="false" />									
</html:form>

</body>
<%
	}	
%>	
</html:html>