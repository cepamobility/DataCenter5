/**
 * Created on 14-feb-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.util;

import communication.security.SecurityConstants;

/**
 * @author jrodriguez
 */
public class SecurityManager {

    public static final String[] permissions =  {
            SecurityConstants.ADMINISTRATOR,
            SecurityConstants.CHANGE_PASSWORD_USER,
            SecurityConstants.CHANGE_USER_ACCESS,
            SecurityConstants.CREATE_CORPORATION,
            SecurityConstants.CREATE_DRIVER,
            SecurityConstants.CREATE_USER,
            SecurityConstants.DELETE_CORPORATION,
            SecurityConstants.DELETE_DRIVER,
            SecurityConstants.DELETE_USER,
            SecurityConstants.EDIT_CORPORATION,
            SecurityConstants.EDIT_DRIVER,
            SecurityConstants.EDIT_USER,
            SecurityConstants.MODIFY_CORPORATION,
            SecurityConstants.MODIFY_DRIVER,
            SecurityConstants.MODIFY_USER,
            SecurityConstants.VIEW_DRIVER_LIST,
            SecurityConstants.VIEW_USER_LIST,
    };
    
    public SecurityManager() {
        super();
    }

}
