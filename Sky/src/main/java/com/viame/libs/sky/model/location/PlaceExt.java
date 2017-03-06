package com.viame.libs.sky.model.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceExt extends Place {
	
	public String CountryId;
	public String RegionId;
	public String CityId;
	public String CityName;
	public String CountryName;
	public String IataCode;
	public String SkyscannerCode;
	

	@Override	
	public String toString() {
		
		return super.toString()+"PlaceExt [CountryId=" + CountryId + ", RegionId=" + RegionId + ", CityId=" + CityId + ", CityName="
				+ CityName + ", CountryName=" + CountryName + ", IataCode=" + IataCode + ", SkyscannerCode="
				+ SkyscannerCode + "]";
	}
	
	
}
