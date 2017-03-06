package com.viame.libs.sky.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.viame.libs.sky.model.IModelId;

public abstract class ADeserializer<T> extends JsonDeserializer<T>{
	private ObjectMapper mapper = new ObjectMapper();
	
	
	protected <T extends IModelId> List<T> getListValues(ObjectNode tree, String fieldName, Class<T> type, HashMap<String, T> cache)throws IOException, JsonProcessingException{
		List<T> res=new ArrayList<T>();
		
		ArrayNode nodes=(ArrayNode)tree.path(fieldName);
		for(JsonNode node:nodes){
			T c=mapper.readValue(node.traverse(), type);
			res.add(c);
			if(cache!=null){
				if(!cache.containsKey(c.getId())){
					cache.put(c.getId(), c);
				}
			}			
		}
		
		return res;
	}
}
