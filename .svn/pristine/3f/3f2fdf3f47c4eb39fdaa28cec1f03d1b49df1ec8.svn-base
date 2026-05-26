/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.tables;

import org.hibernate.classic.Session;

import persistence.activity.tables.CheckList;


public class CENAPRAActivityRegistryValue {

    private CENAPRAActivityRegistryValuePK pk;
    private CheckList checklist;
    
    public CENAPRAActivityRegistryValue() {
    }

    public CENAPRAActivityRegistryValue(Integer registryId, Integer itemId, Integer checkId, Session session) {
        pk = new CENAPRAActivityRegistryValuePK(registryId,itemId,session); 
        checklist = (CheckList)session.load(CheckList.class,checkId);
    }

    public CheckList getChecklist() {
        return checklist;
    }

    public CENAPRAActivityRegistryValuePK getPk() {
        return pk;
    }

    public void setChecklist(CheckList checklist) {
        this.checklist = checklist;
    }

    public void setPk(CENAPRAActivityRegistryValuePK pk) {
        this.pk = pk;
    }

}
