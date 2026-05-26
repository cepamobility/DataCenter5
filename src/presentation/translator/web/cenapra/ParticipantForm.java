/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.cenapra;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ParticipantData;
import communication.datatype.tables.ActionOrganizationDataType;
import communication.datatype.tables.EducationDataType;
import communication.datatype.tables.PopulationGroupDataType;
/**
 * @author jrodriguez
 */
public class ParticipantForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String birthMonth;
    private String birthYear;
    private String startDate;
    private String startMonth;
    private String startYear;

    private String group;
    private String othergroup;
    private String organization;
    private String otherorganization;
    
    private String state;
    private String city;
    private String delegation;
    private String postalcode;
    private String address;
    private String phone;
    private String movile;
    private String email;

    private String medical;
    private String paramedical;
    private String nurse;
    private String police;
    private String vialinspector;
    private String teacher;
    private String prevent;
    private String otherprofesion;

    private String education;
    private String othereducation;

    private String capacitation;
    private String capacitationtrainning;
    private String capacitationtype;
    
    private String teachexperience;
    private String first;
    private String second;
    private String third;
    private String otherteach;

    private String observations;

    private String comment;

    public ParticipantForm() {
        super();
    }

    public ParticipantForm(ParticipantData participant) {
        super();
        id = String.valueOf(participant.getId());
        firstName = participant.getFirstName();
        lastName = participant.getLastName();
        if(participant.getBirthDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(participant.getBirthDate());
            birthDate = String.valueOf(c.get(Calendar.DATE));
            birthMonth = String.valueOf(c.get(Calendar.MONTH)+1);
            birthYear = String.valueOf(c.get(Calendar.YEAR));
        }
        if(participant.getInclusionDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(participant.getInclusionDate());
            startDate = String.valueOf(c.get(Calendar.DATE));
            startMonth = String.valueOf(c.get(Calendar.MONTH)+1);
            startYear = String.valueOf(c.get(Calendar.YEAR));
        }

        group = (participant.getGroup() == null) ? "" : participant.getGroup().getResourceBundleId(); 
        othergroup = participant.getOthergroup();
        organization = (participant.getOrganization() == null) ? "" : participant.getOrganization().getResourceBundleId(); 
        otherorganization = participant.getOtherorganization();
            
        GeoItemData geoItem1 = (GeoItemData)participant.getCountry().getItemIterator().next();
        country1 = String.valueOf(geoItem1.getId());
        if(geoItem1.getSubItems() != null) {
            GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
            country2 = String.valueOf(geoItem2.getId());
            if(geoItem2.getSubItems() != null) {
                GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
                country3 = String.valueOf(geoItem3.getId());
            }
        }

        Iterator it = participant.getDivOrgsIterator();
        int i = 1;
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
            setDivOrg(String.valueOf(item1.getId()),i,1);
            if(item1.getSubItems() != null) {
                ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
                setDivOrg(String.valueOf(item2.getId()),i,2);
                if(item2.getSubItems() != null) {
                    ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
                    setDivOrg(String.valueOf(item3.getId()),i,3);
                }
            }
            i++;
        }

        state = participant.getState();
        city = participant.getCity();
        delegation = participant.getDelegation();
        postalcode = participant.getPostalcode();
        address = participant.getAddress();
        phone = participant.getPhone();
        movile = participant.getMovile();
        email = participant.getEmail();

        medical = String.valueOf(participant.isMedical());
        paramedical = String.valueOf(participant.isParamedical());
        nurse = String.valueOf(participant.isNurse());
        police = String.valueOf(participant.isPolice());
        vialinspector = String.valueOf(participant.isVialinspector());
        teacher = String.valueOf(participant.isTeacher());
        prevent = String.valueOf(participant.isPrevent());
        otherprofesion = participant.getOtherprofession();

        education = (participant.getEducation() != null) ? participant.getEducation().getResourceBundleId() : "";
        othereducation = participant.getOthereducation();

        capacitation = String.valueOf(participant.isSecurity());
        capacitationtrainning = String.valueOf(participant.isSecuritytrainning());
        capacitationtype = participant.getSecuritydesc();
        
        teachexperience = String.valueOf(participant.isTeachexperience());
        first = String.valueOf(participant.isFirst());
        second = String.valueOf(participant.isSecond());
        third = String.valueOf(participant.isThird());
        otherteach = participant.getOtherteach();

        observations = participant.getObservations();
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

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getDelegation() {
        return delegation;
    }

    public String getEmail() {
        return email;
    }

    public String getMovile() {
        return movile;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getState() {
        return state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMovile(String movile) {
        this.movile = movile;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOtherprofesion() {
        return otherprofesion;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
    public String getCapacitation() {
        return capacitation;
    }

    public String getCapacitationtype() {
        return capacitationtype;
    }

    public void setCapacitation(String capacitation) {
        this.capacitation = capacitation;
    }

    public void setCapacitationtype(String capacitationtype) {
        this.capacitationtype = capacitationtype;
    }

    public String getOtherteach() {
        return otherteach;
    }

    public String getTeachexperience() {
        return teachexperience;
    }

    public void setOtherteach(String otherteach) {
        this.otherteach = otherteach;
    }

    public void setTeachexperience(String teachexperience) {
        this.teachexperience = teachexperience;
    }

    public String validate(UserSessionData userSessionData) {
        if(empty(firstName)) {
            return "cenapra.dataerror.firstname";
        }
        if(empty(lastName)) {
            return "cenapra.dataerror.lastname";
        }
        try{
            int day = Integer.parseInt(birthDate);
            int month= Integer.parseInt(birthMonth);
            int year =Integer.parseInt(birthYear);
            boolean checkBirth = Util.checkDate(day,month,year);
            if(checkBirth == false) {
                return "cenapra.dataerror.birthdate";
            }
        }catch(Exception e) {
            return "cenapra.dataerror.birthdate";
        }
        for(int j = 0; j < userSessionData.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= userSessionData.getFilter().getDivOrgLevel(j); i++) {
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "cenapra.dataerror.divorg";
                }
            }
        }
        if(Util.empty(getCountry1())) {
            return "cenapra.dataerror.country";
        }
        return null;
    }

    private boolean empty(String value) {
        if(value == null || value.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public ParticipantData getParticipantData(UserSessionData userSessionData) {
        ParticipantData data = new ParticipantData();
        if(!empty(id)) {
            data.setId(new Integer(id));
        }
        data.setFirstName(firstName);
        data.setLastName(lastName);
        data.setBirthDate(getDate(birthDate,birthMonth,birthYear));
        
        data.setGroup(new PopulationGroupDataType(group));
        data.setOthergroup(othergroup);
        data.setOrganization(new ActionOrganizationDataType(organization));
        data.setOtherorganization(otherorganization);
        
        data.setCountry(getGeoLabelData());

        data.setCity(city);
        data.setState(state);
        data.setAddress(address);
        data.setDelegation(delegation);
        data.setPostalcode(postalcode);
        data.setPhone(phone);
        data.setMovile(movile);
        data.setEmail(email);

        data.setInclusionDate(getDate(startDate,startMonth,startYear));
        
        data.setMedical(Util.isChecked(medical));
        data.setParamedical(Util.isChecked(paramedical));
        data.setNurse(Util.isChecked(nurse));
        data.setPolice(Util.isChecked(police));
        data.setVialinspector(Util.isChecked(vialinspector));
        data.setTeacher(Util.isChecked(teacher));
        data.setPrevent(Util.isChecked(prevent));
        data.setOtherprofession(otherprofesion);

        data.setEducation(new EducationDataType(education));
        data.setOthereducation(othereducation);

        data.setSecurity(Boolean.parseBoolean(capacitation));
        data.setSecuritytrainning(Boolean.parseBoolean(capacitationtrainning));
        data.setSecuritydesc(capacitationtype);

        data.setTeachexperience(Boolean.parseBoolean(teachexperience));
        data.setFirst(Boolean.parseBoolean(first));
        data.setSecond(Boolean.parseBoolean(second));
        data.setThird(Boolean.parseBoolean(third));
        data.setOtherteach(otherteach);

        data.setObservations(observations);
        data.setDivOrgs(getDivOrgs(userSessionData));
        
        return data;
    }

    private Date getDate(String date,String month,String year) {
        if(empty(date) || empty(month) || empty(year)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,Integer.parseInt(date));
        calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
        calendar.set(Calendar.YEAR,Integer.parseInt(year));

        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCapacitationtrainning() {
        return capacitationtrainning;
    }

    public String getEducation() {
        return education;
    }

    public String getFirst() {
        return first;
    }

    public String getGroup() {
        return group;
    }

    public String getMedical() {
        return medical;
    }

    public String getNurse() {
        return nurse;
    }

    public String getOrganization() {
        return organization;
    }

    public String getOthereducation() {
        return othereducation;
    }

    public String getParamedical() {
        return paramedical;
    }

    public String getPolice() {
        return police;
    }

    public String getPrevent() {
        return prevent;
    }

    public String getSecond() {
        return second;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getThird() {
        return third;
    }

    public String getVialinspector() {
        return vialinspector;
    }

    public void setCapacitationtrainning(String capacitationtrainning) {
        this.capacitationtrainning = capacitationtrainning;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOthereducation(String othereducation) {
        this.othereducation = othereducation;
    }

    public void setOtherprofesion(String otherprofesion) {
        this.otherprofesion = otherprofesion;
    }

    public void setParamedical(String paramedical) {
        this.paramedical = paramedical;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public void setVialinspector(String vialinspector) {
        this.vialinspector = vialinspector;
    }

	public String getOthergroup() {
		return othergroup;
	}

	public void setOthergroup(String othergroup) {
		this.othergroup = othergroup;
	}

	public String getOtherorganization() {
		return otherorganization;
	}

	public void setOtherorganization(String otherorganization) {
		this.otherorganization = otherorganization;
	}
    
}
