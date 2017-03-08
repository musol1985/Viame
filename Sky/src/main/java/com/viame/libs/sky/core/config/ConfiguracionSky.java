package com.viame.libs.sky.core.config;

import com.viame.libs.sky.core.cache.ICacheService;

public class ConfiguracionSky {
	private String apiKey;
	private String currencyY;
	private String country;
	private String locale;
	private long sleep;
	private ICacheService cache;
	
	public ConfiguracionSky(String apiKey, String currencyY, String country, String locale, long sleep, ICacheService cache) {
		this.apiKey = apiKey;
		this.currencyY = currencyY;
		this.country = country;
		this.locale = locale;
		this.sleep=sleep;
		this.cache=cache;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getCurrencyY() {
		return currencyY;
	}
	public void setCurrencyY(String currencyY) {
		this.currencyY = currencyY;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public long getSleep() {
		return sleep;
	}

	public void setSleep(long sleep) {
		this.sleep = sleep;
	}

	public ICacheService getCache() {
		return cache;
	}

	public void setCache(ICacheService cache) {
		this.cache = cache;
	}
	
	
}
