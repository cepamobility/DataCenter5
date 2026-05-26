/**
 * Created on 15-feb-2006
 * CEPA
 * DataCenter 5
 */
package communication.administration;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author jrodriguez
 */
public class RoleData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static final String DRIVER = "driver";
    public static final String FLEET_DRIVER = "fleetdriver";
    public static final String MANAGER = "Gerente";
    public static final String CONTRACTOR="contractors"; // Contratista
    public static final String CDS="cds"; // Contratista
    public static final String REPORTERJJ="jjreporter";
    public static final String DPACHECKLIST="dpachecklist";
	public static final String MOBILECHECKLIST = "mobilechecklist";
    public static final String INTERNALCHECKLIST="internalchecklist";
    public static final String CONSULTING = "consulting";
    
	private String name;
    private boolean cepaUser;
    private Collection permissions;
    
    public RoleData(String name,boolean cepaUser,Collection permissions) {
        this.name = name;
        this.cepaUser = cepaUser;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }
    
    public Collection getPermissions() {
        return permissions;
    }

    /**
     * @return Returns the cepaUser.
     */
    public boolean isCepaUser() {
        return cepaUser;
    }

    /**
     * @param cepaUser The cepaUser to set.
     */
    public void setCepaUser(boolean cepaUser) {
        this.cepaUser = cepaUser;
    }

    public boolean isDriver() {
        return name.equals("driver");
    }
    
    /*
     * Devuelve true si el rol es de instructor
     */
    public boolean isInstructor()
    {
    	return name.equals("instructor");
    }
    
    public boolean isInstructorDPA()
    {
    	return name.equals("instructor") || name.equals("dpachecklist") || name.equals("instructorsuperviser");
    }

    public boolean isInstructorActivity()
    {
    	return name.equals("instructor") || name.equals("instructorsuperviser");
    }

    /**
     * 
     * @return
     */
    public boolean isContractor(){
    	return name.equals(CONTRACTOR);
    }
    
    /*
     * Devuelve true si el rol es Administrador de Monsanto
     */
    public boolean isMonsantoAdministrator()
    {
    	return name.equals("monsantoadministrator");
    }
    
    /*
     * return true if the rol is AccountManager
     */
    public boolean isAccountManager()
    {
    	return name.equals("accountmanager");
    }
    
    /*
     * return true if the rol is administrator
     */
    public boolean isAdministrator()
    {
    	return name.equals("administrator");
    }
    
    /*
     * return true if the rol is gerente
     */
    public boolean isGerente()
    {
    	return (name.equals("Gerente"))  ;
    }

	public boolean isFleetDriver() {
        return name.equals("fleetdriver");
	}

	public boolean isCepaSystemUser() {
        return name.equals(RoleData.CDS);
	}

	public boolean isReporterJJ() {
        return name.equals(REPORTERJJ);
	}

	public boolean isDPACheckList() {
		return name.equals(DPACHECKLIST) ||  name.equals(INTERNALCHECKLIST) ;
	}    
    
	public boolean isInternalCheckList() {
		return name.equals(INTERNALCHECKLIST) ;
	}

	public boolean isMobileCheckList() {
		return name.equals(MOBILECHECKLIST);
	}

	public boolean isConsulting() {
        return name.equals(CONSULTING);
	}    
}

