package persistence.activity.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.administration.user.tables.User;
import persistence.datatype.tables.CEPAActivityScheduleState;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;

import communication.activity.report.CEPAActivityScheduleDataType;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAScheduleActivityInstructorData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;

public class CEPAScheduleActivity extends CEPAActivity  implements Serializable {

	private static final long serialVersionUID = 1L;
	protected DivOrgItemLevel1 divOrg1;
	protected DivOrgItemLevel1 divOrg2;
	protected DivOrgItemLevel1 divOrg3;
	
	protected CEPAActivityScheduleState state;
	
	protected Set instructorSet;
	
	public void addInstructor(CEPAScheduleActivityInstructor instructor){
		if (instructorSet == null){
			instructorSet = new HashSet();
		}
		instructorSet.add(instructor);
	}
	
	/**
	 * @return the instructorSet
	 */
	public Set getInstructorSet() {
		return instructorSet;
	}

	/**
	 * @param instructorSet the instructorSet to set
	 */
	public void setInstructorSet(Set instructorSet) {
		this.instructorSet = instructorSet;
	}

	public CEPAActivityScheduleState getState() {
		return state;
	}

	public void setState(CEPAActivityScheduleState state) {
		this.state = state;
	}

	public CEPAScheduleActivity() {
		super();
	}
	
