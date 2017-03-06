package com.viame.libs.sky.core.listeners;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public class AsyncTaskResponseListener extends AResponseListener<String>{

	public AsyncTaskResponseListener(long timeout) {
		
	}
	
	@Override
	public String onResponseComplete(HttpEntity entity, HttpResponse response)
			throws IOException, ClientProtocolException {

		return "";
	}

}
