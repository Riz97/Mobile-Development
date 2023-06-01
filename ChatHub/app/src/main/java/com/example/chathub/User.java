package com.example.chathub;

public class User {


        public String username;
        public String password;

        public boolean status;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String password,boolean status) {
            this.username = username;
            this.password = password;
            this.status = status;
        }


}
