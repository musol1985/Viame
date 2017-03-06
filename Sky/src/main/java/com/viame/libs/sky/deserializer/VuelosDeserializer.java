package com.viame.libs.sky.deserializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.viame.libs.sky.model.Vuelos;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.quotes.Quote;

public class VuelosDeserializer extends ADeserializer<Vuelos> {

	@Override
	public Vuelos deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Vuelos vuelos=new Vuelos();
		
		ObjectNode tree=jp.readValueAsTree();

		List<Carrier> carriers=getListValues(tree, "Carriers", Carrier.class, Vuelos.carriersCache);
		vuelos.setCarriers(carriers);
				
		vuelos.setQuotes(getListValues(tree, "Quotes", Quote.class, null));
		
		return vuelos;
	}

}
