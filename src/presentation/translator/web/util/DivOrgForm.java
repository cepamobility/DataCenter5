/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.util;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.apache.struts.action.ActionForm;

import communication.administration.bookmark.tables.BookmarkData;

/**
 * @author jrodriguez
 */
public class DivOrgForm extends ActionForm implements DivisionForm {

    private static final long serialVersionUID = 1L;

    protected String[] divOrg11;
    protected String[] divOrg12;
    protected String[] divOrg13;
    protected String[] divOrg21;
    protected String[] divOrg22;
    protected String[] divOrg23;
    protected String[] divOrg31;
    protected String[] divOrg32;
    protected String[] divOrg33;
    protected String[] country1;
    protected String[] country2;
    protected String[] country3;

    
    public DivOrgForm() {
        super();
    }


    public DivOrgForm(String[] divOrg11, String[] divOrg12, String[] divOrg13,
			String[] divOrg21, String[] divOrg22, String[] divOrg23,
			String[] divOrg31, String[] divOrg32, String[] divOrg33,
			String[] country1, String[] country2, String[] country3) {
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


	public DivOrgForm(BookmarkData bookmark) {
        divOrg11 = getList(bookmark,"divOrg11");
        divOrg12 = getList(bookmark,"divOrg12");
        divOrg13 = getList(bookmark,"divOrg13");
        divOrg21 = getList(bookmark,"divOrg21");
        divOrg22 = getList(bookmark,"divOrg22");
        divOrg23 = getList(bookmark,"divOrg23");
        divOrg31 = getList(bookmark,"divOrg31");
        divOrg32 = getList(bookmark,"divOrg32");
        divOrg33 = getList(bookmark,"divOrg33");
        country1 = getList(bookmark,"country1");
        country2 = getList(bookmark,"country2");
        country3 = getList(bookmark,"country3");
    }


    private String[] getList(BookmarkData bookmark, String key) {
        Hashtable parameters = bookmark.getParameters(); 
        if(!parameters.containsKey(key)) {
           return new String[0]; 
        }else {
            Collection list = new LinkedList();
            StringTokenizer tokenizer = new StringTokenizer((String)parameters.get(key));
            while(tokenizer.hasMoreTokens()) {
                list.add(tokenizer.nextToken());
            }
            return (String[])list.toArray(new String[0]);
        }
    }


    public String[] getCountry1() {
        return country1;
    }


    public String[] getCountry2() {
        return country2;
    }


    public String[] getCountry3() {
        return country3;
    }


    public String[] getDivOrg11() {
        return divOrg11;
    }


    public String[] getDivOrg12() {
        return divOrg12;
    }


    public String[] getDivOrg13() {
        return divOrg13;
    }


    public String[] getDivOrg21() {
        return divOrg21;
    }


    public String[] getDivOrg22() {
        return divOrg22;
    }


    public String[] getDivOrg23() {
        return divOrg23;
    }


    public String[] getDivOrg31() {
        return divOrg31;
    }


    public String[] getDivOrg32() {
        return divOrg32;
    }


    public String[] getDivOrg33() {
        return divOrg33;
    }


    public void setCountry1(String[] country1) {
        this.country1 = country1;
    }


    public void setCountry2(String[] country2) {
        this.country2 = country2;
    }


    public void setCountry3(String[] country3) {
        this.country3 = country3;
    }


    public void setDivOrg11(String[] divOrg11) {
        this.divOrg11 = divOrg11;
    }


    public void setDivOrg12(String[] divOrg12) {
        this.divOrg12 = divOrg12;
    }


    public void setDivOrg13(String[] divOrg13) {
        this.divOrg13 = divOrg13;
    }


    public void setDivOrg21(String[] divOrg21) {
        this.divOrg21 = divOrg21;
    }


    public void setDivOrg22(String[] divOrg22) {
        this.divOrg22 = divOrg22;
    }


    public void setDivOrg23(String[] divOrg23) {
        this.divOrg23 = divOrg23;
    }


    public void setDivOrg31(String[] divOrg31) {
        this.divOrg31 = divOrg31;
    }


    public void setDivOrg32(String[] divOrg32) {
        this.divOrg32 = divOrg32;
    }


    public void setDivOrg33(String[] divOrg33) {
        this.divOrg33 = divOrg33;
    }
    
    public String[] getDivOrg(int i, int j) {
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


    public String[] getDivOrgValues(int i, int j) {
        return getDivOrg(i,j);
    }


    public String[] getDivGeoValues(int i) {
        switch(i) {
            case 1:
                return country1;
            case 2:
                return country2;
            default:
                return country3;
        }
    }

    public BookmarkData getBookmark(int action) {
        BookmarkData data = new BookmarkData(action);
        if(divOrg11 != null && divOrg11.length > 0) {
            data.addParameter("divOrg11",getString(divOrg11));
        }
        if(divOrg12 != null && divOrg12.length > 0) {
            data.addParameter("divOrg12",getString(divOrg12));
        }
        if(divOrg13 != null && divOrg13.length > 0) {
            data.addParameter("divOrg13",getString(divOrg13));
        }
        if(divOrg21 != null && divOrg21.length > 0) {
            data.addParameter("divOrg21",getString(divOrg21));
        }
        if(divOrg22 != null && divOrg22.length > 0) {
            data.addParameter("divOrg22",getString(divOrg22));
        }
        if(divOrg23 != null && divOrg23.length > 0) {
            data.addParameter("divOrg23",getString(divOrg23));
        }
        if(divOrg31 != null && divOrg31.length > 0) {
            data.addParameter("divOrg31",getString(divOrg31));
        }
        if(divOrg32 != null && divOrg32.length > 0) {
            data.addParameter("divOrg32",getString(divOrg32));
        }
        if(divOrg33 != null && divOrg33.length > 0) {
            data.addParameter("divOrg33",getString(divOrg33));
        }
        if(country1 != null && country1.length > 0) {
            data.addParameter("country1",getString(country1));
        }
        if(country2 != null && country2.length > 0) {
            data.addParameter("country2",getString(country2));
        }
        if(country3 != null && country3.length > 0) {
            data.addParameter("country3",getString(country3));
        }
        return data;
    }


    protected String getString(String[] list) {
        String s = "";
        for(int i = 0; i < list.length; i++) {
            s += list[i] + " ";
        }
        return s.trim();
    }

    public String getLink() {
        String link = "";
        link += (divOrg11 != null && divOrg11.length > 0) ? "&divOrg11="+getIds(divOrg11) : "";
        link += (divOrg12 != null && divOrg12.length > 0) ? "&divOrg12="+getIds(divOrg12) : "";
        link += (divOrg13 != null && divOrg13.length > 0) ? "&divOrg13="+getIds(divOrg13) : "";
        link += (divOrg21 != null && divOrg21.length > 0) ? "&divOrg21="+getIds(divOrg21) : "";
        link += (divOrg22 != null && divOrg22.length > 0) ? "&divOrg22="+getIds(divOrg22) : "";
        link += (divOrg23 != null && divOrg23.length > 0) ? "&divOrg23="+getIds(divOrg23) : "";
        link += (divOrg31 != null && divOrg31.length > 0) ? "&divOrg31="+getIds(divOrg31) : "";
        link += (divOrg32 != null && divOrg32.length > 0) ? "&divOrg32="+getIds(divOrg32) : "";
        link += (divOrg33 != null && divOrg33.length > 0) ? "&divOrg33="+getIds(divOrg33) : "";
        link += (country1 != null && country1.length > 0) ? "&country1="+getIds(country1) : "";
        link += (country2 != null && country2.length > 0) ? "&country2="+getIds(country2) : "";
        link += (country3 != null && country3.length > 0) ? "&country3="+getIds(country3) : "";
        return link;
    }


	private String getIds(String[] list) {
		String s = "";
		if(list != null) {
			for (String string : list) {
				if(s.length() > 0) {
					s += ",";
				}
				s += string;
			}
		}
		return s;
	}
}
