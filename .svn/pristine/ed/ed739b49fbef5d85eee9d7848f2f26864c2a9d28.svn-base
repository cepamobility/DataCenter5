
package ws.assessment.datacenter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assessmentResultList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assessmentResultList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssessmentResult" type="{http://datacenter.assessment.ws/}assessmentResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assessmentResultList", propOrder = {
    "assessmentResult"
})
public class AssessmentResultList {

    @XmlElement(name = "AssessmentResult")
    protected List<AssessmentResult> assessmentResult;

    /**
     * Gets the value of the assessmentResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assessmentResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssessmentResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssessmentResult }
     * 
     * 
     */
    public List<AssessmentResult> getAssessmentResult() {
        if (assessmentResult == null) {
            assessmentResult = new ArrayList<AssessmentResult>();
        }
        return this.assessmentResult;
    }

}
