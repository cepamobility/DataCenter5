package presentation.translator.web.administration.corporation;

import communication.administration.corporation.tables.CorporationData;

public class CorporationSearchForm extends CorporationForm {

    private static final long serialVersionUID = 1L   ;

    private int corporationId; 

    private int divOrgId1; 
    private int divOrgId2; 
    private int divOrgId3; 
    private int divOrgId4; 
    
    private int divOrgLevelId11; 
    private int divOrgLevelId12; 
    private int divOrgLevelId13; 
    
    private int divOrgLevelId21; 
    private int divOrgLevelId22; 
    private int divOrgLevelId23; 
    
    private int divOrgLevelId31; 
    private int divOrgLevelId32; 
    private int divOrgLevelId33; 
    
    private int divOrgLevelId41; 
    private int divOrgLevelId42; 
    private int divOrgLevelId43;
    
    public CorporationSearchForm() {
        super();
    }

    public CorporationSearchForm(Integer id,CorporationData data) {
 /*       this.corporationId = id.intValue();
        this.corporationName = data.getName();
        this.divOrg = String.valueOf(data.getDivOrgDataSet().size());
        Iterator it = data.getDivOrgDataSet().iterator();
        
        int i = 1;
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            setDivOrgId(i,divOrgData.getId().intValue());
            setDivOrgName(i,divOrgData.getName());

            LabelItemData lid = divOrgData.getDivOrgLevels();
            if(lid != null) {
                setDivOrgLevelName(i,1,lid.getName());
                setDivOrgLevelId(i,1,lid.getId().intValue());
                
                Iterator it2 = lid.getItems().iterator();
                if(it2.hasNext()) {
                    ItemData item1 = (ItemData) it2.next();
                    if(item1.getSubItems() == null) {
                        setLevelCount(i,1);
                    }else {
                        LabelItemData lid2 = item1.getSubItems();
                        setDivOrgLevelId(i,2,lid2.getId().intValue());
    	                setDivOrgLevelName(i,2,lid2.getName());    	                
    	                Iterator it3 = lid2.getItems().iterator();
    	                if(it3.hasNext()) {
    	                    ItemData item2 = (ItemData) it3.next();
    	                    if(item2.getSubItems() == null) {
    	                        setLevelCount(i,2);
    	                    }else {
    	                        setLevelCount(i,3);
    	                        LabelItemData lid3 = item2.getSubItems();
    	                        setDivOrgLevelId(i,3,lid3.getId().intValue());
    	                        setDivOrgLevelName(i,3,lid3.getName());
    	                    }
    	                }
                    }
                }
                i++;
            }
        }
*/
    }
    
    /**
     * @param i
     * @param j
     * @param k
     */
    private void setDivOrgLevelId(int divOrg, int level, int id) {
        switch(divOrg) {
            case 1:
                switch(level) {
                    case 1:
                        divOrgLevelId11 = id;
                        break;
                    case 2:
                        divOrgLevelId12 = id;
                        break;
                    case 3:
                        divOrgLevelId13 = id;
                        break;
                }
                break;
            case 2:
                switch(level) {
                    case 1:
                        divOrgLevelId21 = id;
                        break;
                    case 2:
                        divOrgLevelId22 = id;
                        break;
                    case 3:
                        divOrgLevelId23 = id;
                        break;
                }
                break;
            case 3:
                switch(level) {
                    case 1:
                        divOrgLevelId31 = id;
                        break;
                    case 2:
                        divOrgLevelId32 = id;
                        break;
                    case 3:
                        divOrgLevelId33 = id;
                        break;
                }
                break;
            case 4:
                switch(level) {
                    case 1:
                        divOrgLevelId41 = id;
                        break;
                    case 2:
                        divOrgLevelId42 = id;
                        break;
                    case 3:
                        divOrgLevelId43 = id;
                        break;
                }
        }
    }

    /**
     * @param i
     * @param j
     */
    private void setDivOrgId(int divOrg, int id) {
        switch(divOrg) {
            case 1:
                divOrgId1 = id;
                break;
            case 2:
                divOrgId2 = id;
                break;
            case 3:
                divOrgId3 = id;
                break;
            case 4:
                divOrgId4 = id;
                break;
        }
    }

