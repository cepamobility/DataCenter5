
package ws.assessment.datacenter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for courseData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="courseData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psi" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="psiComplete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="testComplete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "courseData", propOrder = {
    "id",
    "name",
    "psi",
    "psiComplete",
    "testComplete",
    "user"
})
public class CourseData {

    protected Integer id;
    protected String name;
    protected boolean psi;
    protected boolean psiComplete;
    protected boolean testComplete;
    protected String user;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the psi property.
     * 
     */
    public boolean isPsi() {
        return psi;
    }

    /**
     * Sets the value of the psi property.
     * 
     */
    public void setPsi(boolean value) {
        this.psi = value;
    }

    /**
     * Gets the value of the psiComplete property.
     * 
     */
    public boolean isPsiComplete() {
        return psiComplete;
    }

    /**
     * Sets the value of the psiComplete property.
     * 
     */
    public void setPsiComplete(boolean value) {
        this.psiComplete = value;
    }

    /**
     * Gets the value of the testComplete property.
     * 
     */
    public boolean isTestComplete() {
        return testComplete;
    }

    /**
     * Sets the value of the testComplete property.
     * 
     */
    public void setTestComplete(boolean value) {
        this.testComplete = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

}
