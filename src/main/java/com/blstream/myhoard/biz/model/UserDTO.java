package com.blstream.myhoard.biz.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UserDTO {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String id;
    private String username;
    private String email;
    private String password;
    private Boolean publicAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @JsonProperty("public_account")
    public Boolean isPublicAccount() {
        return publicAccount;
    }

    @JsonProperty("public_account")
    public void setPublicAccount(Boolean publicAccount) {
        this.publicAccount = publicAccount;
    }
}
