package com.viame.libs.sky.model.carriers;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.model.IModelId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Carrier implements IModelId{
	private String id;
	public String Name;	
	public String Code;
	public String ImageUrl;
	public String DisplayCode;
	
	@JsonGetter("Id")
	public String getId() {
		return id;
	}
	@JsonSetter("Id")
	public void setId(String id) {
		this.id = id;
	}
	@JsonGetter("CarrierId")
	public String getCarrierId() {
		return id;
	}
	@JsonSetter("Carrier")
	public void setCarrierId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Carrier [id=" + id + ", Name=" + Name + ", Code=" + Code + ", ImageUrl=" + ImageUrl + ", DisplayCode="
				+ DisplayCode + "]";
	}
	

}
