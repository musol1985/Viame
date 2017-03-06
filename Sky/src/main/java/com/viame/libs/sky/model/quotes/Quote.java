package com.viame.libs.sky.model.quotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.IModelId;


public class Quote implements IModelId{
	public String QuoteId;
	public float MinPrice;
	public boolean Direct;
	private Vuelo vuelo;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date QuoteDateTime;
	
	
	public String print(){
		return "id: "+QuoteId+", precio: "+MinPrice+", vuelo: "+vuelo.print(); 
	}


	@JsonSetter("OutboundLeg")
	public void setVuelo(Vuelo vuelo) {
		this.vuelo=vuelo;
	}


	@JsonGetter("OutboundLeg")
	public Vuelo getVuelo() {
		return vuelo;
	}


	public String getId() {
		return QuoteId;
	}
	
	
}
