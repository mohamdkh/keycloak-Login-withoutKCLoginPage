package login.infotrade.controllers;

import java.io.IOException;


import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import login.infotrade.entities.Utilisateur;
import login.infotrade.services.LoginService;

@CrossOrigin(origins = "*")
@RestController
public class loginController {
	@Autowired
	LoginService service;
	@RequestMapping(value="/Login", method= RequestMethod.POST)
	public AccessTokenResponse  Login(@RequestBody Utilisateur user) {
		try {
			return service.getToken(user.getUsername(),user.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("erreur");
		}
		return null;
	}

}