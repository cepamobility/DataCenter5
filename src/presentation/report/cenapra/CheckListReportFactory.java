/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.cenapra;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.cenapra.ReportForm;
import business.SystemAccess;

import communication.administration.corporation.util.Util;
import communication.language.Text;

public class CheckListReportFactory extends ReportFactory {

    protected Text messages;
    private int order;
    protected int pages;
    protected int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;
    protected boolean all;

    public CheckListReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, int order, int pages, int pageNumber, boolean all) {
        super(name, model, form);
        this.messages = messages;
        this.order = order;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        this.all = all;
    }

    protected int getWidth() {
        return (all) ? 4202 : 1422;
    }

    protected int getReportWidth() {
        return (all) ? 4120 : 1340;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 200, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        items.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(210, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
        items.addElement(TextFieldElementFactory.createStringElement("municipaly",new Rectangle2D.Float(310, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","municipaly"));
        int x = 410;
        if(all) {
            items.addElement(TextFieldElementFactory.createStringElement("birthdate",new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","birthdate"));
            x += 60;
            items.addElement(TextFieldElementFactory.createStringElement("population",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","population"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("organization",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","organization"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("state",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","state"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","city"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("delegation",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","delegation"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("address",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","address"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("postalcode",new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","postalcode"));
            x += 60;
            items.addElement(TextFieldElementFactory.createStringElement("phone",new Rectangle2D.Float(x, 0, 70, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","phone"));
            x += 70;
            items.addElement(TextFieldElementFactory.createStringElement("movile",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","movile"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("email",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","email"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("medical",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","medical"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("paramedical",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","paramedical"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("nurse",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","nurse"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("police",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","police"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("vialinspector",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vialinspector"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("teacher",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","teacher"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("prevent",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","prevent"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("otherprofession",new Rectangle2D.Float(x, 0, 140, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","otherprofession"));
            x += 140;
            items.addElement(TextFieldElementFactory.createStringElement("education",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","education"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("othereducation",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","othereducation"));
            x += 120;
            items.addElement(TextFieldElementFactory.createStringElement("security",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","security"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("securitytrainning",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","securitytrainning"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("securitydesc",new Rectangle2D.Float(x, 0, 140, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","securitydesc"));
            x += 140;
            items.addElement(TextFieldElementFactory.createStringElement("teachexperience",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","teachexperience"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("first",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","first"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("second",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","second"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("third",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","third"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("otherteach",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","otherteach"));
            x += 120;
            items.addElement(TextFieldElementFactory.createStringElement("observations",new Rectangle2D.Float(x, 0, 150, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","observations"));
            x += 150;
        }
        items.addElement(TextFieldElementFactory.createStringElement("activity",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","activity"));
        x += 120;
        items.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","date"));
        x += 60;
        items.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","city"));
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement("instructor",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","instructor"));
        x += 100;
        if(order == 1) {
            for(int i = 0; i < 28; i++) {
                items.addElement(TextFieldElementFactory.createStringElement("check"+i,new Rectangle2D.Float(x + (20*i), 0, 20, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.BOTTOM,STYLE_SIMPLE_FONT,"-","check"+i));
            }
        }else {
            for(int i = 0; i < 14; i++) {
                items.addElement(TextFieldElementFactory.createStringElement("check"+i,new Rectangle2D.Float(x + (40*i), 0, 20, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.BOTTOM,STYLE_SIMPLE_FONT,"-","check"+i));
                items.addElement(TextFieldElementFactory.createStringElement("check"+String.valueOf(i+14),new Rectangle2D.Float(x + (40*i+20), 0, 20, 14),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.BOTTOM,STYLE_SIMPLE_FONT,"-","check"+String.valueOf(i+14)));
            }
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();          
        int height = writeFilter((ReportForm)form,reportHeader); 
            
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,new Rectangle2D.Float(10, height+18, 400, 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height+18, 200, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity.participant")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height+18, 100, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("imesevi.participant.state")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(310, height+18, 100, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("imesevi.state.municipality")));
        int x = 410;
        if(all) {
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,new Rectangle2D.Float(x, height+18, 990, 18), false, true));       
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 60, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.participant.birthdate")));
            x += 60;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity.population")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity.organization")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.participant.state")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.participant.city")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.delegation")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.address")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 60, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.postalcode")));
            x += 60;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 70, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.phone")));
            x += 70;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.movile")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.email")));
            x += 100;
            
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,new Rectangle2D.Float(x, height, 700, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,new Rectangle2D.Float(x, height+18, 700, 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 700, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion")));

            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.medical")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.paramedical")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.nurse")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.police")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.vialinspector")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.teacher")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.profesion.prevent")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 140, 18),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.otherprofesion")));
            x += 140;
            
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x, height, 200, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x, height+18, 200, 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 200, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.education")));

            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.educationlevel")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 120, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.othereducation")));
            x += 120;
            
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,new Rectangle2D.Float(x, height, 300, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,new Rectangle2D.Float(x, height+18, 300, 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 300, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.security")));

            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.hassecurity")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.securitytrainning")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.securitydesc")));
            x += 100;

            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x, height, 440, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x, height+18, 440, 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 440, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.teachexperience")));

            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.teachexperience.has")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.teachfirst")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.teachsecond")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 80, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.teachthird")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 120, 18),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.otherteach")));
            x += 120;

            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,new Rectangle2D.Float(x, height+18, 150, 18), false, true));       
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 150, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.observations")));
            x += 150;
        }
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,new Rectangle2D.Float(x, height, 380, 18), false, true));
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,new Rectangle2D.Float(x, height+18, 380, 18), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 380, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 120, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity.type")));
        x += 120;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 60, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity.date")));
        x += 60;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.data.city")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height+18, 100, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.activity.instructor")));
        x += 100;

        if(order == 1) {
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,SecondLevelColor, null,new Rectangle2D.Float(x, height, 280, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,SecondLevelColor, null,new Rectangle2D.Float(x, height+18, 280, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x+280, height, 280, 18), false, true));
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x+280, height+18, 280, 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 280, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.checklist1.form")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+280, height, 280, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("cenapra.checklist2.form")));

            for(int i = 0; i < 28; i++) {
                Color cFont = (i < 14) ? Color.WHITE : Color.BLACK;
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x + (20*i), height+18, 20, 18),
                    cFont,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf((i%14) + 1)));
            }
        }else {
            int k = 1;
            for(int i = 0; i < 28; i++) {
                if(i % 2 == 0) {
                    reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,SecondLevelColor, null,new Rectangle2D.Float(x + (20*i), height+18, 20, 18), false, true));
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x + (20*i), height+18, 20, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(k)));
                }else {
                    reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,new Rectangle2D.Float(x + (20*i), height+18, 20, 18), false, true));
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x + (20*i), height+18, 20, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(k)));
                    k++;
                }
            }
        }
        return reportHeader;
    }

	protected int writeFilter(ReportForm reportForm, ReportHeader reportHeader) {
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("imesevi.participantlist.title")));
        
        int height=40;
        height = writeParametersTitle(messages.getText("generic.participant.personaldata"),reportHeader,height,10,200);
        height=writeParameter(messages.getText("participant.data.firstname")+":",reportForm.getFirstName(),reportHeader,height,10,60,70,140);
        height=writeParameter(messages.getText("participant.data.lastname")+":",reportForm.getLastName(),reportHeader,height,10,60,70,140);
        
        height=40;
        
        height = writeParametersTitle(messages.getText("cenapra.activity"),reportHeader,height,210,180);
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",Util.formatDate(reportForm.getFrom(),messages),reportHeader,height,210,60,270,120);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",Util.formatDate(reportForm.getTo(),messages),reportHeader,height,210,60,270,120);
        if(!isChecked(reportForm.getMultiplicadores()) && !isChecked(reportForm.getReplicadores()) && !isChecked(reportForm.getMultiplicadoresNew())) {
            height = writeParameter(messages.getText("cenapra.activity")+":","",reportHeader,height,210,60,270,120);
        }else{
            if(isChecked(reportForm.getMultiplicadores())) {
                height = writeParameter(messages.getText("cenapra.activity")+":",messages.getText("datatype.scheduledactivitytype.cenapramultiplicadores"),reportHeader,height,210,60,270,120);
            }
            if(isChecked(reportForm.getReplicadores())) {
                height = writeParameter(messages.getText("cenapra.activity")+":",messages.getText("datatype.scheduledactivitytype.cenaprareplicadores"),reportHeader,height,210,60,270,120);
            }
            if(isChecked(reportForm.getMultiplicadoresNew())) {
                height = writeParameter(messages.getText("cenapra.activity")+":",messages.getText("datatype.scheduledactivitytype.cenapramultiplicadoresnew"),reportHeader,height,210,60,270,120);
            }
        }

        return height;
	}

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 200, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-70, 0, 80, 15),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
        
        if(((ReportForm)form).getOutput().equals("HTML")) {
        	return pagination (reportFooter,40);
        }else return reportFooter;
    }

    protected ReportFooter pagination (ReportFooter reportFooter,int height){
    	int left = 60;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 50, 15),
                Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
        for(int i = 1; i <= pages; i++) {
            int width = (i < 10) ? 15 : 20; 
            if (left>getReportWidth()){
            	left=60;
            	height+=15;
            }
            if(i != pageNumber) {
                reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field-"+i,new Rectangle2D.Float(left, height, width, 15),
                        Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
            }else {
                reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, height, width, 15),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
            }
            left += width;
        }
        return reportFooter;
    }
    
    
    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/cenapra/ParticipantView.jsp?id={0}");
        textExp.setField(0,"id");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);

        if(((ReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?bookmark=true&refer=/cenapra/reportPage.jsp?page="+i+"&report=report"+((ReportForm)form).getLink());
                    functions.add(textExp2);
            
                    CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
                    link2.setName("AddHRefs"+i);
                    link2.setField("URLCreateExpression"+i);
                    link2.setElement("URL-Field-"+i);
                    functions.add(link2);
                }
            }
        }

        return functions;
    }
    
    private boolean isChecked(String value) {
        return value != null && value.equals("on");
    }
}
