package com.projetESGI.projet.Users;

public class UsersContent {

    /**
     * A user item representing a piece of users.
     */
    public static class UsersItem {
        public String username;
        public String password;
        //public String details;

        public UsersItem() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return this.username;
        }
    }
}
