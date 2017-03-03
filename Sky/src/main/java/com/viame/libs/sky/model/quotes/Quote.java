package com.viame.libs.sky.model.quotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;


public class Quote {
	public String QuoteId;
	public float MinPrice;
	public boolean Direct;
	private Vuelo vuelo;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date QuoteDateTime;
	
	
	public String print(){
		return "id: "+QuoteId+", precio: "+MinPrice+", fecha: "+QuoteDateTime+" crr:"+vuelo.getCarriers().get(0).CarrierId; 
	}


	@JsonSetter("OutboundLeg")
	public void setVuelo(Vuelo vuelo) {
		this.vuelo=vuelo;
	}


	@JsonGetter("OutboundLeg")
	public Vuelo getVuelo() {
		return vuelo;
	}
	
	
}
