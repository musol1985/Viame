package com.viame.libs.sky.model.busqueda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.IModelId;
import com.viame.libs.sky.model.location.Place;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Viaje implements IModelId{
	public String Id;
	
	private List<Vuelo> vuelos;
	
	private Place lugarSalida;
	private Place lugarLlegada;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date Departure;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date Arrival;
	
	public int Duration;
	
	public String getId() {
		return Id;
	}

	@JsonGetter("OriginStation")
	public Place getOriginStation() {
		return lugarSalida;
	}

	@JsonSetter("OriginStation")
	public void setOriginStationJSON(String originStation) {
		lugarSalida = Busqueda.getLugaresCache().get(originStation);
	}

	
	@JsonGetter("DestinationStation")
	public Place getDestinationStation() {
		return lugarLlegada;
	}

	@JsonSetter("DestinationStation")
	public void setDestinationStationJSON(String destination) {
		lugarLlegada = Busqueda.getLugaresCache().get(destination);
	}

	@JsonGetter("SegmentIds")
	public List<Vuelo> getVuelosJSON() {
		return vuelos;
	}
	
	@JsonSetter("SegmentIds")
	public void setVuelosJSON(List<Integer> vuelos) {
		this.vuelos=new ArrayList<Vuelo>(vuelos.size());
		for(Integer i:vuelos){
			this.vuelos.add(Busqueda.getVuelosCache().get(String.valueOf(i)));
		}		
	}

	
}
