/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.cenapra;

import java.util.Calendar;
import java.util.Date;

import org.apache.taglibs.standard.lang.jpath.expression.MultiplicationOperator;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;
/**
 * @author jrodriguez
 */
public class ReportForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String all;
    private String fromDay;
    private String fromMonth;
    private String fromYear;
    private String toDay;
    private String toMonth;
    private String toYear;
    private String checklist;
    private String order;
    private String output;
    
    private String bookmark;

    private String multiplicadores;
    private String replicadores;
    private String multiplicadoresNew;
    private String multiplicadores2;
    private String formadores;
    private String poblacionmeta;

    public ReportForm() {
        order = "1";
        multiplicadores = "off";
        replicadores = "off";
        multiplicadoresNew = "off";
        multiplicadores2 = "off";
        formadores = "off";
        poblacionmeta = "off";
    }
    
    

    /**
     * @param firstName
     * @param lastName
     * @param all
     * @param fromDay
     * @param fromMonth
     * @param fromYear
     * @param toDay
     * @param toMonth
     * @param toYear
     * @param type1
     * @param type2
     */
    public ReportForm(String firstName, String lastName, String all, String fromDay, String fromMonth, String fromYear, String toDay, String toMonth, String toYear, String multiplicadores, String replicadores, String multiplicadoresNew, String multiplicadores2, String formadores, String poblacionmeta, String checklist, String order) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.all = all;
        this.fromDay = fromDay;
        this.fromMonth = fromMonth;
        this.fromYear = fromYear;
        this.toDay = toDay;
        this.toMonth = toMonth;
        this.toYear = toYear;
        this.multiplicadores = multiplicadores;
        this.replicadores = replicadores;
        this.multiplicadoresNew = multiplicadoresNew;
        this.checklist = checklist;
        this.order = order;
        this.multiplicadores2 = multiplicadores2;
        this.formadores = formadores;
        this.poblacionmeta = poblacionmeta;
    }



    public String getAll() {
        return all;
    }

    public String getBookmark() {
        return bookmark;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFromDay() {
        return fromDay;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOutput() {
        return output;
    }

    public String getToDay() {
        return toDay;
    }

    public String getToMonth() {
        return toMonth;
    }

    public String getToYear() {
        return toYear;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public Date getFrom() {
        if(fromDay == null || fromMonth == null || fromYear == null || fromDay.equals("0") || fromMonth.equals("0") || fromYear.equals("0")) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE,Integer.parseInt(fromDay));
        c.set(Calendar.MONTH,Integer.parseInt(fromMonth)-1);
        c.set(Calendar.YEAR,Integer.parseInt(fromYear));
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        return c.getTime();
    }

    public Date getTo() {
        if(toDay == null || toMonth == null || toYear == null || toDay.equals("0") || toMonth.equals("0") || toYear.equals("0")) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH,Integer.parseInt(toMonth)-1);
        c.set(Calendar.YEAR,Integer.parseInt(toYear));
        c.set(Calendar.DATE,Integer.parseInt(toDay));
        c.set(Calendar.HOUR,23);
        c.set(Calendar.MINUTE,59);
        return c.getTime();
    }

    public String getLink() {
        String link = "";
        link += (!Util.empty(firstName)) ? "&firstName="+firstName : "";
        link += (!Util.empty(lastName)) ? "&lastName="+lastName : "";
        link += (!Util.empty(all)) ? "&all="+all : "";
        link += (!Util.empty(fromDay)) ? "&fromDay="+fromDay : "";
        link += (!Util.empty(fromMonth)) ? "&fromMonth="+fromMonth : "";
        link += (!Util.empty(fromYear)) ? "&fromYear="+fromYear : "";
        link += (!Util.empty(toDay)) ? "&toDay="+toDay : "";
        link += (!Util.empty(toMonth)) ? "&toMonth="+toMonth : "";
        link += (!Util.empty(toYear)) ? "&toYear="+toYear : "";
        link += (!Util.empty(multiplicadores)) ? "&multiplicadores="+multiplicadores : "";
        link += (!Util.empty(replicadores)) ? "&replicadores="+replicadores : "";
        link += (!Util.empty(multiplicadoresNew)) ? "&multiplicadoresNew="+multiplicadoresNew : "";
        link += (!Util.empty(multiplicadores2)) ? "&multiplicadores2="+multiplicadores2 : "";
        link += (!Util.empty(formadores)) ? "&formadores="+formadores : "";
        link += (!Util.empty(poblacionmeta)) ? "&poblacionmeta="+poblacionmeta : "";
        link += (!Util.empty(checklist)) ? "&checklist="+checklist : "";
        link += (!Util.empty(order)) ? "&order="+order : "";
        return link;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMultiplicadores() {
        return multiplicadores;
    }

    public String getReplicadores() {
        return replicadores;
    }

    public String getMultiplicadoresNew() {
        return multiplicadoresNew;
    }

    public void setReplicadores(String replicadores) {
        this.replicadores = replicadores;
    }

    public void setMultiplicadores(String multiplicadores) {
        this.multiplicadores = multiplicadores;
    }

    public void setMultiplicadoresNew(String multiplicadoresNew) {
        this.multiplicadoresNew = multiplicadoresNew;
    }



	public String getMultiplicadores2() {
		return multiplicadores2;
	}



	public void setMultiplicadores2(String multiplicadores2) {
		this.multiplicadores2 = multiplicadores2;
	}



	public String getFormadores() {
		return formadores;
	}



	public void setFormadores(String formadores) {
		this.formadores = formadores;
	}



	public String getPoblacionmeta() {
		return poblacionmeta;
	}



	public void setPoblacionmeta(String poblacionmeta) {
		this.poblacionmeta = poblacionmeta;
	}
}
