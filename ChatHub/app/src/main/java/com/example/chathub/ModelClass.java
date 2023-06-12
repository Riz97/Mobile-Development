package com.example.chathub;

import android.graphics.ColorSpace;

/* Class the handle the creation of the Chats between users , making visible stauts
and username which are two textView  */

public class ModelClass {
    private String textViewUsername;
    private String textViewStatus;

    ModelClass(String textViewUsername, String textViewStatus) {
        this.textViewUsername = textViewUsername;
        this.textViewStatus = textViewStatus;
    }

    public String getTextViewUsername() {
        return textViewUsername;
    }

    public String getTextViewStatus() {
        return textViewStatus;
    }
}

