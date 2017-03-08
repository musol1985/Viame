package com.viame.libs.sky.deserializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.viame.libs.sky.core.cache.ICacheService;
import com.viame.libs.sky.model.Quotes;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.PlaceExt;
import com.viame.libs.sky.model.quotes.Quote;

public class DesQuote<C extends ICacheService> extends ADeserializer<Quotes, C> implements IReadNodeListener<Quote>{

	public DesQuote(ObjectMapper mapper, C cache) {
		super(mapper, cache);
	}

	@Override
	public Quotes deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Quotes quotes=new Quotes();
		
		ObjectNode tree=jp.readValueAsTree();

		List<Carrier> carriers=getListValues(tree, "Carriers", Carrier.class, cache.getQuotesCarriers());
		quotes.setCarriers(carriers);
		
		quotes.setPlaces(getListValues(tree, "Places", PlaceExt.class, cache.getQuotesPlaces()));
				
		quotes.setQuotes(getListValues(tree, "Quotes", Quote.class, this));
		
		return quotes;
	}

	public void onReadNode(ObjectMapper mapper, JsonNode node, Quote model) {
		JsonNode leg=node.path("OutboundLeg");
		ArrayNode carriers=(ArrayNode) leg.path("CarrierIds");
		if(carriers.size()>0){
			Integer carrierId=carriers.get(0).asInt();
			model.getVuelo().setCompanyia(cache.getQuotesCarriers().get(String.valueOf(carrierId)));
		}
		
		JsonNode origen=(JsonNode) leg.path("OriginId");
		model.getVuelo().setLugarSalida(cache.getQuotesPlaces().get(origen.asText()));
		
		JsonNode destino=(JsonNode) leg.path("DestinationId");
		model.getVuelo().setLugarLlegada(cache.getQuotesPlaces().get(destino.asText()));
		
	}

}
