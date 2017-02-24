package com.greenfox.peridot.peridot_coz_android.model.request;

public class RegisterRequest {

    String username;
    String password;
    String kingdomname;
    String firstname;
    String lastname;
    String email;

    public RegisterRequest(String username, String password, String kingdomname, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.kingdomname = kingdomname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public RegisterRequest() {}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getKingdomname() {return kingdomname;}

    public void setKingdomname(String kingdomname) {this.kingdomname = kingdomname;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
