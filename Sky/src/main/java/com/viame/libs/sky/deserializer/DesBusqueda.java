package com.viame.libs.sky.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.viame.libs.sky.core.cache.ICacheService;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.busqueda.Viaje;
import com.viame.libs.sky.model.busqueda.Vuelo;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;

public class DesBusqueda<C extends ICacheService> extends ADeserializer<Busqueda, C> {

	public DesBusqueda(ObjectMapper mapper, C cache) {
		super(mapper, cache);
	}

	@Override
	public Busqueda deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Busqueda b=new Busqueda();
		
		ObjectNode tree=jp.readValueAsTree();

		b.setCompanyias(getListValues(tree, "Carriers", Carrier.class, cache.getBusquedaCompanyias()));	
		b.setLugares(getListValues(tree, "Places", Place.class, cache.getBusquedaLugares()));
		
		b.setVuelos(getListValues(tree, "Segments", Vuelo.class, cache.getBusquedaVuelos(), new IReadNodeListener<Vuelo>() {
			public void onReadNode(ObjectMapper mapper, JsonNode node, Vuelo model) {
				JsonNode origen=(JsonNode) node.path("OriginStation");
				JsonNode destino=(JsonNode) node.path("DestinationStation");
				JsonNode companyia=(JsonNode) node.path("Carrier");
				
				model.setCompanyia(cache.getBusquedaCompanyias().get(companyia.asText()));
				model.setLugarLlegada(cache.getBusquedaLugares().get(destino.asText()));
				model.setLugarSalida(cache.getBusquedaLugares().get(origen.asText()));
			}
		}));

		b.setViajes(getListValues(tree, "Legs", Viaje.class, new IReadNodeListener<Viaje>() {
			public void onReadNode(ObjectMapper mapper, JsonNode node, Viaje model) {
				model.setVuelos(getListValuesFromCache(node, "SegmentIds", cache.getBusquedaVuelos()));
				
				model.setEscalas(getListValuesFromCache(node, "Stops", cache.getBusquedaLugares()));
				
				JsonNode origen=(JsonNode) node.path("OriginStation");
				JsonNode destino=(JsonNode) node.path("DestinationStation");
				model.setLugarLlegada(cache.getBusquedaLugares().get(destino.asText()));
				model.setLugarSalida(cache.getBusquedaLugares().get(origen.asText()));
			}
		}));

		return b;
	}
	
	


}
