/**
 * Created on 21-feb-2006
 * CEPA
 * DataCenter 5
 */
package communication.util;

import java.io.Serializable;
import java.util.Iterator;

import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.UserSessionData;

/**
 * @author jrodriguez
 */
public class Validate implements Serializable {

    public static String validateDivOrg(Iterator divOrgIterator,UserSessionData userSession) {
		while(divOrgIterator.hasNext()) {
            boolean found = false;
		    DivOrgData divOrgData = (DivOrgData)divOrgIterator.next();
            DivOrgAttribute[] attributes = userSession.getFilter().getDivOrgsAttrs();
            int index = -1;
            for(int i = 0; i < userSession.getFilter().getDivOrgCount(); i++) {
                if(attributes[i].getId() != null && attributes[i].getId().equals(divOrgData.getId())) {
                    index = i;
                }
            }
            if(index == -1) {
                return "error.permission.divorg";
            }else {
                Iterator it = userSession.getFilter().getGroupLines().iterator();
                while(it.hasNext()) {
                    GroupLineData lineData = (GroupLineData)it.next();
                    LabelItemData lid = lineData.getDivOrgs()[index];
                    if(compareDivOrgData(divOrgData.getDivOrgLevels(),lid)){
                        found = true;
                    }
                }
            }
            if(!found) {
                return "error.permission.divorgitem";
            }
		}
		return null;
	}

    /**
     * @param data
     * @param data2
     * @return
     */
    private static boolean compareDivOrgData(LabelItemData data1, LabelItemData data2) {
        if(data1 != null) { 
	        Iterator itemsIt = data1.getItemIterator();
	        boolean next = true;
	        while(next && itemsIt.hasNext()) {
	            ItemData item1 = (ItemData)itemsIt.next();
	            ItemData item2 = data2.getItem(item1.getId());
	            if(item2 == null) {
	                return false;
	            }else {
	                next = compareDivOrgData(item1.getSubItems(),item2.getSubItems());  
	            }
	        }
        }
        return true;
    }

    /**
     * @param data
     * @param data2
     * @return
     */
    public static boolean validateDivGeo(GeoLabelData data, UserSessionData userSessionData) {
        boolean find = false;
        if(data != null) {
            if(userSessionData.getFilter().getGroupLines() != null) {
                Iterator groupsIt = userSessionData.getFilter().getGroupLines().iterator();
                while(groupsIt.hasNext() && !find) {
                    GroupLineData line = (GroupLineData)groupsIt.next();
                    if(compareDivGeoData(data,line.getCountry())) {
                        find = true;
                    }
                }
            }
        }
        return find;
    }

    private static boolean compareDivGeoData(GeoLabelData data1, GeoLabelData data2) {
        if(data1 != null) { 
            Iterator itemsIt = data1.getItemIterator();
            boolean next = true;
            while(next && itemsIt.hasNext()) {
                GeoItemData item1 = (GeoItemData)itemsIt.next();
                GeoItemData item2 = data2.getItem(item1.getId());
                if(item2 == null) {
                    return false;
                }else {
                    next = compareDivGeoData(item1.getSubItems(),item2.getSubItems());  
                }
            }
        }
        return true;
    }
/*
    public static String validateUserDivOrg(Iterator divOrgIterator,UserSessionData userSession) {
		while(divOrgIterator.hasNext()) {
		    UserDivOrgData divOrgData = (UserDivOrgData)divOrgIterator.next();
		    Iterator userIt = userSession.getFilter().getUserDivOrgDataSet().iterator();
		    boolean found = false;
		    UserDivOrgData userDivOrgData = null;
		    while(!found && userIt.hasNext()) {
		        userDivOrgData = (UserDivOrgData)userIt.next();
		        found = userDivOrgData.getDivOrgAttribute().getId().equals(divOrgData.getDivOrgAttribute().getId());
		    }
		    if(found) {
		        if(!compareUserDivOrgData(divOrgData.getUserLevelLabelData(),userDivOrgData.getUserLevelLabelData())){
			        return "error.permission.divorgitem";
		        }
		    }else {
		        return "error.permission.divorg";
		    }
		}
		return null;
	}

    /**
     * @param data
     * @param data2
     * @return
     *
    private static boolean compareUserDivOrgData(UserLevelLabelData data, UserLevelLabelData userData) {
        if(data != null) { 
	        Iterator itemsIt = data.getItemsIterator();
	        boolean next = true;
	        while(next && itemsIt.hasNext()) {
	            UserItemLevelData item = (UserItemLevelData)itemsIt.next();
	            UserItemLevelData userItem = userData.getItem(item.getItemAttribute().getId());
	            if(userItem == null) {
	                return false;
	            }else {
	                next = compareUserDivOrgData(item.getUserLevelLabelData(),userItem.getUserLevelLabelData());  
	            }
	        }
        }
        return true;
    }

    /**
     * @param data
     * @param data2
     * @return
     *
    public static boolean compareUserDivGeoData(UserGeoLabelData data, Collection groupLines) {
        if(data != null) { 
	        Iterator itemsIt = data.getItemsIterator();
	        boolean next = true;
	        while(next && itemsIt.hasNext()) {
	            UserGeoData item = (UserGeoData)itemsIt.next();
	            UserGeoData userItem = userData.getItem(item.getGeoItemAttribute().getId());
	            if(userItem == null) {
	                return false;
	            }else {
	                next = compareUserDivGeoData(item.getUserGeoLabelData(),userItem.getUserGeoLabelData());  
	            }
	        }
        }
        return true;
    }*/
}