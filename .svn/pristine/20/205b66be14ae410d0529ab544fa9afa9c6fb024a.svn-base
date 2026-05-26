 /*
 * Created on 28-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.test;

import java.util.Collection;
import java.util.Hashtable;

import javax.naming.Context;

import org.jboss.logging.Logger;

import persistence.datatype.DataTypeReport;
import persistence.datatype.DataTypeReportHome;
import persistence.datatype.DataTypeReportUtil;

import communication.administration.user.UserSessionData;
import communication.datatype.DataTypeConstants;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DatatypeTest {
    static Logger log = Logger.getLogger(DatatypeTest.class);
    
	public static void main(String[] args) {
			try {
				
            //PropertyConfigurator.configure("/home/adocarmo/log4j.properties");
          log.info("EMPECE");
			Hashtable env = new Hashtable();
			env.put(
					Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
				env.put(
					Context.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");
				env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
				
			DataTypeReportHome dtrh = DataTypeReportUtil.getHome(env);
			DataTypeReport dtr = dtrh.create();
			UserSessionData userSesion = new UserSessionData(); 
			
			System.out.println("--------TIPOS DE USO--------");
			Collection c = dtr.findAll(userSesion,DataTypeConstants.TYPEUSE);
			System.out.println("Hay " + c.size() + " tipo de vehículos");
			System.out.println("--------TIPOS DE COMBUSTIBLE--------");
			Collection c1 = dtr.findAll(userSesion,DataTypeConstants.TYPEFUEL);
			System.out.println("Hay " + c1.size() + " tipo de combustibles");
			System.out.println("--------TIPOS DE CENTROS DE COSTOS--------");
			Collection c3 = dtr.findAll(userSesion,DataTypeConstants.TYPECENTERCOST);
			System.out.println("Hay " + c3.size() + " tipo de centros de costos");			
			System.out.println("--------TIPOS DE UNIDADES DE DISTANCIA--------");
			Collection c4 = dtr.findAll(userSesion,DataTypeConstants.TYPEDISTANCEUNIT);
			System.out.println("Hay " + c4.size() + " tipo de unidades de distancia");
			System.out.println("--------TIPOS DE UNIDADES DE VOLUMEN--------");
			Collection c5 = dtr.findAll(userSesion,DataTypeConstants.TYPEVOLUMEUNIT);
			System.out.println("Hay " + c5.size() + " tipo de unidades de volumen");			
			System.out.println("--------TIPOS DE MOTOR--------");
			Collection c6 = dtr.findAll(userSesion,DataTypeConstants.TYPEENGINE);
			System.out.println("Hay " + c6.size() + " tipo de motores");
			System.out.println("--------TIPOS DE VEHICULO--------");
			Collection c7 = dtr.findAll(userSesion,DataTypeConstants.TYPEVEHICLE);
			System.out.println("Hay " + c7.size() + " tipo de vehiculo");			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
