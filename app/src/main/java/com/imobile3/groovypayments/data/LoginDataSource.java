package com.imobile3.groovypayments.data;

import com.imobile3.groovypayments.data.entities.UserEntity;
import com.imobile3.groovypayments.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String email, String password) {

        try {
            UserEntity userEntity = DatabaseHelper.getInstance().getDatabase().getUserDao().getUser(email, password);
            if (userEntity != null) {
                // Return logged in user details
                LoggedInUser loggedInUser =
                        new LoggedInUser(userEntity.getUsername(),
                                userEntity.getFirstName() + " " + userEntity.getLastName(),
                                userEntity.getEmail());
                return new Result.Success<>(loggedInUser);
            }
            return new Result.Error(new IOException("No user exists with entered details!!"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
