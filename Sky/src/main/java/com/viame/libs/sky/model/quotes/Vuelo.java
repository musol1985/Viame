package com.viame.libs.sky.model.quotes;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.Vuelos;
import com.viame.libs.sky.model.carriers.Carrier;

public class Vuelo {
	
	private List<Carrier> carriers;
	public String OriginId;
	public String DestinationId;
	public String DepartureDate;
	
	@JsonSetter("CarrierIds")
	public void setCarriersJson(List<String> carriersIds){
		carriers=new ArrayList<Carrier>(carriersIds.size());
		for(String s:carriersIds){
			
			carriers.add(Vuelos.carriersCache.get(s));
		}
	}
	
	public void setCarriers(List<Carrier> carriers){
		this.carriers=carriers;
	}

	@JsonGetter("CarrierIds")
	public List<Carrier> getCarriers() {
		return carriers;
	}
	
	
}
