<%
	String day = request.getParameter("day");
	String month = request.getParameter("month");
	String year = request.getParameter("year");
	String index = request.getParameter("index");
	String formName = request.getParameter("form");
	
	String link = "checkLength('"+day+"','"+month+"')";
	String link2 = "checkLength('"+month+"','"+year+"')";
%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>

<script language="javascript" type='text/javascript' >


	function <%="onSelect"+index%>(calendar, date) {
		var date2 = calendar.date;
		var time = date2.getTime();
  		var input_day = document.getElementById('<%="dayId"+index%>');
  		var input_month = document.getElementById('<%="monthId"+index%>');
  		var input_year = document.getElementById('<%="yearId"+index%>');
  		var date3 = new Date(time);
  		input_day.value = date3.print("%d");
  		input_month.value = date3.print("%m");
  		input_year.value= parseInt(date3.print("%Y"),10);
  	}

	function checkLength(first,second) {
		var form = document.forms['<%=formName%>'];
		var firstElement = form.elements[first]; 
		var secondElement = form.elements[second];
		if(firstElement.value.length == 2) {
			secondElement.focus();
		}
	}
	
</script>
<%		if(day != null) {
%>
									<html:text property='<%=day%>' style='width:20;' styleClass='input' styleId='<%="dayId"+index%>'  onkeyup="<%=link%>"/>&nbsp;/&nbsp;
<%		}else {
%>

									<html:hidden property='<%=day%>'  styleId='<%="dayId"+index%>' />
<%		}
%>
		            				<html:text property='<%=month%>' style='width:20;' styleClass='input' styleId='<%="monthId"+index%>'  onkeyup="<%=link2%>"/>&nbsp;/&nbsp;
		            				<html:text property='<%=year%>' style='width:40;' styleClass='input' styleId='<%="yearId"+index%>'  />
		            				<html:img page='/component/jscalendar-1.0/img.gif' styleId='<%="calendar_button"+index%>' style='cursor: pointer; border: 1px solid red;'
     										onmouseover="this.style.background='red';" style='width:20;' align='middle' onmouseout="this.style.background=''"/>
			          			<script type="text/javascript">
									Calendar.setup({
    									inputArea     :    '<%="yearId"+index%>',
    									ifFormat       :    '%d/%m/%Y',
    									showsTime      :    true,
    									timeFormat     :    '24',
    									button         :    '<%="calendar_button"+index%>',
    									step           :    1,
    									onSelect       :    <%="onSelect"+index%>,
    									singleClick    :    true
 									});
								</script>

