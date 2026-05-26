/**
 * Created on 25-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.exception.InvalidDataException;

/**
 * @author jrodriguez
 */
public class CorporationAddItemAction extends AbstractAction {

    /**
     * @param label1
     * @param divOrgData
     * @param i
     * @return
     */
    private Integer getId(String name, DivOrgData divOrgData, int level) {
        Iterator it = divOrgData.getDivOrgLevels().getItemIterator();
        while(it.hasNext()) {
            ItemData item1 = (ItemData)it.next();
            if(level == 1) {
                if(item1.getName().equals(name)) {
                    return item1.getId();
                }
            }else {
                Iterator it2 = item1.getSubItems().getItemIterator();
                while(it2.hasNext()) {
                    ItemData item2 = (ItemData)it2.next();
                    if(level == 2) {
                        if(item2.getName().equals(name)) {
                            return item2.getId();
                        }
                    }
                }
            }
        }
        return null;
    }

    private ItemData getItem(LabelItemData label, String id, String name) {
        if(label != null) {
            Iterator it = label.getItems().iterator();        
            while(it.hasNext()) {
                ItemData item = (ItemData)it.next();
                if(!empty(id)) {
                    if(item.getId().intValue() == Integer.parseInt(id)) {
                        return item;
                    }                    
                }else {
                    if(item.getName().equals(name)) {
                        return item;
                    }                    
                }
            }
        }
        return null;
    }
    
    private boolean empty(String value) {
        return value == null || value.trim().length() == 0;
    }
    
    private ItemData createItemData(DivOrgData divOrg,String name, int level) throws InvalidDataException {
        if(validate(divOrg,name.trim(),level)) {
            return new ItemData(null,name.trim(),true,null);
        }else {
            throw new InvalidDataException(name,"corporation.additem.error.alreadyexistsdata");
        }
    }

