package com.viame.libs.sky.model.busqueda;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.IModelId;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vuelo implements IModelId{
	@JsonProperty("Id")
	private String id;
	
	
	@JsonProperty("OriginStation")
	@JsonIgnore
	protected Place lugarSalida;
	@JsonProperty("DestinationStation")
	@JsonIgnore
	protected Place lugarLlegada;
	
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("DepartureDateTime")
	protected Date fechaSalida;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("ArrivalDateTime")
	private Date fechaLlegada;
	
	@JsonProperty("Carrier")
	@JsonIgnore
	protected Carrier companyia;
	
	@JsonProperty("Duration")
	private int duracion;
	@JsonProperty("FlightNumber")
	private int numeroVuelo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Place getLugarSalida() {
		return lugarSalida;
	}
	public void setLugarSalida(Place lugarSalida) {
		this.lugarSalida = lugarSalida;
	}
	public Place getLugarLlegada() {
		return lugarLlegada;
	}
	public void setLugarLlegada(Place lugarLlegada) {
		this.lugarLlegada = lugarLlegada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Date getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	public Carrier getCompanyia() {
		return companyia;
	}
	public void setCompanyia(Carrier companyia) {
		this.companyia = companyia;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public int getNumeroVuelo() {
		return numeroVuelo;
	}
	public void setNumeroVuelo(int numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}
	
	
	public String toString(){
		String res="Vuelo: \n";
		res+=" 		"+lugarSalida.getName()+"("+fechaSalida+") - "+lugarLlegada.getName()+"("+fechaLlegada+") = "+duracion+"\n";
		res+="		"+numeroVuelo+" "+companyia.Name;
		return res;
	}
}
