package com.viame.libs.sky.core;

import java.io.IOException;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viame.libs.sky.core.listeners.AResponseListener;
import com.viame.libs.sky.core.listeners.HeaderResponseListener;
import com.viame.libs.sky.core.listeners.JSONResponseListener;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.Places;
import com.viame.libs.sky.model.Vuelos;
import com.viame.libs.sky.model.location.Place;

public class SkyHttp {
	private static final String API_KEY="prtl6749387986743898559646983194";//"ed409855663522854824426554739225";
	private static final String URL="http://partners.api.skyscanner.net/apiservices/";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	final static Logger log = LogManager.getLogger(SkyHttp.class);
	

	public Places getLocation(String query) throws IOException, ClientProtocolException{
		return GET(URL+"autosuggest/v1.0/ES/EUR/ES?query="+query, Places.class);
	}
	
	public Vuelos getVueloMasBarato(Place origen, Place destino, String fecha ) throws IOException, ClientProtocolException{
		return getVueloMasBarato(origen.getId(), destino.getId(), fecha);
	}
	
	public Vuelos getVueloMasBarato(Place origen, Place destino, Date fecha ) throws IOException, ClientProtocolException{
		return getVueloMasBarato(origen.getId(), destino.getId(), fecha);
	}
	
	public Vuelos getVueloMasBarato(String origen, String destino, Date fecha ) throws IOException, ClientProtocolException{
		return getVueloMasBarato(origen, destino, formatter.format(fecha));
	}
	
	public Vuelos getVueloMasBarato(String origen, String destino, String fecha ) throws IOException, ClientProtocolException{
		return GET(URL+"browseroutes/v1.0/ES/EUR/ES/"+origen+"/"+destino+"/"+fecha+"/?apiKey="+API_KEY, Vuelos.class);
	}
	
	public Vuelos getVuelo(Place origen, Place destino, Date fecha, int adultos) throws IOException, ClientProtocolException{
		return getVuelo(origen, destino, formatter.format(fecha), adultos);
	}
	
	
	public Vuelos getVuelo(Place origen, Place destino, String fecha, int adultos) throws IOException, ClientProtocolException{
		return getVuelo(origen.getId(), destino.getId(), formatter.format(fecha), adultos, true);
	}
	
	public Vuelos getVuelo(String origen, String destino, String fecha, int adultos, boolean directos) throws IOException, ClientProtocolException{
		HttpPost post=new HttpPost(URL+"/pricing/v1.0");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		post.addHeader("X-Forwarded-For", Inet4Address.getLocalHost().getHostAddress());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("country", "ES"));
	    params.add(new BasicNameValuePair("currency", "EUR"));
	    params.add(new BasicNameValuePair("locale", "ES"));
	    params.add(new BasicNameValuePair("originPlace", origen));
	    params.add(new BasicNameValuePair("destinationPlace", destino));
	    params.add(new BasicNameValuePair("outboundDate", fecha));
	    params.add(new BasicNameValuePair("adults", String.valueOf(adultos)));
	    params.add(new BasicNameValuePair("apiKey", API_KEY));
	    if(directos){
	    	params.add(new BasicNameValuePair("stops", "0"));
	    }

	    log.debug("POST"+URL+"/pricing/v1.0{");
	    if(log.isDebugEnabled()){
		    for(NameValuePair p:params){
		    	log.debug("-------"+p.getName()+"="+p.getValue());
		    }
	    }
	    log.debug("}");
	    
	    post.setEntity(new UrlEncodedFormEntity(params));
		
	    Header url=execute(post, new HeaderResponseListener("location"));
	    if(url!=null){
	    	String sessionKey=url.getValue().replaceAll(URL+"pricing/uk1/v1.0/","");
	    	
	    	System.out.println(sessionKey);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	getSessionInfo(sessionKey);
	    }
	    return null;
		//http://partners.api.skyscanner.net/apiservices/pricing/uk1/v1.0/
	}
	
	public void getSessionInfo(String idSession)throws IOException, ClientProtocolException{
		Busqueda b=GET(URL+"pricing/uk1/v1.0/"+idSession+"?apiKey="+API_KEY, Busqueda.class);
		System.out.println("----------------------------------------------------------------------");
		System.out.println(b.print());
	}

	
	private <T> T GET(String url, Class<T> returnClass) throws IOException, ClientProtocolException{
		log.debug("GET "+url);
		return execute(new HttpGet(url), new JSONResponseListener<T>(returnClass));		
	}
	
	private <T> T execute(HttpUriRequest request, AResponseListener<T> response)  throws IOException, ClientProtocolException{
		CloseableHttpClient http = HttpClients.createDefault();
		
        return http.execute(request, response);
	}
	
	
	public static <T> T convertStringToObject(String object, Class<T> obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(object, obj);
    }
}
