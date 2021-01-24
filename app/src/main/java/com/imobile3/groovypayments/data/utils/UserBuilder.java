package com.imobile3.groovypayments.data.utils;

import com.imobile3.groovypayments.data.entities.UserEntity;

import androidx.annotation.NonNull;

public final class UserBuilder {

    private UserBuilder() {
    }

    @NonNull
    public static UserEntity build(
            long userId,
            String firstName,
            String lastName,
            String userName,
            String email,
            String password
            ) {
        UserEntity result = new UserEntity();
        result.setId(userId);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setUsername(userName);
        result.setEmail(email);
        result.setPassword(password);
        return result;
    }
}
