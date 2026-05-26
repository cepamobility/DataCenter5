<%@page language="java" 
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.administration.corporation.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	import="org.apache.struts.action.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	CorporationData corporation; int size;
	String divOrgId,level1,level2,label1,label2;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.MODIFY_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		pathLanguage="/util/jsp/language.jsp";
	
		session.setAttribute("url","corporation/edit.jsp");
		session.removeAttribute("target");

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
	
		corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
		String level = (String)request.getParameter("level");
		if(session.getAttribute("level1") != null) {
			level1 = (String)session.getAttribute("level1");
			label1 = null;
			session.removeAttribute("level1");
		}else {
			level1=(String)request.getParameter("level1");
			label1=(String)request.getParameter("label1");
		}
		if(session.getAttribute("level2") != null) {
			level2 = (String)session.getAttribute("level2");
			label2 = null;
			session.removeAttribute("level2");
		}else {
			level2=(String)request.getParameter("level2");
			label2=(String)request.getParameter("label2");
		}
		String level3 = (String)request.getParameter("level3");
		if(corporation != null) {
			messages=sys.getText();

			CorporationCountryForm actionForm = new CorporationCountryForm(level,level1,label1,level2,label2,level3);
			session.setAttribute("CorporationAddCountryForm",actionForm);
			session.setAttribute("CorporationDeleteCountryForm",actionForm);
			session.setAttribute("CorporationUpdateCountryForm",actionForm);
			session.setAttribute("CorporationRenameCountryForm",actionForm);
			Hashtable activeCountries = new Hashtable();
			Collection items = new LinkedList();
			String link = "layout.jsp?refer=/corporation/AddCountry.jsp?level="+String.valueOf(Integer.parseInt(level)+1);
			String linkNew = "layout.jsp?refer=/corporation/NewCountry.jsp?level="+level;
			boolean linetwo = true;
			int length = corporation.getGeoLabels().length;
			String label = corporation.getGeoLabels()[Integer.parseInt(level)-1].getResourceKey();
			String labelB = (Integer.parseInt(level) > 1) ? corporation.getGeoLabels()[Integer.parseInt(level)-1].getResourceKey():"";
			String levelBefore1 = messages.getText(corporation.getGeoLabels()[0].getResourceKey());		
			String levelBefore2 = null;
			int function = 1;		
			if(Integer.parseInt(level) > 1) {
				GeoLabelData geoLabel1 = corporation.getDivGeoDataSet();	
				GeoItemData item1 = null;
				if(!Util.empty(level1)) {
					if(geoLabel1 != null) {
						item1 = geoLabel1.getItem(new Integer(level1));
						levelBefore1 = item1.getName();
						linkNew += "&level1="+level1; 
					}	
				}else{	
					function = 2;
					levelBefore1 = label1;
					linkNew += "&label1="+label1; 
				}
				if(Integer.parseInt(level) > 2) {
					levelBefore2 = levelBefore1;		
					if(item1 != null && !Util.empty(level2)) {
						GeoLabelData geoLabel2 = item1.getSubItems();
						GeoItemData item2 = geoLabel2.getItem(new Integer(level2));
						levelBefore1 = item2.getName();
					}else{
						function = 2;
						levelBefore1 = label2;
					}
				}
			}
			if(Integer.parseInt(level) == 1 || (!Util.empty(level1) || !Util.empty(level2))) {
				if(function == 1) {
					items = corporation.getItems(level1,level2);
				}
			}
			size = 40;
			if(levelBefore2 != null) {
			    size += 22;
			}
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head/>

<script>
function warning(form) {
	if(form.elements['key'].value != -1){
		return confirm('<%=messages.getText("corporation.divgeo.additem.warning")+"."+messages.getText("generic.messages.wishtocontinue")%>');
	}
	return true;
}

function setLevel(form,level,key){
	form.elements['level'].value = level;
	form.elements['key'].value = key;
}

function addItem(formAdd,formText,level){
	var name = 'label'+level+'';
	formAdd.elements[name].value = formText.elements['newItem'].value;
	formAdd.submit();
}

function deleteItem(form,formCheck,label,msg){
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
				}
				else{
					valueCheckboxParamList=element.value+"<"+valueCheckboxParamList;
				}	
			}	
		} 
		
	}
	form.elements[label].value=valueCheckboxParamList;
	if(valueCheckboxParamList.length>0){
		if(confirm(msg)){
			form.submit();
		}
	}
}

