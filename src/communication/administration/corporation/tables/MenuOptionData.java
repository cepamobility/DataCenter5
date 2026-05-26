/*
 * Created on 20-aug-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.administration.corporation.tables;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import communication.language.Text;

/**
 * @author fperez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuOptionData implements Serializable,Comparable{

	private Integer id;
    private Integer parentId;
    private String labelKey;
    private String urlOption;
    private String role;
    private String securityConstant;
    private String country;
    private String language;
    private Integer order;
    
    private Collection<MenuOptionData> children;

    public MenuOptionData(Integer id, Integer parentId, String labelKey, String urlOption,String role, String securityConstant,String country,String language,Integer order) {
        this.id = id;
        this.parentId = parentId;
        this.labelKey = labelKey;
        this.urlOption = urlOption;
        this.role = role;
        this.securityConstant = securityConstant;
        this.country=country;
        this.language=language;
        this.order=order;
        children = new LinkedList<MenuOptionData>();
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
	
	public int compareTo(Object anotheritem){
    	MenuOptionData another = (MenuOptionData)anotheritem;
    	return order.compareTo(another.getOrder());
    }


	public Collection<MenuOptionData> getChildren() {
		return children;
	}


	public void setChildren(Collection<MenuOptionData> children) {
		this.children = children;
	}

	public Object[] print(Text messages, int level, Collection<Integer> added) {
		Object[] values = {new Boolean(false), new String()};
		if(children.size() > 0) {
			String label = "";
			Iterator<MenuOptionData> it = children.iterator();
			while(it.hasNext()) {
				Object[] childValue = it.next().print(messages, level+1, added);
				values[0] = new Boolean(((Boolean)values[0]).booleanValue() || ((Boolean)childValue[0]).booleanValue());
				label += "<div class='level"+level+"'>"+childValue[1]+"</div>";
			}
			if(((Boolean)values[0]).booleanValue()) {
				values[1] = "<div class='selected'>"+messages.getText(labelKey)+"</div>"+label;
			}else {
				values[1] = "<div class='noselected'>"+messages.getText(labelKey)+"</div>"+label;
			}
 		}else {
			if(added.contains(id)) {
				values[0] = new Boolean(true);
				values[1] = "<div class='selected'><input type='checkbox' value='"+id+"' checked/>"+messages.getText(labelKey)+"</div>";
			}else {
				values[0] = new Boolean(false);
				values[1] = "<div class='noselected'><input type='checkbox' value='"+id+"'/>"+messages.getText(labelKey)+"</div>";
			}
		}
		return values;
	}
}
