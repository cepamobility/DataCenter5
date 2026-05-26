/**
 * Created on 20-aug-2008
 * CEPA
 * DataCenter 5 
 */
package persistence.administration.corporation.tables;



import org.hibernate.classic.Session;

import communication.administration.corporation.tables.CorporationMenuData;


public class CorporationMenu {

    private CorporationMenuPK pk;
    private boolean enable;
    public CorporationMenu() {
    }

    public CorporationMenu(Integer corporation,Integer menuId,Session session) {
        MenuOption menu=(MenuOption)session.load(MenuOption.class, menuId);
        pk = new CorporationMenuPK(corporation,menu);
    }

    
    public boolean isEnable(){
    	return enable;
    }
    
    public CorporationMenuPK getPk() {
        return pk;
    }

    public void setPk(CorporationMenuPK pk) {
        this.pk = pk;
    }
    
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public CorporationMenuData getData() {
        return new CorporationMenuData(enable);
    }

}
