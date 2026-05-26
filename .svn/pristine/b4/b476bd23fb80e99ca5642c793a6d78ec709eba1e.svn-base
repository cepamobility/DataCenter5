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
		if(check!=null) {
	    	check = Util.checkPermission(sys, SecurityConstants.QUERY);
		}
	}
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages=sys.getText();
		boolean consult = (request.getParameter("consult") != null && !Boolean.parseBoolean(request.getParameter("consult")));

		String format = request.getParameter("format");

		Hashtable countryMaps = sys.getUserSessionData().getFilter().getCountryMap(2,2);
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
		Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
		if(groupLines != null)  {  
	        Iterator groupIt = groupLines.iterator();
	        OptionItem select1 = new OptionItem(messages.getText("generic.messages.all.region"),"0");
	        String select2 = messages.getText("generic.messages.all.country");
			Collection items1 = new LinkedList();
			Collection items2 = new LinkedList();
			items1.add(select1);						            
			items2.add(new OptionItem(select2,"0"));						            
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
					GeoItemData item2 = (GeoItemData)item.getSubItems().getItemIterator().next();
					if(!id2.contains(item2.getId())) {
					    id2.add(item2.getId());
						items2.add(new OptionItem(messages.getText(item2.getName()),String.valueOf(item2.getId())));
					}
				}
	        }
	        if(items1.size() > 2) {
				Collections.sort((List)items1);
	        }else {
	            items1.remove(select1);
	        }
	        if(items2.size() > 2) {
				Collections.sort((List)items2);
	        }else {
	            items2.remove(select2);
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
<script>
function changeCountry(form,parent,child,message,all){
	var parentCombo = document.forms[form].elements[parent];
	var childCombo = document.forms[form].elements[child];
	if(parentCombo.options[0].selected) {
		var len = 1;
		var k = 1;
		vector = new Array();
		for(i=1; i < parentCombo.options.length; i++) {
			var divi = "geo"+parentCombo.options[i].value;
			var list = document.forms[form].elements[divi];
			for(j=0; j < list.length; j++) {
				vector[k] = list.options[j].text +"_"+list.options[j].value;
				k = k+1;
			}
			len=len+list.options.length;
		}
		vector.sort();
		childCombo.options.length=len;
		childCombo.options[0].value = 0;
		childCombo.options[0].text = all;
		for(i=0; i < vector.length; i++) {
			values = vector[i].split("_");
			childCombo.options[i+1].value = values[1];
			childCombo.options[i+1].text = values[0];
		}
		childCombo.selectedIndex = 0;
	}else {
		var len = 1;
		var k = 1;
		vector = new Array();
		for(i=0; i < parentCombo.options.length; i++) {
			if(parentCombo.options[i].selected) {
				var divi = "geo"+parentCombo.options[i].value;
				var list = document.forms[form].elements[divi];
				for(j=0; j < list.length; j++) {
					vector[k] = list.options[j].text +"_"+list.options[j].value;
					k = k+1;
				}
				len=len+list.options.length;
			}
		}
		vector.sort();
		childCombo.options.length=len;
		childCombo.options[0].value = 0;
		childCombo.options[0].text = all;
		for(i=0; i < vector.length; i++) {
			values = vector[i].split("_");
			childCombo.options[i+1].value = values[1];
			childCombo.options[i+1].text = values[0];
		}
		childCombo.selectedIndex = 0;
	}
}
</script>
		<tr  class="linetwo">
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
        				<td height="6"><img src="x" width="1" height="1"></td>
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
%>								<tr class="linetwo">
			    					<th align="left"><%=messages.getText(labels[0])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
									<%
									String onchange1 = "javascript:changeCountry('"+request.getParameter("form")+"','country1','country2','"+messages.getText("generic.messages.select")+"','"+select2+"'); "; 
									%>
							      		<html:select property="country1" styleClass="input" style="width:155;" onchange='<%=onchange1%>' size="10" multiple="true" value="0">
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
										<%
											String onchange2 = "javascript:changeGeo1('"+request.getParameter("form")+"','"+messages.getText("generic.messages.select")+"');";
										%>
							      		<html:select property="country2" styleClass="input" style="width:155;" onchange='<%=onchange2%>' size="10" multiple="true" value="0">
											<html:options collection="divGeo2" property="value" labelProperty="label"/>
										  </html:select>   
									</td>
								</tr>	
<%						}else {
%>					      		<html:hidden property="country2" value='<%=((OptionItem)items2.iterator().next()).getValue()%>' />
<%						}
					}
				}
%>      						<tr  class="space">
  		      						<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
      							</tr>
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
%>				 	
						<td class="rightLineSearch">&nbsp;</td>
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

