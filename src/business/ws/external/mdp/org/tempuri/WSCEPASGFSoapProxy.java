package business.ws.external.mdp.org.tempuri;

public class WSCEPASGFSoapProxy implements business.ws.external.mdp.org.tempuri.WSCEPASGFSoap {
  private String _endpoint = null;
  private business.ws.external.mdp.org.tempuri.WSCEPASGFSoap wSCEPASGFSoap = null;
  
  public WSCEPASGFSoapProxy() {
    _initWSCEPASGFSoapProxy();
  }
  
  public WSCEPASGFSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSCEPASGFSoapProxy();
  }
  
  private void _initWSCEPASGFSoapProxy() {
    try {
      wSCEPASGFSoap = (new business.ws.external.mdp.org.tempuri.WSCEPASGFLocator()).getWSCEPASGFSoap();
      if (wSCEPASGFSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSCEPASGFSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSCEPASGFSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSCEPASGFSoap != null)
      ((javax.xml.rpc.Stub)wSCEPASGFSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public business.ws.external.mdp.org.tempuri.WSCEPASGFSoap getWSCEPASGFSoap() {
    if (wSCEPASGFSoap == null)
      _initWSCEPASGFSoapProxy();
    return wSCEPASGFSoap;
  }
  
  public business.ws.external.mdp.org.tempuri.TcCondutorLogado conductor_logeado(java.lang.String idvehiculo, java.util.Calendar fecha) throws java.rmi.RemoteException{
    if (wSCEPASGFSoap == null)
      _initWSCEPASGFSoapProxy();
    return wSCEPASGFSoap.conductor_logeado(idvehiculo, fecha);
  }
  
  public java.lang.String situacion_camion(java.lang.String idvehiculo, java.lang.String situacion, java.lang.String descripcion, java.util.Calendar fecha) throws java.rmi.RemoteException{
    if (wSCEPASGFSoap == null)
      _initWSCEPASGFSoapProxy();
    return wSCEPASGFSoap.situacion_camion(idvehiculo, situacion, descripcion, fecha);
  }
  
  
}