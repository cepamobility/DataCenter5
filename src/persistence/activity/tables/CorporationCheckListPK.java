/**
 * Created on 11-mar-2008
 * CEPA
 * DataCenter 5
 */
package persistence.activity.tables;

import java.io.Serializable;

public class CorporationCheckListPK implements Serializable {

    private Integer corporation;
    private CheckList checkList;
    private Integer type;
    
    public CheckList getCheckList() {
        return checkList;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public Integer getType() {
        return type;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    
}
