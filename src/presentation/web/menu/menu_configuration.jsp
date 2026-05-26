<%@page import="business.administration.corporation.CorporationReportFacade"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="communication.util.tables.ResumeData"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.administration.corporation.tables.MenuOptionData"%>
<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
 	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText(); 
	CorporationReportFacade corporationReport = sys.getCorporationReportFacade();
	Collection<MenuOptionData> resume = corporationReport.getMenuOptions(userSessionData);
	Collection<Integer> added = sys.getCorporationReportFacade().findNoSelectedOption(userSessionData,true);
%>
<html>
	<script type="text/javascript" src="./menu/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
	function save(){
		var form = document.forms['ConfigurateCorporationMenuForm'];
		var elements=form.elements;
		var listIds="";
		for(i=0; i<elements.length; i++){
			var element=elements[i];
			if(element.type.toLowerCase()=="checkbox"){
				if(!element.checked){
					listIds=element.value+","+listIds;
				}	
			} 
		}
		form.list.value=listIds;
		form.submit();
	}	
	</script>
	<style>
	@font-face {
		font-family: 'Roboto';
		src: url('./menu/font/Roboto-Regular.ttf');
	}
	.title {
		color: #4F4647;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:22px;
    	padding: 5px;
    }
	.level0 {
		color: black;
		width: 600px;
		background-color:#F2F2F2;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding: 5px;
    	margin: 5px;
    }
	.level1 {
		color: black;
		background-color:#D2D2D2;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding: 5px;
    	margin: 5px;
    }
	.level2 {
		color: black;
		background-color:#A2A2A2;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding: 5px;
    	margin: 5px;
    }
	.level3 {
		color: black;
		background-color:#929292;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding: 5px;
    	margin: 5px;
    }
	.selected {
		color: green;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding-left: 10px;
    }
	.noselected {
		color: red;
    	font-family: Roboto, Verdana, Times, serif;
    	font-size:14px;
    	padding-left: 10px;
    }
	</style>
	<body style="bgcolor=">
		<div class="title">
			<%=messages.getText("generic.data.menuoptions") %>
		</div>
		<html:form action='<%="/MenuConfiguration"%>' >
			<html:hidden property="list" />
			<div class="line"></div>
<%		Iterator it = resume.iterator();
		while(it.hasNext()) {
			Object[] data = ((MenuOptionData)it.next()).print(messages,1,added);
%>		
			<div class='level0'>		
				<%=data[1].toString()%>		
			</div>
			<div class="line"></div>
<%		}
%>
			<div >		
				<input type="button" value="Guardar" onclick="save();"/>
			</div>
		</html:form>
	</body>
</html>
