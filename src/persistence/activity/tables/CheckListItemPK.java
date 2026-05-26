/**
 * Created on 11-mar-2008
 * CEPA
 * DataCenter 5
 */
package persistence.activity.tables;

import java.io.Serializable;

public class CheckListItemPK implements Serializable {

    private CheckList checkList;
    private Integer item;
    private Integer index;
    
    public CheckListItemPK() {
    }
    
    public CheckList getCheckList() {
        return checkList;
    }

    public Integer getItem() {
        return item;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }
    
    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((checkList == null) ? 0 : checkList.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckListItemPK other = (CheckListItemPK) obj;
		if (checkList == null) {
			if (other.checkList != null)
				return false;
		} else if (!checkList.equals(other.checkList))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
    
    
   
}
