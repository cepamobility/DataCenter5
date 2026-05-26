<%@page import="communication.security.SecurityConstants"%>
<%@page import="communication.fleet.ApmkData"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page language="java"	import="business.SystemAccess"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.LinkedList"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	ApmkData data = (ApmkData)userSessionData.getValue();

	if(userSessionData.checkPermission(SecurityConstants.QUERY)) {
		String[][] values = data.getHtmlValues();
		String lastDiv1 = "";
		String lastDiv2 = "";
		String lastDiv3 = "";
		int totalV = 0;
		int totalKMS = 0;
%>
	<head>
		<link rel="stylesheet" href="styles/base.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script src="js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript">
			var div2show = false;
			var div3show = false;
			var div4show = false;
			function showDiv2() {
				if(div2show) {
					if(div3show) {
						showDiv3();
					}
				 	$('.div2').hide();
				}else {
				 	$('.div2').show();
				 }
				 div2show = !div2show;
			}
			function showDiv3() {
				if(div3show) {
					if(div4show) {
						showDiv4();
					}
				 	$('.div3').hide();
				}else {
					if(!div2show) {
						showDiv2();
					}
				 	$('.div3').show();
				 }
				 div3show = !div3show;
			}
			function showDiv4() {
				if(div4show) {
				 	$('.line1').hide();
				 	$('.line2').hide();
				}else {
					if(!div3show) {
						showDiv3();
					}
				 	$('.line1').show();
				 	$('.line2').show();
				 }
				 div4show = !div4show;
			}
			function showHide(pre,className) {
				var expr = "div."+className+"[id^='"+pre+"']";
				if($(expr).is(":visible")) {
					expr = "div[id^='"+pre+"_']";
					$(expr).hide();
				}else {
					$(expr).show();
				}
			}
			function showLine(pre) {
				var expr = "div.line1[id^='"+pre+"']";
				if($(expr).is(":visible")) {
					expr = "div[id^='"+pre+"_']";
					$(expr).hide();
				}else {
					$(expr).show();
					var expr2 = "div.line2[id^='"+pre+"']";
					$(expr2).show();
				}
			}
		</script>
	</head>
	<div id="name">
		<h1 class="customer_logo" >Consulta de APMM por División</h1>
	</div>
	<br>
	<div>
		<div id="contenidos">
			<div id="columna" style="width: 500px; text-align: left;">
				<input type="button" class="button" value="Divisiones" onclick="showDiv2();" />
				<input type="button" class="button" value="Subdivisiones" onclick="showDiv3();" />
				<input type="button" class="button" value="Unidades" onclick="showDiv4();" />
			</div>
			<div id="columna" style="width: 500px; text-align: right;">
				<input type="button" class="button" value="PDF" />
				<input type="button" class="button" value="XLS" />
			</div>
		</div>
	</div>
	<br>
	<div id="tabla">
		<div id="contenidos" style="background: #FFFFFF">
			<div id="columnaFines" style="width: 300px; text-align: center;">
				Dato
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				Vehículos
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				Kms
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				Accidentes
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				APMK
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				%VIA
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				Incidentes
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				IMPK
			</div>
		</div>
<%		boolean line = true;
		String function = "";
		for(int i = 0; i < values.length; i++) {
			int len = values[i].length;
			String id = "div";
			if(len > 4) {
				id += "_"+values[i][0];
				if(len == 8) {
					function = "javascript:showLine('"+id+"');";
				}else {
					function = "javascript:showHide('"+id+"','div2');";
				}
				if(!values[i][0].equals(lastDiv1)) {
					lastDiv1 = values[i][0];
					lastDiv2 = "";
					lastDiv3 = "";
%>
		<div id='<%=id%>' class="div1">
			<div id="contenidos">
				<div id="columnaFines" style="width: 300px; text-align: center;">
					<a href="<%=function%>" style="color: #FFFFFF"><%=(values[i][1].startsWith("datatype.country")) ? messages.getText(values[i][1]) : values[i][1]%></a>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][3]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][2]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
			</div>
		</div>
<%				}
			}
			if(len > 8) {
				id += "_"+values[i][4];
				if(len == 8) {
					function = "javascript:showLine('"+id+"');";
				}else {
					function = "javascript:showHide('"+id+"','div3');";
				}
				if(!values[i][4].equals(lastDiv2)) {
					lastDiv2 = values[i][4];
					lastDiv3 = "";
%>		<div id='<%=id%>' class="div2">
			<div id="contenidos">
				<div id="columnaFines" style="width: 300px; text-align: center;">
					<a href="<%=function%>"><%=(values[i][5].startsWith("datatype.country")) ? messages.getText(values[i][5]) : values[i][5]%></a>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][7]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][6]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
			</div>
		</div>
<%				}
			}
			if(len > 12) {
				id += "_"+values[i][8];
				function = "javascript:showLine('"+id+"');";
				if(!values[i][8].equals(lastDiv3)) {
					lastDiv3 = values[i][8];
%>		<div id='<%=id%>' class="div3">
			<div id="contenidos">
				<div id="columnaFines" style="width: 300px; text-align: center;">
					<a href="<%=function%>"><%=(values[i][9].startsWith("datatype.country")) ? messages.getText(values[i][9]) : values[i][9]%></a>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][11]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][10]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
			</div>
		</div>
<%				}
			}
			id += "_"+values[i][len-4];
			totalV += Integer.parseInt(values[i][len-1]); 
			totalKMS += Integer.parseInt(values[i][len-2]); 
%>		<div id='<%=id%>' class='<%=(line) ? "line1" : "line2"%>'>
			<div id="contenidos">
				<div id="columnaFines" style="width: 300px; text-align: center;">
					<%=(values[i][len-3].startsWith("datatype.country")) ? messages.getText(values[i][len-3]) : values[i][len-3]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][len-1]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					<%=values[i][len-2]%>
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
				<div id="columnaFines" style="width: 80px; text-align: center;">
					0
				</div>
			</div>
		</div>
<%			line = !line;
		}
%>
		<div id="contenidos" class="totalReport">
			<div id="columnaFines" style="width: 300px; text-align: center;">
				Total
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				<%=String.valueOf(totalV)%>
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				<%=String.valueOf(totalKMS)%>
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				0
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				0
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				0
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				0
			</div>
			<div id="columnaFines" style="width: 80px; text-align: center;">
				0
			</div>
		</div>
<%	}
%>
