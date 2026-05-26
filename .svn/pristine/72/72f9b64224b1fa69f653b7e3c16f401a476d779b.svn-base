package business.observer;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class WrappedObserver implements Observer, Serializable {

    private static final long serialVersionUID = 1L;

    private Object oLock=new Object();
    private String msg;
    private boolean isWait=false;
    
    public WrappedObserver(){
    	
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
        	synchronized (oLock) {
        		if(!isWait){
        			o.deleteObserver(this);
        		}else{
        			msg=arg.toString();
        			oLock.notifyAll();
        		}
			}
            
        } catch (Throwable e) {
            System.out.println("Remote exception removing observer:" + this);
            
        }
    }
    
    public String getMessage() {
    	String aux=null;
        try {
        	synchronized (oLock) {
        		isWait=true;
        		oLock.wait();
        		aux=msg;
        		msg=null;
        		System.out.println("Observer messages:"+(aux!=null?aux:"Mensaje vacio"));
    		}
            
        } catch (Throwable e) {
            System.out.println("Remote exception removing observer:" + this);
            
        }finally{
        	isWait=false;
        }
        return aux;
    }
}