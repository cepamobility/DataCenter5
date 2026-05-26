package presentation.translator.web.travel;


import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.travel.tables.CheckPointData;
import communication.travel.tables.TravelDataType;

/**
 * @author jrodriguez
 */
public class TravelCheckPointForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String travel;
    private String stops;
    
    private String date;
	private String month;
	private String year;

    private String hour;  
    private String minute;
    
    private String way;
    private String monitor;

    private String comment;
   	
    private String id1;
    private String date1;
	private String month1;
	private String year1;

    private String hour1;  
    private String minute1;
    
    private String local1;
    private String way1;
    private String communication1;
    private String result1;
    
    private String comment1;

    private String id2;
    private String date2;
	private String month2;
	private String year2;

    private String hour2;  
    private String minute2;
    
    private String local2;
    private String way2;
    private String communication2;
    private String result2;
    
    private String comment2;
    
    private String id3;
    private String date3;
	private String month3;
	private String year3;

    private String hour3;  
    private String minute3;
    
    private String local3;
    private String way3;
    private String communication3;
    private String result3;
    
    private String comment3;
    
    private String id4;
    private String date4;
	private String month4;
	private String year4;

    private String hour4;  
    private String minute4;
    
    private String local4;
    private String way4;
    private String communication4;
    private String result4;
    
    private String comment4;
    
    private String id5;
    private String date5;
	private String month5;
	private String year5;

    private String hour5;  
    private String minute5;
    
    private String local5;
    private String way5;
    private String communication5;
    private String result5;
    
    private String comment5;
    
    private String id6;
    private String date6;
	private String month6;
	private String year6;

    private String hour6;  
    private String minute6;
    
    private String local6;
    private String way6;
    private String communication6;
    private String result6;
    
    private String comment6;
    
    private String id7;
    private String date7;
	private String month7;
	private String year7;

    private String hour7;  
    private String minute7;
    
    private String local7;
    private String way7;
    private String communication7;
    private String result7;
    
    private String comment7;
    
    private String id8;
    private String date8;
	private String month8;
	private String year8;

    private String hour8;  
    private String minute8;
    
    private String local8;
    private String way8;
    private String communication8;
    private String result8;
    
    private String comment8;
    
    private String id9;
    private String date9;
	private String month9;
	private String year9;

    private String hour9;  
    private String minute9;
    
    private String local9;
    private String way9;
    private String communication9;
    private String result9;
    
    private String comment9;
    
    private String id10;
    private String date10;
	private String month10;
	private String year10;

    private String hour10;  
    private String minute10;
    
    private String local10;
    private String way10;
    private String communication10;
    private String result10;
    
    private String comment10;
    
    private String id11;
    private String date11;
	private String month11;
	private String year11;

    private String hour11;  
    private String minute11;
    
    private String local11;
    private String way11;
    private String communication11;
    private String result11;
    
    private String comment11;

    private String id12;
    private String date12;
	private String month12;
	private String year12;

    private String hour12;  
    private String minute12;
    
    private String local12;
    private String way12;
    private String communication12;
    private String result12;
    
    private String comment12;

    private String id13;
    private String date13;
	private String month13;
	private String year13;

    private String hour13;  
    private String minute13;
    
    private String local13;
    private String way13;
    private String communication13;
    private String result13;
    
    private String comment13;

    private String id14;
    private String date14;
	private String month14;
	private String year14;

    private String hour14;  
    private String minute14;
    
    private String local14;
    private String way14;
    private String communication14;
    private String result14;
    
    private String comment14;

    private String id15;
    private String date15;
	private String month15;
	private String year15;

    private String hour15;  
    private String minute15;
    
    private String local15;
    private String way15;
    private String communication15;
    private String result15;
    
    private String comment15;

    public TravelCheckPointForm() {
    }

    public TravelCheckPointForm(TravelDataType travelData, Collection checks) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(travelData.getConfirmedLeave());
        date = String.valueOf(c.get(Calendar.DATE));
    	month = String.valueOf(c.get(Calendar.MONTH)+1);
    	year = String.valueOf(c.get(Calendar.YEAR));

    	c.setTime(travelData.getConfirmedLeaveHour());
        hour = String.valueOf(c.get(Calendar.HOUR));  
        minute = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        
        way = travelData.getCommunicationLeave();
        monitor = travelData.getMonitor();

        comment = travelData.getLeaveComment();
        
        Iterator it = checks.iterator();
        while(it.hasNext()) {
        	CheckPointData checkPoint = (CheckPointData) it.next();
        	switch(checkPoint.getNumber().intValue()) {
        		case 1:
        		    id1 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date1 = String.valueOf(c.get(Calendar.DATE));
        	    	month1 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year1 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour1 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute1 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local1 = checkPoint.getLocal();
        		    way1 = checkPoint.getWay();
        		    communication1 = checkPoint.getCommunication();
        		    result1 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment1 = checkPoint.getComment();
        			break;
        		case 2:
        		    id2 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date2 = String.valueOf(c.get(Calendar.DATE));
        	    	month2 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year2 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour2 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute2 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local2 = checkPoint.getLocal();
        		    way2 = checkPoint.getWay();
        		    communication2 = checkPoint.getCommunication();
        		    result2 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment2 = checkPoint.getComment();
        			break;
        		case 3:
        		    id3 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date3 = String.valueOf(c.get(Calendar.DATE));
        	    	month3 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year3 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour3 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute3 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local3 = checkPoint.getLocal();
        		    way3 = checkPoint.getWay();
        		    communication3 = checkPoint.getCommunication();
        		    result3 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment3 = checkPoint.getComment();
        			break;
        		case 4:
        		    id4 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date4 = String.valueOf(c.get(Calendar.DATE));
        	    	month4 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year4 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour4 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute4 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local4 = checkPoint.getLocal();
        		    way4 = checkPoint.getWay();
        		    communication4 = checkPoint.getCommunication();
        		    result4 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment4 = checkPoint.getComment();
        			break;
        		case 5:
        		    id5 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date5 = String.valueOf(c.get(Calendar.DATE));
        	    	month5 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year5 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour5 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute5 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local5 = checkPoint.getLocal();
        		    way5 = checkPoint.getWay();
        		    communication5 = checkPoint.getCommunication();
        		    result5 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment5 = checkPoint.getComment();
        			break;
        		case 6:
        		    id6 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date6 = String.valueOf(c.get(Calendar.DATE));
        	    	month6 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year6 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour6 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute6 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local6 = checkPoint.getLocal();
        		    way6 = checkPoint.getWay();
        		    communication6 = checkPoint.getCommunication();
        		    result6 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment6 = checkPoint.getComment();
        			break;
        		case 7:
        		    id7 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date7 = String.valueOf(c.get(Calendar.DATE));
        	    	month7 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year7 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour7 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute7 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local7 = checkPoint.getLocal();
        		    way7 = checkPoint.getWay();
        		    communication7 = checkPoint.getCommunication();
        		    result7 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment7 = checkPoint.getComment();
        			break;
        		case 8:
        		    id8 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date8 = String.valueOf(c.get(Calendar.DATE));
        	    	month8 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year8 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour8 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute8 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local8 = checkPoint.getLocal();
        		    way8 = checkPoint.getWay();
        		    communication8 = checkPoint.getCommunication();
        		    result8 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment8 = checkPoint.getComment();
        			break;
        		case 9:
        		    id9 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date9 = String.valueOf(c.get(Calendar.DATE));
        	    	month9 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year9 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour9 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute9 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local9 = checkPoint.getLocal();
        		    way9 = checkPoint.getWay();
        		    communication9 = checkPoint.getCommunication();
        		    result9 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment9 = checkPoint.getComment();
        			break;
        		case 10:
        		    id10 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date10 = String.valueOf(c.get(Calendar.DATE));
        	    	month10 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year10 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour10 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute10 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local10 = checkPoint.getLocal();
        		    way10 = checkPoint.getWay();
        		    communication10 = checkPoint.getCommunication();
        		    result10 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment10 = checkPoint.getComment();
        			break;
        		case 11:
        		    id11 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date11 = String.valueOf(c.get(Calendar.DATE));
        	    	month11 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year11 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour11 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute11 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local11 = checkPoint.getLocal();
        		    way11 = checkPoint.getWay();
        		    communication11 = checkPoint.getCommunication();
        		    result11 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment11 = checkPoint.getComment();
        			break;
        		case 12:
        		    id12 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date12 = String.valueOf(c.get(Calendar.DATE));
        	    	month12 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year12 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour12 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute12 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local12 = checkPoint.getLocal();
        		    way12 = checkPoint.getWay();
        		    communication12 = checkPoint.getCommunication();
        		    result12 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment12 = checkPoint.getComment();
        			break;
        		case 13:
        		    id13 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date13 = String.valueOf(c.get(Calendar.DATE));
        	    	month13 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year13 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour13 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute13 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local13 = checkPoint.getLocal();
        		    way13 = checkPoint.getWay();
        		    communication13 = checkPoint.getCommunication();
        		    result13 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment13 = checkPoint.getComment();
        			break;
        		case 14:
        		    id14 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date14 = String.valueOf(c.get(Calendar.DATE));
        	    	month14 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year14 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour14 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute14 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local14 = checkPoint.getLocal();
        		    way14 = checkPoint.getWay();
        		    communication14 = checkPoint.getCommunication();
        		    result14 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment14 = checkPoint.getComment();
        			break;
        		case 15:
        		    id15 = String.valueOf(checkPoint.getId());
        	    	c.setTime(checkPoint.getDate());
        	        date15 = String.valueOf(c.get(Calendar.DATE));
        	    	month15 = String.valueOf(c.get(Calendar.MONTH)+1);
        	    	year15 = String.valueOf(c.get(Calendar.YEAR));
        	    	c.setTime(checkPoint.getHour());
        	        hour15 = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        	        minute15 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        		    local15 = checkPoint.getLocal();
        		    way15 = checkPoint.getWay();
        		    communication15 = checkPoint.getCommunication();
        		    result15 = (checkPoint.isIncidents()) ? "2" : "1";
        		    comment15 = checkPoint.getComment();
        			break;
        	}
        }
    }

	public String getTravel() {
		return travel;
	}

	public void setTravel(String travel) {
		this.travel = travel;
	}

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getHour1() {
		return hour1;
	}

	public void setHour1(String hour1) {
		this.hour1 = hour1;
	}

	public String getMinute1() {
		return minute1;
	}

	public void setMinute1(String minute1) {
		this.minute1 = minute1;
	}

	public String getLocal1() {
		return local1;
	}

	public void setLocal1(String local1) {
		this.local1 = local1;
	}

	public String getWay1() {
		return way1;
	}

	public void setWay1(String way1) {
		this.way1 = way1;
	}

	public String getCommunication1() {
		return communication1;
	}

	public void setCommunication1(String communication1) {
		this.communication1 = communication1;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public String getHour2() {
		return hour2;
	}

	public void setHour2(String hour2) {
		this.hour2 = hour2;
	}

	public String getMinute2() {
		return minute2;
	}

	public void setMinute2(String minute2) {
		this.minute2 = minute2;
	}

	public String getLocal2() {
		return local2;
	}

	public void setLocal2(String local2) {
		this.local2 = local2;
	}

	public String getWay2() {
		return way2;
	}

	public void setWay2(String way2) {
		this.way2 = way2;
	}

	public String getCommunication2() {
		return communication2;
	}

	public void setCommunication2(String communication2) {
		this.communication2 = communication2;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	public String getDate3() {
		return date3;
	}

	public void setDate3(String date3) {
		this.date3 = date3;
	}

	public String getMonth3() {
		return month3;
	}

	public void setMonth3(String month3) {
		this.month3 = month3;
	}

	public String getYear3() {
		return year3;
	}

	public void setYear3(String year3) {
		this.year3 = year3;
	}

	public String getHour3() {
		return hour3;
	}

	public void setHour3(String hour3) {
		this.hour3 = hour3;
	}

	public String getMinute3() {
		return minute3;
	}

	public void setMinute3(String minute3) {
		this.minute3 = minute3;
	}

	public String getLocal3() {
		return local3;
	}

	public void setLocal3(String local3) {
		this.local3 = local3;
	}

	public String getWay3() {
		return way3;
	}

	public void setWay3(String way3) {
		this.way3 = way3;
	}

	public String getCommunication3() {
		return communication3;
	}

	public void setCommunication3(String communication3) {
		this.communication3 = communication3;
	}

	public String getComment3() {
		return comment3;
	}

	public void setComment3(String comment3) {
		this.comment3 = comment3;
	}

	public String getDate4() {
		return date4;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	public String getMonth4() {
		return month4;
	}

	public void setMonth4(String month4) {
		this.month4 = month4;
	}

	public String getYear4() {
		return year4;
	}

	public void setYear4(String year4) {
		this.year4 = year4;
	}

	public String getHour4() {
		return hour4;
	}

	public void setHour4(String hour4) {
		this.hour4 = hour4;
	}

	public String getMinute4() {
		return minute4;
	}

	public void setMinute4(String minute4) {
		this.minute4 = minute4;
	}

	public String getLocal4() {
		return local4;
	}

	public void setLocal4(String local4) {
		this.local4 = local4;
	}

	public String getWay4() {
		return way4;
	}

	public void setWay4(String way4) {
		this.way4 = way4;
	}

	public String getCommunication4() {
		return communication4;
	}

	public void setCommunication4(String communication4) {
		this.communication4 = communication4;
	}

	public String getComment4() {
		return comment4;
	}

	public void setComment4(String comment4) {
		this.comment4 = comment4;
	}

	public String getDate5() {
		return date5;
	}

	public void setDate5(String date5) {
		this.date5 = date5;
	}

	public String getMonth5() {
		return month5;
	}

	public void setMonth5(String month5) {
		this.month5 = month5;
	}

	public String getYear5() {
		return year5;
	}

	public void setYear5(String year5) {
		this.year5 = year5;
	}

	public String getHour5() {
		return hour5;
	}

	public void setHour5(String hour5) {
		this.hour5 = hour5;
	}

	public String getMinute5() {
		return minute5;
	}

	public void setMinute5(String minute5) {
		this.minute5 = minute5;
	}

	public String getLocal5() {
		return local5;
	}

	public void setLocal5(String local5) {
		this.local5 = local5;
	}

	public String getWay5() {
		return way5;
	}

	public void setWay5(String way5) {
		this.way5 = way5;
	}

	public String getCommunication5() {
		return communication5;
	}

	public void setCommunication5(String communication5) {
		this.communication5 = communication5;
	}

	public String getComment5() {
		return comment5;
	}

	public void setComment5(String comment5) {
		this.comment5 = comment5;
	}

	public String getDate6() {
		return date6;
	}

	public void setDate6(String date6) {
		this.date6 = date6;
	}

	public String getMonth6() {
		return month6;
	}

	public void setMonth6(String month6) {
		this.month6 = month6;
	}

	public String getYear6() {
		return year6;
	}

	public void setYear6(String year6) {
		this.year6 = year6;
	}

	public String getHour6() {
		return hour6;
	}

	public void setHour6(String hour6) {
		this.hour6 = hour6;
	}

	public String getMinute6() {
		return minute6;
	}

	public void setMinute6(String minute6) {
		this.minute6 = minute6;
	}

	public String getLocal6() {
		return local6;
	}

	public void setLocal6(String local6) {
		this.local6 = local6;
	}

	public String getWay6() {
		return way6;
	}

	public void setWay6(String way6) {
		this.way6 = way6;
	}

	public String getCommunication6() {
		return communication6;
	}

	public void setCommunication6(String communication6) {
		this.communication6 = communication6;
	}

	public String getComment6() {
		return comment6;
	}

	public void setComment6(String comment6) {
		this.comment6 = comment6;
	}

	public String getDate7() {
		return date7;
	}

	public void setDate7(String date7) {
		this.date7 = date7;
	}

	public String getMonth7() {
		return month7;
	}

	public void setMonth7(String month7) {
		this.month7 = month7;
	}

	public String getYear7() {
		return year7;
	}

	public void setYear7(String year7) {
		this.year7 = year7;
	}

	public String getHour7() {
		return hour7;
	}

	public void setHour7(String hour7) {
		this.hour7 = hour7;
	}

	public String getMinute7() {
		return minute7;
	}

	public void setMinute7(String minute7) {
		this.minute7 = minute7;
	}

	public String getLocal7() {
		return local7;
	}

	public void setLocal7(String local7) {
		this.local7 = local7;
	}

	public String getWay7() {
		return way7;
	}

	public void setWay7(String way7) {
		this.way7 = way7;
	}

	public String getCommunication7() {
		return communication7;
	}

	public void setCommunication7(String communication7) {
		this.communication7 = communication7;
	}

	public String getComment7() {
		return comment7;
	}

	public void setComment7(String comment7) {
		this.comment7 = comment7;
	}

	public String getDate8() {
		return date8;
	}

	public void setDate8(String date8) {
		this.date8 = date8;
	}

	public String getMonth8() {
		return month8;
	}

	public void setMonth8(String month8) {
		this.month8 = month8;
	}

	public String getYear8() {
		return year8;
	}

	public void setYear8(String year8) {
		this.year8 = year8;
	}

	public String getHour8() {
		return hour8;
	}

	public void setHour8(String hour8) {
		this.hour8 = hour8;
	}

	public String getMinute8() {
		return minute8;
	}

	public void setMinute8(String minute8) {
		this.minute8 = minute8;
	}

	public String getLocal8() {
		return local8;
	}

	public void setLocal8(String local8) {
		this.local8 = local8;
	}

	public String getWay8() {
		return way8;
	}

	public void setWay8(String way8) {
		this.way8 = way8;
	}

	public String getCommunication8() {
		return communication8;
	}

	public void setCommunication8(String communication8) {
		this.communication8 = communication8;
	}

	public String getComment8() {
		return comment8;
	}

	public void setComment8(String comment8) {
		this.comment8 = comment8;
	}

	public String getDate9() {
		return date9;
	}

	public void setDate9(String date9) {
		this.date9 = date9;
	}

	public String getMonth9() {
		return month9;
	}

	public void setMonth9(String month9) {
		this.month9 = month9;
	}

	public String getYear9() {
		return year9;
	}

	public void setYear9(String year9) {
		this.year9 = year9;
	}

	public String getHour9() {
		return hour9;
	}

	public void setHour9(String hour9) {
		this.hour9 = hour9;
	}

	public String getMinute9() {
		return minute9;
	}

	public void setMinute9(String minute9) {
		this.minute9 = minute9;
	}

	public String getLocal9() {
		return local9;
	}

	public void setLocal9(String local9) {
		this.local9 = local9;
	}

	public String getWay9() {
		return way9;
	}

	public void setWay9(String way9) {
		this.way9 = way9;
	}

	public String getCommunication9() {
		return communication9;
	}

	public void setCommunication9(String communication9) {
		this.communication9 = communication9;
	}

	public String getComment9() {
		return comment9;
	}

	public void setComment9(String comment9) {
		this.comment9 = comment9;
	}

	public String getDate10() {
		return date10;
	}

	public void setDate10(String date10) {
		this.date10 = date10;
	}

	public String getMonth10() {
		return month10;
	}

	public void setMonth10(String month10) {
		this.month10 = month10;
	}

	public String getYear10() {
		return year10;
	}

	public void setYear10(String year10) {
		this.year10 = year10;
	}

	public String getHour10() {
		return hour10;
	}

	public void setHour10(String hour10) {
		this.hour10 = hour10;
	}

	public String getMinute10() {
		return minute10;
	}

	public void setMinute10(String minute10) {
		this.minute10 = minute10;
	}

	public String getLocal10() {
		return local10;
	}

	public void setLocal10(String local10) {
		this.local10 = local10;
	}

	public String getWay10() {
		return way10;
	}

	public void setWay10(String way10) {
		this.way10 = way10;
	}

	public String getCommunication10() {
		return communication10;
	}

	public void setCommunication10(String communication10) {
		this.communication10 = communication10;
	}

	public String getComment10() {
		return comment10;
	}

	public void setComment10(String comment10) {
		this.comment10 = comment10;
	}

	public String getDate11() {
		return date11;
	}

	public void setDate11(String date11) {
		this.date11 = date11;
	}

	public String getMonth11() {
		return month11;
	}

	public void setMonth11(String month11) {
		this.month11 = month11;
	}

	public String getYear11() {
		return year11;
	}

	public void setYear11(String year11) {
		this.year11 = year11;
	}

	public String getHour11() {
		return hour11;
	}

	public void setHour11(String hour11) {
		this.hour11 = hour11;
	}

	public String getMinute11() {
		return minute11;
	}

	public void setMinute11(String minute11) {
		this.minute11 = minute11;
	}

	public String getLocal11() {
		return local11;
	}

	public void setLocal11(String local11) {
		this.local11 = local11;
	}

	public String getWay11() {
		return way11;
	}

	public void setWay11(String way11) {
		this.way11 = way11;
	}

	public String getCommunication11() {
		return communication11;
	}

	public void setCommunication11(String communication11) {
		this.communication11 = communication11;
	}

	public String getComment11() {
		return comment11;
	}

	public void setComment11(String comment11) {
		this.comment11 = comment11;
	}

	public String getDate12() {
		return date12;
	}

	public void setDate12(String date12) {
		this.date12 = date12;
	}

	public String getMonth12() {
		return month12;
	}

	public void setMonth12(String month12) {
		this.month12 = month12;
	}

	public String getYear12() {
		return year12;
	}

	public void setYear12(String year12) {
		this.year12 = year12;
	}

	public String getHour12() {
		return hour12;
	}

	public void setHour12(String hour12) {
		this.hour12 = hour12;
	}

	public String getMinute12() {
		return minute12;
	}

	public void setMinute12(String minute12) {
		this.minute12 = minute12;
	}

	public String getLocal12() {
		return local12;
	}

	public void setLocal12(String local12) {
		this.local12 = local12;
	}

	public String getWay12() {
		return way12;
	}

	public void setWay12(String way12) {
		this.way12 = way12;
	}

	public String getCommunication12() {
		return communication12;
	}

	public void setCommunication12(String communication12) {
		this.communication12 = communication12;
	}

	public String getComment12() {
		return comment12;
	}

	public void setComment12(String comment12) {
		this.comment12 = comment12;
	}

	public String getDate13() {
		return date13;
	}

	public void setDate13(String date13) {
		this.date13 = date13;
	}

	public String getMonth13() {
		return month13;
	}

	public void setMonth13(String month13) {
		this.month13 = month13;
	}

	public String getYear13() {
		return year13;
	}

	public void setYear13(String year13) {
		this.year13 = year13;
	}

	public String getHour13() {
		return hour13;
	}

	public void setHour13(String hour13) {
		this.hour13 = hour13;
	}

	public String getMinute13() {
		return minute13;
	}

	public void setMinute13(String minute13) {
		this.minute13 = minute13;
	}

	public String getLocal13() {
		return local13;
	}

	public void setLocal13(String local13) {
		this.local13 = local13;
	}

	public String getWay13() {
		return way13;
	}

	public void setWay13(String way13) {
		this.way13 = way13;
	}

	public String getCommunication13() {
		return communication13;
	}

	public void setCommunication13(String communication13) {
		this.communication13 = communication13;
	}

	public String getComment13() {
		return comment13;
	}

	public void setComment13(String comment13) {
		this.comment13 = comment13;
	}

	public String getDate14() {
		return date14;
	}

	public void setDate14(String date14) {
		this.date14 = date14;
	}

	public String getMonth14() {
		return month14;
	}

	public void setMonth14(String month14) {
		this.month14 = month14;
	}

	public String getYear14() {
		return year14;
	}

	public void setYear14(String year14) {
		this.year14 = year14;
	}

	public String getHour14() {
		return hour14;
	}

	public void setHour14(String hour14) {
		this.hour14 = hour14;
	}

	public String getMinute14() {
		return minute14;
	}

	public void setMinute14(String minute14) {
		this.minute14 = minute14;
	}

	public String getLocal14() {
		return local14;
	}

	public void setLocal14(String local14) {
		this.local14 = local14;
	}

	public String getWay14() {
		return way14;
	}

	public void setWay14(String way14) {
		this.way14 = way14;
	}

	public String getCommunication14() {
		return communication14;
	}

	public void setCommunication14(String communication14) {
		this.communication14 = communication14;
	}

	public String getComment14() {
		return comment14;
	}

	public void setComment14(String comment14) {
		this.comment14 = comment14;
	}

	public String getDate15() {
		return date15;
	}

	public void setDate15(String date15) {
		this.date15 = date15;
	}

	public String getMonth15() {
		return month15;
	}

	public void setMonth15(String month15) {
		this.month15 = month15;
	}

	public String getYear15() {
		return year15;
	}

	public void setYear15(String year15) {
		this.year15 = year15;
	}

	public String getHour15() {
		return hour15;
	}

	public void setHour15(String hour15) {
		this.hour15 = hour15;
	}

	public String getMinute15() {
		return minute15;
	}

	public void setMinute15(String minute15) {
		this.minute15 = minute15;
	}

	public String getLocal15() {
		return local15;
	}

	public void setLocal15(String local15) {
		this.local15 = local15;
	}

	public String getWay15() {
		return way15;
	}

	public void setWay15(String way15) {
		this.way15 = way15;
	}

	public String getCommunication15() {
		return communication15;
	}

	public void setCommunication15(String communication15) {
		this.communication15 = communication15;
	}

	public String getComment15() {
		return comment15;
	}

	public void setComment15(String comment15) {
		this.comment15 = comment15;
	}

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getResult3() {
		return result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public String getResult4() {
		return result4;
	}

	public void setResult4(String result4) {
		this.result4 = result4;
	}

	public String getResult5() {
		return result5;
	}

	public void setResult5(String result5) {
		this.result5 = result5;
	}

	public String getResult6() {
		return result6;
	}

	public void setResult6(String result6) {
		this.result6 = result6;
	}

	public String getResult7() {
		return result7;
	}

	public void setResult7(String result7) {
		this.result7 = result7;
	}

	public String getResult8() {
		return result8;
	}

	public void setResult8(String result8) {
		this.result8 = result8;
	}

	public String getResult9() {
		return result9;
	}

	public void setResult9(String result9) {
		this.result9 = result9;
	}

	public String getResult10() {
		return result10;
	}

	public void setResult10(String result10) {
		this.result10 = result10;
	}

	public String getResult11() {
		return result11;
	}

	public void setResult11(String result11) {
		this.result11 = result11;
	}

	public String getResult12() {
		return result12;
	}

	public void setResult12(String result12) {
		this.result12 = result12;
	}

	public String getResult13() {
		return result13;
	}

	public void setResult13(String result13) {
		this.result13 = result13;
	}

	public String getResult14() {
		return result14;
	}

	public void setResult14(String result14) {
		this.result14 = result14;
	}

	public String getResult15() {
		return result15;
	}

	public void setResult15(String result15) {
		this.result15 = result15;
	}

	public Collection getCheckPoints() {
		Collection checkPoints = new LinkedList();
		for(int i = 1; i <= Integer.parseInt(stops); i++) {
			CheckPointData checkPoint = getCheckPoint(i);
			if(checkPoint != null) {
				checkPoints.add(checkPoint);
			}
		}
		return checkPoints;
	}

	private CheckPointData getCheckPoint(int i) {
		CheckPointData checkPoint = null;
		switch(i) {
			case 1:
				if(!(Util.empty(date1) && Util.empty(month1) && Util.empty(year1) && Util.empty(hour1) && Util.empty(minute1))) {
					checkPoint = new CheckPointData(date1,month1,year1,hour1,minute1,local1,way1,communication1,result1,comment1);
				}
				break;
			case 2:
				if(!(Util.empty(date2) && Util.empty(month2) && Util.empty(year2) && Util.empty(hour2) && Util.empty(minute2))) {
					checkPoint = new CheckPointData(date2,month2,year2,hour2,minute2,local2,way2,communication2,result2,comment2);
				}
				break;
			case 3:
				if(!(Util.empty(date3) && Util.empty(month3) && Util.empty(year3) && Util.empty(hour3) && Util.empty(minute3))) {
					checkPoint = new CheckPointData(date3,month3,year3,hour3,minute3,local3,way3,communication3,result3,comment3);
				}
				break;
			case 4:
				if(!(Util.empty(date4) && Util.empty(month4) && Util.empty(year4) && Util.empty(hour4) && Util.empty(minute4))) {
					checkPoint = new CheckPointData(date4,month4,year4,hour4,minute4,local4,way4,communication4,result4,comment4);
				}
				break;
			case 5:
				if(!(Util.empty(date5) && Util.empty(month5) && Util.empty(year5) && Util.empty(hour5) && Util.empty(minute5))) {
					checkPoint = new CheckPointData(date5,month5,year5,hour5,minute5,local5,way5,communication5,result5,comment5);
				}
				break;
			case 6:
				if(!(Util.empty(date6) && Util.empty(month6) && Util.empty(year6) && Util.empty(hour6) && Util.empty(minute6))) {
					checkPoint = new CheckPointData(date6,month6,year6,hour6,minute6,local6,way6,communication6,result6,comment6);
				}
				break;
			case 7:
				if(!(Util.empty(date7) && Util.empty(month7) && Util.empty(year7) && Util.empty(hour7) && Util.empty(minute7))) {
					checkPoint = new CheckPointData(date7,month7,year7,hour7,minute7,local7,way7,communication7,result7,comment7);
				}
				break;
			case 8:
				if(!(Util.empty(date8) && Util.empty(month8) && Util.empty(year8) && Util.empty(hour8) && Util.empty(minute8))) {
					checkPoint = new CheckPointData(date8,month8,year8,hour8,minute8,local8,way8,communication8,result8,comment8);
				}
				break;
			case 9:
				if(!(Util.empty(date9) && Util.empty(month9) && Util.empty(year9) && Util.empty(hour9) && Util.empty(minute9))) {
					checkPoint = new CheckPointData(date9,month9,year9,hour9,minute9,local9,way9,communication9,result9,comment9);
				}
				break;
			case 10:
				if(!(Util.empty(date10) && Util.empty(month10) && Util.empty(year10) && Util.empty(hour10) && Util.empty(minute10))) {
					checkPoint = new CheckPointData(date10,month10,year10,hour10,minute10,local10,way10,communication10,result10,comment10);
				}
				break;
			case 11:
				if(!(Util.empty(date11) && Util.empty(month11) && Util.empty(year11) && Util.empty(hour11) && Util.empty(minute11))) {
					checkPoint = new CheckPointData(date11,month11,year11,hour11,minute11,local11,way11,communication11,result11,comment11);
				}
				break;
			case 12:
				if(!(Util.empty(date12) && Util.empty(month12) && Util.empty(year12) && Util.empty(hour12) && Util.empty(minute12))) {
					checkPoint = new CheckPointData(date12,month12,year12,hour12,minute12,local12,way12,communication12,result12,comment12);
				}
				break;
			case 13:
				if(!(Util.empty(date13) && Util.empty(month13) && Util.empty(year13) && Util.empty(hour13) && Util.empty(minute13))) {
					checkPoint = new CheckPointData(date13,month13,year13,hour13,minute13,local13,way13,communication13,result13,comment13);
				}
				break;
			case 14:
				if(!(Util.empty(date14) && Util.empty(month14) && Util.empty(year14) && Util.empty(hour14) && Util.empty(minute14))) {
					checkPoint = new CheckPointData(date14,month14,year14,hour14,minute14,local14,way14,communication14,result14,comment14);
				}
				break;
			case 15:
				if(!(Util.empty(date15) && Util.empty(month15) && Util.empty(year15) && Util.empty(hour15) && Util.empty(minute15))) {
					checkPoint = new CheckPointData(date15,month15,year15,hour15,minute15,local15,way15,communication15,result15,comment15);
				}
				break;
		}
		if(checkPoint != null) {
			checkPoint.setNumber(i);
		}
		return checkPoint;
	}

	public TravelDataType getTravelData(SystemAccess sys) {
		TravelDataType travelDataType = new TravelDataType();
		travelDataType.setId(new Integer(travel));
		travelDataType.setConfirmedLeave(Util.getDate(date, month, year));
		travelDataType.setConfirmedLeaveHour(Util.getTime(hour, minute));
		travelDataType.setCommunicationLeave(way);
		travelDataType.setMonitor(monitor);
		travelDataType.setLeaveComments(comment);
		
		return travelDataType;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getId3() {
		return id3;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	public String getId4() {
		return id4;
	}

	public void setId4(String id4) {
		this.id4 = id4;
	}

	public String getId5() {
		return id5;
	}

	public void setId5(String id5) {
		this.id5 = id5;
	}

	public String getId6() {
		return id6;
	}

	public void setId6(String id6) {
		this.id6 = id6;
	}

	public String getId7() {
		return id7;
	}

	public void setId7(String id7) {
		this.id7 = id7;
	}

	public String getId8() {
		return id8;
	}

	public void setId8(String id8) {
		this.id8 = id8;
	}

	public String getId9() {
		return id9;
	}

	public void setId9(String id9) {
		this.id9 = id9;
	}

	public String getId10() {
		return id10;
	}

	public void setId10(String id10) {
		this.id10 = id10;
	}

	public String getId11() {
		return id11;
	}

	public void setId11(String id11) {
		this.id11 = id11;
	}

	public String getId12() {
		return id12;
	}

	public void setId12(String id12) {
		this.id12 = id12;
	}

	public String getId13() {
		return id13;
	}

	public void setId13(String id13) {
		this.id13 = id13;
	}

	public String getId14() {
		return id14;
	}

	public void setId14(String id14) {
		this.id14 = id14;
	}

	public String getId15() {
		return id15;
	}

	public void setId15(String id15) {
		this.id15 = id15;
	}

    public String validate(Text messages) {
    	if(!Util.checkDate(date, month, year)) {
    		return messages.getText("travel.error.invalidconfirmationdate");
    	}
    	if(Util.empty(hour) || Util.empty(minute) || !Util.checkHour(hour, minute)) {
    		return messages.getText("travel.error.invalidconfirmationleavehour");
    	}
    	for(int i = 1; i <= Integer.parseInt(stops); i++) {
    		switch(i) {
				case 1:
					if(!(Util.empty(date1) && Util.empty(month1) && Util.empty(year1) && Util.empty(hour1) && Util.empty(minute1))) {
						if(!Util.checkDate(date1, month1, year1)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour1) || Util.empty(minute1) || !Util.checkHour(hour1, minute1)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result1)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 2:
					if(!(Util.empty(date2) && Util.empty(month2) && Util.empty(year2) && Util.empty(hour2) && Util.empty(minute2))) {
						if(!Util.checkDate(date2, month2, year2)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour2) || Util.empty(minute2) || !Util.checkHour(hour2, minute2)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result2)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 3:
					if(!(!Util.empty(date3) && Util.empty(month3) && Util.empty(year3) && Util.empty(hour3) && Util.empty(minute3))) {
						if(Util.checkDate(date3, month3, year3)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour3) || Util.empty(minute3) || !Util.checkHour(hour3, minute3)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result3)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 4:
					if(!(Util.empty(date4) && Util.empty(month4) && Util.empty(year4) && Util.empty(hour4) && Util.empty(minute4))) {
						if(!Util.checkDate(date4, month4, year4)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour4) || Util.empty(minute4) || !Util.checkHour(hour4, minute4)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result4)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 5:
					if(!(Util.empty(date5) && Util.empty(month5) && Util.empty(year5) && Util.empty(hour5) && Util.empty(minute5))) {
						if(!Util.checkDate(date5, month5, year5)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour5) || Util.empty(minute5) || !Util.checkHour(hour5, minute5)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result5)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 6:
					if(!(Util.empty(date6) && Util.empty(month6) && Util.empty(year6) && Util.empty(hour6) && Util.empty(minute6))) {
						if(!Util.checkDate(date6, month6, year6)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour6) || Util.empty(minute6) || !Util.checkHour(hour6, minute6)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result6)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 7:
					if(!(Util.empty(date7) && Util.empty(month7) && Util.empty(year7) && Util.empty(hour7) && Util.empty(minute7))) {
						if(!Util.checkDate(date7, month7, year7)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour7) || Util.empty(minute7) || !Util.checkHour(hour7, minute7)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result7)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 8:
					if(!(!Util.empty(date8) && Util.empty(month8) && Util.empty(year8) && Util.empty(hour8) && Util.empty(minute8))) {
						if(Util.checkDate(date8, month8, year8)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour8) || Util.empty(minute8) || !Util.checkHour(hour8, minute8)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result8)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 9:
					if(!(!Util.empty(date9) && Util.empty(month9) && Util.empty(year9) && Util.empty(hour9) && Util.empty(minute9))) {
						if(Util.checkDate(date9, month9, year9)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour9) || Util.empty(minute9) || !Util.checkHour(hour9, minute9)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result9)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 10:
					if(!(!Util.empty(date10) && Util.empty(month10) && Util.empty(year10) && Util.empty(hour10) && Util.empty(minute10))) {
						if(Util.checkDate(date10, month10, year10)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour10) || Util.empty(minute10) || !Util.checkHour(hour10, minute10)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result10)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 11:
					if(!(!Util.empty(date11) && Util.empty(month11) && Util.empty(year11) && Util.empty(hour11) && Util.empty(minute11))) {
						if(Util.checkDate(date11, month11, year11)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour11) || Util.empty(minute11) || !Util.checkHour(hour11, minute11)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result11)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 12:
					if(!(!Util.empty(date12) && Util.empty(month12) && Util.empty(year12) && Util.empty(hour12) && Util.empty(minute12))) {
						if(Util.checkDate(date12, month12, year12)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour12) || Util.empty(minute12) || !Util.checkHour(hour12, minute12)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result12)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 13:
					if(!(!Util.empty(date13) && Util.empty(month13) && Util.empty(year13) && Util.empty(hour13) && Util.empty(minute13))) {
						if(Util.checkDate(date13, month13, year13)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour13) || Util.empty(minute13) || !Util.checkHour(hour13, minute13)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result13)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 14:
					if(!(!Util.empty(date14) && Util.empty(month14) && Util.empty(year14) && Util.empty(hour14) && Util.empty(minute14))) {
						if(Util.checkDate(date14, month14, year14)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour14) || Util.empty(minute14) || !Util.checkHour(hour14, minute14)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result14)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
				case 15:
					if(!(!Util.empty(date15) && Util.empty(month15) && Util.empty(year15) && Util.empty(hour15) && Util.empty(minute15))) {
						if(Util.checkDate(date15, month15, year15)) {
				    		return messages.getText("travel.error.invalidcheckpointdate")+" Checkpoint "+i;
				    	}
				    	if(Util.empty(hour15) || Util.empty(minute15) || !Util.checkHour(hour15, minute15)) {
				    		return messages.getText("travel.error.invalidcheckpointhour")+" Checkpoint "+i;
				    	}
						if(Util.empty(result15)) {
				    		return messages.getText("travel.error.invalidresult")+" Checkpoint "+i;						
						}
					}
					break;
			}
    	}
    	return null;	
    }
}
