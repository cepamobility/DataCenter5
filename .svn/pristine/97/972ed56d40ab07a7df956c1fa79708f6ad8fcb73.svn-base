/**
 * WSCEPASGFLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package business.ws.external.mdp.org.tempuri;

public class WSCEPASGFLocator extends  org.apache.axis.client.Service implements business.ws.external.mdp.org.tempuri.WSCEPASGF {

    public WSCEPASGFLocator() {
    }


    public WSCEPASGFLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSCEPASGFLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSCEPASGFSoap
    private java.lang.String WSCEPASGFSoap_address = "http://200.40.224.51/ws_transp/WSCEPASGF.asmx";

    public java.lang.String getWSCEPASGFSoapAddress() {
        return WSCEPASGFSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSCEPASGFSoapWSDDServiceName = "WSCEPASGFSoap";

    public java.lang.String getWSCEPASGFSoapWSDDServiceName() {
        return WSCEPASGFSoapWSDDServiceName;
    }

    public void setWSCEPASGFSoapWSDDServiceName(java.lang.String name) {
        WSCEPASGFSoapWSDDServiceName = name;
    }

    public business.ws.external.mdp.org.tempuri.WSCEPASGFSoap getWSCEPASGFSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSCEPASGFSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSCEPASGFSoap(endpoint);
    }

    public business.ws.external.mdp.org.tempuri.WSCEPASGFSoap getWSCEPASGFSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            business.ws.external.mdp.org.tempuri.WSCEPASGFSoapStub _stub = new business.ws.external.mdp.org.tempuri.WSCEPASGFSoapStub(portAddress, this);
            _stub.setPortName(getWSCEPASGFSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSCEPASGFSoapEndpointAddress(java.lang.String address) {
        WSCEPASGFSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (business.ws.external.mdp.org.tempuri.WSCEPASGFSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                business.ws.external.mdp.org.tempuri.WSCEPASGFSoapStub _stub = new business.ws.external.mdp.org.tempuri.WSCEPASGFSoapStub(new java.net.URL(WSCEPASGFSoap_address), this);
                _stub.setPortName(getWSCEPASGFSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSCEPASGFSoap".equals(inputPortName)) {
            return getWSCEPASGFSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "WSCEPASGF");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "WSCEPASGFSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSCEPASGFSoap".equals(portName)) {
            setWSCEPASGFSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
