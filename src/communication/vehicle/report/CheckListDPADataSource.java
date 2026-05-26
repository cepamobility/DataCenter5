package communication.vehicle.report;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;

public class CheckListDPADataSource implements JRDataSource {

    private VehicleCheckData value;
    private PointData[] points;
    private int index = -1;
	private Text messages;
	private String pointValue;
	private String itemValue;
	private String itemTag;
	private Boolean resume;
	private int corporationId=CorporationData.DPA;
	private int checklistid=0;
    
    public CheckListDPADataSource(Boolean resume,VehicleCheckData value, CheckListData checkList, Text messages) {
    	this.resume=resume;
    	this.value = value;
    	this.messages = messages;
    	points = new PointData[checkList.getId().equals(CheckListData.SYNGENTA_CHECKLIST)?checkList.getPoints().size()-8:(checkList.getPoints().size()-3)];
    	Iterator<PointData> it = checkList.getSortPoints();
    	if(resume){
	    	Iterator auxPointsIter=checkList.getSortPoints();
	    	ArrayList<PointData> auxPoints=new ArrayList<PointData>();
	    	PointData pointHeader=null;
	    	HashMap<Integer,PointData> pointHedaersOK=new HashMap<Integer, PointData>();
	    	while(auxPointsIter.hasNext()) {
				PointData point = (PointData)auxPointsIter.next();
				Iterator items = point.getSortItems();
				if(items.hasNext()) {
					ItemData item = (ItemData)items.next();
					if(item.getType()==1){
						pointHeader=point;
					}
					int valueItem = -1;
					String pointComment = null;
					if(value != null) {
						String checkValue = value.getValueFromItem(item.getId()).trim();
						valueItem = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
					}
					if(valueItem==2){
						if(item.getType() != 1){
							if(pointHeader!=null && !pointHedaersOK.containsKey(point.getId())){
								point.setText(pointHeader.getText());
								pointHedaersOK.put(point.getId(),point);
							}
						}
						auxPoints.add(point);
					}
				}
			}
	    	it=auxPoints.iterator();
	    	points = new PointData[auxPoints.size()];
	    	int in = 0;
	    	while(it.hasNext()) {
	    		points[in] = it.next();
	    		in++;
	    	}
    	}else{
    	
    		int in = 0;
	    	while(it.hasNext()) {
	    		if(checkList.getId().equals(CheckListData.SYNGENTA_CHECKLIST)){
	    			if(points.length>in){
	    				points[in] = it.next();	
	    			}else{
	    				it.next();
	    			}
	    		}else
	    		if(in > 2 ) {
	    			points[in-3] = it.next();
	    		}else {
	    			it.next();
	    		}
	    		in++;
	    	}
    	}
    	
    	checklistid=checkList.getId();
    }
    
    public boolean next() throws JRException {
        if(index+1 < points.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("point")) {
    		if(corporationId==CorporationData.SYNGENTA){
    			if(points[index].getSortItems().next().getType() == 1){
    				pointValue=messages.getText("Datos");	
    			}
    			
    		}else if(corporationId==CorporationData.DPA){
        		if(!resume){
        			if(points[index].getSortItems().next().getType() == 1)
        				pointValue = messages.getText(points[index].getText());
        		}else{
        			pointValue = messages.getText(points[index].getText());
        		} 
    		}else{
    			if(!resume){
        			if(points[index].getSortItems().next().getType() == 1)
        				pointValue = messages.getText(points[index].getText());
        		}else{
        			pointValue = messages.getText(points[index].getText());
        		} 
    		}
    			 
    		return pointValue;
    	}
    	if(field.getName().equals("item")) {
    		itemValue = value.getValueFromItem(points[index].getSortItems().next().getId());
    		itemTag = value.getValueTagFromItem(points[index].getSortItems().next().getId());
    		return messages.getText(points[index].getSortItems().next().getText());
    	}
    	if(field.getName().equals("valueA")) {
    		if(checklistid==CheckListData.SYNGENTA_CHECKLIST){
    			return (itemValue != null && Integer.parseInt(itemValue) == 2) ? "X" : "";
    		}
    		return (itemValue != null && Integer.parseInt(itemValue) == 1) ? "X" : "";
    	}
    	if(field.getName().equals("valueR")) {
    		if(checklistid==CheckListData.SYNGENTA_CHECKLIST){
    			return (itemValue != null && Integer.parseInt(itemValue) == 1) ? "X" : "";
    		}
    		return (itemValue != null && Integer.parseInt(itemValue) == 2) ? "X" : "";
    	}
    	if(field.getName().equals("valueP")) {
    		return (itemTag != null && Integer.parseInt(itemTag) == 1) ? AlertConstants.getTEMP(corporationId)+"images/redFlag.png" : "";
    	}
    	if(field.getName().equals("valueB")) {
    		return (itemTag != null && Integer.parseInt(itemTag) == 1) ? true : false;
    	}
    	if(field.getName().equals("obs")) {
    		String obs = value.getValueFromCommentPoint(points[index].getId()); 
    		return (obs == null) ? "---" : obs;
    	}
    	if(field.getName().startsWith("table")) {
    		if(index % 2 == 0) {
    			return AlertConstants.getTEMP(corporationId)+"images/checkline1_"+field.getName()+".jpg";
    		}else {
    			return AlertConstants.getTEMP(corporationId)+"images/checkline2_"+field.getName()+".jpg";
    		}
    	}
    	return null;
    }

	public int getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(int corporationId) {
		this.corporationId = corporationId;
	}


}
