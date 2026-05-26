// JavaScript Document

function openDivOrg(id) {
	window.open('./user/divOrgFilters.jsp?id='+id+'&level=1','', 'toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,width=350,height=240');	
}
function openDivGeo() {
	window.open('./user/divGeoFilters.jsp?level=1','', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,width=350,height=240');	
}
function openChangePassword(width,height) {
	window.open('./changePassword.jsp?level=1','', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,width='+width+',height='+height);	
}
function openChangeLanguage() {
	window.open('./user/changeLanguage.jsp?level=1','', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,width=350,height=240');	
}
function openChangeCorporation() {
	window.open('./user/changeCorporation.jsp?level=1','', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,width=350,height=240');	
}
