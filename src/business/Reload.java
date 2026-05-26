package business;

import java.io.Serializable;

public interface Reload extends Serializable{

	public void loadFilter(Integer corporationid)	throws Exception ;
	
	public void loadText() throws Exception ;	
	
	public Object getReloadKey();	
	
	public void updateFilter() throws Exception ;
        
	/**
	 * Este metodo es invocado para saber si el bean llego al timeout
	 * , en ese caso lanza una excepción al intentar ejecutarla la operación
	 * @throws Exception
	 */
	public void unSuscribe()throws Exception;
}
