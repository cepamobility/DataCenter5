package communication.util;


public class MetadataColumn {
	
	public static String[] DATE_FORMATS=new String[]{"dd/MM/yyyy","dd-MM-yyyy","yyyy/MM/dd","yyyy-MM-dd"};
	
	public enum MetadataColumnType{
		STRING,
		INTEGER,
		DOUBLE,
		DATE	
	}
	
	private String key;
	private int index=-1;
	private String format="dd/MM/yyyy";
	private boolean optional=true;
	private MetadataColumnType type;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	public MetadataColumnType getType() {
		return type;
	}
	public void setType(MetadataColumnType type) {
		this.type = type;
	}
	
	

}
