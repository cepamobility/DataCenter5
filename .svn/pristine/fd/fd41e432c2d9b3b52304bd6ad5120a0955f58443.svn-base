<%@page language="java"
	import="business.*"
	import="business.administration.corporation.*"
	import="communication.language.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.administration.corporation.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="java.lang.*"
	errorPage="../exception.jsp"
%> 

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html:html>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<%! 
	RequestDispatcher dispatcher; String context; String pathMsg; String pathFindAllWwRegion;
	Text messages; String pathLanguage;
	SystemAccess sys; CorporationCreateAction crpCreateAction;
	CorporationData data; CorporationReportFacade crpReport;
	Integer cid;
%>
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect("/login.jsp");
	}

	String check = Util.checkPermission(sys,communication.security.SecurityConstants.EDIT_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
	
		session.setAttribute("url","corporation/edit.jsp");
		session.setAttribute("target","edit");
	
		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
		
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
		
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		messages=sys.getText();
		
		cid=sys.getUserSessionData().getFilter().getCorporationId();
		
		session.removeAttribute("level1");
		session.removeAttribute("level2");
		if( (cid!=null)){
		
			crpReport=sys.getCorporationReportFacade();
		
			data=crpReport.findCorporationByPrimaryKey(cid,sys.getUserSessionData());
			
			CorporationSearchForm form = new CorporationSearchForm(cid,data);
			request.setAttribute("CorporationUpdateForm",form);
			session.removeAttribute("CorporationAddCountryForm");
			session.removeAttribute("CorporationDeleteCountryForm");
			session.removeAttribute("CorporationUpdateCountryForm");
%>

<script>
	function deleteItem(form,formCheck,id,labelId){
		var msg="Are you sure, that you want to permanently delete this item ? ";
		var elements=formCheck.elements;
		var length=elements.length;
		var i;
		var valueCheckboxParamList="";
		var separator="<";
		
		for(i=0;i<length;i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				
				if(element.checked){
					if(valueCheckboxParamList==""){
						valueCheckboxParamList=element.value;
					}else{
						valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
					}	
				}	
			} 
		
		}
		form.elements[labelId].value=valueCheckboxParamList;
		if(id != -1) {
			form.elements['id'].value=id;
		}
		form.elements['level'].value="0";
		if(valueCheckboxParamList.length>0){
			if(confirm(msg)){
				form.submit();
			}
		}
	}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style><head/>
<body>

<html:form action="/Corporationdeleteitem">
	<html:hidden property="level"/>
	<html:hidden property="id"/>
	<html:hidden property="label3"/>
</html:form>

<html:form action="/Corporationdeletecountry">
	<html:hidden property="level" />   
	<html:hidden property="level1" />   
	<html:hidden property="label1" />   
	<html:hidden property="level2" />   
	<html:hidden property="label2" />   
	<html:hidden property="label3" />   
</html:form>

	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
			<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.driver.driverdata.corporation")%>' />
  		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
  		<tr>
    		<td width="292" height="80" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.corporationdata.data")%>' />
    			<table width='100%' align='center' cellpadding="0" cellspacing="0">
    				<tr>
        				<td>
        					<table width='100%' border="0" cellpadding="0" cellspacing="0" class="lineone">
            					<tr>
              						<td align="left" valign="middle" class="linetwo">&nbsp;<img align="middle" border="0" height="12" src="/datacenter/util/imgs/icon1.jpg" width="12"> <strong><%=messages.getText("generic.corporation.corporationdata.name")%></strong></td>
              						<td align="center" valign="middle" class="linetwo">
										<html:form action="/Corporationupdate" onsubmit="javascript:loadList()">
	              							<html:hidden property="corporationId"/>
	                  						<html:text property="corporationName" size="25" styleClass="input" disabled="true"/>              
										</html:form>
                  					</td>
            					</tr>
        					</table>
        				</td>
      				</tr>
			    </table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
        	<td width="16">&nbsp;</td>
		    <td width="292" height="80" valign="top">
		    	<table align='center' width='100%' cellpadding="0" cellspacing="0">
		    	<%
		    		String include1 = "../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.actions");
		    	%>
				<jsp:include  page='<%=include1%>' />
				<table align='center' width='100%' cellpadding="0" cellspacing="0" >
<%			Iterator it1 = data.getDivOrgsIterator();
			boolean two = true;
			DivOrgData divOrgData=null;
			while(it1.hasNext()) {
				divOrgData = (DivOrgData)it1.next();
%>		    		<tr class="linetwo">
              			<td>
              				<img align="middle" border="0" height="12" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;<a href='layout.jsp?refer=/corporation/AddItem.jsp?level=<%="1"%>&id=<%=divOrgData.getId()%>'><%=messages.getText("generic.messages.modify")+" "+messages.getText(divOrgData.getName())%></a> 
              				<br>              
              			</td>
            		</tr>
<%				two = !two;
			} 
%>
            		<tr class="linetwo">
              			<td>
              				<img align="middle" border="0" height="12" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;<a href="layout.jsp?refer=/corporation/AddCountry.jsp?level=1"><%=messages.getText("generic.messages.modify")+" "+messages.getText("corporation.divgeo.countries")%> </a> 
              			</td>
            		</tr>
<% 			if (data.getId().intValue() == CorporationData.FORESTALORIENTAL || data.getId().intValue() == CorporationData.MONTES_DE_PLATA  || data.getId().intValue() == CorporationData.WEYERHAEUSER){%>            		
            		<tr class="linetwo">
              			<td>
              				<img align="middle" border="0" height="12" src="/datacenter/util/imgs/icon1.jpg" width="12">&nbsp;<a href='layout.jsp?refer=/alert/AddMail.jsp?id=<%=divOrgData.getId()%>'><%=messages.getText("generic.messages.modify")+" "+messages.getText("corporation.divgeo.mails")%> </a> 
              			</td>
            		</tr>
<% 			}%>            		
        		</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
      	</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%	    	Iterator divOrgs = data.getDivOrgsIterator();
       		while(divOrgs.hasNext()) {
				divOrgData = (DivOrgData)divOrgs.next();
				session.setAttribute("currentDivOrg",divOrgData);
%>     	<tr>
    		<td  colspan="3">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(divOrgData.getName())%>' />
    			<table width='100%' align='center' cellpadding="0" cellspacing="0">
					<tr align='center' >
					  	<td height="10" valign="middle"><jsp:include page="./divorg.jsp" /></td>
					</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
<%       	}
%>     	<tr>
    		<td  colspan="3">
<%			session.setAttribute("geoLabelData",data.getDivGeoDataSet());
%>				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("corporation.divgeo.countries")%>' />
    			<table width='100%' align='center' border="0" cellpadding="0" cellspacing="0">
					<tr align='center' width='90%'>
					  	<td height="10" valign="middle"><jsp:include page="./divgeo.jsp" /></td>
					</tr>
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>
      	<tr>
			<jsp:include  page="../component/spaceline.jsp" />
  		</tr>
	</table>
<%		}
	}
%>
</body>
</html:html>