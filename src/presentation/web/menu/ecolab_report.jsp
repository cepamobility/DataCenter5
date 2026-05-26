<%@page import="communication.driver.tables.TelematicControlData"%>
<%@page import="communication.datatype.tables.EcolabReportData"%>
<%@page import="communication.security.SecurityConstants"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page language="java"	import="business.SystemAccess"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.LinkedList"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<html>

<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	String dpto = request.getParameter("dpto");
	String month = request.getParameter("month");
	String year = request.getParameter("year");

	if(userSessionData.isGerente() || userSessionData.isAdministrator()) {
		EcolabReportData reportData = sys.getDriverReportFacade().getEcolabReportData(new Integer(month), new Integer(year), new Integer(dpto), null,userSessionData);
		Iterator<TelematicControlData> itSpeeds = reportData.getSpeeds().iterator();		
%>
<html>
  <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1.1", {packages:["table"]});
      google.setOnLoadCallback(drawTable);

      function drawTable() {
        var data = new google.visualization.DataTable();
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Motorista');
        data.addColumn('string', 'Placa');
        data.addColumn('date', 'Data');
        data.addColumn('timeofday', 'Hora');
        data.addColumn('number', 'Localização');
        data.addColumn('number', 'Velocidade');
        data.addRows(<%=String.valueOf(reportData.getNigths().size())%>);
<%		
		int index = 0;
		while(itSpeeds.hasNext()) {
%>			<%=itSpeeds.next().getEcolabLine(index,1)%>
<%				index++;
		}
%>

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
      }
    </script>
  </head>
  <body>
    <div id="table_div"></div>
  </body>
</html>

<%		}
%>
</html>