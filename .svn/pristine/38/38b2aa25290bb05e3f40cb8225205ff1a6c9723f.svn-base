/*
 * Created on 14-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.language;

import java.io.Serializable;
import java.util.ListResourceBundle;

import persistence.datatype.tables.TypeVehicle;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Text extends ListResourceBundle implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String YES = "generic.messages.yes";
    public static final String NO = "generic.messages.no";

    private  Object[][]contents = new String[0][0];

    public Text() {
        super();
    }
	
	/* (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	public Object[][] getContents() {
		// TODO Auto-generated method stub
		return contents;
	}

	public void setContents(Object[][] contents) {
	    this.contents = contents;
	    super.setParent(null);
	}
	
	public String getText(String key) {
        if(key == null || key.length() == 0) {
            return "";
        }
	    String label = null;
	    try {
	        label = super.getString(key);
         //   System.out.println(key+" -> "+label);
	    }catch(Exception e) {
           // System.out.println("-------------------------------------------------");
            System.out.println(key);
        //    System.out.println("-------------------------------------------------");
	        return key;
	    }
	    return (label==null) ? key : label;
	}

    /**
     * @param string
     * @return
     */
    public static String getMessage(String string)throws Exception {
    
    	String language = System.getProperty("user.language");
        if(string.equals("generic.error.permissions")) {
            if(language.toLowerCase().equals("es")) {
                return "Sus permisos en esta corporaci√≥n no le permiten acceder a esta p√°gina";
            } else if(language.toLowerCase().equals("pt")) {
                return "Sus permisos en esta corporaci√≥n no le permiten acceder a esta p√°gina";
            } else {
                return "You are not allowed to access this page";
            }
        }
        if(string.equals("generic.error.login")) {
            if(language.toLowerCase().equals("es")) {
                return "Se produjo un error en el proceso de login, el usuario o contrase√±a no son correctos.";
            } else if(language.toLowerCase().equals("pt")) {
                return "Se ocorrer um erro no processo de login, o usu√°rio ou senha n√£o est√£o corretos.";
            } else {
                return "An error has been detected on the log in process, username or password are not correct.";
            }
        }
        if(string.equals("generic.error.login.retry")) {
            if(language.toLowerCase().equals("es")) {
                return "Si desear reintentar presione ";
            } else if(language.toLowerCase().equals("pt")) {
                return "Se desejar corrigir pressione ";
            } else {
                return "If you want to retry press ";
            }
        }
        if(string.equals("generic.messages.here")) {
            if(language.toLowerCase().equals("es")) {
                return "aqu√≠.";
            } else if(language.toLowerCase().equals("pt")) {
                return "aqui.";
            } else {
                return "here.";
            }
        }
        if(string.equals("generic.messages.session.expired")) {
            if(language.toLowerCase().equals("es")) {
                return "La sesi√≥n ha expirado";
            } else if(language.toLowerCase().equals("pt")) {
                return "The sesion ha expirado";
            } else{
                return "The session has expired";
            }
        }
        if(string.equals("generic.messages.accept")) {
            if(language.toLowerCase().equals("es")) {
                return "Aceptar";
            } else if(language.toLowerCase().equals("pt")) {
                return "Aceptar";
            } else{
                return "Accept";
            }
        }
        if(string.equals("generic user.userdata.passconfirm")) {
            if(language.toLowerCase().equals("en")) {
                return "Invalid Password";
            } else if(language.toLowerCase().equals("es")) {
                return "Contrase√±a invalida";
            } else if(language.toLowerCase().equals("pt")) {
                return "Senha inv√°lida";
            }
        }  
        if(string.equals("user.error.shortpassword")) {
            if(language.toLowerCase().equals("en")) {
                return "Password Too Short";
            } else if(language.toLowerCase().equals("es")) {
                return "Clave Demasiado Breve";
            } else if(language.toLowerCase().equals("pt")) {
                return "Senha Muito Curta";
            }
        }     
        if(string.equals("user.error.invalidpassword")) {
            if(language.toLowerCase().equals("en")) {
                return "Password must contain upper and lower case letters, and numbers or at least one of the following: @#$%^&+=.";
            } else if(language.toLowerCase().equals("es")) {
                return "La clave debe componerse de letras may√∫sculas, min√∫sculas, y n√∫meros o alguno de estos s√≠mbolos: @#$%^&+=.";
            } else if(language.toLowerCase().equals("pt")) {
                return "A senha deve conter letras mai√∫sculas e min√∫sculas, e n√∫meros o ao menos um destes s√≠mbolos: @#$%^&+=.";
            }
        }      

        return "Error";
    }

	public static String getVehicleAllowed(TypeVehicle vehicle) {
		if(vehicle == null) return "---";
		return getVehicleAllowed(vehicle.getResourceBundleId());
	}
	
	public static String getVehicleAllowed(String typeVehicle) {
		if(typeVehicle == null) return "---";
		if(typeVehicle.equals("datatype.vehicletype.nodata")) return "Sem Dados";
		if(typeVehicle.equals("datatype.vehicletype.novehicle")) return "Sem Veiculo";
		if(typeVehicle.equals("datatype.vehicletype.ambulance")) return "Ambul‚ncia";
		if(typeVehicle.equals("datatype.vehicletypecomb.barelyresposible")) return "Apenas Respons·vel pelo VeÌculo";
		if(typeVehicle.equals("datatype.vehicletype.automobile")) return "AutomÛvel";
		if(typeVehicle.equals("datatype.vehicletypecomb.carpickups")) return "AutomÛvel + Pick up Leve";
		if(typeVehicle.equals("datatype.vehicletypecomb.carpickupstruck")) return "AutomÛvel + Pick up Leve + Caminh„o";
		if(typeVehicle.equals("datatype.vehicletypecomb.carpickupspickupm")) return "AutomÛvel + Pick up Leve + Pick up MÈdia";
		if(typeVehicle.equals("datatype.vehicletypecomb.carpickupm")) return "AutomÛvel + Pick up MÈdia";
		if(typeVehicle.equals("datatype.vehicletypecomb.carpickup")) return "AutomÛvel + Reboque";
		if(typeVehicle.equals("datatype.vehicletype.bycycle")) return "Bicicleta";
		if(typeVehicle.equals("datatype.vehicletype.pickup")) return "Caminhonete - Pick-up";
		if(typeVehicle.equals("datatype.vehicletype.truck")) return "Caminh„o";
		if(typeVehicle.equals("datatype.vehicletypecomb.truckpickupspickupm")) return "Caminh„o + Pick up Leve + Pick up MÈdia";
		if(typeVehicle.equals("datatype.vehicletypecomb.truckvanpickupm")) return "Caminh„o + Pick up MÈdia + Van";
		if(typeVehicle.equals("datatype.vehicletypecomb.truckpickup")) return "Caminh„o + Reboque";
		if(typeVehicle.equals("datatype.vehicletypecomb.truckpickupspickupmvan")) return "Caminh„o + Van + Pick up Leve + Pick up MÈdia";
		if(typeVehicle.equals("datatype.vehicletypecomb.vanemergency")) return "Caminh„o + VeÌculo de EmergÍncia";
		if(typeVehicle.equals("datatype.vehicletype.tractortrailer")) return "Caminh„o c/reboque";
		if(typeVehicle.equals("datatype.vehicletype.vansemireboque")) return "Caminh„o com Semi Reboque";
		if(typeVehicle.equals("datatype.vehicletype.van")) return "Furg„o";
		if(typeVehicle.equals("datatype.vehicletype.minibus")) return "MicroÙnibus";
		if(typeVehicle.equals("datatype.vehicletype.minivan")) return "Mini-Van";
		if(typeVehicle.equals("datatype.vehicletype.motorcycle")) return "Motocicleta";
		if(typeVehicle.equals("datatype.vehicletype.other")) return "Outros";
		if(typeVehicle.equals("datatype.vehicletype.pickuplow")) return "Pick up Leve";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupspickup")) return "Pick up Leve + Reboque";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupmpickupstruck")) return "Pick up Media + Reboque + Caminh„o";
		if(typeVehicle.equals("datatype.vehicletype.pickupmiddle")) return "Pick up MÈdia";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupmvan")) return "Pick up MÈdia + Van";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupmpickups")) return "Pick up MÈdia + Pick up Leve";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupmpickupspickup")) return "Pick up MÈdia + Pick up Leve + Reboque";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupmpickup")) return "Pick up MÈdia + Reboque";
		if(typeVehicle.equals("datatype.vehicletype.pick")) return "Reboque";
		if(typeVehicle.equals("datatype.vehicletype.semireboque")) return "Semi Reboque";
		if(typeVehicle.equals("datatype.vehicletype.trator")) return "Trator";
		if(typeVehicle.equals("datatype.vehicletype.utility")) return "Utilit·rio esportivo - Caminhoneta";
		if(typeVehicle.equals("datatype.vehicletypecomb.automobileutility")) return "Utilit·rio esportivo - Caminhoneta + AutomÛvel";
		if(typeVehicle.equals("datatype.vehicletype.van.automobile")) return "Van";
		if(typeVehicle.equals("datatype.vehicletypecomb.truckvanemergency")) return "Van + VeÌculo de EmergÍncia + Caminh„o";
		if(typeVehicle.equals("datatype.vehicletype.van.emergency")) return "VeÌculo de EmergÍncia";
		if(typeVehicle.equals("datatype.vehicletype.bus")) return "‘nibus";
		if(typeVehicle.equals("datatype.vehicletypecomb.carvan")) return "AutomÛvel + Van";
		if(typeVehicle.equals("datatype.vehicletypecomb.pickupmtruck")) return "Pick up MÈdia + Caminh„o";
		return "---";
	}
}
