package com.viame.libs.sky.model;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viame.libs.sky.deserializer.BusquedaDes;
import com.viame.libs.sky.model.busqueda.Viaje;
import com.viame.libs.sky.model.busqueda.Vuelo;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BusquedaDes.class)
public class Busqueda {
	public String SessionKey;
	
	
	@JsonProperty("Places")
	private List<Place> lugares;

	@JsonProperty("Carriers")
	private List<Carrier> companyias;
	

	private List<Viaje> viajes; 

	@JsonProperty("Segments")
	private List<Vuelo> vuelos;
	
	
	
	private static HashMap<String, Carrier> companyiasCache=new HashMap<String, Carrier>();
	private static HashMap<String, Place> lugaresCache=new HashMap<String, Place>();
	private static HashMap<String, Vuelo> vuelosCache=new HashMap<String, Vuelo>();
	
	public String print(){
		String res="(";
		for(Carrier c:companyias){
			res+=c+",";
		}
		res+=")\n";
		for(Place p:lugares){
			res+=p+",";
		}
		return "OK "+SessionKey+"\n"+res;
	}


	public List<Carrier> getCompanyias() {
		return companyias;
	}

	public void setCompanyias(List<Carrier> companyias) {
		this.companyias = companyias;
	}

	public List<Place> getLugares() {
		return lugares;
	}

	public void setLugares(List<Place> lugares) {
		this.lugares = lugares;
	}

	public static HashMap<String, Carrier> getCompanyiasCache() {
		return companyiasCache;
	}

	public static HashMap<String, Place> getLugaresCache() {
		return lugaresCache;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}


	public static HashMap<String, Vuelo> getVuelosCache() {
		return vuelosCache;
	}


	public List<Vuelo> getVuelos() {
		return vuelos;
	}


	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	
	
}
