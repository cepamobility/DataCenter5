<%@page language="java"
	errorPage="../exception.jsp"
	import="business.SystemAccess"
	import="business.language.LanguageReportFacade"
	import="communication.language.*"
	import="communication.language.tables.LanguageData"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%!
	Collection languages; Iterator iter; SystemAccess sys; LanguageData lngData;
	Text messages; RequestDispatcher dispatcher; String pathLanguage; 
	LanguageReportFacade lngReport; String lng; String target; Collection languageOptionList;
%>
<%
	pathLanguage="/util/jsp/language.jsp";
	
	dispatcher=request.getRequestDispatcher(pathLanguage);
	dispatcher.include(request,response);	
		
	sys=(SystemAccess)session.getAttribute("SystemAccess");
   	if(sys==null) {
		response.sendRedirect("/login.jsp");
	}else {
   		messages=sys.getText();
   		lngReport=sys.getLanguageReportFacade();
		languages=lngReport.findAllLanguage(sys.getUserSessionData());
		iter=languages.iterator();
		lng="es";
		languageOptionList=new LinkedList();
		
		while(iter.hasNext()){
			lngData=(LanguageData)iter.next();	
			if(lngData.getName().compareTo(lng)==0){
				languageOptionList.add(new OptionItem(messages.getText(lngData.getName()),lngData.getName()));
			}
		}
		iter=languages.iterator();
		
		while(iter.hasNext()){
			lngData=(LanguageData)iter.next();	
			if(lngData.getName().compareTo(lng)!=0){
				languageOptionList.add(new OptionItem(messages.getText(lngData.getName()),lngData.getName()));
			}
		}
		session.setAttribute("languageOptionList",languageOptionList);
	}
%>