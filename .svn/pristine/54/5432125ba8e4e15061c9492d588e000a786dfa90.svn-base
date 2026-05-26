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
	ListResult notUpload = null;
	int totalNotUpload = 0;
	LinkedList notUploadFines = null;
	String total = "";
	if (entity.equalsIgnoreCase("fine.messages.upload")){
		notUpload = (ListResult)session.getAttribute("notupload");
		totalNotUpload = notUpload.getTotal();
		notUploadFines = (LinkedList)notUpload.getElements();
		total = (String)session.getAttribute("total");
	}
	
	
	

//	Boolean view = (Boolean) (session.getAttribute("view"));
//	Boolean view = (Boolean) (session.getAttribute("activityview"));
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
		    			<br>
						<img src="/datacenter/util/imgs/icon1.jpg" height="12" width="12">&nbsp;&nbsp;
							<a href='<%=messages.getText(entity+".link")%>'class="linksuccess">
							<%=messages.getText(entity+".next")%></a>
						<br>
						<br>
						<br>
						
						<%if (entity.equalsIgnoreCase("fine.messages.upload")){ %>					
								<%=messages.getText("fine.messages.upload.inserted")+" "+total%>
        		  				<br/>
        		  				<%
        		  					if (notUploadFines != null && notUploadFines.size() > 0){
        		  				%>
        		  				
        		  				<br/>
        		  				<%=messages.getText("fine.messages.upload.notinserted")%>
        		  				<br/>
        		  				<p align='left'>
        		  				<%
        		  						Iterator it = notUploadFines.iterator();
        		  				
        		  						while (it.hasNext()){
        		  							String fineData = (String)it.next(); %>
        		  				
        		  							- <%=fineData%><br/>
        		  				
        		  				<%
        		  						}
        		  					}
        		  				%>
        		  				
        		  				</p>
								
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
