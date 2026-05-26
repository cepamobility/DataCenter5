<%@page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.datatype.*"
	import="communication.datatype.tables.*"
	import="communication.administration.corporation.tables.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.queries.*"
	import="business.datatype.*"
	import="java.util.*"
	import="communication.administration.user.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld" 
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>
<%! RequestDispatcher dispatcher;
	Text messages; 
	SystemAccess sys;
	String type;
	String formEnding;
%>

<%
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.ADMINISTRATOR);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {        
		session.setAttribute("url","corporation/menuConfiguration.jsp");

		dispatcher=request.getRequestDispatcher("/util/jsp/message.jsp");
		dispatcher.include(request,response);
		messages=sys.getText();
		UserSessionData userSession=sys.getUserSessionData(); 

		String corpName = sys.getCorporationReportFacade().findCorporationName(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
		
		Collection menu = new LinkedList();
		menu=sys.getCorporationReportFacade().findAllMenuOption(userSession,false);
	    
%>

	<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
	<head/>

	<script language="javascript" src='./menu/js/driverFunctions.js' type='text/javascript' >
	</script>

	<script>


function changeSelection(formName,value,selection) {
		var elements=document.forms[formName].elements;
		for(i = 0; i < elements.length; i++) {
			var element=elements[i];
			if(element.id.indexOf(value) > -1) {
				if((selection == 1)) {
					element.checked = true;
				}else if((selection == 2)) {
					element.checked = false;
				}
			}
		}
}

	</script>
	
	<body bgcolor="#FFFFFF">
		<html:form action='<%="/MenuConfiguration"%>' >
			<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr class="guide1">
  					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("generic.corporation.configuration.menu")%>' />
			  	</tr>
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
		      	<tr>
		      		<td>
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.corporation.data")%>' />
						<table align='center' width='100%' cellpadding="0" cellspacing="0">
					  		<tr>
								<td class="linetwo">
								<b><%=messages.getText("generic.corporation.corporationdata.name")+" :"%></b>
		   						<%=corpName %>              
								</td>
							</tr>
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>	
			  	</tr>
<% 


	int [] items= {-1,-1,-1,-1,-1,-1,-1};
	int level=0;
	int parentId=0;
	int last=0;
	int leveladd=0;
	int countPrincipalItems=0;
	String urlOption="";
	Iterator it=menu.iterator();
	String checked="";
	Collection noSelectedItems=sys.getCorporationReportFacade().findNoSelectedOption(userSession,true);
	
	while(it.hasNext()) {
		MenuOptionData item = (MenuOptionData)it.next();
		parentId=item.getParentId().intValue();
		leveladd=Util.getlevel(items,parentId,last);
		last=last+leveladd;
		items[last]=item.getId().intValue();
		level=level+leveladd;
		urlOption=(String)item.getUrlOption();
		if(level==0){
				countPrincipalItems+=1;
				if (countPrincipalItems>1){	
					
					
%>							<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems-1)+"',1)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems-1)+"',2)"%>' class="input" />
								</td>
							</tr>							
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>


<% 				}
				
%>				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
			  	</tr>
				<tr>
					<td colspan="3" valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText((String)item.getLabelKey())%>' />
						<table width="90%" >	
<%		}else if(urlOption.equals("#")){
%>							<tr>
								<td width="100%" class="line<%=Util.MENU_LEVELS[level]%> lineborder"><%=messages.getText((String)item.getLabelKey())%></td>
							</tr>
<%		}else {
							checked = (noSelectedItems.contains(item.getId())) ? "" : "checked";
%>							<tr>
								<td width="100%" class='line<%=Util.MENU_LEVELS[level]%>'>
									<input type="checkbox" name='<%=item.getId().intValue()%>' id=<%="item"+countPrincipalItems+item.getId().intValue()%>' <%=checked%> /><%=messages.getText((String)item.getLabelKey())%>
<%				if (item.getCountry()!=null){ 
%>						(<%=messages.getText(item.getCountry())%>)
<%				}
				if (item.getLanguage()!=null){
%>						(<%=messages.getText("menu.items.language."+item.getLanguage())%>)	
<%				} 
%>
								</td>
							</tr>	
<%		}
	}%>			  			<tr>
								<td colspan="3" align="right">
									<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems)+"',1)"%>' class="input" />
									<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems)+"',2)"%>' class="input" />
								</td>
							</tr>							
						</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
					</td>
				</tr>
  				
  				<tr>
					<jsp:include  page="../component/spaceline.jsp" />
	  			</tr>
	  			
	  			<tr>
					<td colspan="5" width="100%">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.menuconfiguration.driver")%>' />
						<table width="100%"  cellpadding="0" cellspacing="0">	  			
