# keycloak Login without KC Login Page
With this approach the backend  will take the role of Keycloak's adapter by communicating the token to it for validation of user access, . This process aims to use our 
own authentication page without going through the form provided by the Keycloak server. 
But this representation does not allow us to take advantage of all the functionality offered 
by the keycloak dependency configured at the client side
