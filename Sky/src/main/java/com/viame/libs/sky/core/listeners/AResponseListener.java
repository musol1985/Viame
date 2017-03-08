package com.viame.libs.sky.core.listeners;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viame.libs.sky.core.SkyHttp;
import com.viame.libs.sky.core.exceptions.TooManyConnectionsException;

public  class AResponseListener<T> implements ResponseHandler<T>{
	final static Logger log = LogManager.getLogger(AResponseListener.class);
	
	private ObjectMapper mapper;
	
	public AResponseListener(ObjectMapper mapper){
		this.mapper=mapper;
	}
	
	
	public T onResponseComplete(HttpEntity entity, HttpResponse response)throws IOException, ClientProtocolException{
		throw new ClientProtocolException("NoImplemented");
	}
	
	public void onError(HttpResponse response)throws IOException, ClientProtocolException{
		throw new ClientProtocolException("NoImplemented");
	}

	public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		int status=response.getStatusLine().getStatusCode();
		log.debug("STATUS RESPONSE: "+status);
		if ((status >= 200 && status < 300)||status==304) {
            HttpEntity entity = response.getEntity();
            return onResponseComplete(entity, response);
        } else if(status==429){
        	throw new TooManyConnectionsException();
        }else{
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
	}
	
	public <T> T fromJSON(String object, Class<T> obj) throws IOException {
        return mapper.readValue(object, obj);
    }
}
