/**
 * Created on 20-aug-2008
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;

public class CorporationMenuPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer corporation;
	private MenuOption menuOption;
    

    public CorporationMenuPK() {
    }

    public CorporationMenuPK(Integer corporation, MenuOption menu) {
        this.corporation = corporation;
        this.menuOption = menu;
    }

    public CorporationMenuPK(Integer corporation, Integer menuId) {
        menuOption = new MenuOption();
        menuOption.setId(menuId);
        this.corporation = corporation;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public MenuOption getMenuOption() {
        return menuOption;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setMenuOption(MenuOption menu) {
        this.menuOption = menu;
    }
}
