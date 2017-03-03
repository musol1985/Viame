package com.viame.libs.sky.model.quotes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("OutboundLeg")
public class Quote {
	public String QuoteId;
	public float MinPrice;
	public boolean Direct;
	public Vuelo OutboundLeg;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date QuoteDateTime;
	
	
	public String print(){
		return "id: "+QuoteId+", precio: "+MinPrice+", fecha: "+QuoteDateTime; 
	}
}
