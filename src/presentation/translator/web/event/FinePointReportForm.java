package presentation.translator.web.event;

import communication.language.Text;

import presentation.translator.web.driver.GenericReportForm;

public class FinePointReportForm extends GenericReportForm {

	private static final long serialVersionUID = 1L;
	
	private String[] types;

	public FinePointReportForm() {
		super();
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getCompleteTypes() {
		String s = null;
		if(types == null)
			return s;
		for(int i = 0; i < types.length; i++) {
			if(types[i].equals("generic.messages.all")) {
				return null;
			}else {
				if(i == 0) {
					s = "'"+types[i]+"'"; 
				}else {
					s += ",'"+types[i]+"'";
				}
			}
		}
		return s;
	}

	public String getTypes(Text messages) {
		String s = null;
		if(types == null)
			return messages.getText("generic.messages.all");
		for(int i = 0; i < types.length; i++) {
			if(types[i].equals("generic.messages.all")) {
				return messages.getText("generic.messages.all");
			}else {
				if(i == 0) {
					s = messages.getText(types[i]); 
				}else {
					s += ","+messages.getText(types[i]);
				}
			}
		}
		return (s == null) ? messages.getText("generic.messages.all") : s;
	}
	
	

}
