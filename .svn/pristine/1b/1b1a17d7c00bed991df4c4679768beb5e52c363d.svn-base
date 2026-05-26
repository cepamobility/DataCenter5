/*
 * Created on 17-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package business;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import communication.administration.user.UserSessionData;
import communication.datatype.DataTypeAccess;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;


/**
 * @author fcarriquiry
 * 
 * Pattern Singlenton
 * 
 * GRASP Controller
 * 
 * TODO To change the template for this generated type comment go to
 */
public class SystemAccessController implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static  HashMap  reloadclasses;
	
	private static SystemAccessController instance;
	
	private SystemAccessController() {
		reloadclasses = new HashMap();
	}
	
	public static SystemAccessController getInstance(){
	
		if(instance==null){
			instance=new SystemAccessController();
		}
		else{
			cleanReloadClasses();
		}

		return instance;
	}
	
	public void reloadFilter(Integer corporationid,UserSessionData userRequest)
	throws DeslogedException, InvalidDataException,RemoteException,
	   CommunicationProblemException, NamingException, CreateException{

			Set set=reloadclasses.keySet();
			Iterator iter=set.iterator();
			while(iter.hasNext()){
				Object key=null;	
				try{	
					key=(Object)iter.next();
					Reload clase=(Reload)reloadclasses.get(key);
					clase.loadFilter(corporationid);
				}catch(Exception e){
					if(key!=null){
						reloadclasses.remove(key);
					}
				}
			}
	}

	public void reloadFilter(Object key, Integer corporationid,UserSessionData userRequest)
	throws DeslogedException, InvalidDataException,RemoteException,
	   CommunicationProblemException, NamingException, CreateException {

		cleanReloadClasses();
		if(reloadclasses.get(key)!=null){
			try{
				Reload clase=(Reload)reloadclasses.get(key);
				clase.loadFilter(corporationid);
			} catch(Exception e){
				//Time Out
				reloadclasses.remove(key);
			}
			
		}

	}

	
	private static void cleanReloadClasses(){

		Set set=reloadclasses.keySet();
		Iterator iter=set.iterator();
		while(iter.hasNext()){
			Object key=null;	
			try{	
				key=(Object)iter.next();
				Reload clase=(Reload)reloadclasses.get(key);
				clase.unSuscribe();
			}catch(Exception e){

				if(key!=null){
					reloadclasses.remove(key);
				}
			}
		}
	}
	
	public void suscribe(Reload clase, Object key){
		reloadclasses.put(key,clase);
	}
	
	public void unSuscribe(Object key){
		reloadclasses.remove(key);
	}
	
}
