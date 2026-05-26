/**
 * Created on 13-dic-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;

/**
 * @author jrodriguez
 */
public class CountryData implements Serializable, Comparable { 

    private static final long serialVersionUID = 1L;

    public static final String BRASIL = "datatype.country.br";
    public static final String ARGENTINA = "datatype.country.ar";
    public static final String PARAGUAY = "datatype.country.py";
    public static final String BOLIVIA = "datatype.country.bo";
    public static final String URUGUAY = "datatype.country.uy";
    public static final String MEXICO = "datatype.country.mx";
    public static final String COLOMBIA = "datatype.country.co";
    public static final String GUATEMALA="datatype.country.gt";
    public static final String EL_SALVADOR="datatype.country.sv";
    public static final String NICARAGUA="datatype.country.ni";
    public static final String COSTA_RICA="datatype.country.cr";

    public static final Integer URUGUAY_ID = new Integer(31);
    public static final Integer BRASIL_ID = new Integer(32);
    public static final Integer ARGENTINA_ID = new Integer(33);
	public static final Integer PARAGUAY_ID = new Integer(34);
	public static final Integer CHILE_ID = new Integer(54);
	public static final Integer PERU_ID = new Integer(64);
	public static final Integer ECUADOR_ID = new Integer(37);
	public static final Integer COLOMBIA_ID = new Integer(55);
	public static final Integer COSTA_RICA_ID = new Integer(56);
	public static final Integer EL_SALVADOR_ID = new Integer(67);
	public static final Integer GUATEMALA_ID = new Integer(59);
	public static final Integer HONDURAS_ID = new Integer(61);
	public static final Integer MEXICO_ID = new Integer(42);
	public static final Integer NICARAGUA_ID = new Integer(62);
	public static final Integer VENEZUELA_ID = new Integer(39);

	public static final Integer SYNGENTA_REGION = new Integer(443956);



    
    private Integer id;
    private String resourceKey;
    
    public CountryData() {
    }
    
    /**
     * @param id
     * @param resourceKey
     */
    public CountryData(Integer id, String resourceKey) {
        super();
        this.id = id;
        this.resourceKey = resourceKey;
    }
    
    /**
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @return Returns the resourceKey.
     */
    public String getResourceKey() {
        return resourceKey;
    }

    public int compareTo(Object arg0) {
        return resourceKey.compareTo(((CountryData)arg0).getResourceKey());
    }
}
