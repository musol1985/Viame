package com.viame.libs.sky.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IReadNodeListener<T> {
	public void onReadNode(ObjectMapper mapper, JsonNode node, T model);
}
