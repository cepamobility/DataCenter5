package persistence.administration.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

import communication.administration.dpa.CorrectiveActionDPAData;
import communication.administration.dpa.CorrectiveActionImageDPAData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;

import persistence.datatype.tables.UnconformityTypeDPA;
import persistence.driver.tables.TelematicControl;
import persistence.hibernate.HibernateUtil;
import persistence.vehicle.tables.VehicleCheck;

/**
 * 
 * @author fcarriquiry
 *
 */
public class CorrectiveActionDPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date date;
	
	private Integer type;
	private String comment;
	
	private UnconformityDPA unconformityDPA;
	
	private Set<UnconformityItemDPA> items;
	
	private Set<CorrectiveActionImageDPA> images;
	
	public CorrectiveActionDPA(){
		items=new HashSet();
	}
	
	public CorrectiveActionDPA(CorrectiveActionDPAData data){
		Session session = HibernateUtil.currentSession();
		
		this.type=data.getType();
		this.comment=data.getComment();
		this.date=data.getDate();
		this.unconformityDPA=(UnconformityDPA)session.load(UnconformityDPA.class, data.getUnconformityDPAData().getId());
		items=new HashSet();
		Iterator iter= data.getItems().iterator();
		while(iter.hasNext()){
			UnconformityItemDPAData item=(UnconformityItemDPAData)iter.next();
			UnconformityItemDPA itemDPA=(UnconformityItemDPA)session.load(UnconformityItemDPA.class, item.getId());
			items.add(itemDPA);
		}
		
		this.images=new HashSet<CorrectiveActionImageDPA>();
		iter= data.getImages().iterator();
		while(iter.hasNext()){
			CorrectiveActionImageDPAData image=(CorrectiveActionImageDPAData)iter.next();
			CorrectiveActionImageDPA imageDPA=new CorrectiveActionImageDPA(image.getFileName(), image.getText());
			images.add(imageDPA);
		}
		
	}
	
	public void updateData(CorrectiveActionDPAData data){
		Session session = HibernateUtil.currentSession();
		
		this.type=data.getType();
		this.comment=data.getComment();
		this.date=data.getDate();
		
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
				 items.add(hash.get(item.getType()));
			}else{
				items.add(itemDPA);
			}
		}
		
		this.images.clear();
		iter= data.getImages().iterator();
		while(iter.hasNext()){
			CorrectiveActionImageDPAData item=(CorrectiveActionImageDPAData)iter.next();
			CorrectiveActionImageDPA itemDPA=new CorrectiveActionImageDPA(item.getFileName(),item.getText());
			images.add(itemDPA);
			
		}
		
	}
	
	public CorrectiveActionDPAData getCorrectiveActionDPAData(UserSessionData userSessionData){
		CorrectiveActionDPAData data=new CorrectiveActionDPAData();
		data.setId(id);
		data.setDate(date);
		data.setComment(comment);
		data.setType(type);
		Iterator iter=items.iterator();
		while(iter.hasNext()){
			UnconformityItemDPA item=(UnconformityItemDPA)iter.next();
			data.getItems().add(item.getUnconformityItemDPAData(userSessionData));
		}
		
		iter=images.iterator();
		while(iter.hasNext()){
			CorrectiveActionImageDPA item=(CorrectiveActionImageDPA)iter.next();
			data.getImages().add(item.getData());
		}
		
		data.setUnconformityDPAData(unconformityDPA.getUnconformityDPAData(userSessionData,false));
		
		return data;
	}
	
	

	public UnconformityDPA getUnconformityDPA() {
		return unconformityDPA;
	}

	public void setUnconformityDPA(UnconformityDPA unconformityDPA) {
		this.unconformityDPA = unconformityDPA;
	}

	public Date getDate() {
		return date;
	}

	public Set<UnconformityItemDPA> getItems() {
		return items;
	}

	public void setItems(Set<UnconformityItemDPA> items) {
		this.items = items;
	}

	public Set<CorrectiveActionImageDPA> getImages() {
		return images;
	}

	public void setImages(Set<CorrectiveActionImageDPA> images) {
		this.images = images;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	
	
	
	
}
