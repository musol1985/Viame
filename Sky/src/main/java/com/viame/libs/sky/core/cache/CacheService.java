package com.viame.libs.sky.core.cache;

import java.util.HashMap;

import com.viame.libs.sky.model.busqueda.Vuelo;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;
import com.viame.libs.sky.model.location.PlaceExt;

public class CacheService implements ICacheService{
	private HashMap<String, Carrier> quotesCarriers=new HashMap<String, Carrier>();
	private HashMap<String, PlaceExt> quotesPlaces=new HashMap<String, PlaceExt>();

	private static HashMap<String, Carrier> busquedaCompanyias=new HashMap<String, Carrier>();
	private static HashMap<String, Place> busquedaLugares=new HashMap<String, Place>();
	private static HashMap<String, Vuelo> busquedaVuelos=new HashMap<String, Vuelo>();
	

	public HashMap<String, Carrier> getQuotesCarriers() {
		return quotesCarriers;
	}

	public void setQuotesCarriers(HashMap<String, Carrier> quotesCarriers) {
		this.quotesCarriers = quotesCarriers;
	}

	public HashMap<String, PlaceExt> getQuotesPlaces() {
		return quotesPlaces;
	}

	public void setQuotesPlaces(HashMap<String, PlaceExt> quotesPlaces) {
		this.quotesPlaces = quotesPlaces;
	}

	public HashMap<String, Carrier> getBusquedaCompanyias() {
		return busquedaCompanyias;
	}

	public HashMap<String, Place> getBusquedaLugares() {
		return busquedaLugares;
	}

	public HashMap<String, Vuelo> getBusquedaVuelos() {
		return busquedaVuelos;
	}
	
	
}
