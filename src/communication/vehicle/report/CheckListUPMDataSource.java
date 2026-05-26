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

public class CheckListUPMDataSource implements JRDataSource {

    private VehicleCheckData value;
    private PointData[] points;
    private int index = -1;
	private Text messages;
	private String pointValue;
	private String itemValue;
	private String itemTag;
	private Boolean resume;
    
    public CheckListUPMDataSource(Boolean resume,VehicleCheckData value, CheckListData checkList, Text messages) {
    	this.resume=resume;
    	this.value = value;
    	this.messages = messages;
    	points = new PointData[checkList.getPoints().size()-3];
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
	    		if(in > 2) {
	    			points[in-3] = it.next();
	    		}else {
	    			it.next();
	    		}
	    		in++;
	    	}
    	}
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
    		if(!resume){
    			if(points[index].getSortItems().next().getType() == 1)
    				pointValue = messages.getText(points[index].getText());
    		}else{
    			pointValue = messages.getText(points[index].getText());
    		} 
    			 
    		return pointValue;
    	}
    	if(field.getName().equals("item")) {
    		itemValue = value.getValueFromItem(points[index].getSortItems().next().getId());
    		itemTag = value.getValueTagFromItem(points[index].getSortItems().next().getId());
    		return messages.getText(points[index].getSortItems().next().getText());
    	}
    	if(field.getName().equals("valueA")) {
    		return (itemValue != null && Integer.parseInt(itemValue) == 1) ? "X" : "";
    	}
    	if(field.getName().equals("valueR")) {
    		return (itemValue != null && Integer.parseInt(itemValue) == 2) ? "X" : "";
    	}
    	if(field.getName().equals("valueP")) {
    		return (itemTag != null && Integer.parseInt(itemTag) == 0) ? AlertConstants.TEMP_DIRECTORY_DPA+"images/redFlag.png" : "";
    	}
    	if(field.getName().equals("valueB")) {
    		return (itemTag != null && Integer.parseInt(itemTag) == 0) ? true : false;
    	}
    	if(field.getName().equals("valueXB")) {
    		return (itemTag != null && Integer.parseInt(itemTag) != 0) ? true : false;
    	}
    	if(field.getName().equals("valueX")) {
    		return getValueX(Integer.parseInt(itemTag));
    	}
    	if(field.getName().equals("obs")) {
    		String obs = value.getValueFromCommentPoint(points[index].getId()); 
			int itemId = points[index].getSortItems().next().getId();
			/*if(itemId == 17000 || itemId == 17001 || itemId == 17002 || itemId == 17012) {
				obs="Su vehiculo se hubiera detenido inmediatamente";
			}
			if(itemId == 17005 || itemId == 17006 || itemId == 17015 || itemId == 17016) {
				obs="Su vehiculo tendría 48 horas para el arreglo";
			}*/

    		return (obs == null || obs.equals("-1")) ? "---" : obs;
    	}
    	if(field.getName().startsWith("table")) {
    		if(index % 2 == 0) {
    			return AlertConstants.TEMP_DIRECTORY_DPA+"images/checkline1_"+field.getName()+".jpg";
    		}else {
    			return AlertConstants.TEMP_DIRECTORY_DPA+"images/checkline2_"+field.getName()+".jpg";
    		}
    	}
    	return null;
    }

	public Object getValueX(int parseInt) {
		// TODO Auto-generated method stub
		if(value.getName().equals("79")) return "N/A";
		
		switch (parseInt) {
			case -1: return "S/P";
			case 0 :return "Detención inmediata";//Detención inmediata	
			case 1: return "24 hs";
			case 2: return "48 hs";
			case 3: return "72 hs";
			case 30: return "30 Días";
		}
		return null;
	}


}
