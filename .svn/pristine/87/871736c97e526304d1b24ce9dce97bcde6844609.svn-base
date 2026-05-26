<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
	import="communication.language.*"
	import="communication.security.*"
	import="communication.administration.user.*"
	import="communication.administration.corporation.tables.*"
	import="communication.administration.corporation.*"
	import="communication.fleet.tables.FleetData"
	import="presentation.translator.web.util.*"
	import="java.util.*"
%>

<%! String context;%>
<%
	context=request.getContextPath();
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys==null){
		response.sendRedirect("../login.jsp");
	}
	Text messages = sys.getText(); 
	UserSessionData userSession=sys.getUserSessionData(); 
	int corporationId = userSession.getFilter().getCorporationId().intValue();
	Collection menu = new LinkedList();
	menu = (Collection)session.getAttribute("menuItems");
	if(menu==null){
		menu=sys.getCorporationReportFacade().findMenuOption(userSession);
		session.setAttribute("menuItems",menu);
	}
	
	
%>
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="./css/menu.css"> 
<script language="JavaScript" src="./js/slidemenu.js" type="text/javascript"></script>
<script language="JavaScript" src="./js/menu_config.js" type="text/javascript"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 -->
</style>
</head>
<body>
	<table align="left" width="150" valign="top">
		<tr>
			<td>
				<script>
			
<%
	int [] items= {-1,-1,-1,-1,-1,-1,-1};
	int level=0;
	int parentId=0;
	int last=0;
	int leveladd=0;
	String urlOption="";
	Iterator it=menu.iterator();
	while(it.hasNext()) {
		MenuOptionData item = (MenuOptionData)it.next();
		parentId=item.getParentId().intValue();
		leveladd=Util.getlevel(items,parentId,last);
		last=last+leveladd;
		items[last]=item.getId().intValue();
		level=level+leveladd;
		urlOption=(String)item.getUrlOption();
		if(sys.isCorporation(CorporationData.BAYER_MX) && (sys.getUserSessionData().isGerente() || sys.getUserSessionData().isDriver())) {
%>			CEPAMenu.makeMenu('<%=Util.MENU_LEVELS[level]%>','<%=messages.getText((String)item.getLabelKey())%>','<%=(String)urlOption%>','_parent')
<%		}else {
			if((level==0)||urlOption.equals("#")){
%>			CEPAMenu.makeMenu('<%=Util.MENU_LEVELS[level]%>','<%=messages.getText((String)item.getLabelKey())%>','<%=(String)urlOption%>')
<%			}else{
%>			CEPAMenu.makeMenu('<%=Util.MENU_LEVELS[level]%>','<%=messages.getText((String)item.getLabelKey())%>','<%=(String)urlOption%>','_parent')
<%			}
		}	
	}
%>
	
		CEPAMenu.init()
				</script>
			</td>
		</tr>
	</table>
</body>
</html>
