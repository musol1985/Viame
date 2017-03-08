package com.viame.libs.sky.core.listeners;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HeaderResponseListener extends AResponseListener<Header>{
	final static Logger log = LogManager.getLogger(HeaderResponseListener.class);
	
	private String header;
	
	public HeaderResponseListener(String header, ObjectMapper mapper){
		super(mapper);
		this.header=header;
	}

	public Header onResponseComplete(HttpEntity entity, HttpResponse response) throws IOException, ClientProtocolException{
        return response.getFirstHeader(header);
	}

	public void onError(HttpResponse response) throws IOException, ClientProtocolException{
		throw new ClientProtocolException("Unexpected response status: " + response.getStatusLine().getStatusCode());
	}

}
