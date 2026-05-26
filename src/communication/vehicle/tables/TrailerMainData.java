/**
 * Created on 27-jul-2007
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.tables;

import java.io.Serializable;

import communication.datatype.tables.TypeTradeDataType;

public class TrailerMainData implements Serializable {

    protected Integer id;
    protected String plate;
    protected String model;
    protected TypeTradeDataType trade;
    
    public TrailerMainData() {
    }

    public TrailerMainData(Integer id, String plate, String model, TypeTradeDataType trade) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.trade = trade;
    }

    public TrailerMainData(Object[] data) {
        this.id = (Integer)data[0];
        this.plate = (String)data[1];
        this.model = (String)data[2];
        if(data[3] != null) {
            this.trade = new TypeTradeDataType((String)data[3]);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public TypeTradeDataType getTrade() {
        return trade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setTrade(TypeTradeDataType trade) {
        this.trade = trade;
    }

    
}
