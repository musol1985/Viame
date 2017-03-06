package com.viame.libs.sky.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.busqueda.Viaje;
import com.viame.libs.sky.model.busqueda.Vuelo;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;

public class BusquedaDes extends ADeserializer<Busqueda> {

	@Override
	public Busqueda deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Busqueda b=new Busqueda();
		
		ObjectNode tree=jp.readValueAsTree();

		b.setCompanyias(getListValues(tree, "Carriers", Carrier.class, Busqueda.getCompanyiasCache()));	
		b.setLugares(getListValues(tree, "Places", Place.class, Busqueda.getLugaresCache()));
		b.setVuelos(getListValues(tree, "Segments", Vuelo.class, Busqueda.getVuelosCache()));

		b.setViajes(getListValues(tree, "Legs", Viaje.class, null));

		return b;
	}
	
	


}