	public CEPAScheduleActivity(CEPAActivityScheduleData data)throws HibernateException,Exception {
		super(data);
		
		Session session = HibernateUtil.currentSession();
		state = (CEPAActivityScheduleState) session.load(CEPAActivityScheduleState.class,data.getState().getResourceBundleId());
		
		/*
		 * Instructores de la actividad:
		 */
		if (data != null && data.getInstructors() != null && data.getInstructors().size() > 0){
			Iterator it = data.getInstructors().iterator();
			while (it.hasNext()){
				CEPAScheduleActivityInstructorData instructorData = (CEPAScheduleActivityInstructorData) it.next();
				User user = (User)session.load(User.class, instructorData.getUser().getLoginName());
				CEPAScheduleActivityInstructor instructor = new CEPAScheduleActivityInstructor(this, user);
				addInstructor(instructor);
			}			
		}
		
	    Iterator it = data.getDivOrgSet().iterator();
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }		
	    
	    
	    
		
	}


	public DivOrgItemLevel1 getDivOrg1() {
		return divOrg1;
	}


	public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
		this.divOrg1 = divOrg1;
	}


	public DivOrgItemLevel1 getDivOrg2() {
		return divOrg2;
	}


	public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
		this.divOrg2 = divOrg2;
	}


	public DivOrgItemLevel1 getDivOrg3() {
		return divOrg3;
	}


	public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
		this.divOrg3 = divOrg3;
	}
		
	public CEPAActivityScheduleData getCEPAScheduleActivityData() {
		
		CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
		
	    GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
	    GeoItemData countryData = (GeoItemData)corpCountry.getData();
	    Collection<GeoItemData> countries = new LinkedList<GeoItemData>();
	    countries.add(countryData);
	    GeoLabelData labelData = null; 
	    if(geoLevel1 != null) {
	        GeoItemData level1 = (GeoItemData)geoLevel1.getData();
		    Collection<GeoItemData> level1s = new LinkedList<GeoItemData>();
		    level1s.add(level1);
	        if(getGeoLevel1().getGeoLevel2() == null) {
		        level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
	        }else {
	            
		        level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
	            
		        GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
		        level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

		        Collection<GeoItemData> level2s = new LinkedList<GeoItemData>();
			    level2s.add(level2);
			    
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
	        }
	    }else {
	        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
	    }
		
		
		Collection<DivOrgData> col=new LinkedList<DivOrgData>();
		if(divOrg1!=null){
			col.add(Util.getDivOrgData(divOrg1,getCorporation().getData()));
		}
		if(divOrg2!=null){
			col.add(Util.getDivOrgData(divOrg2,getCorporation().getData()));
		}
		if(divOrg3!=null){
			col.add(Util.getDivOrgData(divOrg3,getCorporation().getData()));
		}
		
		/*return new CEPAActivityScheduleData(col,labelData,getState().getResourceBundleId(),getActivityId(),null,
				getType().getResourceBundleId(),getCorporation().getData(),getCity(),getDate());
		*/
		
		CEPAActivityScheduleData activity =  new CEPAActivityScheduleData(col,labelData,getState().getResourceBundleId(),
				getActivityId(),getDefinition().getCEPAActivityDefinitionData(),
				getType().getResourceBundleId(),getCorporation().getData(),
				getCity(),getDate(),getIsOld(),getDateExpiry(),getDrivingExperience(),
				this.observations,participants,this.theme,duration,file);
		
		LinkedList<CEPAScheduleActivityInstructorData> instructors = new LinkedList<CEPAScheduleActivityInstructorData>();
	    if (instructorSet != null){
	        Iterator instructorsIt = instructorSet.iterator();
	         while(instructorsIt.hasNext()) {
	        	 CEPAScheduleActivityInstructor instructor =  (CEPAScheduleActivityInstructor) instructorsIt.next();
	        	 if (instructor != null && instructor.getData() != null){
	        		 instructors.add(instructor.getData());
	        	 }
	         }
	    }
	    activity.setInstructors(instructors);
	    
	    
	    return activity;
	    
	}
	
	
	
	
	
    public CEPAActivityScheduleDataType getCEPAActivityScheduleReportData(Integer participantsCount) {
        
        try {
            String divOrgName11 = null;
            String divOrgName12 = null;
            String divOrgName13 = null;
            if(divOrg1 != null) {
                divOrgName11 = divOrg1.getResourceKey();
                if(divOrg1.getDivOrgItemLevel2() != null) {
                    divOrgName12 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName13 = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divOrgName21 = null;
            String divOrgName22 = null;
            String divOrgName23 = null;
            if(divOrg2 != null) {
                divOrgName21 = divOrg2.getResourceKey();
                if(divOrg2.getDivOrgItemLevel2() != null) {
                    divOrgName22 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName23 = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divOrgName31 = null;
            String divOrgName32 = null;
            String divOrgName33 = null;
            if(divOrg3 != null) {
                divOrgName31 = divOrg3.getResourceKey();
                if(divOrg3.getDivOrgItemLevel2() != null) {
                    divOrgName32 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName33 = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divgeo2 = null;
            String divgeo3 = null;
            Iterator it = corpCountry.getGeoLevel1Set().iterator();
            if(it.hasNext()){
                GeoLevel1 level1 = (GeoLevel1)it.next();
                divgeo2 = level1.getResourceKey();
                if(level1.getGeoLevel2() != null) {
                    divgeo3 = level1.getGeoLevel2().getResourceKey();
                }
            }
            
    //        Integer participantsCount = new Integer(0);
            
            return new CEPAActivityScheduleDataType(getActivityId(),getType().getResourceBundleId(),getDate(),corpCountry.getPk().getCountry().getResourceKey(),
                		participantsCount,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,state.getResourceBundleId());

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
	
    public CEPAActivityScheduleDataType getCEPAActivityScheduleReportData2(Integer participantsCount) {
        
        try {
            String divOrgName11 = null;
            String divOrgName12 = null;
            String divOrgName13 = null;
            if(divOrg1 != null) {
                divOrgName13 = divOrg1.getResourceKey();
                if(divOrg1.getDivOrgItemLevel2() != null) {
                    divOrgName12 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName11 = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divOrgName21 = null;
            String divOrgName22 = null;
            String divOrgName23 = null;
            if(divOrg2 != null) {
                divOrgName23 = divOrg2.getResourceKey();
                if(divOrg2.getDivOrgItemLevel2() != null) {
                    divOrgName22 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName21 = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divOrgName31 = null;
            String divOrgName32 = null;
            String divOrgName33 = null;
            if(divOrg3 != null) {
                divOrgName33 = divOrg3.getResourceKey();
                if(divOrg3.getDivOrgItemLevel2() != null) {
                    divOrgName32 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName31 = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divgeo2 = null;
            String divgeo3 = null;
            Iterator it = corpCountry.getGeoLevel1Set().iterator();
            if(it.hasNext()){
                GeoLevel1 level1 = (GeoLevel1)it.next();
                divgeo2 = level1.getResourceKey();
                if(level1.getGeoLevel2() != null) {
                    divgeo3 = level1.getGeoLevel2().getResourceKey();
                }
            }
            
    //        Integer participantsCount = new Integer(0);
            
            return new CEPAActivityScheduleDataType(getActivityId(),getType().getResourceBundleId(),getDate(),corpCountry.getPk().getCountry().getResourceKey(),
                		participantsCount,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,state.getResourceBundleId());

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
