/**
 * Created on 16-dic-2005
 * CEPA
 * DataCenter 5
 */
package persistence.util;

import java.util.Collection;

import communication.util.LabelValueData;

/**
 * @author jrodriguez
 */
public interface LabelValue {

    /**
     * @param idContainer
     * @param data
     * @return
     */
    LabelValueData getData(Object idContainer, Collection data);

}
