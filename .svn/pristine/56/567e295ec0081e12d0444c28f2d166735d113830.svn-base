package business.alert.reporthandler;

import java.io.Serializable;

public class AlertDivisionForm implements Serializable{

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

    
    public AlertDivisionForm() {
        super();
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

    
}
