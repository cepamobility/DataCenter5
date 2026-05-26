<%@page language="java"
	errorPage="../exception.jsp"

	import="business.*"
	import="communication.language.*"
	import="java.util.*"
	import="java.lang.*"
	import="presentation.translator.web.util.*"
	import="presentation.translator.web.activity.schedule.*"
	import="communication.driver.tables.*"
	import="communication.security.*"
	import="communication.datatype.*"
	import="communication.administration.user.*"
	import="communication.datatype.tables.*"
	
	import="ws.assessment.datacenter.AssessmentWSEJBService" 
	import="ws.assessment.datacenter.AssessmentWSEJB"
	import="javax.xml.ws.BindingProvider"
	import="ws.assessment.datacenter.AssessmentData"
	import="ws.assessment.datacenter.AssessmentDataList"
%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<%! 
	RequestDispatcher dispatcher; String context;String pathView; 
	String pathMsg; String pathSearch; 
	Text messages; String pathLanguage;
	SystemAccess sys; 
	DriverData data; 
	UserSessionData userSessionData;
	String id;
%>
<%
	try{

	sys = (SystemAccess)session.getAttribute("SystemAccess");
	userSessionData = sys.getUserSessionData(); 
	
	String check = null;
	if(check!=null) {
		response.sendRedirect(request.getContextPath()+check);
	}else{
		messages=sys.getText();

		pathLanguage="/util/jsp/language.jsp";
	

		dispatcher=request.getRequestDispatcher(pathLanguage);
		dispatcher.include(request,response);
	
		context=request.getContextPath();
		pathMsg="/util/jsp/message.jsp";
	
		dispatcher=request.getRequestDispatcher(pathMsg);
		dispatcher.include(request,response);
		
		
		LinkedList assessmentsOptions = new LinkedList();
		
		if(session.getAttribute("AssessmentResultForm") == null) {
			AssessmentResultForm form = new AssessmentResultForm();
		    session.setAttribute("AssessmentResultForm",form);
		}
		
		try // Capturamos errores de comunicacion con el webservice
		{
			AssessmentWSEJBService service = new AssessmentWSEJBService();
			AssessmentWSEJB port = service.getAssessmentWSEJBPort();
		
			// Seteamos la URL del WebService Remoto:
			String endpointURL = SecurityConstants.WS_ASSESSMENT_DC5;
			System.out.println("Conectando con: "+endpointURL);
			BindingProvider bp = (BindingProvider)port;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
			AssessmentDataList assessments = port.getCorporationAssessments(new Integer(sys.getCorporationId()));
		
			if(assessments != null) {
				Iterator it = assessments.getAssessmentData().iterator();
				while(it.hasNext()) {
					AssessmentData a = (AssessmentData)it.next();
					assessmentsOptions.add(new OptionItem(a.getName(),a.getId().toString()));
				}
				Collections.sort(assessmentsOptions);
				session.setAttribute("assessments",assessmentsOptions);
			}
		}catch(java.lang.Exception e){
			System.out.println("Error de Comunicación con el WebService Assessment-DataCenter: assessmentResult.jsp");			
		}
		
		
		Collection format = new LinkedList();
		format.add(new OptionItem("HTML","HTML"));	    
		format.add(new OptionItem("PDF","PDF"));	    
		format.add(new OptionItem("XLS","XLS"));	    
		session.setAttribute("formats",format);
		
		
		
%>
<!-- AssessmentResult.jsp -->

<html:form action="/AssessmentResult">			
	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="guide1">
		<jsp:include  page='<%="../../component/titlecomponent.jsp?title="+messages.getText("query.assessmentresult.title")%>' />
  			</tr>
	      	<tr>
				<jsp:include  page="../../component/spaceline.jsp" />
      		</tr>
	      	<tr>
    	    	<td width="100%" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<jsp:include  page='<%="../../component/utilityboxtop.jsp?title="+messages.getText("query.assessmentresult.options")%>' />
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr align="left">
			      							<td>
				      							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="default" >
					      							<tr>
														<th align="left">
															<span class="linetwo"><%=messages.getText("query.assessmentresult.assessmentname")%></span>
														</th>
														<td align="right">
															  <% if (assessmentsOptions.size() > 0){ %>
															  	<html:select property="assessment" styleClass="input" style="width:155;">
                  													<html:options collection="assessments" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
															  <% }else{ %>
															  	<%=messages.getText("query.assessmentresult.noassessmentsavailable")%>
															  <% } %>
														</td>
													</tr> 
													<tr>
														<jsp:include  page="../../component/spaceline.jsp" />
      												</tr>
      												
      												<% if (assessmentsOptions.size() > 0){ %>
      												<tr>
														<th align="left">
															<span class="linetwo"><%=messages.getText("query.assessmentresult.format")%></span>
														</th>
														<td align="right">
															 
															  	<html:select property="format" styleClass="input" style="width:155;">
                  													<html:options collection="formats" property="value" labelProperty="label" styleClass="input"/>
                												</html:select>
															
														</td>
													</tr> 
													<tr>
														<jsp:include  page="../../component/spaceline.jsp" />
      												</tr>
													<tr>
														<td align="right" colspan="2">
														<% if (assessmentsOptions.size() > 0){ %>
																<html:submit styleClass="input" value='<%=messages.getText("generic.messages.consult")%>' />
														<% } %>
														</td>
													</tr>
													<% } %>
													
												</table>
											</td>			
										</tr>
									</table>
									<jsp:include  page="../../component/utilitybox4bottom.jsp" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>

<%
	} // fin if check
	}catch(java.lang.Exception e){
		e.printStackTrace();
	}
%>
