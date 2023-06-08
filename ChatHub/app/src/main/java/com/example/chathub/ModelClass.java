package com.example.chathub;

import android.graphics.ColorSpace;



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

