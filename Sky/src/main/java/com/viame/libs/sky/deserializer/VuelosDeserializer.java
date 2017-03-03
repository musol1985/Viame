package com.viame.libs.sky.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.viame.libs.sky.model.Vuelos;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;
import com.viame.libs.sky.model.quotes.Quote;

public class VuelosDeserializer extends JsonDeserializer<Vuelos> {

	@Override
	public Vuelos deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Vuelos vuelos=new Vuelos();
		
		Iterator<Carrier> iCarriers = jp.readValuesAs(Carrier.class);
		Iterator<Place> iPlaces = jp.readValuesAs(Place.class);
		Iterator<Quote> iQuotes = jp.readValuesAs(Quote.class);

		List<Carrier> carriers = new ArrayList<Carrier>();
		while (iCarriers.hasNext()){
			Carrier c=iCarriers.next();
			carriers.add(c);
			if(!Vuelos.carriersCache.containsKey(c.CarrierId))
				Vuelos.carriersCache.put(c.CarrierId, c);
		}
		vuelos.setCarriers(carriers);
		
		List<Quote> quotes = new ArrayList<Quote>();
		while (iQuotes.hasNext()){
			Quote q=iQuotes.next();
			quotes.add(q);
		}
		vuelos.setQuotes(quotes);
		
		return vuelos;
	}

}
