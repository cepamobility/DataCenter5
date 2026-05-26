/**
 * Created on 28-nov-2008
 * CEPA
 * DataCenter 5
 */
package communication.cenapra;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

public class CENAPRAParticipantListFullData extends CENAPRAParticipantListData implements Serializable {

    private Date birthdate;
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

    public CENAPRAParticipantListFullData() {
        super();
    }

    public CENAPRAParticipantListFullData(Object[] data) {
        id = (Integer)data[0];
        firstName = (String)data[1];
        lastName = (String)data[2];
        divOrg1 = (String)data[3];
        divOrg2 = (String)data[4];
        birthdate = (Date)data[5];
        population = (String)data[6];
        organization = (String)data[7];
        state = (String)data[8];
        city = (String)data[9];
        delegation = (String)data[10];
        address = (String)data[11];
        postalcode = (String)data[12];
        phone = (String)data[13];
        movile = (String)data[14];
        email = (String)data[15];
        medical = ((Boolean)data[16]).booleanValue();
        paramedical = ((Boolean)data[17]).booleanValue();
        nurse = ((Boolean)data[18]).booleanValue();
        police = ((Boolean)data[19]).booleanValue();
        vialinspector = ((Boolean)data[20]).booleanValue();
        teacher = ((Boolean)data[21]).booleanValue();
        prevent = ((Boolean)data[22]).booleanValue();
        otherprofession = (String)data[23];
        education = (String)data[24];
        othereducation = (String)data[25];
        security = ((Boolean)data[26]).booleanValue();
        securitytrainning = ((Boolean)data[27]).booleanValue();
        securitydesc = (String)data[28];
        teachexperience = ((Boolean)data[29]).booleanValue();
        first = ((Boolean)data[30]).booleanValue();
        second = ((Boolean)data[31]).booleanValue();
        third = ((Boolean)data[32]).booleanValue();
        otherteach = (String)data[33];
        observations = (String)data[34];
        
        if(data.length > 35) {
            activity = (String)data[35];
            date = (Date)data[36];
            city = (String)data[37];
            instructor = (String)data[38];
            if(data.length > 39)
            	registry = (Integer)data[39];
        }
        results = new Hashtable();
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getCity() {
        return city;
    }

    public String getDelegation() {
        return delegation;
    }

    public String getEducation() {
        return education;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFirst() {
        return first;
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

    public boolean isPolice() {
        return police;
    }

    public String getPopulation() {
        return population;
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

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst(boolean first) {
        this.first = first;
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

    public void setPolice(boolean police) {
        this.police = police;
    }

    public void setPopulation(String population) {
        this.population = population;
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
