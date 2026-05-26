<%@page language="java"
	errorPage="../exception.jsp"
	import="java.util.*"
	import="presentation.translator.web.util.*"
%>

<script type="text/javascript" src='<%= request.getContextPath() %>/util/js/SetCase.js'>
</script>

<script type="text/javascript">
	
	$(window).load(function(){
		$(function() {
			/*Toma el styleClass del input que viene de la jsp 
			  dentro del styleClass va txtUpper o  cualquiera de las acciones que necesite
			*/
		  $(".txtUpper").Setcase({caseValue: 'upper'});//Convierte a mayuscula toda la cadena de String 
		  $(".txtLower").Setcase({caseValue: 'lower'});//Convierte a minuscula toda la cadena de String 
		  $(".txtTitle").Setcase({caseValue: 'title'});//Convierte a mayuscula la primer letra de la primer palabra 
		  $(".txtPascal").Setcase({caseValue: 'pascal'});//Convierte a mayuscula la primer letra de cada palabra 
		});
	});

</script>