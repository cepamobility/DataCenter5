<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="java.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="communication.driver.tables.*"
	import="communication.administration.user.UserSessionData"
	import="communication.util.ElementData"
   
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%!
	Text messages;
	RequestDispatcher dispatcher; 
	SystemAccess sys;	
	String context; 
	UserSessionData userSessionData;
%> 
<%
	sys=(SystemAccess)session.getAttribute("SystemAccess");
	userSessionData=sys.getUserSessionData();
	context=request.getContextPath();
	dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
	dispatcher.include(request,response);
	
	messages = sys.getText();

	Collection format = new LinkedList();
	format.add(new OptionItem("HTML","HTML"));	    
	format.add(new OptionItem("PDF","PDF"));	    
	format.add(new OptionItem("XLS","XLS"));	    
	session.setAttribute("format",format);

	Collection groupElements = sys.getUserSessionData().getFilter().getGroupLines();
	Hashtable[] elements = communication.administration.corporation.util.Util.getDivOrgMap(groupElements,sys.getUserSessionData().getFilter().getDivOrgCount());
	Collection items1 = new LinkedList();
    for(int i = 0; i < sys.getUserSessionData().getDivOrgCount(); i++) {
        Hashtable items = elements[i*3];
		items1.add(new OptionItem(messages.getText("generic.messages.select"),""));						            
		if(items != null) {			
	        Enumeration keys = items.keys();
		    while(keys.hasMoreElements()) {
        	    Integer countryId = (Integer)keys.nextElement();
            	Iterator it1 = ((Collection)items.get(countryId)).iterator();
        		while(it1.hasNext()) {
					ElementData element = (ElementData)it1.next();
					items1.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
				}
		    }
		}
	}
	Collections.sort((List)items1);
	session.setAttribute("divOrg11", items1);

%>



<%@page import="communication.administration.corporation.tables.CorporationData"%><html:html>
	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>
	<body bgcolor="#FFFFFF">
		<html:form action="/CENAPRAParticipantList">			
			<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("report.imesevi.participantlist")%>' />
  				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
	      		<tr>
    	    		<td width="100%" valign="top" colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("participant.list.data")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("participant.data.firstname")%> </th>
				         					<td align="right"><html:text property="firstName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" >
				        					<th align="left"> <%=messages.getText("participant.data.lastname")%> </th>
				         					<td align="right"><html:text property="lastName" size="25" styleClass="input"/></td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo">
					    					<th align="left"><%=messages.getText("imesevi.participant.state")%></th>
											<td align="right">
									      		<html:select property="divOrg11" styleClass="input" style="width:155;">
													<html:options collection="divOrg11" property="value" labelProperty="label"/>
												</html:select>   
											</td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" >
						         			<th align="left"><%=messages.getText("cenapra.activity")%></th>
						         			<td align="right">
						         				<html:select property="multiplicadores" styleClass="input">
<%			if(sys.getCorporationId() == CorporationData.CENAPRA) {
%>						         					<html:option value="0"><%=messages.getText("generic.messages.all.pl")%></html:option>
							         				<html:option value="-1"><%=messages.getText("imesevi.list.withoutactivity")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenapramultiplicadores"><%=messages.getText("datatype.scheduledactivitytype.cenapramultiplicadores")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenaprareplicadores"><%=messages.getText("datatype.scheduledactivitytype.cenaprareplicadores")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenaprapoblacionmeta"><%=messages.getText("datatype.scheduledactivitytype.cenaprapoblacionmeta")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenapramultiplicadoresnew"><%=messages.getText("datatype.scheduledactivitytype.cenapramultiplicadoresnew")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenapramultiplicadores2"><%=messages.getText("datatype.scheduledactivitytype.cenapramultiplicadores2")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenapraformadores"><%=messages.getText("datatype.scheduledactivitytype.cenapraformadores")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenaprapoblacionmeta2"><%=messages.getText("datatype.scheduledactivitytype.cenaprapoblacionmeta2")%></html:option>
<%			}else {
%>							         				<html:option value="datatype.scheduledactivitytype.cenapraformadores"><%=messages.getText("datatype.scheduledactivitytype.cenapraformadores")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenapramultiplicadores2"><%=messages.getText("datatype.scheduledactivitytype.cenapramultiplicadores2")%></html:option>
							         				<html:option value="datatype.scheduledactivitytype.cenaprapoblacionmeta2"><%=messages.getText("datatype.scheduledactivitytype.cenaprapoblacionmeta2")%></html:option>
<%			}
%>							         			</html:select>	
				         					</td>
										</tr>
				          				<tr>
						        			<td class="linetwo" colspan="2">&nbsp;</td>
						        		</tr>
										<tr class="linetwo" >
											<th align="left"><%=messages.getText("imesevi.report.showdata")%></th>
						         			<td align="right">
						         				<html:select property="all" styleClass="input">
			         								<html:option value="false"><%=messages.getText("report.imesevi.showprincipaldata")%></html:option>
			         								<html:option value="true"><%=messages.getText("report.imesevi.showalldata")%></html:option>
						         				</html:select>
						         			</td>
										</tr>
									</table>
									<jsp:include  page="../component/utilityboxbottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
	      		<tr>
					<jsp:include  page="../component/spaceline.jsp" />
      			</tr>
				<tr>
					<td align="left" class="texts" colspan="3">
						<jsp:include  page="../component/utilitybox4top.jsp" />
							<table width="100%" class="texts1" border="0" cellpadding="0" cellspacing="0">
								<tr class="lineone">
									<td align="left" class="texts">
										<html:select property="output" styleClass="input" >
											<html:options collection="format" property="value" labelProperty="label"/>
										</html:select>							          				
									</td>
									<td colspan="2" align="right" class="texts">
										<html:submit styleClass="input" ><%=messages.getText("generic.messages.consult")%></html:submit>
									</td>
								</tr>
							</table>
						<jsp:include  page="../component/utilitybox4bottom.jsp" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>
