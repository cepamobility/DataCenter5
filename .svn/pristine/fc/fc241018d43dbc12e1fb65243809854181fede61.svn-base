<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="business.administration.user.*"
	import="business.datatype.*"
	import="communication.language.*"
	import="communication.vehicle.tables.*"
	import="communication.datatype.tables.*"
	import="communication.datatype.*"
	import="communication.corporation.tables.*"
	import="communication.geografic.tables.*"
	import="communication.administration.user.tables.*"
	import="communication.administration.user.tables.geographicdivisions.*"
	import="communication.security.*"
	import="communication.util.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.vehicle.*"
	import="presentation.translator.web.activity.schedule.*"	
	import="java.util.*"
 	import="communication.administration.user.*" 
 	import = "communication.administration.corporation.tables.*"	
	import = "communication.util.Filter"
	import = "communication.administration.RoleData"
	import ="communication.administration.group.tables.*"
 			
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html" 
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	Text messages = sys.getText();
	UserSessionData userSessionData;
	DataTypeAccess datatypes;
	GeoItemData item;
	String check;
	Iterator iter, iter2;
	LinkedList sortCountries = new LinkedList();
	String name="";
	String countryCode = "";
	String addAccInvPermission;
	boolean canAddAccInv = false;
	LinkedList[] items ;
	LinkedList actives ;
	
	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData();
	
	//	Me fijo si tiene permisos para agregar reportes
	addAccInvPermission = Util.checkPermission(sys,SecurityConstants.ADD_ACCIDENT_INVESTIGATION);
	if (addAccInvPermission == null) {
		// Si tiene permiso para agregar reportes y viene con la opcion de agregar
		// le dejo agregar reportes
		String add = (String)request.getParameter("add");
		//System.out.println("Reports.jsp: add="+add);
		canAddAccInv = new Boolean(add.trim()).booleanValue();
		//System.out.println("Reports.jsp: canAddReport="+canAddReport);
	}		
	
	check = Util.checkPermission(sys,SecurityConstants.VIEW_ACCIDENT_INVESTIGATION);	
	
	if(check != null) {
		response.sendRedirect(request.getContextPath()+check);
	}else{
	
		Collection groupLines = null;
		int corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
		boolean isContractor = false;
		if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){			
	isContractor = true;
	groupLines = sys.getUserSessionData().getFilter().getGroupLines();
		}
%>

<html>
<LINK REL=StyleSheet HREF="../util/css/estilo.css" TYPE="text/css">
<script language="javascript">

		function submitAccidentInvestigation(){
			form = document.forms['AccidentInvestigationForm'];			
			form.submit();
		}
</script>


  <body>
  <form name="AccidentInvestigationForm" action="layout.jsp">
  <table width="600"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="19"><img src="../datacenter/util/imgs/left.jpg" width="19" height="30"></td>
          <td width="572" class="guide1"><%=messages.getText("generic.supportmaterial.accidentinvestigation")%></td>
          <td width="19"><img src="../datacenter/util/imgs/right.jpg" width="19" height="30"></td>
        </tr>
        <tr>
          <td width="19" background="../datacenter/util/imgs/left_line.gif">&nbsp;</td>
          <td width="572">
		  <table width="100%" cellpadding="3" cellspacing="0" class="texts" align='center'>			
