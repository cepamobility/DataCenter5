<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.*"
	import="presentation.translator.web.util.*"
	import="communication.administration.corporation.tables.*"
 	
 	
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<html:html>

<%! RequestDispatcher dispatcher; String context; String pathMsg;
	Text messages; String pathLanguage;
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = Util.checkPermission(sys,SecurityConstants.CREATE_USER);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages = sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	
		session.setAttribute("url","user/create.jsp");

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		
		CorporationData corporation = null;		
		int divOrgId = 0;
		Collection contractorsList = new LinkedList();
		DivOrgData divOrgData;
		String contractorName = "";
		String contractorId = "";
		
		// Contractors FORESTAL ORIENTAL:			
		if (sys.getUserSessionData().getFilter().getCorporationId().intValue() == 
	    		communication.administration.corporation.tables.CorporationData.FORESTALORIENTAL){
			
			
			divOrgId = 10; // FOSA divOrg			
			
			corporation = sys.getCorporationReportFacade().
				findCorporationByPrimaryKey(sys.getUserSessionData().
				getFilter().getCorporationId(),sys.getUserSessionData());
			divOrgData = corporation.getDivOrgData(divOrgId);
			
			if(divOrgData != null) {				
				int length = divOrgData.getLabels().length;
				String label = divOrgData.getLabels()[0].getName();
				LinkedList[] items = divOrgData.getItems(1,null,null); // Traigo Divorg1
				LinkedList actives = items[0]; // Active items, linkedlist de itemdata: id,name,active
				Iterator itContractor = actives.iterator();
				while (itContractor.hasNext()){
					ItemData itemContractor = (ItemData)itContractor.next();
					// Genero una lista de OptionItem(nombreContratista, idContratista);
					if (contractorsList.isEmpty()){
						contractorName = messages.getText(itemContractor.getName());
						contractorId = itemContractor.getId().toString();
					}
					contractorsList.add(new OptionItem(messages.getText(itemContractor.getName()),itemContractor.getId().toString()));					
				}				
			}
		}
		// Contractors MDP:			
		if (sys.getUserSessionData().getFilter().getCorporationId().intValue() == 
	    		communication.administration.corporation.tables.CorporationData.MONTES_DE_PLATA){
			
			
			divOrgId = 456435; // MDP divOrg			
			
			corporation = sys.getCorporationReportFacade().
				findCorporationByPrimaryKey(sys.getUserSessionData().
				getFilter().getCorporationId(),sys.getUserSessionData());
			divOrgData = corporation.getDivOrgData(divOrgId);
			
			if(divOrgData != null) {				
				int length = divOrgData.getLabels().length;
				String label = divOrgData.getLabels()[0].getName();
				LinkedList[] items = divOrgData.getItems(1,null,null); // Traigo Divorg1
				LinkedList actives = items[0]; // Active items, linkedlist de itemdata: id,name,active
				Iterator itContractor = actives.iterator();
				while (itContractor.hasNext()){
					ItemData itemContractor = (ItemData)itContractor.next();
					// Genero una lista de OptionItem(nombreContratista, idContratista);
					if (contractorsList.isEmpty()){
						contractorName = messages.getText(itemContractor.getName());
						contractorId = itemContractor.getId().toString();
					}
					contractorsList.add(new OptionItem(messages.getText(itemContractor.getName()),itemContractor.getId().toString()));					
				}				
			}
		}

		// Contractors LOS PIQUES:		
		if (sys.getUserSessionData().getFilter().getCorporationId().intValue() == 
    		communication.administration.corporation.tables.CorporationData.LOSPIQUES){
		
		
			divOrgId = 205; // Los Piques divOrg3 transportista			
			
			corporation = sys.getCorporationReportFacade().
				findCorporationByPrimaryKey(sys.getUserSessionData().
				getFilter().getCorporationId(),sys.getUserSessionData());
			divOrgData = corporation.getDivOrgData(divOrgId);
			
			if(divOrgData != null) {
				System.out.println("Los Piques DivOrgData != null");
				int length = divOrgData.getLabels().length;
				String label = divOrgData.getLabels()[0].getName();
				LinkedList[] items = divOrgData.getItems(1,null,null); // Traigo Divorg3
				LinkedList actives = items[0]; // Active items, linkedlist de itemdata: id,name,active
				if (actives != null){
					System.out.println("Los Piques Actives != null");
					Iterator itContractor = actives.iterator();
					while (itContractor.hasNext()){
						ItemData itemContractor = (ItemData)itContractor.next();
						// Genero una lista de OptionItem(nombreContratista, idContratista);
						if (contractorsList.isEmpty()){
							contractorName = messages.getText(itemContractor.getName());
							contractorId = itemContractor.getId().toString();
						}
						contractorsList.add(new OptionItem(messages.getText(itemContractor.getName()),itemContractor.getId().toString()));					
					}
				}else{
					System.out.println("ERROR: Los Piques Actives == null");
				}
			}else{
				System.out.println("Error: Los Piques DivOrgData == null");
			}
		}
		
		
%>

