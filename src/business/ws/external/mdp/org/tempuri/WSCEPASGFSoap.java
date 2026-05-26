/**
 * WSCEPASGFSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package business.ws.external.mdp.org.tempuri;

public interface WSCEPASGFSoap extends java.rmi.Remote {
    public business.ws.external.mdp.org.tempuri.TcCondutorLogado conductor_logeado(java.lang.String idvehiculo, java.util.Calendar fecha) throws java.rmi.RemoteException;
    public java.lang.String situacion_camion(java.lang.String idvehiculo, java.lang.String situacion, java.lang.String descripcion, java.util.Calendar fecha) throws java.rmi.RemoteException;
}
