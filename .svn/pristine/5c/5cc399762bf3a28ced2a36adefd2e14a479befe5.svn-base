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
	CorporationData corporation;int size;
	String divOrgId,level1,level2,label1,label2,level3;
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
		String level = (String)request.getParameter("level");
		divOrgId=(String)request.getParameter("id");
		level1 = null;
		if(Integer.parseInt(level) > 1) {
			if(!Util.empty((String)session.getAttribute("newLabel1"))) {
				label1 = (String)session.getAttribute("newLabel1");
				session.removeAttribute("newLabel1");
			}else {
				if(!Util.empty((String)session.getAttribute("level1"))) {
					level1 = (String)session.getAttribute("level1");
					label1 = null;
					session.removeAttribute("level1");
				}else {
					level1=(String)request.getParameter("level1");
					label1=(String)request.getParameter("label1");
				}
			}
			if(Integer.parseInt(level) > 2) {
				if(!Util.empty((String)session.getAttribute("newLabel2"))) {
					label2 = (String)session.getAttribute("newLabel2");
					level2 = null;
					session.removeAttribute("newLabel2");
				}else {
					if(!Util.empty((String)session.getAttribute("level2"))) {
						level2 = (String)session.getAttribute("level2");
						label2 = null;
						session.removeAttribute("level2");
					}else {
						level2=(String)request.getParameter("level2");
						label2=(String)request.getParameter("label2");
					}
				}
			}else{
				level2=null;
				label2=null;
			}
		}
		level3 = (String)request.getParameter("level3");
		String link = "layout.jsp?refer=/corporation/AddItem.jsp?id="+divOrgId+"&level="+String.valueOf(Integer.parseInt(level)+1);
		DivOrgData divOrgData = corporation.getDivOrgData(Integer.parseInt(divOrgId));
		if(divOrgData != null) {
			CorporationAddItemForm actionForm = new CorporationAddItemForm(divOrgId,level, level1,label1,level2,label2,level3);
			session.setAttribute("CorporationAddItemForm",actionForm);
			session.setAttribute("CorporationDeleteItemForm",actionForm);
			session.setAttribute("CorporationUpdateItemForm",actionForm);
			session.setAttribute("CorporationRenameItemForm",actionForm);
			boolean linetwo = true;
			int length = divOrgData.getLabels().length;
			String label = (Integer.parseInt(level) < length) ? divOrgData.getLabels()[Integer.parseInt(level)-1].getName() : "";
			String labelB = (Integer.parseInt(level) > 1) ? divOrgData.getLabels()[Integer.parseInt(level)-2].getName() : "";
			String editLabel = messages.getText(divOrgData.getName());
			String sublevelLabel = null;
			String subSublevelLabel = null;
			String linkNew = "layout.jsp?refer=/corporation/NewItem.jsp?level="+level+"&id="+String.valueOf(divOrgData.getId());
			int function = 1;
			if(Integer.parseInt(level) > 1) {
				LabelItemData divOrgLabel1 = divOrgData.getDivOrgLevels();
				String levelBefore1 = label1;		
				ItemData item1 = null;
				ItemData item2 = null;
				if(divOrgLabel1 != null) {
					if(!Util.empty(level1)) {
						item1 = divOrgLabel1.getItem(new Integer(level1));
						editLabel = item1.getName();
						linkNew += "&level1="+level1; 
					}else{
						function = 2;
						editLabel = label1;
						linkNew += "&label1="+label1; 
					}
				}else {
					function = 2;
					editLabel = label1;
					linkNew += "&label1="+label1; 
				}
				if(Integer.parseInt(level) > 2) {
					sublevelLabel = editLabel;	
					if(item1 != null && !Util.empty(level2)) {
						LabelItemData divOrgLabel2 = item1.getSubItems();
						item2 = divOrgLabel2.getItem(new Integer(level2));
						editLabel = item2.getName();
					}else{
						function = 2;
						editLabel = label2;
					}
					if(Integer.parseInt(level) > 3) {
						subSublevelLabel = editLabel;	
						if(item1 != null && !Util.empty(level2)) {
							ItemData item3 = item2.getSubItems().getItem(new Integer(level3));
							editLabel = item3.getName();
						}
					}
				}
			}
			size = 40;
			if(sublevelLabel != null) {
				size += 20;
			}
			if(subSublevelLabel != null) {
				size += 20;
			}
%>	
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<head/>
<script>

function warning(form) {
	if(form.elements['key'].value != -1){
		return confirm('<%=messages.getText("corporation.divorg.additem.warning")+"."+messages.getText("generic.messages.wishtocontinue")%>');
	}
	return true;
}

function addItem(formAdd,formText,level){
	var name = 'label'+level+'';
	formAdd.elements[name].value = formText.elements['newItem'].value;
	formAdd.submit();
}

function setLevel(form,level,key){
	form.elements['level'].value = level;
	form.elements['key'].value = key;
}

function deleteItem(form,formCheck,id,msg){
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
	form.elements['label3'].value=valueCheckboxParamList;
	form.elements['id'].value=id;
	if(valueCheckboxParamList.length>0){
		if(confirm(msg)){
			form.submit();
		}
	}
}

</script>

<html:form action="/Corporationadditem">
	<html:hidden property="id" />   
	<html:hidden property="level1" />   
	<html:hidden property="label1" />   
	<html:hidden property="level2" />   
	<html:hidden property="label2" />   
	<html:hidden property="label3" />   
</html:form>
<html:form action="/Corporationdeleteitem">
	<html:hidden property="level"/>
	<html:hidden property="id"/>
	<html:hidden property="level1" />   
	<html:hidden property="level2" />   
	<html:hidden property="label3"/>
</html:form>

<table width='600' align='center' cellpadding="0" cellspacing="0">	
		<html:form action="/Corporationrenameitem">
			<html:hidden property="level"/>
			<html:hidden property="id"/>
			<html:hidden property="level1" />   
			<html:hidden property="level2" />   
			<html:hidden property="level3"/>
			<tr class="title2">
			  <td height="40" colspan="3" valign="top">
			    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                	<tr class="title2">
                   		<td width="9"><img src="/datacenter/util/imgs/titulo_left.jpg" width="9" height="40" /></td>
                   		<td class="title2" align="left">
											<font size="2"><%=(function == 1) ? messages.getText("corporation.divorg.editing") : messages.getText("corporation.divorg.adding")%>  <%=messages.getText(labelB)%>:</font> <font size="3"><%=editLabel%></font>					  </td>
                   		<td width="9" align="right"><img src="/datacenter/util/imgs/titulo_right.jpg" width="9" height="40" /></td>
                 	</tr>
              </table>			  </td>
		  </tr>
			<tr>
			  <td colspan="3" height="3px"><img src="x" height="1px" width="1px"></td>
		  </tr>
			<tr>
			  <td colspan="3"><table width="600" border="0" cellpadding="0" cellspacing="0">
                <tr class="guideSearch">
                  <td width="19" align="left" valign="top" ><img src="/datacenter/util/imgs/left_top_especial.jpg" width="19" height="15" align="bottom" /></td>
                  <td width="562" align="left" class="guideSearch"><img src="x" height="1px" width="1px"></td>
                  <td width="20" align="right" valign="top" ><img src="/datacenter/util/imgs/right_top_especial.jpg" width="19" height="15" align="bottom" /></td>
                </tr>
                <tr>
                  <td width="19" align="left" background="/datacenter/util/imgs/left_line_search.jpg" class="leftLineSearch"></td>
                  <td width="562" class="linethree"><table border="0" cellpadding="0" cellspacing="0" class="linethree">
                    <%		if(sublevelLabel != null) {
%>
                    <tr align='center' >
                      <td align="left"><%=messages.getText(divOrgData.getLabels()[0].getName())%>:<%=sublevelLabel%> </td>
                    </tr>
                    <%		}
		if(subSublevelLabel != null) {
%>
                    <tr align='center' >
                      <td align="left"><%=messages.getText(divOrgData.getLabels()[1].getName())%>:<%=subSublevelLabel%> </td>
                    </tr>
                    <%		}		
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
      		<tr>
				<jsp:include  page="../component/spaceline.jsp" />  			</tr>
<%		if(function == 2) {
%>			<tr align='center'  class="linetwo">
				<td colspan="3" align="left"><%=messages.getText("corporation.divorg.additem.warning")%></td>
			</tr>
<%		}else {
			if(Integer.parseInt(level) > 1) {
%>			<tr align='center'  class="linetwo">
				<td colspan="3" align="left">
					<html:text property="label3" styleClass="input" />
					<html:submit value='<%=messages.getText("general.messages.rename")%>' styleClass="input" />				</td>
			</tr>
<%			}
		}
%>		  	<tr>
				<jsp:include  page="../component/spaceline.jsp" />
  			</tr>
		</html:form>
  		
  		<tr>
			<jsp:include  page="../component/spaceline.jsp" />  		
		</tr>
<%		if(Integer.parseInt(level) <= length){
			LinkedList[] items = divOrgData.getItems(Integer.parseInt(level),level1,level2);
%>		<tr align='center' >
			<td valign='top' width="412">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.user.divorg.currentitems")%>' />
				<form name="active">
				<table align="center" width="100%">	
					
<%			LinkedList active = items[0];
			for(int i = 0; i < active.size(); i++) {
				ItemData item = (ItemData)active.get(i);
%>					<tr align='center' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
						<td width='5%' align='center'>
							<input type='checkbox' name='<%=item.getId()%>' value='<%=item.getId()%>' >
						</td>
						<td width='90%' >
<%				if(!Util.empty(level1) && !Util.empty(level2)){
%>						    <a href='<%=link+"&level1="+level1+"&level2="+level2+"&level3="+String.valueOf(item.getId())%>' >
								<%=item.getName()%>
							</a>
<%				}else {
					if(Util.empty(level1)) {
%>						    <a href='<%=link+"&level1="+String.valueOf(item.getId())%>' >
			               	 	 <%=item.getName()%>
							</a>
<%					}else {
%>							<a href='<%=link+"&level1="+level1+"&level2="+String.valueOf(item.getId())%>' >
								<%=item.getName()%>
							</a>
<%					}
				}			
%>						</td>
					</tr>										
<%				linetwo = !linetwo;
			}	
			if(active.size() == 0) {					
%>					<tr align='left'  width='90%' class="linetwo">
						<td><%=messages.getText("generic.user.divorg.noactiveitems")%></td>
					</tr>
<%			}   %>  

				</table>
			</form>
				<jsp:include  page="../component/utilityboxbottom.jsp" />			</td>
			<td width="16">&nbsp;</td>
			<td valign='top' align='center' width='172'>
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.actions")%>' />
					<form name="new">
					<table width="100%">
						
<%			if(Integer.parseInt(level) == length) {
%>							<tr>
								<td class="linetwo">
									<%=messages.getText(label)%>
									<input type="text" name="newItem" class="input"/>
									<input type="button" name="corporationName" onClick="javascript:addItem(document.forms[2],document.forms['new'],'<%=level%>');" value='<%=messages.getText("generic.messages.add")%>' size="50" class="input"/>								</td>
							</tr>			
<%			}else {
%>							<tr>
								<td width="35%" class="linetwo">
									<a href='<%=linkNew%>'><%=messages.getText("generic.messages.add")%></a>								</td>
							</tr>
<%			}
%>							<tr>
								<td width="35%" class="linetwo">
									<%
										String href1 = "javascript:deleteItem(document.forms[8],document.forms[\"active\"],"+ divOrgData.getId()+",\""+ messages.getText("generic.messages.inactivate.confirm") + "\");";
									%>
									<a href='<%=href1 %>'>
										<%=messages.getText("generic.messages.inactivate")%>									</a>								</td>
							</tr>
							<tr>
								<td width="35%" class="linetwo">
									<%
										String href2 = "javascript:deleteItem(document.forms[3],document.forms[\"active\"],"+divOrgData.getId()+",\""+messages.getText("generic.messages.confirmdeleteitem")+"\");";
									%>
									<a href='<%=href2 %>'>
										<%=messages.getText("generic.messages.delete")%>
									</a>
								</td>
							</tr>
					</table>
					</form>				
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />		</tr>
		<tr>
			<td width="412" valign="top">
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.user.divorg.inactiveitems")%>' />
				<form name='inactive'>
					<table width='100%' align='center' cellpadding="0" cellspacing="0">
						
<%					LinkedList inactive = items[1];
					for(int i = 0; i < inactive.size(); i++) {
						ItemData item = (ItemData)inactive.get(i);
%>							<tr align='center' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
								<td width='5%' align='center''>
									<input type='checkbox' name='<%=item.getId()%>' value='<%=item.getId()%>' >								</td>
								<td>		
<%						if(!Util.empty(level1) && !Util.empty(level2)){
%>									<a href='<%=link+"&level1="+level1+"&level2="+level2+"&level3="+String.valueOf(item.getId())%>' >
										<%=item.getName()%>
									</a>
<%						}else {
							if(Util.empty(level1)) {
%>									<a href='<%=link+"&level1="+String.valueOf(item.getId())%>' >
										<%=item.getName()%>
									</a>
<%							}else {
%>									<a href='<%=link+"&level1="+level1+"&level2="+String.valueOf(item.getId())%>' >
										<%=item.getName()%>
									</a>
<%							}
						}			
%>								</td>
							</tr>										
<%						linetwo = !linetwo;
					}
					if(inactive.size() == 0) {
%>							<tr align='left' class="linetwo">
								<td width='5%' align='center''>
									<%=messages.getText("generic.user.divorg.noinactiveitems")%>								</td>
							</tr>
<%					}	
%>              		
					</table>
				</form>
				<jsp:include  page="../component/utilityboxbottom.jsp" />			</td>
			<td width='16'>&nbsp;</td>
			<td valign='top' align='center' width='172'>
				<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("generic.messages.actions")%>' />
					<table width="100%">
						<tr>
							<td width="35%" class="linetwo">
								<%
									String href3 = "javascript:deleteItem(document.forms[8],document.forms[\"inactive\"],"+divOrgData.getId()+",\""+messages.getText("generic.messages.activate.confirm")+"\");";
								%>
								<a href='<%=href3 %>'>
									<%=messages.getText("generic.messages.activate")%>								</a>							</td>
						</tr>
						<tr>
							<td width="35%" class="linetwo">
								<%
									String href4 = "javascript:deleteItem(document.forms[3],document.forms[\"inactive\"],"+divOrgData.getId()+",\""+messages.getText("generic.messages.confirmdeleteitem")+"\");";
								%>
								<a href='<%=href4 %>'>
									<%=messages.getText("generic.messages.delete")%>
								</a>
							</td>
						</tr>
					</table>		
				<jsp:include  page="../component/utilityboxbottom.jsp" />
			</td>
		</tr>
		<tr>
			<jsp:include  page="../component/spaceline.jsp" />		
		</tr>
<%		}else{
%>		
		<tr><td>
			<form name='active'></form>
			<form name='new'></form>
			<form name='inactive'></form>
		</td></tr>
<%
		}				
%>
			
		<tr>
		
		<html:form action="/Corporationupdateitem" onsubmit="return warning(document.forms[8])">
			<html:hidden property="level"/>
			<html:hidden property="id"/>
			<html:hidden property="level1" />   
			<html:hidden property="label1" />   
			<html:hidden property="level2" />   
			<html:hidden property="label2" />   
			<html:hidden property="label3"/>
			<input type="hidden" name="key" value=-1/>
		
				<td colspan="3" class="linetwo"  align="right">
<%					if(function == 1) {
						if(Integer.parseInt(level) > 1) {
%>
		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[8],2,-1)">
						<%=messages.getText("generic.messages.edit")%> <%=messages.getText(divOrgData.getName())%>					</html:cancel>

<%						}
						if(Integer.parseInt(level) > 2) {
%>
		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[8],3,-1)">
						<%=messages.getText("generic.messages.edit")%> <%=sublevelLabel%>					</html:cancel>

<%						}
						if(Integer.parseInt(level) > 3) {
%>
		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[8],4,-1)">
						<%=messages.getText("generic.messages.edit")%> <%=subSublevelLabel%>					</html:cancel>

<%						}
%>
		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[8],1,-1)">
						<%=messages.getText("generic.messages.finalize")%>					</html:cancel>
<%
					}else {
						if(Integer.parseInt(level) == 2) {
%>
		            <html:cancel styleClass="input" onclick='javascript:setLevel(document.forms[8],2,-1)'>
						<%=messages.getText("generic.messages.cancel")%>					</html:cancel>
<%						}else{
%>
		            <html:cancel styleClass="input" onclick='javascript:setLevel(document.forms[8],3,-1)'>
						<%=messages.getText("generic.messages.cancel")%>					</html:cancel>
<%						}
%>	
		            <html:cancel styleClass="input" onclick="javascript:setLevel(document.forms[8],1,1)">
						<%=messages.getText("generic.messages.finalize")%>					</html:cancel>
<%					
					}
%>				</td>
	
			</html:form>
		</tr>
	</table>
	
<%											
		}
	}
%>

</html:html>