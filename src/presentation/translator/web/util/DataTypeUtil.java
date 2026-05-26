/**
 * Created on 10-ene-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import business.SystemAccess;
import business.datatype.DataTypeReportFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.datatype.DataTypeConstants;
import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.TypeVehicleDataType;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class DataTypeUtil {

    private DataTypeReportFacade dataTypeReport;
    private UserSessionData userSessionData;
    private Text messages;
    private OptionItem emptyObject;
    private OptionItem noDataObject;
    private int corporationId;

    public DataTypeUtil(SystemAccess sys) throws Exception {
        userSessionData = sys.getUserSessionData();
        corporationId = sys.getCorporationId();
        messages = sys.getText();
        dataTypeReport = sys.getDataTypeReportFacade();
        emptyObject = new OptionItem(messages.getText("generic.messages.select"),"");
        noDataObject = new OptionItem(messages.getText("generic.messages.nodata"),"generic.messages.nodata");
    }
    
    /**
     * Genera una collection con Yes, No, y opcionalmente NoData y Empty
     * @param empty
     * @param nodata
     * @return
     */
    public Collection getYesNo(boolean empty,boolean nodata) {
        Collection generic = new LinkedList();
        if(empty) {
            generic.add(emptyObject);
        }
        if(nodata) {
            generic.add(noDataObject);
        }
        generic.add(new OptionItem(messages.getText("generic.messages.yes"),"generic.messages.yes"));
        generic.add(new OptionItem(messages.getText("generic.messages.no"),"generic.messages.no"));
        return generic;
    }
    
    /**
     * Genera una collection con No, NoData y Empty
     * @return
     */
    public Collection getNoAndNoData() {
        Collection generic = new LinkedList();
        generic.add(emptyObject);
        generic.add(noDataObject);
        generic.add(new OptionItem(messages.getText("generic.messages.no"),"generic.messages.no"));
        return generic;
    }

    public Collection getYears(int start,int end) {
        Collection years = new LinkedList();
        years.add(emptyObject);
        years.add(noDataObject);
        years.add(new OptionItem(messages.getText("generic.year.beforeninety"),"generic.year.beforeninety"));
        Calendar instance = Calendar.getInstance();
        for(int i = start; i <= end; i++) {
            years.add(new OptionItem(String.valueOf(i),String.valueOf(i)));
        }
        return years;
    }
    
    public Collection getDataType(int constant,boolean empty,boolean nodata,boolean deleteNoData) throws Exception {
    	return getDataType(constant,empty,nodata,deleteNoData,false);
    }
    
    public Collection getDataType(int constant,boolean empty,boolean nodata,boolean deleteNoData,boolean all) throws Exception {
        Collection dataTypes = new LinkedList();
        Collection dataCollection = dataTypeReport.findAll(constant,userSessionData);
        Iterator it = dataCollection.iterator();
        if(empty) {
            dataTypes.add(emptyObject);
        }
        if(nodata) {
            dataTypes.add(noDataObject);                
        }
        if(all) {
            dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.all"),"datatype.vehicletype.all"));
        }
        while(it.hasNext()) {
            String data = ((GeneralDTData)it.next()).getResourceBundleId();
            if(!deleteNoData || !data.equals("generic.messages.nodata")) {
           		dataTypes.add(new OptionItem(messages.getText(data),data));
            }
        }
        Collections.sort((List)dataTypes);
        return dataTypes;
    }

    public Collection getVehicleType(boolean all,boolean empty,boolean nodata) throws Exception {
        Collection dataTypes = new LinkedList();
        if(empty) {
            dataTypes.add(emptyObject);
        }
        if(all) {
            dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.all"),"datatype.vehicletype.all"));
        }
        if(nodata) {
        	dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.nodata"),"datatype.vehicletype.nodata"));
        }
        dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.automobile"),"datatype.vehicletype.automobile"));
        Collection vehicles = new LinkedList();
        Collection dataCollection = dataTypeReport.findAll(DataTypeConstants.TYPEVEHICLE,userSessionData);
        Iterator it = dataCollection.iterator();
        while(it.hasNext()) {
            String data = ((GeneralDTData)it.next()).getResourceBundleId();
            if(!data.equals("datatype.vehicletype.automobile") 
            		&& !data.equals("datatype.vehicletype.all")
            		&& !data.equals("datatype.vehicletype.novehicle")
            		&& !data.equals("datatype.vehicletype.nodata")
            		&& !data.contains("datatype.vehicletypecomb")) {
            	vehicles.add(new OptionItem(messages.getText(data),data));
            }
        }
        Collections.sort((List)vehicles);
        dataTypes.addAll(vehicles);
        return dataTypes;
    }

    public Collection getExtendedVehicleType(boolean all,boolean empty,boolean nodata, boolean novehicle) throws Exception {
        Collection dataTypes = new LinkedList();
        if(empty) {
            dataTypes.add(emptyObject);
        }
        if(all) {
            dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.all"),"datatype.vehicletype.all"));
        }
        if(nodata) {
        	dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.nodata"),"datatype.vehicletype.nodata"));
        }
        if(novehicle) {
            dataTypes.add(new OptionItem(messages.getText("datatype.vehicletype.novehicle"),"datatype.vehicletype.novehicle"));
        }
        Collection vehicles = new LinkedList();
        Collection dataCollection = dataTypeReport.findAll(DataTypeConstants.TYPEVEHICLE,userSessionData);
        Iterator it = dataCollection.iterator();
        while(it.hasNext()) {
            String data = ((GeneralDTData)it.next()).getResourceBundleId();
            if(!data.equals(TypeVehicleDataType.CAR) 
            		&& !data.equals("datatype.vehicletype.all")
            		&& !data.equals("datatype.vehicletype.novehicle")
            		&& !data.equals("datatype.vehicletype.nodata")) {
            	if(data.contains("datatype.vehicletypecomb")) {
            		if(userSessionData.getCorporationId() == CorporationData.MONSANTO) {
                		vehicles.add(new OptionItem(messages.getText(data),data));
            		}
            	}else {
            		vehicles.add(new OptionItem(messages.getText(data),data));
            	}
            }else {
            	if(data.equals(TypeVehicleDataType.CAR) && userSessionData.isBrasilCorporation()) {
            		vehicles.add(new OptionItem(messages.getText(data),data));
            	}
            }
        }
        Collections.sort((List)vehicles);
        dataTypes.addAll(vehicles);
        return dataTypes;
    }

    public Collection getTradeDataType(boolean empty) throws Exception {
        Collection dataTypes = new LinkedList();
        Collection dataCollection = dataTypeReport.findAll(DataTypeConstants.TYPETRADE,userSessionData);
        Iterator it = dataCollection.iterator();
        if(empty) {
            dataTypes.add(emptyObject);
        }
        Collections.sort((List)dataCollection);
        while(it.hasNext()) {
            String data = ((GeneralDTData)it.next()).getResourceBundleId();
            if(data.equals("generic.messages.nodata")) {
                dataTypes.add(new OptionItem(messages.getText(data),data));               
            }else {
                dataTypes.add(new OptionItem(data,data));             
            }
        }
        Collections.sort((List)dataTypes);
        return dataTypes;
    }

    public DataTypeReportFacade getDataTypeReport() {
        return dataTypeReport;
    }
    
    
}