<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.datatype.tables.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.group.tables.*"
	import="communication.administration.corporation.tables.*"
	import="communication.security.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.driver.*"
	import="java.util.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>

<%@ taglib uri="/WEB-INF/struts-bean.tld"
        prefix="bean" 
%>

<%! Text messages;
%>
<%
	SystemAccess sys=(SystemAccess)session.getAttribute("SystemAccess");
	String check = null;
	check = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_ACTIVITY);
	if(check!=null) {
	    check = Util.checkPermission(sys,SecurityConstants.IMESEVI_UPDATE_PARTICIPANT);
	}
	if(check!=null) {
    	check = Util.checkPermission(sys, SecurityConstants.QUERY);
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages=sys.getText();
		boolean consult = (request.getParameter("consult") != null && 
				!Boolean.parseBoolean(request.getParameter("consult")));

		String format = request.getParameter("format");
		String formatLine = (format == null) ? "linetwo" : "lineone";
		Hashtable countryMaps = sys.getUserSessionData().getFilter().getCountryMap(1,2);
		Enumeration maps = countryMaps.keys();
		while(maps.hasMoreElements()) {
		    Integer key = (Integer)maps.nextElement();
%>					<select name='<%="geo"+key.toString()%>' style="display:none; ">
<%			Collection countryColl = (Collection)countryMaps.get(key);
			Iterator countries = countryColl.iterator();
			while(countries.hasNext()) {
			    String[] item = (String[])countries.next();
%>						<option value='<%=item[1]%>'><%=messages.getText(item[0])%></option>
<%			}
%>					</select>
<%		}
		
		String[] labels = sys.getUserSessionData().getFilter().getDivGeoLabels();
		boolean allElements = false;
		if(!Util.empty(request.getParameter("allElements"))) {
		    allElements = Boolean.parseBoolean(request.getParameter("allElements"));
		}
		Collection groupElements = (allElements) ? sys.getUserSessionData().getFilter().getCorporationGroupLines() : sys.getUserSessionData().getFilter().getGroupLines();

		if(groupElements != null)  {  
	        Iterator groupIt = groupElements.iterator();
	        OptionItem select = new OptionItem(messages.getText("generic.messages.select"),"");
			Collection items1 = new LinkedList();
			items1.add(select);						            
			Collection items2 = new LinkedList();
			items2.add(select);						            
			Collection id1 = new LinkedList();
			Collection id2 = new LinkedList();
			
			
	        while(groupIt.hasNext()) {
				GroupLineData groupLineData = (GroupLineData)groupIt.next();  
				GeoLabelData lid = groupLineData.getCountry();
				if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
					GeoItemData item = (GeoItemData)lid.getItemIterator().next();
					lid = item.getSubItems();
					if(!id1.contains(item.getId())) {
					    id1.add(item.getId());
						items1.add(new OptionItem(item.getName(),String.valueOf(item.getId())));
						
					}
					if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
						item = (GeoItemData)lid.getItemIterator().next();
						lid = item.getSubItems();
						if(!id2.contains(item.getId())) {
						    id2.add(item.getId());
							items2.add(new OptionItem(messages.getText(item.getName()),String.valueOf(item.getId())));
							
						}
					}					
				}
	        }
	        if(items1.size() > 2) {
				Collections.sort((List)items1);
	        }else {
	            items1.remove(select);
	        }
	        if(items2.size() > 2) {
				Collections.sort((List)items2);
	        }else {
	            items2.remove(select);
	        }
			session.setAttribute("divGeo1", items1);
			session.setAttribute("divGeo2", items2);
			Iterator itAll = items2.iterator();
%>					<select name="allcountry" style="display:none; ">
<%			while(itAll.hasNext()) {
				OptionItem item = (OptionItem)itAll.next();
%>						<option value='<%=item.getValue()%>'><%=item.getLabel()%></option>
<%			}
%>					</select>
		<tr  class='<%=formatLine%>'>
        	<td align="left" colspan="3">
    			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">    				
<%			if(format == null) {
%>					<tr class="guide1">
						<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
   		  		  		<td height="30" class="guide1"><div align="left"><%=messages.getText("generic.driver.divgeo")%></div></td>
						<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
  					</tr>  					
  					<tr>
    					<td class="leftLine">&nbsp;</td>
				       	<td height="10" colspan="1"><img src="x" width="1" height="1"></td>
    					<td class="rightLine">&nbsp;</td>
  					</tr>  					  					
	 				<tr>
    					<td class="leftLine">&nbsp;</td>
<%			}else {
%>    				<tr class="guideSearch">
						<td width="19" ><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom"></td>
						<td class="guideSearch" align="left"><%=messages.getText("generic.driver.divgeo")%></td>
						<td width="19" ><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom"></td>
					</tr>
  					<tr>
						<td class="leftLineSearch">&nbsp;</td>
        				<td height="6" class="lineone"><img src="x" width="1" height="1"></td>
        				<td class="rightLineSearch">&nbsp;</td>
  					</tr>  					  					
    				<tr>
    					<td class="leftLineSearch">&nbsp;</td>
<%			}
%>    					
    					<td>
							<table width="100%" cellpadding="0" cellspacing="0">
