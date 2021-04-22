package login.infotrade.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor 
public class Utilisateur {
	private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private Long created;
    
    public Utilisateur(String id, String firstName, String lastName, boolean enabled, Long created) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = (firstName + "." + lastName).toLowerCase();
        this.email = this.username + "@flintstones.com";
        this.password = firstName.toLowerCase();
        this.enabled = enabled;
        this.created = created;
    }


}

