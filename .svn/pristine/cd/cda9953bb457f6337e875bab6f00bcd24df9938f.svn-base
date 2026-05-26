package communication.util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import persistence.util.UtilBase64;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.alert.AlertConstants;
import communication.language.Text;

public class ImageBase64DataSource implements JRDataSource {

    private String[]values;
    private int index = -1;
	
    
    public ImageBase64DataSource(ArrayList<String> images) throws IOException {
    	values = new String[images.size()];
    	for(int i=0; i<images.size();i++){
    		String src=images.get(i);
    		String filePath=AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+"tmp"+i+".png";
    		File fileAux=new File(filePath);
    		if(fileAux.exists()){
    			fileAux.delete();
    		}
  			ImageIO.write(UtilBase64.decodeToImage(src.replaceAll("data:image/png;base64,","")), "png", new File(filePath));
  			values[i]=filePath;
  			
    	}
    }
    
    public boolean next() throws JRException {
        if(index+1 < values.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	 if(field.getName().equals("image")){
    		 return values[index];
    	 }
    	return null;
    }


}