</script>
	<table width='600' align='center' cellpadding="0" cellspacing="0">
	<html:form action="/Corporationaddcountry">
		<html:hidden property="level" />   
		<html:hidden property="level1" />   
		<html:hidden property="label1" />   
		<html:hidden property="level2" />   
		<html:hidden property="label2" />   
		<html:hidden property="label3" />   
	</html:form>
	<html:form action="Corporationdeletecountry">
		<html:hidden property="level"/>
		<html:hidden property="level1" />   
		<html:hidden property="label1"/>
		<html:hidden property="level2" />   
		<html:hidden property="label2"/>
		<html:hidden property="label3"/>
	</html:form>
		<html:form action="/Corporationrenamecountry">
			<html:hidden property="level"/>
			<html:hidden property="level1" />   
			<html:hidden property="level2" />   
			<html:hidden property="level3"/>
			<tr class="title2">
				<td colspan="3"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr class="title2">
                    <td width="9"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40" /></td>
                    <td class="title2" align="left">
					 <%=(function == 1) ? messages.getText("corporation.divgeo.editing") : messages.getText("corporation.divgeo.adding")%> <%=messages.getText(labelB)%>:</font> <font size="3"><%=levelBefore1%> </font> 
					
					</td>
                    <td width="9" align="right"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="40" /></td>
                  </tr>
                </table></td>
			</tr>
	   		<tr>
				<jsp:include  page="../component/spaceline.jsp" />			</tr>
<%		if(function == 2) {
%>
			<tr align='center' width='100%'>
			  <td colspan="3" align="left" height="3"> <IMG src="X" height="1" width="1"></td>
		  </tr>
			<tr align='center' width='100%'>
			  <td colspan="3" align="left"><table width="600" border="0" cellpadding="0" cellspacing="0">
                <tr class="guideSearch">
                  <td width="19" align="left" valign="top" ><img src="/datacenter/util/imgs/left_top_especial.jpg" width="19" height="15" align="bottom" /></td>
                  <td width="562" align="left" class="guideSearch"><img src="x" height="1px" width="1px"></td>
                  <td width="20" align="right" valign="top" ><img src="/datacenter/util/imgs/right_top_especial.jpg" width="19" height="15" align="bottom" /></td>
                </tr>
                <tr>
                  <td width="19" align="left" background="/datacenter/util/imgs/left_line_search.jpg" class="leftLineSearch"></td>
                  <td width="562" class="linethree">
				  <table border="0" cellpadding="0" cellspacing="0">

                      <%			if(levelBefore2 != null) {
%>
                      <tr align='center'>
                        <td align="left"><%=messages.getText(corporation.getGeoLabels()[0].getResourceKey())%>:<%=levelBefore2%> </td>
                      </tr>
                      <% 			}
%>
                  </table></td>
                  <td width="19" align="right" background="/datacenter/util/imgs/right_line_search.jpg" class="rightLineSearch"></td>
                </tr>
                <tr>
                  <td width="19" height="11" align="left" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11" /></td>
                  <td width="562" class="centerBaseSearch"></td>
                  <td width="19" height="11" align="right" valign="top"><div align="right"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11" /></div></td>
                </tr>
              </table></td>
		  </tr>
			<tr align='center' width='100%' class="linetwo">
				<td colspan="3" align="left"><%=messages.getText("corporation.divgeo.additem.warning")%></td>
			</tr>
<%		}else {
			if(Integer.parseInt(level) > 1) {
%>			<tr align='center' width='100%' class="linetwo">

				<td align="left">
					<html:text property="label3" styleClass="input" />
					<html:submit value='<%=messages.getText("general.messages.rename")%>' styleClass="input" />				</td>
				<td width="6">&nbsp;</td>
			</tr>
<%			}
		}
%>		</html:form>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />		</tr>
		<tr align='center' >
			<td valign='top' align='center' width='<%=(length == Integer.parseInt(level)) ? "50%" : "70%"%>' height='100%' rowspan="3">
				<form name='active'>
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText(label)%>' />
						<table align="center" width="100%">	
<%
	    Iterator it = items.iterator();
		boolean elements = false;
		while(it.hasNext()) {
			GeoItemData item = (GeoItemData)it.next();
			activeCountries.put(item.getId(),item);
			if(item.isActive()) {
%>							<tr align='center' width='90%' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
								<td width='5%' align='center'>
									<input type='checkbox' name='<%=item.getId()%>' value='<%=item.getId()%>' >								
								</td>
								<td width='90%' >
<%				if((Integer.parseInt(level) == length) || (!Util.empty(level1) && !Util.empty(level2))){
%>					<%=(length == Integer.parseInt(level)) ? messages.getText(item.getName()) : item.getName()%>													 	
<%				}else {
					if(Util.empty(level1)) {
%>								    <a href='<%=link+"&level1="+String.valueOf(item.getId())%>' >
										<%=(length == Integer.parseInt(level)) ? messages.getText(item.getName()) : item.getName()%>													 	
									</a>
<%					}else {
%>								    <a href='<%=link+"&level1="+level1+"&level2="+String.valueOf(item.getId())%>' >
										<%=(length == Integer.parseInt(level)) ? messages.getText(item.getName()) : item.getName()%>													 	
									</a>
<%					}
				}			
%>								</td>
							</tr>										
<%				linetwo = !linetwo;
				elements = true;
			}
		}	
		if(!elements) {					
%>							<tr align='left' width='90%' class="linetwo">
								<td><%=messages.getText("corporation.divgeo.not_any_country")%></td>
							</tr>
<%		}else {     
%>             		
							<tr class="linetwo">
								<td align="right">
									<input name="button" type="button" onClick="javascript:deleteItem(document.forms[3],document.forms['active'],'<%="label"+level%>','<%=messages.getText("generic.messages.confirmdeleteitem")%>');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>								</td>
							</tr>
<%		}
%>
						</table>
					<jsp:include  page="../component/utilityboxbottom.jsp" />
				</form>			</td>
			<td width='16'>&nbsp;</td>
			<td valign='top' align='center' width='50%'>
