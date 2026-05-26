/*
 * Created on 27-oct-2005
 */
package persistence.alert.tables;

import java.io.Serializable;

import communication.alert.ContractorDataData;

/**
 * @author fperez
 */
public class ContractorData implements Serializable{

	private Integer id;
	private String name;
	private Integer corporation;
	private String mail;
	private String mail2;
	private Integer state;
    private int sendResume=0;
 
	public ContractorData() {
		
	}
	
	/**
     * @param id
     * @param parentId
     * @param labelKey
     * @param urlOption
     */
    public ContractorData(Integer id, Integer corporation, String mail, Integer state) {
        this.id = id;
        this.corporation = corporation;
        this.mail = mail;
        this.state = state;
     }


    /**
	 * @param data
	 */
	public ContractorData(ContractorDataData data) {
		
        id = data.getId();
        corporation = data.getCorporation();
        mail = data.getMail();
        state = data.getState();
        mail2 = data.getMail2();
	}

	
	
	
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returs the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @return Returs the corporation
	 */
	public Integer getCorporation() {
		return corporation;
	}
	
	/**
	 * @return Returs the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @return Returs the state
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}
	
	/**
	 * @param mail The mail to set.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * @param state The state to set.
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
    /**
	 * @return ContractorDataData
	 */
	public ContractorDataData getContractorDataData() {
	    
		ContractorDataData contractorDataData= new ContractorDataData(id,corporation,mail,state);
		contractorDataData.setMail2(mail2);

        return contractorDataData;
	}

    
    
    public void updateData(ContractorDataData data) {
        corporation = data.getCorporation();
        mail = data.getMail();
        state = data.getState();
        mail2 = data.getMail2();
    }

	/**
	 * @return the mail2
	 */
	public String getMail2() {
		return mail2;
	}

	/**
	 * @param mail2 the mail2 to set
	 */
	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public int getSendResume() {
		return sendResume;
	}

	public void setSendResume(int sendResume) {
		this.sendResume = sendResume;
	}
	
    
}
