package com.example.chathub;

import java.util.ArrayList;

public class User {


        public String username;
        public String password;

        public boolean status;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String password, boolean status) {
            this.username = username;
            this.password = password;
            this.status = status;
        }


        public String getUsername () {
            return this.username;
        }

        public boolean isOnline() {
            return this.status;
        }

        private static int lastContactId = 0;
        public static ArrayList<User> createContactsList(int numContacts) {
            ArrayList<User> contacts = new ArrayList<User>();

            for (int i = 1; i <= numContacts; i++) {
                lastContactId++;
                contacts.add(new User("Person ", "", i <= numContacts / 2));
            }

            return contacts;
        }


}
