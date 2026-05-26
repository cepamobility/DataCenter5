/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.administration.corporation.tables;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fcarriquiry
 * 
 */
public class EasyTrackEventData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer vehicle;
    private Integer driver;
    private String ci;
    private Date fechaHora;
    private String matricula;
    private String empresa;
    private String dEvento;
    private Integer velDef;
    private Integer velGps;
    private Integer velVehiculo;
    private Long viaje;
    private Long idReporte;
    
	
	public EasyTrackEventData() {
	}


	
	
	public Integer getDriver() {
		return driver;
	}




	public void setDriver(Integer driver) {
		this.driver = driver;
	}




	public String getCi() {
		return ci;
	}




	public void setCi(String ci) {
		this.ci = ci;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getVehicle() {
		return vehicle;
	}


	public void setVehicle(Integer vehicle) {
		this.vehicle = vehicle;
	}


	public Date getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getdEvento() {
		return dEvento;
	}


	public void setdEvento(String dEvento) {
		this.dEvento = dEvento;
	}


	public Integer getVelDef() {
		return velDef;
	}


	public void setVelDef(Integer velDef) {
		this.velDef = velDef;
	}


	public Integer getVelGps() {
		return velGps;
	}


	public void setVelGps(Integer velGps) {
		this.velGps = velGps;
	}


	public Integer getVelVehiculo() {
		return velVehiculo;
	}


	public void setVelVehiculo(Integer velVehiculo) {
		this.velVehiculo = velVehiculo;
	}


	public Long getViaje() {
		return viaje;
	}


	public void setViaje(Long viaje) {
		this.viaje = viaje;
	}


	public Long getIdReporte() {
		return idReporte;
	}


	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}


	 


	
	 
}