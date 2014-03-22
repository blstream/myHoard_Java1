package com.blstream.myhoard.biz.model;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class TokenDTO {

        @JsonIgnore
        private int id;
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("expires_in")
        private int expiresIn;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonIgnore
        private String email;
        @JsonIgnore
        private UserDTO user;
        @JsonIgnore
        private Date createdDate;

        public TokenDTO() {
        }

        public TokenDTO(String accessToken, int expiresIn, String refreshToken) {
                this.accessToken = accessToken;
                this.expiresIn = expiresIn;
                this.refreshToken = refreshToken;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getAccessToken() {
                return accessToken;
        }

        public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
        }

        public int getExpiresIn() {
                return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
                this.expiresIn = expiresIn;
        }

        public String getRefreshToken() {
                return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
                this.refreshToken = refreshToken;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public UserDTO getUser() {
                return user;
        }

        public void setUser(UserDTO user) {
                this.user = user;
        }

        public Date getCreatedDate() {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
                this.createdDate = createdDate;
        }

}
