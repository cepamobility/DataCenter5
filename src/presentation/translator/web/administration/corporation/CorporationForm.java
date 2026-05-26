/**
 * Created on 17-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import org.apache.struts.action.ActionForm;

import communication.administration.corporation.tables.CorporationData;

import presentation.translator.web.util.Util;

/**
 * @author jrodriguez
 */
public class CorporationForm extends ActionForm {
    
    private static final long serialVersionUID = 1L;

    protected String corporationName;
    protected String type;
    
    protected String divOrgCount;    
    
    protected String spanish1;
    protected String english1;
    protected String portuguese1;
    
    protected String levelCount1;

    protected String spanish11;
    protected String english11;
    protected String portuguese11;
    
    protected String spanish12;
    protected String english12;
    protected String portuguese12;
    
    protected String spanish13;
    protected String english13;
    protected String portuguese13;
    
    protected String spanish2;
    protected String english2;
    protected String portuguese2;
    
    protected String levelCount2;

    protected String spanish21;
    protected String english21;
    protected String portuguese21;
    
    protected String spanish22;
    protected String english22;
    protected String portuguese22;
    
    protected String spanish23;
    protected String english23;
    protected String portuguese23;

    protected String spanish3;
    protected String english3;
    protected String portuguese3;
    
    protected String levelCount3;

    protected String spanish31;
    protected String english31;
    protected String portuguese31;
    
    protected String spanish32;
    protected String english32;
    protected String portuguese32;
    
    protected String spanish33;
    protected String english33;
    protected String portuguese33;

    protected String spanish4;
    protected String english4;
    protected String portuguese4;
    
    protected String levelCount4;

    protected String spanish41;
    protected String english41;
    protected String portuguese41;
    
    protected String spanish42;
    protected String english42;
    protected String portuguese42;
    
    protected String spanish43;
    protected String english43;
    protected String portuguese43;

    protected String showLevel11;
    protected String showLevel12;
    protected String showLevel13;
    protected String showLevel21;
    protected String showLevel22;
    protected String showLevel23;
    protected String showLevel31;
    protected String showLevel32;
    protected String showLevel33;
    
    public CorporationForm() {
    }

    public String getCorporationName() {
        return corporationName;
    }

    public String getDivOrgCount() {
        return divOrgCount;
    }

    public String getEnglish1() {
        return english1;
    }

    public String getEnglish11() {
        return english11;
    }

    public String getEnglish12() {
        return english12;
    }

    public String getEnglish13() {
        return english13;
    }

    public String getEnglish2() {
        return english2;
    }

    public String getEnglish21() {
        return english21;
    }

    public String getEnglish22() {
        return english22;
    }

    public String getEnglish23() {
        return english23;
    }

    public String getEnglish3() {
        return english3;
    }

    public String getEnglish31() {
        return english31;
    }

    public String getEnglish32() {
        return english32;
    }

    public String getEnglish33() {
        return english33;
    }

    public String getLevelCount1() {
        return levelCount1;
    }

    public String getLevelCount2() {
        return levelCount2;
    }

    public String getLevelCount3() {
        return levelCount3;
    }

    public String getPortuguese1() {
        return portuguese1;
    }

    public String getPortuguese11() {
        return portuguese11;
    }

    public String getPortuguese12() {
        return portuguese12;
    }

    public String getPortuguese13() {
        return portuguese13;
    }

    public String getPortuguese2() {
        return portuguese2;
    }

    public String getPortuguese21() {
        return portuguese21;
    }

    public String getPortuguese22() {
        return portuguese22;
    }

    public String getPortuguese23() {
        return portuguese23;
    }

    public String getPortuguese3() {
        return portuguese3;
    }

    public String getPortuguese31() {
        return portuguese31;
    }

    public String getPortuguese32() {
        return portuguese32;
    }

    public String getPortuguese33() {
        return portuguese33;
    }

    public String getSpanish1() {
        return spanish1;
    }

    public String getSpanish11() {
        return spanish11;
    }

    public String getSpanish12() {
        return spanish12;
    }

    public String getSpanish13() {
        return spanish13;
    }

    public String getSpanish2() {
        return spanish2;
    }

    public String getSpanish21() {
        return spanish21;
    }

