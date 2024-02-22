package com.test.saml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrations;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SamlConfig  {


	@Bean
	public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() {
		return new InMemoryRelyingPartyRegistrationRepository(
				RelyingPartyRegistrations
				.fromMetadataLocation("https://dev-22535368.okta.com/app/exkczm1bg5VV3lvFm5d7/sso/saml/metadata") //local okta account

				.registrationId("okta")
				.assertionConsumerServiceLocation("{baseUrl}/login/saml2/sso/okta")
				.build());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,RelyingPartyRegistrationRepository relyingPartyRegistrationRepository,
			CustomLogoutHandler customLogoutHandler
			) throws Exception {

		 http
		 	.csrf().disable()

		 	.authorizeHttpRequests( 
					(requests) -> 
					{
						try {
							requests
							.requestMatchers("/hellox" )
							.permitAll()	
							.requestMatchers(HttpMethod.POST ,"/login")
							.permitAll()
							.anyRequest()
							.authenticated()
							.and()
							.saml2Login(
									saml2 -> saml2.loginProcessingUrl("/login/saml2/sso/{registrationId}")
									);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				
					);
		 	
		 	
		 	
		 	
		 	
		 	
		 	
		 	
		/* 	
		 	.authorizeRequests()
		 	.anyRequest().authenticated()
		 	.and()
		 	.saml2Login(
		 			//withDefaults());
		 			saml2 -> saml2.loginProcessingUrl("/login/saml2/sso/{registrationId}")
		 			.authenticationRequestUri("/myaia/agent/v1/saml2/authenticate/{registrationId}")
		 			
		 			);
*/
		 	/*
		 	.logout()
				.logoutUrl("/logout/saml2/slo/okta")
				.logoutSuccessUrl("/login/saml2/sso/okta")
				.clearAuthentication(true)
			 	.invalidateHttpSession(true);
		*/ 
		return http.build();
	}
	
	


}
