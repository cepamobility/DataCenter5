<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import = "communication.administration.RoleData"
	import="java.util.*"
	import ="communication.administration.group.tables.*"
	import = "communication.administration.corporation.tables.*"
	import="communication.language.*"
	import="communication.administration.RoleData"
	
	
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>
<%

	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	Collection groupLines = null;
	LinkedList actives = null;
	boolean isContractor = false;
	DivOrgAttribute[] attrs = sys.getUserSessionData().getFilter().getDivOrgsAttrs();
	Text messages=sys.getText();
	if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){			
		isContractor = true;
		groupLines = sys.getUserSessionData().getFilter().getGroupLines();
	}
	if (isContractor && groupLines != null){		
		// Si es contratista, en ves de mostrar todos los contratistas
		// muestro a los que tiene acceso:
		 actives = new LinkedList();
		 Iterator it5 = groupLines.iterator();
		 while (it5.hasNext()){
			 GroupLineData gld = (GroupLineData)it5.next();
			 LabelItemData[] labelItemData = gld.getDivOrgs();
		 	 actives.addAll(labelItemData[0].getItems());
		 }
	}
	if (actives != null && actives.size() > 0){ %>
		<select name="allorg0" style="display:none">
			
		<% 
		// <option value="0" label="messages.getText("messages.group.all")">messages.getText("messages.group.all")</option>
		Iterator itContractor = actives.iterator();
		while (itContractor.hasNext()){
			ItemData itemContractor = (ItemData)itContractor.next(); %>										
		 	<option value='<%=itemContractor.getId()%>' label='<%=messages.getText(itemContractor.getName())%>' >
		 	<%=messages.getText(itemContractor.getName())%></option>
						
<%		} // fin while		
%>
		</select>
		<tr  class="linetwo">
        	<td align="left" colspan="3">
    			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">    				
					<tr class="guide1">
						<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText(attrs[0].getName())%></div></td>

						<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
  					</tr>  					
  					<tr>
    					<td class="leftLine">&nbsp;</td>
				       	<td height="10" colspan="1"><img src="x" width="1" height="1"></td>
    					<td class="rightLine">&nbsp;</td>
  					</tr>  					  					
	 				<tr>
    					<td class="leftLine">&nbsp;</td>
    					
    					<td>

							<table width="100%" cellpadding="0" cellspacing="0">
								<tr class="linetwo">
			    					<th align="left"><%=messages.getText(attrs[0].getName())%><span class="required"></span></th>
									<td align="right">
									
									<select name="divOrg11" multiple="multiple" size="10" onchange="" style="width:155;" class="input">
										
										<%
											// <option value="0" selected="selected" label="messages.getText("messages.group.all")">messages.getText("messages.group.all")</option>
											Iterator itContractor2 = actives.iterator();
											while (itContractor2.hasNext()){
												ItemData itemContractor = (ItemData)itContractor2.next(); %>										
		 										<option value='<%=itemContractor.getId()%>' selected="selected" label='<%=messages.getText(itemContractor.getName())%>' >
		 										<%=messages.getText(itemContractor.getName())%></option>
																				
										<%	} // fin while		
										%>
									</select>									 
									</td>
								</tr>	
      						<tr  class="space">
  		      						<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
      							</tr>

							</table>					
						</td>
						<td class="rightLine">&nbsp;</td>
					</tr>	
  					<tr>
            	      	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
  					  	<td class="centerBase"></td>
  				  		<td valign="top"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
				 	</tr>
				 	
    			</table>					  
			</td>

      	</tr>
		<tr>
       		<td height="10" colspan="3"><img src="x" width="1" height="1"></td>
		</tr>
		
		
											
<%	
	} // fin if actives != null
%>


		