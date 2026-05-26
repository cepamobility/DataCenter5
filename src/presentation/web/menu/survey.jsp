<!doctype html>

<%@page import="java.util.Calendar"%>
<%@page import="communication.administration.corporation.tables.CorporationData"%>
<%@page import="communication.administration.corporation.tables.DivOrgData"%>
<%@page import="java.util.Date"%>
<%@page import="presentation.translator.web.util.Util"%>
<%@page import="communication.vehicle.tables.VehicleData"%>
<%@page import="java.util.Collections"%>
<%@page import="communication.activity.tables.ItemData"%>
<%@page import="communication.language.Text"%>
<%@page import="communication.activity.tables.PointData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="presentation.translator.web.util.*"%>
<%@page import="communication.activity.tables.CheckListData"%>
<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>

<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();

	
	String success = "surveySuccess.jsp";
	
	String version="1";//String.valueOf( new Date().getTime());
	if(sys.isCorporation(CorporationData.DTCP)) {
%>

<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=EDGE" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/apple-touch-icon-57x57-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/apple-touch-icon-72x72-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/apple-touch-icon-114x114-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/apple-touch-icon-144x144-precomposed.png" />
		<meta name="viewport" content="user-scalable=no, width=980" />
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>Survey</title>
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico">
		<link rel="stylesheet" href="styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="styles/base.css">
		<link rel="stylesheet" href="styles/checklist.css">
		<link rel="stylesheet" href="styles/jquery-ui-1.10.3.custom.min.css">
		<style>
			.novisible{
				display:none;
				visibility:hidden;
			}
		</style>	
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" >Survey</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>


		<section id="content">
			<section class="grid_container">
				<form id="assessment" name="assessment" class="checklist" action="resultSurvey.jsp"  data-module="<%=String.valueOf(CheckListData.SURVEY)%>" data-success-redirect="<%=success%>">
<% 
			CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(CheckListData.SURVEY),userSessionData);	
			Collection points = checkList.getPoints();
			Collections.sort((List)points);
			Iterator pointIt = points.iterator();
			Integer idVG=null;
			Integer countHeaders=0;
			while(pointIt.hasNext()) {
				PointData point = (PointData) pointIt.next();
				Iterator<ItemData> itemsIt = point.getSortItems();
				ItemData item = itemsIt.next();
				if(item.getType().intValue() > 2) {
%>						<fieldset class="title">
							<h5><%=messages.getText(point.getText()) %></h5>
						</fieldset>
<%					do {
						String divName = "qw"+String.valueOf(item.getId());
						String qName = "q"+String.valueOf(item.getId());
												
%>				
						<fieldset>
							<div id="<%=divName%>" data-type="1">
								<label for="<%=qName%>"><%=messages.getText(item.getText()) %></label>
								<label class="inline"><input id="<%=qName%>" name="<%=qName%>" value='' type="text"></label>
							</div>
						</fieldset>
<% 						if(itemsIt.hasNext()) { 
							item = itemsIt.next();
						}else {
							item = null;
						}
					} while (item != null);
				}else {
					String divName = "qw"+String.valueOf(item.getId());
					String qName = "q"+String.valueOf(item.getId());
					String comName = "q"+String.valueOf(item.getId())+"comment";
					
					if(item.getType().intValue() == 1) {
						if(countHeaders>0){
							
%>
						<fieldset tagTotalGroup="s" group="<%=countHeaders%>" style="background: #cccccc;">
								<label><strong style="font-size: 22px;float:left;padding-right:30px;"><%=messages.getText("generic.messages.total")%></strong></label>
							&nbsp;&nbsp;
								<label  class="radio"  style="font-size: 20px;">
									<%=messages.getText("generic.messages.yes")%>:<label id="yes<%=countHeaders%>"></label>
								</label>
							&nbsp;&nbsp;
								<label class="radio"   style="font-size: 20px;" >
									<%=messages.getText("generic.messages.partially")%>:<label id="partially<%=countHeaders%>"></label>
								</label>
							&nbsp;&nbsp;
								<label class="radio"   style="font-size: 20px;" >
									<%=messages.getText("generic.messages.no")%>:<label id="no<%=countHeaders%>"></label>
								</label>
						</fieldset>

<% 					
						}
						
%>					
					<fieldset class="title">
						<h5 ><%= messages.getText(point.getText()) %></h5>
					</fieldset>
<%					
					idVG=item.getId();
					countHeaders++;
					}
					String textQuestion = null;
					switch(item.getId().intValue()) {
						case 12380:
							textQuestion = "survey.p19.i1";
							break;
						case 12385:
							textQuestion = "survey.p24.i6";
							break;
						case 12393:
							textQuestion = "survey.p32.i14";
							break;
						case 12397:
							textQuestion = "survey.p36.i1";
							break;
						case 12401:
							textQuestion = "survey.p40.i5";
							break;
						case 12405:
							textQuestion = "survey.p44.i1";
							break;
						case 12409:
							textQuestion = "survey.p48.i5";
							break;
						case 12411:
							textQuestion = "survey.p50.i7";
							break;
						case 12415:
							textQuestion = "survey.p54.i1";
							break;
						case 12419:
							textQuestion = "survey.p58.i5";
							break;
						case 12422:
							textQuestion = "survey.p61.i8";
							break;
					}
					if(textQuestion != null) {
%>					<fieldset style="background: #dcdcdc;">
						<h3 class="title"><%=messages.getText(textQuestion) %></h3>
					</fieldset>
<%					}
%> 
					<fieldset>
						<div id="<%=divName%>">
							<label style="cursor:pointer;" for="<%=qName%>" ><%=messages.getText(item.getText()) %></label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="radio" style="float:left;">
								<input tagInput="s" tagGroup="<%=countHeaders%>" tagOption="yes"  type="radio" name="<%=qName%>" value="1" ><%=messages.getText("generic.messages.yes")%>
							</label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="radio" style="float:left;" >
								<input tagInput="s" tagGroup="<%=countHeaders%>" tagOption="partially" type="radio"  name="<%=qName%>" value="2"><%=messages.getText("generic.messages.partially")%>
							</label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="radio" style="float:left;" >
								<input  tagInput="s" tagGroup="<%=countHeaders%>" tagOption="no"   type="radio" name="<%=qName%>" value="3"><%=messages.getText("generic.messages.no")%>
							</label>
						</div>
					</fieldset>
<%				}
			}
			
			
