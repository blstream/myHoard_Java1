package com.blstream.myhoard.biz.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UserDTO {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String id;
    private String username;
    private String email;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + '}';
    }

}
