package com.viame.libs.sky;

import com.viame.libs.sky.core.SkyHttp;
import com.viame.libs.sky.model.Vuelos;
import com.viame.libs.sky.model.carriers.Carrier;
import com.viame.libs.sky.model.location.Place;
import com.viame.libs.sky.model.quotes.Quote;

public class Main {

	public static void main(String[] args) throws Exception{
		SkyHttp sHttp=new SkyHttp();
		Place origen=sHttp.getLocation("barce").Places.get(0);
		Place destino=sHttp.getLocation("madrid").Places.get(0);
		
		System.out.println(origen.PlaceId);
		System.out.println(destino.PlaceId);
		
		Vuelos vuelos=sHttp.getVuelo(origen, destino, "2017-03-04");
		for(Carrier c:vuelos.Carriers){
			System.out.println(c.print());
		}
		
		for(Quote q:vuelos.Quotes){
			System.out.println(q.print());
		}
	}

}
