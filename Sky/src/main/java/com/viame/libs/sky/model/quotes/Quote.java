package com.viame.libs.sky.model.quotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.IModelId;
import com.viame.libs.sky.model.busqueda.Vuelo;


public class Quote implements IModelId{
	public String QuoteId;
	public float MinPrice;
	public boolean Direct;
	
	@JsonProperty("OutboundLeg")
	private VueloQuote vuelo;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date QuoteDateTime;
	
	
	public String print(){
		return "id: "+QuoteId+", precio: "+MinPrice+", vuelo: "+vuelo; 
	}




	public String getId() {
		return QuoteId;
	}




	public VueloQuote getVuelo() {
		return vuelo;
	}




	public void setVuelo(VueloQuote vuelo) {
		this.vuelo = vuelo;
	}
	
	
}