    /**
     * @param corporation
     * @param name
     * @param level
     * @return
     */
    private boolean validate(DivOrgData divOrg, String name, int level) {
/*        if(divOrg.getDivOrgLevels() != null) {
	        Iterator it1 = divOrg.getDivOrgLevels().getItemIterator();
	        while(it1.hasNext()) {
	            ItemData item1 = (ItemData)it1.next();
	            if(level == 1 || item1.getSubItems() == null) {
	                if(item1.getName().equals(name)) {
	                    return false;
	                }
	            }else {
	                Iterator it2 = item1.getSubItems().getItemIterator();
	                while(it2.hasNext()) {
	                    ItemData item2 = (ItemData)it2.next();
	                    if(level == 2 || item2.getSubItems() == null) {
	                        if(item2.getName().equals(name)) {
	                            return false;
	                        }
	                    }else {
	                        Iterator it3 = item2.getSubItems().getItemIterator();
	                        while(it3.hasNext()) {
	                            ItemData item3 = (ItemData)it3.next();
	                            if(item3.getName().equals(name)) {
	                                return false;
	                            }
	                        }
	                    }
	                }
	            }
	        }
        }*/
        return true;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("level"+((CorporationAddItemForm)form).getLevel());                
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CorporationAddItemForm itemForm = (CorporationAddItemForm)form;
        UserSessionData userSession=sys.getUserSessionData();
        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(userSession.getFilter().getCorporationId(),userSession);
        int divOrgId =Integer.parseInt(itemForm.getId());
        DivOrgData data = corporation.getDivOrgData(divOrgId);
        int length = data.getLabels().length;
        switch(length) {
            case 2:
                if(empty(itemForm.getLevel2()) && empty(itemForm.getLabel2())) {
                    return mapping.findForward("level2");
                }
                break;
            case 3:
                if(empty(itemForm.getLevel2()) && empty(itemForm.getLabel2())) {
                    return mapping.findForward("level2");
                }
                if(empty(itemForm.getLabel3())) {
                    return mapping.findForward("level3");
                }
                break;
        }

        if(length == 3) {
            ItemData itemData1 = getItem(data.getDivOrgLevels(),itemForm.getLevel1(),itemForm.getLabel1());
            if(itemData1 == null) {
                ItemData item1 = createItemData(data,itemForm.getLabel1(),1);
                item1.setSubItems(new LabelItemData(data.getLabels()[1].getId(),data.getLabels()[1].getName(),new LinkedList(),data.getLabels()[1].getShowLevel()));
                ItemData item2 = createItemData(data,itemForm.getLabel2(),2);
                item2.setSubItems(new LabelItemData(data.getLabels()[2].getId(),data.getLabels()[2].getName(),new LinkedList(),data.getLabels()[2].getShowLevel()));
                item2.getSubItems().addItem(createItemData(data,itemForm.getLabel3(),3));
                item1.getSubItems().addItem(item2);
                if(data.getDivOrgLevels() == null) {
                    data.setDivOrgLevels(new LabelItemData(data.getLabels()[0].getId(),data.getLabels()[0].getName(),new LinkedList(),data.getLabels()[0].getShowLevel()));
                }
                data.getDivOrgLevels().addItem(item1);
            }else {
                ItemData itemData2 = getItem(itemData1.getSubItems(),itemForm.getLevel2(),itemForm.getLabel2());
                if(itemData2 == null) {
                    ItemData item2 = createItemData(data,itemForm.getLabel2(),2);
                    item2.setSubItems(new LabelItemData(data.getLabels()[2].getId(),data.getLabels()[2].getName(),new LinkedList(),data.getLabels()[2].getShowLevel()));
                    item2.getSubItems().addItem(createItemData(data,itemForm.getLabel3(),3));
                    if(itemData1.getSubItems() == null) {
                        itemData1.setSubItems(new LabelItemData(data.getLabels()[1].getId(),data.getLabels()[1].getName(),new LinkedList(),data.getLabels()[1].getShowLevel()));
                    }
                    itemData1.getSubItems().addItem(item2);
                }else {
                    ItemData itemData3 = getItem(itemData2.getSubItems(),null,itemForm.getLabel3());
                    if(itemData3 == null) {
                        if(itemData2.getSubItems() == null) {
                            itemData2.setSubItems(new LabelItemData(data.getLabels()[2].getId(),data.getLabels()[2].getName(),new LinkedList(),data.getLabels()[2].getShowLevel()));
                        }
                        itemData2.getSubItems().addItem(createItemData(data,itemForm.getLabel3(),3));
                    }
                }
            }
        }
        if(length == 2) {
            ItemData itemData1 = getItem(data.getDivOrgLevels(),itemForm.getLevel1(),itemForm.getLabel1());
            if(itemData1 == null) {
                ItemData item1 = createItemData(data,itemForm.getLabel1(),1); 
                item1.setSubItems(new LabelItemData(data.getLabels()[1].getId(),data.getLabels()[1].getName(),new LinkedList(),data.getLabels()[1].getShowLevel()));
                item1.getSubItems().addItem(createItemData(data,itemForm.getLabel2(),2));
                if(data.getDivOrgLevels() == null) {
                    data.setDivOrgLevels(new LabelItemData(data.getLabels()[0].getId(),data.getLabels()[0].getName(),new LinkedList(),data.getLabels()[0].getShowLevel()));
                }
                data.getDivOrgLevels().addItem(item1);
            }else {
                ItemData itemData2 = getItem(itemData1.getSubItems(),itemForm.getLevel2(),itemForm.getLabel2());
                if(itemData2 == null) {
                    if(itemData1.getSubItems() == null) {
                        itemData1.setSubItems(new LabelItemData(data.getLabels()[1].getId(),data.getLabels()[1].getName(),new LinkedList(),data.getLabels()[1].getShowLevel()));
                    }
                    itemData1.getSubItems().addItem(createItemData(data,itemForm.getLabel2(),2));
                }
            }
            
        }
        if(length == 1) {
            if(getItem(data.getDivOrgLevels(),itemForm.getLevel1(),itemForm.getLabel1()) == null) {
                ItemData item1 = createItemData(data,itemForm.getLabel1(),1);
                if(data.getDivOrgLevels() == null) {
                    data.setDivOrgLevels(new LabelItemData(data.getLabels()[0].getId(),data.getLabels()[0].getName(),new LinkedList(),data.getLabels()[0].getShowLevel()));
                }
                data.getDivOrgLevels().addItem(item1);
            }
        }    
        
        CorporationABMFacade corpABM = sys.getCorporationABMFacade();
        corpABM.corporationUpdateDivOrg(corporation,divOrgId,sys.getUserSessionData());

        corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(userSession.getFilter().getCorporationId(),userSession);
        if(!empty(itemForm.getLabel1()) && length > 1) {
            session.setAttribute("level1",String.valueOf(getId(itemForm.getLabel1(),corporation.getDivOrgData(divOrgId),1)));
        }
        if(!empty(itemForm.getLabel2()) && length > 2) {
            session.setAttribute("level2",String.valueOf(getId(itemForm.getLabel2(),corporation.getDivOrgData(divOrgId),2)));
        }
        sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
        return mapping.findForward("level"+String.valueOf(length));
    }
}
