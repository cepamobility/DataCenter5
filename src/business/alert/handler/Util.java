package business.alert.handler; 
import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import communication.alert.AlertConstants;
import communication.util.StringUtils;

public class Util {

    /**
     * Constructor
     */
    public Util() {
        super();
    }
    
    public static boolean empty(String label) {
        return label == null || label.trim().length() == 0;
    }
    
    
    public static String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }

    public static String formatHour(Time time) {
        if(time == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        String minute = (calendar.get(Calendar.MINUTE) < 10) ? "0"+String.valueOf(calendar.get(Calendar.MINUTE)) : String.valueOf(calendar.get(Calendar.MINUTE)); 
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))+":"+minute;
    }

    public static Date getDate(String day, String month, String year) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day));
            calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(year));
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            
            return calendar.getTime();
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    public static String getHtmlString(String word) {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);             
            switch(c) {
            /*
                case 'á':
                    result.append("a");
                    break;
                case '�':
                    result.append("e");
                    break;
                case '�':
                    result.append("i");
                    break;
                case '�':
                    result.append("o");
                    break;
                case '�':
                    result.append("u");
                    break;
                case '�':
                    result.append("n");
                    break;
                case '�':
                    result.append("c");
                    break;
                case '�':
                    result.append("o");
                    break;
                case '�':
                    result.append("a");
                    break;
                case '&':
                    result.append("");
                    break;
                case '/':
                    result.append("-");
                    break;
                case ',':
                    result.append("");
                    break;
                case '.':
                    result.append("");
                    break;
                */
                default:
                    result.append(c);
            }
        }
        return result.toString();
    }
    
    public static Calendar getCalendar(Date slqdate) {
        try {
        	Calendar date = Calendar.getInstance();
            date.setTime(slqdate);
            
            return date;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        }catch(Exception e) {
            return false;
        }
        return true;
    }
    

    public static Time getTime(String hour, String minute) {
        Calendar calendar = Calendar.getInstance();
        if(isNumber(hour)) {
            calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour));
        }
        if(isNumber(minute)) {
            calendar.set(Calendar.MINUTE,Integer.parseInt(minute));
        }
        return new Time(calendar.getTimeInMillis());
    }
    
    
    public static boolean checkDate(int day,int month,int year) {
        if(day <= 0 || month <= 0 || year <= 0) {
            return false;
        }
        String validateYear=""+year;
        if(validateYear.length()!=4){
        	return false;
        }
          switch(month) {
            case 2:
                int max = (year % 4 == 0) ? 29 : 28; 
                if(day > max) {
                    return false;
                }
                break;
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if(day > 31) {
                    return false;
                }    
                break;
            case 4: case 6: case 9: case 11:
                if(day > 30) {
                    return false;
                }    
                break;
            default: return false;    
        }
        return true;
    }    
    

    public static boolean checkHour(int hour, int minute) {
        if(hour > 23 || hour < 0) {
            return false;
        }
        if(minute > 59 || minute < 0) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param word
     * @return
     */
    public static String formatHTML(String word) {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); 
            switch(c) {
                case '\r':
                    break;
                case '\n':
                    result.append("<br>");
                    break;
                case '\t':
                    result.append("&nbsp;&nbsp;&nbsp;&nbsp;");
                    break;
                case ' ':
                    result.append("  ");
                    break;
                default:
                    result.append(c);
            }
        }
        return result.toString();
    }
    
    /**
     * 
     * @param stringList
     * @param splitChar
     * @return
     */
    public static Collection stringToCollection(String stringList,String splitChar){
        String[] stringArray=stringList.split(splitChar);
        Collection col=new LinkedList();
        
        for(int i=0; i < stringArray.length; i++){
            col.add(stringArray[i]);
        }
        
        return col;
    }
    
    
    public static String getDataCenterGenericMail(String text) {
        String mail = "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'>"+
        "<span style='font-family:Arial;'> "+
        text+
        "</span> "+
        "</p> "+
        "--  "+
        "<br> "+
        "<span style='font-size:10.0pt;font-family:Arial;color:navy'>.................................................</span> "+
        "<br> "+
        "<em><b><span lang=EN-US style='font-size:10.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'> DataCenter&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
        "</span></b></em> "+
        "<br> "+
        "<em><b><span lang=EN-US style='font-size:10.0pt;font-family:Verdana;color:blue;mso-ansi-language: "+
        "EN-US'>CEPA  International</span></b></em> "+
        "<br> "+
        "<strong> "+
        "<span lang=EN-US style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language: "+
        "EN-US'>Phone:</span></strong><span class=grame><b><span lang=EN-US "+
        "style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp; "+
        "(</span></b></span> "+
        "<strong> "+
        "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>598<span class=GramE>)(</span>2)&nbsp;&nbsp;518 55 28</span><br> "+
        "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;518 55 29</span> "+
        "</strong> "+
        "<span lang=EN-US style='mso-ansi-language:EN-US'></span> "+
        "<br> "+
        "<strong><span "+
        "lang=EN-US style='font-size:7.5pt;font-family:Verdana;mso-ansi-language:EN-US; "+
        "font-weight:normal'><a href='mailto:"+ AlertConstants.FROM_DC+"'>"+AlertConstants.FROM_DC+"</a></span></strong><span "+
        "lang=EN-US style='font-size:10.0pt;font-family:Arial;mso-ansi-language:EN-US'> </span><span "+
        "lang=EN-US style='mso-ansi-language:EN-US'></span> "+

        "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=823 "+
         "style='width:617.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
         "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
          "auto;text-align:center'><span lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
          "</td> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'><span "+
          "lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
          "</td> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<div class=MsoNormal align=center style='text-align:center'><span "+
          "style='font-size:7.5pt'> "+
          "<hr size=2 width='100%' align=center> "+
          "</span></div> "+
          "<div align=center> "+
          "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=827 "+
          " style='width:620.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
           "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
           "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
            "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
            "auto;text-align:center'><span lang=EN-GB style='font-size:7.5pt;color:navy; "+
            "mso-ansi-language:EN-GB'>&nbsp; *Confidentiality Notice*<br> "+
            "The information contained in this e-mail and any accompanying documents is "+
            "confidential, may be privileged. It is intended solely for the person "+
            "and/or entity to whom it is addressed (i.e. those identified in the "+
            "&quot;To&quot; and &quot;cc&quot; box). The message and any attachments are "+
            "the property of CEPA International LLC, its group companies, or clients. "+
            "Unauthorized review, use, disclosure, or copying of this communication, or "+
            "any part thereof, is strictly prohibited and may be unlawful. If you have "+
            "received this e-mail in error, please return it and any attachment to the "+
            "sender and delete the e-mail and attachments and any copy from your system. "+
            "</span><span style='font-size:7.5pt;color:navy'>CEPA <span class=SpellE>International</span> "+
            "<span class=SpellE><span class=spelle>thanks</span></span> <span "+
            "class=SpellE><span class=spelle>you</span></span> <span class=SpellE><span "+
            "class=spelle>for</span></span> <span class=SpellE><span class=spelle>your</span></span> "+
            "<span class=SpellE><span class=spelle>cooperation</span></span>. </span></p> "+
            "<p align=center style='text-align:center'>&nbsp;</p> "+
            "</td> "+
           "</tr> "+
          "</table> "+
          "</div> "+
          "<p class=MsoNormal align=center style='text-align:center'><o:p></o:p></p> "+
          "</td> "+
         "</tr> "+
        "</table> ";
        return mail;
    }
    
    
    /**
     * 
     * @param text
     * @param signature
     * @param email
     * @return
     */
    public static String getCompleteHTMLMail(String text,String signature,String email) {
        String mail = "<html>";
        mail += "<body>";
        mail += "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'>"+
        "<span style='font-family:Arial;'> "+
        text+
        "</span> "+
        "</p> "+
        "--  "+
        "<br> "+
        "<span style='font-size:10.0pt;font-family:Arial;color:navy'>.................................................</span> "+
        "<br> "+
        "<em><b><span lang=EN-US style='font-size:10.0pt;font-family:Verdana;color:navy'>"+signature+"</span></b></em>"+
        "<br> "+
        "<em><b><span lang=EN-US style='font-size:10.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'> DataCenter&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
        "</span></b></em> "+
        "<br> "+
        "<em><b><span lang=EN-US style='font-size:10.0pt;font-family:Verdana;color:blue;mso-ansi-language: "+
        "EN-US'>CEPA International</span></b></em> "+
        "<br> "+
        "<strong><span "+
        "lang=EN-US style='font-size:7.5pt;font-family:Verdana;mso-ansi-language:EN-US; "+
        "font-weight:normal'><a href='mailto:"+email+"'>"+email+"</a></span></strong><span "+
        "lang=EN-US style='font-size:10.0pt;font-family:Arial;mso-ansi-language:EN-US'> </span><span "+
        "lang=EN-US style='mso-ansi-language:EN-US'></span> "+

        "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=823 "+
         "style='width:617.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
         "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
          "auto;text-align:center'><span lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
          "</td> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'><span "+
          "lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
          "</td> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<div class=MsoNormal align=center style='text-align:center'><span "+
          "style='font-size:7.5pt'> "+
          "<hr size=2 width='100%' align=center> "+
          "</span></div> "+
          "<div align=center> "+
          "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=827 "+
          " style='width:620.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
           "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
           "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
            "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
            "auto;text-align:center'><span lang=EN-GB style='font-size:7.5pt;color:navy; "+
            "mso-ansi-language:EN-GB'>&nbsp; *Confidentiality Notice*<br> "+
            "The information contained in this e-mail and any accompanying documents is "+
            "confidential, may be privileged. It is intended solely for the person "+
            "and/or entity to whom it is addressed (i.e. those identified in the "+
            "&quot;To&quot; and &quot;cc&quot; box). The message and any attachments are "+
            "the property of CEPA International LLC, its group companies, or clients. "+
            "Unauthorized review, use, disclosure, or copying of this communication, or "+
            "any part thereof, is strictly prohibited and may be unlawful. If you have "+
            "received this e-mail in error, please return it and any attachment to the "+
            "sender and delete the e-mail and attachments and any copy from your system. "+
            "</span><span style='font-size:7.5pt;color:navy'>CEPA <span class=SpellE>International</span> "+
            "<span class=SpellE><span class=spelle>thanks</span></span> <span "+
            "class=SpellE><span class=spelle>you</span></span> <span class=SpellE><span "+
            "class=spelle>for</span></span> <span class=SpellE><span class=spelle>your</span></span> "+
            "<span class=SpellE><span class=spelle>cooperation</span></span>. </span></p> "+
            "<p align=center style='text-align:center'>&nbsp;</p> "+
            "</td> "+
           "</tr> "+
          "</table> "+
          "</div> "+
          "<p class=MsoNormal align=center style='text-align:center'><o:p></o:p></p> "+
          "</td> "+
         "</tr> "+
        "</table> ";
        mail += "</body>";
        mail += "</html>";
        return mail;
    }
    
    
}