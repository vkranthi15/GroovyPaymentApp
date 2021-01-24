package com.imobile3.groovypayments.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {

    private final String displayName;
    private final String userName;
    private final String email;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String userName, String email) {
        this.displayName = displayName;
        this.userName = userName;
        this.email = email;
    }

    String getDisplayName() {
        return displayName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
