package com.viame.libs.sky.core.cache;

import java.util.HashMap;

import com.viame.libs.sky.model.busqueda.Vuelo;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;
import com.viame.libs.sky.model.location.PlaceExt;

public interface ICacheService {
	public HashMap<String, PlaceExt> getQuotesPlaces();
	public HashMap<String, Carrier> getQuotesCarriers();

	public HashMap<String, Carrier> getBusquedaCompanyias();
	public HashMap<String, Place> getBusquedaLugares();
	public HashMap<String, Vuelo> getBusquedaVuelos();
}
