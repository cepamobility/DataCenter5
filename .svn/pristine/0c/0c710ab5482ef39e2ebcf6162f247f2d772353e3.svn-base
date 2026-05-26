<%@ page language="java"
    errorPage="../exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
	import="communication.util.*"
	import="java.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();

	String entity = request.getParameter("entity");	
	if (Util.empty(entity)){
		entity = (String)session.getAttribute("entity");
		session.removeAttribute("entity");
	}
	
	// Esta parte es solo para driver upload	
	ListResult notInsertedControls = null;
	ListResult existedControls = null;
	int totalNotInsertedControls = 0;
	int totalExistedControls = 0;
	LinkedList notIC = null;
	LinkedList eC = null;
	
	String total = "";
	if (entity.equalsIgnoreCase("driver.messages.upload.telematiccontrols")){
	
		notInsertedControls = (ListResult)session.getAttribute("notInsertedControls");
		existedControls = (ListResult)session.getAttribute("existedControls");
		totalNotInsertedControls = notInsertedControls.getTotal();
		totalExistedControls = existedControls.getTotal();
		notIC = (LinkedList)notInsertedControls.getElements();
		eC = (LinkedList)existedControls.getElements();
		total = (String)session.getAttribute("total");
	}
	
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
	</head>
 
	<body>
		<center>
			<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
		  		<tr>
		    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
		    		<td width="562" height="30">&nbsp;</td>
		    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
		  		</tr>
		  		<tr>
		    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
	    		  <td width="562" align="center" class="lineone">
		    			<span class="success">		    			
							<%=messages.getText(entity+".message")%>								
			    		</span>
						<br>
		    			<br>
						<%if (entity.equalsIgnoreCase("driver.messages.upload.telematiccontrols")){ %>					
								<%=messages.getText("driver.messages.upload.telematiccontrols.inserted")+" "+total%>
        		  				<br/>
        		  				
        		  				<%
        		  				if (notIC != null && notIC.size() > 0){
        		  				%>
	        		  				<%=messages.getText("driver.messages.upload.telematiccontrols.notInsertedControls")%>
	        		  				<br/>
	        		  				<p align='left'>
	        		  				<%
	        		  				Iterator it = notIC.iterator();
	        		  				
	        		  				while (it.hasNext()){
	        		  					String tcData = (String)it.next(); %>
	        		  					<%=tcData%><br/>        		  				
	        		  				<%
	        		  				}
	        		  				%>        		  				
	        		  				</p>
	        		  			<% 
	        		  			} 
	        		  			%>
        		  				<br/>
        		  				
        		  				<%
        		  				if (eC != null && eC.size() > 0){
        		  				%>
        		  				<%=messages.getText("driver.messages.upload.telematiccontrols.existedControls")%>
        		  				<br/>
        		  				<p align='left'>
        		  				<%
        		  				Iterator it2 = eC.iterator();
        		  				while (it2.hasNext()){
        		  					String tcData = (String)it2.next(); %>
        		  					<%=tcData%><br/>        		  				
        		  				<%
        		  				}
        		  				%>        		  				
        		  				</p>
								<% 
	        		  			} 
	        		  			%>
	        		  			
						<%} %>
						
					</td>
		    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
		    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
			</table>
		</center>
		<br/><br/>
	</body>
</html>
