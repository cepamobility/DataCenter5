/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.DivisionForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;

/**
 * @author jrodriguez
 */
public class DriverDivOrgForm extends ActionForm implements DivisionForm {

    private static final long serialVersionUID = 1L;

    protected String divOrg11;
    protected String divOrg12;
    protected String divOrg13;
    protected String divOrg21;
    protected String divOrg22;
    protected String divOrg23;
    protected String divOrg31;
    protected String divOrg32;
    protected String divOrg33;
    protected String country1;
    protected String country2;
    protected String country3;

    
    public DriverDivOrgForm() {
        super();
    }
    
    /**
     * @param divOrg11
     * @param divOrg12
     * @param divOrg13
     * @param divOrg21
     * @param divOrg22
     * @param divOrg23
     * @param divOrg31
     * @param divOrg32
     * @param divOrg33
     * @param country1
     * @param country2
     * @param country3
     */
    public DriverDivOrgForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super();
        this.divOrg11 = divOrg11;
        this.divOrg12 = divOrg12;
        this.divOrg13 = divOrg13;
        this.divOrg21 = divOrg21;
        this.divOrg22 = divOrg22;
        this.divOrg23 = divOrg23;
        this.divOrg31 = divOrg31;
        this.divOrg32 = divOrg32;
        this.divOrg33 = divOrg33;
        this.country1 = country1;
        this.country2 = country2;
        this.country3 = country3;
    }
    
    public DriverDivOrgForm(BookmarkData bookmark) {
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("divOrg11")) {
            divOrg11 = (String)parameters.get("divOrg11"); 
        }
        if(parameters.containsKey("divOrg12")) {
            divOrg12 = (String)parameters.get("divOrg12"); 
        }
        if(parameters.containsKey("divOrg13")) {
            divOrg13 = (String)parameters.get("divOrg13"); 
        }
        if(parameters.containsKey("divOrg21")) {
            divOrg21 = (String)parameters.get("divOrg21"); 
        }
        if(parameters.containsKey("divOrg22")) {
            divOrg22 = (String)parameters.get("divOrg22"); 
        }
        if(parameters.containsKey("divOrg23")) {
            divOrg23 = (String)parameters.get("divOrg23"); 
        }
        if(parameters.containsKey("divOrg31")) {
            divOrg31 = (String)parameters.get("divOrg31"); 
        }
        if(parameters.containsKey("divOrg32")) {
            divOrg32 = (String)parameters.get("divOrg32"); 
        }
        if(parameters.containsKey("divOrg33")) {
            divOrg33 = (String)parameters.get("divOrg33"); 
        }
        if(parameters.containsKey("country1")) {
            country1 = (String)parameters.get("country1"); 
        }
        if(parameters.containsKey("country2")) {
            country2 = (String)parameters.get("country2"); 
        }
        if(parameters.containsKey("country3")) {
            country3 = (String)parameters.get("country3"); 
        }
    }

    /**
     * @return Returns the country1.
     */
    public String getCountry1() {
        return country1;
    }
    /**
     * @param country1 The country1 to set.
     */
    public void setCountry1(String country1) {
        this.country1 = country1;
    }
    /**
     * @return Returns the country2.
     */
    public String getCountry2() {
        return country2;
    }
    /**
     * @param country2 The country2 to set.
     */
    public void setCountry2(String country2) {
        this.country2 = country2;
    }
    /**
     * @return Returns the country3.
     */
    public String getCountry3() {
        return country3;
    }
    /**
     * @param country3 The country3 to set.
     */
    public void setCountry3(String country3) {
        this.country3 = country3;
    }
    /**
     * @return Returns the divOrg11.
     */
    public String getDivOrg11() {
        return divOrg11;
    }
    /**
     * @param divOrg11 The divOrg11 to set.
     */
    public void setDivOrg11(String divOrg11) {
        this.divOrg11 = divOrg11;
    }
    /**
     * @return Returns the divOrg12.
     */
    public String getDivOrg12() {
        return divOrg12;
    }
    /**
     * @param divOrg12 The divOrg12 to set.
     */
    public void setDivOrg12(String divOrg12) {
        this.divOrg12 = divOrg12;
    }
    /**
     * @return Returns the divOrg13.
     */
    public String getDivOrg13() {
        return divOrg13;
    }
    /**
     * @param divOrg13 The divOrg13 to set.
     */
    public void setDivOrg13(String divOrg13) {
        this.divOrg13 = divOrg13;
    }
    /**
     * @return Returns the divOrg21.
     */
    public String getDivOrg21() {
        return divOrg21;
    }
    /**
     * @param divOrg21 The divOrg21 to set.
     */
    public void setDivOrg21(String divOrg21) {
        this.divOrg21 = divOrg21;
    }
    /**
     * @return Returns the divOrg22.
     */
    public String getDivOrg22() {
        return divOrg22;
    }
    /**
     * @param divOrg22 The divOrg22 to set.
     */
    public void setDivOrg22(String divOrg22) {
        this.divOrg22 = divOrg22;
    }
    /**
     * @return Returns the divOrg23.
     */
    public String getDivOrg23() {
        return divOrg23;
    }
    /**
     * @param divOrg23 The divOrg23 to set.
     */
    public void setDivOrg23(String divOrg23) {
        this.divOrg23 = divOrg23;
    }
    /**
     * @return Returns the divOrg31.
     */
    public String getDivOrg31() {
        return divOrg31;
    }
    /**
     * @param divOrg31 The divOrg31 to set.
     */
    public void setDivOrg31(String divOrg31) {
        this.divOrg31 = divOrg31;
    }
    /**
     * @return Returns the divOrg32.
     */
    public String getDivOrg32() {
        return divOrg32;
    }
    /**
     * @param divOrg32 The divOrg32 to set.
     */
    public void setDivOrg32(String divOrg32) {
        this.divOrg32 = divOrg32;
    }
    /**
     * @return Returns the divOrg33.
     */
    public String getDivOrg33() {
        return divOrg33;
    }
    /**
     * @param divOrg33 The divOrg33 to set.
     */
    public void setDivOrg33(String divOrg33) {
        this.divOrg33 = divOrg33;
    }
    
    /**
     * @param name
     * @param i
     * @param j
     */
    public void setDivOrg(String name, int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        divOrg11 = name;
                        break;
                    case 2:
                        divOrg12 = name;
                        break;
                    case 3:
                        divOrg13 = name;
                        break;
                }
                break;
            case 2:
                switch(j) {
                    case 1:
                        divOrg21 = name;
                        break;
                    case 2:
                        divOrg22 = name;
                        break;
                    case 3:
                        divOrg23 = name;
                        break;
                }
                break;
            case 3:
                switch(j) {
                    case 1:
                        divOrg31 = name;
                        break;
                    case 2:
                        divOrg32 = name;
                        break;
                    case 3:
                        divOrg33 = name;
                        break;
                }
                break;
        }
    }

    public String getDivOrg(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return divOrg11;
                    case 2:
                        return divOrg12;
                    case 3:
                        return divOrg13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return divOrg21;
                    case 2:
                        return divOrg22;
                    case 3:
                        return divOrg23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return divOrg31;
                    case 2:
                        return divOrg32;
                    case 3:
                        return divOrg33;
                }
        }
        return null;
    }
    
    public String getLink() {
        String link = "";
        link += (!Util.empty(divOrg11)) ? "&divOrg11="+divOrg11 : "";
        link += (!Util.empty(divOrg12)) ? "&divOrg12="+divOrg12 : "";
        link += (!Util.empty(divOrg13)) ? "&divOrg13="+divOrg13 : "";
        link += (!Util.empty(divOrg21)) ? "&divOrg21="+divOrg21 : "";
        link += (!Util.empty(divOrg22)) ? "&divOrg22="+divOrg22 : "";
        link += (!Util.empty(divOrg23)) ? "&divOrg23="+divOrg23 : "";
        link += (!Util.empty(divOrg31)) ? "&divOrg31="+divOrg31 : "";
        link += (!Util.empty(divOrg32)) ? "&divOrg32="+divOrg32 : "";
        link += (!Util.empty(divOrg33)) ? "&divOrg33="+divOrg33 : "";
        link += (!Util.empty(country1)) ? "&country1="+country1 : "";
        link += (!Util.empty(country2)) ? "&country2="+country2 : "";
        link += (!Util.empty(country3)) ? "&country3="+country3 : "";
        return link;
    }

    public String getCountry(int i) {
        switch(i) {
            case 1:
                return country1;
            case 2:
                return country2;
            case 3:
                return country3;
        }
        return null;
    }

    public String[] getDivOrgValues(int i, int j) {
        String value = getDivOrg(i,j);
        if(Util.empty(value)) {
            return new String[0];
        }
        String[] s = {value};
        return s;
    }
    
    public String[] getDivGeoValues(int i) {
        String value = null;
        switch(i) {
            case 1:
                value = getCountry1();
                break;
            case 2:
                value = getCountry2();
                break;
            default:
                value = getCountry3();
                break;
        }
        if(Util.empty(value)) {
            return new String[0];
        }
        String[] s = {value};
        return s;
    }

    public BookmarkData getBookmark(int action) {
        BookmarkData data = new BookmarkData(action);
        if(!Util.empty(divOrg11)) {
            data.addParameter("divOrg11",divOrg11);
        }
        if(!Util.empty(divOrg12)) {
            data.addParameter("divOrg12",divOrg12);
        }
        if(!Util.empty(divOrg13)) {
            data.addParameter("divOrg13",divOrg13);
        }
        if(!Util.empty(divOrg21)) {
            data.addParameter("divOrg21",divOrg21);
        }
        if(!Util.empty(divOrg22)) {
            data.addParameter("divOrg22",divOrg22);
        }
        if(!Util.empty(divOrg23)) {
            data.addParameter("divOrg23",divOrg23);
        }
        if(!Util.empty(divOrg31)) {
            data.addParameter("divOrg31",divOrg31);
        }
        if(!Util.empty(divOrg32)) {
            data.addParameter("divOrg32",divOrg32);
        }
        if(!Util.empty(divOrg33)) {
            data.addParameter("divOrg33",divOrg33);
        }
        if(!Util.empty(country1)) {
            data.addParameter("country1",country1);
        }
        if(!Util.empty(country2)) {
            data.addParameter("country2",country2);
        }
        if(!Util.empty(country3)) {
            data.addParameter("country3",country3);
        }
        return data;
    }

    public GeoLabelData getGeoLabelData() {
        Collection items1 = new LinkedList();
        if(Util.empty(country2)) {
            items1.add(new GeoItemData(Integer.valueOf(country1),"",true,null));
        }else{
            if(Util.empty(country3)) {
                Collection items2 = new LinkedList();
                items2.add(new GeoItemData(Integer.valueOf(country2),"",true,null));
                GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
                items1.add(new GeoItemData(Integer.valueOf(country1),"",true,level1));
            }else {
                Collection items3 = new LinkedList();
                items3.add(new GeoItemData(Integer.valueOf(country3),"",true,null));
                GeoLabelData level2 = new GeoLabelData(null,null,null,items3);
                
                Collection items2 = new LinkedList();
                items2.add(new GeoItemData(Integer.valueOf(country2),"",true,level2));
                GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
                
                items1.add(new GeoItemData(Integer.valueOf(country1),"",true,level1));
            }
        }
        return new GeoLabelData(null,null,null,items1);        
    }

    public  ArrayList<DivOrgData>  getDivOrgs(UserSessionData userSession) {
        DivOrgAttribute[] divOrgs = userSession.getFilter().getDivOrgsAttrs();
        ArrayList<DivOrgData> list = new ArrayList<DivOrgData>();
        if(divOrgs[0] != null) {
            DivOrgAttribute divOrgAttribute = divOrgs[0];
            LabelItemData labelData = getDivOrgData(divOrg11,divOrg12,divOrg13);
            if(labelData != null) {
                list.add(new DivOrgData(divOrgAttribute.getId(),divOrgAttribute.getName(),labelData));
            }
        }
        if(divOrgs[1] != null) {
            DivOrgAttribute divOrgAttribute = divOrgs[1];
            LabelItemData labelData = getDivOrgData(divOrg21,divOrg22,divOrg23);
            if(labelData != null) {
                list.add(new DivOrgData(divOrgAttribute.getId(),divOrgAttribute.getName(),labelData));
            }
        }
        if(divOrgs[2] != null) {
            DivOrgAttribute divOrgAttribute = divOrgs[2];
            LabelItemData labelData = getDivOrgData(divOrg31,divOrg32,divOrg33);
            if(labelData != null) {
                list.add(new DivOrgData(divOrgAttribute.getId(),divOrgAttribute.getName(),labelData));
            }
        }
        return list;
    }

    private LabelItemData getDivOrgData(String item1,String item2,String item3) {
        Collection items1 = new LinkedList();
        if(Util.empty(item2)) {
            items1.add(new ItemData(Integer.valueOf(item1),"",true,null));
        }else{
            if(Util.empty(item3)) {
                Collection items2 = new LinkedList();
                items2.add(new ItemData(Integer.valueOf(item2),"",true,null));
                LabelItemData level1 = new LabelItemData(null,null,items2,null);
                items1.add(new ItemData(Integer.valueOf(item1),"",true,level1));
            }else {
                Collection items3 = new LinkedList();
                items3.add(new ItemData(Integer.valueOf(item3),"",true,null));
                LabelItemData level2 = new LabelItemData(null,null,items3,null);
                
                Collection items2 = new LinkedList();
                items2.add(new ItemData(Integer.valueOf(item2),"",true,level2));
                LabelItemData level1 = new LabelItemData(null,null,items2,null);
                
                items1.add(new ItemData(Integer.valueOf(item1),"",true,level1));
                
            }
        }
        return new LabelItemData(null,null,items1,null);     
    }
}
