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
	import="communication.util.Filter"
	import="communication.util.ElementData"
	import="presentation.translator.web.util.Util"
	import="presentation.translator.web.util.OptionItem"
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
	    DriverDivOrgForm form = (session.getAttribute(request.getParameter("form")) == null) ? null : (DriverDivOrgForm)session.getAttribute(request.getParameter("form"));
		String format = request.getParameter("format");
		String formatLine = (format == null) ? "linetwo" : "lineone";
		Text messages=sys.getText();
		boolean consult = (request.getParameter("consult") != null && !Boolean.parseBoolean(request.getParameter("consult")));
		
		String select = messages.getText("generic.messages.select");
        OptionItem selectOption = new OptionItem(select,"");
		DivOrgAttribute[] attrs = sys.getUserSessionData().getFilter().getDivOrgsAttrs();
		String[] labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
		boolean allElements = false;
		if(!Util.empty(request.getParameter("allElements"))) {
		    allElements = Boolean.parseBoolean(request.getParameter("allElements"));
		}
		Collection groupElements = (allElements) ? sys.getUserSessionData().getFilter().getCorporationGroupLines() : sys.getUserSessionData().getFilter().getGroupLines();
		Hashtable[] elements = communication.administration.corporation.util.Util.getDivOrgMap(groupElements,sys.getUserSessionData().getFilter().getDivOrgCount());
	    for(int i = 0; i < sys.getUserSessionData().getDivOrgCount(); i++) {
	        Hashtable items = elements[i*3];
			Collection items1 = new LinkedList();
			items1.add(selectOption);						            
			if(items != null) {			
		        Enumeration keys = items.keys();
				Collection id1 = new LinkedList();
			    while(keys.hasMoreElements()) {
	        	    Integer countryId = (Integer)keys.nextElement();
	            	Iterator it1 = ((Collection)items.get(countryId)).iterator();
%>					<select name='<%="geo_org"+String.valueOf(i)+countryId.toString()%>' style="display:none; ">
<%	        		while(it1.hasNext()) {
    					ElementData element = (ElementData)it1.next();
%>						<option value='<%=element.getId().toString()%>'><%=element.getName()%></option>
<%						if(!id1.contains(element.getId())) {
						    id1.add(element.getId());
							items1.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
							
						}
					}
%>					</select>
<%				}
			}
	        items = elements[i*3+1];
			Collection items2 = new LinkedList();
			items2.add(selectOption);
			if(items != null) {			
		        Enumeration keys = items.keys();
				Collection id2 = new LinkedList();
	    	    while(keys.hasMoreElements()) {
	        	    Integer level1 = (Integer)keys.nextElement();
	            	Iterator it1 = ((Collection)items.get(level1)).iterator();
%>					<select name='<%="org"+String.valueOf(i)+"|"+level1.toString()%>' style="display:none; ">
<%	        		while(it1.hasNext()) {
    					ElementData element = (ElementData)it1.next();
%>						<option value='<%=element.getId().toString()%>'><%=element.getName()%></option>
<%						if(form != null && !Util.empty(form.getDivOrg(i+1,1)) && form.getDivOrg(i+1,1).equals(String.valueOf(level1))) {
							if(!id2.contains(element.getId())) {
						    	id2.add(element.getId());
								items2.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
							}
						}else {
						    if(items1.size() == 2) {
								items2.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
						    }
						}
					}
%>					</select>
<%				}
			}
	        items = elements[i*3+2];
			Collection items3 = new LinkedList();
			items3.add(selectOption);
			if(items != null) {			
		        Enumeration keys = items.keys();
				Collection id3 = new LinkedList();
	    	    while(keys.hasMoreElements()) {
	        	    Integer level2 = (Integer)keys.nextElement();
	            	Iterator it2 = ((Collection)items.get(level2)).iterator();
%>					<select name='<%="org_2"+String.valueOf(i)+"|"+level2.toString()%>' style="display:none; ">
<%	        		while(it2.hasNext()) {
    					ElementData element = (ElementData)it2.next();
%>						<option value='<%=element.getId().toString()%>'><%=element.getName()%></option>
<%						if(form != null && !Util.empty(form.getDivOrg(i+1,2)) && form.getDivOrg(i+1,2).equals(String.valueOf(level2))) {
							if(!id3.contains(element.getId())) {
						    	id3.add(element.getId());
								items3.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
							}
						}else {
    						if(items1.size() == 2 && items2.size() == 2) {
								items3.add(new OptionItem(element.getName(),String.valueOf(element.getId())));
    						}
						}
					}
%>					</select>
<%				}
			}
	        if(items1.size() > 2) {
				Collections.sort((List)items1);
	        }else {
	            items1.remove(selectOption);
	        }
			Iterator itAll = items1.iterator();
%>					<select name='<%="allorg"+String.valueOf(i)%>' style="display:none; ">
<%			while(itAll.hasNext()) {
				OptionItem item = (OptionItem)itAll.next();
%>						<option value='<%=item.getValue()%>'><%=item.getLabel()%></option>
<%			}
%>					</select>
<%	        if(items2.size() > 2) {
				Collections.sort((List)items2);
	        }else{
	        	items2.remove(selectOption);
	        }	        	
	        if(items3.size() > 2) {
				Collections.sort((List)items3);
	        }else{
	            items3.remove(selectOption);
	        }
			session.setAttribute("divOrg"+String.valueOf(i+1)+"1", items1);
			session.setAttribute("divOrg"+String.valueOf(i+1)+"2", items2);
			session.setAttribute("divOrg"+String.valueOf(i+1)+"3", items3);
			
	
	
%>
<script language="JavaScript" type="text/javascript">


</script>

		<tr  class="<%=formatLine%>">
        	<td align="left" colspan="3">
    			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default">    				
<%				if(format == null) {
%>					<tr class="guide1">
						<td width="19" height="30" ><img src="/datacenter/util/imgs/left.jpg" width="19" height="30"></td>
   		  		  		<td height="30" class="guide1"><div align="left"><%=(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.CENAPRA) ? messages.getText("imesevi.participant.state") : messages.getText(attrs[i].getName())%></div></td>
						<td width="19" height="30" ><img src="/datacenter/util/imgs/right.jpg" width="19" height="30"></td>
  					</tr>  					
  					<tr>
    					<td class="leftLine">&nbsp;</td>
				       	<td height="10" colspan="1"><img src="x" width="1" height="1"></td>
    					<td class="rightLine">&nbsp;</td>
  					</tr>  					  					
	 				<tr>
    					<td class="leftLine">&nbsp;</td>
<%				}else {
%>    				<tr class="guideSearch">
						<td width="19" ><img src="/datacenter/util/imgs/left_top_search.jpg" width="19" height="30" align="bottom"></td>
						<td class="guideSearch" align="left"><%=messages.getText(attrs[i].getName())%></td>
						<td width="19" ><img src="/datacenter/util/imgs/right_top_search.jpg" width="19" height="30" align="bottom"></td>
					</tr>
  					<tr>
						<td class="leftLineSearch">&nbsp;</td>
        				<td height="10" colspan="1" class="lineone"><img src="x" width="1" height="1"></td>
    					<td class="rightLineSearch">&nbsp;</td>
  					</tr>  					  					
    				<tr>
    					<td class="leftLineSearch">&nbsp;</td>
<%				}
%>    					
    					<td>
							<table width="100%" cellpadding="0" cellspacing="0">
<%				if(items1.size() > 1) {
    				String javascript = "";
    				if(sys.getUserSessionData().getFilter().getDivOrgLevel(i) > 1 && (items1.size() > 1 || items2.size() > 1)) {
    				    javascript = "javascript:changeDivOrg('"+request.getParameter("form")+"','divOrg"+String.valueOf(i+1)+"1','divOrg"+String.valueOf(i+1)+"2','org"+String.valueOf(i)+"|','"+select+"')";
    					if(sys.getUserSessionData().getFilter().getDivOrgLevel(i) > 2 && (items1.size() > 1 || items2.size() > 1 || items3.size() > 1)) {
    					    javascript += ";changeDivOrg('"+request.getParameter("form")+"','divOrg"+String.valueOf(i+1)+"2','divOrg"+String.valueOf(i+1)+"3','org_2"+String.valueOf(i)+"|','"+select+"')";
    					}
    				}
%>								<tr class="<%=formatLine%>">
			    					<th align="left"><%=(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.CENAPRA) ? messages.getText("imesevi.participant.state") : messages.getText(labels[i*3])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
									
							      		<html:select property='<%="divOrg"+String.valueOf(i+1)+"1"%>' styleClass="input" style="width:155;" onchange='<%=javascript%>'>
											<html:options collection='<%="divOrg"+String.valueOf(i+1)+"1"%>' property="value" labelProperty="label"/>
										</html:select>   
										  
									</td>
								</tr>	
<%				}else {
					String value = "";
					Iterator itValue = items1.iterator();
					if(itValue.hasNext()) {
					    value = ((OptionItem)itValue.next()).getValue();
%>								<html:hidden property='<%="divOrg"+String.valueOf(i+1)+"1"%>' value='<%=value%>'/>
<%					}
				}
				if(sys.getUserSessionData().getFilter().getDivOrgLevel(i) > 1) {
					if(items1.size() > 1 || items2.size() > 1) {
	    				String javascript = "";
    					if(sys.getUserSessionData().getFilter().getDivOrgLevel(i) > 2 && (items1.size() > 1 || items2.size() > 1 || items3.size() > 1)) {
    					    javascript += "changeDivOrg('"+request.getParameter("form")+"','divOrg"+String.valueOf(i+1)+"2','divOrg"+String.valueOf(i+1)+"3','org_2"+String.valueOf(i)+"|','"+select+"')";
    					}
%>    							<tr class="<%=formatLine%>">
        							<th align="right">&nbsp;</th>
        							<td align="right">&nbsp;</td>
	      						</tr>
								<tr class="<%=formatLine%>">
				    				<th align="left"><%=messages.getText(labels[i*3+1])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
							      		<html:select property='<%="divOrg"+String.valueOf(i+1)+"2"%>' styleClass="input" style="width:155;" onchange='<%=javascript%>'>
											<html:options collection='<%="divOrg"+String.valueOf(i+1)+"2"%>' property="value" labelProperty="label"/>
										  </html:select>   
									</td>
								</tr>	
<%					}else { 
						if(items1.size() == 1 || items2.size() == 1) {
							String value = "";
							Iterator itValue = items2.iterator();
							if(itValue.hasNext()) {
						    	value = ((OptionItem)itValue.next()).getValue();
%>								<html:hidden property='<%="divOrg"+String.valueOf(i+1)+"2"%>' value='<%=value%>'/>
<%							}
						}else{	
%>
								<tr class="<%=formatLine%>">
									<th align="right">&nbsp;</th>
									<td align="right">&nbsp;</td>
								</tr>
								<tr class="<%=formatLine%>">
									<th align="left"><%=messages.getText(labels[i*3+1])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
<%							String value = "";
							Iterator itValue = items2.iterator();
							if(itValue.hasNext()) {
						    	value = ((OptionItem)itValue.next()).getValue();
							}
%>									
								  		<html:select property='<%="divOrg"+String.valueOf(i+1)+"2"%>' styleClass="input" style="width:155;" value='<%=value%>'>
											<html:options collection='<%="divOrg"+String.valueOf(i+1)+"2"%>' property="value" labelProperty="label" />
										  </html:select>   
									</td>
								</tr>							
<%						}
					}
					if(sys.getUserSessionData().getFilter().getDivOrgLevel(i) > 2) {
						if(items1.size() > 1 || items2.size() > 1 || items3.size() > 1) {
%>    							<tr class="<%=formatLine%>">
									<th align="right">&nbsp;</th>
									<td align="right">&nbsp;</td>
  								</tr>
								<tr class="<%=formatLine%>">
				    				<th align="left"><%=messages.getText(labels[i*3+2])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
							      		<html:select property='<%="divOrg"+String.valueOf(i+1)+"3"%>' styleClass="input" style="width:155;" >
											<html:options collection='<%="divOrg"+String.valueOf(i+1)+"3"%>' property="value" labelProperty="label"/>
									  	</html:select>   
									</td>
								</tr>	
<%						}else {
%>					      		
								<tr class="<%=formatLine%>">
									<th align="right">&nbsp;</th>
									<td align="right">&nbsp;</td>
  								</tr>
								<tr class="<%=formatLine%>">
				    				<th align="left"><%=messages.getText(labels[i*3+2])%><span class="required"><%=(consult)?"*":""%></span></th>
									<td align="right">
<%						String value = "";
						Iterator itValue = items3.iterator();
						if(itValue.hasNext()) {
						    value = ((OptionItem)itValue.next()).getValue();
						}
%>									
							      		<html:select property='<%="divOrg"+String.valueOf(i+1)+"3"%>' styleClass="input" style="width:155;" value='<%=value%>' >
											<html:options collection='<%="divOrg"+String.valueOf(i+1)+"3"%>' property="value" labelProperty="label"/>
									  	</html:select>   
									</td>
								</tr>	
<%						}
					}
				}
%>      						<tr class="<%=formatLine%>">
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
%>				 		<td class="rightLineSearch">&nbsp;</td>
					</tr>	
					<tr>
						<td height="11" valign="top"><img src="/datacenter/util/imgs/left_base_search.jpg" width="19" height="11"></td>
						<td class="centerBaseSearch"></td>
						<td valign="top" height="11"><img src="/datacenter/util/imgs/right_base_search.jpg" width="19" height="11"></td>
					</tr>
<%				}
%>				 	
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

