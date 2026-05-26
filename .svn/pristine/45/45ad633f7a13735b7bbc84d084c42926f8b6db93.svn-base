<%@page language="java" 
	errorPage="../../exception.jsp"
	import="business.*"
	import="java.util.*"
	import="presentation.report.*"
	import="communication.language.*"
	import="communication.security.*"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<html>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	String check = presentation.translator.web.util.Util.checkPermission(sys,SecurityConstants.EDIT_CEPASCHEDULEACTIVITYREGISTRY); 	
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else {
		Text messages=sys.getText();
%>	

	<LINK REL=StyleSheet HREF="../../util/css/estilo.css" TYPE="text/css">
	<head>
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Expires" content="-1">		
	</head>

	<body>
    	<table width="600" border="0" cellspacing="0" cellpadding="0">
	    	<tr> 
        		<td height="25" colspan="5" valign="middle">
              		<font color="#000000" size="4" face="Arial, Helvetica, sans-serif">
			        	<strong>
							BTW
            	  		</strong>
            	  	</font>
				</td>
            </tr>
     		<tr>
	     		<td colspan="5">&nbsp;</td>
     		</tr>   
            <tr> 
        		<td valign="top"> 
          			<table width="100%" border="1" cellpadding="0" cellspacing="0">
            			<tr> 
        					<th align="center" valign="top" class="print"> 
                  				<%= messages.getText("generic.activity.asistence")%>
							</th>
            			</tr>
            			<tr>
            				<td>
            					<table width="100%" border="0" cellspacing="0" cellpadding="0">
			            			<tr> 
			              				<td class="print"> 
			              					<input type="checkbox"> 
			                				<%= messages.getText("generic.activity.asistence.noteorico")%>
			              				</td>
			            			</tr>
			            			<tr> 
			              				<td class="print"> 
			              					<input type="checkbox"> 
			                				<%= messages.getText("generic.activity.asistence.nopractico")%>
			              				</td>
			            			</tr>            
			            		</table>
			            	</td>
			            </tr>		
          			</table>            
				</td>
				<td valign="top">&nbsp;</td>
		 		<td colspan="3" valign="top"> 
          			<table width="100%" border="1" cellpadding="0" cellspacing="0">
            			<tr> 
        					<th align="center" valign="top" class="print"> 
                  				<%=messages.getText("generic.activity.phrdbycourse")%>
							</th>
            			</tr>
            			<tr>
            				<td>
            					<table width="100%" border="0" cellspacing="0" cellpadding="0">
			            			<tr> 
			              				<td class="print"> 
			              					<input type="checkbox"> 
			               					<%= messages.getText("generic.activity.phrdbycourse.isphrd")%>
			              				</td>
			            			</tr>
			            			<tr><td>&nbsp;</td></tr>
			            		</table>
			            	</td>
			            </tr>		
          			</table>            
				</td>
			</tr>
      		<tr> 
				<td valign="top">&nbsp;</td>
			</tr>
      		<tr> 
        		<td width="200" valign="top"> 
          			<table width="100%" border="1" cellpadding="0" cellspacing="0">
            			<tr> 
        					<th align="center" valign="top" class="print"> 
                 				<%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%>
                  			</th>
            			</tr>
            			<tr>
            				<td>
            					<table width="100%" border="0" cellspacing="0" cellpadding="0">
			            			<tr> 
			              				<td class="print"> 
			              					<input type="checkbox"> 
			          						<%= messages.getText("generic.activity.form.btw1liviano.novato")%>
			              				</td>
			            			</tr>
			          			</table>            
							</td>
						</tr>
          			</table>            
				</td>
				<td valign="top" width="5px"></td>
				<td width="200" valign="top"> 	
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr> 
        					<th align="center" valign="top" class="print"> 
	        					<%=messages.getText("generic.activity.form.tranningonthejob.vehicletype")%>
        					</th>
     					</tr>
					     <tr> 
				    	    <td> 
				        		<input type="text" class="print" style="width:250; height:10;" />       
	        				</td>
    	 				</tr>
					</table>
				</td>
				<td valign="top" width="5px"></td>
				<td width="200" valign="top">
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr> 
        					<th align="center" valign="top" class="print"> 
        						<%=messages.getText("generic.activity.form.tranningonthejob.velocitybox")%>
        					</th>
     					</tr>
            			<tr>
            				<td>
            					<table width="100%" border="0" cellspacing="0" cellpadding="0">
			     					<tr> 
			        					<td class="print">        
			        						<INPUT type="radio" value="generic.activity.form.tranningonthejob.velocitybox.manual">	    				
			        						<%=messages.getText("generic.activity.form.tranningonthejob.velocitybox.manual")%>
			        					</td>
			     					</tr>
			      					<tr>  
			        					<td class="print">        
			             					<INPUT type="radio" name="velocityBox" value="generic.activity.form.tranningonthejob.velocitybox.automatic">	    				
			        						<%= messages.getText("generic.activity.form.tranningonthejob.velocitybox.automatic")%>
			        					</td>
			     					</tr>       
			     					<tr>
								     	<td>&nbsp;</td>
								    </tr>      
			          			</table>            
							</td>
						</tr>
					</table>
				</td>	
			</tr>       
      		<tr> 
				<td valign="top">&nbsp;</td>
			</tr>
      		<tr> 
<%		PrintUtil util = new PrintUtil();
		Column column1 = new Column(1,195);

		Header header1 = new Header("generic.activity.form.btw1liviano.position",1);
		String[] options1 = {"generic.activity.form.btw1liviano.position.pedalnext","generic.activity.form.btw1liviano.position.steeringwheelnext","generic.activity.form.btw1liviano.position.pedalfar","generic.activity.form.btw1liviano.position.steeringwheelfar","generic.activity.form.btw1liviano.position.steeringwheelhands","generic.activity.form.btw1liviano.position.onehand"};
		header1.addOptions(options1);
		column1.addHeader(header1);
		
		Header header2 = new Header("generic.activity.form.btw1liviano.maneuverinreducedspace1",2);
		header2.getOptions().add(new Option("generic.activity.form.btw1liviano.maneuverinreducedspace.asistence",new Integer(1),Option.LABEL));
		String[] options2 = {"generic.activity.form.btw1liviano.maneuverinreducedspace.signal","generic.activity.form.btw1liviano.maneuverinreducedspace.spaceandpositionbeforemaneuver","generic.activity.form.btw1liviano.maneuverinreducedspace.referencesearch","generic.activity.form.btw1liviano.maneuverinreducedspace.referenceuse","generic.activity.form.btw1liviano.maneuverinreducedspace.possibilityevaluation","generic.activity.form.btw1liviano.maneuverinreducedspace.techniqueapply","generic.activity.form.btw1liviano.maneuverinreducedspace.generalobservation","generic.activity.form.btw1liviano.maneuverinreducedspace.mirroruse","generic.activity.form.btw1liviano.maneuverinreducedspace.blindpointcontrol","generic.activity.form.btw1liviano.maneuverinreducedspace.sensibilitywithcommands","generic.activity.form.btw1liviano.maneuverinreducedspace.commandcoordination","generic.activity.form.btw1liviano.maneuverinreducedspace.velocitycontrol","generic.activity.form.btw1liviano.maneuverinreducedspace.limitedmaneuver"};
		header2.addOptions(options2,2);
		column1.addHeader(header2);
		
		Header header3 = new Header("generic.activity.form.btw1liviano.mirrors",3);
		String[] options3 = {"generic.activity.form.btw1liviano.mirrors.regulationoptions","generic.activity.form.btw1liviano.mirrors.blindpoint"};
		header3.addOptions(options3);
		column1.addHeader(header3);

		Header header4 = new Header("generic.activity.form.btw1liviano.velocity",4);
		String[] options4 = {"generic.activity.form.btw1liviano.velocity.tendency","generic.activity.form.btw1liviano.velocity.indication"};
		header4.addOptions(options4);
		column1.addHeader(header4);

		Column column2 = new Column(2,195);

		Header header5 = new Header("generic.activity.form.btw1liviano.mecanicarms",1);
		String[] options5 = {"generic.activity.form.btw1liviano.mecanicarms.onehand","generic.activity.form.btw1liviano.mecanicarms.openhands","generic.activity.form.btw1liviano.mecanicarms.steeringwheelinside","generic.activity.form.btw1liviano.mecanicarms.bottomlocked","generic.activity.form.btw1liviano.mecanicarms.dinamic","generic.activity.form.btw1liviano.mecanicarms.limitedmoviments","generic.activity.form.btw1liviano.mecanicarms.specialwork"};
		header5.addOptions(options5);
		column2.addHeader(header5);

		Header header6 = new Header("generic.activity.form.btw1liviano.braking",2);
		header6.getOptions().add(new Option("Frenaje Convencional",new Integer(1),Option.RADIO));
		String[] options61 = {"generic.activity.form.btw1liviano.requiredbraking.incorrecttechnique","generic.activity.form.btw1liviano.requiredbraking.pressesfirstclutch","generic.activity.form.btw1liviano.requiredbraking.avoidwithoutcontrollingvelocity"};
		header6.addOptions(options61,2);
		header6.getOptions().add(new Option("Frenaje ABS",new Integer(5),Option.RADIO));
		String[] options62 = {"generic.activity.form.btw1liviano.brakingABS.ignoranceabssystem","generic.activity.form.btw1liviano.brakingABS.ignorancevehiclesystem","generic.activity.form.btw1liviano.brakingABS.pressesfirstclutch","generic.activity.form.btw1liviano.brakingABS.powerlack","generic.activity.form.btw1liviano.brakingABS.freesthebrake","generic.activity.form.btw1liviano.brakingABS.coachesalternativetechniques"};
		header6.addOptions(options62,6);
		column2.addHeader(header6);

		Header header7 = new Header("generic.activity.form.btw1liviano.accelerationreaction",3);
		String[] options7 = {"generic.activity.form.btw1liviano.accelerationreaction.eliminate","generic.activity.form.btw1liviano.accelerationreaction.partlyeliminate","generic.activity.form.btw1liviano.accelerationreaction.noteliminate"};
		header7.addOptions(options7);
		column2.addHeader(header7);

		Header header8 = new Header("generic.activity.form.btw1liviano.physicsapplied",4);
		String[] options8 = {"generic.activity.form.btw1liviano.physicsapplied.notappliestechniques"};
		header8.addOptions(options8);
		column2.addHeader(header8);

		Column column3 = new Column(3,195);

		Header header9 = new Header("generic.activity.form.btw1liviano.pedalgearshift1",1);
		String[] options9 = {"generic.activity.form.btw1liviano.pedalgearshift.technicaluseclutch","generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithclutch","generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithbrake","generic.activity.form.btw1liviano.pedalgearshift.sensibilitywithaccelerator","generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchgearshift","generic.activity.form.btw1liviano.pedalgearshift.coordinationclutchaccelerator"};
		header9.addOptions(options9);
		column3.addHeader(header9);

		Header header10 = new Header("generic.activity.form.btw1liviano.boxofchanges1",2);
		String[] options10 = {"generic.activity.form.btw1liviano.boxofchanges.sensibilityerror","generic.activity.form.btw1liviano.boxofchanges.distributionincorrect","generic.activity.form.btw1liviano.boxofchanges.incorrectuseofneuterpoint"};
		header10.addOptions(options10);
		column3.addHeader(header10);
		
		Header header11 = new Header("generic.activity.form.btw1liviano.avoidrequired",4);
		String[] options11 = {"generic.activity.form.btw1liviano.avoidrequired.wheelblocked","generic.activity.form.btw1liviano.avoidrequired.brakecoordination","generic.activity.form.btw1liviano.avoidrequired.undueaccelerationreaction","generic.activity.form.btw1liviano.avoidrequired.dynamic","generic.activity.form.btw1liviano.avoidrequired.brakingtechniqueapplication","generic.activity.form.btw1liviano.avoidrequired.notbrakingtechniqueapplication","generic.activity.form.btw1liviano.avoidrequired.coordinationandsensibilityerrors"};
		header11.addOptions(options11);
		column3.addHeader(header11);

		Header header12 = new Header("generic.activity.form.btw1liviano.reactiontime",5);
		String[] options12 = {"generic.activity.form.btw1liviano.reactiontime.correctsmaneuvererrors","generic.activity.form.btw1liviano.reactiontime.correctstimeerrors","generic.activity.form.btw1liviano.reactiontime.satisfactory","generic.activity.form.btw1liviano.reactiontime.notsatisfactory","generic.activity.form.btw1liviano.reactiontime.medicalappraisal"};
		header12.addOptions(options12);
		column3.addHeader(header12);

		util.addColumn(column1);
		util.addColumn(column2);
		util.addColumn(column3);
		
		Collection columns = util.getColumns();
		Collections.sort((List)columns);
		Iterator itColumns = columns.iterator();
		int i = 1;
		while(itColumns.hasNext()) {
		    Column column = (Column)itColumns.next();
%>		    <td width='<%=column.getWidth()%>' valign="top">
<%			Collection headers = column.getHeaders();
			Collections.sort((List)headers);
			Iterator itHeaders = headers.iterator();
			while(itHeaders.hasNext()) {
			    Header header = (Header)itHeaders.next();
%>				<table width="100%" border="1" cellpadding="0" cellspacing="0">
            		<tr> 
        				<th align="center" valign="top" class="print"> 
                			<%= messages.getText(header.getKey())%>
						</th>
            		</tr>
            		<tr>
            			<td>
            				<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%				Collection options = header.getOptions();
				Collections.sort((List)options);
				Iterator itOptions = options.iterator();
				while(itOptions.hasNext()) {
				    Option option = (Option)itOptions.next();
%>		            			<tr> 
		              				<td class="print"> 
<%					switch(option.getType()) {
				    	case Option.LABEL:
%>				    	    			<b><%= messages.getText(option.getKey())%></b>
<%							break;
				    	case Option.CHECK:
%>					  					<input type="checkbox"/> 
      									<%= messages.getText(option.getKey())%>
<%							break;
						case Option.RADIO:
%>					  					<input type="radio"/> 
										<%= messages.getText(option.getKey())%>
<%					}
%>		              				</td>
		            			</tr>
<%				}
%>            				</table>
            			</td>
            		</tr>
				</table>
<%			}
%>			</td>
<%			if(i < columns.size()) {
    			i++;
%>			<td valign="top" width="5px"></td>
<%			}
		}
%>			</tr>
			<tr> 
				<td valign="top">&nbsp;</td>
			</tr>
      		<tr> 
        		<td colspan="5">
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr> 
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
								    	<th align="left" valign="middle" class="print">
							        		<%=messages.getText("generic.activity.form.tranningonthejob.proofdata")%>
								        </th>
								     </tr>
     								<tr> 
     									<td>
											<input type=text name="name" style="width:250; height:10;" class="printtext" />              
										</td>
									</tr>	
							     	<tr> 
										<td valign="top">&nbsp;</td>
									</tr>
									<tr> 
								    	<th align="left" valign="middle" class="print">
							        		<%=messages.getText("generic.activity.observations")%>
								        </th>
								     </tr>
     								<tr> 
     									<td>
						                	<textarea name="obs" style="border: black 1px solid;" cols="90" rows="5"></textarea>
										</td>
									</tr>	
							     	<tr> 
										<td valign="top">&nbsp;</td>
									</tr>
						      		<tr> 
		    							<th align="left" valign="middle" class="print">
						        			<%=messages.getText("generic.activity.instructor")%>
						        		</th>
						        	</tr>
						        	<tr>	
						        		<td align="left">	
											<input type=text name="name" style="width:250; height:10;" class="printtext" />              
										</td>
									</tr>
								</table>
							</td>	
						</tr>	
					</table>	
				</td>
			</tr>	
    	</table>
		<script>window.print();window.close();</script>
	</body>
<% }
%>
</html>