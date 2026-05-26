<%@page language="java" 
	import="business.*"
	import="business.administration.user.*" 
	import="business.administration.corporation.*" 
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.user.tables.*"
	import="presentation.translator.web.user.*"
	import="presentation.translator.web.util.*"
	import="java.util.*"
	errorPage="../exception.jsp"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html:html>	   

<%! RequestDispatcher dispatcher; String pathMsg;
	Text messages; String pathLanguage;
	DivOrgData divOrgData;
	SystemAccess sys;
%>
<%
	sys = (SystemAccess)session.getAttribute("SystemAccess"); 
	String check = Util.checkPermission(sys,SecurityConstants.EDIT_CORPORATION);
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		boolean found = false;
		pathLanguage="/util/jsp/language.jsp";
	
		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);

		messages=sys.getText();
		divOrgData = (DivOrgData)session.getAttribute("currentDivOrg");
		session.removeAttribute("currentDivOrg");
		if(divOrgData != null) {
    	    boolean linetwo = false;
			boolean hasPermission = sys.getUserSessionData().checkPermission(SecurityConstants.MODIFY_CORPORATION);
%>

<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">

<head/>
	<form name='<%=divOrgData.getName()%>'>	   
		<table width='100%' align='center'>
			<tr align='center' width='90%' >
<%		if(hasPermission) {	%>
				<td width='5%' class="guide2">&nbsp;</td>
<%		}
		LabelItemAttribute[] labels = divOrgData.getLabels();
		for(int i = 0; i < 3; i++) {			
%>				<td width='30%' class="guide2"><%=(i < labels.length) ? messages.getText(labels[i].getName()) : "&nbsp;" %></td>
<%		}
%>			</tr>
<%	    if(divOrgData.getDivOrgLevels() != null) {
	        Iterator itLevel1 = divOrgData.getDivOrgLevels().getItemIterator();
	        while(itLevel1.hasNext()) {
				found = true;
	            boolean first = true;
	            ItemData item1 = (ItemData)itLevel1.next();
	            if(item1.getSubItems() == null) {	                
					if(item1.isActive()) {
%>	 		<tr align='center' width='90%' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
<%						if(hasPermission) {	%>
				<td width='5%' align='center''>
	            	<input type='checkbox' name='<%=item1.getId()%>' value='<%=item1.getId()%>' >
	            </td>
<%						}					
%>	       		<td width='30%' >
<%						if(hasPermission) {	%>
				    <a href='layout.jsp?refer=/corporation/AddItem.jsp?level=2&id=<%=divOrgData.getId()%>&level1=<%=item1.getId()%>' >
						<%=item1.getName()%>
					</a>	
<%						}else {	
%>						<%=item1.getName()%>
<%						}	
%>				</td>
           		<td width='30%' >&nbsp;</td>
           		<td width='30%' >&nbsp;</td>
			</tr>
<%					}
	            }else {
	                Iterator itLevel2 = item1.getSubItems().getItemIterator();
	                while(itLevel2.hasNext()) {
	    	            boolean second = true;
	                    ItemData item2 = (ItemData)itLevel2.next();
	                    if(item2.getSubItems() == null) {
							if(item1.isActive() && item2.isActive()) {
%>			<tr align='center' width='90%' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
<%								if(hasPermission) {			
%>				<td width='5%' align='center''>
<%					            if(first) {
%>	            	<input type='checkbox' name='<%=item1.getId()%>' value='<%=item1.getId()%>' >
<%	            				}
%>	            </td>
<%							}	
%>		        <td width='30%' >
<%				            if(first) {
								if(hasPermission) {	
%>	    		    <a href='layout.jsp?refer=/corporation/AddItem.jsp?level=2&id=<%=divOrgData.getId()%>&level1=<%=item1.getId()%>' >
	               	 	 <%=item1.getName()%>
					</a>
<%								}else {	%>
	           	 	<%=item1.getName()%>
<%							}
	       				    first = false;
	       				}
%>				</td>	 	
           		<td width='30%' >
<%								if(hasPermission) {		%>
    	    		<a href='layout.jsp?refer=/corporation/AddItem.jsp?level=3&id=<%=divOrgData.getId()%>&level1=<%=item1.getId()%>&level2=<%=item2.getId()%>' >
						<%=item2.getName()%>
					</a>
<%								}else {					%>
					<%=item2.getName()%>
<%								}						%>			
				</td>
           		<td width='30%' >&nbsp;</td>
			</tr>
<%	                		}
	                    }else {
	    	                Iterator itLevel3 = item2.getSubItems().getItemIterator();
	    	                while(itLevel3.hasNext()) {
	    	                    ItemData item3 = (ItemData)itLevel3.next();
								if(item1.isActive() && item2.isActive() && item3.isActive()) {
%>			<tr align='center' width='90%' class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
<%									if(hasPermission) {			
%>				<td width='5%' align='center''>
<%			    	            	if(first) {
%>	                
	            	<input type='checkbox' name='<%=item1.getId()%>' value='<%=item1.getId()%>' >
<%	            	
	    	    		            }
%>	            
	            </td>
<%		}	
%>		
	            <td width='30%' >
<%
    	    		            	if(first) {
										if(hasPermission) {
%>	                
				    <a href='layout.jsp?refer=/corporation/AddItem.jsp?level=2&id=<%=divOrgData.getId()%>&level1=<%=item1.getId()%>' >
	               	 	 <%=item1.getName()%>
					 </a>
<%										}else {				%>
	               	 	 <%=item1.getName()%>
<%										}
    	    		                	first = false;
    	    		            	}
%>
				</td>
    			<td width='30%' >
<%
    	    		            	if(second) {	
										if(hasPermission) {	
%>               
    	    		<a href='layout.jsp?refer=/corporation/AddItem.jsp?level=3&id=<%=divOrgData.getId()%>&level1=<%=item1.getId()%>&level2=<%=item2.getId()%>' >
    	    			<%=item2.getName()%>
    	    		</a>
<%										}else {				%>
    	    			<%=item2.getName()%>
<%										}
    	    		                	second = false;
    	    	    	    		}
%>
    			</td>
    			<td width='30%' >
<%									if(hasPermission) {	%>
    	    		<a href='layout.jsp?refer=/corporation/AddItem.jsp?level=4&id=<%=divOrgData.getId()%>&level1=<%=item1.getId()%>&level2=<%=item2.getId()%>&level3=<%=item3.getId()%>' >
					<%=item3.getName()%>
					</a>
<%									}else {				%>
					<%=item3.getName()%>
<%									}					%>										
				</td>
    		</tr>
    					
<% 						 		}
	    	                }    
	                    }
	                }
	            }
	            linetwo = !linetwo;
	        }
	    }
		if(found) {
			if(hasPermission) {
%>
			<tr  class='<%=(linetwo) ? "linetwo" : "lineone"%>'>
				<td colspan="4">
                	<div align="right">
                  		<input name="button" type="button" onClick="javascript:deleteItem(document.forms[2],document.forms['<%=divOrgData.getName()%>'],<%=divOrgData.getId()%>,'label3');" value='<%=messages.getText("generic.messages.delete")%>' class="input"/>
                	</div></td>
				</td>
			</tr>
<%
			}
	    }else {
%>
			<tr class="linetwo">
				<td colspan="4"><%=messages.getText("generic.user.divorg.noasociations")%></td>
			</tr>
<%
		}
%>
		</table>
<%
		}
	}
%>
	</form>
</html:html>