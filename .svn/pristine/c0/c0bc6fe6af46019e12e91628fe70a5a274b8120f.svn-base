<%@page language="java"
	errorPage="../exception.jsp"
	import="business.*"
 	import="communication.util.*" 	
 	import="communication.administration.bookmark.tables.*"
 	import="presentation.translator.web.driver.*"
 	import="presentation.translator.web.event.*"
 	import="presentation.translator.web.queries.*"
 	import="presentation.translator.web.vehicle.*"
 	import="presentation.translator.web.activity.schedule.*"
 	import="presentation.report.*"
 	import="org.jfree.report.JFreeReport"
	import="org.jfree.report.JFreeReportBoot"

%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	if(sys != null) {
	    BookmarkData bookmark = null;
	    if(request.getParameter("id") == null) {
	        bookmark = (BookmarkData)session.getAttribute("Bookmark");
	    }else {
	    	bookmark = sys.getUserReportFacade().getBookmarkData(new Integer(request.getParameter("id")),sys.getUserSessionData());
	    }
	    if(bookmark != null) {
	        JFreeReport report = null;
	        switch(bookmark.getAction()) {
	            case BookmarkData.DRIVER_REPORT:
	                report = new DriverReportAction().getReport(sys,new DriverReportForm(bookmark),1);
	                break;
	            case BookmarkData.FOSA_DRIVER_REPORT:
	                report = new FOSADriverReportAction().getReport(sys,new FOSADriverReportForm(bookmark),1);
	                break;
	            case BookmarkData.DRIVER_LICENSE_EXPIRY:
	                report = new DriverLicenseExpiryAction().getReport(sys,new LicenseExpiryForm(bookmark),1);
	                break;
	            case BookmarkData.HRD_REPORT:
	                report = new DriverHRDQueryAction().getReport(sys,new DriverHRDQueryForm(bookmark),1);
	                break;
	            case BookmarkData.PHRD_REPORT:
	                report = new DriverPHRDQueryAction().getReport(sys,new DriverPHRDQueryForm(bookmark),1);
	                break;
	            case BookmarkData.HRD_MONSANTO_REPORT:
	                report = new DriverHRDMonQueryAction().getReport(sys,new DriverHRDMonQueryForm(bookmark),1);
	                break;
	            case BookmarkData.HISTORY_REPORT:
	                report = new DriverHistoryQueryAction().getReport(sys,new DriverHistoryQueryForm(bookmark),1);
	                break;
	            case BookmarkData.EVENT_REPORT:
	                report = new EventReportAction().getReport(sys,new EventReportForm(bookmark),session,1);
	                break;
	            case BookmarkData.EVENT_TYPE_REPORT:
	                report = new ReportAccidentTypeAction().getReport(sys,new ReportAccidentTypeAllForm(bookmark));
	                break;
	            case BookmarkData.EVENT_BYPERIOD_REPORT:
	                report = new EventReportByPeriodAction().getReport(sys,new EventReportForm(bookmark));
	                break;
	            case BookmarkData.EVENT_COUNT_REPORT:
	                report = new ReportAccidentCountAction().getReport(sys,new ReportAccidentCountForm(bookmark));
	                break;
	            case BookmarkData.VEHICLE_REPORT:
	                report = new VehicleReportAction().getReport(sys,new VehicleReportForm(bookmark),1);
	                break;
	            case BookmarkData.KM_BYDIVISION_REPORT:
	                report = new ReportAPMMAction().getReport(sys,new ReportAPMMFormJJ(bookmark));
	                break;
	            case BookmarkData.ODOMETER_LOG_REPORT:
	                report = new ReportOdometerLogAction().getReport(sys,new ReportOdometerLogForm(bookmark));
	                break;
	            case BookmarkData.CONSUMED_FUEL_REPORT:
	                report = new ReportConsumedFuelAction().getReport(sys,new ReportConsumedFuelForm(bookmark));
	                break;
	            case BookmarkData.KM_LTR_CONSUMED_REPORT:
	                report = new ReportKmLtrAction().getReport(sys,new ReportConsumedFuelForm(bookmark));
	                break;
	            case BookmarkData.MILEAGE_REPORT:
	                report = new ReportMileageAction().getReport(sys,new ReportMileageForm(bookmark));
	                break;
	            case BookmarkData.SUCTA_MTOP_REPORT:
	                report = new SuctaMTOPQueryAction().getReport(sys,new SuctaMTOPQueryForm(bookmark),1);
	                break;
	            case BookmarkData.CHECK_LIST_REPORT:
	                report = new ReportCheckListAction().getReport(sys,new ReportCheckListForm(bookmark),1);
	                break;
	            case BookmarkData.TRAILERS_WO_CHECKLIST_REPORT:
	                report = new TrailerWOCheckListAction().getReport(sys,new TrailersWOCheckListForm(bookmark),1,session);
	                break;
	            case BookmarkData.VEHICLE_PURSUIT_REPORT:
	                report = new ReportVehiclePursuitRouteAction().getReport(sys,new ReportVehiclePursuitRouteForm(bookmark));
	                break;
	            case BookmarkData.VEHICLE_WO_PURSUIT_REPORT:
	                report = new VehicleWithoutPursuitRouteAction().getReport(sys,new VehicleWithoutPursuitRouteForm(bookmark),1);
	                break;
	            case BookmarkData.ACTIVITY_GENERAL_REPORT:
	                report = new ActivityGeneralReportAction().getReport(sys,new ActivityGeneralReportForm(bookmark),1);
	                break;
	            case BookmarkData.ACTIVITY_DELIVERED_REPORT:
	                report = new ActivityDeliveredAction().getReport(sys,new ActivityExpiryForm(bookmark),1);
	                break;
	            case BookmarkData.ACTIVITY_EXPIRY_REPORT:
	                report = new ActivityExpiryAction().getReport(sys,new ActivityExpiryForm(bookmark),1, session);
	                break;
	            case BookmarkData.DRIVERS_WO_BTW_REPORT:
	                report = new DriversWithoutBTWAction().getReport(sys,new DriversWithoutBTWForm(bookmark),1);
	                break;
	            case BookmarkData.DRIVER_WO_EC:
	                report = new DriverWithOutECAction().getReport(sys,new DriverWithOutECForm(bookmark),1);
	                break;
	            case BookmarkData.DRIVERS_BTW_ASSISTANCE_REPORT:
	                report = new DriversBTWAssistAction().getReport(sys,new DriversBTWAssistForm(bookmark),1);
	                break;
	            case BookmarkData.COMMENTARY_DRIVE_LOG_REPORT:
	                report = new ReportCommentaryDriveLogAction().getReport(sys,new ReportCommentaryDriveLogForm(bookmark));
	                break;
	            case BookmarkData.COMMENTARY_DRIVE_REPORT:
	                report = new ReportCommentaryDriveAction().getReport(sys,new ReportCommentaryDriveForm(bookmark),1);
	                break;
	            case BookmarkData.COMMENTARY_DRIVE_REPORT_2:
	                report = new CommentaryDriveReportAction().getReport(sys,new CommentaryDriveReportForm(bookmark));
	                break;
	            case BookmarkData.APMM_BYDIVISION_REPORT:
	                report = new ReportAPMMByDivisionAction().getReport(sys,new ReportAPMMFormJJ(bookmark));
	                break;
	            case BookmarkData.APMM_BYPERIOD_REPORT:
	                report = new ReportAPMMByPeriodAction().getReport(sys,new ReportAPMMByPeriodFormJJ(bookmark));
	                break;
	            case BookmarkData.KM_BYTRADE_REPORT:
	                report = new KmByTradeAction().getReport(sys,new KmByTradeForm(bookmark));
	                break;
	            case BookmarkData.MISSING_ODOMETER_REPORT:
	                report = new ReportMissingOdometerAction().getReport(sys,new ReportMissingOdometerForm(bookmark));
	                break;
	            case BookmarkData.UNCONFORMITIES_REPORT:
	                report = new presentation.translator.web.vehicle.ReportUnconformityAction().getReport(sys,new presentation.translator.web.vehicle.ReportUnconformityForm(bookmark));
	                break;
	        }
	        if(report != null) {
                JFreeReportBoot.getInstance().start();
		        byte[] data = new ReportUtil().getHtml(report);
		        if(data != null) {
		        	for(int i = 0; i < data.length; i++) {
		        	    response.getWriter().write(data[i]);
		        	} 
		        }
	        }
	    }
	}
%>
