package com.viame.libs.sky.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viame.libs.sky.deserializer.DesQuote;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.PlaceExt;
import com.viame.libs.sky.model.quotes.Quote;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotes{
	private List<Carrier> Carriers;
	private List<Quote> Quotes;
	public List<PlaceExt> Places;

	
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
	public List<PlaceExt> getPlaces() {
		return Places;
	}
	public void setPlaces(List<PlaceExt> places) {
		Places = places;
	}

}
