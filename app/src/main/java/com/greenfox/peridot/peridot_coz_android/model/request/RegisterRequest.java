package com.greenfox.peridot.peridot_coz_android.model.request;

public class RegisterRequest {

    String username;
    String password;
    String kingdomName;
    String firstName;
    String lastName;
    String email;

    public RegisterRequest(String username, String password, String kingdomName, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.kingdomName = kingdomName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public RegisterRequest() {}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getKingdomName() {return kingdomName;}

    public void setKingdomName(String kingdomName) {this.kingdomName = kingdomName;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