<% 
	int [] items2= {-1,-1,-1,-1,-1,-1,-1};
	level=0;
	parentId=0;
	last=0;
	leveladd=0;
	int countPrincipalItems2=countPrincipalItems+1;
	urlOption="";
	checked="";
	menu=sys.getCorporationReportFacade().findAllMenuOption(userSession,true);
	it=menu.iterator();
	while(it.hasNext()) {
		MenuOptionData item = (MenuOptionData)it.next();
		parentId=item.getParentId().intValue();
		leveladd=Util.getlevel(items2,parentId,last);
		last=last+leveladd;
		items2[last]=item.getId().intValue();
		level=level+leveladd;
		urlOption=(String)item.getUrlOption();
		if(level==0){
				countPrincipalItems+=1;
				if (countPrincipalItems>countPrincipalItems2){	
%>									<tr class="lineone">
										<td colspan="3" align="right">
											<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems-1)+"',1)"%>' class="input" />
											<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems-1)+"',2)"%>' class="input" />
										</td>
									</tr>							
								</table>
								<jsp:include  page="../component/utilitybox2bottom.jsp" />
								</td>
							</tr>


<% 				}
				
%>							<tr>
								<jsp:include  page="../component/spaceline.jsp" />
			  				</tr>
							<tr class="lineone">
								<td colspan="3" valign="top">
									<jsp:include  page='<%="../component/utilitybox2top.jsp?title="+messages.getText((String)item.getLabelKey())%>' />
										<table width="90%" >	
<%		}else if(urlOption.equals("#")){
%>											<tr class="lineone">
												<td width="100%" class="line<%=Util.MENU_LEVELS[level]%> lineborder"><%=messages.getText((String)item.getLabelKey())%></td>
											</tr>
<%		}else {
							checked = (noSelectedItems.contains(item.getId())) ? "" : "checked";
%>											<tr class="lineone">
												<td width="100%" class='line<%=Util.MENU_LEVELS[level]%>'>
													<input type="checkbox" name='<%=item.getId().intValue()%>' id=<%="item"+countPrincipalItems+item.getId().intValue()%>' <%=checked%> /><%=messages.getText((String)item.getLabelKey())%>
<%				if (item.getCountry()!=null){ 
%>						(<%=messages.getText(item.getCountry())%>)
<%				}
				if (item.getLanguage()!=null){
%>						(<%=messages.getText("menu.items.language."+item.getLanguage())%>)	
<%				} 
%>
												</td>
											</tr>	
<%		}
	}%>			  							<tr class="lineone">
												<td colspan="3" align="right">
													<input type="button" value='<%=messages.getText("generic.messages.all.pl")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems)+"',1)"%>' class="input" />
													<input type="button" value='<%=messages.getText("generic.messages.none")%>' onclick='<%="changeSelection('ConfigurateCorporationMenuForm','item"+(countPrincipalItems)+"',2)"%>' class="input" />
												</td>
											</tr>							
										</table>
										<jsp:include  page="../component/utilitybox2bottom.jsp" />
									</td>
								</tr>
							</table>
							<jsp:include  page="../component/utilityboxbottom.jsp" />
						</td>
				</tr>
  				<tr>
  				  	<td height="18" colspan="3" valign="top">
          				<div align="right">
							<html:submit styleClass="input"><%=messages.getText("generic.messages.open")%></html:submit>
				  		</div>          
				  	</td>
			  	</tr>
		  	</table>
		</html:form>

<%@ include  file = '../util/jsp/googleanalytics.jsp' %>
		
	</body>
<%	}	%>
</html:html>
