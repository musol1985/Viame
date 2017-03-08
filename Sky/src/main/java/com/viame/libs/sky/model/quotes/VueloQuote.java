package com.viame.libs.sky.model.quotes;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.viame.libs.sky.core.cache.CacheService;
import com.viame.libs.sky.model.busqueda.Vuelo;
import com.viame.libs.sky.model.carriers.Carrier;

public class VueloQuote extends Vuelo{

	@JsonSetter("DepartureDate")
	public void setCompanyiaJSON(Date fecha) {
		this.fechaSalida=fecha;
	}

	
	public String toString(){
		String res="Vuelo: \n";
		res+=" 		"+lugarSalida.getName()+"("+fechaSalida+") - "+lugarLlegada.getName()+" - "+companyia.Name+"\n";		
		return res;
	}
}
