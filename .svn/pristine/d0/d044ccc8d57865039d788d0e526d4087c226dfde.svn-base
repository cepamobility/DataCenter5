/**
 * Created on 20-aug-2008
 * CEPA
 * DataCenter 5 
 */
package persistence.administration.corporation.tables;



import org.hibernate.classic.Session;


public class CorporationMenuException {

    private CorporationMenuExceptionPK pk;

    public CorporationMenuException() {
    }

    public CorporationMenuException(Integer corporation,Integer menuId,String role, Session session) {
        MenuOption menu=(MenuOption)session.load(MenuOption.class, menuId);
        pk = new CorporationMenuExceptionPK(corporation,role,menu);
    }

    
    public CorporationMenuExceptionPK getPk() {
        return pk;
    }

    public void setPk(CorporationMenuExceptionPK pk) {
        this.pk = pk;
    }
    
}
