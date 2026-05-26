/**
 * Created on 20-aug-2008
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;


public class CorporationMenuData implements Serializable {

    private boolean enable;
    
    public CorporationMenuData() {
    }

    public CorporationMenuData(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }


    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
