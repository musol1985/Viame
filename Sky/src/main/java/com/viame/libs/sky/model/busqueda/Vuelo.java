package com.viame.libs.sky.model.busqueda;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
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
	private Place lugarSalida;
	@JsonProperty("DestinationStation")
	private Place lugarLlegada;
	
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("DepartureDateTime")
	private Date fechaSalida;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("ArrivalDateTime")
	private Date fechaLlegada;
	
	@JsonProperty("Carrier")
	private Carrier companyia;
	@JsonProperty("Duration")
	private int duracion;
	@JsonProperty("Duration")
	private int FlightNumber;
	
	
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
	public int getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		FlightNumber = flightNumber;
	}

	@JsonGetter("OriginStation")
	public Place getLugarSalidaJSON() {
		return lugarSalida;
	}
	@JsonSetter("OriginStation")
	public void setLugarSalidaJSON(int lugarSalida) {
		this.lugarSalida = Busqueda.getLugaresCache().get(String.valueOf(lugarSalida));
	}
	
	@JsonGetter("DestinationStation")
	public Place getLugarLlegadaJSON() {
		return lugarLlegada;
	}
	@JsonSetter("DestinationStation")
	public void setLugarLlegadaJSON(int lugarLlegada) {
		this.lugarLlegada = Busqueda.getLugaresCache().get(String.valueOf(lugarLlegada));
	}
	
	@JsonGetter("Carrier")
	public Carrier getCompanyiaJSON() {
		return companyia;
	}
	@JsonSetter("Carrier")
	public void setCompanyiaJSON(int companyia) {
		this.companyia = Busqueda.getCompanyiasCache().get(String.valueOf(companyia));
	}
}
