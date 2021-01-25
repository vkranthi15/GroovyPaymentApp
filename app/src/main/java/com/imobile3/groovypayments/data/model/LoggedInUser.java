package com.imobile3.groovypayments.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final String displayName;
    private final String userName;
    private final String email;

    public LoggedInUser(String userName, String displayName, String email) {
        this.userName = userName;
        this.displayName = displayName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
