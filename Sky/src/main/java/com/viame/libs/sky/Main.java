package com.viame.libs.sky;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.viame.libs.sky.core.SkyHttp;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.Quotes;
import com.viame.libs.sky.model.quotes.Quote;

public class Main {

	public static void main(String[] args) throws Exception{
		SkyHttp sHttp=new SkyHttp();
		/*Place origen=sHttp.getLocation("barce").Places.get(0);
		Place destino=sHttp.getLocation("madrid").Places.get(0);
		
		System.out.println(origen.PlaceId);
		System.out.println(destino.PlaceId);
		
		Vuelos vuelos=sHttp.getVueloMasBarato(origen, destino, "2017-03-08");

		
		for(Carrier c:vuelos.getCarriers()){
			System.out.println(c.print());
		}
		
		for(Quote q:vuelos.getQuotes()){
			System.out.println(q.print());
		}*/
		
		//sHttp.getVuelo("BCN-sky",  "MAD-sky", "2017-03-09", 1, true);
		
		
		/*Busqueda b=SkyHttp.convertStringToObject(getFileWithUtil("test.json"), Busqueda.class);
		System.out.println(b);*/
		/*Quotes vuelos=sHttp.getVueloMasBarato("BCN-sky", "MAD-sky", "2017-03-08");
		for(Quote q:vuelos.getQuotes()){
			System.out.println(q.print());
		}*/
		Busqueda b=sHttp.getVuelo("BCN-sky",  "MAD-sky", "2017-03-09", 1, true);
		System.out.println(b);
	}

	
	  private static String getFileWithUtil(String fileName) {

			String result = "";

			ClassLoader classLoader = Main.class.getClassLoader();
			try {
			    result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			return result;
		  }
}
