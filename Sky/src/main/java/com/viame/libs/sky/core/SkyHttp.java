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
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.viame.libs.sky.core.cache.CacheService;
import com.viame.libs.sky.core.config.ConfiguracionSky;
import com.viame.libs.sky.core.listeners.AResponseListener;
import com.viame.libs.sky.core.listeners.HeaderResponseListener;
import com.viame.libs.sky.core.listeners.JSONResponseListener;
import com.viame.libs.sky.deserializer.DesBusqueda;
import com.viame.libs.sky.deserializer.DesQuote;
import com.viame.libs.sky.model.Busqueda;
import com.viame.libs.sky.model.Places;
import com.viame.libs.sky.model.Quotes;
import com.viame.libs.sky.model.location.Place;

public class SkyHttp {	
	private static final String URL="http://partners.api.skyscanner.net/apiservices/";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	private ObjectMapper mapper;
	
	private static final long DEFAULT_SLEEP=2000;
	private static final String DEFAULT_LOCALE="es-ES";
	private static final String DEFAULT_CURRENCY="EUR";
	private static final String DEFAULT_COUNTRY="ES";
	private static final String DEFAULT_API_KEY="prtl6749387986743898559646983194";//"ed409855663522854824426554739225";
	
	final static Logger log = LogManager.getLogger(SkyHttp.class);
	
	private ConfiguracionSky config;
	
	public SkyHttp(){
		this(new ConfiguracionSky(DEFAULT_API_KEY, DEFAULT_CURRENCY, DEFAULT_COUNTRY, DEFAULT_LOCALE, DEFAULT_SLEEP, new CacheService()));
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})	
	public SkyHttp(ConfiguracionSky config){
		this.config=config;
		mapper= new ObjectMapper();				
		SimpleModule skyModule = new SimpleModule()
				.addDeserializer(Quotes.class, new DesQuote(mapper, config.getCache()))
				.addDeserializer(Busqueda.class, new DesBusqueda(mapper, config.getCache()));
		mapper.registerModule(skyModule);
	}
	

	public Places getLocation(String query) throws IOException, ClientProtocolException{
		return GET(URL+"autosuggest/v1.0/ES/EUR/ES?query="+query, Places.class);
	}
	
	public Quotes getVueloMasBarato(Place origen, Place destino, String fecha ) throws IOException, ClientProtocolException{
		return getVueloMasBarato(origen.getId(), destino.getId(), fecha);
	}
	
	public Quotes getVueloMasBarato(Place origen, Place destino, Date fecha ) throws IOException, ClientProtocolException{
		return getVueloMasBarato(origen.getId(), destino.getId(), fecha);
	}
	
	public Quotes getVueloMasBarato(String origen, String destino, Date fecha ) throws IOException, ClientProtocolException{
		return getVueloMasBarato(origen, destino, formatter.format(fecha));
	}
	
	public Quotes getVueloMasBarato(String origen, String destino, String fecha ) throws IOException, ClientProtocolException{
		return GET(URL+"browseroutes/v1.0/"+config.getCountry()+"/"+config.getCurrencyY()+"/"+config.getLocale()+"/"+origen+"/"+destino+"/"+fecha+"/?apiKey="+config.getApiKey(), Quotes.class);
	}
	
	public Busqueda getVuelo(Place origen, Place destino, Date fecha, int adultos) throws IOException, ClientProtocolException{
		return getVuelo(origen, destino, formatter.format(fecha), adultos);
	}
	
	
	public Busqueda getVuelo(Place origen, Place destino, String fecha, int adultos) throws IOException, ClientProtocolException{
		return getVuelo(origen.getId(), destino.getId(), formatter.format(fecha), adultos, true);
	}
	
	public Busqueda getVuelo(String origen, String destino, String fecha, int adultos, boolean directos) throws IOException, ClientProtocolException{
		HttpPost post=new HttpPost(URL+"/pricing/v1.0");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		post.addHeader("X-Forwarded-For", Inet4Address.getLocalHost().getHostAddress());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("country", config.getCountry()));
	    params.add(new BasicNameValuePair("currency", config.getCurrencyY()));
	    params.add(new BasicNameValuePair("locale", config.getLocale()));
	    params.add(new BasicNameValuePair("originPlace", origen));
	    params.add(new BasicNameValuePair("destinationPlace", destino));
	    params.add(new BasicNameValuePair("outboundDate", fecha));
	    params.add(new BasicNameValuePair("adults", String.valueOf(adultos)));
	    params.add(new BasicNameValuePair("apiKey", config.getApiKey()));
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
		
	    Header url=execute(post, new HeaderResponseListener("location", mapper));
	    if(url!=null){
	    	String sessionKey=url.getValue().replaceAll(URL+"pricing/uk1/v1.0/","");
	    	
	    	log.debug("SessionKey:"+sessionKey);
	    	try {
	    		log.debug("Sleeping "+config.getSleep()+"ms");
				Thread.sleep(config.getSleep());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	return getSessionInfo(sessionKey);
	    }
	    return null;
	}
	
	public Busqueda getSessionInfo(String idSession)throws IOException, ClientProtocolException{
		return GET(URL+"pricing/uk1/v1.0/"+idSession+"?apiKey="+config.getApiKey(), Busqueda.class);		
	}

	
	private <T> T GET(String url, Class<T> returnClass) throws IOException, ClientProtocolException{
		log.debug("GET "+url);
		return execute(new HttpGet(url), new JSONResponseListener<T>(returnClass, mapper));		
	}
	
	private <T> T execute(HttpUriRequest request, AResponseListener<T> response)  throws IOException, ClientProtocolException{
		CloseableHttpClient http = HttpClients.createDefault();
		
        return http.execute(request, response);
	}

	
}
