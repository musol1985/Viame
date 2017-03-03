package com.viame.libs.sky.model;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viame.libs.sky.deserializer.VuelosDeserializer;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;
import com.viame.libs.sky.model.quotes.Quote;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = VuelosDeserializer.class)
public class Vuelos {
	private List<Carrier> Carriers;
	private List<Quote> Quotes;
	public List<Place> Places;
	
	public static HashMap<String, Carrier> carriersCache=new HashMap<String, Carrier>();
	public static HashMap<String, Place> placesCache;
	public List<Carrier> getCarriers() {
		return Carriers;
	}
	public void setCarriers(List<Carrier> carriers) {
		Carriers = carriers;
	}
	public List<Quote> getQuotes() {
		return Quotes;
	}
	public void setQuotes(List<Quote> quotes) {
		Quotes = quotes;
	}

	
	
}
