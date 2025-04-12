package com.hubspot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hubspot.dto.ContactTO;
import com.hubspot.dto.WebHookEvent;
import com.hubspot.service.HubSpotService;

@RestController
@RequestMapping("/api")
public class HubSpotController {

	private final HubSpotService hsService;
	
	public HubSpotController(HubSpotService hsService) {
		this.hsService = hsService;
	}
	
	@GetMapping("/oauth/auth-url")
	public ResponseEntity<String> getAuthUrl() {
		return ResponseEntity.ok(hsService.generateAuthUrl());
	}
	
	@GetMapping("/oauth/callback")
	public ResponseEntity<String> oauthCallback(@RequestParam("code") String code) {
		hsService.exchangeCodeForToken(code);
		return ResponseEntity.ok("Token otido com sucesso");
	}
	
	@PostMapping("/contacts")
	public ResponseEntity<String> createContact(@RequestBody ContactTO contactTO) {
		hsService.createContact(contactTO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Contato criado com sucesso!");
	}
	
	@PostMapping("/webhook")
	public ResponseEntity<Void> receiveWebhook(@RequestBody WebHookEvent whEvent) {
		if ("contact.creation".equals(whEvent.getEventType())) {
			hsService.processContactCreation(whEvent);
		}
		return ResponseEntity.ok().build();
	}
}