<%				if(items1.size() > 0) {
					if(items1.size() > 1) {
%>								<tr class='<%=formatLine%>'>
			    					<th align="left"><%=messages.getText(labels[0])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
									<%
									String onChange1 = "javascript:changeCountry('"+request.getParameter("form")+"','country1','country2','"+messages.getText("generic.messages.select")+"');changeOrg('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"')";
									%>
							      		<html:select property="country1" styleClass="input" style="width:155;" 
							      		onchange='<%=onChange1%>' >
											<html:options collection="divGeo1" property="value" labelProperty="label"/>
										</html:select> 										  
									</td>
								</tr>	
<%					}else {
%>					      		<tr class='<%=formatLine%>'>
									<th align="left"><%=messages.getText(labels[0])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
									<%
										String onChange2 = "javascript:changeCountry('"+request.getParameter("form")+"','country1','country2','"+messages.getText("generic.messages.select")+"');changeOrg('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"')"; 
									%>
							      		<html:select property="country1" styleClass="input" style="width:155;" 
							      		onchange='' value='<%=((OptionItem)items1.iterator().next()).getValue()%>' >
											<html:options collection="divGeo1" property="value" labelProperty="label"/>
										</html:select> 										  
									</td>
								</tr>	
							
<%					}
					if(sys.getUserSessionData().getFilter().getGeoLevelCount() > 1) {
						if(items1.size() > 1 || items2.size() > 1) {
%>								<tr  class='<%=formatLine%>'><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class='<%=formatLine%>'>
				    				<th align="left"><%=messages.getText(labels[1])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
									<%
										String onChange3 = "javascript:changeGeo1('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"');changeOrg('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"')";
									%>
							      		<html:select property="country2" styleClass="input" style="width:155;" onchange='<%=onChange3%>'>
											<html:options collection="divGeo2" property="value" labelProperty="label"/>
										  </html:select>   
									</td>
								</tr>	
<%						}else {
%>					      		<tr  class='<%=formatLine%>'><th align="right">&nbsp;</th><td align="right">&nbsp;</td></tr>
								<tr class='<%=formatLine%>'>
				    				<th align="left"><%=messages.getText(labels[1])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
										<%
											String onChange4 = "javascript:changeGeo1('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"');changeOrg('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"')";
										%>
										
							      		<html:select property="country2" styleClass="input" style="width:155;"
							      			 onchange='<%=onChange4%>'  value='<%=((OptionItem)items2.iterator().next()).getValue()%>' >
											<html:options collection="divGeo2" property="value" labelProperty="label"/>
										  </html:select>   
									</td>
								</tr>	
<%						}
					}
				}
				if(!Util.empty(request.getParameter("city"))) {
				    if(request.getParameter("city").equals("both")) {
%>      						<tr  class='<%=formatLine%>'><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class='<%=formatLine%>'>
									<th align="left"> <%=messages.getText("web.event.state")%> </th>
									<td align="right">
										<html:text property="state" size="25" styleClass="input"/>
									</td>
								</tr>
<%					}
				    if(request.getParameter("city").equals("both") || request.getParameter("city").equals("city")) {
%>								<tr  class='<%=formatLine%>'><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
								<tr class='<%=formatLine%>'>
									<th align="left"> <%=messages.getText("web.event.city")%> </th>
									<td align="right">
										<html:text property="city" size="25" styleClass="input"/>
									</td>
								</tr>
<%					}
				}
%>								<tr  class='<%=formatLine%>'><td align="right">&nbsp;</td><td align="right">&nbsp;</td></tr>
							</table>					
						</td>
<%				if(format == null) {						
%>						<td class="rightLine">&nbsp;</td>
					</tr>	
  					<tr>
            	      	<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
  					  	<td class="centerBase"></td>
  				  		<td valign="top"><img src="/datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
				 	</tr>
<%				}else {
%>				 		<td class="rightLineSearch">&nbsp;</td>
					</tr>	
					<tr>
						<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
						<td class="centerBaseSearch"></td>
						<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
					</tr>
<%				}
%>				</table>					  
			</td>
      	</tr>
		<tr>
       		<td height="10" colspan="3"><img src="x" width="1" height="1"></td>
		</tr>
<%			
		}
	}
%>

