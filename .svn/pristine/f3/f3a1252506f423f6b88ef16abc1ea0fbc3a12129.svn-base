package communication.vehicle.report;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.language.Text;

public class PictureDPADataSource implements JRDataSource {

    private String[] images;
    private String[] pointComment;
    private String[] descriptions;
    private int index = -1;
	private Text messages;
	private int corporationId=CorporationData.DPA;
    
    public PictureDPADataSource(String[] images, String[] descriptions,String[] pointComment, Text messages) {
    	this.images = images;
    	this.descriptions = descriptions;
    	this.messages = messages;
    	this.pointComment=pointComment;
    }
    
    public boolean next() throws JRException {
    	if(images.length == 0 && index == -1) {
    		index++;
    		return true;
    	}else {
	        if(index+1 < images.length) {
	        	index += 2;
	        	return true;
	        }
	        return false;
    	}
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(images.length == 0) {
    		return "";
    	}
    	if(field.getName().equals("image1")) {
    		return AlertConstants.getTEMPImage(corporationId)+images[index-1];
    	}
    	if(field.getName().equals("image2")) {
    		if(index < images.length)
    			return AlertConstants.getTEMPImage(corporationId)+images[index];
    	}
    	if(field.getName().equals("description1")) {
    		return messages.getText(descriptions[index-1]);
    	}
    	if(field.getName().equals("description2")) {
    		if(index < images.length)
    			return messages.getText(descriptions[index]);
    	}
    	if(field.getName().equals("comment1")) {
    		return pointComment[index-1];
    	}
    	if(field.getName().equals("comment2")) {
    		if(index < images.length)
    			return pointComment[index];
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
