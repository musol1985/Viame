package com.viame.libs.sky.core.listeners;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.viame.libs.sky.core.exceptions.TooManyConnectionsException;

public  class AResponseListener<T> implements ResponseHandler<T>{
	public T onResponseComplete(HttpEntity entity, HttpResponse response)throws IOException, ClientProtocolException{
		throw new ClientProtocolException("NoImplemented");
	}
	
	public void onError(HttpResponse response)throws IOException, ClientProtocolException{
		throw new ClientProtocolException("NoImplemented");
	}

	public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		int status=response.getStatusLine().getStatusCode();
		if ((status >= 200 && status < 300)||status==304) {
            HttpEntity entity = response.getEntity();
            return onResponseComplete(entity, response);
        } else if(status==429){
        	throw new TooManyConnectionsException();
        }else{
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
	}
}
