package com.blstream.myhoard.db.model;

import java.util.Date;

public class TokenDS {

        private int id;
        private String accessToken;
        private int expiresIn;
        private String refreshToken;
        private Date createdDate;
        private UserDS user;

        public TokenDS() {
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

        public Date getCreatedDate() {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
                this.createdDate = createdDate;
        }

        public UserDS getUser() {
                return user;
        }

        public void setUser(UserDS user) {
                this.user = user;
        }
}
