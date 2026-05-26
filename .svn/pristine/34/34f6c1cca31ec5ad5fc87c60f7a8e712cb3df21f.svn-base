/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.cenapra.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.ActionOrganizationDataType;
import communication.datatype.tables.EducationDataType;
import communication.datatype.tables.PopulationGroupDataType;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParticipantData extends ParticipantMainAttributes implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public static final String WITH_ACTIVITY = "1";
    public static final String WITHOUT_ACTIVITY = "2";
    public static final String ALL = "3";
    
    private PopulationGroupDataType group;
    private ActionOrganizationDataType organization; 
    
    private String state;
    private String delegation;
    private String address;
    private String postalcode;
    private String phone;
    private String movile;
    private String email;

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

    private EducationDataType education;
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

    private Collection divOrgs;

	private String othergroup;

	private String otherorganization;

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param country
     * @param birthDate
     * @param city
     * @param exclusionDate
     */
    public ParticipantData(Integer id, String firstName, String lastName, GeoLabelData country, Date birthDate, String city, Date exclusionDate) {
        super(id, firstName, lastName, country, birthDate, city, exclusionDate);
    }

    public ParticipantData() {
        super();
    }

    public ParticipantData(Integer id, String firstName, String lastName, GeoLabelData country, Date birthDate, String city, PopulationGroupDataType group, String othergroup, ActionOrganizationDataType organization, String otherorganization, String state, String delegation, String address, String postalcode, String phone, String movile, String email, Date inclusionDate, Date exclusionDate, boolean medical, boolean paramedical, boolean nurse, boolean police, boolean vialinspector, boolean teacher, boolean prevent, String otherprofession, EducationDataType education, String othereducation, boolean security, boolean securitytrainning, String securitydesc, boolean teachexperience, boolean first, boolean second, boolean third, String otherteach, String observations, Collection divOrgs) {
        super(id, firstName, lastName, country, birthDate, city);
        this.group = group;
        this.othergroup = othergroup;
        this.organization = organization;
        this.otherorganization = otherorganization;
        this.state = state;
        this.delegation = delegation;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.movile = movile;
        this.email = email;
        this.inclusionDate = inclusionDate;
        this.exclusionDate = exclusionDate;
        this.medical = medical;
        this.paramedical = paramedical;
        this.nurse = nurse;
        this.police = police;
        this.vialinspector = vialinspector;
        this.teacher = teacher;
        this.prevent = prevent;
        this.otherprofession = otherprofession;
        this.education = education;
        this.othereducation = othereducation;
        this.security = security;
        this.securitytrainning = securitytrainning;
        this.securitydesc = securitydesc;
        this.teachexperience = teachexperience;
        this.first = first;
        this.second = second;
        this.third = third;
        this.otherteach = otherteach;
        this.observations = observations;
        this.divOrgs = divOrgs;
    }

    public ParticipantData(Integer id) {
        super(id);
    }

    public String getAddress() {
        return address;
    }

    public String getDelegation() {
        return delegation;
    }

    public Collection getDivOrgs() {
        return divOrgs;
    }

    public EducationDataType getEducation() {
        return education;
    }

    public String getEmail() {
        return email;
    }

    public Date getExclusionDate() {
        return exclusionDate;
    }

    public boolean isFirst() {
        return first;
    }

    public PopulationGroupDataType getGroup() {
        return group;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public boolean isMedical() {
        return medical;
    }

    public String getMovile() {
        return movile;
    }

    public boolean isNurse() {
        return nurse;
    }

    public String getObservations() {
        return observations;
    }

    public ActionOrganizationDataType getOrganization() {
        return organization;
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

    public boolean isParamedical() {
        return paramedical;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isPolice() {
        return police;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public boolean isPrevent() {
        return prevent;
    }

    public boolean isSecond() {
        return second;
    }

    public boolean isSecurity() {
        return security;
    }

    public String getSecuritydesc() {
        return securitydesc;
    }

    public boolean isSecuritytrainning() {
        return securitytrainning;
    }

    public String getState() {
        return state;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public boolean isTeachexperience() {
        return teachexperience;
    }

    public boolean isThird() {
        return third;
    }

    public boolean isVialinspector() {
        return vialinspector;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setDivOrgs(Collection divOrgs) {
        this.divOrgs = divOrgs;
    }

    public void setEducation(EducationDataType education) {
        this.education = education;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setGroup(PopulationGroupDataType group) {
        this.group = group;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setMedical(boolean medical) {
        this.medical = medical;
    }

    public void setMovile(String movile) {
        this.movile = movile;
    }

    public void setNurse(boolean nurse) {
        this.nurse = nurse;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setOrganization(ActionOrganizationDataType organization) {
        this.organization = organization;
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

    public void setParamedical(boolean paramedical) {
        this.paramedical = paramedical;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPolice(boolean police) {
        this.police = police;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setPrevent(boolean prevent) {
        this.prevent = prevent;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public void setSecuritydesc(String securitydesc) {
        this.securitydesc = securitydesc;
    }

    public void setSecuritytrainning(boolean securitytrainning) {
        this.securitytrainning = securitytrainning;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public void setTeachexperience(boolean teachexperience) {
        this.teachexperience = teachexperience;
    }

    public void setThird(boolean third) {
        this.third = third;
    }

    public void setVialinspector(boolean vialinspector) {
        this.vialinspector = vialinspector;
    }

    public Iterator getDivOrgsIterator() {
        Collections.sort((List)divOrgs);
        return divOrgs.iterator();
    }

	public String getOthergroup() {
		return othergroup;
	}

	public String getOtherorganization() {
		return otherorganization;
	}

	public void setOthergroup(String othergroup) {
		this.othergroup = othergroup;
	}

	public void setOtherorganization(String otherorganization) {
		this.otherorganization = otherorganization;
	}

}
