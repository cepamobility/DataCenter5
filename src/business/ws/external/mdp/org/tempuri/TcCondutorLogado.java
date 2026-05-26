/**
 * TcCondutorLogado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package business.ws.external.mdp.org.tempuri;

public class TcCondutorLogado  implements java.io.Serializable {
    private java.lang.String ci;

    private java.util.Calendar fecha_login;

    private java.lang.String error;

    public TcCondutorLogado() {
    }

    public TcCondutorLogado(
           java.lang.String ci,
           java.util.Calendar fecha_login,
           java.lang.String error) {
           this.ci = ci;
           this.fecha_login = fecha_login;
           this.error = error;
    }


    /**
     * Gets the ci value for this TcCondutorLogado.
     * 
     * @return ci
     */
    public java.lang.String getCi() {
        return ci;
    }


    /**
     * Sets the ci value for this TcCondutorLogado.
     * 
     * @param ci
     */
    public void setCi(java.lang.String ci) {
        this.ci = ci;
    }


    /**
     * Gets the fecha_login value for this TcCondutorLogado.
     * 
     * @return fecha_login
     */
    public java.util.Calendar getFecha_login() {
        return fecha_login;
    }


    /**
     * Sets the fecha_login value for this TcCondutorLogado.
     * 
     * @param fecha_login
     */
    public void setFecha_login(java.util.Calendar fecha_login) {
        this.fecha_login = fecha_login;
    }


    /**
     * Gets the error value for this TcCondutorLogado.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this TcCondutorLogado.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TcCondutorLogado)) return false;
        TcCondutorLogado other = (TcCondutorLogado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ci==null && other.getCi()==null) || 
             (this.ci!=null &&
              this.ci.equals(other.getCi()))) &&
            ((this.fecha_login==null && other.getFecha_login()==null) || 
             (this.fecha_login!=null &&
              this.fecha_login.equals(other.getFecha_login()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCi() != null) {
            _hashCode += getCi().hashCode();
        }
        if (getFecha_login() != null) {
            _hashCode += getFecha_login().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TcCondutorLogado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "tcCondutorLogado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ci");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha_login");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "fecha_login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
