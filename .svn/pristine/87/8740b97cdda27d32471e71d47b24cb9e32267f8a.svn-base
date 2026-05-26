/**
 * Created on 25-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.user.UserSessionData;


/**
 * @author jrodriguez
 */
public class CorporationAddCountryAction extends AbstractAction {

    /**
     * @param itemForm
     * @param i
     * @return
     */
    private Collection getCountries(String label) {
	    StringTokenizer tokenizer = new StringTokenizer(label,"<");
	    Collection countries = new LinkedList();
	    while(tokenizer.hasMoreTokens()) {
	        String countryId = (String)tokenizer.nextToken();
	        countries.add(new GeoItemData(Integer.valueOf(countryId),"",true,null));
	    }
	    return countries;
    }

    private GeoLabelData getLabelData(CorporationData corporation,int level,Collection items) {
	    GeoLabelAttributes attr = corporation.getGeoLabels()[level];
	    return new GeoLabelData(attr.getId(),attr.getResourceKey(),attr.getLevel(),items);
    }
    
    private boolean empty(String value) {
        return value == null || value.trim().length() == 0;
    }
    
    private Integer getId(CorporationData corporation,String name,int level) {
        if(corporation.getDivGeoDataSet() != null) {
            Iterator it = corporation.getDivGeoDataSet().getItemIterator();
            while(it.hasNext()) {
                GeoItemData item1 = (GeoItemData)it.next();
                if(level == 1) {
                    if(item1.getName().equals(name)) {
                        return item1.getId();
                    }
                }else {
                    Iterator it2 = item1.getSubItems().getItemIterator();
                    while(it2.hasNext()) {
                        GeoItemData item2 = (GeoItemData)it2.next();
                        if(level == 2) {
                            if(item2.getName().equals(name)) {
                                return item2.getId();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("level"+((CorporationCountryForm)form).getLevel());                
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        UserSessionData userSession=sys.getUserSessionData();
        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(userSession.getFilter().getCorporationId(),userSession);
        
        CorporationCountryForm itemForm = (CorporationCountryForm)form;
        int length = corporation.getGeoLabels().length;
        GeoLabelData newData = null;
        String returnTaget = "";
        switch(length) {
            case 1:
                newData = getLabelData(corporation,0,getCountries(itemForm.getLabel1()));
                returnTaget = "level1";
                break;
            case 2:                             
                if(empty(itemForm.getLevel2()) && empty(itemForm.getLabel2())) {
                    return mapping.findForward("level2");
                }
                Collection items1 = new LinkedList();
                if(!empty(itemForm.getLevel1())) {
                    items1.add(new GeoItemData(Integer.valueOf(itemForm.getLevel1()),"",true,getLabelData(corporation,1,getCountries(itemForm.getLabel2()))));
                }else {
                    items1.add(new GeoItemData(getId(corporation,itemForm.getLabel1(),1),itemForm.getLabel1(),true,getLabelData(corporation,1,getCountries(itemForm.getLabel2()))));
                }                       
                newData = getLabelData(corporation,1,items1);                   
                returnTaget = "level2";
                break;
            case 3:
                if(empty(itemForm.getLevel2()) && empty(itemForm.getLabel2())) {
                    return mapping.findForward("level2");
                }
                if(empty(itemForm.getLabel3())) {
                    return mapping.findForward("level3");
                }
                Collection items2 = new LinkedList();
                if(!empty(itemForm.getLevel2())) {
                    items2.add(new GeoItemData(Integer.valueOf(itemForm.getLevel2()),"",true,getLabelData(corporation,2,getCountries(itemForm.getLabel3()))));
                }else {
                    items2.add(new GeoItemData(getId(corporation,itemForm.getLabel2(),2),itemForm.getLabel2(),true,getLabelData(corporation,2,getCountries(itemForm.getLabel3()))));
                }                       
                Collection items3 = new LinkedList();
                if(!empty(itemForm.getLevel1())) {
                    items3.add(new GeoItemData(Integer.valueOf(itemForm.getLevel1()),"",true,getLabelData(corporation,1,items2)));
                }else {
                    items3.add(new GeoItemData(getId(corporation,itemForm.getLabel1(),1),itemForm.getLabel1(),true,getLabelData(corporation,1,items2)));
                }                       
                newData = getLabelData(corporation,0,items3);                   
                returnTaget = "level3";
                break;
        }

        if(newData != null) {
            sys.getCorporationABMFacade().corporationAddCountry(newData,corporation,userSession);
        }
        corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(userSession.getFilter().getCorporationId(),userSession);
        
        if(!empty(itemForm.getLabel1()) && length > 1) {
            session.setAttribute("level1",String.valueOf(getId(corporation,itemForm.getLabel1(),1)));
        }
        if(!empty(itemForm.getLabel2()) && length > 2) {
            session.setAttribute("level2",String.valueOf(getId(corporation,itemForm.getLabel2(),2)));
        }

        sys.loadFilter(userSession.getFilter().getCorporationId());
        return mapping.findForward(returnTaget);
    } 
}