<% 
				item = null;
				// Ordeno los paises Alfabeticamente:
				if (sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.LOSPIQUES){
					// Para "Los Piques" pongo solo Uruguay.
					 sortCountries.add(new OptionItem(messages.getText("datatype.country.uy"),"datatype.country.uy"));
				}else{
					iter = sys.getUserSessionData().getFilter().getLastLevelItems(sys.getUserSessionData().getFilter().getGeoLevelCount(),null,null);							
					while(iter.hasNext())
					{
	    					 item=(GeoItemData)iter.next();  
	    					 sortCountries.add(new OptionItem(messages.getText(item.getName()),item.getName()));
	    			}
				}
    			Collections.sort(sortCountries);
    			if (!sortCountries.isEmpty())
    			{
    				if (corporationId == CorporationData.FORESTALORIENTAL){	%>
						
						<!-- Fosa -->
  						<!--  < % @ include file="AccidentInvestigationFosa.jsp" % > -->
						<!--  Fin Fosa -->
						<%
						// Muestro 2 select, el primero con el pais y el segundo con el contratista
						// y si es usuario contratista solo muestro el contratista de igual nombre
						// y un boton CONSULTAR.
					
						if (sortCountries != null && sortCountries.size() > 0){ %>
						
						 	<tr><td width="30%" align="left"><%=messages.getText("generic.messages.country") %></td>
						 	<td align='left'>
						 	<input type="hidden" name="refer" value="/routes/AccidentInvestigationCountry.jsp" >
							<select class="input" name="country" style="width:155;" > 
							<% 
							iter2 = sortCountries.iterator();
						
			    			while(iter2.hasNext()){
		    					 OptionItem oi = (OptionItem)iter2.next();
		    					 name = oi.getLabel();
		    					 countryCode = oi.getValue().substring(oi.getValue().length()-2);    
		    					 // ubico ultimo punto:
		    					 int pos = oi.getValue().lastIndexOf(".");
		    					 countryCode = oi.getValue().substring(pos+1);
		    					 // Pre-selecciono Uruguay
		    					 if (countryCode == "uy"){
		    						 %>		
				    					<option value='<%=countryCode %>' label='<%=name %>' selected="selected"><%=name%></option>
								    <%
		    					 }else{
		    						%>		
		    							<option value='<%=countryCode %>' label='<%=name %>'><%=name%></option>
						    <%   }
						    } // end while iter2.hasNext();
						    %>						    
						    </select>
						    <input type="hidden" name="add" value='<%=Boolean.toString(canAddAccInv)%>' >
						    </td></tr>
						    <%
						    
						    // Muestro los contratistas
						 	CorporationData corporation = null;		
							int divOrgId = 0;							
							DivOrgData divOrgData;
			
							divOrgId = 10; // FOSA divOrg			
							
							corporation = sys.getCorporationReportFacade().
								findCorporationByPrimaryKey(sys.getUserSessionData().
								getFilter().getCorporationId(),sys.getUserSessionData());
							
							divOrgData = corporation.getDivOrgData(divOrgId);
							
							if(divOrgData != null) {				
								int length = divOrgData.getLabels().length;
								String label = divOrgData.getLabels()[0].getName();
								items = divOrgData.getItems(1,null,null); // Traigo Divorg1
								actives = items[0]; // Active items, linkedlist de itemdata: id,name,active
								
								if (isContractor && groupLines != null){
									
									// Si es contratista, en ves de mostrar todos los contratistas
									// muestro a los que tiene acceso:
									 actives = null;
									 actives = new LinkedList();
									 Iterator it5 = groupLines.iterator();
									 while (it5.hasNext()){
										 GroupLineData gld = (GroupLineData)it5.next();
										 LabelItemData[] labelItemData = gld.getDivOrgs();
									 	 actives.addAll(labelItemData[0].getItems());
									 }
								}
								
								if (actives != null && actives.size() > 0){ %>
									<tr><td width="30%"><%=messages.getText("generic.messages.contractor") %></td>
									<td align='left'>
									<select class="input" name="contractor" style="width:155;"  >
									<% 
									Iterator itContractor = actives.iterator();
									while (itContractor.hasNext()){
										ItemData itemContractor = (ItemData)itContractor.next(); %>										
										 <option value='<%=messages.getText(itemContractor.getName())%>' label='<%=messages.getText(itemContractor.getName())%>' >
										 <%=messages.getText(itemContractor.getName())%></option>
														
								<%	} // fin while		
								%>
									</select>									
									</td></tr>
								<%	
								} // fin if actives != null
							} // fin if divOrgData != null
							%>
							<tr><td align="right">
							<button value='<%=messages.getText("generic.messages.query")%>' onclick="javascript:submitAccidentInvestigation();">
							<%=messages.getText("generic.messages.query")%></button>
							</td></tr>
						<%			
						} // end if sortCountries != null
						%>
						
						
					<%
				    }else{ 
    				
						// Otras compañias diferentes a FOSA:
						// link a las investigaciones de accidentes para toda la compañia:
								
	    				// Muestro los paises ordenados:
		    			iter2 = sortCountries.iterator();
		    			while(iter2.hasNext()){
	    					 OptionItem oi = (OptionItem)iter2.next();
	    					 name = oi.getLabel();
	    					 countryCode = oi.getValue().substring(oi.getValue().length()-2);    
	    					 // ubico ultimo punto:
	    					 int pos = oi.getValue().lastIndexOf(".");
	    					 countryCode = oi.getValue().substring(pos+1);
							%>	
				            <tr>       
				              <td align='center'>
				              	<%
				              		String href1 = "layout.jsp?refer=/routes/AccidentInvestigationCountry.jsp?country="+countryCode+"&add="+Boolean.toString(canAddAccInv);
				              	%>
					       		<a href='<%=href1 %>' > 
				              		<%=name%>
				              	</a>
				               </td>
				            </tr>		            
				            
	             	<% 
	   					 } // end while iter2.hasNext();
   					 
				 	} // fin if FOSA 
   					 
   					 
   				}else{%>
   	        <tr>       
              <td align='center'>	       		
              		<%=messages.getText("generic.supportmaterial.routes.noaccidentinvestigation")%>             
               </td>
            </tr>
    				
<% 				}%>
        </table></td>
          <td width="19" background="../datacenter/util/imgs/right_line.gif">&nbsp;</td>
        </tr>
        <tr>
          <td width="19"><img src="../datacenter/util/imgs/left_base.gif" width="19" height="11"></td>
          <td width="572" background="../datacenter/util/imgs/center_base.gif"><img src="x" height="1" width="1"></td>
          <td width="19"><img src="../datacenter/util/imgs/right_base.gif" width="19" height="11"></td>
        </tr>
    </table></td>
  </tr>
  <tr><td></td></tr>
</table>
<br/><br/><br/>
</form>	    										
</body>
<%		 
	}
%>
</html>
