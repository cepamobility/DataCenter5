/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.tables;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.ActionOrganization;
import persistence.datatype.tables.Education;
import persistence.datatype.tables.PopulationGroup;
import persistence.datatype.tables.VialExperience;
import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ParticipantData;
import communication.cenapra.tables.ParticipantMainAttributes;
import communication.datatype.tables.ActionOrganizationDataType;
import communication.datatype.tables.EducationDataType;
import communication.datatype.tables.PopulationGroupDataType;
import communication.datatype.tables.VialExperienceDataType;

public class CENAPRAParticipant {

    private Integer id;

    private String firstName;
    private String lastName;
    private Date birthDate;

    private PopulationGroup population;
    private String otherpopulation;
    private ActionOrganization organization;
    private String otherorganization;
    
    private String state;
    private String city;
    private String delegation;
    private String address;
    private String postalcode;
    private String phone;
    private String movile;
    private String email;

    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;

    private CorporationCountry corpCountry;

    private Date inclusionDate;
    private Date exclusionDate;

    private boolean medical;
    private boolean paramedical;
    private boolean nurse;
    private boolean police;
    private boolean vialinspector;
    private boolean teacher;
    private boolean prevent;
    private String otherprofession;

    private Education education;
    private String othereducation;

    private boolean security;
    private boolean securitytrainning;
    private String securitydesc;

    private boolean teachexperience;
    private boolean first;
    private boolean second;
    private boolean third;
    private String otherteach;

    private String observations;
    private boolean activity;
    
    public CENAPRAParticipant() {
    }

    public CENAPRAParticipant(ParticipantData participant, Session session, Integer corporationId) {
        setData(participant,session,corporationId);
    }
    
