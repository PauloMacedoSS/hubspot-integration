package com.hubspot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hubspot.config.Properties;
import com.hubspot.dto.ContactTO;
import com.hubspot.dto.WebHookEvent;


@Service
public class HubSpotService {

	private final Properties properties;
    private final RestTemplate restTemplate;
    private String accessToken;
    
    public HubSpotService(Properties properties) {
        this.properties = properties;
        this.restTemplate = new RestTemplate();
    }
    
    public String generateAuthUrl() {
        return String.format("%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=code",
                properties.getAuthUrl(),
                properties.getClientId(),
                properties.getRedirectUri(),
                properties.getScope());
    }
    
    public void exchangeCodeForToken(String code) {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "authorization_code");
        body.put("client_id", properties.getClientId());
        body.put("client_secret", properties.getClientSecret());
        body.put("redirect_uri", properties.getRedirectUri());
        body.put("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(properties.getTokenUrl(), entity, Map.class);

        this.accessToken = (String) response.getBody().get("access_token");
    }
    
    public void createContact(ContactTO contactTO) {
        String url = properties.getApiUrl() + "/crm/v3/objects/contacts";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("email", contactTO.getEmail());
        propertiesMap.put("firstname", contactTO.getFirstName());
        propertiesMap.put("lastname", contactTO.getLastName());

        Map<String, Object> body = new HashMap<>();
        body.put("properties", propertiesMap);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(url, entity, String.class);
    }
    
    public void processContactCreation(WebHookEvent event) {
        // Processar evento conforme necessário
        System.out.println("Webhook recebido: " + event);
    }
}