<%		if(length == Integer.parseInt(level)) {
			Collection allCountries=sys.getCorporationReportFacade().findAllCountries(sys.getUserSessionData());
%>				<form name='addNew'>
					<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("corporation.divgeo.not_added_countries")%>' />
						<table align="center" width="100%">	
<%			Collections.sort((List)allCountries);
			Iterator allIt = allCountries.iterator();
			while(allIt.hasNext()) {
				CountryData item = (CountryData)allIt.next();
				if(!activeCountries.containsKey(item.getId())) {
%>							<tr align='center' width='90%' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
								<td width='5%' align='center''>
									<input type='checkbox' name='<%=item.getId()%>' value='<%=item.getId()%>' >								</td>
								<td width='90%' ><%=messages.getText(item.getResourceKey())%></td>
							</tr>										
<%					linetwo = !linetwo;
				}
			}
			if(allCountries.size() == 0) {
%>							<tr align='left' width='90%' class="linetwo">
								<td width='5%' align='center''>
									<%=messages.getText("corporation.divgeo.any_country_to_add")%>								</td>
							</tr>
<%			}else {	
%>          				<tr class="linetwo">
								<td colspan='2' align="right">
									<input name="button" type="button" onClick="javascript:deleteItem(document.forms[2],document.forms['addNew'],'<%="label"+level%>','<%=messages.getText("corporation.country.add.confirm")%>');" value='<%=messages.getText("generic.messages.add")%>' class="input"/>								</td>
							</tr>
<%			}		
%>						</table>
					<jsp:include  page="../component/utilityboxbottom.jsp"/>
				</form>	
<%		}else {
%>				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.actions")%>' />
					<table width="100%">
						<tr>
							<td width="35%" class="linetwo">
								<a href='<%=linkNew%>'>
									<%=messages.getText("generic.messages.add")%>								</a>							</td>
	    		    	</tr>
						<tr>
							<td width="35%" class="linetwo">
								<%
									String href1 = "javascript:deleteItem(document.forms[3],document.forms['active'],'" + "label"+level+"','"+messages.getText("generic.messages.confirmdeleteitem")+"');";	
								%>
								<a href='<%=href1 %>' >
									<%=messages.getText("generic.messages.delete")%></td>
								</a>
							</td>
					    </tr>
					</table>		
				<jsp:include  page="../component/utilityboxbottom.jsp"/>
<%		}
%>			</td>	
		</tr>
		<tr>
			<html:form action="/Corporationupdatecountry" onsubmit="return warning(document.forms[7])">
				<html:hidden property="level"/>
				<html:hidden property="level1" />   
				<html:hidden property="level2" />   
				<html:hidden property="label3"/>
				<input type="hidden" name="key" value=-1/>	
				<td colspan="3" class="linetwo"  align="right">
<%		if(function == 1) {
			if(Integer.parseInt(level) > 1) {
%>			        <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[7],2,-1)">
						<%=messages.getText("generic.messages.edit")%> <%=messages.getText(corporation.getGeoLabels()[0].getResourceKey())%>
					</html:cancel>
<%			}
			if(Integer.parseInt(level) > 2) {
%>		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[7],3,-1)">
						<%=messages.getText("generic.messages.edit")%> <%=levelBefore2%>
					</html:cancel>
<%			}
%>	            	<html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[7],1,-1)">
						<%=messages.getText("generic.messages.finalize")%>
					</html:cancel>
<%		}else {
			if(Integer.parseInt(level) == 2) {
%>		            <html:cancel styleClass="input" onclick='javascript:setLevel(document.forms[7],2,-1)'>
						<%=messages.getText("generic.messages.cancel")%>
					</html:cancel>
<%			}else{
%>		            <html:cancel styleClass="input" onclick='javascript:setLevel(document.forms[7],3,-1)'>
						<%=messages.getText("generic.messages.cancel")%>
					</html:cancel>
<%			}
%>		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[7],1,1)">
						<%=messages.getText("generic.messages.finalize")%>
					</html:cancel>
<%		}
%>				</td>
			</html:form>
		</tr>
	</table>
<%															
		}
	}
%>

</html:html>