    public void setData(ParticipantData participant, Session session, Integer corporationId) {
        firstName = participant.getFirstName();
        lastName = participant.getLastName();
        birthDate = participant.getBirthDate();

        if(participant.getGroup() != null && !Util.empty(participant.getGroup().getResourceBundleId())) {
            population = (PopulationGroup)session.load(PopulationGroup.class,participant.getGroup().getResourceBundleId());
        }else {
            population = null;
        }
        otherpopulation = participant.getOthergroup();
        if(participant.getOrganization() != null && !Util.empty(participant.getOrganization().getResourceBundleId())) {
            organization = (ActionOrganization)session.load(ActionOrganization.class,participant.getOrganization().getResourceBundleId());
        }else {
            organization = null;
        }
        otherorganization = participant.getOtherorganization();

        state = participant.getState();
        city = participant.getCity();
        delegation = participant.getDelegation();
        address = participant.getAddress();
        postalcode = participant.getPostalcode();
        phone = participant.getPhone();
        movile = participant.getMovile();
        email = participant.getEmail();

        GeoItemData itemData1 = (GeoItemData)participant.getCountry().getItemIterator().next();
        GeoItemData itemData2 = null; 
        while(itemData1.getSubItems() != null) {
            itemData2 = itemData1;
            itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
        }
        
        Iterator it = participant.getDivOrgsIterator();
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
        
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),corporationId));

        inclusionDate = participant.getInclusionDate();
        exclusionDate = participant.getExclusionDate();

        medical = participant.isMedical();
        paramedical = participant.isParamedical();
        nurse = participant.isNurse();
        police = participant.isPolice();
        vialinspector = participant.isVialinspector();
        teacher = participant.isTeacher();
        prevent = participant.isPrevent();
        otherprofession = participant.getOtherprofession();

        if(participant.getEducation() != null && !Util.empty(participant.getEducation().getResourceBundleId())) {
            education = (Education)session.load(Education.class,participant.getEducation().getResourceBundleId());
        }else {
            education = null;
        }
        othereducation = participant.getOthereducation();

        security = participant.isSecurity();
        securitytrainning = participant.isSecuritytrainning();
        securitydesc = participant.getSecuritydesc();

        teachexperience = participant.isTeachexperience();
        first = participant.isFirst();
        second = participant.isSecond();
        third = participant.isThird();
        otherteach = participant.getOtherteach();

        observations = participant.getObservations();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public CorporationCountry getCorpCountry() {
        return corpCountry;
    }

    public String getDelegation() {
        return delegation;
    }

    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }

    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }

    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
    }

    public Education getEducation() {
        return education;
    }

    public String getEmail() {
        return email;
    }

    public Date getExclusionDate() {
        return exclusionDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getId() {
        return id;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMovile() {
        return movile;
    }

    public String getObservations() {
        return observations;
    }

    public String getOthereducation() {
        return othereducation;
    }

    public String getOtherprofession() {
        return otherprofession;
    }

    public String getOtherteach() {
        return otherteach;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public boolean isSecurity() {
        return security;
    }

    public String getSecuritydesc() {
        return securitydesc;
    }

    public String getState() {
        return state;
    }

    public boolean isTeachexperience() {
        return teachexperience;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCorpCountry(CorporationCountry corpCountry) {
        this.corpCountry = corpCountry;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMovile(String movile) {
        this.movile = movile;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setOthereducation(String othereducation) {
        this.othereducation = othereducation;
    }

    public void setOtherprofession(String otherprofession) {
        this.otherprofession = otherprofession;
    }

    public void setOtherteach(String otherteach) {
        this.otherteach = otherteach;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public void setSecuritydesc(String securitydesc) {
        this.securitydesc = securitydesc;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTeachexperience(boolean teachexperience) {
        this.teachexperience = teachexperience;
    }

    /**
     * @param userRequest 
     * @return
     */
    public ParticipantMainAttributes getMainAttributes(UserSessionData userSessionData) {
        GeoLabelData labelData = null; 
        GeoLabelAttributes[] geoAttrs = corpCountry.getPk().getCorporation().getData().getGeoLabels();
        GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
        countries.add(countryData);
        GeoLevel1 geoLevel1 = (corpCountry.getGeoLevel1Set().size() > 0) ? (GeoLevel1)corpCountry.getGeoLevel1Set().iterator().next() : null;
        if(geoLevel1 != null) {
            GeoItemData level1 = (GeoItemData)geoLevel1.getData();
            Collection level1s = new LinkedList();
            level1s.add(level1);
            if(geoLevel1.getGeoLevel2() == null) {
                level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
            }else {
                
                level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
                
                GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
                level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

                Collection level2s = new LinkedList();
                level2s.add(level2);
                
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
            }
        }else {
            labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
        }
        return new ParticipantMainAttributes(id,firstName,lastName,labelData,birthDate,city);
    }

    // Devuelve la cantidad de niveles de la division que se le pasa como par�metro
    private int cantNiveles(DivOrgItemLevel1 divOrg) {
        if ((divOrg.getDivOrgItemLevel2() != null) && (divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3() != null)) {
            return 3;
        }else {
            if (divOrg.getDivOrgItemLevel2() != null) {
                return 2;
            } else { 
                return 1;
            }
        }
    }

    public ParticipantData getData() {
        
        CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
        
        GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
        GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
        countries.add(countryData);
        GeoLabelData labelData = null; 
        GeoLevel1 geoLevel1 = (corpCountry.getGeoLevel1Set().size() > 0) ? (GeoLevel1)corpCountry.getGeoLevel1Set().iterator().next() : null;
        if(geoLevel1 != null) {
            GeoItemData level1 = (GeoItemData)geoLevel1.getData();
            Collection level1s = new LinkedList();
            level1s.add(level1);
            if(geoLevel1.getGeoLevel2() == null) {
                level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
            }else {
                
                level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
                
                GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
                level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

                Collection level2s = new LinkedList();
                level2s.add(level2);
                
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
            }
        }else {
            labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
        }
        
        Collection divOrgs = new LinkedList();
        if(divOrg1 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg1,corporationData);
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg2 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg2,corporationData);
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg3 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg3,corporationData);
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }

        PopulationGroupDataType populationData = (population != null) ? (PopulationGroupDataType)population.getData() : null;
        ActionOrganizationDataType organizationData = (organization != null) ? (ActionOrganizationDataType)organization.getData() : null;
        EducationDataType educationData = (education != null) ? (EducationDataType)education.getData() : null;
        return new ParticipantData(id,firstName,lastName,labelData,birthDate,city,
                populationData,otherpopulation,organizationData,otherorganization,state,delegation,address,postalcode,phone,movile,email,
                inclusionDate,exclusionDate,medical,paramedical,nurse,police,vialinspector,teacher,prevent,otherprofession,
                educationData,othereducation,security,securitytrainning,securitydesc,teachexperience,
                first,second,third,otherteach,observations,divOrgs);
    }

    private DivOrgData getDivOrgData(DivOrgItemLevel1 level1,CorporationData corporationData) {
        DivOrgData divOrgData = corporationData.getDivOrgData(level1.getDivOrg().getId().intValue());
        LabelItemAttribute[] labelAttrs = divOrgData.getLabels();
        ItemData itemData = new ItemData(level1.getAttributes());
        Collection items1 = new LinkedList();
        items1.add(itemData);
        LabelItemData labelData = null; 
        if(level1.getDivOrgItemLevel2() != null) {
            ItemData item2 = new ItemData(level1.getDivOrgItemLevel2().getAttributes());
            Collection items2 = new LinkedList();
            items2.add(item2);
            if(level1.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
                item2.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items1,labelAttrs[1].getShowLevel()));
                labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items2,labelAttrs[0].getShowLevel());
            }else {
                
                item2.setSubItems(new LabelItemData(labelAttrs[2].getId(),labelAttrs[2].getName(),items1,labelAttrs[2].getShowLevel()));
                
                ItemData item3 = new ItemData(level1.getDivOrgItemLevel2().getDivOrgItemLevel3().getAttributes());
                item3.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items2,labelAttrs[1].getShowLevel()));

                Collection items3 = new LinkedList();
                items3.add(item3);
                
                labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items3,labelAttrs[0].getShowLevel());
            }
        }else {
            labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items1,labelAttrs[0].getShowLevel());
        }
        divOrgData.setDivOrgLevels(labelData);
        return divOrgData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFirst() {
        return first;
    }

    public PopulationGroup getPopulation() {
        return population;
    }

    public boolean isMedical() {
        return medical;
    }

    public boolean isNurse() {
        return nurse;
    }

    public ActionOrganization getOrganization() {
        return organization;
    }

    public boolean isParamedical() {
        return paramedical;
    }

    public boolean isPolice() {
        return police;
    }

    public boolean isPrevent() {
        return prevent;
    }

    public boolean isSecond() {
        return second;
    }

    public boolean isSecuritytrainning() {
        return securitytrainning;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public boolean isThird() {
        return third;
    }

    public boolean isVialinspector() {
        return vialinspector;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setPopulation(PopulationGroup population) {
        this.population = population;
    }

    public void setMedical(boolean medical) {
        this.medical = medical;
    }

    public void setNurse(boolean nurse) {
        this.nurse = nurse;
    }

    public void setOrganization(ActionOrganization organization) {
        this.organization = organization;
    }

    public void setParamedical(boolean paramedical) {
        this.paramedical = paramedical;
    }

    public void setPolice(boolean police) {
        this.police = police;
    }

    public void setPrevent(boolean prevent) {
        this.prevent = prevent;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public void setSecuritytrainning(boolean securitytrainning) {
        this.securitytrainning = securitytrainning;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public void setThird(boolean third) {
        this.third = third;
    }

    public void setVialinspector(boolean vialinspector) {
        this.vialinspector = vialinspector;
    }

	public boolean isActivity() {
		return activity;
	}

	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public String getOtherpopulation() {
		return otherpopulation;
	}

	public void setOtherpopulation(String otherpopulation) {
		this.otherpopulation = otherpopulation;
	}

	public String getOtherorganization() {
		return otherorganization;
	}

	public void setOtherorganization(String otherorganization) {
		this.otherorganization = otherorganization;
	}

}
