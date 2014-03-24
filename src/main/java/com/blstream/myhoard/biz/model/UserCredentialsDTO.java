package com.blstream.myhoard.biz.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

public class UserCredentialsDTO {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    @JsonProperty("grant_type")
    private String grantType;
    @JsonProperty("refresh_token")
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String refreshToken;

    public UserCredentialsDTO() {
    }

    public UserCredentialsDTO(String email, String password, String grantType, String refreshToken) {
        this.email = email;
        this.password = password;
        this.grantType = grantType;
        this.refreshToken = refreshToken;
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

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