%>
		<fieldset tagTotalGroup="s" group="<%=countHeaders%>" style="background: #cccccc;">
				<label><strong style="font-size: 22px;float:left;padding-right:30px;"><%=messages.getText("generic.messages.total")%></strong></label>
							&nbsp;&nbsp;
				<label  class="radio"  style="font-size: 20px;">
					<%=messages.getText("generic.messages.yes")%>:<label id="yes<%=countHeaders%>"></label>
				</label>
							&nbsp;&nbsp;
				<label class="radio"   style="font-size: 20px;" >
					<%=messages.getText("generic.messages.partially")%>:<label id="partially<%=countHeaders%>"></label>
				</label>
							&nbsp;&nbsp;
				<label class="radio"   style="font-size: 20px;" >
					<%=messages.getText("generic.messages.no")%>:<label id="no<%=countHeaders%>"></label>
				</label>
		</fieldset>

<% 					

%> 
					<fieldset class="title">
						<h5><%=messages.getText("generic.messages.observations") %></h5>
					</fieldset>
					<fieldset>
						<div id="qw2" data-type="11">
							<textarea id="q2" name="q2" cols="50" rows="4">.</textarea>
						</div>
					</fieldset>
					<fieldset style="background: #cccccc;">
							<label><strong style="font-size: 22px;float:left;padding-right:30px;"><%=messages.getText("generic.messages.points")%></strong></label>
							&nbsp;&nbsp;
							<label  class="radio"  style="font-size: 20px;">
								<%=messages.getText("generic.messages.yes")%>:<label id="tyes"></label>
							</label>
							&nbsp;&nbsp;
							<label class="radio"   style="font-size: 20px;" >
								<%=messages.getText("generic.messages.partially")%>:<label id="tpartially"></label>
							</label>
							&nbsp;&nbsp;
							<label class="radio"   style="font-size: 20px;" >
								<%=messages.getText("generic.messages.no")%>:<label id="tno"></label>
							</label>
							<br>
							&nbsp;&nbsp;
							<label class="radio"   style="font-size: 20px;" >
								<label id="tpercentage"></label>
							</label>
							
					</fieldset>
					<fieldset>
						<input type="submit"  style="float:left;">
						<label><%=messages.getText("survey.submit.message") %></label>
					</fieldset>
				</form>
			</section>
		</section>
		<style type="text/css">
			.error_text{
				color:red;
				position: absolute;
				top: 0px;
				left: 0px;
				height: 95%;
				width:5px;
				background-image: url("menu/imgs/barra.png");
    			background-repeat: repeat-y;
    			MARGIN-TOP: 5px;
			} 
			.error_ok{
				color:red;
				position: absolute;
				top: 0px;
				left: 0px;
				height: 95%;
				width:5px;
				background-image: url("menu/imgs/barraok.png");
    			background-repeat: repeat-y;
    			MARGIN-TOP: 5px;
			} 
		</style>
		
		<script type="text/javascript">
			var corp="survey";
		</script>
		<script type="text/javascript" src="scripts/json2.min.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery-2.0.2.min.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery-ui-1.10.3.custom.min.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-pt.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.ui.datepicker-es.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.loggable.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessmentController.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.question.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.card.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/jquery.tadeouy.assessment.js?<%=version%>"></script>
		<script type="text/javascript" src="scripts/assessment.js?<%=version%>"></script>
		<script type="text/javascript">
			session();
		   
		   	function session(){
		    	setTimeout(checkSession,1000*60*10);
		   	}
		   
		   	function checkSession(){
		    	$.ajax({async:true,url:'/datacenter/index2.jsp',success:function(){session();}});
		   	}
			$(document).ready(
					function(){

						    $('input[tagInput="s"]').each(
								function(){

									$(this).change(calcGroup($(this).attr('tagGroup')));
								}
							);

							function calcGroup(group){
								return function(){
									var totalYes=0;
									var totalPartially=0;
									var totalNo=0;
									$('input[tagInput="s"][tagGroup="'+group+'"]').each(
										function(){
											if($(this).is(':checked')){
												if($(this).attr('tagOption')=='yes'){
													totalYes++;	
												}
												if($(this).attr('tagOption')=='partially'){
													totalPartially++;	
												}
												if($(this).attr('tagOption')=='no'){
													totalNo++;	
												}
											}
										}
									);

									$('#yes'+group).html(totalYes);
									$('#partially'+group).html(totalPartially);
									$('#no'+group).html(totalNo);

									totalCalc();
									
									
										
								};
							}

							function totalCalc(){
								var totalYes=0;
								var totalPartially=0;
								var totalNo=0;
								
								$('input[tagInput="s"]').each(
										function(){
											if($(this).is(':checked')){
												if($(this).attr('tagOption')=='yes'){
													totalYes++;	
												}
												if($(this).attr('tagOption')=='partially'){
													totalPartially++;	
												}
												if($(this).attr('tagOption')=='no'){
													totalNo++;	
												}
											}
										}
									);
								var totalCount=totalYes+totalPartially+totalNo;
								$('#tyes').html(totalYes);
								$('#tpartially').html(format(totalPartially*0.25));
								$('#tno').html("0");
								var total=((totalYes+totalPartially*0.25)/63)*100;
								
								$('#tpercentage').html(format(total)+" %");
									 
							}

							totalCalc();

							$('fieldset[tagTotalGroup="s"]').each(
								function(){
									calcGroup($(this).attr('group'))();
								}
							); 
					}
			);

			window['format'] = function(v){
				var m="###,###,###.##";
			    if (!m || isNaN(+v)) {
			        return v; //return as it is.
			    }
			    
			    if(v==0 || v=='0'){
			    	return v;
			    }
			    
			    if(v>0 && v < 1){
			    	v=(v+"").replace(",",".");
			    	if(v.indexOf(".")+2<v.length){
			    		v=v.substring(0,v.indexOf(".")+3);	
			    	}
			    	return v;
			    }
			    //convert any string to number according to formation sign.
			    var v = m.charAt(0) == '-'? -v: +v;
			    var isNegative = v<0? v= -v: 0; //process only abs(), and turn on flag.
			    
			    //search for separator for grp & decimal, anything not digit, not +/- sign, not #.
			    var result = m.match(/[^\d\-\+#]/g);
			    var Decimal = (result && result[result.length-1]) || '.'; //treat the right most symbol as decimal 
			    var Group = (result && result[1] && result[0]) || ',';  //treat the left most symbol as group separator
			    
			    //split the decimal for the format string if any.
			    var m = m.split( Decimal);
			    //Fix the decimal first, toFixed will auto fill trailing zero.
			    v = v.toFixed( m[1] && m[1].length);
			    v = +(v) + ''; //convert number to string to trim off *all* trailing decimal zero(es)

			    //fill back any trailing zero according to format
			    var pos_trail_zero = m[1] && m[1].lastIndexOf('0'); //look for last zero in format
			    var part = v.split('.');
			    //integer will get !part[1]
			    if (!part[1] || part[1] && part[1].length <= pos_trail_zero) {
			        v = (+v).toFixed( pos_trail_zero+1);
			    }
			    var szSep = m[0].split( Group); //look for separator
			    m[0] = szSep.join(''); //join back without separator for counting the pos of any leading 0.

			    var pos_lead_zero = m[0] && m[0].indexOf('0');
			    if (pos_lead_zero > -1 ) {
			        while (part[0].length < (m[0].length - pos_lead_zero)) {
			            part[0] = '0' + part[0];
			        }
			    }
			    else if (+part[0] == 0){
			        part[0] = '';
			    }
			    
			    v = v.split('.');
			    v[0] = part[0];
			    
			    //process the first group separator from decimal (.) only, the rest ignore.
			    //get the length of the last slice of split result.
			    var pos_separator = ( szSep[1] && szSep[ szSep.length-1].length);
			    if (pos_separator) {
			        var integer = v[0];
			        var str = '';
			        var offset = integer.length % pos_separator;
			        for (var i=0, l=integer.length; i<l; i++) { 
			            
			            str += integer.charAt(i); //ie6 only support charAt for sz.
			            //-pos_separator so that won't trail separator on full length
			            if (!((i-offset+1)%pos_separator) && i<l-pos_separator ) {
			                str += Group;
			            }
			        }
			        v[0] = str;
			    }

			    v[1] = (m[1] && v[1])? Decimal+v[1] : "";
			    return (isNegative?'-':'') + v[0] + v[1]; //put back any negation and combine integer and fraction.
			};
		</script>
	</body>
</html>
<%	}
%>