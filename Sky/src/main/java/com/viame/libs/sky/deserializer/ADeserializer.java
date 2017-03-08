package com.viame.libs.sky.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.viame.libs.sky.core.cache.ICacheService;
import com.viame.libs.sky.model.IModelId;

public abstract class ADeserializer<M, C extends ICacheService> extends JsonDeserializer<M>{
	
	private ObjectMapper mapper;
	protected C cache;
	
	public ADeserializer(ObjectMapper mapper, C cache){
		this.mapper=mapper;
		this.cache=cache;
	}
	
	protected <T extends IModelId> List<T> getListValues(ObjectNode tree, String fieldName, Class<T> type)throws IOException, JsonProcessingException{
		return getListValues(tree, fieldName, type, null, null);
	}
	
	protected <T extends IModelId> List<T> getListValues(ObjectNode tree, String fieldName, Class<T> type, IReadNodeListener<T> nodeListener)throws IOException, JsonProcessingException{
		return getListValues(tree, fieldName, type, null, nodeListener);
	}
	
	protected <T extends IModelId> List<T> getListValues(ObjectNode tree, String fieldName, Class<T> type, HashMap<String, T> cache)throws IOException, JsonProcessingException{
		return getListValues(tree, fieldName, type, cache, null);
	}
	
	protected <T extends IModelId> List<T> getListValues(ObjectNode tree, String fieldName, Class<T> type, HashMap<String, T> cache, IReadNodeListener<T> nodeListener)throws IOException, JsonProcessingException{
		List<T> res=new ArrayList<T>();
		
		ArrayNode nodes=(ArrayNode)tree.path(fieldName);
		for(JsonNode node:nodes){
			T c=readValue(node.traverse(), type);
			if(nodeListener!=null)
				nodeListener.onReadNode(mapper, node, c);
			res.add(c);
			if(cache!=null){
				if(!cache.containsKey(c.getId())){
					cache.put(c.getId(), c);
				}
			}			
		}
		
		return res;
	}
	
	protected <T extends IModelId> T readValue(JsonParser node, Class<T> type)throws IOException, JsonProcessingException{
		return mapper.readValue(node, type);
	}
	
	protected <T extends IModelId> List<T> getListValuesFromCache(JsonNode node, String path, HashMap<String, T> cacheItem){
		ArrayNode listNode=(ArrayNode) node.path(path);
		List<T> res=new ArrayList<T>(listNode.size());
		for(JsonNode vuelo:listNode){									
			res.add(cacheItem.get(vuelo.asText()));
		}
		return res;
	}
}
