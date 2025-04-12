package com.hubspot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class Properties {

	private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private String authUrl;
    private String tokenUrl;
    private String apiUrl;
    
    //GETTERS
	public String getClientId() {
		return clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public String getScope() {
		return scope;
	}
	public String getAuthUrl() {
		return authUrl;
	}
	public String getTokenUrl() {
		return tokenUrl;
	}
	public String getApiUrl() {
		return apiUrl;
	}
}
