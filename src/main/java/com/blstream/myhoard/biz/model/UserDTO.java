package com.blstream.myhoard.biz.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {

        @JsonIgnore
        private int id;
        @Length(min = 2, max = 250)
        private String username;
        @NotEmpty
        @Length(min = 2, max = 250)
        private String email;
        @NotEmpty
        @Length(min = 4, max = 250)
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        private String password;

        public int getId() {
                return id;
        }

        public void setId(int id) {
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
