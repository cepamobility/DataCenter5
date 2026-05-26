/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.log;

import java.util.Date;

import persistence.cenapra.tables.CENAPRAParticipant;

public class CENAPRAParticipantLog {

    private CENAPRAParticipantLogPK pk;

    private Integer action;
    private String comment;
    
    private String firstName;
    private String lastName;
    private Date birthDate;

    private String population;
    private String organization;

    private String state;
    private String city;
    private String delegation;
    private String address;
    private String postalcode;
    private String phone;
    private String movile;
    private String email;

    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;

    private Integer corporation;
    private Integer country;

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

    private String education;
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

    public CENAPRAParticipantLog() {
    }

    public CENAPRAParticipantLog(CENAPRAParticipant participant,int action,String comment,String user) {
        pk = new CENAPRAParticipantLogPK(participant.getId(),user);
        this.action = action;
        this.comment = comment;
        this.firstName = participant.getFirstName();
        this.lastName = participant.getLastName();
        this.birthDate = participant.getBirthDate();
        this.population = (participant.getPopulation() == null) ? null : participant.getPopulation().getResourceBundleId();
        this.organization = (participant.getOrganization() == null) ? null : participant.getOrganization().getResourceBundleId();
        this.state = participant.getState();
        this.city = participant.getCity();
        this.delegation = participant.getDelegation();
        this.address = participant.getAddress();
        this.postalcode = participant.getPostalcode();
        this.phone = participant.getPhone();
        this.movile = participant.getMovile();
        this.email = participant.getEmail();
        this.divOrg1 = (participant.getDivOrg1() == null) ? null : participant.getDivOrg1().getId();
        this.divOrg2 = (participant.getDivOrg2() == null) ? null : participant.getDivOrg2().getId();
        this.divOrg3 = (participant.getDivOrg3() == null) ? null : participant.getDivOrg3().getId();
        this.corporation = participant.getCorpCountry().getPk().getCorporation().getId();
        this.country = participant.getCorpCountry().getPk().getCountry().getId();
        this.inclusionDate = participant.getInclusionDate();
        this.exclusionDate = participant.getExclusionDate();
        this.medical = participant.isMedical();
        this.paramedical = participant.isParamedical();
        this.nurse = participant.isNurse();
        this.police = participant.isPolice();
        this.vialinspector = participant.isVialinspector();
        this.teacher = participant.isTeacher();
        this.prevent = participant.isPrevent();
        this.otherprofession = participant.getOtherprofession();
        this.education = (participant.getEducation() == null) ? null : participant.getEducation().getResourceBundleId();
        this.othereducation = participant.getOthereducation();
        this.security = participant.isSecurity();
        this.securitytrainning = participant.isSecuritytrainning();
        this.securitydesc = participant.getSecuritydesc();
        this.teachexperience = participant.isTeachexperience();
        this.first = participant.isFirst();
        this.second = participant.isSecond();
        this.third = participant.isThird();
        this.otherteach = participant.getOtherteach();
        this.observations = participant.getObservations();
    }

    public Integer getAction() {
        return action;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public String getComment() {
        return comment;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public Integer getCountry() {
        return country;
    }

    public String getDelegation() {
        return delegation;
    }

    public Integer getDivOrg1() {
        return divOrg1;
    }

    public Integer getDivOrg2() {
        return divOrg2;
    }

    public Integer getDivOrg3() {
        return divOrg3;
    }

    public String getEducation() {
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

    public String getFirstName() {
        return firstName;
    }

    public String getPopulation() {
        return population;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public String getLastName() {
        return lastName;
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

    public String getOrganization() {
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

    public CENAPRAParticipantLogPK getPk() {
        return pk;
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

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setDivOrg1(Integer divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(Integer divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(Integer divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setEducation(String education) {
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setOrganization(String organization) {
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

    public void setPk(CENAPRAParticipantLogPK pk) {
        this.pk = pk;
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

}
