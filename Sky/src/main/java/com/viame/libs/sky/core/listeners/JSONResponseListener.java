package com.viame.libs.sky.core.listeners;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.viame.libs.sky.core.SkyHttp;

public class JSONResponseListener<T> extends AResponseListener<T>{
	final static Logger log = LogManager.getLogger(JSONResponseListener.class);
	
	private Class<T> returnType;
	
	public JSONResponseListener(Class<T> returnType){
		this.returnType=returnType;
	}

	public T onResponseComplete(HttpEntity entity, HttpResponse response) throws IOException, ClientProtocolException{
        if(entity!=null){
        	String res=EntityUtils.toString(entity);
        	
            if(returnType==String.class){
            	log.debug("Response: "+res);
            	return (T) res;
            }else{
            	log.debug("Response: "+res);
            	return SkyHttp.convertStringToObject(res, returnType);
            }               
        }else{
        	return null;
        }
	}

	public void onError(HttpResponse response) throws IOException, ClientProtocolException{
		throw new ClientProtocolException("Unexpected response status: " + response.getStatusLine().getStatusCode());
	}

}
