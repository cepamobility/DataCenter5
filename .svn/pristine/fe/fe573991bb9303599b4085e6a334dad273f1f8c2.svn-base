/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.vehicle;


import org.apache.struts.action.ActionForm;

/**
 * @author jrodriguez
 */
public class VehicleDivOrgForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    //divOrg1* almacenan los tres niveles (el id) de profundidad asociados a la primera división organizativa    
    protected String divOrg11;
    protected String divOrg12;
    protected String divOrg13;
    
    //divOrg2* almacenan los tres niveles (el id) de profundidad asociados a la segunda división organizativa    
    protected String divOrg21;
    protected String divOrg22;
    protected String divOrg23;
    
    //divOrg3* almacenan los tres niveles (el id) de profundidad asociados a la segunda división organizativa    
    protected String divOrg31;
    protected String divOrg32;
    protected String divOrg33;
    
	// country1, country2 y country3 almacenan los tres niveles (el id) de profundidad del arbol de divisiones 
    // geográficas asociados a un conductor    
    protected String country1;
    protected String country2;
    protected String country3;

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
    
}
