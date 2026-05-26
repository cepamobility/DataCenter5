/**
 * Created on 23-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;

public class DivisionData implements Serializable {

    protected Integer divOrg1Id;
    protected Integer divOrg2Id;
    protected Integer divOrg3Id;
    protected Integer divOrg4Id;

    public DivisionData() {
        super();
    }

    public boolean equalDivisions(DivisionData data) {
        if(divOrg1Id != null && !divOrg1Id.equals(data.divOrg1Id)) {
            return false;
        }
        if(divOrg2Id != null && !divOrg2Id.equals(data.divOrg2Id)) {
            return false;
        }
        if(divOrg3Id != null && !divOrg3Id.equals(data.divOrg3Id)) {
            return false;
        }
        if(divOrg4Id != null && !divOrg4Id.equals(data.divOrg4Id)) {
            return false;
        }
        return true;
    }

    public Integer getDivOrg1Id() {
        return divOrg1Id;
    }

    public Integer getDivOrg2Id() {
        return divOrg2Id;
    }

    public Integer getDivOrg3Id() {
        return divOrg3Id;
    }

    public Integer getDivOrg4Id() {
        return divOrg4Id;
    }

    public void setDivOrg1Id(Integer divOrg1Id) {
        this.divOrg1Id = divOrg1Id;
    }

    public void setDivOrg2Id(Integer divOrg2Id) {
        this.divOrg2Id = divOrg2Id;
    }

    public void setDivOrg3Id(Integer divOrg3Id) {
        this.divOrg3Id = divOrg3Id;
    }

    public void setDivOrg4Id(Integer divOrg4Id) {
        this.divOrg4Id = divOrg4Id;
    }

}
