package persistence.administration.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.administration.user.UserSessionData;

import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.datatype.tables.UnconformityTypeDPA;
import persistence.driver.tables.TelematicControl;
import persistence.hibernate.HibernateUtil;
import persistence.vehicle.tables.VehicleCheck;

/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityDPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//Usuario creador
	private String userId;
	private Integer corpId;
	
	private UnconformityTypeDPA type;
	
	private Integer check;
	
	private Integer driverId;
	
	private TelematicControl telematicControl;
	
	private Date date;
	
	private String comment;
	
	private int status;
	
	private Set items;
	
	public UnconformityDPA(){
		items=new HashSet();
	}
	
	public void updateData(UnconformityDPAData data){
		Session session = HibernateUtil.currentSession();
		
		type= (UnconformityTypeDPA)session.load(UnconformityTypeDPA.class, data.getType().getResourceBundleId());
		date=data.getDate();
		comment=data.getComment();
		status=data.getStatus();
		
		if(id==null){
			this.userId=data.getUserId();
		}
		
		if(type.getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)){
			check=data.getCheck();
		}else if(type.getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)){
			telematicControl=(TelematicControl)session.load(TelematicControl.class, data.getTelematicControl().getId());
		}else if(type.getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
			driverId=data.getDriverId();
		}
		
		HashMap<String,UnconformityItemDPA> hash=new HashMap<String, UnconformityItemDPA>();
		Iterator iterAux=items.iterator();
		while(iterAux.hasNext()){
			UnconformityItemDPA item=(UnconformityItemDPA)iterAux.next();
			hash.put(item.getType(), item);
		}
		items.clear();
		Iterator iter= data.getItems().iterator();
		while(iter.hasNext()){
			UnconformityItemDPAData item=(UnconformityItemDPAData)iter.next();
			UnconformityItemDPA itemDPA=new UnconformityItemDPA(item);
			if(hash.containsKey(item.getType())){
				 hash.get(item.getType()).setComment(item.getComment());
				 hash.get(item.getType()).setExpirity(item.getExpirity());
				 hash.get(item.getType()).setNotify(item.getNotify());
				 items.add(hash.get(item.getType()));
			}else{
				items.add(itemDPA);
			}
		}
		
	}
	
	public UnconformityDPAData getUnconformityDPAData(UserSessionData userSessionData, boolean includeRelatedInformation){
		UnconformityDPAData data=new UnconformityDPAData();
		data.setId(id);
		data.setStatus(status);
		data.setDate(date);
		data.setComment(comment);
		data.setUserId(userId);
		data.setType((UnconformityTypeDPADataType)type.getData());
		if(type.getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)){
			data.setCheck(check);
			if(includeRelatedInformation){
				Session session = HibernateUtil.currentSession();
				Query query = session.createSQLQuery("select vc.date ,v.id , v.matricula, v.divOrg1,vc.name,v.divOrg3 from vehiclecheck as vc, vehicles as v where vc.vehicle=v.id and vc.vehicleCheckId="+check);
				List result=query.list();
				Iterator iter=result.iterator();
				Object[] objs=(Object[] )iter.next();
				data.setRelatedInformation("date", objs[0]);
				data.setRelatedInformation("id", objs[1]);
				data.setRelatedInformation("matricula", objs[2]);
				
				
				query=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+objs[3]);
				
				DivOrgItemLevel1 divOrg=(DivOrgItemLevel1) query.uniqueResult();
				String divOrgLevel1="";
				String divOrgLevel2="";
				String divOrgLevel3="";
				String contractor="";
				divOrgLevel1=divOrg.getResourceKey();
				contractor=divOrgLevel1;
				
				if(divOrg.getDivOrgItemLevel2()!=null){
					divOrgLevel1= divOrg.getDivOrgItemLevel2().getResourceKey();
					divOrgLevel2=divOrg.getResourceKey();
					if( divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()!=null){
						divOrgLevel1=divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
						divOrgLevel2=divOrg.getDivOrgItemLevel2().getResourceKey();
						divOrgLevel3=divOrg.getResourceKey();
					}
					
					contractor=divOrgLevel1;
				}
				
				if(objs[5]!=null && userSessionData.getCorporationId()==CorporationData.WEYERHAEUSER){
					query=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+objs[5]);
					
					divOrg=(DivOrgItemLevel1) query.uniqueResult();
					divOrgLevel1=divOrg.getResourceKey();	
					
					if(divOrg.getDivOrgItemLevel2()!=null){
						divOrgLevel1= divOrg.getDivOrgItemLevel2().getResourceKey();
						divOrgLevel2=divOrg.getResourceKey();
						if( divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()!=null){
							divOrgLevel1=divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
							divOrgLevel2=divOrg.getDivOrgItemLevel2().getResourceKey();
							divOrgLevel3=divOrg.getResourceKey();
						}
					}
					
					contractor=divOrgLevel1;
				}
				
				data.setRelatedInformation("contractor", contractor);
				data.setRelatedInformation("divOrgLevel1", divOrgLevel1);
				data.setRelatedInformation("divOrgLevel2", divOrgLevel2);
				data.setRelatedInformation("divOrgLevel3", divOrgLevel3);
				
				data.setRelatedInformation("typeCheckList", objs[4]);
				
			}
		
		}else if(type.getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)){
			data.setTelematicControl(telematicControl.getData(userSessionData));
		}else if(type.getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
			data.setDriverId(driverId);
			if(includeRelatedInformation){
				Session session = HibernateUtil.currentSession();
				Query query = session.createSQLQuery("select d.firstName,d.lastName,d.divorg1 from drivers as d where d.id="+driverId);
				List result=query.list();
				Iterator iter=result.iterator();
				Object[] objs=(Object[] )iter.next();
				data.setRelatedInformation("firstName", objs[0]);
				data.setRelatedInformation("lastName", objs[1]);
				data.setRelatedInformation("fullName", objs[0]+" "+objs[1]);
				
				query=session.createQuery("select d from DivOrgItemLevel1 as d where d.id="+objs[2]);
				
				DivOrgItemLevel1 divOrg=(DivOrgItemLevel1) query.uniqueResult();
				
				String divOrgLevel1="";
				String divOrgLevel2="";
				String divOrgLevel3="";
				divOrgLevel1=divOrg.getResourceKey();
				if(divOrg.getDivOrgItemLevel2()!=null){
					divOrgLevel1= divOrg.getDivOrgItemLevel2().getResourceKey();
					divOrgLevel2=divOrg.getResourceKey();
					if( divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()!=null){
						divOrgLevel1=divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
						divOrgLevel2=divOrg.getDivOrgItemLevel2().getResourceKey();
						divOrgLevel3=divOrg.getResourceKey();
					}
				}
				data.setRelatedInformation("divOrgLevel1", divOrgLevel1);
				data.setRelatedInformation("divOrgLevel2", divOrgLevel2);
				data.setRelatedInformation("divOrgLevel3", divOrgLevel3);
			}
		}
		
		Iterator iter=items.iterator();
		while(iter.hasNext()){
			UnconformityItemDPA item=(UnconformityItemDPA)iter.next();
			data.getItems().add(item.getUnconformityItemDPAData(userSessionData));
		}
		
		
		return data;
	}
	
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set getItems() {
		return items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

	public UnconformityTypeDPA getType() {
		return type;
	}


	public void setType(UnconformityTypeDPA type) {
		this.type = type;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public TelematicControl getTelematicControl() {
		return telematicControl;
	}

	public void setTelematicControl(TelematicControl telematicControl) {
		this.telematicControl = telematicControl;
	}
 

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getCorpId() {
		return corpId;
	}

	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}
	
	
	
	
}
