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
	if(sys.getUserSessionData().isDriver()) {
    	check = Util.checkPermission(sys,SecurityConstants.DRIVER_CREATE_EVENT);
		if(check!=null) {
		    check = Util.checkPermission(sys,SecurityConstants.DRIVER_CREATE_FINE);
		}
	}else {
		check = Util.checkPermission(sys,SecurityConstants.CREATE_DRIVER);
		if(check!=null) {
		    check = Util.checkPermission(sys,SecurityConstants.CREATE_EVENT);
		}
		if(check!=null) {
	    	check = Util.checkPermission(sys,SecurityConstants.CREATE_FINE);
		}
		if(check!=null) {
	    	check = Util.checkPermission(sys,SecurityConstants.CREATE_CEPASCHEDULEACTIVITY);
		}		
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages=sys.getText();
		boolean consult = (request.getParameter("consult") != null && !Boolean.parseBoolean(request.getParameter("consult")));

		Hashtable countryMaps = sys.getUserSessionData().getFilter().getCountryMap(1,3);
		Enumeration maps = countryMaps.keys();
		while(maps.hasMoreElements()) {
		    Integer key = (Integer)maps.nextElement();
%>					<select name='<%="geo"+key.toString()%>' style="display:none; ">
<%			Collection countryColl = (Collection)countryMaps.get(key);
			if(countryColl.size() > 1) {
%>						<option value=""><%=messages.getText("generic.messages.select")%></option>
<%			}
			Iterator countries = countryColl.iterator();
			while(countries.hasNext()) {
			    String[] item = (String[])countries.next();
%>						<option value='<%=item[1]%>'><%=messages.getText(item[0])%></option>
<%			}
%>					</select>
<%		}
		
		String[] labels = sys.getUserSessionData().getFilter().getDivGeoLabels();
		if(sys.getUserSessionData().getFilter().getGroupLines() != null)  {  
	        Iterator groupIt = sys.getUserSessionData().getFilter().getGroupLines().iterator();
	        OptionItem select = new OptionItem(messages.getText("generic.messages.select"),"");
			Collection items1 = new LinkedList();
			items1.add(select);						            
			Collection items2 = new LinkedList();
			items2.add(select);						            
			Collection items3 = new LinkedList();
			items3.add(select);						            
			Collection id1 = new LinkedList();
			Collection id2 = new LinkedList();
			Collection id3 = new LinkedList();
	        while(groupIt.hasNext()) {
				GroupLineData groupLineData = (GroupLineData)groupIt.next();  
				GeoLabelData lid = groupLineData.getCountry();
				if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
					GeoItemData item = (GeoItemData)lid.getItemIterator().next();
					lid = item.getSubItems();
					if(!id1.contains(item.getId())) {
					    id1.add(item.getId());
						if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
							items1.add(new OptionItem(item.getName(),String.valueOf(item.getId())));
						}else {
							items1.add(new OptionItem(messages.getText(item.getName()),String.valueOf(item.getId())));
						}
					}
					if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
						item = (GeoItemData)lid.getItemIterator().next();
						lid = item.getSubItems();
						if(!id2.contains(item.getId())) {
						    id2.add(item.getId());
							if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
								items2.add(new OptionItem(item.getName(),String.valueOf(item.getId())));
							}else {
								items2.add(new OptionItem(messages.getText(item.getName()),String.valueOf(item.getId())));
							}
						}
						if(lid != null && lid.getItems() != null && lid.getItems().size() > 0) {
							item = (GeoItemData)lid.getItemIterator().next();
							if(!id3.contains(item.getId())) {
							    id3.add(item.getId());
								items3.add(new OptionItem(messages.getText(item.getName()),String.valueOf(item.getId())));
							}
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
	        if(items2.size() > 2) {
				Collections.sort((List)items2);
	        }else {
	            items2.remove(select);
	        }
			session.setAttribute("divGeo1", items1);
			session.setAttribute("divGeo2", items2);
			session.setAttribute("divGeo3", items3);
%>
		<tr  class="linetwo">
        	<td align="left" colspan="3">
    			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">    				
					<tr class="guide1">
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
    					<td>
							<table width="100%" cellpadding="0" cellspacing="0">
<%				if(items1.size() > 0) {
					if(items1.size() > 1) {
%>								<tr class="linetwo">
			    					<th align="left"><%=messages.getText(labels[0])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
							      		<html:select property="country1" styleClass="input" style="width:155;" onchange='<%="javascript:changeCountry('"+request.getParameter("form")+"','country1','country2','"+messages.getText("generic.messages.select")+"');"%>'>
											<html:options collection="divGeo1" property="value" labelProperty="label"/>
										  </html:select>   
									</td>
								</tr>	
<%					}else {
%>					      		<html:hidden property="country1" value='<%=((OptionItem)items1.iterator().next()).getValue()%>' />
<%					}
					if(sys.getUserSessionData().getFilter().getGeoLevelCount() > 1) {
						if(items1.size() > 1 || items2.size() > 1) {
%>    							<tr  class="space">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
								<tr class="linetwo">
				    				<th align="left"><%=messages.getText(labels[1])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
							      		<html:select property="country2" styleClass="input" style="width:155;" onchange='<%="javascript:changeGeo1('"+request.getParameter("form")+"');"%>'>
											<html:options collection="divGeo2" property="value" labelProperty="label"/>
										  </html:select>   
									</td>
								</tr>	
<%						}else {
%>					      		<html:hidden property="country2" value='<%=((OptionItem)items2.iterator().next()).getValue()%>' />
<%						}
						if(sys.getUserSessionData().getFilter().getGeoLevelCount() > 2) {
							if(items1.size() > 1 || items2.size() > 1 || items3.size() > 1) {
%>    							<tr  class="space">
									<th align="right">&nbsp;</th>
									<td align="right">&nbsp;</td>
  								</tr>
								<tr class="linetwo">
				    				<th align="left"><%=messages.getText(labels[2])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
							      		<html:select property="country3" styleClass="input" style="width:155;" onchange='<%="javascript:cambio('divOrg11','divOrg12','"+select+"');cambio('divOrg12','divOrg13','"+select+"')"%>'>
											<html:options collection="divGeo3" property="value" labelProperty="label"/>
									  	</html:select>   
									</td>
								</tr>	
<%							}else {
%>					      		<html:hidden property="country3" value='<%=((OptionItem)items3.iterator().next()).getValue()%>' />
<%							}
						}
					}
				}
%>      						<tr  class="space">
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
		}
	}
%>