    public String getSpanish22() {
        return spanish22;
    }

    public String getSpanish23() {
        return spanish23;
    }

    public String getSpanish3() {
        return spanish3;
    }

    public String getSpanish31() {
        return spanish31;
    }

    public String getSpanish32() {
        return spanish32;
    }

    public String getSpanish33() {
        return spanish33;
    }
    
    public String getEnglish4() {
        return english4;
    }

    public String getEnglish41() {
        return english41;
    }

    public String getEnglish42() {
        return english42;
    }

    public String getEnglish43() {
        return english43;
    }

    public String getLevelCount4() {
        return levelCount4;
    }

    public String getPortuguese4() {
        return portuguese4;
    }

    public String getPortuguese41() {
        return portuguese41;
    }

    public String getPortuguese42() {
        return portuguese42;
    }

    public String getPortuguese43() {
        return portuguese43;
    }

    public String getSpanish4() {
        return spanish4;
    }

    public String getSpanish41() {
        return spanish41;
    }

    public String getSpanish42() {
        return spanish42;
    }

    public String getSpanish43() {
        return spanish43;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public void setDivOrgCount(String divOrgCount) {
        this.divOrgCount = divOrgCount;
    }

    public void setEnglish1(String english1) {
        this.english1 = english1;
    }

    public void setEnglish11(String english11) {
        this.english11 = english11;
    }

    public void setEnglish12(String english12) {
        this.english12 = english12;
    }

    public void setEnglish13(String english13) {
        this.english13 = english13;
    }

    public void setEnglish2(String english2) {
        this.english2 = english2;
    }

    public void setEnglish21(String english21) {
        this.english21 = english21;
    }

    public void setEnglish22(String english22) {
        this.english22 = english22;
    }

    public void setEnglish23(String english23) {
        this.english23 = english23;
    }

    public void setEnglish3(String english3) {
        this.english3 = english3;
    }

    public void setEnglish31(String english31) {
        this.english31 = english31;
    }

    public void setEnglish32(String english32) {
        this.english32 = english32;
    }

    public void setEnglish33(String english33) {
        this.english33 = english33;
    }

    public void setLevelCount1(String levelCount1) {
        this.levelCount1 = levelCount1;
    }

    public void setLevelCount2(String levelCount2) {
        this.levelCount2 = levelCount2;
    }

    public void setLevelCount3(String levelCount3) {
        this.levelCount3 = levelCount3;
    }

    public void setPortuguese1(String portuguese1) {
        this.portuguese1 = portuguese1;
    }

    public void setPortuguese11(String portuguese11) {
        this.portuguese11 = portuguese11;
    }

    public void setPortuguese12(String portuguese12) {
        this.portuguese12 = portuguese12;
    }

    public void setPortuguese13(String portuguese13) {
        this.portuguese13 = portuguese13;
    }

    public void setPortuguese2(String portuguese2) {
        this.portuguese2 = portuguese2;
    }

    public void setPortuguese21(String portuguese21) {
        this.portuguese21 = portuguese21;
    }

    public void setPortuguese22(String portuguese22) {
        this.portuguese22 = portuguese22;
    }

    public void setPortuguese23(String portuguese23) {
        this.portuguese23 = portuguese23;
    }

    public void setPortuguese3(String portuguese3) {
        this.portuguese3 = portuguese3;
    }

    public void setPortuguese31(String portuguese31) {
        this.portuguese31 = portuguese31;
    }

    public void setPortuguese32(String portuguese32) {
        this.portuguese32 = portuguese32;
    }

    public void setPortuguese33(String portuguese33) {
        this.portuguese33 = portuguese33;
    }

    public void setSpanish1(String spanish1) {
        this.spanish1 = spanish1;
    }

    public void setSpanish11(String spanish11) {
        this.spanish11 = spanish11;
    }

    public void setSpanish12(String spanish12) {
        this.spanish12 = spanish12;
    }

    public void setSpanish13(String spanish13) {
        this.spanish13 = spanish13;
    }

    public void setSpanish2(String spanish2) {
        this.spanish2 = spanish2;
    }

    public void setSpanish21(String spanish21) {
        this.spanish21 = spanish21;
    }

    public void setSpanish22(String spanish22) {
        this.spanish22 = spanish22;
    }

    public void setSpanish23(String spanish23) {
        this.spanish23 = spanish23;
    }

    public void setSpanish3(String spanish3) {
        this.spanish3 = spanish3;
    }

    public void setSpanish31(String spanish31) {
        this.spanish31 = spanish31;
    }

    public void setSpanish32(String spanish32) {
        this.spanish32 = spanish32;
    }

    public void setSpanish33(String spanish33) {
        this.spanish33 = spanish33;
    }

    public void setEnglish4(String english4) {
        this.english4 = english4;
    }

    public void setEnglish41(String english41) {
        this.english41 = english41;
    }

    public void setEnglish42(String english42) {
        this.english42 = english42;
    }

    public void setEnglish43(String english43) {
        this.english43 = english43;
    }

    public void setLevelCount4(String levelCount4) {
        this.levelCount4 = levelCount4;
    }

    public void setPortuguese4(String portuguese4) {
        this.portuguese4 = portuguese4;
    }

    public void setPortuguese41(String portuguese41) {
        this.portuguese41 = portuguese41;
    }

    public void setPortuguese42(String portuguese42) {
        this.portuguese42 = portuguese42;
    }

    public void setPortuguese43(String portuguese43) {
        this.portuguese43 = portuguese43;
    }

    public void setSpanish4(String spanish4) {
        this.spanish4 = spanish4;
    }

    public void setSpanish41(String spanish41) {
        this.spanish41 = spanish41;
    }

    public void setSpanish42(String spanish42) {
        this.spanish42 = spanish42;
    }

    public void setSpanish43(String spanish43) {
        this.spanish43 = spanish43;
    }

    public String validate() {
        if(Util.empty(corporationName)) {
            return "corporation.error.emptyname";
        }
        if(Integer.parseInt(type) != CorporationData.CEPA_SYSTEM) {
	        switch(Integer.parseInt(divOrgCount)) {
	            case 3:
	                if(Util.empty(spanish3) || Util.empty(english3) || Util.empty(portuguese3)) {
	                    return "corporation.error.emptydivorgname";
	                }
	                switch(Integer.parseInt(levelCount3)) {
	                    case 3:
	                        if(Util.empty(spanish33) || Util.empty(english33) || Util.empty(portuguese33)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                    case 2:
	                        if(Util.empty(spanish32) || Util.empty(english32) || Util.empty(portuguese32)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                    case 1:
	                        if(Util.empty(spanish31) || Util.empty(english31) || Util.empty(portuguese31)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                }
	            case 2:
	                if(Util.empty(spanish2) || Util.empty(english2) || Util.empty(portuguese2)) {
	                    return "corporation.error.emptydivorgname";
	                }
	                switch(Integer.parseInt(levelCount2)) {
	                    case 3:
	                        if(Util.empty(spanish23) || Util.empty(english23) || Util.empty(portuguese23)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                    case 2:
	                        if(Util.empty(spanish22) || Util.empty(english22) || Util.empty(portuguese22)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                    case 1:
	                        if(Util.empty(spanish21) || Util.empty(english21) || Util.empty(portuguese21)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                }
	            case 1:
	                if(Util.empty(spanish1) || Util.empty(english1) || Util.empty(portuguese1)) {
	                    return "corporation.error.emptydivorgname";
	                }
	                switch(Integer.parseInt(levelCount1)) {
	                    case 3:
	                        if(Util.empty(spanish13) || Util.empty(english13) || Util.empty(portuguese13)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                    case 2:
	                        if(Util.empty(spanish12) || Util.empty(english12) || Util.empty(portuguese12)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                    case 1:
	                        if(Util.empty(spanish11) || Util.empty(english11) || Util.empty(portuguese11)) {
	                            return "corporation.error.emptylevelname";
	                        }
	                }
	        }
        }
        return null;
    }

    public String getEnglish(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return english11;
                    case 2:
                        return english12;
                    case 3:
                        return english13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return english21;
                    case 2:
                        return english22;
                    case 3:
                        return english23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return english31;
                    case 2:
                        return english32;
                    case 3:
                        return english33;
                }
            case 4:
                switch(j) {
                    case 1:
                        return english41;
                    case 2:
                        return english42;
                    case 3:
                        return english43;
                }
        }
        return null;
    }

    public String getEnglish(int i) {
        switch(i) {
            case 1:
                return english1;
            case 2:
                return english2;
            case 3:
                return english3;
        }
        return null;
    }

    public String getSpanish(int i) {
        switch(i) {
            case 1:
                return spanish1;
            case 2:
                return spanish2;
            case 3:
                return spanish3;
        }
        return null;
    }

    public String getPortuguese(int i) {
        switch(i) {
            case 1:
                return portuguese1;
            case 2:
                return portuguese2;
            case 3:
                return portuguese3;
        }
        return null;
    }

    public String getSpanish(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return spanish11;
                    case 2:
                        return spanish12;
                    case 3:
                        return spanish13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return spanish21;
                    case 2:
                        return spanish22;
                    case 3:
                        return spanish23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return spanish31;
                    case 2:
                        return spanish32;
                    case 3:
                        return spanish33;
                }
            case 4:
                switch(j) {
                    case 1:
                        return spanish41;
                    case 2:
                        return spanish42;
                    case 3:
                        return spanish43;
                }
        }
        return null;
    }

    public String getPortuguese(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return portuguese11;
                    case 2:
                        return portuguese12;
                    case 3:
                        return portuguese13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return portuguese21;
                    case 2:
                        return portuguese22;
                    case 3:
                        return portuguese23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return portuguese31;
                    case 2:
                        return portuguese32;
                    case 3:
                        return portuguese33;
                }
            case 4:
                switch(j) {
                    case 1:
                        return portuguese41;
                    case 2:
                        return portuguese42;
                    case 3:
                        return portuguese43;
                }
        }
        return null;
    }

    public int getLevelCount(int i) {
        switch(i) {
            case 1:
               if(!Util.empty(levelCount1)) {
                   return Integer.parseInt(levelCount1);
               }
               break;
            case 2:
                if(!Util.empty(levelCount2)) {
                    return Integer.parseInt(levelCount2);
                }
                break;
            case 3:
                if(!Util.empty(levelCount3)) {
                    return Integer.parseInt(levelCount3);
                }
                break;
            case 4:
                if(!Util.empty(levelCount4)) {
                    return Integer.parseInt(levelCount4);
                }
                break;
        }
        return 0;
    }

    public String getShowLevel11() {
        return showLevel11;
    }

    public String getShowLevel12() {
        return showLevel12;
    }

    public String getShowLevel13() {
        return showLevel13;
    }

    public String getShowLevel21() {
        return showLevel21;
    }

    public String getShowLevel22() {
        return showLevel22;
    }

    public String getShowLevel23() {
        return showLevel23;
    }

    public String getShowLevel31() {
        return showLevel31;
    }

    public String getShowLevel32() {
        return showLevel32;
    }

    public String getShowLevel33() {
        return showLevel33;
    }

    public void setShowLevel11(String showLevel11) {
        this.showLevel11 = showLevel11;
    }

    public void setShowLevel12(String showLevel12) {
        this.showLevel12 = showLevel12;
    }

    public void setShowLevel13(String showLevel13) {
        this.showLevel13 = showLevel13;
    }

    public void setShowLevel21(String showLevel21) {
        this.showLevel21 = showLevel21;
    }

    public void setShowLevel22(String showLevel22) {
        this.showLevel22 = showLevel22;
    }

    public void setShowLevel23(String showLevel23) {
        this.showLevel23 = showLevel23;
    }

    public void setShowLevel31(String showLevel31) {
        this.showLevel31 = showLevel31;
    }

    public void setShowLevel32(String showLevel32) {
        this.showLevel32 = showLevel32;
    }

    public void setShowLevel33(String showLevel33) {
        this.showLevel33 = showLevel33;
    }

    public String getShowLevel(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return showLevel11;
                    case 2:
                        return showLevel12;
                    case 3:
                        return showLevel13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return showLevel21;
                    case 2:
                        return showLevel22;
                    case 3:
                        return showLevel23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return showLevel31;
                    case 2:
                        return showLevel32;
                    case 3:
                        return showLevel33;
                }
        }
        return null;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
