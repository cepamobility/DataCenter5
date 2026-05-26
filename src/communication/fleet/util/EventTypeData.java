/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;

import communication.language.Text;



public class EventTypeData extends TypeData implements Serializable {

    protected int damage;
    protected int funcInjured;
    protected int otherInjured;
    protected int funcOtherInjured;
    protected int funcFatal;
    protected int otherFatal;
    protected int funcInjuredOtherFatal;
    protected int funcFatalOtherInjured;
    protected int funcOtherFatal;

    public EventTypeData() {
    }

    public EventTypeData(Object[] data,String type, int corporation, Text messages, int order1, int order2, int order3, int order4) {
        super(data,type,corporation,messages,order1,order2,order3,order4);
        String driverState = (data[4] == null) ? "" : (String)data[4];
        Integer fatOtherCount = (data[5] == null) ? new Integer(0) : (Integer)data[5];
        Integer injOtherCount = (data[6] == null) ? new Integer(0) : (Integer)data[6];
        Integer fatFuncCount = (data[7] == null) ? new Integer(0) : (Integer)data[7];
        Integer injFuncCount = (data[8] == null) ? new Integer(0) : (Integer)data[8];
        if(driverState.equals("datatype.driverstate.dead") || fatFuncCount.intValue() > 0) {
            if(fatOtherCount.intValue() > 0) {
                funcOtherFatal = 1;
            }else if(injOtherCount.intValue() > 0) {
                funcFatalOtherInjured = 1;
            }else {
                funcFatal = 1;
            }
        }else if(driverState.equals("datatype.driverstate.injured") || injFuncCount.intValue() > 0) {
            if(injOtherCount.intValue() > 0) {
                funcOtherInjured = 1;
            }else if(fatOtherCount.intValue() > 0) {
                funcInjuredOtherFatal = 1;
            }else {
                funcInjured = 1;
            } 
        }else if(injOtherCount.intValue() > 0) {
            otherInjured = 1;
        }else if(fatOtherCount.intValue() > 0) {
            otherFatal = 1;
        }else if(((Boolean)data[3]).booleanValue()) {
            damage = 1;
        }
    }

    public EventTypeData(int count, int cost, String eventType, Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name, String type) {
        super(count,cost,eventType,divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,divOrg4Id,divOrg4Name,type);
        damage = count;
    }

    public EventTypeData(int count, int cost, String eventType, String consequence, Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name, String type) {
        super(count,cost,eventType,divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,divOrg4Id,divOrg4Name,type);
        if(consequence.equals("datatype.fleetconsequences.materialdamage")){
            damage = count;
        }
        if(consequence.equals("datatype.fleetconsequences.injuredemployee")){
            funcInjured = count;
        }
        if(consequence.equals("datatype.fleetconsequences.injuredothers")){
           otherInjured = count;
        }
        if(consequence.equals("datatype.fleetconsequences.deceasedemployee")){
            funcFatal = count;
        }
        if(consequence.equals("datatype.fleetconsequences.injuredall")){
            funcOtherInjured = count;
        }
        if(consequence.equals("datatype.fleetconsequences.deceasedothers")){
            otherFatal = count;
        }
        if(consequence.equals("datatype.fleetconsequences.decesaedall")){
            funcOtherFatal = count;
        }
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getFuncFatal() {
        return funcFatal;
    }

    public int getFuncFatalOtherInjured() {
        return funcFatalOtherInjured;
    }

    public int getFuncInjured() {
        return funcInjured;
    }

    public int getFuncInjuredOtherFatal() {
        return funcInjuredOtherFatal;
    }

    public int getFuncOtherFatal() {
        return funcOtherFatal;
    }

    public int getFuncOtherInjured() {
        return funcOtherInjured;
    }

    public int getOtherFatal() {
        return otherFatal;
    }

    public int getOtherInjured() {
        return otherInjured;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDivOrg1Id(Integer divOrg1Id) {
        this.divOrg1Id = divOrg1Id;
    }

    public void setFuncFatal(int funcFatal) {
        this.funcFatal = funcFatal;
    }

    public void setFuncFatalOtherInjured(int funcFatalOtherInjured) {
        this.funcFatalOtherInjured = funcFatalOtherInjured;
    }

    public void setFuncInjured(int funcInjured) {
        this.funcInjured = funcInjured;
    }

    public void setFuncInjuredOtherFatal(int funcInjuredOtherFatal) {
        this.funcInjuredOtherFatal = funcInjuredOtherFatal;
    }

    public void setFuncOtherFatal(int funcOtherFatal) {
        this.funcOtherFatal = funcOtherFatal;
    }

    public void setFuncOtherInjured(int funcOtherInjured) {
        this.funcOtherInjured = funcOtherInjured;
    }

    public void setOtherFatal(int otherFatal) {
        this.otherFatal = otherFatal;
    }

    public void setOtherInjured(int otherInjured) {
        this.otherInjured = otherInjured;
    }

    public void addData(TypeData data) {
        
        super.addData(data);
        this.damage += ((EventTypeData)data).damage;
        this.funcInjured += ((EventTypeData)data).funcInjured;
        this.otherInjured += ((EventTypeData)data).otherInjured;
        this.funcOtherInjured += ((EventTypeData)data).funcOtherInjured;
        this.funcFatal += ((EventTypeData)data).funcFatal;
        this.otherFatal += ((EventTypeData)data).otherFatal;
        this.funcInjuredOtherFatal += ((EventTypeData)data).funcInjuredOtherFatal;
        this.funcFatalOtherInjured += ((EventTypeData)data).funcFatalOtherInjured;
        this.funcOtherFatal += ((EventTypeData)data).funcOtherFatal;
    }
    
}
