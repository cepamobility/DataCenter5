package business.observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class MessagesManager extends Observable implements Serializable {
	
	private static HashMap<Integer, MessagesManager> observables;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
 
	
	private static MessagesManager instance;
	
	private MessagesManager(){
		
	}
	
	public static synchronized MessagesManager getInstance(){
		if(instance==null){
			instance=new MessagesManager();
			observables=new HashMap<Integer, MessagesManager>();
		}
		
		return instance;
	}
	
	 
    public void addObserver(Integer corpId,WrappedObserver o) throws RemoteException {
    	synchronized (instance) {
			if(!observables.containsKey(corpId)){
				observables.put(corpId, new MessagesManager());
			}
			
		}
    	MessagesManager observable=observables.get(corpId);
		observable.addObserver(o);
        
        System.out.println("Added observer("+corpId+"):" + o);
    }
	
	public void notify(Integer corpId,String msg){
		synchronized (instance) {
			if(!observables.containsKey(corpId)){
				observables.put(corpId, new MessagesManager());
			}
			
		}
		MessagesManager observable=observables.get(corpId);
		observable.notify(msg);
	}
	
	private void notify(String msg){
		synchronized (instance) {
			setChanged();
			notifyObservers(msg);
		}
	}
	 
	
}

