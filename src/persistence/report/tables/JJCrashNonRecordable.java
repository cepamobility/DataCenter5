package persistence.report.tables;

import java.io.Serializable;

import communication.report.JJCrashByTypeData;
import communication.report.JJCrashNonRecordableData;

/**
 * @author fcarriquiry
 */
public class JJCrashNonRecordable implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private JJSnapshot snapshot;
	
	private String linelabel;
	
	private Integer count;
	
	   

	 
	public JJCrashNonRecordable() {
		
	}




	 
	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public JJSnapshot getSnapshot() {
		return snapshot;
	}





	public void setSnapshot(JJSnapshot snapshot) {
		this.snapshot = snapshot;
	}





	public String getLinelabel() {
		return linelabel;
	}





	public void setLinelabel(String linelabel) {
		this.linelabel = linelabel;
	}





	public Integer getCount() {
		return count;
	}





	public void setCount(Integer count) {
		this.count = count;
	}





	public JJCrashNonRecordableData getData(){
		JJCrashNonRecordableData data=new JJCrashNonRecordableData();
		
		data.setId(id);

		data.setLinelabel(linelabel);
		
		
		data.setCount(count);
		
		
		return data;
	}
}
