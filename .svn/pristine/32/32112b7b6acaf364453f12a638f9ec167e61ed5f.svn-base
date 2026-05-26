package presentation.translator.web.util;

import org.apache.struts.action.ActionForm;

public class OptionItem extends ActionForm implements Comparable {

    private static final long serialVersionUID = 1L;

    private String label;
	private String value;
	
	
	private boolean selected;

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 *  
	 */
	public OptionItem(String label, String value) {
		this.label = label;
		this.value = value;
	}

    public int compareTo(Object arg0) {
        if(value.length() == 0) {
            return -1;
        }
        if(value.equals("0")) {
            return -1;
        }
        if(value.equals("generic.messages.nodata") || value.equals("generic.messages.all")) {
            return -1;
        }
        if(((OptionItem)arg0).value.equals("generic.messages.nodata") || ((OptionItem)arg0).value.equals("datatype.zone.nodata")) {
            return 1;
        }
        try {
        	String l1 = replacement(label);
        	String l2 = replacement(((OptionItem)arg0).label);
        	return l1.compareTo(l2);
        }catch (Exception e) {
			// TODO: handle exception
		}
        return label.compareTo(((OptionItem)arg0).label);
    }
    
    private String replacement(String label) {
		String value = label.toLowerCase();
		value = value.replace("ã", "a");
		  value = value.replace("â", "a");
		  value = value.replace("ê", "e");
		  value = value.replace("î", "i");
		  value = value.replace("ô", "o");
		  value = value.replace("ô", "o");
		  value = value.replace("û", "u");
		return value;
	}

	public boolean equals(Object o) {
    	boolean result = false;
    	if (OptionItem.class.isInstance(o)){
    		OptionItem oo = (OptionItem)o;
    		if (this.getLabel().equals(oo.getLabel())){
    			result = true;
    		}
    	}
    	
    	return result;
    }

	/**
	 * @return the selected
	 */
	public boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
    
    

}