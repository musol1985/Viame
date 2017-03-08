package com.viame.libs.sky.model.busqueda;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viame.libs.sky.model.IModelId;
import com.viame.libs.sky.model.location.Place;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Viaje implements IModelId {
	public String Id;

	private List<Vuelo> vuelos;

	private Place lugarSalida;
	private Place lugarLlegada;
	private List<Place> escalas;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date Departure;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date Arrival;

	public int Duration;

	public String getId() {
		return Id;
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

	public String toString() {
		String res = "Viaje (" + (vuelos.size() - 1) + " escalas):" + "\n";
		res += "	Desde: " + lugarSalida.getName() + "(" + Departure + ")" + " A: " + lugarLlegada.getName() + "("
				+ Departure + ") Total=" + Duration + "\n";
		Iterator<Place> itEscalas=escalas.iterator();
		for (Vuelo v : vuelos) {
			res += "		" + v + "\n";
			if(itEscalas.hasNext()){
				res+="				"+itEscalas.next()+"\n";
			}
		}
		return res;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public List<Place> getEscalas() {
		return escalas;
	}

	public void setEscalas(List<Place> escalas) {
		this.escalas = escalas;
	}

}
