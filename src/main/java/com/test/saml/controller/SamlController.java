package com.test.saml.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamlController {
	
	
	
	@RequestMapping(value = "/")
	public String homehere(@AuthenticationPrincipal  Saml2AuthenticatedPrincipal principal  ) { 
		
		 System.out.println("here -<<<<>>>");
		 System.out.println("principal :" + principal);
		 System.out.println("principal.getName : " + principal.getName());
		 System.out.println("principal :" + principal.getRelyingPartyRegistrationId());
		 System.out.println("principal :" + principal.getAttributes());
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 WebAuthenticationDetails wad = (WebAuthenticationDetails) auth.getDetails();
		 System.out.println("AUth ->>> " + wad.getSessionId());
		 return  wad.getSessionId();
		
    }

	@GetMapping(value = "/protected-resource")
	public String  get() { 

		System.out.println("here ->>> /protected-resource");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("------->  : " + auth.getDetails());
		WebAuthenticationDetails wad = (WebAuthenticationDetails) auth.getDetails();
		return "Session id :  " +  wad.getSessionId();
    }


}
