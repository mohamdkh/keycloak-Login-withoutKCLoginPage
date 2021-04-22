package login.infotrade.security;


import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter{

	//* ici on définit la stratégie de gestion des sessions
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//* ici je dis à spring security que la gestion des rôles et des utilisateurs c'est keycloak qui va les géurer
		auth.authenticationProvider(keycloakAuthenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
		 http.cors()
         .and()
         .csrf()
         .disable()
         .authorizeRequests()
         .anyRequest().permitAll();
	}

}
