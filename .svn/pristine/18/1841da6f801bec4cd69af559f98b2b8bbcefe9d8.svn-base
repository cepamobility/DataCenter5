/*
 * Created on 27-oct-2005
 */
package persistence.administration.corporation.tables;

import communication.administration.corporation.tables.MenuOptionData;

/**
 * @author fperez
 */
public class MenuOption {

	private Integer id;
	private Integer parentId;
	private String labelKey;
	private String urlOption;
	private String role;
	private String securityConstant;
	private String country;
	private String language;
	private Integer order;
	public MenuOption() {
		
	}
	
	/**
     * @param id
     * @param parentId
     * @param labelKey
     * @param urlOption
     */
    public MenuOption(Integer id, Integer parentId, String labelKey, String urlOption,String role,String securityConstant,String country,String language, Integer order) {
        this.id = id;
        this.parentId = parentId;
        this.labelKey = labelKey;
        this.urlOption = urlOption;
        this.role=role;
        this.securityConstant=securityConstant;
        this.country=country;
        this.language=language;
        this.order=order;
     }


    /**
	 * @param data
	 */
	public MenuOption(MenuOptionData data) {
		
        id = data.getId();
        parentId = data.getParentId();
        labelKey = data.getLabelKey();
        urlOption = data.getUrlOption();
        role = data.getRole();
        securityConstant = data.getSecurityConstant();
        country=data.getCountry();
        language=data.getLanguage();
	    order= data.getOrder();
	}

	 /**
	 * @return Returs the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @return Returs the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	
	/**
	 * @return Returs the labelKey
	 */
	public String getLabelKey() {
		return labelKey;
	}
	
	/**
	 * @return Returs the urlOption
	 */
	public String getUrlOption() {
		return urlOption;
	}
	
	
	/**
	 * @return Returs the rol
	 */
	public String getRole() {
		return role;
	}
	
	
	/**
	 * @return Returs the securityConstant
	 */
	public String getSecurityConstant() {
		return securityConstant;
	}
	
	/**
	 * @return Returs the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @return Returs the language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * @return Returs the order
	 */
	public Integer getOrder() {
		return order;
	}
	

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @param labelKey The labelKey to set.
	 */
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	
	/**
	 * @param urlOption The urlOption to set.
	 */
	public void setUrlOption(String urlOption) {
		this.urlOption = urlOption;
	}
	
	/**
	 * @param role The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	
	/**
	 * @param securityConstant The securityConstant to set.
	 */
	public void setSecurityConstant(String securityConstant) {
		this.securityConstant = securityConstant;
	}
	
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * @param language The language to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	

    /**
	 * @return menuOptionData
	 */
	public MenuOptionData getMenuOptionData() {
	    
	    MenuOptionData menuOptionData= new MenuOptionData(id,parentId,labelKey,urlOption,role,securityConstant,country,language,order);

        return menuOptionData;
	}

    
    
    public void updateData(MenuOptionData data) {
        parentId = data.getParentId();
        labelKey = data.getLabelKey();
        urlOption = data.getUrlOption();
        role = data.getRole();
        securityConstant = data.getSecurityConstant();
        country=data.getCountry();
        language=data.getLanguage();
        order=data.getOrder();
    }

    
}
