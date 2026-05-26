/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.group.tables;


public class GroupElement {

    private Integer id;

    private AbstractGroup groupElement;
    private AbstractGroup element;

    public GroupElement() {
    }
    
    /**
     * @param groupElement
     * @param element
     */
    public GroupElement(AbstractGroup groupElement, AbstractGroup element) {
        this.groupElement = groupElement;
        this.element = element;
    }


    public AbstractGroup getElement() {
        return element;
    }

    public AbstractGroup getGroupElement() {
        return groupElement;
    }

    public Integer getId() {
        return id;
    }

    public void setElement(AbstractGroup element) {
        this.element = element;
    }

    public void setGroupElement(AbstractGroup groupElement) {
        this.groupElement = groupElement;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