<jsp:include page='<%="/language/findalllanguage.jsp" %>'/>
<%	
		if(session.getAttribute("target")==null){
			session.setAttribute("target",request.getParameter("target"));
		}
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>

	<script language="javascript">
		function createWithPassword() {
			document.forms['ContractorUserCreateForm'].elements['type'].value = 'password';
			document.forms['ContractorUserCreateForm'].submit();
		}	
		function changeContractor(){
			form = document.forms['ContractorUserCreateForm'];
			form.elements['contractorId'].value = form.elements['contractor'].value;
			var label = form.elements['contractor'].options[form.elements['contractor'].options.selectedIndex].text;
			form.elements['contractorName'].value = label;
		}
	</script>

	<body bgcolor="#FFFFFF">
		<html:form action="/ContractorUserCreate" focus="loginname"  >
			<html:hidden property="type" value="normal" />
			<table width="450" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr class="guide1">
					<jsp:include  page='<%="../component/titlecomponent.jsp?title="+messages.getText("web.user.usernedlist.newuser")%>' />
		  		</tr>
		      	<tr>
					<jsp:include  page="../component/spaceline.jsp" />
		  		</tr>
			  	<tr>
		    		<td valign="top">
						<jsp:include  page='<%="../component/utilityboxtop.jsp?title="+messages.getText("web.user.userview.systemuserdata")%>' />
						<table>
					      	<tr  class="linetwo">
					        	<th width="224" align="left"> <%=messages.getText("generic.user.userdata.nickname")%><span class="required">*</span></th>
					         	<td align="left">
					          		<div align="right">
					            		<html:text property="loginname" size="25" styleClass="input"/>
					          		</div>
					          	</td>
					      	</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"><%=messages.getText("generic.user.userdata.pass")%><span class="required">*</span></th>
        						<td align="left">
          							<div align="right">
            							<html:password property="password" size="25" styleClass="input"/>
          							</div>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("web.user.create.confirmpassword")%><span class="required">*</span></th>
        						<td align="left">
          							<div align="right">
            							<html:password property="rePassword" size="25" styleClass="input"/>
          							</div>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" > <%=messages.getText("generic.user.userdata.firstname")%><span class="required">*</span></th>
        						<td align="left">
          							<div align="right">
            							<html:text property="firstName" size="25" styleClass="input"/>
          							</div>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.user.userdata.lastname")%><span class="required">*</span></th>
        						<td align="left">
          							<div align="right">
            							<html:text property="lastName" size="25" styleClass="input"/>
          							</div>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left"> <%=messages.getText("generic.user.userdata.mail")%></th>
        						<td align="left">
          							<div align="right">
            							<html:text property="email" size="25" styleClass="input"/>
          							</div>
          						</td>
      						</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
      						<tr  class="linetwo">
        						<th align="left" > <%=messages.getText("generic.user.userdata.language")%><span class="required">*</span></th>
        						<td align="left" >
									<div align="right">
										<html:select property="language" styleClass="input" style="width:160; ">
											<html:options collection="languageOptionList" property="value" labelProperty="label"/>
										</html:select>
          							</div>
      							</td>
							</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
					      	
      						<tr  class="linetwo">
        						<th align="left"  ><%=messages.getText("generic.user.userdata.role")%><span class="required">*</span></th>
        						<td align="right" ><%=messages.getText("role.contractors.name")%>
      							</td>
							</tr>
					      	<tr  class="space">
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>					      	      	
					      						      	
						    <tr  class="linetwo" >
        						<th align="left" > <%=messages.getText("generic.user.userdata.contractor")%><span class="required">*</span></th>
        						<td align="left" >
									<div align="right">
										<select name="contractor" id="contractor" style="width:160;" class="input" onclick="javascript:changeContractor();">
											<%
											Iterator list = contractorsList.iterator();
											while (list.hasNext()){
												OptionItem oi = (OptionItem) list.next();
												String value = oi.getValue();
												String label = oi.getLabel(); %>
												<option value='<%=value%>' label='<%=label%>'><%=label%></option>
											<% } %>
										</select>
											
										<html:hidden property="contractorName" value='<%=contractorName %>' />
										<html:hidden property="contractorId" value='<%=contractorId %>' />
          							</div>
      							</td>
							</tr>
					      	<tr  class="space" >
					        	<th align="right"></th>
					        	<td align="right"></td>
					      	</tr>
					      					      	
					      	
      						<tr class="linetwo">
        						<td align="right"> </td>
        						<td align="right">
          							<div align="right">
          								<input type="button" class="input" onclick="createWithPassword()" value='<%=messages.getText("user.create.with.password")%>' />
							            <html:submit styleClass="input">
											<%=messages.getText("generic.messages.save")%>
										</html:submit>
							            <html:cancel styleClass="input">
											<%=messages.getText("generic.messages.cancel")%>
										</html:cancel>
          							</div>
          						</td>
      						</tr>
      					</table>
						<jsp:include  page="../component/utilityboxbottom.jsp" />
    				</td>
  				</tr>
			</table>
		</html:form>
	</body>
<%	}	%>
</html:html>
