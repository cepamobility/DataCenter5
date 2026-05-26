<%@page language="java" 
   errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.corporation.*" 
	import="business.alert.*" 
	import="communication.language.*"
	import="communication.administration.corporation.tables.*"
	import="communication.alert.*"
	import="communication.security.*"
	import="presentation.translator.web.administration.corporation.*"
	import="presentation.translator.web.alert.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="org.apache.struts.action.*"
	import="java.util.Hashtable"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>


<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	CorporationData corporation;int size;
	String divOrgId,level1;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		messages=sys.getText();

		session.setAttribute("url","corporation/edit.jsp");
		session.removeAttribute("target");

		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
		divOrgId=(String)request.getParameter("id");
		if (divOrgId==null){
			divOrgId=(String)session.getAttribute("addmail");
		}
		session.removeAttribute("addmail");
		DivOrgData divOrgData = corporation.getDivOrgData(Integer.parseInt(divOrgId));
		if(divOrgData != null) {
			boolean linetwo = true;
			int length = divOrgData.getLabels().length;
			String label = divOrgData.getLabels()[0].getName();
			String labelB ="";
			String editLabel = messages.getText(divOrgData.getName());
			AlertABMFacade alertABMFacade = sys.getAlertABMFacade();
			AlertReportFacade alertReportFacade = sys.getAlertReportFacade();
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<script>


function preparedContractorsMails(form){
	var elements=form.elements;
	var length=elements.length;
	var i;
	var mail=null;
	var dateList="";
	var mailId=null;
	for(i=0;i<=length;i++){
		mailId=document.getElementById("mailid"+i);
		if (mailId!=null){
			mail=document.getElementById("mail"+i).value;
			if ((mail==null)||(mail=="")){
				mail="mailna";
			}
			if(dateList==""){
				dateList = mailId.value+"	"+mail;
			}else{
				dateList = mailId.value+"	"+mail+"<"+dateList;
			}
		}else{
			form.list.value=dateList;
			return true;
		}
	}
	return false
}

function setAction(form,text){
	if(preparedContractorsMails(form)) {
		form.submit();
	}else{
		alert(text);
	}
}

</script>
<head/>
<body bgcolor="#FFFFFF">
<form action="layout.jsp?refer=/corporation/edit.jsp" name='cancel' method="post">
		<input type="hidden" name="target" value="addmail" />
</form>	
<html:form action='<%="/ContractorAddMailsAction"%>'>
<html:hidden property="list" />
<html:hidden property="addmail" value='<%=divOrgId%>'/>
	<table width='600' align='center' cellpadding="0" cellspacing="0">
		<tr class="title2">
			  <td height="40" colspan="3" valign="top">
			    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                	<tr class="title2">
                   		<td width="9"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40" /></td>
                   		<td class="title2" align="left">
							<font size="2"><%= messages.getText("corporation.divorg.editmails")%>  <%=messages.getText(labelB)%></font></td>
                   		<td width="9" align="right"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="40" /></td>
                 	</tr>
             	 </table>			  
             </td>
		  	</tr>
	  		<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
  			<tr>
				<td width="100%" valign="top" colspan="5">
					<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
					<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
						<tr> 
          					<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          					<td width="46%"><a href="javascript:document.forms['cancel'].submit()" class="texts"> 
		           	 			<%=messages.getText("generic.messages.cancel")%> </a></td>
		           	 		<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          					<td width="46%"><a href="javascript:setAction(document.forms['ContractorAddMailForm'],'<%=messages.getText("generic.alert.invaliddata")%>');" class="texts"> 
		           	 		<%=messages.getText("generic.messages.accept")%> </a></td>
		           	 	</tr>
        			</table>
        			<jsp:include page="../component/utilitybox3bottom.jsp"/>
			 	</td>
			</tr>
			<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>	
<%		
			LinkedList[] items = divOrgData.getItems(1,null,null);
%>			<tr align='center' width='100%'>
				<td valign='top' width="412">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.user.divorg.currentitems")%>' />
				<table align="center" width="100%">	
					
<%			LinkedList active = items[0];
			Hashtable result=alertReportFacade.getContractorsMail(active,sys.getUserSessionData());
			Enumeration e = result.keys();
			int pos=0;
			while (e.hasMoreElements())  {
				Object name=e.nextElement();
				ContractorDataData item = (ContractorDataData)result.get(name);
				//ContractorDataData mailData=new ContractorDataData();
%>						<tr align='center' width='90%' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
							<td width='35%' align='center'>
								<input type="hidden" name='<%="mailid"+pos%>' value='<%=item.getId()%>' id='<%="mailid"+pos%>' />
						  	  	<%=name.toString()%>
							</td>
							<td width='60%' align='center'>
<%							if (item.getMail()!=null){ 
%>							
						  	  <input type="text" name='<%="mail"+pos%>' style="width:200;" class="input" value='<%=item.getMail()%>' id='<%="mail"+pos%>'/>
<%                           }else{ 
%>							  <input type="text" name='<%="mail"+pos%>' style="width:200;" class="input" id='<%="mail"+pos%>'/>

<%							} 
%>
							</td>
						</tr>										
<%				linetwo = !linetwo;
				pos+=1;
			}	
							
%>				
							
         		
				</table>
				<jsp:include  page="../component/utilityboxbottom.jsp" />			
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />		
		</tr>
		<tr>
						<td width="100%" valign="top" colspan="5">
						<jsp:include  page='<%="../component/utilitybox3top.jsp?title="+messages.getText("generic.messages.options")%>' />
						<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
							<tr> 
          						<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          						<td width="46%"><a href="javascript:document.forms['cancel'].submit()" class="texts"> 
		           	 				<%=messages.getText("generic.messages.cancel")%> </a></td>
		           	 			<td width="3%" class="texts1"><img align="middle" border="0" src="./util/imgs/icon2.gif" width="12">&nbsp;</td>
          						<td width="46%"><a href="javascript:setAction(document.forms['ContractorAddMailForm'],'<%=messages.getText("generic.alert.invaliddata")%>');" class="texts"> 
		           	 			<%=messages.getText("generic.messages.accept")%> </a></td>
		           	 	
        					</tr>
        				</table>
        			<jsp:include page="../component/utilitybox3bottom.jsp"/>
			</td>
		</tr>	
		
		</table>
	</html:form>
<%  }
 }
%>

</body>
</html:html>