package login.infotrade.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.internal.build.AllowSysOut;
import org.keycloak.OAuth2Constants;
import org.keycloak.common.util.KeycloakUriBuilder;
import org.keycloak.constants.ServiceUrlConstants;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Value("${keycloak.auth-server-url}")
	private String keycloakUrl;
	@Value("${keycloak.realm}")
	private String realmName;
	@Value("${keycloak.resource}")
	private String clientId;
	
	public AccessTokenResponse getToken(String username, String password) throws IOException {
		CloseableHttpClient client = HttpClientBuilder.create()
	            .build();
	    try {
	        HttpPost post = new HttpPost(KeycloakUriBuilder.fromUri(keycloakUrl)
	                .path(ServiceUrlConstants.TOKEN_PATH)
	                .build(realmName));
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        formparams.add(new BasicNameValuePair(OAuth2Constants.GRANT_TYPE, "password"));
	        formparams.add(new BasicNameValuePair("username", username));
	        formparams.add(new BasicNameValuePair("password", password));

	        //will obtain a token on behalf of angular-product-app
	        formparams.add(new BasicNameValuePair(OAuth2Constants.CLIENT_ID,clientId));


	        UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams, "UTF-8");
	        post.setEntity(form);
	        CloseableHttpResponse response = client.execute(post);
	        int status = response.getStatusLine()
	                .getStatusCode();
	        org.apache.http.HttpEntity entity = response.getEntity();
	        System.out.println(response);
	        if (status != 200) {
	            throw new IOException("Bad status: " + status);
	        }
	        if (entity == null) {
	            throw new IOException("No Entity");
	        }
	        InputStream is = entity.getContent();
	        try {
	            AccessTokenResponse tokenResponse = JsonSerialization.readValue(is, AccessTokenResponse.class);
	            return tokenResponse;
	        } finally {
	            try {
	                is.close();
	            } catch (IOException ignored) {
	            }
	        }
	    } finally {
	        client.close();
	    }
	}

}
