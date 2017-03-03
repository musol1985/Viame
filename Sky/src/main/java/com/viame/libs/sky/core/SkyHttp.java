package com.viame.libs.sky.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viame.libs.sky.model.Places;
import com.viame.libs.sky.model.Vuelos;
import com.viame.libs.sky.model.location.Place;

public class SkyHttp {
	private static final String API_KEY="ed409855663522854824426554739225";
	private static final String URL="http://partners.api.skyscanner.net/apiservices/";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	final static Logger log = LogManager.getLogger(SkyHttp.class);
	

	public Places getLocation(String query) throws IOException, ClientProtocolException{
		return GET(URL+"autosuggest/v1.0/ES/EUR/ES?query="+query, Places.class);
	}
	
	public Vuelos getVuelo(Place origen, Place destino, String fecha ) throws IOException, ClientProtocolException{
		return getVuelo(origen.PlaceId, destino.PlaceId, fecha);
	}
	
	public Vuelos getVuelo(Place origen, Place destino, Date fecha ) throws IOException, ClientProtocolException{
		return getVuelo(origen.PlaceId, destino.PlaceId, fecha);
	}
	
	public Vuelos getVuelo(String origen, String destino, Date fecha ) throws IOException, ClientProtocolException{
		return getVuelo(origen, destino, formatter.format(fecha));
	}
	
	public Vuelos getVuelo(String origen, String destino, String fecha ) throws IOException, ClientProtocolException{
		return GET(URL+"browseroutes/v1.0/ES/EUR/ES/"+origen+"/"+destino+"/"+fecha+"?apiKey="+API_KEY, Vuelos.class);
	}
	
	private <T> T GET(String url, Class<T> returnClass) throws IOException, ClientProtocolException{
		log.debug("GET "+url);
		return execute(new HttpGet(url), returnClass);		
	}
	
	private <T> T execute(HttpUriRequest request, final Class<T> returnClass)  throws IOException, ClientProtocolException{
		CloseableHttpClient http = HttpClients.createDefault();
		
		ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
			public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    if(entity!=null){
                    	String res=EntityUtils.toString(entity);
                    	
	                    if(returnClass==String.class){
	                    	return (T) res;
	                    }else{
	                    	return convertStringToObject(res, returnClass);
	                    }               
                    }else{
                    	return null;
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
			}
        };
        
        return http.execute(request, responseHandler);
	}
	
	
	private <T> T convertStringToObject(String object, Class<T> obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(object, obj);
    }
}
