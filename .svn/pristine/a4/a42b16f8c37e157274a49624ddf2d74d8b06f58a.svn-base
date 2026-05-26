<%@page import="presentation.translator.web.util.UploadValidator.ColumnMetadata"%>
<%@page import="communication.util.MetadataColumn"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="communication.administration.corporation.tables.ItemData"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.driver.tables.DriverData"%>
<%@ page language="java"
    errorPage="exception.jsp"
	import="business.*"
	import="presentation.translator.web.util.*"
	import="communication.language.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	
%>

<html>
	<head>
		<LINK REL=StyleSheet HREF="./util/css/estilo.css" TYPE="text/css">
		<LINK REL=StyleSheet HREF="./util/css/bootstrap.min.css" TYPE="text/css">
	</head>
	<script type="text/javascript">
	 $(document).ready(
		function(){
			$('select[tag="selColumn"]').each(
				function(){
					var name=$(this).attr('name');
					var value=$(this).attr('value');
					$(this).change(
						function(){
							$('select[tag="selColumn"]').each(
								function(){
									if($(this).attr('name')!=name){
										$(this).find('option').each(
											function(){
												if($(this).attr('value')==value){
													$(this).remove();
												}
											}
										);
									}
								}	
							)
						}
					)					
				}
			);
		}
	 );
	</script>
 
	<body>
		<center>
			<html:form action="/UploadUpdateDriver"  enctype="multipart/form-data">
			<html:hidden property="step" value="1"/>
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
							<%=messages.getText("driver.upload.update.title2")%>								
			    		</span>
						<br>
		    			<br>
						<table width="600" border="0" cellpadding="0" cellspacing="0" class="guideSearch">
					  		<tr>
					    		<td width="19" height="30"><img src="./util/imgs/left_top_search.jpg" width="19" height="30" /></td>
					    		<td width="562" height="30"><%=messages.getText("driver.upload.update.selectcolumns")%></td>
					    		<td width="19" height="30"><img src="./util/imgs/right_top_search.jpg" width="19" height="30" /></td>
					  		</tr>
					  		<tr>
					    		<td background="./util/imgs/left_line_search.jpg">&nbsp;</td>
				    		  	<td width="562" align="center" class="lineone">
				    		  	<div style="height: 400px;width: 100%;overflow: scroll;">
				    		  		<table class="table table-bordered table-striped table-condensed">
				    		  		
				    		  		<%
				    		  			
				    		  		    ArrayList rows=(ArrayList)session.getAttribute("rows");
				    		  			Iterator rowIter=rows.iterator();
				    		  			if(rows.size()>0){
				    		  				ArrayList rowHead=(ArrayList)rowIter.next();
			    		  					%>
				    		  					<tr class="active">
				    		  				<%
				    		  						Iterator celHeadIterator=rowHead.iterator();
				    		  						int i=0;
				    		  						while(celHeadIterator.hasNext()){
				    		  							String celHead=(String)celHeadIterator.next();
				    		  							%>
				    		  								<td style="font-style: bold;font-size: 18px" >
				    		  									<%= celHead %>
				    		  									<br/>
				    		  									<% 
				    		  									   UploadValidator validator=new UploadValidator(sys.getCorporationId());
				    		  									   
				    		  									%>
				    		  									<select name="column<%=i%>" tag="selColumn">
				    		  										<%for(ColumnMetadata col:validator.columns){ %>
				    		  											<option value="<%=col.getColIndex()%>"><%=messages.getText(col.getToken())%></option>
				    		  										<%}%>
				    		  									</select>
				    		  								</td>
				    		  							<%
				    		  							i++;
				    		  						}
				    		  				%>
			    		  						</tr>
			    		  					<%
			    		  					
					    		  			while(rowIter.hasNext()){
					    		  				ArrayList row=(ArrayList)rowIter.next();
					    		  				%>
					    		  					<tr class="success">
					    		  				<%
					    		  						Iterator celIterator=row.iterator();
					    		  						while(celIterator.hasNext()){
					    		  							String cel=(String)celIterator.next();
					    		  							%>
					    		  								<td>
					    		  									<%= cel %>
					    		  								</td>
					    		  							<%
					    		  						}
					    		  				%>
				    		  						</tr>
				    		  					<%
					    		  			}
				    		  			}else{
				    		  				
												%>
				    		  						<%=messages.getText("driver.upload.update.empty")%>
				    		  					<%
				    		  			}
				    		  		%>
										
											
										
									</table>
								</div>	
				    		  	</td>
					    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
					  		</tr>
					  		<tr>
					    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
					    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
					    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
						  	</tr>
						</table>
						<html:submit value='<%=messages.getText("generic.messages.accept")%>' />
					</td>
		    		<td background="./util/imgs/right_line_search.jpg">&nbsp;</td>
		  		</tr>
		  		<tr>
		    		<td width="19" height="11"><img src="./util/imgs/left_base_search.jpg" width="19" height="11" /></td>
		    		<td width="562" height="11" background="./util/imgs/center_base_search.jpg"><img src="x" width="1" height="1" /></td>
		    		<td width="19" height="11"><img src="./util/imgs/right_base_search.jpg" width="19" height="11" /></td>
			  	</tr>
			</table>
			</html:form>
		</center>
		<br/><br/>
	</body>
</html>
