package login.infotrade.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//* ce fichier a pour objectif de mentionner que la configuration de keycloack se trouve 
//* au niveau de la configuration spring (applications.properties)
@Configuration
public class KeycloakAdapterConfig {
	@Bean
public KeycloakSpringBootConfigResolver soringBootConfigResolver() {
	return new KeycloakSpringBootConfigResolver();
}
}