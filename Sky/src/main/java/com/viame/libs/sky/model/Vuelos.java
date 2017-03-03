package com.viame.libs.sky.model;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;
import com.viame.libs.sky.model.quotes.Quote;

@JsonIgnoreProperties({"Routes","Currencies"})
public class Vuelos {
	public List<Carrier> Carriers;
	public List<Quote> Quotes;
	public List<Place> Places;
	
	private HashMap<String, Carrier> carriersCache;
	private HashMap<String, Place> placesCache;
	
	
	public void load(){
		for(Carrier c:Carriers){
			carriersCache.put(c.CarrierId, c);
		}
		for(Place p:Places){
			placesCache.put(p.PlaceId, p);
		}
		
		for(Quote q: Quotes){
			
		}
	}
}