    /**
     * @return Returns the corporationId.
     */
    public int getCorporationId() {
        return corporationId;
    }
    /**
     * @param corporationId The corporationId to set.
     */
    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }
    /**
     * @return Returns the divOrgId1.
     */
    public int getDivOrgId1() {
        return divOrgId1;
    }
    /**
     * @param divOrgId1 The divOrgId1 to set.
     */
    public void setDivOrgId1(int divOrgId1) {
        this.divOrgId1 = divOrgId1;
    }
    /**
     * @return Returns the divOrgId2.
     */
    public int getDivOrgId2() {
        return divOrgId2;
    }
    /**
     * @param divOrgId2 The divOrgId2 to set.
     */
    public void setDivOrgId2(int divOrgId2) {
        this.divOrgId2 = divOrgId2;
    }
    /**
     * @return Returns the divOrgId3.
     */
    public int getDivOrgId3() {
        return divOrgId3;
    }
    /**
     * @param divOrgId3 The divOrgId3 to set.
     */
    public void setDivOrgId3(int divOrgId3) {
        this.divOrgId3 = divOrgId3;
    }
    /**
     * @return Returns the divOrgId4.
     */
    public int getDivOrgId4() {
        return divOrgId4;
    }
    /**
     * @param divOrgId4 The divOrgId4 to set.
     */
    public void setDivOrgId4(int divOrgId4) {
        this.divOrgId4 = divOrgId4;
    }
    /**
     * @return Returns the divOrgLevelId11.
     */
    public int getDivOrgLevelId11() {
        return divOrgLevelId11;
    }
    /**
     * @param divOrgLevelId11 The divOrgLevelId11 to set.
     */
    public void setDivOrgLevelId11(int divOrgLevelId11) {
        this.divOrgLevelId11 = divOrgLevelId11;
    }
    /**
     * @return Returns the divOrgLevelId12.
     */
    public int getDivOrgLevelId12() {
        return divOrgLevelId12;
    }
    /**
     * @param divOrgLevelId12 The divOrgLevelId12 to set.
     */
    public void setDivOrgLevelId12(int divOrgLevelId12) {
        this.divOrgLevelId12 = divOrgLevelId12;
    }
    /**
     * @return Returns the divOrgLevelId13.
     */
    public int getDivOrgLevelId13() {
        return divOrgLevelId13;
    }
    /**
     * @param divOrgLevelId13 The divOrgLevelId13 to set.
     */
    public void setDivOrgLevelId13(int divOrgLevelId13) {
        this.divOrgLevelId13 = divOrgLevelId13;
    }
    /**
     * @return Returns the divOrgLevelId21.
     */
    public int getDivOrgLevelId21() {
        return divOrgLevelId21;
    }
    /**
     * @param divOrgLevelId21 The divOrgLevelId21 to set.
     */
    public void setDivOrgLevelId21(int divOrgLevelId21) {
        this.divOrgLevelId21 = divOrgLevelId21;
    }
    /**
     * @return Returns the divOrgLevelId22.
     */
    public int getDivOrgLevelId22() {
        return divOrgLevelId22;
    }
    /**
     * @param divOrgLevelId22 The divOrgLevelId22 to set.
     */
    public void setDivOrgLevelId22(int divOrgLevelId22) {
        this.divOrgLevelId22 = divOrgLevelId22;
    }
    /**
     * @return Returns the divOrgLevelId23.
     */
    public int getDivOrgLevelId23() {
        return divOrgLevelId23;
    }
    /**
     * @param divOrgLevelId23 The divOrgLevelId23 to set.
     */
    public void setDivOrgLevelId23(int divOrgLevelId23) {
        this.divOrgLevelId23 = divOrgLevelId23;
    }
    /**
     * @return Returns the divOrgLevelId31.
     */
    public int getDivOrgLevelId31() {
        return divOrgLevelId31;
    }
    /**
     * @param divOrgLevelId31 The divOrgLevelId31 to set.
     */
    public void setDivOrgLevelId31(int divOrgLevelId31) {
        this.divOrgLevelId31 = divOrgLevelId31;
    }
    /**
     * @return Returns the divOrgLevelId32.
     */
    public int getDivOrgLevelId32() {
        return divOrgLevelId32;
    }
    /**
     * @param divOrgLevelId32 The divOrgLevelId32 to set.
     */
    public void setDivOrgLevelId32(int divOrgLevelId32) {
        this.divOrgLevelId32 = divOrgLevelId32;
    }
    /**
     * @return Returns the divOrgLevelId33.
     */
    public int getDivOrgLevelId33() {
        return divOrgLevelId33;
    }
    /**
     * @param divOrgLevelId33 The divOrgLevelId33 to set.
     */
    public void setDivOrgLevelId33(int divOrgLevelId33) {
        this.divOrgLevelId33 = divOrgLevelId33;
    }
    /**
     * @return Returns the divOrgLevelId41.
     */
    public int getDivOrgLevelId41() {
        return divOrgLevelId41;
    }
    /**
     * @param divOrgLevelId41 The divOrgLevelId41 to set.
     */
    public void setDivOrgLevelId41(int divOrgLevelId41) {
        this.divOrgLevelId41 = divOrgLevelId41;
    }
    /**
     * @return Returns the divOrgLevelId42.
     */
    public int getDivOrgLevelId42() {
        return divOrgLevelId42;
    }
    /**
     * @param divOrgLevelId42 The divOrgLevelId42 to set.
     */
    public void setDivOrgLevelId42(int divOrgLevelId42) {
        this.divOrgLevelId42 = divOrgLevelId42;
    }
    /**
     * @return Returns the divOrgLevelId43.
     */
    public int getDivOrgLevelId43() {
        return divOrgLevelId43;
    }
    /**
     * @param divOrgLevelId43 The divOrgLevelId43 to set.
     */
    public void setDivOrgLevelId43(int divOrgLevelId43) {
        this.divOrgLevelId43 = divOrgLevelId43;
    }
    
    public Integer getDivOrgId(int divOrg) {
        switch(divOrg) {
            case 1:
                return (divOrgId1 != 0) ? new Integer(divOrgId1) : null;
            case 2:
                return (divOrgId2 != 0) ? new Integer(divOrgId2) : null;
            case 3:
                return (divOrgId3 != 0) ? new Integer(divOrgId3) : null;
            case 4:
                return (divOrgId4 != 0) ? new Integer(divOrgId4) : null;
        }
        return null;
    }
    
    public Integer getIdCorporation() {
        return new Integer(corporationId);
    }
    
    public Integer getDivOrgLevelId(int divOrg, int level) {
        switch(divOrg) {
            case 1:
                switch(level) {
                    case 1:
                        return (divOrgLevelId11 != 0) ? new Integer(divOrgLevelId11) : null;
                    case 2:
                        return (divOrgLevelId12 != 0) ? new Integer(divOrgLevelId12) : null;
                    case 3:
                        return (divOrgLevelId13 != 0) ? new Integer(divOrgLevelId13) : null;
                }
                break;
            case 2:
                switch(level) {
                    case 1:
                        return (divOrgLevelId21 != 0) ? new Integer(divOrgLevelId21) : null;
                    case 2:
                        return (divOrgLevelId22 != 0) ? new Integer(divOrgLevelId22) : null;
                    case 3:
                        return (divOrgLevelId23 != 0) ? new Integer(divOrgLevelId23) : null;
                }
                break;
            case 3:
                switch(level) {
                    case 1:
                        return (divOrgLevelId31 != 0) ? new Integer(divOrgLevelId31) : null;
                    case 2:
                        return (divOrgLevelId32 != 0) ? new Integer(divOrgLevelId32) : null;
                    case 3:
                        return (divOrgLevelId33 != 0) ? new Integer(divOrgLevelId33) : null;
                }
                break;
            case 4:
                switch(level) {
                    case 1:
                        return (divOrgLevelId41 != 0) ? new Integer(divOrgLevelId41) : null;
                    case 2:
                        return (divOrgLevelId42 != 0) ? new Integer(divOrgLevelId42) : null;
                    case 3:
                        return (divOrgLevelId43 != 0) ? new Integer(divOrgLevelId43) : null;
                }
        }
        return null;
    }
}
