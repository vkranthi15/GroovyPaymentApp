package com.imobile3.groovypayments.data.dao;

import com.imobile3.groovypayments.data.entities.UserEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<UserEntity> getUsers();

    @Query("SELECT * FROM user WHERE email = :email AND password = :pwd")
    UserEntity getUser(String email, String pwd);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<UserEntity> values);

    @Update
    void updateUsers(UserEntity... values);

    @Delete
    void deleteUsers(UserEntity... values);
}
