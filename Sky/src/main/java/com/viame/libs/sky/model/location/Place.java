package com.viame.libs.sky.model.location;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.IModelId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place implements IModelId{
	private String id;
	private String name;
	private String type;
	
	@JsonGetter("Id")
	public String getId() {
		return id;
	}
	@JsonSetter("Id")
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonGetter("PlaceId")
	public String getPlaceId() {
		return id;
	}
	@JsonSetter("PlaceId")
	public void setPlaceId(String id) {
		this.id = id;
	}
	
	@JsonGetter("Name")
	public String getName() {
		return name;
	}
	@JsonSetter("Name")
	public void setName(String name) {
		this.name = name;
	}
	@JsonGetter("PlaceName")
	public String getPlaceName() {
		return name;
	}
	@JsonSetter("PlaceName")
	public void setPlaceName(String name) {
		this.name = name;
	}
	
	@JsonGetter("Type")
	public String getType() {
		return type;
	}
	@JsonSetter("Type")
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	

	

}
