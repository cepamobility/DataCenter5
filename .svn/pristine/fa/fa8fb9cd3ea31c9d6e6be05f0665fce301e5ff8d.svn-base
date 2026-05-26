/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;



public class FineTypeData extends TypeData implements Serializable {

    private int extserious;
    private int serious;
    private int half;
    private int nodata;
    private int others;
    private int soft;

    public FineTypeData() {
    }

    public FineTypeData(int count,int cost,String eventType,String fineType, Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name) {
        super(count,cost,eventType,divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,divOrg4Id,divOrg4Name,"fine");
        if(fineType.equals("datatype.finetype.extserious")) {
            extserious = count;
        }else if(fineType.equals("datatype.finetype.serious")) {
            serious = count;
        }else if(fineType.equals("datatype.finetype.half")) {
            half = count;
        }else if(fineType.equals("datatype.finetype.nodata")) {
            nodata = count;
        }else if(fineType.equals("datatype.finetype.others")) {
            others = count;
        }else {
            soft = count;
        }
    }

    public int getExtserious() {
        return extserious;
    }

    public int getHalf() {
        return half;
    }

    public int getNodata() {
        return nodata;
    }

    public int getOthers() {
        return others;
    }

    public int getSerious() {
        return serious;
    }

    public int getSoft() {
        return soft;
    }

    public void setExtserious(int extserious) {
        this.extserious = extserious;
    }

    public void setHalf(int half) {
        this.half = half;
    }

    public void setNodata(int nodata) {
        this.nodata = nodata;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public void setSerious(int serious) {
        this.serious = serious;
    }

    public void setSoft(int soft) {
        this.soft = soft;
    }

    public void addData(TypeData data) {
        super.addData(data);
        this.extserious += ((FineTypeData)data).extserious;
        this.serious += ((FineTypeData)data).serious;
        this.half += ((FineTypeData)data).half;
        this.nodata += ((FineTypeData)data).nodata;
        this.others += ((FineTypeData)data).others;
        this.soft += ((FineTypeData)data).soft;
    }

}
