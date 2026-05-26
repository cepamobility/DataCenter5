/**
 * Created on 26-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.groups;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.UserSessionData;
import communication.util.Filter;

public class GroupAddLineForm extends ActionForm {

    private String id;

    protected String[] divOrg11;
    protected String[] divOrg12;
    protected String[] divOrg13;
    protected String[] divOrg21;
    protected String[] divOrg22;
    protected String[] divOrg23;
    protected String[] divOrg31;
    protected String[] divOrg32;
    protected String[] divOrg33;
    
    protected String country1;
    protected String country2;
    protected String country3;

    public GroupAddLineForm() {
    }
    
    

    public GroupAddLineForm(String country1, String country2, String country3,
			String[] divOrg11, String[] divOrg12, String[] divOrg13,
			String[] divOrg21, String[] divOrg22, String[] divOrg23,
			String[] divOrg31, String[] divOrg32, String[] divOrg33, String id) {
		super();
		this.country1 = country1;
		this.country2 = country2;
		this.country3 = country3;
		this.divOrg11 = divOrg11;
		this.divOrg12 = divOrg12;
		this.divOrg13 = divOrg13;
		this.divOrg21 = divOrg21;
		this.divOrg22 = divOrg22;
		this.divOrg23 = divOrg23;
		this.divOrg31 = divOrg31;
		this.divOrg32 = divOrg32;
		this.divOrg33 = divOrg33;
		this.id = id;
	}



	/**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    public String validate(UserSessionData sessionData) {
        for(int j = 0; j < sessionData.getDivOrgCount(); j++) {
            for(int i = 1; i <= sessionData.getFilter().getDivOrgLevel(j); i++) {
                if(getDivOrg(j+1,i).length == 0) {
                    return "group.error.emptydivorg";            
                }
            }
        }
        int count = sessionData.getFilter().getGeoLevelCount(); 
        if(count >= 1) {
            if(Util.empty(country1)) {
                return "group.error.emptydivgeo";            
            }
        }
        if(count >= 2) {
            if(Util.empty(country2)) {
                return "group.error.emptydivgeo";            
            }
        }
        if(count >= 3) {
            if(Util.empty(country3)) {
                return "group.error.emptydivgeo";            
            }
        }
        return null;
    }

    public Collection getGroupLine(UserSessionData session) {
        Collection lines = new LinkedList();
        
        Collection[] collections = new LinkedList[3];
        for(int i = 0; i < 3; i++) {
            collections[i] = new LinkedList();
        }


        Collection geoItems3 = new LinkedList();
        GeoLabelData data3 = null;
        if(!Util.empty(country3)) {
            geoItems3.add(new GeoItemData(new Integer(country3),"",true,null));
            data3 = new GeoLabelData(null,null,null,geoItems3);
        }
        Collection geoItems2 = new LinkedList();
        GeoLabelData data2 = null;
        if(!Util.empty(country2)) {
            geoItems2.add(new GeoItemData(new Integer(country2),"",true,data3));
            data2 = new GeoLabelData(null,null,null,geoItems2);
        }
        Collection geoItems1 = new LinkedList();
        GeoLabelData country = null;
        if(!Util.empty(country1)) {
        	geoItems1.add(new GeoItemData(new Integer(country1),"",true,data2));
        	country = new GeoLabelData(null,null,null,geoItems1);
        }
        for(int i = 0; i < session.getDivOrgCount(); i++) {
            int divOrgCount = session.getFilter().getDivOrgLevel(i);
            String[] ids = getDivOrg(i+1,divOrgCount);
            if(ids[0].equals("0")) {
                if(divOrgCount == 1) {
                    insertData(session, collections, i, divOrgCount);
                }else {
                    ids = getDivOrg(i+1,divOrgCount-1);
                    if(ids[0].equals("0")) {
                        if(divOrgCount == 2) {
                            insertData(session, collections, i, divOrgCount);
                        }else {
                            ids = getDivOrg(i+1,divOrgCount-2);
                            if(ids[0].equals("0")) {
                                insertData(session, collections, i, divOrgCount);
                            }else {
                                for(int j = 0; j < ids.length; j++) {
                                    Iterator it = session.getFilter().getLastLevelItems(divOrgCount,i,ids[j],null,Filter.CORPORATION);
                                    Collection idList = new LinkedList();
                                    while(it.hasNext()) {
                                        ItemData itemData = (ItemData)it.next();
                                        if(!idList.contains(itemData.getId())) {
                                            idList.add(itemData.getId());
                                            collections[i].add(session.getLabelItemData(i,itemData.getId()));
                                        }
                                    }
                                }
                            }
                        }
                    }else {
                        for(int j = 0; j < ids.length; j++) {
                            Iterator it = (divOrgCount == 2) ? session.getFilter().getLastLevelItems(divOrgCount,i,ids[j],null,Filter.CORPORATION) : session.getFilter().getLastLevelItems(divOrgCount,i,null,ids[j],Filter.CORPORATION);
                            Collection idList = new LinkedList();
                            while(it.hasNext()) {
                                ItemData itemData = (ItemData)it.next();
                                if(!idList.contains(itemData.getId())) {
                                    idList.add(itemData.getId());
                                    collections[i].add(session.getLabelItemData(i,itemData.getId()));
                                }
                            }
                        }
                    }
                }
            }else {
                for(int j = 0; j < ids.length; j++) {
                    collections[i].add(session.getLabelItemData(i,new Integer(ids[j])));

                }
            }    
        }
        
        Iterator it1 = collections[0].iterator();
        Iterator it2 = collections[1].iterator();
        Iterator it3 = collections[2].iterator();
        int count = 0;
        if(collections[0].size() >= collections[1].size() && collections[0].size() >= collections[2].size()) {
            count = collections[0].size();
        }else if(collections[1].size() >= collections[0].size() && collections[1].size() >= collections[2].size()) {
            count = collections[1].size();
        }else {
            count = collections[2].size();
        }
        
        if(session.getFilter().isCEPASystem()) {
            GroupLineData group = new GroupLineData();
            group.setCountry(country);
            lines.add(group);            
        }else {
	        LabelItemData label1 = null;
	        LabelItemData label2 = null; 
	        LabelItemData label3 = null;
	        for(int i = 0; i < count; i++) {
	            GroupLineData group = new GroupLineData();
	            group.setCountry(country);
	            LabelItemData[] lid = new LabelItemData[3];
	            int index = 0;
	            if(it1.hasNext()) {
	                label1 = (LabelItemData)it1.next();
	            }
	            if(label1 != null) {
	                lid[index] = label1;
	                index++;
	            }
	            if(it2.hasNext()) {
	                label2 = (LabelItemData)it2.next();
	            }
	            if(label2 != null) {
	                lid[index] = label2;
	                index++;
	            }
	            if(it3.hasNext()) {
	                label3 = (LabelItemData)it3.next();
	            }
	            if(label3 != null) {
	                lid[index] = label3;
	                index++;
	            }
	            group.setDivOrgs(lid);
	            lines.add(group);
	        }
        }
        return lines;
    }

    /**
     * @param session
     * @param collections
     * @param i
     * @param divOrgCount
     */
    private void insertData(UserSessionData session, Collection[] collections, int i, int divOrgCount) {
        Collection ids = new LinkedList();
        Iterator it = session.getFilter().getLastLevelItems(divOrgCount,i,null,null,Filter.CORPORATION);
        while(it.hasNext()) {
            ItemData itemData = (ItemData)it.next();
            if(!collections[i].contains(itemData.getId())) {
                if(!ids.contains(itemData.getId())) {
                    ids.add(itemData.getId());
                    collections[i].add(session.getLabelItemData(i,itemData.getId()));
                }
            }
        }
    }

    public String[] getDivOrg(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return divOrg11;
                    case 2:
                        return divOrg12;
                    case 3:
                        return divOrg13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return divOrg21;
                    case 2:
                        return divOrg22;
                    case 3:
                        return divOrg23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return divOrg31;
                    case 2:
                        return divOrg32;
                    case 3:
                        return divOrg33;
                }
        }
        return null;
    }

    public static Integer[][] getDivOrgs(SystemAccess sys, DivOrgForm form, int corporation) {
        Collection result = new LinkedList();
        for(int j = 0; j < sys.getUserSessionData().getDivOrgCount(); j++) {
            Collection divOrgs = new LinkedList();
            switch(sys.getUserSessionData().getFilter().getDivOrgLevel(j)) {
                case 1:
                    String[] list = form.getDivOrg(j+1,1);
                    if(list == null) {
                        return new Integer[0][0];
                    }
                    switch(list.length) {
                        case 0:
                            return new Integer[0][0];
                        case 1:
                            if(list[0].equals("0")) {
                                Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(1,j,null,null,corporation);
                                while(it2.hasNext()) {
                                    ItemData itemData = (ItemData)it2.next();
                                    if(!divOrgs.contains(itemData.getId())) {
                                        divOrgs.add(itemData.getId());
                                    }
                                }
                            }else {
                                if(!divOrgs.contains(new Integer(list[0]))) {
                                    divOrgs.add(new Integer(list[0]));
                                }
                            }
                            break;
                        default:
                            for(int i = 0; i < list.length; i++) {
                                if(!divOrgs.contains(new Integer(list[i]))) {
                                    divOrgs.add(new Integer(list[i]));
                                }
                            }
                            break;
                    }
                    break;
                case 2:
                    String[] list2 = form.getDivOrg(j+1,2);
                    if((list2 == null) || (list2.length == 0) || (list2.length == 1 && list2[0].equals("0"))) {        
                        list = form.getDivOrg(j+1,1);
                        if(list == null) {
                            return new Integer[0][0];
                        }
                        switch(list.length) {
                            case 0:
                                return new Integer[0][0];
                            case 1:
                                if(list[0].equals("0")) {
                                    Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,j,null,null,corporation);
                                    while(it2.hasNext()) {
                                        ItemData itemData = (ItemData)it2.next();
                                        if(!divOrgs.contains(itemData.getId())) {
                                            divOrgs.add(itemData.getId());
                                        }
                                    }
                                }else {
                                    Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,j,list[0],null,corporation);
                                    while(it2.hasNext()) {
                                        ItemData itemData = (ItemData)it2.next();
                                        if(!divOrgs.contains(itemData.getId())) {
                                            divOrgs.add(itemData.getId());
                                        }
                                    }
                                }
                                break;
                            default:
                                for(int i = 0; i < list.length; i++) {
                                    Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,j,list[i],null,corporation);
                                    while(it2.hasNext()) {
                                        ItemData itemData = (ItemData)it2.next();
                                        if(!divOrgs.contains(itemData.getId())) {
                                            divOrgs.add(itemData.getId());
                                        }
                                    }
                                }
                                break;
                        }
                    }else {
                        for(int i = 0; i < list2.length; i++) {
                            if(!divOrgs.contains(new Integer(list2[i]))) {
                                divOrgs.add(new Integer(list2[i]));
                            }
                        }
                    }
                    break;
                case 3:
                    String[] list3 = form.getDivOrg(j+1,3);
                    if((list3 == null) || (list3.length == 0) || (list3.length == 1 && list3[0].equals("0"))) {
                        list2 = form.getDivOrg(j+1,2);
                        if((list2 == null) || (list2.length == 0) || (list2.length == 1 && list2[0].equals("0"))) {
                            list = form.getDivOrg(j+1,1);
                            if(list == null) {
                                return new Integer[0][0];
                            }
                            switch(list.length) {
                                case 0:
                                    return new Integer[0][0];
                                case 1:
                                    if(list[0].equals("0")) {
                                        Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,j,null,null,corporation);
                                        while(it2.hasNext()) {
                                            ItemData itemData = (ItemData)it2.next();
                                            if(!divOrgs.contains(itemData.getId())) {
                                                divOrgs.add(itemData.getId());
                                            }
                                        }
                                    }else {
                                        Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,j,list[0],null,corporation);
                                        while(it2.hasNext()) {
                                            ItemData itemData = (ItemData)it2.next();
                                            if(!divOrgs.contains(itemData.getId())) {
                                                divOrgs.add(itemData.getId());
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    for(int i = 0; i < list.length; i++) {
                                        Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,j,list[i],null,corporation);
                                        while(it2.hasNext()) {
                                            ItemData itemData = (ItemData)it2.next();
                                            if(!divOrgs.contains(itemData.getId())) {
                                                divOrgs.add(itemData.getId());
                                            }
                                        }
                                    }
                                    break;
                            }
                        }else {
                            for(int i = 0; i < list2.length; i++) {
                                Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,j,null,list2[i],corporation);
                                while(it2.hasNext()) {
                                    ItemData itemData = (ItemData)it2.next();
                                    if(!divOrgs.contains(itemData.getId())) {
                                        divOrgs.add(itemData.getId());
                                    }
                                }
                            }
                        }
                    }else {
                        for(int i = 0; i < list3.length; i++) {
                            if(!divOrgs.contains(new Integer(list3[i]))) {
                                divOrgs.add(new Integer(list3[i]));
                            }
                        }
                    }
                    break;
            }
            result.add((Integer[])divOrgs.toArray(new Integer[0]));
        }
        return (Integer[][])result.toArray(new Integer[0][0]);
    }

    public String getCountry1() {
        return country1;
    }

    public String getCountry2() {
        return country2;
    }

    public String getCountry3() {
        return country3;
    }

    public String[] getDivOrg11() {
        return divOrg11;
    }

    public String[] getDivOrg12() {
        return divOrg12;
    }

    public String[] getDivOrg13() {
        return divOrg13;
    }

    public String[] getDivOrg21() {
        return divOrg21;
    }

    public String[] getDivOrg22() {
        return divOrg22;
    }

    public String[] getDivOrg23() {
        return divOrg23;
    }

    public String[] getDivOrg31() {
        return divOrg31;
    }

    public String[] getDivOrg32() {
        return divOrg32;
    }

    public String[] getDivOrg33() {
        return divOrg33;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public void setCountry3(String country3) {
        this.country3 = country3;
    }

    public void setDivOrg11(String[] divOrg11) {
        this.divOrg11 = divOrg11;
    }

    public void setDivOrg12(String[] divOrg12) {
        this.divOrg12 = divOrg12;
    }

    public void setDivOrg13(String[] divOrg13) {
        this.divOrg13 = divOrg13;
    }

    public void setDivOrg21(String[] divOrg21) {
        this.divOrg21 = divOrg21;
    }

    public void setDivOrg22(String[] divOrg22) {
        this.divOrg22 = divOrg22;
    }

    public void setDivOrg23(String[] divOrg23) {
        this.divOrg23 = divOrg23;
    }

    public void setDivOrg31(String[] divOrg31) {
        this.divOrg31 = divOrg31;
    }

    public void setDivOrg32(String[] divOrg32) {
        this.divOrg32 = divOrg32;
    }

    public void setDivOrg33(String[] divOrg33) {
        this.divOrg33 = divOrg33;
    }

    
}
